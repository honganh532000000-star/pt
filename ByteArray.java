public class ByteArray {
   private static final byte DEFAULT_SIZE = 16;
   public static final byte BOOLEAN_SIZE = 1;
   public static final byte BYTE_SIZE = 1;
   public static final byte CHAR_SIZE = 2;
   public static final byte SHORT_SIZE = 2;
   public static final byte INT_SIZE = 4;
   public static final byte LONG_SIZE = 8;
   private int currentPos;
   private byte[] data;

   public ByteArray() {
      this(16);
   }

   public ByteArray(int size) {
      this.currentPos = 0;
      this.data = new byte[size];
      this.currentPos = 0;
   }

   public ByteArray(byte[] src) {
      this.currentPos = 0;
      this.data = src;
      this.currentPos = 0;
   }

   public void writeBoolean(boolean val) {
      this.ensureCapacity(1);
      this.data[this.currentPos++] = (byte)(val ? 1 : 0);
   }

   public void writeByte(byte val) {
      this.ensureCapacity(1);
      this.data[this.currentPos++] = val;
   }

   public void writeByte(int val) {
      this.writeByte((byte)val);
   }

   public void writeChar(char c) {
      this.ensureCapacity(2);
      this.data[this.currentPos + 1] = (byte)(c >>> 0);
      this.data[this.currentPos + 0] = (byte)(c >>> 8);
      this.currentPos += 2;
   }

   public void writeShort(short val) {
      this.ensureCapacity(2);
      this.data[this.currentPos + 1] = (byte)(val >>> 0);
      this.data[this.currentPos + 0] = (byte)(val >>> 8);
      this.currentPos += 2;
   }

   public void writeShort(int val) {
      this.writeShort((short)val);
   }

   public void writeInt(int val) {
      this.ensureCapacity(4);
      this.data[this.currentPos + 3] = (byte)(val >>> 0);
      this.data[this.currentPos + 2] = (byte)(val >>> 8);
      this.data[this.currentPos + 1] = (byte)(val >>> 16);
      this.data[this.currentPos + 0] = (byte)(val >>> 24);
      this.currentPos += 4;
   }

   public void writeLong(long val) {
      this.ensureCapacity(8);
      this.data[this.currentPos + 7] = (byte)((int)(val >>> 0));
      this.data[this.currentPos + 6] = (byte)((int)(val >>> 8));
      this.data[this.currentPos + 5] = (byte)((int)(val >>> 16));
      this.data[this.currentPos + 4] = (byte)((int)(val >>> 24));
      this.data[this.currentPos + 3] = (byte)((int)(val >>> 32));
      this.data[this.currentPos + 2] = (byte)((int)(val >>> 40));
      this.data[this.currentPos + 1] = (byte)((int)(val >>> 48));
      this.data[this.currentPos + 0] = (byte)((int)(val >>> 56));
      this.currentPos += 8;
   }

   public void writeByteArray(byte[] src) {
      if (src != null) {
         this.ensureCapacity(src.length);
         System.arraycopy(src, 0, this.data, this.currentPos, src.length);
         this.currentPos += src.length;
      }
   }

   public void writeUTF(String str) {
      this.writeByteArray(getByteArrFromUTF(str));
   }

   public boolean readBoolean() {
      return this.data[this.currentPos++] != 0;
   }

   public byte readByte() {
      return this.data[this.currentPos++];
   }

   public char readChar() {
      char c = (char)(((this.data[this.currentPos + 1] & 255) << 0) + ((this.data[this.currentPos + 0] & 255) << 8));
      this.currentPos += 2;
      return c;
   }

   public short readShort() {
      short s = (short)(((this.data[this.currentPos + 1] & 255) << 0) + ((this.data[this.currentPos + 0] & 255) << 8));
      this.currentPos += 2;
      return s;
   }

   public int readInt() {
      int i = ((this.data[this.currentPos + 3] & 255) << 0) + ((this.data[this.currentPos + 2] & 255) << 8) + ((this.data[this.currentPos + 1] & 255) << 16) + ((this.data[this.currentPos + 0] & 255) << 24);
      this.currentPos += 4;
      return i;
   }

   public long readLong() {
      long l = (((long)this.data[this.currentPos + 7] & 255L) << 0) + (((long)this.data[this.currentPos + 6] & 255L) << 8) + (((long)this.data[this.currentPos + 5] & 255L) << 16) + (((long)this.data[this.currentPos + 4] & 255L) << 24) + (((long)this.data[this.currentPos + 3] & 255L) << 32) + (((long)this.data[this.currentPos + 2] & 255L) << 40) + (((long)this.data[this.currentPos + 1] & 255L) << 48) + (((long)this.data[this.currentPos + 0] & 255L) << 56);
      this.currentPos += 8;
      return l;
   }

   public byte[] readByteArray(int length) {
      if (length == -1 || this.currentPos + length > this.data.length) {
         length = this.data.length - this.currentPos;
      }

      byte[] temp = new byte[length];
      System.arraycopy(this.data, this.currentPos, temp, 0, length);
      this.currentPos += length;
      return temp;
   }

   public byte[] readByteArray(int off, int length) {
      if (length == -1 || off + length > this.data.length) {
         length = this.data.length - off;
      }

      byte[] temp = new byte[length];
      System.arraycopy(this.data, off, temp, 0, length);
      return temp;
   }

   public String readUTF() {
      int utflen = this.readUnsignedShort();
      if (utflen == -1) {
         System.err.println("Error!! ByteArray: readUTF()");
         return "ERROR";
      } else {
         byte[] bytearr = (byte[])null;
         char[] chararr = (char[])null;
         bytearr = this.readByteArray(utflen);
         if (utflen > bytearr.length) {
            return null;
         } else {
            chararr = new char[utflen];
            int count = 0;

            int c;
            int chararr_count;
            for(chararr_count = 0; count < utflen; chararr[chararr_count++] = (char)c) {
               c = bytearr[count] & 255;
               if (c > 127) {
                  break;
               }

               ++count;
            }

            while(count < utflen) {
               c = bytearr[count] & 255;
               byte char2;
               switch(c >> 4) {
               case 0:
               case 1:
               case 2:
               case 3:
               case 4:
               case 5:
               case 6:
               case 7:
                  ++count;
                  chararr[chararr_count++] = (char)c;
               case 8:
               case 9:
               case 10:
               case 11:
               default:
                  break;
               case 12:
               case 13:
                  count += 2;
                  char2 = bytearr[count - 1];
                  chararr[chararr_count++] = (char)((c & 31) << 6 | char2 & 63);
                  break;
               case 14:
                  count += 3;
                  char2 = bytearr[count - 2];
                  int char3 = bytearr[count - 1];
                  chararr[chararr_count++] = (char)((c & 15) << 12 | (char2 & 63) << 6 | (char3 & 63) << 0);
               }
            }

            return new String(chararr, 0, chararr_count);
         }
      }
   }

   private void ensureCapacity(int length) {
      if (this.currentPos + length >= this.data.length) {
         byte[] tmp = new byte[this.data.length + 2 * length];
         System.arraycopy(this.data, 0, tmp, 0, this.data.length);
         this.data = tmp;
      }

   }

   public static byte[] getByteArrFromUTF(String str) {
      int strlen = str.length();
      int utflen = 0;
      int count = 0;

      char c;
      for(int i = 0; i < strlen; ++i) {
         c = str.charAt(i);
         if (c >= 1 && c <= 127) {
            ++utflen;
         } else if (c > 2047) {
            utflen += 3;
         } else {
            utflen += 2;
         }
      }

      byte[] bytearr = new byte[utflen + 2];
      int var7 = count + 1;
      bytearr[count] = (byte)(utflen >>> 8 & 255);
      bytearr[var7++] = (byte)(utflen >>> 0 & 255);
      int i = false;

      int i;
      for(i = 0; i < strlen; ++i) {
         c = str.charAt(i);
         if (c < 1 || c > 127) {
            break;
         }

         bytearr[var7++] = (byte)c;
      }

      for(; i < strlen; ++i) {
         c = str.charAt(i);
         if (c >= 1 && c <= 127) {
            bytearr[var7++] = (byte)c;
         } else if (c > 2047) {
            bytearr[var7++] = (byte)(224 | c >> 12 & 15);
            bytearr[var7++] = (byte)(128 | c >> 6 & 63);
            bytearr[var7++] = (byte)(128 | c >> 0 & 63);
         } else {
            bytearr[var7++] = (byte)(192 | c >> 6 & 31);
            bytearr[var7++] = (byte)(128 | c >> 0 & 63);
         }
      }

      return bytearr;
   }

   private int readUnsignedByte() {
      return this.data[this.currentPos++] & 255;
   }

   private int readUnsignedShort() {
      int ch1 = this.readUnsignedByte();
      int ch2 = this.readUnsignedByte();
      return (ch1 | ch2) < 0 ? -1 : (ch1 << 8) + (ch2 << 0);
   }

   public byte[] toByteArray() {
      return this.currentPos < this.data.length ? this.readByteArray(0, this.currentPos) : this.data;
   }

   public void resetPosition() {
      this.currentPos = 0;
   }

   public void close() {
      this.data = null;
   }

   public static int[] bytesToInts(byte[] bytes) {
      if (bytes != null && bytes.length >= 4) {
         int[] ints = new int[bytes.length >> 2];
         ByteArray ba = new ByteArray(bytes);
         int i = 0;

         for(int kk = ints.length; i < kk; ++i) {
            ints[i] = ba.readInt();
         }

         return ints;
      } else {
         return null;
      }
   }

   public static byte[] intsToBytes(int[] ints) {
      if (ints != null && ints.length > 0) {
         byte[] bytes = new byte[ints.length << 2];
         ByteArray ba = new ByteArray(bytes);
         int i = 0;

         for(int kk = ints.length; i < kk; ++i) {
            ba.writeInt(ints[i]);
         }

         return ba.toByteArray();
      } else {
         return null;
      }
   }

   public int getLength() {
      return this.data.length;
   }
}

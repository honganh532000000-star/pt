import java.io.IOException;

public class GZIP {
   private static final int FTEXT_MASK = 1;
   private static final int FHCRC_MASK = 2;
   private static final int FEXTRA_MASK = 4;
   private static final int FNAME_MASK = 8;
   private static final int FCOMMENT_MASK = 16;
   private static final int BTYPE_NONE = 0;
   private static final int BTYPE_FIXED = 1;
   private static final int BTYPE_DYNAMIC = 2;
   private static final int BTYPE_RESERVED = 3;
   private static final int MAX_BITS = 16;
   private static final int MAX_CODE_LITERALS = 287;
   private static final int MAX_CODE_DISTANCES = 31;
   private static final int MAX_CODE_LENGTHS = 18;
   private static final int EOB_CODE = 256;
   private static final int[] LENGTH_EXTRA_BITS = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 0, 99, 99};
   private static final int[] LENGTH_VALUES = new int[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17, 19, 23, 27, 31, 35, 43, 51, 59, 67, 83, 99, 115, 131, 163, 195, 227, 258, 0, 0};
   private static final int[] DISTANCE_EXTRA_BITS = new int[]{0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13};
   private static final int[] DISTANCE_VALUES = new int[]{1, 2, 3, 4, 5, 7, 9, 13, 17, 25, 33, 49, 65, 97, 129, 193, 257, 385, 513, 769, 1025, 1537, 2049, 3073, 4097, 6145, 8193, 12289, 16385, 24577};
   private static final int[] DYNAMIC_LENGTH_ORDER = new int[]{16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15};
   private static int gzipIndex;
   private static int gzipByte;
   private static int gzipBit;

   public static byte[] inflate(byte[] gzip) throws IOException {
      gzipBit = 0;
      gzipByte = 0;
      gzipIndex = 0;
      if (readBits(gzip, 16) == 35615 && readBits(gzip, 8) == 8) {
         int flg = readBits(gzip, 8);
         gzipIndex += 6;
         if ((flg & 4) != 0) {
            gzipIndex += readBits(gzip, 16);
         }

         if ((flg & 8) != 0) {
            while(gzip[gzipIndex++] != 0) {
            }
         }

         if ((flg & 16) != 0) {
            while(gzip[gzipIndex++] != 0) {
            }
         }

         if ((flg & 2) != 0) {
            gzipIndex += 2;
         }

         int index = gzipIndex;
         gzipIndex = gzip.length - 4;
         byte[] uncompressed = new byte[readBits(gzip, 16) | readBits(gzip, 16) << 16];
         int uncompressedIndex = 0;
         gzipIndex = index;
         int bfinal = false;
         boolean var6 = false;

         int bfinal;
         label118:
         do {
            bfinal = readBits(gzip, 1);
            int btype = readBits(gzip, 2);
            if (btype == 0) {
               gzipBit = 0;
               int len = readBits(gzip, 16);
               int nlen = readBits(gzip, 16);
               System.arraycopy(gzip, gzipIndex, uncompressed, uncompressedIndex, len);
               gzipIndex += len;
               uncompressedIndex += len;
            } else {
               int[] literalTree;
               int[] distanceTree;
               int code;
               int leb;
               int deb;
               int distance;
               if (btype == 2) {
                  code = readBits(gzip, 5) + 257;
                  leb = readBits(gzip, 5) + 1;
                  deb = readBits(gzip, 4) + 4;
                  byte[] lengthBits = new byte[19];

                  for(distance = 0; distance < deb; ++distance) {
                     lengthBits[DYNAMIC_LENGTH_ORDER[distance]] = (byte)readBits(gzip, 3);
                  }

                  int[] lengthTree = createHuffmanTree(lengthBits, 18);
                  literalTree = createHuffmanTree(decodeCodeLengths(gzip, lengthTree, code), code - 1);
                  distanceTree = createHuffmanTree(decodeCodeLengths(gzip, lengthTree, leb), leb - 1);
               } else {
                  byte[] literalBits = new byte[288];

                  for(leb = 0; leb < 144; ++leb) {
                     literalBits[leb] = 8;
                  }

                  for(leb = 144; leb < 256; ++leb) {
                     literalBits[leb] = 9;
                  }

                  for(leb = 256; leb < 280; ++leb) {
                     literalBits[leb] = 7;
                  }

                  for(leb = 280; leb < 288; ++leb) {
                     literalBits[leb] = 8;
                  }

                  literalTree = createHuffmanTree(literalBits, 287);
                  byte[] distanceBits = new byte[32];

                  for(deb = 0; deb < distanceBits.length; ++deb) {
                     distanceBits[deb] = 5;
                  }

                  distanceTree = createHuffmanTree(distanceBits, 31);
               }

               int code = false;
               int leb = false;
               boolean var21 = false;

               while(true) {
                  while(true) {
                     if ((code = readCode(gzip, literalTree)) == 256) {
                        continue label118;
                     }

                     if (code > 256) {
                        code -= 257;
                        int length = LENGTH_VALUES[code];
                        if ((leb = LENGTH_EXTRA_BITS[code]) > 0) {
                           length += readBits(gzip, leb);
                        }

                        code = readCode(gzip, distanceTree);
                        distance = DISTANCE_VALUES[code];
                        if ((deb = DISTANCE_EXTRA_BITS[code]) > 0) {
                           distance += readBits(gzip, deb);
                        }

                        int offset;
                        for(offset = uncompressedIndex - distance; distance < length; distance <<= 1) {
                           System.arraycopy(uncompressed, offset, uncompressed, uncompressedIndex, distance);
                           uncompressedIndex += distance;
                           length -= distance;
                        }

                        System.arraycopy(uncompressed, offset, uncompressed, uncompressedIndex, length);
                        uncompressedIndex += length;
                     } else {
                        uncompressed[uncompressedIndex++] = (byte)code;
                     }
                  }
               }
            }
         } while(bfinal == 0);

         return uncompressed;
      } else {
         throw new IOException("Invalid GZIP format");
      }
   }

   private static int readBits(byte[] gzip, int n) {
      int data = gzipBit == 0 ? (gzipByte = gzip[gzipIndex++] & 255) : gzipByte >> gzipBit;

      for(int i = 8 - gzipBit; i < n; i += 8) {
         gzipByte = gzip[gzipIndex++] & 255;
         data |= gzipByte << i;
      }

      gzipBit = gzipBit + n & 7;
      return data & (1 << n) - 1;
   }

   private static int readCode(byte[] gzip, int[] tree) {
      int node;
      for(node = tree[0]; node >= 0; gzipBit = gzipBit + 1 & 7) {
         if (gzipBit == 0) {
            gzipByte = gzip[gzipIndex++] & 255;
         }

         node = (gzipByte & 1 << gzipBit) == 0 ? tree[node >> 16] : tree[node & '\uffff'];
      }

      return node & '\uffff';
   }

   private static byte[] decodeCodeLengths(byte[] gzip, int[] lengthTree, int count) {
      byte[] bits = new byte[count];
      int i = 0;
      int code = false;

      int code;
      for(int last = 0; i < count; last = code) {
         code = readCode(gzip, lengthTree);
         if (code >= 16) {
            int repeat = false;
            int repeat;
            if (code == 16) {
               repeat = 3 + readBits(gzip, 2);
               code = last;
            } else {
               if (code == 17) {
                  repeat = 3 + readBits(gzip, 3);
               } else {
                  repeat = 11 + readBits(gzip, 7);
               }

               code = 0;
            }

            while(repeat-- > 0) {
               bits[i++] = (byte)code;
            }
         } else {
            bits[i++] = (byte)code;
         }
      }

      return bits;
   }

   private static int[] createHuffmanTree(byte[] bits, int maxCode) {
      int[] bl_count = new int[17];

      int code;
      for(code = 0; code < bits.length; ++code) {
         ++bl_count[bits[code]];
      }

      code = 0;
      bl_count[0] = 0;
      int[] next_code = new int[17];

      for(int i = 1; i <= 16; ++i) {
         next_code[i] = code = code + bl_count[i - 1] << 1;
      }

      int[] tree = new int[(maxCode << 1) + 16];
      int treeInsert = 1;

      for(int i = 0; i <= maxCode; ++i) {
         int len = bits[i];
         if (len != 0) {
            code = next_code[len]++;
            int node = 0;

            for(int bit = len - 1; bit >= 0; --bit) {
               int value = code & 1 << bit;
               int left;
               if (value == 0) {
                  left = tree[node] >> 16;
                  if (left == 0) {
                     tree[node] |= treeInsert << 16;
                     node = treeInsert++;
                  } else {
                     node = left;
                  }
               } else {
                  left = tree[node] & '\uffff';
                  if (left == 0) {
                     tree[node] |= treeInsert;
                     node = treeInsert++;
                  } else {
                     node = left;
                  }
               }
            }

            tree[node] = Integer.MIN_VALUE | i;
         }
      }

      return tree;
   }
}

import com.nokia.mid.ui.DirectGraphics;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

public abstract class Util {
   static boolean[] isInitRes = new boolean[7];
   public static final short STR_INTRO = 4;
   public static final short STR_RETURN = 16;
   public static final short STR_ABOUT = 5;
   public static final short STR_SETUP = 3;
   public static final short STR_AUDIO = 17;
   public static final short STR_WAITING = 18;
   public static final short STR_OPEN = 25;
   public static final short STR_CLOSE = 26;
   public static final short STR_FIRE_TO_CHANGE = 27;
   public static Vector focusPics = new Vector();
   public static Vector focusPicIndex = new Vector();
   public static Vector focusPicRowIndex = new Vector();
   public static byte m_nTalkId = 0;
   public static boolean isDebug = true;
   public static final short[] STRS_ID_LANG = new short[]{19, 20, 21, 22, 23, 24};
   private static String[] strRes;
   private static Random random;
   private static short ROLE_EDIT_VERSION;
   private static final String optionRmsName = "setup";
   public static boolean audioOpen;
   public static final byte LANG_CN = 0;
   public static final byte LANG_EN = 1;
   public static final byte LANG_FR = 2;
   public static final byte LANG_DE = 3;
   public static final byte LANG_IT = 4;
   public static final byte LANG_ES = 5;
   public static byte lang;
   private static byte curStrLang;
   public static final int EXCEPTION = 4;
   public static final int ERROR = 3;
   public static final int NOTE = 2;
   public static final int TITLE = 1;
   public static final int MESSAGE = 0;
   public static int sCount;
   public static int[] sColor;
   public static byte[][] colorSigns;
   public static final byte ROLL_CIRCLE = 0;
   public static final byte ROLL_LEFT_RIGHT = 1;
   public static final byte ROLL_SLOW = 3;
   public static final byte ROLL_NORMAL = 6;
   public static final byte ROLL_FAST = 12;
   public static int rollCounter;
   public static int waitCounter;
   public static boolean isLeftRoll;
   public static final int BOTTOM = 32;
   public static final int HCENTER = 1;
   public static final int LEFT = 4;
   public static final int RIGHT = 8;
   public static final int TOP = 16;
   public static final int VCENTER = 2;
   public static final int LEFT_TOP = 20;
   public static final int LEFT_VCENTER = 6;
   public static final int LEFT_BOTTOM = 36;
   public static final int HCENTER_TOP = 17;
   public static final int HCENTER_VCENTER = 3;
   public static final int HCENTER_BOTTOM = 33;
   public static final int RIGHT_TOP = 24;
   public static final int RIGHT_VCENTER = 10;
   public static final int RIGHT_BOTTOM = 40;
   public static final byte TRANS_NONE = 0;
   public static final byte TRANS_V = 1;
   public static final byte TRANS_H = 2;
   public static final byte TRANS_HV = 3;
   public static final byte TRANS_V90 = 4;
   public static final byte TRANS_90 = 5;
   public static final byte TRANS_270 = 6;
   public static final byte TRANS_H90 = 7;
   public static final short[] TRANS_TABLE;
   private static DirectGraphics dg;
   static final byte ANY_ALL = 0;
   static final byte NUM_ONLY = 1;
   private static String matter;
   static int gambleIndex;

   static {
      strRes = new String[]{"Trò chơi mới", "Tiếp tục trò chơi", "Log xuất", "Thiết lập trò chơi", "Giải thích hỗ trợ", "Liên quan", Cons.C_STR[4], "Khoa học công nghệ Mammoth Thiên Tân\nwww.mammothworld.com\n", "Giới thiệu trò chơi", "Quy tắc sử dụng", "Phương thức thao tác", "Thông tin phục vụ", "......1The implementation indicates its support for traversal internal to a CustomItem by setting one or both of the TRAVERSE_HORIZONTAL or TRAVERSE_VERTICAL bits in the value returned by getInteractionModes(). If neither of these bits is set, the implementation is unwilling to let CustomItems traverse internally, or the implementation does not support traversal at all. If the implementation does support traversal but has declined to permit traversal internal to CustomItems, the implementation will supply its own highlighting outside the CustomItem's content area.", "......2 Nội dung cụ thể quy tắc sử dụng", "......3 Nội dung cụ thế cách thao tác", "......4 Nội dung cụ thể thông tin phục vụ khách hàng", Cons.C_STR[1], "Âm thanh", "Xin chờ...", "Tiếng Trung giản thể", "Tiếng Anh", "Tiếng Pháp", "TIếng Đức", "Ý", "Tây Ban Nha", "Mở", "Đóng", "Nhấn phím 5 thay đổi", "Ngôn ngữ", "Trở về trang chủ"};
      random = new Random();
      ROLE_EDIT_VERSION = 100;
      audioOpen = true;
      lang = 0;
      curStrLang = 0;
      sCount = 0;
      sColor = new int[]{16711680, 16711424, 65409, 31999, 8585471, 6045209};
      rollCounter = 0;
      waitCounter = 0;
      isLeftRoll = true;
      TRANS_TABLE = new short[]{0, 16384, 8192, 180, 16474, 270, 90, 8282};
      dg = null;
   }

   public static void debugPrint(String str) {
      if (isDebug) {
         System.out.print(str);
      }

   }

   public static void debugPrint(boolean b) {
      if (isDebug) {
         System.out.print(b);
      }

   }

   public static void debugPrint(char c) {
      if (isDebug) {
         System.out.print(c);
      }

   }

   public static void debugPrint(char[] cs) {
      if (isDebug) {
         System.out.print(cs);
      }

   }

   public static void debugPrint(int i) {
      if (isDebug) {
         System.out.print(i);
      }

   }

   public static void debugPrint(long l) {
      if (isDebug) {
         System.out.print(l);
      }

   }

   public static void debugPrint(Object o) {
      if (isDebug) {
         System.out.print(o);
      }

   }

   public static void debugPrintln(String str) {
   }

   public static void debugPrintln(boolean b) {
   }

   public static void debugPrintln(char c) {
   }

   public static void debugPrintln(char[] cs) {
   }

   public static void debugPrintln(int i) {
   }

   public static void debugPrintln(long l) {
   }

   public static void debugPrintln(Object o) {
   }

   public static final String getStr(int strID) {
      return strRes[strID];
   }

   public static byte[] parseMapData(byte[] data, int sizez) {
      byte[] newMapData = (byte[])null;

      try {
         newMapData = GZIP.inflate(data);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return newMapData;
   }

   public static int getRandom(int num) {
      int tempRandom = random.nextInt() << 1 >>> 1;
      return tempRandom % num;
   }

   public static final int genRandom(int beginNum, int endNum) {
      int tempRandom = random.nextInt() << 1 >>> 1;
      return tempRandom % (endNum - beginNum + 1) + beginNum;
   }

   public static byte[] rwDOC(boolean isRead, byte[] doc, String rmsName) {
      byte[] buf = (byte[])null;
      if (doc == null && !isRead) {
         return null;
      } else {
         if (!isRead) {
            try {
               RecordStore.deleteRecordStore(rmsName);
            } catch (Exception var6) {
            }
         }

         try {
            RecordStore recordStore = RecordStore.openRecordStore(rmsName, true);
            if (recordStore.getNumRecords() == 0) {
               if (!isRead) {
                  recordStore.addRecord(doc, 0, doc.length);
               }
            } else if (isRead) {
               buf = recordStore.getRecord(1);
            } else {
               recordStore.setRecord(1, doc, 0, doc.length);
            }

            recordStore.closeRecordStore();
         } catch (Exception var5) {
         }

         return buf;
      }
   }

   static void saveRecord(byte[] doc, String rmsName) {
      rwDOC(false, doc, rmsName);
   }

   static void saveByteRecord(byte[] tmp, String rmsName) {
      if (tmp != null) {
         byte[] buf = (byte[])null;
         ByteArray ba = new ByteArray();

         for(int i = 0; i < tmp.length; ++i) {
            ba.writeByte(tmp[i]);
         }

         buf = ba.toByteArray();
         ba.close();
         rwDOC(false, buf, rmsName);
      }
   }

   static void saveIntRecord(int[] tmp, String rmsName) {
      if (tmp != null) {
         byte[] buf = (byte[])null;
         ByteArray ba = new ByteArray();

         for(int i = 0; i < tmp.length; ++i) {
            ba.writeInt(tmp[i]);
         }

         buf = ba.toByteArray();
         ba.close();
         rwDOC(false, buf, rmsName);
      }
   }

   static void saveShortRecord(short[] tmp, String rmsName) {
      if (tmp != null) {
         byte[] buf = (byte[])null;
         ByteArray ba = new ByteArray();

         for(int i = 0; i < tmp.length; ++i) {
            ba.writeShort(tmp[i]);
         }

         buf = ba.toByteArray();
         ba.close();
         rwDOC(false, buf, rmsName);
      }
   }

   static void saveStrRecord(String[] str, String rmsName) {
      if (str != null) {
         byte[] buf = (byte[])null;
         ByteArray ba = new ByteArray();

         for(int i = 0; i < str.length; ++i) {
            ba.writeUTF(str[i]);
         }

         buf = ba.toByteArray();
         ba.close();
         rwDOC(false, buf, rmsName);
      }
   }

   static final byte[] loadByteRecord(String rmsName) {
      return rwDOC(true, (byte[])null, rmsName);
   }

   static final byte[] loadByteRecord(String rmsName, int lg) {
      byte[] tmp = new byte[lg];
      byte[] buf = rwDOC(true, (byte[])null, rmsName);
      if (buf == null) {
         return null;
      } else {
         ByteArray ba = new ByteArray(buf);

         for(int i = 0; i < lg; ++i) {
            tmp[i] = ba.readByte();
         }

         return tmp;
      }
   }

   static final int[] loadIntRecord(String rmsName, int lg) {
      int[] tmp = new int[lg];
      byte[] buf = rwDOC(true, (byte[])null, rmsName);
      if (buf == null) {
         return null;
      } else {
         ByteArray ba = new ByteArray(buf);

         for(int i = 0; i < lg; ++i) {
            tmp[i] = ba.readInt();
         }

         return tmp;
      }
   }

   static final short[] loadShortRecord(String rmsName, int lg) {
      short[] tmp = new short[lg];
      byte[] buf = rwDOC(true, (byte[])null, rmsName);
      if (buf == null) {
         return null;
      } else {
         ByteArray ba = new ByteArray(buf);

         for(int i = 0; i < lg; ++i) {
            tmp[i] = ba.readShort();
         }

         return tmp;
      }
   }

   static final String[] loadStrRecord(String rmsName, int lg) {
      String[] str = new String[lg];
      byte[] buf = rwDOC(true, (byte[])null, rmsName);
      if (buf == null) {
         return null;
      } else {
         ByteArray ba = new ByteArray(buf);

         for(int i = 0; i < lg; ++i) {
            str[i] = ba.readUTF();
         }

         return str;
      }
   }

   public static String encodeUnicode(String gbString) {
      char[] utfBytes = gbString.toCharArray();
      String unicodeBytes = "";

      for(int byteIndex = 0; byteIndex < utfBytes.length; ++byteIndex) {
         String hexB = Integer.toHexString(utfBytes[byteIndex]);
         if (hexB.length() <= 2) {
            hexB = "00" + hexB;
         }

         unicodeBytes = unicodeBytes + "\\u" + hexB;
      }

      return unicodeBytes;
   }

   public static short[] readPdatFile(String fileName, boolean isStatic) {
      short[] dataArray = new short[0];

      try {
         InputStream is = loadFile(fileName, isStatic);
         DataInputStream dis = new DataInputStream(is);
         short version = dis.readShort();
         boolean isShort = dis.readBoolean();
         short tileLength = dis.readShort();
         dataArray = new short[tileLength * 4];
         int i;
         if (isShort) {
            for(i = 0; i < tileLength * 4; ++i) {
               dataArray[i] = dis.readShort();
            }
         } else {
            for(i = 0; i < tileLength * 4; ++i) {
               dataArray[i] = (short)(dis.readByte() & 255);
            }
         }

         dis.close();
      } catch (Exception var9) {
         var9.printStackTrace();
      }

      return dataArray;
   }

   public static short[][] readTdatFile(String fileName, boolean isStatic) {
      short[][] dataArray = new short[0][0];

      try {
         InputStream is = loadFile(fileName, isStatic);
         DataInputStream dis = new DataInputStream(is);
         short version = dis.readShort();
         boolean isShort = dis.readBoolean();
         short frameLength = dis.readShort();
         dataArray = new short[frameLength][];
         short tileLength = false;
         int i;
         int j;
         short tileLength;
         if (isShort) {
            for(i = 0; i < frameLength; ++i) {
               tileLength = (short)(dis.readByte() & 255);
               dataArray[i] = new short[tileLength * 5];

               for(j = 0; j < dataArray[i].length; ++j) {
                  dataArray[i][j] = dis.readShort();
               }
            }
         } else {
            for(i = 0; i < frameLength; ++i) {
               tileLength = (short)(dis.readByte() & 255);
               dataArray[i] = new short[tileLength * 5];

               for(j = 0; j < dataArray[i].length; j += 5) {
                  dataArray[i][j] = dis.readByte();
                  dataArray[i][j + 1] = (short)(dis.readByte() & 255);
                  dataArray[i][j + 2] = dis.readByte();
                  dataArray[i][j + 3] = dis.readByte();
                  dataArray[i][j + 4] = dis.readByte();
               }
            }
         }

         dis.close();
      } catch (Exception var11) {
         var11.printStackTrace();
      }

      return dataArray;
   }

   public static short[][] readFdatFile(String fileName, int offset, boolean isStatic) {
      short[][] dataArray = new short[0][0];

      try {
         InputStream is = loadFile(fileName, isStatic);
         DataInputStream dis = new DataInputStream(is);
         short version = dis.readShort();
         boolean isShort = dis.readBoolean();
         short frameLength = dis.readShort();
         dataArray = new short[frameLength][];
         short tileLength = false;
         int i;
         int j;
         short tileLength;
         if (isShort) {
            for(i = 0; i < frameLength; ++i) {
               tileLength = (short)(dis.readByte() & 255);
               dataArray[i] = new short[tileLength * 4];

               for(j = 0; j < dataArray[i].length; ++j) {
                  if (j == 0) {
                     dataArray[i][j] = (short)(dis.readShort() - offset);
                  } else {
                     dataArray[i][j] = dis.readShort();
                  }
               }
            }
         } else {
            for(i = 0; i < frameLength; ++i) {
               tileLength = (short)(dis.readByte() & 255);
               dataArray[i] = new short[tileLength * 4];

               for(j = 0; j < dataArray[i].length; j += 4) {
                  dataArray[i][j] = (short)((dis.readByte() & 255) - offset);
                  dataArray[i][j + 1] = dis.readByte();
                  dataArray[i][j + 2] = dis.readByte();
                  dataArray[i][j + 3] = dis.readByte();
               }
            }
         }

         dis.close();
      } catch (Exception var12) {
         var12.printStackTrace();
      }

      return dataArray;
   }

   public static short[][] readMdatFile(String fileName, boolean isStatic) {
      short[][] dataArray = new short[0][0];

      try {
         InputStream is = loadFile(fileName, isStatic);
         DataInputStream dis = new DataInputStream(is);
         short version = dis.readShort();
         boolean isShort = dis.readBoolean();
         short motionLength = dis.readShort();
         dataArray = new short[motionLength][];
         short frameLength = false;
         int i;
         int j;
         short frameLength;
         if (isShort) {
            for(i = 0; i < motionLength; ++i) {
               frameLength = (short)(dis.readByte() & 255);
               dataArray[i] = new short[frameLength];

               for(j = 0; j < dataArray[i].length; ++j) {
                  dataArray[i][j] = dis.readShort();
               }
            }
         } else {
            for(i = 0; i < motionLength; ++i) {
               frameLength = (short)(dis.readByte() & 255);
               dataArray[i] = new short[frameLength];

               for(j = 0; j < dataArray[i].length; ++j) {
                  dataArray[i][j] = (short)(dis.readByte() & 255);
               }
            }
         }

         dis.close();
      } catch (Exception var11) {
         var11.printStackTrace();
      }

      return dataArray;
   }

   public static void drawRoleEditFrame(Image img, Graphics g, short[] frameDatas, short[] pdatDatas, int x, int y, boolean isMirror) {
      int transform = 0;

      for(int i = 0; i < frameDatas.length; i += 4) {
         short attribute = frameDatas[i + 1];
         short picIndex = frameDatas[i];
         if ((attribute & 1) == 0) {
            int flip = (attribute & 8) >> 3;
            int rotate = (attribute & 6) >> 1;
            int type = rotate + (flip == 0 ? 0 : 1 + flip << 1);
            if (type == 8) {
               type = 4;
            } else if (type == 9) {
               type = 5;
            }

            if (isMirror) {
               if (type != 0 && type != 4) {
                  type = 8 - type;
               } else {
                  type = 4 - type;
               }
            }

            short pos = (short)(picIndex << 2);
            short picOffsetX = pdatDatas[pos];
            short picOffsetY = pdatDatas[pos + 1];
            short picWidth = pdatDatas[pos + 2];
            short picHeight = pdatDatas[pos + 3];
            short tempPicWidth = picWidth;
            switch(type) {
            case 0:
               transform = 0;
               tempPicWidth = picWidth;
               break;
            case 1:
               transform = 6;
               tempPicWidth = picHeight;
               break;
            case 2:
               transform = 3;
               tempPicWidth = picWidth;
               break;
            case 3:
               transform = 5;
               tempPicWidth = picHeight;
               break;
            case 4:
               transform = 2;
               tempPicWidth = picWidth;
               break;
            case 5:
               transform = 4;
               tempPicWidth = picHeight;
               break;
            case 6:
               transform = 1;
               tempPicWidth = picWidth;
               break;
            case 7:
               transform = 7;
               tempPicWidth = picHeight;
            }

            int drawX;
            if (!isMirror) {
               drawX = x + frameDatas[i + 2];
            } else {
               drawX = x - (frameDatas[i + 2] + tempPicWidth);
            }

            int drawY = y + frameDatas[i + 3];
            drawRegion(g, img, picOffsetX, picOffsetY, picWidth, picHeight, drawX, drawY, transform);
         }
      }

      g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
   }

   public static String[] wrapText(String s, int usable, Font font) {
      if (usable == 0) {
         String[] temp = new String[]{s};
         return temp;
      } else {
         String ss = null;
         ss = new String(s);
         int advance = 0;
         int idxBegin = 0;
         int idxEnd = 0;
         int len = ss.length();
         Vector v = null;
         v = new Vector(font.stringWidth(ss) / usable, 4);
         boolean var9 = false;

         while(idxBegin < len) {
            char tmp;
            for(tmp = 0; idxEnd < len; ++idxEnd) {
               char chr = ss.charAt(idxEnd);
               if (chr == '\n') {
                  break;
               }

               int charSize = false;
               int charSize = font.charWidth(chr);
               if (advance + charSize > usable) {
                  break;
               }

               advance += charSize;
            }

            boolean truncate = false;
            if (idxEnd < len) {
               switch(ss.charAt(idxEnd)) {
               case '\n':
                  break;
               default:
                  truncate = true;
                  tmp = ss.charAt(idxEnd);
               }
            }

            v.addElement(idxEnd > idxBegin ? ss.substring(idxBegin, idxEnd) : "");
            ++idxEnd;
            idxBegin = idxEnd;
            if (truncate) {
               idxBegin = idxEnd - 1;
               advance = font.charWidth(tmp);
            } else {
               advance = 0;
            }
         }

         String[] list = new String[v.size()];

         for(idxBegin = 0; idxBegin < list.length; ++idxBegin) {
            list[idxBegin] = (String)v.elementAt(idxBegin);
         }

         return list;
      }
   }

   public static short[][] wrapText_ColorXY(String s, String[] insStr, int usable, Font font) {
      String ss = null;
      ss = new String(s + "  ");
      int advance = 0;
      int idxBegin = 0;
      int idxEnd = 0;
      int len = ss.length();
      Vector v = null;
      v = new Vector(font.stringWidth(ss) / usable, 4);
      char tmp = false;
      short line = 1;
      short xd = false;
      short sl = (short)insStr.length;
      short[][] xy = new short[sl][2];

      while(idxBegin < len) {
         char tmp = 0;

         for(short xd = 0; idxEnd < len; ++xd) {
            char chr = ss.charAt(idxEnd);
            if (chr == '\n') {
               break;
            }

            int charSize = false;
            int charSize = font.charWidth(chr);
            if (advance + charSize > usable) {
               break;
            }

            advance += charSize;
            ++idxEnd;
         }

         boolean truncate = false;
         if (idxEnd < len) {
            switch(ss.charAt(idxEnd)) {
            case '\n':
            case ' ':
               break;
            default:
               truncate = true;
               tmp = ss.charAt(idxEnd);
            }
         }

         v.addElement(idxEnd > idxBegin ? ss.substring(idxBegin, idxEnd) : "");
         String teStr = idxEnd > idxBegin ? ss.substring(idxBegin, idxEnd) : "";

         for(int teind = 0; teind < teStr.length(); ++teind) {
            char chr = teStr.charAt(teind);

            for(short i = 0; i < sl; ++i) {
               char news = insStr[i].charAt(0);
               if (chr == news) {
                  xy[i][0] = (short)((teind + 0) * MainCanvas.CHARW);
                  xy[i][1] = line;
               }
            }
         }

         ++line;
         ++idxEnd;
         idxBegin = idxEnd;
         if (truncate) {
            idxBegin = idxEnd - 1;
            advance = font.charWidth(tmp);
         } else {
            advance = 0;
         }
      }

      return xy;
   }

   public static final int getColorPlace(int place, int[][] colorPlace) {
      int lg = colorPlace.length;
      int lg1 = -1;

      for(int i = 0; i < lg; ++i) {
         if (place >= colorPlace[i][0] && place <= colorPlace[i][1]) {
            lg1 = i;
            break;
         }
      }

      return lg1;
   }

   public static void drawNumberInCircle(Graphics g, int x, int y, int number) {
      if (number < 0 || number > 9) {
         ;
      }
   }

   public static void drawStr(Graphics g, String s, int x, int y, int a, int color1, int color2, int type) {
      if (type == 0) {
         g.setColor(color1);
         g.drawString(s, x - 2, y, a);
         g.drawString(s, x + 2, y, a);
         g.drawString(s, x, y - 2, a);
         g.drawString(s, x, y + 2, a);
         g.drawString(s, x - 1, y - 1, a);
         g.drawString(s, x - 1, y + 1, a);
         g.drawString(s, x + 1, y - 1, a);
         g.drawString(s, x + 1, y + 1, a);
      }

      g.setColor(color2);
      g.drawString(s, x, y, a);
   }

   public static void drawNumber(Graphics g, byte number, int x, int y) {
      byte[] fig = new byte[]{(byte)(number / 10), (byte)(number % 10)};
   }

   public static int abs(int num) {
      return num > 0 ? num : -num;
   }

   public static boolean isEnemy(GameObject obj, GameObject target) {
      if (obj == null) {
         return false;
      } else {
         return target == null ? false : false;
      }
   }

   private static final byte[][] readPKG(String pkgPath, String[] filenames) {
      byte[][] tmpArr = new byte[filenames.length][];
      boolean var3 = false;

      try {
         InputStream is = (new Object()).getClass().getResourceAsStream(pkgPath);
         DataInputStream dis = new DataInputStream(is);
         String fileType = dis.readUTF();
         if (fileType.compareTo("PKG0") != 0) {
            return null;
         }

         int fileCount = dis.readInt();
         short[] fsize = new short[fileCount];
         String[] fname = new String[fileCount];

         int i;
         for(i = 0; i < fileCount; ++i) {
            fname[i] = dis.readUTF();
            fsize[i] = (short)dis.readInt();
         }

         for(i = 0; i < fileCount; ++i) {
            int j;
            for(j = 0; j < filenames.length && (filenames[j] == null || filenames[j].compareTo(fname[i]) != 0); ++j) {
            }

            if (j >= filenames.length) {
               dis.skip((long)fsize[i]);
            } else {
               tmpArr[j] = new byte[fsize[i]];
               dis.readFully(tmpArr[j]);
               if ((pkgPath.equals("/tiles/map.pkg") || pkgPath.equals("/mimage/rolebody.pkg") || pkgPath.equals("/pkg_npc/mimage/monsterbody.pkg")) && tmpArr[j] != null) {
                  for(int k = 0; k < tmpArr[j].length; ++k) {
                     tmpArr[j][k] = (byte)(tmpArr[j][k] ^ 50);
                  }
               }
            }
         }

         dis.close();
         dis = null;
         is = null;
      } catch (Exception var12) {
         var12.printStackTrace();
      }

      return tmpArr;
   }

   public static byte[] deCodeXOR(byte[] a) {
      int index = 0;
      int fileLen = a.length;
      byte[] pwdByte = "2".getBytes();
      byte[] datas = new byte[fileLen];
      System.arraycopy(a, index, datas, 0, fileLen);
      int i = 0;

      while(i < datas.length) {
         for(int j = 0; j < pwdByte.length; ++i) {
            if (i >= datas.length) {
               return datas;
            }

            datas[i] ^= pwdByte[j];
            ++j;
         }
      }

      return datas;
   }

   private static byte[] readPKG(String pkgPath, String filename) {
      byte[] tmpArr = (byte[])null;
      int fileCount = false;
      int fsize = false;
      int thisFileSize = -1;
      String fname = null;

      try {
         InputStream is = (new Object()).getClass().getResourceAsStream(pkgPath);
         DataInputStream dis = new DataInputStream(is);
         String fileType = dis.readUTF();
         if (fileType.compareTo("PKG0") != 0) {
            return null;
         }

         int fileCount = dis.readInt();
         int preFileSize = 0;

         for(int i = 0; i < fileCount; ++i) {
            fname = dis.readUTF();
            int fsize = dis.readInt();
            if (filename.compareTo(fname) == 0) {
               thisFileSize = fsize;
            }

            if (thisFileSize == -1) {
               preFileSize += fsize;
            }
         }

         if (thisFileSize == -1) {
            return null;
         }

         tmpArr = new byte[thisFileSize];
         dis.skip((long)preFileSize);
         dis.readFully(tmpArr);
         dis.close();
         dis = null;
         is = null;
      } catch (Exception var12) {
      }

      return tmpArr;
   }

   public static void deleteRecord(String recordStoreName) {
      if (recordStoreName != null && !recordStoreName.trim().equals("")) {
         try {
            RecordStore.deleteRecordStore(recordStoreName);
         } catch (RecordStoreNotFoundException var2) {
         } catch (RecordStoreException var3) {
         }

      }
   }

   public static boolean isLegal(char ch) {
      return ch >= 19968 && ch <= '龤' || ch >= '_' && ch <= 'z' || ch >= '0' && ch <= '9' || ch >= 'A' && ch <= 'Z';
   }

   public static boolean checkLegal(String s, byte n, Form form) {
      int lg = s.length();
      char illegalChar = true;
      StringBuffer sb = new StringBuffer(s);
      boolean isLegal = true;
      if (sb.length() > n) {
         isLegal = false;
      } else {
         for(int i = 0; i < lg; ++i) {
            if (!isLegal(sb.charAt(i))) {
               isLegal = false;
               sb.charAt(i);
               break;
            }
         }
      }

      if (form != null) {
      }

      return isLegal;
   }

   public static boolean checkLogin(String s) {
      int lg = s.trim().length();
      return lg >= 6 && lg <= 16;
   }

   public static String[] split(String str, int width, char token) {
      return split(str, width, token, true);
   }

   public static String[] split(String str, int width, char token, boolean flag) {
      int length = str.length();
      Vector tmp = new Vector();

      for(int i = 0; i < length; ++i) {
         String string = "";

         for(int j = 0; j < width; ++j) {
            char c = str.charAt(i);
            string = string + c;
            if (i == length - 1) {
               tmp.addElement(string);
               break;
            }

            if (c == '\n' && flag || c == token) {
               tmp.addElement(string);
               break;
            }

            if (j == width - 1) {
               if (flag) {
                  string = string + "\n";
               }

               tmp.addElement(string);
               break;
            }

            ++i;
         }
      }

      String[] result = new String[tmp.size()];

      for(int i = 0; i < result.length; ++i) {
         result[i] = ((String)tmp.elementAt(i)).replace(token, ' ').trim();
      }

      return result;
   }

   public static String[] splitToken(String str, char token) {
      int length = str.length();
      Vector tmp = new Vector();

      for(int i = 0; i < length; ++i) {
         String string = "";

         for(int j = 0; j < length; ++j) {
            char c = str.charAt(i);
            string = string + c;
            if (i == length - 1) {
               tmp.addElement(string);
               break;
            }

            if (c == token) {
               tmp.addElement(string);
               break;
            }

            ++i;
         }
      }

      String[] result = new String[tmp.size()];

      for(int i = 0; i < result.length; ++i) {
         result[i] = ((String)tmp.elementAt(i)).replace(token, ' ').trim();
      }

      return result;
   }

   public static final int[][][] resizeArray(int[][][] array, int index, int var) {
      int len = array.length;
      int[][][] tmp = array;
      array = new int[len + var][][];
      System.arraycopy(tmp, 0, array, 0, index);
      if (var > 0) {
         System.arraycopy(tmp, index, array, index + var, len - index);
      } else {
         System.arraycopy(tmp, index - var, array, index, len + var - index);
      }

      tmp = (int[][][])null;
      return array;
   }

   public static final int[][][] addArray(int[][][] array, int[][] element) {
      array = resizeArray((int[][][])array, array.length, 1);
      array[array.length - 1] = element;
      return array;
   }

   public static final int[][][] addArrays(int[][][] array, int index, int[][][] element) {
      array = resizeArray(array, index, element.length);
      System.arraycopy(element, 0, array, index, element.length);
      return array;
   }

   public static final int[][][] removeArray(int[][][] array, int index) {
      array = resizeArray((int[][][])array, index, -1);
      return array;
   }

   public static final int[][] resizeArray(int[][] array, int index, int var) {
      int len = array.length;
      int[][] tmp = array;
      array = new int[len + var][];
      System.arraycopy(tmp, 0, array, 0, index);
      if (var > 0) {
         System.arraycopy(tmp, index, array, index + var, len - index);
      } else {
         System.arraycopy(tmp, index - var, array, index, len + var - index);
      }

      tmp = (int[][])null;
      return array;
   }

   public static final int[][] addArray(int[][] array, int index, int[] element) {
      array = resizeArray((int[][])array, index, 1);
      array[index] = element;
      return array;
   }

   public static final int[][] removeArray(int[][] array, int index) {
      array = resizeArray((int[][])array, index, -1);
      return array;
   }

   public static final short[][] resizeArray(short[][] array, int index, int var) {
      int len = array.length;
      short[][] tmp = array;
      array = new short[len + var][];
      System.arraycopy(tmp, 0, array, 0, index);
      if (var > 0) {
         System.arraycopy(tmp, index, array, index + var, len - index);
      } else {
         System.arraycopy(tmp, index - var, array, index, len + var - index);
      }

      tmp = (short[][])null;
      return array;
   }

   public static final short[][] addArray(short[][] array, short[] element) {
      array = resizeArray((short[][])array, array.length, 1);
      array[array.length - 1] = element;
      return array;
   }

   public static final short[][] removeArray(short[][] array, int index) {
      array = resizeArray((short[][])array, index, -1);
      return array;
   }

   public static final byte[][] resizeArray(byte[][] array, int index, int var) {
      int len = array.length;
      byte[][] tmp = array;
      array = new byte[len + var][];
      System.arraycopy(tmp, 0, array, 0, index);
      if (var > 0) {
         System.arraycopy(tmp, index, array, index + var, len - index);
      } else {
         System.arraycopy(tmp, index - var, array, index, len + var - index);
      }

      tmp = (byte[][])null;
      return array;
   }

   public static final byte[][] addArray(byte[][] array, int index, byte[] element) {
      array = resizeArray((byte[][])array, index, 1);
      array[index] = element;
      return array;
   }

   public static final byte[][] removeArray(byte[][] array, int index) {
      array = resizeArray((byte[][])array, index, -1);
      return array;
   }

   public static final Object[] resizeArray(Object[] array, int index, int var) {
      int len = array.length;
      Object[] tmp = array;
      array = new Object[len + var];
      System.arraycopy(tmp, 0, array, 0, index);
      if (var > 0) {
         System.arraycopy(tmp, index, array, index + var, len - index);
      } else {
         System.arraycopy(tmp, index - var, array, index, len + var - index);
      }

      tmp = (Object[])null;
      return array;
   }

   public static final Object[] addArray(Object[] array, int index, Object element) {
      array = resizeArray((Object[])array, index, 1);
      array[index] = element;
      return array;
   }

   public static final Object[] addArrays(Object[] array, int index, Object[] element) {
      array = resizeArray(array, index, element.length);
      System.arraycopy(element, 0, array, index, element.length);
      return array;
   }

   public static final Object[] removeArray(Object[] array, int index) {
      array = resizeArray((Object[])array, index, -1);
      return array;
   }

   public static final GameObject[] resizeArray(GameObject[] array, int index, int var) {
      int len = array.length;
      GameObject[] tmp = array;
      array = new GameObject[len + var];
      System.arraycopy(tmp, 0, array, 0, index);
      if (var > 0) {
         System.arraycopy(tmp, index, array, index + var, len - index);
      } else {
         System.arraycopy(tmp, index - var, array, index, len + var - index);
      }

      tmp = (GameObject[])null;
      return array;
   }

   public static final GameObject[] addArray(GameObject[] array, GameObject element) {
      array = resizeArray((GameObject[])array, array.length, 1);
      array[array.length - 1] = element;
      return array;
   }

   public static final GameObject[] removeArray(GameObject[] array, int index) {
      array = resizeArray((GameObject[])array, index, -1);
      return array;
   }

   public static final int getArrayIndex(GameObject[] array, GameObject element) {
      for(int i = array.length - 1; i >= 0; --i) {
         if (array[i].getID() == element.getID()) {
            return i;
         }
      }

      return -1;
   }

   public static final String[] resizeArray(String[] array, int index, int var) {
      int len = array.length;
      String[] tmp = array;
      array = new String[len + var];
      System.arraycopy(tmp, 0, array, 0, index);
      if (var > 0) {
         System.arraycopy(tmp, index, array, index + var, len - index);
      } else {
         System.arraycopy(tmp, index - var, array, index, len + var - index);
      }

      tmp = (String[])null;
      return array;
   }

   public static final String[] addArray(String[] array, int index, String element) {
      array = resizeArray((String[])array, index, 1);
      array[index] = element;
      return array;
   }

   public static final String[] addArrays(String[] array, int index, String[] element) {
      array = resizeArray(array, index, element.length);
      System.arraycopy(element, 0, array, index, element.length);
      return array;
   }

   public static final String[] removeArray(String[] array, int index) {
      array = resizeArray((String[])array, index, -1);
      return array;
   }

   public static final int[] resizeArray(int[] array, int index, int var) {
      int len = array.length;
      int[] tmp = array;
      array = new int[len + var];
      System.arraycopy(tmp, 0, array, 0, index);
      if (var > 0) {
         System.arraycopy(tmp, index, array, index + var, len - index);
      } else {
         System.arraycopy(tmp, index - var, array, index, len + var - index);
      }

      tmp = (int[])null;
      return array;
   }

   public static final int[] addArray(int[] array, int index, int element) {
      array = resizeArray((int[])array, index, 1);
      array[index] = element;
      return array;
   }

   public static final int[] addArrays(int[] array, int index, int[] element) {
      array = resizeArray(array, index, element.length);
      System.arraycopy(element, 0, array, index, element.length);
      return array;
   }

   public static final int[] removeArray(int[] array, int index) {
      array = resizeArray((int[])array, index, -1);
      return array;
   }

   public static final byte[] resizeArray(byte[] array, int index, int var) {
      int len = array.length;
      byte[] tmp = array;
      array = new byte[len + var];
      System.arraycopy(tmp, 0, array, 0, index);
      if (var > 0) {
         System.arraycopy(tmp, index, array, index + var, len - index);
      } else {
         System.arraycopy(tmp, index - var, array, index, len + var - index);
      }

      tmp = (byte[])null;
      return array;
   }

   public static final byte[] addArray(byte[] array, int index, byte element) {
      array = resizeArray((byte[])array, index, 1);
      array[index] = element;
      return array;
   }

   public static final byte[] removeArray(byte[] array, int index) {
      array = resizeArray((byte[])array, index, -1);
      return array;
   }

   public static final char[] resizeArray(char[] array, int index, int var) {
      int len = array.length;
      char[] tmp = array;
      array = new char[len + var];
      System.arraycopy(tmp, 0, array, 0, index);
      if (var > 0) {
         System.arraycopy(tmp, index, array, index + var, len - index);
      } else {
         System.arraycopy(tmp, index - var, array, index, len + var - index);
      }

      tmp = (char[])null;
      return array;
   }

   public static final char[] addArray(char[] array, int index, char element) {
      array = resizeArray((char[])array, index, 1);
      array[index] = element;
      return array;
   }

   public static final char[] addArrays(char[] array, int index, char[] element) {
      array = resizeArray(array, index, element.length);
      System.arraycopy(element, 0, array, index, element.length);
      return array;
   }

   public static final char[] removeArray(char[] array, int index, int length) {
      array = resizeArray(array, index, -length);
      return array;
   }

   public static final Vector[] resizeArray(Vector[] array, int index, int var) {
      int len = array.length;
      Vector[] tmp = array;
      array = new Vector[len + var];
      System.arraycopy(tmp, 0, array, 0, index);
      if (var > 0) {
         System.arraycopy(tmp, index, array, index + var, len - index);
      } else {
         System.arraycopy(tmp, index - var, array, index, len + var - index);
      }

      tmp = (Vector[])null;
      return array;
   }

   public static final Vector[] addArray(Vector[] array, int index, Vector element) {
      array = resizeArray((Vector[])array, index, 1);
      array[index] = element;
      return array;
   }

   public static final Vector[] removeArray(Vector[] array, int index) {
      array = resizeArray((Vector[])array, index, -1);
      return array;
   }

   public static final void loadOption() {
      try {
         byte[] tmpBytes = rwDOC(true, (byte[])null, "setup");
         if (tmpBytes == null) {
            return;
         }

         audioOpen = tmpBytes[0] != 0;
         lang = tmpBytes[1];
      } catch (Exception var1) {
      }

   }

   public static final void saveOption() {
      try {
         byte[] tmpBytes = new byte[]{(byte)(audioOpen ? 1 : 0), lang};
         rwDOC(false, tmpBytes, "setup");
      } catch (Exception var1) {
      }

   }

   public static void drawString(Graphics g, String txt, int width, int x, int y, int fontColor) {
      g.setColor(fontColor);
      int offset = (width - getStrLen(txt)) / 2;
      g.drawString(txt, x + offset, y, 20);
   }

   public static int getStrLen(String str) {
      int w = 0;

      for(int i = 0; i < str.length(); ++i) {
         w += MainCanvas.curFont.charWidth(str.charAt(i));
      }

      return w;
   }

   public static int drawDoubleNum(Graphics g, int num1, int num2, int x, int y) {
      int frame_w = MainCanvas.ui_dataNumMImg.frame_w;
      int width = false;
      String num1Str = String.valueOf(num1);
      String num2Str = String.valueOf(num2);
      int width1 = 0;

      int j;
      for(j = String.valueOf(num1).length(); width1 < j; ++width1) {
         byte lev_digit = Byte.parseByte(String.valueOf(num1Str.charAt(width1)));
         MainCanvas.ui_dataNumMImg.draw(g, x + (frame_w + 1) * width1, y, lev_digit);
      }

      width1 = (frame_w + 1) * String.valueOf(num1).length() + 1;
      MainCanvas.ui_dataNumMImg.draw(g, x + width1, y, 10);
      j = 0;

      for(int n = String.valueOf(num2).length(); j < n; ++j) {
         byte lev_digit = Byte.parseByte(String.valueOf(num2Str.charAt(j)));
         MainCanvas.ui_dataNumMImg.draw(g, x + width1 + frame_w + 2 + (frame_w + 1) * j, y, lev_digit);
      }

      int width = width1 + frame_w + 2 + (frame_w + 1) * String.valueOf(num2).length();
      return width;
   }

   public static int[] getDoubleIntSize(int num1, int num2) {
      int[] size = new int[2];
      int w = (MainCanvas.ui_dataNumMImg.frame_w + 1) * String.valueOf(num1).length() + 1 + MainCanvas.ui_dataNumMImg.frame_w + 2 + (MainCanvas.ui_dataNumMImg.frame_w + 1) * String.valueOf(num2).length();
      int h = MainCanvas.ui_dataNumMImg.frame_h;
      size[0] = w;
      size[1] = h;
      return size;
   }

   public static void drawLevelNum(Graphics g, short level, int x, int y) {
      int frame_w = MainCanvas.ui_lvNumMImg.frame_w;
      if (level < 10) {
         MainCanvas.ui_lvNumMImg.draw(g, x, y, 0);
         MainCanvas.ui_lvNumMImg.draw(g, x + frame_w + 2, y, level);
      } else {
         int i = 0;

         for(int n = String.valueOf(level).length(); i < n; ++i) {
            char c = String.valueOf(level).charAt(i);
            MainCanvas.ui_lvNumMImg.draw(g, x + (frame_w + 2) * i, y, c - 48);
         }
      }

   }

   public static void drawLevelUpImg(Graphics g, int x1, int x2, int y, int frameIndex) {
      int posX = x1 - x2;
      int posX2 = x1 + x2;
      int y2 = MainCanvas.si_caoImg.getHeight() - 7;
      MainCanvas.levelUpImg.draw(g, posX - 8, y, frameIndex, 0);
      MainCanvas.levelUpImg.draw(g, posX2 + 1, y, frameIndex, 2);
      MainCanvas.levelUpImg.draw(g, posX - 8, y + y2, frameIndex, 1);
      MainCanvas.levelUpImg.draw(g, posX2 + 1, y + y2, frameIndex, 3);
   }

   public static void drawIntNum(Graphics g, long num, int x, int y) {
      drawIntNum(g, MainCanvas.ui_dataNumMImg, num, x, y);
   }

   public static void drawIntNum(Graphics g, MImage numImg, long num, int x, int y) {
      int frame_w = numImg.frame_w;
      long tmpNum = num;
      String numStr = String.valueOf(num);
      int charSize = false;
      int charSize;
      if (numStr.charAt(0) == '-') {
         charSize = frame_w + 1;
         tmpNum = Math.abs(num);
         numStr = String.valueOf(tmpNum);
         numImg.draw(g, x, y, numImg.frames - 1);
      } else {
         charSize = 0;
      }

      int j = 0;

      for(int n = String.valueOf(tmpNum).length(); j < n; ++j) {
         byte lev_digit = Byte.parseByte(String.valueOf(numStr.charAt(j)));
         numImg.draw(g, x + charSize + (frame_w + 1) * j, y, lev_digit);
      }

   }

   public static int[] getIntSize(long num) {
      int[] size = new int[]{(MainCanvas.ui_dataNumMImg.frame_w + 1) * String.valueOf(num).length(), MainCanvas.ui_dataNumMImg.frame_h};
      return size;
   }

   public static void printInfo(String info, int level) {
   }

   public static final void fillRect(Graphics g, int x, int y, int w, int h, int RGB) {
      g.setColor(RGB);
      g.fillRect(x, y, w, h);
   }

   public static final void drawRect(Graphics g, int x, int y, int w, int h, int RGB) {
      g.setColor(RGB);
      g.drawRect(x, y, w - 1, h - 1);
   }

   public static final void drawStringShadow(Graphics g, String s, int x, int y, int color, int shadowColor) {
      g.setColor(shadowColor);
      g.drawString(s, x + 1, y + 1, 0);
      g.setColor(color);
      g.drawString(s, x, y, 0);
   }

   public static void drawSelectedKuangEffect(Graphics g, int kx, int ky, int kw, int kh) {
      g.setColor(sColor[sCount % 5]);
      g.drawRect(kx, ky, kw - 1, kh - 1);
   }

   public static void drawReactionKuangEffect(Graphics g, int kx, int ky, int kw, int kh) {
      g.setColor(Cons.COLOR_MENU_SEL_ITEM_BG);
      g.fillRect(kx, ky, kw, kh);
   }

   public static void drawLetter(Graphics g, String str, int x, int y, int color_1, int color_2) {
      if (MainCanvas.countTick % 2 == 0) {
         g.setColor(color_1);
      } else if (MainCanvas.countTick % 2 == 1) {
         g.setColor(color_2);
      }

      g.drawString(str, x, y, 20);
   }

   public static void drawSelectedCursorEffect(Graphics g, int x, int y) {
      g.drawImage(MainCanvas.cursorImg, x, y, 20);
   }

   public static void drawExchangeRimEffect(Graphics g, int x, int y, int w, int h) {
      if (sCount % 5 <= 2) {
         g.setColor(16777215);
         g.drawRect(x - 1, y - 1, w + 1, h + 1);
         g.setColor(0);
         g.drawRect(x, y, w - 1, h - 1);
         g.setColor(16777215);
         g.drawRect(x + 1, y + 1, w - 3, h - 3);
      } else {
         g.setColor(0);
         g.drawRect(x - 1, y - 1, w + 1, h + 1);
         g.setColor(16777215);
         g.drawRect(x, y, w - 1, h - 1);
         g.setColor(0);
         g.drawRect(x + 1, y + 1, w - 3, h - 3);
      }

   }

   public static String[] colorChangeLine(String s, int useWidth, Font font) {
      char[] tempChars = s.toCharArray();
      int lg = tempChars.length;
      int[] sign = new int[34];
      int number = 0;
      StringBuffer sb = new StringBuffer();
      Vector tempV = new Vector();

      int i;
      for(i = 0; i < lg; ++i) {
         if (tempChars[i] != '\n') {
            tempV.addElement(new Character(tempChars[i]));
         }

         if (tempChars[i] != '$') {
            sb.append(tempChars[i]);
         }
      }

      i = 0;

      for(int ii = tempV.size(); i < ii; ++i) {
         if (tempV.elementAt(i).toString().charAt(0) == '$') {
            sign[number] = i;
            ++number;
         }
      }

      String[] strs = wrapText(sb.toString(), useWidth, font);
      lg = strs.length;
      colorSigns = new byte[lg][];
      int[][] start_end = new int[lg][2];

      int i;
      for(int i = 0; i < lg; ++i) {
         i = strs[i].length();
         colorSigns[i] = new byte[i];
         if (i == 0) {
            start_end[i][0] = 0;
            start_end[i][1] = i - 1;
         } else {
            start_end[i][0] = start_end[i - 1][1] + 1;
            start_end[i][1] = start_end[i][0] + i - 1;
         }
      }

      Vector vector = new Vector(4);
      if (number % 2 != 0) {
         --number;
      }

      int b;
      int i;
      int j;
      for(i = 0; i < number; i += 2) {
         sign[i] -= i;
         sign[i + 1] -= i + 1 + 1;
         int a = getColorPlace(sign[i], start_end);
         b = getColorPlace(sign[i + 1], start_end);
         if (a == b) {
            int[] cs = new int[]{a, sign[i] - start_end[a][0], sign[i + 1] - start_end[a][0]};
            vector.addElement(cs);
         } else {
            i = b - a;
            if (i > 1) {
               for(j = a + 1; j < b; ++j) {
                  int[] cs = new int[]{j, 0, strs[j].length() - 1};
                  vector.addElement(cs);
               }
            }

            int[] cs = new int[]{a, sign[i] - start_end[a][0], start_end[a][1] - start_end[a][0]};
            vector.addElement(cs);
            cs = new int[]{b, 0, sign[i + 1] - start_end[b][0]};
            vector.addElement(cs);
         }
      }

      lg = vector.size();
      int[][] signs = new int[lg][];
      Enumeration e = vector.elements();

      for(b = 0; e.hasMoreElements(); ++b) {
         signs[b] = (int[])e.nextElement();
      }

      for(i = 0; i < lg; ++i) {
         for(j = signs[i][1]; j <= signs[i][2]; ++j) {
            colorSigns[signs[i][0]][j] = 1;
         }
      }

      return strs;
   }

   public static void drawRollStr(Graphics g, String rollStr, int strX, int strY, int w, byte type, byte speed) {
      if (getStrLen(rollStr) <= w) {
         g.drawString(rollStr, strX, strY, 20);
      } else {
         int clipX = g.getClipX();
         int clipY = g.getClipY();
         int clipW = g.getClipWidth();
         int clipH = g.getClipHeight();
         g.setClip(strX, strY, w, MainCanvas.CHARH + 3);
         int leftX;
         switch(type) {
         case 0:
            leftX = strX - speed * rollCounter;
            if (leftX + getStrLen(rollStr) < strX) {
               rollCounter = -(strX + w) / speed + 2;
            } else if (waitCounter < 3) {
               ++waitCounter;
            } else {
               ++rollCounter;
            }

            g.drawString(rollStr, leftX, strY, 20);
            break;
         case 1:
            if (isLeftRoll) {
               leftX = strX - speed * rollCounter;
               g.drawString(rollStr, leftX, strY, 20);
               ++rollCounter;
               if (leftX + getStrLen(rollStr) < strX + w) {
                  isLeftRoll = false;
                  rollCounter = 0;
               }
            } else {
               leftX = strX - (getStrLen(rollStr) - w) + speed * rollCounter;
               g.drawString(rollStr, leftX, strY, 20);
               ++rollCounter;
               if (leftX > strX) {
                  isLeftRoll = true;
                  rollCounter = 0;
               }
            }
         }

         g.setClip(clipX, clipY, clipW, clipH);
      }

   }

   public static final void drawImage(Graphics g, Image img, int x, int y, int trans) {
      drawImage(g, img, x, y, trans, 20);
   }

   public static final void drawImage(Graphics g, Image img, int x, int y, int trans, int archor) {
      if (trans == 0) {
         g.drawImage(img, x, y, 20);
      } else {
         g.drawRegion(img, 0, 0, img.getWidth(), img.getHeight(), trans, x, y, archor);
      }

   }

   public static final void drawRegion(Graphics g, Image src, int src_x, int src_y, int w, int h, int dst_x, int dst_y) {
      drawRegion(g, src, src_x, src_y, w, h, dst_x, dst_y, 0);
   }

   public static final void drawRegion(Graphics g, Image src, int src_x, int src_y, int w, int h, int dst_x, int dst_y, int trans) {
      try {
         g.drawRegion(src, src_x, src_y, w, h, trans, dst_x, dst_y, 20);
      } catch (IllegalArgumentException var10) {
      }

   }

   public static Image loadImage(String path) {
      return loadImage(path, true);
   }

   public static Image loadImage(String path, boolean isStatic) {
      try {
         Image img = Image.createImage(path);
         return img;
      } catch (IOException var3) {
         var3.printStackTrace();
         return null;
      }
   }

   public static final Image loadImage(String pkgPath, String filename) {
      try {
         byte[] tmpArr = readPKG(pkgPath, filename);
         Image img = Image.createImage(tmpArr, 0, tmpArr.length);
         tmpArr = (byte[])null;
         return img;
      } catch (Exception var4) {
         return null;
      }
   }

   public static final Image[] loadImage(String pkgPath, String[] filename) {
      try {
         Image[] img = new Image[filename.length];
         byte[][] tmpArr = readPKG(pkgPath, filename);

         for(int i = 0; i < tmpArr.length; ++i) {
            if (img[i] == null && tmpArr[i] != null) {
               img[i] = Image.createImage(tmpArr[i], 0, tmpArr[i].length);
            }
         }

         tmpArr = (byte[][])null;
         return img;
      } catch (Exception var5) {
         var5.printStackTrace();
         return null;
      }
   }

   public static final Image[] loadImageMap(Image[] img, String pkgPath, String[] filename) {
      try {
         int a = 0;
         int b = 0;
         byte[][] tmpArr = readPKG(pkgPath, filename);

         for(int i = 0; i < tmpArr.length; ++i) {
            if (img[i] != null) {
               ++b;
            } else if (tmpArr[i] != null) {
               img[i] = Image.createImage(tmpArr[i], 0, tmpArr[i].length);
               ++a;
               ++b;
            }
         }

         tmpArr = (byte[][])null;
         return img;
      } catch (Exception var7) {
         var7.printStackTrace();
         return null;
      }
   }

   public static final Image[] loadImageMoster(Image[] img, String pkgPath, String[] filename) {
      try {
         byte[][] tmpArr = readPKG(pkgPath, filename);

         for(int i = 0; i < tmpArr.length; ++i) {
            if (img[i] == null && tmpArr[i] != null) {
               img[i] = Image.createImage(tmpArr[i], 0, tmpArr[i].length);
               Monster.MonsterImgHa.put(filename[i], img[i]);
            }
         }

         tmpArr = (byte[][])null;
         return img;
      } catch (Exception var5) {
         var5.printStackTrace();
         return null;
      }
   }

   public static InputStream loadFile(String path, boolean isStatic) {
      try {
         InputStream is = (new Object()).getClass().getResourceAsStream(path);
         return is;
      } catch (Exception var3) {
         var3.printStackTrace();
         return null;
      }
   }

   public static void TouchDefault(int[] widgetID) {
      final int widgetNum = widgetID.length;
      UITitle title = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
      final Form form = new Form(title.getTitleText());
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      final TextField[] tf = new TextField[widgetNum];
      final UITextField[] textfield = new UITextField[widgetNum];
      final int[] restrictLev = new int[widgetNum];

      for(int i = 0; i < widgetNum; ++i) {
         textfield[i] = (UITextField)MainCanvas.curForm.getComponents().elementAt(widgetID[i]);
         restrictLev[i] = textfield[i].getStyle();
         int len = textfield[i].getMaxWordsNum();
         String titl = textfield[i].getLabelText();
         String con = textfield[i].getSb().toString();
         tf[i] = new TextField(titl, con, len, 2);
         form.append(tf[i]);
      }

      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            if (c != okCmd) {
               if (c == exitCmd) {
                  form.removeCommand(okCmd);
                  form.removeCommand(exitCmd);
                  MainCanvas.mc.setFullScreenMode(true);
                  MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
               }
            } else {
               int j = 0;

               while(true) {
                  if (j >= widgetNum) {
                     MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
                     break;
                  }

                  String tfContent = tf[j].getString();
                  Alert alertx;
                  if (tfContent.equals("") || tfContent == null) {
                     alertx = new Alert(Cons.C_STR[9], "Xin nhập nội dung!", (Image)null, AlertType.ERROR);
                     tf[j].setString("");
                     MainCanvas.aMidlet.display.setCurrent(alertx, form);
                     return;
                  }

                  if (tfContent.length() > textfield[j].getMaxWordsNum()) {
                     alertx = new Alert(Cons.C_STR[9], "Nội dung nhập quá dài!", (Image)null, AlertType.ERROR);
                     tf[j].setString("");
                     MainCanvas.aMidlet.display.setCurrent(alertx, form);
                     return;
                  }

                  if (restrictLev[j] == 1) {
                     for(int i = 0; i < tf[j].getString().length(); ++i) {
                        char ti = tf[j].getString().charAt(i);
                        if (ti < '0' || ti > '9') {
                           Alert alert = new Alert(Cons.C_STR[9], "Xin nhập chính xác!", (Image)null, AlertType.ERROR);
                           tf[j].setString("");
                           MainCanvas.aMidlet.display.setCurrent(alert, form);
                           return;
                        }
                     }
                  }

                  if (MainCanvas.curForm.clientCommand == 1638427) {
                     UILabel medal = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
                     int clanMedal = medal.getNum1();
                     if (Long.parseLong(tf[j].getString()) > (long)clanMedal) {
                        textfield[j].setSb(new StringBuffer(String.valueOf(clanMedal)));
                     } else {
                        textfield[j].setSb(new StringBuffer(tf[j].getString()));
                     }
                  } else {
                     textfield[j].setSb(new StringBuffer(tf[j].getString()));
                  }

                  ++j;
               }
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }

   public static final void Touch(String caption_1, String caption_2, int length, String content, final int number, final byte error) {
      matter = null;
      final Form form = new Form(caption_1);
      final TextField tf = new TextField(caption_2, "", length, 0);
      form.append(tf);
      if (MainCanvas.curForm.clientCommand == 1376260 && !content.equals("0") && !content.equals("") && content != null) {
         tf.setString(content);
      } else if (MainCanvas.curForm.clientCommand != 1376260 && content != null && !content.equals("")) {
         tf.setString(content);
      }

      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            UITextField textfield = (UITextField)MainCanvas.curForm.getComponents().elementAt(number);
            UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
            if (c == okCmd) {
               Util.matter = tf.getString();
               if (tf.getString().equals("")) {
                  Util.matter.equals("");
               } else {
                  Util.matter.equals(tf.getString());
               }

               Alert alertxx;
               if (tf.getString().equals("") || tf.getString() == null) {
                  alertxx = new Alert(Cons.C_STR[9], "Xin nhập nội dung!", (Image)null, AlertType.ERROR);
                  tf.setString("");
                  MainCanvas.aMidlet.display.setCurrent(alertxx, form);
                  return;
               }

               if (!MainCanvas.CharacterValidate(tf.getString(), error)) {
                  alertxx = new Alert(Cons.C_STR[9], "Nội dung nhập không phù hợp, xin nhập lại!", (Image)null, AlertType.ERROR);
                  tf.setString("");
                  MainCanvas.aMidlet.display.setCurrent(alertxx, form);
                  Util.matter = tf.getString();
                  if (!Util.matter.equals("")) {
                     Util.matter.equals("");
                  }

                  return;
               }

               if (error == 5) {
                  if (!tf.getString().trim().equals("DEL") && !tf.getString().trim().equals("del") || tf.getString().trim().length() < 3) {
                     alertxx = new Alert(Cons.C_STR[9], "Xin nhập chính xác!", (Image)null, AlertType.ERROR);
                     tf.setString("");
                     MainCanvas.aMidlet.display.setCurrent(alertxx, form);
                     Util.matter = tf.getString();
                     if (!Util.matter.equals("")) {
                        Util.matter.equals("");
                     }

                     return;
                  }
               } else if (error == 6) {
                  int l_nMoney;
                  for(l_nMoney = 0; l_nMoney < tf.getString().length(); ++l_nMoney) {
                     char ti = tf.getString().charAt(l_nMoney);
                     if (ti < '0' || ti > '9') {
                        Alert alert = new Alert(Cons.C_STR[9], "Xin nhập chính xác!", (Image)null, AlertType.ERROR);
                        tf.setString("");
                        MainCanvas.aMidlet.display.setCurrent(alert, form);
                        Util.matter = tf.getString();
                        if (!Util.matter.equals("")) {
                           Util.matter.equals("");
                        }

                        return;
                     }
                  }

                  l_nMoney = Integer.parseInt(tf.getString().trim());
                  if (l_nMoney > UIForm.myMoney) {
                     Alert alertx = new Alert(Cons.C_STR[9], "Số lượng nhập quá giới hạn hiện tại, xin nhập lại!", (Image)null, AlertType.ERROR);
                     tf.setString("");
                     MainCanvas.aMidlet.display.setCurrent(alertx, form);
                     Util.matter = tf.getString();
                     if (!Util.matter.equals("")) {
                        Util.matter.equals("");
                     }

                     return;
                  }
               }

               if (MainCanvas.getState() == 19) {
                  Util.matter = Util.Conversion(Util.matter);
               }

               textfield.setSb(new StringBuffer(Util.matter));
               if (MainCanvas.getState() == 18) {
                  MainCanvas.m_sName = Util.matter;
                  UIRadio gender = (UIRadio)MainCanvas.curForm.getComponents().elementAt(7);
                  if (textfield.isFocus()) {
                     menuBar.setMenuText("Sửa", 0);
                  } else if (gender.isFocus()) {
                     menuBar.setMenuText("", 0);
                  } else {
                     menuBar.setMenuText("Giới thiệu", 0);
                  }
               } else if (MainCanvas.curForm.clientCommand == 1376260) {
                  menuBar.setMenuText("Sửa", 0);
               } else {
                  menuBar.setMenuText("Xác nhận ", 0);
               }

               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            } else if (c == exitCmd) {
               form.removeCommand(okCmd);
               form.removeCommand(exitCmd);
               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            }

         }
      });
      if (matter != null && !matter.equals("")) {
         content.equals(matter);
      }

      MainCanvas.aMidlet.display.setCurrent(form);
   }

   public static final void Touch_Login(final int number_1, final int number_2) {
      final Form form = new Form("Xin nhập tài khoản và mật khẩu");
      final TextField tf = new TextField("Xin nhập tài khoản", "", 16, 0);
      final TextField tf_1 = new TextField("Xin nhập mật khẩu", "", 16, 0);
      form.append(tf);
      form.append(tf_1);
      if (MainCanvas.getState() != 21 && MainCanvas.getState() != 22 && MainCanvas.getState() != 11) {
         if (MainCanvas.name_password[0] != null && !MainCanvas.name_password[0].equals("")) {
            tf.setString(MainCanvas.name_password[0]);
         }

         if (MainCanvas.name_password[1] != null && !MainCanvas.name_password[1].equals("")) {
            tf_1.setString(MainCanvas.name_password[1]);
         }
      } else {
         UITextField userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(number_1);
         UITextField pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(number_2);
         if (userNameText.getSb().toString().trim() != null && !userNameText.getSb().toString().trim().equals("")) {
            tf.setString(userNameText.getSb().toString().trim());
         }

         if (pswText.getSb().toString().trim() != null && !pswText.getSb().toString().trim().equals("")) {
            tf_1.setString(pswText.getSb().toString().trim());
         }
      }

      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            UITextField userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(number_1);
            UITextField pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(number_2);
            UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
            if (c == okCmd) {
               if (tf.getString().equals("") || tf.getString() == null || tf_1.getString().equals("") || tf_1.getString() == null) {
                  Alert alert = new Alert(Cons.C_STR[9], "Xin nhập nội dung!", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
               }

               MainCanvas.name_password[0] = tf.getString();
               MainCanvas.name_password[1] = tf_1.getString();
               MainCanvas.name_password[0] = Util.Conversion(MainCanvas.name_password[0]);
               MainCanvas.name_password[1] = Util.Conversion(MainCanvas.name_password[1]);
               userNameText.setSb(new StringBuffer(MainCanvas.name_password[0]));
               pswText.setSb(new StringBuffer(MainCanvas.name_password[1]));
               if (MainCanvas.getState() == 11) {
                  menuBar.setMenuText("Đăng nhập", 0);
               } else {
                  menuBar.setMenuText("Xác nhận ", 0);
               }

               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            } else if (c == exitCmd) {
               form.removeCommand(okCmd);
               form.removeCommand(exitCmd);
               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }

   public static final void Touch_Exchange(final int number_1) {
      final Form form = new Form("Xin nhập mã đổi");
      final TextField tf = new TextField("Xin nhập mã đổi", "", 16, 0);
      form.append(tf);
      if (MainCanvas.getState() != 21 && MainCanvas.getState() != 22 && MainCanvas.getState() != 23 && MainCanvas.name_password[0] != null && !MainCanvas.name_password[0].equals("")) {
         tf.setString(MainCanvas.name_password[0]);
      }

      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            UITextField userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(number_1);
            UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
            if (c == okCmd) {
               Alert alert;
               if (tf.getString().equals("") || tf.getString() == null) {
                  alert = new Alert(Cons.C_STR[9], "Xin nhập nội dung！", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
               }

               MainCanvas.name_password[0] = tf.getString();
               if (!MainCanvas.CharacterValidate(tf.getString(), (byte)4)) {
                  alert = new Alert(Cons.C_STR[9], "Tài khoản nhập không phù hợp, xin nhập lại!", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
               }

               MainCanvas.name_password[0] = Util.Conversion(MainCanvas.name_password[0]);
               userNameText.setSb(new StringBuffer(MainCanvas.name_password[0]));
               menuBar.setMenuText("Xác nhận ", 0);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            } else if (c == exitCmd) {
               form.removeCommand(okCmd);
               form.removeCommand(exitCmd);
               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }

   public static final void Touch_Enroll() {
      final Form form = new Form("Xin nhập tài khoản và mật khẩu");
      final TextField tf = new TextField("Xin nhập tài khoản ", "", 16, 0);
      final TextField tf_1 = new TextField("Xin nhập mập khẩu", "", 16, 0);
      form.append(tf);
      form.append(tf_1);
      if (MainCanvas.name_password[0] != null && !MainCanvas.name_password[0].equals("")) {
         tf.setString(MainCanvas.name_password[0]);
      }

      if (MainCanvas.name_password[1] != null && !MainCanvas.name_password[1].equals("")) {
         tf_1.setString(MainCanvas.name_password[1]);
      }

      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            UITextField userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(9);
            UITextField pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(10);
            UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
            if (c == okCmd) {
               if (tf.getString().equals("") || tf.getString() == null || tf_1.getString().equals("") || tf_1.getString() == null) {
                  Alert alert = new Alert(Cons.C_STR[9], "Xin nhập nội dung！", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
               }

               MainCanvas.name_password[0] = tf.getString();
               MainCanvas.name_password[1] = tf_1.getString();
               MainCanvas.name_password[0] = Util.Conversion(MainCanvas.name_password[0]);
               MainCanvas.name_password[1] = Util.Conversion(MainCanvas.name_password[1]);
               userNameText.setSb(new StringBuffer(MainCanvas.name_password[0]));
               pswText.setSb(new StringBuffer(MainCanvas.name_password[1]));
               menuBar.setMenuText("Xác nhận ", 0);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            } else if (c == exitCmd) {
               form.removeCommand(okCmd);
               form.removeCommand(exitCmd);
               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }

   public static final void Touch_AmendPwd() {
      final Form form = new Form("Xin nhập tài khoản và mật khẩu");
      final TextField tf = new TextField("Xin nhập tài khoản ", "", 16, 0);
      final TextField tf_1 = new TextField("Xin nhập mập khẩu cũ", "", 16, 0);
      final TextField tf_2 = new TextField("Xin nhập mập khẩu mới", "", 16, 0);
      final TextField tf_3 = new TextField("Xin xác nhận mật khẩu", "", 16, 0);
      form.append(tf);
      form.append(tf_1);
      form.append(tf_2);
      form.append(tf_3);
      if (MainCanvas.m_sTemporaryStr[0] != null && !MainCanvas.m_sTemporaryStr[0].equals("")) {
         tf.setString(MainCanvas.m_sTemporaryStr[0]);
      }

      if (MainCanvas.m_sTemporaryStr[1] != null && !MainCanvas.m_sTemporaryStr[1].equals("")) {
         tf_1.setString(MainCanvas.m_sTemporaryStr[1]);
      }

      if (MainCanvas.m_sTemporaryStr[2] != null && !MainCanvas.m_sTemporaryStr[2].equals("")) {
         tf_2.setString(MainCanvas.m_sTemporaryStr[2]);
      }

      if (MainCanvas.m_sTemporaryStr[3] != null && !MainCanvas.m_sTemporaryStr[3].equals("")) {
         tf_3.setString(MainCanvas.m_sTemporaryStr[3]);
      }

      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            UITextField userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(10);
            UITextField pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(11);
            UITextField new_password = (UITextField)MainCanvas.curForm.getComponents().elementAt(12);
            UITextField validate = (UITextField)MainCanvas.curForm.getComponents().elementAt(13);
            UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
            if (c == okCmd) {
               Alert alert;
               if (tf.getString().equals("") || tf.getString() == null || tf_1.getString().equals("") || tf_1.getString() == null || tf_2.getString().equals("") || tf_2.getString() == null || tf_3.getString().equals("") || tf_3.getString() == null) {
                  alert = new Alert(Cons.C_STR[9], "Hãy nhập nội dung!", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
               }

               MainCanvas.m_sTemporaryStr[0] = tf.getString().trim();
               MainCanvas.m_sTemporaryStr[1] = tf_1.getString().trim();
               MainCanvas.m_sTemporaryStr[2] = tf_2.getString().trim();
               MainCanvas.m_sTemporaryStr[3] = tf_3.getString().trim();
               if (!MainCanvas.m_sTemporaryStr[2].equals(MainCanvas.m_sTemporaryStr[3])) {
                  alert = new Alert(Cons.C_STR[9], "Xác nhận mật khẩu có lỗi, nhập lại!", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
               }

               MainCanvas.m_sTemporaryStr[0] = Util.Conversion(MainCanvas.m_sTemporaryStr[0]);
               MainCanvas.m_sTemporaryStr[1] = Util.Conversion(MainCanvas.m_sTemporaryStr[1]);
               MainCanvas.m_sTemporaryStr[2] = Util.Conversion(MainCanvas.m_sTemporaryStr[2]);
               MainCanvas.m_sTemporaryStr[3] = Util.Conversion(MainCanvas.m_sTemporaryStr[3]);
               userNameText.setSb(new StringBuffer(MainCanvas.m_sTemporaryStr[0]));
               pswText.setSb(new StringBuffer(MainCanvas.m_sTemporaryStr[1]));
               new_password.setSb(new StringBuffer(MainCanvas.m_sTemporaryStr[2]));
               validate.setSb(new StringBuffer(MainCanvas.m_sTemporaryStr[3]));
               menuBar.setMenuText("Xác nhận ", 0);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            } else if (c == exitCmd) {
               form.removeCommand(okCmd);
               form.removeCommand(exitCmd);
               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }

   public static final void Touch_InputNum() {
      final Form form = new Form("Xin nhập số cần thao tác");
      final TextField tf = new TextField("Xin nhập số chữ", "", 9, 0);
      form.append(tf);
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            String numStr = null;
            if (c != okCmd) {
               if (c == exitCmd) {
                  form.removeCommand(okCmd);
                  form.removeCommand(exitCmd);
                  MainCanvas.mc.setFullScreenMode(true);
                  MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
               }
            } else {
               if (tf.getString().equals("") || tf.getString() == null) {
                  Alert alertx = new Alert(Cons.C_STR[9], "Xin nhập nội dung!", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alertx, form);
                  return;
               }

               numStr = tf.getString();
               int i = 0;

               int len;
               char cc;
               for(len = numStr.length(); i < len; ++i) {
                  cc = numStr.charAt(i);
                  if (cc < '0' || cc > '9') {
                     Alert alert = new Alert(Cons.C_STR[9], "Nội dung nhập không phù hợp, nhập lại!", (Image)null, AlertType.ERROR);
                     MainCanvas.aMidlet.display.setCurrent(alert, form);
                     return;
                  }
               }

               MainCanvas.curTopForm.sb = new StringBuffer();
               i = 0;

               for(len = numStr.length(); i < len; ++i) {
                  cc = numStr.charAt(i);
                  MainCanvas.curTopForm.addNumChar(cc);
               }

               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }

   public static final void Touch_Auction() {
      final Form form = new Form("Xin nhập giá khởi điểm và giá duy nhất");
      final TextField tf = new TextField("Xin nhập giá khởi điểm", "", 9, 0);
      final TextField tf1 = new TextField("Xin nhập giá bán ngay", "", 9, 0);
      form.append(tf);
      form.append(tf1);
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            UITextField bidPrice = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
            UITextField supPrice = (UITextField)MainCanvas.curForm.getComponents().elementAt(5);
            String numStr = null;
            String numStr1 = null;
            if (c != okCmd) {
               if (c == exitCmd) {
                  form.removeCommand(okCmd);
                  form.removeCommand(exitCmd);
                  MainCanvas.mc.setFullScreenMode(true);
                  MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
               }
            } else {
               if (tf.getString().equals("") || tf.getString() == null || tf1.getString().equals("") || tf1.getString() == null) {
                  Alert alert = new Alert(Cons.C_STR[9], "Nội dung không được để trống，Xin nhập nội dung！", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
               }

               numStr = tf.getString();
               numStr1 = tf1.getString();
               int i = 0;

               int len;
               char num;
               Alert alertx;
               for(len = numStr.length(); i < len; ++i) {
                  num = numStr.charAt(i);
                  if (num < '0' || num > '9') {
                     alertx = new Alert(Cons.C_STR[9], "Nội dung không phù hợp, nhập lại!", (Image)null, AlertType.ERROR);
                     MainCanvas.aMidlet.display.setCurrent(alertx, form);
                     return;
                  }
               }

               i = 0;
               len = numStr1.length();

               while(true) {
                  if (i >= len) {
                     if (numStr.trim().length() > 8) {
                        numStr = "99999998";
                     }

                     if (numStr1.trim().length() > 8) {
                        numStr1 = "99999999";
                     }

                     bidPrice.setSb(new StringBuffer(numStr));
                     supPrice.setSb(new StringBuffer(numStr1));
                     MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
                     break;
                  }

                  num = numStr1.charAt(i);
                  if (num < '0' || num > '9') {
                     alertx = new Alert(Cons.C_STR[9], "Nội dung không phù hợp, nhập lại!", (Image)null, AlertType.ERROR);
                     MainCanvas.aMidlet.display.setCurrent(alertx, form);
                     return;
                  }

                  ++i;
               }
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }

   public static final void Touch_Incremece() {
      final Form form = new Form("Xin nhập số tin nhắn gửi");
      final TextField tf = new TextField("Xin nhập số chữ", "", 1, 0);
      tf.setString("5");
      form.append(tf);
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            UITextField bidPrice = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
            String numStr = tf.getString();
            if (c != okCmd) {
               if (c == exitCmd) {
                  form.removeCommand(okCmd);
                  form.removeCommand(exitCmd);
                  MainCanvas.mc.setFullScreenMode(true);
                  MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
               }
            } else {
               Alert alertx;
               if (numStr.equals("") || numStr == null) {
                  alertx = new Alert(Cons.C_STR[9], "Nội dung không để trống, hãy nhập nội dung!", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alertx, form);
                  return;
               }

               int i = 0;

               for(int len = numStr.length(); i < len; ++i) {
                  int num = numStr.charAt(i);
                  if (num < '0' || num > '9') {
                     Alert alert = new Alert(Cons.C_STR[9], "Nội dung không phù hợp, nhập lại!", (Image)null, AlertType.ERROR);
                     MainCanvas.aMidlet.display.setCurrent(alert, form);
                     return;
                  }
               }

               if (Integer.parseInt(numStr) > 9) {
                  alertx = new Alert(Cons.C_STR[9], "Nhiều nhất nhập được 9 tin, nhập lại!", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alertx, form);
                  return;
               }

               bidPrice.setSb(new StringBuffer(numStr));
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }

   public static final void Touch_MailMoney() {
      final Form form = new Form("Nhâp tiền trả lại");
      final TextField tf = new TextField("Xin nhập số chữ", "", 8, 0);
      form.append(tf);
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            UITextField textfield3 = (UITextField)MainCanvas.curForm.getComponents().elementAt(13);
            String numStr = tf.getString();
            if (c != okCmd) {
               if (c == exitCmd) {
                  form.removeCommand(okCmd);
                  form.removeCommand(exitCmd);
                  MainCanvas.mc.setFullScreenMode(true);
                  MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
               }
            } else {
               while(numStr.startsWith("0")) {
                  numStr = numStr.substring(1);
               }

               if (numStr.equals("") || numStr == null) {
                  textfield3.setSb(new StringBuffer(Integer.toString(0)));
                  PCMail.money = false;
                  PCMail.Postage();
                  MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
                  return;
               }

               int i = 0;
               int len = numStr.length();

               while(true) {
                  if (i >= len) {
                     long CurrentlyMoney = Long.parseLong(numStr);
                     if (CurrentlyMoney <= 0L) {
                        textfield3.setSb(new StringBuffer(Integer.toString(0)));
                        return;
                     }

                     if (CurrentlyMoney + (long)PCMail.m_nPostage >= (long)PCMail.m_nBackpackMoney && (MainCanvas.curForm.clientCommand == 1703937 || MainCanvas.curForm.clientCommand == 1703952)) {
                        CurrentlyMoney = (long)(PCMail.m_nBackpackMoney - PCMail.m_nPostage);
                        if (CurrentlyMoney > 0L) {
                           textfield3.setSb(new StringBuffer(Long.toString(CurrentlyMoney)));
                        } else {
                           textfield3.setSb(new StringBuffer(Long.toString(0L)));
                        }
                     } else {
                        textfield3.setSb(new StringBuffer(numStr));
                     }

                     PCMail.money = true;
                     PCMail.Postage();
                     MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
                     break;
                  }

                  int num = numStr.charAt(i);
                  if (num < '0' || num > '9') {
                     Alert alert = new Alert(Cons.C_STR[9], "Nội dung không phù hợp, nhập lại!", (Image)null, AlertType.ERROR);
                     MainCanvas.aMidlet.display.setCurrent(alert, form);
                     return;
                  }

                  ++i;
               }
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }

   public static final void Touch_Gamble(int index) {
      gambleIndex = index;
      final Form form = new Form("Xin nhập số tiền");
      final TextField tf = new TextField("Xin nhập số chữ", "", 2, 0);
      form.append(tf);
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            UITextField textfield3 = (UITextField)MainCanvas.curForm.getComponents().elementAt(24 + Util.gambleIndex);
            String oldNum = textfield3.getSb().toString();
            String numStr = tf.getString();
            if (c != okCmd) {
               if (c == exitCmd) {
                  form.removeCommand(okCmd);
                  form.removeCommand(exitCmd);
                  MainCanvas.mc.setFullScreenMode(true);
                  MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
               }
            } else {
               while(numStr.startsWith("0") && numStr.length() > 1) {
                  numStr = numStr.substring(1);
               }

               if (numStr.equals("") || numStr == null) {
                  Alert alert = new Alert(Cons.C_STR[9], "Nội dung không để trống，Xin nhập nội dung!", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
               }

               int i = 0;

               int oldMoney;
               for(oldMoney = numStr.length(); i < oldMoney; ++i) {
                  int num = numStr.charAt(i);
                  if (num < '0' || num > '9') {
                     Alert alertx = new Alert(Cons.C_STR[9], "Nội dung không phù hợp, nhập lại!", (Image)null, AlertType.ERROR);
                     MainCanvas.aMidlet.display.setCurrent(alertx, form);
                     return;
                  }
               }

               UILabel label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(37);
               oldMoney = Integer.parseInt(label_2.getText());
               oldMoney += Integer.parseInt(oldNum);
               oldMoney -= Integer.parseInt(numStr);
               if (oldMoney < 0) {
                  textfield3.setSb(new StringBuffer("0"));
                  UITopForm.createLocalTopForm((byte)0, (String)"Không đủ Tiền", "Xác nhận ", "", -1, -2);
               } else {
                  label_2.setText("" + oldMoney);
                  textfield3.setSb(new StringBuffer(numStr));
               }

               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }

   public static final void TouchBing(final int number_1, final int number_2) {
      final Form form = new Form("Xin nhập tài khoản và mật khẩu");
      final TextField tf = new TextField("Xin nhập tài khoản ", "", 16, 0);
      final TextField tf_1 = new TextField("Xin nhập mập khẩu", "", 16, 0);
      form.append(tf);
      form.append(tf_1);
      UITextField userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(number_1);
      UITextField pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(number_2);
      if (userNameText != null && !userNameText.equals("")) {
         tf.setString(userNameText.getSb().toString().trim());
      }

      if (pswText != null && !pswText.equals("")) {
         tf_1.setString(pswText.getSb().toString().trim());
      }

      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            UITextField userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(number_1);
            UITextField pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(number_2);
            UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
            if (c == okCmd) {
               if (tf.getString().equals("") || tf.getString() == null || tf_1.getString().equals("") || tf_1.getString() == null) {
                  Alert alert = new Alert(Cons.C_STR[9], "Hãy nhập nội dung!", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
               }

               userNameText.setSb(new StringBuffer(tf.getString()));
               pswText.setSb(new StringBuffer(tf_1.getString()));
               menuBar.setMenuText("Xác nhận ", 0);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            } else if (c == exitCmd) {
               form.removeCommand(okCmd);
               form.removeCommand(exitCmd);
               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }

   public static final void Touch_Charge_Normal(final int number_1, final int number_2, String title, String s1, String s2) {
      final Form form = new Form(title);
      final TextField tf = new TextField(s1, "", 17, 0);
      final TextField tf_1 = new TextField(s2, "", 18, 0);
      form.append(tf);
      form.append(tf_1);
      UITextField userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(number_1);
      UITextField pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(number_2);
      if (userNameText != null) {
         tf.setString(userNameText.getSb().toString().trim());
      }

      if (pswText != null) {
         tf_1.setString(pswText.getSb().toString().trim());
      }

      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            UITextField userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(number_1);
            UITextField pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(number_2);
            if (c == okCmd) {
               if (tf.getString().equals("") || tf.getString() == null || tf_1.getString().equals("") || tf_1.getString() == null) {
                  Alert alert = new Alert(Cons.C_STR[9], "Hãy nhập nội dung!", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
               }

               userNameText.setSb(new StringBuffer(tf.getString()));
               pswText.setSb(new StringBuffer(tf_1.getString()));
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            } else if (c == exitCmd) {
               form.removeCommand(okCmd);
               form.removeCommand(exitCmd);
               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }

   public static int factorial(int x, int r) {
      int result = 1;

      for(int i = 0; i < r; ++i) {
         result *= x;
      }

      return result;
   }

   public static void setReturnTaskPicUnSelected() {
      for(int i = 0; i < focusPics.size(); ++i) {
         UIPicture pic = (UIPicture)focusPics.elementAt(i);
         pic.setSelected(false);
      }

   }

   public static String Conversion(String str) {
      for(int i = 0; i < str.length(); ++i) {
         char ti = str.charAt(i);
         if (ti >= 'A' && ti <= 'Z') {
            char newTi = (char)(ti + 32);
            str = str.replace(ti, newTi);
         }
      }

      return str;
   }

   public static final Image getDeviImage(Image srcImg, int desW, int desH) {
      int srcW = srcImg.getWidth();
      int srcH = srcImg.getHeight();
      int[] srcBuf = new int[srcW * srcH];
      srcImg.getRGB(srcBuf, 0, srcW, 0, 0, srcW, srcH);
      short[] tabY = new short[desH];
      short[] tabX = new short[desW];
      int sb = 0;
      int db = 0;
      int tems = 0;
      int temd = 0;
      int distance = srcH > desH ? srcH : desH;

      int i;
      for(i = 0; i <= distance; ++i) {
         tabY[db] = (short)sb;
         tems += srcH;
         temd += desH;
         if (tems > distance) {
            tems -= distance;
            ++sb;
         }

         if (temd > distance) {
            temd -= distance;
            ++db;
         }
      }

      sb = 0;
      db = 0;
      tems = 0;
      temd = 0;
      distance = srcW > desW ? srcW : desW;

      for(i = 0; i <= distance; ++i) {
         tabX[db] = (short)sb;
         tems += srcW;
         temd += desW;
         if (tems > distance) {
            tems -= distance;
            ++sb;
         }

         if (temd > distance) {
            temd -= distance;
            ++db;
         }
      }

      int[] desBuf = new int[desW * desH];
      int dx = false;
      int dy = 0;
      int sx = false;
      int sy = 0;
      int oldy = -1;

      for(int i = 0; i < desH; ++i) {
         if (oldy == tabY[i]) {
            System.arraycopy(desBuf, dy - desW, desBuf, dy, desW);
         } else {
            int dx = 0;

            for(int j = 0; j < desW; ++j) {
               desBuf[dy + dx] = srcBuf[sy + tabX[j]];
               ++dx;
            }

            sy += (tabY[i] - oldy) * srcW;
         }

         oldy = tabY[i];
         dy += desW;
      }

      return Image.createRGBImage(desBuf, desW, desH, false);
   }

   public static int drawEspecialDoubleNum(Graphics g, int num1, int num2, int x, int y) {
      int frame_w = MainCanvas.ui_numMImg.frame_w;
      int width = false;
      String num1Str = String.valueOf(num1);
      String num2Str = String.valueOf(num2);
      int width1 = 0;

      int j;
      for(j = String.valueOf(num1).length(); width1 < j; ++width1) {
         byte lev_digit = Byte.parseByte(String.valueOf(num1Str.charAt(width1)));
         MainCanvas.ui_numMImg.draw(g, x + (frame_w + 1) * width1, y, lev_digit);
      }

      width1 = (frame_w + 1) * String.valueOf(num1).length() + 1;
      MainCanvas.ui_dataNumMImg.draw(g, x + width1 - 1, y, 10);
      j = 0;

      for(int n = String.valueOf(num2).length(); j < n; ++j) {
         byte lev_digit = Byte.parseByte(String.valueOf(num2Str.charAt(j)));
         MainCanvas.ui_numMImg.draw(g, x + width1 + frame_w + 2 + (frame_w + 1) * j, y, lev_digit);
      }

      int width = width1 + frame_w + 2 + (frame_w + 1) * String.valueOf(num2).length();
      return width;
   }

   public static int[] getEspecialDoubleIntSize(int num1, int num2) {
      int[] size = new int[2];
      int w = (MainCanvas.ui_numMImg.frame_w + 1) * String.valueOf(num1).length() + 1 + MainCanvas.ui_numMImg.frame_w + 2 + (MainCanvas.ui_numMImg.frame_w + 1) * String.valueOf(num2).length();
      int h = MainCanvas.ui_numMImg.frame_h;
      size[0] = w;
      size[1] = h;
      return size;
   }

   public static int getMaxStringLen(String[] str) {
      if (str != null && str.length != 0) {
         int maxLen = str[0].length();

         for(int i = 1; i < str.length; ++i) {
            if (str[i].length() > maxLen) {
               maxLen = str[i].length();
            }
         }

         return maxLen;
      } else {
         return 0;
      }
   }

   public static String replaceFirstStr(String srcStr, String regex, String replacement) {
      int index = srcStr.indexOf(regex);
      if (index != -1 && replacement != null) {
         int endIndex = srcStr.indexOf(regex) + regex.length();
         StringBuffer objSB = new StringBuffer();
         objSB.append(srcStr.substring(0, index)).append(replacement).append(srcStr.substring(endIndex, srcStr.length()));
         return objSB.toString();
      } else {
         return srcStr;
      }
   }

   public static String replaceAllStr(String srcStr, String regex, String replacement) {
      if (regex.equals(replacement)) {
         return srcStr;
      } else {
         String objStr = srcStr;

         String beforeStr;
         do {
            beforeStr = objStr;
            objStr = replaceFirstStr(objStr, regex, replacement);
         } while(!objStr.equals(beforeStr));

         return objStr;
      }
   }

   public static int getCharNum(String str, char ch) {
      int count = 0;
      char[] chs = str.toCharArray();

      for(int i = 0; i < chs.length; ++i) {
         if (chs[i] == ch) {
            ++count;
         }
      }

      return count;
   }

   public static String[] split(String original, String separator) {
      Vector nodes = new Vector();

      for(int index = original.indexOf(separator); index >= 0; index = original.indexOf(separator)) {
         nodes.addElement(original.substring(0, index));
         original = original.substring(index + separator.length());
      }

      nodes.addElement(original);
      String[] result = new String[nodes.size()];
      if (nodes.size() > 0) {
         for(int i = 0; i < nodes.size(); ++i) {
            result[i] = (String)nodes.elementAt(i);
         }
      }

      return result;
   }

   public static void sendSMS(final String data, final String address) {
      (new Thread(new Runnable() {
         public void run() {
            try {
               MessageConnection smsconn = null;
               smsconn = (MessageConnection)Connector.open(address);
               TextMessage txtmessage = (TextMessage)smsconn.newMessage("text");
               txtmessage.setAddress(address);
               txtmessage.setPayloadText(data);
               smsconn.send(txtmessage);
               if (PassPort.waitActive) {
                  MainCanvas.activeStartTime = System.currentTimeMillis();
                  MainCanvas.TopForm((byte)0, "Vui lòng chờ", "", "", -2, -2);
               } else {
                  MainCanvas.TopForm((byte)0, "Đã gửi lệnh, hãy nhanh chóng tra kết quả bằng tin nhắn", "", "Đóng", -120, -1);
               }
            } catch (Exception var3) {
               var3.printStackTrace();
            }

         }
      })).start();
   }
}

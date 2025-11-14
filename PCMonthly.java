public class PCMonthly {
   static final String SEND_INFO = "Đang gửi lệnh";
   static final String RECEIVED_INFO = "Đang nhận thông tin";
   static int sendWaitTime = 30;
   byte connectMark = 0;
   String connectLoghead = "";
   byte[] connectLogbody = null;
   String baoyueIP = "";
   String baoyueUrl = "";
   static byte bodyMark = 0;
   static int max_BodySend = 0;
   static final int MAX_SEND_LENGTH = 256;
   static String logAddress = "";
   byte conBaoyue = 0;
   private boolean[] isSelect;
   int baoyueItem = 0;
   byte otherBaoyueIndex = 0;
   static byte dingzhiLen = 0;
   static boolean is15Send = false;
   short[] monthlyItemID = null;
   String[] monthlyItemName = null;
   int[] monthlyItemLocation = null;
   byte[] monthlyItemMark = null;
   String monthlyTextInfo = null;
   boolean isMonthly = false;
   boolean isInBaoyueWait = false;
   private static PCMonthly instance;
   public static String explain;
   public static String[] name;
   public static int color;
   public static int[] index;
   public static int[] id;
   public static byte num;
   public static boolean[] m_bwhetherChoice;
   public static boolean displayUI;

   public static PCMonthly getInstance() {
      if (instance == null) {
         instance = new PCMonthly();
      }

      return instance;
   }

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 2686977:
         ba.writeUTF(NetInterface.getIP());
         ba.writeByte(getInstance().connectMark);
         ba.writeInt(getInstance().baoyueItem);
         ba.writeByte(getInstance().conBaoyue);
         ba.writeUTF(logAddress);
         ba.writeUTF(getInstance().connectLoghead);
         ba.writeByte(max_BodySend);
         if (sendWaitTime > 4) {
            sendWaitTime -= 4;
         }

         UITopForm.createLocalTopForm((byte)0, (String)("Đang gửi lệnh, Cần đợi" + sendWaitTime + "giây"), "", "", -2, -2);
         break;
      case 2686978:
         ba.writeByte(bodyMark);
         int length = 0;
         if (max_BodySend > 0) {
            if (max_BodySend == 1) {
               length = PassPort.Monthly.getContents().length;
            } else if (bodyMark == max_BodySend) {
               length = PassPort.Monthly.getContents().length - (bodyMark - 1) * 256;
            } else {
               length = 256;
            }

            byte[] s = new byte[length];

            for(int i = 0; i < length; ++i) {
               s[i] = PassPort.Monthly.getContents()[i + (bodyMark - 1) * 256];
            }

            getInstance().connectLogbody = s;
         }

         ba.writeShort((short)length);
         ba.writeByteArray(getInstance().connectLogbody);
         break;
      case 2686979:
         dingzhiLen = (byte)getInstance().monthlyItemName.length;
         getInstance().otherBaoyueIndex = 0;
         is15Send = false;
         ba.writeByte(dingzhiLen);

         int i;
         for(i = 0; i < dingzhiLen; ++i) {
            ba.writeByte(getInstance().isSelect[i] ? 0 : 1);
            ba.writeInt(getInstance().monthlyItemID[i]);
            ba.writeInt(getInstance().monthlyItemLocation[i]);
            if (getInstance().monthlyItemMark[i] == 1) {
               getInstance().otherBaoyueIndex = (byte)i;
            }
         }

         for(i = 0; i < getInstance().otherBaoyueIndex; ++i) {
            if (getInstance().isSelect[i]) {
               getInstance().setBaoyue(1, getInstance().monthlyItemID[i]);
               MainCanvas.ni.send(2686977);
               break;
            }
         }

         for(i = getInstance().otherBaoyueIndex; i < dingzhiLen; ++i) {
            if (getInstance().isSelect[i]) {
               getInstance().setBaoyue(1, getInstance().monthlyItemID[i]);
               MainCanvas.ni.send(2686977);
               is15Send = true;
               return ba.toByteArray();
            }
         }

         return ba.toByteArray();
      case 2686980:
         ba.writeByte(num);

         for(byte i = 0; i < num; ++i) {
            ba.writeBoolean(m_bwhetherChoice[i]);
            ba.writeInt(index[i]);
            ba.writeInt(id[i]);
         }
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      byte type;
      int i;
      switch(commID) {
      case -2144796671:
         type = data.readByte();
         if (type == 1) {
            bodyMark = 1;
            MainCanvas.ni.send(2686978);
         } else {
            UITopForm.createLocalTopForm((byte)0, (String)"Yêu cầu thất bại, hãy thử lại", "Xác nhận", "", -1, -2);
            MainCanvas.isWaiting = false;
         }
         break;
      case -2144796670:
         type = data.readByte();
         switch(type) {
         case 1:
            getInstance().connectMark = data.readByte();
            getInstance().baoyueIP = data.readUTF();
            getInstance().baoyueUrl = data.readUTF();
            MainCanvas.isWaiting = true;
            PassPort.accessMonthlyUrl(getInstance().baoyueIP, getInstance().baoyueUrl);
            logAddress = "http://" + getInstance().baoyueIP + getInstance().baoyueUrl;
            if (sendWaitTime > 4) {
               sendWaitTime -= 4;
            }

            UITopForm.createLocalTopForm((byte)0, (String)("Đang nhận thông tin, Cần đợi" + sendWaitTime + "giây"), "", "", -2, -2);
            return;
         case 2:
            return;
         case 3:
            UITopForm.createLocalTopForm((byte)0, (String)data.readUTF(), "Xác nhận", "", -1, -2);
            return;
         case 4:
            if (!is15Send) {
               for(i = getInstance().otherBaoyueIndex; i < dingzhiLen; ++i) {
                  if (getInstance().isSelect[i]) {
                     getInstance().setBaoyue(1, getInstance().monthlyItemID[i]);
                     MainCanvas.ni.send(2686977);
                     return;
                  }
               }
            }

            return;
         case 5:
            ++bodyMark;
            MainCanvas.ni.send(2686978);
            return;
         default:
            UITopForm.createLocalTopForm((byte)0, (String)"Yêu cầu thất bại, hãy thử lại", "Xác nhận", "", -1, -2);
            MainCanvas.isWaiting = false;
            return;
         }
      case -2144796669:
         getInstance().isMonthly = false;
         type = data.readByte();
         if (type == 1) {
            PassPort.onlyGetUserID();
            getInstance().monthlyTextInfo = data.readUTF();
            byte len = data.readByte();
            if (len > 0) {
               getInstance().isMonthly = true;
               getInstance().monthlyItemID = null;
               getInstance().monthlyItemName = null;
               getInstance().monthlyItemLocation = null;
               getInstance().monthlyItemMark = null;
               getInstance().monthlyItemID = new short[len];
               getInstance().monthlyItemName = new String[len];
               getInstance().monthlyItemLocation = new int[len];
               getInstance().monthlyItemMark = new byte[len];

               for(int i = 0; i < len; ++i) {
                  getInstance().monthlyItemID[i] = data.readShort();
                  getInstance().monthlyItemName[i] = data.readUTF();
                  getInstance().monthlyItemLocation[i] = data.readInt();
                  getInstance().monthlyItemMark[i] = data.readByte();
               }
            }
         }
         break;
      case -2144796668:
         explain = data.readUTF();
         color = data.readInt();
         num = data.readByte();
         index = new int[num];
         id = new int[num];
         name = new String[num];
         m_bwhetherChoice = new boolean[num];

         for(i = 0; i < num; ++i) {
            index[i] = data.readInt();
            id[i] = data.readInt();
            name[i] = data.readUTF();
         }

         displayUI = true;
         break;
      case -2144796667:
         if (MainCanvas.curTopForm != null) {
            UITopForm.removeAllTopForm();
         }

         UIForm.backUIForm();
         break;
      case -2144796666:
         clear();
      }

   }

   void setBaoyue(int type, int itemId) {
      sendWaitTime = 45;
      this.connectMark = 0;
      max_BodySend = 0;
      logAddress = "";
      this.conBaoyue = (byte)type;
      this.baoyueItem = itemId;
      this.isInBaoyueWait = true;
      MainCanvas.isWaiting = true;
   }

   public static void clear() {
      getInstance().connectMark = 0;
      bodyMark = 0;
      max_BodySend = 0;
   }
}

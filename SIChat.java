import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class SIChat {
   public static final byte CHAT_MODE_LOCK = 0;
   public static final byte CHAT_MODE_SCROLL = 1;
   public static byte showMode = 1;
   public static final byte NET_CHANNEL_NONE = 0;
   public static final byte NET_CHANNEL_PRIVATE = 1;
   public static final byte NET_CHANNEL_WORLD = 2;
   public static final byte NET_CHANNEL_CLAN = 3;
   public static final byte NET_CHANNEL_SCENE = 4;
   public static final byte NET_CHANNEL_TEAM = 5;
   public static final byte NET_CHANNEL_SYSTEM = 6;
   public static final byte NET_CHANNEL_LOCAL = 7;
   public static final byte NET_CHANNEL_HINT = 8;
   public static final byte NET_CHANNEL_ANNOUNCE = 9;
   public static final byte NET_CHANNEL_FACE = 10;
   public static final byte NET_CHANNEL_HORN = 11;
   public static final byte NET_CHANNEL_NUM = 12;
   public static final byte SETUP_CHANNEL_NUM = 5;
   public static final byte CHAT_SHOW_LENGTH = 3;
   public static final int[] CHAT_CHANNAL_COLOR = new int[]{16776960, 16417256, 16750899, 52377, 65280, 39423, 16711680, 16711680, 8848254, 16777215, 8847615, 16716066};
   public static final int CHAT_BACKGROUND_COLOR = 2167563;
   public static final int CHAT_SELECT_COLOR = 16776960;
   public static long[] chatStartTime = new long[6];
   public static final short[] chatIntervalTime = new short[]{0, 5000, 10000, 5000, 5000, 5000};
   public static final String[] STR_CHANNEL_CHAT = new String[]{"", "[Riêng]", "[Thế]", "[Tộc]", "[Chiến]", "[Đội]", "[H.Thống]", "", "", "", "", "[Loa]"};
   public static final int CHAT_BUFF_NUM_MAX = 10;
   private static final int CHAT_SHOW_TIME_MIN = 7;
   private static int chatShowTimer = 0;
   public static String[] chatStringBuff = new String[0];
   public static int[][] chatColorBuff = new int[0][];
   public static String[] chatShow = new String[3];
   public static int[][] chatColor = new int[3][];
   public static int chatFrame = 0;
   public static short channelOpen = 4095;
   private static int chatTimer = 0;
   private static int chatDy = 0;
   private static int chatMaxDy = 0;
   private static final int CHAT_DY_STEP = 4;
   private static final int CHAT_DELAY_TIME = 20;
   public static Image buff = null;
   public static Graphics gBuff = null;
   public static final int CHAT_BUFF_HEIGHT;
   private static final int CHAT_DX = 2;
   public static final String LOCAL_DEFAULT_STR = "";
   public static final int GOODS_DX = 0;
   public static final int GOODS_NAME = 1;
   public static final int GOODS_ID = 2;
   public static final int GOODS_ATTACH = 3;
   public static final int GOODS_QUALITY = 4;
   public static final int GOODS_DRAW = 5;
   public static final byte CHANNEL_OFFSET = 1;
   long st = 0L;
   static int cheatTime;
   static int totalTime;
   public static String[] allChat;
   public static String[] allSenderName;
   public static int[] allSenderId;
   public static byte[] allChatChannel;
   static int anounceDX;
   static String anounceStr;
   static int anounceLength;
   static final byte ANOUNCE_STEP = 3;
   static int hornDX;
   static String hornStr;
   static int hornLength;
   static byte HORN_STEP;
   static final int strMoveSpeed = 2;
   static int strStartY;
   static String[] hintInfo;
   static int[][] color;
   static String[] hinfoBuff;
   static int[][] colorBuff;
   static int[] tmpc;

   static {
      CHAT_BUFF_HEIGHT = MainCanvas.CHARH * 3;
      cheatTime = 0;
      totalTime = 0;
      allChat = new String[0];
      allSenderName = new String[0];
      allSenderId = new int[0];
      allChatChannel = new byte[0];
      anounceDX = 0;
      anounceStr = null;
      anounceLength = 0;
      hornDX = 0;
      hornStr = null;
      hornLength = 0;
      HORN_STEP = 3;
      strStartY = MainCanvas.screenH;
      color = new int[0][];
      hinfoBuff = new String[0];
      colorBuff = new int[0][];
   }

   public SIChat() {
      this.initBuff();
   }

   public final void initBuff() {
      buff = Image.createImage(MainCanvas.screenW, CHAT_BUFF_HEIGHT);
      gBuff = buff.getGraphics();

      for(int i = 0; i < 3; ++i) {
         chatShow[i] = "";
      }

      upDataChatBar();
   }

   public static void clear() {
      UIChat.allChat = new String[0];
      UIChat.allSenderName = new String[0];
      UIChat.allSenderId = new int[0];
      UIChat.allChatChannel = new byte[0];
      UIChat.allGoodsContents = new Object[0];
      UIChat.allNameContent = new Object[0];
      UIChat.channelChat = new String[5][0];
      UIChat.channelSenderName = new String[5][0];
      UIChat.channelSenderId = new int[5][0];
      UIChat.channelGoodsContents = new Object[5][0];
      UIChat.channelNameContent = new Object[5][0];
      UIChat.allChatInSer = new String[0];
      UIChat.allSenderNameInSer = new String[0];
      UIChat.allSenderIdInSer = new int[0];
      UIChat.allChatChannelInSer = new byte[0];
      UIChat.allGoodsContentsInSer = new Object[0];
      UIChat.allNameContentInSer = new Object[0];
      UIChat.hornChat = new String[0];
      UIChat.hornSenderName = new String[0];
      UIChat.hornSenderId = new int[0];
      UIChat.hornChatChannel = new byte[0];
      UIChat.hornGoodsContents = new Object[0];
      UIChat.hornNameContent = new Object[0];
      UIChat.lastSenderName = new String[0];
      UIChat.lastSenderId = new int[0];
      UIChat.copySenderId = new int[0];
      chatStringBuff = new String[0];
      chatColorBuff = new int[0][];
      chatShow = new String[3];
      chatColor = new int[3][];
      buff = null;
   }

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 655361:
         UIChat.chatSendString = UIChat.chatSendString.replace('\n', ' ').replace('\r', ' ');
         ba.writeByte(UIChat.chatNetSendChannel);
         ba.writeInt(UIChat.chatRecieverId);
         ba.writeUTF(UIChat.chatSendString);
         ba.writeByte(UIChat.chatSendGoods.length);

         for(int i = 0; i < UIChat.chatSendGoods.length; ++i) {
            Vector goods = UIChat.chatSendGoods[i];
            ba.writeShort((Short)goods.elementAt(0));
            ba.writeByte((Byte)goods.elementAt(1));
            ba.writeByte((Byte)goods.elementAt(2));
         }

         UIChat.chatSendString = "";
         UIChat.chatSendGoods = new Vector[0];
         break;
      case 655362:
         ba.writeShort(channelOpen);
         break;
      case 655363:
         ba.writeLong(UIChat.goodsId);
         ba.writeInt(UIChat.goodsOwnerId);
         ba.writeByte(UIChat.goodsAttach);
         if (UIChat.goodsAttach == 20) {
            UIList.selectedListId = (int)UIChat.goodsId;
         }

         UIChat.goodsOwnerId = -1;
         UIChat.goodsId = -1L;
         UIChat.goodsAttach = 0;
         PCTreasure.unlockMenu = false;
         MainCanvas.isWaiting = true;
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2146828287:
         byte chatChannel = data.readByte();
         int chatSenderID = data.readInt();
         int chatRecieveID = data.readInt();
         String chatSenderName = data.readUTF();
         String chatContent = data.readUTF();
         int length = data.readByte();
         Vector[] goodsContent = (Vector[])null;
         Vector nameContent;
         if (length > 0) {
            goodsContent = new Vector[length];

            for(int i = 0; i < length; ++i) {
               nameContent = new Vector();
               nameContent.addElement(new Short(data.readShort()));
               nameContent.addElement("[" + data.readUTF() + "]");
               nameContent.addElement(new Long(data.readLong()));
               nameContent.addElement(new Byte(data.readByte()));
               nameContent.addElement(new Byte(data.readByte()));
               goodsContent[i] = nameContent;
            }
         }

         String chatSenderString = null;
         nameContent = new Vector();
         if (chatChannel == 1) {
            if (Player.getInstance().getID() == chatSenderID) {
               nameContent.addElement(new Integer(5));
               chatSenderString = "Nói với" + chatSenderName + "rằng";
               chatSenderID = chatRecieveID;
            } else {
               nameContent.addElement(new Integer(3));
               chatSenderString = chatSenderName + "với các hạ";
            }

            nameContent.addElement(new Integer(chatSenderName.length()));
         } else {
            if (chatSenderName != null && chatSenderName.length() != 0 && Player.getInstance().getID() != chatSenderID) {
               nameContent.addElement(new Integer(3));
               nameContent.addElement(new Integer(chatSenderName.length()));
            } else {
               nameContent = null;
            }

            chatSenderString = chatSenderName;
         }

         if (chatChannel <= 5) {
            chatContent = checkFace(chatContent, chatSenderID);
            if (chatContent.length() != 0) {
               saveLastSender(chatSenderName, chatSenderID);
            }
         }

         String chatShowString = composeChatShowString(STR_CHANNEL_CHAT[chatChannel], chatSenderString, chatContent, goodsContent);
         addChat(chatChannel, chatShowString, chatSenderID, chatSenderName, nameContent, goodsContent);
         break;
      case -2146828286:
         channelOpen = data.readShort();
         break;
      case -2146828285:
         HORN_STEP = data.readByte();
      case -2146828284:
      case -2146828283:
      default:
         break;
      case -2146828282:
         PCIncrement.formHornChannelToBuy = true;
         UIPicture.equipTypeForServer = 23;
      }

   }

   private static final String composeChatShowString(String channelStr, String chatSenderString, String chatContent, Vector[] goodsContent) {
      String elseString = channelStr + chatSenderString + (chatSenderString.equals("") ? "" : ":");
      if (goodsContent != null) {
         int offset = 0;
         StringBuffer contentBuff = new StringBuffer(chatContent);
         int elseLength = elseString.length();

         for(int i = 0; i < goodsContent.length; ++i) {
            Vector goods = goodsContent[i];
            short index = (Short)goods.elementAt(0);
            goods.setElementAt(new Short((short)(index + offset + elseLength)), 0);
            String str = (String)goods.elementAt(1);
            contentBuff.insert(offset + index, str);
            offset += str.length();
            index = (Short)goods.elementAt(0);
            int strLength = str.length();
            byte quality = (Byte)goods.elementAt(4);
            int[] specialGoods = new int[]{index, index + strLength - 1, Cons.COLOR_STUFF_QUALITY[quality]};
            goods.addElement(specialGoods);
         }

         chatContent = contentBuff.toString();
      }

      String chatShowString = elseString + chatContent;
      return chatShowString;
   }

   private static final void saveLastSender(String senderName, int senderId) {
      if (senderId != Player.getInstance().getID() && senderId != 0 && senderName != null && !senderName.equals("")) {
         for(int i = UIChat.lastSenderId.length - 1; i >= 0; --i) {
            if (UIChat.lastSenderId[i] == senderId) {
               return;
            }
         }

         UIChat.lastSenderId = Util.addArray((int[])UIChat.lastSenderId, 0, (int)senderId);
         UIChat.lastSenderName = Util.addArray((String[])UIChat.lastSenderName, 0, (String)senderName);
         if (UIChat.lastSenderId.length > 4) {
            UIChat.lastSenderId = Util.removeArray(UIChat.lastSenderId, UIChat.lastSenderId.length - 1);
            UIChat.lastSenderName = Util.removeArray(UIChat.lastSenderName, UIChat.lastSenderName.length - 1);
         }

      }
   }

   public static void addChat(byte netChannel, String str, int id, String senderName, Vector nameContent, Vector[] goodsContent) {
      boolean canAddServe = true;
      switch(netChannel) {
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
         canAddServe = false;
         addChatChannel((byte)(netChannel - 1), str, id, senderName, nameContent, goodsContent);
      case 6:
         if (canAddServe) {
            addChatServe(netChannel, str, id, senderName, nameContent, goodsContent);
         }
      case 10:
         addChatAll(netChannel, str, id, senderName, nameContent, goodsContent);
         addChatBuff(netChannel, str, goodsContent);
         break;
      case 7:
         addChatLocal(str);
         break;
      case 8:
         addChatAll(netChannel, str, id, senderName, nameContent, goodsContent);
         addChatHint(str, goodsContent);
         addChatServe(netChannel, str, id, senderName, nameContent, goodsContent);
         break;
      case 9:
         addChatAnnounce(str);
         addChatServe(netChannel, str, id, senderName, nameContent, goodsContent);
         break;
      case 11:
         addChatHorn(netChannel, str, id, senderName, nameContent, goodsContent);
         addChatAll(netChannel, str, id, senderName, nameContent, goodsContent);
         addChatHorn(str);
      }

   }

   public static void addSysChat(String str) {
      addChat((byte)6, str, -1, "", (Vector)null, (Vector[])null);
   }

   public final void tick() {
      // $FF: Couldn't be decompiled
   }

   private final boolean MoveChat() {
      int tmpStep = Math.min(4, Math.abs(chatMaxDy - chatDy));
      if (tmpStep != 4) {
         return false;
      } else {
         chatDy += chatMaxDy > chatDy ? tmpStep : -tmpStep;
         return true;
      }
   }

   public static final void addChatAll(byte netChannel, String str, int id, String senderName, Vector nameContent, Vector[] goodsContent) {
      if (str != null && !str.equals("")) {
         UIChat.allChat = Util.addArray((String[])UIChat.allChat, 0, (String)str);
         UIChat.allSenderId = Util.addArray((int[])UIChat.allSenderId, 0, (int)id);
         UIChat.allSenderName = Util.addArray((String[])UIChat.allSenderName, 0, (String)senderName);
         UIChat.allChatChannel = Util.addArray((byte[])UIChat.allChatChannel, 0, (byte)netChannel);
         UIChat.allGoodsContents = Util.addArray((Object[])UIChat.allGoodsContents, 0, (Object)goodsContent);
         UIChat.allNameContent = Util.addArray((Object[])UIChat.allNameContent, 0, (Object)nameContent);
         if (UIChat.allChat.length > 20) {
            UIChat.allChat = Util.removeArray(UIChat.allChat, UIChat.allChat.length - 1);
            UIChat.allSenderId = Util.removeArray(UIChat.allSenderId, UIChat.allSenderId.length - 1);
            UIChat.allSenderName = Util.removeArray(UIChat.allSenderName, UIChat.allSenderName.length - 1);
            UIChat.allChatChannel = Util.removeArray(UIChat.allChatChannel, UIChat.allChatChannel.length - 1);
            UIChat.allGoodsContents = Util.removeArray(UIChat.allGoodsContents, UIChat.allGoodsContents.length - 1);
            UIChat.allNameContent = Util.removeArray(UIChat.allNameContent, UIChat.allNameContent.length - 1);
         }

         if (UIChat.chatForm != null && UIChat.chatForm.showChannel == 100) {
            if ((UIChat.chatForm.controlState == 2 || UIChat.chatForm.controlState == 3 || UIChat.chatForm.controlState == 4) && ++UIChat.chatForm.curSelectChat > UIChat.allChat.length - 1) {
               UIChat.chatForm.curSelectChat = (byte)(UIChat.allChat.length - 1);
               UIChat.chatForm.curSelectNameStr = null;
               UIChat.chatForm.changeControlState((byte)2);
            }

            UIChat.chatForm.curChat = UIChat.allChat;
            UIChat.chatForm.refreshChannelString();
         }

      }
   }

   public static final void addChatServe(byte netChannel, String str, int id, String senderName, Vector nameContent, Vector[] goodsContent) {
      if (str != null && !str.equals("")) {
         UIChat.allChatInSer = Util.addArray((String[])UIChat.allChatInSer, 0, (String)str);
         UIChat.allSenderIdInSer = Util.addArray((int[])UIChat.allSenderIdInSer, 0, (int)id);
         UIChat.allSenderNameInSer = Util.addArray((String[])UIChat.allSenderNameInSer, 0, (String)senderName);
         UIChat.allChatChannelInSer = Util.addArray((byte[])UIChat.allChatChannelInSer, 0, (byte)netChannel);
         UIChat.allGoodsContentsInSer = Util.addArray((Object[])UIChat.allGoodsContentsInSer, 0, (Object)goodsContent);
         UIChat.allNameContentInSer = Util.addArray((Object[])UIChat.allNameContentInSer, 0, (Object)nameContent);
         if (UIChat.allChatInSer.length > (MainCanvas.isLargeScreen ? 20 : 10)) {
            UIChat.allChatInSer = Util.removeArray(UIChat.allChatInSer, UIChat.allChatInSer.length - 1);
            UIChat.allSenderIdInSer = Util.removeArray(UIChat.allSenderIdInSer, UIChat.allSenderIdInSer.length - 1);
            UIChat.allSenderNameInSer = Util.removeArray(UIChat.allSenderNameInSer, UIChat.allSenderNameInSer.length - 1);
            UIChat.allChatChannelInSer = Util.removeArray(UIChat.allChatChannelInSer, UIChat.allChatChannelInSer.length - 1);
            UIChat.allGoodsContentsInSer = Util.removeArray(UIChat.allGoodsContentsInSer, UIChat.allGoodsContentsInSer.length - 1);
            UIChat.allNameContentInSer = Util.removeArray(UIChat.allNameContentInSer, UIChat.allNameContentInSer.length - 1);
         }

         if (UIChat.chatForm != null && UIChat.chatForm.showChannel == 5) {
            if ((UIChat.chatForm.controlState == 2 || UIChat.chatForm.controlState == 3 || UIChat.chatForm.controlState == 4) && ++UIChat.chatForm.curSelectChat > UIChat.allChatInSer.length - 1) {
               UIChat.chatForm.curSelectChat = (byte)(UIChat.allChatInSer.length - 1);
               UIChat.chatForm.curSelectNameStr = null;
               UIChat.chatForm.changeControlState((byte)2);
            }

            UIChat.chatForm.curChat = UIChat.allChatInSer;
            UIChat.chatForm.refreshChannelString();
         }

      }
   }

   public static final void addChatHorn(byte netChannel, String str, int id, String senderName, Vector nameContent, Vector[] goodsContent) {
      if (str != null && !str.equals("")) {
         UIChat.hornChat = Util.addArray((String[])UIChat.hornChat, 0, (String)str);
         UIChat.hornSenderId = Util.addArray((int[])UIChat.hornSenderId, 0, (int)id);
         UIChat.hornSenderName = Util.addArray((String[])UIChat.hornSenderName, 0, (String)senderName);
         UIChat.hornChatChannel = Util.addArray((byte[])UIChat.hornChatChannel, 0, (byte)netChannel);
         UIChat.hornGoodsContents = Util.addArray((Object[])UIChat.hornGoodsContents, 0, (Object)goodsContent);
         UIChat.hornNameContent = Util.addArray((Object[])UIChat.hornNameContent, 0, (Object)nameContent);
         if (UIChat.hornChat.length > (MainCanvas.isLargeScreen ? 20 : 10)) {
            UIChat.hornChat = Util.removeArray(UIChat.hornChat, UIChat.hornChat.length - 1);
            UIChat.hornSenderId = Util.removeArray(UIChat.hornSenderId, UIChat.hornSenderId.length - 1);
            UIChat.hornSenderName = Util.removeArray(UIChat.hornSenderName, UIChat.hornSenderName.length - 1);
            UIChat.hornChatChannel = Util.removeArray(UIChat.hornChatChannel, UIChat.hornChatChannel.length - 1);
            UIChat.hornGoodsContents = Util.removeArray(UIChat.hornGoodsContents, UIChat.hornGoodsContents.length - 1);
            UIChat.hornNameContent = Util.removeArray(UIChat.hornNameContent, UIChat.hornNameContent.length - 1);
         }

         if (UIChat.chatForm != null && UIChat.chatForm.showChannel == 6) {
            if ((UIChat.chatForm.controlState == 2 || UIChat.chatForm.controlState == 3 || UIChat.chatForm.controlState == 4) && ++UIChat.chatForm.curSelectChat > UIChat.hornChat.length - 1) {
               UIChat.chatForm.curSelectChat = (byte)(UIChat.hornChat.length - 1);
               UIChat.chatForm.curSelectNameStr = null;
               UIChat.chatForm.changeControlState((byte)2);
            }

            UIChat.chatForm.curChat = UIChat.hornChat;
            UIChat.chatForm.refreshChannelString();
         }

      }
   }

   public static final void addChatChannel(byte showChatChannel, String str, int id, String senderName, Vector nameContent, Vector[] goodsContent) {
      if (str != null && !str.equals("")) {
         UIChat.channelChat[showChatChannel] = Util.addArray((String[])UIChat.channelChat[showChatChannel], 0, (String)str);
         UIChat.channelSenderId[showChatChannel] = Util.addArray((int[])UIChat.channelSenderId[showChatChannel], 0, (int)id);
         UIChat.channelSenderName[showChatChannel] = Util.addArray((String[])UIChat.channelSenderName[showChatChannel], 0, (String)senderName);
         UIChat.channelGoodsContents[showChatChannel] = Util.addArray((Object[])UIChat.channelGoodsContents[showChatChannel], 0, (Object)goodsContent);
         UIChat.channelNameContent[showChatChannel] = Util.addArray((Object[])UIChat.channelNameContent[showChatChannel], 0, (Object)nameContent);
         if (UIChat.channelChat[showChatChannel].length > 10) {
            UIChat.channelChat[showChatChannel] = Util.removeArray(UIChat.channelChat[showChatChannel], UIChat.channelChat[showChatChannel].length - 1);
            UIChat.channelSenderId[showChatChannel] = Util.removeArray(UIChat.channelSenderId[showChatChannel], UIChat.channelSenderId[showChatChannel].length - 1);
            UIChat.channelSenderName[showChatChannel] = Util.removeArray(UIChat.channelSenderName[showChatChannel], UIChat.channelSenderName[showChatChannel].length - 1);
            UIChat.channelGoodsContents[showChatChannel] = Util.removeArray(UIChat.channelGoodsContents[showChatChannel], UIChat.channelGoodsContents[showChatChannel].length - 1);
            UIChat.channelNameContent[showChatChannel] = Util.removeArray(UIChat.channelNameContent[showChatChannel], UIChat.channelNameContent[showChatChannel].length - 1);
         }

         if (UIChat.chatForm != null && UIChat.chatForm.showChannel == showChatChannel) {
            if ((UIChat.chatForm.controlState == 2 || UIChat.chatForm.controlState == 3 || UIChat.chatForm.controlState == 4) && ++UIChat.chatForm.curSelectChat > UIChat.channelChat[showChatChannel].length - 1) {
               UIChat.chatForm.curSelectChat = (byte)(UIChat.channelChat[showChatChannel].length - 1);
               UIChat.chatForm.curSelectNameStr = null;
               UIChat.chatForm.changeControlState((byte)2);
            }

            UIChat.chatForm.curChat = UIChat.channelChat[showChatChannel];
            UIChat.chatForm.refreshChannelString();
         }

      }
   }

   public static final void addChatLocal(String str) {
      UITitle.localChat = str;
      UITitle.chatDX = 0;
      UITitle.chatDX1 = 0;
      UITitle.isOneOnly = true;
      UITitle.isTwoScroll = false;
      UITitle.chatTimer = 0;
      UITitle.isSplash = false;
   }

   public static final void addChatLocal(String str, boolean isSplash) {
      UITitle.localChat = str;
      UITitle.chatDX = 0;
      UITitle.chatDX1 = 0;
      UITitle.isOneOnly = true;
      UITitle.isTwoScroll = false;
      UITitle.chatTimer = 0;
      UITitle.isSplash = isSplash;
   }

   public static final void addChatAnnounce(String str) {
      anounceStr = str;
      anounceDX = MainCanvas.screenW;
      anounceLength = MainCanvas.curFont.stringWidth(str);
   }

   public static final void drawAnounce(Graphics g, boolean isInUIForm) {
      if (anounceStr != null) {
         int hight = MainCanvas.screenH - CHAT_BUFF_HEIGHT - 36 - MainCanvas.CHARH - 2;
         if (!isInUIForm) {
            g.setColor(8519935);
            g.fillRect(0, hight, MainCanvas.screenW, MainCanvas.CHARH);
            g.setColor(CHAT_CHANNAL_COLOR[9]);
         } else {
            g.setColor(16711680);
         }

         g.drawString(anounceStr, anounceDX, hight, 0);
      }

   }

   public static final void addChatHorn(String str) {
      hornStr = str;
      hornDX = MainCanvas.screenW;
      hornLength = MainCanvas.curFont.stringWidth(str);
   }

   public static final void drawHorn(Graphics g, boolean isInUIForm) {
      if (hornStr != null) {
         int hight = 2 + SIManager.SIDy;
         if (!isInUIForm) {
            g.setColor(8519935);
            g.fillRect(0, hight, MainCanvas.screenW, MainCanvas.CHARH);
            g.setColor(CHAT_CHANNAL_COLOR[9]);
         } else {
            g.setColor(16711680);
         }

         int offsetX = hornDX;
         char[] chatCharArray = hornStr.toCharArray();
         int lg = chatCharArray.length;

         for(int i = 0; i < lg; ++i) {
            int faceId;
            if (i != lg - 1 && chatCharArray[i] == '#' && chatCharArray[i + 1] >= '0' && chatCharArray[i + 1] <= '9') {
               faceId = chatCharArray[i + 1] - 48;
               if (UIChat.faceSwing[faceId][MainCanvas.countTick % UIChat.faceSwing[faceId].length] == 0) {
                  MainCanvas.faceImg.draw(g, offsetX, hight, faceId);
               } else {
                  MainCanvas.faceImg1.draw(g, offsetX, hight, faceId);
               }

               ++i;
               offsetX += MainCanvas.faceImg.frame_w;
            } else {
               g.drawChar(chatCharArray[i], offsetX, hight, 20);
               faceId = g.getFont().charWidth(chatCharArray[i]);
               offsetX += faceId;
            }
         }
      }

   }

   public static final void removeChatLocal() {
      UITitle.localChat = "";
      UITitle.chatDX = 0;
      UITitle.chatDX1 = 0;
      UITitle.isOneOnly = true;
      UITitle.isTwoScroll = false;
      UITitle.chatTimer = 0;
   }

   public static final void addChatBuff(byte channel, String str, Vector[] goodsContent) {
      int[] color = findGoodsColor(channel, str, goodsContent);
      if (chatShowTimer <= 0) {
         addChatScreen(str, color);
      } else {
         chatColorBuff = Util.addArray(chatColorBuff, chatColorBuff.length, color);
         chatStringBuff = Util.addArray(chatStringBuff, chatStringBuff.length, str);
         if (chatStringBuff.length > 10) {
            chatColorBuff = Util.removeArray((int[][])chatColorBuff, 0);
            chatStringBuff = Util.removeArray((String[])chatStringBuff, 0);
         }
      }

   }

   private static final int[] findGoodsColor(byte channel, String str, Vector[] goodsContent) {
      int length = str.length();
      int[] color = new int[length];

      int i;
      for(i = 0; i < length; ++i) {
         color[i] = CHAT_CHANNAL_COLOR[channel];
      }

      if (goodsContent != null) {
         for(i = 0; i < goodsContent.length; ++i) {
            int[] goodsDraw = (int[])goodsContent[i].elementAt(5);

            for(int j = goodsDraw[0]; j <= goodsDraw[1]; ++j) {
               color[j] = goodsDraw[2];
            }
         }
      }

      return color;
   }

   public static final void addChatScreen(String str, int[] color) {
      chatShowTimer = 7;
      String[] tempStr = Util.wrapText(str, MainCanvas.screenW - 4, MainCanvas.curFont);
      int length = 0;

      int tmpLength;
      int a;
      for(int len = tempStr.length; length < len; ++length) {
         int ind = true;
         tmpLength = tempStr[length].length();
         if (len > 1 && length != len - 1 && (a = tempStr[length].lastIndexOf(35)) == tmpLength - 1) {
            tempStr[length] = tempStr[length].substring(0, a);
            tempStr[length + 1] = "#" + tempStr[length + 1];
         }
      }

      length = tempStr.length;
      int[][] tempColor = new int[0][];
      a = 0;

      for(tmpLength = 0; tmpLength < length; ++tmpLength) {
         int[] tc = new int[tempStr[tmpLength].length()];
         System.arraycopy(color, a, tc, 0, tc.length);
         tempColor = Util.addArray(tempColor, tempColor.length, tc);
         a += tc.length;
      }

      int len;
      if (showMode == 0) {
         tmpLength = tempStr.length;

         for(len = 0; len + tmpLength < chatShow.length; ++len) {
            chatShow[len] = chatShow[len + tmpLength];
            chatColor[len] = chatColor[len + tmpLength];
         }

         len = chatShow.length - 1;

         for(int k = tempStr.length - 1; len >= 0 && k >= 0; --len) {
            chatShow[len] = tempStr[k];
            chatColor[len] = tempColor[k];
            --k;
         }
      } else if (showMode == 1) {
         tmpLength = Math.min(chatShow.length, tempStr.length);

         for(len = 0; len < tmpLength; ++len) {
            chatShow[len] = tempStr[len];
            chatColor[len] = tempColor[len];
         }

         chatDy = 0;
         len = tempStr.length;
         if (len > 3) {
            len = 3;
         }

         chatMaxDy = len * MainCanvas.CHARH;
         chatTimer = 20;
      }

      upDataChatBar();
   }

   public static final void upDataChatBar() {
      if (gBuff != null) {
         Util.fillRect(gBuff, 0, 0, buff.getWidth(), buff.getHeight(), 2167563);
         gBuff.setColor(5190176);

         int i;
         for(i = 0; i < 2; ++i) {
            gBuff.drawLine(0, MainCanvas.CHARH * (i + 1), buff.getWidth(), MainCanvas.CHARH * (i + 1));
         }

         gBuff.setFont(MainCanvas.curFont);

         for(i = 0; i < 3; ++i) {
            drawChatString(gBuff, chatShow[i], chatColor[i], 2, MainCanvas.CHARH * i, false, false);
         }

      }
   }

   public static final void drawChatString(Graphics g, String str, int[] color, int x, int y, boolean isHint, boolean isDrawFace) {
      int offsetX = x;
      char[] chatCharArray = str.toCharArray();
      int lg = chatCharArray.length;

      for(int i = 0; i < lg; ++i) {
         int wordWidth;
         if (i != lg - 1 && chatCharArray[i] == '#' && chatCharArray[i + 1] >= '0' && chatCharArray[i + 1] <= '9') {
            wordWidth = chatCharArray[i + 1] - 48;
            if (UIChat.faceSwing[wordWidth][MainCanvas.countTick % UIChat.faceSwing[wordWidth].length] == 0) {
               MainCanvas.faceImg.draw(g, offsetX, y, wordWidth);
            } else {
               MainCanvas.faceImg1.draw(g, offsetX, y, wordWidth);
            }

            ++i;
            offsetX += MainCanvas.faceImg.frame_w;
         } else {
            if (!isDrawFace) {
               g.setColor(color[i]);
               g.drawChar(chatCharArray[i], offsetX, y, 20);
            }

            wordWidth = g.getFont().charWidth(chatCharArray[i]);
            offsetX += wordWidth;
         }
      }

   }

   public static final void drawChatString(Graphics g, String str, int color, int x, int y, int[][] special, int[] select, int[] selectName) {
      int offsetX = x;
      char[] chatCharArray = str.toCharArray();
      int lg = chatCharArray.length;

      for(int i = 0; i < lg; ++i) {
         int wordWidth;
         if (i != lg - 1 && chatCharArray[i] == '#' && chatCharArray[i + 1] >= '0' && chatCharArray[i + 1] <= '9') {
            wordWidth = chatCharArray[i + 1] - 48;
            if (UIChat.faceSwing[wordWidth][MainCanvas.countTick % UIChat.faceSwing[wordWidth].length] == 0) {
               MainCanvas.faceImg.draw(g, offsetX, y, wordWidth);
            } else {
               MainCanvas.faceImg1.draw(g, offsetX, y, wordWidth);
            }

            ++i;
            offsetX += MainCanvas.faceImg.frame_w;
         } else {
            g.setColor(color);
            if (special != null) {
               wordWidth = special.length;

               for(int j = 0; j < wordWidth; ++j) {
                  if (i >= special[j][0] && i <= special[j][1]) {
                     g.setColor(special[j][2]);
                  }
               }
            }

            wordWidth = g.getFont().charWidth(chatCharArray[i]);
            g.drawChar(chatCharArray[i], offsetX, y, 20);
            if (selectName != null && i >= selectName[0] && i < selectName[1]) {
               g.setColor(16776960);
               g.drawLine(offsetX, y + MainCanvas.CHARH, offsetX + wordWidth, y + MainCanvas.CHARH);
            }

            if (select != null && i >= select[0] && i <= select[1]) {
               g.drawLine(offsetX, y + MainCanvas.CHARH, offsetX + wordWidth, y + MainCanvas.CHARH);
            }

            offsetX += wordWidth;
         }
      }

   }

   private static final String checkFace(String str, int senderId) {
      int tmpIndex = -1;

      for(int i = GOManager.curGameObj.length - 1; i >= 0; --i) {
         if (GOManager.curGameObj[i].getID() == senderId) {
            tmpIndex = i;
            break;
         }
      }

      OtherPlayer tmpPlayer = null;
      if (tmpIndex != -1) {
         tmpPlayer = (OtherPlayer)GOManager.curGameObj[tmpIndex];
         char[] chatCharArray = str.toCharArray();
         int lg = chatCharArray.length;

         for(int i = 0; i < lg; ++i) {
            if (i != lg - 1 && chatCharArray[i] == '#' && chatCharArray[i + 1] >= '0' && chatCharArray[i + 1] <= '9' && tmpPlayer != null) {
               tmpPlayer.faceId = (byte)(chatCharArray[i + 1] - 48);
               tmpPlayer.faceTime = 48;
            }
         }

         return str;
      } else {
         return str;
      }
   }

   public final void draw(Graphics g) {
      if (showMode == 0) {
         g.drawImage(buff, 0, MainCanvas.screenH - 36 - buff.getHeight(), 20);
      } else if (showMode == 1 && chatDy != 0) {
         g.drawImage(buff, 0, MainCanvas.screenH - 36 - chatDy, 20);
      }

      for(int i = 0; i < 3; ++i) {
         drawChatString(gBuff, chatShow[i], chatColor[i], 2, MainCanvas.CHARH * i, false, true);
      }

   }

   public static final void addChatHint(String str, Vector[] goodsContent) {
      if (hinfoBuff.length == 0) {
         strStartY = MainCanvas.screenH;
      }

      if (hinfoBuff.length != 0) {
         for(int i = hinfoBuff.length - 1; i >= 0; --i) {
            if (str.equals(hinfoBuff[i])) {
               return;
            }
         }
      }

      hinfoBuff = Util.addArray(hinfoBuff, hinfoBuff.length, str);
      int[] tmpcolor = findGoodsColor((byte)8, str, goodsContent);
      colorBuff = Util.addArray(colorBuff, colorBuff.length, tmpcolor);
      hintInfo = Util.wrapText(hinfoBuff[0], MainCanvas.screenW - 4, MainCanvas.curFont);
      tmpc = colorBuff[0];
      color = colorBranch(hintInfo, tmpc);
   }

   public static int[][] colorBranch(String[] hintInfo, int[] tmpcolor) {
      int length = hintInfo.length;
      int a = 0;

      for(int i = 0; i < length; ++i) {
         int[] tc = new int[hintInfo[i].length()];
         System.arraycopy(tmpcolor, a, tc, 0, tc.length);
         color = Util.addArray(color, color.length, tc);
         a += tc.length;
      }

      return color;
   }

   public void drawHintInfo(Graphics g) {
      if (hinfoBuff.length != 0 && colorBuff.length != 0) {
         g.setFont(MainCanvas.curFont);
         g.setClip(0, MainCanvas.screenH - 72 - CHAT_BUFF_HEIGHT, MainCanvas.screenW, 36);
         int len = hintInfo.length;
         int i;
         if (showMode == 0) {
            for(i = 0; i < len; ++i) {
               drawChatString(g, hintInfo[i], color[i], 2, strStartY - CHAT_BUFF_HEIGHT - 36 + MainCanvas.CHARH * i, true, false);
            }
         } else {
            for(i = 0; i < len; ++i) {
               drawChatString(g, hintInfo[i], color[i], 2, strStartY - CHAT_BUFF_HEIGHT - 36 + MainCanvas.CHARH * i, true, false);
            }
         }

         g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
      }
   }
}

import javax.microedition.io.Connector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

public class PCIncrement {
   public static String[] m_sFullExplain;
   public static byte[] m_bHotGoodsShopping;
   public static int[] m_bBuyShopping;
   public static byte[] m_bBuyRestrictShopping;
   public static String[] m_sBuyShopping;
   public static int[] m_nGoodsNumberCommodity;
   public static byte[] m_bGoodsHowCommodity;
   public static final byte BUY_BY_MAMMOTH = 0;
   public static final byte BUY_BY_YUANBAO = 1;
   public static final byte BUY_BY_GPLUS = 2;
   public static final byte BUY_BY_PHONE = 3;
   public static final byte CHARGE_BY_YUANBAO = 4;
   public static final byte CHARGE_BY_MAMMOTH = 5;
   public static final byte SEARCH_FOR_CONSUME = 6;
   public static final byte SEARCH_BY_ABATE = 7;
   public static final byte SEARCH_BY_ABATE_HF = 8;
   public static final byte SEARCH_BY_ABATE_YB = 9;
   public static final byte SEARCH_BY_ABATE_MMB = 10;
   public static byte m_bBuyMoney = 0;
   public static byte m_bFull = 0;
   public static byte[] m_bSelectedIndex = new byte[2];
   public static int[] m_nCost;
   public static String m_sTitle;
   public static int m_nSmallNum;
   public static byte[] m_bBuyRestrict;
   public static byte m_bHotGoods;
   public static boolean m_bSendChoose;
   public static byte m_bShortcutChoose = 0;
   public static int m_nIndex = 0;
   private static int m_nNewGoodsNumber;
   public static byte m_bBuyRestrictHot;
   public static byte m_nBuyNum = 0;
   public static int m_nMoneyChange = 0;
   public static byte m_nRebirthID = 0;
   public static String phoneNumber = "1066335561";
   public static byte m_bNote = 0;
   public static final byte m_bNoteFull = 1;
   public static final byte m_bNoteSeizing = 2;
   public static final byte m_bNoteUnchainSeizing = 3;
   public static final byte m_bNoteGetBack = 4;
   public static boolean isSendOK = false;
   public static String m_sFullAddress;
   public static int sum;
   static String address;
   static MessageConnection conn;
   static TextMessage msg;
   public static int count;
   public static int mammothNum;
   public static int processCount;
   public static final byte CHARGE_WAIT = 1;
   public static final byte CHARGE_RESULT = 2;
   public static byte chargeState;
   public static String m_sFullValidate;
   public static boolean fromExpandBagToBuy = false;
   public static boolean formHornChannelToBuy = false;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 2424833:
      case 2424834:
      case 2424836:
      case 2424840:
      case 2424841:
      case 2424842:
      case 2424843:
      case 2425008:
      case 2425026:
         MainCanvas.isWaiting = true;
         break;
      case 2424835:
      case 2425029:
         MainCanvas.isWaiting = true;
         if (!MainCanvas.isChinaMobileVer) {
            ba.writeByte(m_bBuyMoney);
         } else if (MainCanvas.isChinaMobileVer) {
            ba.writeByte((byte)2);
         }
         break;
      case 2424839:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bShortcutChoose);
         if (MainCanvas.isChinaMobileVer) {
            ba.writeUTF(MainCanvas.userID);
         }
         break;
      case 2424993:
         MainCanvas.isWaiting = true;
         if (!MainCanvas.isChinaMobileVer) {
            ba.writeByte(m_bBuyMoney);
         } else if (MainCanvas.isChinaMobileVer) {
            ba.writeByte((byte)2);
         }

         ba.writeUTF(MainCanvas.jarFrom);
         if (!m_bSendChoose) {
            ba.writeInt(m_nNewGoodsNumber);
            if (m_bBuyRestrictHot == 0) {
               ba.writeByte((byte)UITopForm.buyNum);
            }
         } else {
            ba.writeInt(m_nGoodsNumberCommodity[m_nIndex]);
            if (m_bBuyRestrictShopping[m_nIndex] == 1) {
               ba.writeByte(m_bGoodsHowCommodity[m_nIndex]);
            } else if (m_bBuyRestrictShopping[m_nIndex] == 0) {
               ba.writeByte((byte)UITopForm.buyNum);
            }
         }

         ba.writeByte(m_nRebirthID);
         ba.writeUTF(MainCanvas.userID);
         break;
      case 2424994:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bSelectedIndex[0]);
         if (MainCanvas.isChinaMobileVer) {
            ba.writeUTF(MainCanvas.userID);
         }
         break;
      case 2424995:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bSelectedIndex[0]);
         ba.writeByte(m_nIndex);
         if (MainCanvas.isChinaMobileVer) {
            ba.writeUTF(MainCanvas.userID);
         }
         break;
      case 2424996:
         MainCanvas.isWaiting = true;
         ba.writeInt(m_nMoneyChange);
         break;
      case 2424997:
         MainCanvas.isWaiting = true;
         ba.writeUTF(MainCanvas.userID);
         break;
      case 2424998:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bFull);
         String[] name_password = new String[2];
         name_password = Util.loadStrRecord("name_password", 2);
         ba.writeUTF(name_password[1]);
         break;
      case 2425009:
         MainCanvas.TopForm((byte)0, "Hãy chờ ...", "", "", -120, -120);
         UITextField text = (UITextField)MainCanvas.curForm.getComponents().elementAt(5);
         ba.writeUTF(MainCanvas.userID);
         ba.writeUTF(MainCanvas.userKey);
         ba.writeInt(Integer.parseInt(text.getSb().toString().trim()));
         break;
      case 2425010:
         ba.writeUTF(MainCanvas.userID);
         break;
      case 2425011:
         ba.writeUTF(MainCanvas.userID);
         break;
      case 2425012:
         ba.writeUTF(MainCanvas.userID);
         ba.writeByte((byte)7);
         ba.writeByte(PassPort.timeType);
         ba.writeInt(PassPort.selMonth);
         break;
      case 2425013:
         ba.writeUTF(MainCanvas.userID);
         ba.writeByte(PassPort.chooseType);
         ba.writeByte(PassPort.timeType);
         ba.writeInt(PassPort.selMonth);
         break;
      case 2425024:
         MainCanvas.isWaiting = true;
         ba.writeUTF(MainCanvas.userID);
         break;
      case 2425072:
         MainCanvas.isWaiting = true;
         if (UIPicture.equipTypeForServer == 22) {
            fromExpandBagToBuy = true;
         }

         if (UIPicture.equipTypeForServer == 23) {
            formHornChannelToBuy = true;
         }

         ba.writeByte(UIPicture.equipTypeForServer);
         ba.writeUTF(MainCanvas.userID);
         break;
      case 2425076:
         MainCanvas.isWaiting = true;
         ba.writeByte(PCHang.m_bIncrementGenre[PCHang.m_bIncrementGenreIndex]);
         ba.writeByte(PCHang.m_bIncrementIndex);
         break;
      case 2425077:
         MainCanvas.isWaiting = true;
         ba.writeUTF(MainCanvas.userID);
         break;
      case 2425078:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bBuyMoney);
         break;
      case 2425079:
      case 2425080:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bBuyMoney);
         ba.writeByte(m_nIndex);
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      String resultStr;
      UITextArea chargeArea;
      switch(commID) {
      case -2145058656:
         m_nNewGoodsNumber = data.readInt();
         m_bBuyRestrictHot = data.readByte();
         m_bHotGoods = data.readByte();
         break;
      case -2145058650:
         m_sFullAddress = data.readUTF();
         if (m_sFullAddress != null || !m_sFullAddress.equals("")) {
            MainCanvas.isWaiting = false;
            if (m_bFull == 0) {
               ExclusiveUI("Nạp thẻ mệnh giá lớn", "nhấp vào “Xác nhận” chuyển đến trang nạp thẻ mệnh giá lớn, nhấp “Quay về” để quay về trò chơi");
            } else if (m_bFull == 1) {
               ExclusiveUI("Nạp KNB", " nhấp “Xác nhận” chuyển đến trang nạp thẻ KNB, nhấp “Quay về” để quay về trò chơi");
               if (MainCanvas.backForms.size() > 0) {
                  MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
               }
            }
         }
         break;
      case -2145058649:
         m_sFullValidate = data.readUTF();
         break;
      case -2145058639:
         if (MainCanvas.curTopForm != null) {
            MainCanvas.curTopForm = null;
         }

         UIMenu.formSaveForm();
         int money = data.readInt();
         int balance = data.readInt();
         MainCanvas.curForm = ParseUI.parseUI("/cm/ui/charger.ui");
         MainCanvas.curForm.clientCommand = -1610612688;
         resultStr = "";
         if (money != 0) {
            resultStr = "Chúc mừng, nạp thẻ" + money + "thành công, nhận" + money * 100 + "điểm! Số điểm hiện tại là " + balance + "điểm!";
         } else {
            resultStr = "Nạp thẻ thất bại, hãy thử lại hoặc liên hệ server!";
         }

         chargeArea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(3);
         chargeArea.setContent(resultStr);
         break;
      case -2145058638:
         if (MainCanvas.curTopForm != null) {
            MainCanvas.curTopForm = null;
         }

         int demand = data.readInt();
         resultStr = "";
         if (demand >= 0) {
            resultStr = "Số điểm hiện tại là " + demand + "điểm!";
         } else {
            resultStr = "Tra tìm thất bại, hãy thử lại hoặc liên hệ server!";
         }

         UITextArea remainArea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(3);
         remainArea.setContent(resultStr);
         break;
      case -2145058637:
         if (MainCanvas.curTopForm != null) {
            MainCanvas.curTopForm = null;
         }

         int particularity = data.readInt();
         resultStr = "";
         if (particularity >= 0) {
            resultStr = "Số điểm hiện tại là " + particularity + "điểm!";
         } else {
            resultStr = "Tra tìm thất bại, hãy thử lại hoặc liên hệ server!";
         }

         UITextArea detailArea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(3);
         detailArea.setContent(resultStr);
         break;
      case -2145058636:
      case -2145058635:
         if (MainCanvas.curTopForm != null) {
            MainCanvas.curTopForm = null;
         }

         String history = data.readUTF();
         UIMenu.formSaveForm();
         MainCanvas.curForm = ParseUI.parseUI("/cm/ui/result.ui");
         if (commID == -2145058636) {
            MainCanvas.curForm.clientCommand = -1610612640;
         } else if (commID == -2145058635) {
            MainCanvas.curForm.clientCommand = -1610612639;
         }

         chargeArea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(3);
         chargeArea.setContent(history);
         chargeArea.addUIScroll();
         break;
      case -2145058634:
         PassPort.CM_PASSPORT_IP = data.readUTF();
         PassPort.CM_CHARGE_IP = data.readUTF();
         PassPort.CM_PASSPORT_GET_URL = data.readUTF();
         PassPort.cm_type = data.readByte();
         if (PassPort.cm_type == 2) {
            PassPort.gplusDownloadIp = data.readUTF();
            PassPort.jadDownloadUrl = data.readUTF();
            PassPort.jadNotifyUrl = data.readUTF();
         } else if (PassPort.cm_type == 3) {
            PassPort.gplusDownloadIp = data.readUTF();
            PassPort.jadDownloadUrl = data.readUTF();
            PassPort.jadNotifyUrl = data.readUTF();
            PassPort.jarDownloadUrl = data.readUTF();
         }

         PassPort.onlyGetUserID();
         PassPort.accessJadDownloadUrl();
         break;
      case -2145058623:
         byte num = data.readByte();
         UIForm.shoppingList = new String[num];
         UIForm.shoppingListId = new byte[num];

         for(int i = 0; i < num; ++i) {
            UIForm.shoppingListId[i] = data.readByte();
            UIForm.shoppingList[i] = data.readUTF();
         }

         return;
      case -2145058620:
         MainCanvas.userID = "";
         PassPort.onlyGetUserID();
         break;
      case -2145058575:
         m_bBuyMoney = data.readByte();
         m_sTitle = data.readUTF();
         m_bSelectedIndex[0] = data.readByte();
      }

   }

   public static void initSend(byte type) {
      try {
         if (type == 1) {
            if (PassPort.sms_coin != null) {
               phoneNumber = PassPort.getSMSNumber(PassPort.sms_coin);
            }
         } else if (PassPort.sms_bond != null) {
            phoneNumber = PassPort.getSMSNumber(PassPort.sms_bond);
         }

         address = "sms://" + phoneNumber;
         conn = (MessageConnection)Connector.open(address);
         msg = (TextMessage)conn.newMessage("text");
         msg.setPayloadText(createContent(type));
         if (type == 2 || type == 3 || type == 4) {
            conn.send(msg);
            isSendOK = true;
         }
      } catch (Exception var2) {
         var2.printStackTrace();
         if (type == 2 || type == 3 || type == 4) {
            isSendOK = false;
         }
      }

   }

   private static String createContent(byte type) {
      String content = null;
      StringBuffer sb = new StringBuffer(4);
      if (type == 1) {
         String coin = getSMSCoin();
         if (coin == null) {
            sb.append("AP").append(MainCanvas.getUserName()).append(".fengshen").append(".").append(m_sFullValidate);
         } else {
            sb = new StringBuffer(coin);
         }
      } else {
         UITextField text_1;
         String seek;
         String text_1Str;
         String del;
         UITextField text;
         if (type == 2) {
            text = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
            text_1 = (UITextField)MainCanvas.curForm.getComponents().elementAt(6);
            seek = text.getSb().toString().trim();
            text_1Str = text_1.getSb().toString().trim();
            del = getSMSBond(seek, text_1Str);
            if (del == null) {
               sb.append("AGB").append(":").append(seek).append(":").append(text_1Str);
            } else {
               sb = new StringBuffer(del);
            }
         } else if (type == 3) {
            text = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
            text_1 = (UITextField)MainCanvas.curForm.getComponents().elementAt(6);
            seek = text.getSb().toString().trim();
            text_1Str = text_1.getSb().toString().trim();
            del = getSMSDel(seek, text_1Str);
            if (del == null) {
               sb.append("AGD").append(":").append(seek).append(":").append(text_1Str);
            } else {
               sb = new StringBuffer(del);
            }
         } else if (type == 4) {
            text = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
            String textStr = text.getSb().toString().trim();
            seek = getSMSSeek(textStr);
            if (seek == null) {
               sb.append("AGC").append(":").append(textStr);
            } else {
               sb = new StringBuffer(seek);
            }
         }
      }

      content = sb.toString().trim();
      return content;
   }

   private static String getSMSCoin() {
      if (PassPort.sms_coin != null) {
         String coin = Util.replaceFirstStr(PassPort.sms_coin, "[name]", MainCanvas.getUserName());
         coin = Util.replaceFirstStr(coin, "[game]", "fengshen");
         coin = Util.replaceFirstStr(coin, "[check]", m_sFullValidate);
         if (coin.indexOf(64) > 0) {
            coin = coin.substring(0, coin.indexOf(64));
         }

         return coin;
      } else {
         return null;
      }
   }

   private static String getSMSBond(String name, String psw) {
      if (PassPort.sms_bond != null) {
         String bond = Util.replaceFirstStr(PassPort.sms_bond, "[name]", name);
         bond = Util.replaceFirstStr(bond, "[password]", psw);
         if (bond.indexOf(64) > 0) {
            bond = bond.substring(0, bond.indexOf(64));
         }

         return bond;
      } else {
         return null;
      }
   }

   private static String getSMSSeek(String name) {
      if (PassPort.sms_seek != null) {
         String seek = Util.replaceFirstStr(PassPort.sms_seek, "[name]", name);
         if (seek.indexOf(64) > 0) {
            seek = seek.substring(0, seek.indexOf(64));
         }

         return seek;
      } else {
         return null;
      }
   }

   private static String getSMSDel(String name, String psw) {
      if (PassPort.sms_del != null) {
         String del = Util.replaceFirstStr(PassPort.sms_del, "[name]", name);
         del = Util.replaceFirstStr(del, "[password]", psw);
         if (del.indexOf(64) > 0) {
            del = del.substring(0, del.indexOf(64));
         }

         return del;
      } else {
         return null;
      }
   }

   public static void sends(int number) {
      for(int i = 0; i < number; ++i) {
         send();
      }

   }

   private static void send() {
      try {
         ++count;
         conn.send(msg);
      } catch (Exception var4) {
         --count;
         var4.printStackTrace();
      } finally {
         ++processCount;
      }

   }

   public static void init() {
      count = 0;
      processCount = 0;
   }

   public static boolean isSendMessageEnd() {
      return processCount >= sum;
   }

   private static void ExclusiveUI(String caption_1, String caption_2) {
      final Form form = new Form(caption_1);
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.append(caption_2);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            if (c == okCmd) {
               try {
                  MainCanvas.aMidlet.platformRequest(PCIncrement.m_sFullAddress);
               } catch (Exception var4) {
               }
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

   public static void SendNote(byte type) {
      m_bNote = type;
      Thread initsend = new Thread() {
         public void run() {
            PCIncrement.initSend(PCIncrement.m_bNote);
            MainCanvas.TopForm((byte)0, PCIncrement.isSendOK ? "Đã gửi lệnh, hãy nhanh chóng tra kết quả bằng tin nhắn" : "Gửi thất bại", Cons.C_STR[2], "", -1, -120);
         }
      };
      initsend.start();
   }

   public static void noteCharge() {
      m_bNote = 1;
      initSend(m_bNote);
      init();
      Thread send = new Thread() {
         public void run() {
            PCIncrement.sends(PCIncrement.sum);
         }
      };
      send.start();
      chargeState = 2;
      UITopForm.createLocalTopForm((byte)0, (String)"Đang nạp thẻ, hãy chờ...", "", "", -2, -2);
   }
}

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public class PCMail {
   public static String[] m_sSendMailValidity = new String[3];
   public static long m_lFriendID = -1L;
   public static int m_nRemit = 0;
   public static boolean m_bComeDownWith;
   public static int m_nMoney = 0;
   public static boolean m_bWhetherRestore;
   public static byte m_bAccessoriesAmount;
   public static byte m_bAccessoriesID = -1;
   public static byte m_bAccessoriesArticleAmount;
   private static final byte m_bBasalPostage = 10;
   private static final byte m_bIncrementPostage = 10;
   public static byte m_nPostage = 10;
   public static boolean money;
   public static byte m_bSymbol = 0;
   public static int m_nBackpackMoney = 0;
   public static long selectedMailID = -1L;
   public static byte beforeMailIndex = 0;
   public static byte selectedAttIndex = -1;
   public static boolean isNewMail = false;
   public static boolean isPay = false;
   public static long m_lCharacter = 0L;
   public static String m_sCharacterName = "";
   public static String m_sMailMotif = "";
   public static byte m_bForbidEnchase = 0;
   public static short[][] Accessory = new short[][]{{-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}};
   private static byte aa = 0;
   public static boolean canReturnMail = false;
   public static int receiveMoney = 0;
   public static boolean haveAttach = false;
   public static String[] m_sBingIntro;
   public static byte m_bIndex;
   public static int[] m_nIndex;
   public static String[] m_sContent;
   public static byte m_bContentment;
   public static byte[] m_bReboundKind;
   private static int m_nMatterIndex;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 1703937:
         MainCanvas.isWaiting = true;
         ba.writeInt(GOManager.currentTarget.getID());
         break;
      case 1703938:
         MainCanvas.isWaiting = true;
         ba.writeInt(GOManager.currentTarget.getID());
         break;
      case 1703939:
         MainCanvas.isWaiting = true;
         ba.writeInt(GOManager.currentTarget.getID());
      case 1703940:
      case 1703941:
      case 1703953:
      case 1703955:
      case 1703957:
      case 1703962:
      case 1703963:
      case 1703964:
      case 1703965:
      case 1703966:
      case 1703967:
      default:
         break;
      case 1703942:
         MainCanvas.isWaiting = true;
         ba.writeLong(selectedMailID);
         break;
      case 1703943:
         MainCanvas.isWaiting = true;
         ba.writeLong(selectedMailID);
         break;
      case 1703944:
         MainCanvas.isWaiting = true;
         break;
      case 1703945:
      case 1703958:
      case 1703959:
      case 1703960:
      case 1703961:
      case 1703968:
      case 1703969:
      case 1703971:
      case 1703972:
         MainCanvas.isWaiting = true;
         break;
      case 1703946:
         MainCanvas.isWaiting = true;
         break;
      case 1703947:
         MainCanvas.isWaiting = true;
         if (!m_bWhetherRestore) {
            ba.writeLong(m_lFriendID);
         } else {
            ba.writeLong(m_lCharacter);
         }

         ba.writeUTF(m_sSendMailValidity[0]);
         ba.writeUTF(m_sSendMailValidity[1]);
         ba.writeUTF(m_sSendMailValidity[2]);
         ba.writeInt(m_nRemit);
         ba.writeBoolean(m_bComeDownWith);
         ba.writeInt(m_nMoney);
         ba.writeBoolean(m_bWhetherRestore);
         ba.writeByte(m_bAccessoriesAmount);
         if (m_bAccessoriesAmount > 0) {
            for(int i = 0; i < Accessory.length; ++i) {
               if (Accessory[i][0] != -1) {
                  m_bAccessoriesID = (byte)Accessory[i][0];
                  m_bAccessoriesArticleAmount = (byte)Accessory[i][3];
                  ba.writeByte(m_bAccessoriesID);
                  ba.writeByte(m_bAccessoriesArticleAmount);
               }
            }
         }
         break;
      case 1703948:
         MainCanvas.isWaiting = true;
         ba.writeByte(selectedAttIndex);
         PCTreasure.unlockMenu = false;
         break;
      case 1703949:
         MainCanvas.isWaiting = true;
         ba.writeByte(selectedAttIndex);
         break;
      case 1703950:
         MainCanvas.isWaiting = true;
         break;
      case 1703951:
         MainCanvas.isWaiting = true;
         break;
      case 1703952:
         MainCanvas.isWaiting = true;
         break;
      case 1703954:
         MainCanvas.isWaiting = true;
         ba.writeByte((byte)UIGrid.accessories[MainCanvas.m_bMailAccessoriesN][0]);
         PCTreasure.unlockMenu = false;
         break;
      case 1703956:
         MainCanvas.isWaiting = true;
         StringBuffer sb = new StringBuffer(4);
         sb.append("Phiên bản chuẩn 7610:");
         int tmpV = GameLogin.m_nVersion;
         sb.append(String.valueOf(tmpV >>> 24)).append(".");
         tmpV = GameLogin.m_nVersion;
         sb.append(String.valueOf((tmpV & 16711680) >>> 16)).append(".");
         tmpV = GameLogin.m_nVersion;
         sb.append(String.valueOf((tmpV & '\uff00') >>> 8)).append(".");
         tmpV = GameLogin.m_nVersion;
         sb.append(String.valueOf(tmpV & 255));
         UITextArea textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(3);
         UITextField text = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
         ba.writeByte(m_bIndex);
         ba.writeUTF(textarea.getContent());
         ba.writeUTF(sb.toString());
         ba.writeUTF(text.getSb().toString().trim());
         if (m_bContentment != 5) {
            ba.writeInt(0);
         } else {
            ba.writeInt(m_nMatterIndex);
            m_bContentment = 0;
            UIForm.backUIFormAction();
         }
         break;
      case 1703970:
         MainCanvas.isWaiting = true;
         m_nMatterIndex = m_nIndex[m_bIndex];
         ba.writeInt(m_nMatterIndex);
         ba.writeByte(m_bContentment);
         if (m_bContentment == 5) {
            m_bIndex = m_bReboundKind[m_bIndex];
            MainCanvas.ni.send(1703971);
         } else {
            UIForm.backUIFormAction();
         }
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2145779711:
      case -2145779710:
      case -2145779707:
         MainCanvas.cmdID = commID;
         MainCanvas.cmdData = data.readByteArray(data.getLength() - 6);
         break;
      case -2145779709:
         byte isShow = data.readByte();
         if (isShow == 0) {
            isNewMail = false;
         } else if (isShow == 1) {
            isNewMail = true;
         }
         break;
      case -2145779708:
         isPay = data.readBoolean();
         UITextField tf = (UITextField)MainCanvas.curForm.getComponents().elementAt(13);
         if (tf != null) {
            tf.setSb(new StringBuffer("0"));
         }
         break;
      case -2145779693:
         m_nBackpackMoney = data.readInt();
      }

   }

   public static void CompilationMail() {
      final Form form = new Form("Soạn thư");
      final TextField tf = new TextField("Nhập vào tên người nhận", "", 6, 0);
      final TextField tf1 = new TextField("Nhập vào chủ đề thư", "", 6, 0);
      final TextField tf2 = new TextField("Nhập vào nội dung thư", "", 50, 0);
      form.append(tf);
      form.append(tf1);
      form.append(tf2);
      if (m_sSendMailValidity[0] != null) {
         tf.setString(m_sSendMailValidity[0]);
      }

      if (m_sSendMailValidity[1] != null) {
         tf1.setString(m_sSendMailValidity[1]);
      }

      if (m_sSendMailValidity[2] != null) {
         tf2.setString(m_sSendMailValidity[2]);
      }

      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            UITextField textfield1 = (UITextField)MainCanvas.curForm.getComponents().elementAt(3);
            UITextField textfield2 = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
            UITextArea textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(5);
            if (c == okCmd) {
               if (PCMail.m_bSymbol == 1 && !PCMail.m_sSendMailValidity[0].equals(tf.getString())) {
                  PCMail.m_lFriendID = -1L;
               }

               PCMail.m_sSendMailValidity[0] = tf.getString();
               PCMail.m_sSendMailValidity[1] = tf1.getString();
               if (tf2.getString().equals("")) {
                  PCMail.m_sSendMailValidity[2] = "";
               } else {
                  PCMail.m_sSendMailValidity[2] = tf2.getString();
               }

               Alert alert2;
               if (!MainCanvas.CharacterValidate(tf.getString(), (byte)2)) {
                  alert2 = new Alert(Cons.C_STR[9], "Tên người chơi có ký tự phi pháp", (Image)null, AlertType.ERROR);
                  tf.setString("");
                  MainCanvas.aMidlet.display.setCurrent(alert2, form);
                  if (!PCMail.m_sSendMailValidity[0].equals("")) {
                     PCMail.m_sSendMailValidity[0] = "";
                  }

                  PCMail.aa = 1;
                  return;
               }

               if (!MainCanvas.CharacterValidate(tf1.getString(), (byte)2)) {
                  alert2 = new Alert(Cons.C_STR[9], "Chủ đề thư có ký tự phi pháp, hãy nhập lại", (Image)null, AlertType.ERROR);
                  tf1.setString("");
                  MainCanvas.aMidlet.display.setCurrent(alert2, form);
                  if (!PCMail.m_sSendMailValidity[1].equals("")) {
                     PCMail.m_sSendMailValidity[1] = "";
                  }

                  return;
               }

               if (!MainCanvas.CharacterValidate(tf2.getString(), (byte)3)) {
                  alert2 = new Alert(Cons.C_STR[9], "Nội dung thư có ký tự phi pháp, hãy nhập lại", (Image)null, AlertType.ERROR);
                  tf2.setString("");
                  MainCanvas.aMidlet.display.setCurrent(alert2, form);
                  if (!PCMail.m_sSendMailValidity[2].equals("")) {
                     PCMail.m_sSendMailValidity[2] = "";
                  }

                  return;
               }

               textfield1.setSb(new StringBuffer(PCMail.m_sSendMailValidity[0]));
               textfield2.setSb(new StringBuffer(PCMail.m_sSendMailValidity[1]));
               textarea.setContent(PCMail.m_sSendMailValidity[2]);
               textarea.addUIScroll();
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            } else if (c == exitCmd) {
               form.removeCommand(okCmd);
               form.removeCommand(exitCmd);
               if (PCMail.aa == 1) {
                  textfield1.setSb(new StringBuffer(""));
                  PCMail.aa = 0;
               }

               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }

   public static void SendMailValidity() {
      UITextField textfield1 = (UITextField)MainCanvas.curForm.getComponents().elementAt(3);
      UITextField textfield2 = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
      UITextArea texrarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(5);
      UITextField textfield3 = (UITextField)MainCanvas.curForm.getComponents().elementAt(13);
      if ((!textfield1.getSb().toString().trim().equals("") || !textfield2.getSb().toString().trim().equals("")) && !textfield1.getSb().toString().trim().equals("")) {
         if (textfield2.getSb().toString().trim().equals("")) {
            UITopForm.createLocalTopForm((byte)0, (String)"Điền chủ đề thư", "Xác định", "", -1, -2);
         } else {
            if (texrarea.getContent().equals("")) {
               m_sSendMailValidity[2] = "";
            }

            if (MainCanvas.curForm.clientCommand != 1703937 && MainCanvas.curForm.clientCommand != 1703952) {
               if (MainCanvas.curForm.clientCommand == 1703938) {
                  if (m_bAccessoriesAmount <= 0) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Thư trả phí không có vật phẩm", "Xác định", "", -1, -2);
                     return;
                  }

                  if (Integer.parseInt(textfield3.getSb().toString().trim()) == 0 && m_bAccessoriesAmount <= 0 || Integer.parseInt(textfield3.getSb().toString().trim()) == 0) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Xác nhận số tiền được nhận", "Xác định", "", -1, -2);
                     return;
                  }

                  m_bComeDownWith = true;

                  try {
                     m_nMoney = Integer.parseInt(textfield3.getSb().toString().trim());
                  } catch (NumberFormatException var7) {
                     var7.printStackTrace();
                  }
               }
            } else {
               m_bComeDownWith = false;
               m_nRemit = Integer.parseInt(textfield3.getSb().toString().trim());
               if (m_nBackpackMoney - m_nRemit < m_nPostage) {
                  UITopForm.createLocalTopForm((byte)0, (String)"Không đủ vàng để gửi thư!", "Xác định", "", -1, -2);
                  return;
               }

               try {
                  m_nRemit = Integer.parseInt(textfield3.getSb().toString().trim());
                  if (m_nRemit >= m_nBackpackMoney - m_nPostage) {
                     String str = "";

                     try {
                        str = Integer.toString(m_nRemit);
                     } catch (NumberFormatException var6) {
                     }

                     textfield3.setLabel(str);
                     if (m_nRemit > 0 && m_nRemit >= m_nBackpackMoney - m_nPostage) {
                        UITopForm.createLocalTopForm((byte)0, (String)"Muốn dùng hết vàng?", "Xác định", "Hủy", 1703947, -1);
                     } else {
                        MainCanvas.ni.send(1703947);
                     }

                     return;
                  }
               } catch (NumberFormatException var8) {
                  m_nRemit = 0;
               }
            }

            MainCanvas.ni.send(1703947);
         }
      } else {
         UITopForm.createLocalTopForm((byte)0, (String)"Điền tên người nhận", "Xác định", "", -1, -2);
      }
   }

   public static void EliminateMail() {
      UITextField textfield1 = (UITextField)MainCanvas.curForm.getComponents().elementAt(3);
      UITextField textfield2 = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
      UITextArea textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(5);
      UITextField textfield3 = (UITextField)MainCanvas.curForm.getComponents().elementAt(13);
      UILabel label = (UILabel)MainCanvas.curForm.getComponents().elementAt(15);
      textfield1.setLabel("");
      textfield2.setLabel("");
      textarea.setContent("");
      textfield3.setLabel("0");
      label.setText("10");

      for(int i = 0; i < m_sSendMailValidity.length; ++i) {
         m_sSendMailValidity[i] = null;
      }

      MainCanvas.m_bMailAccessoriesN = 0;
      m_bSymbol = 0;
      m_bWhetherRestore = false;
      m_lFriendID = -1L;
      m_nRemit = 0;
      m_bComeDownWith = false;
      m_nMoney = 0;
      m_bAccessoriesAmount = 0;
      m_bAccessoriesID = 0;
      m_bAccessoriesArticleAmount = 0;
      money = false;
      m_bForbidEnchase = 0;
   }

   public static void Postage() {
      UILabel label = (UILabel)MainCanvas.curForm.getComponents().elementAt(15);
      if (m_bAccessoriesAmount > 0) {
         m_nPostage = (byte)(money ? 10 * m_bAccessoriesAmount + 20 : 10 * m_bAccessoriesAmount + 10);
      } else {
         m_nPostage = (byte)(money ? 20 : 10);
      }

      try {
         label.setText(Integer.toString(m_nPostage));
      } catch (NumberFormatException var3) {
         var3.printStackTrace();
      }

      UITextField textfield3 = (UITextField)MainCanvas.curForm.getComponents().elementAt(13);
      int CurrentlyMoney = Integer.parseInt(textfield3.getSb().toString().trim());
      if (CurrentlyMoney <= 0) {
         textfield3.setSb(new StringBuffer(Integer.toString(0)));
      } else {
         if (CurrentlyMoney + m_nPostage >= m_nBackpackMoney && (MainCanvas.curForm.clientCommand == 1703937 || MainCanvas.curForm.clientCommand == 1703952)) {
            CurrentlyMoney = m_nBackpackMoney - m_nPostage;
            if (CurrentlyMoney > 0) {
               textfield3.setSb(new StringBuffer(Integer.toString(CurrentlyMoney)));
            } else {
               textfield3.setSb(new StringBuffer(Integer.toString(0)));
            }
         }

      }
   }

   public static void LiquidationBata() {
      String[] m_sSendMailValidity = new String[3];
      m_lFriendID = -1L;
      m_nRemit = 0;
      m_bComeDownWith = false;
      m_nMoney = 0;
      m_bWhetherRestore = false;
      m_bAccessoriesAmount = 0;
      m_bAccessoriesID = -1;
      m_bAccessoriesArticleAmount = 0;
      m_nPostage = 0;
      money = false;
      m_bSymbol = 0;
      m_nBackpackMoney = 0;
      selectedMailID = -1L;
      beforeMailIndex = 0;
      selectedAttIndex = -1;
      isNewMail = false;
      isPay = false;
      m_lCharacter = 0L;
      m_sCharacterName = "";
      m_sMailMotif = "";
      m_bForbidEnchase = 0;
      aa = 0;
   }
}

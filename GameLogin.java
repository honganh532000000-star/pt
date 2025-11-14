public class GameLogin {
   public static byte m_bjumpIP;
   public static int m_nVersion;
   public static final int GENERAL_VER = 16777215;
   public static String downloadAddress;
   public static boolean m_bEncrypt;
   public static boolean errorLoginResult;
   public static byte login_server_result;
   public static String m_sVersion;

   static {
      m_nVersion = MainCanvas.isChinaMobileVer ? 16777984 : 67109377;
      downloadAddress = "";
      m_bEncrypt = true;
      errorLoginResult = false;
      login_server_result = -1;
      m_sVersion = "1.0.1";
   }

   private GameLogin() {
   }

   public static byte emulator() {
      String platform = "";

      try {
         if (Class.forName("javax.microedition.lcdui.a") != null) {
            return 5;
         }
      } catch (Exception var6) {
      }

      try {
         if (Class.forName("emulator.Emulator") != null) {
            return 1;
         }
      } catch (Exception var5) {
      }

      try {
         if (Runtime.getRuntime().totalMemory() == 8000000L) {
            return 2;
         }
      } catch (Exception var4) {
      }

      try {
         if ((platform = System.getProperty("microedition.platform")) != null && platform.equals("SunMicrosystems_wtk")) {
            return 3;
         }
      } catch (Exception var3) {
      }

      try {
         if (Class.forName("java.applet.Applet") != null) {
            return 4;
         }
      } catch (Exception var2) {
      }

      return 0;
   }

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 3:
         ba.writeInt(MainCanvas.timeTaken2);
         break;
      case 4:
         ba.writeShort(MainCanvas.ni.encryptId);
         break;
      case 65537:
         if (!m_bEncrypt) {
            ba.writeByte((int)18);
         }

         ba.writeByte(MainCanvas.jumpID);
         ba.writeUTF(PassPort.loginType == 0 ? MainCanvas.getUserName() : "pt" + MainCanvas.getUserName());
         ba.writeUTF(MainCanvas.name_password[1]);
         break;
      case 65539:
         Player.totalTime = 0;
         Player.cheatTime = 0;

         try {
            UIForm.encryptImg.getHeight();
         } catch (Exception var3) {
            MainCanvas.aMidlet.exitMIDlet();
         }

         ba.writeByte(MainCanvas.m_bChoose);
         ba.writeUTF(MainCanvas.jarFrom);
         ba.writeUTF(PassPort.imei);
         ba.writeUTF(MainCanvas.userID);
         ba.writeByte(MainCanvas.emulatorType);
         ba.writeLong(Runtime.getRuntime().totalMemory());
         break;
      case 65544:
      case 65545:
      case 65550:
         MainCanvas.isWaiting = true;
         break;
      case 65546:
         ba.writeByte(MainCanvas.m_bjumpIP1);
         ba.writeInt(m_nVersion);
         ba.writeByte((byte)1);
         ba.writeUTF(MainCanvas.jarFrom);
      case 65547:
      case 65551:
      default:
         break;
      case 65548:
         ba.writeUTF(MainCanvas.name_password[0]);
         ba.writeUTF(MainCanvas.name_password[1]);
         ba.writeUTF(MainCanvas.kongMD5);
         break;
      case 65557:
         ba.writeUTF(PassPort.localPlace);
         break;
      case 65558:
         ba.writeUTF(PassPort.price);
         break;
      case 65568:
         ba.writeUTF(PassPort.virUser);
         break;
      case 65569:
         ba.writeUTF(MainCanvas.userID);
         break;
      case 117502:
         if (!m_bEncrypt) {
            ba.writeByte((int)18);
         }

         ba.writeByte(MainCanvas.jumpID);
         ba.writeInt(m_nVersion);
         ba.writeByte((byte)1);
         ba.writeUTF(PassPort.loginType == 0 ? MainCanvas.getUserName() : "pt" + MainCanvas.getUserName());
         ba.writeUTF(MainCanvas.name_password[1]);
         break;
      case 131071:
         if (!m_bEncrypt) {
            ba.writeByte((int)18);
         }

         ba.writeByte(MainCanvas.m_bjumpIP1);
         ba.writeInt(m_nVersion);
         ba.writeByte((byte)1);
         ba.writeUTF(PassPort.loginType == 0 ? MainCanvas.getUserName() : "pt" + MainCanvas.getUserName());
         ba.writeUTF(MainCanvas.name_password[1]);
         ba.writeUTF("l");
         ba.writeByte(PassPort.kongFirstResult);
         ba.writeUTF(MainCanvas.kongMD5);
         ba.writeUTF(MainCanvas.name_password[0]);
         break;
      case 1114113:
         ba.writeUTF(MainCanvas.name_password[0]);
         ba.writeUTF(MainCanvas.name_password[1]);
         ba.writeUTF(MainCanvas.password_validate);
         break;
      case 1114114:
         ba.writeByte(MainCanvas.m_bChoose);
         ba.writeByte(MainCanvas.m_bPhyle);
         ba.writeByte(MainCanvas.m_bGender);
         ba.writeByte(MainCanvas.m_bVocation);
         ba.writeUTF(MainCanvas.m_sName);
         break;
      case 1114115:
         ba.writeUTF(MainCanvas.m_sTemporaryStr[0]);
         ba.writeUTF(MainCanvas.m_sTemporaryStr[1]);
         ba.writeUTF(MainCanvas.m_sTemporaryStr[2]);
         ba.writeUTF(MainCanvas.m_sTemporaryStr[3]);
         break;
      case 1114116:
      case 1114118:
      case 1114120:
         ba.writeByte(MainCanvas.m_bChoose);
         break;
      case 1114119:
         ba.writeByte(MainCanvas.m_bChoose);
         ba.writeUTF(UIForm.m_sAmendNickname[1]);
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      String servername;
      switch(commID) {
      case -2147483644:
         MainCanvas.ni.encryptId = data.readShort();
         MainCanvas.ni.send(4);

         try {
            MainCanvas.ni.sendInfo();
         } catch (Exception var10) {
         }

         int length = data.readByte();
         MainCanvas.ni.encryptArray = new byte[length];

         for(int i = 0; i < length; ++i) {
            MainCanvas.ni.encryptArray[i] = data.readByte();
         }

         return;
      case -2147418111:
         login_server_result = data.readByte();
         errorLoginResult = true;
         break;
      case -2147418109:
         byte var12 = data.readByte();
         break;
      case -2147418107:
         byte servercount = data.readByte();
         MainCanvas.servercount = servercount;
         MainCanvas.servername = new String[MainCanvas.servercount];
         MainCanvas.serverIp = new String[MainCanvas.servercount];
         MainCanvas.m_bjumpIP = new byte[MainCanvas.servercount];

         for(int i = 0; i < servercount; ++i) {
            servername = data.readUTF();
            String serverip = data.readUTF();
            MainCanvas.m_bjumpIP[i] = data.readByte();
            MainCanvas.servername[i] = servername;
            MainCanvas.serverIp[i] = serverip;
         }

         NetInterface.getInstance().closeConn();
         break;
      case -2147418098:
         String content = "";
         content = data.readUTF();
         if (MainCanvas.rightMenu != null) {
            MainCanvas.rightMenu.savePositionIDStack.removeAllElements();
            MainCanvas.rightMenu = null;
         }

         if (MainCanvas.getGameState() != 0) {
            MainCanvas.setGameState((byte)0);
         }

         UITopForm.createLocalTopForm((byte)0, (String)content, "Đăng ký", "Thoát", -27, -12);
         break;
      case -2147418093:
         Download.testDownloadUrl = data.readUTF();
         MainCanvas.TopForm((byte)0, "Số phiên bản không phải mới nhất, nhưng vẫn có thể dùng được, hãy lựa chọn có cập nhật hay không", "Tải về", "Tiếp tục", -8, -2);
         break;
      case -2147418089:
         UITopForm.mammothChargeTip = data.readUTF();
         PCIncrement.mammothNum = data.readInt();
         break;
      case -2147418078:
         MainCanvas.ni.send(65569);
         break;
      case -2146369531:
         servername = "";
         servername = data.readUTF();
         MainCanvas.name_password[0] = servername;
         MainCanvas.name_password[1] = servername;
         MainCanvas.setState((byte)14);
         UITopForm.removeAllTopForm();
         UITextField userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(10);
         UITextField pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(11);
         UITextField validate = (UITextField)MainCanvas.curForm.getComponents().elementAt(12);
         userNameText.setSb(new StringBuffer(MainCanvas.name_password[0]));
         pswText.setSb(new StringBuffer(MainCanvas.name_password[1]));
         validate.setSb(new StringBuffer(servername));
      case 1114117:
      }

   }
}

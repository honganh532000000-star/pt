import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public class PCFarm {
   public static byte m_bPropManeFinger = 0;
   public static String[] m_sPropMane = new String[3];
   public static String m_sFarmName;
   public static String m_sWeather;
   public static int[] m_nPropPrice;
   public static int m_nBuyGermCode;
   public static final int m_nUpperLimit = 99;
   public static String m_sFarmEnounce = "";
   public static byte m_bGlebeKind;
   public static byte m_bGlebeIndex;
   public static byte m_bMenuIndex;
   public static Vector menuvector;
   public static byte m_nFarm_On_Off;
   public static byte m_bWhetherGetHome;
   public static int m_nPlayerIndex;
   public static byte m_nDepotSellingIndex = 0;
   public static byte m_bBacking = -1;
   public static byte m_nLargessFriend;
   public static byte m_nBackpackIndex;
   public static int m_nFriendID;
   public static byte m_bRenovate;
   public static String HelpCaption;
   public static byte HelpID;
   public static final String[] MENUS = new String[]{"Gieo giống", "Cày", "Thu hoạch", "Trộm cắp", "Tra xem", "Nhổ cỏ", "Tưới nước", "Bắt sâu", "Trồng cỏ dại", "Thả côn trùng", "Sử dụng vật phẩm"};
   public static final int[] NENU_MANIPULATE = new int[]{2, 3, 3, 3, 2, 3, 3, 3, 3, 3, 2};
   public static final String[] FARM_DYNAMIC_MENU = new String[]{"Kiểm tra chi tiết", "Gửi thông tin", "Thêm vào hảo hữu", "Chặn người này", "Thêm vào địch thủ"};
   public static final int[] FARM_DYNAMIC_MENU_MANIPULATE = new int[]{2, 3, 3, 3, 3};
   public static final String[] SOIL_CIRCS = new String[]{"", "Nứt nẻ", "Khô", "Bình thường", "Ẩm ướt"};
   private static int m_nCharacterID = 0;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 2555905:
      case 2555906:
         MainCanvas.isWaiting = true;
         ba.writeInt(m_nPlayerIndex);
         break;
      case 2555907:
      case 2555908:
      case 2555910:
      case 2555911:
      case 2555912:
      case 2555913:
      case 2555915:
      case 2555955:
      case 2555957:
      case 2555959:
      case 2555960:
      case 2555961:
      case 2555972:
      case 2555976:
         MainCanvas.isWaiting = true;
         break;
      case 2555909:
      case 2555958:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_nBackpackIndex);
         ba.writeByte((byte)UITopForm.buyNum);
         break;
      case 2555914:
         MainCanvas.isWaiting = true;
         ba.writeInt(m_nCharacterID);
      case 2555916:
      case 2555917:
      case 2555918:
      case 2555919:
      case 2555921:
      case 2555922:
      case 2555923:
      case 2555924:
      case 2555925:
      case 2555926:
      case 2555927:
      case 2555928:
      case 2555929:
      case 2555930:
      case 2555931:
      case 2555932:
      case 2555933:
      case 2555934:
      case 2555935:
      case 2555947:
      case 2555948:
      case 2555949:
      case 2555950:
      case 2555951:
      case 2555953:
      case 2555967:
      default:
         break;
      case 2555920:
         ba.writeUTF(m_sFarmEnounce);
         break;
      case 2555936:
         MainCanvas.isWaiting = true;
         ba.writeInt(m_nCharacterID);
         ba.writeByte(m_bGlebeKind);
         ba.writeByte(m_bGlebeIndex);
         ba.writeByte(m_bMenuIndex);
         break;
      case 2555937:
         MainCanvas.isWaiting = true;
         ba.writeInt(m_nCharacterID);
         ba.writeByte(m_bGlebeKind);
         ba.writeByte(m_bGlebeIndex);
         ba.writeByte(m_nBackpackIndex);
         break;
      case 2555938:
         MainCanvas.isWaiting = true;
         ba.writeInt(m_nCharacterID);
         ba.writeByte(m_bGlebeKind);
         ba.writeByte(m_bGlebeIndex);
         ba.writeByte(m_nBackpackIndex);
         break;
      case 2555939:
      case 2555956:
      case 2555962:
      case 2555968:
      case 2555969:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_nBackpackIndex);
         break;
      case 2555940:
      case 2555963:
      case 2555970:
      case 2555971:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_nBackpackIndex);
         ba.writeByte((byte)UITopForm.buyNum);
         break;
      case 2555941:
      case 2555943:
      case 2555945:
         MainCanvas.isWaiting = true;
         m_nLargessFriend = (byte)UITopForm.buyNum;
         break;
      case 2555942:
      case 2555944:
      case 2555946:
         MainCanvas.isWaiting = true;
         ba.writeInt(m_nFriendID);
         ba.writeByte(m_nBackpackIndex);
         ba.writeByte(m_nLargessFriend);
         break;
      case 2555952:
         ba.writeInt(m_nCharacterID);
         ba.writeByte(m_nFarm_On_Off);
         break;
      case 2555954:
      case 2555975:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_nDepotSellingIndex);
         ba.writeByte((byte)UITopForm.buyNum);
         break;
      case 2555964:
      case 2555965:
         MainCanvas.isWaiting = true;
         ba.writeInt(m_nPlayerIndex);
         break;
      case 2555966:
         MainCanvas.isWaiting = true;
         ba.writeInt(m_nPlayerIndex);
         ba.writeInt(m_bMenuIndex);
         break;
      case 2555973:
         ba.writeInt(m_nCharacterID);
         break;
      case 2555974:
         ba.writeByte(m_bRenovate);
         break;
      case 2555977:
         MainCanvas.isWaiting = true;
         ba.writeByte(HelpID);
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      int i;
      switch(commID) {
      case -2144927743:
         m_sWeather = data.readUTF();
         m_sFarmName = data.readUTF();
         m_nCharacterID = data.readInt();
         m_bWhetherGetHome = data.readByte();
         break;
      case -2144927695:
         byte number = data.readByte();

         UIPicture picture;
         for(i = 0; i < number; ++i) {
            byte index = data.readByte();
            if (MainCanvas.backForms.size() > 0) {
               UIForm f = (UIForm)MainCanvas.backForms.elementAt(0);
               picture = (UIPicture)f.getComponents().elementAt(index);
            } else {
               picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(index);
            }

            byte WhetherUse = data.readByte();
            picture.setWhetherEnvelop(WhetherUse);
            if (WhetherUse == 0) {
               picture.setCropName("");
               picture.setGrowthPhase("");
               picture.setGrowthLimit((byte)0);
               picture.setWhetherGain((byte)0);
               picture.setCropStatus_1((byte)0);
               picture.setCropStatus_2("");
               picture.setGroundStatus((byte)0);
               picture.setFrameIndex(0);
               picture.setMenuIndex(0);
            } else {
               String name = data.readUTF();
               picture.setCropName(name);
               String pullulate = data.readUTF();
               picture.setGrowthPhase(pullulate);
               byte pullulate_limit = data.readByte();
               picture.setGrowthLimit(pullulate_limit);
               byte gain = data.readByte();
               picture.setWhetherGain(gain);
               byte crop_status_1 = data.readByte();
               picture.setCropStatus_1(crop_status_1);
               String crop_status_2 = data.readUTF();
               picture.setCropStatus_2(crop_status_2);
               byte ground_status = data.readByte();
               picture.setGroundStatus(ground_status);
               short picture_index = data.readShort();
               int frame_index = data.readInt();
               picture.setFrameIndex(frame_index);
               int menuindex = data.readInt();
               picture.setMenuIndex(menuindex);
               picture.setQuality(picture_index);
               picture.isWpPic = true;
               short picID = (short)(picture_index % 1000);
               picture.wpIndex = picID;
               picture.setImg(MainCanvas.stuffMImg);
            }
         }

         UILabel label;
         UILabel label_1;
         UILabel label_2;
         UILabel label_3;
         UILabel label_4;
         if (MainCanvas.backForms.size() > 0) {
            UIForm f = (UIForm)MainCanvas.backForms.elementAt(0);
            label = (UILabel)f.getComponents().elementAt(3);
            label_1 = (UILabel)f.getComponents().elementAt(4);
            label_2 = (UILabel)f.getComponents().elementAt(6);
            label_3 = (UILabel)f.getComponents().elementAt(10);
            label_4 = (UILabel)f.getComponents().elementAt(8);
         } else {
            label = (UILabel)MainCanvas.curForm.getComponents().elementAt(3);
            label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
            label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
            label_3 = (UILabel)MainCanvas.curForm.getComponents().elementAt(10);
            label_4 = (UILabel)MainCanvas.curForm.getComponents().elementAt(8);
         }

         UIForm f;
         for(i = 0; i < 12; ++i) {
            if (MainCanvas.backForms.size() > 0) {
               f = (UIForm)MainCanvas.backForms.elementAt(0);
               picture = (UIPicture)f.getComponents().elementAt(i + 11);
            } else {
               picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 11);
            }

            if (picture.isFocus() && picture.getWhetherEnvelop() != 0) {
               label.setText(picture.getCropName());
               label_1.setText(picture.getGrowthPhase());
               label_2.setNum1(picture.getGrowthLimit());
               label_3.setText(picture.getCropStatus_2());
               if (picture.getGroundStatus() == -1) {
                  label_4.setText(SOIL_CIRCS[0]);
               } else if (picture.getGroundStatus() == 0) {
                  label_4.setText(SOIL_CIRCS[1]);
               } else if (picture.getGroundStatus() >= 1 && picture.getGroundStatus() <= 30) {
                  label_4.setText(SOIL_CIRCS[2]);
               } else if (picture.getGroundStatus() >= 31 && picture.getGroundStatus() <= 70) {
                  label_4.setText(SOIL_CIRCS[3]);
               } else if (picture.getGroundStatus() >= 71 && picture.getGroundStatus() <= 100) {
                  label_4.setText(SOIL_CIRCS[4]);
               }
            }
         }

         for(i = 0; i < 6; ++i) {
            if (MainCanvas.backForms.size() > 0) {
               f = (UIForm)MainCanvas.backForms.elementAt(0);
               picture = (UIPicture)f.getComponents().elementAt(i + 25);
            } else {
               picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 25);
            }

            if (picture.isFocus() && picture.getWhetherEnvelop() != 0) {
               label.setText(picture.getCropName());
               label_1.setText(picture.getGrowthPhase());
               label_2.setNum1(picture.getGrowthLimit());
               label_3.setText(picture.getCropStatus_2());
               if (picture.getGroundStatus() == -1) {
                  label_4.setText(SOIL_CIRCS[0]);
               } else if (picture.getGroundStatus() == 0) {
                  label_4.setText(SOIL_CIRCS[1]);
               } else if (picture.getGroundStatus() >= 1 && picture.getGroundStatus() <= 30) {
                  label_4.setText(SOIL_CIRCS[2]);
               } else if (picture.getGroundStatus() >= 31 && picture.getGroundStatus() <= 70) {
                  label_4.setText(SOIL_CIRCS[3]);
               } else if (picture.getGroundStatus() >= 71 && picture.getGroundStatus() <= 100) {
                  label_4.setText(SOIL_CIRCS[4]);
               }
            }
         }

         return;
      case -2144927675:
         i = data.readInt();
         UILabel label_5 = (UILabel)MainCanvas.curForm.getComponents().elementAt(32);
         label_5.setNum1(i);
      }

   }

   public static void Initialization() {
      if (m_bWhetherGetHome != 0) {
         m_bWhetherGetHome = 0;
      }

      if (m_nFarm_On_Off != 0) {
         m_nFarm_On_Off = 0;
      }

      if (m_nPlayerIndex != 0) {
         m_nPlayerIndex = 0;
      }

   }

   public static final void FARM_SEND_DEOP() {
      final Form form = new Form("Gửi thông tin");
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      final TextField tf = new TextField("Nhập nội dung thông tin", "", 30, 0);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.append(tf);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            if (c == okCmd) {
               Alert alert;
               if (!MainCanvas.CharacterValidate(tf.getString(), (byte)3)) {
                  alert = new Alert(Cons.C_STR[9], "Nội dung nhập có ký tự phi pháp, nhập lại!", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
               }

               if (tf.getString().equals("") || tf.getString() == null) {
                  alert = new Alert(Cons.C_STR[9], "Nhập nội dung thông tin!", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
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
   }
}

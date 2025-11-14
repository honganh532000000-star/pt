public class PCGamble {
   public static final byte m_bExtent = 7;
   public static boolean Begin = false;
   public static UITextField[] textfield = new UITextField[7];
   public static int m_nJetton = 0;
   public static final byte[] picture = new byte[]{1, 3, 5, 7, 9, 11, 13};
   public static UIPicture[] uipicture = new UIPicture[7];
   public static byte BigOrSmall = 0;
   public static int index = 0;
   public static byte server_index = 0;
   public static int m_nCircle = 0;
   public static int m_nCircleCounter = 0;
   public static byte arithmometer = 0;
   public static int jetton;
   public static boolean award = false;
   public static boolean award_1 = false;
   public static boolean renovate = false;
   public static boolean Begin_1 = false;
   public static byte luxian = 0;
   public static boolean rebound = false;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 2097153:
         MainCanvas.isWaiting = true;

         for(int i = 0; i < 7; ++i) {
            ba.writeByte(textfield[i].getNumber());
         }

         ba.writeInt(m_nJetton);
         break;
      case 2097154:
         MainCanvas.isWaiting = true;
         ba.writeInt(jetton);
         ba.writeInt(m_nJetton);
         ba.writeByte(BigOrSmall);
         ba.writeInt(jetton);
         break;
      case 2097155:
         MainCanvas.isWaiting = true;
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2145386495:
         MainCanvas.isWaiting = false;
         award = data.readBoolean();
         m_nJetton = data.readInt();
         jetton = data.readInt();
         server_index = data.readByte();
         Begin = true;
         renovate = true;
         arithmometer = 0;
         break;
      case -2145386494:
         MainCanvas.isWaiting = false;
         award_1 = data.readBoolean();
         m_nJetton = data.readInt();
         jetton = data.readInt();
         Begin_1 = true;
      }

   }

   public static void ExpenditureCount(byte index) {
      UILabel label = (UILabel)MainCanvas.curForm.getComponents().elementAt(37);

      for(int i = 0; i < 7; ++i) {
         textfield[i] = (UITextField)MainCanvas.curForm.getComponents().elementAt(24 + i);
         if (textfield[i].getNumber() != 0 && textfield[i].isFocus()) {
            int jetton;
            if (index == 1) {
               jetton = Integer.parseInt(label.getText()) - textfield[i].getNumber();
               label.setText("" + jetton);
            } else if (index == 2) {
               jetton = Integer.parseInt(label.getText()) + textfield[i].getNumber();
               label.setText("" + jetton);
            }
         }
      }

   }

   public static void Eliminate() {
      UILabel label = (UILabel)MainCanvas.curForm.getComponents().elementAt(32);
      UILabel label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(36);
      UIPicture picture_1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(7);
      UIPicture picture_2 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(9);
      UIPicture picture_3 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(31);
      UIPicture picture_4 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(33);
      UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
      renovate = false;
      m_nCircle = 0;
      m_nCircleCounter = 0;
      if (award && luxian == 0) {
         label_1.setText("" + jetton);
         label.setText("Kết toán");
         menuBar.setMenuText("Kết toán", 0);
         luxian = 1;
         UITopForm.createLocalTopForm((byte)0, (String)("Chúc mừng các hạ nhận được" + jetton + "KNB, các hạ có thể kết toán KNB này, cũng có thể nhấn trái phải đoán âm dương, nếu đoán trúng sẽ nhận được KNBx2!"), "Đồng ý", "", -1, -2);
      } else if (!award && luxian == 0 || !award_1 && luxian == 1) {
         label.setText("Mở");
         menuBar.setMenuText("Mở", 0);
         UITopForm.createLocalTopForm((byte)0, (String)"Thật ngại quá, các hạ không nhận được KNB, hãy cố gắng thêm!", "Đồng ý", "", -1, -2);
         Begin = false;
         label_1.setText("0");

         for(int i = 0; i < 7; ++i) {
            textfield[i].setLabel("0");
         }

         luxian = 0;
         index = 0;
         server_index = 0;
      } else if (award_1 && luxian == 1) {
         label_1.setText("" + jetton);
         label.setText("Kết toán");
         menuBar.setMenuText("Kết toán", 0);
         UITopForm.createLocalTopForm((byte)0, (String)("Chúc mừng các hạ nhận được" + jetton + "KNB, các hạ có thể kết toán KNB này, cũng có thể nhấn trái phải đoán âm dương!"), "Đồng ý", "", -1, -2);
      }

      picture_1.setFocus(false, MainCanvas.curForm);
      picture_2.setFocus(false, MainCanvas.curForm);
      picture_3.setFocus(false, MainCanvas.curForm);
      picture_4.setFocus(false, MainCanvas.curForm);
      label.setFocus(true, MainCanvas.curForm);
      rebound = false;
   }
}

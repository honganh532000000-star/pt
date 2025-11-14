public class PCExchange {
   public static String[] name;
   public static String[] name_1;
   public static String[] name_2;
   public static byte m_nRegister = 0;
   public static int[] m_nMax;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 2359297:
         MainCanvas.isWaiting = true;
         break;
      case 2359298:
      case 2359299:
         if (UITopForm.buyNum == 0) {
            UITopForm.createLocalTopForm((byte)0, (String)"Hãy nhập số lượng", "Xác nhận", "", -1, -2);
         } else {
            MainCanvas.isWaiting = true;
            ba.writeByte(m_nRegister + 1);
            ba.writeInt(UITopForm.buyNum);
         }
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2145124351:
         String l_sStr1 = data.readUTF();
         String l_sStr2 = data.readUTF();
         name_1[m_nRegister] = l_sStr1;
         name_2[m_nRegister] = l_sStr2;
         UILabel label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(7);
         label_1.setText(name_1[m_nRegister]);
         UILabel label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(8);
         label_2.setText(name_2[m_nRegister]);
      default:
      }
   }
}

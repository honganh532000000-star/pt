public class PCCharge {
   public static int chargeId = 0;
   public static int otherChargeId = 0;
   public static String chargeStr;
   public static int chargeNum = 0;
   public static byte chargeType;
   public static final byte CHARGE_TYPE_DEFAULT = 0;
   public static final byte CHARGE_TYPE_YIBAO = 1;
   public static final byte CHARGE_TYPE_SHENZHOUFU = 2;
   public static final byte CHARGE_TYPE_JUNWANG = 3;
   public static final byte CHARGE_TYPE_RUYIFU = 4;
   public static final byte CHARGE_TYPE_19PAY = 5;
   public static final byte CHARGE_TYPE_KUAIQIAN = 6;
   public static final byte CHARGE_TYPE_YINHANG = 7;
   public static final byte CHARGE_TYPE_ZHIFUBAO = 8;
   public static String chargeAccount;
   public static String myAccount;
   public static String cardNum;
   public static String cardPsw;
   public static byte failNum = 0;
   public static boolean isPop = false;
   public static boolean isPassPortCharge = true;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 2621441:
         PCIncrement.m_bFull = 1;
         MainCanvas.isWaiting = true;
         break;
      case 2621442:
         PCIncrement.m_bFull = 0;
         MainCanvas.isWaiting = true;
         break;
      case 2621443:
      case 2621444:
      case 2621447:
      case 2621448:
         MainCanvas.isWaiting = true;
         break;
      case 2621445:
         MainCanvas.isWaiting = true;
         ba.writeInt(otherChargeId);
         ba.writeByte(PCIncrement.m_bFull);
         break;
      case 2621446:
         MainCanvas.isWaiting = true;
         ba.writeByte(PCIncrement.m_bFull);
         if (chargeId == -100) {
            ba.writeInt(otherChargeId);
         } else {
            ba.writeInt(0);
         }

         if (chargeId == -100) {
            ba.writeInt(chargeNum);
         } else {
            ba.writeInt(chargeId);
         }

         ba.writeUTF(cardNum);
         ba.writeUTF(cardPsw);
         ba.writeUTF(myAccount);
         ba.writeUTF(chargeAccount);
         if (isPassPortCharge) {
            ba.writeUTF("p");
         } else {
            ba.writeUTF("k");
         }
         break;
      case 2621449:
         MainCanvas.isWaiting = true;
         if (chargeId == -100) {
            ba.writeInt(otherChargeId);
         } else {
            ba.writeInt(0);
         }

         ba.writeByte(PCIncrement.m_bFull);
         ba.writeUTF(chargeAccount);
         break;
      case 2621450:
         MainCanvas.isWaiting = true;
         MainCanvas.quitUI();
         MainCanvas.ni.send(2425024);
         break;
      case 2621451:
         MainCanvas.isWaiting = true;
         if (chargeId == -100) {
            ba.writeInt(otherChargeId);
         } else {
            ba.writeInt(0);
         }
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2144862206:
         isPop = data.readBoolean();
         break;
      case -2144796673:
         if (++failNum == 2) {
            failNum = 0;
            isPassPortCharge = true;
            MainCanvas.ni.send(2424998);
         } else {
            MainCanvas.ni.send(2621446);
         }
      }

   }
}

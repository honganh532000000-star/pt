public class PCPropCallback {
   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 3211265:
      case 3211266:
      case 3211267:
      case 3211268:
      case 3211269:
         MainCanvas.isWaiting = true;
      default:
         return ba.toByteArray();
      }
   }

   public static void rec_Parse(int commID, ByteArray data) {
   }
}

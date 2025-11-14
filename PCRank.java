public class PCRank {
   public static byte rankIndex;

   public static byte[] snd_Parse(int commID) {
      MainCanvas.isWaiting = true;
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 1507329:
         ba.writeInt(GOManager.currentTarget.getID());
         break;
      case 1507330:
         ba.writeByte(rankIndex);
      case 1507331:
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
   }
}

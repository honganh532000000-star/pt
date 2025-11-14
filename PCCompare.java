public class PCCompare {
   public static boolean isTaskCompare = false;

   public static byte[] snd_Parse(int commID) {
      MainCanvas.isWaiting = true;
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 1245186:
         if (isTaskCompare) {
            ba.writeInt(Player.getInstance().getID());
         } else if (UIForm.getIndexType()[0] == 2) {
            ba.writeInt(GOManager.currentTarget.getID());
         } else {
            ba.writeInt(Player.getInstance().getID());
         }

         for(int i = 0; i < UIForm.getCompareIndex().length; ++i) {
            ba.writeByte(UIForm.getIndexType()[i]);
            ba.writeInt(UIForm.getCompareIndex()[i]);
         }

         if (isTaskCompare) {
            ba.writeInt(UIList.selectedListId);
            isTaskCompare = false;
         }
      case 1245185:
      default:
         return ba.toByteArray();
      }
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2146238462:
         MainCanvas.cmdID = commID;
         MainCanvas.cmdData = data.readByteArray(data.getLength() - 6);
      default:
      }
   }
}

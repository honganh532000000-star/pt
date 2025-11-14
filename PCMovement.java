public class PCMovement {
   public static int movementType = -1;
   public static int actID = -1;
   public static int guideID = -1;
   public static int[] movementIds;

   public static byte[] snd_Parse(int commID) {
      MainCanvas.isWaiting = true;
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 2293874:
         ba.writeInt(actID);
         break;
      case 2293875:
         ba.writeInt(guideID);
         ba.writeShort(UIList.selectedObjX);
         ba.writeShort(UIList.selectedObjY);
         break;
      case 2293876:
         ba.writeInt(UILabel.movementID);
         break;
      case 2297615:
         ba.writeInt(movementType);
         ba.writeUTF(MainCanvas.name_password[0]);
         ba.writeUTF(MainCanvas.name_password[1]);
         MainCanvas.quitUI();
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2145128208:
         movementType = data.readInt();
         break;
      case -2145127951:
         byte fc = data.readByte();
         boolean haveMapMenu = false;
         int[] finds = new int[fc];
         String[] fcens = new String[fc];
         movementIds = new int[fc];

         for(int fi = 0; fi < fc; ++fi) {
            finds[fi] = data.readInt();
            haveMapMenu = true;
            fcens[fi] = data.readUTF();
            movementIds[fi] = data.readInt();
         }

         if (haveMapMenu) {
            UIMenu.npcMenu = new UIMenu((byte)2);
            UIMenu.npcMenu.addMenu(fcens, finds);
            UIMenu.npcMenu.setCenter(false);
            MainCanvas.setGameState((byte)5);
            MainCanvas.isWaiting = false;
         }
      }

   }
}

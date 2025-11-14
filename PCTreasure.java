public class PCTreasure {
   public static long curExpLabel;
   public static long maxExpLabel;
   public static short treasureGrade;
   public static int treasurePicID;
   public static String[] explain = new String[5];
   public static byte tally = 0;
   public static boolean interphase;
   private static int differentiate;
   private static long id;
   private static boolean bargaining;
   public static byte pictureIndex;
   public static boolean unlockMenu = true;
   public static boolean treasureMenu;
   public static String[] treasureMenuContent = new String[2];
   public static byte registerIndex = -1;
   private static byte registerTreasureIndex = -1;
   public static byte selectItemIndex = -1;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 3342337:
      case 3342339:
         ba.writeByte(UIGrid.severGridIndex);
         MainCanvas.isWaiting = true;
         break;
      case 3342338:
         ba.writeByte(UIPicture.equipTypeForServer);
         MainCanvas.isWaiting = true;
         break;
      case 3342340:
         ba.writeInt(differentiate);
         ba.writeLong(id);
         ba.writeBoolean(bargaining);
         MainCanvas.isWaiting = true;
         break;
      case 3342341:
      case 3342343:
         ba.writeByte(pictureIndex);
         ba.writeInt(differentiate);
         ba.writeLong(id);
         ba.writeBoolean(bargaining);
         MainCanvas.isWaiting = true;
         break;
      case 3342342:
      case 3342344:
         UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(10);
         ba.writeByte(grid.gridIndex);
         ba.writeByte(pictureIndex);
         ba.writeInt(differentiate);
         ba.writeLong(id);
         ba.writeBoolean(bargaining);
         MainCanvas.isWaiting = true;
      case 3342345:
      case 3342346:
      case 3342347:
      case 3342348:
      case 3342349:
      case 3342350:
      case 3342351:
      case 3342355:
      default:
         break;
      case 3342352:
         registerTreasureIndex = UIGrid.severGridIndex;
         ba.writeByte(UIGrid.severGridIndex);
         MainCanvas.isWaiting = true;
         break;
      case 3342353:
         ba.writeByte(registerIndex);
         ba.writeByte(registerTreasureIndex);
         MainCanvas.isWaiting = true;
         break;
      case 3342354:
      case 3342356:
      case 3342361:
         MainCanvas.isWaiting = true;
         break;
      case 3342357:
      case 3342358:
         ba.writeByte(selectItemIndex);
         MainCanvas.isWaiting = true;
         break;
      case 3342359:
         MainCanvas.isWaiting = true;
         ba.writeByte(PCUnsealGemCarve.m_bPrimaryGemIndex);
         ba.writeByte(PCUnsealGemCarve.m_bCarveSymbolIndex);
         break;
      case 3342360:
         MainCanvas.isWaiting = true;
         ba.writeByte(PCUnsealGemCarve.m_bPrimaryGemIndex);
         ba.writeByte(PCUnsealGemCarve.m_bCarveSymbolIndex);
         PCUnsealGemCarve.EliminateBata();
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2144141311:
         curExpLabel = data.readLong();
         maxExpLabel = data.readLong();
         treasureGrade = data.readShort();
         treasurePicID = data.readInt();
         differentiate = data.readInt();
         id = data.readLong();
         bargaining = data.readBoolean();
         interphase = true;
         break;
      case -2144141310:
         treasureMenu = data.readBoolean();
         treasureMenuContent[0] = data.readUTF();
         treasureMenuContent[1] = data.readUTF();
         if (!treasureMenu) {
            registerIndex = -1;
            registerTreasureIndex = -1;
         }
         break;
      case -2144141309:
         PCUnsealGemCarve.m_bPrimaryGemIndex = data.readByte();
         PCUnsealGemCarve.AppendPicture(4, true, 0, false);
         break;
      case -2144141308:
         PCUnsealGemCarve.m_bCarveSymbolIndex = data.readByte();
         PCUnsealGemCarve.AppendPicture(6, true, 0, true);
      }

   }

   public static void intermit() {
      if (treasurePicID != 0) {
         treasurePicID = 0;
      }

      if (interphase) {
         interphase = false;
      }

      if (!unlockMenu) {
         unlockMenu = true;
      }

   }

   public static void backtrack() {
      if (treasureMenu) {
         treasureMenu = false;

         for(int i = 0; i < 2; ++i) {
            treasureMenuContent[i] = "";
         }

         registerIndex = -1;
         registerTreasureIndex = -1;
      }

   }
}

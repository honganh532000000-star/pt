public class PCEquip {
   public static byte m_bEquipIndex = -1;
   public static byte m_bPropIndex = -1;
   public static byte m_bOrigin = -1;
   public static byte durable = 0;

   public static byte[] snd_Parse(int commID) {
      MainCanvas.isWaiting = true;
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 917506:
         ba.writeByte(UIPicture.equipTypeForServer);
         break;
      case 917507:
         ba.writeByte(UIPicture.equipTypeForServer);
      case 917508:
      case 917510:
      case 917512:
      case 917513:
      case 917514:
      case 917515:
      case 917516:
      case 917517:
      case 917518:
      case 917519:
      case 917520:
      default:
         break;
      case 917509:
      case 917523:
         ba.writeByte(UIGrid.severGridIndex);
         ba.writeByte(UIPicture.equipTypeForServer);
         break;
      case 917511:
         ba.writeByte(UIGrid.dropIndex);
         break;
      case 917521:
         ba.writeByte(UIPicture.equipTypeForServer);
         break;
      case 917522:
         ba.writeByte(UIPicture.equipTypeForServer);
         break;
      case 917524:
         ba.writeByte(m_bEquipIndex);
         ba.writeByte(m_bOrigin);
         ba.writeByte(m_bPropIndex);
         break;
      case 917525:
      case 917530:
         MainCanvas.isWaiting = true;
         break;
      case 917526:
      case 917527:
         ba.writeByte(PCTreasure.selectItemIndex);
         MainCanvas.isWaiting = true;
         break;
      case 917528:
         MainCanvas.isWaiting = true;
         ba.writeByte(PCUnsealGemCarve.m_bPrimaryGemIndex);
         ba.writeByte(PCUnsealGemCarve.m_bCarveSymbolIndex);
         break;
      case 917529:
         MainCanvas.isWaiting = true;
         ba.writeByte(PCUnsealGemCarve.m_bPrimaryGemIndex);
         ba.writeByte(PCUnsealGemCarve.m_bCarveSymbolIndex);
         PCUnsealGemCarve.EliminateBata();
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2146566141:
         PCUnsealGemCarve.m_bPrimaryGemIndex = data.readByte();
         PCUnsealGemCarve.AppendPicture(4, true, 0, false);
         break;
      case -2146566140:
         PCUnsealGemCarve.m_bCarveSymbolIndex = data.readByte();
         PCUnsealGemCarve.AppendPicture(6, true, 0, true);
         break;
      case -2146565984:
         durable = data.readByte();
      }

   }
}

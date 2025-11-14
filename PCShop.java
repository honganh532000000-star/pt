public class PCShop {
   public static byte[] snd_Parse(int commID) {
      MainCanvas.isWaiting = true;
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 983041:
         ba.writeInt(GOManager.currentTarget.getID());
         ba.writeByte(UIGrid.severGridIndex);
         break;
      case 983042:
         ba.writeInt(GOManager.currentTarget.getID());
         ba.writeByte(UIGrid.severGridIndex);
         ba.writeByte(UITopForm.buyNum);
         UITopForm.buyNum = 0;
         break;
      case 983043:
         ba.writeByte(UIGrid.severGridIndex);
         ba.writeByte(UITopForm.buyNum);
         UITopForm.buyNum = 0;
         break;
      case 983044:
         MainCanvas.isWaiting = true;
         break;
      case 983045:
         ba.writeByte(UIGrid.severGridIndex);
         break;
      case 983046:
      case 983047:
      case 983048:
      case 983057:
         MainCanvas.isWaiting = true;
         ba.writeInt(GOManager.currentTarget.getID());
         break;
      case 983049:
         ba.writeInt(GOManager.currentTarget.getID());
      case 983050:
      case 983051:
      case 983052:
      case 983053:
      case 983054:
      case 983055:
      default:
         break;
      case 983056:
      case 983058:
         ba.writeInt(GOManager.currentTarget.getID());
      }

      return ba.toByteArray();
   }
}

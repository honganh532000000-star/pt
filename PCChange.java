public class PCChange {
   public static byte[] snd_Parse(int commID) {
      MainCanvas.isWaiting = true;
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 1769473:
      case 1769478:
      case 1769479:
      case 1769480:
      case 1769488:
      case 1769489:
      case 1769490:
      case 1769491:
      case 1769492:
         ba.writeInt(GOManager.currentTarget.getID());
         break;
      case 1769474:
         ba.writeInt(GOManager.currentTarget.getID());
         ba.writeByte(UIGrid.severGridIndex);
         ba.writeInt(UITopForm.buyNum);
         break;
      case 1769475:
      case 1769481:
         ba.writeInt(GOManager.currentTarget.getID());
         ba.writeByte(UIGrid.severGridIndex);
         break;
      case 1769476:
         ba.writeInt(GOManager.currentTarget.getID());
         ba.writeByte(UIGrid.severGridIndex);
      case 1769477:
      case 1769482:
      case 1769483:
      case 1769484:
      case 1769485:
      case 1769486:
      case 1769487:
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2145714175:
         MainCanvas.isWaiting = false;
         int maxNum = data.readInt();
         String info = data.readUTF();
         int cmd = data.readInt();
         UITopForm.MAXNUMBER = maxNum;
         UITopForm.createLocalTopForm((byte)1, (String)info, "Xác nhận", "Quay về", cmd, -1);
         MainCanvas.curTopForm.setNumber(maxNum);
      default:
      }
   }
}

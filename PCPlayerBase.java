public class PCPlayerBase {
   public static byte[] snd_Parse(int commID) {
      MainCanvas.isWaiting = true;
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 393218:
         UIList list = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
         ba.writeByte(list.selectedIndex);
      case 393219:
      case 393220:
      case 393221:
      case 393222:
      case 393223:
      case 393224:
      case 393225:
      case 393228:
      default:
         break;
      case 393226:
         int len = UIDummyWidget.saveInfoFormContent.size();

         for(int i = 0; i < len; ++i) {
            ba.writeUTF(UIDummyWidget.saveInfoFormContent.elementAt(i).toString());
         }

         return ba.toByteArray();
      case 393227:
         ba.writeByte(UIList.PCSelectedIndex);
         break;
      case 393229:
         ba.writeInt(GOManager.currentTarget.getID());
         break;
      case 393230:
         ba.writeInt(GOManager.currentTarget.getID());
         break;
      case 393231:
         ba.writeInt(GOManager.currentTarget.getID());
         ba.writeByte(UIPicture.equipTypeForServer);
         PCTreasure.unlockMenu = false;
         break;
      case 393232:
         ba.writeInt(PCPackage.m_nGrade);
         ba.writeInt(PCPackage.m_nClanGrade);
         break;
      case 393233:
         ba.writeInt(GOManager.currentTarget.getID());
         ba.writeByte(Player.getInstance().showActionIndex);
         MainCanvas.isWaiting = false;
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2147090429:
         MainCanvas.cmdID = commID;
         MainCanvas.cmdData = data.readByteArray(data.getLength() - 6);
         break;
      case -2147090423:
         MainCanvas.cmdID = commID;
         MainCanvas.cmdData = data.readByteArray(data.getLength() - 6);
      }

   }
}

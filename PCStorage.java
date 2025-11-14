public class PCStorage {
   public static byte keepNum = 0;
   public static byte moveIndex = -1;

   public static byte[] snd_Parse(int commID) {
      MainCanvas.isWaiting = true;
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 1179650:
         ba.writeByte(UIGrid.severGridIndex);
         break;
      case 1179651:
         ba.writeByte(UIGrid.severGridIndex);
         ba.writeByte(keepNum);
         break;
      case 1179652:
         ba.writeInt(UIGrid.storageMoney);
         UIGrid.storageMoney = 0;
         break;
      case 1179653:
         ba.writeByte(UIGrid.severGridIndex);
         ba.writeByte(keepNum);
         break;
      case 1179654:
         ba.writeInt(UIGrid.storageMoney);
         UIGrid.storageMoney = 0;
         break;
      case 1179655:
         MainCanvas.isWaiting = true;
         break;
      case 1179656:
         ba.writeByte(moveIndex);
         ba.writeByte(PCPackage.exchangeIndex[0]);
         ba.writeByte(PCPackage.exchangeIndex[1]);
         ba.writeByte(PCPackage.moveNum);
         MainCanvas.curForm.resetExchangeVal();
         MainCanvas.isWaiting = true;
      case 1179657:
      case 1179658:
      case 1179660:
      default:
         break;
      case 1179659:
         ba.writeByte(UIGrid.severGridIndex);
         break;
      case 1179661:
         ba.writeByte(UIGrid.severGridIndex);
         ba.writeByte(PCPackage.extendIndex);
         PCPackage.needExtend = false;
         UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
         menuBar.setMenuText("Quay về", 1);
         setExtendPicMoveToGrid(true);
         break;
      case 1179662:
         ba.writeByte(PCPackage.extendIndex);
         MainCanvas.isWaiting = true;
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2146303988:
         boolean isUnLock = data.readBoolean();
         byte beginIndex = data.readByte();
         byte endIndex = data.readByte();
         UIGrid grid = null;
         if (MainCanvas.curForm != null) {
            if (MainCanvas.curForm.clientCommand == 1179649) {
               grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(6);
            }

            if (grid != null) {
               for(int i = beginIndex; i < endIndex; ++i) {
                  if (isUnLock) {
                     grid.stuffLock[i] = false;
                  } else {
                     grid.stuffLock[i] = true;
                  }
               }
            }
         }
         break;
      case -2146303987:
         byte result = data.readByte();
         if (result == 0) {
            PCPackage.needExtend = false;
         } else {
            PCPackage.needExtend = true;
            UIPicture[] pics = new UIPicture[]{(UIPicture)MainCanvas.curForm.getComponents().elementAt(7), (UIPicture)MainCanvas.curForm.getComponents().elementAt(8), (UIPicture)MainCanvas.curForm.getComponents().elementAt(9), (UIPicture)MainCanvas.curForm.getComponents().elementAt(10)};

            for(int i = 0; i < pics.length; ++i) {
               pics[i].isExtendPic = (result & Util.factorial(2, i)) != 0;
            }

            UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(3);
            pics[0].setFocus(true, MainCanvas.curForm);
            grid.setFocus(false, MainCanvas.curForm);
            UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
            menuBar.setMenuText("Hủy", 1);
            setExtendPicMoveToGrid(false);
         }
      }

      MainCanvas.isWaiting = false;
   }

   public static byte getExtendIndex() {
      if (((UIPicture)MainCanvas.curForm.getComponents().elementAt(7)).isFocus()) {
         return 0;
      } else if (((UIPicture)MainCanvas.curForm.getComponents().elementAt(8)).isFocus()) {
         return 1;
      } else if (((UIPicture)MainCanvas.curForm.getComponents().elementAt(9)).isFocus()) {
         return 2;
      } else {
         return (byte)(((UIPicture)MainCanvas.curForm.getComponents().elementAt(10)).isFocus() ? 3 : -1);
      }
   }

   public static void setExtendPicMoveToGrid(boolean isMove) {
      if (isMove) {
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(7)).setAroundComponent((byte)1, (byte)4);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(8)).setAroundComponent((byte)1, (byte)4);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(9)).setAroundComponent((byte)1, (byte)4);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(10)).setAroundComponent((byte)1, (byte)4);
      } else {
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(7)).setAroundComponent((byte)1, (byte)-1);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(8)).setAroundComponent((byte)1, (byte)-1);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(9)).setAroundComponent((byte)1, (byte)-1);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(10)).setAroundComponent((byte)1, (byte)-1);
      }

   }

   public static void setGridMoveToGrid(boolean isMove) {
      if (isMove) {
         ((UIGrid)MainCanvas.curForm.getComponents().elementAt(6)).setAroundComponent((byte)1, (byte)9);
         ((UIGrid)MainCanvas.curForm.getComponents().elementAt(8)).setAroundComponent((byte)0, (byte)7);
      } else {
         ((UIGrid)MainCanvas.curForm.getComponents().elementAt(6)).setAroundComponent((byte)1, (byte)-1);
         ((UIGrid)MainCanvas.curForm.getComponents().elementAt(8)).setAroundComponent((byte)0, (byte)-1);
      }

   }
}

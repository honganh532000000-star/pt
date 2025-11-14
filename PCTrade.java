public class PCTrade {
   public static int otherPlayerID = 0;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 1376257:
         ba.writeInt(otherPlayerID);
      case 1376258:
      case 1376259:
      case 1376270:
      case 1376271:
      case 1376272:
      default:
         break;
      case 1376260:
         UIForm.clearTradeVal();
         MainCanvas.isWaiting = true;
         break;
      case 1376261:
         byte a = 0;

         int i;
         for(i = 0; i < UIGrid.tradeSign.length; ++i) {
            if (UIGrid.tradeSign[i] > -1) {
               ++a;
            }
         }

         ba.writeByte(a);
         i = 0;

         for(int ii = UIGrid.tradeSign.length; i < ii; ++i) {
            if (UIGrid.tradeSign[i] > -1) {
               ba.writeByte(UIGrid.tradeSign[i]);
               ba.writeByte(UIGrid.tradeSignNum[i]);
            }
         }

         MainCanvas.isWaiting = true;
         break;
      case 1376262:
      case 1376274:
         UIForm.clearTradeVal();
         MainCanvas.isWaiting = true;
         break;
      case 1376263:
         MainCanvas.isWaiting = true;
         break;
      case 1376264:
         UIForm.isClear = true;
         UIGrid.resetTradeSign();
         UIForm.tradeMyMoney = 0;
         MainCanvas.isWaiting = true;
         break;
      case 1376265:
         ba.writeInt(UIForm.tradeMyMoney);
         MainCanvas.isWaiting = true;
         break;
      case 1376266:
         if (UIForm.tradeMyMoney > UIForm.alertMoney) {
            ba.writeBoolean(true);
         } else {
            ba.writeBoolean(false);
         }

         UIForm.clearTradeVal();
         MainCanvas.isWaiting = true;
         break;
      case 1376267:
         ba.writeBoolean(UIForm.isOtherPic);
         ba.writeByte(UIForm.curPicNum);
         PCTreasure.unlockMenu = false;
         MainCanvas.isWaiting = true;
         break;
      case 1376268:
         ba.writeByte(UIForm.curPicNum);
         MainCanvas.isWaiting = true;
         break;
      case 1376269:
         ba.writeInt(GOManager.currentTarget.getID());
         MainCanvas.isWaiting = true;
         break;
      case 1376273:
         MainCanvas.isWaiting = true;
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2146107388:
         UIForm.myMoney = data.readInt();
      case -2146107387:
      case -2146107385:
      case -2146107384:
      case -2146107381:
      default:
         break;
      case -2146107386:
         UIForm.clearTradeVal();
         if (MainCanvas.getGameState() == 4) {
            MainCanvas.quitUI();
         }
         break;
      case -2146107383:
         UIForm.alertMoney = data.readInt();
         UIForm.tradeMyMoney = data.readInt();
         break;
      case -2146107382:
         UIForm.clearTradeVal();
         break;
      case -2146107380:
         byte b = data.readByte();
         UIGrid.tradeSign[b] = -1;
         UIGrid.tradeSignNum[b] = 1;
         break;
      case -2146107379:
         otherPlayerID = data.readInt();
         if (MainCanvas.getGameState() == 0 && !MainCanvas.isExitChoosePlayer && MainCanvas.curTopForm == null) {
            MainCanvas.ni.send(1376257);
         } else {
            MainCanvas.ni.send(1376270);
         }
      }

   }
}

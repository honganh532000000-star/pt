public class PCTask {
   public static boolean haveMayBeChoice = false;
   public static int selectedSelfTaskID = -1;
   public static byte getGoodsIndex = -1;
   public static byte answerIndex = -1;
   public static boolean isReceiveToView = false;
   public static int[] npcTaskIds;
   public static byte[] colorIndexs;

   public static byte[] snd_Parse(int commID) {
      MainCanvas.isWaiting = true;
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 262147:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262148:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262149:
         ba.writeInt(UIList.selectedListId);
         ba.writeInt(GOManager.currentTarget.getID());
         isReceiveToView = true;
         break;
      case 262150:
         ba.writeInt(UIList.selectedListId);
         ba.writeInt(GOManager.currentTarget.getID());
         ba.writeByte(UIPicture.sSelectedColIndex);
         UIPicture.sSelectedColIndex = -1;
         break;
      case 262151:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262152:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262153:
         ba.writeInt(GOManager.currentTarget.getID());
         break;
      case 262154:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262155:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262156:
         ba.writeInt(UIList.selectedListId);
         ba.writeByte(UIPicture.sGroupIndex);
         ba.writeByte(UIPicture.sColIndex);
         break;
      case 262157:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262158:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262159:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262162:
         ba.writeInt(UIList.selectedListId);
         ba.writeByte(UIList.guideSelectedIndex);
         break;
      case 262164:
         ba.writeInt(UITopForm.curTaskID);
         break;
      case 262166:
         ba.writeInt(selectedSelfTaskID);
         break;
      case 262167:
         ba.writeInt(selectedSelfTaskID);
         break;
      case 262169:
         ba.writeInt(selectedSelfTaskID);
         break;
      case 262170:
         ba.writeInt(selectedSelfTaskID);
      case 262171:
      case 262180:
      case 262181:
      case 262185:
      case 262187:
      case 262193:
      default:
         break;
      case 262172:
         ba.writeInt(UITopForm.curTaskID);
         break;
      case 262173:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262174:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262175:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262176:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262177:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262178:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262179:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262182:
         ba.writeByte(UIPicture.sGroupIndex);
         ba.writeByte(UIPicture.sColIndex);
         break;
      case 262184:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262186:
         ba.writeInt(UIList.selectedListId);
         break;
      case 262188:
         ba.writeInt(UIList.selectedListId);
         ba.writeInt(GOManager.currentTarget.getID());
         break;
      case 262189:
         ba.writeInt(UIList.selectedListId);
         ba.writeByte(getGoodsIndex);
         break;
      case 262190:
         ba.writeInt(UIList.selectedListId);
         ba.writeInt(GOManager.currentTarget.getID());
         break;
      case 262192:
         ba.writeByte(answerIndex);
         break;
      case 262194:
         ba.writeInt(UIList.selectedListId);
         ba.writeByte(UIList.guideSelectedIndex);
         break;
      case 263168:
         ba.writeInt(GOManager.currentTarget.getID());
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2147221500:
      case -2147221499:
      case -2147221498:
      case -2147221493:
      case -2147221492:
      case -2147221490:
         MainCanvas.cmdID = commID;
         MainCanvas.cmdData = data.readByteArray(data.getLength() - 6);
         break;
      case -2147221495:
         MainCanvas.quitUI();
         MainCanvas.mc.leftRightMenu();
         Player.getInstance().path = (new AStar()).findPath(Player.getInstance().nCol, Player.getInstance().nRow, UIList.selectedObjX, UIList.selectedObjY);
         Player.getInstance().setState((byte)104);
         MainCanvas.isWaiting = false;
         break;
      case -2147221491:
         UIList.selectedListId = data.readInt();
         break;
      case -2147221489:
         byte payResult = data.readByte();
         if (payResult == 1) {
            haveMayBeChoice = false;
         } else {
            haveMayBeChoice = true;
         }
         break;
      case -2147221488:
         MainCanvas.quitUI();
         break;
      case -2147221487:
         byte fc = data.readByte();
         boolean haveMapMenu = false;
         int[] finds = new int[fc];
         String[] fcens = new String[fc];
         npcTaskIds = new int[fc];
         colorIndexs = new byte[fc];

         for(int fi = 0; fi < fc; ++fi) {
            finds[fi] = data.readInt();
            haveMapMenu = true;
            fcens[fi] = data.readUTF();
            npcTaskIds[fi] = data.readInt();
            colorIndexs[fi] = data.readByte();
         }

         if (haveMapMenu) {
            UIMenu.npcMenu = new UIMenu((byte)3);
            UIMenu.npcMenu.addMenu(fcens, finds);
            UIMenu.npcMenu.setCenter(false);
            MainCanvas.setGameState((byte)5);
         }

         MainCanvas.isWaiting = false;
         break;
      case -2147221453:
         byte length = data.readByte();

         for(int i = length - 1; i >= 0; --i) {
            int id = data.readInt();
            byte faceId = data.readByte();

            for(int k = GOManager.curGameObj.length - 1; k >= 0; --k) {
               if (GOManager.curGameObj[k].getID() == id) {
                  OtherPlayer op = (OtherPlayer)GOManager.curGameObj[k];
                  op.faceId = faceId;
                  op.faceTime = 48;
                  break;
               }
            }
         }
      }

   }

   public static void setTurnTaskInfoLeftMenu() {
      UITitle title = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
      if (UIList.canReturn) {
         title.setMenuText("Hoàn thành", 0);
      } else {
         title.setMenuText("", 0);
      }

   }

   public static void setTakeTaskInfoLeftMenu() {
      UITitle title = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
      if (UIList.canReceive) {
         title.setMenuText("Tiếp nhận", 0);
      } else {
         title.setMenuText("", 0);
      }

   }
}

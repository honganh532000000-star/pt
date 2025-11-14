public class PCUI {
   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2147155967:
         if (UITitle.isEnter) {
            Map.isMapDetail = 1;
            UITitle.isEnter = false;
            SIChat.removeChatLocal();
            UIMenu.formSaveCommand();
         }

         MainCanvas.uiData = data.readByteArray(data.getLength() - 6);
         break;
      case -2147155966:
         MainCanvas.partUIUserData = data.readByteArray(data.getLength() - 6);
         MainCanvas.isWaiting = false;
         break;
      case -2147155965:
         String content = data.readUTF();
         NPC.topTipStrs = Util.colorChangeLine(content, NPC.topTipWidth, MainCanvas.curFont);
         NPC.topTipHeight = NPC.topTipStrs.length * (MainCanvas.CHARH + 2) + 8;
         int aveW = NPC.topTipWidth / 10;
         int aveH = NPC.topTipHeight / 10;

         for(int i = 0; i < 10; ++i) {
            NPC.topTipChangeWidth[i] = (i + 1) * aveW;
            NPC.topTipChangeHeight[i] = (i + 1) * aveH;
         }

         NPC.isDrawTopTip = true;
         break;
      case -2147151599:
         MainCanvas.popData = Util.addArray(MainCanvas.popData, MainCanvas.popData.length, data.readByteArray(data.getLength() - 6));
         MainCanvas.popLevel = Util.addArray(MainCanvas.popLevel, MainCanvas.popLevel.length, MainCanvas.popData[MainCanvas.popData.length - 1][0]);
         break;
      case -2147147230:
         byte num = data.readByte();
         UITopForm.driftStr = new String[num];

         for(int i = 0; i < num; ++i) {
            UITopForm.driftStr[i] = data.readUTF();
         }

         UITopForm.driftTime = data.readByte();
         UITopForm.driftSpecialColor = data.readInt();
         UITopForm.drawDriftCount = 0;
         UITopForm.driftColorIndex = 0;
         UITopForm.isDrawDrift = true;
         break;
      case -2147129754:
         UITopForm.removeAllTopForm();
         break;
      case -2147121016:
         UITopForm.removeCurTopForm();
         break;
      case -2147112278:
         MainCanvas.isWaiting = false;
         if (MainCanvas.curForm == null) {
            return;
         }

         if ((MainCanvas.curForm.clientCommand == 1900548 || MainCanvas.curForm.clientCommand == 1900546 || MainCanvas.curForm.clientCommand == 458753 || MainCanvas.curForm.clientCommand == 917507 || MainCanvas.curForm.clientCommand == 983041 || MainCanvas.curForm.clientCommand == 458760 || MainCanvas.curForm.clientCommand == 1179650 || MainCanvas.curForm.clientCommand == 1179658 || MainCanvas.curForm.clientCommand == 393230 || MainCanvas.curForm.clientCommand == 262187 || MainCanvas.curForm.clientCommand == 458771 || MainCanvas.curForm.clientCommand == 589825 || MainCanvas.curForm.clientCommand == 589828 || MainCanvas.curForm.clientCommand == 589833 || MainCanvas.curForm.clientCommand == 2424840 || MainCanvas.curForm.clientCommand == 2424833 || MainCanvas.curForm.clientCommand == 1966097 || MainCanvas.curForm.clientCommand == 589845 || MainCanvas.curForm.clientCommand == 589832 || MainCanvas.curForm.clientCommand == 2424835 || MainCanvas.curForm.clientCommand == 2425029 || MainCanvas.curForm.clientCommand == 393217 || MainCanvas.curForm.clientCommand == 2490390 || MainCanvas.curForm.clientCommand == 2490391) && MainCanvas.backForms.size() > 0) {
            MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
         }
         break;
      case -2147107909:
         MainCanvas.isWaiting = true;
         break;
      case -2147090433:
         MainCanvas.userData = data.readByteArray(data.getLength() - 6);
      }

   }
}

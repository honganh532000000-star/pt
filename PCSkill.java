import java.util.Vector;

public class PCSkill {
   public static boolean isStudySkill = false;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 1572865:
         MainCanvas.isWaiting = true;
         ba.writeInt(GOManager.getCurrentTarget().getID());
         break;
      case 1572866:
         ba.writeInt(GOManager.getCurrentTarget().getID());
         break;
      case 1572867:
         MainCanvas.isWaiting = true;
         isStudySkill = true;
         ba.writeInt(GOManager.getCurrentTarget().getID());
         UIList skillList = (UIList)MainCanvas.curForm.getComponents().elementAt(3);
         short skillIND = (short)skillList.getListIDByIndex(skillList.getSelectedIndex());
         Player.getInstance();
         ba.writeShort(Player.skillID[skillIND]);
         break;
      case 1572868:
         MainCanvas.isWaiting = true;
         break;
      case 1572869:
         ba.writeByte(Player.getInstance().talentTypeCount);

         for(byte i = 0; i < Player.getInstance().talentTypeCount; ++i) {
            ba.writeByte(Player.getInstance().talentCurPoint[i].length);
            ba.writeByteArray(Player.getInstance().talentCurPoint[i]);
         }
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      byte il;
      switch(commID) {
      case -2145910783:
         boolean dow = data.readBoolean();
         intoStudySkillUI(-1610612731);
         MainCanvas.setGameState((byte)4);
      case -2145910782:
      case -2145910779:
      case -2145910778:
      default:
         break;
      case -2145910781:
         long[] skda = new long[4];

         for(il = 0; il < 4; ++il) {
            skda[il] = data.readLong();
         }

         Player.getInstance().checkCanUseSkill(skda);
         SIManager.getInstance();
         SIManager.shortCut.refreshShortCutOption();
         if (isStudySkill) {
            isStudySkill = false;
            UIList studySkillList = (UIList)MainCanvas.curForm.getComponents().elementAt(3);
            int oldSize = studySkillList.getItems().size();
            int startIndex = studySkillList.startIndex;
            studySkillList.removeAll();
            studySkillList.startIndex = 0;
            studySkillList.setType((byte)3);
            int[] canStudySkill_IND = Player.getInstance().searchCanStudySkill();

            for(short i = 0; i < canStudySkill_IND.length; ++i) {
               StringBuffer var10002 = new StringBuffer();
               Player.getInstance();
               ListItem tempSkill = new ListItem(var10002.append(Player.skillName[canStudySkill_IND[i]]).toString(), (byte)8, (byte)0, studySkillList.getListWidth());
               Player.getInstance();
               tempSkill.setSkillImgInd(Player.skillImgInd[canStudySkill_IND[i]]);
               studySkillList.addItem(tempSkill);
            }

            int curSize = studySkillList.getItems().size();
            studySkillList.setListID(canStudySkill_IND);
            UIList.addUIScroll(studySkillList, (byte)0);
            if (oldSize > curSize) {
               studySkillList.startIndex = startIndex;
               if (studySkillList.startIndex >= curSize) {
                  --studySkillList.startIndex;
                  if (studySkillList.startIndex < 0) {
                     studySkillList.startIndex = 0;
                  }
               }

               if (studySkillList.getSelectedIndex() == (byte)curSize) {
                  studySkillList.setSelectedIndex((byte)(curSize - 1));
                  --studySkillList.startIndex;
                  if (studySkillList.startIndex < 0) {
                     studySkillList.startIndex = 0;
                  }
               }

               if (curSize - studySkillList.startIndex < studySkillList.getCanShowCol((byte)0)) {
                  --studySkillList.startIndex;
                  if (studySkillList.startIndex < 0) {
                     studySkillList.startIndex = 0;
                  }
               }
            } else if (oldSize == curSize) {
               studySkillList.startIndex = startIndex;
            }

            if (studySkillList.getPanel().getPanelVScroll() != null) {
               studySkillList.getPanel().getPanelVScroll().scrollPositionY((short)curSize, studySkillList.getCanShowCol((byte)0), (short)studySkillList.startIndex);
            }
         }
         break;
      case -2145910780:
         Player.getInstance().loadNPC_GeniusData(data);
         intoGeniusUI(524292);
         MainCanvas.setGameState((byte)4);
         break;
      case -2145910777:
         Player.getInstance().loadGenius_CurPoint(data);
         break;
      case -2145910776:
         il = data.readByte();
         Player.loadNPC_SKILLData(il, data);
         break;
      case -2145910775:
         boolean refresh = data.readBoolean();
         Player.loadSkillDes(data, refresh);
      }

      MainCanvas.isWaiting = false;
   }

   public static final void intoStudySkillUI(int command) {
      UIForm f1 = ParseUI.parseUI("/ui/learnskill.ui");
      UIForm f2 = ParseUI.parseUI("/ui/learnskill.ui");
      MainCanvas.curForm = f1;
      MainCanvas.curForm.clientCommand = command;
      MainCanvas.curFormVector.addElement(f1);
      MainCanvas.curFormVector.addElement(f2);
      UIList studySkillList = (UIList)MainCanvas.curForm.getComponents().elementAt(3);
      studySkillList.removeAll();
      studySkillList.setType((byte)3);
      int[] canStudySkill_IND = Player.getInstance().searchCanStudySkill();

      for(short i = 0; i < canStudySkill_IND.length; ++i) {
         StringBuffer var10002 = new StringBuffer();
         Player.getInstance();
         ListItem tempSkill = new ListItem(var10002.append(Player.skillName[canStudySkill_IND[i]]).toString(), (byte)8, (byte)0, studySkillList.getListWidth());
         Player.getInstance();
         tempSkill.setSkillImgInd(Player.skillImgInd[canStudySkill_IND[i]]);
         studySkillList.addItem(tempSkill);
      }

      studySkillList.setListID(canStudySkill_IND);
      UIList.addUIScroll(studySkillList, (byte)0);
      UIList studySkillList2 = (UIList)f2.getComponents().elementAt(3);
      studySkillList2.removeAll();
      studySkillList2.setType((byte)4);
      studySkillList2.addItem(new ListItem("Cấp 1-10", (byte)0, (byte)0, studySkillList2.getListWidth()));
      studySkillList2.addItem(new ListItem("Cấp 11-20", (byte)0, (byte)0, studySkillList2.getListWidth()));
      studySkillList2.addItem(new ListItem("Cấp 21-30", (byte)0, (byte)0, studySkillList2.getListWidth()));
      studySkillList2.addItem(new ListItem("Cấp 31-40", (byte)0, (byte)0, studySkillList2.getListWidth()));
      studySkillList2.addItem(new ListItem("Cấp 41-50", (byte)0, (byte)0, studySkillList2.getListWidth()));
      studySkillList2.addItem(new ListItem("Cấp 51-60", (byte)0, (byte)0, studySkillList2.getListWidth()));
      studySkillList2.addItem(new ListItem("Cấp 61-70", (byte)0, (byte)0, studySkillList2.getListWidth()));
      int[] li = new int[]{1, 2, 3, 4, 5, 6, 7};
      studySkillList2.setListID(li);
      UIList.addUIScroll(studySkillList2, (byte)0);
   }

   public static final void intoGeniusUI(int command) {
      Vector tempitems = new Vector();

      for(byte formi = 0; formi < Player.getInstance().talentTypeCount; ++formi) {
         UIForm tempForm = ParseUI.parseUI("/ui/geniustree.ui");
         if (formi == 0) {
            int i = 0;

            while(true) {
               if (i >= Player.getInstance().talentTypeCount) {
                  UILabel tempgeniusLabel = (UILabel)tempForm.getComponents().elementAt(6);
                  UILabel tLabel = (UILabel)tempForm.getComponents().elementAt(7);
                  Player.getInstance().areaPosition_Y = (short)(3 + tempgeniusLabel.getPositionY() + UILabel.getRimLabelHeight());
                  Player.getInstance().areaPosition_H = (short)(tLabel.getPositionY() - Player.getInstance().areaPosition_Y);
                  break;
               }

               tempitems.addElement(Player.getInstance().talentTypeName[i] + "Thiên phú");

               for(int z = 0; z < Player.getInstance().talentAddPoint[i].length; ++z) {
                  Player.getInstance().talentAddPoint[i][z] = 0;
               }

               ++i;
            }
         }

         tempForm.clientCommand = command;
         UIFolder tempgeniusFolder = (UIFolder)tempForm.getComponents().elementAt(4);
         tempgeniusFolder.setItems(tempitems);
         MainCanvas.curFormVector.addElement(tempForm);
         UITitle geniusTitle = (UITitle)tempForm.getComponents().elementAt(0);
         geniusTitle.setCanFocus(true);
         geniusTitle.setFocus(true, tempForm);
         geniusTitle.setAroundFocusNull();
         UILabel geniusLabel = (UILabel)tempForm.getComponents().elementAt(6);
         Player.getInstance().areaPosition_X = geniusLabel.getPositionX();
         Player.getInstance().areaPosition_W = geniusLabel.getWidth();
         int talentid = Player.getInstance().getIND_FromTalentData(formi, Player.getInstance().selTalent[formi]);
         if (talentid == -1) {
            talentid = 0;
         }

         String tn = Player.getInstance().talentName[formi][talentid];
         tempForm.addWarningStr(Player.getInstance().talentCon[formi][talentid]);
         geniusLabel.setText(tn);
         geniusLabel = (UILabel)tempForm.getComponents().elementAt(8);
         geniusLabel.setText(String.valueOf(Player.getInstance().remaindTalentPoint));
         byte tp = Player.getInstance().talentPosition[formi][Player.getInstance().talentPosition[formi].length - 1];
         geniusTitle.allTalentRows = (byte)((tp - 1) / 3 + 1);
         geniusTitle.onePageTalentRows = (byte)(Player.getInstance().areaPosition_H / 27);
         geniusTitle.curTalentRow = 0;
         short trackLength = (short)(Player.getInstance().areaPosition_H + MainCanvas.ui_3Img.getHeight() * 2);
         short moveLength = (short)(trackLength - MainCanvas.ui_2Img.getHeight());
         geniusTitle.moveStep = (short)(moveLength / (geniusTitle.allTalentRows - geniusTitle.onePageTalentRows + 2));
         geniusTitle.slipperEndY = (short)(Player.getInstance().areaPosition_Y + 3 + MainCanvas.ui_3Img.getHeight() + moveLength);
         geniusTitle.titleScroll = new UIScroll((short)(Player.getInstance().areaPosition_X + Player.getInstance().areaPosition_W - MainCanvas.ui_3Img.getWidth() - 5), (short)(Player.getInstance().areaPosition_Y + 3), (short)0, moveLength, (byte)0, true, (UIForm)null);
         geniusTitle.titleScroll.scrollPositionY(geniusTitle.allTalentRows, geniusTitle.onePageTalentRows, geniusTitle.curTalentRow);
      }

      MainCanvas.curForm = (UIForm)MainCanvas.curFormVector.elementAt(0);
      MainCanvas.curForm.addWarningStr();
   }
}

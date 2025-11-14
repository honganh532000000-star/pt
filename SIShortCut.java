import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class SIShortCut {
   public static final byte SHORTCUT_TYPE_NONE = 0;
   public static final byte SHORTCUT_TYPE_SKILL = 1;
   public static final byte SHORTCUT_TYPE_GOODS = 2;
   public static int shortCutId = -1;
   public static final int SHORTCUT_COMMAND_CLEAR = -268435455;
   public static final int SHORTCUT_COMMAND_CLEAR_ALL = -268435454;
   public static final int SHORTCUT_COMMAND_VIEW = -268435453;
   public static final int SHORTCUT_COMMAND_SKILL = -268435451;
   public static final int SHORTCUT_COMMAND_GOODS = -268435450;
   public static final int SHORTCUT_COMMAND_KEY = -268435449;
   public static final int BUFF_HEIGHT = 36;
   public static final int D_SPACE = 3;
   private static final int D_CENTER = 1;
   private static final int EX_BAR_HALF = 5;
   private static final int EX_BAR_TOTAL = 10;
   private static final int EX_BAR_DY = 12;
   public static final int EX_BAR_HEIGHT = 4;
   private static final int EX_DY = 13;
   private static final int EX_HEIGHT = 2;
   private static int exWidth;
   public static final int SHORTCUT_FRAME_NUM = 4;
   public static final int SHORTCUT_FRAME_WIDTH = 16;
   public static final int SHORTCUT_FRAME_HEIGHT = 16;
   public Image buff = null;
   private static Graphics gBuff = null;
   byte jigcount = 0;
   byte dw = 0;
   int drawWidth = 0;
   int exFrameRightX = 0;
   public static int shortCutFrameDy = 0;
   static int shortCutFrameSpace = 0;
   public static int shortCutRightX = 0;
   public static final int SHORTCUT_NUM_MAX = 8;
   public static final int SHORTCUT_GROUP_MAX = 3;
   public static final int SHORTCUT_INT_GROUP_NUM = 4;
   public static final int SHORTCUT_SAVE_NUM = 97;
   public static final int SHORTCUT_NAME_SAVE_NUM = 24;
   public static final byte SHORTCUT_GROUP_DEFAULT_NUM = 2;
   public byte[][] curShortCutType = null;
   public int[][] curShortCutSelfIndex = null;
   public short[][] curShortCutImageQuality = null;
   public byte[][] curShortCutColorLevel = null;
   public String[][] curShortCutName = null;
   public boolean[][] curShortCutCD = null;
   public byte curShortCutGroup = 0;
   public byte curShortCutGroupNum;

   public SIShortCut() {
      this.initBuff();
   }

   public static void clear() {
   }

   public final void initBuff() {
      this.buff = Image.createImage(MainCanvas.screenW, 36);
      gBuff = this.buff.getGraphics();
      Util.fillRect(gBuff, 0, 0, this.buff.getWidth(), this.buff.getHeight(), Cons.COLOR_SKILL_BACKGROUND);
      Util.drawRect(gBuff, 0, 0, this.buff.getWidth(), this.buff.getHeight(), Cons.COLOR_SKILL_BORDER_1);
      Util.drawRect(gBuff, 1, 1, this.buff.getWidth() - 2, this.buff.getHeight() - 2, Cons.COLOR_SKILL_BORDER_2);
      this.drawWidth = (this.buff.getWidth() >> 1) - MainCanvas.si_caoImg.getWidth() - 3 - 1 - 1;
      int huaNum = this.drawWidth / MainCanvas.si_huaImg.getWidth();
      int huaRightX = this.buff.getWidth() - 3 - MainCanvas.si_huaImg.getWidth();
      int D_HUA_X = 0;

      int tmpExLength;
      for(tmpExLength = 0; tmpExLength < huaNum; ++tmpExLength) {
         Util.drawImage(gBuff, MainCanvas.si_huaImg, D_HUA_X + 3 + tmpExLength * (MainCanvas.si_huaImg.getWidth() + 1), 3, 0);
         Util.drawImage(gBuff, MainCanvas.si_huaImg, -D_HUA_X + huaRightX - tmpExLength * (MainCanvas.si_huaImg.getWidth() + 1), 3, 0);
      }

      tmpExLength = this.drawWidth / 5;
      int totalLength = tmpExLength * 5 + 1;
      int exValue = totalLength - 2;
      this.exFrameRightX = MainCanvas.screenW - 3 - totalLength;
      Util.fillRect(gBuff, 4, 13, exValue, 2, Cons.COLOR_SKILL_PANEL);
      Util.fillRect(gBuff, this.exFrameRightX + 1, 13, exValue, 2, Cons.COLOR_SKILL_PANEL);
      Util.drawRect(gBuff, 3, 12, totalLength, 4, Cons.COLOR_SKILL_FRAME);
      Util.drawRect(gBuff, this.exFrameRightX, 12, totalLength, 4, Cons.COLOR_SKILL_FRAME);
      exWidth = tmpExLength - 1;

      int i;
      for(i = 1; i < 5; ++i) {
         gBuff.drawLine(3 + tmpExLength * i, 13, 3 + tmpExLength * i, 15);
         gBuff.drawLine(this.exFrameRightX + tmpExLength * i, 13, this.exFrameRightX + tmpExLength * i, 15);
      }

      shortCutFrameDy = this.buff.getHeight() - 3 - 16;
      shortCutFrameSpace = (this.drawWidth + 1 - 64) / 4;
      shortCutRightX = this.buff.getWidth() - 3 - 16;
      if (MainCanvas.isLargeScreen) {
         for(i = 0; i < 4; ++i) {
            Util.drawImage(gBuff, MainCanvas.si_huaImg, 19 + (shortCutFrameSpace - MainCanvas.si_huaImg.getHeight() >> 1) + i * (shortCutFrameSpace + 16), shortCutFrameDy + (16 - MainCanvas.si_huaImg.getWidth() >> 1), 6);
            Util.drawImage(gBuff, MainCanvas.si_huaImg, shortCutRightX - shortCutFrameSpace + (shortCutFrameSpace - MainCanvas.si_huaImg.getHeight() >> 1) - i * (shortCutFrameSpace + 16), shortCutFrameDy + (16 - MainCanvas.si_huaImg.getWidth() >> 1), 6);
         }
      }

      Util.drawImage(gBuff, MainCanvas.si_caoImg, (this.buff.getWidth() >> 1) - MainCanvas.si_caoImg.getWidth(), 33 - MainCanvas.si_caoImg.getHeight() + 1, 0);
      Util.drawImage(gBuff, MainCanvas.si_caoImg, this.buff.getWidth() >> 1, 33 - MainCanvas.si_caoImg.getHeight() + 1, 2);
      this.refreshShortCutAll();
   }

   public void setCD(byte type, int id, boolean isCD) {
      for(int i = 0; i < this.curShortCutGroupNum; ++i) {
         for(int j = 0; j < 8; ++j) {
            if (this.curShortCutType[i][j] == type && this.curShortCutSelfIndex[i][j] == id) {
               this.curShortCutCD[i][j] = isCD;
            }
         }
      }

   }

   public void removeGoods(int id) {
      boolean isChange = false;

      for(int i = 0; i < this.curShortCutGroupNum; ++i) {
         for(int j = 0; j < 8; ++j) {
            if (this.curShortCutType[i][j] == 2 && this.curShortCutSelfIndex[i][j] == id) {
               this.curShortCutType[i][j] = 0;
               this.curShortCutSelfIndex[i][j] = -1;
               this.curShortCutImageQuality[i][j] = -1;
               this.curShortCutColorLevel[i][j] = -1;
               this.curShortCutName[i][j] = "";
               this.curShortCutCD[i][j] = false;
               isChange = true;
            }
         }
      }

      if (isChange) {
         MainCanvas.ni.send(196633);
         this.refreshShortCutAll();
      }
   }

   public final void refreshShortCutAll() {
      for(byte i = 0; i < 8; ++i) {
         this.refreshShortCutOne(i);
      }

   }

   public final void refreshShortCutOne(byte index) {
      if (this.curShortCutImageQuality != null) {
         short imgIndex = getImgId(this.curShortCutImageQuality[this.curShortCutGroup][index]);
         byte quality = getQuality(this.curShortCutImageQuality[this.curShortCutGroup][index]);
         int dx;
         if (index < 4) {
            dx = 3 + index * (shortCutFrameSpace + 16);
            if (this.curShortCutType[this.curShortCutGroup][index] > 0 && imgIndex >= 0) {
               Util.fillRect(gBuff, dx + 1, shortCutFrameDy + 1, 14, 14, Cons.COLOR_GRID_FRAME_BG[quality][0]);
               MainCanvas.stuffMImg.draw(gBuff, dx + 1, shortCutFrameDy + 1, imgIndex);
               Util.drawRect(gBuff, dx, shortCutFrameDy, 16, 16, Cons.COLOR_GRID_FRAME_BG[quality][1]);
               if (this.curShortCutCD[this.curShortCutGroup][index]) {
                  gBuff.drawImage(MainCanvas.ui_icon_shadow, dx + 1, shortCutFrameDy + 1, 0);
               }
            } else {
               Util.fillRect(gBuff, dx + 1, shortCutFrameDy + 1, 14, 14, Cons.COLOR_SKILL_PANEL);
               Util.drawRect(gBuff, dx, shortCutFrameDy, 16, 16, Cons.COLOR_SKILL_FRAME);
            }

            MainCanvas.ui_numMImg.draw(gBuff, dx, shortCutFrameDy, index + 1);
         } else {
            dx = shortCutRightX - (7 - index) * (shortCutFrameSpace + 16);
            if (this.curShortCutType[this.curShortCutGroup][index] > 0 && imgIndex >= 0) {
               Util.fillRect(gBuff, dx + 1, shortCutFrameDy + 1, 14, 14, Cons.COLOR_GRID_FRAME_BG[quality][0]);
               MainCanvas.stuffMImg.draw(gBuff, dx + 1, shortCutFrameDy + 1, imgIndex);
               Util.drawRect(gBuff, dx, shortCutFrameDy, 16, 16, Cons.COLOR_GRID_FRAME_BG[quality][1]);
               if (this.curShortCutCD[this.curShortCutGroup][index]) {
                  gBuff.drawImage(MainCanvas.ui_icon_shadow, dx + 1, shortCutFrameDy + 1, 0);
               }
            } else {
               Util.fillRect(gBuff, dx + 1, shortCutFrameDy + 1, 14, 14, Cons.COLOR_SKILL_PANEL);
               Util.drawRect(gBuff, dx, shortCutFrameDy, 16, 16, Cons.COLOR_SKILL_FRAME);
            }

            MainCanvas.ui_numMImg.draw(gBuff, dx, shortCutFrameDy, 8 - (7 - index));
         }

      }
   }

   public final void draw(Graphics g) {
      int dx = 0;
      int dy = MainCanvas.screenH - this.buff.getHeight();
      g.drawImage(this.buff, dx, dy, 20);
      MainCanvas.newui_lvNumMImg.draw(g, dx + (this.buff.getWidth() >> 1) - MainCanvas.newui_lvNumMImg.frame_w, dy + 4 + (MainCanvas.si_HPMPImg.getHeight() >> 1) - (MainCanvas.newui_lvNumMImg.frame_h >> 1), Player.getInstance().getLevel() / 10);
      MainCanvas.newui_lvNumMImg.draw(g, dx + (this.buff.getWidth() >> 1), dy + 4 + (MainCanvas.si_HPMPImg.getHeight() >> 1) - (MainCanvas.newui_lvNumMImg.frame_h >> 1), Player.getInstance().getLevel() % 10);
      if (Player.getInstance().isFight) {
         this.dw = (byte)(this.dw ^ 1);
         Util.drawLevelUpImg(g, this.buff.getWidth() >> 1, MainCanvas.si_caoImg.getWidth() >> 1, dy + 2, this.dw + 2);
      } else {
         byte isP = 0;
         if (Player.getInstance().isPVP) {
            isP = 4;
         }

         if (Player.getInstance().getCurrentEXP() >= Player.getInstance().getMAXEXP()) {
            if (this.jigcount >= 31 && this.jigcount <= 36) {
               this.dw = (byte)(this.dw ^ 1);
               ++this.jigcount;
            } else if (this.jigcount > 36) {
               this.dw = 0;
               this.jigcount = 0;
            } else {
               ++this.jigcount;
               this.dw = (byte)(this.jigcount % 31 == 0 ? 1 : 0);
            }
         }

         Util.drawLevelUpImg(g, this.buff.getWidth() >> 1, MainCanvas.si_caoImg.getWidth() >> 1, dy + 2, this.dw + isP);
      }

      int tmpWidth;
      int tmpNum;
      if (Player.getInstance().getMAXHP() != 0) {
         tmpNum = MainCanvas.si_HPMPImg.getHeight() * Player.getInstance().getCurrentHP() / Player.getInstance().getMAXHP();
         tmpWidth = MainCanvas.si_HPMPImg.getHeight() - tmpNum;
         Util.drawRegion(g, MainCanvas.si_HPMPImg, 0, tmpWidth, MainCanvas.si_HPMPImg.getWidth() >> 1, tmpNum, dx + (this.buff.getWidth() >> 1) - MainCanvas.si_caoImg.getWidth() + 2, dy + 36 - 3 - MainCanvas.si_caoImg.getHeight() + tmpWidth + 2);
         if (Player.getInstance().getMAXMP() != 0) {
            int MPLength = MainCanvas.si_HPMPImg.getHeight() * Player.getInstance().getCurrentMP() / Player.getInstance().getMAXMP();
            int MPDY = MainCanvas.si_HPMPImg.getHeight() - MPLength;
            Util.drawRegion(g, MainCanvas.si_HPMPImg, MainCanvas.si_HPMPImg.getWidth() >> 1, MPDY, (MainCanvas.si_HPMPImg.getWidth() >> 1) + 1, MPLength, dx + (this.buff.getWidth() >> 1) + 1 - 1, dy + 36 - 3 - MainCanvas.si_caoImg.getHeight() + MPDY + 2);
         }

         g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
      }

      if (Player.getInstance().getMAXEXP() > 0L) {
         tmpNum = (int)(Player.getInstance().getCurrentEXP() * 10L / Player.getInstance().getMAXEXP());
         if (tmpNum > 10) {
            tmpNum = 10;
         }

         for(tmpWidth = 0; tmpWidth < tmpNum; ++tmpWidth) {
            if (tmpWidth < 10) {
               Util.fillRect(g, tmpWidth < 5 ? dx + 3 + 1 + (exWidth + 1) * tmpWidth : dx + this.exFrameRightX + 1 + (exWidth + 1) * (tmpWidth - 5), dy + 13, exWidth, 2, Cons.COLOR_PLAYER_EXPERIENCE);
            }
         }

         if (tmpNum < 10) {
            tmpWidth = (int)((Player.getInstance().getCurrentEXP() * 10L - (long)tmpNum * Player.getInstance().getMAXEXP()) * (long)exWidth / Player.getInstance().getMAXEXP());
            Util.fillRect(g, tmpNum < 5 ? dx + 3 + 1 + (exWidth + 1) * tmpNum : dx + this.exFrameRightX + 1 + (exWidth + 1) * (tmpNum - 5), dy + 13, tmpWidth, 2, Cons.COLOR_PLAYER_EXPERIENCE);
         }
      }

      this.drawBuff(g, dy + 12 - MainCanvas.si_huaImg.getHeight() - 2);
   }

   public final void drawBuff(Graphics g, int y) {
      for(byte i = 0; i < MainCanvas.buffImg.cols; ++i) {
         if (Player.getInstance().buffTYPE[i] != 0) {
            if (i < 6) {
               MainCanvas.buffImg.draw(g, 3 + i * 10, y, Player.getInstance().buffTYPE[i] - 1);
            } else {
               MainCanvas.buffImg.draw(g, (this.buff.getWidth() >> 1) + 1 + MainCanvas.si_caoImg.getWidth() + 1 + (i - 6) * 10, y, Player.getInstance().buffTYPE[i] - 1);
            }
         }
      }

   }

   public final void changeGroup() {
      ++this.curShortCutGroup;
      if (this.curShortCutGroup >= this.curShortCutGroupNum) {
         this.curShortCutGroup = 0;
      }

      this.refreshShortCutAll();
   }

   public final void initUIFormGridState(byte groupNum) {
      for(int i = 0; i < 3; ++i) {
         UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(3 + i);
         grid.setClientType((short)6);
         grid.setCanUseNum((short)8);
         int j;
         if (i < groupNum) {
            for(j = 0; j < grid.getCanUseNum(); ++j) {
               grid.setItemType(j, this.curShortCutType[i][j]);
               if (this.curShortCutType[i][j] > 0) {
                  grid.setItemID(j, this.curShortCutSelfIndex[i][j]);
                  grid.setItemImgID(j, getImgId(this.curShortCutImageQuality[i][j]));
                  grid.setItemQuality(j, getQuality(this.curShortCutImageQuality[i][j]));
                  grid.setItemColorLevel(j, this.curShortCutColorLevel[i][j]);
                  grid.setItemName(j, this.curShortCutName[i][j]);
                  grid.setItemCD(j, this.curShortCutCD[i][j]);
                  grid.stuffImgQuality[j] = this.curShortCutImageQuality[i][j];
               } else {
                  grid.setItemID(j, -1);
                  grid.setItemImgID(j, (short)-1);
                  grid.setItemQuality(j, (byte)-1);
                  grid.setItemName(j, "");
                  grid.setItemCD(j, false);
               }
            }
         } else {
            for(j = 0; j < grid.getCanUseNum(); ++j) {
               this.clearOneGrid(grid, j);
            }
         }

         if (i < groupNum) {
            grid.canUseNum = 8;
            grid.setCanFocus(true);
         } else {
            grid.canUseNum = 0;
            grid.setCanFocus(false);
         }

         grid.getTitleLabel().setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
      }

   }

   public final void changeUIFormGridState(byte groupNum) {
      for(int i = 0; i < 3; ++i) {
         UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(3 + i);
         if (i < groupNum) {
            grid.canUseNum = 8;
            grid.setCanFocus(true);
         } else {
            grid.canUseNum = 0;
            grid.setCanFocus(false);
         }
      }

   }

   public void saveShortCutOption() {
      UIRadio radio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(6);
      this.curShortCutGroupNum = (byte)(radio.getSelectIndex() + 1);
      this.curShortCutType = new byte[this.curShortCutGroupNum][8];
      this.curShortCutSelfIndex = new int[this.curShortCutGroupNum][8];
      this.curShortCutImageQuality = new short[this.curShortCutGroupNum][8];
      this.curShortCutName = new String[this.curShortCutGroupNum][8];
      this.curShortCutColorLevel = new byte[this.curShortCutGroupNum][8];
      this.curShortCutCD = new boolean[this.curShortCutGroupNum][8];

      for(int i = 0; i < this.curShortCutGroupNum; ++i) {
         UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(3 + i);

         for(int j = 0; j < 8; ++j) {
            this.curShortCutType[i][j] = (byte)grid.getItemType(j);
            this.curShortCutSelfIndex[i][j] = grid.getItemID(j);
            this.curShortCutImageQuality[i][j] = grid.stuffImgQuality[j];
            this.curShortCutName[i][j] = grid.getItemName(j);
            this.curShortCutColorLevel[i][j] = grid.getItemColorLevel(j);
            if (grid.getItemType(j) == 1) {
               short index = Player.getInstance().getIND_FromSkillData(grid.getItemID(j));
               boolean[] var10000 = this.curShortCutCD[i];
               Player.getInstance();
               var10000[j] = Player.CD_Count[index] > 0L;
            } else {
               this.curShortCutCD[i][j] = grid.getItemCD(j);
            }
         }
      }

      if (this.curShortCutGroup >= this.curShortCutGroupNum) {
         this.curShortCutGroup = 0;
      }

      MainCanvas.ni.send(196633);
      this.refreshShortCutAll();
   }

   public void clearAllGrid() {
      for(int i = 0; i < this.curShortCutGroupNum; ++i) {
         UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(3 + i);

         for(int k = 0; k < grid.getCanUseNum(); ++k) {
            this.clearOneGrid(grid, k);
         }
      }

   }

   public void clearOneGrid(UIGrid grid, int k) {
      grid.setItemType(k, (short)-1);
      grid.setItemID(k, -1);
      grid.setItemImgID(k, (short)-1);
      grid.setItemQuality(k, (byte)-1);
      grid.setItemName(k, "");
   }

   public static void setSkill() {
      MainCanvas.curForm.foldedIndex = 1;
      if (MainCanvas.curFormVector.elementAt(1) == null) {
         UIRightMenu.intoSkillBarUI(-1610612732);
         MainCanvas.curFormVector.setElementAt(MainCanvas.curForm, 1);
      } else {
         MainCanvas.curForm = (UIForm)MainCanvas.curFormVector.elementAt(1);
         MainCanvas.curForm.addWarningStr();
      }

      MainCanvas.backForms.addElement(new Byte((byte)0));
   }

   public static void setGoods() {
      MainCanvas.curForm.foldedIndex = 2;
      if (MainCanvas.curFormVector.elementAt(2) == null) {
         MainCanvas.isWaiting = true;
         MainCanvas.replaceFormId = 2;
         MainCanvas.ni.send(458761);
      } else {
         MainCanvas.curForm = (UIForm)MainCanvas.curFormVector.elementAt(2);
         MainCanvas.curForm.addWarningStr();
      }

      MainCanvas.backForms.addElement(new Byte((byte)0));
   }

   public void initShortCut() {
      this.curShortCutGroupNum = 2;
      this.curShortCutType = new byte[this.curShortCutGroupNum][8];
      this.curShortCutSelfIndex = new int[this.curShortCutGroupNum][8];
      this.curShortCutImageQuality = new short[this.curShortCutGroupNum][8];
      this.curShortCutName = new String[this.curShortCutGroupNum][8];
      this.curShortCutColorLevel = new byte[this.curShortCutGroupNum][8];
      this.curShortCutCD = new boolean[this.curShortCutGroupNum][8];

      int k;
      for(k = 0; k < this.curShortCutGroupNum; ++k) {
         for(int j = 0; j < 8; ++j) {
            this.curShortCutSelfIndex[k][j] = -1;
         }
      }

      int k = 0;
      this.curShortCutType[0][k] = 1;
      this.curShortCutSelfIndex[0][k] = 0;
      this.curShortCutImageQuality[0][k] = 1000;
      this.curShortCutName[0][k] = "Tấn công thường";
      this.curShortCutColorLevel[0][k] = 1;
      k = k + 1;

      for(short cu = 0; cu < Player.getInstance().getCanUseSkillCount(); ++cu) {
         short ind = Player.getInstance().getIND_FromSkillData(Player.getInstance().canUseSkillID[cu]);
         if (ind != -1) {
            this.curShortCutType[0][k] = 1;
            int[] var10000 = this.curShortCutSelfIndex[0];
            Player.getInstance();
            var10000[k] = Player.skillID[ind];
            short[] var6 = this.curShortCutImageQuality[0];
            Player.getInstance();
            var6[k] = Player.skillImgInd[ind];
            String[] var7 = this.curShortCutName[0];
            Player.getInstance();
            var7[k] = Player.skillName[ind];
            this.curShortCutColorLevel[0][k] = 1;
            ++k;
            if (k >= 8) {
               break;
            }
         }
      }

      MainCanvas.ni.send(196633);
      SIManager.getInstance();
      SIManager.shortCut.refreshShortCutAll();
   }

   public final void refreshShortCutOption() {
      int offP = '\ufff0';

      for(int i = 0; i < this.curShortCutGroupNum; ++i) {
         for(int j = 0; j < 8; ++j) {
            if (this.curShortCutType[i][j] == 1) {
               for(short cu = 0; cu < Player.getInstance().getCanUseSkillCount(); ++cu) {
                  if ((Player.getInstance().canUseSkillID[cu] & offP) == (this.curShortCutSelfIndex[i][j] & offP)) {
                     short ind = Player.getInstance().getIND_FromSkillData(Player.getInstance().canUseSkillID[cu]);
                     int[] var10000 = this.curShortCutSelfIndex[i];
                     Player.getInstance();
                     var10000[j] = Player.skillID[ind];
                     byte[] var6 = this.curShortCutColorLevel[i];
                     Player.getInstance();
                     var6[j] = (byte)(Player.skillImgInd[ind] / 1000 - 1);
                     String[] var7 = this.curShortCutName[i];
                     Player.getInstance();
                     var7[j] = Player.skillName[ind];
                     break;
                  }
               }
            }
         }
      }

      MainCanvas.ni.send(196633);
      this.refreshShortCutAll();
   }

   public final String enShortCutStr() {
      String str = String.valueOf(this.curShortCutGroupNum);

      for(int i = 0; i < this.curShortCutGroupNum; ++i) {
         for(int j = 0; j < 8; ++j) {
            str = str + "," + this.curShortCutSelfIndex[i][j];
         }
      }

      return str;
   }

   public final void deShortCutStr(String str) {
      try {
         String[] tmpStr = Util.splitToken(str, ',');

         try {
            this.curShortCutGroupNum = Byte.parseByte(tmpStr[0]);
         } catch (Exception var8) {
            this.initShortCut();
            return;
         }

         if (this.curShortCutGroupNum <= 0) {
            this.initShortCut();
            return;
         }

         this.curShortCutGroupNum = Byte.parseByte(tmpStr[0]);
         this.curShortCutType = new byte[this.curShortCutGroupNum][8];
         this.curShortCutSelfIndex = new int[this.curShortCutGroupNum][8];
         this.curShortCutImageQuality = new short[this.curShortCutGroupNum][8];
         this.curShortCutName = new String[this.curShortCutGroupNum][8];
         this.curShortCutColorLevel = new byte[this.curShortCutGroupNum][8];
         this.curShortCutCD = new boolean[this.curShortCutGroupNum][8];
         if (this.curShortCutGroupNum <= 0) {
            this.initShortCut();
            return;
         }

         boolean haveGoods = false;
         int k = 1;

         for(int i = 0; i < this.curShortCutGroupNum; ++i) {
            for(int j = 0; j < 8; ++j) {
               this.curShortCutSelfIndex[i][j] = Integer.parseInt(tmpStr[k]);
               if (this.curShortCutSelfIndex[i][j] < 0) {
                  this.curShortCutType[i][j] = 0;
                  this.curShortCutImageQuality[i][j] = -1;
                  this.curShortCutName[i][j] = "";
                  this.curShortCutColorLevel[i][j] = -1;
               } else if (tmpStr[k].length() <= 5) {
                  this.curShortCutType[i][j] = 1;
                  if (this.curShortCutSelfIndex[i][j] == 0) {
                     this.curShortCutImageQuality[i][j] = 1000;
                     this.curShortCutName[i][j] = "Tấn công thường";
                     this.curShortCutColorLevel[i][j] = 1;
                  } else {
                     int ind = Player.getInstance().getIND_FromSkillData(this.curShortCutSelfIndex[i][j]);
                     short[] var10000 = this.curShortCutImageQuality[i];
                     Player.getInstance();
                     var10000[j] = Player.skillImgInd[ind];
                     String[] var10 = this.curShortCutName[i];
                     Player.getInstance();
                     var10[j] = Player.skillName[ind];
                     this.curShortCutColorLevel[i][j] = 1;
                  }
               } else {
                  this.curShortCutType[i][j] = 2;
                  this.curShortCutImageQuality[i][j] = -1;
                  this.curShortCutName[i][j] = "";
                  this.curShortCutColorLevel[i][j] = -1;
                  haveGoods = true;
               }

               ++k;
            }
         }

         if (haveGoods) {
            MainCanvas.ni.send(196634);
         }

         this.refreshShortCutAll();
      } catch (Exception var9) {
         this.initShortCut();
      }

   }

   public final int[] enGoods() {
      int[] goods = new int[0];

      for(int i = 0; i < this.curShortCutGroupNum; ++i) {
         for(int j = 0; j < 8; ++j) {
            if (this.curShortCutType[i][j] == 2) {
               goods = Util.addArray(goods, goods.length, this.curShortCutSelfIndex[i][j]);
            }
         }
      }

      return goods;
   }

   public final void deGoods(int id, short imgId, String name, byte quality, int CD) {
      for(int i = 0; i < this.curShortCutGroupNum; ++i) {
         for(int j = 0; j < 8; ++j) {
            if (this.curShortCutSelfIndex[i][j] == id) {
               this.curShortCutImageQuality[i][j] = imgId;
               this.curShortCutName[i][j] = name;
               this.curShortCutColorLevel[i][j] = quality;
               this.curShortCutCD[i][j] = CD != 0;
            }
         }
      }

   }

   public static final short getImgId(short index) {
      return (short)(index % 1000);
   }

   public static final byte getQuality(short index) {
      return (byte)(index / 1000 - 1);
   }
}

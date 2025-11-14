import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class Player extends OtherPlayer {
   public static int busyId = 0;
   public static Player instance = null;
   public static byte mouseTimer = 0;
   public static short mouseDrawX = 0;
   public static short mouseDrawY = 0;
   private byte loverState = 0;
   private int loverId = 0;
   private String loverName = "";
   private int clanId = 0;
   private byte clanRight = 0;
   private int kingId = 0;
   private byte kingRight = 0;
   long curEXP = 0L;
   long maxEXP = 0L;
   public int playerDuel = 0;
   public int m_nPersonalEnemy = 0;
   private byte skillLimit = 0;
   public boolean isFight = false;
   public int skill_OBJID = 0;
   public static final byte SKILL_LIMIT_COUNT = 5;
   public static final int OTHER_PLAYER_OPERATE_LOOK = -268435456;
   public static final int OTHER_PLAYER_OPERATE_CHAT = -268435452;
   public static final int C_UI_PLAYER_OTHER_PLAYER_SHOWFRIEND = -268435451;
   public static final int C_UI_PLAYER_OTHER_PLAYER_SHOWDEFY = -268435450;
   public byte showActionIndex = 0;
   public static final short SKILLCOUNT_CANHOLD_MAX = 63;
   public static byte skillP = 0;
   public static int[] canUseSkill = new int[63];
   public static short[] skillID = null;
   public static String[] skillName = null;
   public static short[] skillEP = null;
   public static short[] skilltimer = null;
   public static int[] skillCD = null;
   public static String[] skillCon = null;
   public static short[] skillImgInd = null;
   public static byte[] skillTar = null;
   public static short[] skillAr = null;
   public static short[] skillDis = null;
   public static short[] needSkillLevel = null;
   public static byte[] needPriceType = null;
   public static int[] needPriceCount = null;
   public static byte[] needPlayerLevel = null;
   public static byte[] isFinality = null;
   public static short[] playershape = null;
   public static long[] CD_Count = null;
   public static final short TALENTCOUNT_COLUMN_MAX = 3;
   public static final short PERTALENTDIS = 27;
   public static final short TALENTCOUNT_BASE_POINT = 5;
   public short areaPosition_X = 0;
   public short areaPosition_Y = 0;
   public short areaPosition_W = 0;
   public short areaPosition_H = 0;
   public byte talentTypeCount = 0;
   public String[] talentTypeName = null;
   public byte[][] talentPosition = null;
   public String[][] talentName = null;
   public short[][] talentImgInd = null;
   public String[][] talentCon = null;
   public byte[][] talentSuperPosition = null;
   public boolean[][] talentIsOpen = null;
   public byte[][] talentMaxPoint = null;
   public byte[][] talentCurPoint = null;
   public byte[][] talentAddPoint = null;
   public byte remaindTalentPoint = 0;
   public short[] usedPoint = null;
   public byte[] selTalent = null;
   public byte[] maxTalentPosition = null;
   public static Vector canUseS = new Vector();
   public boolean isInitShortCut = false;
   public byte canUseSkillCount = 0;
   public short[] canUseSkillID = new short[0];
   public short selStudySkillID = 0;
   public short COLLECTION_TIME = 6000;
   public short FALG_TIME = 2000;
   public static int ATTACK_ROUND_SQUARE = 1000;
   public static int ACTION_ROUND_NPC_SQUARE = 3600;
   public static int ACTION_ROUND_MONSTER_SQUARE = 8100;
   public static int RETURN_NPC_ROUND_SQUARE;
   public static int RETURN_MONSTER_ROUND_SQUARE;
   public static int TARGET_NPC_ROUND_SQUARE;
   public static int TARGET_MONSTER_ROUND_SQUARE;
   public boolean isAttacked = false;
   public boolean isCollection = true;
   public static int[] LEVEL_UP_SPECIAL;
   public boolean m_bUpgrade;
   static String[] tc;
   long st = 0L;
   static int cheatTime;
   static int totalTime;
   short pingtime = 200;
   short baqiCount;
   byte isGettingFlag = 0;
   static boolean isAttacking;
   public static final byte SKILL_TARGET_SELF = 1;
   public static final byte SKILL_TARGET_ENEMY = 2;
   public static final byte SKILL_TARGET_CAMP = 3;
   public static final byte SKILL_TARGET_NONE = 4;
   private static final int TARGET_TYPE_ALL = 0;
   private static final int TARGET_TYPE_PLAYER = 1;
   private static final int TARGET_TYPE_FRIEND = 2;
   private static final int TARGET_TYPE_ENEMY = 3;
   public static final short SKILL_LEN = 50;
   public static final short SKILL_HI = 3;

   static {
      RETURN_NPC_ROUND_SQUARE = ACTION_ROUND_NPC_SQUARE;
      RETURN_MONSTER_ROUND_SQUARE = ACTION_ROUND_MONSTER_SQUARE;
      TARGET_NPC_ROUND_SQUARE = RETURN_NPC_ROUND_SQUARE;
      TARGET_MONSTER_ROUND_SQUARE = RETURN_MONSTER_ROUND_SQUARE;
      LEVEL_UP_SPECIAL = new int[]{27, 44, 93};
      tc = null;
      cheatTime = 0;
      totalTime = 0;
      isAttacking = false;
   }

   public static Player getInstance() {
      if (instance == null) {
         instance = new Player();
      }

      return instance;
   }

   public static void clear() {
      instance = null;
   }

   private Player() {
      super.type = 1;
      super.nState = 0;
      super.motionID = 0;
      super.hpStates = new Vector();
      this.checkState();
      super.nCol = Map.getCurrentColumn(super.y, super.x);
      super.nRow = Map.getCurrentRow(super.y, super.x);
      this.copy_CR();
      super.curStep = 49;
      super.horseState = 0;
      this.newSpecs((byte)4);
   }

   public static void loadSkillDes(ByteArray bar, boolean refresh) {
      short skillConCount;
      if (refresh) {
         try {
            InputStream isDataC = Util.loadFile("/special/sk_des.data", true);
            DataInputStream disDataC = new DataInputStream(isDataC);
            short tc_lengh = disDataC.readShort();
            skillConCount = disDataC.readShort();
            tc = new String[tc_lengh];

            for(int c = 0; c < skillConCount; ++c) {
               short sk = disDataC.readShort();
               tc[sk] = disDataC.readUTF();
            }
         } catch (Exception var8) {
         }
      } else {
         short tc_lengh = bar.readShort();
         short skillConCount = bar.readShort();
         tc = new String[tc_lengh];

         for(int c = 0; c < skillConCount; ++c) {
            skillConCount = bar.readShort();
            tc[skillConCount] = bar.readUTF();
         }
      }

   }

   public static void loadNPC_SKILLData(byte pro, ByteArray bar) {
      try {
         if (skillP != pro) {
            skillP = pro;
            short skillallCount = bar.readShort();
            skillID = new short[skillallCount + 1];
            skillName = new String[skillallCount + 1];
            skillEP = new short[skillallCount + 1];
            skilltimer = new short[skillallCount + 1];
            skillCD = new int[skillallCount + 1];
            skillCon = new String[skillallCount + 1];
            skillImgInd = new short[skillallCount + 1];
            skillTar = new byte[skillallCount + 1];
            skillAr = new short[skillallCount + 1];
            skillDis = new short[skillallCount + 1];
            needSkillLevel = new short[skillallCount + 1];
            needPriceType = new byte[skillallCount + 1];
            needPriceCount = new int[skillallCount + 1];
            needPlayerLevel = new byte[skillallCount + 1];
            isFinality = new byte[skillallCount + 1];
            playershape = new short[skillallCount + 1];
            CD_Count = new long[skillallCount + 1];

            for(int i = 0; i < skillallCount + 1; ++i) {
               if (i == 0) {
                  skillID[i] = 0;
                  skillName[i] = "Tấn công thường";
                  skillEP[i] = 0;
                  skilltimer[i] = 0;
                  skillCD[i] = 2000;
                  skillCon[i] = tc[0];
                  skillImgInd[i] = 0;
                  skillTar[i] = 2;
                  skillAr[i] = 50;
                  skillDis[i] = 0;
                  needSkillLevel[i] = 0;
                  needPriceType[i] = 9;
                  needPriceCount[i] = 1;
                  needPlayerLevel[i] = 1;
                  isFinality[i] = 0;
                  playershape[i] = 32767;
                  CD_Count[i] = 0L;
               } else {
                  skillID[i] = bar.readShort();
                  skillName[i] = bar.readUTF();
                  skillEP[i] = bar.readShort();
                  skilltimer[i] = bar.readShort();
                  skillCD[i] = bar.readInt();
                  short twejif = bar.readShort();

                  try {
                     skillCon[i] = tc[twejif];
                  } catch (Exception var6) {
                     skillCon[i] = "Kỹ năng này thiếu dữ liệu kế hoạch";
                  }

                  skillImgInd[i] = bar.readShort();
                  skillTar[i] = bar.readByte();
                  skillAr[i] = bar.readShort();
                  skillDis[i] = bar.readShort();
                  needSkillLevel[i] = bar.readShort();
                  needPriceType[i] = bar.readByte();
                  needPriceCount[i] = bar.readInt();
                  needPlayerLevel[i] = bar.readByte();
                  isFinality[i] = bar.readByte();
                  playershape[i] = bar.readShort();
                  CD_Count[i] = 0L;
               }
            }

            tc = null;
            bar.close();
            bar = null;
            bar = null;
         } else {
            for(int i = 0; i < CD_Count.length; ++i) {
               CD_Count[i] = 0L;
            }
         }
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }

   public void checkCanUseSkill(long[] da) {
      canUseSkill = null;
      canUseSkill = getSkillsFromData(getInstance().getProfession(), da);
      canUseS.removeAllElements();

      for(byte k = 0; k < 63; ++k) {
         if (canUseSkill[k] != 0) {
            short sind = this.getIND_FromSkillData(canUseSkill[k]);
            if (sind != -1) {
               canUseS.addElement(new Integer(canUseSkill[k]));
            }
         }
      }

      this.setCanUseSkillCount((byte)canUseS.size());
      this.canUseSkillID = new short[this.getCanUseSkillCount()];

      for(short z = 0; z < canUseS.size(); ++z) {
         this.canUseSkillID[z] = (short)(Integer)canUseS.elementAt(z);
      }

   }

   public int[] searchCanStudySkill() {
      int count = skillID.length - 1;
      int offP = '\ufff0';
      boolean isCanUse = false;
      canUseS.removeAllElements();

      short j;
      for(short i = 1; i <= count; ++i) {
         if (getInstance().getLevel() >= needPlayerLevel[i]) {
            isCanUse = false;

            for(j = 0; j < this.getCanUseSkillCount(); ++j) {
               if ((this.canUseSkillID[j] & offP) == (skillID[i] & offP) && this.canUseSkillID[j] >= skillID[i]) {
                  isCanUse = true;
                  break;
               }
            }

            if (!isCanUse) {
               if (needSkillLevel[i] == 0) {
                  canUseS.addElement(new Short(i));
               } else {
                  for(j = 0; j < this.getCanUseSkillCount(); ++j) {
                     if ((this.canUseSkillID[j] & offP) == (needSkillLevel[i] & offP) && this.canUseSkillID[j] >= needSkillLevel[i]) {
                        canUseS.addElement(new Short(i));
                        break;
                     }
                  }
               }
            }
         }
      }

      int[] canStudySkill_IND = new int[canUseS.size()];

      for(j = 0; j < canUseS.size(); ++j) {
         canStudySkill_IND[j] = (Short)canUseS.elementAt(j);
      }

      return canStudySkill_IND;
   }

   public int[] searchNotCanStudySkill(byte lel) {
      int count = skillID.length - 1;
      int offP = '\ufff0';
      boolean isCanUse = false;
      int levelANG = lel * 10;
      canUseS.removeAllElements();

      short j;
      for(short i = 1; i <= count; ++i) {
         if (needPlayerLevel[i] >= levelANG - 9 && needPlayerLevel[i] <= levelANG) {
            isCanUse = false;

            for(j = 0; j < this.getCanUseSkillCount(); ++j) {
               if ((this.canUseSkillID[j] & offP) == (skillID[i] & offP) && this.canUseSkillID[j] >= skillID[i]) {
                  isCanUse = true;
                  break;
               }
            }

            if (!isCanUse) {
               boolean isNotCanStudy = false;

               for(short j = 0; j < this.getCanUseSkillCount(); ++j) {
                  if ((this.canUseSkillID[j] & offP) == (skillID[i] & offP)) {
                     isNotCanStudy = true;
                     if (this.canUseSkillID[j] == needSkillLevel[i] && needPlayerLevel[i] > this.getLevel()) {
                        canUseS.addElement(new Short(i));
                     } else if (this.canUseSkillID[j] != needSkillLevel[i]) {
                        canUseS.addElement(new Short(i));
                     }
                     break;
                  }
               }

               if (!isNotCanStudy && needPlayerLevel[i] > this.getLevel()) {
                  canUseS.addElement(new Short(i));
               }
            }
         }
      }

      int[] canStudySkill_IND = new int[canUseS.size()];

      for(j = 0; j < canUseS.size(); ++j) {
         canStudySkill_IND[j] = (Short)canUseS.elementAt(j);
      }

      return canStudySkill_IND;
   }

   public static int[] getSkillsFromData(int classType, long[] data) {
      if (data.length == 4) {
         classType <<= 12;
         int[] skills = new int[63];

         for(int i = 0; i < 63; ++i) {
            int j = i >> 4;
            int bit = i & 15;
            bit <<= 2;
            long l = 15L;
            l <<= bit;
            l &= data[j];
            l >>>= bit;
            if (l != 0L) {
               skills[i] = classType;
               skills[i] |= i << 4;
               skills[i] = (int)((long)skills[i] | l);
            }
         }

         return skills;
      } else {
         return new int[0];
      }
   }

   public short getIND_FromSkillData(int skillid) {
      short si = -1;
      short SkillDataCount = (short)skillID.length;

      for(short i = 0; i < SkillDataCount; ++i) {
         if (skillid == skillID[i]) {
            si = i;
            break;
         }
      }

      return si;
   }

   public void loadNPC_GeniusData(ByteArray disData) {
      try {
         this.talentTypeCount = disData.readByte();
         this.talentTypeName = new String[this.talentTypeCount];
         this.talentPosition = new byte[this.talentTypeCount][];
         this.talentName = new String[this.talentTypeCount][];
         this.talentImgInd = new short[this.talentTypeCount][];
         this.talentCon = new String[this.talentTypeCount][];
         this.talentSuperPosition = new byte[this.talentTypeCount][];
         this.talentIsOpen = new boolean[this.talentTypeCount][];
         this.talentMaxPoint = new byte[this.talentTypeCount][];
         this.talentCurPoint = new byte[this.talentTypeCount][];
         this.talentAddPoint = new byte[this.talentTypeCount][];
         this.maxTalentPosition = new byte[this.talentTypeCount];
         this.usedPoint = new short[this.talentTypeCount];
         this.selTalent = new byte[this.talentTypeCount];

         for(int i = 0; i < this.talentTypeCount; ++i) {
            String tt = disData.readUTF();
            this.talentTypeName[i] = tt;
            byte talentCount = disData.readByte();
            this.talentPosition[i] = new byte[talentCount];
            this.talentName[i] = new String[talentCount];
            this.talentImgInd[i] = new short[talentCount];
            this.talentCon[i] = new String[talentCount];
            this.talentSuperPosition[i] = new byte[talentCount];
            this.talentIsOpen[i] = new boolean[talentCount];
            this.talentMaxPoint[i] = new byte[talentCount];
            this.talentCurPoint[i] = new byte[talentCount];
            this.talentAddPoint[i] = new byte[talentCount];

            for(int z = 0; z < talentCount; ++z) {
               this.talentPosition[i][z] = disData.readByte();
               if (this.maxTalentPosition[i] < this.talentPosition[i][z]) {
                  this.maxTalentPosition[i] = this.talentPosition[i][z];
               }

               this.talentName[i][z] = disData.readUTF();
               this.talentImgInd[i][z] = disData.readShort();
               this.talentCon[i][z] = disData.readUTF();
               this.talentSuperPosition[i][z] = disData.readByte();
               this.talentMaxPoint[i][z] = disData.readByte();
            }

            if (talentCount != 0) {
               this.selTalent[i] = this.talentPosition[i][0];
            }
         }

         this.loadGenius_CurPoint(disData);
         disData.close();
         disData = null;
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   public void loadGenius_CurPoint(ByteArray disData) {
      disData.readByte();

      for(int ci = 0; ci < this.talentTypeCount; ++ci) {
         disData.readByte();
         this.usedPoint[ci] = 0;

         for(int cj = 0; cj < this.talentCurPoint[ci].length; ++cj) {
            this.talentCurPoint[ci][cj] = disData.readByte();
            short[] var10000 = this.usedPoint;
            var10000[ci] = (short)(var10000[ci] + this.talentCurPoint[ci][cj]);
            this.refreshTalentOpen(ci, cj);
         }
      }

      this.remaindTalentPoint = disData.readByte();
   }

   public void drawTalentTree(Graphics g, int clientCommand) {
      short tx = false;
      short ty = false;
      if (clientCommand == 524292) {
         g.setColor(Cons.COLOR_PANEL_BORDER_3);
         g.drawRect(this.areaPosition_X, this.areaPosition_Y, this.areaPosition_W - 1, this.areaPosition_H - 1);
         g.fillRect(this.areaPosition_X, this.areaPosition_Y, 3, this.areaPosition_H);
         g.fillRect(this.areaPosition_X + this.areaPosition_W - 3, this.areaPosition_Y, 3, this.areaPosition_H);
         g.drawRect(this.areaPosition_X + 3 + 1, this.areaPosition_Y + 1 + 1, this.areaPosition_W - 8 - 1, this.areaPosition_H - 4 - 1);
         short bx = (short)((MainCanvas.screenW >> 1) - 27 - (MainCanvas.stuffMImg.frame_w >> 1));
         short by = this.areaPosition_Y;
         g.setClip(0, this.areaPosition_Y + 2, MainCanvas.screenW, this.areaPosition_H - 5);
         UIForm tform = (UIForm)MainCanvas.curFormVector.elementAt(0);
         int idw = tform.foldedIndex;
         UITitle geniusTitle = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
         int tl = this.talentPosition[idw].length;
         short selX = 0;
         short selY = 0;

         for(int p = 0; p < tl; ++p) {
            short tx = (short)(bx + (this.talentPosition[idw][p] - 1) % 3 * 27);
            short ty = (short)(8 + this.areaPosition_Y + ((this.talentPosition[idw][p] - 1) / 3 - geniusTitle.gewgg) * 27);
            g.setColor(Cons.COLOR_GRID_FRAME_BG[this.talentImgInd[idw][p] / 1000 - 1][0]);
            g.fillRect(tx - 1, ty - 1, 16, 16);
            MainCanvas.stuffMImg.draw(g, tx, ty, this.talentImgInd[idw][p] % 1000);
            g.setColor(Cons.COLOR_GRID_FRAME_BG[this.talentImgInd[idw][p] / 1000 - 1][1]);
            g.drawRect(tx - 1, ty - 1, 15, 15);
            if (this.talentIsOpen[idw][p]) {
               MainCanvas.ui_numMImg.draw(g, tx + MainCanvas.stuffMImg.frame_w - MainCanvas.ui_numMImg.frame_w, ty + MainCanvas.stuffMImg.frame_h - MainCanvas.ui_numMImg.frame_h, this.talentCurPoint[idw][p]);
            } else {
               g.drawImage(MainCanvas.ui_icon_shadow, tx, ty, 0);
            }

            if (this.talentSuperPosition[idw][p] != 0) {
               byte arrH = (byte)((this.talentPosition[idw][p] - this.talentSuperPosition[idw][p]) / 3 - 1);
               g.setColor(6045209);
               g.fillRect(tx + (MainCanvas.stuffMImg.frame_w - MainCanvas.talentArrImg.getWidth() >> 1) + 2, ty - 3 - MainCanvas.talentArrImg.getHeight() - arrH * 27, 4, arrH * 27);
               Util.drawImage(g, MainCanvas.talentArrImg, tx + (MainCanvas.stuffMImg.frame_w - MainCanvas.talentArrImg.getWidth() >> 1), ty - 3 - MainCanvas.talentArrImg.getHeight(), 0);
            }

            if (this.selTalent[idw] == this.talentPosition[idw][p]) {
               selX = tx;
               selY = ty;
            }
         }

         g.setColor(16711680);
         g.drawRect(selX - 3, selY - 3, 19, 19);
         geniusTitle.titleScroll.draw(g);
         g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
      }

   }

   public void refreshTalentOpen(int talentType, int talentInd) {
      if ((this.talentPosition[talentType][talentInd] - 1) / 3 * 5 <= this.usedPoint[talentType]) {
         int superID = -1;

         for(int z = 0; z < talentInd; ++z) {
            if (this.talentPosition[talentType][z] == this.talentSuperPosition[talentType][talentInd]) {
               superID = z;
               break;
            }
         }

         if (superID == -1) {
            this.talentIsOpen[talentType][talentInd] = true;
         } else if (this.talentCurPoint[talentType][superID] == this.talentMaxPoint[talentType][superID]) {
            this.talentIsOpen[talentType][talentInd] = true;
         } else {
            this.talentIsOpen[talentType][talentInd] = false;
         }
      } else {
         this.talentIsOpen[talentType][talentInd] = false;
      }

   }

   public void selTalentTree(int dire) {
      boolean isFinish = false;
      UIForm tform = (UIForm)MainCanvas.curFormVector.elementAt(0);
      byte cwi = tform.foldedIndex;
      byte newST = this.selTalent[cwi];
      UITitle geniusTitle = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);

      do {
         byte offx;
         byte offx;
         byte offx;
         switch(dire) {
         case 0:
            geniusTitle.curTalentRow = --geniusTitle.curTalentRow < 0 ? 0 : geniusTitle.curTalentRow;
            newST = (byte)(newST - 3);
            if (newST < 1) {
               isFinish = true;
            }

            if (this.isExsitTalent(newST)) {
               this.selTalent[cwi] = newST;
               MainCanvas.curForm.addWarningStr(getInstance().talentCon[cwi][this.getIND_FromTalentData(cwi, this.selTalent[cwi])]);
               isFinish = true;
               break;
            }

            for(offx = 0; offx < 3; ++offx) {
               offx = (byte)(geniusTitle.curTalentRow * 3 + offx + 1);
               if (this.isExsitTalent(offx)) {
                  this.selTalent[cwi] = offx;
                  MainCanvas.curForm.addWarningStr(getInstance().talentCon[cwi][this.getIND_FromTalentData(cwi, this.selTalent[cwi])]);
                  break;
               }
            }

            isFinish = true;
            break;
         case 1:
            geniusTitle.curTalentRow = (byte)(++geniusTitle.curTalentRow >= geniusTitle.allTalentRows ? geniusTitle.allTalentRows - 1 : geniusTitle.curTalentRow);
            newST = (byte)(newST + 3);
            if (newST > this.maxTalentPosition[cwi]) {
               isFinish = true;
            }

            if (this.isExsitTalent(newST)) {
               this.selTalent[cwi] = newST;
               MainCanvas.curForm.addWarningStr(getInstance().talentCon[cwi][this.getIND_FromTalentData(cwi, this.selTalent[cwi])]);
               isFinish = true;
               break;
            }

            for(offx = 0; offx < 3; ++offx) {
               offx = (byte)(geniusTitle.curTalentRow * 3 + offx + 1);
               if (this.isExsitTalent(offx)) {
                  this.selTalent[cwi] = offx;
                  MainCanvas.curForm.addWarningStr(getInstance().talentCon[cwi][this.getIND_FromTalentData(cwi, this.selTalent[cwi])]);
                  break;
               }
            }

            isFinish = true;
            break;
         case 2:
            offx = (byte)((this.talentPosition[cwi][this.getIND_FromTalentData(cwi, this.selTalent[cwi])] - 1) % 3);
            --newST;
            if (newST >= 1 && offx != 0) {
               if (this.isExsitTalent(newST)) {
                  this.selTalent[cwi] = newST;
                  MainCanvas.curForm.addWarningStr(getInstance().talentCon[cwi][this.getIND_FromTalentData(cwi, this.selTalent[cwi])]);
                  isFinish = true;
                  break;
               }

               for(offx = 1; offx < 2; ++offx) {
                  offx = (byte)(newST - offx);
                  if (this.isExsitTalent(offx)) {
                     this.selTalent[cwi] = offx;
                     MainCanvas.curForm.addWarningStr(getInstance().talentCon[cwi][this.getIND_FromTalentData(cwi, this.selTalent[cwi])]);
                     break;
                  }
               }

               isFinish = true;
               break;
            }

            isFinish = true;
            break;
         case 3:
            offx = (byte)((this.talentPosition[cwi][this.getIND_FromTalentData(cwi, this.selTalent[cwi])] - 1) % 3);
            ++newST;
            if (newST <= this.maxTalentPosition[cwi] && offx != 2) {
               if (this.isExsitTalent(newST)) {
                  this.selTalent[cwi] = newST;
                  MainCanvas.curForm.addWarningStr(getInstance().talentCon[cwi][this.getIND_FromTalentData(cwi, this.selTalent[cwi])]);
                  isFinish = true;
               } else {
                  if (offx != 2) {
                     for(offx = 1; offx < 3 - offx - 1; ++offx) {
                        byte talentP = (byte)(newST + offx);
                        if (this.isExsitTalent(talentP)) {
                           this.selTalent[cwi] = talentP;
                           MainCanvas.curForm.addWarningStr(getInstance().talentCon[cwi][this.getIND_FromTalentData(cwi, this.selTalent[cwi])]);
                           break;
                        }
                     }
                  }

                  isFinish = true;
               }
            } else {
               isFinish = true;
            }
         }
      } while(!isFinish);

      UILabel geniusNameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
      geniusNameLabel.setText(this.talentName[cwi][this.getIND_FromTalentData(cwi, this.selTalent[cwi])]);
      geniusTitle.titleScroll.keyInScroll(geniusTitle.curTalentRow, true);
   }

   private boolean isExsitTalent(byte st) {
      boolean temE = false;
      UIForm tform = (UIForm)MainCanvas.curFormVector.elementAt(0);
      int cwi = tform.foldedIndex;

      for(int z = 0; z < this.talentPosition[cwi].length; ++z) {
         if (this.talentPosition[cwi][z] == st) {
            temE = true;
            break;
         }
      }

      return temE;
   }

   public int getIND_FromTalentData(byte fid, byte st) {
      int ind = -1;

      for(int z = 0; z < this.talentPosition[fid].length; ++z) {
         if (this.talentPosition[fid][z] == st) {
            ind = z;
            break;
         }
      }

      return ind;
   }

   public void tick() {
      ++this.pingtime;
      if (this.pingtime > 200) {
         this.pingtime = 0;
         MainCanvas.ni.send(196639);
      }

      if (!MainCanvas.isWaiting && totalTime <= 20) {
         long l = System.currentTimeMillis() - this.st;
         this.st = System.currentTimeMillis();
         ++totalTime;
         if (l < 92L) {
            ++cheatTime;
            if (cheatTime > 10) {
               MainCanvas.aMidlet.exitMIDlet();
            }
         }
      }

      if (!PCHang.m_bHang) {
         this.pressArrowKey();
      }

      this.motionState_tick();
      if (!Map.battleDoorOpen && !Map.checkDoorCanThr()) {
         short row = this.getRow(super.x, super.y);
         short col = this.getCol(super.x, super.y);
         if (Map.m_bBattlefieldMap == 2) {
            if (Map.m_bCamp == 1) {
               this.setRowCol(row, (short)(col + 1));
            } else if (Map.m_bCamp == 2) {
               this.setRowCol(row, (short)(col - 1));
            }
         } else if (Map.m_bBattlefieldMap == 3) {
            this.setRowCol((short)(row + 1), col);
         }

         super.x = Map.getCurrentCentreX(this.getCol(), this.getRow());
         super.y = Map.getCurrentCentreY(this.getCol(), this.getRow());
         UITopForm.createLocalTopForm((byte)0, (String)"Cửa chiến trường chưa mở, hãy đợi.", "Xác nhận", "", -1, -2);
      } else {
         this.setRowCol(this.getRow(super.x, super.y), this.getCol(super.x, super.y));
      }

      if (!this.checkPOS()) {
         if (!super.isTreadMapCarryPoint) {
            super.isTreadMapCarryPoint = this.checkIsMapChange();
            if (super.isTreadMapCarryPoint) {
               MainCanvas.isWaiting = true;
               this.copy_CR();
               MainCanvas.ni.send(196612);
            }
         } else {
            super.isTreadMapCarryPoint = false;
         }

         if (MainCanvas.countTick % 8 == 0 && NetInterface.socket != null && super.isSendMove && !MainCanvas.isWaiting) {
            this.copy_CR();
            MainCanvas.ni.send(196612);
         }
      }

      this.tickSkillCD();
      this.tickHpChangeVectorPop();
      this.tickSpecial();
      this.drawTick();
   }

   private void sendMove() {
      if (!this.checkPOS()) {
         MainCanvas.ni.send(196612);
         this.copy_CR();
      }

   }

   private boolean checkIsMapChange() {
      for(int i = 0; i < Map.curCarryPoint.length; ++i) {
         if (this.getCol() == Map.curCarryPoint[i][0] && this.getRow() == Map.curCarryPoint[i][1]) {
            if (this.getState() >= 100) {
               return false;
            }

            this.setStandState();
            MainCanvas.isWaiting = true;
            return true;
         }
      }

      return false;
   }

   private boolean checkPOS() {
      return super.oCol == super.nCol && super.oRow == super.nRow;
   }

   private void shortCutActions() {
      this.touchShortCutAction();
      if (MainCanvas.isInputDown(MainCanvas.curGameKey[14])) {
         SIManager.getInstance();
         SIManager.shortCut.changeGroup();
      } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[6])) {
         this.shortCutAction((byte)0);
      } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[7])) {
         this.shortCutAction((byte)1);
      } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[8])) {
         this.shortCutAction((byte)2);
      } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[9])) {
         this.shortCutAction((byte)3);
      } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[10])) {
         this.shortCutAction((byte)4);
      } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[11])) {
         this.shortCutAction((byte)5);
      } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[12])) {
         this.shortCutAction((byte)6);
      } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[13])) {
         this.shortCutAction((byte)7);
      }

   }

   public void touchShortCutAction() {
      if (MainCanvas.SUPPORT_POINTER) {
         byte index;
         if (MainCanvas.isInputDown(268435456)) {
            index = this.getShortCutIndex();
            if (index >= 0) {
               this.shortCutAction(index);
            }
         } else if (MainCanvas.isInputUp(268435456) && MainCanvas.isPointerDrag() && MainCanvas.isPointInRect(0, MainCanvas.screenH - 36, MainCanvas.screenW, 36)) {
            index = this.getShortCutIndex();
            if (index < 0) {
               SIManager.getInstance();
               SIManager.shortCut.changeGroup();
            }
         }
      }

   }

   private byte getShortCutIndex() {
      int SY = 36;
      int DY = SIShortCut.shortCutFrameDy;
      int EY = true;
      int SW = true;
      int D_SPACE = 3;
      if (MainCanvas.isPointInRect(0, MainCanvas.screenH - DY, MainCanvas.screenW, SY)) {
         int dx = false;
         int dy = MainCanvas.screenH - DY - 4;
         int x = MainCanvas.pointerX;
         int y = MainCanvas.pointerY;
         int w = 16 + SIShortCut.shortCutFrameSpace;
         int h = 20;

         for(byte i = 0; i < 8; ++i) {
            int dx;
            if (i < 4) {
               dx = D_SPACE + i * (SIShortCut.shortCutFrameSpace + 16) - (SIShortCut.shortCutFrameSpace >> 1);
            } else {
               dx = SIShortCut.shortCutRightX - (7 - i) * (SIShortCut.shortCutFrameSpace + 16) - (SIShortCut.shortCutFrameSpace >> 1);
            }

            if (x >= dx && x <= dx + w && y >= dy && y <= dy + h) {
               return i;
            }
         }
      }

      return -1;
   }

   private void pressArrowKey() {
      if (!MainCanvas.isWaiting) {
         if (Map.m_bBattlefield) {
            if (MainCanvas.curTopForm != null) {
               this.setState((byte)0);
               return;
            }

            if (super.state == 9) {
               return;
            }
         }

         this.noKeyTick();
         if (MainCanvas.getGameState() == 0 && MainCanvas.curTopForm == null && MainCanvas.AfficheForm == null) {
            if (MainCanvas.isInputDown(MainCanvas.curGameKey[17])) {
               this.changeTarget(0);
            } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[18])) {
               this.changeTarget(1);
            } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[19])) {
               this.changeTarget(2);
            } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[20])) {
               this.changeTarget(3);
            } else if (this.touchScreenAction()) {
               return;
            }

            switch(super.state) {
            case 0:
               if (MainCanvas.isInputDownOrHold(MainCanvas.curGameKey[0])) {
                  if (super.direction == 3) {
                     this.setState((byte)1);
                     this.move((byte)3);
                  } else {
                     this.setDirection((byte)3);
                  }
               } else if (MainCanvas.isInputDownOrHold(MainCanvas.curGameKey[1])) {
                  if (super.direction == 1) {
                     this.setState((byte)1);
                     this.move((byte)1);
                  } else {
                     this.setDirection((byte)1);
                  }
               } else if (MainCanvas.isInputDownOrHold(MainCanvas.curGameKey[2])) {
                  if (super.direction == 2) {
                     this.setState((byte)1);
                     this.move((byte)2);
                  } else {
                     this.setDirection((byte)2);
                  }
               } else if (MainCanvas.isInputDownOrHold(MainCanvas.curGameKey[3])) {
                  if (super.direction == 4) {
                     this.setState((byte)1);
                     this.move((byte)4);
                  } else {
                     this.setDirection((byte)4);
                  }
               } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[4])) {
                  switch(super.direction) {
                  case 1:
                  case 8:
                     super.direction = 1;
                     break;
                  case 2:
                  case 7:
                     super.direction = 2;
                     break;
                  case 3:
                  case 5:
                     super.direction = 3;
                     break;
                  case 4:
                  case 6:
                     super.direction = 4;
                  }

                  this.setState((byte)10);
               } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[5])) {
                  this.fireKeyAction(false);
               } else if (MainCanvas.isInputDown(131072)) {
                  this.fireKeyAction(true);
               } else {
                  this.shortCutActions();
                  if (this.getSkill_Index() == -1) {
                     return;
                  }

                  if (isAttacking && super.SkillTime_Count == 0) {
                     this.attGenaral();
                  }
               }
               break;
            case 1:
               isAttacking = false;
               if (MainCanvas.isInputDownOrHold(MainCanvas.curGameKey[0])) {
                  this.move((byte)3);
               } else if (MainCanvas.isInputDownOrHold(MainCanvas.curGameKey[1])) {
                  this.move((byte)1);
               } else if (MainCanvas.isInputDownOrHold(MainCanvas.curGameKey[2])) {
                  this.move((byte)2);
               } else if (MainCanvas.isInputDownOrHold(MainCanvas.curGameKey[3])) {
                  this.move((byte)4);
               } else {
                  this.setState((byte)0);
                  super.ism = false;
                  if (super.cartPlayer != null) {
                     super.cartPlayer.setState((byte)0);
                  }
               }
               break;
            case 2:
               if (MainCanvas.isInputDown(MainCanvas.curGameKey[0] | MainCanvas.curGameKey[1] | MainCanvas.curGameKey[2] | MainCanvas.curGameKey[3])) {
                  this.setState((byte)0);
               }
               break;
            case 3:
               if (MainCanvas.isInputDownOrHold(MainCanvas.curGameKey[0])) {
                  super.SkillTime_Count = 0;
                  this.setState((byte)1);
                  this.move((byte)3);
               } else if (MainCanvas.isInputDownOrHold(MainCanvas.curGameKey[1])) {
                  super.SkillTime_Count = 0;
                  this.setState((byte)1);
                  this.move((byte)1);
               } else if (MainCanvas.isInputDownOrHold(MainCanvas.curGameKey[2])) {
                  super.SkillTime_Count = 0;
                  this.setState((byte)1);
                  this.move((byte)2);
               } else if (MainCanvas.isInputDownOrHold(MainCanvas.curGameKey[3])) {
                  super.SkillTime_Count = 0;
                  this.setState((byte)1);
                  this.move((byte)4);
               } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[17] | MainCanvas.curGameKey[18] | MainCanvas.curGameKey[19] | MainCanvas.curGameKey[20])) {
                  super.SkillTime_Count = 0;
                  this.setState((byte)0);
               } else if (Map.m_bBattlefield) {
                  if (GOManager.getCurrentTarget().getType() == 10 && this.isGettingFlag != 0 && this.getAttacked()) {
                     super.SkillTime_Count = 0;
                     this.baqiCount = 0;
                     this.isGettingFlag = 0;
                     this.setAttacked(false);
                     this.setState((byte)0);
                  }
               } else if (GOManager.getCurrentTarget().getType() == 11 && this.getAttacked()) {
                  super.SkillTime_Count = 0;
                  this.baqiCount = 0;
                  this.setAttacked(false);
                  this.setState((byte)0);
               }
               break;
            case 10:
               if (MainCanvas.getGameState() == 0) {
                  if (MainCanvas.isInputDown(MainCanvas.curGameKey[0])) {
                     this.setDirection((byte)3);
                  } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[1])) {
                     this.setDirection((byte)1);
                  } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[2])) {
                     this.setDirection((byte)2);
                  } else if (MainCanvas.isInputDown(MainCanvas.curGameKey[3])) {
                     this.setDirection((byte)4);
                  } else if (!MainCanvas.isInputDown(MainCanvas.curGameKey[5] | 131072) && !MainCanvas.isInputDown(MainCanvas.curGameKey[4]) && !MainCanvas.isInputDown(MainCanvas.curGameKey[17] | MainCanvas.curGameKey[18] | MainCanvas.curGameKey[19] | MainCanvas.curGameKey[20])) {
                     this.shortCutActions();
                  } else {
                     this.setState((byte)0);
                     super.ism = false;
                     if (super.cartPlayer != null) {
                        super.cartPlayer.setState((byte)0);
                     }
                  }
               }

               this.move(super.direction);
               break;
            case 100:
            case 101:
               if (MainCanvas.isInputDown(-262145)) {
                  this.jumpOutState();
                  this.setAimRowCol(this.getRow(), this.getCol());
                  super.path = new int[0][];
               }
               break;
            case 102:
            case 103:
            case 105:
               if (MainCanvas.isInputDown(-262145) && !MainCanvas.isInputDown(MainCanvas.curGameKey[5] | 131072)) {
                  this.jumpOutState();
                  this.setAimRowCol(this.getRow(), this.getCol());
                  super.path = new int[0][];
               }
               break;
            case 104:
               isAttacking = false;
               if (MainCanvas.isInputDown(-262145)) {
                  this.jumpOutState();
                  this.setAimRowCol(this.getRow(), this.getCol());
                  super.path = new int[0][];
               }
            }

         } else {
            if (super.state == 10) {
               this.move(super.direction);
            }

         }
      }
   }

   private final void noKeyTick() {
      switch(super.state) {
      case 3:
         ++super.SkillTime_Count;
         ++this.baqiCount;
         if (this.getSkill_ID() != 0 || GOManager.getCurrentTarget().getType() != 4 && GOManager.getCurrentTarget().getType() != 5 && GOManager.getCurrentTarget().getType() != 6 && GOManager.getCurrentTarget().getType() != 7 && GOManager.getCurrentTarget().getType() != 8 && GOManager.getCurrentTarget().getType() != 11) {
            if (GOManager.getCurrentTarget().getType() == 10 && this.isGettingFlag != 0) {
               if (this.baqiCount > this.FALG_TIME / 120) {
                  this.baqiCount = 0;
                  this.setState((byte)0);
                  if (this.isGettingFlag == 1) {
                     MainCanvas.ni.send(852000);
                  } else if (this.isGettingFlag == 2) {
                     MainCanvas.ni.send(852009);
                  } else if (this.isGettingFlag == 3) {
                     MainCanvas.ni.send(852003);
                  }

                  this.isGettingFlag = 0;
               }
            } else {
               short skillInd = this.getSkill_Index();
               if (skillInd != -1) {
                  if (super.SkillTime_Count > skilltimer[skillInd] / 120) {
                     super.SkillTime_Count = 0;
                     CD_Count[skillInd] = System.currentTimeMillis();
                     switch(this.getProfession()) {
                     case 1:
                     case 2:
                        this.setState((byte)2);
                        break;
                     default:
                        this.setState((byte)4);
                     }

                     this.sendMove();
                     MainCanvas.ni.send(196614);
                     SIManager.getInstance();
                     SIShortCut sc = SIManager.shortCut;
                     sc.setCD((byte)1, skillID[skillInd], true);
                     getInstance();
                     CD_Count[0] = System.currentTimeMillis();
                     sc.setCD((byte)1, 0, true);
                     sc.refreshShortCutAll();
                  }
               } else {
                  this.setState((byte)0);
               }
            }
         } else if (super.SkillTime_Count > this.COLLECTION_TIME / 120) {
            super.SkillTime_Count = 0;
            this.setState((byte)4);
            MainCanvas.ni.send(1572866);
         }
         break;
      case 100:
      case 101:
         this.movePath();
         if (super.state == 0 || super.state == 101) {
            this.setAimRowColFindPath(((ActiveGO)GOManager.currentTarget).getAimRow(), ((ActiveGO)GOManager.currentTarget).getAimCol());
            if (super.path.length > 0) {
               this.setState((byte)100);
            } else {
               this.setState((byte)101);
            }
         }
         break;
      case 102:
      case 103:
      case 105:
         this.approach(super.state);
         break;
      case 104:
         this.movePath();
      }

   }

   private final void jumpOutState() {
      this.setState((byte)0);
      this.shortCutActions();
   }

   private final void attGenaral() {
      this.skill_OBJID = GOManager.currentTarget.getID();
      if (GOManager.currentTarget != this && GOManager.getCurrentTarget().getType() != 4 && GOManager.getCurrentTarget().getType() != 5 && GOManager.getCurrentTarget().getType() != 6 && GOManager.getCurrentTarget().getType() != 7 && GOManager.getCurrentTarget().getType() != 8) {
         if (this.canAttackTarget()) {
            if (super.isnotattck) {
               return;
            }

            if (this.attackTime()) {
               this.setPhyAttack();
            }
         } else {
            this.setState((byte)102);
            this.setAimRowColFindPath(((ActiveGO)GOManager.currentTarget).getRow(), ((ActiveGO)GOManager.currentTarget).getCol());
         }

      } else {
         this.downHorse();
         this.setState((byte)2);
         super.nNextFrame = -1;
      }
   }

   private final boolean attackTime() {
      if (CD_Count[0] > 0L) {
         if (this.getState() != 105) {
            this.setState((byte)105);
         }

         return false;
      } else {
         return true;
      }
   }

   private final void setPhyAttack() {
      this.downHorse();
      CD_Count[0] = System.currentTimeMillis();
      this.setState((byte)2);
      getInstance().setSkill_ID((short)0);
      super.nNextFrame = -1;
      this.skill_OBJID = GOManager.currentTarget.getID();
      this.sendMove();
      MainCanvas.ni.send(196614);
      SIManager.getInstance();
      SIShortCut sc = SIManager.shortCut;
      sc.setCD((byte)1, 0, true);
      sc.refreshShortCutAll();
   }

   public final void fireKeyAction(boolean isSoftLeftKey) {
      if (GOManager.currentTarget == this) {
         this.findTargetAction();
      } else if (GOManager.currentTarget.type == 1) {
         if (((ActiveGO)GOManager.currentTarget).getPrestige() >= 0) {
            this.addOtherPlayerMenu();
         } else if (((ActiveGO)GOManager.currentTarget).getPrestige() < 0) {
            if (isSoftLeftKey && ((ActiveGO)GOManager.currentTarget).getPrestige() != -4 && !((ActiveGO)GOManager.currentTarget).getSpecialMonster()) {
               this.addOtherEnemyMenu();
            } else {
               isAttacking = true;
               this.attGenaral();
            }
         }
      } else if ((GOManager.currentTarget.type == 3 || GOManager.currentTarget.type == 2) && GOManager.currentTarget.getState() != 9) {
         ActiveGO go = (ActiveGO)GOManager.currentTarget;
         if (go.getPrestige() < 0) {
            isAttacking = true;
            this.attGenaral();
         } else if (this.canAttackTarget()) {
            MainCanvas.ni.send(851969);
            this.setState((byte)0);
         } else {
            this.setState((byte)103);
            this.setAimRowColFindPath(((ActiveGO)GOManager.currentTarget).getAimRow(), ((ActiveGO)GOManager.currentTarget).getAimCol());
         }
      } else if (GOManager.currentTarget.type != 4 && GOManager.currentTarget.type != 5 && GOManager.currentTarget.type != 6 && GOManager.currentTarget.type != 7 && GOManager.currentTarget.type != 8) {
         if (GOManager.currentTarget.type == 10) {
            MainCanvas.ni.send(851969);
            this.setState((byte)0);
         } else if (GOManager.currentTarget.type == 11) {
            MainCanvas.ni.send(852071);
            this.setState((byte)0);
         }
      } else {
         this.setState((byte)103);
         this.setAimRowColFindPath(((PassiveGO)GOManager.currentTarget).getRow(), ((PassiveGO)GOManager.currentTarget).getCol());
      }

   }

   public final void addOtherEnemyMenu() {
      UIMenu.otherPlayerMenu = new UIMenu((byte)1);
      String[] menu = new String[]{"Tra tìm", "Chào", "Khiêu khích", "Báo cáo"};
      int[] cmds = new int[]{393230, -268435451, -268435450, 196638};
      UIMenu.otherPlayerMenu.addMenu(menu, cmds);
      MainCanvas.setGameState((byte)6);
   }

   public final void addOtherPlayerMenu() {
      UIMenu.otherPlayerMenu = new UIMenu((byte)1);
      String[] menu = new String[]{"Tra tìm", "Thông tin hảo hữu", "Thêm hảo hữu", "Ẩn người chơi", "Chat riêng", "Giao dịch", "Mời khiêu chiến", "Báo cáo"};
      int[] cmds = new int[]{393230, 393229, 589835, 589836, -268435452, 1376269, 196626, 196638};
      String tmpMenu;
      int tmpCmd;
      if (SITeam.teamState == 0 || SITeam.headerID == getInstance().getID()) {
         tmpMenu = "Yêu cầu tổ đội";
         tmpCmd = 720897;
         menu = Util.addArray(menu, menu.length, tmpMenu);
         cmds = Util.addArray(cmds, cmds.length, tmpCmd);
      }

      if (this.kingRight != PCKing.KING_RIGHT_HERDER && this.kingRight != PCKing.KING_RIGHT_VICE_HERDER) {
         if (this.clanRight == PCClan.CLAN_RIGHT_VICE_HERDER || this.clanRight == PCClan.CLAN_RIGHT_HERDER) {
            tmpMenu = "Chiêu mộ";
            tmpCmd = 1638405;
            menu = Util.addArray(menu, menu.length, tmpMenu);
            cmds = Util.addArray(cmds, cmds.length, tmpCmd);
         }
      } else {
         tmpMenu = "Chiêu mộ";
         tmpCmd = 1835013;
         menu = Util.addArray(menu, menu.length, tmpMenu);
         cmds = Util.addArray(cmds, cmds.length, tmpCmd);
      }

      UIMenu.otherPlayerMenu.addMenu(menu, cmds);
      MainCanvas.setGameState((byte)6);
   }

   private final void shortCutAction(byte index) {
      SIManager.getInstance();
      SIShortCut sc = SIManager.shortCut;
      byte type = sc.curShortCutType[sc.curShortCutGroup][index];
      int id = sc.curShortCutSelfIndex[sc.curShortCutGroup][index];
      if (id >= 0) {
         switch(type) {
         case 1:
            this.downHorse();
            this.useSkill((short)id);
            break;
         case 2:
            SIShortCut.shortCutId = id;
            MainCanvas.ni.send(458763);
         }
      }

   }

   private void downHorse() {
      if (super.horseState == 1) {
         MainCanvas.ni.send(458766);
         if (super.cartPlayer == null) {
            this.releaseHorse();
         } else {
            this.releaseCart();
         }
      }

   }

   private void tickSkillCD() {
      SIManager.getInstance();
      SIShortCut sc = SIManager.shortCut;
      int length = canUseS.size();
      boolean isRefresh = false;

      for(byte scI = 0; scI < 63; ++scI) {
         int index = this.getIND_FromSkillData(canUseSkill[scI]);
         if (index != -1 && canUseSkill[scI] != 0 && CD_Count[index] > 0L) {
            long timeTaken = System.currentTimeMillis();
            if (timeTaken - CD_Count[index] >= (long)skillCD[index]) {
               CD_Count[index] = 0L;
               sc.setCD((byte)1, canUseSkill[scI], false);
               isRefresh = true;
            }
         }
      }

      if (CD_Count[0] > 0L) {
         long timeTaken = System.currentTimeMillis();
         if (timeTaken - CD_Count[0] >= (long)skillCD[0]) {
            CD_Count[0] = 0L;
            sc.setCD((byte)1, 0, false);
            isRefresh = true;
         }
      }

      if (isRefresh) {
         sc.refreshShortCutAll();
      }

   }

   private void useSkill(short id) {
      short index = this.getIND_FromSkillData(id);
      if (CD_Count[index] <= 0L) {
         if (super.isnotcast) {
            if (this.getProfession() == 1) {
               if (id < 4480 || id > 4495) {
                  return;
               }
            } else if (this.getProfession() == 5) {
               if (id < 21040 || id > 21055) {
                  return;
               }
            } else if (this.getProfession() == 2) {
               if (id < 8512 || id > 8527) {
                  return;
               }
            } else {
               if (this.getProfession() != 4) {
                  return;
               }

               if (id < 16768 || id > 16783) {
                  return;
               }
            }
         }

         if (id > 0) {
            if (!this.isCompatiblePlayerShape(playershape[index])) {
               SIChat.addChatHint("Trạng thái hiện tại không dúng, không thể dùng" + skillName[index] + "!", (Vector[])null);
               return;
            }

            if (this.getProfession() == 2) {
               switch(isFinality[index]) {
               case 1:
                  if (this.getSkillLimit() == 0) {
                     SIChat.addChatHint("Số lần nhấp liên tục không đủ, không thể dùng" + skillName[index] + "!", (Vector[])null);
                     return;
                  }
               }
            }

            boolean canUse = false;
            switch(skillTar[index]) {
            case 1:
               canUse = true;
               this.skill_OBJID = this.getID();
               break;
            case 2:
               if (GOManager.getCurrentTarget() instanceof ActiveGO && ((ActiveGO)GOManager.getCurrentTarget()).getPrestige() < 0) {
                  canUse = true;
                  this.skill_OBJID = GOManager.getCurrentTarget().getID();
               }
               break;
            case 3:
               if (GOManager.getCurrentTarget() instanceof ActiveGO) {
                  if (((ActiveGO)GOManager.getCurrentTarget()).getPrestige() >= 0) {
                     if (this.isRezSkill(id) && ((ActiveGO)GOManager.getCurrentTarget()).getState() != 9) {
                        canUse = false;
                        break;
                     }

                     canUse = true;
                     this.skill_OBJID = GOManager.getCurrentTarget().getID();
                  } else if (!this.isRezSkill(id)) {
                     canUse = true;
                     this.skill_OBJID = this.getID();
                  }
               } else if (!this.isRezSkill(id)) {
                  canUse = true;
                  this.skill_OBJID = this.getID();
               }
               break;
            case 4:
               this.skill_OBJID = this.getID();
               canUse = true;
            }

            if (!canUse) {
               SIChat.addChatHint("Đối tượng thi triển lỗi, không thể dùng" + skillName[index] + "!", (Vector[])null);
               return;
            }

            if (this.skill_OBJID != this.getID() && this.isOutdis(index)) {
               SIChat.addChatHint("Cự ly thi triển quá xa, không thể dùng" + skillName[index] + "!", (Vector[])null);
               return;
            }

            if (this.getCurrentMP() >= skillEP[index]) {
               this.setSkill_ID(id);
               this.setSkill_Index(index);
               this.setState((byte)3);
            } else {
               SIChat.addChatHint("Điểm ma pháp không đủ!", (Vector[])null);
            }
         } else {
            this.attGenaral();
         }

      }
   }

   public boolean isCompatiblePlayerShape(short skillplayershape) {
      boolean dji = false;
      byte ind = true;
      short[] ps = new short[]{1, 2, 4, 8, 16, 32};

      for(byte i = 0; i < ps.length; ++i) {
         if ((skillplayershape & ps[i]) == ps[i]) {
            switch(i) {
            case 0:
               if (super.nType == 0 && !super.bStealth) {
                  dji = true;
               }
               break;
            case 1:
               if (super.nType == 4 && !super.bStealth) {
                  dji = true;
               }
               break;
            case 2:
               if (super.nType == 1 && !super.bStealth) {
                  dji = true;
               }
               break;
            case 3:
               if (super.nType == 3 && !super.bStealth) {
                  dji = true;
               }
               break;
            case 4:
               if (super.nType == 2 && !super.bStealth) {
                  dji = true;
               }
               break;
            case 5:
               if (super.nType == 0 && super.bStealth) {
                  dji = true;
               }
            }

            if (dji) {
               break;
            }
         }
      }

      return dji;
   }

   public boolean isRezSkill(short id) {
      return id >= 16496 && id <= 16511 || id >= 20656 && id <= 20671;
   }

   private boolean isOutdis(short ind) {
      boolean isIn = false;
      int distance = (super.x - GOManager.getCurrentTarget().getMapX()) * (super.x - GOManager.getCurrentTarget().getMapX()) + (super.y - GOManager.getCurrentTarget().getMapY()) * (super.y - GOManager.getCurrentTarget().getMapY());
      if (distance > skillDis[ind] * skillDis[ind]) {
         isIn = true;
      }

      return isIn;
   }

   private void approach(byte s) {
      if (this.canAttackTarget()) {
         switch(s) {
         case 102:
            if (super.isnotattck) {
               return;
            }

            if (this.attackTime()) {
               this.setPhyAttack();
            }
            break;
         case 103:
            switch(GOManager.currentTarget.type) {
            case 2:
            case 3:
               ActiveGO go = (ActiveGO)GOManager.currentTarget;
               if (go.getPrestige() < 0) {
                  this.attGenaral();
               } else {
                  MainCanvas.ni.send(851969);
                  this.setState((byte)0);
               }
               break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
               this.isCollection = true;
               this.downHorse();
               this.setState((byte)3);
            }
         case 104:
         default:
            break;
         case 105:
            if (super.isnotattck) {
               return;
            }

            getInstance();
            if (CD_Count[0] <= 0L) {
               this.setPhyAttack();
            }
         }

         this.setAimRowCol(this.getRow(), this.getCol());
         super.path = new int[0][];
      } else {
         this.movePath();
         if (super.state == 0) {
            this.setAimRowColFindPath(GOManager.currentTarget.getRow(), GOManager.currentTarget.getCol());
            if (super.path.length == 0) {
               this.turn();
            }

            if (s == 105) {
               if (super.path.length > 0) {
                  this.setState((byte)102);
               } else {
                  this.setState((byte)105);
               }
            } else {
               this.setState(s);
            }
         }
      }

   }

   private void turn() {
      switch(this.getDirection()) {
      case 1:
         this.setDirection((byte)3);
         super.bRMirror = false;
         break;
      case 2:
         this.setDirection((byte)4);
         super.bRMirror = true;
         break;
      case 3:
         this.setDirection((byte)1);
         super.bRMirror = false;
         break;
      case 4:
         this.setDirection((byte)2);
         super.bRMirror = false;
         break;
      case 5:
         this.setDirection((byte)8);
         super.bRMirror = true;
         break;
      case 6:
         this.setDirection((byte)7);
         super.bRMirror = false;
         break;
      case 7:
         this.setDirection((byte)6);
         super.bRMirror = true;
         break;
      case 8:
         this.setDirection((byte)5);
         super.bRMirror = false;
      }

   }

   public void changeTarget(int type) {
      if (GOManager.currentTarget != null && type != 1) {
         int size = GOManager.curGameObj.length;
         int index = Util.getArrayIndex(GOManager.curGameObj, GOManager.currentTarget);

         for(int i = size - 1; i >= 0; --i) {
            int t = i + index;
            t %= size;
            if (GOManager.curGameObj[t].type != 9) {
               switch(type) {
               case 0:
                  if (!GOManager.isShowOtherPlayer && GOManager.curGameObj[t].type == 1 && GOManager.curGameObj[t] != this) {
                     continue;
                  }
               case 1:
               default:
                  break;
               case 2:
                  if (GOManager.curGameObj[t] instanceof ActiveGO && ((ActiveGO)GOManager.curGameObj[t]).getPrestige() < 0) {
                     continue;
                  }
                  break;
               case 3:
                  if (GOManager.curGameObj[t] instanceof PassiveGO || GOManager.curGameObj[t] instanceof ActiveGO && ((ActiveGO)GOManager.curGameObj[t]).getPrestige() >= 0 && GOManager.curGameObj[t] != this) {
                     continue;
                  }
               }

               if (this.canBeSetTarget(GOManager.curGameObj[t])) {
                  GOManager.currentTarget = GOManager.curGameObj[t];
                  break;
               }
            }
         }
      } else {
         GOManager.currentTarget = this;
      }

      this.changeTargetRound();
   }

   public final void changeTargetRound() {
      switch(GOManager.currentTarget.getType()) {
      case 1:
      case 2:
      case 3:
         switch(((ActiveGO)GOManager.currentTarget).getPrestige()) {
         case -4:
         case -3:
         case -2:
         case -1:
         case 0:
            if (this.getLevel() > ((ActiveGO)GOManager.currentTarget).getLevel() + 20) {
               GOManager.selectCircle.setSpecial(GOManager.currentTarget, (short)2, (byte)-1);
            } else {
               GOManager.selectCircle.setSpecial(GOManager.currentTarget, (short)1, (byte)-1);
            }

            return;
         case 1:
         case 2:
         case 3:
         case 4:
            isAttacking = false;
            GOManager.selectCircle.setSpecial(GOManager.currentTarget, (short)0, (byte)-1);
            return;
         default:
            return;
         }
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 10:
      case 11:
         GOManager.selectCircle.setSpecial(GOManager.currentTarget, (short)0, (byte)-1);
      case 9:
      }

   }

   private boolean canBeSetTarget(GameObject target) {
      int instance = false;
      int instance;
      if (target instanceof ActiveGO) {
         ActiveGO go = (ActiveGO)target;
         if (go.getPrestige() < 0) {
            instance = TARGET_MONSTER_ROUND_SQUARE;
         } else {
            instance = TARGET_NPC_ROUND_SQUARE;
         }
      } else {
         instance = TARGET_NPC_ROUND_SQUARE;
      }

      return (super.x - target.getMapX()) * (super.x - target.getMapX()) + (super.y - target.getMapY()) * (super.y - target.getMapY()) < instance;
   }

   private void findObject(short x, short y) {
      boolean isAction = false;

      for(int i = GOManager.curGameObj.length - 1; i >= 0; --i) {
         ActiveGO go;
         if ((GOManager.curGameObj[i].type == 1 || GOManager.curGameObj[i].type == 2 || GOManager.curGameObj[i].type == 3) && GOManager.curGameObj[i].getState() != 9 || GOManager.curGameObj[i].type == 1 && GOManager.curGameObj[i].getState() == 9) {
            go = (ActiveGO)GOManager.curGameObj[i];
            if (MainCanvas.isPointInRect(go.getSadGODx() + go.getDrawX(), go.getSadGODy() + go.getDrawY(), go.getSadGOWidth(), go.getSadGOHeight())) {
               isAction = true;
               if (go.type == 1 && go != getInstance() && !GOManager.isShowOtherPlayer) {
                  isAction = false;
               }
            }
         } else if (GOManager.curGameObj[i].type != 4 && GOManager.curGameObj[i].type != 5 && GOManager.curGameObj[i].type != 6 && GOManager.curGameObj[i].type != 7 && GOManager.curGameObj[i].type != 8) {
            if ((GOManager.curGameObj[i].type == 10 || GOManager.curGameObj[i].type == 11) && MainCanvas.isPointInRect(GOManager.curGameObj[i].drawX - (MainCanvas.red_flag.frame_w >> 1), GOManager.curGameObj[i].drawY - (MainCanvas.red_flag.frame_h >> 1), MainCanvas.red_flag.frame_w, MainCanvas.red_flag.frame_h)) {
               isAction = true;
            }
         } else if (MainCanvas.isPointInRect(GOManager.curGameObj[i].drawX - (MainCanvas.kuangimg.frame_w >> 1), GOManager.curGameObj[i].drawY - (MainCanvas.kuangimg.frame_h >> 1), MainCanvas.kuangimg.frame_w, MainCanvas.kuangimg.frame_h)) {
            isAction = true;
         }

         if (isAction) {
            if (this.canBeSetTarget(GOManager.curGameObj[i])) {
               if (GOManager.curGameObj[i] == GOManager.getCurrentTarget()) {
                  if (GOManager.curGameObj[i].type != 2 && (GOManager.curGameObj[i].type != 1 || GOManager.curGameObj[i] == getInstance())) {
                     this.fireKeyAction(false);
                  } else {
                     if (!isAttacking) {
                        this.fireKeyAction(false);
                        isAttacking = true;
                     }

                     if (GOManager.curGameObj[i].type == 1) {
                        go = (ActiveGO)GOManager.curGameObj[i];
                        if (go.getPrestige() >= 0) {
                           isAttacking = false;
                        }
                     }
                  }
               } else {
                  isAttacking = false;
                  GOManager.setCurrentTarget(GOManager.curGameObj[i]);
                  this.changeTargetRound();
                  super.SkillTime_Count = 0;
                  this.setState((byte)0);
               }
            }

            return;
         }
      }

      short tmpX = (short)(MainCanvas.curPointerX + Map.currentWindowX);
      short tmpY = (short)(MainCanvas.curPointerY + Map.currentWindowY);
      short col = Map.getCurrentColumn(tmpY, tmpX);
      short row = Map.getCurrentRow(tmpY, tmpX);
      if (Map.getInstance().isFloor(col, row)) {
         super.SkillTime_Count = 0;
         isAttacking = false;
         this.setState((byte)104);
         this.setAimRowColFindPath(row, col);
         mouseTimer = MainCanvas.mouseImg.frames;
         mouseDrawX = (short)(MainCanvas.curPointerX - (MainCanvas.mouseImg.frame_w >> 1) + Map.currentWindowX);
         mouseDrawY = (short)(MainCanvas.curPointerY - (MainCanvas.mouseImg.frame_h >> 1) + Map.currentWindowY);
      }

   }

   private GameObject findNearBy() {
      GameObject ob = null;
      int minDistance = ACTION_ROUND_MONSTER_SQUARE;
      int i;
      int distance;
      if (GOManager.isShowOtherPlayer) {
         for(i = GOManager.curGameObj.length - 1; i >= 0; --i) {
            if (GOManager.curGameObj[i].type == 1 && ((ActiveGO)GOManager.curGameObj[i]).getPrestige() < 0 && GOManager.curGameObj[i].getState() != 9) {
               distance = (super.x - GOManager.curGameObj[i].getMapX()) * (super.x - GOManager.curGameObj[i].getMapX()) + (super.y - GOManager.curGameObj[i].getMapY()) * (super.y - GOManager.curGameObj[i].getMapY());
               if (distance < minDistance) {
                  minDistance = distance;
                  ob = GOManager.curGameObj[i];
               }
            }
         }
      }

      if (ob != null) {
         return ob;
      } else {
         for(i = GOManager.curGameObj.length - 1; i >= 0; --i) {
            if ((GOManager.curGameObj[i].type == 2 || GOManager.curGameObj[i].type == 3) && ((ActiveGO)GOManager.curGameObj[i]).getPrestige() < 0 && GOManager.curGameObj[i].getState() != 9) {
               distance = (super.x - GOManager.curGameObj[i].getMapX()) * (super.x - GOManager.curGameObj[i].getMapX()) + (super.y - GOManager.curGameObj[i].getMapY()) * (super.y - GOManager.curGameObj[i].getMapY());
               if (distance < minDistance) {
                  minDistance = distance;
                  ob = GOManager.curGameObj[i];
               }
            }
         }

         if (ob != null) {
            return ob;
         } else {
            minDistance = ACTION_ROUND_NPC_SQUARE;

            for(i = GOManager.curGameObj.length - 1; i >= 0; --i) {
               if (GOManager.curGameObj[i].type == 4 || GOManager.curGameObj[i].type == 5 || GOManager.curGameObj[i].type == 6 || GOManager.curGameObj[i].type == 7 || GOManager.curGameObj[i].type == 8) {
                  distance = (super.x - GOManager.curGameObj[i].getMapX()) * (super.x - GOManager.curGameObj[i].getMapX()) + (super.y - GOManager.curGameObj[i].getMapY()) * (super.y - GOManager.curGameObj[i].getMapY());
                  if (distance < minDistance) {
                     minDistance = distance;
                     ob = GOManager.curGameObj[i];
                  }
               }
            }

            if (ob != null) {
               return ob;
            } else {
               for(i = GOManager.curGameObj.length - 1; i >= 0; --i) {
                  if ((GOManager.curGameObj[i].type == 2 || GOManager.curGameObj[i].type == 3) && ((ActiveGO)GOManager.curGameObj[i]).getPrestige() >= 0 && GOManager.curGameObj[i].getState() != 9) {
                     distance = (super.x - GOManager.curGameObj[i].getMapX()) * (super.x - GOManager.curGameObj[i].getMapX()) + (super.y - GOManager.curGameObj[i].getMapY()) * (super.y - GOManager.curGameObj[i].getMapY());
                     if (distance < minDistance) {
                        minDistance = distance;
                        ob = GOManager.curGameObj[i];
                     }
                  }
               }

               if (ob != null) {
                  return ob;
               } else {
                  if (GOManager.isShowOtherPlayer) {
                     for(i = GOManager.curGameObj.length - 1; i >= 0; --i) {
                        if (GOManager.curGameObj[i] != this && GOManager.curGameObj[i].type == 1 && ((ActiveGO)GOManager.curGameObj[i]).getPrestige() >= 0 && GOManager.curGameObj[i].getState() != 9) {
                           distance = (super.x - GOManager.curGameObj[i].getMapX()) * (super.x - GOManager.curGameObj[i].getMapX()) + (super.y - GOManager.curGameObj[i].getMapY()) * (super.y - GOManager.curGameObj[i].getMapY());
                           if (distance < minDistance) {
                              minDistance = distance;
                              ob = GOManager.curGameObj[i];
                           }
                        }
                     }
                  }

                  if (ob != null) {
                     return ob;
                  } else {
                     return ob;
                  }
               }
            }
         }
      }
   }

   private void findTargetAction() {
      GameObject ob = this.findNearBy();
      if (ob != null) {
         GOManager.currentTarget = ob;
         ActiveGO go;
         switch(ob.type) {
         case 1:
            go = (ActiveGO)GOManager.currentTarget;
            if (go.getPrestige() < 0) {
               isAttacking = true;
               this.attGenaral();
            }
            break;
         case 2:
         case 3:
            go = (ActiveGO)GOManager.currentTarget;
            if (go.getPrestige() < 0) {
               isAttacking = true;
               this.attGenaral();
            } else if (this.canAttackTarget()) {
               MainCanvas.ni.send(851969);
               this.setState((byte)0);
            } else {
               this.setState((byte)103);
               this.setAimRowColFindPath(((ActiveGO)GOManager.currentTarget).getAimRow(), ((ActiveGO)GOManager.currentTarget).getAimCol());
            }
            break;
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
            if (this.canAttackTarget()) {
               this.isCollection = true;
               this.downHorse();
               this.setState((byte)3);
            } else {
               this.setState((byte)103);
               this.setAimRowColFindPath(((PassiveGO)GOManager.currentTarget).getRow(), ((PassiveGO)GOManager.currentTarget).getCol());
            }
         }

         this.changeTargetRound();
      } else {
         if (super.isnotattck) {
            return;
         }

         this.downHorse();
         this.setState((byte)2);
         super.nNextFrame = -1;
      }

   }

   public final boolean currentTargetIsFar() {
      int instance = false;
      int instance;
      if (GOManager.currentTarget instanceof ActiveGO) {
         ActiveGO go = (ActiveGO)GOManager.currentTarget;
         if (go.getPrestige() < 0) {
            instance = RETURN_MONSTER_ROUND_SQUARE;
         } else {
            instance = RETURN_NPC_ROUND_SQUARE;
         }
      } else {
         instance = RETURN_NPC_ROUND_SQUARE;
      }

      return this.getState() != 102 && (super.x - GOManager.currentTarget.getMapX()) * (super.x - GOManager.currentTarget.getMapX()) + (super.y - GOManager.currentTarget.getMapY()) * (super.y - GOManager.currentTarget.getMapY()) > instance;
   }

   public void setPoiontNPC() {
      for(int i = GOManager.curGameObj.length - 1; i >= 0; --i) {
         if (GOManager.curGameObj[i].nCol == super.nCol && GOManager.curGameObj[i].nRow == super.nRow && GOManager.curGameObj[i].type == 3) {
            GOManager.currentTarget = GOManager.curGameObj[i];
            ActiveGO go = (ActiveGO)GOManager.currentTarget;
            this.changeTargetRound();
            if (go.getPrestige() >= 0) {
               MainCanvas.ni.send(851969);
            }

            return;
         }
      }

   }

   private boolean canAttackTarget() {
      return this.canAttack(GOManager.currentTarget);
   }

   private boolean canAttack(GameObject objTarget) {
      if ((super.x - objTarget.getMapX()) * (super.x - objTarget.getMapX()) + (super.y - objTarget.getMapY()) * (super.y - objTarget.getMapY()) <= ATTACK_ROUND_SQUARE) {
         boolean isLeft = super.x >= objTarget.getMapX();
         boolean isUP = super.y >= objTarget.getMapY();
         switch(super.direction) {
         case 1:
         case 8:
            if (!isUP) {
               return true;
            }

            return false;
         case 2:
         case 7:
            if (isLeft) {
               return true;
            }

            return false;
         case 3:
         case 5:
            if (isUP) {
               return true;
            }

            return false;
         case 4:
         case 6:
            if (!isLeft) {
               return true;
            }

            return false;
         default:
            return false;
         }
      } else {
         return false;
      }
   }

   public final void setCurrentEXP(long cexp) {
      this.curEXP = cexp;
   }

   public final void setMAXEXP(long mexp) {
      this.maxEXP = mexp;
   }

   public final long getCurrentEXP() {
      return this.curEXP;
   }

   public final long getMAXEXP() {
      return this.maxEXP;
   }

   public short getSelStudySkillID() {
      return this.selStudySkillID;
   }

   public void setSelStudySkillID(short selStudySkillID) {
      this.selStudySkillID = selStudySkillID;
   }

   public byte getCanUseSkillCount() {
      return this.canUseSkillCount;
   }

   public void setCanUseSkillCount(byte canUseSkillCount) {
      this.canUseSkillCount = canUseSkillCount;
   }

   public int getLoverId() {
      return this.loverId;
   }

   public void setLoverId(int loverId) {
      this.loverId = loverId;
   }

   public int getLoverState() {
      return this.loverState;
   }

   public void setLoverState(byte loverState) {
      this.loverState = loverState;
   }

   public String getLoverName() {
      return this.loverName;
   }

   public void setLoverName(String loverName) {
      this.loverName = loverName;
   }

   public byte getSkillLimit() {
      return this.skillLimit;
   }

   public void setSkillLimit(byte skillLimit) {
      this.skillLimit = skillLimit;
   }

   public boolean getAttacked() {
      return this.isAttacked;
   }

   public void setAttacked(boolean isAttacked) {
      this.isAttacked = isAttacked;
      if (isAttacked && !UIForm.isInitAtt) {
         UIForm.isInitAtt = true;
         SIChat.addChatLocal("Đang bị tấn công!", true);
      } else {
         UIForm.isInitAtt = false;
      }

   }

   public void draw(Graphics g) {
      super.draw(g);
   }

   public void drawSkillTimer(Graphics g) {
      int hight = false;
      int hight;
      if (MainCanvas.isLargeScreen) {
         hight = MainCanvas.screenH - 36 - SIChat.buff.getHeight() - 7 - 20;
      } else {
         hight = MainCanvas.screenH - 36 - 2 * MainCanvas.CHARH - 8;
      }

      int dx;
      int dwidth;
      if (this.getSkill_ID() == 0 && (GOManager.getCurrentTarget().getType() == 4 || GOManager.getCurrentTarget().getType() == 5 || GOManager.getCurrentTarget().getType() == 6 || GOManager.getCurrentTarget().getType() == 7 || GOManager.getCurrentTarget().getType() == 8 || GOManager.getCurrentTarget().getType() == 11)) {
         this.drawSkillTimerRect(g, hight);
         dx = MainCanvas.screenW - 50 >> 1;
         dwidth = super.SkillTime_Count * 50 / (this.COLLECTION_TIME / 120);
         Util.fillRect(g, dx, hight + 2, dwidth, 3, 16776960);
         Util.drawImage(g, MainCanvas.magicTT, dx + dwidth - MainCanvas.magicTT.getWidth(), hight + 1, 0);
      } else if (GOManager.getCurrentTarget().getType() == 10 && this.isGettingFlag != 0) {
         this.drawSkillTimerRect(g, hight);
         dx = MainCanvas.screenW - 50 >> 1;
         dwidth = this.baqiCount * 50 / (this.FALG_TIME / 120);
         Util.fillRect(g, dx, hight + 2, dwidth, 3, 16776960);
         Util.drawImage(g, MainCanvas.magicTT, dx + dwidth - MainCanvas.magicTT.getWidth(), hight + 1, 0);
      } else if (skilltimer[this.getSkill_Index()] != 0) {
         this.drawSkillTimerRect(g, hight);
         dx = MainCanvas.screenW - 50 >> 1;
         dwidth = super.SkillTime_Count * 50 / (skilltimer[this.getSkill_Index()] / 120);
         Util.fillRect(g, dx, hight + 2, dwidth, 3, 16776960);
         Util.drawImage(g, MainCanvas.magicTT, dx + dwidth - MainCanvas.magicTT.getWidth(), hight + 1, 0);
      }

   }

   public void drawSkillTimerRect(Graphics g, int hight) {
      Util.drawRect(g, MainCanvas.screenW - 50 - 4 >> 1, hight, 54, 7, Cons.COLOR_TEAM_BORDER_1);
      Util.drawRect(g, (MainCanvas.screenW - 50 - 4 >> 1) + 1, hight + 1, 52, 5, Cons.COLOR_TEAM_BORDER_2);
      Util.fillRect(g, MainCanvas.screenW - 50 >> 1, hight + 2, 50, 3, 0);
   }

   public void drawOriginalRole(Graphics g, byte camp, byte phyle, byte sex, byte profession) {
   }

   public static void drawSelectRole(Graphics g) {
      for(int k = GOManager.curGameObj.length - 1; k >= 0; --k) {
         ActiveGO tmpgo = (ActiveGO)GOManager.curGameObj[k];
         tmpgo.draw(g);
      }

   }

   public void move(byte dir) {
      if (super.direction != dir) {
         this.setDirection(dir);
      }

      this.moveStep(dir);
      this.canMove();
   }

   public boolean canMove() {
      int tmpStep = super.curStep % 7;
      switch(super.direction) {
      case 1:
         if (!Map.getInstance().isFloor(super.nCol + 1, super.nRow + 1)) {
            if (super.x <= Map.getCurrentCentreX(super.nCol, super.nRow)) {
               if (!Map.getInstance().isFloor(super.nCol, super.nRow + 1)) {
                  if (Map.getInstance().isFloor(super.nCol + 1, super.nRow)) {
                     this.moveStep((byte)8);
                  }
               } else {
                  this.moveStep((byte)7);
               }
            } else if (!Map.getInstance().isFloor(super.nCol + 1, super.nRow)) {
               if (Map.getInstance().isFloor(super.nCol, super.nRow + 1)) {
                  this.moveStep((byte)7);
               }
            } else {
               this.moveStep((byte)8);
            }

            super.y = (short)(super.y - ActiveGO.STEP[tmpStep][1]);
         } else if (!Map.getInstance().isFloor(super.nCol, super.nRow + 1)) {
            if (Map.getInstance().isFloor(super.nCol + 1, super.nRow)) {
               this.moveStep((byte)8);
            }

            super.y = (short)(super.y - ActiveGO.STEP[tmpStep][1]);
         } else if (!Map.getInstance().isFloor(super.nCol + 1, super.nRow)) {
            if (Map.getInstance().isFloor(super.nCol, super.nRow + 1)) {
               this.moveStep((byte)7);
            }

            super.y = (short)(super.y - ActiveGO.STEP[tmpStep][1]);
         }
         break;
      case 2:
         if (!Map.getInstance().isFloor(super.nCol - 1, super.nRow + 1)) {
            if (super.y <= Map.getCurrentCentreY(super.nCol, super.nRow)) {
               if (!Map.getInstance().isFloor(super.nCol - 1, super.nRow)) {
                  if (Map.getInstance().isFloor(super.nCol, super.nRow + 1)) {
                     this.moveStep((byte)7);
                  }
               } else {
                  this.moveStep((byte)5);
               }
            } else if (!Map.getInstance().isFloor(super.nCol, super.nRow + 1)) {
               if (Map.getInstance().isFloor(super.nCol - 1, super.nRow)) {
                  this.moveStep((byte)5);
               }
            } else {
               this.moveStep((byte)7);
            }

            super.x = (short)(super.x + ActiveGO.STEP[tmpStep][0]);
         } else if (!Map.getInstance().isFloor(super.nCol - 1, super.nRow)) {
            if (Map.getInstance().isFloor(super.nCol, super.nRow + 1)) {
               this.moveStep((byte)7);
            }

            super.x = (short)(super.x + ActiveGO.STEP[tmpStep][0]);
         } else if (!Map.getInstance().isFloor(super.nCol, super.nRow + 1)) {
            if (Map.getInstance().isFloor(super.nCol - 1, super.nRow)) {
               this.moveStep((byte)5);
            }

            super.x = (short)(super.x + ActiveGO.STEP[tmpStep][0]);
         }
         break;
      case 3:
         if (!Map.getInstance().isFloor(super.nCol - 1, super.nRow - 1)) {
            if (super.x <= Map.getCurrentCentreX(super.nCol, super.nRow)) {
               if (!Map.getInstance().isFloor(super.nCol - 1, super.nRow)) {
                  if (Map.getInstance().isFloor(super.nCol, super.nRow - 1)) {
                     this.moveStep((byte)6);
                  }
               } else {
                  this.moveStep((byte)5);
               }
            } else if (!Map.getInstance().isFloor(super.nCol, super.nRow - 1)) {
               if (Map.getInstance().isFloor(super.nCol - 1, super.nRow)) {
                  this.moveStep((byte)5);
               }
            } else {
               this.moveStep((byte)6);
            }

            super.y = (short)(super.y + ActiveGO.STEP[tmpStep][1]);
         } else if (!Map.getInstance().isFloor(super.nCol - 1, super.nRow)) {
            if (Map.getInstance().isFloor(super.nCol, super.nRow - 1)) {
               this.moveStep((byte)6);
            }

            super.y = (short)(super.y + ActiveGO.STEP[tmpStep][1]);
         } else if (!Map.getInstance().isFloor(super.nCol, super.nRow - 1)) {
            if (Map.getInstance().isFloor(super.nCol - 1, super.nRow)) {
               this.moveStep((byte)5);
            }

            super.y = (short)(super.y + ActiveGO.STEP[tmpStep][1]);
         }
         break;
      case 4:
         if (!Map.getInstance().isFloor(super.nCol + 1, super.nRow - 1)) {
            if (super.y <= Map.getCurrentCentreY(super.nCol, super.nRow)) {
               if (!Map.getInstance().isFloor(super.nCol, super.nRow - 1)) {
                  if (Map.getInstance().isFloor(super.nCol + 1, super.nRow)) {
                     this.moveStep((byte)8);
                  }
               } else {
                  this.moveStep((byte)6);
               }
            } else if (!Map.getInstance().isFloor(super.nCol + 1, super.nRow)) {
               if (Map.getInstance().isFloor(super.nCol, super.nRow - 1)) {
                  this.moveStep((byte)6);
               }
            } else {
               this.moveStep((byte)8);
            }

            super.x = (short)(super.x - ActiveGO.STEP[tmpStep][0]);
         } else if (!Map.getInstance().isFloor(super.nCol, super.nRow - 1)) {
            if (Map.getInstance().isFloor(super.nCol + 1, super.nRow)) {
               this.moveStep((byte)8);
            }

            super.x = (short)(super.x - ActiveGO.STEP[tmpStep][0]);
         } else if (!Map.getInstance().isFloor(super.nCol + 1, super.nRow)) {
            if (Map.getInstance().isFloor(super.nCol, super.nRow - 1)) {
               this.moveStep((byte)6);
            }

            super.x = (short)(super.x - ActiveGO.STEP[tmpStep][0]);
         }
      }

      return false;
   }

   public final void keyInOtherPlayerMenu() {
      if (MainCanvas.SUPPORT_POINTER && MainCanvas.isInputDown(268435456) && UIMenu.otherPlayerMenu.pointIndex()) {
         MainCanvas.keySimPressed(131072);
      }

      int cmds = (Integer)UIMenu.otherPlayerMenu.getCmdIds().elementAt(UIMenu.otherPlayerMenu.getIndex());
      switch(cmds) {
      case -268435452:
         UIChat.intoPrivateChannel(GOManager.currentTarget.getID(), GOManager.currentTarget.getRoleName(), true);
         break;
      case -268435451:
         this.showActionIndex = 1;
         MainCanvas.ni.send(393233);
         MainCanvas.setGameState((byte)0);
         break;
      case -268435450:
         this.showActionIndex = 2;
         MainCanvas.ni.send(393233);
         MainCanvas.setGameState((byte)0);
         break;
      case 196626:
         MainCanvas.ni.send(cmds);
         MainCanvas.setGameState((byte)0);
         break;
      case 196638:
         GOManager.reportedPlayerID = GOManager.currentTarget.getID();
         MainCanvas.ni.send(cmds);
         MainCanvas.setGameState((byte)0);
         break;
      case 393229:
         MainCanvas.ni.send(cmds);
         MainCanvas.setGameState((byte)0);
         break;
      case 393230:
         MainCanvas.ni.send(cmds);
         MainCanvas.setGameState((byte)0);
         break;
      case 589835:
      case 589836:
         UITable.selectedPlayerId = GOManager.currentTarget.getID();
         MainCanvas.ni.send(cmds);
         MainCanvas.setGameState((byte)0);
         break;
      case 720897:
         SITeam.otherPlayerID = GOManager.currentTarget.getID();
         MainCanvas.ni.send(cmds);
         MainCanvas.setGameState((byte)0);
         break;
      case 1376269:
         MainCanvas.ni.send(cmds);
         MainCanvas.setGameState((byte)0);
         break;
      case 1638405:
         PCClan.otherPlayerID = GOManager.currentTarget.getID();
         MainCanvas.ni.send(cmds);
         MainCanvas.setGameState((byte)0);
         break;
      case 1835013:
         PCKing.otherPlayerID = GOManager.currentTarget.getID();
         MainCanvas.ni.send(cmds);
         MainCanvas.setGameState((byte)0);
         break;
      default:
         SIChat.addChat((byte)7, "Công năng này tạm thời chưa có.", -1, "", (Vector)null, (Vector[])null);
         MainCanvas.setGameState((byte)0);
      }

      UIMenu.otherPlayerMenu = null;
   }

   public int getClanId() {
      return this.clanId;
   }

   public void setClanId(int clanId) {
      this.clanId = clanId;
   }

   public byte getClanRight() {
      return this.clanRight;
   }

   public void setClanRight(byte clanRight) {
      this.clanRight = clanRight;
   }

   public int getKingId() {
      return this.kingId;
   }

   public void setKingId(int kingId) {
      this.kingId = kingId;
   }

   public byte getKingRight() {
      return this.kingRight;
   }

   public void setKingRight(byte kingRight) {
      this.kingRight = kingRight;
   }

   public boolean touchScreenAction() {
      boolean isInTeamRect = false;
      boolean isInScRect = false;
      boolean isSearchPath = false;
      boolean isInChat = false;
      if (MainCanvas.SUPPORT_POINTER && MainCanvas.isInputDown(268435456)) {
         byte y1;
         int drawSpace;
         int drawStartY;
         int touchID;
         if (MainCanvas.getState() == 5 && MainCanvas.getGameState() == 0 && SIChat.showMode == 0) {
            y1 = 0;
            drawSpace = MainCanvas.screenH - 36 - SIChat.buff.getHeight();
            drawStartY = MainCanvas.screenW;
            touchID = SIChat.buff.getHeight();
            if (MainCanvas.isPointInRect(y1, drawSpace, drawStartY, touchID)) {
               isInChat = true;
               MainCanvas.keySimPressed(MainCanvas.curGameKey[16]);
            }
         }

         int i;
         if (SITeam.teamMates != null) {
            int index = -1;
            drawSpace = 26;
            drawStartY = 2 + SIManager.SIDy;
            if (SITeam.isShowName) {
               drawSpace += MainCanvas.CHARH;
               drawStartY += MainCanvas.CHARH;
            }

            int x = 2;
            int w = 48;
            int h = 14;
            i = 0;

            int distance;
            for(distance = SITeam.teamMates.length; i < distance; ++i) {
               int y = drawStartY + i * drawSpace;
               if (MainCanvas.isPointInRect(x, y, w, h)) {
                  isInTeamRect = true;
                  index = i;
                  break;
               }
            }

            if (isInTeamRect) {
               for(i = GOManager.curGameObj.length - 1; i >= 0; --i) {
                  if (GOManager.curGameObj[i].ID == SITeam.teamMates[index].ID) {
                     distance = (super.x - GOManager.curGameObj[i].getMapX()) * (super.x - GOManager.curGameObj[i].getMapX()) + (super.y - GOManager.curGameObj[i].getMapY()) * (super.y - GOManager.curGameObj[i].getMapY());
                     if (distance <= ACTION_ROUND_NPC_SQUARE) {
                        GOManager.setCurrentTarget(GOManager.curGameObj[i]);
                        this.changeTargetRound();
                     }
                     break;
                  }
               }
            }
         }

         y1 = 2;
         int w1 = MainCanvas.touchMenu.frame_w;
         int h1 = MainCanvas.touchMenu.frame_h;
         touchID = -1;
         int cols = MainCanvas.touchMenu.cols;

         for(int i = 0; i < cols; ++i) {
            i = SIManager.TOUCH_DX + w1 * i;
            if (MainCanvas.isPointInRect(i, y1, w1, h1)) {
               isInScRect = true;
               touchID = i;
               break;
            }
         }

         if (isInScRect) {
            switch(touchID) {
            case 0:
               MainCanvas.keySimPressed(MainCanvas.curGameKey[16]);
               break;
            case 1:
               UIRightMenu.setRightMenuNum(1);
               break;
            case 2:
               MainCanvas.ni.send(458760);
               break;
            case 3:
               UIRightMenu.setRightMenuNum(3);
               break;
            case 4:
               UIRightMenu.setRightMenuNum(4);
               break;
            case 5:
               UIRightMenu.setRightMenuNum(8);
               break;
            case 6:
               UIRightMenu.setRightMenuNum(6);
               break;
            case 7:
               UIRightMenu.requestTeamUI();
               break;
            case 8:
               UIRightMenu.setRightMenuNum(9);
               break;
            case 9:
               UIRightMenu.setRightMenu();
               UIRightMenu.curMenu.setCurPositionIndex((byte)10);
               UIRightMenu.curMenu.dealPressNum();
               UIRightMenu.curMenu.setHaveFather(false);
               break;
            case 10:
               MainCanvas.ni.send(2425024);
               break;
            case 11:
               UIRightMenu.setRightMenu();
               UIRightMenu.curMenu.setCurPositionIndex((byte)11);
               UIRightMenu.curMenu.dealPressNum();
               UIRightMenu.curMenu.setHaveFather(false);
               break;
            case 12:
               MainCanvas.ni.send(2355697);
            }
         }

         if (!isInScRect && !isInTeamRect && MainCanvas.pointDownInRect(0, 0, MainCanvas.screenW, Map.MAP_H)) {
            isSearchPath = true;
            this.findObject(MainCanvas.curPointerX, MainCanvas.curPointerY);
         }
      }

      return isInScRect || isInTeamRect || isSearchPath || isInChat;
   }

   public boolean isLevelUpSpecialIndex(int sIndex) {
      for(int i = 0; i < LEVEL_UP_SPECIAL.length; ++i) {
         if (sIndex == LEVEL_UP_SPECIAL[i]) {
            return true;
         }
      }

      return false;
   }
}

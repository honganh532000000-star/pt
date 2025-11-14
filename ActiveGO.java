import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public abstract class ActiveGO extends GameObject {
   public static final byte SHOW_BUFF_NUM_MAX = 12;
   public static final byte DIRECT_NULL = 0;
   public static final byte DIRECT_UP = 3;
   public static final byte DIRECT_DOWN = 1;
   public static final byte DIRECT_LEFT = 2;
   public static final byte DIRECT_RIGHT = 4;
   public static final byte DIRECT_UP_LEFT = 5;
   public static final byte DIRECT_UP_RIGHT = 6;
   public static final byte DIRECT_DOWN_LEFT = 7;
   public static final byte DIRECT_DOWN_RIGHT = 8;
   public static final byte STATE_STAND = 0;
   public static final byte STATE_MOVE = 1;
   public static final byte STATE_PHY_ATTACK = 2;
   public static final byte STATE_SKILL_SONG = 3;
   public static final byte STATE_SKILL_ATTACK = 4;
   public static final byte STATE_DEAD = 9;
   public static final byte STATE_AUTO_MOVE = 10;
   public static final byte STATE_FOLLOW = 100;
   public static final byte STATE_WAIT = 101;
   public static final byte STATE_HUNT = 102;
   public static final byte STATE_APPROACH_NPC = 103;
   public static final byte STATE_APPROACH_POINT = 104;
   public static final byte STATE_WAIT_ATTACK = 105;
   public short[][] nImageIndex1 = null;
   public OtherPlayer cartPlayer = null;
   public static final byte HORSE_STATE_NONE = 0;
   public static final byte HORSE_STATE_HORSE = 1;
   public static final byte HORSE_STATE_CART = 2;
   byte horseState = 0;
   short[][] CURRRider = null;
   public static final short BEGIN_HORSE_FRAMEID = 94;
   short cartX = 0;
   short cartY = 0;
   boolean ism = false;
   public static final int HORSE_CART_DIS = 5;
   short[][] otherPX_Y_D = new short[10][4];
   byte horse_IND = 1;
   byte cart_IND = 0;
   public static final byte ATT_STYLE_UNKNOW = -1;
   public static final byte OBJ_BODY_ARMET = 1;
   public static final byte OBJ_BODY_LORICAE = 2;
   public static final byte OBJ_BODY_SCAPULAR = 3;
   public static final byte OBJ_BODY_RIGHTHAND = 4;
   public static final byte OBJ_BODY_SHIELD = 5;
   public static final byte OBJ_BODY_LEFTHAND = 6;
   public static final byte ATTACK_TABLE_NORMAL = 0;
   public static final byte ATTACK_TABLE_MISS = 1;
   public static final byte ATTACK_TABLE_DODGE = 2;
   public static final byte ATTACK_TABLE_PARRY = 3;
   public static final byte ATTACK_TABLE_BLOCK = 4;
   public static final byte ATTACK_TABLE_ABSORB = 5;
   public static final byte ATTACK_TABLE_CRIT = 6;
   public static final byte ATTACK_TABLE_CB = 7;
   public static final byte ATTACK_TABLE_RESIST = 8;
   public static final byte BUFF_DEBUFF_COUNT = 20;
   public byte[] buffTYPE = new byte[20];
   public static final byte STATE_BUFF_SPEED = 1;
   public static final byte STATE_BUFF_NOTMOVE = 2;
   public static final byte STATE_BUFF_NOTATTACK = 3;
   public static final byte STATE_BUFF_NOTSKILL = 4;
   public static final byte FOOT_SPEED_NOMAL = 49;
   public static final byte FOOT_SPEED_DOWN = 43;
   public static final byte FOOT_SPEED_UP1 = 58;
   public static final byte FOOT_SPEED_UP2 = 66;
   public static final byte SPEED_NONE = 27;
   public static final byte HORSE_SPEED_NOMAL = 18;
   public static final byte CART_SPEED_NOMAL = 33;
   public static final byte[][] STEP = new byte[][]{{5, 4, 4, 2}, {2, 2, 2, 1}, {7, 6, 6, 3}, {7, 6, 8, 4}, {7, 6, 8, 4}, {7, 6, 8, 4}, new byte[4]};
   public static final short[][] ORIEquipment = new short[][]{{369, 370, 371}, {25, 27, 28, 29, 30, 31, 32, 33, 34}, {46, 53, 54, 55}, {372}, {373, 377}, {374}, {369, 370, 371}, {225, 226, 227, 228, 229, 230, 231, 232, 233}, {46, 53, 54, 55}, {372}, {373, 377}, {374}};
   public byte nType = 0;
   public boolean bStealth = false;
   byte camp = 1;
   byte phyle = 1;
   byte sex = 0;
   byte profession = 1;
   byte prestige = 0;
   short level;
   byte changehptype = 0;
   byte isUseSkill = 0;
   short aimx = 0;
   short aimy = 0;
   int curHP = 0;
   int maxHP = 0;
   int curMP = 0;
   int maxMP = 0;
   public byte curStep;
   public boolean isnotattck = false;
   public boolean isnotcast = false;
   public boolean isHorseMove = false;
   public boolean isInvisible = false;
   public boolean isFreezing = false;
   public boolean isSpecialMonster = false;
   public byte backdirection;
   public byte direction;
   public short aimRow;
   public short aimCol;
   protected int[][] path = new int[0][];
   private byte countFindPathJump;
   public static final byte[][] HP_MOVEPATH_LR = new byte[][]{{5, 5}, {11, 9}, {16, 11}, {17, 12}, {18, 13}, {19, 13}, {20, 13}, {21, 12}, {23, 10}, {25, 8}, {29, 2}};
   public static final byte[][] HP_MOVEPATH_STRIGHT = new byte[][]{{17, -5}, {0, 10}, {0, 11}, {0, 12}, {0, 13}, {0, 14}, {0, 15}, {0, 16}, {0, 21}, {0, 29}, {0, 39}};
   public static final byte HP_CHANGE_COUNT;
   public static final byte SPECIAL_LEVEL_0 = 0;
   public static final byte SPECIAL_LEVEL_1 = 1;
   public static final byte SPECIAL_LEVEL_2 = 2;
   public Vector v_special = new Vector();
   public static int PLAYERARR_NAME;
   public static int OBJ_NAME_DY2;
   public static int OBJ_NAME_SPACE;
   byte offy = 0;
   public Vector hpStates = new Vector();
   public byte hpMoveDir = 1;
   public static final byte COPY_SHOW = 0;
   public static final byte COPY_CART = 1;
   int UPY = 0;

   static {
      HP_CHANGE_COUNT = (byte)HP_MOVEPATH_LR.length;
      PLAYERARR_NAME = 53;
      OBJ_NAME_DY2 = 60;
      OBJ_NAME_SPACE = 3;
   }

   public final void setCamp(byte c) {
      this.camp = c;
   }

   public final byte getCamp() {
      return this.camp;
   }

   public final void setPhyle(byte p) {
      this.phyle = p;
   }

   public final byte getPhyle() {
      return this.phyle;
   }

   public final void setSex(byte s) {
      this.sex = s;
   }

   public final byte getSex() {
      return this.sex;
   }

   public final void setProfession(byte p) {
      this.profession = p;
   }

   public final byte getProfession() {
      return this.profession;
   }

   public final void setPrestige(byte p) {
      this.prestige = p;
   }

   public final byte getPrestige() {
      return this.prestige;
   }

   public final void setLevel(short l) {
      this.level = l;
   }

   public final short getLevel() {
      return this.level;
   }

   public final void setChanHPType(byte cht) {
      this.changehptype = cht;
   }

   public final byte getChanHPType() {
      return this.changehptype;
   }

   public final void setIsUseSkill(byte isu) {
      this.isUseSkill = isu;
   }

   public final byte getIsUseSkill() {
      return this.isUseSkill;
   }

   public final void setAimMapXY(short ax, short ay) {
      this.aimx = ax;
      this.aimy = ay;
   }

   public final short getMapAimX() {
      return this.aimx;
   }

   public final short getMapAimY() {
      return this.aimy;
   }

   public final boolean setAimRowColFindPath(short row, short col) {
      this.aimCol = col;
      this.aimRow = row;
      if (super.nCol == this.aimCol && super.nRow == this.aimRow) {
         this.path = new int[0][];
         return false;
      } else {
         this.path = (new AStar()).findPath(super.nCol, super.nRow, this.aimCol, this.aimRow);
         return true;
      }
   }

   public final void setAimRowCol(short r, short c) {
      this.aimRow = r;
      this.aimCol = c;
   }

   public final short getAimRow() {
      return this.aimRow;
   }

   public final short getAimCol() {
      return this.aimCol;
   }

   public final byte getDirection() {
      return this.direction;
   }

   public final void setCurrentHP(int chp) {
      this.curHP = chp;
   }

   public final void setMAXHP(int mhp) {
      this.maxHP = mhp;
   }

   public final int getCurrentHP() {
      return this.curHP;
   }

   public final int getMAXHP() {
      return this.maxHP;
   }

   public final void setCurrentMP(int cmp) {
      this.curMP = cmp;
   }

   public final void setMAXMP(int mmp) {
      this.maxMP = mmp;
   }

   public final int getCurrentMP() {
      return this.curMP;
   }

   public final int getMAXMP() {
      return this.maxMP;
   }

   public boolean getSpecialMonster() {
      return this.isSpecialMonster;
   }

   public void setSpecialMonster(boolean isSpecialMonster) {
      this.isSpecialMonster = isSpecialMonster;
   }

   public void setTaskShowType(byte p) {
   }

   public final void movePath() {
      if (this.getMoveDirect()) {
         this.moveStep(this.getDirection());
      }

      ++this.countFindPathJump;
      this.setRowCol(this.getRow(super.x, super.y), this.getCol(super.x, super.y));
   }

   protected final void moveStep(byte dir) {
      int tmpStep = this.curStep % 7;
      switch(dir) {
      case 1:
         super.y = (short)(super.y + STEP[tmpStep][1]);
         break;
      case 2:
         super.x = (short)(super.x - STEP[tmpStep][0]);
         break;
      case 3:
         super.y = (short)(super.y - STEP[tmpStep][1]);
         break;
      case 4:
         super.x = (short)(super.x + STEP[tmpStep][0]);
         break;
      case 5:
         super.x = (short)(super.x - STEP[tmpStep][2]);
         super.y = (short)(super.y - STEP[tmpStep][3]);
         break;
      case 6:
         super.x = (short)(super.x + STEP[tmpStep][2]);
         super.y = (short)(super.y - STEP[tmpStep][3]);
         break;
      case 7:
         super.x = (short)(super.x - STEP[tmpStep][2]);
         super.y = (short)(super.y + STEP[tmpStep][3]);
         break;
      case 8:
         super.x = (short)(super.x + STEP[tmpStep][2]);
         super.y = (short)(super.y + STEP[tmpStep][3]);
      }

      this.isHorseMove = true;
   }

   protected final boolean getMoveDirect() {
      while(this.path.length > 0) {
         int dCol = this.path[0][0] - super.nCol;
         int dRow = this.path[0][1] - super.nRow;
         if (dCol != 0 || dRow != 0) {
            if (dCol > 0) {
               if (dRow > 0) {
                  this.setDirection((byte)1);
               } else if (dRow < 0) {
                  this.setDirection((byte)4);
               } else {
                  this.setDirection((byte)8);
               }
            } else if (dCol < 0) {
               if (dRow > 0) {
                  this.setDirection((byte)2);
               } else if (dRow < 0) {
                  this.setDirection((byte)3);
               } else {
                  this.setDirection((byte)5);
               }
            } else if (dRow > 0) {
               this.setDirection((byte)7);
            } else if (dRow < 0) {
               this.setDirection((byte)6);
            }

            return true;
         }

         this.path = AStar.resizeArray(this.path, 0, -1);
      }

      if (this.getState() == 104) {
         Player.getInstance().setPoiontNPC();
      }

      this.path = new int[0][];
      this.setStandState();
      return false;
   }

   public void setMoveState() {
      this.setState((byte)1);
   }

   public void setStandState() {
      this.setState((byte)0);
   }

   public abstract void setDirection(byte var1);

   public abstract byte getSadGODx();

   public abstract byte getSadGODy();

   public abstract byte getSadGOWidth();

   public abstract byte getSadGOHeight();

   public void drawName(Graphics g) {
      int horseDy = MainCanvas.CHARH + OBJ_NAME_SPACE - this.getSadGODy();
      int parrY = 0;
      if (this.horseState != 0) {
         horseDy = OBJ_NAME_DY2;
         parrY = 12;
      }

      if (this.getTitleName().length() == 0) {
         if (this.getID() == Player.getInstance().getID()) {
            this.offy = (byte)(this.offy ^ 1);
            if (!Map.m_bBattlefield) {
               Util.drawImage(g, MainCanvas.playerArrImg, super.drawX - (MainCanvas.playerArrImg.getWidth() >> 1), super.drawY - PLAYERARR_NAME - this.offy - parrY, 0);
            }

            if (Player.getInstance().getSkillLimit() != 0) {
               g.setColor(2625633);
               g.drawRect(super.drawX + 3 - 13, super.drawY - PLAYERARR_NAME - this.offy - parrY + MainCanvas.playerArrImg.getHeight(), 21, 2);
               g.setColor(16776960);
               if (Player.getInstance().getSkillLimit() == 5 && this.offy == 0) {
                  g.setColor(16711680);
               }

               g.fillRect(super.drawX + 3 - 13 + 1, super.drawY - PLAYERARR_NAME - this.offy - parrY + MainCanvas.playerArrImg.getHeight() + 1, Player.getInstance().getSkillLimit() * 4, 1);
            }
         } else if (this.getType() == 3) {
            if (this.getPrestige() <= 0) {
               Util.drawStringShadow(g, this.getRoleName(), super.drawX - (g.getFont().stringWidth(this.getRoleName()) >> 1), super.drawY - horseDy, this.getOBJNameCol(this.getPrestige()), 0);
            } else {
               Util.drawStringShadow(g, this.getRoleName(), super.drawX - (g.getFont().stringWidth(this.getRoleName()) >> 1), super.drawY - horseDy, 55551, 0);
            }

            this.drawTaskInfo(g, horseDy);
         } else {
            Util.drawStringShadow(g, this.getRoleName(), super.drawX - (g.getFont().stringWidth(this.getRoleName()) >> 1), super.drawY - horseDy, this.getOBJNameCol(this.getPrestige()), 0);
         }
      } else if (this.getType() == 3) {
         if (this.getPrestige() <= 0) {
            Util.drawStringShadow(g, this.getRoleName(), super.drawX - (g.getFont().stringWidth(this.getRoleName()) >> 1), super.drawY - horseDy - MainCanvas.CHARH, this.getOBJNameCol(this.getPrestige()), 0);
            Util.drawStringShadow(g, this.getTitleName(), super.drawX - (g.getFont().stringWidth(this.getTitleName()) >> 1), super.drawY - horseDy, this.getOBJNameCol(this.getPrestige()), 0);
         } else {
            Util.drawStringShadow(g, this.getRoleName(), super.drawX - (g.getFont().stringWidth(this.getRoleName()) >> 1), super.drawY - horseDy - MainCanvas.CHARH, 55551, 0);
            Util.drawStringShadow(g, this.getTitleName(), super.drawX - (g.getFont().stringWidth(this.getTitleName()) >> 1), super.drawY - horseDy, 55551, 0);
         }

         this.drawTaskInfo(g, horseDy + MainCanvas.CHARH);
      } else {
         if (this.getID() == Player.getInstance().getID()) {
            this.offy = (byte)(this.offy ^ 1);
            if (!Map.m_bBattlefield) {
               Util.drawImage(g, MainCanvas.playerArrImg, super.drawX, super.drawY - PLAYERARR_NAME - MainCanvas.CHARH - this.offy - parrY, 0);
            }

            if (Player.getInstance().getSkillLimit() != 0) {
               g.setColor(2625633);
               g.drawRect(super.drawX + 3 - 13, super.drawY - PLAYERARR_NAME - this.offy - parrY + MainCanvas.playerArrImg.getHeight(), 21, 2);
               g.setColor(16776960);
               if (Player.getInstance().getSkillLimit() == 5 && this.offy == 0) {
                  g.setColor(16711680);
               }

               g.fillRect(super.drawX + 3 - 13 + 1, super.drawY - PLAYERARR_NAME - this.offy - parrY + MainCanvas.playerArrImg.getHeight() + 1, Player.getInstance().getSkillLimit() * 4, 1);
            }
         } else {
            Util.drawStringShadow(g, this.getRoleName(), super.drawX - (g.getFont().stringWidth(this.getRoleName()) >> 1), super.drawY - horseDy - MainCanvas.CHARH, this.getOBJNameCol(this.getPrestige()), 0);
         }

         Util.drawStringShadow(g, this.getTitleName(), super.drawX - (g.getFont().stringWidth(this.getTitleName()) >> 1), super.drawY - horseDy, this.getOBJNameCol(this.getPrestige()), 0);
      }

   }

   public int getOBJNameCol(int cp) {
      int col = 0;
      switch(cp) {
      case -4:
      case -3:
      case -2:
         col = Cons.OBJ_NAME_COLOR[0];
         break;
      case -1:
         col = Cons.OBJ_NAME_COLOR[1];
         break;
      case 0:
         col = Cons.OBJ_NAME_COLOR[2];
         break;
      case 1:
      case 2:
      case 3:
      case 4:
         col = Cons.OBJ_NAME_COLOR[3];
      }

      return col;
   }

   public abstract void setState(byte var1);

   public abstract void motionState_tick();

   public void drawHpChange(Graphics g, int offx, int offy) {
      if (this.hpStates.size() != 0) {
         g.setColor(16711680);

         label119:
         for(int i = 0; i < this.hpStates.size(); ++i) {
            int[] fi = (int[])this.hpStates.elementAt(i);
            int[] tmpNum = (int[])null;
            int tmp = fi[2];
            byte md = 0;
            int color = 0;
            if (fi[1] < 0) {
               md = -1;
            } else if (fi[1] > 0) {
               md = 1;
            }

            byte ind = (byte)Math.abs(fi[1]);
            if (tmp == 0) {
               switch(this.getChanHPType()) {
               case 1:
               case 2:
               case 3:
               case 4:
               case 5:
                  color = 8;
                  MainCanvas.numberImg[color].draw(g, super.x - (MainCanvas.numberImg[color].frame_w >> 1) - Map.currentWindowX, super.y - Map.currentWindowY - HP_MOVEPATH_STRIGHT[ind][1] - 18, this.getChanHPType() - 1);
               }
            } else {
               if (tmp < 0) {
                  tmp = -tmp;
               }

               int length = (new String(String.valueOf(tmp))).length();
               tmpNum = new int[length];

               int j;
               for(j = length - 1; j >= 0; --j) {
                  tmpNum[j] = tmp % 10 + 1;
                  tmp /= 10;
               }

               int kk1;
               switch(this.getChanHPType()) {
               case 4:
               case 5:
                  color = 8;
                  MainCanvas.numberImg[color].draw(g, super.x - (MainCanvas.numberImg[color].frame_w >> 1) - Map.currentWindowX, super.y - Map.currentWindowY - HP_MOVEPATH_STRIGHT[ind][1] - 18, this.getChanHPType() - 1);
                  if (this.getID() == Player.getInstance().getID()) {
                     color = 0;
                  } else if (fi[0] == 1) {
                     color = 1;
                  } else {
                     color = 2;
                  }

                  MainCanvas.numberImg[color].draw(g, super.x - (MainCanvas.numberImg[color].frame_w * tmpNum.length >> 1) - Map.currentWindowX - (MainCanvas.numberImg[color].frame_w - 1) + md * HP_MOVEPATH_LR[ind][0], super.y - Map.currentWindowY - HP_MOVEPATH_LR[ind][1] - 18, 0);
                  j = 0;
                  kk1 = tmpNum.length;

                  while(true) {
                     if (j >= kk1) {
                        continue label119;
                     }

                     MainCanvas.numberImg[color].draw(g, super.x - (MainCanvas.numberImg[color].frame_w * tmpNum.length >> 1) - Map.currentWindowX + (MainCanvas.numberImg[color].frame_w - 1) * j + md * HP_MOVEPATH_LR[ind][0], super.y - Map.currentWindowY - HP_MOVEPATH_LR[ind][1] - 18, tmpNum[j]);
                     ++j;
                  }
               default:
                  if (this.getChanHPType() == 6) {
                     if (fi[2] > 0) {
                        if (ind == 0) {
                           color = 3;
                        } else {
                           color = 7;
                        }
                     } else if (fi[2] < 0) {
                        if (ind == 0) {
                           if (this.getID() == Player.getInstance().getID()) {
                              color = 0;
                           } else if (fi[0] == 1) {
                              color = 1;
                           } else {
                              color = 2;
                           }
                        } else if (this.getID() == Player.getInstance().getID()) {
                           color = 4;
                        } else if (fi[0] == 1) {
                           color = 5;
                        } else {
                           color = 6;
                        }
                     }

                     MainCanvas.numberImg[color].draw(g, super.x - (MainCanvas.numberImg[color].frame_w * tmpNum.length >> 1) - Map.currentWindowX - (MainCanvas.numberImg[color].frame_w - 2) + md * HP_MOVEPATH_STRIGHT[ind][0], super.y - Map.currentWindowY - HP_MOVEPATH_STRIGHT[ind][1] - 18, 0);
                     j = 0;

                     for(kk1 = tmpNum.length; j < kk1; ++j) {
                        MainCanvas.numberImg[color].draw(g, super.x - (MainCanvas.numberImg[color].frame_w * tmpNum.length >> 1) - Map.currentWindowX + (MainCanvas.numberImg[color].frame_w - 2) * j + md * HP_MOVEPATH_STRIGHT[ind][0], super.y - Map.currentWindowY - HP_MOVEPATH_STRIGHT[ind][1] - 18, tmpNum[j]);
                     }
                  } else {
                     if (fi[2] > 0) {
                        color = 3;
                     } else if (fi[2] < 0) {
                        if (this.getID() == Player.getInstance().getID()) {
                           color = 0;
                        } else if (fi[0] == 1) {
                           color = 1;
                        } else {
                           color = 2;
                        }
                     }

                     MainCanvas.numberImg[color].draw(g, super.x - (MainCanvas.numberImg[color].frame_w * tmpNum.length >> 1) - Map.currentWindowX - (MainCanvas.numberImg[color].frame_w - 1) + md * HP_MOVEPATH_LR[ind][0], super.y - Map.currentWindowY - HP_MOVEPATH_LR[ind][1] - 18, 0);
                     j = 0;

                     for(kk1 = tmpNum.length; j < kk1; ++j) {
                        MainCanvas.numberImg[color].draw(g, super.x - (MainCanvas.numberImg[color].frame_w * tmpNum.length >> 1) - Map.currentWindowX + (MainCanvas.numberImg[color].frame_w - 1) * j + md * HP_MOVEPATH_LR[ind][0], super.y - Map.currentWindowY - HP_MOVEPATH_LR[ind][1] - 18, tmpNum[j]);
                     }
                  }
               }
            }
         }

      }
   }

   public void saveHPChange(int[] hpc) {
      this.hpStates.addElement(hpc);
   }

   public void tickHpChangeVectorPop() {
      if (this.hpStates.size() != 0) {
         for(int i = 0; i < this.hpStates.size(); ++i) {
            this.hpMoveDir = (byte)(-this.hpMoveDir);
            int[] fi = (int[])this.hpStates.elementAt(i);
            if (fi[1] >= HP_CHANGE_COUNT - 1) {
               fi[1] = (byte)(HP_CHANGE_COUNT - 1);
               this.hpStates.removeElementAt(i);
            } else if (fi[1] <= -(HP_CHANGE_COUNT - 1)) {
               fi[1] = (byte)(-(HP_CHANGE_COUNT - 1));
               this.hpStates.removeElementAt(i);
            } else {
               if (fi[1] == 0) {
                  fi[1] = this.hpMoveDir;
               } else {
                  int var10002;
                  if (fi[1] < 0) {
                     var10002 = fi[1]--;
                  } else {
                     var10002 = fi[1]++;
                  }
               }

               this.hpStates.setElementAt(fi, i);
            }
         }

      }
   }

   public static ActiveGO copyGameObject(GameObject oldobj, byte type) {
      OtherPlayer old;
      OtherPlayer teM;
      switch(type) {
      case 0:
         switch(oldobj.type) {
         case 1:
            old = (OtherPlayer)oldobj;
            teM = new OtherPlayer();
            teM.type = oldobj.getType();
            OtherPlayer.sadGO = OtherPlayer.sadGO;
            teM.nNextFrame = 0;
            teM.nMirrorAndRef = old.nMirrorAndRef;
            teM.nImageIndex1 = old.nImageIndex1;
            teM.curStep = old.curStep;
            teM.setState((byte)0);
            teM.setDirection((byte)1);
            return teM;
         case 2:
         default:
            break;
         case 3:
            NPC old = (NPC)oldobj;
            NPC teM = new NPC();
            teM.type = oldobj.getType();
            teM.sadGO = old.sadGO;
            teM.apparel = old.apparel;
            teM.nNextFrame = 0;
            teM.currentFrameID = 0;
            teM.currentmotionID = 0;
            teM.curStep = old.curStep;
            teM.setState((byte)0);
            teM.setDirection((byte)1);
            return teM;
         }
      case 1:
         old = (OtherPlayer)oldobj;
         teM = new OtherPlayer();
         teM.type = 9;
         teM.ID = 0;
         teM.nImageIndex1 = old.nImageIndex1;
         teM.nMirrorAndRef = old.nMirrorAndRef;
         teM.nNextFrame = 0;
         teM.motionID = old.motionID;
         teM.horseState = 2;
         teM.setState((byte)0);
         teM.curStep = old.curStep;
         teM.setDirection(old.getDirection());
         teM.cartPlayer = null;
         return teM;
      default:
         return null;
      }
   }

   public void dismount() {
      if (this.cartPlayer == null) {
         this.releaseHorse();
      } else {
         this.releaseCart();
      }

   }

   public void changeRide(byte pos, short[][] pngfrmInd, boolean isgetOn) {
      int e;
      for(short n = 94; n < 124; ++n) {
         e = this.nImageIndex1[n].length;

         for(byte m = 0; m < e; ++m) {
            for(int z = 0; z < pos; ++z) {
               for(int e = 0; e < this.CURRRider[z].length; ++e) {
                  if (this.nImageIndex1[n][m] == this.CURRRider[z][e]) {
                     this.nImageIndex1[n][m] = pngfrmInd[z][e];
                  }
               }
            }
         }
      }

      for(int z = 0; z < pos; ++z) {
         for(e = 0; e < pngfrmInd[z].length; ++e) {
            this.CURRRider[z][e] = pngfrmInd[z][e];
         }
      }

   }

   public void getInHorse() {
      GOManager.delObj(this.cartPlayer);
      this.cartPlayer = null;
      this.cartX = 0;
      this.cartY = 0;
      this.isHorseMove = false;
      this.horse_IND = 1;
      this.cart_IND = 0;
      this.horseState = 1;
      this.setState((byte)0);
   }

   public void getInCart() {
      this.releaseCart();
      this.cartPlayer = (OtherPlayer)copyGameObject(this, (byte)1);
      this.cartPlayer.x = this.otherPX_Y_D[0][0] = super.x;
      this.cartPlayer.y = this.otherPX_Y_D[0][1] = super.y;
      this.otherPX_Y_D[0][2] = this.getDirection();
      this.cartPlayer.setDirection((byte)this.otherPX_Y_D[0][2]);
      this.horseState = 1;
      this.setState((byte)0);
   }

   public void releaseHorse() {
      this.horseState = 0;
      if (this.getState() != 9) {
         this.setState((byte)0);
      }

      short[][] pf_horse = new short[][]{{580, 581}, {582, 583}, {584, 585, 586, 587}, {588, 589}, {591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606}, {607, 608}};
      this.changeRide((byte)6, pf_horse, false);
   }

   public void releaseCart() {
      GOManager.delObj(this.cartPlayer);
      this.cartPlayer = null;
      this.cartX = 0;
      this.cartY = 0;
      this.isHorseMove = false;

      for(int i = 0; i < this.otherPX_Y_D.length; ++i) {
         for(int j = 0; j < this.otherPX_Y_D[i].length; ++j) {
            this.otherPX_Y_D[i][j] = 0;
         }
      }

      this.horse_IND = 1;
      this.cart_IND = 0;
      this.horseState = 0;
      if (this.getState() != 9) {
         this.setState((byte)0);
      }

      short[][] pf_horse = new short[][]{{580, 581}, {582, 583}, {584, 585, 586, 587}, {588, 589}, {591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606}, {607, 608}, {609, 610, 611, 612, 613, 614}, {615, 616, 617, 618, 619}, {620, 621}, {571, 572, 573}, {574}, {575, 576, 577}, {578, 579}};
      this.changeRide((byte)13, pf_horse, false);
   }

   public boolean addSpecial(short _x, short _y, short _motionID, byte lc, byte l) {
      boolean tmp = false;
      Special tmpSpecial = new Special(_x, _y, _motionID, lc, l);
      this.v_special.addElement(tmpSpecial);
      tmp = true;
      return tmp;
   }

   public boolean addSpecial(GameObject _obj, short _motionID, byte lc, byte l) {
      boolean tmp = false;
      Special tmpSpecial = new Special(_obj, _motionID, lc, l);
      this.v_special.addElement(tmpSpecial);
      tmp = true;
      return tmp;
   }

   private boolean delSpecial(Special obj) {
      return this.v_special.removeElement(obj);
   }

   public void delSpecial(short _motionID) {
      for(int i = 0; i < this.v_special.size(); ++i) {
         Special tmpSpecial = (Special)this.v_special.elementAt(i);
         if (tmpSpecial.curMotionIndex == _motionID) {
            this.v_special.removeElement(tmpSpecial);
            break;
         }
      }

   }

   public void delSpecial(int gid) {
      for(int i = 0; i < this.v_special.size(); ++i) {
         Special tmpSpecial = (Special)this.v_special.elementAt(i);
         if (tmpSpecial != null && tmpSpecial.speciaObj != null && tmpSpecial.speciaObj.getID() == gid && tmpSpecial.level != 2) {
            this.v_special.removeElement(tmpSpecial);
            --i;
         }
      }

   }

   private void specialTaxis() {
      byte level_0count = 0;

      for(int i = 0; i < this.v_special.size(); ++i) {
         Special tmpSpecial = (Special)this.v_special.elementAt(i);
         if (tmpSpecial.level == 0) {
            this.v_special.removeElementAt(i);
            this.v_special.insertElementAt(tmpSpecial, 0);
            ++level_0count;
         } else if (tmpSpecial.level == 1) {
            this.v_special.removeElementAt(i);
            this.v_special.insertElementAt(tmpSpecial, level_0count);
         }
      }

   }

   public void tickSpecial() {
      this.specialTaxis();

      for(int i = this.v_special.size() - 1; i >= 0; --i) {
         Special tmpSpecial = (Special)this.v_special.elementAt(i);
         tmpSpecial.tick();
         if (tmpSpecial.isFinish()) {
            this.delSpecial(tmpSpecial);
         }
      }

   }

   public void drawSpecial(Graphics g) {
      for(int i = 0; i < this.v_special.size(); ++i) {
         Special tmpSpecial = (Special)this.v_special.elementAt(i);
         if (this instanceof Player && Player.getInstance().isLevelUpSpecialIndex(tmpSpecial.curMotionIndex)) {
            if (MainCanvas.getGameState() == 0 && MainCanvas.curTopForm == null) {
               tmpSpecial.draw(g);
            }
         } else {
            tmpSpecial.draw(g);
         }
      }

   }

   private void drawTaskInfo(Graphics g, int horseDy) {
      if (this instanceof NPC) {
         NPC npc = (NPC)this;
         if (((NPC)this).getTaskShowType() != 0) {
            int offY = MainCanvas.CHARH - MainCanvas.taskInfoMImg.frame_h >> 1;
            switch(((NPC)this).getTaskShowType()) {
            case 4:
               if (MainCanvas.countTick % 6 >= 3) {
                  MainCanvas.taskInfoMImg.draw(g, super.drawX - (g.getFont().stringWidth(this.getRoleName()) >> 1), super.drawY - horseDy + offY, 0);
               }
            case 5:
            default:
               break;
            case 6:
               if (MainCanvas.countTick % 6 >= 3) {
                  MainCanvas.taskInfoMImg.draw(g, super.drawX - (g.getFont().stringWidth(this.getRoleName()) >> 1), super.drawY - horseDy + offY, 1);
               }
            }
         }
      }

   }

   public void drawUpgrade(Graphics g) {
      if (Player.getInstance().m_bUpgrade && MainCanvas.getGameState() == 0 && MainCanvas.curTopForm == null) {
         int x = super.drawX - (MainCanvas.upgradeMImg.getWidth() >> 1);
         int y = super.drawY - (MainCanvas.upgradeMImg.getHeight() << 2);
         Util.drawImage(g, MainCanvas.upgradeMImg, x, y - this.UPY, 0);
         ++this.UPY;
      }

      if (this.UPY != 0 && this.UPY > 6) {
         this.UPY = 0;
         if (Player.getInstance().m_bUpgrade) {
            Player.getInstance().m_bUpgrade = false;
         }
      }

   }
}

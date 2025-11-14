import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class OtherPlayer extends ActiveGO {
   public static final byte PHYLE_HUMMAN = 1;
   public static final byte PHYLE_IMMORTAL = 2;
   public static final byte PHYLE_GOBLIN = 3;
   public static final byte PHYLE_DEMON = 4;
   public static final byte PRO_WAR = 1;
   public static final byte PRO_ASS = 2;
   public static final byte PRO_TAO = 3;
   public static final byte PRO_DOC = 4;
   public static final byte PRO_ENC = 5;
   public static final byte PRO_WL = 6;
   public static final byte SEX_MAN = 0;
   public static final byte SEX_WOMEN = 1;
   byte faceId = -1;
   int faceTime = 0;
   short mapID = -1;
   public static final int FACE_MAX_TIME = 48;
   public static final int GOS_STAND_DOWN = 0;
   public static final int GOS_STAND_LEFT = 1;
   public static final int GOS_STAND_UP = 2;
   public static final int GOS_WALK_DOWN = 3;
   public static final int GOS_WALK_LEFT = 4;
   public static final int GOS_WALK_UP = 5;
   public static final int GOS_MELEEBH_DOWN = 6;
   public static final int GOS_MELEEBH_LEFT = 7;
   public static final int GOS_MELEEBH_UP = 8;
   public static final int GOS_MELEESS_DOWN = 9;
   public static final int GOS_MELEESS_LEFT = 10;
   public static final int GOS_MELEESS_UP = 11;
   public static final int GOS_MELEE1S_DOWN = 12;
   public static final int GOS_MELEE1S_LEFT = 13;
   public static final int GOS_MELEE1S_UP = 14;
   public static final int GOS_MELEE2S_DOWN = 15;
   public static final int GOS_MELEE2S_LEFT = 16;
   public static final int GOS_MELEE2S_UP = 17;
   public static final int GOS_CASTING_DOWN = 18;
   public static final int GOS_CASTING_LEFT = 19;
   public static final int GOS_CASTING_UP = 20;
   public static final int GOS_CAST_DOWN = 21;
   public static final int GOS_CAST_LEFT = 22;
   public static final int GOS_CAST_UP = 23;
   public static final int GOS_DYING = 24;
   public static final int GOS_DEAD = 25;
   public static final int GOS_STEALTH_DOWN = 26;
   public static final int GOS_STEALTH_LEFT = 27;
   public static final int GOS_STEALTH_UP = 28;
   public static final int MOUNT_STAND_DOWN = 29;
   public static final int MOUNT_STAND_LEFT = 30;
   public static final int MOUNT_STAND_UP = 31;
   public static final int MOUNT_WALK_DOWN = 32;
   public static final int MOUNT_WALK_LEFT = 33;
   public static final int MOUNT_WALK_UP = 34;
   public static final int CART_STAND_DOWN = 35;
   public static final int CART_STAND_LEFT = 36;
   public static final int CART_STAND_UP = 37;
   public static final int CART_WALK_DOWN = 38;
   public static final int CART_WALK_LEFT = 39;
   public static final int CART_WALK_UP = 40;
   public static final int ANIMAL_BEGIN_FRAME = 41;
   byte[][] hidePlayerPoints = new byte[][]{{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}, {0, 1, 10, 11, 12, 13, 14, 15, 16, 19, 20}, {4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}};
   public static final short PLAYER_IMG_WIDHT = 27;
   public static final short PLAYER_IMG_HEIGHT = 30;
   public static final short ren_FIGURE = 124;
   public static final short anim_FIGURE = 132;
   public static final short BEGIN_CART_FRAMEID = 109;
   public static final short PHYLE_COUNT = 4;
   public static final short SEX_COUNT = 2;
   public static final short NPCXXX_COUNT = 8;
   private static short[][] humanImageIndex = null;
   private static byte[][] humanMirrorAndRef = null;
   private static short[][] beastImageIndex = null;
   private static byte[][] beastMirrorAndRef = null;
   short[][] CURREquipment = null;
   public boolean att_CD = true;
   public static final byte R_HUM_F_SOD_P_OFFSET_X = 7;
   public static final byte R_HUM_F_SOD_P_OFFSET_Y = 3;
   public static final byte R_HUM_F_SOD_P_OFFSET_W = 9;
   public static final byte R_HUM_F_SOD_P_OFFSET_H = 3;
   public static final byte P_OFFSET_X = 0;
   public static final byte P_OFFSET_Y = 0;
   public static final byte P_OFFSET_W = 0;
   public static final byte P_OFFSET_H = 0;
   public static Image[] imgSTiles = null;
   public static byte[] nSTilePos = null;
   public static short[] nSTileID = null;
   public static byte[] nSTileFlag = null;
   public static byte[] imgAttr = null;
   private static final String STATIC_REF = "m_refe00.dat";
   public int nSkeNow = 1;
   public byte[][] nMirrorAndRef = null;
   public byte nState = 0;
   public short motionID = -1;
   public byte nWeapon = 0;
   public static final byte RIGHT_HANDS_ATTC = 1;
   public static final byte LEFT_HANDS_ATTC = 3;
   public static final byte TWO_HANDS_TURN = 4;
   public boolean istwohandsturn = false;
   public short currentSkill_ID = 0;
   public short currentSkill_ARRInd = -1;
   public short SkillTime_Count = 0;
   public boolean isSendMove = false;
   public boolean isTreadMapCarryPoint = false;
   public boolean isPVP = false;
   protected static SAData sadGO = null;
   short UI_x = 0;
   short UI_y = 0;
   public boolean m_bGainedFlag = false;
   public byte m_bFlagLind;
   public byte small_flag;
   private int TreasurePicIDMay;
   public static byte OBJ_BUBBLE_DX = 10;
   public static byte OBJ_BUBBLE_DY = 40;
   public static byte OBJ_FACE_DX = 18;
   public static byte OBJ_FACE_DY = 37;
   public static final byte[][] position_XY = new byte[][]{{10, 20}, {10, 20}, {10, 20}, {10, 22}, {10, 20}, {10, 20}};
   public static final byte[][] SPE_MOVEPATH_LR = new byte[][]{{1, 1}, {3, 3}, {6, 6}, {7, 8}, {8, 6}, {9, 8}};
   public static final int[] SPE_COLOR = new int[]{16446976, 16523786, 1506314};
   private static final byte pre_SpCount = 2;
   byte[][] specs = new byte[20][7];
   byte spindex = 0;
   private byte treasureCounter = 1;
   private short treasurePicX;
   private short treasurePicY;

   public OtherPlayer(short sx, short sy, byte phyle4, byte pro6, byte sex2) {
      super.type = 1;
      this.nState = 0;
      this.motionID = 0;
      super.nType = 0;
      super.hpStates = new Vector();
      this.setPhyle(phyle4);
      this.load_OrgEquip(sex2);
      this.load_humanIMG(this.getNPCFileName(phyle4, sex2));
      this.checkState();
      super.x = super.aimx = sx;
      super.y = super.aimy = sy;
      super.nRow = super.aimRow = Map.getCurrentRow(sy, sx);
      super.nCol = super.aimCol = Map.getCurrentColumn(sy, sx);
      this.setDirection((byte)1);
      super.curStep = 49;
      super.horseState = 0;
      this.setState((byte)0);
   }

   protected OtherPlayer() {
   }

   public static void loadStaticTiles() {
      InputStream isData = Util.loadFile("/m_refe00.dat", true);
      DataInputStream disData = new DataInputStream(isData);

      try {
         short count = disData.readShort();
         imgSTiles = new Image[count];
         nSTilePos = new byte[count * 2];
         nSTileID = new short[count];
         nSTileFlag = new byte[count];
         imgAttr = new byte[count];
         String[] temPngName = new String[count];

         for(short n = 0; n < count; ++n) {
            nSTileID[n] = disData.readShort();
            nSTileFlag[n] = 0;
            short m = n;

            while(--m >= 0) {
               if (nSTileFlag[m] != 1 && nSTileID[m] == nSTileID[n]) {
                  nSTileID[n] = m;
                  nSTileFlag[n] = 1;
                  break;
               }
            }

            if (nSTileFlag[n] == 0) {
               temPngName[n] = nSTileID[n] + ".png";
            }

            nSTilePos[2 * n] = disData.readByte();
            nSTilePos[2 * n + 1] = disData.readByte();
            int pngatrr = disData.readInt();
            imgAttr[n] = getPosFromAttr(pngatrr);
         }

         imgSTiles = Util.loadImageMap(imgSTiles, "/mimage/rolebody.pkg", temPngName);
         temPngName = (String[])null;
         disData.close();
         disData = null;
      } catch (IOException var7) {
         var7.printStackTrace();
      }

   }

   private static byte getPosFromAttr(int attri) {
      byte pos = -1;
      if (attri < 1000000) {
         return pos;
      } else {
         String strrStr = Integer.toString(attri);
         strrStr = strrStr.substring(1, 3);
         pos = Integer.valueOf(strrStr).byteValue();
         return pos;
      }
   }

   public static void loadNPC_HUMANData() {
      try {
         humanImageIndex = new short[992][];
         humanMirrorAndRef = new byte[992][];
         InputStream isData = null;
         DataInputStream disData = null;

         for(int p = 0; p < 8; ++p) {
            isData = Util.loadFile("/pd/npc" + (p + 1) + ".dat", true);
            disData = new DataInputStream(isData);
            short dddd = disData.readShort();

            for(int n = 124 * p; n < 124 * (p + 1); ++n) {
               disData.readShort();
               byte c = disData.readByte();
               humanImageIndex[n] = new short[c];
               humanMirrorAndRef[n] = new byte[c];

               for(byte m = 0; m < c; ++m) {
                  humanImageIndex[n][m] = disData.readShort();
                  humanMirrorAndRef[n][m] = disData.readByte();
               }
            }

            disData.close();
         }

         disData = null;
         isData = null;
      } catch (IOException var7) {
         var7.printStackTrace();
      }

   }

   public static void loadNPC_BEASTData() {
      try {
         InputStream isData = Util.loadFile("/pd/npc4444.dat", true);
         DataInputStream disData = new DataInputStream(isData);
         short dddd = disData.readShort();
         beastImageIndex = new short[132][];
         beastMirrorAndRef = new byte[132][];

         for(short n = 0; n < 132; ++n) {
            disData.readShort();
            byte c = disData.readByte();
            beastImageIndex[n] = new short[c];
            beastMirrorAndRef[n] = new byte[c];

            for(byte m = 0; m < c; ++m) {
               beastImageIndex[n][m] = disData.readShort();
               beastMirrorAndRef[n][m] = disData.readByte();
            }
         }

         disData.close();
         disData = null;
         isData = null;
      } catch (IOException var6) {
         var6.printStackTrace();
      }

   }

   public void load_humanIMG(byte p) {
      super.nImageIndex1 = new short[124][];
      this.nMirrorAndRef = new byte[124][];

      int z;
      byte m;
      for(z = 124 * (p - 1); z < 124 * p; ++z) {
         super.nImageIndex1[z - 124 * (p - 1)] = new short[humanImageIndex[z].length];

         for(m = 0; m < humanImageIndex[z].length; ++m) {
            super.nImageIndex1[z - 124 * (p - 1)][m] = humanImageIndex[z][m];
         }
      }

      for(z = 124 * (p - 1); z < 124 * p; ++z) {
         this.nMirrorAndRef[z - 124 * (p - 1)] = new byte[humanMirrorAndRef[z].length];

         for(m = 0; m < humanMirrorAndRef[z].length; ++m) {
            this.nMirrorAndRef[z - 124 * (p - 1)][m] = humanMirrorAndRef[z][m];
         }
      }

   }

   public void load_OrgEquip(byte p) {
      int chquC = ActiveGO.ORIEquipment.length / 2;
      this.CURREquipment = new short[chquC][];

      for(int i = p * chquC; i < chquC * (p + 1); ++i) {
         this.CURREquipment[i - p * chquC] = new short[ActiveGO.ORIEquipment[i].length];

         for(int j = 0; j < ActiveGO.ORIEquipment[i].length; ++j) {
            this.CURREquipment[i - p * chquC][j] = ActiveGO.ORIEquipment[i][j];
         }
      }

   }

   public byte getNPCFileName(int p, int s) {
      return (byte)(p + (p - 1) + s);
   }

   public final void setObj_UI_XY(short nx, short ny) {
      this.UI_x = nx;
      this.UI_y = ny;
   }

   public final short getObj_UI_X() {
      return this.UI_x;
   }

   public final short getObj_UI_Y() {
      return this.UI_y;
   }

   public void setWeaponType(byte i, boolean it) {
      this.nWeapon = i;
      this.istwohandsturn = it;
   }

   public short getWeaponType() {
      return this.nWeapon;
   }

   public void setSkill_ID(short i) {
      this.currentSkill_ID = i;
   }

   public short getSkill_ID() {
      return this.currentSkill_ID;
   }

   public void setSkill_Index(short i) {
      this.currentSkill_ARRInd = i;
   }

   public short getSkill_Index() {
      return this.currentSkill_ARRInd;
   }

   public final void setTreasurePicIDMay(int i) {
      this.TreasurePicIDMay = i;
   }

   public final int getTreasurePicIDMay() {
      return this.TreasurePicIDMay;
   }

   public void changeEquip(byte[] pos, short[][] pngfrmInd) {
      int e;
      for(short n = 0; n < 124; ++n) {
         e = super.nImageIndex1[n].length;

         for(byte m = 0; m < e; ++m) {
            for(int z = 0; z < pos.length; ++z) {
               for(int e = 0; e < this.CURREquipment[Math.abs(pos[z]) - 1].length; ++e) {
                  if (super.nImageIndex1[n][m] == this.CURREquipment[Math.abs(pos[z]) - 1][e]) {
                     if (Math.abs(pos[z]) == 4) {
                        if (imgAttr[super.nImageIndex1[n][m]] == 7) {
                           super.nImageIndex1[n][m] = pngfrmInd[z][e];
                        }
                     } else if (Math.abs(pos[z]) == 6) {
                        if (imgAttr[super.nImageIndex1[n][m]] == 9) {
                           super.nImageIndex1[n][m] = pngfrmInd[z][e];
                        }
                     } else {
                        super.nImageIndex1[n][m] = pngfrmInd[z][e];
                     }

                     if (pos[z] >= 0 && pos[z] > 0) {
                        byte[] var10000 = this.nMirrorAndRef[n];
                        var10000[m] = (byte)(var10000[m] & 239);
                     }
                  }
               }
            }
         }
      }

      for(int z = 0; z < pos.length; ++z) {
         for(e = 0; e < pngfrmInd[z].length; ++e) {
            this.CURREquipment[Math.abs(pos[z]) - 1][e] = pngfrmInd[z][e];
         }
      }

   }

   public void checkState() {
      int nOffset = 0;
      if (super.nType == 1) {
         nOffset = 29;
      } else if (super.nType > 1) {
         nOffset = 29 + (super.nType - 1) * 9;
      }

      if (this.nState + nOffset >= sadGO.nAnimation.length) {
         this.nState = 0;
      }

   }

   public void gameObject_setPosition(short row, short col) {
   }

   public void tick() {
      if (super.type != 9) {
         switch(super.state) {
         case 1:
            this.movePath();
         case 0:
         default:
            this.motionState_tick();
            this.tickHpChangeVectorPop();
         }
      }

      this.tickSpecial();
      this.drawTick();
   }

   protected void drawTick() {
      super.drawX = (short)(super.x - Map.currentWindowX + 0);
      super.drawY = (short)(super.y - Map.currentWindowY + 3 + 0);
      if (super.cartPlayer != null) {
         this.refreshHorseInfo();
         super.cartX = (short)(super.cartPlayer.x - Map.currentWindowX + 0);
         super.cartY = (short)(super.cartPlayer.y - Map.currentWindowY + 3 + 0);
      }

      if (this.faceTime > 0) {
         --this.faceTime;
      } else {
         this.faceId = -1;
      }

   }

   public void refreshHorseInfo() {
      byte temind = (byte)(super.horse_IND - 1);
      if (super.isHorseMove) {
         if (temind < 0) {
            temind = (byte)(super.otherPX_Y_D.length - 1);
         }

         if (super.x != super.otherPX_Y_D[temind][0] || super.y != super.otherPX_Y_D[temind][1]) {
            super.otherPX_Y_D[super.horse_IND][0] = super.x;
            super.otherPX_Y_D[super.horse_IND][1] = super.y;
            super.otherPX_Y_D[super.horse_IND][2] = this.getDirection();
            super.otherPX_Y_D[super.horse_IND][3] = this.getState();
            super.horse_IND = ++super.horse_IND >= super.otherPX_Y_D.length ? 0 : super.horse_IND;
            if (Math.abs(super.horse_IND - super.cart_IND) == 5) {
               super.cartPlayer.x = super.otherPX_Y_D[super.cart_IND][0];
               super.cartPlayer.y = super.otherPX_Y_D[super.cart_IND][1];
               super.cartPlayer.setDirection((byte)super.otherPX_Y_D[super.cart_IND][2]);
               if (super.cartPlayer.getState() != 1) {
                  super.cartPlayer.setState((byte)1);
               }

               super.cart_IND = ++super.cart_IND >= super.otherPX_Y_D.length ? 0 : super.cart_IND;
            }
         }

         super.isHorseMove = false;
         super.cartPlayer.setRowCol(this.getRow(super.cartPlayer.x, super.cartPlayer.y), this.getCol(super.cartPlayer.x, super.cartPlayer.y));
      } else if (super.cartPlayer.getState() != 0) {
         super.cartPlayer.setState((byte)0);
      }

   }

   public void copy_CR() {
      super.oCol = super.nCol;
      super.oRow = super.nRow;
   }

   public void motionState_tick() {
      if (super.isFreezing) {
         if (super.nNextFrame == -1) {
            super.nNextFrame = 0;
         }

      } else {
         short nOffset = 0;
         short weapon = 0;
         if (super.nType >= 1) {
            nOffset = (short)(41 + (super.nType - 1) * 9);
            weapon = 0;
         }

         if (super.nType == 0 && this.nState >= 6 && this.nState <= 8) {
            if (this.getWeaponType() == 4) {
               weapon = 3;
            } else {
               weapon = (short)(this.nWeapon * 3);
            }
         }

         if (super.nType == 0 && this.nState >= 3 && this.nState <= 5 && super.bStealth) {
            weapon = (short)(weapon + 23);
         }

         this.motionID = (short)(this.nState + nOffset + weapon);
         if (this.motionID >= sadGO.nAnimation.length) {
            this.motionID = 0;
         }

         if (super.nNextFrame < sadGO.nAnimation[this.motionID].length - 1) {
            ++super.nNextFrame;
         } else {
            label142: {
               switch(this.getState()) {
               case 2:
                  if (this.getWeaponType() == 4) {
                     if (this.istwohandsturn) {
                        this.setWeaponType((byte)3, true);
                     }
                  } else {
                     weapon = 0;
                     if (this.getWeaponType() == 3 && this.istwohandsturn) {
                        this.setWeaponType((byte)4, true);
                     }

                     if (this == Player.getInstance()) {
                        if (GOManager.currentTarget.type == 2 || GOManager.currentTarget.type == 1 || GOManager.currentTarget.type == 3) {
                           if (GOManager.currentTarget.type == 2 && GOManager.currentTarget.getState() != 9) {
                              this.setState((byte)102);
                           } else if (((ActiveGO)GOManager.currentTarget).getPrestige() < 0) {
                              if (GOManager.currentTarget.getState() != 9) {
                                 this.setState((byte)102);
                              } else {
                                 this.setState((byte)0);
                              }
                           } else {
                              this.setState((byte)0);
                           }
                        }
                     } else if (super.path.length > 0) {
                        this.setState((byte)1);
                     } else {
                        this.setState((byte)0);
                     }
                  }
               case 3:
               default:
                  break label142;
               case 4:
               }

               if (GOManager.getCurrentTarget() != null && this.getSkill_ID() == 0 && (GOManager.getCurrentTarget().getType() == 4 || GOManager.getCurrentTarget().getType() == 5 || GOManager.getCurrentTarget().getType() == 6 || GOManager.getCurrentTarget().getType() == 7 || GOManager.getCurrentTarget().getType() == 8)) {
                  if (Player.getInstance().isCollection) {
                     this.setState((byte)3);
                  } else {
                     this.setState((byte)0);
                  }
               } else {
                  this.setState((byte)0);
               }
            }

            super.nNextFrame = 0;
            if (this.nState == 24) {
               this.nState = 25;
            }

            this.motionID = (short)(this.nState + nOffset + weapon);
         }

         if (super.cartPlayer != null) {
            if (super.cartPlayer.nNextFrame < sadGO.nAnimation[super.cartPlayer.motionID].length - 1) {
               ++super.cartPlayer.nNextFrame;
            } else {
               super.cartPlayer.nNextFrame = 0;
               super.cartPlayer.motionID = (short)(super.cartPlayer.nState + nOffset + weapon);
            }
         }

      }
   }

   public void draw(Graphics g) {
      if (GOManager.isShowOtherPlayer || Player.getInstance() == this) {
         this.drawBody(g, super.drawX, super.drawY);
         this.drawSpecial(g);
      }

   }

   public void drawFace(Graphics g) {
      if (this.faceId >= 0) {
         g.drawImage(MainCanvas.bubble, super.drawX + OBJ_BUBBLE_DX, super.drawY - OBJ_BUBBLE_DY, 20);
         if (UIChat.faceSwing[this.faceId][MainCanvas.countTick % UIChat.faceSwing[this.faceId].length] == 0) {
            MainCanvas.faceImg.draw(g, super.drawX + OBJ_FACE_DX, super.drawY - OBJ_FACE_DY, this.faceId);
         } else {
            MainCanvas.faceImg1.draw(g, super.drawX + OBJ_FACE_DX, super.drawY - OBJ_FACE_DY, this.faceId);
         }

      }
   }

   public void drawBody(Graphics g, short dx, short dy) {
      if (!super.isInvisible) {
         if (super.cartPlayer != null) {
            this.drawHumanHorseCart(g, dx, dy, false);
         } else {
            this.drawHumanHorseCart(g, dx, dy, true);
            if (super.nType == 0 && super.horseState == 0 && MainCanvas.weaponSpecial == 1) {
               this.drawBodilySpecial(g, dx, dy, (byte)3, (byte)0);
            }
         }

      }
   }

   public void drawHumanHorseCart(Graphics g, short dx, short dy, boolean isDrawHuman) {
      short nFrame = sadGO.nAnimation[this.motionID - 0][super.nNextFrame];
      int nPointsCount = sadGO.nSkeleton[nFrame].length;

      for(int n = 0; n < nPointsCount; n += 2) {
         if (isDrawHuman || !this.isDrawPlayer((short)(this.motionID - 0), nFrame, n >> 1)) {
            int y = dy + sadGO.nSkeleton[nFrame][n + 1];
            int x;
            if (super.bRMirror) {
               x = dx - sadGO.nSkeleton[nFrame][n] + 2;
            } else {
               x = dx + sadGO.nSkeleton[nFrame][n];
            }

            try {
               int q = true;
               byte mirror = true;
               byte temp = true;
               short q;
               byte mirror;
               byte temp;
               if (super.nType == 0) {
                  q = super.nImageIndex1[nFrame][n >> 1];
                  temp = this.nMirrorAndRef[nFrame][n >> 1];
                  mirror = (byte)(temp & 7);
               } else {
                  if (nFrame - 124 < 0) {
                     return;
                  }

                  q = beastImageIndex[nFrame - 124][n >> 1];
                  temp = beastMirrorAndRef[nFrame - 124][n >> 1];
                  mirror = (byte)(temp & 7);
               }

               if ((temp & 16) == 0) {
                  Image img2Draw = null;
                  if (nSTileFlag[q] == 0) {
                     img2Draw = imgSTiles[q];
                  } else {
                     img2Draw = imgSTiles[nSTileID[q]];
                  }

                  int tx = false;
                  int ty = false;
                  int tx;
                  int ty;
                  switch(mirror) {
                  case 0:
                     ty = y - nSTilePos[2 * q + 1];
                     if (super.bRMirror) {
                        tx = x - (img2Draw.getWidth() - nSTilePos[2 * q]);
                        Util.drawImage(g, img2Draw, tx, ty, 2);
                     } else {
                        tx = x - nSTilePos[2 * q];
                        Util.drawImage(g, img2Draw, tx, ty, 0);
                     }
                     break;
                  case 1:
                     ty = y - nSTilePos[2 * q];
                     if (super.bRMirror) {
                        tx = x - nSTilePos[2 * q + 1] - 1;
                        Util.drawImage(g, img2Draw, tx, ty, 4);
                     } else {
                        tx = x - (img2Draw.getHeight() - nSTilePos[2 * q + 1]) + 1;
                        Util.drawImage(g, img2Draw, tx, ty, 5);
                     }
                     break;
                  case 2:
                     ty = y - (img2Draw.getHeight() - nSTilePos[2 * q + 1]) + 1;
                     if (super.bRMirror) {
                        tx = x - nSTilePos[2 * q] - 1;
                        Util.drawImage(g, img2Draw, tx, ty, 1);
                     } else {
                        tx = x - (img2Draw.getWidth() - nSTilePos[2 * q]) + 1;
                        Util.drawImage(g, img2Draw, tx, ty, 3);
                     }
                     break;
                  case 3:
                     ty = y - (img2Draw.getWidth() - nSTilePos[2 * q]) + 1;
                     if (super.bRMirror) {
                        tx = x - (img2Draw.getHeight() - nSTilePos[2 * q + 1]);
                        Util.drawImage(g, img2Draw, tx, ty, 7);
                     } else {
                        tx = x - nSTilePos[2 * q + 1];
                        Util.drawImage(g, img2Draw, tx, ty, 6);
                     }
                     break;
                  case 4:
                     ty = y - nSTilePos[2 * q + 1];
                     if (super.bRMirror) {
                        tx = x - nSTilePos[2 * q] - 1;
                        Util.drawImage(g, img2Draw, tx, ty, 0);
                     } else {
                        tx = x - (img2Draw.getWidth() - nSTilePos[2 * q]) + 1;
                        Util.drawImage(g, img2Draw, tx, ty, 2);
                     }
                     break;
                  case 5:
                     ty = y - nSTilePos[2 * q];
                     if (super.bRMirror) {
                        tx = x - (img2Draw.getHeight() - nSTilePos[2 * q + 1]);
                        Util.drawImage(g, img2Draw, tx, ty, 5);
                     } else {
                        tx = x - nSTilePos[2 * q + 1];
                        Util.drawImage(g, img2Draw, tx, ty, 4);
                     }
                     break;
                  case 6:
                     ty = y - (img2Draw.getHeight() - nSTilePos[2 * q + 1]) + 1;
                     if (super.bRMirror) {
                        tx = x - (img2Draw.getWidth() - nSTilePos[2 * q]);
                        Util.drawImage(g, img2Draw, tx, ty, 3);
                     } else {
                        tx = x - nSTilePos[2 * q];
                        Util.drawImage(g, img2Draw, tx, ty, 1);
                     }
                     break;
                  case 7:
                     ty = y - (img2Draw.getWidth() - nSTilePos[2 * q]) + 1;
                     if (super.bRMirror) {
                        tx = x - nSTilePos[2 * q + 1] - 1;
                        Util.drawImage(g, img2Draw, tx, ty, 6);
                     } else {
                        tx = x - (img2Draw.getHeight() - nSTilePos[2 * q + 1]) + 1;
                        Util.drawImage(g, img2Draw, tx, ty, 7);
                     }
                  }
               }
            } catch (NullPointerException var16) {
               var16.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException var17) {
               var17.printStackTrace();
            }
         }
      }

   }

   private boolean isDrawPlayer(short motionid, short frameid, int points) {
      boolean isdraw = false;
      if (super.cartPlayer != null) {
         int i;
         switch(frameid) {
         case 94:
         case 97:
         case 98:
         case 99:
         case 100:
            for(i = 0; i < this.hidePlayerPoints[0].length; ++i) {
               if (points == this.hidePlayerPoints[0][i]) {
                  isdraw = true;
                  return isdraw;
               }
            }

            return isdraw;
         case 95:
         case 101:
         case 102:
         case 103:
         case 104:
            for(i = 0; i < this.hidePlayerPoints[1].length; ++i) {
               if (points == this.hidePlayerPoints[1][i]) {
                  isdraw = true;
                  return isdraw;
               }
            }

            return isdraw;
         case 96:
         case 105:
         case 106:
         case 107:
         case 108:
            for(i = 0; i < this.hidePlayerPoints[2].length; ++i) {
               if (points == this.hidePlayerPoints[2][i]) {
                  isdraw = true;
                  break;
               }
            }
         }
      } else {
         isdraw = true;
      }

      return isdraw;
   }

   public void drawName(Graphics g) {
      if (GOManager.isShowOtherPlayer) {
         int horseDy = MainCanvas.CHARH + ActiveGO.OBJ_NAME_SPACE - this.getSadGODy();
         if (super.horseState != 0) {
            horseDy = ActiveGO.OBJ_NAME_DY2;
         }

         if (this.getPrestige() == -4) {
            if (this.getTitleName().length() == 0) {
               Util.drawStringShadow(g, this.getRoleName(), super.drawX - (g.getFont().stringWidth(this.getRoleName()) >> 1), super.drawY - horseDy, 16711680, 0);
            } else {
               Util.drawStringShadow(g, this.getRoleName(), super.drawX - (g.getFont().stringWidth(this.getRoleName()) >> 1), super.drawY - horseDy - MainCanvas.CHARH, 16711680, 0);
               Util.drawStringShadow(g, this.getTitleName(), super.drawX - (g.getFont().stringWidth(this.getTitleName()) >> 1), super.drawY - horseDy, 16711680, 0);
            }
         } else if (Player.getInstance() != this && !this.isPVP) {
            if (this.getTitleName().length() == 0) {
               Util.drawStringShadow(g, this.getRoleName(), super.drawX - (g.getFont().stringWidth(this.getRoleName()) >> 1), super.drawY - horseDy, 16765952, 0);
            } else {
               Util.drawStringShadow(g, this.getRoleName(), super.drawX - (g.getFont().stringWidth(this.getRoleName()) >> 1), super.drawY - horseDy - MainCanvas.CHARH, 16765952, 0);
               Util.drawStringShadow(g, this.getTitleName(), super.drawX - (g.getFont().stringWidth(this.getTitleName()) >> 1), super.drawY - horseDy, 16765952, 0);
            }
         } else {
            super.drawName(g);
         }
      } else if ((Player.getInstance() == this || this.isPVP) && Player.getInstance().getSkillLimit() != 0) {
         super.drawName(g);
      }

      this.drawFace(g);
      if (Map.m_bBattlefield && this.m_bGainedFlag) {
         if (this.getID() == Player.getInstance().getID()) {
            if (this.m_bFlagLind == 1) {
               MainCanvas.small_redflag.draw(g, super.drawX - 7, super.drawY - (MainCanvas.CHARH + ActiveGO.OBJ_NAME_SPACE - this.getSadGODy()) + (this.getTitleName().length() == 0 ? 5 : -10), this.small_flag);
            } else {
               MainCanvas.small_blueflag.draw(g, super.drawX - 7, super.drawY - (MainCanvas.CHARH + ActiveGO.OBJ_NAME_SPACE - this.getSadGODy()) + (this.getTitleName().length() == 0 ? 5 : -10), this.small_flag);
            }
         } else if (this.m_bFlagLind == 1) {
            MainCanvas.small_redflag.draw(g, super.drawX - 7, super.drawY - (MainCanvas.CHARH + ActiveGO.OBJ_NAME_SPACE - this.getSadGODy()) + (this.getTitleName().length() == 0 ? -10 : -20), this.small_flag);
         } else {
            MainCanvas.small_blueflag.draw(g, super.drawX - 7, super.drawY - (MainCanvas.CHARH + ActiveGO.OBJ_NAME_SPACE - this.getSadGODy()) + (this.getTitleName().length() == 0 ? -10 : -20), this.small_flag);
         }

         ++this.small_flag;
         if (this.small_flag > 3) {
            this.small_flag = 0;
         }
      }

      this.drawTreasurePic(g);
      this.drawUpgrade(g);
   }

   public void drawBodilySpecial(Graphics g, short dx, short dy, byte bodyPosition, byte specialIndex) {
      this.drawCross(g, dx, dy, bodyPosition);
   }

   private short[] getPositionPoint(byte bodyPosition, short dx, short dy) {
      boolean isgetR = false;
      short nFrame = sadGO.nAnimation[this.motionID - 0][super.nNextFrame];
      byte basePoint = 0;
      int frmBoneC = super.nImageIndex1[nFrame].length;

      for(byte m = 0; m < frmBoneC; ++m) {
         for(int e = 0; e < this.CURREquipment[bodyPosition - 1].length; ++e) {
            if (super.nImageIndex1[nFrame][m] == this.CURREquipment[bodyPosition - 1][e]) {
               isgetR = true;
               basePoint = (byte)(m * 2);
               break;
            }
         }

         if (isgetR) {
            break;
         }
      }

      short[] pxy = new short[]{0, (short)(dy + sadGO.nSkeleton[nFrame][basePoint + 1])};
      if (super.bRMirror) {
         pxy[0] = (short)(dx - sadGO.nSkeleton[nFrame][basePoint] + 2);
      } else {
         pxy[0] = (short)(dx + sadGO.nSkeleton[nFrame][basePoint]);
      }

      return pxy;
   }

   public void newSpecs(byte bodyPosition) {
      for(byte i = this.spindex; i < this.specs.length; ++i) {
         this.spindex = i;
         byte it = (byte)Util.getRandom(2);
         this.specs[i][0] = it;
         if (it == 0) {
            this.specs[i][0] = -1;
         }

         it = (byte)Util.getRandom(2);
         this.specs[i][1] = it;
         if (it == 0) {
            this.specs[i][1] = -1;
         }

         this.specs[i][4] = (byte)(Util.getRandom(3) + 1);
         this.specs[i][5] = 0;
         this.specs[i][6] = (byte)Util.getRandom(SPE_COLOR.length);
         if (this.spindex % 2 == 1) {
            if (this.spindex == (byte)(this.specs.length - 1)) {
               this.spindex = 0;
            }
            break;
         }
      }

   }

   private void drawCross(Graphics g, short x, short y, byte bodyPosition) {
      short spx = 0;
      short spy = 0;

      for(byte i = 0; i <= this.spindex; ++i) {
         byte[] var10000 = this.specs[i];
         var10000[2] = (byte)(var10000[2] + this.specs[i][0] * 1);
         var10000 = this.specs[i];
         var10000[3] = (byte)(var10000[3] + this.specs[i][1] * this.specs[i][4]);
         switch(this.getDirection()) {
         case 1:
         case 8:
            spx = (short)(x - this.specs[i][2]);
            spy = (short)(y - this.specs[i][3]);
            break;
         case 2:
         case 7:
            spx = (short)(x - this.specs[i][2]);
            spy = (short)(y - this.specs[i][3]);
            break;
         case 3:
         case 5:
            spx = (short)(x + this.specs[i][2]);
            spy = (short)(y - this.specs[i][3]);
            break;
         case 4:
         case 6:
            spx = (short)(x + this.specs[i][2]);
            spy = (short)(y - this.specs[i][3]);
         }

         if (this.specs[i][5] == 0) {
            this.specs[i][5] = 0;
            g.setColor(0);
            g.drawLine(spx - 1, spy, spx + 1, spy);
            g.drawLine(spx, spy - 1, spx, spy + 1);
            g.setColor(16777215);
            g.fillRect(spx, spy, 1, 1);
         } else if (this.specs[i][5] == 1) {
            this.specs[i][5] = 0;
            g.setColor(16777215);
            g.drawLine(spx - 2, spy, spx + 2, spy);
            g.drawLine(spx, spy - 2, spx, spy + 2);
            g.setColor(SPE_COLOR[this.specs[i][6]]);
            g.fillRect(spx, spy, 1, 1);
         }

         if (this.specs[i][2] <= 0 || this.specs[i][2] >= 20 || this.specs[i][3] <= 8 || this.specs[i][3] >= 28) {
            byte it = (byte)Util.getRandom(2);
            this.specs[i][0] = it;
            if (it == 0) {
               this.specs[i][0] = -1;
            }

            it = (byte)Util.getRandom(2);
            this.specs[i][1] = it;
            if (it == 0) {
               this.specs[i][1] = -1;
            }

            this.specs[i][2] = (byte)(position_XY[bodyPosition][0] + Util.getRandom(4));
            this.specs[i][3] = (byte)(position_XY[bodyPosition][1] + Util.getRandom(4));
            this.specs[i][4] = (byte)(Util.getRandom(3) + 1);
            this.specs[i][6] = (byte)Util.getRandom(SPE_COLOR.length);
         }
      }

   }

   public void setMotionState(byte s) {
      label127:
      switch(s) {
      case 0:
      case 101:
      case 105:
         switch(super.horseState) {
         case 0:
            switch(this.getDirection()) {
            case 1:
            case 8:
               this.nState = 0;
               super.bRMirror = false;
               break label127;
            case 2:
            case 7:
               this.nState = 1;
               super.bRMirror = false;
               break label127;
            case 3:
            case 5:
               this.nState = 2;
               super.bRMirror = false;
               break label127;
            case 4:
            case 6:
               this.nState = 1;
               super.bRMirror = true;
            default:
               break label127;
            }
         case 1:
            switch(this.getDirection()) {
            case 1:
            case 8:
               this.nState = 29;
               super.bRMirror = false;
               break label127;
            case 2:
            case 7:
               this.nState = 30;
               super.bRMirror = false;
               break label127;
            case 3:
            case 5:
               this.nState = 31;
               super.bRMirror = false;
               break label127;
            case 4:
            case 6:
               this.nState = 30;
               super.bRMirror = true;
            default:
               break label127;
            }
         case 2:
            switch(this.getDirection()) {
            case 1:
            case 8:
               this.nState = 35;
               super.bRMirror = false;
               break label127;
            case 2:
            case 7:
               this.nState = 36;
               super.bRMirror = false;
               break label127;
            case 3:
            case 5:
               this.nState = 37;
               super.bRMirror = false;
               break label127;
            case 4:
            case 6:
               this.nState = 36;
               super.bRMirror = true;
            }
         default:
            break label127;
         }
      case 1:
      case 10:
      case 100:
      case 102:
      case 103:
      case 104:
         switch(super.horseState) {
         case 0:
            switch(this.getDirection()) {
            case 1:
            case 8:
               this.nState = 3;
               super.bRMirror = false;
               break label127;
            case 2:
            case 7:
               this.nState = 4;
               super.bRMirror = false;
               break label127;
            case 3:
            case 5:
               this.nState = 5;
               super.bRMirror = false;
               break label127;
            case 4:
            case 6:
               this.nState = 4;
               super.bRMirror = true;
            default:
               break label127;
            }
         case 1:
            switch(this.getDirection()) {
            case 1:
            case 8:
               this.nState = 32;
               super.bRMirror = false;
               break label127;
            case 2:
            case 7:
               this.nState = 33;
               super.bRMirror = false;
               break label127;
            case 3:
            case 5:
               this.nState = 34;
               super.bRMirror = false;
               break label127;
            case 4:
            case 6:
               this.nState = 33;
               super.bRMirror = true;
            default:
               break label127;
            }
         case 2:
            switch(this.getDirection()) {
            case 1:
            case 8:
               this.nState = 38;
               super.bRMirror = false;
               break label127;
            case 2:
            case 7:
               this.nState = 39;
               super.bRMirror = false;
               break label127;
            case 3:
            case 5:
               this.nState = 40;
               super.bRMirror = false;
               break label127;
            case 4:
            case 6:
               this.nState = 39;
               super.bRMirror = true;
            }
         default:
            break label127;
         }
      case 2:
         if (!this.att_CD) {
            this.att_CD = true;
            switch(this.getDirection()) {
            case 1:
            case 8:
               this.nState = 6;
               super.bRMirror = false;
               break;
            case 2:
            case 7:
               this.nState = 7;
               super.bRMirror = false;
               break;
            case 3:
            case 5:
               this.nState = 8;
               super.bRMirror = false;
               break;
            case 4:
            case 6:
               this.nState = 7;
               super.bRMirror = true;
            }
         }
         break;
      case 3:
         if (super.nType == 0) {
            this.SkillTime_Count = 0;
            switch(this.getDirection()) {
            case 1:
            case 8:
               this.nState = 18;
               super.bRMirror = false;
               break;
            case 2:
            case 7:
               this.nState = 19;
               super.bRMirror = false;
               break;
            case 3:
            case 5:
               this.nState = 20;
               super.bRMirror = false;
               break;
            case 4:
            case 6:
               this.nState = 19;
               super.bRMirror = true;
            }
         }
         break;
      case 4:
         if (super.nType == 0) {
            switch(this.getDirection()) {
            case 1:
            case 8:
               this.nState = 21;
               super.bRMirror = false;
               break;
            case 2:
            case 7:
               this.nState = 22;
               super.bRMirror = false;
               break;
            case 3:
            case 5:
               this.nState = 23;
               super.bRMirror = false;
               break;
            case 4:
            case 6:
               this.nState = 22;
               super.bRMirror = true;
            }
         }
         break;
      case 9:
         this.nState = 24;
      }

      short nOffset = 0;
      short weapon = 0;
      if (super.nType >= 1) {
         nOffset = (short)(41 + (super.nType - 1) * 9);
         weapon = 0;
      }

      if (super.nType == 0 && this.nState >= 6 && this.nState <= 8) {
         if (this.getWeaponType() == 4) {
            weapon = 3;
         } else {
            weapon = (short)(this.nWeapon * 3);
         }
      }

      if (super.nType == 0 && this.nState >= 3 && this.nState <= 5 && super.bStealth) {
         weapon = (short)(weapon + 23);
      }

      this.motionID = (short)(this.nState + nOffset + weapon);
   }

   public void setState(byte s) {
      this.att_CD = false;
      super.state = s;
      super.nNextFrame = 0;
      this.setMotionState(super.state);
   }

   public final void setDirection(byte dir) {
      if (super.direction != dir) {
         super.backdirection = super.direction;
         super.direction = dir;
         this.setMotionState(super.state);
      }

   }

   public short getMapID() {
      return this.mapID;
   }

   public void setMapID(short mapID) {
      this.mapID = mapID;
   }

   public void setSadGO(SAData sad) {
      sadGO = sad;
   }

   public SAData getSadGO() {
      return sadGO;
   }

   public byte getSadGODx() {
      return SAData.RECT[sadGO.nID][0];
   }

   public byte getSadGODy() {
      return SAData.RECT[sadGO.nID][1];
   }

   public byte getSadGOWidth() {
      return SAData.RECT[sadGO.nID][2];
   }

   public byte getSadGOHeight() {
      return SAData.RECT[sadGO.nID][3];
   }

   public void drawTreasurePic(Graphics g) {
      this.treasureCounter = (byte)(this.treasureCounter ^ 1);
      if (this.getTreasurePicIDMay() > 0) {
         this.treasurePicY = (short)(super.drawY - 25 + this.treasureCounter);
         if (this.getDirection() != 1 && this.getDirection() != 7 && this.getDirection() != 8) {
            if (this.getDirection() != 3 && this.getDirection() != 5 && this.getDirection() != 6) {
               if (this.getDirection() == 2) {
                  this.treasurePicX = (short)(super.drawX + 12);
               } else if (this.getDirection() == 4) {
                  this.treasurePicX = (short)(super.drawX - 18);
               }
            } else {
               this.treasurePicX = (short)(super.drawX - 20);
            }
         } else {
            this.treasurePicX = (short)(super.drawX + 13);
         }

         this.drawTreasure(g, this.treasurePicX, this.treasurePicY);
      }

   }

   private void drawTreasure(Graphics g, short x, short y) {
      g.drawImage(MainCanvas.treasurePic, x, y, 20);
   }
}

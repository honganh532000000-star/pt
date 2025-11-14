import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Monster extends ActiveGO {
   public static final int GOS_STAND_DOWN = 0;
   public static final int GOS_STAND_LEFT = 1;
   public static final int GOS_STAND_UP = 2;
   public static final int GOS_WALK_DOWN = 3;
   public static final int GOS_WALK_LEFT = 4;
   public static final int GOS_WALK_UP = 5;
   public static final int GOS_MELEEBH_DOWN = 6;
   public static final int GOS_MELEEBH_LEFT = 7;
   public static final int GOS_MELEEBH_UP = 8;
   public static final int GOS_CASTING_DOWN = 9;
   public static final int GOS_CASTING_LEFT = 10;
   public static final int GOS_CASTING_UP = 11;
   public static final int GOS_CAST_DOWN = 12;
   public static final int GOS_CAST_LEFT = 13;
   public static final int GOS_CAST_UP = 14;
   public static final int GOS_DEAD = 15;
   byte apparel = -1;
   public static String[] temPngName = null;
   public static Image[] imgSTiles = null;
   public static byte[] nSTilePos = null;
   public static short[] nSTileID = null;
   public static byte[] nSTileFlag = null;
   public static short[][][] monsterImageIndex = null;
   public static byte[][][] monsterMirrorAndRef = null;
   private static byte[] monsterBody = null;
   short currentFrameID = -1;
   short currentmotionID = -1;
   public SAData sadGO = null;
   private int distinction = 0;
   static Hashtable MonsterImgHa = new Hashtable();
   static Hashtable MonsterRefe = new Hashtable();

   protected Monster() {
   }

   public Monster(byte ntype, byte boneindex) {
      super.type = ntype;
      this.sadGO = GameObject.mgrSA.getSADByIndex(GameObject.mgrSA.requestData(monsterBody[boneindex]));
      super.nNextFrame = 0;
      this.currentFrameID = 0;
      this.currentmotionID = 0;
      super.curStep = 49;
      this.setState((byte)0);
      this.setDirection((byte)1);
      super.hpStates = new Vector();
   }

   public static void loadStaticTiles(ByteArray data) {
      try {
         byte neiYICount = data.readByte();
         monsterImageIndex = new short[neiYICount][][];
         monsterMirrorAndRef = new byte[neiYICount][][];
         monsterBody = new byte[neiYICount];
         short imgIndCount = 0;
         Hashtable temp = new Hashtable();
         int a = false;
         int b = false;

         short m;
         for(int p = 0; p < neiYICount; ++p) {
            boolean isClientExist = data.readBoolean();
            int fc;
            if (isClientExist) {
               int dddd = data.readInt();
               int bodyType = dddd / 100000;
               fc = dddd % 100000;
               monsterBody[p] = (byte)bodyType;
               int frmCount = data.readShort();
               monsterImageIndex[p] = new short[frmCount][];
               monsterMirrorAndRef[p] = new byte[frmCount][];
               InputStream isData = null;
               DataInputStream disData = null;
               isData = Util.loadFile("/pkg_npc/npcdata/npc" + fc + ".dat", true);
               disData = new DataInputStream(isData);
               disData.readShort();

               int fc;
               byte m;
               for(fc = 0; fc < frmCount; ++fc) {
                  disData.readShort();
                  m = disData.readByte();
                  monsterImageIndex[p][fc] = new short[m];
                  monsterMirrorAndRef[p][fc] = new byte[m];

                  for(byte m = 0; m < m; ++m) {
                     monsterImageIndex[p][fc][m] = disData.readShort();
                     monsterMirrorAndRef[p][fc][m] = disData.readByte();
                     if ((monsterMirrorAndRef[p][fc][m] & 8) == 0) {
                        if (!temp.containsKey(new String(monsterImageIndex[p][fc][m] + "P"))) {
                           temp.put(new String(monsterImageIndex[p][fc][m] + "P"), new Short(monsterImageIndex[p][fc][m]));
                        }
                     } else if (!temp.containsKey(new String(monsterImageIndex[p][fc][m] + "M"))) {
                        temp.put(new String(monsterImageIndex[p][fc][m] + "M"), new Short(imgIndCount++));
                     }
                  }
               }

               for(fc = 0; fc < frmCount; ++fc) {
                  for(m = 0; m < monsterImageIndex[p][fc].length; ++m) {
                     if ((monsterMirrorAndRef[p][fc][m] & 8) == 0) {
                        monsterImageIndex[p][fc][m] = (Short)temp.get(new String(monsterImageIndex[p][fc][m] + "P"));
                     } else {
                        monsterImageIndex[p][fc][m] = (Short)temp.get(new String(monsterImageIndex[p][fc][m] + "M"));
                     }
                  }
               }

               disData.close();
               disData = null;
               isData = null;
            } else {
               m = data.readShort();
               monsterBody[p] = (byte)m;
               short frmCount = data.readShort();
               monsterImageIndex[p] = new short[frmCount][];
               monsterMirrorAndRef[p] = new byte[frmCount][];

               byte m;
               for(fc = 0; fc < frmCount; ++fc) {
                  data.readShort();
                  m = data.readByte();
                  monsterImageIndex[p][fc] = new short[m];
                  monsterMirrorAndRef[p][fc] = new byte[m];

                  for(byte m = 0; m < m; ++m) {
                     monsterImageIndex[p][fc][m] = data.readShort();
                     monsterMirrorAndRef[p][fc][m] = data.readByte();
                     if ((monsterMirrorAndRef[p][fc][m] & 8) == 0) {
                        if (!temp.containsKey(new String(monsterImageIndex[p][fc][m] + "P"))) {
                           temp.put(new String(monsterImageIndex[p][fc][m] + "P"), new Short(monsterImageIndex[p][fc][m]));
                        }
                     } else if (!temp.containsKey(new String(monsterImageIndex[p][fc][m] + "M"))) {
                        temp.put(new String(monsterImageIndex[p][fc][m] + "M"), new Short(imgIndCount++));
                     }
                  }
               }

               for(fc = 0; fc < frmCount; ++fc) {
                  for(m = 0; m < monsterImageIndex[p][fc].length; ++m) {
                     if ((monsterMirrorAndRef[p][fc][m] & 8) == 0) {
                        monsterImageIndex[p][fc][m] = (Short)temp.get(new String(monsterImageIndex[p][fc][m] + "P"));
                     } else {
                        monsterImageIndex[p][fc][m] = (Short)temp.get(new String(monsterImageIndex[p][fc][m] + "M"));
                     }
                  }
               }
            }
         }

         temp.clear();
         temp = null;
         short count = data.readShort();
         imgSTiles = new Image[count];
         nSTilePos = new byte[count * 2];
         nSTileID = new short[count];
         nSTileFlag = new byte[count];
         temPngName = new String[count];

         for(short n = 0; n < count; ++n) {
            nSTileID[n] = data.readShort();
            nSTileFlag[n] = 0;
            m = n;

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

            nSTilePos[2 * n] = data.readByte();
            nSTilePos[2 * n + 1] = data.readByte();
         }

         for(int i = 0; i < imgSTiles.length; ++i) {
            if (temPngName[i] != null && MonsterImgHa.containsKey(temPngName[i])) {
               imgSTiles[i] = (Image)MonsterImgHa.get(temPngName[i]);
            }
         }

         imgSTiles = Util.loadImageMoster(imgSTiles, "/pkg_npc/mimage/monsterbody.pkg", temPngName);
         data.close();
         data = null;
      } catch (Exception var17) {
         var17.printStackTrace();
      }

   }

   public void setDirection(byte dir) {
      super.direction = dir;
      this.setMotionState(super.state);
   }

   public void setState(byte s) {
      super.state = s;
      super.nNextFrame = 0;
      this.setMotionState(super.state);
   }

   public void setDistinction(int s) {
      this.distinction = s;
   }

   public int getDistinction() {
      return this.distinction;
   }

   public void setSadGO(SAData sad) {
      this.sadGO = sad;
   }

   public SAData getSadGO() {
      return this.sadGO;
   }

   public void draw(Graphics g) {
      this.drawBody(g, super.drawX, super.drawY);
      this.drawSpecial(g);
   }

   public void drawBody(Graphics g, short dx, short dy) {
      if (!super.isInvisible) {
         byte boneCount = (byte)this.sadGO.nSkeleton[this.currentFrameID].length;
         short px = false;
         short py = false;
         short imgIndex = true;
         byte mirror = true;

         for(byte p = 0; p < boneCount; p = (byte)(p + 2)) {
            try {
               short px;
               if (super.bRMirror) {
                  px = (short)(dx - this.sadGO.nSkeleton[this.currentFrameID][p]);
               } else {
                  px = (short)(dx + this.sadGO.nSkeleton[this.currentFrameID][p]);
               }

               short py = (short)(dy + this.sadGO.nSkeleton[this.currentFrameID][p + 1]);
               short imgIndex = monsterImageIndex[this.apparel][this.currentFrameID][p >> 1];
               byte mirror = monsterMirrorAndRef[this.apparel][this.currentFrameID][p >> 1];
               if ((mirror & 16) == 0) {
                  Image boneImg = null;
                  short tx = false;
                  short ty = false;
                  short tx;
                  short ty;
                  if ((mirror & 8) == 0) {
                     if (OtherPlayer.nSTileFlag[imgIndex] == 0) {
                        boneImg = OtherPlayer.imgSTiles[imgIndex];
                     } else {
                        boneImg = OtherPlayer.imgSTiles[OtherPlayer.nSTileID[imgIndex]];
                     }

                     switch(mirror) {
                     case 0:
                        ty = (short)(py - OtherPlayer.nSTilePos[2 * imgIndex + 1]);
                        if (super.bRMirror) {
                           tx = (short)(px - (boneImg.getWidth() - OtherPlayer.nSTilePos[2 * imgIndex]));
                           Util.drawImage(g, boneImg, tx, ty, 2);
                        } else {
                           tx = (short)(px - OtherPlayer.nSTilePos[2 * imgIndex]);
                           Util.drawImage(g, boneImg, tx, ty, 0);
                        }
                        break;
                     case 1:
                        ty = (short)(py - OtherPlayer.nSTilePos[2 * imgIndex]);
                        if (super.bRMirror) {
                           tx = (short)(px - OtherPlayer.nSTilePos[2 * imgIndex + 1] - 1);
                           Util.drawImage(g, boneImg, tx, ty, 4);
                        } else {
                           tx = (short)(px - (boneImg.getHeight() - OtherPlayer.nSTilePos[2 * imgIndex + 1]) + 1);
                           Util.drawImage(g, boneImg, tx, ty, 5);
                        }
                        break;
                     case 2:
                        ty = (short)(py - (boneImg.getHeight() - OtherPlayer.nSTilePos[2 * imgIndex + 1]) + 1);
                        if (super.bRMirror) {
                           tx = (short)(px - OtherPlayer.nSTilePos[2 * imgIndex] - 1);
                           Util.drawImage(g, boneImg, tx, ty, 1);
                        } else {
                           tx = (short)(px - (boneImg.getWidth() - OtherPlayer.nSTilePos[2 * imgIndex]) + 1);
                           Util.drawImage(g, boneImg, tx, ty, 3);
                        }
                        break;
                     case 3:
                        ty = (short)(py - (boneImg.getWidth() - OtherPlayer.nSTilePos[2 * imgIndex]) + 1);
                        if (super.bRMirror) {
                           tx = (short)(px - (boneImg.getHeight() - OtherPlayer.nSTilePos[2 * imgIndex + 1]));
                           Util.drawImage(g, boneImg, tx, ty, 7);
                        } else {
                           tx = (short)(px - OtherPlayer.nSTilePos[2 * imgIndex + 1]);
                           Util.drawImage(g, boneImg, tx, ty, 6);
                        }
                        break;
                     case 4:
                        ty = (short)(py - OtherPlayer.nSTilePos[2 * imgIndex + 1]);
                        if (super.bRMirror) {
                           tx = (short)(px - OtherPlayer.nSTilePos[2 * imgIndex] - 1);
                           Util.drawImage(g, boneImg, tx, ty, 0);
                        } else {
                           tx = (short)(px - (boneImg.getWidth() - OtherPlayer.nSTilePos[2 * imgIndex]) + 1);
                           Util.drawImage(g, boneImg, tx, ty, 2);
                        }
                        break;
                     case 5:
                        ty = (short)(py - OtherPlayer.nSTilePos[2 * imgIndex]);
                        if (super.bRMirror) {
                           tx = (short)(px - (boneImg.getHeight() - OtherPlayer.nSTilePos[2 * imgIndex + 1]) - 1);
                           Util.drawImage(g, boneImg, tx, ty, 5);
                        } else {
                           tx = (short)(px - OtherPlayer.nSTilePos[2 * imgIndex + 1]);
                           Util.drawImage(g, boneImg, tx, ty, 4);
                        }
                        break;
                     case 6:
                        ty = (short)(py - (boneImg.getHeight() - OtherPlayer.nSTilePos[2 * imgIndex + 1]) + 1);
                        if (super.bRMirror) {
                           tx = (short)(px - (boneImg.getWidth() - OtherPlayer.nSTilePos[2 * imgIndex]));
                           Util.drawImage(g, boneImg, tx, ty, 3);
                        } else {
                           tx = (short)(px - OtherPlayer.nSTilePos[2 * imgIndex]);
                           Util.drawImage(g, boneImg, tx, ty, 1);
                        }
                        break;
                     case 7:
                        ty = (short)(py - (boneImg.getWidth() - OtherPlayer.nSTilePos[2 * imgIndex]) + 1);
                        if (super.bRMirror) {
                           tx = (short)(px - OtherPlayer.nSTilePos[2 * imgIndex + 1] - 1);
                           Util.drawImage(g, boneImg, tx, ty, 6);
                        } else {
                           tx = (short)(px - (boneImg.getHeight() - OtherPlayer.nSTilePos[2 * imgIndex + 1]) + 1);
                           Util.drawImage(g, boneImg, tx, ty, 7);
                        }
                     }
                  } else {
                     mirror = (byte)(mirror & 7);
                     if (nSTileFlag[imgIndex] == 0) {
                        boneImg = imgSTiles[imgIndex];
                     } else {
                        boneImg = imgSTiles[nSTileID[imgIndex]];
                     }

                     switch(mirror) {
                     case 0:
                        ty = (short)(py - nSTilePos[2 * imgIndex + 1]);
                        if (super.bRMirror) {
                           tx = (short)(px - (boneImg.getWidth() - nSTilePos[2 * imgIndex]));
                           Util.drawImage(g, boneImg, tx, ty, 2);
                        } else {
                           tx = (short)(px - nSTilePos[2 * imgIndex]);
                           g.drawImage(boneImg, tx, ty, 20);
                        }
                        break;
                     case 1:
                        ty = (short)(py - nSTilePos[2 * imgIndex]);
                        if (super.bRMirror) {
                           tx = (short)(px - nSTilePos[2 * imgIndex + 1] - 1);
                           Util.drawImage(g, boneImg, tx, ty, 4);
                        } else {
                           tx = (short)(px - (boneImg.getHeight() - nSTilePos[2 * imgIndex + 1]) + 1);
                           Util.drawImage(g, boneImg, tx, ty, 5);
                        }
                        break;
                     case 2:
                        ty = (short)(py - (boneImg.getHeight() - nSTilePos[2 * imgIndex + 1]) + 1);
                        if (super.bRMirror) {
                           tx = (short)(px - nSTilePos[2 * imgIndex] - 1);
                           Util.drawImage(g, boneImg, tx, ty, 1);
                        } else {
                           tx = (short)(px - (boneImg.getWidth() - nSTilePos[2 * imgIndex]) + 1);
                           Util.drawImage(g, boneImg, tx, ty, 3);
                        }
                        break;
                     case 3:
                        ty = (short)(py - (boneImg.getWidth() - nSTilePos[2 * imgIndex]) + 1);
                        if (super.bRMirror) {
                           tx = (short)(px - (boneImg.getHeight() - nSTilePos[2 * imgIndex + 1]));
                           Util.drawImage(g, boneImg, tx, ty, 7);
                        } else {
                           tx = (short)(px - nSTilePos[2 * imgIndex + 1]);
                           Util.drawImage(g, boneImg, tx, ty, 6);
                        }
                        break;
                     case 4:
                        ty = (short)(py - nSTilePos[2 * imgIndex + 1]);
                        if (super.bRMirror) {
                           tx = (short)(px - nSTilePos[2 * imgIndex] - 1);
                           g.drawImage(boneImg, tx, ty, 20);
                        } else {
                           tx = (short)(px - (boneImg.getWidth() - nSTilePos[2 * imgIndex]) + 1);
                           Util.drawImage(g, boneImg, tx, ty, 2);
                        }
                        break;
                     case 5:
                        ty = (short)(py - nSTilePos[2 * imgIndex]);
                        if (super.bRMirror) {
                           tx = (short)(px - (boneImg.getHeight() - nSTilePos[2 * imgIndex + 1]) - 1);
                           Util.drawImage(g, boneImg, tx, ty, 5);
                        } else {
                           tx = (short)(px - nSTilePos[2 * imgIndex + 1]);
                           Util.drawImage(g, boneImg, tx, ty, 4);
                        }
                        break;
                     case 6:
                        ty = (short)(py - (boneImg.getHeight() - nSTilePos[2 * imgIndex + 1]) + 1);
                        if (super.bRMirror) {
                           tx = (short)(px - (boneImg.getWidth() - nSTilePos[2 * imgIndex]));
                           Util.drawImage(g, boneImg, tx, ty, 3);
                        } else {
                           tx = (short)(px - nSTilePos[2 * imgIndex]);
                           Util.drawImage(g, boneImg, tx, ty, 1);
                        }
                        break;
                     case 7:
                        ty = (short)(py - (boneImg.getWidth() - nSTilePos[2 * imgIndex]) + 1);
                        if (super.bRMirror) {
                           tx = (short)(px - nSTilePos[2 * imgIndex + 1] - 1);
                           Util.drawImage(g, boneImg, tx, ty, 6);
                        } else {
                           tx = (short)(px - (boneImg.getHeight() - nSTilePos[2 * imgIndex + 1]) + 1);
                           Util.drawImage(g, boneImg, tx, ty, 7);
                        }
                     }
                  }
               }
            } catch (Exception var13) {
               var13.printStackTrace();
            }
         }

      }
   }

   public void tick() {
      switch(super.state) {
      case 1:
         this.movePath();
      case 0:
      default:
         this.motionState_tick();
         this.tickHpChangeVectorPop();
         this.tickSpecial();
         super.drawX = (short)(super.x - Map.currentWindowX + 0);
         super.drawY = (short)(super.y - Map.currentWindowY + 3 + 0);
      }
   }

   public void setMotionState(byte s) {
      byte nState;
      nState = 0;
      label52:
      switch(s) {
      case 0:
         switch(this.getDirection()) {
         case 1:
         case 8:
            nState = 0;
            super.bRMirror = false;
            break label52;
         case 2:
         case 7:
            nState = 1;
            super.bRMirror = false;
            break label52;
         case 3:
         case 5:
            nState = 2;
            super.bRMirror = false;
            break label52;
         case 4:
         case 6:
            nState = 1;
            super.bRMirror = true;
         default:
            break label52;
         }
      case 1:
         switch(this.getDirection()) {
         case 1:
         case 8:
            nState = 3;
            super.bRMirror = false;
            break label52;
         case 2:
         case 7:
            nState = 4;
            super.bRMirror = false;
            break label52;
         case 3:
         case 5:
            nState = 5;
            super.bRMirror = false;
            break label52;
         case 4:
         case 6:
            nState = 4;
            super.bRMirror = true;
         default:
            break label52;
         }
      case 2:
         switch(this.getDirection()) {
         case 1:
         case 8:
            nState = 6;
            super.bRMirror = false;
            break label52;
         case 2:
         case 7:
            nState = 7;
            super.bRMirror = false;
            break label52;
         case 3:
         case 5:
            nState = 8;
            super.bRMirror = false;
            break label52;
         case 4:
         case 6:
            nState = 7;
            super.bRMirror = true;
         default:
            break label52;
         }
      case 3:
         switch(this.getDirection()) {
         case 1:
         case 8:
            nState = 9;
            super.bRMirror = false;
            break label52;
         case 2:
         case 7:
            nState = 10;
            super.bRMirror = false;
            break label52;
         case 3:
         case 5:
            nState = 11;
            super.bRMirror = false;
            break label52;
         case 4:
         case 6:
            nState = 10;
            super.bRMirror = true;
         default:
            break label52;
         }
      case 4:
         switch(this.getDirection()) {
         case 1:
         case 8:
            nState = 12;
            super.bRMirror = false;
            break;
         case 2:
         case 7:
            nState = 13;
            super.bRMirror = false;
            break;
         case 3:
         case 5:
            nState = 14;
            super.bRMirror = false;
            break;
         case 4:
         case 6:
            nState = 13;
            super.bRMirror = true;
         }
      case 5:
      case 6:
      case 7:
      case 8:
      default:
         break;
      case 9:
         nState = 15;
      }

      this.currentmotionID = (short)nState;
   }

   public void motionState_tick() {
      if (!super.isFreezing) {
         if (super.nNextFrame >= this.sadGO.nAnimation[this.currentmotionID].length) {
            super.nNextFrame = 0;
            switch(this.getState()) {
            case 2:
               if (this.setAimRowColFindPath(Map.getCurrentRow(this.getMapAimY(), this.getMapAimX()), Map.getCurrentColumn(this.getMapAimY(), this.getMapAimX()))) {
                  this.setState((byte)1);
               } else {
                  this.setState((byte)0);
               }
               break;
            case 3:
               this.setState((byte)4);
               break;
            case 4:
               this.setState((byte)0);
            case 5:
            case 6:
            case 7:
            case 8:
            default:
               break;
            case 9:
               super.nNextFrame = (byte)(this.sadGO.nAnimation[this.currentmotionID].length - 1);
            }
         }

         short[] var10001 = this.sadGO.nAnimation[this.currentmotionID];
         byte var10004 = super.nNextFrame;
         super.nNextFrame = (byte)(var10004 + 1);
         this.currentFrameID = var10001[var10004];
      }
   }

   public byte getSadGODx() {
      return SAData.RECT[this.sadGO.nID][0];
   }

   public byte getSadGODy() {
      return SAData.RECT[this.sadGO.nID][1];
   }

   public byte getSadGOWidth() {
      return SAData.RECT[this.sadGO.nID][2];
   }

   public byte getSadGOHeight() {
      return SAData.RECT[this.sadGO.nID][3];
   }
}

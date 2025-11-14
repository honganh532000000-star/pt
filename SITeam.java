import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class SITeam {
   public static byte divideType = 0;
   public static int rollGoodsId = 0;
   public static boolean isShowName = true;
   public static final byte TEAM_STATE_NONE = 0;
   public static final byte TEAM_STATE_TEAM = 1;
   public static final byte TEAM_STATE_RAID = 2;
   public static byte teamState = 0;
   public static int otherPlayerID = -1;
   public static int headerID = -1;
   public static int UIHeaderID = -1;
   public static OtherPlayer[] teamMates;
   public static final int TEAM_BUFF_WIDTH = 48;
   public static final int TEAM_BUFF_HEIGHT = 14;
   public static final int TEAM_BUFF_DX = 2;
   public static final int TEAM_BUFF_DY = 2;
   public static final int TEAM_BUFF_SPACE = 26;
   private static final int TEAM_FRAME_WIDTH = 16;
   private static final int PROFESSION_DX = 1;
   private static final int PROFESSION_WIDTH = 12;
   private static final int LEVEL_DX = 14;
   private static final int LEVEL_DY = 7;
   private static int HP_DX;
   private static int HP_DY;
   private static int MP_DY;
   private static int HP_WIDTH;
   private static int HP_HEIGHT;
   public static final int HP_COLOR = 15728640;
   public static final int MP_COLOR = 18416;
   public static byte m_bCopyDifficulty = 0;
   public Image buff = null;

   public SITeam() {
      this.initBuff();
   }

   public final void initBuff() {
      this.buff = Image.createImage(48, 14);
      Graphics gBuff = this.buff.getGraphics();
      Util.fillRect(gBuff, 0, 0, this.buff.getWidth(), this.buff.getHeight(), Cons.COLOR_TEAM_BACKGROUND);
      Util.drawRect(gBuff, 0, 0, this.buff.getHeight(), this.buff.getHeight(), Cons.COLOR_PANEL_BORDER_2);
      MainCanvas.ui_numMImg.draw(gBuff, this.buff.getHeight(), 0, 10);
      MainCanvas.ui_numMImg.draw(gBuff, this.buff.getHeight() + MainCanvas.ui_numMImg.frame_w - 1, 0, 11);
      int frameX = this.buff.getHeight() + (MainCanvas.ui_numMImg.frame_w << 1) - 2;
      int frameWidth = this.buff.getWidth() - frameX;
      int frameHeight = this.buff.getHeight() >> 1;
      Util.drawRect(gBuff, frameX, 0, frameWidth, frameHeight, Cons.COLOR_TEAM_BORDER_1);
      Util.drawRect(gBuff, frameX + 1, 1, frameWidth - 2, frameHeight - 2, Cons.COLOR_TEAM_BORDER_2);
      Util.drawRect(gBuff, frameX, frameHeight, frameWidth, frameHeight, Cons.COLOR_TEAM_BORDER_1);
      Util.drawRect(gBuff, frameX + 1, frameHeight + 1, frameWidth - 2, frameHeight - 2, Cons.COLOR_TEAM_BORDER_2);
      HP_DX = frameX + 2;
      HP_DY = 2;
      MP_DY = frameHeight + 2;
      HP_WIDTH = frameWidth - 4;
      HP_HEIGHT = frameHeight - 4;
   }

   public static void clear() {
      teamState = 0;
      otherPlayerID = -1;
      headerID = -1;
      UIHeaderID = -1;
      teamMates = null;
   }

   public static int findLover() {
      return teamMates != null && teamMates.length > 0 ? teamMates[0].getID() : 0;
   }

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 720897:
         ba.writeInt(otherPlayerID);
         break;
      case 720898:
      case 720909:
         ba.writeInt(headerID);
      case 720899:
      case 720900:
      case 720908:
      case 720919:
      default:
         break;
      case 720901:
      case 720902:
         ba.writeInt(otherPlayerID);
         MainCanvas.isWaiting = true;
         break;
      case 720903:
         ba.writeByte(divideType);
         break;
      case 720904:
      case 720905:
      case 720906:
      case 720907:
         MainCanvas.isWaiting = true;
         break;
      case 720910:
         MainCanvas.isWaiting = true;
         break;
      case 720911:
      case 720912:
         ba.writeInt(rollGoodsId);
         break;
      case 720913:
      case 720915:
      case 720916:
         MainCanvas.isWaiting = true;
         ba.writeInt(rollGoodsId);
         break;
      case 720914:
         ba.writeInt(Player.busyId);
         break;
      case 720917:
         ba.writeInt(rollGoodsId);
         break;
      case 720918:
         ba.writeInt(rollGoodsId);
         ba.writeByte(UIList.PCSelectedIndex);
         break;
      case 720920:
         MainCanvas.isWaiting = true;
         break;
      case 720921:
         if (m_bCopyDifficulty == 0) {
            ba.writeInt(4122);
         } else if (m_bCopyDifficulty == 1) {
            ba.writeInt(4123);
         } else if (m_bCopyDifficulty == 2) {
            ba.writeInt(4124);
         }
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      int i;
      switch(commID) {
      case -2146762751:
         MainCanvas.quitUI();
         break;
      case -2146762750:
         teamState = data.readByte();
         teamMates = new OtherPlayer[data.readByte() - 1];
         headerID = data.readInt();

         for(i = 0; i < teamMates.length; ++i) {
            teamMates[i] = new OtherPlayer();
            teamMates[i].roleName = data.readUTF();
            teamMates[i].ID = data.readInt();
            teamMates[i].profession = data.readByte();
            teamMates[i].level = data.readShort();
            teamMates[i].curHP = data.readInt();
            teamMates[i].maxHP = data.readInt();
            teamMates[i].curMP = data.readInt();
            teamMates[i].maxMP = data.readInt();
            teamMates[i].setMapID(data.readShort());
            teamMates[i].x = data.readShort();
            teamMates[i].y = data.readShort();

            for(byte k = 0; k < 12; ++k) {
               teamMates[i].buffTYPE[k] = data.readByte();
            }
         }

         return;
      case -2146762749:
         teamMates = null;
         teamState = 0;
         if (m_bCopyDifficulty != 0) {
            m_bCopyDifficulty = 0;
         }
         break;
      case -2146762748:
      case -2146762747:
      case -2146762746:
      case -2146762745:
      case -2146762740:
         i = data.readInt();

         for(int i = 0; i < teamMates.length; ++i) {
            if (teamMates[i].getID() == i) {
               switch(commID) {
               case -2146762748:
                  teamMates[i].level = data.readShort();
                  teamMates[i].curHP = data.readInt();
                  teamMates[i].maxHP = data.readInt();
                  teamMates[i].curMP = data.readInt();
                  teamMates[i].maxMP = data.readInt();
                  return;
               case -2146762747:
                  teamMates[i].curHP = data.readInt();
                  teamMates[i].curMP = data.readInt();
                  return;
               case -2146762746:
                  for(byte k = 0; k < 12; ++k) {
                     teamMates[i].buffTYPE[k] = data.readByte();
                  }

                  return;
               case -2146762745:
                  teamMates[i].setMapID(data.readShort());
                  teamMates[i].x = data.readShort();
                  teamMates[i].y = data.readShort();
                  return;
               case -2146762744:
               case -2146762743:
               case -2146762742:
               case -2146762741:
               default:
                  return;
               case -2146762740:
                  teamMates[i].profession = data.readByte();
                  return;
               }
            }
         }
      case -2146762744:
      case -2146762739:
      default:
         break;
      case -2146762743:
      case -2146762741:
         MainCanvas.ni.sendCommands(new int[]{720905, 720906, 720907});
         break;
      case -2146762742:
         headerID = data.readInt();
         break;
      case -2146762738:
         MainCanvas.cmdID = commID;
         MainCanvas.cmdData = data.readByteArray(data.getLength() - 6);
      }

   }

   public final void draw(Graphics g) {
      if (GOManager.currentTarget != null) {
         if (GOManager.currentTarget.getID() == Player.getInstance().getID()) {
            this.drawMapName(g);
         } else if (GOManager.currentTarget.getType() == 1 || GOManager.currentTarget.getType() == 3 || GOManager.currentTarget.getType() == 2) {
            this.drawTargetInfo(g);
         }
      }

      if (teamMates != null) {
         int dy = 2 + SIManager.SIDy;
         int drawSpace = 26;
         int drawStartY = dy;
         if (isShowName) {
            drawSpace += MainCanvas.CHARH;
            drawStartY = dy + MainCanvas.CHARH;
         }

         for(int i = 0; i < teamMates.length; ++i) {
            if (isShowName) {
               Util.drawStringShadow(g, teamMates[i].getRoleName(), 2, dy + i * drawSpace, Cons.OBJ_NAME_COLOR[3], 0);
            }

            g.drawImage(this.buff, 2, drawStartY + i * drawSpace, 20);
            Util.drawRect(g, 1, drawStartY + i * drawSpace - 1, 16, 16, Cons.COLOR_TEAM_BORDER_1);
            MainCanvas.zyMImg.draw(g, 3 + (12 - MainCanvas.zyMImg.frame_w >> 1), drawStartY + i * drawSpace + 1 + (12 - MainCanvas.zyMImg.frame_h >> 1), teamMates[i].getProfession());
            MainCanvas.ui_numMImg.draw(g, 16, drawStartY + i * drawSpace + 7, teamMates[i].getLevel() / 10);
            MainCanvas.ui_numMImg.draw(g, 16 + MainCanvas.ui_numMImg.frame_w - 1, drawStartY + i * drawSpace + 7, teamMates[i].getLevel() % 10);
            int HPWidth = teamMates[i].curHP * HP_WIDTH / teamMates[i].maxHP;
            if (HPWidth > HP_WIDTH) {
               HPWidth = HP_WIDTH;
            }

            Util.fillRect(g, 2 + HP_DX, drawStartY + i * drawSpace + HP_DY, HPWidth, HP_HEIGHT, 15728640);
            int EPWidth = false;
            int EPColor = false;
            int EPWidth = teamMates[i].curMP * HP_WIDTH / teamMates[i].maxMP;
            EPColor = true;
            if (EPWidth > HP_WIDTH) {
               EPWidth = HP_WIDTH;
            }

            int EPColor = 18416;
            Util.fillRect(g, 2 + HP_DX, drawStartY + i * drawSpace + MP_DY, EPWidth, HP_HEIGHT, EPColor);
            this.drawBuff(g, teamMates[i], 2, drawStartY + i * drawSpace);
         }

      }
   }

   private void drawMapName(Graphics g) {
      String mn = Map.getInstance().getMap_Name() + "(" + Map.getCurrentRow(Player.getInstance().y, Player.getInstance().x) + "," + Map.getCurrentColumn(Player.getInstance().y, Player.getInstance().x) + ")";
      Util.drawStringShadow(g, mn, MainCanvas.screenW - g.getFont().stringWidth(mn) - 1, 2 + SIManager.SIDy, 16777215, 0);
   }

   private void drawTargetInfo(Graphics g) {
      short x = 2;
      short y = (short)(2 + MainCanvas.CHARH + SIManager.SIDy);
      g.drawImage(this.buff, MainCanvas.screenW - 48 - x, y, 20);
      Util.drawRect(g, MainCanvas.screenW - 48 - x - 1, y - 1, 16, 16, Cons.COLOR_TEAM_BORDER_1);
      if (((ActiveGO)GOManager.currentTarget).getType() == 3) {
         if (((ActiveGO)GOManager.currentTarget).getPrestige() != -4 && ((ActiveGO)GOManager.currentTarget).getPrestige() != -3 && ((ActiveGO)GOManager.currentTarget).getPrestige() != -2) {
            Util.drawStringShadow(g, ((ActiveGO)GOManager.currentTarget).getRoleName(), MainCanvas.screenW - g.getFont().stringWidth(((ActiveGO)GOManager.currentTarget).getRoleName()) - 2, 2 + SIManager.SIDy, 55551, 0);
         } else {
            Util.drawStringShadow(g, ((ActiveGO)GOManager.currentTarget).getRoleName(), MainCanvas.screenW - g.getFont().stringWidth(((ActiveGO)GOManager.currentTarget).getRoleName()) - 2, 2 + SIManager.SIDy, ((ActiveGO)GOManager.currentTarget).getOBJNameCol(((ActiveGO)GOManager.currentTarget).getPrestige()), 0);
         }
      } else {
         Util.drawStringShadow(g, ((ActiveGO)GOManager.currentTarget).getRoleName(), MainCanvas.screenW - g.getFont().stringWidth(((ActiveGO)GOManager.currentTarget).getRoleName()) - 2, 2 + SIManager.SIDy, ((ActiveGO)GOManager.currentTarget).getOBJNameCol(((ActiveGO)GOManager.currentTarget).getPrestige()), 0);
      }

      this.drawTargetHead(g, x, y);
      MainCanvas.ui_numMImg.draw(g, MainCanvas.screenW - 48 - x + 14, y + 7, ((ActiveGO)GOManager.currentTarget).getLevel() / 10);
      MainCanvas.ui_numMImg.draw(g, MainCanvas.screenW - 48 - x + 14 + MainCanvas.ui_numMImg.frame_w - 1, y + 7, ((ActiveGO)GOManager.currentTarget).getLevel() % 10);
      Util.fillRect(g, MainCanvas.screenW - 48 - x + HP_DX, y + HP_DY, ((ActiveGO)GOManager.currentTarget).getCurrentHP() * HP_WIDTH / Math.max(1, ((ActiveGO)GOManager.currentTarget).getMAXHP()), HP_HEIGHT, 15728640);
      int tmpWidth = 0;
      int tmpColor = false;
      if (((ActiveGO)GOManager.currentTarget).getMAXMP() != 0) {
         tmpWidth = ((ActiveGO)GOManager.currentTarget).getCurrentMP() * HP_WIDTH / Math.max(1, ((ActiveGO)GOManager.currentTarget).getMAXMP());
         tmpColor = true;
      }

      int tmpColor = 18416;
      Util.fillRect(g, MainCanvas.screenW - 48 - x + HP_DX, y + MP_DY, tmpWidth, HP_HEIGHT, tmpColor);
      this.drawBuff(g, (ActiveGO)GOManager.currentTarget, MainCanvas.screenW - 48 - x, y);
   }

   private void drawBuff(Graphics g, ActiveGO go, int x, int y) {
      for(byte i = 0; i < MainCanvas.buffImg.cols; ++i) {
         if (go.buffTYPE[i] != 0) {
            if (i < 6) {
               MainCanvas.buffImg.draw(g, x + 14 + i * 9, y + MP_DY + HP_HEIGHT + 3, go.buffTYPE[i] - 1);
            } else {
               MainCanvas.buffImg.draw(g, x + 14 + (i - 6) * 9, y + MP_DY + HP_HEIGHT + 3 + 9, go.buffTYPE[i] - 1);
            }
         }
      }

   }

   private void drawTargetHead(Graphics g, short x, short y) {
      switch(GOManager.currentTarget.getType()) {
      case 1:
         MainCanvas.zyMImg.draw(g, MainCanvas.screenW - 48 - x + 1 + (12 - MainCanvas.zyMImg.frame_w >> 1), y + 1 + (12 - MainCanvas.zyMImg.frame_h >> 1), ((ActiveGO)GOManager.currentTarget).getProfession());
         break;
      case 2:
         Monster tempm = (Monster)GOManager.currentTarget;
         MainCanvas.zyMImg.draw(g, MainCanvas.screenW - 48 - x + 1 + (12 - MainCanvas.zyMImg.frame_w >> 1), y + 1 + (12 - MainCanvas.zyMImg.frame_h >> 1), tempm.getDistinction());
         tempm = null;
         break;
      case 3:
         MainCanvas.zyMImg.draw(g, MainCanvas.screenW - 48 - x + 1 + (12 - MainCanvas.zyMImg.frame_w >> 1), y + 1 + (12 - MainCanvas.zyMImg.frame_h >> 1), 7);
      }

   }
}

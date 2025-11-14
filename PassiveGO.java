import javax.microedition.lcdui.Graphics;

public class PassiveGO extends GameObject {
   public SAData sadGO = null;
   public static final byte PassiveGO_STATE_LIVE = 0;
   public static final byte PassiveGO_STATE_DEAD = 1;
   public byte imgInd = 0;
   public byte m_bFlagIndex = 0;

   public PassiveGO(byte ntype) {
      super.type = ntype;
      super.nNextFrame = 0;
      this.setState((byte)0);
   }

   public void setSadGO(SAData sad) {
      this.sadGO = sad;
   }

   public SAData getSadGO() {
      return this.sadGO;
   }

   public byte getImgInd() {
      return this.imgInd;
   }

   public void setImgInd(byte imgInd) {
      this.imgInd = imgInd;
   }

   public void draw(Graphics g) {
      this.drawBody(g, super.drawX, super.drawY);
      if (Map.m_bBattlefield) {
         this.drawflag(g, super.drawX, super.drawY);
      }

      if (super.type == 11) {
         this.drawflag(g, super.drawX, super.drawY);
      }

   }

   public void drawBody(Graphics g, short x, short y) {
      MainCanvas.kuangimg.draw(g, x - (MainCanvas.kuangimg.frame_w >> 1), y - (MainCanvas.kuangimg.frame_h >> 1), this.getImgInd());
   }

   public void drawName(Graphics g) {
      Util.drawStringShadow(g, this.getRoleName(), super.drawX - (g.getFont().stringWidth(this.getRoleName()) >> 1), super.drawY - (!Map.m_bBattlefield && super.type != 11 ? MainCanvas.kuangimg.frame_h >> 1 : (MainCanvas.red_flag.frame_h >> 1) + 15) - MainCanvas.CHARH, 65280, 0);
   }

   public void drawflag(Graphics g, short x, short y) {
      if (this.getImgInd() == 5) {
         MainCanvas.red_flag.draw(g, x - (MainCanvas.red_flag.frame_w >> 1) + 2, y - (MainCanvas.red_flag.frame_h >> 1) - 18, this.m_bFlagIndex);
      } else if (this.getImgInd() == 6) {
         MainCanvas.blue_flag.draw(g, x - (MainCanvas.blue_flag.frame_w >> 1) + 2, y - (MainCanvas.blue_flag.frame_h >> 1) - 18, this.m_bFlagIndex);
      } else if (this.getImgInd() == 7) {
         MainCanvas.gray_flag.draw(g, x - (MainCanvas.gray_flag.frame_w >> 1) + 2, y - (MainCanvas.gray_flag.frame_h >> 1) - 18, this.m_bFlagIndex);
      }

   }

   public void tick() {
      super.drawX = (short)(super.x - Map.currentWindowX + 0);
      super.drawY = (short)(super.y - Map.currentWindowY + 3 + 0);
      if (Map.m_bBattlefield) {
         ++this.m_bFlagIndex;
         if (this.m_bFlagIndex > 3) {
            this.m_bFlagIndex = 0;
         }
      }

   }

   public void motionState_tick() {
   }

   public void setState(byte state) {
   }

   public void setMotionState(byte s) {
   }
}

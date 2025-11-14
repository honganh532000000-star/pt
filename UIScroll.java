import javax.microedition.lcdui.Graphics;

public class UIScroll extends UIComponent {
   static final short OX = 2;
   static final short OY = 2;
   public static final byte TYPE_VERTICAL = 0;
   public static final byte TYPE_HORIZONTAL = 1;
   private short scrollPositionY = 0;
   private short scrollPositionX = 0;
   private boolean nonUseRelative = true;
   short sY1 = 0;
   short sY2 = 0;
   short sX1 = 0;
   short sX2 = 0;
   public boolean haveHorizontal = false;
   public boolean haveVertical = false;
   private int trackLength = 0;
   private short slipperStartX = 0;
   private short slipperStartY = 0;
   private short slipperEndX = 0;
   private short slipperEndY = 0;
   public short allRowNum = 0;
   public short canShowRowNum = 0;
   public short canShowColNum = 0;
   public short moreRowNum = 0;
   public short showStartRow = 0;

   public UIScroll(short x, short y, short w, short h, byte type, boolean nonUseRelative, UIForm form) {
      super(x, y, w, h, form);
      super.positionX = x;
      super.positionY = y;
      super.width = w;
      super.height = h;
      this.sY1 = (short)(super.positionY + MainCanvas.ui_3Img.getHeight());
      this.sY2 = (short)(this.setVerticalY(this.isHaveHorizontal()) - 1);
      this.sX1 = (short)(super.positionX + MainCanvas.ui_3Img.getWidth());
      this.sX2 = (short)(this.setHorizontalX(this.isHaveVertical()) - 1);
      this.setType(type);
      this.nonUseRelative = nonUseRelative;
      if (this.getType() == 0) {
         super.width = (short)MainCanvas.ui_3Img.getWidth();
         this.resetScrollPositionY();
         this.setSlipperTrackLength(this.sY1, this.sY2 + 1);
      } else if (this.getType() == 1) {
         super.height = (short)MainCanvas.ui_3Img.getHeight();
         this.resetScrollPositionX();
         this.setSlipperTrackLength(this.sX1, this.sX2 + 1);
      }

   }

   public int setVerticalY(boolean haveHorizontal) {
      return haveHorizontal ? super.positionY + super.height - 2 * MainCanvas.ui_3Img.getHeight() : super.positionY + super.height - MainCanvas.ui_3Img.getHeight();
   }

   public int setHorizontalX(boolean haveVertical) {
      return haveVertical ? super.positionX + super.width - 2 * MainCanvas.ui_3Img.getWidth() : super.positionX + super.width - MainCanvas.ui_3Img.getHeight();
   }

   public void draw(Graphics g) {
      if (this.getType() == 0) {
         g.drawImage(MainCanvas.ui_3Img, super.positionX, super.positionY, 20);
         Util.drawImage(g, MainCanvas.ui_3Img, super.positionX, this.setVerticalY(this.isHaveHorizontal()), 1);
         g.setColor(Cons.COLOR_SCROLL_LINE_1);
         g.drawLine(super.positionX + 2, this.slipperStartY, super.positionX + 2, this.sY2);
         g.setColor(Cons.COLOR_SCROLL_LINE_2);
         g.drawLine(super.positionX + 2 + 1, this.slipperStartY, super.positionX + 2 + 1, this.sY2);
         g.setColor(Cons.COLOR_SCROLL_LINE_3);
         g.drawLine(super.positionX + 2 + 2, this.slipperStartY, super.positionX + 2 + 2, this.sY2);
         g.setColor(Cons.COLOR_SCROLL_LINE_1);
         g.drawLine(super.positionX + 2 + 3, this.slipperStartY, super.positionX + 2 + 3, this.sY2);
         g.drawImage(MainCanvas.ui_2Img, super.positionX, this.scrollPositionY, 20);
      } else if (this.getType() == 1) {
         Util.drawImage(g, MainCanvas.ui_3Img, super.positionX, super.positionY, 6);
         Util.drawImage(g, MainCanvas.ui_3Img, this.setHorizontalX(this.isHaveVertical()), super.positionY, 5);
         g.setColor(Cons.COLOR_SCROLL_LINE_1);
         g.drawLine(this.sX1, super.positionY + 2, this.sX2, super.positionY + 2);
         g.setColor(Cons.COLOR_SCROLL_LINE_3);
         g.drawLine(this.sX1, super.positionY + 2 + 1, this.sX2, super.positionY + 2 + 1);
         g.setColor(Cons.COLOR_SCROLL_LINE_2);
         g.drawLine(this.sX1, super.positionY + 2 + 2, this.sX2, super.positionY + 2 + 2);
         g.setColor(Cons.COLOR_SCROLL_LINE_1);
         g.drawLine(this.sX1, super.positionY + 2 + 3, this.sX2, super.positionY + 2 + 3);
         Util.drawImage(g, MainCanvas.ui_2Img, this.scrollPositionX, super.positionY, 6);
      }

   }

   public void resetScrollPositionY() {
      this.sY2 = (short)(this.setVerticalY(this.isHaveHorizontal()) - 1);
      this.scrollPositionY = (short)(super.positionY + MainCanvas.ui_3Img.getHeight());
      this.slipperStartY = this.scrollPositionY;
      this.slipperEndY = (short)(super.positionY + super.height - MainCanvas.ui_3Img.getHeight() - this.getSlipperLength());
   }

   public void resetScrollPositionX() {
      this.scrollPositionX = (short)(super.positionX + MainCanvas.ui_3Img.getWidth());
      this.slipperStartX = this.scrollPositionX;
   }

   public boolean isHaveHorizontal() {
      return this.haveHorizontal;
   }

   public void setHaveHorizontal(boolean haveHorizontal) {
      this.haveHorizontal = haveHorizontal;
   }

   public boolean isHaveVertical() {
      return this.haveVertical;
   }

   public void setHaveVertical(boolean haveVertical) {
      this.haveVertical = haveVertical;
   }

   public void keyInScroll(short curSelect, boolean canSelect) {
      if (canSelect) {
         if (MainCanvas.isInputDownOrHold(20480)) {
            if (curSelect < this.showStartRow) {
               this.showStartRow = curSelect;
            }
         } else if (MainCanvas.isInputDownOrHold(40960) && curSelect >= this.showStartRow + this.canShowRowNum) {
            this.showStartRow = (short)(curSelect - this.canShowRowNum + 1);
         }
      }

      this.moreRowNum = (short)(this.allRowNum - this.canShowRowNum);
      if (this.moreRowNum > 0) {
         if (this.showStartRow == this.allRowNum - 1) {
            this.scrollPositionY = this.slipperEndY;
         } else {
            this.scrollPositionY = (short)(super.positionY + MainCanvas.ui_3Img.getHeight() + this.showStartRow * (this.trackLength - this.getSlipperLength()) / this.moreRowNum);
         }
      } else {
         this.scrollPositionY = (short)(super.positionY + MainCanvas.ui_3Img.getHeight());
      }

   }

   public void setSlipperTrackLength(int len_1, int len_2) {
      this.trackLength = Math.abs(len_1 - len_2);
      if (this.getType() == 0) {
         this.slipperEndY = (short)(super.positionY + MainCanvas.ui_3Img.getHeight() + this.trackLength - this.getSlipperLength());
      } else if (this.getType() == 1) {
         this.slipperEndX = (short)(super.positionX + MainCanvas.ui_3Img.getHeight() + this.trackLength - this.getSlipperLength());
      }

   }

   private int getSlipperLength() {
      return MainCanvas.ui_2Img != null ? MainCanvas.ui_2Img.getHeight() : 0;
   }

   public void setShowStartRow(short start) {
      this.showStartRow = start;
   }

   public short getShowStartRow() {
      return this.showStartRow;
   }

   public final void scrollPositionY(short allRowNum, short canShowRowNum, short showStartRow) {
      this.allRowNum = allRowNum;
      this.canShowRowNum = canShowRowNum;
      this.showStartRow = showStartRow;
      this.moreRowNum = (short)(allRowNum - canShowRowNum);
      if (this.moreRowNum > 0) {
         if (showStartRow == allRowNum - 1) {
            this.scrollPositionY = this.slipperEndY;
         } else {
            this.scrollPositionY = (short)(super.positionY + MainCanvas.ui_3Img.getHeight() + showStartRow * (this.trackLength - this.getSlipperLength()) / this.moreRowNum);
         }
      } else {
         this.scrollPositionY = (short)(super.positionY + MainCanvas.ui_3Img.getHeight());
      }

   }

   public final boolean focusWidgetPointAction() {
      if (MainCanvas.pointDownInRect(super.positionX, super.positionY, MainCanvas.ui_2Img.getWidth(), this.scrollPositionY - super.positionY)) {
         MainCanvas.keySimPressed(4096);
         return true;
      } else if (MainCanvas.pointDownInRect(super.positionX, this.scrollPositionY + MainCanvas.ui_2Img.getHeight(), MainCanvas.ui_2Img.getWidth(), super.positionY + super.height - this.scrollPositionY - MainCanvas.ui_2Img.getHeight())) {
         MainCanvas.keySimPressed(8192);
         return true;
      } else {
         return false;
      }
   }
}

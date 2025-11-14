import javax.microedition.lcdui.Graphics;

public class UIPanel extends UIComponent {
   public static final byte PANEL_TYPE_SIMPLE = 0;
   public static final byte PANEL_TYPE_MULTI = 1;
   public static final byte PANEL_TYPE_FILL = 2;
   static final short OUT_X = 4;
   static final short OUT_Y = 5;
   static final short IN_X1 = 10;
   static final short IN_Y1 = 5;
   static final short IN_X2 = 3;
   static final short IN_Y2 = 1;
   static final short IN_X3 = 1;
   static final short IN_Y3 = 1;
   static final short IN_X4 = 1;
   static final short IN_Y4 = 1;
   private short innerHeight = -1;
   private short innerOY = -1;
   private UIScroll panelVScrollBar = null;
   private UIScroll panelHScrollBar = null;
   private int fillColor = 15317892;
   private int kuangColor = 600869;
   public int[] INNER_XYWH = new int[4];
   private int panelRowNum = 0;
   public static boolean m_bDataH;
   public static boolean m_bDataX;

   public int getPanelRowNum() {
      return this.panelRowNum;
   }

   public void setPanelRowNum(int panelRowNum) {
      this.panelRowNum = panelRowNum;
   }

   public UIPanel(short x, short y, short w, short h, byte subType, UIForm form) {
      super(x, y, w, h, form);
      super.type = subType;
      this.init();
   }

   private void init() {
      this.INNER_XYWH[0] = super.positionX + 10 + 3 + 1 + 1;
      if (this.innerOY == -1) {
         this.INNER_XYWH[1] = super.positionY + 5 + 1 + 1 + 1;
      } else {
         this.INNER_XYWH[1] = super.positionY + 5 + 1 + 1 + 1 + this.innerOY;
      }

      this.INNER_XYWH[2] = super.positionX + super.width - 30 - MainCanvas.ui_3Img.getWidth();
      if (this.innerHeight == -1) {
         this.INNER_XYWH[3] = super.height - 16;
      } else {
         this.INNER_XYWH[3] = this.innerHeight - 6;
      }

      this.panelRowNum = this.INNER_XYWH[3] / MainCanvas.CHARH;
   }

   public void draw(Graphics g) {
      if (super.type == 2) {
         g.setColor(this.kuangColor);
         g.drawRect(super.positionX, super.positionY, super.width - 1, super.height - 1);
         g.setColor(this.fillColor);
         g.fillRect(super.positionX + 1, super.positionY + 1, super.width - 2, super.height - 2);
      } else {
         short panelWidth = super.width;
         if (panelWidth <= 0) {
            throw new IllegalArgumentException("控件宽度初始值不够长");
         } else {
            g.setColor(7755053);
            g.fillRect(super.positionX, super.positionY, panelWidth, super.height);
            g.setColor(11241821);
            g.fillRect(super.positionX + 1, super.positionY + 1, panelWidth - 2, super.height - 2);
            g.setColor(15255701);
            g.fillRect(super.positionX + 2, super.positionY + 2, panelWidth - 4, super.height - 4);
            g.setColor(11241821);
            g.fillRect(super.positionX + 3, super.positionY + 3, panelWidth - 6, super.height - 6);
            g.setColor(12952181);
            g.fillRect(super.positionX + 4, super.positionY + 4, panelWidth - 8, super.height - 8);
            g.setColor(15255701);
            g.fillRect(super.positionX + 5, super.positionY + 5, panelWidth - 10, super.height - 10);
            g.setColor(Cons.COLOR_PANEL_BG);
            g.fillRect(super.positionX + 6, super.positionY + 6, panelWidth - 12, super.height - 12);
            g.drawImage(MainCanvas.m_cImg, super.positionX, super.positionY, 20);
            Util.drawImage(g, MainCanvas.m_cImg, super.positionX, super.positionY + super.height - MainCanvas.m_cImg.getHeight(), 1);
            Util.drawImage(g, MainCanvas.m_cImg, super.positionX + super.width - MainCanvas.m_cImg.getWidth(), super.positionY, 2);
            Util.drawImage(g, MainCanvas.m_cImg, super.positionX + super.width - MainCanvas.m_cImg.getWidth(), super.positionY + super.height - MainCanvas.m_cImg.getHeight(), 3);
            if (super.type == 1) {
               int innerX1;
               if (m_bDataX) {
                  innerX1 = super.positionX + 10 - 4;
               } else {
                  innerX1 = super.positionX + 10;
               }

               int innerY1 = false;
               int innerY1;
               if (this.innerOY == -1) {
                  innerY1 = super.positionY + 5 + 2;
               } else {
                  innerY1 = super.positionY + 5 + 2 + this.innerOY;
               }

               int innerW1;
               if (m_bDataX) {
                  innerW1 = super.width - 20 + 8;
               } else {
                  innerW1 = super.width - 20;
               }

               int innerH1 = false;
               int innerH1;
               if (this.innerHeight == -1) {
                  innerH1 = super.height - 14;
               } else {
                  innerH1 = this.innerHeight - 5;
               }

               g.setColor(Cons.COLOR_PANEL_BORDER_3);
               g.fillRect(innerX1, innerY1, innerW1, innerH1);
               int innerX2 = innerX1 + 3;
               int innerY2 = innerY1 + 1;
               int innerW2 = innerW1 - 6;
               int innerH2 = innerH1 - 2;
               g.setColor(Cons.COLOR_PANEL_BG);
               g.fillRect(innerX2, innerY2, innerW2, innerH2);
               int innerX3 = innerX2 + 1;
               int innerY3 = innerY2 + 1;
               int innerW3 = innerW2 - 2;
               int innerH3 = innerH2 - 2;
               g.setColor(Cons.COLOR_PANEL_BORDER_3);
               g.fillRect(innerX3, innerY3, innerW3, innerH3);
               int innerX4 = innerX3 + 1;
               int innerY4 = innerY3 + 1;
               int innerW4 = innerW3 - 2;
               int innerH4 = innerH3 - 2;
               g.setColor(Cons.COLOR_PANEL_BG);
               g.fillRect(innerX4, innerY4, innerW4, innerH4);
               if (this.panelVScrollBar != null) {
                  this.panelVScrollBar.draw(g);
               }
            }

         }
      }
   }

   public void keyInAction() {
   }

   public void addScrollBar() {
      if (super.type == 1 && this.panelVScrollBar == null) {
         int x = super.positionX + super.width - 10 - 3 - 1 - 1 - MainCanvas.ui_3Img.getWidth();
         int y = false;
         int y;
         if (this.innerOY == -1) {
            y = super.positionY + 5 + 1 + 1 + 1;
         } else {
            y = super.positionY + 5 + 1 + 1 + 1 + this.innerOY;
         }

         int h = false;
         int h;
         if (this.innerHeight == -1) {
            h = super.height - 16;
         } else {
            h = this.innerHeight - 6;
         }

         this.panelVScrollBar = new UIScroll((short)x, (short)y, (short)0, (short)h, (byte)0, false, (UIForm)null);
      }

      if (super.type == 1 && this.panelHScrollBar != null) {
         this.panelVScrollBar.setHaveHorizontal(true);
         this.panelHScrollBar.setHaveVertical(true);
      }

   }

   public void addPanelHScrollBar() {
      if (super.type == 1 && this.panelHScrollBar == null) {
         int x = false;
         int y = false;
         int y;
         if (this.innerOY == -1) {
            y = super.positionY + this.getInnerOY() - 5 + this.getInnerHeight();
         } else {
            y = super.positionY + this.getInnerOY() - 5 + this.getInnerHeight() - 1;
         }

         int x = super.positionX + 10 + 3 + 1 + 1;
         int w = false;
         int w = super.width - 30 - MainCanvas.ui_3Img.getWidth();
         this.panelHScrollBar = new UIScroll((short)x, (short)y, (short)w, (short)0, (byte)1, true, (UIForm)null);
      }

      if (super.type == 1 && this.panelVScrollBar != null) {
         this.panelVScrollBar.setHaveHorizontal(true);
         this.panelHScrollBar.setHaveVertical(true);
      }

   }

   public void addPanelVScrollBar(boolean nonUseRelative) {
      if (super.type == 1 && this.panelVScrollBar == null) {
         int x;
         if (m_bDataX) {
            x = super.positionX + super.width - 10 - 3 - 1 - 1 - MainCanvas.ui_3Img.getWidth() + 3;
         } else {
            x = super.positionX + super.width - 10 - 3 - 1 - 1 - MainCanvas.ui_3Img.getWidth() - 3;
         }

         int y = false;
         int y;
         if (this.innerOY == -1) {
            y = super.positionY + 5 + 1 + 1 + 1 + 3;
         } else {
            y = super.positionY + 5 + 1 + 1 + 1 + 3 + this.innerOY;
         }

         int h = false;
         int h;
         if (this.innerHeight == -1) {
            h = super.height - 16 - 6;
         } else if (m_bDataH) {
            h = this.innerHeight - 6 - 7;
         } else {
            h = this.innerHeight - 6 - 1;
         }

         this.panelVScrollBar = new UIScroll((short)x, (short)y, (short)0, (short)h, (byte)0, nonUseRelative, (UIForm)null);
      }

      if (super.type == 1 && this.panelHScrollBar != null) {
         this.panelVScrollBar.setHaveHorizontal(true);
         this.panelHScrollBar.setHaveVertical(true);
      }

   }

   public void removePanelVScrollBar() {
      if (this.panelVScrollBar != null) {
         this.panelVScrollBar = null;
      }

      if (this.panelHScrollBar != null) {
         this.panelHScrollBar.setHaveVertical(false);
      }

   }

   public void removepanelHScrollBar() {
      if (this.panelHScrollBar != null) {
         this.panelHScrollBar = null;
      }

      if (this.panelVScrollBar != null) {
         this.panelVScrollBar.setHaveHorizontal(false);
      }

   }

   public short getInnerHeight() {
      int innerH = false;
      int innerH;
      if (this.innerHeight == -1) {
         innerH = super.height - 10;
      } else {
         innerH = this.innerHeight;
      }

      return (short)innerH;
   }

   public void setInnerHeight(short innerHeight) {
      this.innerHeight = innerHeight;
      int h = false;
      int h;
      if (innerHeight == -1) {
         h = super.height - 16;
      } else {
         h = innerHeight - 6;
      }

      this.INNER_XYWH[3] = h;
      this.panelRowNum = this.INNER_XYWH[3] / MainCanvas.CHARH;
      if (this.panelVScrollBar != null) {
         this.panelVScrollBar.setHeight((short)h);
      }

   }

   public void setScrollBar(UIScroll scrollBar) {
      this.panelVScrollBar = scrollBar;
   }

   public short getInnerOY() {
      return this.innerOY;
   }

   public void setInnerOY(short innerOY) {
      this.innerOY = innerOY;
   }

   public int getFillColor() {
      return this.fillColor;
   }

   public void setFillColor(int fillColor) {
      this.fillColor = fillColor;
   }

   public int getKuangColor() {
      return this.kuangColor;
   }

   public void setKuangColor(int kuangColor) {
      this.kuangColor = kuangColor;
   }

   public void setInnerY(short innerY) {
      if (innerY == this.getPositionY() + 5) {
         this.innerOY = -1;
      } else {
         this.innerOY = (short)((innerY - 5) * MainCanvas.screenH / 208 - this.getPositionY());
         this.INNER_XYWH[1] = this.innerOY + this.getPositionY() + 8;
      }

      this.setPositionY(this.getPositionY());
   }

   public short getInnerY() {
      int innerY = false;
      int innerY;
      if (this.innerOY == -1) {
         innerY = this.getPositionY() + 5;
      } else {
         innerY = this.getPositionY() + 5 + this.innerOY;
      }

      return (short)innerY;
   }

   public void setIsPanelVScrollBar(boolean b) {
      if (b) {
         if (this.panelVScrollBar == null) {
            this.addScrollBar();
         }
      } else if (this.panelVScrollBar != null) {
         this.removePanelVScrollBar();
      }

   }

   public void setIspanelHScrollBar(boolean b) {
      if (b) {
         if (this.panelHScrollBar == null) {
            this.addPanelHScrollBar();
         }
      } else if (this.panelHScrollBar != null) {
         this.removepanelHScrollBar();
      }

   }

   public UIScroll getPanelVScroll() {
      return this.panelVScrollBar;
   }

   public boolean havePanelVScrollBar() {
      return this.getType() == 1 && this.panelVScrollBar != null;
   }

   public void addUIScroll(short allRowNum, short canShowNum) {
      if (this.getPanelVScroll() != null) {
         this.getPanelVScroll().canShowRowNum = canShowNum;
         this.getPanelVScroll().allRowNum = allRowNum;
         this.getPanelVScroll().setShowStartRow((short)0);
      }

   }
}

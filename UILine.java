import javax.microedition.lcdui.Graphics;

public class UILine extends UIComponent {
   private boolean isHorizontal = true;
   private int lineColor;
   private boolean isCenter;
   private static final short LENGTH = 50;
   private short length;

   public UILine(short x, short y, UIForm form) {
      super(x, y, (short)0, (short)0, form);
      this.lineColor = Cons.COLOR_LINE;
      this.isCenter = false;
      this.length = 0;
      this.length = 50;
      if (this.isHorizontal()) {
         this.setWidth((short)(this.length * MainCanvas.screenW / 176));
      } else {
         this.setHeight((short)(this.length * MainCanvas.screenH / 208));
      }

   }

   public void draw(Graphics g) {
      if (this.isHorizontal()) {
         this.setWidth(this.length);
         this.setHeight((short)0);
      } else {
         this.setWidth((short)0);
         this.setHeight(this.length);
      }

      g.setColor(Cons.COLOR_LINE);
      if (this.isHorizontal()) {
         g.drawLine(this.getPositionX(), this.getPositionY(), this.getPositionX() + this.getWidth() - 1, this.getPositionY());
      } else {
         g.drawLine(this.getPositionX(), this.getPositionY(), this.getPositionX(), this.getPositionY() + this.getHeight() - 1);
      }

   }

   public boolean isHorizontal() {
      return this.isHorizontal;
   }

   public void setHorizontal(boolean isHorizontal) {
      this.isHorizontal = isHorizontal;
   }

   public short getLength() {
      return this.length;
   }

   public void setLength(short length) {
      this.length = length;
   }

   public int getLineColor() {
      return this.lineColor;
   }

   public void setLineColor(int lineColor) {
      this.lineColor = lineColor;
   }
}

import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class TableItem {
   protected short x;
   protected short y;
   protected short w;
   protected short h;
   private Vector terms = new Vector();
   protected short[] termWidth;
   protected boolean[] isCenter;
   private short termHeight = getDefaultTableItemHeight();
   public short selectedNPCX = -1;
   public short selectedNPCY = -1;
   public int playerID = -1;
   public byte playerCamp = -1;
   public boolean isOL = false;
   public long mailID = 0L;
   private byte payType = -1;
   public long m_lBattlefield = -1L;

   public Vector getTerms() {
      return this.terms;
   }

   public void setTerms(Vector terms) {
      this.terms = terms;
   }

   public void addItem(UIComponent uic) {
      this.terms.addElement(uic);
   }

   public void resetTermWidth(int size) {
      this.termWidth = new short[size];
   }

   public void setItem(UIComponent uic, byte rowIndex) {
      if (uic != null) {
         this.terms.setElementAt(uic, rowIndex);
      }

   }

   public void draw(Graphics g, boolean inverse) {
      for(int i = 0; i < this.terms.size(); ++i) {
         UIComponent uic = (UIComponent)this.terms.elementAt(i);
         uic.setInverse(inverse);
         uic.draw(g);
      }

   }

   public static short getDefaultTableItemHeight() {
      return (short)(MainCanvas.CHARH + 2);
   }

   public void resetUicPositionX(short tableStartX, boolean isTitle) {
      for(int i = 0; i < this.terms.size(); ++i) {
         UIComponent uic = (UIComponent)this.terms.elementAt(i);
         if (uic != null) {
            short offsetW = 0;
            if (isTitle || this.isCenter[i]) {
               offsetW = (short)((this.termWidth[i] - uic.getWidth()) / 2);
            }

            uic.setPositionX((short)(tableStartX + this.getCurWidth((byte)i) + offsetW + 1));
         }
      }

   }

   public short[] getTermWidth() {
      return this.termWidth;
   }

   public void setTermWidth(short[] termWidth) {
      this.termWidth = termWidth;
   }

   public void setTermWidth(short termWidth, short termIndex) {
      this.termWidth[termIndex] = termWidth;
   }

   public short getCurWidth(byte index) {
      short w = 0;

      for(int i = 0; i < index; ++i) {
         w += this.getTermWidth()[i];
      }

      return w;
   }

   public boolean[] getIsCenter() {
      return this.isCenter;
   }

   public void setIsCenter(boolean[] isCenter) {
      this.isCenter = isCenter;
   }

   public void setIsCenter(boolean isCenter, short termIndex) {
      this.isCenter[termIndex] = isCenter;
   }

   public short getTermHeight() {
      return this.termHeight;
   }

   public void setTermHeightFirst(short termHeight) {
      this.termHeight = (short)(termHeight * MainCanvas.screenH / 208);
   }

   public void setTermHeight(short termHeight) {
      this.termHeight = termHeight;
   }

   public short getH() {
      return this.h;
   }

   public void setH(short h) {
      this.h = h;
   }

   public short getW() {
      return this.w;
   }

   public void setW(short w) {
      this.w = w;
   }

   public short getX() {
      return this.x;
   }

   public void setX(short x) {
      this.x = x;
   }

   public short getY() {
      return this.y;
   }

   public void setY(short y) {
      this.y = y;
   }

   public byte getPayType() {
      return this.payType;
   }

   public void setPayType(byte payType) {
      this.payType = payType;
   }
}

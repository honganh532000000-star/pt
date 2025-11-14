import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class UIFolder extends UIComponent {
   public static final byte TYPE_DEFAULT = 0;
   public static final byte TYPE_SKILLINTERFACE = 1;
   private byte foldertype;
   public static final short FOLDER_OY = 4;
   public static final short FOLDER_OX = 1;
   private Vector items;
   private byte index;
   private static final String DEFAULT_STR = "……";
   private byte showItemLen;
   public static byte MAX_SHOW_ITEM_NUM = 5;

   public UIFolder(short x, short y, short w, short h, byte showItemLen, UIForm form) {
      super(x, y, w, h, form);
      this.foldertype = 0;
      this.showItemLen = 0;
      this.showItemLen = showItemLen;
      this.items = new Vector();
      if (x <= 0) {
         super.positionX = 0;
      }

      super.width = w;
      if (h <= 0) {
         super.height = getAutoFolderHeight();
      }

      this.setFoldertype((byte)0);
   }

   public UIFolder(short y, byte showItemLen, UIForm form) {
      this((short)0, y, (short)0, (short)0, showItemLen, form);
   }

   public UIFolder(short x, short y, byte showItemLen, String[] strs, UIForm form) {
      this(x, y, showItemLen, form);
      this.addItems(strs);
   }

   public UIFolder(short x, short y, byte showItemLen, UIForm form) {
      this(x, y, (short)0, (short)0, showItemLen, form);
   }

   public byte getFoldertype() {
      return this.foldertype;
   }

   public void setFoldertype(byte foldertype) {
      this.foldertype = foldertype;
   }

   public void draw(Graphics g) {
      if (MainCanvas.curFormVector.size() > 0) {
         this.index = ((UIForm)MainCanvas.curFormVector.elementAt(0)).foldedIndex;
      }

      int startI = 0;
      if (this.items.size() > this.showItemLen) {
         startI = this.index < this.showItemLen - 1 ? 0 : this.index - this.showItemLen + 1;
      }

      int wenKuangOffsetX = 0;
      int backKuangWidth = this.showItemLen * (Util.getStrLen((String)this.items.elementAt(startI)) + 2 + 2) - 2 + 6;
      int startX = MainCanvas.screenW - backKuangWidth >> 1;
      g.setColor(Cons.COLOR_FOLDER_BORDER_1);
      g.drawRect(startX, super.positionY, backKuangWidth - 4, super.height - 1);
      g.setColor(Cons.COLOR_FOLDER_BORDER_2);
      g.drawRect(startX + 1, super.positionY + 1, backKuangWidth - 3, super.height - 3);
      int wenKuangX = startX + 2 + 1;
      int wenKuangY = super.positionY + 4 - 2 + 1;
      int wenKuangH = super.height - 6;

      for(int i = startI; i < startI + this.showItemLen; ++i) {
         int wenKuangW = ((String)this.items.elementAt(i)).length() * MainCanvas.CHARW + 2;
         if (i - startI > 0) {
            wenKuangOffsetX += Util.getStrLen((String)this.items.elementAt(i - 1)) + 2;
         }

         if (i - startI == (this.index > this.showItemLen - 1 ? this.showItemLen - 1 : this.index)) {
            g.setColor(Cons.COLOR_FOLDER_SEL_BG);
            g.fillRect(wenKuangX + wenKuangOffsetX + (i - startI) * 2, wenKuangY, wenKuangW, wenKuangH);
            g.setColor(Cons.COLOR_FOLDER_SEL_BORDER);
            g.drawRect(wenKuangX + wenKuangOffsetX + (i - startI) * 2 - 1, wenKuangY - 1, wenKuangW + 1, wenKuangH + 1);
         } else {
            g.setColor(Cons.COLOR_FOLDER_UNSEL_BG);
            g.fillRect(wenKuangX + wenKuangOffsetX + (i - startI) * 2, wenKuangY, wenKuangW, wenKuangH);
            g.setColor(Cons.COLOR_FOLDER_BG);
            g.drawRect(wenKuangX + wenKuangOffsetX + (i - startI) * 2 - 1, wenKuangY - 1, wenKuangW + 1, wenKuangH + 1);
         }

         g.setColor(Cons.COLOR_FOLDER_FONT);
         if ((this.items.size() <= this.showItemLen || i != startI + this.showItemLen - 1 || this.index >= this.showItemLen - 1) && (this.items.size() <= this.showItemLen || i != startI || this.index < this.showItemLen - 1)) {
            g.drawString((String)this.items.elementAt(i > startI + this.showItemLen - 1 ? startI + this.showItemLen - 1 : i), wenKuangX + 1 + wenKuangOffsetX + (i - startI) * 2, wenKuangY, 20);
         } else {
            g.drawString("……", wenKuangX + 1 + wenKuangOffsetX + (i - startI) * 2, wenKuangY, 20);
         }
      }

      short earY = (short)(super.positionY + (super.height - MainCanvas.ui_5Img.getHeight()) / 2);
      startX -= MainCanvas.ui_5Img.getWidth();
      int offX = 0;
      if (MainCanvas.countTick % 4 > 2) {
         offX = 2;
      }

      g.drawImage(MainCanvas.ui_5Img, startX - offX, earY, 20);
      Util.drawImage(g, MainCanvas.ui_5Img, MainCanvas.screenW - startX - MainCanvas.ui_5Img.getWidth() + offX, earY, 2);
      short offsetX = 3;
      short offsetY = 2;
      g.drawImage(MainCanvas.ui_xImg, startX + offsetX - offX, earY + offsetY, 20);
      g.drawImage(MainCanvas.ui_jImg, MainCanvas.screenW - startX - offsetX - MainCanvas.ui_jImg.getWidth() + offX, earY + offsetY, 20);
   }

   public static short getAutoFolderHeight() {
      return (short)(MainCanvas.CHARH + 8);
   }

   public byte getIndex() {
      if (MainCanvas.curFormVector.size() > 0) {
         this.index = ((UIForm)MainCanvas.curFormVector.elementAt(0)).foldedIndex;
      }

      return this.index;
   }

   public void setIndex(byte index) {
      if (index < this.items.size() && index >= 0) {
         if (MainCanvas.curFormVector.size() > 0) {
            ((UIForm)MainCanvas.curFormVector.elementAt(0)).foldedIndex = index;
         }

         this.index = index;
      } else {
         throw new IndexOutOfBoundsException("Nhóm số đánh dấu quá giới hạn");
      }
   }

   public Vector getItems() {
      return this.items;
   }

   public void setItems(Vector items) {
      this.items = items;
   }

   public byte getShowItemLen() {
      return this.showItemLen;
   }

   public void setShowItemLen(byte showItemLen) {
      this.showItemLen = showItemLen;
   }

   public void addItems(String[] newItems) {
      if (newItems != null) {
         for(int i = 0; i < newItems.length; ++i) {
            if (newItems[i] != null && !newItems[i].equals("")) {
               this.items.addElement(newItems[i]);
            }
         }
      }

   }

   public void keyInAction() {
   }

   private void touchAction() {
      if (MainCanvas.SUPPORT_POINTER && MainCanvas.isInputDown(268435456)) {
         int startI = 0;
         int backKuangWidth = this.showItemLen * (Util.getStrLen((String)this.items.elementAt(startI)) + 2 + 2) - 2 + 6;
         int startX = MainCanvas.screenW - backKuangWidth >> 1;
         short earY = (short)(super.positionY + (super.height - MainCanvas.ui_5Img.getHeight()) / 2);
         startX -= MainCanvas.ui_5Img.getWidth();
         if (MainCanvas.isPointInRect(startX, earY, MainCanvas.ui_5Img.getWidth(), MainCanvas.ui_5Img.getHeight())) {
            MainCanvas.keySimPressed(1024);
         } else if (MainCanvas.isPointInRect(MainCanvas.screenW - startX - MainCanvas.ui_5Img.getWidth(), earY, MainCanvas.ui_5Img.getWidth(), MainCanvas.ui_5Img.getHeight())) {
            MainCanvas.keySimPressed(2048);
         }
      }

   }

   public void keyInFolder() {
      this.touchAction();
      boolean isChange = true;
      if (MainCanvas.isInputDown(1024)) {
         this.index = ((UIForm)MainCanvas.curFormVector.elementAt(0)).foldedIndex;
         --this.index;
         if (this.index < 0) {
            this.index = 0;
            isChange = false;
         }

         ((UIForm)MainCanvas.curFormVector.elementAt(0)).foldedIndex = this.index;
         if (isChange) {
            MainCanvas.curForm.cancelFormMenu();
         }

         MainCanvas.curForm = (UIForm)MainCanvas.curFormVector.elementAt(this.index);
         MainCanvas.curForm.addWarningStr();
      } else if (MainCanvas.isInputDown(2048)) {
         this.index = ((UIForm)MainCanvas.curFormVector.elementAt(0)).foldedIndex;
         ++this.index;
         if (this.index > MainCanvas.curFormVector.size() - 1) {
            this.index = (byte)(MainCanvas.curFormVector.size() - 1);
            isChange = false;
         }

         ((UIForm)MainCanvas.curFormVector.elementAt(0)).foldedIndex = this.index;
         if (isChange) {
            MainCanvas.curForm.cancelFormMenu();
         }

         MainCanvas.curForm = (UIForm)MainCanvas.curFormVector.elementAt(this.index);
         MainCanvas.curForm.addWarningStr();
      }

   }

   public void resetWidth() {
      super.width = (short)((MainCanvas.ui_5Img.getWidth() + 1 + 2) * 2);
      int itemWidth = 0;

      for(int i = 0; i < this.showItemLen; ++i) {
         itemWidth += Util.getStrLen((String)this.items.elementAt(i)) + 4;
      }

      super.width = (short)(super.width + itemWidth);
      if (super.width < MainCanvas.screenW) {
         super.positionX = (short)((MainCanvas.screenW - super.width) / 2);
      }

   }
}

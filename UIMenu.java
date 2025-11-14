import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class UIMenu extends UIComponent {
   private Vector items;
   private Vector cmdIds;
   private Vector inns;
   private byte index;
   public byte menuType;
   private boolean isCenter;
   public static final byte MENU_UI = 0;
   public static final byte MENU_MAP = 1;
   public static final byte MENU_MAP_MOVEMENT = 2;
   public static final byte MENU_MAP_NPC = 3;
   public static final short INNER_OUTER_OX = 2;
   public static final short INNER_OUTER_OY = 2;
   public static final short LAYER_SIZE = 3;
   public static final short ITEM_OY = 2;
   public static final short INNER_ITEM_OX = 8;
   public static final short INNER_ITEM_OY = 5;
   public static final short FILL_OX = 4;
   public static final short FILL_OY = 1;
   public static UIMenu otherPlayerMenu = null;
   public static UIMenu npcMenu = null;

   public UIMenu(short x, short y, short w, short h, byte menuType) {
      super(x, y, w, h, (UIForm)null);
      this.index = 0;
      this.menuType = -1;
      this.isCenter = true;
      this.menuType = menuType;
      this.items = new Vector();
      this.cmdIds = new Vector();
      this.inns = new Vector();
      this.reset();
   }

   public UIMenu(byte menuType) {
      this((short)0, (short)0, (short)0, (short)0, menuType);
   }

   public void reset() {
      super.positionX = this.getAutoMenuX();
      super.positionY = this.getAutoMenuY();
      super.width = this.getAutoMenuWidth();
      super.height = this.getAutoMenuHeight();
   }

   public void draw(Graphics g) {
      g.setColor(7755053);
      g.fillRect(super.positionX, super.positionY, super.width, super.height);
      g.setColor(11241821);
      g.fillRect(super.positionX + 1, super.positionY + 1, super.width - 2, super.height - 2);
      g.setColor(15255701);
      g.fillRect(super.positionX + 2, super.positionY + 2, super.width - 4, super.height - 4);
      g.setColor(11241821);
      g.fillRect(super.positionX + 3, super.positionY + 3, super.width - 6, super.height - 6);
      g.setColor(12952181);
      g.fillRect(super.positionX + 4, super.positionY + 4, super.width - 8, super.height - 8);
      g.setColor(15255701);
      g.fillRect(super.positionX + 5, super.positionY + 5, super.width - 10, super.height - 10);
      g.setColor(Cons.COLOR_PANEL_BG);
      g.fillRect(super.positionX + 6, super.positionY + 6, super.width - 12, super.height - 12);
      g.drawImage(MainCanvas.ui_mjImg, super.positionX, super.positionY, 20);
      Util.drawImage(g, MainCanvas.ui_mjImg, super.positionX, super.positionY + super.height - MainCanvas.ui_mjImg.getHeight(), 1);
      Util.drawImage(g, MainCanvas.ui_mjImg, super.positionX + super.width - MainCanvas.ui_mjImg.getWidth(), super.positionY, 2);
      Util.drawImage(g, MainCanvas.ui_mjImg, super.positionX + super.width - MainCanvas.ui_mjImg.getWidth(), super.positionY + super.height - MainCanvas.ui_mjImg.getHeight(), 3);
      int curStrColor = false;

      for(int i = 0; i < this.items.size(); ++i) {
         int curStrColor;
         if (i == this.index) {
            curStrColor = Cons.COLOR_PANEL_BG;
            int fillX = super.positionX + (super.width - Util.getStrLen((String)this.items.elementAt(this.getMaxItemsLenIndex()))) / 2 - 4;
            int fillY = super.positionY + 3 + 2 + 5 + i * (MainCanvas.CHARH + 2) - 1;
            int fillW = Util.getStrLen((String)this.items.elementAt(this.getMaxItemsLenIndex())) + 8;
            int fillH = MainCanvas.CHARH + 2;
            g.setColor(Cons.COLOR_MENU_SEL_ITEM_BG);
            g.fillRect(fillX, fillY, fillW, fillH);
         } else {
            curStrColor = Cons.COLOR_FONT_1;
         }

         int offsetX = 0;
         if (this.menuType == 3) {
            byte picIndex = -1;
            switch(PCTask.colorIndexs[i]) {
            case 1:
               picIndex = 5;
               break;
            case 2:
               picIndex = 6;
               break;
            case 3:
               picIndex = 3;
               break;
            case 4:
               picIndex = 4;
               break;
            case 5:
               picIndex = 0;
               break;
            case 6:
               picIndex = 1;
               break;
            case 7:
               picIndex = 2;
               break;
            case 8:
               picIndex = 6;
            }

            MainCanvas.taskIconImg.draw(g, super.positionX + 10, super.positionY + 3 + 2 + 5 + i * (MainCanvas.CHARH + 2), picIndex);
            offsetX = 10;
         }

         if (this.isCenter) {
            Util.drawString(g, (String)this.items.elementAt(i), super.width, super.positionX, super.positionY + 3 + 2 + 5 + i * (MainCanvas.CHARH + 2), curStrColor);
         } else {
            g.setColor(curStrColor);
            g.drawString((String)this.items.elementAt(i), super.positionX + 10 + offsetX, super.positionY + 3 + 2 + 5 + i * (MainCanvas.CHARH + 2), 20);
         }
      }

   }

   public Vector getItems() {
      return this.items;
   }

   public void setItems(Vector items) {
      this.items = items;
   }

   public byte getIndex() {
      return this.index;
   }

   public void setIndex(byte index) {
      this.index = index;
   }

   public void increaseIndex() {
      ++this.index;
      if (this.index > this.items.size() - 1) {
         this.index = 0;
      }

   }

   public void decreaseIndex() {
      --this.index;
      if (this.index < 0) {
         this.index = (byte)(this.items.size() - 1);
      }

   }

   public boolean pointIndex() {
      if (MainCanvas.SUPPORT_POINTER && MainCanvas.isInputDown(268435456)) {
         int size = this.items.size();

         for(byte i = 0; i < size; ++i) {
            int x = super.positionX + (super.width - Util.getStrLen((String)this.items.elementAt(this.getMaxItemsLenIndex()))) / 2 - 4;
            int y = super.positionY + 3 + 2 + 5 + i * (MainCanvas.CHARH + 2) - 1;
            int w = Util.getStrLen((String)this.items.elementAt(this.getMaxItemsLenIndex())) + 8;
            int h = MainCanvas.CHARH + 2;
            if (MainCanvas.isPointInRect(x, y, w, h)) {
               this.index = i;
               return true;
            }
         }

         if (this == otherPlayerMenu || this == npcMenu) {
            int menuX = this.getAutoMenuX();
            int menuY = this.getAutoMenuY();
            int menuW = this.getAutoMenuWidth();
            int menuH = this.getAutoMenuHeight();
            if (!MainCanvas.isPointInRect(menuX, menuY, menuW, menuH) && MainCanvas.isPointInRect(0, 0, MainCanvas.screenW, MainCanvas.screenH)) {
               MainCanvas.keySimPressed(262144);
               return false;
            }
         }
      }

      return false;
   }

   public short getAutoMenuX() {
      short autoMenuX = 0;
      if (this.menuType == 0) {
         autoMenuX = 0;
      } else if (this.menuType == 1 || this.menuType == 2 || this.menuType == 3) {
         autoMenuX = (short)((MainCanvas.screenW - this.getAutoMenuWidth()) / 2);
      }

      return autoMenuX;
   }

   public short getAutoMenuY() {
      short autoMenuY = 0;
      if (this.menuType == 0) {
         autoMenuY = (short)(MainCanvas.screenH - this.getAutoMenuHeight() - UITitle.getMenuBarHeight());
      } else if (this.menuType == 1 || this.menuType == 2 || this.menuType == 3) {
         autoMenuY = (short)((MainCanvas.screenH - this.getAutoMenuHeight()) / 2);
      }

      return autoMenuY;
   }

   public short getAutoMenuHeight() {
      int fontH = MainCanvas.CHARH;
      return (short)(this.items.size() * fontH + (this.items.size() - 1) * 2 + 20);
   }

   public short getAutoMenuWidth() {
      return this.items.size() == 0 ? 0 : (short)(Util.getStrLen((String)this.items.elementAt(this.getMaxItemsLenIndex())) + 26);
   }

   private int getMaxItemsLenIndex() {
      if (this.items.size() == 0) {
         return 0;
      } else {
         int maxIndex = 0;
         int maxLen = ((String)this.items.elementAt(0)).length();

         for(int i = 1; i < this.items.size(); ++i) {
            if (((String)this.items.elementAt(i)).length() > maxLen) {
               maxLen = ((String)this.items.elementAt(i)).length();
               maxIndex = i;
            }
         }

         return maxIndex;
      }
   }

   public static int getMaxItemsLen(String[] items) {
      int maxLen = 0;

      for(int i = 0; i < items.length; ++i) {
         if (items[i].length() > maxLen) {
            maxLen = items[i].length();
         }
      }

      return maxLen;
   }

   public Vector getCmdIds() {
      return this.cmdIds;
   }

   public void setCmdIds(Vector cmdIds) {
      this.cmdIds = cmdIds;
   }

   public void addMenu(String name, int id, int inn) {
      if (name != null && !name.equals("")) {
         this.items.addElement(name);
         this.cmdIds.addElement(new Integer(id));
         this.inns.addElement(new Integer(inn));
         this.reset();
      }

   }

   public void addMenu(String[] names, int[] ids, int[] ns) {
      if (names != null && ids != null && names.length == ids.length) {
         for(int i = 0; i < names.length; ++i) {
            this.addMenu(names[i], ids[i], ns[i]);
         }

      }
   }

   public void addMenu(String[] names, int[] ids) {
      if (names != null && ids != null && names.length == ids.length) {
         for(int i = 0; i < names.length; ++i) {
            this.addMenu(names[i], ids[i], 3);
         }

      }
   }

   public void saveForm() {
      switch((Integer)this.inns.elementAt(this.index)) {
      case 0:
         SIChat.removeChatLocal();
         MainCanvas.backForms.removeAllElements();
         MainCanvas.backForms.addElement(new Integer(0));
         MainCanvas.curFormVector.removeAllElements();
         break;
      case 1:
         switch(MainCanvas.curForm.clientCommand) {
         case 131079:
            if (!UITitle.isEnter) {
               SIChat.removeChatLocal();
               formSaveCommand();
            }

            return;
         default:
            SIChat.removeChatLocal();
            formSaveCommand();
            return;
         }
      case 2:
         SIChat.removeChatLocal();
         formSaveForm();
      case 3:
      default:
         break;
      case 4:
         SIChat.removeChatLocal();
         break;
      case 5:
         SIChat.removeChatLocal();
         if (MainCanvas.backForms.size() == 0) {
            MainCanvas.backForms.addElement(new Integer(MainCanvas.curForm.clientCommand));
         } else {
            MainCanvas.backForms.removeElementAt(0);
         }
         break;
      case 6:
         formDeleteForm();
      }

   }

   public static void formSaveForm() {
      if (MainCanvas.curFormVector.size() > 0) {
         Vector formVector = new Vector();

         for(int i = 0; i < MainCanvas.curFormVector.size(); ++i) {
            formVector.addElement(MainCanvas.curFormVector.elementAt(i));
         }

         MainCanvas.backForms.addElement(formVector);
         MainCanvas.curFormVector.removeAllElements();
      } else if (MainCanvas.curForm != null) {
         MainCanvas.backForms.addElement(MainCanvas.curForm);
      }

   }

   public static void formSaveCommand() {
      if (MainCanvas.curFormVector.size() > 0) {
         Vector commandVector = new Vector();

         for(int i = 0; i < MainCanvas.curFormVector.size(); ++i) {
            commandVector.addElement(new Integer(((UIForm)MainCanvas.curFormVector.elementAt(i)).clientCommand));
         }

         MainCanvas.backForms.addElement(commandVector);
         MainCanvas.curFormVector.removeAllElements();
      } else if (MainCanvas.curForm != null) {
         MainCanvas.backForms.addElement(new Integer(MainCanvas.curForm.clientCommand));
      }

   }

   public static void formDeleteForm() {
      if (MainCanvas.backForms.size() > 0) {
         MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
      }

   }

   public void removeMenu(int menuIndex) {
      if (menuIndex >= 0 && menuIndex <= this.items.size() - 1) {
         this.items.removeElementAt(menuIndex);
         this.cmdIds.removeElementAt(menuIndex);
         this.reset();
      }

   }

   public void resetIndex() {
      this.index = 0;
   }

   public boolean isCenter() {
      return this.isCenter;
   }

   public void setCenter(boolean isCenter) {
      this.isCenter = isCenter;
   }
}

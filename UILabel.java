import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class UILabel extends UIComponent {
   public static final byte LABEL_NORMAL = 0;
   public static final byte LABEL_RIM = 1;
   public static final byte LABEL_DOUBLEINT = 2;
   public static final byte LABEL_INT = 3;
   public static final byte LABEL_FRAME = 4;
   public static final byte LABEL_IMG_TEXT = 5;
   public static final byte LABEL_TASK_ICON = 6;
   public static final byte LABEL_LONG = 7;
   public byte selectedNPCTaskIcon;
   private String text;
   private int textColor;
   private boolean isHCenter;
   private static final int CHOICE_RIMX = 40;
   private static final int CHOICE_RIMW = 95;
   public static final short LABELH = 16;
   private int num1;
   private int num2;
   private long longNum;
   public Vector gridInfoVector;
   private boolean isStrCenterXAnchor;
   private boolean sameCamp;
   private boolean isOL;
   public String[] textGroup;
   public boolean isRoll;
   public short rollWidth;
   public int rollCounter;
   public boolean isLeftRoll;
   public boolean isAlwaysScroll;
   public static int movementID = 0;

   public UILabel(short x, short y, short w, short h, byte type, String txt, UIForm form) {
      super(x, y, w, h, form);
      this.selectedNPCTaskIcon = 0;
      this.textColor = Cons.COLOR_FONT_1;
      this.isHCenter = false;
      this.gridInfoVector = new Vector();
      this.isStrCenterXAnchor = false;
      this.sameCamp = true;
      this.isOL = true;
      this.textGroup = new String[0];
      this.isRoll = false;
      this.rollWidth = 0;
      this.rollCounter = 0;
      this.isLeftRoll = true;
      super.type = type;
      this.setText(txt);
      if (type == 0) {
         super.canFocus = true;
      }

   }

   public UILabel(short x, short y, short w, byte type, String txt, UIForm form) {
      this(x, y, w, (short)16, type, txt, form);
   }

   public UILabel(short x, short y, byte type, String txt, UIForm form) {
      this(x, y, (short)0, (short)16, type, txt, form);
   }

   public UILabel(short x, short y, byte type, String txt, boolean isStrCenterXAnchor, UIForm form) {
      this(x, y, (short)0, (short)16, type, txt, form);
      this.setStrCenterXAnchor(isStrCenterXAnchor);
   }

   public UILabel(short x, short y, byte type, int num1, int num2, UIForm form) {
      this(x, y, (short)0, (short)16, type, "", form);
   }

   public UILabel(short x, short y, byte type, int num, UIForm form) {
      this(x, y, (short)0, (short)16, type, "", form);
   }

   public void setType(byte t) {
      super.type = t;
   }

   public byte getType() {
      return super.type;
   }

   public void draw(Graphics g) {
      if (super.type == 0) {
         if (this.text != null && !this.text.equals("")) {
            if (!this.isOL) {
               g.setColor(8224125);
            } else if (!this.sameCamp) {
               g.setColor(16711680);
            } else if (super.inverse) {
               g.setColor(Cons.COLOR_PANEL_BG);
            } else {
               g.setColor(Cons.COLOR_FONT_1);
            }

            if (this.isFocus()) {
               if (this.isStrCenterXAnchor) {
                  Util.drawReactionKuangEffect(g, (MainCanvas.screenW - Util.getStrLen(this.getText())) / 2 - 2, super.positionY - 1, Util.getStrLen(this.getText()) + 3, MainCanvas.CHARH + 1);
               } else {
                  Util.drawReactionKuangEffect(g, super.positionX - 2, super.positionY - 1, this.getText().length() * MainCanvas.CHARW + 3, MainCanvas.CHARH + 1);
               }

               if (this.getText().equals("Sửa đổi tên")) {
                  g.setColor(16711680);
               } else {
                  g.setColor(Cons.COLOR_PANEL_BG);
               }
            }

            if (this.isStrCenterXAnchor) {
               g.drawString(this.text, super.positionX, super.positionY, 17);
            } else if (this.isRoll) {
               if (!super.inverse && !this.isAlwaysScroll) {
                  this.drawSimpleStr(g, this.text, super.positionX, super.positionY, this.rollWidth);
               } else {
                  this.drawRollStr(g, this.text, super.positionX, super.positionY, this.rollWidth, (byte)1, (byte)3, false);
               }
            } else {
               g.drawString(this.text, super.positionX, super.positionY, 20);
            }

            if (MainCanvas.curForm != null && MainCanvas.curForm.clientCommand == 3145731) {
               int[] index = new int[]{4, 6, 10, 12};
               int[] index_1 = new int[]{3, 5, 9, 11};
               UIRadio[] radio = new UIRadio[index.length];
               UILabel[] label = new UILabel[index_1.length];

               for(int i = 0; i < index.length; ++i) {
                  radio[i] = (UIRadio)MainCanvas.curForm.getComponents().elementAt(index[i]);
                  label[i] = (UILabel)MainCanvas.curForm.getComponents().elementAt(index_1[i]);
                  if (radio[i].isFocus()) {
                     Util.drawReactionKuangEffect(g, label[i].positionX - 2, label[i].positionY - 1, label[i].getText().length() * MainCanvas.CHARW + 3, MainCanvas.CHARH + 1);
                     g.setColor(Cons.COLOR_PANEL_BG);
                     g.drawString(label[i].text, label[i].positionX, label[i].positionY, 20);
                  } else {
                     g.setColor(Cons.COLOR_MENU_SEL_ITEM_BG);
                     g.drawString(label[i].text, label[i].positionX, label[i].positionY, 20);
                  }
               }
            }
         }
      } else {
         int kuangH;
         int txtY;
         if (super.type == 1) {
            kuangH = MainCanvas.ui_labelImg.getHeight();
            g.setColor(Cons.COLOR_LABEL_BORDER);
            g.drawRect(super.positionX, super.positionY, super.width - 1, kuangH - 1);
            g.setColor(Cons.COLOR_LABEL_BG);
            g.fillRect(super.positionX + 1, super.positionY + 1, super.width - 2, kuangH - 2);
            g.drawImage(MainCanvas.ui_labelImg, super.positionX, super.positionY, 20);
            Util.drawImage(g, MainCanvas.ui_labelImg, super.positionX + super.width - MainCanvas.ui_labelImg.getWidth(), super.positionY, 2);
            if (this.text != null && !this.text.equals("")) {
               txtY = super.positionY + (kuangH - MainCanvas.CHARH) / 2;
               if (Util.getStrLen(this.text) <= super.width - 2 * MainCanvas.ui_labelImg.getWidth()) {
                  Util.drawString(g, this.text, super.width, super.positionX, txtY, this.textColor);
               } else {
                  g.setColor(this.textColor);
                  Util.drawRollStr(g, this.text, super.positionX + MainCanvas.ui_labelImg.getWidth(), txtY, super.width - 2 * MainCanvas.ui_labelImg.getWidth(), (byte)1, (byte)3);
               }
            }
         } else if (super.type == 2) {
            if (MainCanvas.curForm.clientCommand == 393223) {
               Util.drawEspecialDoubleNum(g, this.num1, this.num2, super.positionX, super.positionY);
            } else {
               Util.drawDoubleNum(g, this.num1, this.num2, super.positionX, super.positionY);
            }
         } else if (super.type == 3) {
            Util.drawIntNum(g, (long)this.num1, super.positionX, super.positionY);
         } else if (super.type == 7) {
            Util.drawIntNum(g, this.longNum, super.positionX, super.positionY);
         } else {
            short offsetX;
            if (super.type == 4) {
               short kuangX = super.positionX;
               offsetX = super.width;
               g.setColor(Cons.COLOR_TEXT_BORDER);
               g.drawRect(kuangX, super.positionY, offsetX - 1, super.height - 1);
               g.setColor(Cons.COLOR_TEXT_BG);
               g.fillRect(kuangX + 1, super.positionY + 1, offsetX - 2, super.height - 2);
               short strX = (short)(super.positionX + (super.width / 2 - MainCanvas.curFont.stringWidth(this.text) / 2));
               if (this.text != null && !this.text.equals("")) {
                  g.setColor(Cons.COLOR_TEXT_FONT);
                  g.drawString(this.text, strX, super.positionY + 2, 20);
               }
            } else if (super.type == 5) {
               kuangH = MainCanvas.ui_labelImg.getHeight();
               g.setColor(Cons.COLOR_LABEL_BORDER);
               g.drawRect(super.positionX, super.positionY, super.width - 1, kuangH - 1);
               g.setColor(Cons.COLOR_LABEL_BG);
               g.fillRect(super.positionX + 1, super.positionY + 1, super.width - 2, kuangH - 2);
               g.drawImage(MainCanvas.ui_labelImg, super.positionX, super.positionY, 20);
               Util.drawImage(g, MainCanvas.ui_labelImg, super.positionX + super.width - MainCanvas.ui_labelImg.getWidth(), super.positionY, 2);
               if (this.text != null && !this.text.equals("")) {
                  txtY = super.positionY + (kuangH - MainCanvas.CHARH) / 2;
                  Util.drawString(g, this.text, super.width, super.positionX, txtY, this.textColor);
               }
            } else if (super.type == 6) {
               int ImgId = 0;
               switch(this.selectedNPCTaskIcon) {
               case 1:
                  ImgId = 0;
                  break;
               case 2:
                  ImgId = 1;
                  break;
               case 3:
                  if (MainCanvas.countTick % 6 < 3) {
                     ImgId = 6;
                  } else {
                     ImgId = 7;
                  }
                  break;
               case 4:
                  if (MainCanvas.countTick % 6 < 3) {
                     ImgId = 2;
                  } else {
                     ImgId = 3;
                  }
                  break;
               case 5:
                  if (MainCanvas.countTick % 6 < 3) {
                     ImgId = 8;
                  } else {
                     ImgId = 9;
                  }
                  break;
               case 6:
                  if (MainCanvas.countTick % 6 < 3) {
                     ImgId = 4;
                  } else {
                     ImgId = 5;
                  }
               }

               if (this.selectedNPCTaskIcon != 0) {
                  MainCanvas.maskImg.draw(g, super.positionX, super.positionY, ImgId);
               }

               if (super.inverse) {
                  g.setColor(Cons.COLOR_PANEL_BG);
               } else {
                  g.setColor(Cons.COLOR_FONT_1);
               }

               offsetX = this.selectedNPCTaskIcon == 0 ? 0 : MainCanvas.taskIconImg.frame_w;
               if (this.isRoll) {
                  if (super.inverse) {
                     this.drawRollStr(g, this.text, super.positionX + offsetX, super.positionY, this.rollWidth, (byte)1, (byte)3, false);
                  } else {
                     this.drawSimpleStr(g, this.text, super.positionX + offsetX, super.positionY, this.rollWidth);
                  }
               } else {
                  g.drawString(this.text, super.positionX + offsetX, super.positionY, 20);
               }
            }
         }
      }

   }

   public void drawLabelEffect(Graphics g, int[] oldColor, int[] curColor, int time) {
      int[] color = new int[curColor.length];
      int a = false;
      if (MainCanvas.countTick % (time << 1) < time) {
         color = oldColor;
      } else {
         color = curColor;
      }

      switch(super.type) {
      case 4:
         short kuangX = super.positionX;
         short kuangW = super.width;
         g.setColor(color[0]);
         g.drawRect(kuangX, super.positionY, kuangW - 1, super.height - 1);
         g.setColor(color[1]);
         g.fillRect(kuangX + 1, super.positionY + 1, kuangW - 2, super.height - 2);
         short strX = (short)(super.positionX + (super.width / 2 - MainCanvas.curFont.stringWidth(this.text) / 2));
         if (this.text != null && !this.text.equals("")) {
            g.setColor(color[2]);
            g.drawString(this.text, strX, super.positionY + 2, 20);
         }
      default:
      }
   }

   public void draw(Graphics g, int color) {
      g.setColor(color);
      g.drawString(this.text, super.positionX, super.positionY, 20);
   }

   public String getText() {
      if (this.text == null) {
         this.text = "";
      }

      return this.text;
   }

   public void setText(String text) {
      this.text = text;
      if (super.type == 0) {
         this.setWidth((short)Util.getStrLen(text));
      }

   }

   public static int getRimLabelHeight() {
      return MainCanvas.ui_labelImg.getHeight();
   }

   public int getTextColor() {
      return this.textColor;
   }

   public void setTextColor(int textColor) {
      this.textColor = textColor;
   }

   public boolean isHCenter() {
      return this.isHCenter;
   }

   public void setHCenter(boolean isHCenter) {
      this.isHCenter = isHCenter;
      if (isHCenter) {
         this.setPositionX((short)((MainCanvas.curForm.getWidth() - super.width) / 2));
      }

   }

   public int getNum1() {
      return this.num1;
   }

   public void setNum1(int num1) {
      this.num1 = num1;
      if (super.type == 2) {
         super.width = (short)Util.getDoubleIntSize(num1, this.getNum2())[0];
         super.height = (short)Util.getDoubleIntSize(num1, this.getNum2())[1];
      } else if (super.type == 3) {
         super.width = (short)Util.getIntSize((long)num1)[0];
         super.height = (short)Util.getIntSize((long)num1)[1];
      }

   }

   public void setLongNum(long num) {
      this.longNum = num;
      super.width = (short)Util.getIntSize(this.longNum)[0];
      super.height = (short)Util.getIntSize(this.longNum)[1];
   }

   public int getNum2() {
      return this.num2;
   }

   public void setNum2(int num2) {
      this.num2 = num2;
      if (super.type == 2) {
         super.width = (short)Util.getDoubleIntSize(this.getNum1(), num2)[0];
         super.height = (short)Util.getDoubleIntSize(this.getNum1(), num2)[1];
      }

   }

   public boolean isStrCenterXAnchor() {
      return this.isStrCenterXAnchor;
   }

   public void setStrCenterXAnchor(boolean isStrCenterXAnchor) {
      this.isStrCenterXAnchor = isStrCenterXAnchor;
   }

   public boolean isSameCamp() {
      return this.sameCamp;
   }

   public void setSameCamp(boolean sameCamp) {
      this.sameCamp = sameCamp;
   }

   public boolean isOL() {
      return this.isOL;
   }

   public void setOL(boolean isOL) {
      this.isOL = isOL;
   }

   public void drawRollStr(Graphics g, String rollStr, int strX, int strY, int w, byte type, byte speed, boolean isDependW) {
      if (Util.getStrLen(rollStr) <= w && !isDependW) {
         g.drawString(rollStr, strX, strY, 20);
      } else {
         int clipX = g.getClipX();
         int clipY = g.getClipY();
         int clipW = g.getClipWidth();
         int clipH = g.getClipHeight();
         g.setClip(strX, strY, w, MainCanvas.CHARH + 3);
         int leftX;
         switch(type) {
         case 0:
            leftX = strX + w - speed * this.rollCounter;
            if (leftX + Util.getStrLen(rollStr) < strX) {
               this.rollCounter = 0;
            } else {
               ++this.rollCounter;
            }

            g.drawString(rollStr, leftX, strY, 20);
            break;
         case 1:
            if (this.isLeftRoll) {
               leftX = strX - speed * this.rollCounter;
               g.drawString(rollStr, leftX, strY, 20);
               ++this.rollCounter;
               if (leftX + Util.getStrLen(rollStr) < strX + w) {
                  this.isLeftRoll = false;
                  this.rollCounter = 0;
               }
            } else {
               leftX = strX - (Util.getStrLen(rollStr) - w) + speed * this.rollCounter;
               g.drawString(rollStr, leftX, strY, 20);
               ++this.rollCounter;
               if (leftX > strX) {
                  this.isLeftRoll = true;
                  this.rollCounter = 0;
               }
            }
         }

         g.setClip(clipX, clipY, clipW, clipH);
      }

   }

   public void drawSimpleStr(Graphics g, String simpleStr, int strX, int strY, int w) {
      if (Util.getStrLen(simpleStr) <= w) {
         g.drawString(simpleStr, strX, strY, 20);
      } else {
         int wordNum = w / MainCanvas.CHARW - 1;
         g.drawString(simpleStr.substring(0, wordNum) + "...", strX, strY, 20);
      }

   }

   public void keyInAction() {
      if (super.isShowMenu) {
         if (this.getMenu() == null) {
            return;
         }

         if (MainCanvas.isInputDownOrHold(4096)) {
            this.getMenu().decreaseIndex();
         } else if (MainCanvas.isInputDownOrHold(8192)) {
            this.getMenu().increaseIndex();
         }

         if (!MainCanvas.isInputDown(65536) && !MainCanvas.isInputDown(131072)) {
            if (MainCanvas.isInputDown(262144)) {
               super.isShowMenu = false;
               this.getMenu().resetIndex();
            }
         } else {
            this.getMenu().saveForm();
            switch(MainCanvas.curForm.clientCommand) {
            case 2555905:
               int cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               if (cmd == 2555906) {
                  PCFarm.m_bPropManeFinger = 0;
               } else if (cmd == 2555905) {
                  if (PCFarm.m_bWhetherGetHome == 0) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Đã ở trong nông trường", "Xác nhận", "", -1, -2);
                     super.isShowMenu = false;
                     return;
                  }

                  if (PCFarm.m_nFarm_On_Off != 0) {
                     PCFarm.m_nFarm_On_Off = 0;
                     MainCanvas.ni.send(2555952);
                  }

                  PCFarm.m_nPlayerIndex = 0;
               }

               MainCanvas.ni.send(cmd);
               super.isShowMenu = false;
            }
         }
      } else if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
         this.addEquipPicMenu();
      }

   }

   public void addEquipPicMenu() {
      switch(MainCanvas.curForm.clientCommand) {
      case 2555905:
         UILabel label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(35);
         UILabel label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(36);
         UILabel label_3 = (UILabel)MainCanvas.curForm.getComponents().elementAt(37);
         UIMenu menu;
         int[] menuCommand;
         int[] inns;
         String[] strs;
         if (label_1.isFocus()) {
            menu = new UIMenu((byte)0);
            if (PCFarm.m_bWhetherGetHome == 0) {
               menuCommand = new int[]{2555906, 2555911, 2555907, 2555915, 2555910, 2555914, 2555976};
               inns = new int[]{2, 2, 2, 2, 2, 2, 2};
               strs = new String[]{"Chi tiết", "Thương khố", "Túi hạt giống", "Túi vật phẩm", "Bảng thông báo", "Trạng thái nông trường", "Hỗ trợ nông trường"};
               menu.addMenu(strs, menuCommand, inns);
            } else if (PCFarm.m_bWhetherGetHome == 1) {
               menuCommand = new int[]{2555906, 2555914, 2555976};
               inns = new int[]{2, 2, 2};
               strs = new String[]{"Chi tiết", "Trạng thái nông trường", "Hỗ trợ nông trường"};
               menu.addMenu(strs, menuCommand, inns);
            }

            this.setMenu(menu);
            super.isShowMenu = true;
            this.getMenu().resetIndex();
         } else if (label_2.isFocus()) {
            menu = new UIMenu((byte)0);
            menuCommand = new int[]{2555908, 2555957};
            inns = new int[]{2, 2};
            strs = new String[]{"Tiệm hạt giống", "Tiệm vật phẩm"};
            menu.addMenu(strs, menuCommand, inns);
            this.setMenu(menu);
            super.isShowMenu = true;
            this.getMenu().resetIndex();
         } else if (label_3.isFocus()) {
            menu = new UIMenu((byte)0);
            menuCommand = new int[]{2555905, 2555912, 2555913};
            inns = new int[]{3, 2, 2};
            strs = new String[]{"Nông trường của mình", "Đi trộm bạn bè", "Đi trộm kẻ thù"};
            menu.addMenu(strs, menuCommand, inns);
            this.setMenu(menu);
            super.isShowMenu = true;
            this.getMenu().resetIndex();
         }
      default:
      }
   }

   public long getLongNum() {
      return this.longNum;
   }
}

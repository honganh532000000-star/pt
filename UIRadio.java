import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class UIRadio extends UIComponent {
   public static final byte TYPE_RADIO = 0;
   public static final byte TYPE_SELECT = 1;
   public static final byte TYPE_CHECKBOX = 2;
   public static final short OX = 8;
   public static final short IX = 4;
   public static final short BX = 7;
   public static final short BY = 2;
   public static final short RADIO_SIZE = 8;
   public String text;
   public Vector items;
   public boolean[] isCanUsed;
   private byte selectIndex;
   private byte sureIndex;
   public boolean[] isCheckBoxSelect;
   private boolean isHorizontal;
   private byte hangnum;
   public static byte[] m_bIndex = new byte[5];
   public boolean isEndDown;
   public boolean isEndUp;

   public boolean isHorizontal() {
      return this.isHorizontal;
   }

   public void setHorizontal(boolean isHorizontal) {
      this.isHorizontal = isHorizontal;
   }

   public byte getSureIndex() {
      return this.sureIndex;
   }

   public void setSureIndex(byte sureIndex) {
      this.sureIndex = sureIndex;
   }

   public UIRadio(short x, short y, short w, short h, byte type, String txt, UIForm form) {
      super(x, y, w, h, form);
      this.isHorizontal = true;
      this.isEndDown = false;
      this.isEndUp = false;
      super.type = type;
      this.text = txt;
      this.items = new Vector();
      super.width = this.getAutoRadioWidth();
      super.height = this.getAutoRadioHeight();
      super.canFocus = true;
      super.haveInnerComponent = true;
      this.selectIndex = this.sureIndex = 0;
   }

   public UIRadio(short x, short y, byte type, String txt, UIForm form) {
      this(x, y, (short)0, (short)0, type, txt, form);
   }

   public void draw(Graphics g) {
      if (this.text != null && !this.text.equals("")) {
         if (this.isFocus()) {
            g.setColor(Cons.COLOR_PANEL_BG);
         } else {
            g.setColor(Cons.COLOR_FONT_1);
         }

         if (super.type == 2) {
            g.setColor(Cons.COLOR_FONT_1);
         }

         g.drawString(this.text, super.positionX, super.positionY, 20);
      }

      int startX = super.positionX;
      if (this.text != null && !this.text.equals("")) {
         startX += Util.getStrLen(this.text) + 8;
      }

      int kuaiY;
      int i;
      int skx;
      int sky;
      int skw;
      int skh;
      if (super.type == 0 && !this.items.isEmpty()) {
         if (this.isHorizontal) {
            kuaiY = super.positionY + (super.height - 8) / 2;

            for(i = 0; i < this.items.size(); ++i) {
               if (i == this.sureIndex && this.isCanUsed[i]) {
                  g.setColor(Cons.COLOR_RADIO_CORE);
                  g.drawRect(startX + this.getBetweenWidth(i), kuaiY, 7, 7);
                  g.setColor(Cons.COLOR_RADIO_UNSEL_CORE_BG);
                  g.drawRect(startX + this.getBetweenWidth(i) + 1, kuaiY + 1, 5, 5);
                  g.setColor(Cons.COLOR_RADIO_CORE);
                  g.fillRect(startX + this.getBetweenWidth(i) + 2, kuaiY + 2, 4, 4);
               } else if (this.isCanUsed[i]) {
                  g.setColor(Cons.COLOR_RADIO_UNSEL_CORE_BG);
                  g.drawRect(startX + this.getBetweenWidth(i), kuaiY, 7, 7);
                  g.setColor(Cons.COLOR_RADIO_UNSEL_CORE_BG);
                  g.fillRect(startX + this.getBetweenWidth(i) + 1, kuaiY + 1, 6, 6);
               } else {
                  g.setColor(Cons.COLOR_RADIO_CORE);
                  g.drawRect(startX + this.getBetweenWidth(i), kuaiY, 7, 7);
                  g.setColor(Cons.COLOR_RADIO_UNUSE_CORE_BG);
                  g.fillRect(startX + this.getBetweenWidth(i) + 1, kuaiY + 1, 6, 6);
                  g.setColor(Cons.COLOR_RADIO_CORE);
                  g.drawLine(startX + this.getBetweenWidth(i), kuaiY, startX + this.getBetweenWidth(i) + 8 - 1, kuaiY + 8 - 1);
                  g.drawLine(startX + this.getBetweenWidth(i) + 8 - 1, kuaiY, startX + this.getBetweenWidth(i), kuaiY + 8 - 1);
               }

               if (i == this.selectIndex && this.isFocus()) {
                  g.setColor(Cons.COLOR_RADIO_INDEX_ITEM_BG);
                  skx = startX + 8 + 4 + this.getBetweenWidth(i) - 1;
                  sky = super.positionY - 1;
                  skw = Util.getStrLen((String)this.items.elementAt(i)) + 2;
                  skh = MainCanvas.CHARH + 2;
                  g.fillRect(skx, sky, skw, skh);
                  g.setColor(Cons.COLOR_RADIO_UNSEL_CORE_BG);
               } else {
                  g.setColor(Cons.COLOR_FONT_1);
               }

               g.drawString((String)this.items.elementAt(i), startX + 8 + 4 + this.getBetweenWidth(i), super.positionY, 20);
            }
         } else {
            kuaiY = super.positionY + (MainCanvas.CHARH - 8) / 2;

            for(i = 0; i < this.items.size(); ++i) {
               if (i == this.sureIndex && this.isCanUsed[i]) {
                  g.setColor(Cons.COLOR_RADIO_CORE);
                  g.drawRect(startX, kuaiY + this.getBetweenRadioHeight(i), 7, 7);
                  g.setColor(Cons.COLOR_RADIO_UNSEL_CORE_BG);
                  g.drawRect(startX + 1, kuaiY + this.getBetweenRadioHeight(i) + 1, 5, 5);
                  g.setColor(Cons.COLOR_RADIO_CORE);
                  g.fillRect(startX + 2, kuaiY + this.getBetweenRadioHeight(i) + 2, 4, 4);
               } else if (this.isCanUsed[i]) {
                  g.setColor(Cons.COLOR_RADIO_CORE);
                  g.drawRect(startX, kuaiY + this.getBetweenRadioHeight(i), 7, 7);
                  g.setColor(Cons.COLOR_RADIO_UNSEL_CORE_BG);
                  g.fillRect(startX + 1, kuaiY + this.getBetweenRadioHeight(i) + 1, 6, 6);
               } else {
                  g.setColor(Cons.COLOR_RADIO_CORE);
                  g.drawRect(startX, kuaiY + this.getBetweenRadioHeight(i), 7, 7);
                  g.setColor(Cons.COLOR_RADIO_UNUSE_CORE_BG);
                  g.fillRect(startX + 1, kuaiY + this.getBetweenRadioHeight(i) + 1, 6, 6);
                  g.setColor(Cons.COLOR_RADIO_CORE);
                  g.drawLine(startX, kuaiY + this.getBetweenRadioHeight(i), startX + 8 - 1, kuaiY + this.getBetweenRadioHeight(i) + 8 - 1);
                  g.drawLine(startX + 8 - 1, kuaiY + this.getBetweenRadioHeight(i), startX, kuaiY + this.getBetweenRadioHeight(i) + 8 - 1);
               }

               if (i == this.selectIndex) {
                  g.setColor(Cons.COLOR_RADIO_INDEX_ITEM_BG);
                  skx = startX + 8 + 4 - 1;
                  sky = super.positionY + this.getBetweenHeight(i) - 1;
                  skw = Util.getStrLen((String)this.items.elementAt(i)) + 2;
                  skh = MainCanvas.CHARH + 2;
                  g.fillRect(skx, sky, skw, skh);
                  g.setColor(Cons.COLOR_RADIO_UNSEL_CORE_BG);
               } else {
                  g.setColor(Cons.COLOR_FONT_1);
               }

               g.drawString((String)this.items.elementAt(i), startX + 8 + 4, super.positionY + this.getBetweenHeight(i), 20);
            }
         }
      } else if (super.type == 1 && !this.items.isEmpty()) {
         if (this.isFocus()) {
            super.width = this.getAutoRadioWidth();
            g.setColor(Cons.COLOR_FONT_1);
            g.fillRect(super.positionX - 2, super.positionY - 1, super.width + 3, MainCanvas.CHARH + 1);
         }

         if (this.text != null && !this.text.equals("")) {
            if (this.isFocus()) {
               g.setColor(Cons.COLOR_PANEL_BG);
            } else {
               g.setColor(Cons.COLOR_FONT_1);
            }

            g.drawString(this.text, super.positionX, super.positionY, 20);
         }

         g.drawImage(MainCanvas.ui_aImg, startX, super.positionY, 20);
         if (this.isFocus()) {
            g.setColor(Cons.COLOR_PANEL_BG);
         } else {
            g.setColor(Cons.COLOR_FONT_1);
         }

         g.drawString((String)this.items.elementAt(this.selectIndex), startX + MainCanvas.ui_aImg.getWidth() + 4, super.positionY, 20);
         Util.drawImage(g, MainCanvas.ui_aImg, startX + MainCanvas.ui_aImg.getWidth() + 8 + Util.getStrLen((String)this.items.elementAt(this.selectIndex)), super.positionY, 2);
      } else if (super.type == 2 && !this.items.isEmpty()) {
         kuaiY = super.positionY + (MainCanvas.CHARH - 8) / 2;

         for(i = 0; i < this.items.size(); ++i) {
            if (this.isCheckBoxSelect[i]) {
               g.setColor(Cons.COLOR_RADIO_CORE);
               g.drawRect(startX, kuaiY + this.getBetweenRadioHeight(i), 7, 7);
               g.setColor(Cons.COLOR_RADIO_UNSEL_CORE_BG);
               g.drawRect(startX + 1, kuaiY + this.getBetweenRadioHeight(i) + 1, 5, 5);
               g.setColor(Cons.COLOR_RADIO_CORE);
               g.fillRect(startX + 2, kuaiY + this.getBetweenRadioHeight(i) + 2, 4, 4);
            } else {
               g.setColor(Cons.COLOR_RADIO_CORE);
               g.drawRect(startX, kuaiY + this.getBetweenRadioHeight(i), 7, 7);
               g.setColor(Cons.COLOR_RADIO_UNSEL_CORE_BG);
               g.fillRect(startX + 1, kuaiY + this.getBetweenRadioHeight(i) + 1, 6, 6);
            }

            if (i == this.selectIndex) {
               g.setColor(Cons.COLOR_RADIO_INDEX_ITEM_BG);
               skx = startX + 8 + 4 - 1;
               sky = super.positionY + this.getBetweenHeight(i) - 1;
               skw = Util.getStrLen((String)this.items.elementAt(i)) + 2;
               skh = MainCanvas.CHARH + 2;
               g.fillRect(skx, sky, skw, skh);
               g.setColor(Cons.COLOR_RADIO_UNSEL_CORE_BG);
            } else {
               g.setColor(Cons.COLOR_FONT_1);
            }

            g.drawString((String)this.items.elementAt(i), startX + 8 + 4, super.positionY + this.getBetweenHeight(i), 20);
         }
      }

   }

   public short getAutoRadioHeight() {
      switch(super.type) {
      case 0:
      case 2:
         if (this.isHorizontal) {
            return (short)MainCanvas.CHARH;
         }

         return (short)((MainCanvas.CHARH + 2) * this.items.size() - 2);
      case 1:
         return (short)MainCanvas.ui_aImg.getHeight();
      default:
         throw new IllegalArgumentException("Loại hình không phù hợp");
      }
   }

   public String getText() {
      return this.text;
   }

   public void setText(String text) {
      this.text = text;
   }

   public byte getSelectIndex() {
      return this.selectIndex;
   }

   public void setSelectIndex(byte selectIndex) {
      if (selectIndex >= 0 && selectIndex < this.items.size()) {
         this.selectIndex = selectIndex;
      } else {
         throw new IllegalArgumentException("Tham số không phù hợp");
      }
   }

   public Vector getItems() {
      return this.items;
   }

   public String[] getItemsStr() {
      String[] strs = new String[this.items.size()];

      for(int i = 0; i < strs.length; ++i) {
         strs[i] = (String)this.items.elementAt(i);
      }

      return strs;
   }

   public void add(String item) {
      if (item != null) {
         this.items.addElement(item);
         super.width = this.getAutoRadioWidth();
         super.height = this.getAutoRadioHeight();
      }

   }

   public void add(String[] its) {
      if (its != null && its.length != 0) {
         for(int i = 0; i < its.length; ++i) {
            this.items.addElement(its[i]);
         }
      }

   }

   private short getBetweenWidth(int index) {
      short bW = 0;

      for(int i = 0; i < index; ++i) {
         bW = (short)(bW + 12 + Util.getStrLen((String)this.items.elementAt(i)) + 7);
      }

      return bW;
   }

   private short getBetweenRadioHeight(int index) {
      short bH = 0;

      for(int i = 0; i < index; ++i) {
         bH = (short)(bH + 10 + (MainCanvas.CHARH - 8));
      }

      return bH;
   }

   private short getBetweenHeight(int index) {
      short bH = 0;

      for(int i = 0; i < index; ++i) {
         bH = (short)(bH + MainCanvas.CHARH + 2);
      }

      return bH;
   }

   public void setCanUsed(int index, boolean isUsed) {
      if (index >= 0 && index <= this.isCanUsed.length - 1) {
         this.isCanUsed[index] = isUsed;
      } else {
         throw new IndexOutOfBoundsException("Biểu tượng index vượt giới hạn");
      }
   }

   public short getAutoRadioWidth() {
      short autoWidth = 0;
      if (this.text != null && !this.text.equals("")) {
         autoWidth = (short)(autoWidth + Util.getStrLen(this.text) + 8);
      }

      switch(super.type) {
      case 0:
      case 2:
         if (this.isHorizontal) {
            int i = 0;

            for(int n = this.items.size(); i < n; ++i) {
               autoWidth = (short)(autoWidth + 12 + Util.getStrLen((String)this.items.elementAt(i)));
               if (i != n - 1) {
                  autoWidth = (short)(autoWidth + 7);
               }
            }

            return autoWidth;
         } else {
            autoWidth = (short)(autoWidth + 12 + this.getMaxStrWidth());
            break;
         }
      case 1:
         if (!this.items.isEmpty()) {
            autoWidth = (short)(autoWidth + (MainCanvas.ui_aImg.getWidth() + 4) * 2 + Util.getStrLen((String)this.items.elementAt(this.selectIndex)));
         }
         break;
      default:
         throw new IllegalArgumentException("Loại hình không phù hợp");
      }

      return autoWidth;
   }

   private short getMaxStrWidth() {
      if (this.items.size() > 0) {
         int maxIndex = Util.getStrLen((String)this.items.elementAt(0));

         for(int i = 1; i < this.items.size(); ++i) {
            String curStr = (String)this.items.elementAt(i);
            String maxStr = (String)this.items.elementAt(maxIndex);
            if (Util.getStrLen(curStr) > Util.getStrLen(maxStr)) {
               maxIndex = Util.getStrLen(curStr);
            }
         }

         return (short)maxIndex;
      } else {
         return 0;
      }
   }

   public void keyInAction() {
      int cmd;
      if (super.isShowMenu) {
         if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
            if (!MainCanvas.isInputDownOrHold(16384) && !MainCanvas.isInputDownOrHold(32768)) {
               if (MainCanvas.isInputDownOrHold(4096)) {
                  this.getMenu().decreaseIndex();
               } else if (MainCanvas.isInputDownOrHold(8192)) {
                  this.getMenu().increaseIndex();
               } else if (MainCanvas.isInputDown(262144)) {
                  super.isShowMenu = false;
               }
            }
         } else {
            if (super.type == 0 && !this.items.isEmpty()) {
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case 0:
                  this.setSureIndex(this.getSelectIndex());
               default:
                  super.isShowMenu = false;
               }
            } else if (super.type != 2) {
               if (super.type == 1) {
                  switch(MainCanvas.curForm.clientCommand) {
                  case 1900547:
                     if (PCAuction.m_bEntityGoods == 1) {
                        cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                        switch(cmd) {
                        case -1610612714:
                           PCAuction.RetrieveGoods((byte)1);
                           break;
                        case -1610612713:
                           PCAuction.ConfirmAuction();
                        }

                        super.isShowMenu = false;
                     }
                  }
               }
            } else {
               switch(MainCanvas.curForm.clientCommand) {
               case -1610612632:
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  byte i;
                  label277:
                  switch(cmd) {
                  case -1610612631:
                     if (!this.isCheckBoxSelect[this.selectIndex]) {
                        this.isCheckBoxSelect[this.selectIndex] = true;
                     }
                     break;
                  case -1610612624:
                     if (this.isCheckBoxSelect[this.selectIndex]) {
                        this.isCheckBoxSelect[this.selectIndex] = false;
                     }
                     break;
                  case -1610612623:
                     i = 0;

                     while(true) {
                        if (i >= PCMonthly.num) {
                           break label277;
                        }

                        this.isCheckBoxSelect[i] = true;
                        ++i;
                     }
                  case -1610612621:
                     i = 0;

                     while(true) {
                        if (i >= PCMonthly.num) {
                           break label277;
                        }

                        this.isCheckBoxSelect[i] = false;
                        ++i;
                     }
                  case 2686980:
                     for(i = 0; i < PCMonthly.num; ++i) {
                        PCMonthly.m_bwhetherChoice[i] = this.isCheckBoxSelect[i];
                     }

                     MainCanvas.ni.send(2686980);
                     UITopForm.createLocalTopForm((byte)0, (String)"Đang thao tác, vui lòng chờ", "", "", -2, -2);
                  }
               }
            }

            super.isShowMenu = false;
         }
      } else if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
         UIRadio newPlayProtect;
         if (MainCanvas.isInputDownOrHold(16384)) {
            if (super.type == 1 && !this.items.isEmpty()) {
               if (this.isFocus()) {
                  if (MainCanvas.getState() == 18) {
                     KEY_LEFT();
                     super.width = this.getAutoRadioWidth();
                     MainCanvas.playPicture[0].otherP = new OtherPlayer((short)0, (short)0, MainCanvas.m_bPhyle, MainCanvas.m_bVocation, MainCanvas.m_bGender);
                     MainCanvas.Equip(false);
                  } else {
                     switch(MainCanvas.curForm.clientCommand) {
                     case -1610612734:
                        if (this.decreaseIndex()) {
                           SIManager.getInstance();
                           SIManager.shortCut.changeUIFormGridState((byte)(this.getSelectIndex() + 1));
                        }
                        break;
                     case -1610612728:
                        this.PIC_QUA_SELECT(true);
                        break;
                     case -1610612727:
                        newPlayProtect = (UIRadio)MainCanvas.curForm.getComponents().elementAt(3);
                        if (Player.getInstance().getLevel() < 20 || !newPlayProtect.isFocus()) {
                           this.decreaseIndex();
                        }
                        break;
                     case 1900547:
                        if (this.decreaseIndex()) {
                           PCAuction.m_bAbidanceHours = this.selectIndex;
                        }
                        break;
                     default:
                        this.decreaseIndex();
                     }
                  }
               }
            } else if ((super.type == 0 || super.type == 2) && !this.items.isEmpty() && this.isHorizontal) {
               this.decreaseIndex();
               if (MainCanvas.curForm.clientCommand == 3145731) {
                  this.setSureIndex(this.selectIndex);
               }
            }
         } else if (MainCanvas.isInputDownOrHold(32768)) {
            if (super.type == 1 && !this.items.isEmpty()) {
               if (this.isFocus()) {
                  if (MainCanvas.getState() == 18) {
                     KEY_RIGHT();
                     super.width = this.getAutoRadioWidth();
                     MainCanvas.playPicture[0].otherP = new OtherPlayer((short)0, (short)0, MainCanvas.m_bPhyle, MainCanvas.m_bVocation, MainCanvas.m_bGender);
                     MainCanvas.Equip(false);
                  } else {
                     switch(MainCanvas.curForm.clientCommand) {
                     case -1610612734:
                        if (this.increaseIndex()) {
                           SIManager.getInstance();
                           SIManager.shortCut.changeUIFormGridState((byte)(this.getSelectIndex() + 1));
                        }
                        break;
                     case -1610612728:
                        this.PIC_QUA_SELECT(false);
                        break;
                     case -1610612727:
                        newPlayProtect = (UIRadio)MainCanvas.curForm.getComponents().elementAt(3);
                        if (Player.getInstance().getLevel() < 20 || !newPlayProtect.isFocus()) {
                           this.increaseIndex();
                        }
                        break;
                     case 1900547:
                        if (this.increaseIndex()) {
                           PCAuction.m_bAbidanceHours = this.selectIndex;
                        }
                        break;
                     default:
                        this.increaseIndex();
                     }
                  }
               }
            } else if ((super.type == 0 || super.type == 2) && !this.items.isEmpty() && this.isHorizontal) {
               this.increaseIndex();
               if (MainCanvas.curForm.clientCommand == 3145731) {
                  this.setSureIndex(this.selectIndex);
               }
            }
         } else if (MainCanvas.isInputDownOrHold(4096)) {
            if ((super.type == 0 || super.type == 2) && !this.items.isEmpty() && !this.isHorizontal) {
               this.decreaseIndex();
               if (MainCanvas.curForm.clientCommand == 1703972) {
                  this.setSureIndex(this.selectIndex);
               }
            }
         } else if (MainCanvas.isInputDownOrHold(8192) && (super.type == 0 || super.type == 2) && !this.items.isEmpty() && !this.isHorizontal) {
            this.increaseIndex();
            if (MainCanvas.curForm.clientCommand == 1703972) {
               this.setSureIndex(this.selectIndex);
            }
         }
      } else if (!this.items.isEmpty()) {
         int[] inns;
         UIMenu menu;
         int[] inns;
         String[] cmdStrs;
         if (super.type == 0) {
            switch(MainCanvas.curForm.clientCommand) {
            case 720910:
               menu = new UIMenu((byte)0);
               cmdStrs = new String[]{"Chọn lựa"};
               inns = new int[1];
               inns = new int[]{3};
               menu.addMenu(cmdStrs, inns, inns);
               this.setMenu(menu);
               super.isShowMenu = true;
               break;
            case 720920:
               this.setSureIndex(this.getSelectIndex());
               SITeam.m_bCopyDifficulty = this.getSureIndex();
               MainCanvas.ni.send(720921);
               break;
            case 1703972:
               if (this.getSureIndex() == 0) {
                  PCMail.m_bContentment = 4;
               } else if (this.getSureIndex() == 1) {
                  PCMail.m_bContentment = 3;
               } else if (this.getSureIndex() == 2) {
                  PCMail.m_bContentment = 2;
               } else if (this.getSureIndex() == 3) {
                  PCMail.m_bContentment = 1;
               } else if (this.getSureIndex() == 4) {
                  PCMail.m_bContentment = 5;
               }

               MainCanvas.ni.send(1703970);
               break;
            case 3145730:
               cmd = 0;

               for(; cmd < PCHang.WatchSkill.length; ++cmd) {
                  UIPicture picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(cmd + 3);
                  if (picture.getImg() == null) {
                     ++this.hangnum;
                  }
               }

               if (this.hangnum == 8) {
                  UITopForm.createLocalTopForm((byte)0, (String)"Xin thiết lập chức năng bảo vệ trước", "Xác nhận", "", -1, -2);
                  this.hangnum = 0;
                  return;
               }

               PCHang.SkillTime = this.getSelectIndex();
               this.hangnum = 0;
               break;
            case 3145731:
               int[] index = new int[]{6, 10, 12};
               UIRadio[] radio = new UIRadio[index.length];

               for(int i = 0; i < index.length; ++i) {
                  radio[i] = (UIRadio)MainCanvas.curForm.getComponents().elementAt(index[i]);
                  PCHang.HANG_INTERCALATE[i] = radio[i].getSureIndex();
               }

               MainCanvas.ni.send(3145740);
               UIForm.backUIForm();
            }

            if (this.isHorizontal) {
               this.sureIndex = this.selectIndex;
            }
         } else if (super.type == 2) {
            switch(MainCanvas.curForm.clientCommand) {
            case -1610612632:
               menu = new UIMenu((byte)0);
               cmdStrs = new String[]{"Chọn lựa", "Hủy", "Chọn hết", "Hủy hết", "Tiếp tục bao tháng"};
               inns = new int[]{-1610612631, -1610612624, -1610612623, -1610612621, 2686980};
               inns = new int[]{3, 3, 3, 3, 3};
               menu.addMenu(cmdStrs, inns, inns);
               this.setMenu(menu);
               super.isShowMenu = true;
            }
         } else if (super.type == 1) {
            switch(MainCanvas.curForm.clientCommand) {
            case 1900547:
               if (PCAuction.m_bEntityGoods == 1) {
                  menu = new UIMenu((byte)0);
                  int[] cmds = new int[]{-1610612713, -1610612714};
                  inns = new int[]{3, 3};
                  menu.addMenu(Cons.AUCTION_SALE_SUBMENU, cmds, inns);
                  this.setMenu(menu);
                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               }
            }
         }
      }

   }

   public void PIC_QUA_SELECT(boolean isLeft) {
      UIRadio picQua = (UIRadio)MainCanvas.curForm.getComponents().elementAt(3);
      UIRadio otherPla = (UIRadio)MainCanvas.curForm.getComponents().elementAt(4);
      UIRadio name = (UIRadio)MainCanvas.curForm.getComponents().elementAt(5);
      UIRadio teammateName = (UIRadio)MainCanvas.curForm.getComponents().elementAt(6);
      UIRadio monsterName = (UIRadio)MainCanvas.curForm.getComponents().elementAt(7);
      if (picQua.isFocus()) {
         if (isLeft) {
            --picQua.selectIndex;
            if (picQua.selectIndex < 0) {
               picQua.selectIndex = 0;
            }
         } else {
            ++picQua.selectIndex;
            if (picQua.selectIndex > picQua.items.size() - 1) {
               picQua.selectIndex = (byte)(picQua.items.size() - 1);
            }
         }

         MainCanvas.picQuaInd = picQua.selectIndex;
         switch(picQua.selectIndex) {
         case 0:
            otherPla.selectIndex = 1;
            name.selectIndex = 1;
            teammateName.selectIndex = 1;
            monsterName.selectIndex = 1;
            break;
         case 1:
            otherPla.selectIndex = 1;
            name.selectIndex = 0;
            teammateName.selectIndex = 1;
            monsterName.selectIndex = 1;
            break;
         case 2:
            otherPla.selectIndex = 0;
            name.selectIndex = 0;
            teammateName.selectIndex = 0;
            monsterName.selectIndex = 0;
         }
      } else if (isLeft) {
         this.decreaseIndex();
      } else {
         this.increaseIndex();
      }

   }

   public static void KEY_LEFT() {
      UIRadio camp = (UIRadio)MainCanvas.curForm.getComponents().elementAt(5);
      UIRadio phyle = (UIRadio)MainCanvas.curForm.getComponents().elementAt(6);
      UIRadio gender = (UIRadio)MainCanvas.curForm.getComponents().elementAt(7);
      UIRadio vocation = (UIRadio)MainCanvas.curForm.getComponents().elementAt(8);
      if (camp.isFocus()) {
         --camp.selectIndex;
         m_bIndex[1] = 0;
         if (camp.selectIndex < 0) {
            camp.selectIndex = 0;
            ++m_bIndex[0];
         }

         MainCanvas.m_bCamp = (byte)(camp.selectIndex + 1);
         if (camp.selectIndex == 0 && m_bIndex[0] == 0) {
            phyle.selectIndex = 0;
            vocation.selectIndex = 0;
            MainCanvas.m_bPhyle = (byte)(phyle.selectIndex + 1);
            MainCanvas.m_bVocation = (byte)(vocation.selectIndex + 1);
         }
      } else if (phyle.isFocus()) {
         --phyle.selectIndex;
         m_bIndex[3] = 0;
         m_bIndex[4] = 0;
         if (camp.selectIndex == 0) {
            if (phyle.selectIndex < 0) {
               phyle.selectIndex = 0;
               ++m_bIndex[2];
            }
         } else if (camp.selectIndex == 1 && phyle.selectIndex < 2) {
            phyle.selectIndex = 2;
            ++m_bIndex[2];
         }

         MainCanvas.m_bPhyle = (byte)(phyle.selectIndex + 1);
         if ((phyle.selectIndex == 0 || phyle.selectIndex == 2 || phyle.selectIndex == 3) && m_bIndex[2] == 0) {
            vocation.selectIndex = 0;
         } else if (phyle.selectIndex == 1) {
            vocation.selectIndex = 1;
         }

         MainCanvas.m_bVocation = (byte)(vocation.selectIndex + 1);
      } else if (gender.isFocus()) {
         --gender.selectIndex;
         if (gender.selectIndex < 0) {
            gender.selectIndex = 0;
         }

         MainCanvas.m_bGender = gender.selectIndex;
      } else if (vocation.isFocus()) {
         --vocation.selectIndex;
         if (phyle.selectIndex != 0 && phyle.selectIndex != 2) {
            if (phyle.selectIndex == 1) {
               if (vocation.selectIndex < 1) {
                  vocation.selectIndex = 1;
               }
            } else if (phyle.selectIndex == 3) {
               if (vocation.selectIndex < 0) {
                  vocation.selectIndex = 0;
               } else if (vocation.selectIndex == 4) {
                  vocation.selectIndex = 3;
               } else if (vocation.selectIndex == 2) {
                  vocation.selectIndex = 1;
               }
            }
         } else if (vocation.selectIndex < 0) {
            vocation.selectIndex = 0;
         } else if (phyle.selectIndex == 0 && vocation.selectIndex == 4) {
            vocation.selectIndex = 3;
         }

         MainCanvas.m_bVocation = (byte)(vocation.selectIndex + 1);
      }

   }

   public static void KEY_RIGHT() {
      UIRadio camp = (UIRadio)MainCanvas.curForm.getComponents().elementAt(5);
      UIRadio phyle = (UIRadio)MainCanvas.curForm.getComponents().elementAt(6);
      UIRadio gender = (UIRadio)MainCanvas.curForm.getComponents().elementAt(7);
      UIRadio vocation = (UIRadio)MainCanvas.curForm.getComponents().elementAt(8);
      if (camp.isFocus()) {
         ++camp.selectIndex;
         m_bIndex[0] = 0;
         if (camp.selectIndex > camp.items.size() - 1) {
            camp.selectIndex = (byte)(camp.items.size() - 1);
            ++m_bIndex[1];
         }

         MainCanvas.m_bCamp = (byte)(camp.selectIndex + 1);
         if (camp.selectIndex == 1 && m_bIndex[1] == 0) {
            phyle.selectIndex = 2;
            vocation.selectIndex = 0;
            MainCanvas.m_bPhyle = (byte)(phyle.selectIndex + 1);
            MainCanvas.m_bVocation = (byte)(vocation.selectIndex + 1);
         }
      } else if (phyle.isFocus()) {
         ++phyle.selectIndex;
         m_bIndex[2] = 0;
         if (camp.selectIndex == 0) {
            if (phyle.selectIndex > phyle.items.size() - 3) {
               phyle.selectIndex = (byte)(phyle.items.size() - 3);
               ++m_bIndex[3];
            }
         } else if (camp.selectIndex == 1 && phyle.selectIndex > phyle.items.size() - 1) {
            phyle.selectIndex = (byte)(phyle.items.size() - 1);
            ++m_bIndex[4];
         }

         MainCanvas.m_bPhyle = (byte)(phyle.selectIndex + 1);
         if ((phyle.selectIndex == 0 || phyle.selectIndex == 2 || phyle.selectIndex == 3) && m_bIndex[4] == 0) {
            vocation.selectIndex = 0;
         } else if (phyle.selectIndex == 1 && m_bIndex[3] == 0) {
            vocation.selectIndex = 1;
         }

         MainCanvas.m_bVocation = (byte)(vocation.selectIndex + 1);
      } else if (gender.isFocus()) {
         ++gender.selectIndex;
         if (gender.selectIndex > gender.items.size() - 1) {
            gender.selectIndex = (byte)(gender.items.size() - 1);
         }

         MainCanvas.m_bGender = gender.selectIndex;
      } else if (vocation.isFocus()) {
         ++vocation.selectIndex;
         if (phyle.selectIndex == 0) {
            if (vocation.selectIndex > vocation.items.size() - 3) {
               vocation.selectIndex = (byte)(vocation.items.size() - 1);
            }
         } else if (phyle.selectIndex == 2) {
            if (vocation.selectIndex > vocation.items.size() - 3) {
               vocation.selectIndex = (byte)(vocation.items.size() - 2);
            }
         } else if (phyle.selectIndex == 1) {
            if (vocation.selectIndex > vocation.items.size() - 2) {
               vocation.selectIndex = (byte)(vocation.items.size() - 2);
            }
         } else if (phyle.selectIndex == 3) {
            if (vocation.selectIndex > 3) {
               vocation.selectIndex = (byte)(vocation.items.size() - 1);
            } else if (vocation.selectIndex > 1) {
               vocation.selectIndex = 3;
            } else if (vocation.selectIndex > vocation.items.size() - 1) {
               vocation.selectIndex = (byte)(vocation.items.size() - 1);
            }
         }

         MainCanvas.m_bVocation = (byte)(vocation.selectIndex + 1);
      }

   }

   public boolean increaseIndex() {
      ++this.selectIndex;
      this.isEndUp = false;
      if (this.selectIndex > this.items.size() - 1) {
         this.isEndDown = true;
         this.selectIndex = (byte)(this.items.size() - 1);
         return false;
      } else {
         this.isEndDown = false;
         return true;
      }
   }

   public boolean decreaseIndex() {
      --this.selectIndex;
      this.isEndDown = false;
      if (this.selectIndex < 0) {
         this.isEndUp = true;
         this.selectIndex = 0;
         return false;
      } else {
         this.isEndUp = false;
         return true;
      }
   }

   public boolean focusWidgetPointAction() {
      if (MainCanvas.SUPPORT_POINTER) {
         boolean oldSelect;
         int size;
         int i;
         short pointx;
         short pointy;
         short x;
         short y;
         short w;
         short h;
         byte oldSelect;
         switch(super.type) {
         case 0:
            oldSelect = true;
            size = this.items.size();
            if (!this.isHorizontal) {
               if (MainCanvas.isPointInRect(super.positionX, super.positionY, super.width, super.positionY + this.getBetweenRadioHeight(size))) {
                  oldSelect = this.selectIndex;

                  for(i = 0; i < size; ++i) {
                     pointx = MainCanvas.pointerX;
                     pointy = MainCanvas.pointerY;
                     x = super.positionX;
                     y = super.positionY;
                     w = super.width;
                     h = this.getBetweenRadioHeight(i + 1);
                     if (pointx >= x && pointx <= x + w && pointy >= y && pointy <= y + h) {
                        this.selectIndex = (byte)i;
                        this.setSureIndex(this.selectIndex);
                        break;
                     }
                  }

                  if (oldSelect == this.selectIndex) {
                     MainCanvas.keySimPressed(131072);
                  }

                  return true;
               }
            } else if (MainCanvas.isPointInRect(super.positionX, super.positionY, super.positionX + this.getBetweenWidth(size), super.height)) {
               oldSelect = this.selectIndex;

               for(i = 0; i < size; ++i) {
                  pointx = MainCanvas.pointerX;
                  pointy = MainCanvas.pointerY;
                  x = super.positionX;
                  y = super.positionY;
                  w = this.getBetweenWidth(i + 1);
                  h = super.height;
                  if (pointx >= x && pointx <= x + w && pointy >= y && pointy <= y + h) {
                     this.selectIndex = (byte)i;
                     this.setSureIndex(this.selectIndex);
                     break;
                  }
               }

               if (oldSelect == this.selectIndex) {
                  MainCanvas.keySimPressed(131072);
               }

               return true;
            }
            break;
         case 1:
            int startX = super.positionX;
            if (this.text != null && !this.text.equals("")) {
               startX += Util.getStrLen(this.text) + 8;
            }

            if (MainCanvas.isPointInRect(startX, super.positionY, MainCanvas.ui_aImg.getWidth(), MainCanvas.ui_aImg.getHeight())) {
               MainCanvas.keySimPressed(16384);
               return true;
            }

            if (MainCanvas.isPointInRect(startX + MainCanvas.ui_aImg.getWidth() + 8 + Util.getStrLen((String)this.items.elementAt(this.selectIndex)), super.positionY, MainCanvas.ui_aImg.getWidth(), MainCanvas.ui_aImg.getHeight())) {
               MainCanvas.keySimPressed(32768);
               return true;
            }
            break;
         case 2:
            oldSelect = true;
            size = this.items.size();
            if (MainCanvas.isPointInRect(super.positionX, super.positionY, super.width, super.positionY + this.getBetweenRadioHeight(size))) {
               oldSelect = this.selectIndex;

               for(i = 0; i < size; ++i) {
                  pointx = MainCanvas.pointerX;
                  pointy = MainCanvas.pointerY;
                  x = super.positionX;
                  y = super.positionY;
                  w = super.width;
                  h = this.getBetweenRadioHeight(i + 1);
                  if (pointx >= x && pointx <= x + w && pointy >= y && pointy <= y + h) {
                     this.selectIndex = (byte)i;
                     this.isCheckBoxSelect[i] = !this.isCheckBoxSelect[i];
                     break;
                  }
               }

               if (oldSelect == this.selectIndex) {
                  MainCanvas.keySimPressed(131072);
               }

               return true;
            }
         }
      }

      return false;
   }
}

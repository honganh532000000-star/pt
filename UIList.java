import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public class UIList extends UIComponent {
   public static final byte LIST_NORMAL = 0;
   public static final byte LIST_TEAM = 1;
   public static final byte LIST_OPTION = 2;
   public static final byte LIST_SKILL_CANSTUDY = 3;
   public static final byte LIST_SKILL_NOTCAN_L = 4;
   public static final byte LIST_SKILL_NOTCAN_C = 5;
   public static final byte ITEM_OX = 6;
   public static final byte ITEM_OY = 3;
   private UIPanel panel;
   private Vector items;
   private int[] listIDs;
   public Vector goIDs = new Vector();
   private byte listRow;
   private byte showCol;
   private boolean isCenter = false;
   public byte selectedIndex;
   public static byte PCSelectedIndex;
   public static byte guideSelectedIndex;
   public static int npcTransformID;
   public static byte npcTransformPoint;
   public static int selectedListId = -1;
   public static long selectTimeStamp = -1L;
   public static short selectedObjX;
   public static short selectedObjY;
   public static final byte SERVER_TYPE_NONE = -1;
   public static final byte SERVER_TYPE_INCOMPLETION = 0;
   public static final byte SERVER_TYPE_COMPLETION = 1;
   public static final byte SERVER_TYPE_NPC_TASK = 2;
   public static final byte SERVER_TYPE_DIALOG = 3;
   public static final byte SERVER_TYPE_TEAM = 4;
   public static final byte SERVER_TYPE_MONICKER = 5;
   public static final byte SERVER_TYPE_MONICKER_DETAIL = 6;
   public static final byte SERVER_TYPE_TASK_GUIDE = 7;
   public static final byte SERVER_TYPE_NPC_TRANSFORM = 8;
   public static final byte SERVER_TYPE_TASK_SELF = 9;
   public static final byte SERVER_TYPE_TASK_RESET_AREA = 10;
   public static final byte SERVER_TYPE_TASK_RESET_MAP = 11;
   public static final byte SERVER_TYPE_TASK_RESET_DETAIL = 12;
   public static final byte SERVER_TYPE_TASK_ACCEPT_AREA = 13;
   public static final byte SERVER_TYPE_TASK_ACCEPT_MAP = 14;
   public static final byte SERVER_TYPE_TASK_ACCEPT_DETAIL = 15;
   public static final byte SERVER_TYPE_KING_NOTICE = 16;
   public static final byte SERVER_TYPE_RANK = 17;
   public static final byte SERVER_TYPE_HANG_SELF = 18;
   public static final byte SERVER_TYPE_AUCTION_ALL = 19;
   public static final byte SERVER_TYPE_AUCTION_WEAPON = 20;
   public static final byte SERVER_TYPE_AUCTION_ARMOR = 21;
   public static final byte SERVER_TYPE_AUCTION_MATERIAL = 22;
   public static final byte SERVER_TYPE_AUCTION_EXPEND = 23;
   public static final byte SERVER_TYPE_AUCTION_WEAPON_LEVEL = 24;
   public static final byte SERVER_TYPE_AUCTION_GEM = 25;
   public static final byte SERVER_TYPE_AUCTION_ARMOR_LEVEL = 26;
   public static final byte SERVER_TYPE_AUCTION_WEAPON_QUALITY = 27;
   public static final byte SERVER_TYPE_AUCTION_ARMOR_QUALITY = 28;
   public static final byte SERVER_TYPE_TASK_FAVORIE = 29;
   public static final byte SERVER_TYPE_FORG_ALL = 30;
   public static final byte SERVER_TYPE_FORG_LEVEL = 31;
   public static final byte SERVER_TYPE_FORG_LEARN_LEVEL = 32;
   public static final byte SERVER_TYPE_FORG_LEARN_TYPE = 33;
   public static final byte SERVER_TYPE_FORG_LEARN_WEAPON = 34;
   public static final byte SERVER_TYPE_FORG_TYPE = 35;
   public static final byte SERVER_TYPE_FORG_WEAPON = 36;
   public static final byte SERVER_TYPE_SMITH_LEVEL = 37;
   public static final byte SERVER_TYPE_SMITH_LEARN_LEVEL = 38;
   public static final byte SERVER_TYPE_SMITH_TYPE = 39;
   public static final byte SERVER_TYPE_SMITH_LEARN_TYPE = 40;
   public static final byte SERVER_TYPE_SMITH_ARMOR = 41;
   public static final byte SERVER_TYPE_SMITH_LEARN_ARMOR = 42;
   public static final byte SERVER_TYPE_LEATHER_LEVEL = 43;
   public static final byte SERVER_TYPE_LEATHER_LEARN_LEVEL = 44;
   public static final byte SERVER_TYPE_LEATHER_TYPE = 45;
   public static final byte SERVER_TYPE_LEATHER_LEARN_TYPE = 46;
   public static final byte SERVER_TYPE_LEATHER_GE = 47;
   public static final byte SERVER_TYPE_LEATHER_LEARN_GE = 48;
   public static final byte SERVER_TYPE_SEWING_LEVEL = 49;
   public static final byte SERVER_TYPE_SEWING_LEARN_LEVEL = 50;
   public static final byte SERVER_TYPE_SEWING_TYPE = 51;
   public static final byte SERVER_TYPE_SEWING_LEARN_TYPE = 52;
   public static final byte SERVER_TYPE_SEWING_SEW = 53;
   public static final byte SERVER_TYPE_SEWING_LEARN_SEW = 54;
   public static final byte SERVER_TYPE_ENCHANTING_LEVEL = 55;
   public static final byte SERVER_TYPE_ENCHANTING_LEARN_LEVEL = 56;
   public static final byte SERVER_TYPE_ENCHANTING_ENCHANT = 57;
   public static final byte SERVER_TYPE_ENCHANTING_LEARN_ENCHANT = 58;
   public static final byte SERVER_TYPE_RESOLVE_LEVEL = 59;
   public static final byte SERVER_TYPE_RESOLVE_LEARN_LEVEL = 60;
   public static final byte SERVER_TYPE_RESOLVE_ITEM = 61;
   public static final byte SERVER_TYPE_MINING_LEVEL = 62;
   public static final byte SERVER_TYPE_MINING_LEARN_LEVEL = 63;
   public static final byte SERVER_TYPE_MINING_MINE = 64;
   public static final byte SERVER_TYPE_MINING_LEARN_MINE = 65;
   public static final byte SERVER_TYPE_HERBALISM_LEVEL = 66;
   public static final byte SERVER_TYPE_HERBALISM_LEARN_LEVEL = 67;
   public static final byte SERVER_TYPE_HERBALISM_DRUG = 68;
   public static final byte SERVER_TYPE_HERBALISM_LEARN_DRUG = 69;
   public static final byte SERVER_TYPE_BOTIAN = 70;
   public static final byte SERVER_MONEY_EXCHANGE = 71;
   public static final byte SERVER_TYPE_SHOPPING = 72;
   public static final byte SERVER_TYPE_CLOTH_LEVEL = 74;
   public static final byte SERVER_TYPE_CLOTH_LEARN_LEVEL = 75;
   public static final byte SERVER_TYPE_CLOTH_DETAIL = 76;
   public static final byte SERVER_TYPE_CLOTH_LEARN_DETAIL = 77;
   public static final byte SERVER_TYPE_SKIN_LEVEL = 78;
   public static final byte SERVER_TYPE_SKIN_LEARN_LEVEL = 79;
   public static final byte SERVER_TYPE_SKIN_DETAIL = 80;
   public static final byte SERVER_TYPE_SKIN_LEARN_DETAIL = 81;
   public static final byte SERVER_TYPE_BOTIAN_OP1 = 82;
   public static final byte SERVER_TYPE_BING = 83;
   public static final byte SERVER_KEFU = 84;
   public static final byte SERVER_KEFU_HUIDA = 85;
   public static final byte SERVER_RECHARD = 86;
   public static final byte SERVER_ACT_GUIDE = 86;
   public static final byte SERVER_FARM_DYNAMIC = 87;
   public static final byte SERVER_FARM_HELP = 88;
   public static final byte SERVER_BATTLEFIELD_SUMMARIZE = 89;
   public byte serverType = -1;
   public static final int GUIDE_COLOR = 255;
   int startListX = 0;
   int startListY = 0;
   private boolean isDrawLine = true;
   private boolean isCanSelected = true;
   public static boolean canReceive = true;
   public static boolean canReturn = true;
   public static int[] keyValue = new int[0];
   private int replaceKey = -1;
   private int replaceKeyId = -1;
   public static final byte RANK_MONEY = 0;
   public static final byte RANK_EXP = 1;
   public static final byte RANK_HIT = 2;
   public static final byte RANK_CREDIT = 3;
   public static final byte RANK_CHARGE = 4;
   public static final byte RANK_LEVEL = 5;
   public static String auctionStr = "";
   public boolean isEndUp = false;
   public int startIndex = 0;
   public byte botianIndex = 0;
   private static final int TMP_UP_Y = 4;
   private static final int KEY_COMMAND_REMOVE = -1;
   private static final int KEY_COMMAND_NONE = -2;
   private static final int KEY_COMMAND_CHANGE = -3;
   private static final int KEY_COMMAND_CLEAR = -5;
   private static final int KEY_COMMAND_DEFAULT = -6;
   private static final int KEY_COMMAND_SET = -7;
   private static final int KEY_COMMAND_SPACE = -8;

   public UIList(short x, short y, short w, short h, byte type, UIForm form) {
      super(x, y, w, h, form);
      super.type = type;
      super.canFocus = true;
      super.haveInnerComponent = true;
      this.items = new Vector();
      this.panel = new UIPanel(x, y, w, h, (byte)1, form);
      this.startListX = super.positionX + 4 + 10 + 3 + 1 + 1;
      this.startListY = super.positionY + 5 + 1 + 1 + 1 + 2;
      this.selectedIndex = 0;
      this.setType(type);
   }

   public int[] getListID() {
      return this.listIDs;
   }

   public int getListIDByIndex(int index) {
      if (index >= 0 && index <= this.listIDs.length - 1) {
         return this.listIDs[index];
      } else {
         throw new IndexOutOfBoundsException("Vượt giới hạn");
      }
   }

   public void setListID(int[] listIDs) {
      this.listIDs = listIDs;
   }

   public void draw(Graphics g) {
      if (this.panel != null) {
         this.panel.draw(g);
      }

      if (this.panel.getPanelVScroll() != null) {
         this.panel.getPanelVScroll().draw(g);
      }

      switch(super.type) {
      case 0:
      case 3:
      case 4:
      case 5:
         this.drawNormalList(g);
         break;
      case 1:
         this.drawTeamList(g);
         break;
      case 2:
         this.drawOptionList(g);
      }

   }

   public Vector getItems() {
      return this.items;
   }

   public void setItemStr(int index, String itemStr) {
      if (index >= 0 && index < this.items.size()) {
         ((ListItem)this.items.elementAt(index)).setItemName(itemStr);
      } else {
         throw new IllegalArgumentException("Hướng dẫn không hợp pháp");
      }
   }

   public void addItem(ListItem item) {
      if (item != null) {
         this.items.addElement(item);
         if (this.getListHeight() > this.getListShowHeight()) {
            this.panel.getPanelVScroll();
         }
      }

   }

   public UIPanel getPanel() {
      return this.panel;
   }

   private void drawNormalList(Graphics g) {
      int i = this.startIndex;

      for(int n = this.items.size() < this.showCol ? this.items.size() : this.showCol + this.startIndex; i < n; ++i) {
         ListItem item = (ListItem)this.items.elementAt(i);
         short centerX;
         int centerX;
         if (this.isCanSelected && i == this.selectedIndex && this.isFocus()) {
            if (this.isDrawLine) {
               g.setColor(Cons.COLOR_FONT_1);
               g.fillRect(this.startListX + 1, this.startListY + (i + 1 - this.startIndex) * 1 + (i - this.startIndex) * this.getListItemHeight(), this.getListWidth(), this.getListItemHeight() - 2);
            } else {
               g.setColor(Cons.COLOR_FONT_1);
               if (this.isCenter) {
                  centerX = super.positionX;
                  if ((super.width - 30 - Util.getStrLen(item.getItemName())) / 2 > 0) {
                     centerX = centerX + (super.width - Util.getStrLen(item.getItemName())) / 2;
                  } else {
                     centerX = this.startListX + 6;
                  }

                  g.fillRect(centerX - 1, this.startListY + (i + 1 - this.startIndex) * 1 + (i - this.startIndex) * this.getListItemHeight() + 6 - 4 + 1, item.getItemNameWidth() + 1, this.getListItemHeight() + 1);
               } else {
                  g.fillRect(this.startListX + 4, this.startListY + (i + 1 - this.startIndex) * 1 + (i - this.startIndex) * this.getListItemHeight() + 6 - 4 + 1, item.getItemNameWidth() + 1, this.getListItemHeight() + 1);
               }
            }
         }

         if (this.isDrawLine) {
            if (this.isCenter) {
               centerX = super.positionX;
               if ((super.width - 30 - Util.getStrLen(item.getItemName())) / 2 > 0) {
                  centerX = centerX + (super.width - Util.getStrLen(item.getItemName())) / 2;
               } else {
                  centerX = this.startListX + 6;
               }

               item.draw(g, centerX, this.startListY + (i - this.startIndex) * 1 + (i - this.startIndex) * this.getListItemHeight() + 3, this.getListWidth(), this.getPanel().getPanelVScroll(), i == this.selectedIndex && this.isCanSelected && this.isFocus(), false);
            } else {
               item.draw(g, this.startListX + 6, this.startListY + (i - this.startIndex) * 1 + (i - this.startIndex) * this.getListItemHeight() + 3, this.getListWidth(), this.getPanel().getPanelVScroll(), i == this.selectedIndex && this.isCanSelected && this.isFocus(), false);
            }

            g.setColor(Cons.COLOR_BACKGROUND);
            g.drawLine(this.startListX, this.startListY + (i - this.startIndex) * 1 + (i - this.startIndex + 1) * this.getListItemHeight(), this.startListX + this.getListWidth(), this.startListY + (i - this.startIndex) * 1 + (i - this.startIndex + 1) * this.getListItemHeight());
         } else if (this.isCenter) {
            centerX = super.positionX;
            if ((super.width - 30 - Util.getStrLen(item.getItemName())) / 2 > 0) {
               centerX = centerX + (super.width - Util.getStrLen(item.getItemName())) / 2;
            } else {
               centerX = this.startListX + 6;
            }

            item.draw(g, centerX, this.startListY + (i - this.startIndex) * 1 + (i - this.startIndex) * this.getListItemHeight() + 6 - 4 + 2, this.getListWidth(), this.getPanel().getPanelVScroll(), i == this.selectedIndex && this.isCanSelected && this.isFocus(), false);
         } else {
            item.draw(g, this.startListX + 6, this.startListY + (i - this.startIndex) * 1 + (i - this.startIndex) * this.getListItemHeight() + 6 - 4 + 2, this.getListWidth(), this.getPanel().getPanelVScroll(), i == this.selectedIndex && this.isCanSelected && this.isFocus(), false);
         }
      }

   }

   private void drawTeamList(Graphics g) {
      int i = this.startIndex;

      for(int n = this.items.size() < this.showCol ? this.items.size() : this.showCol + this.startIndex; i < n; ++i) {
         ListItem item = (ListItem)this.items.elementAt(i);
         item.draw(g, this.startListX, this.startListY + (i - this.startIndex) * 1 + (i - this.startIndex) * ListItem.getTeamItemHeight(), this.getListWidth(), this.getPanel().getPanelVScroll(), i == this.selectedIndex, false);
      }

   }

   private void drawOptionList(Graphics g) {
      int i = this.startIndex;

      for(int n = this.items.size() < this.showCol ? this.items.size() : this.showCol + this.startIndex; i < n; ++i) {
         ListItem item = (ListItem)this.items.elementAt(i);
         item.draw(g, this.startListX, this.startListY + (i - this.startIndex) * 1 + (i - this.startIndex) * ListItem.getOptionItemHeight(), this.getListWidth(), this.getPanel().getPanelVScroll(), i == this.selectedIndex, i == UIForm.lockIndex);
      }

   }

   private short getListShowHeight() {
      return this.isDrawLine ? (short)(super.height - 24) : (short)(super.height - 24 - 12);
   }

   public byte getCanShowCol(byte itemType) {
      byte showCol = 0;

      for(short h = 0; h <= this.getListShowHeight(); ++showCol) {
         switch(super.type) {
         case 0:
         case 3:
         case 4:
         case 5:
            h += this.getListItemHeight();
            break;
         case 1:
            h += ListItem.getTeamItemHeight();
            break;
         case 2:
            h += ListItem.getOptionItemHeight();
         }
      }

      --showCol;
      return showCol;
   }

   private int getListHeight() {
      int listH = false;
      int listH = this.items.size() * this.getListItemHeight();
      return listH;
   }

   public short getListWidth() {
      short listWidth = false;
      short listWidth;
      if (this.getPanel().getPanelVScroll() != null) {
         listWidth = (short)(super.width - 38 - 10);
      } else {
         listWidth = (short)(super.width - 38);
      }

      return listWidth;
   }

   public void setPositionX(short positionX) {
      super.setPositionX(positionX);
      this.panel.setPositionX(positionX);
   }

   public void setPositionY(short positionY) {
      super.setPositionY(positionY);
      this.panel.setPositionY(positionY);
   }

   public void setWidth(short width) {
      super.setWidth(width);
      this.panel.setWidth(width);
   }

   public void setHeight(short height) {
      super.setHeight(height);
      this.panel.setHeight(height);
   }

   public byte getListRow() {
      return this.listRow;
   }

   public void setListRow(byte listRow) {
      this.listRow = listRow;
   }

   public boolean isCenter() {
      return this.isCenter;
   }

   public void setCenter(boolean isCenter) {
      this.isCenter = isCenter;
   }

   public byte getSelectedIndex() {
      return this.selectedIndex;
   }

   public void setSelectedIndex(byte selectedIndex) {
      this.selectedIndex = selectedIndex;
   }

   private void keyInKeyOption() {
      if (MainCanvas.curInputDownState != 0) {
         if (MainCanvas.curInputDownState >= 1 && MainCanvas.curInputDownState <= 65536) {
            boolean canChange = true;

            for(int i = 0; i < keyValue.length; ++i) {
               if (UIForm.lockIndex != i && (keyValue[i] & MainCanvas.curInputDownState) != 0) {
                  this.replaceKeyId = i;
                  this.replaceKey = keyValue[i];
                  canChange = false;
                  UITopForm.createLocalTopForm((byte)6, (String)"Phím vừa nhập đang mang công năng khác，đồng ý thay đổi?", "Có", "Không", -3, -1);
                  break;
               }
            }

            if (canChange) {
               ListItem item = (ListItem)this.items.elementAt(UIForm.lockIndex);
               keyValue[UIForm.lockIndex] = MainCanvas.curInputDownState;
               item.setKeyStr(item.getKeyStr(keyValue[UIForm.lockIndex]));
               UIForm.lockIndex = -1;
            }
         } else if (MainCanvas.curInputDownState == 262144) {
            UIForm.lockIndex = -1;
         } else {
            UITopForm.createLocalTopForm((byte)0, (String)"Không thể thiết lập phím mà các hạ nhập!", "Đồng Ý", "", -1, -2);
         }
      }

   }

   public final void keyInTopForm(int command) {
      ListItem item;
      int i;
      switch(command) {
      case -6:
         for(i = 0; i < this.items.size(); ++i) {
            item = (ListItem)this.items.elementAt(i);
            keyValue[i] = MainCanvas.DEFAULT_GAME_KEY[i];
            item.setKeyStr(item.getKeyStr(keyValue[i]));
         }

         UITopForm.removeCurTopForm();
         break;
      case -5:
         for(i = 0; i < this.items.size(); ++i) {
            item = (ListItem)this.items.elementAt(i);
            keyValue[i] = 0;
            item.setKeyStr("");
         }

         UITopForm.removeCurTopForm();
      case -4:
      case -2:
      default:
         break;
      case -3:
         ListItem item = (ListItem)this.items.elementAt(this.replaceKeyId);
         keyValue[this.replaceKeyId] = 0;
         this.replaceKeyId = -1;
         item.setKeyStr("");
         item = (ListItem)this.items.elementAt(UIForm.lockIndex);
         keyValue[UIForm.lockIndex] = this.replaceKey;
         item.setKeyStr(item.getKeyStr(keyValue[UIForm.lockIndex]));
         UIForm.lockIndex = -1;
         UITopForm.removeCurTopForm();
         break;
      case -1:
         UITopForm.removeCurTopForm();
      }

   }

   public void keyInAction() {
      if (UIForm.lockIndex != -1) {
         this.keyInKeyOption();
      } else if (super.isShowMenu) {
         if (MainCanvas.isInputDownOrHold(4096)) {
            this.getMenu().decreaseIndex();
         } else if (MainCanvas.isInputDownOrHold(8192)) {
            this.getMenu().increaseIndex();
         } else if (!MainCanvas.isInputDown(65536) && !MainCanvas.isInputDown(131072)) {
            if (MainCanvas.isInputDown(262144)) {
               super.isShowMenu = false;
               this.setMenu((UIMenu)null);
            }
         } else {
            this.keyInMenu();
         }
      } else {
         UITextArea area;
         UILabel label_1;
         UILabel label_2;
         UILabel label;
         ListItem item;
         UITitle menuBar;
         if (MainCanvas.isInputDownOrHold(4096)) {
            if (this.items.size() == 0) {
               return;
            }

            if (this.isCanSelected) {
               --this.selectedIndex;
               if (this.selectedIndex < 0) {
                  this.selectedIndex = 0;
                  this.isEndUp = true;
               } else {
                  Util.rollCounter = 0;
                  Util.waitCounter = 0;
                  this.isEndUp = false;
               }
            }

            if (MainCanvas.getState() == 16) {
               this.State_Seizing();
            } else if (MainCanvas.curForm.clientCommand == 2359297) {
               PCExchange.m_nRegister = this.selectedIndex;
               label = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
               label.setText(PCExchange.name[this.selectedIndex]);
               label.setPositionX((short)(MainCanvas.screenW - label.getWidth() >> 1));
               label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(7);
               label_1.setText(PCExchange.name_1[this.selectedIndex]);
               label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(8);
               label_2.setText(PCExchange.name_2[this.selectedIndex]);
            } else if (MainCanvas.curForm.clientCommand != 2424994 && MainCanvas.curForm.clientCommand != 2425072) {
               if (MainCanvas.curForm.clientCommand == 1703958) {
                  area = (UITextArea)MainCanvas.curForm.getComponents().elementAt(4);
                  area.setContent(PCMail.m_sBingIntro[this.selectedIndex]);
               } else if (MainCanvas.curForm.clientCommand == 1703969) {
                  area = (UITextArea)MainCanvas.curForm.getComponents().elementAt(6);
                  area.setContent(PCMail.m_sContent[this.selectedIndex]);
               }
            } else {
               this.Increment(this.selectedIndex);
            }

            if (MainCanvas.curForm.clientCommand == 1900548 || MainCanvas.curForm.clientCommand == 1900546) {
               this.auctionGoodsKey();
            }

            if (MainCanvas.curForm.clientCommand == 262153 && this.getItems().size() > 0) {
               this.npcTaskKey(((ListItem)this.getItems().elementAt(this.selectedIndex)).getTaskType());
            }

            if (MainCanvas.curForm.clientCommand == 852020) {
               item = (ListItem)this.getItems().elementAt(this.selectedIndex);
               menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
               if (item.m_bBattlefieldExamine == 4125) {
                  menuBar.setMenuText("", 0);
               } else {
                  menuBar.setMenuText("Thao tác", 0);
               }
            }

            if (this.getPanel().getPanelVScroll() == null) {
               return;
            }

            if (this.isCanSelected) {
               if (this.selectedIndex < this.getPanel().getPanelVScroll().showStartRow) {
                  --this.startIndex;
               }
            } else {
               --this.startIndex;
               if (this.startIndex < 0) {
                  this.startIndex = 0;
               }

               this.getPanel().getPanelVScroll().setShowStartRow((short)this.startIndex);
            }
         } else if (MainCanvas.isInputDownOrHold(8192)) {
            if (this.items.size() == 0) {
               return;
            }

            if (this.isCanSelected) {
               ++this.selectedIndex;
               this.isEndUp = false;
               if (this.selectedIndex > this.items.size() - 1) {
                  this.selectedIndex = (byte)(this.items.size() - 1);
               } else {
                  Util.rollCounter = 0;
                  Util.waitCounter = 0;
               }
            }

            if (MainCanvas.getState() == 16) {
               this.State_Seizing();
            } else if (MainCanvas.curForm.clientCommand == 2359297) {
               PCExchange.m_nRegister = this.selectedIndex;
               label = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
               label.setText(PCExchange.name[this.selectedIndex]);
               label.setPositionX((short)(MainCanvas.screenW - label.getWidth() >> 1));
               label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(7);
               label_1.setText(PCExchange.name_1[this.selectedIndex]);
               label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(8);
               label_2.setText(PCExchange.name_2[this.selectedIndex]);
            } else if (MainCanvas.curForm.clientCommand != 2424994 && MainCanvas.curForm.clientCommand != 2425072) {
               if (MainCanvas.curForm.clientCommand == 1703958) {
                  area = (UITextArea)MainCanvas.curForm.getComponents().elementAt(4);
                  area.setContent(PCMail.m_sBingIntro[this.selectedIndex]);
               } else if (MainCanvas.curForm.clientCommand == 1703969) {
                  area = (UITextArea)MainCanvas.curForm.getComponents().elementAt(6);
                  area.setContent(PCMail.m_sContent[this.selectedIndex]);
               }
            } else {
               this.Increment(this.selectedIndex);
            }

            if (MainCanvas.curForm.clientCommand == 1900548 || MainCanvas.curForm.clientCommand == 1900546) {
               this.auctionGoodsKey();
            }

            if (MainCanvas.curForm.clientCommand == 262153) {
               this.npcTaskKey(((ListItem)this.getItems().elementAt(this.selectedIndex)).getTaskType());
            }

            if (MainCanvas.curForm.clientCommand == 852020) {
               item = (ListItem)this.getItems().elementAt(this.selectedIndex);
               menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
               if (item.m_bBattlefieldExamine == 4125) {
                  menuBar.setMenuText("", 0);
               } else {
                  menuBar.setMenuText("Thao tác", 0);
               }
            }

            if (this.getPanel().getPanelVScroll() == null) {
               return;
            }

            if (this.isCanSelected) {
               if (this.selectedIndex >= this.getPanel().getPanelVScroll().showStartRow + this.getPanel().getPanelVScroll().canShowRowNum) {
                  ++this.startIndex;
               }
            } else {
               ++this.startIndex;
               if (this.startIndex > this.getPanel().getPanelVScroll().allRowNum - this.getPanel().getPanelVScroll().canShowRowNum) {
                  this.startIndex = this.getPanel().getPanelVScroll().allRowNum - this.getPanel().getPanelVScroll().canShowRowNum;
               }

               this.getPanel().getPanelVScroll().setShowStartRow((short)this.startIndex);
            }
         } else if (!MainCanvas.isInputDown(65536) && !MainCanvas.isInputDown(131072)) {
            if (MainCanvas.curForm.clientCommand == 1703969) {
               if (MainCanvas.isInputDown(2048)) {
                  area = (UITextArea)MainCanvas.curForm.getComponents().elementAt(6);
                  if (this.isFocus()) {
                     this.setFocus(false, MainCanvas.curForm);
                     area.setFocus(true, MainCanvas.curForm);
                     area.setTextAreaType((byte)2);
                     area.addUIScroll();
                  }
               }
            } else if (super.type == 2) {
               if (MainCanvas.isInputDown(2048)) {
                  UIForm.lockIndex = this.selectedIndex;
               } else if (MainCanvas.isInputDown(1024)) {
                  item = (ListItem)this.items.elementAt(this.selectedIndex);
                  keyValue[this.selectedIndex] = 0;
                  item.setKeyStr("");
               }
            }
         } else {
            if (this.serverType == 8) {
               item = (ListItem)this.getItems().elementAt(this.selectedIndex);
               npcTransformPoint = item.getTransformID();
               MainCanvas.ni.send(131076);
               return;
            }

            if (this.serverType == 10) {
               if (this.getItems().size() == 0) {
                  return;
               }

               PCTask.selectedSelfTaskID = this.listIDs[this.selectedIndex];
               MainCanvas.ni.send(262166);
               MainCanvas.backForms.addElement(MainCanvas.curForm);
               return;
            }

            if (this.serverType == 11) {
               if (this.getItems().size() == 0) {
                  return;
               }

               PCTask.selectedSelfTaskID = this.listIDs[this.selectedIndex];
               MainCanvas.ni.send(262167);
               MainCanvas.backForms.addElement(MainCanvas.curForm);
               return;
            }

            if (this.serverType == 13) {
               if (this.getItems().size() == 0) {
                  return;
               }

               PCTask.selectedSelfTaskID = this.listIDs[this.selectedIndex];
               MainCanvas.ni.send(262169);
               MainCanvas.backForms.addElement(MainCanvas.curForm);
               return;
            }

            if (this.serverType == 14) {
               if (this.getItems().size() == 0) {
                  return;
               }

               PCTask.selectedSelfTaskID = this.listIDs[this.selectedIndex];
               MainCanvas.ni.send(262170);
               MainCanvas.backForms.addElement(MainCanvas.curForm);
               return;
            }

            int cmd;
            if (this.serverType == 7) {
               PCTask.isReceiveToView = false;
               guideSelectedIndex = (byte)(this.selectedIndex + 1);
               item = (ListItem)this.items.elementAt(this.selectedIndex);
               selectedObjX = item.objX;
               selectedObjY = item.objY;
               if (MainCanvas.curForm.clientCommand == 655363) {
                  cmd = 262194;
               } else {
                  cmd = 262162;
               }

               MainCanvas.ni.send(cmd);
            } else if (this.serverType == 17) {
               item = (ListItem)this.items.elementAt(this.selectedIndex);
               PCRank.rankIndex = item.rankType;
               MainCanvas.ni.send(1507330);
               MainCanvas.backForms.addElement(MainCanvas.curForm);
            } else {
               UITitle titleBar;
               UIList list;
               int i;
               if (this.serverType == 19) {
                  switch(this.selectedIndex) {
                  case 0:
                     MainCanvas.backForms.addElement(MainCanvas.curForm);
                     PCAuction.subTypeIndex = 0;
                     PCAuction.aSubTypeIndex = -1;
                     PCAuction.bSubTypeIndex = -1;
                     PCAuction.cSubTypeIndex = -1;
                     PCAuction.dSubTypeIndex = -1;
                     PCAuction.resetPage();
                     MainCanvas.ni.send(1900546);
                     break;
                  case 1:
                     PCAuction.subTypeIndex = 1;
                     PCAuction.aSubTypeIndex = -1;
                     PCAuction.bSubTypeIndex = -1;
                     PCAuction.cSubTypeIndex = -1;
                     PCAuction.dSubTypeIndex = -1;
                     MainCanvas.backForms.addElement(MainCanvas.curForm);
                     MainCanvas.curForm = ParseUI.parseUI("/ui/ulist.ui");
                     MainCanvas.curForm.clientCommand = -1610612724;
                     titleBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
                     titleBar.setTitleText("Vũ khí");
                     menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                     menuBar.setMenuText("Đồng Ý", 0);
                     list = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
                     list.removeAll();

                     for(i = 0; i < Cons.AUCTION_WEAPON_LIST.length; ++i) {
                        list.addItem(new ListItem(Cons.AUCTION_WEAPON_LIST[i], (byte)0, (byte)0, list.getListWidth()));
                     }

                     list.serverType = 20;
                     addUIScroll(list, (byte)0);
                     break;
                  case 2:
                     PCAuction.subTypeIndex = 2;
                     PCAuction.aSubTypeIndex = -1;
                     PCAuction.bSubTypeIndex = -1;
                     PCAuction.cSubTypeIndex = -1;
                     PCAuction.dSubTypeIndex = -1;
                     MainCanvas.backForms.addElement(MainCanvas.curForm);
                     MainCanvas.curForm = ParseUI.parseUI("/ui/ulist.ui");
                     MainCanvas.curForm.clientCommand = -1610612723;
                     titleBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
                     titleBar.setTitleText("Phòng Cụ");
                     menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                     menuBar.setMenuText("Đồng Ý", 0);
                     list = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
                     list.removeAll();

                     for(i = 0; i < Cons.AUCTION_ARMOR_LIST.length; ++i) {
                        list.addItem(new ListItem(Cons.AUCTION_ARMOR_LIST[i], (byte)0, (byte)0, list.getListWidth()));
                     }

                     list.serverType = 21;
                     addUIScroll(list, (byte)0);
                     break;
                  case 3:
                     PCAuction.subTypeIndex = 3;
                     PCAuction.aSubTypeIndex = -1;
                     PCAuction.bSubTypeIndex = -1;
                     PCAuction.cSubTypeIndex = -1;
                     PCAuction.dSubTypeIndex = -1;
                     MainCanvas.backForms.addElement(MainCanvas.curForm);
                     MainCanvas.curForm = ParseUI.parseUI("/ui/ulist.ui");
                     MainCanvas.curForm.clientCommand = -1610612722;
                     titleBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
                     titleBar.setTitleText("Nguyên Liệu");
                     menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                     menuBar.setMenuText("Đồng Ý", 0);
                     list = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
                     list.removeAll();

                     for(i = 0; i < Cons.AUCTION_MATERIAL_LIST.length; ++i) {
                        list.addItem(new ListItem(Cons.AUCTION_MATERIAL_LIST[i], (byte)0, (byte)0, list.getListWidth()));
                     }

                     list.serverType = 22;
                     addUIScroll(list, (byte)0);
                     break;
                  case 4:
                     PCAuction.subTypeIndex = 4;
                     PCAuction.aSubTypeIndex = -1;
                     PCAuction.bSubTypeIndex = -1;
                     PCAuction.cSubTypeIndex = -1;
                     PCAuction.dSubTypeIndex = -1;
                     MainCanvas.backForms.addElement(MainCanvas.curForm);
                     MainCanvas.curForm = ParseUI.parseUI("/ui/ulist.ui");
                     MainCanvas.curForm.clientCommand = -1610612721;
                     titleBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
                     titleBar.setTitleText("Tiêu Hao");
                     menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                     menuBar.setMenuText("Đồng Ý", 0);
                     list = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
                     list.removeAll();

                     for(i = 0; i < Cons.AUCTION_EXPEND_LIST.length; ++i) {
                        list.addItem(new ListItem(Cons.AUCTION_EXPEND_LIST[i], (byte)0, (byte)0, list.getListWidth()));
                     }

                     list.serverType = 23;
                     addUIScroll(list, (byte)0);
                     break;
                  case 5:
                     MainCanvas.backForms.addElement(MainCanvas.curForm);
                     PCAuction.subTypeIndex = 5;
                     PCAuction.aSubTypeIndex = 0;
                     PCAuction.bSubTypeIndex = -1;
                     PCAuction.cSubTypeIndex = -1;
                     PCAuction.dSubTypeIndex = -1;
                     PCAuction.resetPage();
                     MainCanvas.ni.send(1900546);
                  }
               } else {
                  int i;
                  if (this.serverType != 20 && this.serverType != 21) {
                     if (this.serverType == 22) {
                        MainCanvas.backForms.addElement(MainCanvas.curForm);
                        PCAuction.aSubTypeIndex = this.selectedIndex;
                        PCAuction.bSubTypeIndex = -1;
                        PCAuction.cSubTypeIndex = -1;
                        PCAuction.dSubTypeIndex = -1;
                        if (this.selectedIndex == 0) {
                           PCAuction.resetPage();
                           MainCanvas.ni.send(1900546);
                        } else if (this.selectedIndex != 5) {
                           PCAuction.resetPage();
                           MainCanvas.ni.send(1900546);
                        } else {
                           MainCanvas.curForm = ParseUI.parseUI("/ui/ulist.ui");
                           MainCanvas.curForm.clientCommand = -1610612719;
                           titleBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
                           titleBar.setTitleText("Bảo thạch");
                           menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                           menuBar.setMenuText("Đồng Ý", 0);
                           list = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
                           list.removeAll();

                           for(i = 0; i < Cons.AUCTION_GEM_LIST.length; ++i) {
                              list.addItem(new ListItem(Cons.AUCTION_GEM_LIST[i], (byte)0, (byte)0, list.getListWidth()));
                           }

                           list.serverType = 25;
                           addUIScroll(list, (byte)0);
                        }
                     } else if (this.serverType == 23) {
                        MainCanvas.backForms.addElement(MainCanvas.curForm);
                        PCAuction.aSubTypeIndex = this.selectedIndex;
                        PCAuction.bSubTypeIndex = -1;
                        PCAuction.cSubTypeIndex = -1;
                        PCAuction.dSubTypeIndex = -1;
                        PCAuction.resetPage();
                        MainCanvas.ni.send(1900546);
                     } else if (this.serverType != 24 && this.serverType != 26) {
                        if (this.serverType != 27 && this.serverType != 28) {
                           if (this.serverType == 25) {
                              MainCanvas.backForms.addElement(MainCanvas.curForm);
                              PCAuction.dSubTypeIndex = this.selectedIndex;
                              PCAuction.resetPage();
                              MainCanvas.ni.send(1900546);
                           } else if (this.serverType != 31 && this.serverType != 37 && this.serverType != 43 && this.serverType != 49 && this.serverType != 55 && this.serverType != 62 && this.serverType != 66 && this.serverType != 74 && this.serverType != 78) {
                              if (this.serverType != 33 && this.serverType != 40 && this.serverType != 46 && this.serverType != 52) {
                                 if (this.serverType != 35 && this.serverType != 39 && this.serverType != 45 && this.serverType != 51) {
                                    if (this.serverType != 34 && this.serverType != 42 && this.serverType != 48 && this.serverType != 54 && this.serverType != 58) {
                                       if (this.serverType == 60) {
                                          UIMenu.formSaveForm();
                                          item = (ListItem)this.items.elementAt(this.selectedIndex);
                                          PCProduce.forgLearnLevelIndex = item.forgIndex;
                                          PCProduce.forgStr = item.getItemName();
                                          PCProduce.selectedLevelIndex = (byte)(this.selectedIndex + PCProduce.canListNum);
                                          MainCanvas.ni.send(2162747);
                                       } else if (this.serverType == 70) {
                                          UIMenu.formSaveForm();
                                          item = (ListItem)this.items.elementAt(this.selectedIndex);
                                          PCPackage.botianID = item.itemId;
                                          MainCanvas.ni.send(458771);
                                       } else if (this.serverType == 71) {
                                          item = (ListItem)this.items.elementAt(this.selectedIndex);
                                          UITopForm.MAXNUMBER = PCExchange.m_nMax[this.selectedIndex];
                                          UITopForm.createLocalTopForm((byte)1, "Các hạ muốn đổi bao nhiêu" + item.getItemName() + "?", "Đồng Ý", "Trở về", 2359298, -1, -1, -1);
                                          MainCanvas.curTopForm.setNumber(0);
                                       } else if (this.serverType == 72) {
                                          item = (ListItem)this.items.elementAt(this.selectedIndex);
                                          PCIncrement.m_bSelectedIndex[0] = this.selectedIndex;
                                          PCIncrement.m_sTitle = item.getItemName();
                                          MainCanvas.ni.send(2424994);
                                          UIMenu.formSaveForm();
                                       } else if (this.serverType == 86) {
                                          item = (ListItem)this.items.elementAt(this.selectedIndex);
                                          if (item.itemId == -100) {
                                             PCCharge.chargeStr = item.getItemName();
                                             MainCanvas.ni.send(2621445);
                                          } else {
                                             PCCharge.chargeId = item.itemId;
                                             int money = 0;
                                             if (PCCharge.chargeId == 8200) {
                                                money = 2000;
                                             } else if (PCCharge.chargeId == 8300) {
                                                money = 3000;
                                             } else if (PCCharge.chargeId == 8400) {
                                                money = 4000;
                                             } else if (PCCharge.chargeId == 8500) {
                                                money = 5000;
                                             } else if (PCCharge.chargeId == 8600) {
                                                money = 10000;
                                             } else if (PCCharge.chargeId == 8700 || PCCharge.chargeId == 8713) {
                                                money = 15000;
                                             }

                                             UITopForm.createLocalTopForm((byte)0, "Đồng ý nạp " + money + "?", "Đồng ý", "Đóng", -32, -1, -1, -1);
                                          }
                                       }
                                    } else {
                                       if (this.items.size() == 0) {
                                          return;
                                       }

                                       UIMenu.formSaveForm();
                                       item = (ListItem)this.items.elementAt(this.selectedIndex);
                                       PCProduce.forgItemID = item.itemId;
                                       MainCanvas.ni.send(2162695);
                                    }
                                 } else {
                                    if (this.items.size() == 0) {
                                       return;
                                    }

                                    UIMenu.formSaveForm();
                                    item = (ListItem)this.items.elementAt(this.selectedIndex);
                                    PCProduce.forgLearnTypeID = item.itemId;
                                    int cmd = false;
                                    if (this.serverType == 35) {
                                       MainCanvas.ni.send(2162699);
                                    } else if (this.serverType == 39) {
                                       MainCanvas.ni.send(2162706);
                                    } else if (this.serverType == 45) {
                                       MainCanvas.ni.send(2162717);
                                    } else if (this.serverType == 51) {
                                       MainCanvas.ni.send(2162728);
                                    }
                                 }
                              } else {
                                 if (this.items.size() == 0) {
                                    return;
                                 }

                                 UIMenu.formSaveForm();
                                 item = (ListItem)this.items.elementAt(this.selectedIndex);
                                 PCProduce.forgLearnTypeID = item.itemId;
                                 cmd = 0;
                                 if (this.serverType == 33) {
                                    cmd = 2162694;
                                 } else if (this.serverType == 40) {
                                    cmd = 2162707;
                                 } else if (this.serverType == 46) {
                                    cmd = 2162718;
                                 } else if (this.serverType == 52) {
                                    cmd = 2162729;
                                 }

                                 MainCanvas.ni.send(cmd);
                              }
                           } else {
                              if (this.items.size() == 0) {
                                 return;
                              }

                              UIMenu.formSaveForm();
                              item = (ListItem)this.items.elementAt(this.selectedIndex);
                              PCProduce.forgLearnLevelIndex = item.forgIndex;
                              PCProduce.forgStr = item.getItemName();
                              PCProduce.selectedLevelIndex = this.selectedIndex;
                              cmd = 0;
                              if (this.serverType == 31) {
                                 cmd = 2162698;
                              } else if (this.serverType == 37) {
                                 cmd = 2162704;
                              } else if (this.serverType == 43) {
                                 cmd = 2162715;
                              } else if (this.serverType == 49) {
                                 cmd = 2162726;
                              } else if (this.serverType == 55) {
                                 cmd = 2162737;
                              } else if (this.serverType == 62) {
                                 cmd = 2162758;
                              } else if (this.serverType == 66) {
                                 cmd = 2162765;
                              } else if (this.serverType == 74) {
                                 cmd = 2162772;
                              } else if (this.serverType == 78) {
                                 cmd = 2162779;
                              }

                              MainCanvas.ni.send(cmd);
                           }
                        } else {
                           MainCanvas.backForms.addElement(MainCanvas.curForm);
                           PCAuction.cSubTypeIndex = this.selectedIndex;
                           PCAuction.dSubTypeIndex = -1;
                           PCAuction.resetPage();
                           MainCanvas.ni.send(1900546);
                        }
                     } else {
                        MainCanvas.backForms.addElement(MainCanvas.curForm);
                        PCAuction.bSubTypeIndex = this.selectedIndex;
                        PCAuction.cSubTypeIndex = -1;
                        PCAuction.dSubTypeIndex = -1;
                        if (this.selectedIndex == 0) {
                           PCAuction.resetPage();
                           MainCanvas.ni.send(1900546);
                           return;
                        }

                        MainCanvas.curForm = ParseUI.parseUI("/ui/ulist.ui");
                        if (this.serverType == 24) {
                           MainCanvas.curForm.clientCommand = -1610612717;
                        } else if (this.serverType == 26) {
                           MainCanvas.curForm.clientCommand = -1610612716;
                        }

                        titleBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
                        if (this.serverType == 24) {
                           titleBar.setTitleText("Vũ khí");
                        } else if (this.serverType == 26) {
                           titleBar.setTitleText("Phòng Cụ");
                        }

                        menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                        menuBar.setMenuText("Đồng Ý", 0);
                        list = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
                        list.removeAll();
                        String[] qualityStrs = new String[Cons.AUCTION_QUALITY_LIST.length + 1];
                        String curItemStr = ((ListItem)this.items.elementAt(this.selectedIndex)).getItemName();
                        qualityStrs[0] = curItemStr + "Tất cả" + auctionStr;
                        System.arraycopy(Cons.AUCTION_QUALITY_LIST, 0, qualityStrs, 1, Cons.AUCTION_QUALITY_LIST.length);

                        for(i = 0; i < qualityStrs.length; ++i) {
                           list.addItem(new ListItem(qualityStrs[i], (byte)0, (byte)0, list.getListWidth()));
                        }

                        if (this.serverType == 24) {
                           list.serverType = 27;
                        } else if (this.serverType == 26) {
                           list.serverType = 28;
                        }

                        addUIScroll(list, (byte)0);
                     }
                  } else {
                     MainCanvas.backForms.addElement(MainCanvas.curForm);
                     PCAuction.aSubTypeIndex = this.selectedIndex;
                     PCAuction.bSubTypeIndex = -1;
                     PCAuction.cSubTypeIndex = -1;
                     PCAuction.dSubTypeIndex = -1;
                     if (this.selectedIndex == 0) {
                        PCAuction.resetPage();
                        MainCanvas.ni.send(1900546);
                        return;
                     }

                     String curItemStr = ((ListItem)this.items.elementAt(this.selectedIndex)).getItemName();
                     auctionStr = curItemStr;
                     MainCanvas.curForm = ParseUI.parseUI("/ui/ulist.ui");
                     if (this.serverType == 20) {
                        MainCanvas.curForm.clientCommand = -1610612720;
                     } else if (this.serverType == 21) {
                        MainCanvas.curForm.clientCommand = -1610612718;
                     }

                     menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
                     menuBar.setTitleText(curItemStr);
                     UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                     menuBar.setMenuText("Đồng Ý", 0);
                     UIList list = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
                     list.removeAll();
                     String[] weaponStrs = new String[]{"Tất cả đẳng cấp" + curItemStr, "Cấp 1-10", "Cấp 11-20", "Cấp 21-30", "Cấp 31-40", "Cấp 41-50", "Cấp 51-60", "Cấp 61-70"};

                     for(i = 0; i < weaponStrs.length; ++i) {
                        list.addItem(new ListItem(weaponStrs[i], (byte)0, (byte)0, list.getListWidth()));
                     }

                     if (this.serverType == 20) {
                        list.serverType = 24;
                     } else if (this.serverType == 21) {
                        list.serverType = 26;
                     }

                     addUIScroll(list, (byte)0);
                  }
               }
            }

            if (MainCanvas.curForm.clientCommand == 851974) {
               MainCanvas.backForms.addElement(MainCanvas.curForm);
            } else {
               if (MainCanvas.curForm.clientCommand == 720916) {
                  PCSelectedIndex = this.selectedIndex;
                  MainCanvas.ni.send(720918);
                  MainCanvas.quitUI();
                  return;
               }

               if (MainCanvas.curForm.clientCommand == 262193) {
                  if (this.getItems().size() != 0) {
                     if (UIChat.chatForm.textStr.length() + 4 >= 30) {
                        UITopForm.createLocalTopForm((byte)5, (String)"Ký tự vượt quá giới hạn!", "Xác nhận", "", -1, -2);
                     } else {
                        UIChat var10000 = UIChat.chatForm;
                        var10000.textStr = var10000.textStr + "[r" + (this.selectedIndex + 1) + "]";
                        UIChat.chatForm.advancedForm(false);
                     }
                  }

                  return;
               }

               if (MainCanvas.curForm.clientCommand == 2621441) {
                  item = (ListItem)this.items.elementAt(this.selectedIndex);
                  PCCharge.chargeId = item.itemId;
                  if (item.itemId == -100) {
                     MainCanvas.ni.send(2621444);
                  } else {
                     PCCharge.chargeStr = item.getItemName();
                     MainCanvas.ni.send(2621443);
                  }

                  UIMenu.formSaveForm();
                  return;
               }

               if (MainCanvas.curForm.clientCommand == 2621444) {
                  item = (ListItem)this.items.elementAt(this.selectedIndex);
                  PCCharge.otherChargeId = item.itemId;
                  PCCharge.chargeStr = item.getItemName();
                  MainCanvas.ni.send(2621445);
                  UIMenu.formSaveForm();
                  return;
               }

               if (MainCanvas.curForm.clientCommand == 2621442) {
                  item = (ListItem)this.items.elementAt(this.selectedIndex);
                  PCCharge.chargeId = item.itemId;
                  PCIncrement.sum = PCCharge.chargeId;
                  if (PCCharge.isPop) {
                     UITopForm.createLocalTopForm((byte)0, (String)UITopForm.mammothChargeTip, "Xác nhận", "Trở về", -260, -1);
                  } else {
                     PCIncrement.noteCharge();
                  }
               }
            }

            if (super.clientType == 130) {
               PCTask.answerIndex = this.selectedIndex;
               MainCanvas.ni.send(262192);
               return;
            }

            if (super.type == 0 && MainCanvas.curForm.clientCommand == 1900545) {
               this.AuctionKey();
               return;
            }

            if (super.type == 0 && MainCanvas.curForm.clientCommand == 3145738) {
               PCHang.m_bIncrementGenreIndex = this.selectedIndex;
               MainCanvas.ni.send(3145739);
               UIMenu.formSaveForm();
               return;
            }

            if (super.type == 0 && MainCanvas.curForm.clientCommand == 3145739) {
               PCHang.m_bIncrementIndex = this.selectedIndex;
               MainCanvas.ni.send(2425076);
               return;
            }

            this.addMenu();
         }

         if (this.getPanel().getPanelVScroll() != null) {
            this.getPanel().getPanelVScroll().keyInScroll(this.selectedIndex, this.isCanSelected);
         }
      }

      PCSelectedIndex = this.selectedIndex;
   }

   public void keyInMenu() {
      this.getMenu().saveForm();
      int cmd;
      ListItem item;
      if (super.type == 2) {
         cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
         switch(cmd) {
         case -8:
            item = (ListItem)this.items.elementAt(this.selectedIndex);
            keyValue[this.selectedIndex] = 0;
            item.setKeyStr("");
            break;
         case -7:
            UIForm.lockIndex = this.selectedIndex;
            break;
         case -6:
            UITopForm.createLocalTopForm((byte)6, (String)"Có muốn khôi phục đến phím thiết lập mặc định không?", "Có", "Không", -6, -1);
            break;
         case -5:
            UITopForm.createLocalTopForm((byte)6, (String)"Có muốn xóa hết tất cả phím thiết lập hiện tại không?", "Có", "Không", -5, -1);
         }

         super.isShowMenu = false;
      } else if (super.type == 3) {
         cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
         if (cmd == -1) {
            super.isShowMenu = false;
         } else {
            if (cmd == -1610612730) {
               intoSkillDetailUI(cmd, (short)this.getListIDByIndex(this.selectedIndex));
            } else if (cmd == 1572867) {
               MainCanvas.ni.send(cmd);
            }

            super.isShowMenu = false;
         }
      } else if (super.type == 4) {
         cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
         if (cmd == -1) {
            super.isShowMenu = false;
         } else {
            if (cmd == -1610612731) {
               this.intoUnCanStudySkillUI(cmd);
            }

            super.isShowMenu = false;
         }
      } else if (super.type == 5) {
         cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
         if (cmd == -1) {
            super.isShowMenu = false;
         } else {
            if (cmd == -1610612730) {
               intoSkillDetailUI(cmd, (short)this.getListIDByIndex(this.selectedIndex));
            }

            super.isShowMenu = false;
         }
      } else {
         ListItem item;
         int cmd;
         switch(MainCanvas.curForm.clientCommand) {
         case 1441793:
            cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
            if (cmd >= 0) {
               switch(cmd) {
               case 1441794:
               case 1441795:
               case 1441796:
               case 1441797:
                  MainCanvas.ni.send(cmd);
               }
            }

            super.isShowMenu = false;
            break;
         case 1835012:
            item = (ListItem)this.getItems().elementAt(this.selectedIndex);
            selectedListId = item.itemId;
            cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
            if (cmd >= 0) {
               switch(cmd) {
               case 1835016:
               case 1835017:
               case 1835020:
                  PCKing.secondSureTopform(cmd);
                  break;
               case 1835028:
               case 1835029:
                  MainCanvas.ni.send(cmd);
               }
            }

            super.isShowMenu = false;
            break;
         case 1835021:
            if (this.getItems().size() > 0) {
               item = (ListItem)this.getItems().elementAt(this.selectedIndex);
               selectedListId = item.itemId;
               selectTimeStamp = item.timeStamp;
            }

            cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
            if (cmd >= 0) {
               switch(cmd) {
               case 1835022:
               case 1835025:
                  MainCanvas.ni.send(cmd);
                  break;
               case 1835023:
                  PCKing.editAffiche(1835023, "", "");
                  break;
               case 1835024:
                  MainCanvas.ni.send(1835026);
               }
            }

            super.isShowMenu = false;
            break;
         case 1835030:
         case 1835031:
            if (this.getItems().size() > 0) {
               item = (ListItem)this.getItems().elementAt(this.selectedIndex);
               selectedListId = item.itemId;
            }

            cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
            if (cmd >= 0) {
               switch(cmd) {
               case 1835018:
               case 1835019:
                  MainCanvas.ni.send(cmd);
               }
            }

            super.isShowMenu = false;
            break;
         case 2490385:
            item = (ListItem)this.getItems().elementAt(this.selectedIndex);
            cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
            UITable.selectedPlayerId = item.playerID;
            if (cmd == -268435452) {
               UIChat.intoPrivateChannel(item.playerID, item.getItemName(), true);
            } else {
               if (cmd == 720897) {
                  SITeam.otherPlayerID = item.playerID;
               }

               if (cmd == 196638) {
                  GOManager.reportedPlayerID = item.playerID;
               }

               MainCanvas.ni.send(cmd);
            }

            super.isShowMenu = false;
            break;
         default:
            switch(this.serverType) {
            case 2:
            case 9:
            case 12:
            case 15:
            case 29:
               if (this.listIDs != null) {
                  selectedListId = this.listIDs[this.selectedIndex];
               }

               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               if (cmd == -1) {
                  super.isShowMenu = false;
               } else if (cmd != 262148 && cmd != 262155) {
                  MainCanvas.ni.send(cmd);
                  super.isShowMenu = false;
               } else {
                  UITopForm.createLocalTopForm((byte)0, (String)"Muốn bỏ nhiệm vụ này?", "Xác nhận", "Hủy", cmd, -1);
                  super.isShowMenu = false;
               }
               break;
            case 4:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               if (cmd != -1) {
                  item = (ListItem)this.getItems().elementAt(this.selectedIndex);
                  switch(cmd) {
                  case -1610612726:
                     UIChat.intoPrivateChannel(item.teammateId, item.teammateName, true);
                     break;
                  case 589831:
                  case 589835:
                     UITable.selectedPlayerId = item.teammateId;
                     MainCanvas.ni.send(cmd);
                     break;
                  case 720899:
                  case 720900:
                  case 720901:
                  case 720902:
                  case 720908:
                  case 720910:
                     if (SITeam.teamState == 2 && MainCanvas.curFormVector.size() > 0) {
                        MainCanvas.stayFormId = ((UIForm)MainCanvas.curFormVector.elementAt(0)).foldedIndex;
                     }

                     SITeam.otherPlayerID = item.teammateId;
                     MainCanvas.ni.send(cmd);
                     break;
                  case 720919:
                  case 720920:
                     MainCanvas.ni.send(cmd);
                  }
               }

               super.isShowMenu = false;
               break;
            case 5:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case 393218:
                  MainCanvas.ni.send(cmd);
                  break;
               case 393227:
                  UIForm.backUIFormAction();
                  MainCanvas.ni.send(cmd);
               }

               super.isShowMenu = false;
               break;
            case 18:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               item = (ListItem)this.items.elementAt(this.selectedIndex);
               PCAuction.auctionID = item.auctionID;
               PCAuction.maxPrice = item.stuffMaxPrice;
               PCAuction.surePrice = item.stuffSurePrice;
               if (cmd == 1) {
                  if (PCAuction.surePrice < PCAuction.playerMoney) {
                     UITopForm.MAXNUMBER = PCAuction.surePrice;
                  } else {
                     UITopForm.MAXNUMBER = PCAuction.playerMoney;
                  }

                  UITopForm.createLocalTopForm((byte)13, (String[])(new String[]{"Giá cao nhất hiện nay:" + PCAuction.maxPrice, "Giá duy nhất：" + PCAuction.surePrice, "Xin nhập giá Các hạ muốn"}), "Đồng Ý", "Hủy", PCAuction.isBuy ? 1900553 : 1900555, -1);
                  super.isShowMenu = false;
               } else {
                  MainCanvas.ni.send(cmd);
                  super.isShowMenu = false;
               }
               break;
            case 32:
            case 38:
            case 44:
            case 50:
            case 56:
            case 63:
            case 67:
            case 75:
            case 79:
               item = (ListItem)this.items.elementAt(this.selectedIndex);
               PCProduce.forgLearnLevelIndex = item.forgIndex;
               PCProduce.forgStr = item.getItemName();
               PCProduce.selectedLevelIndex = (byte)(this.selectedIndex + PCProduce.canListNum);
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               MainCanvas.ni.send(cmd);
               super.isShowMenu = false;
               break;
            case 36:
            case 41:
            case 47:
            case 53:
            case 57:
            case 61:
               item = (ListItem)this.items.elementAt(this.selectedIndex);
               PCProduce.forgItemID = item.itemId;
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               MainCanvas.ni.send(cmd);
               super.isShowMenu = false;
               break;
            case 59:
               item = (ListItem)this.items.elementAt(this.selectedIndex);
               PCProduce.forgLearnLevelIndex = item.forgIndex;
               PCProduce.forgStr = item.getItemName();
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               MainCanvas.ni.send(cmd);
               super.isShowMenu = false;
               break;
            case 82:
               item = (ListItem)this.items.elementAt(this.selectedIndex);
               PCPackage.botianID = item.itemId;
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               MainCanvas.ni.send(cmd);
               super.isShowMenu = false;
               break;
            case 86:
               item = (ListItem)this.items.elementAt(this.selectedIndex);
               PCMovement.guideID = item.itemId;
               if (PCMovement.guideID >= 0) {
                  selectedObjX = item.objX;
                  selectedObjY = item.objY;
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  MainCanvas.ni.send(cmd);
               }

               super.isShowMenu = false;
               break;
            case 87:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               item = (ListItem)this.items.elementAt(this.selectedIndex);
               PCFarm.m_nPlayerIndex = item.getCharacterIndex();
               PCFarm.m_bMenuIndex = (Byte)PCFarm.menuvector.elementAt(this.getMenu().getIndex());
               MainCanvas.ni.send(cmd);
               super.isShowMenu = false;
               break;
            case 89:
               item = (ListItem)this.items.elementAt(this.selectedIndex);
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case -1610612633:
                  UITopForm.createLocalTopForm((byte)0, (String)"Có muốn thoát chiến trường? Sau khi cưỡng chế thoát ra sẽ phải chờ 1 chút mới đăng nhập lại được", "Xác nhận", "Hủy", 852001, -1);
                  break;
               case 196642:
                  GOManager.m_bPlayersortNPC = true;
                  GOManager.m_bBattlefieldMenuId = this.getMenu().getIndex();
                  break;
               case 852021:
               case 852023:
                  GOManager.m_sBattlefieldIndex = item.m_bBattlefieldGenre;
               }

               MainCanvas.ni.send(cmd);
               super.isShowMenu = false;
            }
         }
      }

   }

   public void addMenu() {
      UIMenu menu = new UIMenu((byte)0);
      String[] cmdStrs;
      int[] cmds;
      int[] cmds;
      if (super.type == 2) {
         cmdStrs = new String[]{"Thiết Lập", "Xóa", "Xóa hết tất cả", "Khôi phục mặc định"};
         cmds = new int[]{-7, -8, -5, -6};
         cmds = new int[]{3, 3, 3, 3};
         menu.addMenu(cmdStrs, cmds, cmds);
      } else {
         int[] inns;
         int id;
         String[] tmpMenuStr;
         if (super.type == 3) {
            id = this.getItems().size();
            if (id != 0) {
               tmpMenuStr = new String[]{"Học tập", "Tra xem"};
               cmds = new int[]{1572867, -1610612730};
               inns = new int[]{3, 2};
               menu.addMenu(tmpMenuStr, cmds, inns);
            }
         } else if (super.type == 4) {
            cmdStrs = new String[]{"Tra xem"};
            cmds = new int[]{-1610612731};
            cmds = new int[]{2};
            menu.addMenu(cmdStrs, cmds, cmds);
         } else if (super.type == 5) {
            id = this.getItems().size();
            if (id != 0) {
               tmpMenuStr = new String[]{"Tra xem"};
               cmds = new int[]{-1610612730};
               inns = new int[]{2};
               menu.addMenu(tmpMenuStr, cmds, inns);
            }
         } else {
            ListItem item;
            switch(MainCanvas.curForm.clientCommand) {
            case 1441793:
               if (this.getItems().size() == 0) {
                  return;
               }

               ListItem var10000 = (ListItem)this.getItems().elementAt(this.selectedIndex);
               tmpMenuStr = (String[])null;
               cmds = (int[])null;
               inns = (int[])null;
               switch(this.selectedIndex) {
               case 0:
                  tmpMenuStr = new String[]{"Kết làm ý trung nhân"};
                  cmds = new int[]{1441794};
                  inns = new int[]{3};
                  break;
               case 1:
                  tmpMenuStr = new String[]{"Đăng ký kết hôn"};
                  cmds = new int[]{1441795};
                  inns = new int[]{3};
                  break;
               case 2:
                  tmpMenuStr = new String[]{"Ly hôn", "Cưỡng chế ly hôn"};
                  cmds = new int[]{1441796, 1441797};
                  inns = new int[]{3, 3};
               }

               menu.addMenu(tmpMenuStr, cmds, inns);
               break;
            case 1638420:
               if (this.getItems().size() > 0) {
                  item = (ListItem)this.getItems().elementAt(this.selectedIndex);
                  PCClan.rightType = (byte)item.itemId;
                  PCClan.rightStr = item.getItemName();
               }

               UIMenu.formSaveForm();
               MainCanvas.ni.send(1638421);
               break;
            case 1835012:
               cmdStrs = new String[]{"Xem  chi tiết", "Xem thành viên"};
               cmds = new int[]{1835029, 1835028};
               cmds = new int[]{2, 2};
               ListItem item = (ListItem)this.getItems().elementAt(this.selectedIndex);
               String[] tmpMenus;
               int[] tmpCmds;
               int[] tmpInns;
               if (Player.getInstance().getClanId() == item.itemId) {
                  if (Player.getInstance().getKingRight() == PCKing.KING_RIGHT_HERDER) {
                     tmpMenus = new String[]{"Giải tán chư hầu"};
                     tmpCmds = new int[]{1835016};
                     tmpInns = new int[1];
                     cmdStrs = Util.addArrays(cmdStrs, cmdStrs.length, tmpMenus);
                     cmds = Util.addArrays(cmds, cmds.length, tmpCmds);
                     cmds = Util.addArrays(cmds, cmds.length, tmpInns);
                  } else if (Player.getInstance().getClanRight() == PCClan.CLAN_RIGHT_HERDER) {
                     tmpMenus = new String[]{"Rời khỏi chư hầu"};
                     tmpCmds = new int[]{1835020};
                     tmpInns = new int[1];
                     cmdStrs = Util.addArrays(cmdStrs, cmdStrs.length, tmpMenus);
                     cmds = Util.addArrays(cmds, cmds.length, tmpCmds);
                     cmds = Util.addArrays(cmds, cmds.length, tmpInns);
                  }
               } else if (Player.getInstance().getKingRight() == PCKing.KING_RIGHT_HERDER || Player.getInstance().getKingRight() == PCKing.KING_RIGHT_VICE_HERDER) {
                  tmpMenus = new String[]{"Giải trừ Thị Tộc"};
                  tmpCmds = new int[]{1835017};
                  tmpInns = new int[]{3};
                  cmdStrs = Util.addArrays(cmdStrs, cmdStrs.length, tmpMenus);
                  cmds = Util.addArrays(cmds, cmds.length, tmpCmds);
                  cmds = Util.addArrays(cmds, cmds.length, tmpInns);
               }

               menu.addMenu(cmdStrs, cmds, cmds);
               break;
            case 1835021:
               cmdStrs = (String[])null;
               cmds = (int[])null;
               cmds = (int[])null;
               if (Player.getInstance().getKingRight() != PCKing.KING_RIGHT_HERDER && Player.getInstance().getKingRight() != PCKing.KING_RIGHT_VICE_HERDER) {
                  if (this.getItems().size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Tra xem thông báo"};
                  cmds = new int[]{1835022};
                  cmds = new int[]{2};
               } else if (this.getItems().size() == 0) {
                  cmdStrs = new String[]{"Thêm thông báo"};
                  cmds = new int[]{1835023};
                  cmds = new int[]{3};
               } else {
                  cmdStrs = new String[]{"Xem thông báo", "Thêm thông báo", "Sửa thông báo", "Xóa thông báo"};
                  cmds = new int[]{1835022, 1835023, 1835024, 1835025};
                  cmds = new int[]{2, 3, 3, 3};
               }

               menu.addMenu(cmdStrs, cmds, cmds);
               break;
            case 1835030:
               cmdStrs = new String[]{"Giao chức vụ"};
               cmds = new int[]{1835018};
               cmds = new int[]{3};
               menu.addMenu(cmdStrs, cmds, cmds);
               break;
            case 1835031:
               cmdStrs = new String[]{"Cách chức"};
               cmds = new int[]{1835019};
               cmds = new int[]{3};
               menu.addMenu(cmdStrs, cmds, cmds);
               break;
            case 2490385:
               if (this.getItems().size() == 0) {
                  return;
               }

               item = (ListItem)this.getItems().elementAt(this.selectedIndex);
               menu.addMenu("Xem", 2490403, 2);
               menu.addMenu("Thêm bạn bè", 589835, 3);
               menu.addMenu("Chặn người chơi", 589836, 3);
               if (item.isOL()) {
                  menu.addMenu("Chat riêng", -268435452, 2);
               }

               menu.addMenu("Báo cáo", 196638, 3);
               if (item.isOL() && SITeam.teamState == 0) {
                  menu.addMenu("Mời tổ đội", 720897, 3);
               }
               break;
            case 2490389:
               if (this.getItems().size() == 0) {
                  return;
               }

               PCMentor.freeIndex = this.selectedIndex;
               MainCanvas.ni.send(2490421);
               break;
            default:
               switch(this.serverType) {
               case 2:
                  if (this.getItems().size() == 0) {
                     return;
                  }

                  item = (ListItem)this.getItems().elementAt(this.selectedIndex);
                  tmpMenuStr = (String[])null;
                  cmds = (int[])null;
                  inns = (int[])null;
                  if (this.listIDs != null) {
                     selectedListId = this.listIDs[this.selectedIndex];
                  }

                  if (item.getTaskType() == 2) {
                     canReceive = false;
                  } else if (item.getTaskType() == 1) {
                     canReceive = true;
                     MainCanvas.ni.send(262154);
                     UIMenu.formSaveForm();
                  } else if (item.getTaskType() == 4) {
                     canReturn = false;
                     MainCanvas.ni.send(262157);
                     MainCanvas.backForms.addElement(MainCanvas.curForm);
                  } else if (item.getTaskType() == 3) {
                     canReturn = true;
                     MainCanvas.ni.send(262157);
                     UIMenu.formSaveCommand();
                  }

                  if (tmpMenuStr != null) {
                     menu.addMenu(tmpMenuStr, cmds, inns);
                  }
                  break;
               case 3:
                  Util.m_nTalkId = this.selectedIndex;
                  MainCanvas.ni.send(851975);
                  break;
               case 4:
                  if (this.getItems().size() == 0) {
                     return;
                  }

                  item = (ListItem)this.getItems().elementAt(this.selectedIndex);
                  tmpMenuStr = (String[])null;
                  cmds = (int[])null;
                  inns = (int[])null;
                  if (SITeam.teamState == 0) {
                     MainCanvas.quitUI();
                     UITopForm.createLocalTopForm((byte)0, (String)"Đội của Các hạ đã giải tán!", Cons.C_STR[2], "", -1, -2);
                  } else if (Player.getInstance().getID() == SITeam.UIHeaderID) {
                     if (Player.getInstance().getID() == item.teammateId) {
                        switch(SITeam.teamState) {
                        case 1:
                           tmpMenuStr = new String[]{"Hình thức phân bố", "Độ khó phụ bản", "Tạo Quân Đoàn", "Thiết lập lại phụ bản", "Giải tán đội ngũ", "Rời khỏi đội ngũ"};
                           cmds = new int[]{720910, 720920, 720908, 720919, 720900, 720899};
                           inns = new int[]{2, Map.m_bCopyEnret ? 3 : 2, 3, 3, 3, 3};
                           break;
                        case 2:
                           tmpMenuStr = new String[]{"Hình thức phân bố", "Độ khó phụ bản", "Thiết lập lại phụ bản", "Giải tán quân đoàn", "Rời khỏi quân đoàn"};
                           cmds = new int[]{720910, 720920, 720919, 720900, 720899};
                           inns = new int[]{2, Map.m_bCopyEnret ? 3 : 2, 3, 3, 3};
                        }

                        menu.addMenu(tmpMenuStr, cmds, inns);
                     } else {
                        switch(SITeam.teamState) {
                        case 1:
                           tmpMenuStr = new String[]{"Xem thông tin", "Chat riêng", "Kết bạn bè", "Trao chức đội trưởng", "Giải trừ thành viên", "Rời khỏi đội ngũ"};
                           cmds = new int[]{589831, -1610612726, 589835, 720902, 720901, 720899};
                           inns = new int[]{2, 2, 3, 3, 3, 3};
                           break;
                        case 2:
                           tmpMenuStr = new String[]{"Tra xem thông tin", "Chat riêng", "Kết bạn bè", "Trao chức đội trưởng", "Giải trừ đội viên", "Rời khỏi đội ngũ"};
                           cmds = new int[]{589831, -1610612726, 589835, 720902, 720901, 720899};
                           inns = new int[]{2, 2, 2, 3, 3, 3};
                        }

                        menu.addMenu(tmpMenuStr, cmds, inns);
                     }
                  } else if (Player.getInstance().getID() == item.teammateId) {
                     switch(SITeam.teamState) {
                     case 1:
                        tmpMenuStr = new String[]{"Rời khỏi đội ngũ"};
                        cmds = new int[]{720899};
                        break;
                     case 2:
                        tmpMenuStr = new String[]{"Rời khỏi quân đoàn"};
                        cmds = new int[]{720899};
                     }

                     menu.addMenu(tmpMenuStr, cmds);
                  } else {
                     switch(SITeam.teamState) {
                     case 1:
                        tmpMenuStr = new String[]{"Tra xem thông tin", "Chat riêng", "Kết bạn bè", "Rời khỏi đội ngũ"};
                        cmds = new int[]{589831, -1610612726, 589835, 720899};
                        inns = new int[]{2, 2, 3, 3};
                        break;
                     case 2:
                        tmpMenuStr = new String[]{"Tra xem thông tin", "Chat riêng", "Kết bạn bè", "Rời khỏi quân đoàn"};
                        cmds = new int[]{589831, -1610612726, 589835, 720899};
                        inns = new int[]{2, 2, 3, 3};
                     }

                     menu.addMenu(tmpMenuStr, cmds, inns);
                  }
                  break;
               case 5:
                  if (this.getItems().size() == 0) {
                     return;
                  }

                  int[] cmds = new int[]{393227, 393218};
                  cmds = new int[]{3, 2};
                  String[] strs = new String[]{"Đồng Ý", "Tra xem"};
                  menu.addMenu(strs, cmds, cmds);
                  break;
               case 9:
                  if (this.getItems().size() == 0) {
                     return;
                  }

                  item = (ListItem)this.getItems().elementAt(this.selectedIndex);
                  menu.addMenu("Thông tin chi tiết", 262147, 2);
                  menu.addMenu("Xem thù lao", 262159, 2);
                  if (SITeam.teamState != 0) {
                     menu.addMenu("Cùng hưởng", 262151, 3);
                  }

                  menu.addMenu("Từ Bỏ", 262148, 3);
                  if (item.getTaskType2() == 1 && item.isCanRefresh()) {
                     menu.addMenu("Làm Mới", 262178, 2);
                  }

                  menu.addMenu("Cất giữ", 262184, 3);
                  break;
               case 12:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Thiết lập lại", "Thông tin chi tiết", "Tra xem thù lao"};
                  cmds = new int[]{262173, 262147, 262159};
                  cmds = new int[]{3, 2, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 15:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Chi tiết nhiệm vụ", "Thù lao nhiệm vụ"};
                  cmds = new int[]{262147, 262159};
                  cmds = new int[]{2, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 18:
                  if (this.items.size() == 0) {
                     return;
                  }

                  if (MainCanvas.curForm.clientCommand == 1900548) {
                     menu.addMenu("Tra xem thông tin", 1900552, 2);
                     menu.addMenu("Làm mới", 1900548, 3);
                  } else if (MainCanvas.curForm.clientCommand == 1900546) {
                     menu.addMenu("Tra xem thông tin", 1900552, 2);
                     menu.addMenu("Ra giá", 1, 3);
                     int id = false;
                     if (PCAuction.isBuy) {
                        id = 1900546;
                     } else {
                        id = 1900554;
                     }

                     menu.addMenu("Làm mới", id, 3);
                  }
                  break;
               case 29:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Thông tin chi tiết", "Tra xem thù lao", "Xóa"};
                  cmds = new int[]{262147, 262159, 262186};
                  cmds = new int[]{2, 2, 3};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 32:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Học tập", "Chi tiết"};
                  cmds = new int[]{2162693, 2162692};
                  cmds = new int[]{2, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 36:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Rèn", "Tra xem"};
                  cmds = new int[]{2162700, 2162695};
                  cmds = new int[]{3, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 38:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Học tập", "Chi tiết"};
                  cmds = new int[]{2162708, 2162705};
                  cmds = new int[]{2, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 41:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Chú giáp", "Tra xem"};
                  cmds = new int[]{2162711, 2162695};
                  cmds = new int[]{3, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 44:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Học tập", "Chi tiết"};
                  cmds = new int[]{2162719, 2162716};
                  cmds = new int[]{2, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 47:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Chế Cách", "Tra xem"};
                  cmds = new int[]{2162722, 2162695};
                  cmds = new int[]{3, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 50:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Học tập", "Chi tiết"};
                  cmds = new int[]{2162730, 2162727};
                  cmds = new int[]{2, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 53:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"May Mặc", "Tra xem"};
                  cmds = new int[]{2162733, 2162695};
                  cmds = new int[]{3, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 56:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Học tập", "Chi tiết"};
                  cmds = new int[]{2162739, 2162738};
                  cmds = new int[]{2, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 57:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Ngưng Nguyên", "Tra xem"};
                  cmds = new int[]{2162742, 2162695};
                  cmds = new int[]{3, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 59:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Phân giải", "Tra xem"};
                  cmds = new int[]{2162746, 2162753};
                  cmds = new int[]{2, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 61:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Phân giải", "Tra xem"};
                  cmds = new int[]{2162750, 2162752};
                  cmds = new int[]{3, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 63:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Học tập", "Chi tiết"};
                  cmds = new int[]{2162760, 2162759};
                  cmds = new int[]{2, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 67:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Học tập", "Chi tiết"};
                  cmds = new int[]{2162767, 2162766};
                  cmds = new int[]{2, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 75:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Học tập", "Chi tiết"};
                  cmds = new int[]{2162774, 2162773};
                  cmds = new int[]{2, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 79:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Học tập", "Chi tiết"};
                  cmds = new int[]{2162781, 2162780};
                  cmds = new int[]{2, 2};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 82:
                  if (this.items.size() == 0) {
                     return;
                  }

                  cmdStrs = new String[]{"Tra xem", "Dịch chuyển"};
                  cmds = new int[]{851978, 458771};
                  cmds = new int[]{2, 3};
                  menu.addMenu(cmdStrs, cmds, cmds);
                  break;
               case 83:
                  if (this.selectedIndex == 0) {
                     MainCanvas.ni.send(1703959);
                  } else if (this.selectedIndex == 1) {
                     MainCanvas.ni.send(1703960);
                  } else if (this.selectedIndex == 2) {
                     MainCanvas.ni.send(1703961);
                  }

                  UIMenu.formSaveForm();
                  break;
               case 84:
                  PCMail.m_bIndex = (byte)(this.selectedIndex + 1);
                  MainCanvas.ni.send(1703971);
                  UIMenu.formSaveForm();
                  break;
               case 85:
                  PCMail.m_bIndex = this.selectedIndex;
                  MainCanvas.ni.send(1703972);
                  break;
               case 86:
                  item = (ListItem)this.items.elementAt(this.selectedIndex);
                  if (item.itemId >= 0 && MainCanvas.getState() != 40) {
                     menu.addMenu("Di chuyển", 2293875, 3);
                  }
                  break;
               case 87:
                  this.FarmDynamicMenu();
                  break;
               case 88:
                  if (this.items.size() == 0) {
                     return;
                  }

                  item = (ListItem)this.items.elementAt(this.selectedIndex);
                  PCFarm.HelpID = (byte)item.itemId;
                  PCFarm.HelpCaption = item.getItemName();
                  MainCanvas.ni.send(2555977);
                  UIMenu.formSaveForm();
                  break;
               case 89:
                  if (this.items.size() == 0) {
                     return;
                  }

                  item = (ListItem)this.items.elementAt(this.selectedIndex);
                  if (item.m_bBattlefieldExamine == 4122) {
                     tmpMenuStr = new String[]{"Xem chiến báo", "Xếp hạng Thiên Nhân", "Xếp hạng Tu La", "Tổng xếp hạng", "Rời chiến trường"};
                     cmds = new int[]{852022, 196642, 196642, 196642, -1610612633};
                     inns = new int[]{2, 2, 2, 2, 3};
                     menu.addMenu(tmpMenuStr, cmds, inns);
                  } else if (item.m_bBattlefieldExamine == 4123) {
                     tmpMenuStr = new String[]{"Thoát xếp hàng"};
                     cmds = new int[]{852023};
                     inns = new int[]{3};
                     menu.addMenu(tmpMenuStr, cmds, inns);
                  } else if (item.m_bBattlefieldExamine == 4124) {
                     tmpMenuStr = new String[]{"Xếp hàng chiến trường"};
                     cmds = new int[]{852021};
                     inns = new int[]{3};
                     menu.addMenu(tmpMenuStr, cmds, inns);
                  } else {
                     item.getClass();
                  }
               }
            }
         }
      }

      if (menu.getItems().size() > 0) {
         this.setMenu(menu);
      }

      if (this.serverType != 3 && menu.getItems().size() > 0) {
         super.isShowMenu = true;
         this.getMenu().resetIndex();
      }

   }

   private void State_Seizing() {
      UITextArea area = (UITextArea)MainCanvas.curForm.getComponents().elementAt(4);
      area.setContent(Cons.SEIZING_EXPLAIN[this.selectedIndex]);
      MainCanvas.mc.m_bSeed_Seizing = this.selectedIndex;
   }

   public void removeAll() {
      if (this.items != null) {
         this.items.removeAllElements();
      }

   }

   public byte getShowCol() {
      return this.showCol;
   }

   public void setShowCol(byte showCol) {
      this.showCol = showCol;
   }

   public void setType(byte newType) {
      super.setType(newType);
      switch(super.type) {
      case 0:
      case 3:
      case 4:
      case 5:
         this.listRow = 1;
         this.showCol = this.getCanShowCol((byte)0);
         break;
      case 1:
         this.listRow = 1;
         this.showCol = this.getCanShowCol((byte)2);
         break;
      case 2:
         this.listRow = 1;
         this.showCol = this.getCanShowCol((byte)4);
      }

   }

   public boolean isDrawLine() {
      return this.isDrawLine;
   }

   public void setDrawLine(boolean isDrawLine) {
      this.isDrawLine = isDrawLine;
   }

   public boolean isCanSelected() {
      return this.isCanSelected;
   }

   public void setCanSelected(boolean isCanSelected) {
      this.isCanSelected = isCanSelected;
   }

   public boolean focusWidgetPointAction() {
      if (MainCanvas.SUPPORT_POINTER) {
         if (this.panel != null) {
            UIScroll us = this.panel.getPanelVScroll();
            if (us != null && us.focusWidgetPointAction()) {
               return true;
            }
         }

         int height = 0;
         switch(super.type) {
         case 0:
         case 3:
         case 4:
         case 5:
            height = this.getListItemHeight();
            break;
         case 1:
            height = ListItem.getTeamItemHeight();
            break;
         case 2:
            height = ListItem.getOptionItemHeight();
         }

         int i = this.startIndex;

         for(int n = this.items.size() < this.showCol ? this.items.size() : this.showCol + this.startIndex; i < n; ++i) {
            if (MainCanvas.isPointInRect(this.startListX, this.startListY + (i - this.startIndex) * 1 + (i - this.startIndex) * height, this.getListWidth(), height)) {
               if (this.selectedIndex == i) {
                  return false;
               }

               Util.rollCounter = 0;
               Util.waitCounter = 0;
               this.selectedIndex = (byte)i;
               if (MainCanvas.curForm.clientCommand == 262153) {
                  if (this.getItems().size() > 0) {
                     this.npcTaskKey(((ListItem)this.getItems().elementAt(this.selectedIndex)).getTaskType());
                  }
               } else if (MainCanvas.curForm.clientCommand == 1900548 || MainCanvas.curForm.clientCommand == 1900546) {
                  this.auctionGoodsKey();
               }

               if (MainCanvas.getState() == 16) {
                  this.State_Seizing();
               }

               return true;
            }
         }
      }

      return true;
   }

   public short getListItemHeight() {
      short height = false;
      short height;
      if (this.isDrawLine) {
         height = (short)(MainCanvas.CHARH + 6);
      } else {
         height = (short)MainCanvas.CHARH;
      }

      return height;
   }

   public static final void addUIScroll(UIList list, byte listItemType) {
      if (list.items.size() > list.getCanShowCol(listItemType) && list.getPanel().getPanelVScroll() == null) {
         list.getPanel().addPanelVScrollBar(false);
      } else if (list.items.size() <= list.getCanShowCol(listItemType) && list.getPanel().getPanelVScroll() != null) {
         list.removeUIScroll();
      }

      if (list.getPanel().getPanelVScroll() != null) {
         list.getPanel().getPanelVScroll().setShowStartRow((short)0);
         list.getPanel().getPanelVScroll().allRowNum = (short)list.items.size();
         list.getPanel().getPanelVScroll().canShowRowNum = list.getCanShowCol(listItemType);
      }

   }

   public void removeUIScroll() {
      this.getPanel().removePanelVScrollBar();
   }

   public final void intoUnCanStudySkillUI(int command) {
      MainCanvas.curForm = ParseUI.parseUI("/ui/unlearn.ui");
      MainCanvas.curForm.clientCommand = command;
      UIList unSkillList = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
      unSkillList.removeAll();
      unSkillList.setType((byte)5);
      int[] notcanStudySkill_IND = Player.getInstance().searchNotCanStudySkill((byte)this.getListIDByIndex(this.getSelectedIndex()));

      for(byte i = 0; i < notcanStudySkill_IND.length; ++i) {
         StringBuffer var10002 = new StringBuffer();
         Player.getInstance();
         ListItem tempSkill = new ListItem(var10002.append(Player.skillName[notcanStudySkill_IND[i]]).toString(), (byte)8, (byte)0, unSkillList.getListWidth());
         Player.getInstance();
         tempSkill.setSkillImgInd(Player.skillImgInd[notcanStudySkill_IND[i]]);
         unSkillList.addItem(tempSkill);
      }

      unSkillList.setListID(notcanStudySkill_IND);
      addUIScroll(unSkillList, (byte)0);
   }

   public static final void intoSkillDetailUI(int command, short skillIND) {
      MainCanvas.curForm = ParseUI.parseUI("/ui/skilldetail.ui");
      MainCanvas.curForm.clientCommand = command;
      UILabel skillDetailLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(3);
      skillDetailLabel.setType((byte)5);
      Player var10000 = Player.getInstance();
      Player.getInstance();
      var10000.setSelStudySkillID(Player.skillID[skillIND]);
      Player.getInstance();
      skillDetailLabel.setText(Player.skillName[skillIND]);
      UITextArea skillDetailTextarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(4);
      Player.getInstance();
      StringBuffer var8 = (new StringBuffer(String.valueOf(Player.skillCon[skillIND]))).append("\n\n").append("Kỹ năng học được cần thiết:").append(Player.needPlayerLevel[skillIND]).append("Cấp\n\n");
      String[] var10001 = Cons.skill_CONDITION;
      Player.getInstance();
      var8 = var8.append(var10001[Player.needPriceType[skillIND]]);
      Player.getInstance();
      String skilldescription = var8.append(Player.needPriceCount[skillIND]).toString();
      skillDetailTextarea.setContent(skilldescription);
      skillDetailTextarea.addUIScroll();
      UIPicture skillDetailP = (UIPicture)MainCanvas.curForm.getComponents().elementAt(5);
      Player.getInstance();
      short goodsID = Player.skillImgInd[skillIND];
      skillDetailP.quality = (byte)(goodsID / 1000 - 1);
      if (skillDetailP.quality == -1) {
         skillDetailP.quality = 1;
      }

      skillDetailP.isWpPic = true;
      short picID = (short)(goodsID % 1000);
      skillDetailP.wpIndex = picID;
      skillDetailP.setImg(MainCanvas.stuffMImg);
   }

   private void AuctionKey() {
      if (this.selectedIndex == 0) {
         PCAuction.isBuy = true;
         MainCanvas.backForms.addElement(MainCanvas.curForm);
         MainCanvas.curForm = ParseUI.parseUI("/ui/ulist.ui");
         MainCanvas.curForm.clientCommand = -1610612725;
         UITitle titleBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
         titleBar.setTitleText("Đấu giá");
         UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
         menuBar.setMenuText("Đồng Ý", 0);
         UIList list = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
         list.removeAll();

         for(int i = 0; i < Cons.AUCTION_ALL_LIST.length; ++i) {
            list.addItem(new ListItem(Cons.AUCTION_ALL_LIST[i], (byte)0, (byte)0, list.getListWidth()));
         }

         list.serverType = 19;
         addUIScroll(list, (byte)0);
      } else if (this.selectedIndex == 1) {
         MainCanvas.backForms.addElement(MainCanvas.curForm);
         MainCanvas.ni.send(1900547);
      } else if (this.selectedIndex == 2) {
         MainCanvas.backForms.addElement(MainCanvas.curForm);
         MainCanvas.ni.send(1900548);
      } else if (this.selectedIndex == 3) {
         final Form form = new Form("Tìm kiếm vật phẩm");
         final TextField tf = new TextField("Xin nhập tên vật phẩm:", "", 6, 0);
         form.append(tf);
         final Command okCmd = new Command("Tìm kiếm", 4, 0);
         final Command exitCmd = new Command("Trở về", 3, 1);
         form.addCommand(okCmd);
         form.addCommand(exitCmd);
         form.setCommandListener(new CommandListener() {
            public void commandAction(Command c, Displayable d) {
               if (c == okCmd) {
                  PCAuction.searchName = tf.getString();
                  if (PCAuction.searchName != null && !PCAuction.searchName.equals("")) {
                     PCAuction.isBuy = false;
                     PCAuction.resetPage();
                     MainCanvas.ni.send(1900554);
                     MainCanvas.mc.setFullScreenMode(true);
                     MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
                     MainCanvas.backForms.addElement(MainCanvas.curForm);
                  } else {
                     Alert a = new Alert(Cons.C_STR[9], "Không để trống tên vật phẩm", (Image)null, AlertType.ERROR);
                     MainCanvas.aMidlet.display.setCurrent(a, form);
                  }
               } else if (c == exitCmd) {
                  MainCanvas.mc.setFullScreenMode(true);
                  MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
               }

            }
         });
         MainCanvas.aMidlet.display.setCurrent(form);
      }

   }

   private void auctionGoodsKey() {
      ListItem curItem = (ListItem)this.getItems().elementAt(this.getSelectedIndex());
      UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(5);
      UILabel stuffLvLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(8);
      UITextField maxPriceTxt = (UITextField)MainCanvas.curForm.getComponents().elementAt(7);
      UITextField surePriceTxt = (UITextField)MainCanvas.curForm.getComponents().elementAt(10);
      UITextField leftTimeTxt = (UITextField)MainCanvas.curForm.getComponents().elementAt(12);
      pic.setQuality(curItem.stuffPicID);
      pic.wpIndex = (short)(curItem.stuffPicID % 1000);
      UIGrid.getStuffWordColor(curItem.stuffQuality);
      if (curItem.stuffMaxNum == 1) {
         pic.setShowNum(false);
      } else {
         pic.setShowNum(true, curItem.stuffNum);
      }

      stuffLvLabel.setNum1(curItem.stuffLevel);
      maxPriceTxt.setSb(new StringBuffer(String.valueOf(curItem.stuffMaxPrice)));
      surePriceTxt.setSb(new StringBuffer(String.valueOf(curItem.stuffSurePrice)));
      leftTimeTxt.setSb(new StringBuffer(curItem.stuffLeftTimeStr));
   }

   private void Increment(byte index) {
      UILabel label = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
      label.setNum1(PCIncrement.m_nCost[index]);
   }

   public void npcTaskKey(byte taskType) {
      UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
      String tmpStr = "";
      if (taskType == 1) {
         tmpStr = "Chọn lựa";
      } else if (taskType == 2) {
         tmpStr = "";
      } else if (taskType == 3) {
         tmpStr = "Chọn lựa";
      } else if (taskType == 4) {
         tmpStr = "Tra xem";
      }

      menuBar.setMenuText(tmpStr, 0);
   }

   private void FarmDynamicMenu() {
      PCFarm.menuvector = new Vector();

      for(int i = 0; i < PCFarm.MENUS.length; ++i) {
         if ((((ListItem)this.items.elementAt(this.selectedIndex)).getMenuIndex() & 1 << i) != 0) {
            PCFarm.menuvector.addElement(new Byte((byte)i));
         }

         int[] menuCommand = new int[PCFarm.menuvector.size()];
         int[] inns = new int[PCFarm.menuvector.size()];
         String[] str = new String[PCFarm.menuvector.size()];

         for(int j = 0; j < PCFarm.menuvector.size(); ++j) {
            menuCommand[j] = 2555966;
            inns[j] = PCFarm.FARM_DYNAMIC_MENU_MANIPULATE[(Byte)PCFarm.menuvector.elementAt(j)];
            str[j] = PCFarm.FARM_DYNAMIC_MENU[(Byte)PCFarm.menuvector.elementAt(j)];
         }

         UIMenu menu = new UIMenu((byte)0);
         menu.addMenu(str, menuCommand, inns);
         this.setMenu(menu);
         super.isShowMenu = true;
         this.getMenu().resetIndex();
      }

   }
}

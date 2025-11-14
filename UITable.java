import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class UITable extends UIComponent {
   private Vector items;
   private TableItem titleItem = null;
   private byte row;
   private UIScroll tableVScroll;
   private UIScroll tableHScroll;
   private byte allRowNum = 0;
   private byte allColNum = 0;
   private byte selectedIndex = -1;
   public boolean isEndUp = false;
   public static int selectedPlayerId = -1;
   public boolean m_bWhetherTime = false;
   public byte serverType = -1;
   public static final byte SERVER_TYPE_FRIEND_TABLE = 0;
   public static final byte SERVER_TYPE_BLACK_TABLE = 1;
   public static final byte SERVER_TYPE_SEARCH_TABLE = 2;
   public static final byte SERVER_TYPE_CREDIT_TABLE = 3;
   public static final byte SERVER_TYPE_NPC_TABLE = 4;
   public static final byte SERVER_TYPE_TALK_TABLE = 5;
   public static final byte SERVER_TYPE_MAIL_TABLE = 6;
   public static final byte SERVER_TYPE_CLAN_MEM_TABLE = 7;
   public static final byte SERVER_PERSONAL_ENEMY = 8;
   public static final byte SERVER_TYPE_TASK_ALL_GET = 10;
   public static final byte SERVER_TYPE_GEM_BESET = 11;
   public static final byte SERVER_TYPE_GEM_BESET_1 = 12;
   public static final byte SERVER_TYPE_GEM_UNCHAIN = 13;
   public static final byte SERVER_TYPE_ACT_TABLE = 14;
   public static final byte SERVER_TYPE_MENTOR_FIND_TABLE = 15;
   public static final byte SERVER_TYPE_FARM_FRIEND = 16;
   public static final byte SERVER_TYPE_FARM_PERSONAL_ENEMY = 17;
   public static final byte SERVER_TYPE_FARM__LARGESS_FRIEND = 18;
   public static final byte SERVER_BATTLEFIELD = 19;
   public static final byte SERVER_TYPE_CLAN_BATTLE = 20;
   private int startIndex = 0;
   private boolean isCanSelected = true;

   public UITable(short x, short y, short w, short h, byte row, TableItem titleItem, UIForm form) {
      super(x, y, w, h, form);
      super.width = (short)(MainCanvas.screenW - (super.positionX << 1));
      this.row = row;
      this.titleItem = titleItem;
      super.canFocus = true;
      if (titleItem != null) {
         this.selectedIndex = 0;
      }

      super.haveInnerComponent = this.selectedIndex != -1;
      this.items = new Vector();
   }

   public void draw(Graphics g) {
      int innerX1 = super.positionX;
      int innerY1 = super.positionY;
      int innerW1 = super.width;
      int innerH1 = super.height;
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
      if (this.titleItem != null) {
         g.setColor(Cons.COLOR_TABLE_TITLE_BG);
         g.fillRect(super.positionX + 3 + 1 + 1, super.positionY + 1 + 1 + 1, super.width - 10, this.titleItem.getTermHeight());
         this.titleItem.draw(g, false);
      }

      int n = this.items.size() < this.getCanShowCol() ? this.items.size() : this.getCanShowCol() + this.startIndex;

      int i;
      for(i = this.startIndex; i < n; ++i) {
         TableItem ti = (TableItem)this.items.elementAt(i);
         if (i == this.selectedIndex && this.isCanSelected) {
            g.setColor(Cons.COLOR_TABLE_SEL_ITEM_BG);
            g.fillRect(ti.getX(), ti.getY(), this.getTableShowWidth() - MainCanvas.ui_3Img.getWidth(), ti.getTermHeight());
            ti.draw(g, true);
         } else {
            ti.draw(g, false);
         }
      }

      if (this.titleItem != null) {
         g.setColor(Cons.COLOR_PANEL_BORDER_3);
         g.drawLine(super.positionX + 3 + 1 + 1, super.positionY + 1 + 1 + 1 + TableItem.getDefaultTableItemHeight() - 1, super.positionX + 3 + 1 + 1 + super.width - 10, super.positionY + 1 + 1 + 1 + TableItem.getDefaultTableItemHeight() - 1);
      }

      if (this.items.size() > 0) {
         i = 1;

         for(int len = ((TableItem)this.items.elementAt(0)).getTerms().size(); i < len; ++i) {
            g.setColor(Cons.COLOR_PANEL_BORDER_3);
            g.drawLine(super.positionX + (5 + ((TableItem)this.items.elementAt(0)).getCurWidth((byte)i) - 1) * MainCanvas.screenW / 176, super.positionY + 1 + 1 + 1, super.positionX + (5 + ((TableItem)this.items.elementAt(0)).getCurWidth((byte)i) - 1) * MainCanvas.screenW / 176, super.positionY + 1 + 1 + 1 + this.getTableShowHeight());
         }
      }

      if (this.tableVScroll != null) {
         this.tableVScroll.draw(g);
      }

   }

   public void keyInAction() {
      TableItem ti;
      int i;
      TableItem ti;
      UILabel ul;
      String gemname;
      String name;
      UILabel ul;
      UILabel ul;
      TableItem ti;
      String name;
      if (super.isShowMenu) {
         if (MainCanvas.isInputDownOrHold(4096)) {
            if (this.items.size() == 0 && this.serverType != 19 && MainCanvas.curForm.clientCommand != 196642) {
               return;
            }

            this.getMenu().decreaseIndex();
         } else if (MainCanvas.isInputDownOrHold(8192)) {
            if (this.items.size() == 0 && this.serverType != 19 && MainCanvas.curForm.clientCommand != 196642) {
               return;
            }

            this.getMenu().increaseIndex();
         } else if (!MainCanvas.isInputDown(65536) && !MainCanvas.isInputDown(131072)) {
            if (MainCanvas.isInputDown(262144)) {
               super.isShowMenu = false;
            }
         } else {
            this.getMenu().saveForm();
            int id;
            int cmd;
            switch(this.serverType) {
            case 0:
               i = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               if (this.items.size() == 0) {
                  if (i == 1) {
                     PCSocial.addform("Thêm bạn bè", "Xin nhập tên bạn bè：", 589833);
                  }

                  return;
               }

               id = ((TableItem)this.items.elementAt(this.selectedIndex)).playerID;
               switch(i) {
               case -1610612729:
                  int ID = ((TableItem)this.items.elementAt(this.selectedIndex)).playerID;
                  TableItem TI = (TableItem)this.items.elementAt(this.selectedIndex);
                  UILabel UI = (UILabel)TI.getTerms().elementAt(2);
                  PCMail.m_lFriendID = (long)ID;
                  PCMail.m_sSendMailValidity[0] = UI.getText();
                  UIForm.backUIFormAction();
                  UITextField textfield1 = (UITextField)MainCanvas.curForm.getComponents().elementAt(3);
                  textfield1.setLabel(PCMail.m_sSendMailValidity[0]);
                  break;
               case -1610612726:
                  ti = (TableItem)this.items.elementAt(this.selectedIndex);
                  ul = (UILabel)ti.getTerms().elementAt(2);
                  name = ul.getText();
                  UIChat.intoPrivateChannel(id, name, true);
                  break;
               case 1:
                  PCSocial.addform("Thêm bạn bè", "Xin nhập tên bạn bè:", 589833);
                  break;
               case 589827:
               case 589829:
                  selectedPlayerId = id;
                  MainCanvas.ni.send(i);
                  break;
               case 589831:
                  selectedPlayerId = id;
                  MainCanvas.ni.send(i);
                  break;
               case 589846:
                  MainCanvas.quitUI();
                  MainCanvas.mc.leftRightMenu();
                  selectedPlayerId = id;
                  MainCanvas.ni.send(i);
                  break;
               case 720897:
                  SITeam.otherPlayerID = id;
                  MainCanvas.ni.send(i);
               }

               super.isShowMenu = false;
               break;
            case 1:
               i = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               if (i == 1) {
                  PCSocial.addform("Thêm ds", "Xin nhập tên người chơi：", 589847);
               } else {
                  selectedPlayerId = ((TableItem)this.items.elementAt(this.selectedIndex)).playerID;
                  MainCanvas.ni.send(i);
               }

               super.isShowMenu = false;
               break;
            case 2:
               selectedPlayerId = ((TableItem)this.items.elementAt(this.selectedIndex)).playerID;
               i = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(i) {
               case -1610612726:
                  ti = (TableItem)this.items.elementAt(this.selectedIndex);
                  UIChat.intoPrivateChannel(selectedPlayerId, ((UILabel)ti.getTerms().elementAt(2)).getText(), true);
                  break;
               case 720897:
                  SITeam.otherPlayerID = selectedPlayerId;
                  MainCanvas.ni.send(i);
                  break;
               case 1638405:
                  PCClan.otherPlayerID = selectedPlayerId;
                  MainCanvas.ni.send(i);
                  break;
               case 1835013:
                  PCKing.otherPlayerID = selectedPlayerId;
                  MainCanvas.ni.send(i);
                  break;
               default:
                  MainCanvas.ni.send(i);
               }

               super.isShowMenu = false;
            case 3:
            case 4:
            case 9:
            case 14:
            case 18:
            default:
               break;
            case 5:
               ti = (TableItem)this.items.elementAt(this.selectedIndex);
               ul = (UILabel)ti.getTerms().elementAt(2);
               name = ul.getText();
               UIForm.backUIFormAction();
               UIChat.intoPrivateChannel(((TableItem)this.items.elementAt(this.selectedIndex)).playerID, name, false);
               break;
            case 6:
               PCMail.beforeMailIndex = this.selectedIndex;
               PCMail.selectedMailID = ((TableItem)this.items.elementAt(this.selectedIndex)).mailID;
               i = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               if (i != -1) {
                  if (i == 0) {
                     super.isShowMenu = false;
                     UITopForm.createLocalTopForm((byte)0, (String)"Các hạ muốn xóa hết thư?", "Phải", "Không", -250, -1);
                  } else {
                     MainCanvas.ni.send(i);
                     super.isShowMenu = false;
                  }
               }
               break;
            case 7:
               i = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               id = ((TableItem)this.items.elementAt(this.selectedIndex)).playerID;
               if (i != -1) {
                  ti = (TableItem)this.items.elementAt(this.selectedIndex);
                  switch(i) {
                  case -1610612726:
                     UIChat.intoPrivateChannel(id, ((UILabel)ti.getTerms().elementAt(2)).getText(), true);
                     break;
                  case 589831:
                  case 1638444:
                     selectedPlayerId = id;
                     MainCanvas.ni.send(i);
                     break;
                  case 589835:
                     selectedPlayerId = id;
                     MainCanvas.ni.send(i);
                     break;
                  case 1638408:
                  case 1638413:
                  case 1638416:
                     PCClan.secondSureTopform(i);
                     break;
                  case 1638409:
                  case 1638410:
                     PCClan.otherPlayerID = id;
                     PCClan.secondSureTopform(i);
                     break;
                  case 1638411:
                  case 1638412:
                  case 1638415:
                     PCClan.otherPlayerID = id;
                     MainCanvas.ni.send(i);
                     break;
                  case 1835030:
                     PCKing.otherPlayerID = id;
                     MainCanvas.ni.send(i);
                     break;
                  case 1835035:
                     PCKing.otherPlayerID = id;
                     PCKing.secondSureTopform(i);
                  }
               }

               super.isShowMenu = false;
               break;
            case 8:
               i = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               if (this.items.size() == 0) {
                  if (i == -1610612703) {
                     PCSocial.addform("Thêm kẻ thù", "Xin nhập tên kẻ thù:", 589848);
                  }
               } else {
                  selectedPlayerId = ((TableItem)this.items.elementAt(this.selectedIndex)).playerID;
                  if (i == -1610612708) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Các hạ xác nhận muốn xóa tên này trong danh sách kẻ thù?", "Xác nhận", "Hủy", 589842, -1);
                  } else if (i == -1610612703) {
                     PCSocial.addform("Thêm kẻ thù", "Xin nhập tên kẻ thù：", 589848);
                  } else {
                     MainCanvas.ni.send(i);
                  }
               }

               super.isShowMenu = false;
               break;
            case 10:
               i = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               MainCanvas.ni.send(i);
               super.isShowMenu = false;
               break;
            case 11:
               ti = (TableItem)this.items.elementAt(this.selectedIndex);
               ul = (UILabel)ti.getTerms().elementAt(2);
               name = ul.getText();
               if (!name.equals("")) {
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  if (cmd == 1966082) {
                     PCGem.m_bGemDisplace = 1;
                  }

                  MainCanvas.ni.send(cmd);
                  super.isShowMenu = false;
               }
               break;
            case 12:
               ti = (TableItem)this.items.elementAt(this.selectedIndex);
               ul = (UILabel)ti.getTerms().elementAt(2);
               name = ul.getText();
               if (!name.equals("")) {
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  if (cmd == 1966092) {
                     PCGem.m_bGemDisplace = 1;
                  }

                  MainCanvas.ni.send(cmd);
                  super.isShowMenu = false;
               }
               break;
            case 13:
               i = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(i) {
               case 1966101:
                  PCGem.m_bListIndex = this.selectedIndex;
                  MainCanvas.ni.send(1966101);
                  break;
               case 1966102:
                  ti = (TableItem)this.items.elementAt(this.selectedIndex);
                  ul = (UILabel)ti.getTerms().elementAt(2);
                  gemname = ul.getText();
                  if (!gemname.equals("")) {
                     PCGem.m_bListIndex = this.selectedIndex;
                     MainCanvas.ni.send(1966102);
                  } else {
                     UITopForm.createLocalTopForm((byte)0, (String)"Vị trí này không có bảo thạch, xin chọn lại!", "Xác nhận", "", -1, -2);
                  }
                  break;
               case 2031651:
                  MainCanvas.ni.send(2031651);
               }

               super.isShowMenu = false;
               break;
            case 15:
               i = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               id = ((TableItem)this.items.elementAt(this.selectedIndex)).playerID;
               selectedPlayerId = id;
               if (i == -1610612726) {
                  ti = (TableItem)this.items.elementAt(this.selectedIndex);
                  ul = (UILabel)ti.getTerms().elementAt(2);
                  name = ul.getText();
                  UIChat.intoPrivateChannel(id, name, true);
               } else {
                  if (i == 720897) {
                     SITeam.otherPlayerID = id;
                  }

                  if (i == 589831) {
                     selectedPlayerId = id;
                  }

                  if (i == 2490407 || i == 2490417) {
                     PCMentor.otherId = id;
                  }

                  MainCanvas.ni.send(i);
               }

               super.isShowMenu = false;
               break;
            case 16:
            case 17:
               i = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               PCFarm.m_nPlayerIndex = ((TableItem)this.items.elementAt(this.selectedIndex)).playerID;
               if (i == 2555905) {
                  PCFarm.m_nFarm_On_Off = 0;
                  MainCanvas.ni.send(2555952);
               }

               MainCanvas.ni.send(i);
               super.isShowMenu = false;
               break;
            case 19:
               UITitle title = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
               if (title.getTitleText().equals("Tổng xếp hạng")) {
                  GOManager.m_bBattlefieldMenuId = this.getMenu().getIndex();
               } else if (title.getTitleText().equals("Xếp hạng Thiên Nhân")) {
                  GOManager.m_bBattlefieldMenuId = (byte)(this.getMenu().getIndex() + 1);
               } else if (title.getTitleText().equals("Xếp hạng Tu La")) {
                  GOManager.m_bBattlefieldMenuId = this.getMenu().getIndex();
                  if (GOManager.m_bBattlefieldMenuId == 2) {
                     GOManager.m_bBattlefieldMenuId = 3;
                  }
               }

               id = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               if (id == 196641 && this.items.size() == 0) {
                  UITopForm.createLocalTopForm((byte)0, (String)"Không có thông tin xếp hạng!", "Xác nhận", "", -1, -2);
                  super.isShowMenu = false;
                  return;
               }

               MainCanvas.ni.send(id);
               this.m_bWhetherTime = false;
               super.isShowMenu = false;
               break;
            case 20:
               i = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               PCClan.clan_rec_id = ((TableItem)this.items.elementAt(this.selectedIndex)).playerID;
               MainCanvas.ni.send(i);
               super.isShowMenu = false;
            }
         }
      } else {
         UITitle menuBar;
         UIComponent uic;
         int j;
         if (MainCanvas.isInputDownOrHold(4096)) {
            --this.selectedIndex;
            if (this.selectedIndex < 0) {
               this.selectedIndex = 0;
               this.isEndUp = true;
            } else {
               this.isEndUp = false;
            }

            if (this.serverType == 11 || this.serverType == 12) {
               ti = (TableItem)this.items.elementAt(this.selectedIndex);
               ul = (UILabel)ti.getTerms().elementAt(2);
               name = ul.getText();
               if (PCGem.m_bEnchaseSucceed == 1) {
                  menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                  if (!name.equals("")) {
                     menuBar.setMenuText("Tra xem", 0);
                  } else {
                     menuBar.setMenuText("", 0);
                  }
               } else if (name.equals("")) {
                  menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                  menuBar.setMenuText("Khảm nạm", 0);
               } else {
                  menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                  menuBar.setMenuText("Thao tác", 0);
               }
            }

            if (this.getTableVScroll() == null) {
               return;
            }

            if (this.isCanSelected) {
               if (this.selectedIndex < this.getTableVScroll().showStartRow) {
                  --this.startIndex;

                  for(i = 0; i < this.items.size(); ++i) {
                     ti = (TableItem)this.items.elementAt(i);
                     ti.setY((short)(ti.getY() + ti.getTermHeight()));

                     for(j = 0; j < ti.getTerms().size(); ++j) {
                        uic = (UIComponent)ti.getTerms().elementAt(j);
                        uic.setPositionY((short)(uic.getPositionY() + ti.getTermHeight()));
                     }
                  }
               }
            } else {
               --this.startIndex;
               if (this.startIndex < 0) {
                  this.startIndex = 0;
               } else {
                  for(i = 0; i < this.items.size(); ++i) {
                     ti = (TableItem)this.items.elementAt(i);
                     ti.setY((short)(ti.getY() + ti.getTermHeight()));

                     for(j = 0; j < ti.getTerms().size(); ++j) {
                        uic = (UIComponent)ti.getTerms().elementAt(j);
                        uic.setPositionY((short)(uic.getPositionY() + ti.getTermHeight()));
                     }
                  }
               }

               this.getTableVScroll().setShowStartRow((short)this.startIndex);
            }

            if (MainCanvas.curForm.clientCommand == 1703939) {
               for(i = 0; i < this.items.size(); ++i) {
                  ti = (TableItem)this.items.elementAt(i);
                  UIForm.m_nDelieveY[i] = ti.getY() + 9;
               }
            }

            if (MainCanvas.curForm.clientCommand == 262157) {
               PCTask.setTurnTaskInfoLeftMenu();
            }

            if (MainCanvas.curForm.clientCommand == 262154) {
               PCTask.setTakeTaskInfoLeftMenu();
            }
         } else if (MainCanvas.isInputDownOrHold(8192)) {
            ++this.selectedIndex;
            this.isEndUp = false;
            if (this.isCanSelected && this.selectedIndex > this.items.size() - 1) {
               this.selectedIndex = (byte)(this.items.size() - 1);
            }

            if (this.serverType == 11 || this.serverType == 12) {
               ti = (TableItem)this.items.elementAt(this.selectedIndex);
               ul = (UILabel)ti.getTerms().elementAt(2);
               name = ul.getText();
               if (PCGem.m_bEnchaseSucceed == 1) {
                  menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                  if (!name.equals("")) {
                     menuBar.setMenuText("Tra xem", 0);
                  } else {
                     menuBar.setMenuText("", 0);
                  }
               } else if (name.equals("")) {
                  menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                  menuBar.setMenuText("Khảm nạm", 0);
               } else {
                  menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                  menuBar.setMenuText("Thao tác", 0);
               }
            }

            if (this.getTableVScroll() == null) {
               return;
            }

            if (this.isCanSelected) {
               if (this.selectedIndex >= this.getTableVScroll().showStartRow + this.getTableVScroll().canShowRowNum) {
                  ++this.startIndex;

                  for(i = 0; i < this.items.size(); ++i) {
                     ti = (TableItem)this.items.elementAt(i);
                     ti.setY((short)(ti.getY() - ti.getTermHeight()));

                     for(j = 0; j < ti.getTerms().size(); ++j) {
                        uic = (UIComponent)ti.getTerms().elementAt(j);
                        uic.setPositionY((short)(uic.getPositionY() - ti.getTermHeight()));
                     }
                  }
               }
            } else {
               ++this.startIndex;
               if (this.startIndex > this.getItems().size() - this.getCanShowCol()) {
                  this.startIndex = this.getItems().size() - this.getCanShowCol();
               } else {
                  for(i = 0; i < this.items.size(); ++i) {
                     ti = (TableItem)this.items.elementAt(i);
                     ti.setY((short)(ti.getY() - ti.getTermHeight()));

                     for(j = 0; j < ti.getTerms().size(); ++j) {
                        uic = (UIComponent)ti.getTerms().elementAt(j);
                        uic.setPositionY((short)(uic.getPositionY() - ti.getTermHeight()));
                     }
                  }
               }

               this.getTableVScroll().setShowStartRow((short)this.startIndex);
            }

            if (MainCanvas.curForm.clientCommand == 1703939) {
               for(i = 0; i < this.items.size(); ++i) {
                  ti = (TableItem)this.items.elementAt(i);
                  UIForm.m_nDelieveY[i] = ti.getY() + 9;
               }
            }

            if (MainCanvas.curForm.clientCommand == 262157) {
               PCTask.setTurnTaskInfoLeftMenu();
            }

            if (MainCanvas.curForm.clientCommand == 262154) {
               PCTask.setTakeTaskInfoLeftMenu();
            }
         } else if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
            UIMenu menu;
            menu = new UIMenu((byte)0);
            int[] menuCommand;
            int[] cmds;
            String[] strs;
            String[] strs;
            int[] cmds;
            int[] tmpCmds;
            int[] tmpInns;
            int[] inns;
            String[] menus;
            int[] inns;
            String[] menus;
            String[] tmpMenus;
            label670:
            switch(this.serverType) {
            case 0:
               menus = (String[])null;
               cmds = (int[])null;
               inns = (int[])null;
               if (MainCanvas.curForm.clientCommand == 1703945) {
                  if (this.items.size() == 0) {
                     return;
                  }

                  menus = new String[]{"Người nhận"};
                  cmds = new int[]{-1610612729};
                  inns = new int[]{3};
                  menu.addMenu(menus, cmds, inns);
               } else if (this.items.size() == 0) {
                  menus = new String[]{"Thêm bạn bè"};
                  cmds = new int[]{1};
                  inns = new int[]{3};
                  menu.addMenu(menus, cmds, inns);
               } else {
                  TableItem ti = (TableItem)this.items.elementAt(this.selectedIndex);
                  menu.addMenu("Tra xem chi tiết", 589831, 2);
                  if (ti.isOL) {
                     menu.addMenu("Chat riêng ", -1610612726, 2);
                  }

                  if ((SITeam.teamState == 0 || SITeam.headerID == Player.getInstance().getID()) && ti.isOL) {
                     menu.addMenu("Mời tổ đội", 720897, 3);
                  }

                  menu.addMenu("Xóa bạn bè", 589827, 3);
                  menu.addMenu("Chặn người này", 589829, 0);
                  if (ti.isOL) {
                     menu.addMenu("Dịch chuyển", 589846, 0);
                  }

                  menu.addMenu("Thêm bạn bè", 1, 3);
               }
               break;
            case 1:
               if (this.items.size() == 0) {
                  menuCommand = new int[]{1};
                  cmds = new int[]{3};
                  menu.addMenu(new String[]{"Thêm ds đen"}, menuCommand, cmds);
               } else {
                  menuCommand = new int[]{589831, 589830, 1};
                  cmds = new int[]{2, 3, 3};
                  menu.addMenu(Cons.MENU_BLACK_STR, menuCommand, cmds);
               }
               break;
            case 2:
               if (this.items.size() == 0) {
                  return;
               }

               byte camp = ((TableItem)this.items.elementAt(this.selectedIndex)).playerCamp;
               if (Player.getInstance().getCamp() == camp) {
                  menus = new String[]{Cons.C_STR[6], "Chat riêng", "Thêm bạn", "Chặn người này"};
                  inns = new int[]{589831, -1610612726, 589826, 589829};
                  inns = new int[]{2, 2, 3, 2};
                  if (SITeam.teamState == 0 || SITeam.headerID == Player.getInstance().getID()) {
                     tmpMenus = new String[]{"Mời tổ đội"};
                     tmpCmds = new int[]{720897};
                     tmpInns = new int[]{3};
                     menus = Util.addArrays(menus, menus.length, tmpMenus);
                     inns = Util.addArrays(inns, inns.length, tmpCmds);
                     inns = Util.addArrays(inns, inns.length, tmpInns);
                  }

                  if (Player.getInstance().getKingRight() != PCKing.KING_RIGHT_HERDER && Player.getInstance().getKingRight() != PCKing.KING_RIGHT_VICE_HERDER) {
                     if (Player.getInstance().getClanRight() == PCClan.CLAN_RIGHT_HERDER || Player.getInstance().getClanRight() == PCClan.CLAN_RIGHT_VICE_HERDER) {
                        tmpMenus = new String[]{"Chiêu nạp"};
                        tmpCmds = new int[]{1638405};
                        tmpInns = new int[]{3};
                        menus = Util.addArrays(menus, menus.length, tmpMenus);
                        inns = Util.addArrays(inns, inns.length, tmpCmds);
                        inns = Util.addArrays(inns, inns.length, tmpInns);
                     }
                  } else {
                     tmpMenus = new String[]{"Chiêu nạp"};
                     tmpCmds = new int[]{1835013};
                     tmpInns = new int[]{3};
                     menus = Util.addArrays(menus, menus.length, tmpMenus);
                     inns = Util.addArrays(inns, inns.length, tmpCmds);
                     inns = Util.addArrays(inns, inns.length, tmpInns);
                  }

                  menu.addMenu(menus, inns, inns);
               } else {
                  menus = (String[])null;
                  gemname = "Thêm kẻ thù";
                  menus = new String[]{Cons.C_STR[6], gemname};
                  inns = new int[]{589831, 589841};
                  cmds = new int[]{2, 3};
                  menu.addMenu(menus, inns, cmds);
               }
               break;
            case 3:
               if (this.items.size() == 0) {
                  return;
               }
               break;
            case 4:
               if (this.items.size() == 0) {
                  return;
               }

               if (MainCanvas.curForm.clientCommand == 851978) {
                  return;
               }

               ti = (TableItem)this.items.elementAt(this.selectedIndex);
               Player.getInstance().path = (new AStar()).findPath(Player.getInstance().nCol, Player.getInstance().nRow, ti.selectedNPCX, ti.selectedNPCY);
               Player.getInstance().setState((byte)104);
               MainCanvas.quitUI();
               MainCanvas.mc.leftRightMenu();
               return;
            case 5:
               if (this.items.size() == 0) {
                  return;
               }

               menuCommand = new int[]{-1};
               cmds = new int[]{3};
               menu.addMenu(Cons.MENU_TALK_LIST, menuCommand, cmds);
               break;
            case 6:
               if (this.items.size() == 0) {
                  return;
               }

               menuCommand = new int[]{1703942, 1703943, 1703944};
               cmds = new int[]{1, 3, 3};
               menu.addMenu(Cons.MENU_MAIL_INBOX, menuCommand, cmds);
               break;
            case 7:
               if (this.items.size() == 0) {
                  return;
               }

               menus = (String[])null;
               cmds = (int[])null;
               inns = (int[])null;
               int id = ((TableItem)this.items.elementAt(this.selectedIndex)).playerID;
               switch(MainCanvas.curForm.clientCommand) {
               case 1638404:
                  if (id == Player.getInstance().getID()) {
                     menus = new String[]{"Thoát thị tộc"};
                     cmds = new int[]{1638413};
                     inns = new int[1];
                     if (Player.getInstance().getClanRight() == PCClan.CLAN_RIGHT_HERDER) {
                        tmpMenus = new String[]{"Giải tán thị tộc"};
                        tmpCmds = new int[]{1638408};
                        tmpInns = new int[1];
                        menus = Util.addArrays(menus, menus.length, tmpMenus);
                        cmds = Util.addArrays(cmds, cmds.length, tmpCmds);
                        inns = Util.addArrays(inns, inns.length, tmpInns);
                     } else if (Player.getInstance().getClanRight() == PCClan.CLAN_RIGHT_VICE_HERDER || Player.getInstance().getClanRight() == 15) {
                        tmpMenus = new String[]{"Cách chức"};
                        tmpCmds = new int[]{1638416};
                        tmpInns = new int[]{3};
                        menus = Util.addArrays(menus, menus.length, tmpMenus);
                        cmds = Util.addArrays(cmds, cmds.length, tmpCmds);
                        inns = Util.addArrays(inns, inns.length, tmpInns);
                     }
                  } else {
                     menus = new String[0];
                     cmds = new int[]{1638444, 589835, -1610612726};
                     inns = new int[]{2, 3, 2};
                     if (Player.getInstance().getClanRight() == PCClan.CLAN_RIGHT_HERDER) {
                        tmpMenus = new String[]{"Thăng làm Phó tộc trưởng", "Thăng làm Trưởng lão", "Hạ chức", "Nhường ngôi Tộc trưởng", "Khai trừ người này"};
                        tmpCmds = new int[]{1638411, 1638415, 1638412, 1638410, 1638409};
                        tmpInns = new int[]{3, 3, 3, 3, 3};
                        menus = Util.addArrays(menus, menus.length, tmpMenus);
                        cmds = Util.addArrays(cmds, cmds.length, tmpCmds);
                        inns = Util.addArrays(inns, inns.length, tmpInns);
                     } else if (Player.getInstance().getClanRight() == PCClan.CLAN_RIGHT_VICE_HERDER) {
                        tmpMenus = new String[]{"Thăng làm Trưởng lão", "Hạ chức", "Khai trừ người này"};
                        tmpCmds = new int[]{1638415, 1638412, 1638409};
                        tmpInns = new int[]{3, 3, 3};
                        menus = Util.addArrays(menus, menus.length, tmpMenus);
                        cmds = Util.addArrays(cmds, cmds.length, tmpCmds);
                        inns = Util.addArrays(inns, inns.length, tmpInns);
                     }
                  }
                  break;
               case 1835028:
                  if (Player.getInstance().getID() != id) {
                     menus = new String[0];
                     cmds = new int[]{1638444, 589835, -1610612726};
                     inns = new int[]{2, 3, 2};
                     if (Player.getInstance().getKingRight() == PCKing.KING_RIGHT_HERDER) {
                        tmpMenus = new String[]{"Giao chức vụ", "Nhường ngôi Hầu chủ"};
                        tmpCmds = new int[]{1835030, 1835035};
                        tmpInns = new int[]{2, 3};
                        menus = Util.addArrays(menus, menus.length, tmpMenus);
                        cmds = Util.addArrays(cmds, cmds.length, tmpCmds);
                        inns = Util.addArrays(inns, inns.length, tmpInns);
                     }
                  }
               }

               menu.addMenu(menus, cmds, inns);
               break;
            case 8:
               if (this.items.size() == 0) {
                  menuCommand = new int[]{-1610612703};
                  cmds = new int[]{3};
                  menu.addMenu(Cons.MENU_PERSONAL_APPEND, menuCommand, cmds);
               } else {
                  ti = (TableItem)this.items.elementAt(this.selectedIndex);
                  if (ti.isOL) {
                     cmds = new int[]{589843, -1610612708, -1610612703, 589844};
                     inns = new int[]{2, 3, 3, 3};
                     menu.addMenu(Cons.MENU_PERSONAL_ENEMY, cmds, inns);
                  } else {
                     cmds = new int[]{589843, -1610612708, -1610612703};
                     inns = new int[]{2, 3, 3};
                     menu.addMenu(Cons.MENU_PERSONAL_ENEMY_OFFLINE, cmds, inns);
                  }
               }
            case 9:
            default:
               break;
            case 10:
               switch(MainCanvas.curForm.clientCommand) {
               case 262154:
                  if (this.items.size() != 0) {
                     ti = (TableItem)this.items.elementAt(this.selectedIndex);
                     if (ti.getPayType() == 1) {
                        PCTask.getGoodsIndex = (byte)(this.selectedIndex + 1);
                        menus = new String[]{"Nhận nhiệm vụ", "Tra xem vật phẩm"};
                        inns = new int[]{262149, 262189};
                        inns = new int[]{6, 2};
                        menu.addMenu(menus, inns, inns);
                     } else {
                        UIMenu.formDeleteForm();
                        MainCanvas.ni.send(262149);
                     }
                  }
                  break label670;
               case 262157:
                  if (this.items.size() != 0) {
                     ti = (TableItem)this.items.elementAt(this.selectedIndex);
                     if (UIList.canReturn) {
                        if (ti.getPayType() == 1) {
                           PCTask.getGoodsIndex = (byte)(this.selectedIndex + 1);
                           menus = new String[]{"Hoàn thành nhiệm vụ", "Tra xem vật phẩm"};
                           inns = new int[]{262188, 262189};
                           inns = new int[]{PCTask.haveMayBeChoice ? 2 : 0, 2};
                           menu.addMenu(menus, inns, inns);
                        } else {
                           if (PCTask.haveMayBeChoice) {
                              UIMenu.formSaveForm();
                           }

                           MainCanvas.ni.send(262188);
                        }
                     } else if (ti.getPayType() == 1) {
                        PCTask.getGoodsIndex = (byte)(this.selectedIndex + 1);
                        UIMenu.formSaveForm();
                        MainCanvas.ni.send(262189);
                     }
                  }
                  break label670;
               case 262159:
                  if (this.items.size() != 0) {
                     ti = (TableItem)this.items.elementAt(this.selectedIndex);
                     if (ti.getPayType() == 1) {
                        PCTask.getGoodsIndex = (byte)(this.selectedIndex + 1);
                        menus = new String[]{"Tra xem物品"};
                        inns = new int[]{262189};
                        inns = new int[]{2};
                        menu.addMenu(menus, inns, inns);
                     }
                  }
                  break label670;
               case 262178:
                  if (this.items.size() != 0) {
                     ti = (TableItem)this.items.elementAt(this.selectedIndex);
                     if (ti.getPayType() == 1) {
                        PCTask.getGoodsIndex = (byte)(this.selectedIndex + 1);
                        menus = new String[]{"Refresh", "Tra xem vật phẩm"};
                        inns = new int[]{262179, 262189};
                        inns = new int[]{0, 2};
                        menu.addMenu(menus, inns, inns);
                     } else {
                        menus = new String[]{"Refresh"};
                        inns = new int[]{262179};
                        inns = new int[1];
                        menu.addMenu(menus, inns, inns);
                     }
                  }
                  break label670;
               case 458754:
                  if (this.items.size() != 0) {
                     ti = (TableItem)this.items.elementAt(this.selectedIndex);
                     if (ti.getPayType() == 1) {
                        PCTask.getGoodsIndex = (byte)(this.selectedIndex + 1);
                        menus = new String[]{"Nhận nhiệm vụ", "Tra xem vật phẩm"};
                        inns = new int[]{262180, 262182};
                        inns = new int[]{0, 2};
                        menu.addMenu(menus, inns, inns);
                     } else {
                        menus = new String[]{"Nhận nhiệm vụ"};
                        inns = new int[]{262180};
                        inns = new int[1];
                        menu.addMenu(menus, inns, inns);
                     }
                  }
               default:
                  break label670;
               }
            case 11:
               PCGem.m_bListIndex = this.selectedIndex;
               ti = (TableItem)this.items.elementAt(this.selectedIndex);
               ul = (UILabel)ti.getTerms().elementAt(2);
               gemname = ul.getText();
               if (PCGem.m_bEnchaseSucceed == 1) {
                  if (!gemname.equals("")) {
                     UIMenu.formSaveForm();
                     MainCanvas.ni.send(1966083);
                  }
               } else if (gemname.equals("")) {
                  PCGem.m_bGemDisplace = 0;
                  UIMenu.formSaveForm();
                  MainCanvas.ni.send(1966082);
               } else {
                  inns = new int[]{1966083, 1966082};
                  cmds = new int[]{2, 2};
                  menu.addMenu(Cons.MENU_GEM_VIEW, inns, cmds);
               }
               break;
            case 12:
               PCGem.m_bListIndex = this.selectedIndex;
               ti = (TableItem)this.items.elementAt(this.selectedIndex);
               ul = (UILabel)ti.getTerms().elementAt(2);
               gemname = ul.getText();
               if (PCGem.m_bEnchaseSucceed == 1) {
                  if (!gemname.equals("")) {
                     UIMenu.formSaveForm();
                     MainCanvas.ni.send(1966083);
                  }
               } else if (gemname.equals("")) {
                  PCGem.m_bGemDisplace = 0;
                  MainCanvas.ni.send(1966088);
               } else {
                  inns = new int[]{1966083, 1966092};
                  cmds = new int[]{2, 3};
                  menu.addMenu(Cons.MENU_GEM_VIEW, inns, cmds);
               }
               break;
            case 13:
               ti = (TableItem)this.items.elementAt(this.selectedIndex);
               ul = (UILabel)ti.getTerms().elementAt(2);
               gemname = ul.getText();
               if (PCGem.m_bSucceed == -1) {
                  strs = new String[]{"Tra xem chi tiết", "Giải trừ bảo thạch", "Thuyết minh giải trừ"};
                  cmds = new int[]{1966101, 1966102, 2031651};
                  tmpCmds = new int[]{gemname.equals("") ? 3 : 2, 3, 2};
                  menu.addMenu(strs, cmds, tmpCmds);
               } else if (PCGem.m_bSucceed == 1) {
                  strs = new String[]{"Tra xem chi tiết", "Thuyết minh giải trừ"};
                  cmds = new int[]{1966101, 2031651};
                  tmpCmds = new int[]{gemname.equals("") ? 3 : 2, 2};
                  menu.addMenu(strs, cmds, tmpCmds);
               }
               break;
            case 14:
               if (this.items.size() > 0) {
                  ti = (TableItem)this.items.elementAt(this.selectedIndex);
                  PCMovement.actID = ti.playerID;
                  UIMenu.formSaveForm();
                  MainCanvas.ni.send(2293874);
               }
               break;
            case 15:
               if (this.items.size() != 0) {
                  ti = (TableItem)this.items.elementAt(this.selectedIndex);
                  if (MainCanvas.curForm.clientCommand == 2490390) {
                     menu.addMenu("Bái sư", 2490407, 3);
                  } else if (MainCanvas.curForm.clientCommand == 2490391) {
                     menu.addMenu("Thu đồ đệ", 2490417, 3);
                  }

                  menu.addMenu("Tra xem", 589831, 2);
                  if (SITeam.teamState == 0 || SITeam.headerID == Player.getInstance().getID()) {
                     ti.getClass();
                  }

                  ti.getClass();
                  menu.addMenu("Thêm bạn bè", 589826, 3);
               }
               break;
            case 16:
            case 17:
               if (this.items.size() != 0) {
                  menuCommand = new int[]{2555905, this.serverType == 16 ? 2555964 : 2555965};
                  cmds = new int[]{3, 2};
                  strs = new String[]{"Ra nông trường của người khác", "Tra xem"};
                  menu.addMenu(strs, menuCommand, cmds);
               }
               break;
            case 18:
               if (this.items.size() != 0) {
                  ti = (TableItem)this.items.elementAt(this.selectedIndex);
                  PCFarm.m_nFriendID = ti.playerID;
                  if (MainCanvas.curForm.clientCommand == 2555941) {
                     MainCanvas.ni.send(2555942);
                  } else if (MainCanvas.curForm.clientCommand == 2555943) {
                     MainCanvas.ni.send(2555944);
                  } else if (MainCanvas.curForm.clientCommand == 2555945) {
                     MainCanvas.ni.send(2555946);
                  }
               }
               break;
            case 19:
               UITitle title = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
               if (title.getTitleText().equals("Tổng xếp hạng")) {
                  cmds = new int[]{196641, 196642, 196642};
                  inns = new int[]{3, 3, 3};
                  strs = new String[]{"Thông tin nhân vật", "Xếp hạng Thiên Nhân", "Xếp hạn Tu La"};
                  menu.addMenu(strs, cmds, inns);
               } else if (title.getTitleText().equals("Xếp hạng Thiên Nhân")) {
                  cmds = new int[]{196641, 196642, 196642};
                  inns = new int[]{3, 3, 3};
                  strs = new String[]{"Thông tin nhân vật", "Xếp hạng Tu La", "Tổng xếp hạng"};
                  menu.addMenu(strs, cmds, inns);
               } else if (title.getTitleText().equals("Xếp hạn Tu La")) {
                  cmds = new int[]{196641, 196642, 196642};
                  inns = new int[]{3, 3, 3};
                  strs = new String[]{"Thông tin nhân vật", "Xếp hạng Thiên Nhân", "Tổng xếp hạng"};
                  menu.addMenu(strs, cmds, inns);
               }

               if (this.items.size() != 0) {
                  GameObject.m_lBattlefield = ((TableItem)this.items.elementAt(this.selectedIndex)).m_lBattlefield;
               }
               break;
            case 20:
               if (this.items.size() != 0) {
                  menuCommand = new int[]{1638449};
                  cmds = new int[]{3};
                  strs = new String[]{"Xin Khiêu chiến"};
                  menu.addMenu(strs, menuCommand, cmds);
               }
            }

            switch(MainCanvas.curForm.clientCommand) {
            case 1638421:
               UITable tb = (UITable)MainCanvas.curForm.getComponents().elementAt(3);
               ti = (TableItem)tb.getItems().elementAt(this.selectedIndex);
               ul = (UILabel)ti.getTerms().elementAt(2);
               name = ul.getText();
               ul.setText(name == "Không" ? "Có" : "Không");
            default:
               if (menu.getItems().size() > 0) {
                  this.setMenu(menu);
                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               }
            }
         }

         if (this.getTableVScroll() != null) {
            this.getTableVScroll().keyInScroll(this.selectedIndex, this.isCanSelected);
         }
      }

   }

   public byte getSelectedIndex() {
      return this.selectedIndex;
   }

   public void setSelectedIndex(byte index) {
      this.selectedIndex = index;
      super.haveInnerComponent = this.selectedIndex != -1;
   }

   public Vector getItems() {
      return this.items;
   }

   public void setItems(Vector items) {
      this.items = items;
   }

   public void addItem(TableItem ti) {
      if (ti != null) {
         this.items.addElement(ti);
         this.checkScroll();
      }

   }

   public void setCamp() {
      for(int i = 0; i < this.items.size(); ++i) {
         TableItem ti = (TableItem)this.items.elementAt(i);

         for(int j = 0; j < ti.getTerms().size(); ++j) {
            UIComponent uic = (UIComponent)ti.getTerms().elementAt(j);
            if (uic instanceof UILabel) {
               ((UILabel)uic).setSameCamp(((TableItem)this.items.elementAt(i)).playerCamp == Player.getInstance().getCamp());
            }
         }
      }

   }

   public void setOL() {
      for(int i = 0; i < this.items.size(); ++i) {
         TableItem ti = (TableItem)this.items.elementAt(i);

         for(int j = 0; j < ti.getTerms().size(); ++j) {
            UIComponent uic = (UIComponent)ti.getTerms().elementAt(j);
            if (uic instanceof UILabel) {
               ((UILabel)uic).setOL(((TableItem)this.items.elementAt(i)).isOL);
            }
         }
      }

   }

   public void sortOL() {
      if (this.items.size() != 0) {
         UITable tmpTable = new UITable(this.getPositionX(), this.getPositionY(), this.getWidth(), this.getHeight(), this.getRow(), this.getTitleItem(), this.getAttachForm());
         Vector olItemV = new Vector();
         Vector offItemV = new Vector();

         TableItem firstItem;
         for(int i = 0; i < this.items.size(); ++i) {
            firstItem = (TableItem)this.items.elementAt(i);
            boolean isOL = ((TableItem)this.items.elementAt(i)).isOL;
            if (isOL) {
               olItemV.addElement(firstItem);
            } else {
               offItemV.addElement(firstItem);
            }
         }

         short startY = (short)(this.getPositionY() + 1 + 1 + 1 + (this.getTitleItem() == null ? 0 : this.getTitleItem().getTermHeight()));

         for(int i = 0; i < olItemV.size(); ++i) {
            TableItem ti = (TableItem)olItemV.elementAt(i);

            for(int j = 0; j < ti.getTerms().size(); ++j) {
               this.resetTableItemPositionY(ti, i, j, startY);
            }

            tmpTable.addItem(ti);
         }

         firstItem = null;
         if (olItemV.size() > 0) {
            firstItem = (TableItem)olItemV.elementAt(0);
         } else if (offItemV.size() > 0) {
            firstItem = (TableItem)offItemV.elementAt(0);
         }

         startY = (short)(startY + olItemV.size() * firstItem.getTermHeight());

         int i;
         for(i = 0; i < offItemV.size(); ++i) {
            TableItem ti = (TableItem)offItemV.elementAt(i);

            for(int j = 0; j < ti.getTerms().size(); ++j) {
               this.resetTableItemPositionY(ti, i, j, startY);
            }

            tmpTable.addItem(ti);
         }

         this.removeAllItems();

         for(i = 0; i < tmpTable.getItems().size(); ++i) {
            this.addItem((TableItem)tmpTable.getItems().elementAt(i));
         }

      }
   }

   private void resetTableItemPositionY(TableItem ti, int tableIndex, int termIndex, short startY) {
      UIComponent uic = (UIComponent)ti.getTerms().elementAt(termIndex);
      ti.setY((short)(startY + tableIndex * ti.getTermHeight()));
      if (uic instanceof UIPicture) {
         UIPicture pic = (UIPicture)uic;
         pic.setPositionY((short)(startY + tableIndex * ti.getTermHeight() + (ti.getTermHeight() - pic.getImg().frame_h) / 2));
      } else if (uic instanceof UILabel) {
         UILabel label = (UILabel)uic;
         if (label.getType() == 0) {
            label.setPositionY((short)(startY + tableIndex * ti.getTermHeight() + (ti.getTermHeight() - MainCanvas.CHARH) / 2));
         } else if (label.getType() == 2) {
            label.setPositionY((short)(startY + tableIndex * ti.getTermHeight() + (ti.getTermHeight() - MainCanvas.ui_dataNumMImg.frame_h) / 2 + 1));
         }
      }

   }

   public boolean isCanSelected() {
      return this.isCanSelected;
   }

   public void setCanSelected(boolean isCanSelected) {
      this.isCanSelected = isCanSelected;
   }

   public void checkScroll() {
      if (this.getTableHeight() + (this.titleItem == null ? 0 : this.titleItem.getTermHeight()) > this.getTableShowHeight()) {
         this.tableVScroll = new UIScroll((short)(super.positionX + super.width - 5 - MainCanvas.ui_3Img.getWidth()), (short)(super.positionY + 1 + 1 + 1), (short)0, this.getTableShowHeight(), (byte)0, !this.isCanSelected, (UIForm)null);
      } else {
         this.tableVScroll = null;
      }

   }

   public void removeItem(int index) {
      if (index >= 0 && index < this.items.size()) {
         this.items.removeElementAt(index);
         this.checkScroll();
      } else {
         throw new IndexOutOfBoundsException("Biểu tượng vượt giới hạn");
      }
   }

   public void removeAllItems() {
      for(int i = 0; i < this.items.size(); ++i) {
         this.items.removeElementAt(i);
      }

      this.items = new Vector();
      if (this.tableVScroll != null) {
         this.tableVScroll = null;
      }

   }

   public byte getCanShowCol() {
      byte showCol = 0;
      short termHeight = false;
      short termHeight;
      if (this.items.size() > 0) {
         termHeight = ((TableItem)this.items.elementAt(0)).getTermHeight();
      } else {
         termHeight = TableItem.getDefaultTableItemHeight();
      }

      short h = termHeight;
      if (this.titleItem != null) {
         h = (short)(termHeight + termHeight);
      }

      while(h <= this.getTableShowHeight()) {
         ++showCol;
         h += termHeight;
      }

      return showCol;
   }

   private short getTableShowHeight() {
      return (short)(super.height - 6);
   }

   private short getTableShowWidth() {
      return (short)(super.width - 10);
   }

   private int getTableHeight() {
      return this.items.size() * ((TableItem)this.items.elementAt(0)).getTermHeight();
   }

   public byte getRow() {
      return this.row;
   }

   public void setRow(byte row) {
      this.row = row;
   }

   public void setPositionX(short positionX) {
      super.setPositionX(positionX);
      int i;
      if (this.titleItem != null) {
         for(i = 0; i < this.titleItem.getTerms().size(); ++i) {
            UIComponent uic = (UIComponent)this.titleItem.getTerms().elementAt(i);
            uic.setPositionX((short)(positionX + 3 + 1 + 1 + this.titleItem.getCurWidth((byte)i) + (this.titleItem.getTermWidth()[i] - uic.getWidth()) / 2));
         }
      }

      for(i = 0; i < this.items.size(); ++i) {
         TableItem ti = (TableItem)this.items.elementAt(i);

         for(int j = 0; j < ti.getTerms().size(); ++j) {
            UIComponent uic = (UIComponent)ti.getTerms().elementAt(j);
            short offset = 0;
            if (ti.getIsCenter()[j]) {
               offset = (short)((ti.getTermWidth()[j] - uic.getWidth()) / 2);
            }

            uic.setPositionX((short)(positionX + 3 + 1 + 1 + ti.getCurWidth((byte)j) + offset + 1));
         }
      }

      if (this.tableVScroll != null) {
         this.tableVScroll.setPositionX((short)(positionX + super.width - 5 - MainCanvas.ui_3Img.getWidth()));
      }

   }

   public void setPositionY(short positionY) {
      super.setPositionY(positionY);
      int i;
      if (this.titleItem != null) {
         for(i = 0; i < this.titleItem.getTerms().size(); ++i) {
            UIComponent uic = (UIComponent)this.titleItem.getTerms().elementAt(i);
            uic.setPositionY((short)(positionY + 1 + 1 + 1));
         }
      }

      for(i = 0; i < this.items.size(); ++i) {
         TableItem ti = (TableItem)this.items.elementAt(i);

         for(int j = 0; j < ti.getTerms().size(); ++j) {
            UIComponent uic = (UIComponent)ti.getTerms().elementAt(j);
            uic.setPositionY((short)(positionY + 1 + 1 + 1 + (i + (this.titleItem == null ? 0 : 1)) * ti.getTermHeight() + (ti.getTermHeight() - uic.getHeight()) / 2));
         }
      }

      if (this.tableVScroll != null) {
         this.tableVScroll.setPositionY((short)(positionY + 1 + 1 + 1));
         this.tableVScroll.resetScrollPositionY();
      }

   }

   public TableItem getTitleItem() {
      return this.titleItem;
   }

   public void setTitleItem(TableItem titleItem) {
      this.titleItem = titleItem;
   }

   public UIScroll getTableVScroll() {
      return this.tableVScroll;
   }

   public void setTableVScroll(UIScroll scroll) {
      this.tableVScroll = scroll;
   }

   public byte getAllRowNum() {
      return this.allRowNum;
   }

   public void setAllRowNum(byte allRowNum) {
      this.allRowNum = allRowNum;
   }

   public byte getAllColNum() {
      return this.allColNum;
   }

   public void setAllColNum(byte allColNum) {
      this.allColNum = allColNum;
   }

   public void addUIScroll() {
      if (this.getTableVScroll() != null) {
         this.getTableVScroll().allRowNum = (short)this.items.size();
         this.getTableVScroll().canShowRowNum = this.getCanShowCol();
         this.getTableVScroll().setShowStartRow((short)0);
      }
   }

   public int getStartIndex() {
      return this.startIndex;
   }

   public void setStartIndex(int startIndex) {
      this.startIndex = startIndex;
   }

   public boolean focusWidgetPointAction() {
      if (MainCanvas.SUPPORT_POINTER) {
         if (this.getTableVScroll() != null && this.getTableVScroll().focusWidgetPointAction()) {
            return true;
         }

         int n = this.items.size() < this.getCanShowCol() ? this.items.size() : this.getCanShowCol() + this.startIndex;

         for(int i = this.startIndex; i < n; ++i) {
            TableItem ti = (TableItem)this.items.elementAt(i);
            if (MainCanvas.isPointInRect(ti.getX(), ti.getY(), this.getTableShowWidth() - MainCanvas.ui_3Img.getWidth(), ti.getTermHeight())) {
               if (this.selectedIndex == i) {
                  return false;
               }

               this.selectedIndex = (byte)i;
               return true;
            }
         }
      }

      return true;
   }
}

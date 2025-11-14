import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public class UITextArea extends UIComponent {
   static final String ENTER = System.getProperty("line.separator");
   public static final byte TYPE_DEFAULT = 0;
   public static final byte TYPE_SKILLINTERFACE = 1;
   public static final byte TYPE_KEFU = 2;
   private byte textareatype = 0;
   private byte rimType = 0;
   public static final byte TYPE_NONE_RIM = 0;
   public static final byte TYPE_IN_RIM = 1;
   public static final byte TYPE_OUT_RIM = 2;
   private String content = null;
   private String[] strArray = null;
   private short contentFullRows = 0;
   private short canShowRows = 0;
   private short pointer = 0;
   private Font textFont = null;
   private UIScroll textAreaScroll = null;
   private short textAreaX = 0;
   private short textAreaY = 0;
   private short textAreaW = 0;
   private short textAreaH = 0;
   private int color = -1;
   public boolean m_bWhetherTime = false;
   short outRimW = 3;
   short outRimH = 1;
   public byte serverType = -1;
   public static final byte SERVER_TYPE_TASK_INFO = 0;
   public static final byte SERVER_TYPE_NPC_TALK_INFO = 1;
   public static final byte SERVER_TYPE_TASK_RECEIVE_INFO = 2;
   public static final byte SERVER_TYPE_TASK_PAY_INFO = 3;
   public static final byte SERVER_TYPE_CLAN_INFO = 4;
   public static final byte SERVER_TYPE_CLAN_NOTICE_INFO = 5;
   public static final byte SERVER_TYPE_RESET_INFO = 6;
   public static final byte SERVER_TYPE_ACCEPT_INFO = 7;
   public static final byte SERVER_TYPE_TASK_GOODS_INFO = 8;
   public static final byte SERVER_TYPE_TASK_REFRESH_INFO = 9;
   public static final byte SERVER_TYPE_FORGING_LEARN_INFO = 10;
   public static final byte SERVER_TYPE_SMITH_LEARN_INFO = 11;
   public static final byte SERVER_TYPE_LEATHER_LEARN_INFO = 12;
   public static final byte SERVER_TYPE_SEWING_LEARN_INFO = 13;
   public static final byte SERVER_TYPE_ENCHANTING_LEARN_INFO = 14;
   public static final byte SERVER_TYPE_RESOLVE_LEARN_INFO = 15;
   public static final byte SERVER_TYPE_MINING_LEARN_INFO = 16;
   public static final byte SERVER_TYPE_HERBALISM_LEARN_INFO = 17;
   public static final byte SERVER_TYPE_CLOTH_LEARN_INFO = 18;
   public static final byte SERVER_TYPE_SKIN_LEARN_INFO = 19;
   private boolean isEditable = true;
   public static final char TIME_CHAR = 'ε';
   private String timeStr = "";
   public static byte taskMinitue = 0;
   public static byte taskSecond = 0;
   public static long taskStartTime = 0L;
   private static String str = "";
   public byte[][] colorSigns;

   public boolean isEditable() {
      return this.isEditable;
   }

   public void setEditable(boolean isEditable) {
      this.isEditable = isEditable;
   }

   public UITextArea(short x, short y, short w, short h, UIForm form) {
      super(x, y, w, h, form);
      super.canFocus = true;
      this.textFont = MainCanvas.curFont;
      this.textAreaScroll = new UIScroll(super.positionX, super.positionY, super.width, super.height, (byte)0, true, form);
      this.setTextAreaType((byte)0);
   }

   public boolean focusWidgetPointAction() {
      return this.getTextAreaScroll() != null && this.getTextAreaScroll().focusWidgetPointAction();
   }

   public void keyInAction() {
      int cmd;
      if (super.isShowMenu) {
         if (this.getMenu() == null) {
            return;
         }

         if (MainCanvas.isInputDownOrHold(4096)) {
            this.getMenu().decreaseIndex();
         } else if (MainCanvas.isInputDownOrHold(8192)) {
            this.getMenu().increaseIndex();
         } else if (!MainCanvas.isInputDown(65536) && !MainCanvas.isInputDown(131072)) {
            if (MainCanvas.isInputDown(262144)) {
               super.isShowMenu = false;
            }
         } else {
            this.getMenu().saveForm();
            switch(MainCanvas.curForm.clientCommand) {
            case 196641:
               GOManager.m_bBattlefieldMenuId = (byte)(this.getMenu().getIndex() + 1);
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               MainCanvas.ni.send(cmd);
               this.m_bWhetherTime = false;
               super.isShowMenu = false;
               break;
            case 458753:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               if (cmd == 1966081) {
                  PCGem.m_bEnchaseRoute = 1;
                  PCGem.m_bEquipIndex = UIGrid.m_nGoods;
               }

               MainCanvas.ni.send(cmd);
               super.isShowMenu = false;
               break;
            case 720913:
               MainCanvas.ni.send(720911);
               UIForm.backUIFormAction();
               break;
            case 917507:
               if (PCGem.m_bAppendMenu == 1) {
                  PCGem.m_bEquipIndex = UIPicture.equipTypeForServer;
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  MainCanvas.ni.send(cmd);
                  super.isShowMenu = false;
               }
               break;
            case 1441805:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case -1610612726:
                  UIChat.intoPrivateChannel(Player.getInstance().getLoverId(), Player.getInstance().getLoverName(), true);
                  break;
               case 589831:
                  UITable.selectedPlayerId = Player.getInstance().getLoverId();
                  MainCanvas.ni.send(cmd);
                  break;
               case 720897:
                  SITeam.otherPlayerID = Player.getInstance().getLoverId();
                  MainCanvas.ni.send(cmd);
                  break;
               case 1441804:
                  UITopForm.createLocalTopForm((byte)0, (String)"Muốn xóa ý trung nhân?", "Xác nhận", "Hủy", 1441804, -1);
                  break;
               case 1441808:
                  MainCanvas.quitUI();
                  MainCanvas.mc.leftRightMenu();
                  MainCanvas.ni.send(cmd);
               }

               super.isShowMenu = false;
               break;
            case 1638401:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               PCClan.createClanKeyInMenu(cmd);
               super.isShowMenu = false;
               break;
            case 1638417:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               PCClan.clanAfficheInMenu(cmd);
               super.isShowMenu = false;
               break;
            case 1638419:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case 1638420:
                  MainCanvas.ni.send(cmd);
               default:
                  super.isShowMenu = false;
                  return;
               }
            case 1638426:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case 1638428:
                  MainCanvas.ni.send(cmd);
               default:
                  super.isShowMenu = false;
                  return;
               }
            case 1703937:
            case 1703938:
            case 1703952:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case -1610612711:
                  PCMail.SendMailValidity();
               case -1610612710:
               default:
                  break;
               case -1610612709:
                  PCMail.CompilationMail();
               }

               super.isShowMenu = false;
               break;
            case 1703971:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case -1610612698:
                  Serve();
                  break;
               case 1703956:
                  if (str != null && !str.equals("") && this.getContent() != null && !this.getContent().equals("") && str.equals(this.getContent()) && PCMail.m_bContentment != 5) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Xin đừng gửi nội dung giống nhau", "Xác nhận", "", -1, -2);
                     super.isShowMenu = false;
                     return;
                  }

                  if (!str.equals(this.getContent())) {
                     str = this.getContent();
                  }

                  MainCanvas.ni.send(1703956);
               }

               super.isShowMenu = false;
               break;
            case 1835009:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               PCKing.createKingKeyInMenu(cmd);
               super.isShowMenu = false;
               break;
            case 1835027:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case 1835031:
                  MainCanvas.ni.send(cmd);
               case 1835032:
               case 1835033:
               default:
                  break;
               case 1835034:
                  PCKing.secondSureTopform(cmd);
               }

               super.isShowMenu = false;
               break;
            default:
               if (this.serverType != 1) {
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  if (cmd == -1) {
                     super.isShowMenu = false;
                  } else {
                     super.isShowMenu = false;
                     MainCanvas.ni.send(cmd);
                  }
               }
            }
         }
      } else {
         if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
            if (MainCanvas.isInputDownOrHold(4096)) {
               if (!this.isHaveScroll()) {
                  return;
               }

               if (this.pointer <= 0) {
                  this.pointer = 0;
               } else {
                  --this.pointer;
               }

               this.getTextAreaScroll().setShowStartRow(this.pointer);
            } else if (MainCanvas.isInputDownOrHold(8192)) {
               if (!this.isHaveScroll()) {
                  return;
               }

               if (this.pointer >= this.contentFullRows - this.canShowRows) {
                  this.pointer = (short)(this.contentFullRows - this.canShowRows);
               } else {
                  ++this.pointer;
               }

               this.getTextAreaScroll().setShowStartRow(this.pointer);
            } else if (MainCanvas.isInputDown(2048)) {
               switch(this.getTextAreaType()) {
               case 1:
                  UIGrid skillgrid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(3);
                  if (skillgrid != null) {
                     this.setFocus(false, MainCanvas.curForm);
                     skillgrid.setFocus(true, MainCanvas.curForm);
                  }
                  break;
               case 2:
                  UIList list = (UIList)MainCanvas.curForm.getComponents().elementAt(4);
                  if (this.isFocus()) {
                     this.setFocus(false, MainCanvas.curForm);
                     list.setFocus(true, MainCanvas.curForm);
                  }
               }
            }
         } else {
            UIMenu menu;
            String[] cmdStrs;
            int[] cmds;
            int[] inns;
            if (this.serverType != 1) {
               menu = new UIMenu((byte)0);
               if (this.serverType != 0) {
                  if (this.serverType == 2) {
                     switch(MainCanvas.curForm.clientCommand) {
                     case 262154:
                        if (UIList.canReceive) {
                           UIMenu.formDeleteForm();
                           MainCanvas.ni.send(262149);
                        }
                        break;
                     case 262157:
                        if (UIList.canReturn) {
                           if (PCTask.haveMayBeChoice) {
                              UIMenu.formSaveForm();
                           }

                           MainCanvas.ni.send(262188);
                        }
                        break;
                     case 262178:
                        cmdStrs = new String[]{"Refresh"};
                        cmds = new int[]{262179};
                        inns = new int[1];
                        menu.addMenu(cmdStrs, cmds, inns);
                     }
                  } else if (this.serverType == 6) {
                     cmdStrs = new String[]{"Thiết lập lại", "Tra xem thù lao"};
                     cmds = new int[]{262173, 262175};
                     inns = new int[]{0, 2};
                     menu.addMenu(cmdStrs, cmds, inns);
                  } else if (this.serverType != 7) {
                     if (this.serverType == 8) {
                        cmdStrs = new String[]{"Nhận nhiệm vụ"};
                        cmds = new int[]{262180};
                        inns = new int[1];
                        menu.addMenu(cmdStrs, cmds, inns);
                     } else if (this.serverType == 10) {
                        MainCanvas.ni.send(2162696);
                     } else if (this.serverType == 11) {
                        MainCanvas.ni.send(2162709);
                     } else if (this.serverType == 12) {
                        MainCanvas.ni.send(2162720);
                     } else if (this.serverType == 13) {
                        MainCanvas.ni.send(2162731);
                     } else if (this.serverType == 14) {
                        MainCanvas.ni.send(2162740);
                     } else if (this.serverType == 15) {
                        MainCanvas.ni.send(2162748);
                     } else if (this.serverType == 16) {
                        MainCanvas.ni.send(2162761);
                     } else if (this.serverType == 17) {
                        MainCanvas.ni.send(2162768);
                     } else if (this.serverType == 18) {
                        MainCanvas.ni.send(2162775);
                     } else if (this.serverType == 19) {
                        MainCanvas.ni.send(2162782);
                     }
                  }
               }

               if (menu.getItems().size() > 0) {
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  if (this.getMenu() != null) {
                     this.getMenu().resetIndex();
                  }
               }
            }

            int[] cmds;
            switch(MainCanvas.curForm.clientCommand) {
            case 196641:
               menu = new UIMenu((byte)0);
               cmds = new int[]{196642, 196642, 196642};
               cmds = new int[]{3, 3, 3};
               String[] cmdStrs = new String[]{"Xếp hạng Thiên Nhân", "Xếp hạng Tu La", "Tổng xếp hạng"};
               menu.addMenu(cmdStrs, cmds, cmds);
               this.setMenu(menu);
               this.getMenu().resetIndex();
               super.isShowMenu = true;
               break;
            case 458753:
               if (PCMail.m_bForbidEnchase == 1 || PCAuction.m_bForbidEnchase == 1) {
                  return;
               }

               if (UIGrid.m_nGoodsIndex / 10000 >= 101 && UIGrid.m_nGoodsIndex / 10000 <= 120 && PCGem.m_bAppendMenu == 1) {
                  menu = new UIMenu((byte)0);
                  cmdStrs = new String[]{"Khảm nạm bảo thạch"};
                  cmds = new int[]{1966081};
                  inns = new int[]{2};
                  menu.addMenu(cmdStrs, cmds, inns);
                  this.setMenu(menu);
                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               }
               break;
            case 720913:
               MainCanvas.ni.send(720911);
               UIForm.backUIFormAction();
               break;
            case 720915:
               MainCanvas.ni.send(720916);
               break;
            case 851975:
               UIForm.backUIFormAction();
               break;
            case 917507:
               if (PCGem.m_bAppendMenu == 1) {
                  menu = new UIMenu((byte)0);
                  cmdStrs = new String[]{"Khảm nạm bảo thạch"};
                  cmds = new int[]{1966081};
                  inns = new int[]{2};
                  menu.addMenu(cmdStrs, cmds, inns);
                  this.setMenu(menu);
                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               }
               break;
            case 1441805:
               if (Player.getInstance().getLoverState() == 0) {
                  break;
               }

               menu = new UIMenu((byte)0);
               cmdStrs = new String[0];
               cmds = new int[0];
               inns = new int[0];
               String[] tmpStrs = new String[]{"Xem chi tiết", "Chat riêng ", "Dịch chuyển"};
               int[] tmpCmds = new int[]{589831, -1610612726, 1441808};
               int[] tmpInns = new int[]{2, 2, 3};
               if (SITeam.teamState == 0 || SITeam.headerID == Player.getInstance().getID()) {
                  tmpStrs = Util.addArray(tmpStrs, tmpStrs.length, "Mời tổ đội");
                  tmpCmds = Util.addArray(tmpCmds, tmpCmds.length, 720897);
                  tmpInns = Util.addArray((int[])tmpInns, tmpInns.length, (int)2);
               }

               if (Player.getInstance().getLoverState() == 1) {
                  tmpStrs = Util.addArray(tmpStrs, tmpStrs.length, "Giải trừ ý trung nhân");
                  tmpCmds = Util.addArray(tmpCmds, tmpCmds.length, 1441804);
                  tmpInns = Util.addArray((int[])tmpInns, tmpInns.length, (int)3);
               }

               cmdStrs = Util.addArrays((String[])cmdStrs, 0, (String[])tmpStrs);
               cmds = Util.addArrays((int[])cmds, 0, (int[])tmpCmds);
               inns = Util.addArrays((int[])inns, 0, (int[])tmpInns);
               menu.addMenu(cmdStrs, cmds, inns);
               this.setMenu(menu);
               this.getMenu().resetIndex();
               super.isShowMenu = true;
               break;
            case 1638401:
               this.setMenu(PCClan.createClanKeyInAction());
               super.isShowMenu = true;
               break;
            case 1638417:
               this.setMenu(PCClan.clanAfficheKeyInAction());
               super.isShowMenu = true;
               break;
            case 1638419:
               if (Player.getInstance().getClanRight() == PCClan.CLAN_RIGHT_HERDER || Player.getInstance().getClanRight() == PCClan.CLAN_RIGHT_VICE_HERDER) {
                  this.setMenu(PCClan.clanRightKeyInAction());
                  super.isShowMenu = true;
               }
               break;
            case 1638426:
               menu = new UIMenu((byte)0);
               cmdStrs = new String[]{"Báo cáo chi tiết"};
               cmds = new int[]{1638428};
               inns = new int[]{2};
               menu.addMenu(cmdStrs, cmds, inns);
               this.setMenu(menu);
               super.isShowMenu = true;
               break;
            case 1703937:
            case 1703938:
            case 1703952:
               menu = new UIMenu((byte)0);
               cmds = new int[]{-1610612709, -1610612711};
               cmds = new int[]{3, 3};
               menu.addMenu(Cons.SUBJECT, cmds, cmds);
               this.setMenu(menu);
               this.getMenu().resetIndex();
               super.isShowMenu = true;
               break;
            case 1703940:
            case 1703941:
               if (super.isShowMenu) {
                  if (MainCanvas.isInputDownOrHold(4096)) {
                     this.getMenu().decreaseIndex();
                  } else if (MainCanvas.isInputDownOrHold(8192)) {
                     this.getMenu().increaseIndex();
                  } else if (MainCanvas.isInputDown(262144)) {
                     super.isShowMenu = false;
                  } else if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
                     cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                     if (cmd == 1703952) {
                        PCMail.m_bWhetherRestore = true;
                        MainCanvas.ni.send(cmd);
                        super.isShowMenu = false;
                     } else if (cmd != -1) {
                        MainCanvas.ni.send(cmd);
                        super.isShowMenu = false;
                     }
                  }
               } else {
                  if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
                     break;
                  }

                  if (MainCanvas.curForm.clientCommand == 1703940) {
                     menu = new UIMenu((byte)0);
                     if (PCMail.receiveMoney > 0) {
                        menu.addMenu("Nhận tiền", 1703951, 3);
                     }

                     if (PCMail.haveAttach || PCMail.receiveMoney > 0) {
                        menu.addMenu("Nhận hết", 1703950, 3);
                     }

                     if (PCMail.canReturnMail) {
                        menu.addMenu("Hồi thư", 1703952, 2);
                     }

                     if (menu.getItems().size() > 0) {
                        this.setMenu(menu);
                        this.getMenu().resetIndex();
                        super.isShowMenu = true;
                     }
                  } else {
                     if (MainCanvas.curForm.clientCommand != 1703941) {
                        break;
                     }

                     menu = new UIMenu((byte)0);
                     if (PCMail.isPay) {
                        if (PCMail.haveAttach || PCMail.receiveMoney > 0) {
                           menu.addMenu("Nhận hết", 1703950, 3);
                        }

                        if (PCMail.canReturnMail) {
                           menu.addMenu("Hồi thư", 1703952, 2);
                        }
                     } else {
                        menu.addMenu("Trả tiền", 1703953, 3);
                        menu.addMenu("Trả thư", 1703957, 0);
                        if (PCMail.canReturnMail) {
                           menu.addMenu("Hồi thư", 1703952, 2);
                        }
                     }

                     if (menu.getItems().size() > 0) {
                        this.setMenu(menu);
                        this.getMenu().resetIndex();
                        super.isShowMenu = true;
                     }
                  }
               }
               break;
            case 1703969:
               if (this.isFocus() && !this.getContent().equals("") && this.getContent() != null) {
                  UIList list = (UIList)MainCanvas.curForm.getComponents().elementAt(4);
                  PCMail.m_bIndex = list.selectedIndex;
                  MainCanvas.ni.send(1703972);
               }
               break;
            case 1703971:
               if (this.getContent().trim().equals("")) {
                  Serve();
               } else {
                  menu = new UIMenu((byte)0);
                  cmdStrs = new String[]{"Sửa", "Gửi"};
                  cmds = new int[]{-1610612698, 1703956};
                  inns = new int[]{3, 3};
                  menu.addMenu(cmdStrs, cmds, inns);
                  this.setMenu(menu);
                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               }
               break;
            case 1835009:
               this.setMenu(PCKing.createKingKeyInAction());
               super.isShowMenu = true;
               break;
            case 1835027:
               if (Player.getInstance().getKingRight() == PCKing.KING_RIGHT_HERDER) {
                  menu = new UIMenu((byte)0);
                  cmdStrs = new String[]{"Đổi chức vị"};
                  cmds = new int[]{1835031};
                  inns = new int[]{1};
                  menu.addMenu(cmdStrs, cmds, inns);
                  this.setMenu(menu);
                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               } else if (Player.getInstance().getKingRight() != PCKing.KING_RIGHT_MEMBER) {
                  menu = new UIMenu((byte)0);
                  cmdStrs = new String[]{"Cách chức"};
                  cmds = new int[]{1835034};
                  inns = new int[]{3};
                  menu.addMenu(cmdStrs, cmds, inns);
                  this.setMenu(menu);
                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               }
               break;
            case 2555906:
               if (this.isFocus() && PCFarm.m_bWhetherGetHome == 0) {
                  Farm();
               }
            }

            if (MainCanvas.getState() == 27) {
               MainCanvas.setState(MainCanvas.mc.getOldState());
            }
         }

         if (this.getTextAreaScroll() != null) {
            this.getTextAreaScroll().keyInScroll(this.pointer, false);
         }
      }

   }

   public void draw(Graphics g) {
      g.setColor(Cons.COLOR_PANEL_BG);
      g.fillRect(super.positionX, super.positionY, super.width, super.height);
      switch(this.rimType) {
      case 0:
      default:
         break;
      case 1:
         g.setColor(Cons.COLOR_PANEL_BG);
         g.fillRect(super.positionX + 1, super.positionY + 1, super.width - 2, super.height - 2);
         g.setColor(Cons.COLOR_PANEL_BORDER_3);
         g.drawRect(super.positionX, super.positionY, super.width - 1, super.height - 1);
         break;
      case 2:
         g.setColor(Cons.COLOR_PANEL_BORDER_3);
         g.drawRect(super.positionX, super.positionY, super.width - 1, super.height - 1);
         g.fillRect(super.positionX, super.positionY, this.outRimW, super.height);
         g.fillRect(super.positionX + super.width - this.outRimW, super.positionY, this.outRimW, super.height);
         g.drawRect(super.positionX + this.outRimW + 1, super.positionY + this.outRimH + 1, super.width - 2 * (this.outRimW + 1) - 1, super.height - 2 * (this.outRimH + 1) - 1);
      }

      this.drawContent(g);
      if (this.textAreaScroll != null && this.isHaveScroll()) {
         this.textAreaScroll.draw(g);
      }

      if (MainCanvas.curForm != null) {
         if (this.isFocus()) {
            if (MainCanvas.getState() == 8 || MainCanvas.curForm.clientCommand == 1703937 || MainCanvas.curForm.clientCommand == 1703938 || MainCanvas.curForm.clientCommand == 1703940 || MainCanvas.curForm.clientCommand == 1703941 || MainCanvas.curForm.clientCommand == 262147 || MainCanvas.curForm.clientCommand == 1703952 || MainCanvas.curForm.clientCommand == 262176 || MainCanvas.curForm.clientCommand == 393230 || MainCanvas.curForm.clientCommand == 262157 || MainCanvas.curForm.clientCommand == 262154 || MainCanvas.curForm.clientCommand == 458754 || MainCanvas.curForm.clientCommand == 458775 || MainCanvas.curForm.clientCommand == 262178 || MainCanvas.curForm.clientCommand == 2293874 || MainCanvas.curForm.clientCommand == 1703969 || MainCanvas.curForm.clientCommand == 2490385 || MainCanvas.curForm.clientCommand == 262188 || MainCanvas.curForm.clientCommand == 2555906 || MainCanvas.curForm.clientCommand == 2621443 || MainCanvas.curForm.clientCommand == 2621442 || MainCanvas.curForm.clientCommand == 2621446 || MainCanvas.curForm.clientCommand == -1610612632) {
               Util.drawSelectedKuangEffect(g, super.positionX, super.positionY, super.width, super.height);
            }
         } else if (MainCanvas.curForm != null && MainCanvas.curForm.clientCommand == 393230) {
            this.resetTextArea();
         }
      }

   }

   private void drawContent(Graphics g) {
      if (this.strArray != null) {
         switch(this.rimType) {
         case 0:
            this.textAreaX = super.positionX;
            this.textAreaY = super.positionY;
            this.textAreaW = super.width;
            this.textAreaH = super.height;
            break;
         case 1:
            this.textAreaX = (short)(super.positionX + 1);
            this.textAreaY = (short)(super.positionY + 1);
            this.textAreaW = (short)(super.width - 2);
            this.textAreaH = (short)(super.height - 2);
            break;
         case 2:
            this.textAreaX = (short)(super.positionX + this.outRimW + 1 + 1);
            this.textAreaY = (short)(super.positionY + this.outRimH + 1 + 1);
            this.textAreaW = (short)(super.width - 2 * (this.outRimW + 1 + 1));
            this.textAreaH = (short)(super.height - 2 * (this.outRimH + 1 + 1));
         }

         int length = false;
         int length = this.strArray.length;
         if (length > this.canShowRows) {
            length = this.pointer + this.canShowRows;
         }

         int timeCounter = false;
         int i = this.pointer;

         for(int j = 0; i < length; ++j) {
            int offSet = 0;
            int distanceLeft = 4;
            int distanceUp = 3;
            char[] tmpChars = this.strArray[i].toCharArray();

            for(int m = 0; m < tmpChars.length; ++m) {
               if (this.colorSigns[i][m] == 0) {
                  g.setColor(Cons.COLOR_PANEL_BORDER_3);
               } else {
                  g.setColor(this.getColor());
               }

               if (tmpChars[m] == 949) {
                  this.drawTaskTime(g, this.textAreaX + offSet + distanceLeft + 1, this.textAreaY + distanceUp + j * (MainCanvas.CHARH + 1));
                  break;
               }

               g.drawChar(tmpChars[m], this.textAreaX + offSet + distanceLeft + 1, this.textAreaY + distanceUp + j * (MainCanvas.CHARH + 1), 20);
               offSet += this.textFont.charWidth(tmpChars[m]);
            }

            ++i;
         }

      }
   }

   private void drawTaskTime(Graphics g, int x, int y) {
      StringBuffer sb = new StringBuffer();
      sb.append(taskMinitue).append(":").append(taskSecond);
      if (taskMinitue == 0 && taskSecond == 0) {
         g.setColor(16711680);
      } else {
         g.setColor(Cons.COLOR_PANEL_BORDER_3);
      }

      g.drawString(sb.toString(), x, y, 20);
   }

   public String getContent() {
      return this.content;
   }

   public void setContent(String content) {
      this.content = content;
      if (this.content == null) {
         this.content = " ";
      }

      this.strArray = this.colorChangeLine(this.content, this.textAreaW - this.textFont.stringWidth("Chính") / 2 - MainCanvas.ui_3Img.getWidth(), this.textFont);
      if (this.strArray != null) {
         this.contentFullRows = (short)this.strArray.length;
      }

      this.canShowRows = (short)((this.textAreaH - 0) / (MainCanvas.CHARH + 1));
      this.resetTextArea();
   }

   public void resetTextArea() {
      this.pointer = 0;
      this.getTextAreaScroll().resetScrollPositionY();
   }

   private boolean isHaveScroll() {
      return this.canShowRows < this.contentFullRows;
   }

   public UIScroll getTextAreaScroll() {
      return this.textAreaScroll;
   }

   public void setTextAreaScroll(UIScroll textAreaScroll) {
      this.textAreaScroll = textAreaScroll;
   }

   public byte getRimType() {
      return this.rimType;
   }

   public void setRimType(byte rimType) {
      this.rimType = rimType;
      short scrollX = 0;
      short scrollY = 0;
      short scrollW = 0;
      short scrollH = 0;
      switch(this.rimType) {
      case 0:
         this.textAreaX = super.positionX;
         this.textAreaY = super.positionY;
         this.textAreaW = super.width;
         this.textAreaH = super.height;
         scrollX = (short)(this.textAreaX + this.textAreaW - MainCanvas.ui_3Img.getWidth());
         scrollY = this.textAreaY;
         scrollW = 0;
         scrollH = this.textAreaH;
         break;
      case 1:
         this.textAreaX = (short)(super.positionX + 1);
         this.textAreaY = (short)(super.positionY + 1);
         this.textAreaW = (short)(super.width - 2);
         this.textAreaH = (short)(super.height - 2);
         scrollX = (short)(super.positionX + super.width - MainCanvas.ui_3Img.getWidth());
         scrollY = super.positionY;
         scrollW = 0;
         scrollH = super.height;
         break;
      case 2:
         this.textAreaX = (short)(super.positionX + this.outRimW + 1 + 1);
         this.textAreaY = (short)(super.positionY + this.outRimH + 1 + 1);
         this.textAreaW = (short)(super.width - 2 * (this.outRimW + 1 + 1));
         this.textAreaH = (short)(super.height - 2 * (this.outRimH + 1 + 1));
         scrollX = (short)(super.positionX + super.width - this.outRimW - 1 - MainCanvas.ui_3Img.getWidth());
         scrollY = (short)(super.positionY + this.outRimH + 1);
         scrollW = 0;
         scrollH = (short)(super.height - 2 * (this.outRimH + 1));
      }

      this.textAreaScroll.setPositionX(scrollX);
      this.textAreaScroll.setPositionY(scrollY);
      this.textAreaScroll.setWidth(scrollW);
      this.textAreaScroll.setHeight(scrollH);
   }

   public int getColor() {
      if (this.color == -1) {
         this.color = Cons.COLOR_TEXT_FONT;
      }

      return this.color;
   }

   public void setColor(int color) {
      this.color = color;
   }

   public short getContentFullRows() {
      return this.contentFullRows;
   }

   public void setContentFullRows(short contentFullRows) {
      this.contentFullRows = contentFullRows;
   }

   public short getCanShowRows() {
      return this.canShowRows;
   }

   public void setCanShowRows(short canShowRows) {
      this.canShowRows = canShowRows;
   }

   public byte getTextAreaType() {
      return this.textareatype;
   }

   public void setTextAreaType(byte t) {
      this.textareatype = t;
   }

   public String[] colorChangeLine(String s, int useWidth, Font font) {
      char[] tempChars = s.toCharArray();
      int lg = tempChars.length;
      int[] sign = new int[34];
      int number = 0;
      StringBuffer sb = new StringBuffer();
      Vector tempV = new Vector();

      int timeCounter;
      for(timeCounter = 0; timeCounter < lg; ++timeCounter) {
         if (tempChars[timeCounter] != '\n') {
            tempV.addElement(new Character(tempChars[timeCounter]));
         }

         if (tempChars[timeCounter] != '$') {
            sb.append(tempChars[timeCounter]);
         }
      }

      timeCounter = 0;
      StringBuffer timeSb = new StringBuffer();
      int i = 0;

      int time;
      for(int ii = tempV.size(); i < ii; ++i) {
         if (tempV.elementAt(i).toString().charAt(0) == '$') {
            sign[number] = i;
            ++number;
         }

         if (timeCounter == 1) {
            timeSb.append(tempV.elementAt(i).toString().charAt(0));
         } else if (timeCounter == 2) {
            timeSb.setLength(timeSb.length() - 1);
            this.timeStr = timeSb.toString();

            try {
               time = Integer.parseInt(this.timeStr);
               taskMinitue = (byte)(time / 60);
               taskSecond = (byte)(time % 60);
            } catch (NumberFormatException var21) {
               var21.printStackTrace();
               taskMinitue = 0;
               taskSecond = 0;
            }

            timeCounter = 0;
         }

         if (tempV.elementAt(i).toString().charAt(0) == 949) {
            ++timeCounter;
         }
      }

      String[] strs = Util.wrapText(sb.toString(), useWidth, font);
      lg = strs.length;
      this.colorSigns = new byte[lg][];
      int[][] start_end = new int[lg][2];

      int i;
      for(time = 0; time < lg; ++time) {
         i = strs[time].length();
         this.colorSigns[time] = new byte[i];
         if (time == 0) {
            start_end[time][0] = 0;
            start_end[time][1] = i - 1;
         } else {
            start_end[time][0] = start_end[time - 1][1] + 1;
            start_end[time][1] = start_end[time][0] + i - 1;
         }
      }

      Vector vector = new Vector(4);
      if (number % 2 != 0) {
         --number;
      }

      int b;
      int i;
      int j;
      for(i = 0; i < number; i += 2) {
         sign[i] -= i;
         sign[i + 1] -= i + 1 + 1;
         int a = Util.getColorPlace(sign[i], start_end);
         b = Util.getColorPlace(sign[i + 1], start_end);
         if (a == b) {
            int[] cs = new int[]{a, sign[i] - start_end[a][0], sign[i + 1] - start_end[a][0]};
            vector.addElement(cs);
         } else {
            i = b - a;
            if (i > 1) {
               for(j = a + 1; j < b; ++j) {
                  int[] cs = new int[]{j, 0, strs[j].length() - 1};
                  vector.addElement(cs);
               }
            }

            int[] cs = new int[]{a, sign[i] - start_end[a][0], start_end[a][1] - start_end[a][0]};
            vector.addElement(cs);
            cs = new int[]{b, 0, sign[i + 1] - start_end[b][0]};
            vector.addElement(cs);
         }
      }

      lg = vector.size();
      int[][] signs = new int[lg][];
      Enumeration e = vector.elements();

      for(b = 0; e.hasMoreElements(); ++b) {
         signs[b] = (int[])e.nextElement();
      }

      for(i = 0; i < lg; ++i) {
         for(j = signs[i][1]; j <= signs[i][2]; ++j) {
            this.colorSigns[signs[i][0]][j] = 1;
         }
      }

      for(i = 0; i < strs.length; ++i) {
         strs[i] = strs[i].trim();
      }

      return strs;
   }

   public void addUIScroll() {
      if (this.getTextAreaScroll() != null) {
         this.getTextAreaScroll().allRowNum = this.getContentFullRows();
         this.getTextAreaScroll().canShowRowNum = this.getCanShowRows();
         this.getTextAreaScroll().setShowStartRow((short)0);
      }
   }

   public short getPointer() {
      return this.pointer;
   }

   private static final void Serve() {
      final Form form = new Form("Trung tâm phục vụ khách hàng");
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      final TextField tf = new TextField("Xin nhập nội dung vấn đề", "", 100, 0);
      final TextField jixing = new TextField("Xin nhập loại máy", "", 20, 0);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.append(jixing);
      form.append(tf);
      UITextArea textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(3);
      UITextField text = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
      if (textarea.getContent() != null && !textarea.getContent().equals("")) {
         tf.setString(textarea.getContent());
      }

      if (text.getSb().toString().trim() != null && !text.getSb().toString().trim().equals("")) {
         jixing.setString(text.getSb().toString().trim());
      }

      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            UITextArea textarea;
            UITextField text;
            UITitle menuBar;
            if (c == okCmd) {
               Alert alert;
               if (!jixing.getString().equals("") && jixing.getString() != null) {
                  if (!tf.getString().equals("") && tf.getString() != null) {
                     if (UITextArea.str.equals(tf.getString()) && PCMail.m_bContentment != 5) {
                        alert = new Alert(Cons.C_STR[9], "Xin đừng nhập nội dung giống nhau!", (Image)null, AlertType.ERROR);
                        MainCanvas.aMidlet.display.setCurrent(alert, form);
                        return;
                     }

                     if (MainCanvas.CharacterValidate(tf.getString(), (byte)3) && MainCanvas.CharacterValidate(jixing.getString(), (byte)3)) {
                        textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(3);
                        text = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
                        menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                        textarea.setContent(tf.getString());
                        text.setSb(new StringBuffer(jixing.getString()));
                        textarea.addUIScroll();
                        menuBar.setMenuText("Thao tác", 0);
                        MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
                        return;
                     }

                     alert = new Alert(Cons.C_STR[9], "Nội dung có từ cấm, xin nhập lại!", (Image)null, AlertType.ERROR);
                     MainCanvas.aMidlet.display.setCurrent(alert, form);
                     return;
                  }

                  alert = new Alert(Cons.C_STR[9], "Xin nhập nội dung!", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
               }

               alert = new Alert(Cons.C_STR[9], "Xin nhập nội dung loại máy!", (Image)null, AlertType.ERROR);
               MainCanvas.aMidlet.display.setCurrent(alert, form);
               return;
            } else if (c == exitCmd) {
               form.removeCommand(okCmd);
               form.removeCommand(exitCmd);
               textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(3);
               textarea.setContent("");
               text = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
               text.setSb(new StringBuffer(""));
               menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
               menuBar.setMenuText("Nhập vào", 0);
               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }

   private static final void Farm() {
      final Form form = new Form("Tuyên ngôn nông trường");
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      final TextField tf = new TextField("Xin nhập nội dung", "", 100, 0);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.append(tf);
      UITextArea textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(15);
      if (textarea.getContent() != null && !textarea.getContent().equals("")) {
         tf.setString(textarea.getContent());
      }

      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            if (c == okCmd) {
               if (!MainCanvas.CharacterValidate(tf.getString(), (byte)3)) {
                  Alert alert = new Alert(Cons.C_STR[9], "Nội dung không phù hợp, nhập lại!", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
               }

               UITextArea textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(15);
               textarea.setContent(tf.getString());
               if (!tf.getString().equals("") && tf.getString() != null) {
                  PCFarm.m_sFarmEnounce = tf.getString();
               } else {
                  PCFarm.m_sFarmEnounce = "";
               }

               MainCanvas.ni.send(2555920);
               textarea.addUIScroll();
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            } else if (c == exitCmd) {
               form.removeCommand(okCmd);
               form.removeCommand(exitCmd);
               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }
}

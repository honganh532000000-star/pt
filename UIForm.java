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

public class UIForm extends UIComponent {
   public byte gridSelectRow = 0;
   private Vector components = new Vector();
   public int clientCommand = 0;
   public byte foldedIndex = 0;
   public String warningStr = "";
   public static byte lockIndex = -1;
   public static boolean isEndPic = false;
   public static short baochouCol = 0;
   public static String str_1;
   public static String str_2;
   public static boolean netFailOverMount = false;
   public UIComponent focusWidget = null;
   public byte focusWidgetId = -1;
   public static final byte SELECTED_EFFECT_STATE_NONE = 0;
   public static final byte SELECTED_EFFECT_STATE_COMPARE = 1;
   public static final byte SELECTED_EFFECT_MOVE = 2;
   private static byte[] indexType = new byte[]{-1, -1};
   private static int[] compareIndex = new int[]{-1, -1};
   public static final byte COMPARE_INDEX_BAG = 0;
   public static final byte COMPARE_INDEX_EQUIP = 1;
   public static final byte COMPARE_INDEX_SHOP = 2;
   public static final byte COMPARE_INDEX_STORAGE = 3;
   public static final byte COMPARE_INDEX_BUSINESS = 4;
   public static final byte COMPARE_INDEX_TASK = 5;
   public static final int INTERFACE_SHOP_NUM = 11;
   public static final int INTERFACE_SHOP_SELLER = 3;
   public static final int INTERFACE_SHOP_BAG = 5;
   public static final int INTERFACE_SHOP_GOODSNAME = 4;
   public static final int INTERFACE_SHOP_GOODSPRICE = 10;
   public static final int INTERFACE_SHOP_PLAYERMONEY = 7;
   public static final int INTERFACE_EQUIP_NUM = 39;
   public static final int INTERFACE_EQUIP_EQUIPNAME = 22;
   public static final int INTERFACE_EQUIP_BAG = 23;
   public static final int INTERFACE_EQUIP_主 = 9;
   public static final int INTERFACE_EQUIP_副 = 10;
   public static final int INTERFACE_EQUIP_戒左 = 16;
   public static final int INTERFACE_EQUIP_戒右 = 17;
   public static final int INTERFACE_EQUIP_饰左 = 18;
   public static final int INTERFACE_EQUIP_饰右 = 19;
   public static final int INTERFACE_PACKAGE_背包 = 10;
   public static final int INTERFACE_STORAGE_NUM = 11;
   public static final int INTERFACE_STORAGE = 6;
   public static final int INTERFACE_STORAGE_BAG = 8;
   public static final int INTERFACE_STORAGE_GOODS_NAME = 7;
   public static final int INTERFACE_COMPARE_NUM = 24;
   public static final int INTERFACE_COMPARE_EQUIP_NAME = 22;
   public static final int INTERFACE_COMPARE_BAG = 23;
   public static int tradeMyMoney = 0;
   public static int alertMoney = 0;
   public static final int TRADE_MY_MONEY_TF = 27;
   public static final int TRADE_MY_NAME_LABEL = 4;
   public static final int TRADE_OTHER_NAME_LABEL = 16;
   public static final int TRADE_MY_GOODS = 5;
   public static final int TRADE_OTHER_GOODS = 17;
   public static boolean isOtherPic = false;
   public static byte curPicNum = 0;
   public static boolean isClear = false;
   public static int myMoney = 0;
   public static final int CHANGE_SHOP_BAG = 3;
   public static final int CHANGE_SHOP_UNIT = 11;
   public static final int CHANGE_PLAYER_BAG = 5;
   public static final int CHANGE_GOODS_NAME_LABEL = 4;
   public static final int PLAYER_BASE_UPLEVEL_BUTTON = 36;
   public static final int PLAYER_BASE_CUR_EXP = 33;
   public static final int PLAYER_BASE_BASE_EXP = 35;
   private byte arithmometer = 0;
   private byte arithmometer_1 = 0;
   private byte m_bFinishArithmometer = 0;
   private boolean finish;
   private short textareaX;
   private short textareaY;
   private short textareaH;
   private short textareaW;
   private short lableX;
   private short lableY;
   private short lableW;
   final int FACE_NUM = 3;
   int faceSelect;
   public static Image encryptImg = null;
   static long conStartTime;
   PassPort loginPP = null;
   public static boolean isInitAtt = false;
   public static int[] attColor = new int[]{16777215, 16711680};
   public static String[] m_sAmendNickname = new String[2];
   public static int[] m_nDelieveX;
   public static int[] m_nDelieveY;
   public static byte[] m_bState;
   public static String[] shoppingList;
   public static byte[] shoppingListId;
   public static int shoppingID = 0;

   public UIForm(short x, short y, short w, short h) {
      super(x, y, w, h, (UIForm)null);
      lockIndex = -1;
   }

   public final void addWarningStr(String str) {
      if (!MainCanvas.SUPPORT_POINTER) {
         this.warningStr = str;
         this.addWarningStr();
      }

   }

   public final void addWarningStr() {
      if (this.warningStr.length() > 0) {
         SIChat.addChatLocal(this.warningStr);
      } else {
         SIChat.addChatLocal("");
      }

   }

   public static byte[] getIndexType() {
      return indexType;
   }

   public static int[] getCompareIndex() {
      return compareIndex;
   }

   public static void setCompareTI(byte type, int index) {
      if (compareIndex[0] == -1) {
         indexType[0] = type;
         compareIndex[0] = index;
         switch(MainCanvas.curForm.clientCommand) {
         case 1245185:
         case 1245186:
            break;
         default:
            MainCanvas.ni.send(1245185);
         }
      } else {
         indexType[1] = type;
         compareIndex[1] = index;
         MainCanvas.ni.send(1245186);
      }

   }

   public static void resetCompareTI() {
      for(int i = 0; i < 2; ++i) {
         indexType[i] = -1;
         compareIndex[i] = -1;
      }

   }

   public void resetExchangeVal() {
      int i = 0;

      for(int ii = PCPackage.exchangeIndex.length; i < ii; ++i) {
         PCPackage.exchangeIndex[i] = -1;
      }

      for(i = 0; i < this.components.size(); ++i) {
         if ((UIComponent)this.components.elementAt(i) instanceof UIGrid) {
            UIGrid grid = (UIGrid)this.components.elementAt(i);
            grid.gridExchangeIndex[0] = grid.gridExchangeIndex[1] = -1;
            if (grid.clientType == 6 && grid.getGridFormType() == 2) {
               PCStorage.setGridMoveToGrid(true);
            }

            if (grid.clientType == 29) {
               PCStorage.setGridMoveToGrid(true);
            }
         }
      }

      PCPackage.moveNum = 0;
   }

   public static void equipEffect() {
      switch(UIGrid.getEquipPartType()) {
      case 1:
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(9)).setSelected(true);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(10)).setSelected(true);
         break;
      case 2:
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(16)).setSelected(true);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(17)).setSelected(true);
         break;
      case 3:
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(18)).setSelected(true);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(19)).setSelected(true);
      }

   }

   public static void clearTradeVal() {
      tradeMyMoney = 0;
      alertMoney = 0;
      UIGrid.resetTradeSign();
      UIPicture.isMyTradeLock = false;
      UIPicture.isOtherTradeLock = false;
      curPicNum = 0;
      isOtherPic = false;
   }

   public void draw(Graphics g) {
      try {
         UIComponent.especialDealChange();
         UIComponent.especialDealEquip();
         UIComponent.especialDealEquipFix();
         UIComponent.especialDealOtherEquip();
         UIComponent.especialDealCompare();
         UIComponent.especialDealShop();
         UIComponent.especialDealStorage();
         UIComponent.especialDeaTrade2();
      } catch (Exception var16) {
      }

      g.setColor(Cons.COLOR_BACKGROUND);
      g.fillRect(0, 0, MainCanvas.screenW, MainCanvas.screenH);
      UIComponent comTmp = null;
      UIComponent comGridTmp = null;
      UIComponent comPicTmp = null;
      UIPanel playerInfoPanel = null;
      Map.getInstance().drawWorldMap(g, this.clientCommand);
      int i = 0;

      int i;
      UIComponent curUic;
      for(i = this.components.size(); i < i; ++i) {
         curUic = (UIComponent)this.components.elementAt(i);
         if (curUic instanceof UIPanel) {
            playerInfoPanel = (UIPanel)curUic;
         }

         UILabel label;
         if (this != null && (this.clientCommand == 393225 || this.clientCommand == 393229)) {
            if (!(curUic instanceof UILabel) && !(curUic instanceof UITextArea)) {
               g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
               curUic.draw(g);
            } else {
               g.setClip(playerInfoPanel.INNER_XYWH[0], playerInfoPanel.INNER_XYWH[1] + 2, playerInfoPanel.INNER_XYWH[2], playerInfoPanel.INNER_XYWH[3] - 6);
               curUic.draw(g);
               g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
            }
         } else if (this != null && this.clientCommand == 2293873 && i == 4) {
            if (curUic instanceof UILabel) {
               label = (UILabel)curUic;
               if (!"".equals(label.getText())) {
                  g.setColor(8519935);
                  g.fillRect(12, label.getPositionY(), MainCanvas.screenW - 24, MainCanvas.CHARH);
                  g.setColor(16777215);
                  label.drawRollStr(g, label.getText(), 24, label.getPositionY(), MainCanvas.screenW - 48, (byte)0, (byte)3, true);
               }
            }
         } else if (this != null && MainCanvas.getState() == 18 && i == 4) {
            ((UILabel)curUic).draw(g, 16711680);
         } else if (this != null && MainCanvas.getState() == 17 && i == 16) {
            if (curUic instanceof UILabel) {
               label = (UILabel)curUic;
               if (!label.isFocus()) {
                  if (label.getText().equals("Đổi nick")) {
                     ((UILabel)curUic).draw(g, 16711680);
                  } else {
                     ((UILabel)curUic).draw(g, 6045209);
                  }
               } else {
                  label.draw(g);
               }
            }
         } else {
            ((UIComponent)this.components.elementAt(i)).draw(g);
         }

         if (((UIComponent)this.components.elementAt(i)).isShowMenu && ((UIComponent)this.components.elementAt(i)).isFocus()) {
            comTmp = (UIComponent)this.components.elementAt(i);
         }

         if ((UIComponent)this.components.elementAt(i) instanceof UIGrid && ((UIComponent)this.components.elementAt(i)).isFocus()) {
            comGridTmp = (UIComponent)this.components.elementAt(i);
         }

         if ((UIComponent)this.components.elementAt(i) instanceof UIPicture && ((UIComponent)this.components.elementAt(i)).isFocus()) {
            comPicTmp = (UIComponent)this.components.elementAt(i);
         }

         try {
            switch(this.clientCommand) {
            case 393219:
               label = (UILabel)this.components.elementAt(36);
               UILabel curExpLabel = (UILabel)this.components.elementAt(33);
               UILabel baseExpLabel = (UILabel)this.components.elementAt(35);
               if (curExpLabel.getLongNum() >= baseExpLabel.getLongNum()) {
                  int[] oldColor = new int[]{Cons.COLOR_TEXT_BORDER, Cons.COLOR_TEXT_BG, Cons.COLOR_TEXT_FONT};
                  int[] curColor = new int[]{Cons.COLOR_TEXT_BORDER, Cons.COLOR_TEXT_BORDER, Cons.COLOR_PANEL_BG};
                  int time = 4;
                  label.drawLabelEffect(g, oldColor, curColor, time);
               }
            }
         } catch (Exception var15) {
         }
      }

      i = 0;

      for(i = this.components.size(); i < i; ++i) {
         curUic = (UIComponent)this.components.elementAt(i);
         if (curUic.isFocus() && curUic.haveTopTip() && curUic.isTipState && MainCanvas.curTopForm == null) {
            if (curUic.t2 != 0L && System.currentTimeMillis() - curUic.t2 > 4000L || curUic.isInterruptTopTip) {
               curUic.isTipState = false;
            }

            if (curUic.t1 != 0L && System.currentTimeMillis() - curUic.t1 > 2000L) {
               curUic.drawTopTip(g);
               if (curUic.beginRecordT2) {
                  curUic.t2 = System.currentTimeMillis();
                  curUic.beginRecordT2 = false;
               }

               if (MainCanvas.isInputDown(-1)) {
                  curUic.isInterruptTopTip = true;
               }
            }
         }
      }

      if (Player.getInstance().getAttacked()) {
         this.drawAttatckHit(g);
      }

      UIPicture pic;
      UIPicture picture_1;
      if (this.clientCommand == 2097155) {
         pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(8);
         picture_1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(7);
         UIPicture picture_2 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(9);
         if (PCGamble.Begin) {
            MainCanvas.stuffMImg.draw(g, pic.positionX + (pic.getWidth() >> 1) - 7, pic.positionY + (pic.getHeight() >> 1) - 7, PCGamble.picture[PCGamble.index]);
         }

         if (!PCGamble.Begin_1) {
            if (PCGamble.Begin && PCGamble.renovate) {
               if (PCGamble.m_nCircleCounter > PCGamble.m_nCircle) {
                  if (MainCanvas.countTick % 9 == 1) {
                     PCGamble.index = PCGamble.server_index;
                     ++this.arithmometer;
                  }

                  if (this.arithmometer >= 4) {
                     this.arithmometer = 0;
                     PCGamble.Eliminate();
                  }
               } else if (PCGamble.m_nCircleCounter >= 0 && PCGamble.m_nCircleCounter < PCGamble.m_nCircle / 2) {
                  ++PCGamble.index;
               } else if (PCGamble.m_nCircleCounter >= PCGamble.m_nCircle / 2 && PCGamble.m_nCircleCounter < PCGamble.m_nCircle - 1) {
                  if (MainCanvas.countTick % 3 == 1) {
                     PCGamble.index += Util.genRandom(0, 6);
                  }
               } else if (PCGamble.m_nCircleCounter >= PCGamble.m_nCircle - 1 && MainCanvas.countTick % 7 == 1) {
                  PCGamble.index += Util.genRandom(0, 6);
               }

               if (PCGamble.index > 6) {
                  ++PCGamble.m_nCircleCounter;
                  PCGamble.index = 0;
               }
            }
         } else {
            g.setColor(Cons.COLOR_SCROLL_LINE_1);
            if (this.arithmometer_1 % 2 == 0) {
               g.drawString("Âm", picture_1.positionX + (14 + 2 - Util.getStrLen("阴")) / 2, picture_1.positionY + (14 + 2 - MainCanvas.CHARH) / 2, 20);
            } else if (this.arithmometer_1 % 2 == 1) {
               g.drawString("Dương", picture_2.positionX + (14 + 2 - Util.getStrLen("阳")) / 2, picture_2.positionY + (14 + 2 - MainCanvas.CHARH) / 2, 20);
            }

            if (this.arithmometer_1 >= 0 && this.arithmometer_1 <= 20) {
               if (MainCanvas.countTick % 2 == 1) {
                  ++this.arithmometer_1;
               }
            } else if (this.arithmometer_1 >= 20 && this.arithmometer_1 < 40 && MainCanvas.countTick % 6 == 1) {
               ++this.arithmometer_1;
            }

            if (this.arithmometer_1 >= 35) {
               PCGamble.Begin_1 = false;
               this.finish = true;
               if (PCGamble.award_1) {
                  if (PCGamble.BigOrSmall == 1) {
                     picture_1.setFocus(true, MainCanvas.curForm);
                     picture_2.setFocus(false, MainCanvas.curForm);
                  } else if (PCGamble.BigOrSmall == 2) {
                     picture_2.setFocus(true, MainCanvas.curForm);
                     picture_1.setFocus(false, MainCanvas.curForm);
                  }
               } else if (PCGamble.BigOrSmall == 1) {
                  picture_1.setFocus(false, MainCanvas.curForm);
                  picture_2.setFocus(true, MainCanvas.curForm);
               } else if (PCGamble.BigOrSmall == 2) {
                  picture_2.setFocus(false, MainCanvas.curForm);
                  picture_1.setFocus(true, MainCanvas.curForm);
               }
            }
         }

         if (this.arithmometer_1 == 35) {
            ++this.m_bFinishArithmometer;
         }

         if (this.finish && this.m_bFinishArithmometer > 15) {
            this.finish = false;
            PCGamble.BigOrSmall = 0;
            this.arithmometer_1 = 0;
            PCGamble.Eliminate();
            this.m_bFinishArithmometer = 0;
         }
      } else if (this.clientCommand != 1376260 && this.clientCommand != 1376265) {
         if (this.clientCommand == 1703939) {
            UITable table = (UITable)MainCanvas.curForm.getComponents().elementAt(3);
            g.setClip(table.getPositionX(), table.getPositionY(), table.getWidth(), table.getHeight() - 10);

            for(i = 0; i < m_bState.length; ++i) {
               drawDelieve(g, m_nDelieveX[i], m_nDelieveY[i], m_bState[i]);
            }

            g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
         } else if (this.clientCommand != 2425024 && this.clientCommand != 2425077) {
            UITextField chargeName;
            UITextField myName;
            UITitle menuBar;
            if (this.clientCommand == 2621443) {
               chargeName = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
               myName = (UITextField)MainCanvas.curForm.getComponents().elementAt(6);
               menuBar = (UITitle)this.getComponents().elementAt(1);
               UIRadio radio = (UIRadio)this.getComponents().elementAt(7);
               UITextArea ta = (UITextArea)this.getComponents().elementAt(8);
               if (!chargeName.isFocus() && !myName.isFocus()) {
                  if (radio.isFocus()) {
                     if (radio.getSelectIndex() == radio.getSureIndex()) {
                        menuBar.setMenuText("Nạp thẻ", 0);
                     } else {
                        menuBar.setMenuText("Lựa chọn", 0);
                     }
                  } else if (ta.isFocus()) {
                     menuBar.setMenuText("", 0);
                  }
               } else {
                  String nameStr = chargeName.getSb().toString().trim();
                  String pswStr = myName.getSb().toString().trim();
                  if (nameStr != null && !"".equals(nameStr) && pswStr != null && !"".equals(pswStr)) {
                     menuBar.setMenuText("Nạp thẻ", 0);
                  } else {
                     menuBar.setMenuText("Nhập vào", 0);
                  }
               }
            } else {
               String chargeStr;
               String myStr;
               if (this.clientCommand == 2621445) {
                  chargeName = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
                  myName = (UITextField)MainCanvas.curForm.getComponents().elementAt(6);
                  menuBar = (UITitle)this.getComponents().elementAt(1);
                  if (chargeName.isFocus() || myName.isFocus()) {
                     chargeStr = chargeName.getSb().toString().trim();
                     myStr = myName.getSb().toString().trim();
                     if (chargeStr != null && !"".equals(chargeStr) && myStr != null && !"".equals(myStr)) {
                        menuBar.setMenuText("Nạp thẻ", 0);
                     } else {
                        menuBar.setMenuText("Nhập vào", 0);
                     }
                  }
               } else if (this.clientCommand == 2621447) {
                  chargeName = (UITextField)MainCanvas.curForm.getComponents().elementAt(5);
                  myName = (UITextField)MainCanvas.curForm.getComponents().elementAt(7);
                  menuBar = (UITitle)this.getComponents().elementAt(1);
                  if (chargeName.isFocus() || myName.isFocus()) {
                     chargeStr = chargeName.getSb().toString().trim();
                     myStr = myName.getSb().toString().trim();
                     if (chargeStr != null && !"".equals(chargeStr) && myStr != null && !"".equals(myStr)) {
                        menuBar.setMenuText("Nạp thẻ", 0);
                     } else {
                        menuBar.setMenuText("Nhập vào", 0);
                     }
                  }
               } else if (this.clientCommand == 131079) {
                  g.setColor(16777215);
                  g.drawString("Chào mừng đến PT", MainCanvas.screenW - 5, MainCanvas.screenH - UITitle.getMenuBarHeight() - 5, 40);
               }
            }
         } else {
            this.drawUIShoppingList(g);
         }
      } else {
         pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(14);
         picture_1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(15);
         if (UIPicture.isMyTradeLock) {
            g.setColor(16746240);
         } else {
            g.setColor(6154240);
         }

         g.fillTriangle(pic.getPositionX() - 3, pic.getPositionY() + 6, pic.getPositionX() + 6, pic.getPositionY() + 16, pic.getPositionX() + 16, pic.getPositionY() + 6);
         g.fillRect(pic.getPositionX() + 1, pic.getPositionY() - 4, 11, 10);
         if (UIPicture.isOtherTradeLock) {
            g.setColor(16746240);
         } else {
            g.setColor(6154240);
         }

         g.fillTriangle(picture_1.getPositionX() - 4, picture_1.getPositionY() + 10, picture_1.getPositionX() + 6, pic.getPositionY() - 2, picture_1.getPositionX() + 16, picture_1.getPositionY() + 10);
         g.fillRect(picture_1.getPositionX() + 1, picture_1.getPositionY() + 9, 11, 10);
      }

      if (comGridTmp != null && comGridTmp instanceof UIGrid && comGridTmp.isFocus()) {
         UIGrid grid = (UIGrid)comGridTmp;
         grid.drawCursorAndTopForm(g);
      }

      Player.getInstance().drawTalentTree(g, this.clientCommand);
      if (comTmp != null && comTmp.isShowMenu && comTmp.getMenu() != null) {
         comTmp.getMenu().draw(g);
      }

      if (super.isShowMenu && this.getMenu() != null) {
         this.getMenu().draw(g);
      }

      if (comPicTmp != null && comPicTmp instanceof UIPicture && comPicTmp.isFocus()) {
         pic = (UIPicture)comPicTmp;
         if (pic.getType() == 3) {
            pic.drawCursor(g);
         } else if (pic.getType() == 1 && (this.clientCommand == 1376260 || this.clientCommand == 1376261) || this.clientCommand == 1376265 || this.clientCommand == 1376268 || this.clientCommand == 1376264) {
            pic.drawCursor(g);
         }
      }

      if (this.clientCommand == 1900546 && !this.haveFormMenu()) {
         if (PCAuction.sumPage > 1) {
            if (PCAuction.pageIndex == 1) {
               PCAuction.drawArrow(this, g, false, true);
            } else if (PCAuction.pageIndex == PCAuction.sumPage) {
               PCAuction.drawArrow(this, g, true, false);
            } else {
               PCAuction.drawArrow(this, g, true, true);
            }
         }
      } else if (this.clientCommand == 2621442 && !this.haveFormMenu() && PCIncrement.chargeState == 2 && PCIncrement.isSendMessageEnd()) {
         String result;
         if (PCIncrement.count == 0) {
            result = "Nạp thẻ thất bại";
         } else {
            result = "Đã gửi thành công " + PCIncrement.count + "tin nhắn, nhận tiền" + PCIncrement.mammothNum + ", có vào Tiền Trang không?";
         }

         UITopForm.createLocalTopForm((byte)0, (String)result, "Có", "Không", 2621450, -1);
         PCIncrement.chargeState = 1;
      }

      if (this.clientCommand == -1610612687) {
         this.drawInserChatFace(g);
      }

   }

   public void drawInserChatFace(Graphics g) {
      int frame_w = MainCanvas.faceImg.frame_w;
      int frame_h = MainCanvas.faceImg.frame_h;
      int space = (MainCanvas.screenW - 3 * frame_w) / 4;
      int space2 = (MainCanvas.screenH - 40 - 4 * frame_w) / 5;
      int drawWidth = frame_w + space;
      int drawheight = frame_h + space2;
      int TOUCH_DX = space;
      int TOUCH_DY = 16 + space2;
      int cols = MainCanvas.faceImg.cols;

      for(int i = 0; i < cols; ++i) {
         int dx = i / 3;
         int dy = i % 3;
         int faceX = TOUCH_DX + drawWidth * dy;
         int faceY = TOUCH_DY + dx * drawheight;
         g.setColor(5450263);
         if (i == this.faceSelect) {
            g.drawRect(faceX - 8, faceY - 8, frame_w + 16, frame_h + 8 + MainCanvas.CHARH);
         }

         MainCanvas.numberImg[1].draw(g, faceX + 2, faceY + frame_h + 3, i + 1);
         if (UIChat.faceSwing[i][MainCanvas.countTick % UIChat.faceSwing[i].length] == 0) {
            MainCanvas.faceImg.draw(g, faceX, faceY, i);
         } else {
            MainCanvas.faceImg1.draw(g, faceX, faceY, i);
         }
      }

   }

   public static final int getWait() {
      try {
         if (encryptImg == null) {
            encryptImg = Util.loadImage("/special/mm.pkg", "tt.png");
         }

         return encryptImg.getWidth() + 110;
      } catch (Exception var1) {
         MainCanvas.aMidlet.exitMIDlet();
         return encryptImg.getWidth() + 110;
      }
   }

   public Vector getComponents() {
      return this.components;
   }

   public void setComponents(Vector components) {
      this.components = components;
   }

   public void addComponent(UIComponent uic) {
      this.components.addElement(uic);
   }

   public void clear() {
      this.components.removeAllElements();
   }

   private final void touchInGamble() {
      UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
      UIPicture yin = (UIPicture)MainCanvas.curForm.getComponents().elementAt(31);
      UILabel gambleStart = (UILabel)MainCanvas.curForm.getComponents().elementAt(32);
      UIPicture yang = (UIPicture)MainCanvas.curForm.getComponents().elementAt(33);
      int startX = gambleStart.getPositionX();
      int startY = gambleStart.getPositionY();
      int startW = gambleStart.getWidth();
      int startH = gambleStart.getHeight();
      boolean isStart = gambleStart.getText().equals("Thanh toán");
      if (!PCGamble.Begin_1 && !PCGamble.renovate && !this.finish) {
         int m;
         short gx;
         short gy;
         short gw;
         short gh;
         if (!isStart) {
            for(m = 0; m < 7; ++m) {
               UITextField gambleTf = (UITextField)MainCanvas.curForm.getComponents().elementAt(24 + m);
               gx = gambleTf.getPositionX();
               gy = gambleTf.getPositionY();
               gw = gambleTf.getWidth();
               gh = gambleTf.getHeight();
               if (MainCanvas.isPointInRect(gx, gy, gw, gh)) {
                  gambleTf.setFocus(true, this);

                  for(int j = 0; j < 7; ++j) {
                     if (j != m) {
                        ((UITextField)MainCanvas.curForm.getComponents().elementAt(24 + j)).setFocus(false, this);
                     }
                  }

                  Util.Touch_Gamble(m);
                  gambleStart.setFocus(false, this);
                  menuBar.setMenuText("", 0);
                  break;
               }
            }
         } else {
            int yinyX = yin.getPositionX();
            int yinyY = yin.getPositionY();
            gx = yang.getPositionX();
            gy = yang.getPositionY();
            gw = yin.getWidth();
            gh = yin.getHeight();
            if (MainCanvas.isPointInRect(yinyX, yinyY, gw, gh)) {
               PCGamble.BigOrSmall = 1;
               if (yin.isFocus()) {
                  MainCanvas.keySimPressed(65536);
               } else {
                  menuBar.setMenuText("Bắt đầu", 0);
                  yin.setFocus(true, this);
                  yang.setFocus(false, this);
                  gambleStart.setFocus(false, this);
               }

               return;
            }

            if (MainCanvas.isPointInRect(gx, gy, gw, gh)) {
               PCGamble.BigOrSmall = 2;
               if (yang.isFocus()) {
                  MainCanvas.keySimPressed(65536);
               } else {
                  menuBar.setMenuText("Bắt đầu", 0);
                  yin.setFocus(false, this);
                  yang.setFocus(true, this);
                  gambleStart.setFocus(false, this);
               }

               return;
            }
         }

         if (MainCanvas.isPointInRect(startX, startY, startW, startH)) {
            if (gambleStart.isFocus()) {
               MainCanvas.keySimPressed(65536);
            } else {
               if (!isStart) {
                  menuBar.setMenuText("Bắt đầu", 0);
               } else {
                  menuBar.setMenuText("Thanh toán", 0);
               }

               gambleStart.setFocus(true, this);
               yin.setFocus(false, this);
               yang.setFocus(false, this);

               for(m = 0; m < 7; ++m) {
                  ((UITextField)MainCanvas.curForm.getComponents().elementAt(24 + m)).setFocus(false, this);
               }
            }
         }

      }
   }

   private final void touchScreenAction() {
      if (MainCanvas.SUPPORT_POINTER && MainCanvas.curForm != null && !MainCanvas.ni.isSendingCommands && MainCanvas.isInputDown(268435456)) {
         boolean isAction = false;
         if (this.focusWidget != null) {
            UIMenu menu = this.focusWidget.getMenu();
            if (menu != null && this.focusWidget.isShowMenu && menu.pointIndex()) {
               MainCanvas.keySimPressed(131072);
               isAction = true;
            }
         }

         int i;
         if (!isAction) {
            i = MainCanvas.screenW - (MainCanvas.CHARW + 4 << 1);
            int leftX = 0;
            int Y = MainCanvas.screenH - UITitle.getMenuBarHeight();
            int W = MainCanvas.CHARW + 4 << 1;
            int H = UITitle.getMenuBarHeight();
            if (MainCanvas.isPointInRect(i, Y, W, H)) {
               MainCanvas.keySimPressed(262144);
               isAction = true;
            }

            if (MainCanvas.isPointInRect(leftX, Y, W, H)) {
               MainCanvas.keySimPressed(131072);
               isAction = true;
            }

            if (!this.haveFormMenu()) {
               UILabel doubleLabel;
               short arrY;
               switch(MainCanvas.getState()) {
               case 18:
                  doubleLabel = (UILabel)this.getComponents().elementAt(4);
                  arrY = doubleLabel.getPositionX();
                  int conY = doubleLabel.getPositionY();
                  int conW = doubleLabel.getWidth();
                  int conH = doubleLabel.getHeight();
                  if (MainCanvas.isPointInRect(arrY, conY, conW, conH)) {
                     MainCanvas.keySimPressed(65536);
                     isAction = true;
                  }
               }

               int i;
               int itemHeight;
               int dy;
               int panelX;
               int panelY;
               label131:
               switch(this.clientCommand) {
               case -1610612687:
                  int frame_w = MainCanvas.faceImg.frame_w;
                  int frame_h = MainCanvas.faceImg.frame_h;
                  int space = (MainCanvas.screenW - 3 * frame_w) / 4;
                  int space2 = (MainCanvas.screenH - 40 - 4 * frame_w) / 5;
                  int drawWidth = frame_w + space;
                  int drawheight = frame_h + space2;
                  int TOUCH_DX = space;
                  int TOUCH_DY = 16 + space2;
                  int cols = MainCanvas.faceImg.cols;
                  int oldFaceSelect = this.faceSelect;
                  i = 0;

                  while(true) {
                     if (i >= cols) {
                        break label131;
                     }

                     itemHeight = i / 3;
                     dy = i % 3;
                     panelX = TOUCH_DX + drawWidth * dy;
                     panelY = TOUCH_DY + itemHeight * drawheight;
                     if (MainCanvas.isPointInRect(panelX - 8, panelY - 8, frame_w + 16, frame_h + 8 + MainCanvas.CHARH)) {
                        this.faceSelect = i;
                        if (oldFaceSelect == this.faceSelect) {
                           MainCanvas.keySimPressed(131072);
                        }
                        break label131;
                     }

                     ++i;
                  }
               case 1900546:
                  doubleLabel = (UILabel)this.getComponents().elementAt(15);
                  arrY = doubleLabel.getPositionY();
                  int lX = doubleLabel.getPositionX() - 5 - MainCanvas.auctionArrImg.getWidth();
                  int rX = doubleLabel.getPositionX() + doubleLabel.getWidth() + 5;
                  int arrW = MainCanvas.auctionArrImg.getWidth();
                  int arrH = MainCanvas.auctionArrImg.getHeight();
                  if (MainCanvas.isPointInRect(lX, arrY, arrW, arrH)) {
                     MainCanvas.keySimPressed(16384);
                  } else if (MainCanvas.isPointInRect(rX, arrY, arrW, arrH)) {
                     MainCanvas.keySimPressed(32768);
                  }
                  break;
               case 2097155:
                  this.touchInGamble();
                  isAction = true;
                  break;
               case 2425024:
               case 2425077:
                  i = MainCanvas.CHARW * Util.getMaxStringLen(shoppingList) + 70;
                  itemHeight = MainCanvas.CHARH;
                  dy = MainCanvas.CHARH * shoppingList.length + 16;
                  panelX = (MainCanvas.screenW - i) / 2;
                  panelY = (((UIComponent)this.getComponents().elementAt(3)).getPositionY() - ((UIComponent)this.getComponents().elementAt(2)).getPositionY() - dy) / 2 + ((UIComponent)this.getComponents().elementAt(2)).getPositionY();

                  for(int i = 0; i < shoppingList.length; ++i) {
                     if (MainCanvas.isPointInRect(panelX, panelY + i * itemHeight + 8, i, itemHeight)) {
                        shoppingID = i;
                        this.keyInSelectShoppingList();
                     }
                  }
               }
            }
         }

         if (!isAction) {
            i = 0;

            for(int n = this.components.size(); i < n; ++i) {
               UIComponent uic = (UIComponent)this.components.elementAt(i);
               if (uic.hasFocus()) {
                  if (MainCanvas.isPointInRect(uic.getPositionX(), uic.getPositionY(), uic.getWidth(), uic.getHeight())) {
                     if (this.focusWidget == uic) {
                        boolean tmpAct = uic.focusWidgetPointAction();
                        if (tmpAct) {
                           this.focusWidget.isShowMenu = false;
                        } else if (!uic.isShowMenu) {
                           if (MainCanvas.getState() == 18) {
                              MainCanvas.keySimPressed(65536);
                           } else {
                              MainCanvas.keySimPressed(131072);
                           }
                        }
                     } else {
                        if (this.clientCommand == 2555905 && uic instanceof UIPicture) {
                           UIPicture picture = (UIPicture)uic;
                           if (picture.getWhetherEnvelop() == 0) {
                              return;
                           }
                        }

                        this.focusWidget.isShowMenu = false;
                        this.focusWidget.setFocus(false, this);
                        uic.setFocus(true, this);
                        if (uic.haveTopTip()) {
                           uic.resetTopTip();
                           uic.startTopTip();
                        }

                        if (this.focusWidget instanceof UIGrid || this.focusWidget instanceof UIRadio) {
                           this.focusWidget.focusWidgetPointAction();
                        }
                     }

                     isAction = true;
                     break;
                  }
               } else if ((uic instanceof UITextArea || uic instanceof UIList || uic instanceof UITextField) && MainCanvas.isPointInRect(uic.getPositionX(), uic.getPositionY(), uic.getWidth(), uic.getHeight())) {
                  switch(this.clientCommand) {
                  case 1703969:
                     MainCanvas.keySimPressed(2048);
                  case 1703970:
                  default:
                     break;
                  case 1703971:
                     MainCanvas.keySimPressed(131072);
                  }
               }
            }
         }
      }

   }

   public void keyInForm() {
      UIRadio typeRadio;
      UIRadio timeRadio;
      String str;
      UITextArea textarea;
      UILabel label_1;
      String str;
      UITextField userNameText;
      UILabel label_2;
      int chargeNum;
      UILabel label_3;
      UILabel label_4;
      UITextField chargeText;
      UITextField pswText;
      UILabel label_5;
      UITextField chargeText;
      this.touchScreenAction();
      PassPort pp;
      String confirmStr;
      String str;
      PassPort pp;
      UIList list;
      PassPort pp;
      byte listIndex;
      String chargeStr;
      UIRadio radio;
      String str;
      label1407:
      switch(MainCanvas.getState()) {
      case 6:
         if (MainCanvas.isInputDown(196608)) {
            MainCanvas.setState((byte)29);
         } else if (MainCanvas.isInputDown(262144)) {
            MainCanvas.setState((byte)4);
         }
         break;
      case 7:
      case 9:
      case 13:
      case 20:
      case 24:
      case 25:
      case 26:
      case 28:
      default:
         UITitle menuBar;
         if (MainCanvas.isInputDown(262144)) {
            isEndPic = false;
            if (MainCanvas.getState() != 4 && !this.haveFormMenu()) {
               resetCompareTI();
               PCMail.selectedAttIndex = -1;
               Util.rollCounter = 0;
               Util.waitCounter = 0;
               if (this.clientCommand == 1376260 || this.clientCommand == 1376265 || this.clientCommand == 1376264 || this.clientCommand == 1376268 || this.clientCommand == 1376261) {
                  MainCanvas.curTopForm = new UITopForm((byte)0, (UIForm)null);
                  MainCanvas.curTopForm.setTopFormInfo((byte)5, Cons.COLOR_PANEL_BORDER_3, "Có hủy giao dịch không?", "Xác nhận", "Hủy", 1376274, -1);
                  return;
               }

               if (this.clientCommand == 1376263) {
                  MainCanvas.ni.send(1376261);
                  return;
               }

               if (this.clientCommand == 1376267) {
                  MainCanvas.ni.send(1376273);
                  if (PCTreasure.treasurePicID > 0) {
                     PCTreasure.treasurePicID = 0;
                  }

                  if (PCTreasure.interphase) {
                     PCTreasure.interphase = false;
                  }

                  if (!PCTreasure.unlockMenu) {
                     PCTreasure.unlockMenu = true;
                  }

                  return;
               }

               if (this.clientCommand == 458760 && PCPackage.needExtend) {
                  menuBar = (UITitle)this.getComponents().elementAt(1);
                  menuBar.setMenuText("Quay về", 1);
                  PCPackage.setExtendPicMoveToGrid(true);
                  PCPackage.needExtend = false;
                  return;
               }

               if (this.clientCommand == 1179658 && PCPackage.needExtend) {
                  menuBar = (UITitle)this.getComponents().elementAt(1);
                  menuBar.setMenuText("Quay về", 1);
                  PCStorage.setExtendPicMoveToGrid(true);
                  PCPackage.needExtend = false;
                  return;
               }

               if (this.clientCommand == 262152) {
                  UIPicture.sSelectedColIndex = -1;
               } else if (this.clientCommand == 458755) {
                  UIPicture.clearEquipKuang();
               }

               if (lockIndex == -1) {
                  backUIForm();
               }
            }
         } else if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
            if (MainCanvas.isInputDown(2)) {
               switch(this.clientCommand) {
               case 2555905:
                  this.FarmKeystoke((byte)35);
               }
            } else if (MainCanvas.isInputDown(4)) {
               switch(this.clientCommand) {
               case 2555905:
                  if (PCFarm.m_bWhetherGetHome == 1) {
                     return;
                  }

                  this.FarmKeystoke((byte)36);
               }
            } else if (MainCanvas.isInputDown(8)) {
               switch(this.clientCommand) {
               case 2555905:
                  this.FarmKeystoke((byte)37);
               }
            }
         } else if (this.clientCommand == 2097155) {
            menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
            label_2 = (UILabel)this.components.elementAt(32);
            label_3 = (UILabel)this.components.elementAt(36);
            label_4 = (UILabel)this.components.elementAt(37);
            int i;
            if (label_2.isFocus() && label_2.getText().equals("Bắt đầu")) {
               if (!PCGamble.Begin) {
                  for(i = 0; i < 7; ++i) {
                     PCGamble.textfield[i] = (UITextField)MainCanvas.curForm.getComponents().elementAt(24 + i);
                     if (PCGamble.textfield[i].getNumber() == 0) {
                        ++PCGamble.arithmometer;
                     }
                  }

                  if (PCGamble.arithmometer == 7) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Hãy nhập số tiền", "Xác nhận", "", -1, -2);
                     PCGamble.arithmometer = 0;
                     return;
                  }

                  label_2.setFocus(false, MainCanvas.curForm);
                  PCGamble.m_nCircle = Util.genRandom(5, 8);
                  MainCanvas.ni.send(2097153);
                  PCGamble.rebound = true;
               }
            } else if (label_2.isFocus() && label_2.getText().equals("Thanh toán")) {
               i = Integer.parseInt(label_3.getText());
               int possess = Integer.parseInt(label_4.getText());
               possess += i;
               label_3.setText("0");
               label_4.setText("" + possess);
               label_2.setText("Bắt đầu");
               menuBar.setMenuText("Bắt đầu", 0);
               PCGamble.Begin = false;
               PCGamble.renovate = false;
               PCGamble.m_nCircle = 0;
               PCGamble.m_nCircleCounter = 0;

               for(int i = 0; i < 7; ++i) {
                  PCGamble.textfield[i].setLabel("0");
               }

               PCGamble.index = 0;
               PCGamble.server_index = 0;
               PCGamble.award = false;
               PCGamble.award_1 = false;
               PCGamble.luxian = 0;
            }
         } else if (this.clientCommand == 2621443) {
            userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
            pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(6);
            radio = (UIRadio)this.getComponents().elementAt(7);
            chargeStr = userNameText.getSb().toString().trim();
            confirmStr = pswText.getSb().toString().trim();
            if (!userNameText.isFocus() && !pswText.isFocus()) {
               if (radio.isFocus()) {
                  if (radio.getSelectIndex() == radio.getSureIndex()) {
                     if (chargeStr != null && !chargeStr.equals("") && confirmStr != null && !confirmStr.equals("")) {
                        PCCharge.cardNum = chargeStr;
                        PCCharge.cardPsw = confirmStr;
                        if (radio.getSureIndex() == 0) {
                           PCCharge.chargeAccount = PCCharge.myAccount = MainCanvas.getUserName();
                           MainCanvas.ni.send(2621451);
                        } else if (radio.getSureIndex() == 1) {
                           MainCanvas.ni.send(2621447);
                           UIMenu.formSaveForm();
                        }
                     } else {
                        UITopForm.createLocalTopForm((byte)0, (String)"Nhập số thẻ nạp và Mật khẩu", "Xác nhận", "", -1, -2);
                     }
                  } else {
                     radio.setSureIndex(radio.getSelectIndex());
                  }
               }
            } else if (chargeStr != null && !chargeStr.equals("") && confirmStr != null && !confirmStr.equals("")) {
               PCCharge.cardNum = chargeStr;
               PCCharge.cardPsw = confirmStr;
               if (radio.getSureIndex() == 0) {
                  PCCharge.chargeAccount = PCCharge.myAccount = MainCanvas.getUserName();
                  MainCanvas.ni.send(2621451);
               } else if (radio.getSureIndex() == 1) {
                  MainCanvas.ni.send(2621447);
                  UIMenu.formSaveForm();
               }
            } else {
               Util.Touch_Charge_Normal(4, 6, "Nhập số thẻ nạp và mật khẩu", "Hãy nhập số thẻ nạp", "Hãy nhập mật khẩu của thẻ");
            }
         } else if (this.clientCommand == 2621445) {
            userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
            pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(6);
            str = userNameText.getSb().toString().trim();
            chargeStr = pswText.getSb().toString().trim();
            if (userNameText.isFocus() || pswText.isFocus()) {
               if (str != null && !str.equals("")) {
                  PCCharge.cardNum = str;
                  PCCharge.cardPsw = chargeStr;
                  PassPort.menuSelected = 29;
                  this.loginPP = new PassPort(PassPort.KONG_PASSPORT_IP, "", (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
                  this.loginPP.authenticate((byte)21);
               } else {
                  Util.Touch_Charge_Normal(4, 6, "Nhập số Serial và Mã nạp tiền", "Số Serial:", "Mã nạp tiền:");
               }
            }
         } else if (this.clientCommand == 2621447) {
            userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(5);
            pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(7);
            radio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(8);
            chargeStr = userNameText.getSb().toString().trim();
            confirmStr = pswText.getSb().toString().trim();
            if (userNameText.isFocus() || pswText.isFocus()) {
               if (chargeStr != null && !chargeStr.equals("") && confirmStr != null && !confirmStr.equals("")) {
                  PCCharge.chargeAccount = chargeStr;
                  PCCharge.myAccount = MainCanvas.getUserName();
                  if (radio.getSelectIndex() == 0) {
                     PCCharge.isPassPortCharge = true;
                  } else if (radio.getSelectIndex() == 1) {
                     PCCharge.isPassPortCharge = false;
                  }

                  if (!chargeStr.equals(confirmStr)) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Tài khoản thẻ thông hành không đúng", "Xác nhận", "", -1, -100);
                  } else {
                     MainCanvas.ni.send(2621451);
                  }
               } else {
                  Util.Touch_Charge_Normal(5, 7, "Nhập vào", "Thẻ thông hành cần nạp", "Xác nhận thẻ");
               }
            }
         } else if ((this.clientCommand == 3342337 || this.clientCommand == 3342338 || this.clientCommand == 3342339 || this.clientCommand == 1900552 || this.clientCommand == 1703954 || this.clientCommand == 1703948 || this.clientCommand == 1376267 || this.clientCommand == 655363 || this.clientCommand == 393231) && PCTreasure.interphase) {
            MainCanvas.ni.send(3342340);
            UIMenu.formSaveForm();
         }
         break;
      case 8:
         if (!this.haveFormMenu() && MainCanvas.isInputDown(262144)) {
            KeyConfig.backSetup();
         }
         break;
      case 10:
         if (MainCanvas.isInputDown(262144)) {
            MainCanvas.setState(MainCanvas.mc.getOldState());
         }
         break;
      case 11:
         if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
            if (MainCanvas.isInputDown(262144)) {
               MainCanvas.setState((byte)4);
            }
            break;
         } else {
            userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
            pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(5);
            label_3 = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
            label_4 = (UILabel)MainCanvas.curForm.getComponents().elementAt(7);
            label_5 = (UILabel)MainCanvas.curForm.getComponents().elementAt(8);
            UIRadio r = (UIRadio)MainCanvas.curForm.getComponents().elementAt(10);
            if (r.getSelectIndex() == 1) {
               PassPort.loginType = 0;
            } else {
               PassPort.loginType = 1;
            }

            if (!userNameText.isFocus() && !pswText.isFocus()) {
               if (label_3.isFocus()) {
                  if (MainCanvas.mc.isTestNum == 1) {
                     MainCanvas.TopForm((byte)0, "Nhân viên test không được đăng ký", "Xác nhận", "", -7, -120);
                     return;
                  }

                  PassPort.menuSelected = 24;
                  MainCanvas.setState((byte)14);
                  MainCanvas.mc.m_bEnterBadge = 1;
               } else if (label_4.isFocus()) {
                  if (MainCanvas.mc.isTestNum == 1) {
                     MainCanvas.TopForm((byte)0, "Nhân viên test không được đăng ký", "Xác nhận", "", -7, -120);
                     return;
                  }

                  str = "Hãy chờ...";
                  MainCanvas.TopForm((byte)0, str, "", "", -120, -120);
                  PassPort.isQuick = true;
                  PassPort.menuSelected = 23;
                  pp = new PassPort(PassPort.KONG_PASSPORT_IP, PassPort.getURL((byte)5), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
                  pp.authenticate((byte)21);
               } else if (label_5.isFocus()) {
                  MainCanvas.setState((byte)15);
               } else if (!MainCanvas.isChinaMobileVer) {
                  UILabel seizing = (UILabel)MainCanvas.curForm.getComponents().elementAt(9);
                  MainCanvas.mc.m_bEnterBadge = 4;
                  MainCanvas.setState((byte)16);
               }
               break;
            }

            MainCanvas.name_password[0] = userNameText.getSb().toString().trim();
            MainCanvas.name_password[1] = pswText.getSb().toString().trim();
            if (MainCanvas.name_password[0] != null && !MainCanvas.name_password[0].equals("") && MainCanvas.name_password[1] != null && !MainCanvas.name_password[1].equals("")) {
               Util.saveStrRecord(MainCanvas.name_password, "name_password");
               if (MainCanvas.name_password[0].equals("")) {
                  str = "Tài khoản hoặc mật khẩu lỗi, hãy nhập lại!";
                  MainCanvas.TopForm((byte)0, str, "Xác nhận", "", -3, -120);
                  return;
               }

               if (MainCanvas.name_password[1].equals("")) {
                  str = "Tài khoản hoặc mật khẩu lỗi, hãy nhập lại!";
                  MainCanvas.TopForm((byte)0, str, "Xác nhận", "", -3, -120);
                  return;
               }

               MainCanvas.saveLoginType();
               str = "Chờ liên kết với server...";
               MainCanvas.TopForm((byte)0, str, "", "", -120, -120);
               MainCanvas.m_bFocusEnactment = 0;
               MainCanvas.m_bChoose = 0;
               MainCanvas.Liquidate();
               PassPort.loseMark = 1;
               if (!PassPort.isLocalServerList) {
                  if (MainCanvas.m_bWhetherRound) {
                     MainCanvas.ni.send(117502);
                  } else {
                     if (!MainCanvas.isChinaMobileVer) {
                        this.loginPP = new PassPort(PassPort.KONG_PASSPORT_IP, PassPort.getURL((byte)1), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
                        if (PassPort.VER == 1) {
                           PassPort.connectToGetLocalPlace();
                        }
                     } else if (PassPort.haveUserIDAndKey()) {
                        this.loginPP = new PassPort("221.179.216.38", PassPort.getURL((byte)1), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
                     } else {
                        PassPort.isFirstConnect = true;
                        this.loginPP = new PassPort(PassPort.CM_PASSPORT_IP, PassPort.CM_PASSPORT_GET_URL, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)1);
                     }

                     if (MainCanvas.isAutoConn) {
                        conStartTime = System.currentTimeMillis();
                     }

                     this.loginPP.authenticate((byte)1);
                  }
               } else {
                  if (PassPort.menuSelected == 25) {
                     this.loginPP = new PassPort(PassPort.KONG_PASSPORT_IP, PassPort.getURL((byte)1), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
                     this.loginPP.authenticate((byte)1);
                     return;
                  }

                  MainCanvas.loadPlayerRes();
                  PassPort.kongFirstResult = 1;
                  MainCanvas.ni.send(65537);
               }

               return;
            }

            Util.Touch_Login(4, 5);
            return;
         }
      case 12:
         if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
            if (MainCanvas.isInputDown(262144)) {
               MainCanvas.setState((byte)4);
            }
         } else {
            list = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
            listIndex = list.getSelectedIndex();
            if (PassPort.checkUpdateInfo(listIndex)) {
               return;
            }

            PassPort.loseMark = 2;
            str = "Đang kết nối, hãy đợi...";
            MainCanvas.TopForm((byte)0, str, "", "", -120, -120);
            if (!PassPort.isLocalServerList) {
               MainCanvas.m_bjumpIP1 = Byte.parseByte(PassPort.getServerJumpIP(listIndex));
               NetInterface.serverUrl = "socket://" + PassPort.getServerIP(listIndex) + ":" + PassPort.getServerPort(listIndex);
               if (MainCanvas.mc.isTestNum != 1) {
                  Util.saveStrRecord(PassPort.serverListContent[listIndex], "last_time_login");
               }
            } else {
               MainCanvas.m_bjumpIP1 = MainCanvas.m_bjumpIP[listIndex];
               NetInterface.serverUrl = "socket://" + MainCanvas.serverIp[listIndex];
               MainCanvas.save_ServerInd[0] = listIndex;
               Util.saveRecord(MainCanvas.save_ServerInd, "serverlist");
            }

            MainCanvas.Cleanup_part();
            UIMenu.formSaveForm();
            if (MainCanvas.m_bTourist == 1) {
               MainCanvas.ni.send(65546);
            } else {
               MainCanvas.ni.send(131071);
            }
         }
         break;
      case 14:
         if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
            if (MainCanvas.isInputDown(262144)) {
               if (MainCanvas.mc.m_bEnterBadge == 1) {
                  MainCanvas.setState((byte)11);
               } else if (MainCanvas.mc.m_bEnterBadge == 2) {
                  MainCanvas.setState((byte)4);
               }

               MainCanvas.CleanupNamePassword();
               MainCanvas.name_password();
            }
            break;
         }

         MainCanvas.name_password[0] = null;
         MainCanvas.name_password[1] = null;
         userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(9);
         MainCanvas.name_password[0] = userNameText.getSb().toString().trim();
         pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(10);
         MainCanvas.name_password[1] = pswText.getSb().toString().trim();
         MainCanvas.m_sTemporaryStr[0] = MainCanvas.name_password[0];
         MainCanvas.m_sTemporaryStr[1] = MainCanvas.name_password[1];
         if (MainCanvas.name_password[0] == null || MainCanvas.name_password[0].equals("") || MainCanvas.name_password[1] == null || MainCanvas.name_password[1].equals("")) {
            Util.Touch_Enroll();
            return;
         }

         str = "Tài khoản：" + MainCanvas.name_password[0] + "\n" + "Mật khẩu：" + MainCanvas.name_password[1] + "\n" + "Xác nhận Tài khoản、Mật khẩu？";
         MainCanvas.TopForm((byte)0, str, "Xác nhận", "Sửa", -11, -1);
         break;
      case 15:
         if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
            if (MainCanvas.isInputDown(262144)) {
               MainCanvas.setState((byte)11);
               MainCanvas.CleanupNamePassword();
            }
         } else {
            typeRadio = null;
            timeRadio = null;
            MainCanvas.name_password[0] = null;
            MainCanvas.name_password[1] = null;
            UITextField userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(10);
            MainCanvas.name_password[0] = userNameText.getSb().toString().trim();
            chargeText = (UITextField)MainCanvas.curForm.getComponents().elementAt(11);
            MainCanvas.name_password[1] = chargeText.getSb().toString().trim();
            UITextField new_password = (UITextField)MainCanvas.curForm.getComponents().elementAt(12);
            str = new_password.getSb().toString().trim();
            chargeText = (UITextField)MainCanvas.curForm.getComponents().elementAt(13);
            str = chargeText.getSb().toString().trim();
            MainCanvas.m_sTemporaryStr[0] = MainCanvas.name_password[0];
            MainCanvas.m_sTemporaryStr[1] = MainCanvas.name_password[1];
            MainCanvas.m_sTemporaryStr[2] = str;
            MainCanvas.m_sTemporaryStr[3] = str;
            if (MainCanvas.m_sTemporaryStr[0] == null || MainCanvas.m_sTemporaryStr[0].equals("") || MainCanvas.m_sTemporaryStr[1] == null || MainCanvas.m_sTemporaryStr[1].equals("") || MainCanvas.m_sTemporaryStr[2] == null || MainCanvas.m_sTemporaryStr[2].equals("") || MainCanvas.m_sTemporaryStr[3] == null || MainCanvas.m_sTemporaryStr[3].equals("")) {
               Util.Touch_AmendPwd();
               return;
            }

            str = null;
            switch(MainCanvas.mc.EnrolValidate(MainCanvas.name_password[1], str, str)) {
            case 0:
               str = "Hãy chờ ...";
               MainCanvas.TopForm((byte)0, str, "", "", -120, -120);
               pp = null;
               if (!MainCanvas.isChinaMobileVer) {
                  pp = new PassPort(PassPort.KONG_PASSPORT_IP, PassPort.getURL((byte)2), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
                  pp.authenticate((byte)2);
               } else {
                  if (PassPort.haveUserIDAndKey()) {
                     pp = new PassPort("221.179.216.38", PassPort.getURL((byte)2), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
                  } else {
                     PassPort.isFirstConnect = true;
                     pp = new PassPort(PassPort.CM_PASSPORT_IP, PassPort.CM_PASSPORT_GET_URL, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)1);
                  }

                  pp.authenticate((byte)2);
               }
               break label1407;
            case 1:
               str = "Tài khoản hoặc Mật khẩu không được trống, hãy nhập lại!";
               MainCanvas.TopForm((byte)0, str, Cons.C_STR[2], "", -3, -120);
               break label1407;
            case 2:
               str = "Độ dài Mật khẩu không phù hợp, hãy nhập lại!";
               MainCanvas.TopForm((byte)0, str, Cons.C_STR[2], "", -3, -120);
               break label1407;
            case 3:
               str = "Mật khẩu không đúng, hãy nhập lại!";
               MainCanvas.TopForm((byte)0, str, Cons.C_STR[2], "", -3, -120);
            }
         }
         break;
      case 16:
         if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
            if (MainCanvas.isInputDown(262144)) {
               if (MainCanvas.mc.m_bEnterBadge == 3) {
                  MainCanvas.setState((byte)4);
               } else if (MainCanvas.mc.m_bEnterBadge == 4) {
                  MainCanvas.setState((byte)11);
               }
            }
         } else {
            str = "Chức năng này đang cập nhật.";
            if (MainCanvas.mc.m_bSeed_Seizing == 0) {
               MainCanvas.TopForm((byte)0, str, "", "Đóng", -2, -1);
            } else if (MainCanvas.mc.m_bSeed_Seizing == 1) {
               MainCanvas.TopForm((byte)0, str, "", "Đóng", -2, -1);
            } else if (MainCanvas.mc.m_bSeed_Seizing == 2) {
               if (PassPort.ptmForgotPass.equals("*")) {
                  MainCanvas.TopForm((byte)0, "Chức năng này đang bảo trì.", "", "Đóng", -2, -1);
               } else {
                  MainCanvas.setState((byte)23);
               }
            }

            if (MainCanvas.name_password[0] != null && !MainCanvas.name_password[0].equals("")) {
               str_1 = MainCanvas.name_password[0];
               MainCanvas.name_password[0] = null;
            }

            if (MainCanvas.name_password[1] != null && !MainCanvas.name_password[1].equals("")) {
               str_2 = MainCanvas.name_password[1];
               MainCanvas.name_password[1] = null;
            }
         }
         break;
      case 17:
         if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
            if (!this.haveFormMenu() && MainCanvas.isInputDown(262144)) {
               MainCanvas.setState((byte)4);
               MainCanvas.ni.closeConn();
               MainCanvas.Liquidate();
               MainCanvas.Cleanup_part();
               MainCanvas.m_bFocusEnactment = 0;
               MainCanvas.m_bChoose = 0;
            }
         } else {
            label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(16);
            if (label_1.isFocus() && MainCanvas.m_sCharacterName[MainCanvas.m_bChoose] != null) {
               if ((MainCanvas.m_bDelete[MainCanvas.m_bChoose] == 0 || MainCanvas.m_bDelete[MainCanvas.m_bChoose] == 2) && !MainCanvas.m_bAmendNickname[MainCanvas.m_bChoose]) {
                  MainCanvas.setState((byte)19);
               } else if (MainCanvas.m_bDelete[MainCanvas.m_bChoose] == 0 && MainCanvas.m_bAmendNickname[MainCanvas.m_bChoose]) {
                  this.AmendNickname();
               }
            }
         }
         break;
      case 18:
         if (MainCanvas.isInputDown(65536)) {
            userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(9);
            if (userNameText.getSb().toString().trim() != null && !userNameText.getSb().toString().trim().trim().equals("")) {
               if (!MainCanvas.m_sName.equals(userNameText.getSb().toString().trim())) {
                  MainCanvas.m_sName.equals(userNameText.getSb().toString().trim());
               }

               str = "";
               str = "Hãy chờ ...";
               MainCanvas.TopForm((byte)0, str, "", "", -120, -120);
               MainCanvas.ni.send(1114114);
               int i = 0;

               while(true) {
                  if (i >= 5) {
                     break label1407;
                  }

                  UIRadio.m_bIndex[i] = 0;
                  ++i;
               }
            } else {
               Util.Touch("Nhập Tên đăng nhập", "Nhập Tên đăng nhập, nhiều nhất 12 ký tự", 12, userNameText.getSb().toString().trim(), 9, (byte)2);
            }
         } else if (MainCanvas.isInputDown(262144)) {
            str = "";
            str = "Hãy chờ ...";
            MainCanvas.TopForm((byte)0, str, "", "", -120, -120);
            MainCanvas.ni.send(131071);
            MainCanvas.Liquidate();
            MainCanvas.Cleanup_part();

            for(chargeNum = 0; chargeNum < 5; ++chargeNum) {
               UIRadio.m_bIndex[chargeNum] = 0;
            }

            MainCanvas.focus = 0;
         } else if (MainCanvas.isInputDown(131072)) {
            typeRadio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(5);
            timeRadio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(6);
            radio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(8);
            chargeText = (UITextField)MainCanvas.curForm.getComponents().elementAt(9);
            if (chargeText.isFocus()) {
               if (chargeText.getSb().toString().trim() == null || chargeText.getSb().toString().trim().trim().equals("") || chargeText.getSb().toString().trim() != null && !chargeText.getSb().toString().trim().equals("")) {
                  Util.Touch("Nhập Tên đăng nhập", "Nhập Tên đăng nhập, nhiều nhất 12 ký tự (gồm số)", 12, chargeText.getSb().toString().trim(), 9, (byte)2);
               }
            } else {
               UITitle title;
               if (typeRadio.isFocus()) {
                  MainCanvas.setState((byte)27);
                  title = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
                  textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(4);
                  if (MainCanvas.m_bCamp == 1) {
                     title.setTitleText("Thiên Nhân");
                     textarea.setContent(Cons.ESTABLISH_INTRODUCE[0]);
                  } else if (MainCanvas.m_bCamp == 2) {
                     title.setTitleText("Tu La");
                     textarea.setContent(Cons.ESTABLISH_INTRODUCE[1]);
                  }

                  textarea.addUIScroll();
               } else if (timeRadio.isFocus()) {
                  MainCanvas.setState((byte)27);
                  title = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
                  textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(4);
                  if (MainCanvas.m_bPhyle == 1) {
                     title.setTitleText("Nhân Tộc");
                     textarea.setContent(Cons.ESTABLISH_INTRODUCE[2]);
                  } else if (MainCanvas.m_bPhyle == 2) {
                     title.setTitleText("Tiên Tộc");
                     textarea.setContent(Cons.ESTABLISH_INTRODUCE[3]);
                  } else if (MainCanvas.m_bPhyle == 3) {
                     title.setTitleText("Yêu Tộc");
                     textarea.setContent(Cons.ESTABLISH_INTRODUCE[4]);
                  } else if (MainCanvas.m_bPhyle == 4) {
                     title.setTitleText("Ma Tộc");
                     textarea.setContent(Cons.ESTABLISH_INTRODUCE[5]);
                  }

                  textarea.addUIScroll();
               } else if (radio.isFocus()) {
                  MainCanvas.setState((byte)27);
                  title = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
                  textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(4);
                  if (MainCanvas.m_bVocation == 1) {
                     title.setTitleText("Kiếm Khách");
                     textarea.setContent(Cons.ESTABLISH_INTRODUCE[6]);
                  } else if (MainCanvas.m_bVocation == 2) {
                     title.setTitleText("Thích Khách");
                     textarea.setContent(Cons.ESTABLISH_INTRODUCE[7]);
                  } else if (MainCanvas.m_bVocation == 3) {
                     title.setTitleText("Đạo Sỹ");
                     textarea.setContent(Cons.ESTABLISH_INTRODUCE[8]);
                  } else if (MainCanvas.m_bVocation == 4) {
                     title.setTitleText("Y Sư");
                     textarea.setContent(Cons.ESTABLISH_INTRODUCE[9]);
                  } else if (MainCanvas.m_bVocation == 5) {
                     title.setTitleText("Ảo Thuật Sư");
                     textarea.setContent(Cons.ESTABLISH_INTRODUCE[10]);
                  } else if (MainCanvas.m_bVocation == 6) {
                     title.setTitleText("Ám Vu");
                     textarea.setContent(Cons.ESTABLISH_INTRODUCE[11]);
                  }

                  textarea.addUIScroll();
               }
            }
         }
         break;
      case 19:
         if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
            if (MainCanvas.isInputDown(262144)) {
               str = "";
               str = "Hãy chờ ...";
               MainCanvas.TopForm((byte)0, str, "", "", -120, -120);
               MainCanvas.Liquidate();
               MainCanvas.setState((byte)17);
               MainCanvas.ni.send(131071);
            }
            break;
         } else {
            typeRadio = null;
            pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(9);
            if (pswText.getSb().toString().trim() != null && !pswText.getSb().toString().trim().equals("")) {
               str = pswText.getSb().toString().trim();
               if ("del".equals(str)) {
                  if (MainCanvas.m_bDelete[MainCanvas.m_bChoose] == 2) {
                     MainCanvas.ni.send(1114118);
                  } else {
                     MainCanvas.ni.send(1114116);
                  }
               } else {
                  str = "Nhập sai, hãy nhập lại";
                  MainCanvas.TopForm((byte)0, str, Cons.C_STR[2], "", -3, -120);
               }
               break;
            }

            Util.Touch("Xóa nhân vật", "Nhập del xác nhận", 3, typeRadio, 9, (byte)5);
            return;
         }
      case 21:
         if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
            if (MainCanvas.isInputDown(262144)) {
               if (str_1 != null && !str_1.equals("")) {
                  MainCanvas.name_password[0] = str_1;
               }

               if (str_2 != null && !str_2.equals("")) {
                  MainCanvas.name_password[1] = str_2;
               }

               MainCanvas.mc.m_bSeed_Seizing = 0;
               MainCanvas.setState(MainCanvas.mc.getOldState());
               list = (UIList)MainCanvas.curForm.getComponents().elementAt(3);
               list.setFocus(true, MainCanvas.curForm);
            }
            break;
         }

         userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
         pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(6);
         if (userNameText.getSb().toString().trim() == null || userNameText.getSb().toString().trim().equals("") || pswText.getSb().toString().trim() == null || pswText.getSb().toString().trim().equals("")) {
            Util.Touch_Login(4, 6);
            return;
         }

         MainCanvas.name_password[0] = userNameText.getSb().toString().trim();
         MainCanvas.name_password[1] = pswText.getSb().toString().trim();
         PCIncrement.m_bNote = 2;
         str = null;
         pp = new PassPort(PassPort.KONG_PASSPORT_IP, PassPort.getURL((byte)1), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)0);
         PassPort.isFirstConnect = true;
         pp.authenticate((byte)4);
         MainCanvas.TopForm((byte)0, "Hãy chờ ...", "", "", -120, -120);
         break;
      case 22:
         if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
            if (MainCanvas.isInputDown(262144)) {
               if (str_1 != null && !str_1.equals("")) {
                  MainCanvas.name_password[0] = str_1;
               }

               if (str_2 != null && !str_2.equals("")) {
                  MainCanvas.name_password[1] = str_2;
               }

               MainCanvas.mc.m_bSeed_Seizing = 0;
               MainCanvas.setState(MainCanvas.mc.getOldState());
               list = (UIList)MainCanvas.curForm.getComponents().elementAt(3);
               list.setFocus(true, MainCanvas.curForm);
            }
            break;
         } else {
            userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
            pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(6);
            if (userNameText.getSb().toString().trim() != null && !userNameText.getSb().toString().trim().equals("") && pswText.getSb().toString().trim() != null && !pswText.getSb().toString().trim().equals("")) {
               MainCanvas.name_password[0] = userNameText.getSb().toString().trim();
               MainCanvas.name_password[1] = pswText.getSb().toString().trim();
               PCIncrement.m_bNote = 3;
               str = null;
               pp = new PassPort(PassPort.KONG_PASSPORT_IP, PassPort.getURL((byte)1), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)0);
               PassPort.isFirstConnect = true;
               pp.authenticate((byte)4);
               MainCanvas.TopForm((byte)0, "Hãy chờ ...", "", "", -120, -120);
               break;
            }

            Util.Touch_Login(4, 6);
            return;
         }
      case 23:
         if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
            if (MainCanvas.isInputDown(262144)) {
               MainCanvas.mc.m_bSeed_Seizing = 0;
               MainCanvas.setState(MainCanvas.mc.getOldState());
               list = (UIList)MainCanvas.curForm.getComponents().elementAt(3);
               list.setFocus(true, MainCanvas.curForm);
               if (str_1 != null && !str_1.equals("")) {
                  MainCanvas.name_password[0] = str_1;
               }
            }
            break;
         } else {
            userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
            str = userNameText.getSb().toString().trim();
            radio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(5);
            if (str != null && !str.equals("")) {
               if (radio.getSelectIndex() == 0) {
                  str = "pt" + str;
               }

               chargeStr = PassPort.ptmCode + " PASS " + str;
               confirmStr = "sms://" + PassPort.ptmForgotPass;
               Util.sendSMS(chargeStr, confirmStr);
               System.out.println("UIForm. case STATE_SEED_PASSWORD = " + chargeStr + confirmStr);
               break;
            }

            Util.Touch("Nhập tên tài khoản", "Nhập tên tài khoản", 16, userNameText.getSb().toString().trim(), 4, (byte)1);
            return;
         }
      case 27:
         if (MainCanvas.isInputDown(262144)) {
            MainCanvas.setState(MainCanvas.mc.getOldState());
            SimplenessIntroduce();
         }
         break;
      case 29:
         if (MainCanvas.isInputDown(262144)) {
            MainCanvas.setState((byte)6);
            list = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
            list.setSelectedIndex(MainCanvas.helpIndex);
         }
         break;
      case 30:
         if (MainCanvas.isInputDown(196608)) {
            list = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
            listIndex = list.getSelectedIndex();
            switch(listIndex) {
            case 0:
               MainCanvas.setState((byte)31);
               break label1407;
            case 1:
               this.remain();
               break label1407;
            case 2:
               this.detail();
               break label1407;
            case 3:
               MainCanvas.setState((byte)37);
            }
         } else if (MainCanvas.isInputDown(262144)) {
            MainCanvas.setState((byte)4);
         }
         break;
      case 31:
         if (MainCanvas.isInputDown(196608)) {
            userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(5);
            chargeNum = 0;

            try {
               chargeNum = Integer.parseInt(userNameText.getSb().toString());
            } catch (Exception var9) {
               var9.printStackTrace();
            }

            if (chargeNum >= 1 && chargeNum <= 100) {
               PassPort.chargeNum = chargeNum;
               if (PassPort.haveUserIDAndKey()) {
                  str = "/judgeserver/charge?&userId=" + MainCanvas.userID + "&key=" + MainCanvas.userKey + "&game=fengshen&money=" + chargeNum + "&from=kong";
                  pp = new PassPort(PassPort.CM_CHARGE_IP, str, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)3);
                  pp.authenticate((byte)3);
               } else {
                  PassPort.isFirstConnect = true;
                  pp = new PassPort(PassPort.CM_PASSPORT_IP, PassPort.CM_PASSPORT_GET_URL, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)1);
                  pp.authenticate((byte)3);
               }

               MainCanvas.TopForm((byte)0, "Hãy chờ ...", "", "", -120, -120);
            } else {
               str = "Nhập sai, hãy nhập lại, điều chỉnh trong khoảng “1-100”";
               MainCanvas.TopForm((byte)0, str, Cons.C_STR[2], "", -3, -120);
            }
         } else if (MainCanvas.isInputDown(262144)) {
            MainCanvas.setState((byte)30);
         }
         break;
      case 32:
         if (MainCanvas.isInputDown(196608)) {
            label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
            label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(5);
            label_3 = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
            label_4 = (UILabel)MainCanvas.curForm.getComponents().elementAt(7);
            if (label_1.isFocus()) {
               MainCanvas.setState((byte)31);
            } else if (label_2.isFocus()) {
               this.remain();
            } else if (label_3.isFocus()) {
               this.detail();
            } else if (label_4.isFocus()) {
               MainCanvas.setState((byte)30);
            }
         } else if (MainCanvas.isInputDown(262144)) {
            MainCanvas.setState((byte)30);
         }
         break;
      case 33:
         if (MainCanvas.isInputDown(196608)) {
            label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
            label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(5);
            label_3 = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
            if (label_1.isFocus()) {
               MainCanvas.setState((byte)31);
            } else if (label_2.isFocus()) {
               this.detail();
            } else if (label_3.isFocus()) {
               MainCanvas.setState((byte)30);
            }
         } else if (MainCanvas.isInputDown(262144)) {
            MainCanvas.setState((byte)30);
         }
         break;
      case 34:
         if (MainCanvas.isInputDown(196608)) {
            label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
            label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(5);
            if (label_1.isFocus()) {
               MainCanvas.setState((byte)35);
            } else if (label_2.isFocus()) {
               MainCanvas.setState((byte)36);
            }
         } else if (MainCanvas.isInputDown(262144)) {
            MainCanvas.setState((byte)30);
         }
         break;
      case 35:
         if (MainCanvas.isInputDown(196608)) {
            typeRadio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(3);
            PassPort.timeType = (byte)(typeRadio.getSelectIndex() + 1);
            if (PassPort.timeType == 2) {
               UITopForm.MAXNUMBER = 999999;
               UITopForm.createLocalTopForm((byte)1, "Nhập tra tìm ngày tháng:\n（Ví dụ: 200906）", "Xác nhận", "Hủy", -1610612640, -1, -1, -1);
               MainCanvas.curTopForm.MAXWORDSNUM = 6;
            } else if (PassPort.haveUserIDAndKey()) {
               str = "/judgeserver/QueryChargeUpRecord?usr=&userId=" + MainCanvas.userID + "&game=fengshen&subgame=fengshen&from=kong&sdate=&edate=&sseq=0&rcount=" + 100 + "&qtype=7&qtime=" + PassPort.timeType + "&qmonth=" + PassPort.selMonth;
               MainCanvas.TopForm((byte)0, "Hãy chờ ...", "", "", -120, -120);
               pp = new PassPort(PassPort.CM_CHARGE_IP, str, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)3);
               pp.authenticate((byte)8);
            } else {
               MainCanvas.TopForm((byte)0, "Hãy chờ ...", "", "", -120, -120);
               PassPort.isFirstConnect = true;
               PassPort pp = new PassPort(PassPort.CM_PASSPORT_IP, PassPort.CM_PASSPORT_GET_URL, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)1);
               pp.authenticate((byte)8);
            }
         } else if (MainCanvas.isInputDown(262144)) {
            MainCanvas.setState((byte)34);
         }
         break;
      case 36:
         if (MainCanvas.isInputDown(196608)) {
            typeRadio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(3);
            timeRadio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(5);
            PassPort.timeType = (byte)(timeRadio.getSelectIndex() + 1);
            switch(typeRadio.getSelectIndex()) {
            case 0:
               PassPort.chooseType = 3;
               break;
            case 1:
               PassPort.chooseType = 5;
               break;
            case 2:
               PassPort.chooseType = 13;
            }

            if (PassPort.timeType == 2) {
               UITopForm.MAXNUMBER = 999999;
               UITopForm.createLocalTopForm((byte)1, "Tra ngày tháng：\n（Ví dụ: 200906）", "Xác nhận", "Hủy", -1610612639, -1, -1, -1);
               MainCanvas.curTopForm.MAXWORDSNUM = 6;
            } else if (PassPort.haveUserIDAndKey()) {
               str = "/judgeserver/QueryChargeUpRecord?usr=&userId=" + MainCanvas.userID + "&game=fengshen&subgame=fengshen&from=kong&sdate=&edate=&sseq=0&rcount=" + 100 + "&qtype=" + PassPort.chooseType + "&qtime=" + PassPort.timeType + "&qmonth=" + PassPort.selMonth;
               MainCanvas.TopForm((byte)0, "Hãy chờ ...", "", "", -120, -120);
               pp = new PassPort(PassPort.CM_CHARGE_IP, str, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)3);
               pp.authenticate((byte)9);
            } else {
               MainCanvas.TopForm((byte)0, "Hãy chờ ...", "", "", -120, -120);
               PassPort.isFirstConnect = true;
               pp = new PassPort(PassPort.CM_PASSPORT_IP, PassPort.CM_PASSPORT_GET_URL, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)1);
               pp.authenticate((byte)9);
            }
         } else if (MainCanvas.isInputDown(262144)) {
            MainCanvas.setState((byte)34);
         }
         break;
      case 37:
         if (MainCanvas.isInputDown(262144)) {
            MainCanvas.setState((byte)30);
         }
         break;
      case 38:
         if (MainCanvas.isInputDown(262144)) {
            MainCanvas.setState((byte)34);
         }
      }

      if (MainCanvas.curForm != null && !MainCanvas.ni.isSendingCommands) {
         int i = 0;

         for(chargeNum = this.components.size(); i < chargeNum; ++i) {
            UIComponent uic = (UIComponent)this.components.elementAt(i);
            if (uic instanceof UIFolder) {
               ((UIFolder)uic).keyInFolder();
            }

            if (uic instanceof UIDummyWidget) {
               ((UIDummyWidget)uic).keyDummyWidget();
            }
         }

         if (this.focusWidget != null) {
            if (this.focusWidget instanceof UIGrid) {
               this.focusWidget.keyInAction();
            } else {
               this.focusWidget.keyInAction();
               this.focusWidget.navigationKey(this);
            }
         }

         if (this != null && (this.clientCommand == 262147 || this.clientCommand == 262176) && MainCanvas.isInputDown(2048)) {
            UIComponent text = (UIComponent)MainCanvas.curForm.getComponents().elementAt(4);
            UIComponent gList = (UIComponent)MainCanvas.curForm.getComponents().elementAt(5);
            if (text.isFocus()) {
               text.setFocus(false, this);
               gList.setFocus(true, this);
            } else if (gList.isFocus()) {
               gList.setFocus(false, this);
               text.setFocus(true, this);
            }
         }

         if (this.clientCommand == 1900548 && MainCanvas.isInputDown(2048)) {
            MainCanvas.ni.send(1900548);
         }

         if (this.clientCommand == 1900546) {
            if (MainCanvas.isInputDown(2048)) {
               if (PCAuction.isBuy) {
                  MainCanvas.ni.send(1900546);
               } else {
                  MainCanvas.ni.send(1900554);
               }
            } else if (MainCanvas.isInputDownOrHold(16384) && !this.haveFormMenu()) {
               --PCAuction.pageIndex;
               if (PCAuction.pageIndex < 1) {
                  PCAuction.pageIndex = 1;
               } else if (PCAuction.isBuy) {
                  MainCanvas.ni.send(1900546);
               } else {
                  MainCanvas.ni.send(1900554);
               }
            } else if (MainCanvas.isInputDownOrHold(32768) && !this.haveFormMenu()) {
               ++PCAuction.pageIndex;
               if (PCAuction.pageIndex > PCAuction.sumPage) {
                  PCAuction.pageIndex = PCAuction.sumPage;
               } else if (PCAuction.isBuy) {
                  MainCanvas.ni.send(1900546);
               } else {
                  MainCanvas.ni.send(1900554);
               }
            }
         } else if (this.clientCommand != 2425024 && this.clientCommand != 2425077) {
            if (this.clientCommand == 2621446) {
               if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
                  label_1 = (UILabel)this.getComponents().elementAt(4);
                  label_2 = (UILabel)this.getComponents().elementAt(5);
                  if (label_1.isFocus()) {
                     MainCanvas.ni.send(2621449);
                  } else if (label_2.isFocus()) {
                     MainCanvas.ni.send(2621448);
                     UIMenu.formSaveForm();
                  }
               }
            } else if (this.clientCommand == 2424839) {
               if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
                  PCIncrement.m_nRebirthID = 20;
                  if (PCIncrement.m_bBuyRestrictHot == 0) {
                     if (!MainCanvas.isCMWAP && (PCIncrement.m_bBuyMoney == 3 || PCIncrement.m_bBuyMoney == 8)) {
                        UITopForm.createBuyTipTopForm();
                     } else {
                        UITopForm.MAXNUMBER = 20;
                        UITopForm.createLocalTopForm((byte)1, "Nhập số lượng", "Xác nhận", "Quay về", 2424993, -1, -1, -1);
                        MainCanvas.curTopForm.setNumber(1);
                     }
                  } else if (PCIncrement.m_bBuyRestrictHot == 1) {
                     if (MainCanvas.isCMWAP || PCIncrement.m_bBuyMoney != 3 && PCIncrement.m_bBuyMoney != 8) {
                        MainCanvas.ni.send(2424993);
                     } else {
                        UITopForm.createBuyTipTopForm();
                     }
                  }
               }
            } else if (this.clientCommand == 2424995) {
               if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
                  PCIncrement.m_bSendChoose = true;
                  PCIncrement.m_nRebirthID = 20;
                  if (PCIncrement.m_bBuyRestrictShopping[PCIncrement.m_nIndex] == 0) {
                     if (!MainCanvas.isCMWAP && (PCIncrement.m_bBuyMoney == 3 || PCIncrement.m_bBuyMoney == 8)) {
                        UITopForm.createBuyTipTopForm();
                     } else {
                        UITopForm.MAXNUMBER = 20;
                        UITopForm.createLocalTopForm((byte)1, "Nhập số lượng", "Xác nhận", "Quay về", 2424993, -1, -1, -1);
                        MainCanvas.curTopForm.setNumber(1);
                     }
                  } else if (PCIncrement.m_bBuyRestrictShopping[PCIncrement.m_nIndex] == 1) {
                     if (MainCanvas.isCMWAP || PCIncrement.m_bBuyMoney != 3 && PCIncrement.m_bBuyMoney != 8) {
                        UITextArea textaren = (UITextArea)this.getComponents().elementAt(15);
                        if (MainCanvas.pointDownInRect(textaren.getPositionX(), textaren.getPositionY(), textaren.getWidth(), textaren.getHeight())) {
                           return;
                        }

                        MainCanvas.ni.send(2424993);
                     } else {
                        UITopForm.createBuyTipTopForm();
                     }
                  }
               }
            } else if (this.clientCommand == 2297615) {
               if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
                  userNameText = (UITextField)this.getComponents().elementAt(4);
                  pswText = (UITextField)this.getComponents().elementAt(5);
                  if (userNameText.isFocus() || pswText.isFocus()) {
                     MainCanvas.name_password[0] = userNameText.getSb().toString().trim();
                     MainCanvas.name_password[1] = pswText.getSb().toString().trim();
                     if (MainCanvas.name_password[0] == null || MainCanvas.name_password[0].equals("") || MainCanvas.name_password[1] == null || MainCanvas.name_password[1].equals("")) {
                        Util.Touch_Login(4, 5);
                        return;
                     }

                     if (MainCanvas.name_password[0].equals("")) {
                        str = "Mật khẩu hay ID không đúng, xin nhập lại!";
                        MainCanvas.TopForm((byte)0, str, "Xác nhận", "", -3, -120);
                        return;
                     }

                     if (MainCanvas.name_password[1].equals("")) {
                        str = "Mật khẩu hay ID không đúng, xin nhập lại!";
                        MainCanvas.TopForm((byte)0, str, "Xác nhận", "", -3, -120);
                        return;
                     }

                     MainCanvas.ni.send(2297615);
                  }
               }
            } else if (this.clientCommand == 2297358) {
               if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
                  userNameText = (UITextField)this.getComponents().elementAt(4);
                  if (userNameText.isFocus()) {
                     MainCanvas.name_password[0] = userNameText.getSb().toString().trim();
                     MainCanvas.name_password[1] = "";
                     if (MainCanvas.name_password[0] == null || MainCanvas.name_password[0].equals("")) {
                        Util.Touch_Exchange(4);
                        return;
                     }

                     if (MainCanvas.name_password[0].equals("")) {
                        str = "Mật khẩu hay ID không đúng, xin nhập lại!";
                        MainCanvas.TopForm((byte)0, str, "Xác nhận", "", -3, -120);
                        return;
                     }

                     MainCanvas.ni.send(2297615);
                  }
               }
            } else if (this.clientCommand == 2425008) {
               if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
                  UIMenu.formSaveForm();
                  label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(3);
                  label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
                  label_3 = (UILabel)MainCanvas.curForm.getComponents().elementAt(5);
                  label_4 = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
                  label_5 = (UILabel)MainCanvas.curForm.getComponents().elementAt(7);
                  if (label_1.isFocus()) {
                     MainCanvas.ni.send(2425029);
                  } else if (label_2.isFocus()) {
                     MainCanvas.curForm = ParseUI.parseUI("/cm/ui/charge.ui");
                     MainCanvas.curForm.clientCommand = -1610612697;
                     chargeText = (UITextField)MainCanvas.curForm.getComponents().elementAt(5);
                     chargeText.setMaxNumber(100L);
                  } else if (label_3.isFocus()) {
                     MainCanvas.ni.send(2425010);
                     MainCanvas.TopForm((byte)0, "Hãy chờ ...", "", "", -120, -120);
                     MainCanvas.curForm = ParseUI.parseUI("/cm/ui/remain.ui");
                     MainCanvas.curForm.clientCommand = -1610612696;
                     textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(3);
                     textarea.setContent("");
                  } else if (label_4.isFocus()) {
                     MainCanvas.ni.send(2425011);
                     MainCanvas.TopForm((byte)0, "Hãy chờ ...", "", "", -120, -120);
                     MainCanvas.curForm = ParseUI.parseUI("/cm/ui/detail.ui");
                     MainCanvas.curForm.clientCommand = -1610612695;
                     textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(3);
                     textarea.setContent("");
                  } else if (label_5.isFocus()) {
                     MainCanvas.curForm = ParseUI.parseUI("/cm/ui/phelp.ui");
                     MainCanvas.curForm.clientCommand = -1610612686;
                     textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(3);
                     textarea.addUIScroll();
                  }
               }
            } else if (this.clientCommand == -1610612688) {
               if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
                  label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
                  label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(5);
                  label_3 = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
                  label_4 = (UILabel)MainCanvas.curForm.getComponents().elementAt(7);
                  if (label_1.isFocus()) {
                     backUIForm();
                  } else {
                     UITextArea detailArea;
                     if (label_2.isFocus()) {
                        UIMenu.formSaveForm();
                        MainCanvas.ni.send(2425010);
                        MainCanvas.TopForm((byte)0, "Hãy chờ ...", "", "", -120, -120);
                        MainCanvas.curForm = ParseUI.parseUI("/cm/ui/remain.ui");
                        MainCanvas.curForm.clientCommand = -1610612696;
                        detailArea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(3);
                        detailArea.setContent("");
                     } else if (label_3.isFocus()) {
                        UIMenu.formSaveForm();
                        MainCanvas.ni.send(2425011);
                        MainCanvas.TopForm((byte)0, "Hãy chờ ...", "", "", -120, -120);
                        MainCanvas.curForm = ParseUI.parseUI("/cm/ui/detail.ui");
                        MainCanvas.curForm.clientCommand = -1610612695;
                        detailArea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(3);
                        detailArea.setContent("");
                     } else if (label_4.isFocus()) {
                        backUIForm();
                        backUIForm();
                     }
                  }
               }
            } else if (this.clientCommand == -1610612696) {
               if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
                  label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
                  label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(5);
                  label_3 = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
                  if (label_1.isFocus()) {
                     UIMenu.formSaveForm();
                     MainCanvas.curForm = ParseUI.parseUI("/cm/ui/charge.ui");
                     MainCanvas.curForm.clientCommand = -1610612697;
                     chargeText = (UITextField)MainCanvas.curForm.getComponents().elementAt(5);
                     chargeText.setMaxNumber(100L);
                  } else if (label_2.isFocus()) {
                     UIMenu.formSaveForm();
                     MainCanvas.ni.send(2425011);
                     MainCanvas.TopForm((byte)0, "Hãy chờ ...", "", "", -120, -120);
                     MainCanvas.curForm = ParseUI.parseUI("/cm/ui/detail.ui");
                     MainCanvas.curForm.clientCommand = -1610612695;
                     UITextArea detailArea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(3);
                     detailArea.setContent("");
                  } else if (label_3.isFocus()) {
                     backUIForm();
                  }
               }
            } else if (this.clientCommand == -1610612695) {
               if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
                  UIMenu.formSaveForm();
                  label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
                  label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(5);
                  if (label_1.isFocus()) {
                     MainCanvas.curForm = ParseUI.parseUI("/cm/ui/pcrecord.ui");
                     MainCanvas.curForm.clientCommand = -1610612640;
                  } else if (label_2.isFocus()) {
                     MainCanvas.curForm = ParseUI.parseUI("/cm/ui/pbrecord.ui");
                     MainCanvas.curForm.clientCommand = -1610612639;
                  }
               }
            } else if (this.clientCommand == -1610612640) {
               if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
                  typeRadio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(3);
                  PassPort.timeType = (byte)(typeRadio.getSelectIndex() + 1);
                  if (PassPort.timeType == 2) {
                     UITopForm.MAXNUMBER = 999999;
                     UITopForm.createLocalTopForm((byte)1, "Tra ngày tháng：\n（Ví dụ: 200906）", "Xác nhận", "Hủy", -1610612640, -1, -1, -1);
                     MainCanvas.curTopForm.MAXWORDSNUM = 6;
                  } else {
                     MainCanvas.TopForm((byte)0, "Hãy chờ ...", "", "", -120, -120);
                     MainCanvas.ni.send(2425012);
                  }
               }
            } else if (this.clientCommand == -1610612639) {
               if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
                  typeRadio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(3);
                  timeRadio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(5);
                  PassPort.timeType = (byte)(timeRadio.getSelectIndex() + 1);
                  switch(typeRadio.getSelectIndex()) {
                  case 0:
                     PassPort.chooseType = 3;
                     break;
                  case 1:
                     PassPort.chooseType = 5;
                     break;
                  case 2:
                     PassPort.chooseType = 13;
                  }

                  if (PassPort.timeType == 2) {
                     UITopForm.MAXNUMBER = 999999;
                     UITopForm.createLocalTopForm((byte)1, "Tra ngày tháng：\n（Ví dụ: 200906）", "Xác nhận", "Hủy", -1610612639, -1, -1, -1);
                     MainCanvas.curTopForm.MAXWORDSNUM = 6;
                  } else {
                     MainCanvas.TopForm((byte)0, "Xin chờ...", "", "", -120, -120);
                     MainCanvas.ni.send(2425013);
                  }
               }
            } else if (this.clientCommand == 2424993) {
               if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
                  label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
                  label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(5);
                  if (label_1.isFocus()) {
                     backUIForm();
                  } else if (label_2.isFocus()) {
                     MainCanvas.quitUI();
                  }
               }
            } else if (this.clientCommand == -1610612638) {
               if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
                  MainCanvas.name_password[0] = null;
                  MainCanvas.name_password[1] = null;
                  userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(9);
                  MainCanvas.name_password[0] = userNameText.getSb().toString().trim();
                  pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(10);
                  MainCanvas.name_password[1] = pswText.getSb().toString().trim();
                  MainCanvas.m_sTemporaryStr[0] = MainCanvas.name_password[0];
                  MainCanvas.m_sTemporaryStr[1] = MainCanvas.name_password[1];
                  if (MainCanvas.name_password[0] == null || MainCanvas.name_password[0].equals("") || MainCanvas.name_password[1] == null || MainCanvas.name_password[1].equals("")) {
                     Util.Touch_Enroll();
                     return;
                  }

                  str = "Tài khoản：" + MainCanvas.name_password[0] + "\n" + "Mật khẩu：" + MainCanvas.name_password[1] + "\n" + "Xác nhận Tài khoản, Mật khẩu？";
                  UITopForm.createLocalTopForm((byte)0, (String)str, "Xác nhận", "Sửa đổi", -28, -1);
               }
            } else if (this.clientCommand == 2425079 && MainCanvas.isInputDown(196608)) {
               MainCanvas.ni.send(2425080);
            }
         } else if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
            if (MainCanvas.isInputDownOrHold(4096)) {
               --shoppingID;
               if (shoppingID < 0) {
                  shoppingID = shoppingListId.length - 1;
               }
            } else if (MainCanvas.isInputDownOrHold(8192)) {
               ++shoppingID;
               if (shoppingID > shoppingListId.length - 1) {
                  shoppingID = 0;
               }
            }
         } else {
            this.keyInSelectShoppingList();
         }

         if (this.clientCommand == -1610612687) {
            if (MainCanvas.isInputDown(4096)) {
               if (this.faceSelect >= 3) {
                  this.faceSelect -= 3;
               }
            } else if (MainCanvas.isInputDown(8192)) {
               if (this.faceSelect <= MainCanvas.faceImg.cols - 1 - 3) {
                  this.faceSelect += 3;
               }
            } else if (MainCanvas.isInputDown(16384)) {
               --this.faceSelect;
               if (this.faceSelect < 0) {
                  this.faceSelect = 0;
               }
            } else if (MainCanvas.isInputDown(32768)) {
               ++this.faceSelect;
               if (this.faceSelect > MainCanvas.faceImg.cols - 1) {
                  this.faceSelect = MainCanvas.faceImg.cols - 1;
               }
            } else if (MainCanvas.isInputDown(131072) || MainCanvas.isInputDown(65536)) {
               if (UIChat.chatForm.textStr.length() + 4 >= 30) {
                  UITopForm.createLocalTopForm((byte)5, (String)"Nhập chữ số trò chuyện đã quá nhiều!", "Xác nhận", "", -1, -2);
               } else {
                  UIChat var10000 = UIChat.chatForm;
                  var10000.textStr = var10000.textStr + "#" + MainCanvas.curForm.faceSelect;
                  UIChat.chatForm.advancedForm(false);
               }
            }
         }
      }

   }

   public static void backUIForm() {
      if (PCPackage.exchangeIndex[0] != -1) {
         MainCanvas.curForm.resetExchangeVal();
      } else if (backElseAction()) {
         if (MainCanvas.curForm.clientCommand != 589834 && MainCanvas.curForm.clientCommand != 655363 && !UIChat.playerInfoInChat) {
            if (MainCanvas.curForm.clientCommand == 2097155 && PCGamble.rebound) {
               return;
            }

            if ((MainCanvas.curForm.clientCommand == 196642 || MainCanvas.curForm.clientCommand == 196641) && MainCanvas.curTopForm != null) {
               return;
            }

            backUIFormAction();
         } else {
            UIChat.playerInfoInChat = false;
            backUIFormAction();
            MainCanvas.setGameStateWithoutOld((byte)3);
         }
      }

   }

   private static final boolean backElseAction() {
      boolean continueAction = true;
      int i;
      byte picID;
      int i;
      UIRadio radio;
      short tmpSetup;
      UIRadio radio;
      switch(MainCanvas.curForm.clientCommand) {
      case -1610612735:
         Util.saveIntRecord(UIList.keyValue, "gameKey" + MainCanvas.roleSaveName);
         MainCanvas.curGameKey = Util.loadIntRecord("gameKey" + MainCanvas.roleSaveName, MainCanvas.DEFAULT_GAME_KEY.length);
         break;
      case -1610612734:
         SIManager.getInstance();
         SIManager.shortCut.saveShortCutOption();
         break;
      case -1610612728:
         tmpSetup = 0;
         picID = 0;

         for(i = 0; i < 5; ++i) {
            UIRadio radio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(i + 3);
            if (i == 0) {
               picID = radio.getSelectIndex();
            } else {
               tmpSetup = (short)(tmpSetup | radio.getSelectIndex() << i - 1);
            }
         }

         MainCanvas.showSetup = tmpSetup;
         MainCanvas.picQuaInd = (byte)picID;
         Util.saveShortRecord(new short[]{MainCanvas.showSetup, picID}, "showSetup");
         MainCanvas.setShowSetup();
         if (GOManager.getCurrentTarget().type == 1 && GOManager.getCurrentTarget() != Player.getInstance() && picID == 2) {
            GOManager.setCurrentTarget(Player.getInstance());
            Player.getInstance().changeTargetRound();
         }

         MainCanvas.ni.send(196632);
         break;
      case -1610612727:
         radio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(3);
         picID = radio.getSelectIndex();
         MainCanvas.pvpSetup = picID;
         if (Player.getInstance().getLevel() < 20) {
            MainCanvas.ni.send(196635);
         }

         radio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(9);
         MainCanvas.quantitySetup = (byte)(radio.getSelectIndex() ^ 1);
         Util.saveByteRecord(new byte[]{MainCanvas.quantitySetup}, "quantitySetup");
         Util.saveByteRecord(new byte[]{MainCanvas.pvpSetup}, "pvpSetup" + MainCanvas.roleSaveName);
         MainCanvas.ni.send(196637);
         break;
      case -1610612699:
         tmpSetup = 0;

         for(int i = 0; i < 5; ++i) {
            radio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(i + 4);
            tmpSetup = (short)(tmpSetup | radio.getSelectIndex() << i);
         }

         SIChat.channelOpen = tmpSetup;
         MainCanvas.ni.send(655362);
         break;
      case -1610612687:
         UIChat.chatForm.advancedForm(false);
         continueAction = false;
         break;
      case -1610612632:
         PCMonthly.displayUI = false;
         break;
      case 196641:
      case 196642:
         if (GOManager.m_bPlayersortNPC) {
            GOManager.m_bPlayersortNPC = false;
         } else {
            UITopForm.createLocalTopForm((byte)0, (String)"Muốn thoát khỏi chiến trường?", "Xác nhận", "Hủy", 852005, -1);
         }
         break;
      case 262154:
         if (PCTask.isReceiveToView) {
            PCTask.isReceiveToView = false;
         }

         UIList.canReceive = true;
         break;
      case 262193:
         UIChat.chatForm.advancedForm(false);
         continueAction = false;
         break;
      case 393225:
      case 393229:
         UIPanel.m_bDataH = false;
         break;
      case 393231:
      case 655363:
      case 1703948:
      case 1703954:
      case 1900552:
      case 3342337:
      case 3342338:
      case 3342339:
         if (PCTreasure.treasurePicID > 0) {
            PCTreasure.treasurePicID = 0;
         }

         if (PCTreasure.interphase) {
            PCTreasure.interphase = false;
         }

         if (!PCTreasure.unlockMenu) {
            PCTreasure.unlockMenu = true;
         }
         break;
      case 458760:
         PCGem.m_bAppendMenu = 0;
         PCTreasure.backtrack();
         break;
      case 458773:
         UIChat.chatForm.advancedForm(false);
         continueAction = false;
         break;
      case 524292:
         if (UITitle.isTalent_AddPoint) {
            UITopForm.createLocalTopForm((byte)35, (String)"Thiên phú đã tăng điểm, có muốn thoát không?", "Có", "Không", -2, -2);
            continueAction = false;
         }
         break;
      case 720910:
         radio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(3);
         SITeam.divideType = radio.getSureIndex();
         MainCanvas.ni.send(720903);
         break;
      case 720913:
         MainCanvas.ni.send(720912);
         break;
      case 720915:
      case 720916:
         MainCanvas.ni.send(720917);
         break;
      case 852020:
         GOManager.m_bPlayersortNPC = false;
         if (GOManager.m_bBattlefieldMenuId != 3) {
            GOManager.m_bBattlefieldMenuId = 3;
         }
         break;
      case 917505:
         PCGem.m_bEquipIndex = -1;
         PCGem.m_bAppendMenu = 0;
         break;
      case 917525:
      case 2031621:
      case 2031636:
      case 3342356:
         PCUnsealGemCarve.EliminateBata();
         break;
      case 1245185:
         UIPanel.m_bDataH = false;
         UIPanel.m_bDataX = false;
         break;
      case 1638401:
         PCClan.clanName = "";
         PCClan.clanIntro = "";
         break;
      case 1638421:
         PCClan.sendRight();
         break;
      case 1703937:
      case 1703938:
      case 1703952:
         UIGrid.EliminateMail();
         PCMail.EliminateMail();
         break;
      case 1703945:
         PCMail.m_bSymbol = 0;
         break;
      case 1703946:
         UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(10);
         UIForm f = (UIForm)MainCanvas.backForms.elementAt(MainCanvas.backForms.size() - 1);
         if (f != null) {
            for(i = 0; i < PCMail.Accessory.length; ++i) {
               UIPicture pic = (UIPicture)f.getComponents().elementAt(i + 7);
               pic.quality = (byte)PCMail.Accessory[i][2];
               short picID = PCMail.Accessory[i][1];
               pic.isWpPic = true;
               pic.wpIndex = picID;
               pic.setImg(MainCanvas.stuffMImg);
               if (PCMail.Accessory[i][3] == 1) {
                  if (grid.stuffShowMiniNum[PCMail.Accessory[i][0]]) {
                     pic.setShowNum(true, (byte)1);
                  } else {
                     pic.setShowNum(false);
                  }
               } else {
                  grid.stuffShowMiniNum[i] = true;
                  if (PCMail.Accessory[i][3] > 0) {
                     pic.setShowNum(true, (byte)PCMail.Accessory[i][3]);
                  } else {
                     pic.setShowNum(false);
                  }
               }
            }
         }
         break;
      case 1703959:
      case 1703960:
      case 1703961:
         MainCanvas.m_sBingZi = false;
         break;
      case 1703969:
         continueAction = false;
         break;
      case 1703971:
         if (PCMail.m_bContentment == 5) {
            continueAction = false;
         }
         break;
      case 1703972:
         continueAction = false;
         break;
      case 1835009:
         PCKing.kingName = "";
         PCKing.kingIntro = "";
         break;
      case 1900547:
         PCAuction.RetrieveGoods((byte)2);
         break;
      case 1966081:
         if (PCGem.m_bEnchaseRoute == 2) {
            MainCanvas.ni.send(917507);
         } else if (PCGem.m_bEnchaseRoute == 1) {
            UIGrid.severGridIndex = PCGem.m_bEquipIndex;
            MainCanvas.ni.send(458753);
         }

         PCGem.m_bEquipIndex = -1;
         PCGem.m_bGemIndex = -1;
         break;
      case 1966087:
      case 1966089:
         for(i = 0; i < MainCanvas.backForms.size() - 1; ++i) {
            MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
         }

         PCGem.m_bEquipIndex = -1;
         PCGem.m_bGemIndex = -1;
         break;
      case 1966097:
         UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
         UIPicture pic1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(6);
         UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(7);

         for(int i = 0; i < UIGrid.AuctionBag.length; ++i) {
            UIGrid.AuctionBag[i] = -1;
         }

         pic.setShowNum(false);
         pic.isWpPic = false;
         pic.setImg((MImage)null);
         pic1.setShowNum(false);
         pic1.isWpPic = false;
         pic1.setImg((MImage)null);
         grid.setFocus(false, MainCanvas.curForm);
         pic.setFocus(true, MainCanvas.curForm);
         pic1.setFocus(false, MainCanvas.curForm);
         pic.setShowNum(false);
         pic.isWpPic = false;
         pic.setImg((MImage)null);
         pic1.setShowNum(false);
         pic1.isWpPic = false;
         pic1.setImg((MImage)null);
         PCGem.m_bEquipIndex = -1;
         PCGem.m_bGemIndex = -1;
         PCGem.m_bFocus = -1;
         PCGem.m_bListIndex = 0;
         PCGem.m_bExamine = -1;
         break;
      case 1966100:
         PCGem.m_bListIndex = 0;

         for(i = 0; i < UIGrid.AuctionBag.length; ++i) {
            UIGrid.AuctionBag[i] = -1;
         }

         PCGem.m_bEquipIndex = -1;
         PCGem.m_bGemIndex = -1;
         PCGem.m_bFocus = -1;
         PCGem.m_bListIndex = 0;
         PCGem.m_bExamine = -1;
         PCGem.m_bSucceed = -1;
         break;
      case 2097155:
         if (!PCGamble.rebound) {
            PCGamble.Begin = false;
            PCGamble.arithmometer = 0;
            PCGamble.m_nCircle = 0;
            PCGamble.m_nJetton = 0;
            PCGamble.award = false;
            PCGamble.award_1 = false;
            PCGamble.renovate = false;
            PCGamble.Begin_1 = false;
            PCGamble.luxian = 0;
            PCGamble.BigOrSmall = 0;
            PCGamble.index = 0;
            PCGamble.server_index = 0;
            PCGamble.m_nCircleCounter = 0;
            PCGamble.m_nJetton = 0;

            for(i = 0; i < 7; ++i) {
               PCGamble.textfield[i] = null;
            }
         }
         break;
      case 2228225:
      case 2228256:
         PCGemJoinOrRemove.CleanupData();
         break;
      case 2425024:
      case 2425029:
      case 2425072:
         PCIncrement.m_bBuyMoney = 0;
         if (MainCanvas.curForm.clientCommand == 2425029 && MainCanvas.backForms.size() > 1) {
            MainCanvas.backForms.removeElementAt(1);
         }

         if (PCIncrement.fromExpandBagToBuy) {
            PCIncrement.fromExpandBagToBuy = false;
            continueAction = false;
            MainCanvas.ni.send(1179658);
         }

         if (PCIncrement.formHornChannelToBuy) {
            PCIncrement.formHornChannelToBuy = false;
            continueAction = false;
            backUIFormAction();
            UIChat.chatForm = new UIChat((byte)6);
            MainCanvas.setGameStateWithoutOld((byte)3);
         }
         break;
      case 2555905:
         if (PCFarm.m_nPlayerIndex != 0) {
            PCFarm.m_nPlayerIndex = 0;
         }

         PCFarm.m_nFarm_On_Off = 0;
         MainCanvas.ni.send(2555952);
         break;
      case 2555906:
         PCFarm.m_sPropMane = new String[3];
         PCFarm.m_bPropManeFinger = 0;
         break;
      case 2555908:
      case 2555911:
      case 2555957:
         MainCanvas.ni.send(2555973);
         break;
      case 2555936:
         if (PCFarm.m_bBacking == 1) {
            PCFarm.m_bBacking = -1;
         }
         break;
      case 2555941:
      case 2555943:
      case 2555945:
         if (MainCanvas.curForm.clientCommand == 2555945) {
            PCFarm.m_bRenovate = 1;
         } else if (MainCanvas.curForm.clientCommand == 2555943) {
            PCFarm.m_bRenovate = 2;
         } else if (MainCanvas.curForm.clientCommand == 2555941) {
            PCFarm.m_bRenovate = 3;
         }

         MainCanvas.ni.send(2555974);
         break;
      case 2621442:
         if (PCIncrement.sum > 0) {
            PCIncrement.sum = 0;
            MainCanvas.ni.send(2425024);
         }
         break;
      case 3145729:
         MainCanvas.ni.send(3145736);
         break;
      case 3145730:
         MainCanvas.ni.send(3145737);
         break;
      case 3342340:
         if (PCTreasure.tally != 0) {
            PCTreasure.tally = 0;
         }
      }

      return continueAction;
   }

   public static final void backUIFormAction() {
      SIChat.removeChatLocal();
      int tmpSize = MainCanvas.backForms.size();
      if (tmpSize > 0) {
         Object tmpObj = MainCanvas.backForms.elementAt(tmpSize - 1);
         MainCanvas.backForms.removeElementAt(tmpSize - 1);
         if (tmpObj instanceof Integer) {
            MainCanvas.curFormVector.removeAllElements();
            int tmpCommand = (Integer)tmpObj;
            if (tmpCommand == 0) {
               MainCanvas.quitUI();
            } else {
               MainCanvas.ni.send(tmpCommand);
            }
         } else if (tmpObj instanceof UIForm) {
            MainCanvas.curFormVector.removeAllElements();
            MainCanvas.curForm = (UIForm)tmpObj;
            MainCanvas.curForm.addWarningStr();
         } else if (tmpObj instanceof Vector) {
            MainCanvas.curFormVector.removeAllElements();
            Vector tmpVector = (Vector)tmpObj;
            Object tj = tmpVector.elementAt(0);
            if (tj instanceof UIForm) {
               MainCanvas.curFormVector = tmpVector;
               MainCanvas.curForm = (UIForm)MainCanvas.curFormVector.elementAt(((UIForm)tj).foldedIndex);
               MainCanvas.curForm.addWarningStr();
            } else if (tj instanceof Integer) {
               MainCanvas.ni.sendCommands(tmpVector);
            }
         } else if (tmpObj instanceof Byte) {
            byte index = (Byte)tmpObj;
            MainCanvas.curForm = (UIForm)MainCanvas.curFormVector.elementAt(index);
            ((UIForm)MainCanvas.curFormVector.elementAt(0)).foldedIndex = index;
            MainCanvas.curForm.addWarningStr();
         }
      } else {
         if (MainCanvas.getState() == 40) {
            MainCanvas.setState((byte)5);
         }

         MainCanvas.quitUI();
         if (UIMenu.npcMenu != null) {
            MainCanvas.setGameState((byte)5);
         }

         MainCanvas.rightMenu = new UIRightMenu();
         MainCanvas.setGameState((byte)1);
      }

   }

   public boolean haveFormMenu() {
      for(int i = 0; i < this.components.size(); ++i) {
         UIComponent uic = (UIComponent)this.components.elementAt(i);
         if (uic.isShowMenu) {
            return true;
         }
      }

      return false;
   }

   public void cancelFormMenu() {
      for(int i = 0; i < this.components.size(); ++i) {
         UIComponent uic = (UIComponent)this.components.elementAt(i);
         uic.isShowMenu = false;
      }

   }

   public void drawAttatckHit(Graphics g) {
      if (MainCanvas.countTick % 4 < 2) {
         g.setColor(attColor[0]);
      } else {
         g.setColor(attColor[1]);
      }

      g.drawRect(0, 0, MainCanvas.screenW - 1, MainCanvas.screenH - 1);
      g.drawRect(1, 1, MainCanvas.screenW - 3, MainCanvas.screenH - 3);
   }

   private void IncrementCommand() {
      MainCanvas.ni.send(2424839);
      UIMenu.formSaveForm();
   }

   private void AmendNickname() {
      final Form form = new Form("Sửa Tên đăng nhập");
      final TextField tf = new TextField("Sửa Tên đăng nhập, nhiều nhất 6 ký tự (gồm số)", "", 6, 0);
      form.append(tf);
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            if (c == okCmd) {
               Alert alert;
               if (MainCanvas.m_sCharacterName[MainCanvas.m_bChoose].equals(tf.getString())) {
                  tf.setString("");
                  alert = new Alert(Cons.C_STR[9], "Xin thay tên!", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
               }

               if (!MainCanvas.CharacterValidate(tf.getString(), (byte)2)) {
                  alert = new Alert(Cons.C_STR[9], "Nội dung không đúng, hãy nhập lại!", (Image)null, AlertType.ERROR);
                  tf.setString("");
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
               }

               if (tf.getString() == null || tf.getString().equals("")) {
                  alert = new Alert(Cons.C_STR[9], "Xin nhập nội dung!", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(alert, form);
                  return;
               }

               UIForm.m_sAmendNickname[0] = MainCanvas.m_sCharacterName[MainCanvas.m_bChoose];
               UIForm.m_sAmendNickname[1] = tf.getString();
               MainCanvas.ni.send(1114119);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
               String str = "Hãy chờ ...";
               MainCanvas.TopForm((byte)0, str, "", "", -120, -120);
            } else if (c == exitCmd) {
               tf.setString("");
               form.removeCommand(okCmd);
               form.removeCommand(exitCmd);
               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }

   private void remain() {
      MainCanvas.TopForm((byte)0, "Hãy chờ ...", "", "", -120, -120);
      PassPort.isDetail = false;
      if (PassPort.haveUserIDAndKey()) {
         String charge_url = "/judgeserver/QueryBalance?usr=&userId=" + MainCanvas.userID + "&game=fengshen&subgame=" + "fengshen" + "&from=kong";
         PassPort pp = new PassPort(PassPort.CM_CHARGE_IP, charge_url, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)3);
         pp.authenticate((byte)7);
      } else {
         PassPort.isFirstConnect = true;
         PassPort pp = new PassPort(PassPort.CM_PASSPORT_IP, PassPort.CM_PASSPORT_GET_URL, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)1);
         pp.authenticate((byte)7);
      }

   }

   private void detail() {
      MainCanvas.TopForm((byte)0, "Hãy chờ ...", "", "", -120, -120);
      PassPort.isDetail = true;
      if (PassPort.haveUserIDAndKey()) {
         String charge_url = "/judgeserver/QueryBalance?usr=&userId=" + MainCanvas.userID + "&game=fengshen&subgame=" + "fengshen" + "&from=kong";
         PassPort pp = new PassPort(PassPort.CM_CHARGE_IP, charge_url, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)3);
         pp.authenticate((byte)7);
      } else {
         PassPort.isFirstConnect = true;
         PassPort pp = new PassPort(PassPort.CM_PASSPORT_IP, PassPort.CM_PASSPORT_GET_URL, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)1);
         pp.authenticate((byte)7);
      }

   }

   public static void SimplenessIntroduce() {
      UIRadio camp = (UIRadio)MainCanvas.curForm.getComponents().elementAt(5);
      UIRadio phyle = (UIRadio)MainCanvas.curForm.getComponents().elementAt(6);
      UIRadio vocation = (UIRadio)MainCanvas.curForm.getComponents().elementAt(8);
      UITextArea textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(10);
      UITextField textfield = (UITextField)MainCanvas.curForm.getComponents().elementAt(9);
      if (camp.isFocus()) {
         if (MainCanvas.m_bCamp == 1) {
            textarea.setContent(Cons.SIMPLENESS_INTRODUCE[0]);
         } else if (MainCanvas.m_bCamp == 2) {
            textarea.setContent(Cons.SIMPLENESS_INTRODUCE[1]);
         }
      } else if (phyle.isFocus()) {
         if (MainCanvas.m_bPhyle == 1) {
            textarea.setContent(Cons.SIMPLENESS_INTRODUCE[2]);
         } else if (MainCanvas.m_bPhyle == 2) {
            textarea.setContent(Cons.SIMPLENESS_INTRODUCE[3]);
         } else if (MainCanvas.m_bPhyle == 3) {
            textarea.setContent(Cons.SIMPLENESS_INTRODUCE[4]);
         } else if (MainCanvas.m_bPhyle == 4) {
            textarea.setContent(Cons.SIMPLENESS_INTRODUCE[5]);
         }
      } else if (vocation.isFocus()) {
         if (MainCanvas.m_bVocation == 1) {
            textarea.setContent(Cons.SIMPLENESS_INTRODUCE[6]);
         } else if (MainCanvas.m_bVocation == 2) {
            textarea.setContent(Cons.SIMPLENESS_INTRODUCE[7]);
         } else if (MainCanvas.m_bVocation == 3) {
            textarea.setContent(Cons.SIMPLENESS_INTRODUCE[8]);
         } else if (MainCanvas.m_bVocation == 4) {
            textarea.setContent(Cons.SIMPLENESS_INTRODUCE[9]);
         } else if (MainCanvas.m_bVocation == 5) {
            textarea.setContent(Cons.SIMPLENESS_INTRODUCE[10]);
         } else if (MainCanvas.m_bVocation == 6) {
            textarea.setContent(Cons.SIMPLENESS_INTRODUCE[11]);
         }
      } else if (textfield.isFocus()) {
         textarea.setContent("Nhập Tên đăng nhập");
      }

   }

   private void FarmKeystoke(byte index) {
      for(int i = 0; i < 3; ++i) {
         UILabel label_bevy = (UILabel)MainCanvas.curForm.getComponents().elementAt(i + 35);
         if (label_bevy.isFocus() && label_bevy.isShowMenu) {
            return;
         }

         if (label_bevy.isFocus()) {
            label_bevy.setFocus(false, MainCanvas.curForm);
         }
      }

      UILabel label = (UILabel)MainCanvas.curForm.getComponents().elementAt(index);
      if (!label.isFocus()) {
         label.setFocus(true, MainCanvas.curForm);
         label.addEquipPicMenu();
      } else if (label.isFocus() && !label.isShowMenu) {
         label.addEquipPicMenu();
      }

      UIPicture picture;
      int i;
      for(i = 0; i < 12; ++i) {
         picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 11);
         if (picture.isFocus()) {
            picture.setFocus(false, MainCanvas.curForm);
         }
      }

      for(i = 0; i < 6; ++i) {
         picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 25);
         if (picture.isFocus()) {
            picture.setFocus(false, MainCanvas.curForm);
         }
      }

   }

   public static void drawDelieve(Graphics g, int x, int y, byte estate) {
      if (estate == 1) {
         g.setColor(7934208);
         g.fillRect(x, y, 10, 8);
         g.setColor(16721152);
         g.fillRect(x + 1, y + 1, 8, 6);
         g.setColor(7934208);
         g.drawLine(x, y, x + 5, y + 5);
         g.drawLine(x + 9, y, x + 4, y + 5);
      }

      if (estate == 2) {
         g.setColor(4144959);
         g.fillRect(x - 3, y, 13, 8);
         g.fillTriangle(x - 3, y, x + 3, y - 5, x + 10, y);
         g.setColor(8947848);
         g.fillRect(x - 2, y + 1, 11, 6);
         g.fillTriangle(x - 2, y + 1, x + 3, y - 4, x + 10, y + 2);
         g.setColor(4144959);
         g.drawLine(x - 3, y, x - 3 + 12, y);
         g.drawLine(x - 3, y, x + 2, y + 5);
         g.drawLine(x - 3 + 12, y, x + 4, y + 5);
         g.fillRect(x + 2 + 1, y + 5, 1, 1);
      }

   }

   private void drawUIShoppingList(Graphics g) {
      int panelWidth = MainCanvas.CHARW * Util.getMaxStringLen(shoppingList) + 70;
      int panelX = (MainCanvas.screenW - panelWidth) / 2;
      int panelHeight = MainCanvas.CHARH * shoppingList.length + 16;
      int panelY = (((UIComponent)this.getComponents().elementAt(3)).getPositionY() - ((UIComponent)this.getComponents().elementAt(2)).getPositionY() - panelHeight) / 2 + ((UIComponent)this.getComponents().elementAt(2)).getPositionY();
      int innerX1 = panelX + 4 + 10 - 1;
      int innerY1 = panelY + 5 - 1;
      int innerW1 = panelWidth - 28 + 2;
      int innerH1 = panelHeight - 10 + 2;
      g.setColor(14527877);
      g.fillRect(innerX1 + 1, innerY1 + 1, innerW1 - 1, innerH1 - 1);
      g.setColor(4930874);
      g.drawRect(innerX1, innerY1, innerW1, innerH1);
      int innerX2 = innerX1 + 2;
      int innerY2 = innerY1 + 2;
      int innerW2 = innerW1 - 4;
      int innerH2 = innerH1 - 4;
      g.setColor(16575691);
      g.fillRect(innerX2 + 1, innerY2 + 1, innerW2 - 1, innerH2 - 1);
      g.setColor(4930874);
      g.drawRect(innerX2, innerY2, innerW2, innerH2);
      int innerX3 = innerX2 + 2;
      int innerY3 = innerY2 + 2;
      int innerW3 = innerW2 - 4;
      int innerH3 = innerH2 - 4;
      g.setColor(14397060);
      g.drawRect(innerX3, innerY3, innerW3, innerH3);
      int innerX4 = innerX3 + 1;
      int innerY4 = innerY3 + 1;
      if (this.clientCommand == 2425024) {
         Util.drawImage(g, MainCanvas.smenu, innerX1 - MainCanvas.smenu.getWidth(), innerY1 + (MainCanvas.CHARH + 3 >> 1) - 5, 2);
         Util.drawImage(g, MainCanvas.smenu, innerX1 + innerW1, innerY1 + 5 * (MainCanvas.CHARH + 2) + 2, 0);
      } else if (this.clientCommand == 2425077) {
         Util.drawImage(g, MainCanvas.smenu, innerX1 - (MainCanvas.smenu.getWidth() - 1), innerY1 + (MainCanvas.CHARH + 3 >> 1) - 5, 2);
         Util.drawImage(g, MainCanvas.smenu, innerX1 + innerW1, innerY1 + 3 * (MainCanvas.CHARH + 2) - 10, 0);
      }

      int curStrColor = false;
      int startStrY = innerY4 - 1;

      for(int i = 0; i < shoppingList.length; ++i) {
         int rectY = innerY4 + i * MainCanvas.CHARH;
         int rectW = innerW3 - 1;
         int rectH = MainCanvas.CHARH;
         if (i % 2 == 0) {
            g.setColor(Cons.COLOR_TEXT_BG);
            g.fillRect(innerX4, rectY, rectW, rectH);
         }

         int lineX;
         int lineY;
         int lineX2;
         int curStrColor;
         if (i == shoppingID) {
            curStrColor = Cons.COLOR_PANEL_BG;
            lineX = innerX4 + 1;
            lineY = rectY + 1;
            lineX2 = rectW - 1;
            int fillH = rectH - 2;
            g.setColor(Cons.COLOR_MENU_SEL_ITEM_BG);
            g.fillRect(lineX, lineY, lineX2, fillH);
         } else {
            curStrColor = Cons.COLOR_FONT_1;
         }

         Util.drawString(g, shoppingList[i], panelWidth, panelX, startStrY + 2 + i * MainCanvas.CHARH - 1, curStrColor);
         if (i != shoppingList.length - 1) {
            lineX = innerX3 + 1;
            lineY = startStrY + (i + 1) * MainCanvas.CHARH;
            lineX2 = lineX + innerW3 - 2;
            g.setColor(Cons.COLOR_BACKGROUND);
            g.drawLine(lineX, lineY, lineX2, lineY);
         }
      }

   }

   private void keyInSelectShoppingList() {
      PCIncrement.m_bBuyMoney = shoppingListId[shoppingID];
      switch(PCIncrement.m_bBuyMoney) {
      case 0:
         UIMenu.formSaveCommand();
         MainCanvas.ni.send(2425029);
         break;
      case 1:
         UIMenu.formSaveCommand();
         MainCanvas.ni.send(2425029);
         break;
      case 2:
         MainCanvas.ni.send(2425029);
         UIMenu.formSaveForm();
         break;
      case 3:
         MainCanvas.ni.send(2425029);
         UIMenu.formSaveForm();
         break;
      case 4:
         UIMenu.formSaveCommand();
         PassPort.rechardType = 1;
         MainCanvas.setState((byte)40);
         break;
      case 5:
         UIMenu.formSaveCommand();
         MainCanvas.ni.send(2621442);
         break;
      case 6:
         MainCanvas.ni.sendCommands(new int[]{2424834, 2424842});
         UIMenu.formSaveForm();
         break;
      case 7:
         UIMenu.formSaveCommand();
         MainCanvas.ni.send(2425077);
         break;
      case 8:
      case 9:
      case 10:
         UIMenu.formSaveForm();
         MainCanvas.ni.send(2425078);
      }

   }
}

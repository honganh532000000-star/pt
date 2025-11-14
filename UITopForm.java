import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class UITopForm extends UIComponent {
   public static final byte TOPFORM_DEFAULT = 0;
   public static final byte TOPFORM_INPUT_NUM = 1;
   public static final byte TOPFORM_JOIN_TEAM = 2;
   public static final byte TOPFORM_UI_TEAM = 3;
   public static final byte TOPFORM_PLAYER_DEAD = 4;
   public static final byte TOPFORM_TRADE = 5;
   public static final byte TOPFORM_KEY_OPTION = 6;
   public static final byte TOPFORM_LOVER = 7;
   public static final byte TOPFORM_TASK_SHARE = 8;
   public static final byte TOPFORM_CLAN = 9;
   public static final byte TOPFORM_ROLL = 10;
   public static final byte TOPFORM_DUEL = 11;
   public static final byte TOPFORM_KING = 12;
   public static final byte TOPFORM_AUCTION_INPUT_NUM = 13;
   public static final byte TOPFORM_PERSONAL_ENEMY = 14;
   public static final byte TOPFORM_COMPARE = 15;
   public static final byte TOPFORM_TRADE_CANCEL = 16;
   public static final byte TOPFORM_DROPT_MESHWORK = 17;
   public static final byte TOPFORM_HEADER_DIVIDE = 31;
   public static final byte TOPFORM_FORG_LEARN_CONFIRM = 32;
   public static final byte TOPFORM_FORG_CONFIRM = 33;
   public static final byte TOPFORM_ENCHANTING_ITEM_CONFIRM = 34;
   public static final byte TOPFORM_TALENT_CONFIRM = 35;
   public static final byte TOPFORM_FENGSHEN_LAW = 36;
   public static final byte TOPFORM_TOURIST = 37;
   public static final byte TOPFORM_QUICK_MENTOR = 38;
   public static final byte TOPFORM_BATTLE_DATATRANS = 40;
   public static final byte TOPFORM_BATTLE_RESTRICT = 41;
   public static final byte TOPFORM_CLAN_BATTLE = 42;
   public static final byte LEVEL_BUFF = 0;
   public static final byte LEVEL_GAME = 1;
   public static final byte LEVEL_BUSY = 2;
   public static final byte LEVEL_DIRECT = 3;
   public static final byte LEVEL_ALARM = 4;
   public static final byte LEVEL_LOCAL = 5;
   public byte level;
   private String contentDefault;
   private static int heightDefault = 50;
   public byte type;
   private String leftStr;
   private String rightStr;
   private int leftCmd;
   private int rightCmd;
   private int contentUseableW;
   private short innerBoardH;
   private short boardH;
   public static boolean isCancelTrade = false;
   public static String mammothChargeTip = "Xác nhận nạp tiền và phí trừ xin tiếp tục，nếu không xin trở về sẽ không mất phí.";
   private String[] insStrs;
   private int[] colors;
   private short[][] insStrXY;
   private short contentX;
   private short contentY;
   private short innerBoardDownY;
   private static int specialColor = 16711680;
   static final short OUT_X = 4;
   static final short OUT_Y = 5;
   static final short IN_X1 = 10;
   static final short IN_Y1 = 5;
   static final short IN_X2 = 3;
   static final short IN_Y2 = 1;
   static final short IN_X3 = 1;
   static final short IN_Y3 = 1;
   static final short IN_X4 = 1;
   static final short IN_Y4 = 1;
   private short innerHeight;
   private short innerOY;
   public static boolean isLoginErr = false;
   public static String charNum = "0123456789";
   StringBuffer sb;
   public byte MAXWORDSNUM;
   public static int MAXNUMBER = 20;
   private static byte MINNUMBER = 0;
   private byte maskCount;
   StringBuffer sbMask;
   private int interval;
   private int keyOld;
   private int keyIndex;
   private int[] inputButtonXY;
   private String[] labelStrs;
   private int cx;
   public int taskID;
   public static int curTaskID = -1;
   public static int buyNum = -1;
   private static byte tradeIndex = -1;
   private static byte gridIndex = -1;
   public byte[][] colorSigns;
   public static String[] driftStr;
   public static byte driftTime = 0;
   public static boolean isDrawDrift;
   public static byte drawDriftCount;
   public static byte driftColorIndex;
   public static int driftSpecialColor;

   public UITopForm(short x, short y, short w, short h, UIForm form) {
      super(x, y, w, h, form);
      this.level = 0;
      this.contentDefault = "";
      this.type = 0;
      this.leftStr = "";
      this.rightStr = "";
      this.leftCmd = -1;
      this.rightCmd = -1;
      this.contentUseableW = 0;
      this.innerBoardH = 0;
      this.boardH = 0;
      this.insStrs = new String[]{"#"};
      this.colors = new int[]{16711935};
      this.insStrXY = null;
      this.contentX = 0;
      this.contentY = 0;
      this.innerBoardDownY = 0;
      this.innerHeight = -1;
      this.innerOY = -1;
      this.sb = null;
      this.MAXWORDSNUM = 10;
      this.maskCount = 0;
      this.sbMask = null;
      this.interval = 0;
      this.keyOld = -1;
      this.keyIndex = 0;
      this.inputButtonXY = new int[4];
      this.cx = 0;
      this.taskID = -1;
   }

   public UITopForm(byte type, UIForm form) {
      this((short)0, (short)0, (short)0, (short)0, form);
      this.setType(type);
      switch(type) {
      case 0:
      case 4:
      default:
         break;
      case 1:
      case 13:
         this.sb = new StringBuffer();
         this.sb.append("0");
         break;
      case 16:
         isCancelTrade = false;
      }

   }

   public void setTopFormInfo(byte level, int specialColor, String content, String leftButton, String rightButton, int leftCommand, int rightCommand) {
      this.level = level;
      this.setSpecialColor(specialColor);
      this.setTopFormInfo(content, leftButton, rightButton, leftCommand, rightCommand);
   }

   public void setTopFormInfo(String content, String leftButton, String rightButton, int leftCommand, int rightCommand) {
      this.leftCmd = leftCommand;
      this.rightCmd = rightCommand;
      switch(this.getType()) {
      case 1:
         this.setPositionX((short)0);
         this.setWidth((short)MainCanvas.screenW);
         this.contentUseableW = super.width - 28 - 6 - 2 - 2;
         this.setContentStr(content);
         this.setPositionY((short)(MainCanvas.screenH - this.getHeight() >> 1));
         this.setButtonStr(leftButton, rightButton);
         this.contentX = (short)(super.positionX + 4 + 10 + 3 + 1 + 1 + 1);
         this.contentY = (short)(super.positionY + 5 + 1 + 1 + 1 + 0);
         break;
      case 2:
      case 3:
      default:
         this.setPositionX((short)0);
         this.setWidth((short)MainCanvas.screenW);
         this.contentUseableW = super.width - 28 - 6 - 2 - 2;
         this.setContentStr(content);
         this.setPositionY((short)((MainCanvas.screenH - this.boardH) / 2));
         this.setButtonStr(leftButton, rightButton);
         this.contentX = (short)(super.positionX + 4 + 10 + 3 + 1 + 1 + 1);
         this.contentY = (short)(super.positionY + 5 + 1 + 1 + 1 + 3);
         break;
      case 4:
         this.setPositionX((short)0);
         this.setWidth((short)MainCanvas.screenW);
         this.contentUseableW = super.width - 28 - 6 - 2 - 2;
         content = MainCanvas.SUPPORT_POINTER ? "Đã tử vong，$Khung cảm ứng$Hồi phục!" : "Người chơi đã tử vong，nhấn phím $#$ hồi phục!";
         this.setContentStr(content);
         this.setPositionY((short)((MainCanvas.screenH - this.boardH) / 2));
         this.contentX = (short)(super.positionX + 4 + 10 + 3 + 1 + 1 + 1);
         this.contentY = (short)(super.positionY + 5 + 1 + 1 + 1 + 0);
         this.setDiff_ColorContentStr(content, this.insStrs);
      }

   }

   private void setTopFormInfo(String[] content, String leftButton, String rightButton, int leftCommand, int rightCommand) {
      this.leftCmd = leftCommand;
      this.rightCmd = rightCommand;
      if (this.getType() == 13) {
         this.setPositionX((short)0);
         this.setWidth((short)MainCanvas.screenW);
         this.setAuctionInputNumHeight(content.length);
         this.contentUseableW = super.width - 28 - 6 - 2 - 2;
         this.setPositionY((short)(MainCanvas.screenH - this.getHeight() >> 1));
         this.setButtonStr(leftButton, rightButton);
         this.setLabelStrs(content);
      }

   }

   public void setInputNumHeight() {
      this.innerBoardH = (short)(MainCanvas.CHARH * 2 + 6);
      this.boardH = (short)((this.innerBoardH + 2 + 2 + 2 + 10 + 4) * MainCanvas.screenH / 208);
      this.innerBoardDownY = (short)(this.boardH - 5 - 1 - 1 - 1);
      this.setHeight(this.boardH);
   }

   public void setAuctionInputNumHeight(int strNum) {
      this.innerBoardH = (short)((MainCanvas.CHARH + 3) * (2 + strNum));
      this.boardH = (short)((this.innerBoardH + 2 + 2 + 2 + 10 + 4) * MainCanvas.screenH / 208);
      this.innerBoardDownY = (short)(this.boardH - 5 - 1 - 1 - 1);
      this.setHeight(this.boardH);
   }

   public void setContentStr(String content) {
      if (content != null) {
         this.labelStrs = this.colorChangeLine(content, this.contentUseableW, MainCanvas.curFont);
         this.innerBoardH = (short)(this.labelStrs.length * (MainCanvas.curFont.getHeight() + 1) + 5);
         if (this.getType() == 1) {
            this.boardH = (short)(this.innerBoardH + 2 + 2 + 2 + 10 + 4 + 13);
            this.innerBoardDownY = (short)(this.boardH - 5 - 1 - 1 - 1);
         } else {
            this.boardH = (short)(this.innerBoardH + 2 + 2 + 2 + 10 + 4 + 8);
            this.innerBoardDownY = (short)(this.boardH - 5 - 1 - 1 - 1);
         }

         this.setHeight(this.boardH);
      }
   }

   public void setDiff_ColorContentStr(String content, String[] ns) {
      this.insStrXY = Util.wrapText_ColorXY(content, ns, this.contentUseableW, MainCanvas.curFont);

      for(int i = 0; i < this.insStrXY.length; ++i) {
         short[] var10000 = this.insStrXY[i];
         var10000[0] += this.contentX;
         this.insStrXY[i][1] = (short)(this.contentY + MainCanvas.CHARH * (this.insStrXY[i][1] - 1));
      }

      this.innerBoardH = (short)(this.labelStrs.length * (MainCanvas.CHARH + 1) + 5);
      this.boardH = (short)(this.innerBoardH + 2 + 2 + 2 + 10 + 4);
      this.innerBoardDownY = (short)(this.boardH - 5 - 1 - 1 - 1);
      this.setHeight(this.boardH);
   }

   public void setButtonStr(String leftName, String rightName) {
      if (leftName == null) {
         this.leftStr = "";
      } else {
         this.leftStr = leftName;
      }

      if (rightName == null) {
         this.rightStr = "";
      } else {
         this.rightStr = rightName;
      }

   }

   private void drawDefaultButton(Graphics g) {
      g.setColor(Cons.COLOR_FONT_1);
      g.drawString(this.leftStr, this.contentX, super.positionY + this.innerBoardDownY + 1, 36);
      g.drawString(this.rightStr, this.contentX + this.contentUseableW - 2, super.positionY + this.innerBoardDownY + 1, 40);
   }

   private void drawContent(Graphics g) {
      int length = this.labelStrs.length;
      if (this.labelStrs != null) {
         for(int i = 0; i < length; ++i) {
            int offSet = 0;
            char[] tmpChars = this.labelStrs[i].toCharArray();
            int offWidth = MainCanvas.curFont.stringWidth(this.labelStrs[i]) >> 1;

            for(int j = 0; j < tmpChars.length; ++j) {
               if (this.colorSigns[i][j] == 0) {
                  g.setColor(Cons.COLOR_FONT_1);
               } else {
                  g.setColor(specialColor);
               }

               g.drawChar(tmpChars[j], MainCanvas.screenW / 2 - offWidth + offSet, this.contentY + MainCanvas.CHARH * i, 20);
               offSet += MainCanvas.curFont.charWidth(tmpChars[j]);
            }
         }
      }

   }

   public int getSpecialColor() {
      return specialColor;
   }

   public static int getRedColor() {
      return 16711680;
   }

   public void setSpecialColor(int specialColor) {
      UITopForm.specialColor = specialColor;
   }

   private void drawDiff_ColorContent(Graphics g) {
      g.setColor(Cons.COLOR_FONT_1);
      if (this.labelStrs != null) {
         int z = 0;

         int zz;
         for(zz = this.labelStrs.length; z < zz; ++z) {
            g.drawString(this.labelStrs[z], MainCanvas.screenW / 2, this.contentY + MainCanvas.CHARH * z, 17);
         }

         z = 0;

         for(zz = this.colors.length; z < zz; ++z) {
            g.setColor(this.colors[z]);
            g.drawString(this.insStrs[z], this.insStrXY[z][0], this.insStrXY[z][1], 20);
         }
      }

   }

   public void setLocation(short y) {
      this.setPositionY((short)(MainCanvas.screenH / 2 - heightDefault / 2));
   }

   public void draw(Graphics g) {
      switch(this.type) {
      case 1:
         this.drawInputNum(g);
         break;
      case 4:
         this.drawPlayerDead(g);
         break;
      case 10:
      case 31:
         this.drawRollLook(g);
         break;
      case 13:
         this.drawAuctionInputNum(g);
         break;
      default:
         this.drawDefault(g);
      }

   }

   public void keyInTopForm() {
      this.touchScreenAction();
      if (MainCanvas.getState() == 5) {
         this.keyInGameRunState();
      } else {
         this.keyInOtherState();
      }

   }

   private void drawDefault(Graphics g) {
      this.drawBoard(g);
      this.drawContent(g);
      this.drawDefaultButton(g);
   }

   private void drawBoard(Graphics g) {
      short panelWidth = (short)(super.width - 8);
      if (panelWidth <= 0) {
         throw new IllegalArgumentException("Giá trị kiểm soát độ rộng không đủ");
      } else {
         g.setColor(Cons.COLOR_PANEL_BG);
         g.fillRect(super.positionX + 4 + 2, super.positionY + 2, panelWidth - 4, super.height - 4);
         g.setColor(Cons.COLOR_PANEL_BORDER_2);
         g.drawRect(super.positionX + 4 + 1, super.positionY + 1, panelWidth - 3, super.height - 3);
         g.setColor(Cons.COLOR_PANEL_BORDER_1);
         g.drawRect(super.positionX + 4, super.positionY, panelWidth - 1, super.height - 1);
         g.drawImage(MainCanvas.ui_1Img, super.positionX, super.positionY + 5, 20);
         g.drawImage(MainCanvas.ui_1Img, super.positionX, super.positionY + super.height - 5 - MainCanvas.ui_1Img.getHeight(), 20);
         Util.drawImage(g, MainCanvas.ui_1Img, super.positionX + super.width - MainCanvas.ui_1Img.getWidth(), super.positionY + 5, 2);
         Util.drawImage(g, MainCanvas.ui_1Img, super.positionX + super.width - MainCanvas.ui_1Img.getWidth(), super.positionY + super.height - 5 - MainCanvas.ui_1Img.getHeight(), 2);
         int innerX1 = super.positionX + 4 + 10;
         int innerY1 = false;
         int innerY1;
         if (this.innerOY == -1) {
            innerY1 = super.positionY + 5;
         } else {
            innerY1 = super.positionY + 5 + this.innerOY;
         }

         int innerW1 = super.width - 28;
         int innerH1 = false;
         int innerH1;
         if (this.innerHeight == -1) {
            innerH1 = super.height - 10;
         } else {
            innerH1 = this.innerHeight;
         }

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
         this.inputButtonXY[0] = innerX4 + 1;
         this.inputButtonXY[1] = innerY4 + innerH4 - MainCanvas.CHARH - 3;
         this.inputButtonXY[2] = innerX4 + innerW4 - 2 * MainCanvas.CHARW;
         this.inputButtonXY[3] = innerY4 + innerH4 - MainCanvas.CHARH - 3;
         g.setColor(Cons.COLOR_PANEL_BG);
         g.fillRect(innerX4, innerY4, innerW4, innerH4);
      }
   }

   private void keyInGameRunState() {
      int i;
      switch(this.type) {
      case 1:
      case 13:
         i = 0;

         for(int kk = MainCanvas.NUM_KEYS.length; i < kk; ++i) {
            if (MainCanvas.isInputDown(MainCanvas.NUM_KEYS[i])) {
               this.pushKey(MainCanvas.NUM_KEYS[i]);
            }
         }

         if (MainCanvas.isInputDown(131072)) {
            if (this.leftCmd == -1610612640) {
               PassPort.selMonth = this.getNumber();
               if (PassPort.checkMonth()) {
                  MainCanvas.ni.send(2425012);
               }

               return;
            }

            if (this.leftCmd == -1610612639) {
               PassPort.selMonth = this.getNumber();
               if (PassPort.checkMonth()) {
                  MainCanvas.ni.send(2425013);
               }

               return;
            }

            if (this.leftCmd == -1610612617) {
               if ((byte)this.getNumber() == 0) {
                  createLocalTopForm((byte)0, (String)"Hãy nhập số lượng", "Xác nhận", "", -1, -2);
               } else {
                  PCGemJoinOrRemove.m_bImportNum = (byte)this.getNumber();
                  removeCurTopForm();
                  PCGemJoinOrRemove.Calculate();
               }

               return;
            }
         }

         if (!MainCanvas.isInputDown(1048576) && !MainCanvas.isInputDownOrHold(16384)) {
            if (this.isResponseLeftCmd()) {
               buyNum = this.getNumber();
               switch(MainCanvas.curForm.clientCommand) {
               case 1179649:
               case 1179650:
               case 1179652:
               case 1179654:
               case 1179655:
               case 1179657:
               case 1638423:
                  UIGrid.storageMoney = buyNum;
                  break;
               case 1376263:
                  if (!this.sb.toString().equals("0")) {
                     UIGrid.tradeSignNum[tradeIndex] = (byte)buyNum;
                     UIGrid.tradeSign[tradeIndex] = gridIndex;
                  }
                  break;
               case 1703946:
                  if (buyNum <= 0) {
                     UIGrid.accessories[tradeIndex][0] = -1;
                     UIGrid.accessories[tradeIndex][1] = -1;
                     UIGrid.accessories[tradeIndex][2] = -1;
                     UIGrid.accessories[tradeIndex][3] = -1;
                     if (PCMail.m_bAccessoriesAmount > 0) {
                        --PCMail.m_bAccessoriesAmount;
                     }

                     removeCurTopForm();
                     return;
                  }

                  UIGrid.accessories[tradeIndex][3] = (byte)buyNum;
                  break;
               case 1900546:
                  PCAuction.payPrice = buyNum;
                  break;
               case 1900547:
                  if (buyNum > 0) {
                     PCAuction.ChooseGoods((byte)buyNum);
                  } else {
                     removeCurTopForm();
                  }
               }

               if (this.leftCmd >= 0) {
                  removeCurTopForm();
                  if (this.leftCmd != 458756 && this.leftCmd != 1179656) {
                     if (this.leftCmd != 1179651 && this.leftCmd != 1179653) {
                        if ((this.leftCmd == 2555963 || this.leftCmd == 2555943 || this.leftCmd == 2555970 || this.leftCmd == 2555954 || this.leftCmd == 2555945 || this.leftCmd == 2555971 || this.leftCmd == 2555940 || this.leftCmd == 2555941 || this.leftCmd == 2555909 || this.leftCmd == 2555958 || this.leftCmd == 2555975) && (byte)this.getNumber() == 0) {
                           createLocalTopForm((byte)0, (String)"Hãy nhập số lượng", "Xác nhận", "", -1, -2);
                           if ((this.leftCmd == 2555943 || this.leftCmd == 2555945 || this.leftCmd == 2555941) && MainCanvas.backForms.size() > 0) {
                              MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
                           }

                           return;
                        }
                     } else {
                        PCStorage.keepNum = (byte)this.getNumber();
                        if (PCStorage.keepNum == 0) {
                           return;
                        }
                     }
                  } else {
                     PCPackage.moveNum = (byte)this.getNumber();
                  }

                  MainCanvas.ni.send(this.leftCmd);
               } else if (this.leftCmd == -1) {
                  removeCurTopForm();
               }
            } else if (MainCanvas.isInputDown(262144)) {
               if (this.rightCmd >= 0) {
                  removeCurTopForm();
                  MainCanvas.ni.send(this.rightCmd);
               } else if (this.rightCmd == -1) {
                  removeCurTopForm();
                  switch(MainCanvas.curForm.clientCommand) {
                  case 1703946:
                     for(i = 0; i < UIGrid.accessories.length; ++i) {
                        if (UIGrid.accessories[i][0] == gridIndex) {
                           UIGrid.accessories[i][0] = -1;
                           UIGrid.accessories[i][1] = -1;
                           UIGrid.accessories[i][2] = -1;
                           UIGrid.accessories[i][3] = -1;
                           break;
                        }
                     }

                     if (PCMail.m_bAccessoriesAmount > 0) {
                        --PCMail.m_bAccessoriesAmount;
                     }
                  }
               } else if (this.rightCmd == -16) {
                  removeCurTopForm();
                  if (MainCanvas.backForms.size() > 0) {
                     MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
                  }
               }
            }
         } else {
            this.removeChar();
         }
         break;
      case 4:
         this.keyInPlayerDead();
         break;
      case 6:
         this.keyInKeyOption();
         break;
      case 11:
         this.keyInPlayerPK();
         break;
      case 14:
         byte var10000;
         if (this.isResponseLeftCmd()) {
            if (this.leftCmd == 624776) {
               MainCanvas.ni.send(624776);
               removeCurTopForm();
               var10000 = Player.getInstance().nState;
               Player.getInstance();
               if (var10000 == 25) {
                  createLocalTopForm((byte)4, (String)"", "", "", -2, -2);
               }
            }
         } else if (MainCanvas.isInputDown(262144) && this.rightCmd == -1) {
            removeCurTopForm();
            var10000 = Player.getInstance().nState;
            Player.getInstance();
            if (var10000 == 25) {
               createLocalTopForm((byte)4, (String)"", "", "", -2, -2);
            }
         }
         break;
      case 15:
         this.keyInCompare();
         break;
      case 16:
         this.keyInTradeCancel();
         break;
      case 17:
         if (this.isResponseLeftCmd() && this.leftCmd == -1) {
            MainCanvas.m_bCompelDroptMeshwork = 0;
            MainCanvas.mc.Abnormity();
         }
         break;
      case 35:
         this.keyInTalent();
         break;
      default:
         if (this.isResponseLeftCmd()) {
            if (this.leftCmd >= 0) {
               curTaskID = this.taskID;
               removeCurTopForm();
               if (this.leftCmd == 1638447) {
                  UIForm.backUIFormAction();
                  return;
               }

               if (this.leftCmd == 3342345) {
                  if (MainCanvas.backForms.size() > 0) {
                     MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
                  }

                  return;
               }

               MainCanvas.ni.send(this.leftCmd);
               this.specialAction(this.leftCmd);
            } else if (this.leftCmd == -1) {
               if (MainCanvas.curForm != null && (MainCanvas.curForm.clientCommand == 131078 || MainCanvas.curForm.clientCommand == 131079)) {
                  MainCanvas.ni.isSendingCommands = false;
               }

               removeCurTopForm();
            } else if (this.leftCmd == -250) {
               removeCurTopForm();
               createLocalTopForm((byte)0, (String)"Thư gửi sẽ có đính kèm, xác nhận xóa?", "Có", "Không", 1703944, -1);
            } else if (this.leftCmd == -260) {
               removeCurTopForm();
               PCIncrement.noteCharge();
            } else if (this.leftCmd == -3) {
               removeCurTopForm();
               UIGrid.EliminateMail();
               PCMail.EliminateMail();
               MainCanvas.ni.send(1703955);
            } else if (this.leftCmd != -4 && this.leftCmd != -5) {
               if (this.leftCmd == -6) {
                  removeCurTopForm();
                  PCUnsealGemCarve.AppendPicture(4, false, 3, true);
                  MainCanvas.ni.send(2031622);
               } else if (this.leftCmd == -7) {
                  removeCurTopForm();
                  if (MainCanvas.m_sBingError) {
                     MainCanvas.m_sBingError = false;
                  } else {
                     PCUnsealGemCarve.AppendPicture(6, false, 1, true);
                  }
               } else if (this.leftCmd == -8) {
                  removeCurTopForm();
                  if (MainCanvas.m_sBingError) {
                     MainCanvas.m_sBingError = false;
                  } else {
                     PCUnsealGemCarve.AppendPicture(8, false, 2, true);
                  }
               } else if (this.leftCmd == -10) {
                  removeCurTopForm();
                  PCUnsealGemCarve.m_bPrimaryGemIndex = (byte)UIGrid.AuctionBag[0];
                  MainCanvas.ni.send(2031637);
               } else if (this.leftCmd == -11) {
                  removeCurTopForm();
                  PCGemJoinOrRemove.CleanupData();
               } else if (this.leftCmd == -12) {
                  removeCurTopForm();
                  PCGemJoinOrRemove.m_bGemAmount_1 -= (Byte)PCGemJoinOrRemove.itemsnum.elementAt(PCGemJoinOrRemove.itemsnum.size() - 1);
                  if (PCGemJoinOrRemove.items.size() > 0) {
                     PCGemJoinOrRemove.items.removeElementAt(PCGemJoinOrRemove.items.size() - 1);
                     PCGemJoinOrRemove.itemsnum.removeElementAt(PCGemJoinOrRemove.itemsnum.size() - 1);
                     PCGemJoinOrRemove.m_bGemIndex = (Byte)PCGemJoinOrRemove.items.elementAt(PCGemJoinOrRemove.items.size() - 1);
                  }
               } else {
                  UIPicture pic;
                  UIGrid pp;
                  if (this.leftCmd == -13) {
                     removeCurTopForm();
                     PCGem.m_bEquipIndex = (byte)UIGrid.AuctionBag[0];
                     pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                     pp = (UIGrid)MainCanvas.curForm.getComponents().elementAt(7);
                     pic.setFocus(true, MainCanvas.curForm);
                     pp.setFocus(false, MainCanvas.curForm);
                     MainCanvas.ni.send(1966098);
                  } else if (this.leftCmd == -14) {
                     removeCurTopForm();
                     PCGem.m_bGemIndex = (byte)UIGrid.AuctionBag[0];
                     pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(6);
                     pp = (UIGrid)MainCanvas.curForm.getComponents().elementAt(7);
                     pic.setShowNum(true, (byte)1);
                     pic.quality = (byte)UIGrid.AuctionBag[2];
                     short picID = UIGrid.AuctionBag[1];
                     pic.isWpPic = true;
                     pic.wpIndex = picID;
                     pic.setImg(MainCanvas.stuffMImg);
                     pic.setFocus(false, MainCanvas.curForm);
                     pp.setFocus(true, MainCanvas.curForm);
                  } else if (this.leftCmd == -15) {
                     removeCurTopForm();
                     PCGem.m_bSucceed = 1;
                  } else if (this.leftCmd == -16) {
                     removeCurTopForm();
                     PCGem.m_bEquipIndex = -1;
                     pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                     pic.setShowNum(false);
                     pic.isWpPic = false;
                     pic.setImg((MImage)null);
                  } else if (this.leftCmd == -17) {
                     removeCurTopForm();
                     pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                     UIPicture pic1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(6);
                     UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(7);
                     pic.setFocus(false, MainCanvas.curForm);
                     pic1.setFocus(true, MainCanvas.curForm);
                     grid.setFocus(false, MainCanvas.curForm);
                     if (MainCanvas.backForms.size() > 0) {
                        MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
                     }
                  } else if (this.leftCmd == -18) {
                     removeCurTopForm();
                     UILabel lable = (UILabel)MainCanvas.curForm.getComponents().elementAt(32);
                     lable.setFocus(true, MainCanvas.curForm);
                  } else if (this.leftCmd == -19) {
                     removeAllTopForm();
                     if (MainCanvas.rightMenu != null) {
                        MainCanvas.rightMenu.savePositionIDStack.removeAllElements();
                        MainCanvas.rightMenu = null;
                     }

                     MainCanvas.isExitChoosePlayer = true;
                     MainCanvas.ni.send(65542);
                     MainCanvas.setState((byte)2);
                     SIChat.addChatLocal("");
                     MainCanvas.mc.exitGame((byte)0);
                     Player.getInstance().isSendMove = false;
                     if (SITeam.m_bCopyDifficulty != 0) {
                        SITeam.m_bCopyDifficulty = 0;
                     }

                     if (Map.m_bBattlefield) {
                        Map.m_bBattlefield = false;
                     }

                     if (GOManager.m_bBattlefieldMenuId != 3) {
                        GOManager.m_bBattlefieldMenuId = 3;
                     }

                     Map.flagID = new Vector();
                  } else if (this.leftCmd == -20) {
                     removeAllTopForm();
                     if (Player.getInstance() != null) {
                        Player.getInstance().setAttacked(false);
                     }

                     if (MainCanvas.rightMenu != null) {
                        MainCanvas.rightMenu.savePositionIDStack.removeAllElements();
                        MainCanvas.rightMenu = null;
                     }

                     MainCanvas.closeConnection();
                     Player.getInstance().isSendMove = false;
                     SIChat.addChatLocal("");
                     if (SITeam.m_bCopyDifficulty != 0) {
                        SITeam.m_bCopyDifficulty = 0;
                     }

                     if (Map.m_bBattlefield) {
                        Map.m_bBattlefield = false;
                     }

                     if (GOManager.m_bBattlefieldMenuId != 3) {
                        GOManager.m_bBattlefieldMenuId = 3;
                     }

                     Map.flagID = new Vector();
                  } else if (this.leftCmd == -21) {
                     removeCurTopForm();
                     MainCanvas.quitUI();
                  } else if (this.leftCmd == -22) {
                     removeCurTopForm();
                     PCGem.m_bEnchaseSucceed = 0;
                  } else if (this.leftCmd == -23) {
                     removeCurTopForm();
                     PCIncrement.m_bNote = 1;
                     PCIncrement.initSend(PCIncrement.m_bNote);
                     PCIncrement.init();
                     Thread send = new Thread() {
                        public void run() {
                           PCIncrement.sends(PCIncrement.sum);
                        }
                     };
                     send.start();
                     PCIncrement.chargeState = 2;
                     createLocalTopForm((byte)0, (String)"Đang nạp, xin chờ...", "", "", -2, -2);
                  } else if (this.leftCmd == -24) {
                     UITextField userNameText_1 = (UITextField)MainCanvas.curForm.getComponents().elementAt(9);
                     UITextField userPswText_1 = (UITextField)MainCanvas.curForm.getComponents().elementAt(10);
                     userNameText_1.setSb(new StringBuffer(PassPort.suggestion));
                     userPswText_1.setSb(new StringBuffer(PassPort.suggestion));
                     removeCurTopForm();
                  } else if (this.leftCmd == -25) {
                     removeCurTopForm();
                     MainCanvas.quitUI();
                     if (MainCanvas.rightMenu != null) {
                        MainCanvas.rightMenu.savePositionIDStack.removeAllElements();
                        MainCanvas.rightMenu = null;
                     }

                     Util.saveStrRecord(MainCanvas.name_password, "name_password");
                     MainCanvas.setGameState((byte)0);
                     MainCanvas.m_bTourist = 2;
                     MainCanvas.ni.send(65548);
                  } else if (this.leftCmd == -26) {
                     removeCurTopForm();
                     UIForm.backUIFormAction();
                  } else if (this.leftCmd == -27) {
                     TouristFormal();
                  } else if (this.leftCmd == -28) {
                     removeCurTopForm();
                     String str = "Hãy chờ...";
                     MainCanvas.TopForm((byte)0, str, "", "", -120, -120);
                     pp = null;
                     PassPort pp;
                     if (!MainCanvas.isChinaMobileVer) {
                        pp = new PassPort(PassPort.KONG_PASSPORT_IP, PassPort.getURL((byte)0), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
                     } else if (PassPort.haveUserIDAndKey()) {
                        pp = new PassPort("221.179.216.38", PassPort.getURL((byte)0), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
                     } else {
                        PassPort.isFirstConnect = true;
                        pp = new PassPort(PassPort.CM_PASSPORT_IP, PassPort.CM_PASSPORT_GET_URL, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)1);
                     }

                     pp.authenticate((byte)0);
                  } else if (this.leftCmd == -29) {
                     removeCurTopForm();
                     MainCanvas.ni.send(2555955);
                  }
               }
            } else {
               removeCurTopForm();
               PCUnsealGemCarve.EliminateBata();
            }
         } else if (MainCanvas.isInputDown(262144)) {
            if (this.rightCmd >= 0) {
               curTaskID = this.taskID;
               removeCurTopForm();
               MainCanvas.ni.send(this.rightCmd);
            } else if (this.rightCmd == -1) {
               removeCurTopForm();
            } else if (this.rightCmd != -4 && this.rightCmd != -5) {
               if (this.rightCmd == -9) {
                  removeCurTopForm();

                  for(i = 0; i < UIGrid.AuctionBag.length; ++i) {
                     UIGrid.AuctionBag[i] = -1;
                  }
               } else if (this.rightCmd == -11) {
                  removeCurTopForm();
                  PCGemJoinOrRemove.CleanupData();
               } else if (this.rightCmd == -12) {
                  removeAllTopForm();
                  if (Player.getInstance() != null) {
                     Player.getInstance().setAttacked(false);
                  }

                  if (MainCanvas.rightMenu != null) {
                     MainCanvas.rightMenu.savePositionIDStack.removeAllElements();
                     MainCanvas.rightMenu = null;
                  }

                  MainCanvas.closeConnection();
                  Player.getInstance().isSendMove = false;
                  SIChat.addChatLocal("");
                  MainCanvas.m_bTourist = 0;
               } else if (this.rightCmd == -15) {
                  removeCurTopForm();
                  PCGem.m_bSucceed = 1;
               } else if (this.rightCmd == -18) {
                  removeCurTopForm();
                  buyNum = 0;
               }
            } else {
               removeCurTopForm();
               PCUnsealGemCarve.EliminateBata();
            }
         } else if (MainCanvas.isInputDown(1)) {
            switch(this.type) {
            case 10:
               UIMenu.formSaveForm();
               MainCanvas.ni.send(720913);
               removeCurTopForm();
               break;
            case 31:
               UIMenu.formSaveForm();
               MainCanvas.ni.send(720915);
               removeCurTopForm();
            }
         }
      }

   }

   private void specialAction(int cmd) {
      switch(cmd) {
      case 65547:
         TouristFormal();
         break;
      case 65549:
         removeCurTopForm();
         MainCanvas.ni.send(131071);
         MainCanvas.setState((byte)2);
         SIChat.addChatLocal("");
         MainCanvas.mc.exitGame((byte)0);
         Player.getInstance().isSendMove = false;
         if (MainCanvas.m_bTourist != 0) {
            MainCanvas.m_bTourist = 0;
         }

         MainCanvas.ni.send(65551);
         if (MainCanvas.ni.isSendingCommands) {
            MainCanvas.ni.isSendingCommands = false;
         }

         MainCanvas.isExitChoosePlayer = true;
         break;
      case 1966085:
         Object obj = MainCanvas.backForms.elementAt(0);
         if (obj instanceof UIForm) {
            UIForm form = (UIForm)obj;
            MainCanvas.backForms.setElementAt(new Integer(form.clientCommand), 0);
         }
      }

   }

   private void keyInOtherState() {
      int i;
      String loginPP;
      PassPort loginPP;
      PassPort pp;
      String str;
      switch(this.type) {
      case 1:
         i = 0;

         for(int kk = MainCanvas.NUM_KEYS.length; i < kk; ++i) {
            if (MainCanvas.isInputDown(MainCanvas.NUM_KEYS[i])) {
               this.pushKey(MainCanvas.NUM_KEYS[i]);
            }
         }

         if (MainCanvas.isInputDown(1048576) || MainCanvas.isInputDownOrHold(16384)) {
            this.removeChar();
         }

         if (MainCanvas.isInputDown(131072)) {
            if (this.leftCmd == -1610612640) {
               PassPort.selMonth = this.getNumber();
               if (PassPort.checkMonth()) {
                  if (PassPort.haveUserIDAndKey()) {
                     loginPP = "/judgeserver/QueryChargeUpRecord?usr=&userId=" + MainCanvas.userID + "&game=fengshen&subgame=fengshen&from=kong&sdate=&edate=&sseq=0&rcount=" + 100 + "&qtype=7&qtime=" + PassPort.timeType + "&qmonth=" + PassPort.selMonth;
                     MainCanvas.TopForm((byte)0, "Xin chờ...", "", "", -120, -120);
                     pp = new PassPort(PassPort.CM_CHARGE_IP, loginPP, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)3);
                     pp.authenticate((byte)8);
                  } else {
                     MainCanvas.TopForm((byte)0, "Xin chờ...", "", "", -120, -120);
                     PassPort.isFirstConnect = true;
                     loginPP = new PassPort(PassPort.CM_PASSPORT_IP, PassPort.CM_PASSPORT_GET_URL, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)1);
                     loginPP.authenticate((byte)8);
                  }
               }
            } else if (this.leftCmd == -1610612639) {
               PassPort.selMonth = this.getNumber();
               if (PassPort.checkMonth()) {
                  if (PassPort.haveUserIDAndKey()) {
                     loginPP = "/judgeserver/QueryChargeUpRecord?usr=&userId=" + MainCanvas.userID + "&game=fengshen&subgame=fengshen&from=kong&sdate=&edate=&sseq=0&rcount=" + 100 + "&qtype=" + PassPort.chooseType + "&qtime=" + PassPort.timeType + "&qmonth=" + PassPort.selMonth;
                     MainCanvas.TopForm((byte)0, "Xin chờ...", "", "", -120, -120);
                     pp = new PassPort(PassPort.CM_CHARGE_IP, loginPP, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)3);
                     pp.authenticate((byte)9);
                  } else {
                     MainCanvas.TopForm((byte)0, "Xin chờ...", "", "", -120, -120);
                     PassPort.isFirstConnect = true;
                     loginPP = new PassPort(PassPort.CM_PASSPORT_IP, PassPort.CM_PASSPORT_GET_URL, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)1);
                     loginPP.authenticate((byte)9);
                  }
               }
            } else if (this.leftCmd == -1610612616) {
               PassPort.capchaAnswer = this.getNumber();
               if (PassPort.menuSelected == 23) {
                  loginPP = new PassPort(PassPort.CM_PASSPORT_IP, PassPort.CM_PASSPORT_GET_URL, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
                  loginPP.authenticate((byte)0);
               } else if (PassPort.menuSelected == 29) {
                  PassPort.validateCardRechard(PCCharge.cardNum, PCCharge.cardPsw, PCCharge.chargeStr);
                  loginPP = new PassPort(PassPort.KONG_PASSPORT_IP, "", (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
                  loginPP.authenticate((byte)27);
               }

               removeCurTopForm();
            }
         }
         break;
      case 17:
         if (this.isResponseLeftCmd() && this.leftCmd == -1) {
            MainCanvas.m_bCompelDroptMeshwork = 0;
            MainCanvas.mc.Abnormity();
         }
         break;
      default:
         if (MainCanvas.isInputDown(131072)) {
            if (this.leftCmd == -1610612615) {
               PassPort.waitActive = true;
               loginPP = PassPort.ptmCode + " DK " + (PassPort.loginType == 1 ? "pt" : "") + MainCanvas.name_password[0] + " " + MainCanvas.name_password[1];
               str = "sms://" + PassPort.ptmActiveCode;
               Util.sendSMS(loginPP, str);
               removeCurTopForm();
            } else if (this.leftCmd == -1610612608) {
               UIRightMenu.goUrl(PassPort.updateVerUrl);
               removeCurTopForm();
            }
         }
      }

      if (this.isResponseLeftCmd()) {
         if (this.leftCmd >= 0) {
            removeCurTopForm();
            MainCanvas.ni.send(this.leftCmd);
         } else if (this.leftCmd == -1) {
            removeCurTopForm();
            if (MainCanvas.getState() != 21 && MainCanvas.getState() != 22 && MainCanvas.getState() != 23 && MainCanvas.getState() != 17) {
               MainCanvas.setState((byte)4);
            }
         } else if (this.leftCmd != -2) {
            if (this.leftCmd != -3 && this.leftCmd != -5) {
               if (this.leftCmd == -4) {
                  removeCurTopForm();
                  if (MainCanvas.getState() == 14) {
                     Util.saveStrRecord(MainCanvas.name_password, "name_password");
                     MainCanvas.password_validate = null;
                     MainCanvas.CleanupNamePassword();
                     MainCanvas.ni.send(65537);
                  } else if (MainCanvas.getState() == 18) {
                     MainCanvas.ni.send(131071);
                     loginPP = "Xin chờ...";
                     MainCanvas.TopForm((byte)0, loginPP, "", "", -120, -120);
                     MainCanvas.mc.m_bPlace[MainCanvas.m_bChooseRen] = 1;
                     MainCanvas.Liquidate();
                     MainCanvas.Cleanup_part();
                     MainCanvas.focus = 0;
                     MainCanvas.m_bFocusEnactment = 1;
                  } else if (MainCanvas.getState() == 15) {
                     MainCanvas.CleanupNamePassword();
                     MainCanvas.setState((byte)11);
                  } else if (MainCanvas.getState() == 19) {
                     MainCanvas.Liquidate();
                     MainCanvas.Cleanup_part();
                     MainCanvas.m_bFocusEnactment = 2;
                     MainCanvas.ni.send(131071);
                     MainCanvas.setState((byte)17);
                  } else if (MainCanvas.getState() == 17) {
                     MainCanvas.mc.changSave(UIForm.m_sAmendNickname[0], UIForm.m_sAmendNickname[1]);
                     loginPP = "Xin chờ...";
                     MainCanvas.TopForm((byte)0, loginPP, "", "", -120, -120);
                     MainCanvas.ni.send(131071);
                     MainCanvas.setState((byte)17);
                     MainCanvas.m_bChooseCounter = 0;
                  }
               } else if (this.leftCmd == -6) {
                  removeCurTopForm();
                  MainCanvas.ni.closeConn();
                  MainCanvas.setState((byte)11);
               } else if (this.leftCmd == -7) {
                  removeCurTopForm();
               } else if (this.leftCmd == -8) {
                  removeCurTopForm();
                  Download.gotoURL(MainCanvas.aMidlet, (byte)0);
               } else if (this.leftCmd == -9) {
                  UITextField userNameText_1 = (UITextField)MainCanvas.curForm.getComponents().elementAt(9);
                  UITextField userPswText_1 = (UITextField)MainCanvas.curForm.getComponents().elementAt(10);
                  userNameText_1.setSb(new StringBuffer(PassPort.suggestion));
                  userPswText_1.setSb(new StringBuffer(PassPort.suggestion));
                  removeCurTopForm();
               } else if (this.leftCmd == -10) {
                  removeCurTopForm();
                  UIForm.netFailOverMount = true;
                  MainCanvas.setState((byte)8);
               } else if (this.leftCmd == -11) {
                  if (MainCanvas.loginType == null) {
                     MainCanvas.loginType = new byte[1];
                  }

                  MainCanvas.loginType[0] = 0;
                  Util.saveByteRecord(MainCanvas.loginType, "login_type");
                  loginPP = "Xin chờ...";
                  MainCanvas.TopForm((byte)0, loginPP, "", "", -120, -120);
                  pp = null;
                  if (!MainCanvas.isChinaMobileVer) {
                     pp = new PassPort(PassPort.KONG_PASSPORT_IP, PassPort.getURL((byte)0), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
                  } else if (PassPort.haveUserIDAndKey()) {
                     pp = new PassPort("221.179.216.38", PassPort.getURL((byte)0), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
                  } else {
                     PassPort.isFirstConnect = true;
                     pp = new PassPort(PassPort.CM_PASSPORT_IP, PassPort.CM_PASSPORT_GET_URL, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)1);
                  }

                  pp.authenticate((byte)0);
               } else if (this.leftCmd == -19) {
                  removeCurTopForm();
                  MainCanvas.playPicture[MainCanvas.m_bChoose] = (UIPicture)MainCanvas.curForm.getComponents().elementAt(MainCanvas.m_bChoose + 3);
                  MainCanvas.playPicture[MainCanvas.m_bChoose].otherP = null;

                  for(i = 0; i < MainCanvas.label.length; ++i) {
                     MainCanvas.label[i] = (UILabel)MainCanvas.curForm.getComponents().elementAt(i + 11);
                     MainCanvas.label[i].setText("");
                  }

                  MainCanvas.m_sCharacterName[MainCanvas.m_bChoose] = null;
                  MainCanvas.m_nGrade[MainCanvas.m_bChoose] = 0;
                  MainCanvas.m_sPhyle[MainCanvas.m_bChoose] = 0;
                  MainCanvas.m_bProfession[MainCanvas.m_bChoose] = 0;
                  MainCanvas.m_nScene[MainCanvas.m_bChoose] = null;
                  UITextArea textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(16);
                  textarea.setContent("Xóa nhân vật");
               } else if (this.leftCmd != -30 && this.leftCmd != -31) {
                  if (this.leftCmd == -32) {
                     loginPP = PassPort.ptmCode + " " + (PassPort.loginType == 1 ? "pt" : "") + MainCanvas.name_password[0];
                     str = "sms://" + PCCharge.chargeId;
                     Util.sendSMS(loginPP, str);
                  }
               } else {
                  if (this.leftCmd == -31) {
                     if (PassPort.isQuick) {
                        PassPort.isQuick = false;
                     }

                     MainCanvas.m_bCelerityEnrol = 1;
                  }

                  Util.saveStrRecord(MainCanvas.name_password, "name_password");
                  loginPP = null;
                  if (!MainCanvas.isChinaMobileVer) {
                     loginPP = new PassPort(PassPort.KONG_PASSPORT_IP, PassPort.getURL((byte)1), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
                  } else if (PassPort.haveUserIDAndKey()) {
                     loginPP = new PassPort("221.179.216.38", PassPort.getURL((byte)1), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
                  } else {
                     PassPort.isFirstConnect = true;
                     loginPP = new PassPort(PassPort.CM_PASSPORT_IP, PassPort.CM_PASSPORT_GET_URL, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)1);
                  }

                  if (MainCanvas.isAutoConn) {
                     UIForm.conStartTime = System.currentTimeMillis();
                  }

                  loginPP.authenticate((byte)1);
                  str = "Xin chờ...";
                  MainCanvas.TopForm((byte)0, str, "", "", -120, -120);
               }
            } else {
               removeCurTopForm();
            }
         }
      } else if (MainCanvas.isInputDown(262144)) {
         if (this.rightCmd >= 0) {
            removeCurTopForm();
            MainCanvas.ni.send(this.rightCmd);
         } else if (this.rightCmd == -1) {
            removeCurTopForm();
         } else if (this.rightCmd == -2) {
            MainCanvas.setState((byte)12);
         } else if (this.rightCmd == -3) {
            removeCurTopForm();
         } else if (this.rightCmd == -17) {
            removeCurTopForm();
            if (PassPort.isQuick) {
               PassPort.isQuick = false;
            }
         } else if (this.rightCmd == -1610612606) {
            PassPort.gotoGame();
         } else if (this.rightCmd == -1610612607) {
            MainCanvas.aMidlet.exitMIDlet();
         }
      }

   }

   private void keyInPlayerDead() {
      if (MainCanvas.isInputDown(2048)) {
         MainCanvas.quitUI();
         MainCanvas.mc.leftRightMenu();
         removeAllTopForm();
         GOManager.removeAll();
         MainCanvas.setState((byte)2);
         MainCanvas.ni.send(196621);
      }

   }

   private void keyInPlayerPK() {
      if (this.isResponseLeftCmd()) {
         MainCanvas.quitUI();
         removeCurTopForm();
         MainCanvas.setGameState(MainCanvas.oldGameState);
         MainCanvas.ni.send(196627);
      } else if (MainCanvas.isInputDown(262144)) {
         MainCanvas.quitUI();
         removeCurTopForm();
         MainCanvas.setGameState(MainCanvas.oldGameState);
         MainCanvas.ni.send(196628);
      }

   }

   private void keyInCompare() {
      if (this.isResponseLeftCmd()) {
         removeCurTopForm();
         MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
         UIForm.resetCompareTI();
      }

   }

   private void keyInTradeCancel() {
      if (MainCanvas.isInputDown(262144)) {
         removeCurTopForm();
         isCancelTrade = true;
         if (this.rightCmd >= 0) {
            MainCanvas.ni.send(this.rightCmd);
         }
      }

   }

   private void keyInKeyOption() {
      if (this.isResponseLeftCmd()) {
         ((UIList)MainCanvas.curForm.getComponents().elementAt(2)).keyInTopForm(this.leftCmd);
      } else if (MainCanvas.isInputDown(262144)) {
         ((UIList)MainCanvas.curForm.getComponents().elementAt(2)).keyInTopForm(this.rightCmd);
      }

   }

   private void keyInTalent() {
      if (this.isResponseLeftCmd()) {
         UITitle.isTalent_AddPoint = false;
         removeCurTopForm();
         UIForm.backUIFormAction();
      } else if (MainCanvas.isInputDown(262144)) {
         removeCurTopForm();
      }

   }

   public static void removeCurTopForm() {
      MainCanvas.curTopForm = null;
   }

   public static void topFormBuffAction() {
      if (MainCanvas.topFormBuff.length > 0) {
         byte[] tmpData = MainCanvas.topFormBuff[0];
         boolean canShow = false;
         switch(tmpData[0]) {
         case 0:
            canShow = true;
            break;
         case 1:
            if (MainCanvas.getGameState() == 0 && !MainCanvas.isWaiting) {
               canShow = true;
            }
         }

         if (canShow) {
            MainCanvas.curTopForm = ParseUI.parseTopForm(tmpData);
            MainCanvas.topFormBuff = Util.removeArray((byte[][])MainCanvas.topFormBuff, 0);
         }
      }

   }

   public static void removeAllTopForm() {
      MainCanvas.topFormBuff = new byte[0][];
      MainCanvas.curTopForm = null;
   }

   private void drawInputNum(Graphics g) {
      this.drawBoard(g);
      this.drawStrLabels(g);
      this.drawInputLabel(g);
      this.drawInputLabelButton(g);
   }

   private void drawRollLook(Graphics g) {
      this.drawDefault(g);
      String str = MainCanvas.SUPPORT_POINTER ? "Kiểm tra độ cảm ứng" : "Nhấn phím 0 kiểm tra";
      int len = this.labelStrs.length;
      int y = this.contentY + MainCanvas.CHARH * len;
      g.drawString(str, MainCanvas.screenW >> 1, y, 17);
   }

   private void drawAuctionInputNum(Graphics g) {
      this.drawBoard(g);
      this.drawStrLabels(g);
      this.drawAuctionInputLabel(g);
      this.drawInputLabelButton(g);
   }

   private void drawPlayerDead(Graphics g) {
      this.drawBoard(g);
      this.drawContent(g);
   }

   private void drawInputLabelButton(Graphics g) {
      g.setColor(Cons.COLOR_FONT_1);
      g.drawString(this.leftStr, this.inputButtonXY[0], this.inputButtonXY[1], 20);
      g.drawString(this.rightStr, this.inputButtonXY[2], this.inputButtonXY[3], 20);
   }

   private void drawStrLabels(Graphics g) {
      g.setColor(Cons.COLOR_FONT_1);
      if (this.labelStrs != null) {
         int strY = super.positionY + 5 + 1 + 1 + 1;

         for(int i = 0; i < this.labelStrs.length; ++i) {
            int strW = Util.getStrLen(this.labelStrs[i]);
            int strX = (super.width - strW) / 2;
            g.drawString(this.labelStrs[i], strX, strY + i * (MainCanvas.CHARH + 3), 20);
         }
      }

   }

   private void drawInputLabel(Graphics g) {
      int labelW = 75;
      int labelH = 14 > MainCanvas.CHARH + 1 ? 14 : MainCanvas.CHARH + 1;
      int labelX = super.positionX + (super.width - labelW >> 1);
      int labelY = super.positionY + 5 + 1 + 1 + 1 + this.labelStrs.length * (MainCanvas.CHARH + 3) + 3;
      g.setColor(Cons.COLOR_PANEL_BORDER_2);
      g.fillRect(labelX, labelY, labelW, labelH);
      g.setColor(Cons.COLOR_PANEL_BORDER_3);
      g.drawRect(labelX, labelY, labelW, labelH);
      int strWidth = false;
      g.setColor(Cons.COLOR_TEXT_FONT);
      int len = this.sb.toString().trim().length();
      int start = 0;
      String tempStr = this.sb.toString().trim();

      int strWidth;
      for(strWidth = MainCanvas.curFont.stringWidth(tempStr); strWidth + 4 > super.width && start <= len - 1; strWidth = MainCanvas.curFont.stringWidth(tempStr)) {
         ++start;
         tempStr = this.sb.toString().substring(start).trim();
      }

      g.drawString(tempStr, labelX + 5, labelY, 20);
      if (++this.interval > 5) {
         this.interval = 0;
      }

      if (this.interval < 3) {
         this.cx = labelX + 5 + strWidth + 2;
         g.setColor(Cons.COLOR_TEXT_FONT);
         g.drawLine(this.cx, labelY + 1, this.cx, labelY + 12);
      }

   }

   private void drawAuctionInputLabel(Graphics g) {
      int labelW = 75;
      int labelH = 13;
      int labelX = super.width - labelW >> 1;
      int labelY = super.positionY + 5 + 1 + 1 + 1 + this.labelStrs.length * (MainCanvas.CHARH + 3) + 3;
      g.setColor(Cons.COLOR_PANEL_BORDER_2);
      g.fillRect(labelX, labelY, labelW, labelH);
      g.setColor(Cons.COLOR_PANEL_BORDER_3);
      g.drawRect(labelX, labelY, labelW, labelH);
      int strWidth = false;
      g.setColor(Cons.COLOR_TEXT_FONT);
      int len = this.sb.toString().trim().length();
      int start = 0;
      String tempStr = this.sb.toString().trim();

      int strWidth;
      for(strWidth = MainCanvas.curFont.stringWidth(tempStr); strWidth + 4 > super.width && start <= len - 1; strWidth = MainCanvas.curFont.stringWidth(tempStr)) {
         ++start;
         tempStr = this.sb.toString().substring(start).trim();
      }

      g.drawString(tempStr, labelX + 5, labelY, 20);
      if (++this.interval > 5) {
         this.interval = 0;
      }

      if (this.interval < 3) {
         this.cx = labelX + 5 + strWidth + 2;
         g.setColor(Cons.COLOR_TEXT_FONT);
         g.drawLine(this.cx, labelY + 1, this.cx, labelY + 12);
      }

   }

   public void pushKey(int keyValue) {
      for(int i = 0; i < 10; ++i) {
         if (1 << i == keyValue) {
            if (this.type == 1 || this.type == 13) {
               this.addNumChar(charNum.charAt(i));
            }
            break;
         }
      }

   }

   public void addNumChar(char c) {
      if (this.sb.length() < this.MAXWORDSNUM) {
         if (this.sb.length() == 1 && this.sb.toString().equals("0")) {
            this.sb.deleteCharAt(0);
         }

         this.sb.append(c);
         long temp = Long.parseLong(this.sb.toString());
         if (MAXNUMBER >= 0) {
            try {
               temp = Long.parseLong(this.sb.toString());
               if (temp > (long)MAXNUMBER) {
                  this.setLabel(Long.toString((long)MAXNUMBER));
               } else {
                  this.setLabel(Long.toString(temp));
               }
            } catch (Exception var5) {
            }
         }

         if (temp < (long)MINNUMBER) {
            this.setLabel(String.valueOf(MINNUMBER));
         }
      }

   }

   public void removeChar() {
      if (this.sb.length() > 0) {
         this.sb.deleteCharAt(this.sb.length() - 1);
         if (this.sb.length() == 0 || this.getNumber() < MINNUMBER) {
            this.setLabel(String.valueOf(MINNUMBER));
         }
      }

      this.interval = 0;
      this.keyOld = 0;
      this.keyIndex = 0;
   }

   public int getNumber() {
      try {
         if (this.sb.toString().trim().equals("")) {
            return 0;
         } else {
            int num = Integer.parseInt(this.sb.toString());
            return num;
         }
      } catch (Exception var2) {
         return 0;
      }
   }

   public void setNumber(int num) {
      this.sb = new StringBuffer();
      this.sb.append(num);
   }

   public void setLabel(String s) {
      if (s != null && s.length() != 0) {
         int i = 0;

         for(int kk = s.length(); i < kk; ++i) {
            if (!Character.isDigit(s.charAt(i))) {
               return;
            }
         }

         this.sb.delete(0, this.sb.length());
         this.sb.append(s);
      } else {
         this.clear();
      }

   }

   public void clear() {
      this.sb.delete(0, this.sb.length());
   }

   public byte getType() {
      return this.type;
   }

   public void setType(byte type) {
      this.type = type;
   }

   public String getContentDefault() {
      return this.contentDefault;
   }

   public void setContentDefault(String contentDefault) {
      this.contentDefault = contentDefault;
   }

   public void setDiff_Color(int[] color) {
      this.colors = color;
   }

   public static void createLockTopForm() {
      createLocalTopForm((byte)0, (String)"Chức năng này chưa mở", Cons.C_STR[2], "", -1, -2);
      UIRightMenu.isDraw = true;
   }

   public static void createLocalTopForm(byte type, String str, String button1, String button2, int command1, int command2) {
      MainCanvas.curTopForm = new UITopForm(type, (UIForm)null);
      MainCanvas.curTopForm.setTopFormInfo((byte)5, specialColor, str, button1, button2, command1, command2);
   }

   public static void createLocalTopForm(byte type, String str, String button1, String button2, int command1, int command2, int i1, int i2) {
      createLocalTopForm(type, str, button1, button2, command1, command2);
      tradeIndex = (byte)i1;
      gridIndex = (byte)i2;
   }

   public static void createLocalTopForm(byte type, String[] strs, String button1, String button2, int command1, int command2) {
      MainCanvas.curTopForm = new UITopForm(type, (UIForm)null);
      MainCanvas.curTopForm.setTopFormInfo(strs, button1, button2, command1, command2);
   }

   public static void createBuyTipTopForm() {
      createLocalTopForm((byte)0, (String)"Mạng hiện đang sử dụng không hỗ trợ chức năng này，xin trở lại trang chính nhập CMWAP vào chổ kết nối sau đó thử lại", "Xác nhận", "", -1, -2);
   }

   public String[] getLabelStrs() {
      return this.labelStrs;
   }

   public void setLabelStrs(String[] labelStrs) {
      this.labelStrs = labelStrs;
   }

   public String[] colorChangeLine(String s, int useWidth, Font font) {
      char[] tempChars = s.toCharArray();
      int lg = tempChars.length;
      int[] sign = new int[34];
      int number = 0;
      StringBuffer sb = new StringBuffer();
      Vector tempV = new Vector();

      int i;
      for(i = 0; i < lg; ++i) {
         if (tempChars[i] != '\n') {
            tempV.addElement(new Character(tempChars[i]));
         }

         if (tempChars[i] != '$') {
            sb.append(tempChars[i]);
         }
      }

      i = 0;

      for(int ii = tempV.size(); i < ii; ++i) {
         if (tempV.elementAt(i).toString().charAt(0) == '$') {
            sign[number] = i;
            ++number;
         }
      }

      String[] strs = Util.wrapText(sb.toString(), useWidth, font);
      lg = strs.length;
      this.colorSigns = new byte[lg][];
      int[][] start_end = new int[lg][2];

      int i;
      for(int i = 0; i < lg; ++i) {
         i = strs[i].length();
         this.colorSigns[i] = new byte[i];
         if (i == 0) {
            start_end[i][0] = 0;
            start_end[i][1] = i - 1;
         } else {
            start_end[i][0] = start_end[i - 1][1] + 1;
            start_end[i][1] = start_end[i][0] + i - 1;
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

      return strs;
   }

   public void touchScreenAction() {
      if (MainCanvas.SUPPORT_POINTER && MainCanvas.isInputDown(268435456)) {
         boolean touchLeft = false;
         boolean touchRight = false;
         int strLen = MainCanvas.curFont.stringWidth(this.leftStr);
         int dx = this.type == 13 ? this.inputButtonXY[0] : this.contentX;
         int dy = this.type == 13 ? this.inputButtonXY[1] : super.positionY + this.innerBoardDownY + 1 - MainCanvas.CHARH;
         touchLeft = MainCanvas.pointDownInRect(dx, dy, strLen, MainCanvas.CHARH);
         if (touchLeft && this.leftStr != null) {
            MainCanvas.keySimPressed(131072);
         }

         int strLen1 = MainCanvas.curFont.stringWidth(this.rightStr);
         int dx1 = this.type == 13 ? this.inputButtonXY[2] : this.contentX + this.contentUseableW - 2 - strLen1;
         int dy1 = this.type == 13 ? this.inputButtonXY[3] : super.positionY + this.innerBoardDownY + 1 - MainCanvas.CHARH;
         touchRight = MainCanvas.pointDownInRect(dx1, dy1, strLen1, MainCanvas.CHARH);
         if (touchRight && this.rightStr != null) {
            MainCanvas.keySimPressed(262144);
         }

         switch(this.type) {
         case 1:
         case 13:
            int labelW = 75;
            int labelH = 13;
            int labelX = super.positionX + (super.width - labelW >> 1);
            int labelY = super.positionY + 5 + 1 + 1 + 1 + this.labelStrs.length * (MainCanvas.CHARH + 3) + 3;
            if (MainCanvas.isPointInRect(labelX, labelY, labelW, labelH)) {
               Util.Touch_InputNum();
            }
            break;
         case 4:
            short panelWidth = (short)(super.width - 8);
            int x = super.positionX + 4;
            int y = super.positionY;
            int w = panelWidth - 1;
            int h = super.height - 1;
            if (MainCanvas.isPointInRect(x, y, w, h)) {
               MainCanvas.keySimPressed(2048);
            }
            break;
         case 10:
         case 31:
            String str = MainCanvas.SUPPORT_POINTER ? "Chạm nơi này để tìm" : "Nhấn 0 để tìm";
            int len = this.labelStrs.length;
            int strWid = MainCanvas.curFont.stringWidth(str);
            int xx = (MainCanvas.screenW >> 1) - (strWid >> 1);
            int yy = this.contentY + MainCanvas.CHARH * len;
            int hh = MainCanvas.CHARH;
            if (MainCanvas.isPointInRect(xx, yy, strWid, hh)) {
               MainCanvas.keySimPressed(1);
            }
         }
      }

   }

   public boolean isResponseLeftCmd() {
      boolean canResponse = false;
      if (MainCanvas.isInputDown(131072)) {
         canResponse = true;
      } else if (MainCanvas.isInputDown(65536)) {
         if (this.rightStr != null && !this.rightStr.equals("")) {
            canResponse = false;
         } else {
            canResponse = true;
         }
      }

      return canResponse;
   }

   public static void TouristFormal() {
      removeCurTopForm();
      createLocalTopForm((byte)0, (String)"Xin chờ...", "", "", -2, -2);
      PassPort.isQuick = true;
      MainCanvas.name_password[0] = "tjuser";
      MainCanvas.name_password[1] = "121212";
      PassPort pp = new PassPort(PassPort.KONG_PASSPORT_IP, PassPort.getURL((byte)5), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
      pp.authenticate((byte)0);
   }

   public static void drawDrift(Graphics g) {
      if (driftTime != 0) {
         int w = MainCanvas.CHARW * Util.getMaxStringLen(driftStr) + 30;
         int rectWidth = w > MainCanvas.screenW ? MainCanvas.screenW : w;
         int rectHeight = (MainCanvas.CHARH + 2) * driftStr.length + 30;
         short rectY = (short)(2 + SIManager.SIDy + MainCanvas.CHARH + 2);
         int placeY = rectY + (rectHeight - (MainCanvas.CHARH + 2) * driftStr.length >> 1);
         int totalCount = driftTime * 1000 / 120;
         byte colorType = 0;
         int[][] color = new int[][]{{16765320, 13740650, 8023640, 4866613, 4866613, 8023640, 13740650, 16765320}, {1461506, 2392068, 3455750, 4718344, 4718344, 3455750, 2392068, 1461506}, {5046786, 8389892, 12257286, 16714504, 16714504, 12257286, 8389892, 5046786}, {5064450, 8418820, 12299782, 16772360, 16772360, 12299782, 8418820, 5064450}};
         int positionX = MainCanvas.screenW - rectWidth >> 1;
         int positionY = rectY;
         if (isDrawDrift) {
            ++drawDriftCount;
            if (drawDriftCount == totalCount) {
               driftTime = 0;
               drawDriftCount = 0;
               isDrawDrift = false;
               driftColorIndex = 0;
            } else if (drawDriftCount < 4) {
               ++driftColorIndex;
            } else if (drawDriftCount < 4 || drawDriftCount >= totalCount - 4) {
               ++driftColorIndex;
            }

            g.setColor(Cons.COLOR_MENU_BG);
            g.fillRect(positionX + 1, rectY + 1, rectWidth - 2, rectHeight - 2);
            int jiaoW = MainCanvas.m_cImg.getWidth();
            int jiaoH = MainCanvas.m_cImg.getHeight();
            g.drawImage(MainCanvas.m_cImg, positionX, rectY, 20);
            Util.drawImage(g, MainCanvas.m_cImg, positionX + rectWidth - MainCanvas.m_cImg.getWidth(), rectY, 2);
            Util.drawImage(g, MainCanvas.m_cImg, positionX, rectY + rectHeight - MainCanvas.m_cImg.getHeight(), 1);
            Util.drawImage(g, MainCanvas.m_cImg, positionX + rectWidth - MainCanvas.m_cImg.getWidth(), rectY + rectHeight - MainCanvas.m_cImg.getHeight(), 3);
            int[] kuangColor = new int[]{Cons.COLOR_MENU_OUT_BORDER_1, Cons.COLOR_MENU_OUT_BORDER_2, Cons.COLOR_MENU_OUT_BORDER_3};

            for(int i = 0; i < 3; ++i) {
               g.setColor(kuangColor[i]);
               g.drawLine(positionX + jiaoW, positionY + i * 1, positionX + rectWidth - jiaoW, positionY + i * 1);
               g.drawLine(positionX + jiaoW, positionY + rectHeight - 1 - i * 1, positionX + rectWidth - jiaoW, positionY + rectHeight - 1 - i * 1);
               g.drawLine(positionX + i * 1, positionY + jiaoH, positionX + i * 1, positionY + rectHeight - jiaoH);
               g.drawLine(positionX + rectWidth - 1 - i * 1, positionY + jiaoH, positionX + rectWidth - 1 - i * 1, positionY + rectHeight - jiaoH);
            }

            int[] kuangColor2 = new int[]{Cons.COLOR_MENU_INNER_BORDER_1, Cons.COLOR_MENU_INNER_BORDER_2, Cons.COLOR_MENU_INNER_BORDER_3, Cons.COLOR_MENU_INNER_BORDER_4};

            int i;
            for(i = 0; i < 4; ++i) {
               g.setColor(kuangColor2[i]);
               g.drawLine(positionX + jiaoW, positionY + 3 + 2 + i * 1, positionX + rectWidth - jiaoW, positionY + 3 + 2 + i * 1);
               g.drawLine(positionX + jiaoW, positionY + rectHeight - 1 - 3 - 2 - i * 1, positionX + rectWidth - jiaoW, positionY + rectHeight - 1 - 3 - 2 - i * 1);
               g.drawLine(positionX + 3 + 2 + i * 1, positionY + jiaoH, positionX + 3 + 2 + i * 1, positionY + rectHeight - jiaoH);
               g.drawLine(positionX + rectWidth - 1 - 3 - 2 - i * 1, positionY + jiaoH, positionX + rectWidth - 1 - 3 - 2 - i * 1, positionY + rectHeight - jiaoH);
            }

            for(i = 0; i < driftStr.length; ++i) {
               int offSet = 0;
               char[] tmpChars = driftStr[i].toCharArray();
               int offWidth = MainCanvas.curFont.stringWidth(driftStr[i]) >> 1;
               int colorSignCount = Util.getCharNum(driftStr[i], '$');
               if (colorSignCount > 0 && colorSignCount % 2 == 0) {
                  offWidth -= MainCanvas.curFont.stringWidth("$") * (colorSignCount / 2);
               }

               boolean beginColorSign = false;

               for(int j = 0; j < tmpChars.length; ++j) {
                  if (tmpChars[j] == '$' && !beginColorSign) {
                     beginColorSign = true;
                  } else if (tmpChars[j] == '$' && beginColorSign) {
                     beginColorSign = false;
                  } else {
                     if (beginColorSign) {
                        if (driftColorIndex == 3) {
                           g.setColor(driftSpecialColor);
                        } else {
                           g.setColor(color[colorType][driftColorIndex]);
                        }
                     } else {
                        g.setColor(color[colorType][driftColorIndex]);
                     }

                     g.drawChar(tmpChars[j], MainCanvas.screenW / 2 - offWidth + offSet, placeY + (MainCanvas.CHARH + 2) * i, 20);
                     offSet += MainCanvas.curFont.charWidth(tmpChars[j]);
                  }
               }
            }
         }

      }
   }
}

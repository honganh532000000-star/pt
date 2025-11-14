import javax.microedition.lcdui.Graphics;

public abstract class UIComponent {
   public static boolean isAdapt = true;
   protected byte type;
   protected short width;
   protected short height;
   protected short positionX;
   protected short positionY;
   protected boolean haveInnerComponent;
   protected boolean canFocus;
   private boolean isFocus;
   protected UIForm attachForm;
   byte upIndex;
   byte downIndex;
   byte leftIndex;
   byte rightIndex;
   protected short clientType = -1;
   public static final byte DIRECT_UP = 0;
   public static final byte DIRECT_DOWN = 1;
   public static final byte DIRECT_LEFT = 2;
   public static final byte DIRECT_RIGHT = 3;
   protected boolean isUserData = false;
   protected short userDataType = -1;
   protected boolean inverse = false;
   private UIMenu menu = null;
   public boolean isShowMenu = false;
   private boolean isTopTip = false;
   public boolean isTipState = true;
   private short tipX;
   private short tipY;
   private short tipW;
   private short tipH;
   private String[] tipStrs;
   public static final long TIP_BEFORE_TIME = 2000L;
   public static final long TIP_STAY_TIEM = 4000L;
   public long t1;
   public long t2;
   public boolean beginRecordT2 = true;
   public boolean isInterruptTopTip = false;
   public static final int INTERFACE_OTHER_EQUIP_NAME = 4;

   public UIComponent(short x, short y, short w, short h, UIForm form) {
      this.attachForm = form;
      if (isAdapt) {
         this.positionX = (short)(x * MainCanvas.screenW / 176);
         this.positionY = (short)(y * MainCanvas.screenH / 208);
         this.width = (short)(w * MainCanvas.screenW / 176);
         this.height = (short)(h * MainCanvas.screenH / 208);
      } else {
         this.positionX = x;
         this.positionY = y;
         this.width = w;
         this.height = h;
      }

   }

   public void setAroundComponent(byte direction, byte uicIndex) {
      switch(direction) {
      case 0:
         this.upIndex = uicIndex;
         break;
      case 1:
         this.downIndex = uicIndex;
         break;
      case 2:
         this.leftIndex = uicIndex;
         break;
      case 3:
         this.rightIndex = uicIndex;
      }

   }

   public void setAroundFocusNull() {
      this.setAroundComponent((byte)0, (byte)-1);
      this.setAroundComponent((byte)1, (byte)-1);
      this.setAroundComponent((byte)2, (byte)-1);
      this.setAroundComponent((byte)3, (byte)-1);
   }

   public byte getAroundComponentIndex(byte direction) {
      switch(direction) {
      case 0:
         return this.upIndex;
      case 1:
         return this.downIndex;
      case 2:
         return this.leftIndex;
      case 3:
         return this.rightIndex;
      default:
         return -1;
      }
   }

   public UIComponent getAroundComponent(UIForm form, byte direction) {
      byte index = this.getAroundComponentIndex(direction);
      return index >= 1 && index - 1 < form.getComponents().size() ? (UIComponent)form.getComponents().elementAt(index - 1) : null;
   }

   public abstract void draw(Graphics var1);

   public void drawTopTip(Graphics g) {
      g.setColor(5450262);
      g.drawRoundRect(this.tipX, this.tipY, this.tipW, this.tipH, 8, 8);
      g.setColor(16117971);
      g.fillRoundRect(this.tipX + 1, this.tipY + 1, this.tipW - 1, this.tipH - 1, 8, 8);
      if (this.tipStrs != null) {
         g.setColor(Cons.COLOR_FONT_1);

         for(int i = 0; i < this.tipStrs.length; ++i) {
            g.drawString(this.tipStrs[i], this.tipX + 4, this.tipY + 4 + i * MainCanvas.CHARH, 20);
         }
      }

   }

   public void setTopTipProperty(short tipX, short tipY, short tipW, short tipH) {
      this.tipX = tipX;
      this.tipY = tipY;
      this.tipW = tipW;
      this.tipH = tipH;
   }

   public void setTipStrs(String[] tipStrs) {
      this.tipStrs = tipStrs;
   }

   protected void keyInAction() {
   }

   protected void keyInMenu() {
   }

   protected void addMenu() {
   }

   public static void especialDealCompare() {
      switch(MainCanvas.curForm.clientCommand) {
      case 1245185:
         UILabel nameLabel = null;
         UIGrid grid = null;
         int i = 0;

         for(int ii = MainCanvas.curForm.getComponents().size(); i < ii; ++i) {
            if ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i) instanceof UIPicture && ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i)).getType() == 3 && ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i)).isFocus) {
               UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i);
               pic.setEquipPicType((byte)1);
               UIPicture.equipTypeForServer = pic.getEquipType();
               if ((UIComponent)MainCanvas.curForm.getComponents().elementAt(22) instanceof UILabel) {
                  nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(22);
                  if (nameLabel.getType() == 1) {
                     nameLabel.setText(pic.getEquipName());
                     nameLabel.setTextColor(UIGrid.getStuffWordColor(pic.getEquipColorLevel()));
                  }
               }
            }

            if ((UIComponent)MainCanvas.curForm.getComponents().elementAt(23) instanceof UIGrid && ((UIComponent)MainCanvas.curForm.getComponents().elementAt(23)).getClientType() == 6 && ((UIComponent)MainCanvas.curForm.getComponents().elementAt(23)).isFocus()) {
               grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(23);
            }

            if (grid != null && (UIComponent)MainCanvas.curForm.getComponents().elementAt(22) instanceof UILabel) {
               nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(22);
               if (nameLabel.getType() == 1) {
                  nameLabel.setText(grid.stuffName[0]);
                  if (grid.stuffId[grid.getGridIndex()] != -1) {
                     nameLabel.setText(grid.stuffName[grid.getGridIndex()]);
                     nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
                  } else {
                     nameLabel.setText("");
                  }
               }
            }
         }
      default:
      }
   }

   public static void especialDealOtherEquip() {
      switch(MainCanvas.curForm.clientCommand) {
      case 393230:
         UILabel nameLabel = null;
         int i = 0;

         for(int ii = MainCanvas.curForm.getComponents().size(); i < ii; ++i) {
            if ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i) instanceof UIPicture && ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i)).getType() == 3 && ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i)).isFocus) {
               UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i);
               pic.setEquipPicType((byte)2);
               UIPicture.equipTypeForServer = pic.getEquipType();
               nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
               nameLabel.setText(pic.getEquipName());
               if (pic.getEquipColorLevel() == -1) {
                  nameLabel.setTextColor(Cons.COLOR_FONT_1);
               } else {
                  nameLabel.setTextColor(UIGrid.getStuffWordColor(pic.getEquipColorLevel()));
               }
            }
         }
      default:
      }
   }

   public static void especialDealEquip() {
      switch(MainCanvas.curForm.clientCommand) {
      case 458755:
      case 458817:
      case 917505:
      case 917506:
      case 917509:
      case 917510:
      case 917511:
      case 939349:
      case 1966086:
         UILabel nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(22);
         UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
         if (MainCanvas.curForm.focusWidget instanceof UIPicture && MainCanvas.curForm.focusWidget.getType() == 3) {
            if (MainCanvas.curForm.clientCommand == 1966086) {
               menuBar.setMenuText("Thao tác", 0);
            }

            UIPicture pic = (UIPicture)MainCanvas.curForm.focusWidget;
            pic.setEquipPicType((byte)0);
            UIPicture.equipTypeForServer = pic.getEquipType();
            nameLabel.setText(pic.getEquipName());
            if (pic.getEquipColorLevel() == -1) {
               nameLabel.setTextColor(Cons.COLOR_FONT_1);
            } else {
               nameLabel.setTextColor(UIGrid.getStuffWordColor(pic.getEquipColorLevel()));
            }
         } else if (MainCanvas.curForm.focusWidget instanceof UIGrid) {
            UIGrid grid = (UIGrid)MainCanvas.curForm.focusWidget;
            UIPicture.equipTypeForServer = -1;
            nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(22);
            if (grid.stuffId[grid.getGridIndex()] != -1) {
               nameLabel.setText(grid.stuffName[grid.getGridIndex()]);
               nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
            } else {
               nameLabel.setText("");
               if (MainCanvas.curForm.clientCommand == 1966086) {
                  menuBar.setMenuText("", 0);
               }
            }
         }
      default:
      }
   }

   public static void especialDealEquipFix() {
      switch(MainCanvas.curForm.clientCommand) {
      case 917520:
      case 917521:
      case 917522:
      case 1638431:
      case 1638432:
      case 1638433:
         UILabel nameLabel = null;
         UILabel durableLabel = null;
         UILabel fixAllLabel = null;
         UILabel fixSingalLabel = null;
         int allEquipFixPay = 0;
         int i = 0;

         int ii;
         UIPicture pic;
         for(ii = MainCanvas.curForm.getComponents().size(); i < ii; ++i) {
            if ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i) instanceof UIPicture && ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i)).getType() == 3) {
               pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i);
               allEquipFixPay += pic.getEquipFixPay();
            }
         }

         i = 0;

         for(ii = MainCanvas.curForm.getComponents().size(); i < ii; ++i) {
            if ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i) instanceof UIPicture && ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i)).getType() == 3 && ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i)).isFocus) {
               pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i);
               pic.setEquipPicType((byte)0);
               UIPicture.equipTypeForServer = pic.getEquipType();
               nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(22);
               if (nameLabel.getType() == 1) {
                  nameLabel.setText(pic.getEquipName());
                  if (pic.getEquipColorLevel() == -1) {
                     nameLabel.setTextColor(Cons.COLOR_FONT_1);
                  } else {
                     nameLabel.setTextColor(UIGrid.getStuffWordColor(pic.getEquipColorLevel()));
                  }
               }

               durableLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(25);
               if (pic.equipIndex != -1) {
                  durableLabel.setText(pic.getEquipCurDuable() + "/" + pic.getEquipMaxDuable());
               } else {
                  durableLabel.setText("");
               }

               fixAllLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(27);
               fixAllLabel.setText(String.valueOf(allEquipFixPay));
               fixSingalLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(29);
               if (pic.equipIndex != -1) {
                  fixSingalLabel.setText(String.valueOf(pic.getEquipFixPay()));
               } else {
                  fixSingalLabel.setText("0");
               }
            }
         }
      default:
      }
   }

   public static void especialDeaTrade2() {
      switch(MainCanvas.curForm.clientCommand) {
      case 1376260:
      case 1376261:
      case 1376265:
      case 1376268:
         UILabel labelMe = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
         labelMe.setStrCenterXAnchor(true);
         labelMe.setPositionX((short)(MainCanvas.screenW >> 1));
         UILabel labelOther = (UILabel)MainCanvas.curForm.getComponents().elementAt(16);
         labelOther.setStrCenterXAnchor(true);
         labelOther.setPositionX((short)(MainCanvas.screenW >> 1));
      case 1376262:
      case 1376263:
      case 1376264:
      case 1376266:
      case 1376267:
      default:
      }
   }

   public static void especialDealTrade() {
      switch(MainCanvas.curForm.clientCommand) {
      case 1376260:
      case 1376261:
      case 1376265:
      case 1376268:
         UILabel labelMyGoods = (UILabel)MainCanvas.curForm.getComponents().elementAt(5);
         UILabel labelOtherGoods = (UILabel)MainCanvas.curForm.getComponents().elementAt(17);
         if ((UIComponent)MainCanvas.curForm.getComponents().elementAt(27) instanceof UITextField) {
            UITextField uiTF = (UITextField)MainCanvas.curForm.getComponents().elementAt(27);
            uiTF.setMaxNumber((long)UIForm.myMoney);
            UIForm.tradeMyMoney = uiTF.getNumber();
            if (UIForm.isClear) {
               UIForm.tradeMyMoney = 0;
               UIForm.isClear = false;
            }

            uiTF.setLabel(Integer.toString(UIForm.tradeMyMoney));
         }

         int i = 0;

         for(int ii = MainCanvas.curForm.getComponents().size(); i < ii; ++i) {
            if ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i) instanceof UIPicture && ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i)).getType() == 1 && ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i)).isFocus) {
               UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i);
               if (pic.clientType == 34) {
                  labelMyGoods.setText(pic.getEquipName());
                  UIForm.isOtherPic = false;
                  UIForm.curPicNum = (byte)(i - 7);
                  labelMyGoods.setTextColor(UIGrid.getStuffWordColor(pic.getEquipColorLevel()));
               } else if (pic.clientType == 35) {
                  labelOtherGoods.setText(pic.getEquipName());
                  UIForm.isOtherPic = true;
                  UIForm.curPicNum = (byte)(i - 20);
                  labelOtherGoods.setTextColor(UIGrid.getStuffWordColor(pic.getEquipColorLevel()));
               }
            } else if (((UIComponent)MainCanvas.curForm.getComponents().elementAt(i)).isFocus && !((UIComponent)MainCanvas.curForm.getComponents().elementAt(i) instanceof UIPicture)) {
               labelMyGoods.setText("");
               labelOtherGoods.setText("");
            }
         }
      case 1376262:
      case 1376263:
      case 1376264:
      case 1376266:
      case 1376267:
      default:
      }
   }

   public static void especialDealChange() {
      switch(MainCanvas.curForm.clientCommand) {
      case 1769473:
      case 1769474:
      case 1769476:
      case 1769478:
      case 1769479:
      case 1769480:
      case 1769488:
      case 1769489:
      case 1769490:
      case 1769491:
      case 1769492:
         UIGrid grid = null;
         UILabel nameLabel = null;
         if (((UIComponent)MainCanvas.curForm.getComponents().elementAt(3)).isFocus) {
            grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(3);
         } else if (((UIComponent)MainCanvas.curForm.getComponents().elementAt(5)).isFocus) {
            grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(5);
         }

         if (grid != null) {
            nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
            nameLabel.setText(grid.stuffName[0]);
            if (grid.stuffId[grid.getGridIndex()] != -1) {
               nameLabel.setText(grid.stuffName[grid.getGridIndex()]);
               nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
            } else {
               nameLabel.setText("");
            }
         }
      case 1769475:
      case 1769477:
      case 1769481:
      case 1769482:
      case 1769483:
      case 1769484:
      case 1769485:
      case 1769486:
      case 1769487:
      default:
      }
   }

   public static void especialDealShop() {
      switch(MainCanvas.curForm.clientCommand) {
      case 983041:
      case 983042:
      case 983043:
      case 983044:
      case 983045:
      case 983046:
      case 983047:
      case 983048:
      case 983049:
      case 983056:
      case 983057:
      case 983058:
         UIGrid grid = null;
         UILabel nameLabel = null;
         UILabel priceLabel = null;
         UILabel playerMoneyLabel = null;
         UILabel unitLabel = null;
         unitLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(11);
         if ((UIComponent)MainCanvas.curForm.getComponents().elementAt(3) instanceof UIGrid && (((UIComponent)MainCanvas.curForm.getComponents().elementAt(3)).getClientType() == 15 || ((UIComponent)MainCanvas.curForm.getComponents().elementAt(3)).getClientType() == 42) && ((UIComponent)MainCanvas.curForm.getComponents().elementAt(3)).isFocus()) {
            grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(3);
            unitLabel.setText(unitLabel.textGroup[0]);
         } else if ((UIComponent)MainCanvas.curForm.getComponents().elementAt(5) instanceof UIGrid && ((UIComponent)MainCanvas.curForm.getComponents().elementAt(5)).getClientType() == 6 && ((UIComponent)MainCanvas.curForm.getComponents().elementAt(5)).isFocus()) {
            grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(5);
            unitLabel.setText(unitLabel.textGroup[1]);
         }

         if (grid != null) {
            if ((UIComponent)MainCanvas.curForm.getComponents().elementAt(4) instanceof UILabel) {
               nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
               if (nameLabel.getType() == 1) {
                  if (grid.stuffId[grid.getGridIndex()] != -1) {
                     nameLabel.setText(grid.stuffName[grid.getGridIndex()]);
                     nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
                  } else {
                     nameLabel.setText("");
                  }
               }
            }

            if ((UIComponent)MainCanvas.curForm.getComponents().elementAt(10) instanceof UILabel) {
               priceLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(10);
               if (priceLabel.getType() == 4) {
                  priceLabel.setText(Integer.toString(grid.stuffPrice[0]));
                  if (grid.stuffId[grid.getGridIndex()] != -1) {
                     priceLabel.setText(Integer.toString(grid.stuffPrice[grid.getGridIndex()]));
                  } else {
                     priceLabel.setText("");
                  }
               }
            }

            if ((UIComponent)MainCanvas.curForm.getComponents().elementAt(7) instanceof UILabel) {
               playerMoneyLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(7);
               if (playerMoneyLabel.getType() == 3) {
                  grid.setPlayerMoney(playerMoneyLabel.getNum1());
               }
            }
         }
      case 983050:
      case 983051:
      case 983052:
      case 983053:
      case 983054:
      case 983055:
      default:
      }
   }

   public static void especialDealStorage() {
      switch(MainCanvas.curForm.clientCommand) {
      case 1179649:
      case 1179650:
      case 1179651:
      case 1179652:
      case 1179653:
      case 1179654:
      case 1179655:
      case 1179657:
      case 1638423:
         UIGrid grid = null;
         UILabel nameLabel = null;
         if (((UIComponent)MainCanvas.curForm.getComponents().elementAt(6)).isFocus()) {
            grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(6);
         } else if (((UIComponent)MainCanvas.curForm.getComponents().elementAt(8)).isFocus()) {
            grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(8);
         }

         if (grid != null) {
            nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(7);
            if (grid.stuffId[grid.getGridIndex()] != -1) {
               nameLabel.setText(grid.stuffName[grid.getGridIndex()]);
               nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
            } else {
               nameLabel.setText("");
            }
         }
      default:
      }
   }

   public static void GemAndUnsealModule() {
      UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
      UITextArea textarea;
      int[] index;
      UIPicture[] picture;
      int i;
      UILabel label_4;
      UIGrid grid;
      UILabel nameLabel;
      int i;
      UILabel label_1;
      UIPicture picture;
      UIGrid grid;
      UILabel label_2;
      UIPicture picture;
      UIGrid grid;
      UILabel label_3;
      label369:
      switch(MainCanvas.curForm.clientCommand) {
      case 917525:
      case 2031621:
      case 2031636:
      case 3342356:
         nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(14);
         picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
         picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(6);
         grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(9);
         if (grid.isFocus()) {
            if (grid.stuffId[grid.getGridIndex()] != -1) {
               menuBar.setMenuText("Thao tác", 0);
               nameLabel.setText(grid.stuffName[grid.getGridIndex()]);
               nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
            } else {
               menuBar.setMenuText("", 0);
               nameLabel.setText("");
            }
         } else {
            menuBar.setMenuText("Thao tác", 0);
            if (picture.isFocus()) {
               if (PCUnsealGemCarve.m_bPrimaryGemIndex != -1) {
                  nameLabel.setText(grid.stuffName[PCUnsealGemCarve.m_bPrimaryGemIndex]);
                  nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[PCUnsealGemCarve.m_bPrimaryGemIndex]));
               } else {
                  nameLabel.setTextColor(Cons.COLOR_FONT_1);
                  if (MainCanvas.curForm.clientCommand == 2031636) {
                     nameLabel.setText("Đặt trang bị cần mở Ấn");
                  } else if (MainCanvas.curForm.clientCommand == 2031621) {
                     nameLabel.setText("Đặt Nguyên Thạch");
                  } else if (MainCanvas.curForm.clientCommand == 3342356) {
                     nameLabel.setText("Đặt Pháp Bảo cần khắc");
                  } else if (MainCanvas.curForm.clientCommand == 917525) {
                     nameLabel.setText("Đặt trang bị cần Cường Hóa");
                  }
               }
            } else if (picture.isFocus()) {
               if (PCUnsealGemCarve.m_bCarveSymbolIndex != -1) {
                  nameLabel.setText(grid.stuffName[PCUnsealGemCarve.m_bCarveSymbolIndex]);
                  nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[PCUnsealGemCarve.m_bCarveSymbolIndex]));
               } else {
                  nameLabel.setTextColor(Cons.COLOR_FONT_1);
                  if (MainCanvas.curForm.clientCommand == 2031636) {
                     nameLabel.setText("Đặt Quyển Mở Ấn");
                  } else if (MainCanvas.curForm.clientCommand == 2031621) {
                     nameLabel.setText("Đặt Điêu Khắc Phù");
                  } else if (MainCanvas.curForm.clientCommand == 3342356) {
                     nameLabel.setText("Đặt Pháp Bảo Minh Văn");
                  } else if (MainCanvas.curForm.clientCommand == 917525) {
                     nameLabel.setText("Đặt Cường Hóa Phù");
                  }
               }
            }
         }
         break;
      case 1376260:
         UITextField rextfield = (UITextField)MainCanvas.curForm.getComponents().elementAt(27);
         if (rextfield.isFocus() && rextfield.getSb().toString().equals("0")) {
            menuBar.setMenuText("Nhập vào", 0);
         } else if (rextfield.isFocus() && !rextfield.getSb().toString().equals("0")) {
            menuBar.setMenuText("Sửa", 0);
         } else {
            menuBar.setMenuText("Thao tác", 0);
         }
         break;
      case 1900547:
         UIPicture picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(3);
         UITextField rextfield = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
         UIRadio radio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(6);
         grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(8);
         if (!picture.isFocus() && !rextfield.isFocus() && !radio.isFocus()) {
            if (grid.isFocus()) {
               menuBar.setMenuText("Thao tác", 0);
            }
         } else if (PCAuction.m_bEntityGoods == 1) {
            menuBar.setMenuText("Thao tác", 0);
         } else if (PCAuction.m_bEntityGoods == 0) {
            menuBar.setMenuText("", 0);
         }
         break;
      case 1966097:
         nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(10);
         picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
         picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(6);
         grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(7);
         if (grid.isFocus()) {
            if (grid.stuffId[grid.getGridIndex()] != -1) {
               menuBar.setMenuText("Thao tác", 0);
               nameLabel.setText(grid.stuffName[grid.getGridIndex()]);
               nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
            } else {
               menuBar.setMenuText("", 0);
               nameLabel.setText("");
            }
         } else {
            menuBar.setMenuText("Thao tác", 0);
            if (picture.isFocus()) {
               if (PCGem.m_bEquipIndex != -1) {
                  nameLabel.setText(grid.stuffName[PCGem.m_bEquipIndex]);
                  nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[PCGem.m_bEquipIndex]));
               } else {
                  nameLabel.setTextColor(Cons.COLOR_FONT_1);
                  nameLabel.setText("Đặt trang bị");
               }
            } else if (picture.isFocus()) {
               if (PCGem.m_bGemIndex != -1) {
                  nameLabel.setText(grid.stuffName[PCGem.m_bGemIndex]);
                  nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[PCGem.m_bGemIndex]));
               } else {
                  nameLabel.setTextColor(Cons.COLOR_FONT_1);
                  nameLabel.setText("Đặt phù giải trừ vào đây");
               }
            }
         }
         break;
      case 2228225:
         nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(14);
         picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
         grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(7);
         if (grid.isFocus()) {
            if (grid.stuffId[grid.getGridIndex()] != -1) {
               menuBar.setMenuText("Thao tác", 0);
               nameLabel.setText(grid.stuffName[grid.getGridIndex()]);
               nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
            } else {
               menuBar.setMenuText("", 0);
               nameLabel.setText("");
            }
         } else {
            menuBar.setMenuText("Thao tác", 0);
            if (picture.isFocus()) {
               if (PCGemJoinOrRemove.m_bGemIndex != -1) {
                  if (grid.stuffId[PCGemJoinOrRemove.m_bGemIndex] != -1) {
                     nameLabel.setText(grid.stuffName[PCGemJoinOrRemove.m_bGemIndex]);
                     nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[PCGemJoinOrRemove.m_bGemIndex]));
                  } else {
                     nameLabel.setText(PCGemJoinOrRemove.m_sName);
                     nameLabel.setTextColor(UIGrid.getStuffWordColor(PCGemJoinOrRemove.m_sNameColo));
                  }
               } else {
                  nameLabel.setTextColor(Cons.COLOR_FONT_1);
                  nameLabel.setText("Đặt Nguyên Thạch");
               }
            }
         }
         break;
      case 2228256:
         nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(14);
         picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
         grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(7);
         if (grid.isFocus()) {
            if (grid.stuffId[grid.getGridIndex()] != -1) {
               menuBar.setMenuText("Thao tác", 0);
               nameLabel.setText(grid.stuffName[grid.getGridIndex()]);
               nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
            } else {
               menuBar.setMenuText("", 0);
               nameLabel.setText("");
            }
         } else {
            menuBar.setMenuText("Thao tác", 0);
            if (picture.isFocus()) {
               if (PCGemJoinOrRemove.m_bGemIndex != -1) {
                  if (grid.stuffId[PCGemJoinOrRemove.m_bGemIndex] != -1) {
                     nameLabel.setText(grid.stuffName[PCGemJoinOrRemove.m_bGemIndex]);
                     nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[PCGemJoinOrRemove.m_bGemIndex]));
                  } else {
                     nameLabel.setText(PCGemJoinOrRemove.m_sName);
                     nameLabel.setTextColor(UIGrid.getStuffWordColor(PCGemJoinOrRemove.m_sNameColo));
                  }
               } else {
                  nameLabel.setTextColor(Cons.COLOR_FONT_1);
                  nameLabel.setText("Đặt Bảo Thạch");
               }
            }
         }
         break;
      case 2424833:
         nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(3);
         label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
         label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(5);
         label_3 = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
         label_4 = (UILabel)MainCanvas.curForm.getComponents().elementAt(7);
         UILabel label_6 = (UILabel)MainCanvas.curForm.getComponents().elementAt(9);
         UITextArea textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(8);
         if (nameLabel.isFocus()) {
            textarea.setContent(PCIncrement.m_sFullExplain[0]);
         } else if (label_1.isFocus()) {
            textarea.setContent(PCIncrement.m_sFullExplain[1]);
         } else if (label_2.isFocus()) {
            textarea.setContent(PCIncrement.m_sFullExplain[2]);
         } else if (label_3.isFocus()) {
            textarea.setContent(PCIncrement.m_sFullExplain[3]);
         } else if (label_4.isFocus()) {
            textarea.setContent(PCIncrement.m_sFullExplain[4]);
         } else if (label_6.isFocus()) {
            textarea.setContent(PCIncrement.m_sFullExplain[5]);
         }
         break;
      case 2424840:
         nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(3);
         label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
         UITextArea textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(5);
         if (nameLabel.isFocus()) {
            textarea.setContent(PCIncrement.m_sFullExplain[0]);
         } else if (label_1.isFocus()) {
            textarea.setContent(PCIncrement.m_sFullExplain[1]);
         }
         break;
      case 2555905:
         nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(3);
         label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
         label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
         label_3 = (UILabel)MainCanvas.curForm.getComponents().elementAt(10);
         label_4 = (UILabel)MainCanvas.curForm.getComponents().elementAt(8);

         int i;
         UIPicture picture;
         for(i = 0; i < 12; ++i) {
            picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 11);
            if (picture.isFocus() && picture.getWhetherEnvelop() != 0) {
               nameLabel.setText(picture.getCropName());
               label_1.setText(picture.getGrowthPhase());
               label_2.setNum1(picture.getGrowthLimit());
               label_3.setText(picture.getCropStatus_2());
               if (picture.getGroundStatus() == -1) {
                  label_4.setText(PCFarm.SOIL_CIRCS[0]);
               } else if (picture.getGroundStatus() == 0) {
                  label_4.setText(PCFarm.SOIL_CIRCS[1]);
               } else if (picture.getGroundStatus() >= 1 && picture.getGroundStatus() <= 30) {
                  label_4.setText(PCFarm.SOIL_CIRCS[2]);
               } else if (picture.getGroundStatus() >= 31 && picture.getGroundStatus() <= 70) {
                  label_4.setText(PCFarm.SOIL_CIRCS[3]);
               } else if (picture.getGroundStatus() >= 71 && picture.getGroundStatus() <= 100) {
                  label_4.setText(PCFarm.SOIL_CIRCS[4]);
               }
            }
         }

         i = 0;

         while(true) {
            if (i >= 6) {
               break label369;
            }

            picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 25);
            if (picture.isFocus() && picture.getWhetherEnvelop() != 0) {
               nameLabel.setText(picture.getCropName());
               label_1.setText(picture.getGrowthPhase());
               label_2.setNum1(picture.getGrowthLimit());
               label_3.setText(picture.getCropStatus_2());
               if (picture.getGroundStatus() == -1) {
                  label_4.setText(PCFarm.SOIL_CIRCS[0]);
               } else if (picture.getGroundStatus() == 0) {
                  label_4.setText(PCFarm.SOIL_CIRCS[1]);
               } else if (picture.getGroundStatus() >= 1 && picture.getGroundStatus() <= 30) {
                  label_4.setText(PCFarm.SOIL_CIRCS[2]);
               } else if (picture.getGroundStatus() >= 31 && picture.getGroundStatus() <= 70) {
                  label_4.setText(PCFarm.SOIL_CIRCS[3]);
               } else if (picture.getGroundStatus() >= 71 && picture.getGroundStatus() <= 100) {
                  label_4.setText(PCFarm.SOIL_CIRCS[4]);
               }
            }

            ++i;
         }
      case 2555906:
         nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(10);

         for(i = 0; i < 3; ++i) {
            picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 11);
            if (picture.isFocus()) {
               nameLabel.setText(PCFarm.m_sPropMane[i]);
            }
         }

         UITextArea textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(15);
         if (textarea.isFocus()) {
            if (PCFarm.m_bWhetherGetHome == 0) {
               menuBar.setMenuText("Biên tập", 0);
            } else {
               menuBar.setMenuText("", 0);
            }
         } else if (PCFarm.m_bWhetherGetHome == 0) {
            menuBar.setMenuText("Thao tác", 0);
         } else {
            menuBar.setMenuText("Xem", 0);
         }
         break;
      case 2555907:
      case 2555915:
         grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(3);
         if (!grid.stuffName[grid.gridIndex].equals("") && grid.stuffName[grid.gridIndex] != null) {
            menuBar.setMenuText("Thao tác", 0);
         } else {
            menuBar.setMenuText("", 0);
         }
         break;
      case 2555908:
      case 2555957:
         nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
         label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(7);
         grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(3);
         grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(5);
         if (grid.isFocus()) {
            nameLabel.setText(grid.stuffName[grid.getGridIndex()]);
            nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
            label_1.setNum1(PCFarm.m_nPropPrice[grid.getGridIndex()]);
            if (!grid.stuffName[grid.gridIndex].equals("") && grid.stuffName[grid.gridIndex] != null) {
               menuBar.setMenuText("Thao tác", 0);
            } else {
               menuBar.setMenuText("", 0);
            }
         } else if (grid.isFocus()) {
            nameLabel.setText(grid.stuffName[grid.getGridIndex()]);
            nameLabel.setTextColor(UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
            if (!grid.stuffName[grid.gridIndex].equals("") && grid.stuffName[grid.gridIndex] != null) {
               label_1.setNum1(grid.stuffPrice[grid.getGridIndex()]);
               menuBar.setMenuText("Thao tác", 0);
            } else {
               label_1.setNum1(0);
               menuBar.setMenuText("", 0);
            }
         }
         break;
      case 2555911:
         grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(10);
         if (grid.stuffId[grid.gridIndex] != -1) {
            menuBar.setMenuText("Thao tác", 0);
         } else {
            menuBar.setMenuText("Sắp xếp", 0);
         }

         i = 0;

         while(true) {
            if (i >= 10) {
               break label369;
            }

            picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 3);
            if (picture.isFocus()) {
               picture.setFocus(false, MainCanvas.curForm);
               grid.setFocus(true, MainCanvas.curForm);
            }

            i += 2;
         }
      case 2555936:
         if (((UIComponent)MainCanvas.curForm.getComponents().elementAt(3)).getClientType() == 164) {
            grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(3);
            if (!grid.stuffName[grid.gridIndex].equals("") && grid.stuffName[grid.gridIndex] != null) {
               menuBar.setMenuText("Thao tác", 0);
            } else {
               menuBar.setMenuText("", 0);
            }
         } else if (((UIComponent)MainCanvas.curForm.getComponents().elementAt(3)).getClientType() == 167) {
            grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(3);
            if (!grid.stuffName[grid.gridIndex].equals("") && grid.stuffName[grid.gridIndex] != null) {
               menuBar.setMenuText("Thao tác", 0);
            } else {
               menuBar.setMenuText("", 0);
            }
         }
         break;
      case 3145730:
         UIRadio radio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(13);
         index = new int[]{3, 4, 5, 6, 7, 8, 9, 10};
         picture = new UIPicture[index.length];

         for(i = 0; i < index.length; ++i) {
            picture[i] = (UIPicture)MainCanvas.curForm.getComponents().elementAt(index[i]);
            if (picture[i].isFocus()) {
               PCHang.m_bSkillIndex = (byte)i;
            }
         }

         if (radio.isFocus()) {
            menuBar.setMenuText("Xác nhận", 0);
         } else {
            menuBar.setMenuText("Thao tác", 0);
         }
         break;
      case 3145731:
         textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(13);
         index = new int[]{4, 6, 10, 12};
         UIRadio[] radio = new UIRadio[index.length];
         i = 0;

         while(true) {
            if (i >= index.length) {
               break label369;
            }

            radio[i] = (UIRadio)MainCanvas.curForm.getComponents().elementAt(index[i]);
            if (radio[i].isFocus()) {
               textarea.setContent(PCHang.IntercalteContent[i]);
            }

            ++i;
         }
      case 3342340:
         textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(13);
         index = new int[]{3, 5, 7, 9, 11};
         picture = new UIPicture[index.length];

         for(i = 0; i < index.length; ++i) {
            picture[i] = (UIPicture)MainCanvas.curForm.getComponents().elementAt(index[i]);
            if (picture[i].isFocus()) {
               textarea.setContent(PCTreasure.explain[i]);
            }
         }
      }

      if (MainCanvas.getState() == 18) {
         UIForm.SimplenessIntroduce();
      }

   }

   public static void especialDeal() {
      if (MainCanvas.curForm != null) {
         try {
            especialDealChange();
            especialDealTrade();
            especialDealEquip();
            especialDealEquipFix();
            especialDealOtherEquip();
            especialDealCompare();
            especialDealShop();
            especialDealStorage();
            GemAndUnsealModule();
         } catch (Exception var1) {
         }
      }

   }

   public void touchMailAction() {
      if (MainCanvas.SUPPORT_POINTER && MainCanvas.isInputDown(268435456)) {
         if (MainCanvas.curForm.clientCommand != 1703937 && MainCanvas.curForm.clientCommand != 1703952 && MainCanvas.curForm.clientCommand != 1703938) {
            if (MainCanvas.curForm.clientCommand == 852020) {
               UIList list = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
               ListItem item = (ListItem)list.getItems().elementAt(list.selectedIndex);
               UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
               if (item.m_bBattlefieldExamine == 4125) {
                  menuBar.setMenuText("", 0);
               } else {
                  menuBar.setMenuText("Thao tác", 0);
               }
            } else if (MainCanvas.curForm.clientCommand == 3145730) {
               UIRadio radio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(13);

               for(int i = 0; i < PCHang.WatchSkill.length; ++i) {
                  UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 3);
                  if (!pic.isFocus() && radio.isFocus()) {
                     if (MainCanvas.isPointInRect(pic.getPositionX(), pic.getPositionY(), pic.getWidth(), pic.getHeight())) {
                        pic.setFocus(true, MainCanvas.curForm);
                     }
                  } else if (!radio.isFocus() && pic.isFocus() && MainCanvas.isPointInRect(radio.getPositionX(), radio.getPositionY(), radio.getWidth(), radio.getHeight())) {
                     pic.setFocus(false, MainCanvas.curForm);
                     radio.setFocus(true, MainCanvas.curForm);
                     radio.focusWidgetPointAction();
                  }
               }
            }
         } else {
            for(int i = 0; i < 6; ++i) {
               UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 7);
               if (pic.isFocus()) {
                  MainCanvas.m_bMailAccessoriesN = (byte)i;
               }
            }
         }
      }

   }

   private void addLoginStr(UIComponent uic) {
      if (MainCanvas.getState() == 11) {
         if (uic instanceof UIRadio) {
            UIRadio r = (UIRadio)uic;
            if (r.isFocus()) {
               if (r.getSelectIndex() == 0) {
                  SIChat.addChatLocal("Sử dụng tài khoản PT đăng nhập");
               } else if (r.getSelectIndex() == 1) {
                  SIChat.addChatLocal("Sử dụng tài khoản Vitalk để đăng nhập");
               }
            } else {
               SIChat.removeChatLocal();
            }
         } else {
            SIChat.removeChatLocal();
         }
      }

   }

   public void navigationKey(UIForm form) {
      if (this.isFocus && MainCanvas.curForm != null && !this.isShowMenu) {
         this.touchMailAction();
         UITextArea ta;
         UITextField textfield_1;
         UITextField textfield_2;
         UITitle menuBar;
         UILabel label;
         UIRadio radio;
         UIPicture picture11;
         UIComponent rightUic;
         int i;
         UIPicture picture_1;
         UITitle menuBar;
         UIRadio gender;
         if (!MainCanvas.isInputDownOrHold(4096)) {
            UILabel label;
            if (MainCanvas.isInputDownOrHold(8192)) {
               if (this instanceof UITextArea) {
                  ta = (UITextArea)this;
                  if (ta.getPointer() < ta.getContentFullRows() - ta.getCanShowRows()) {
                     return;
                  }
               } else if (this instanceof UIRadio && (this.type == 0 || this.type == 2) && !((UIRadio)this).isHorizontal()) {
                  radio = (UIRadio)this;
                  if (!radio.isEndDown) {
                     return;
                  }
               } else if (MainCanvas.getState() == 17) {
                  label = (UILabel)MainCanvas.curForm.getComponents().elementAt(16);
                  if (MainCanvas.playPicture[MainCanvas.m_bChoose].otherP == null) {
                     return;
                  }

                  if (label.getText().equals("Xóa sau 3 ngày")) {
                     return;
                  }
               }

               rightUic = this.getFinalAroundComponent(MainCanvas.curForm, (byte)1);
               if (rightUic != null) {
                  this.setFocus(false, form);
                  rightUic.setFocus(true, form);
                  this.addLoginStr(rightUic);
                  if (rightUic.isTopTip && rightUic != this) {
                     rightUic.resetTopTip();
                     rightUic.startTopTip();
                  }

                  if (MainCanvas.curForm.clientCommand == 2097155) {
                     label = (UILabel)MainCanvas.curForm.getComponents().elementAt(32);
                     UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                     if (label.getText().equals("Bắt đầu")) {
                        menuBar.setMenuText("Bắt đầu", 0);
                     }
                  } else if (MainCanvas.getState() == 17) {
                     menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                     if (MainCanvas.m_bAmendNickname[MainCanvas.m_bChoose]) {
                        menuBar.setMenuText("Sửa", 0);
                     } else {
                        menuBar.setMenuText("Đồng ý", 0);
                     }
                  } else if (MainCanvas.getState() != 18) {
                     if (MainCanvas.getState() != 11 && MainCanvas.curForm.clientCommand != 2297615) {
                        if (MainCanvas.curForm.clientCommand == 2555905) {
                           label = (UILabel)MainCanvas.curForm.getComponents().elementAt(35);
                           picture_1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(25);
                           if (rightUic instanceof UIPicture) {
                              UIPicture picture = (UIPicture)rightUic;
                              if (picture.getWhetherEnvelop() == 0) {
                                 rightUic.setFocus(false, form);
                                 if (picture_1.getWhetherEnvelop() == 0) {
                                    label.setFocus(true, form);
                                 } else {
                                    picture_1.setFocus(true, form);
                                 }
                              }
                           }
                        }
                     } else {
                        textfield_1 = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
                        textfield_2 = (UITextField)MainCanvas.curForm.getComponents().elementAt(5);
                        menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                        if (!textfield_1.isFocus() && !textfield_2.isFocus()) {
                           menuBar.setMenuText("Đồng ý", 0);
                        } else if (!textfield_1.getSb().toString().equals("") && textfield_1.getSb().toString() != null && !textfield_2.getSb().toString().equals("") && textfield_2.getSb().toString() != null) {
                           if (MainCanvas.getState() == 11) {
                              menuBar.setMenuText("Đăng nhập", 0);
                           } else {
                              menuBar.setMenuText("Đồng ý", 0);
                           }
                        } else {
                           menuBar.setMenuText("Nhập vào", 0);
                        }
                     }
                  } else {
                     menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                     textfield_2 = (UITextField)MainCanvas.curForm.getComponents().elementAt(9);
                     gender = (UIRadio)MainCanvas.curForm.getComponents().elementAt(7);
                     UIRadio vocation = (UIRadio)MainCanvas.curForm.getComponents().elementAt(8);
                     if (!textfield_2.isFocus() || textfield_2.getSb().toString().trim() != null && !textfield_2.getSb().toString().trim().equals("")) {
                        if (textfield_2.isFocus() && textfield_2.getSb().toString().trim() != null && !textfield_2.getSb().toString().trim().equals("")) {
                           menuBar.setMenuText("Sửa", 0);
                        } else if (gender.isFocus()) {
                           menuBar.setMenuText("", 0);
                        } else {
                           menuBar.setMenuText("Giới thiệu", 0);
                        }
                     } else {
                        menuBar.setMenuText("Nhập vào", 0);
                     }

                     if (vocation.isFocus()) {
                        vocation.width = vocation.getAutoRadioWidth();
                     }
                  }
               }

               if (MainCanvas.getState() == 18) {
                  ++MainCanvas.focus;
                  if (MainCanvas.focus > 4) {
                     MainCanvas.focus = 4;
                  }
               }
            } else {
               UILabel label;
               if (MainCanvas.isInputDownOrHold(16384)) {
                  if (MainCanvas.curForm.clientCommand == 2097155) {
                     label = (UILabel)MainCanvas.curForm.getComponents().elementAt(32);
                     if (label.getText().equals("Bắt đầu") && label.isFocus()) {
                        return;
                     }
                  }

                  rightUic = this.getFinalAroundComponent(MainCanvas.curForm, (byte)2);
                  if (rightUic != null) {
                     this.setFocus(false, form);
                     rightUic.setFocus(true, form);
                     this.addLoginStr(rightUic);
                     if (rightUic.isTopTip && rightUic != this) {
                        rightUic.resetTopTip();
                        rightUic.startTopTip();
                     }

                     if (MainCanvas.curForm.clientCommand != 1703937 && MainCanvas.curForm.clientCommand != 1703952 && MainCanvas.curForm.clientCommand != 1703938) {
                        if (MainCanvas.curForm.clientCommand == 2097155) {
                           menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                           picture_1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(31);
                           label = (UILabel)MainCanvas.curForm.getComponents().elementAt(32);
                           if (picture_1.isFocus() && label.getText().equals("Thanh toán")) {
                              menuBar.setMenuText("Bắt đầu", 0);
                           } else if (label.getText().equals("Thanh toán") && label.isFocus()) {
                              menuBar.setMenuText("Thanh toán", 0);
                           }
                        } else if (MainCanvas.curForm.clientCommand == 2555905) {
                           if (PCFarm.m_bWhetherGetHome == 1 && this instanceof UILabel) {
                              label = (UILabel)MainCanvas.curForm.getComponents().elementAt(35);
                              this.setFocus(false, form);
                              rightUic.setFocus(false, form);
                              label.setFocus(true, form);
                           }

                           if (rightUic instanceof UIPicture) {
                              picture11 = (UIPicture)rightUic;
                              if (picture11.getWhetherEnvelop() == 0) {
                                 this.setFocus(true, form);
                                 rightUic.setFocus(false, form);
                              }
                           }
                        }
                     } else {
                        for(i = 0; i < 6; ++i) {
                           picture_1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 7);
                           if (picture_1.isFocus()) {
                              --MainCanvas.m_bMailAccessoriesN;
                              if (MainCanvas.m_bMailAccessoriesN < 0) {
                                 MainCanvas.m_bMailAccessoriesN = 0;
                              }
                           }
                        }
                     }
                  }
               } else if (MainCanvas.isInputDownOrHold(32768)) {
                  if (MainCanvas.curForm.clientCommand == 2097155) {
                     label = (UILabel)MainCanvas.curForm.getComponents().elementAt(32);
                     if (label.getText().equals("Bắt đầu") && label.isFocus()) {
                        return;
                     }
                  } else if (MainCanvas.curForm.clientCommand == 2555905 && this instanceof UIPicture) {
                     UIPicture picture = (UIPicture)this;
                     if (picture.getWhetherEnvelop() == 0) {
                        return;
                     }
                  }

                  rightUic = this.getFinalAroundComponent(MainCanvas.curForm, (byte)3);
                  if (rightUic != null) {
                     this.setFocus(false, form);
                     rightUic.setFocus(true, form);
                     this.addLoginStr(rightUic);
                     if (rightUic.isTopTip && rightUic != this) {
                        rightUic.resetTopTip();
                        rightUic.startTopTip();
                     }

                     if (MainCanvas.curForm.clientCommand != 1703937 && MainCanvas.curForm.clientCommand != 1703952 && MainCanvas.curForm.clientCommand != 1703938) {
                        if (MainCanvas.curForm.clientCommand == 2097155) {
                           menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                           picture_1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(33);
                           label = (UILabel)MainCanvas.curForm.getComponents().elementAt(32);
                           if (picture_1.isFocus()) {
                              menuBar.setMenuText("Bắt đầu", 0);
                           } else if (label.getText().equals("Thanh toán") && label.isFocus()) {
                              menuBar.setMenuText("Thanh toán", 0);
                           }
                        } else if (MainCanvas.curForm.clientCommand == 2555905) {
                           if (PCFarm.m_bWhetherGetHome == 1 && this instanceof UILabel) {
                              label = (UILabel)MainCanvas.curForm.getComponents().elementAt(37);
                              this.setFocus(false, form);
                              rightUic.setFocus(false, form);
                              label.setFocus(true, form);
                           }

                           if (rightUic instanceof UIPicture) {
                              picture11 = (UIPicture)rightUic;
                              if (picture11.getWhetherEnvelop() == 0) {
                                 this.setFocus(true, form);
                                 rightUic.setFocus(false, form);
                              }
                           }
                        }
                     } else {
                        for(i = 0; i < 6; ++i) {
                           picture_1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 7);
                           if (picture_1.isFocus()) {
                              ++MainCanvas.m_bMailAccessoriesN;
                              if (MainCanvas.m_bMailAccessoriesN > 5) {
                                 MainCanvas.m_bMailAccessoriesN = 5;
                              }
                           }
                        }
                     }
                  }
               }
            }
         } else {
            if (this instanceof UITextArea) {
               ta = (UITextArea)this;
               if (ta.getPointer() > 0) {
                  return;
               }
            } else if (this instanceof UITable) {
               UITable table = (UITable)this;
               if (table.getSelectedIndex() > 0 || !table.isEndUp) {
                  return;
               }
            } else if (this instanceof UIList) {
               UIList list = (UIList)this;
               if (list.getSelectedIndex() > 0 || !list.isEndUp) {
                  return;
               }
            } else if (this instanceof UIRadio && (this.type == 0 || this.type == 2) && !((UIRadio)this).isHorizontal()) {
               radio = (UIRadio)this;
               if (!radio.isEndUp) {
                  return;
               }
            } else if (MainCanvas.curForm.clientCommand == 2097155) {
               label = (UILabel)MainCanvas.curForm.getComponents().elementAt(32);
               if (label.getText().equals("Thanh toán")) {
                  return;
               }
            }

            rightUic = this.getFinalAroundComponent(MainCanvas.curForm, (byte)0);
            if (rightUic != null) {
               this.setFocus(false, form);
               if (MainCanvas.getState() == 17) {
                  rightUic.setFocus(false, form);
               } else {
                  rightUic.setFocus(true, form);
               }

               this.addLoginStr(rightUic);
               if (rightUic.isTopTip && rightUic != this) {
                  rightUic.resetTopTip();
                  rightUic.startTopTip();
               }

               if (MainCanvas.curForm.clientCommand == 2097155) {
                  menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                  if (rightUic instanceof UITextField) {
                     menuBar.setMenuText("", 0);
                  }
               } else if (MainCanvas.getState() == 18) {
                  menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                  UIRadio gender = (UIRadio)MainCanvas.curForm.getComponents().elementAt(7);
                  gender = (UIRadio)MainCanvas.curForm.getComponents().elementAt(8);
                  if (gender.isFocus()) {
                     menuBar.setMenuText("", 0);
                  } else {
                     menuBar.setMenuText("Giới thiệu", 0);
                  }

                  if (gender.isFocus()) {
                     gender.width = gender.getAutoRadioWidth();
                  }
               } else if (MainCanvas.getState() == 17 && rightUic instanceof UIPicture) {
                  for(i = 0; i < MainCanvas.playPicture.length; ++i) {
                     MainCanvas.playPicture[i] = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 3);
                     if (i == MainCanvas.m_bChoose) {
                        MainCanvas.playPicture[i].setFocus(true, MainCanvas.curForm);
                     } else {
                        MainCanvas.playPicture[i].setFocus(false, MainCanvas.curForm);
                     }
                  }
               } else if (MainCanvas.getState() != 11 && MainCanvas.curForm.clientCommand != 2297615) {
                  if (MainCanvas.curForm.clientCommand == 2555905) {
                     picture11 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(11);
                     if (rightUic instanceof UIPicture) {
                        picture_1 = (UIPicture)rightUic;
                        if (picture_1.getWhetherEnvelop() == 0) {
                           this.setFocus(false, form);
                           rightUic.setFocus(false, form);
                           picture11.setFocus(true, form);
                        }
                     }
                  } else if (MainCanvas.curForm.clientCommand == 3145730) {
                     picture11 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(PCHang.m_bSkillIndex + 3);
                     rightUic.setFocus(false, form);
                     picture11.setFocus(true, form);
                  }
               } else {
                  textfield_1 = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
                  textfield_2 = (UITextField)MainCanvas.curForm.getComponents().elementAt(5);
                  menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                  if (!textfield_1.isFocus() && !textfield_2.isFocus()) {
                     menuBar.setMenuText("Đồng ý", 0);
                  } else if (!textfield_1.getSb().toString().equals("") && textfield_1.getSb().toString() != null && !textfield_2.getSb().toString().equals("") && textfield_2.getSb().toString() != null) {
                     if (MainCanvas.getState() == 11) {
                        menuBar.setMenuText("Đăng nhập", 0);
                     } else {
                        menuBar.setMenuText("Đồng ý", 0);
                     }
                  } else {
                     menuBar.setMenuText("Nhập vào", 0);
                  }
               }
            }

            if (MainCanvas.getState() == 18) {
               --MainCanvas.focus;
               if (MainCanvas.focus < 0) {
                  MainCanvas.focus = 0;
               }
            }
         }

      }
   }

   public void resetTopTip() {
      this.isTipState = true;
      this.beginRecordT2 = true;
      this.isInterruptTopTip = false;
      this.t1 = this.t2 = 0L;
   }

   public static void Function() {
      UILabel label = (UILabel)MainCanvas.curForm.getComponents().elementAt(16);
      UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);

      int i;
      for(i = 0; i < MainCanvas.playPicture.length; ++i) {
         MainCanvas.playPicture[i] = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 3);
         if (MainCanvas.playPicture[i].isFocus()) {
            MainCanvas.m_bChoose = (byte)i;
         }
      }

      if (MainCanvas.playPicture[MainCanvas.m_bChoose].otherP == null) {
         menuBar.setMenuText("Tạo", 0);

         for(i = 0; i < MainCanvas.label.length; ++i) {
            MainCanvas.label[i] = (UILabel)MainCanvas.curForm.getComponents().elementAt(i + 11);
            MainCanvas.label[i].setText("");
         }
      } else {
         menuBar.setMenuText("Đồng ý", 0);
         MainCanvas.Character((byte)11);
      }

      for(i = 0; i < MainCanvas.playPicture.length; ++i) {
         MainCanvas.playPicture[i] = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 3);
         if (MainCanvas.playPicture[i].otherP != null) {
            if (i == MainCanvas.m_bChoose) {
               MainCanvas.playPicture[i].otherP.setState((byte)1);
            } else {
               MainCanvas.playPicture[i].otherP.setState((byte)0);
            }
         }
      }

      if (MainCanvas.playPicture[MainCanvas.m_bChoose].otherP != null && MainCanvas.m_bDelete[MainCanvas.m_bChoose] == 1) {
         label.setText("Xóa sau 3 ngày");
         label.setPositionX((short)(MainCanvas.screenW - label.getWidth() >> 1));
      } else if (MainCanvas.playPicture[MainCanvas.m_bChoose].otherP != null && MainCanvas.m_bDelete[MainCanvas.m_bChoose] == 2) {
         label.setText("Nhân vật đã xóa không thể phục hồi");
         label.setPositionX((short)(MainCanvas.screenW - label.getWidth() >> 1));
      } else if (MainCanvas.playPicture[MainCanvas.m_bChoose].otherP == null || MainCanvas.playPicture[MainCanvas.m_bChoose].otherP != null && MainCanvas.m_bDelete[MainCanvas.m_bChoose] == 0) {
         if (MainCanvas.m_bAmendNickname[MainCanvas.m_bChoose]) {
            label.setText("Sửa Tên đăng nhập");
            label.setPositionX((short)(MainCanvas.screenW - label.getWidth() >> 1));
         } else {
            label.setText("Xóa nhân vật");
            label.setPositionX((short)(MainCanvas.screenW - label.getWidth() >> 1));
         }
      }

   }

   public final boolean hasFocus() {
      if (this.isFocus()) {
         return true;
      } else {
         for(byte i = 0; i <= 3; ++i) {
            if (this.getAroundComponent(MainCanvas.curForm, i) != null) {
               return true;
            }
         }

         return false;
      }
   }

   public final UIComponent getFinalAroundComponent(UIForm form, byte direct) {
      UIComponent uic = this;

      do {
         uic = uic.getAroundComponent(form, direct);
         if (uic == null) {
            return this;
         }
      } while(!uic.canFocus);

      return uic;
   }

   public boolean isCanFocus() {
      return this.canFocus;
   }

   public void setCanFocus(boolean canFocus) {
      this.canFocus = canFocus;
   }

   public boolean isHaveInnerComponent() {
      return this.haveInnerComponent;
   }

   public void setHaveInnerComponent(boolean haveInnerComponent) {
      this.haveInnerComponent = haveInnerComponent;
   }

   public short getHeight() {
      return this.height;
   }

   public void setHeight(short height) {
      this.height = height;
   }

   public boolean isFocus() {
      return this.isFocus;
   }

   public void setFocus(boolean isFocus, UIForm form) {
      this.isFocus = isFocus;
      if (isFocus && form != null) {
         form.focusWidget = this;
         this.focusAction();
      }

   }

   private final void focusAction() {
      switch(MainCanvas.getState()) {
      case 17:
         Function();
      default:
         if (MainCanvas.curForm != null) {
            switch(MainCanvas.curForm.clientCommand) {
            case 458760:
            case 2555911:
               UIGrid.packageSelectedView();
            }
         }

         especialDeal();
      }
   }

   public short getPositionX() {
      return this.positionX;
   }

   public boolean focusWidgetPointAction() {
      return false;
   }

   public void setPositionX(short positionX) {
      this.positionX = positionX;
   }

   public short getPositionY() {
      return this.positionY;
   }

   public void setPositionY(short positionY) {
      this.positionY = positionY;
   }

   public short getWidth() {
      return this.width;
   }

   public void setWidth(short width) {
      this.width = width;
   }

   public byte getType() {
      return this.type;
   }

   public void setType(byte type) {
      this.type = type;
   }

   public boolean isUserData() {
      return this.isUserData;
   }

   public void setUserData(boolean isUserData) {
      this.isUserData = isUserData;
   }

   public short getUserDataType() {
      return this.userDataType;
   }

   public void setUserDataType(short userDataType) {
      this.userDataType = userDataType;
   }

   public boolean isInverse() {
      return this.inverse;
   }

   public void setInverse(boolean inverse) {
      this.inverse = inverse;
   }

   public UIMenu getMenu() {
      return this.menu;
   }

   public void setMenu(UIMenu menu) {
      this.menu = menu;
   }

   public short getClientType() {
      return this.clientType;
   }

   public void setClientType(short clientType) {
      this.clientType = clientType;
   }

   public void autoAdapt() {
      this.setWidth((short)(this.width * MainCanvas.screenW / 176));
      this.setHeight((short)(this.height * MainCanvas.screenH / 208));
   }

   public UIForm getAttachForm() {
      return this.attachForm;
   }

   public void setAttachForm(UIForm attachForm) {
      this.attachForm = attachForm;
   }

   public void startTopTip() {
      this.t1 = System.currentTimeMillis();
   }

   public boolean haveTopTip() {
      return this.isTopTip;
   }

   public void offerTopTip() {
      this.isTopTip = true;
   }
}

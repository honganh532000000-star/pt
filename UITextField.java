import javax.microedition.lcdui.Graphics;

public class UITextField extends UIComponent {
   public static final short TEXT_OY = 2;
   public static final byte TEXT_STYLE_NORMAL = 0;
   public static final byte TEXT_STYLE_NUMBER = 1;
   public static final byte TEXT_STYLE_PASSWORD = 2;
   public static final byte TEXT_STYLE_CHAR = 3;
   private static final char PSW_CHAR = '*';
   private long lastTime;
   private long intervalTime;
   private int interval;
   private int keyOld;
   private int keyIndex;
   private long maxNumber;
   private long minNumber;
   private byte maskCount;
   private byte style;
   public static final String[] TEXT_STYLE_DESCRIPTOR = new String[]{"Tùy ý", "Chữ số", "Mật khẩu", "Chữ"};
   private short maxWordsNum;
   StringBuffer sb;
   StringBuffer sbMask;
   private String labelText;
   public static String[] chars = new String[]{"0", "1,.+_)(*&^%$#@", "abc2", "def3", "ghi4", "jkl5", "mno6", "pqrs7", "tuv8", "wxyz9"};
   public static String charNum = "0123456789";
   private int cx;
   private boolean isEditable;

   public long getMaxNumber() {
      return this.maxNumber;
   }

   public void setMaxNumber(long maxNumber) {
      this.maxNumber = maxNumber;
   }

   public long getMinNumber() {
      return this.minNumber;
   }

   public void setMinNumber(long minNumber) {
      this.minNumber = minNumber;
   }

   public boolean isEditable() {
      return this.isEditable;
   }

   public void setEditable(boolean isEditable) {
      this.isEditable = isEditable;
   }

   public UITextField(short x, short y, short w, short h, String txt, String labelTxt, byte style, short maxNum, UIForm form) {
      super(x, y, w, h, form);
      this.lastTime = 0L;
      this.intervalTime = 1000L;
      this.interval = 0;
      this.keyOld = -1;
      this.keyIndex = 0;
      this.maxNumber = -1L;
      this.minNumber = 0L;
      this.maskCount = 0;
      this.style = 0;
      this.maxWordsNum = 0;
      this.sb = null;
      this.sbMask = null;
      this.cx = 0;
      this.isEditable = true;
      if (txt != null) {
         this.sb = new StringBuffer(txt);
      } else {
         this.sb = new StringBuffer();
      }

      this.labelText = labelTxt;
      this.style = style;
      this.maxWordsNum = maxNum;
      if (style == 2) {
         this.sbMask = new StringBuffer();
         int i = 0;

         for(int kk = this.sb.length(); i < kk; ++i) {
            this.sbMask.append('*');
         }
      } else if (style == 1) {
         long temp = 0L;

         for(int i = 0; i < this.maxWordsNum; ++i) {
            temp *= 10L;
            ++temp;
         }

         this.maxNumber = temp * 9L;
      }

      if (UIForm.encryptImg == null) {
         MainCanvas.aMidlet.exitMIDlet();
      }

      if (super.height <= 0) {
         super.height = this.getAutoTextFieldHeight();
      }

      super.canFocus = true;
   }

   public UITextField(short x, short y, short w, String txt, byte style, short maxNum, UIForm form) {
      this(x, y, w, (short)0, txt, "", style, maxNum, form);
   }

   public void draw(Graphics g) {
      int strWidth = 0;
      if (this.labelText != null) {
         g.setColor(Cons.COLOR_FONT_1);
         g.drawString(this.labelText, super.positionX, super.positionY + 2, 20);
      }

      short kuangX = super.positionX;
      short kuangW = super.width;
      int textW = 0;
      if (this.labelText != null && !this.labelText.equals("")) {
         textW = Util.getStrLen(this.labelText) + 2;
         kuangX = (short)(kuangX + textW);
         kuangW = (short)(kuangW - textW);
      }

      g.setColor(Cons.COLOR_TEXT_BORDER);
      g.drawRect(kuangX, super.positionY, kuangW - 1, super.height - 1);
      g.setColor(Cons.COLOR_TEXT_BG);
      g.fillRect(kuangX + 1, super.positionY + 1, kuangW - 2, super.height - 2);
      int startX = super.positionX + 2 + textW;
      int len;
      int start;
      String tempStr;
      switch(this.style) {
      case 0:
      case 2:
         g.setColor(Cons.COLOR_TEXT_FONT);
         if (this.style == 2) {
            if (this.maskCount > 0) {
               --this.maskCount;
               if (this.maskCount == 0 && this.sbMask.length() > 0) {
                  this.sbMask.setCharAt(this.sbMask.length() - 1, '*');
               }
            }

            len = this.sbMask.toString().trim().length();
            start = 0;
            tempStr = this.sbMask.toString().trim();

            for(strWidth = MainCanvas.curFont.stringWidth(tempStr); strWidth + 4 > kuangW && start <= len - 1; strWidth = MainCanvas.curFont.stringWidth(tempStr)) {
               ++start;
               tempStr = this.sbMask.toString().substring(start).trim();
            }

            g.drawString(tempStr, startX, super.positionY + 2, 20);
         } else {
            len = this.sb.toString().trim().length();
            start = 0;
            tempStr = this.sb.toString().trim();

            for(strWidth = MainCanvas.curFont.stringWidth(tempStr); strWidth + 4 > kuangW && start <= len - 1; strWidth = MainCanvas.curFont.stringWidth(tempStr)) {
               ++start;
               tempStr = this.sb.toString().substring(start).trim();
            }

            g.drawString(tempStr, startX, super.positionY + 2, 20);
         }
         break;
      case 1:
         g.setColor(Cons.COLOR_TEXT_FONT);
         len = this.sb.toString().trim().length();
         start = 0;
         tempStr = this.sb.toString().trim();

         for(strWidth = MainCanvas.curFont.stringWidth(tempStr); strWidth + 4 > kuangW && start <= len - 1; strWidth = MainCanvas.curFont.stringWidth(tempStr)) {
            ++start;
            tempStr = this.sb.toString().substring(start).trim();
         }

         if (this.style == 1) {
            g.drawString(tempStr, startX, super.positionY + 2, 20);
         }
      }

      if (this.isFocus()) {
         if ((MainCanvas.curForm.clientCommand != 1703937 && MainCanvas.curForm.clientCommand != 1703938 && MainCanvas.curForm.clientCommand != 1703952 || this == (UIComponent)MainCanvas.curForm.getComponents().elementAt(13)) && MainCanvas.curForm.clientCommand != 1703940 && MainCanvas.curForm.clientCommand != 1703941) {
            if (++this.interval > 5) {
               this.interval = 0;
            }

            if (this.interval < 3) {
               this.cx = super.positionX + 4 + strWidth + textW;
               if (this.isEditable) {
                  if (this.style == 1) {
                     g.setColor(Cons.COLOR_TEXT_FONT);
                     g.drawLine(this.cx, super.positionY + 1, this.cx, super.positionY + 12);
                  } else {
                     g.setColor(Cons.COLOR_TEXT_FONT);
                     g.drawLine(this.cx, super.positionY + 2, this.cx, super.positionY + MainCanvas.CHARH);
                  }
               }
            }
         } else {
            Util.drawSelectedKuangEffect(g, kuangX, super.positionY, kuangW, super.height);
         }
      }

   }

   public short getMaxWordsNum() {
      return this.maxWordsNum;
   }

   public void setMaxWordsNum(short maxWordsNum) {
      this.maxWordsNum = maxWordsNum;
      if (this.style == 1) {
         long temp = 0L;

         for(int i = 0; i < maxWordsNum; ++i) {
            temp *= 10L;
            ++temp;
         }

         this.maxNumber = temp * 9L;
      }

   }

   public byte getStyle() {
      return this.style;
   }

   public void setStyle(byte style) {
      this.style = style;
   }

   public StringBuffer getSb() {
      return this.sb;
   }

   public void setSb(StringBuffer sb) {
      this.sb = sb;
      if (this.style == 2) {
         this.sbMask = new StringBuffer();
         int i = 0;

         for(int kk = sb.length(); i < kk; ++i) {
            this.sbMask.append('*');
         }
      }

   }

   public String getLabelText() {
      return this.labelText;
   }

   public void setLabelText(String labelText) {
      this.labelText = labelText;
   }

   public short getAutoTextFieldHeight() {
      return (short)(MainCanvas.CHARH + 4);
   }

   public void setFocus(boolean flag, UIForm form) {
      super.setFocus(flag, form);
      this.interval = 0;
      this.keyOld = 0;
      this.keyIndex = 0;
   }

   public void pushKey(int keyValue) {
      for(int i = 0; i < 10; ++i) {
         if (1 << i == keyValue) {
            if (this.style != 0 && this.style != 2) {
               this.addChar(charNum.charAt(i));
            } else {
               if (this.keyOld == keyValue && System.currentTimeMillis() - this.lastTime <= this.intervalTime) {
                  if (this.sb.length() > 0) {
                     this.sb.deleteCharAt(this.sb.length() - 1);
                  }

                  if (++this.keyIndex >= chars[i].length()) {
                     this.keyIndex = 0;
                  }

                  this.addChar(chars[i].charAt(this.keyIndex));
               } else {
                  this.keyIndex = 0;
                  if (this.sb.length() < this.maxWordsNum) {
                     this.addChar(chars[i].charAt(this.keyIndex));
                     this.keyOld = keyValue;
                  } else {
                     this.keyOld = 0;
                  }
               }

               this.lastTime = System.currentTimeMillis();
            }
            break;
         }
      }

   }

   private void addChar(char c) {
      if (this.sb.length() < this.maxWordsNum) {
         if (this.style == 1 && this.sb.length() == 1 && this.sb.toString().equals("0")) {
            this.sb.deleteCharAt(0);
         }

         this.sb.append(c);
         if (this.style == 2) {
            this.sbMask.delete(0, this.sbMask.length());
            int num = this.sb.length();

            for(int i = 0; i < num - 1; ++i) {
               this.sbMask.append('*');
            }

            if (num > 0) {
               this.sbMask.append(this.sb.charAt(this.sb.length() - 1));
            }

            this.maskCount = 10;
         } else if (this.style == 1) {
            long temp = Long.parseLong(this.sb.toString());
            if (this.maxNumber >= 0L) {
               try {
                  temp = Long.parseLong(this.sb.toString());
                  if (temp > this.maxNumber) {
                     this.setLabel(Long.toString(this.maxNumber));
                  } else {
                     this.setLabel(Long.toString(temp));
                  }
               } catch (Exception var5) {
               }
            }

            if (temp < this.minNumber) {
               this.setLabel(String.valueOf(this.minNumber));
            }
         }
      }

   }

   public void removeChar() {
      if (this.sb.length() > 0) {
         this.sb.deleteCharAt(this.sb.length() - 1);
         if (this.style == 2) {
            this.sbMask.deleteCharAt(this.sbMask.length() - 1);
         } else if (this.style == 1 && (this.sb.length() == 0 || (long)this.getNumber() < this.minNumber)) {
            this.setLabel(String.valueOf(this.minNumber));
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

   public void clear() {
      this.sb.delete(0, this.sb.length());
      if (this.style == 2) {
         this.sbMask.delete(0, this.sbMask.length());
      }

   }

   public void setLabel(String s) {
      if (s != null && s.length() != 0) {
         if (this.style == 1) {
            int i = 0;

            for(int kk = s.length(); i < kk; ++i) {
               if (!Character.isDigit(s.charAt(i))) {
                  return;
               }
            }
         }

         this.sb.delete(0, this.sb.length());
         this.sb.append(s);
      } else {
         this.clear();
      }

   }

   public void keyInAction() {
      if (MainCanvas.curForm.clientCommand != 1376260 && MainCanvas.curForm.clientCommand != 1376265 && MainCanvas.curForm.clientCommand != 1376268 && MainCanvas.curForm.clientCommand != 1376267 && MainCanvas.curForm.clientCommand != 1376261 || !UIPicture.isMyTradeLock) {
         int cmd = 0;

         int num;
         String sb;
         UITextField new_password;
         for(int kk = MainCanvas.NUM_KEYS.length; cmd < kk; ++cmd) {
            if (this.isEditable && MainCanvas.getState() != 18) {
               if (!MainCanvas.isInputDown(MainCanvas.NUM_KEYS[cmd])) {
                  if (MainCanvas.isInputDown(1024) || MainCanvas.isInputDown(1048576)) {
                     if (MainCanvas.curForm.clientCommand == 2097155) {
                        PCGamble.ExpenditureCount((byte)2);
                     }

                     this.removeChar();
                     if (MainCanvas.curForm.clientCommand == 2097155) {
                        PCGamble.ExpenditureCount((byte)1);
                     }

                     MainCanvas.clearCurInput();
                  }
               } else {
                  UITextField userNameText;
                  if (MainCanvas.curForm.clientCommand != 1703937 && MainCanvas.curForm.clientCommand != 1703952 && MainCanvas.curForm.clientCommand != 1703938) {
                     UILabel label;
                     if (MainCanvas.curForm.clientCommand == 2097155) {
                        label = (UILabel)MainCanvas.curForm.getComponents().elementAt(37);
                        if (Integer.parseInt(label.getText()) <= 0) {
                           UITopForm.createLocalTopForm((byte)0, (String)"Thiếu KNB", "Xác nhận", "", -1, -2);
                           return;
                        }

                        PCGamble.ExpenditureCount((byte)2);
                     } else if (MainCanvas.curForm.clientCommand != 1900547 && MainCanvas.curForm.clientCommand != 2424843) {
                        if (MainCanvas.curForm.clientCommand == 2424836) {
                           num = Integer.parseInt(this.getSb().toString().trim());
                           if (num >= 9) {
                              this.setSb(new StringBuffer("9"));
                              return;
                           }
                        }
                     } else if (this.isFocus() && this.getSb().toString().trim().length() == 8) {
                        this.setSb(new StringBuffer("99999999"));
                        return;
                     }

                     this.pushKey(MainCanvas.NUM_KEYS[cmd]);
                     if (MainCanvas.curForm.clientCommand == 1376260) {
                        UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                        menuBar.setMenuText("Sửa đổi", 0);
                     } else if (MainCanvas.curForm.clientCommand == 2097155) {
                        label = (UILabel)MainCanvas.curForm.getComponents().elementAt(37);
                        if (Integer.parseInt(this.getSb().toString().trim()) > Integer.parseInt(label.getText())) {
                           this.setSb(new StringBuffer(label.getText()));
                        }

                        PCGamble.ExpenditureCount((byte)1);
                     } else {
                        UITextField pswText;
                        UITitle menuBar;
                        if (MainCanvas.getState() == 11) {
                           userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
                           pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(5);
                           menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                           if (userNameText.getSb().toString().trim() != null && !userNameText.getSb().toString().trim().equals("") && pswText.getSb().toString().trim() != null && !pswText.getSb().toString().trim().equals("")) {
                              menuBar.setMenuText("Đăng nhập ", 0);
                           }
                        } else {
                           UITitle menuBar;
                           if (MainCanvas.curForm.clientCommand == 2297358) {
                              userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
                              menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                              if (userNameText.getSb().toString().trim() != null && !userNameText.getSb().toString().trim().equals("")) {
                                 menuBar.setMenuText("Xác nhận", 0);
                              }
                           } else if (MainCanvas.getState() == 19) {
                              userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(9);
                              menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                              if (userNameText.getSb().toString().trim() != null && !userNameText.getSb().toString().trim().equals("")) {
                                 menuBar.setMenuText("Xác nhận", 0);
                              }
                           } else if (MainCanvas.getState() == 14) {
                              userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(9);
                              pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(10);
                              menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                              if (userNameText.getSb().toString().trim() != null && !userNameText.getSb().toString().trim().equals("") && pswText.getSb().toString().trim() != null && !pswText.getSb().toString().trim().equals("")) {
                                 menuBar.setMenuText("Xác nhận", 0);
                              }
                           } else if (MainCanvas.getState() == 15) {
                              userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(10);
                              pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(11);
                              new_password = (UITextField)MainCanvas.curForm.getComponents().elementAt(12);
                              UITextField validate = (UITextField)MainCanvas.curForm.getComponents().elementAt(13);
                              UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                              if (userNameText.getSb().toString().trim() != null && !userNameText.getSb().toString().trim().equals("") && pswText.getSb().toString().trim() != null && !pswText.getSb().toString().trim().equals("") && validate.getSb().toString().trim() != null && !validate.getSb().toString().trim().equals("") && new_password.getSb().toString().trim() != null && !new_password.getSb().toString().trim().equals("")) {
                                 menuBar.setMenuText("Xác nhận", 0);
                              }
                           } else if (MainCanvas.curForm.clientCommand == 2424836) {
                              num = Integer.parseInt(this.getSb().toString().trim());
                              if (num >= 9) {
                                 this.setSb(new StringBuffer("9"));
                                 return;
                              }
                           } else if (MainCanvas.curForm.clientCommand == -1610612638) {
                              userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(10);
                              pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(11);
                              new_password = (UITextField)MainCanvas.curForm.getComponents().elementAt(12);
                              UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                              if (userNameText.getSb().toString().trim() != null && !userNameText.getSb().toString().trim().equals("") && pswText.getSb().toString().trim() != null && !pswText.getSb().toString().trim().equals("") && pswText.getSb().toString().trim() != null && !pswText.getSb().toString().trim().equals("") && new_password.getSb().toString().trim() != null && !new_password.getSb().toString().trim().equals("")) {
                                 menuBar.setMenuText("Xác nhận", 0);
                              }
                           } else if (MainCanvas.curForm.clientCommand == 1638427) {
                              sb = this.getSb().toString();
                              UILabel medal = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
                              int clanMedal = medal.getNum1();
                              if (sb == null || sb.equals("")) {
                                 sb = "0";
                              }

                              int num = Integer.parseInt(this.getSb().toString().trim());
                              if (num > clanMedal) {
                                 this.setSb(new StringBuffer(String.valueOf(clanMedal)));
                              } else {
                                 this.setSb(new StringBuffer(sb));
                              }
                           }
                        }
                     }
                  } else if (this.style == 1 && this.isFocus()) {
                     if (MainCanvas.curForm.clientCommand == 1703937) {
                        if (PCMail.m_nBackpackMoney <= 0) {
                           UITopForm.createLocalTopForm((byte)0, (String)"Vàng không đủ!", "Xác nhận", "", -1, -2);
                           return;
                        }

                        if (PCMail.m_nBackpackMoney - PCMail.m_nPostage <= 10) {
                           UITopForm.createLocalTopForm((byte)0, (String)"Vàng không đủ để trả Bưu phí!", "Xác nhận", "", -1, -2);
                           return;
                        }
                     } else if (MainCanvas.curForm.clientCommand == 1703938 && this.isFocus() && this.getSb().toString().trim().length() == 8) {
                        this.setSb(new StringBuffer("99999999"));
                        return;
                     }

                     this.pushKey(MainCanvas.NUM_KEYS[cmd]);
                     if (MainCanvas.curForm.clientCommand == 1703937 || MainCanvas.curForm.clientCommand == 1703952) {
                        userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(13);
                        if (!userNameText.getSb().toString().trim().equals("0")) {
                           PCMail.money = true;
                           PCMail.Postage();
                        }
                     }
                  }
               }
            }
         }

         UITextField text;
         UITextField textfield2;
         if (super.isShowMenu) {
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
               if (MainCanvas.curForm.clientCommand != 1703937 && MainCanvas.curForm.clientCommand != 1703952 && MainCanvas.curForm.clientCommand != 1703938) {
                  if (MainCanvas.curForm.clientCommand != 1703940 && MainCanvas.curForm.clientCommand != 1703941) {
                     switch(MainCanvas.curForm.clientCommand) {
                     case 1638401:
                        cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                        PCClan.createClanKeyInMenu(cmd);
                        super.isShowMenu = false;
                        break;
                     case 1835009:
                        cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                        PCKing.createKingKeyInMenu(cmd);
                        super.isShowMenu = false;
                        break;
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
                  } else {
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
                  text = (UITextField)MainCanvas.curForm.getComponents().elementAt(3);
                  textfield2 = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
                  if (!text.isFocus() || MainCanvas.curForm.clientCommand != 1703937 && MainCanvas.curForm.clientCommand != 1703938) {
                     if (text.isFocus() && MainCanvas.curForm.clientCommand == 1703952) {
                        num = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                        switch(num) {
                        case -1610612711:
                           PCMail.SendMailValidity();
                        case -1610612710:
                        default:
                           break;
                        case -1610612709:
                           PCMail.CompilationMail();
                        }
                     } else if (textfield2.isFocus()) {
                        num = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                        switch(num) {
                        case -1610612711:
                           PCMail.SendMailValidity();
                        case -1610612710:
                        default:
                           break;
                        case -1610612709:
                           PCMail.CompilationMail();
                        }
                     } else if (this.style == 1 && this.isFocus()) {
                        num = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                        switch(num) {
                        case -1610612711:
                           PCMail.SendMailValidity();
                        case -1610612710:
                        default:
                           break;
                        case -1610612709:
                           PCMail.CompilationMail();
                        }
                     }
                  } else {
                     num = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                     switch(num) {
                     case -1610612711:
                        PCMail.SendMailValidity();
                        break;
                     case -1610612710:
                     default:
                        PCMail.m_bSymbol = 1;
                        MainCanvas.ni.send(num);
                        break;
                     case -1610612709:
                        PCMail.CompilationMail();
                     }
                  }

                  super.isShowMenu = false;
               }
            }
         } else if (MainCanvas.isInputDown(65536) || MainCanvas.isInputDown(131072)) {
            if (MainCanvas.curForm.clientCommand != 1703937 && MainCanvas.curForm.clientCommand != 1703952 && MainCanvas.curForm.clientCommand != 1703938) {
               UIMenu menu;
               if (MainCanvas.curForm.clientCommand != 1703940 && MainCanvas.curForm.clientCommand != 1703941) {
                  String pass;
                  PassPort pp;
                  switch(MainCanvas.curForm.clientCommand) {
                  case -1610612697:
                     if (Integer.parseInt(this.getSb().toString().trim()) == 0) {
                        MainCanvas.TopForm((byte)0, "Xin nhập số Vàng", "Xác nhận", "", -1, -120);
                     } else {
                        MainCanvas.ni.send(2425009);
                     }
                     break;
                  case 1376260:
                     Util.Touch("Xin nhập tiền", "Xin nhập số vàng", 8, this.getSb().toString().trim(), 27, (byte)6);
                     break;
                  case 1638401:
                     this.setMenu(PCClan.createClanKeyInAction());
                     super.isShowMenu = true;
                     break;
                  case 1638427:
                     text = (UITextField)MainCanvas.curForm.getComponents().elementAt(6);
                     PCClan.payMedal = text.getNumber();
                     MainCanvas.ni.send(1638429);
                     break;
                  case 1638445:
                     if (this.getSb().toString().trim() != null && !this.getSb().toString().trim().equals("")) {
                        PCClan.clanName_gai = this.getSb().toString().trim();
                        MainCanvas.ni.send(1638446);
                     } else if (this.getSb().toString().trim() == null || this.getSb().toString().trim().equals("")) {
                        Util.Touch("Đổi tên Thị Tộc", "Xin nhập tên", 6, this.getSb().toString().trim(), 6, (byte)3);
                     }
                     break;
                  case 1703959:
                     text = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
                     textfield2 = (UITextField)MainCanvas.curForm.getComponents().elementAt(6);
                     if (text.getSb().toString().trim() != null && !text.getSb().toString().trim().equals("") && textfield2.getSb().toString().trim() != null && !textfield2.getSb().toString().trim().equals("")) {
                        if (text.getSb().length() >= 6 && text.getSb().length() <= 16 && textfield2.getSb().length() >= 6 && textfield2.getSb().length() <= 16) {
                           sb = MainCanvas.name_password[0];
                           pass = MainCanvas.name_password[1];
                           MainCanvas.name_password[0] = text.getSb().toString().trim();
                           MainCanvas.name_password[1] = textfield2.getSb().toString().trim();
                           PCIncrement.m_bNote = 2;
                           new_password = null;
                           pp = new PassPort(PassPort.KONG_PASSPORT_IP, PassPort.getURL((byte)1), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)0);
                           PassPort.isFirstConnect = true;
                           pp.authenticate((byte)4);
                           MainCanvas.TopForm((byte)0, "Xin chờ...", "", "", -120, -120);
                           MainCanvas.name_password[0] = sb;
                           MainCanvas.name_password[1] = pass;
                           break;
                        }

                        MainCanvas.TopForm((byte)0, "Xin nhập tên và Mật khẩu", "Xác nhận", "", -1, -120);
                        return;
                     }

                     Util.TouchBing(4, 6);
                     return;
                  case 1703960:
                     text = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
                     textfield2 = (UITextField)MainCanvas.curForm.getComponents().elementAt(6);
                     if (text.getSb().toString().trim() != null && !text.getSb().toString().trim().equals("") && textfield2.getSb().toString().trim() != null && !textfield2.getSb().toString().trim().equals("")) {
                        if (text.getSb().length() >= 6 && text.getSb().length() <= 16 && textfield2.getSb().length() >= 6 && textfield2.getSb().length() <= 16) {
                           sb = MainCanvas.name_password[0];
                           pass = MainCanvas.name_password[1];
                           MainCanvas.name_password[0] = text.getSb().toString().trim();
                           MainCanvas.name_password[1] = textfield2.getSb().toString().trim();
                           PCIncrement.m_bNote = 3;
                           new_password = null;
                           pp = new PassPort(PassPort.KONG_PASSPORT_IP, PassPort.getURL((byte)1), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)0);
                           PassPort.isFirstConnect = true;
                           pp.authenticate((byte)4);
                           MainCanvas.TopForm((byte)0, "Xin chờ...", "", "", -120, -120);
                           MainCanvas.name_password[0] = sb;
                           MainCanvas.name_password[1] = pass;
                           break;
                        }

                        MainCanvas.TopForm((byte)0, "Xin nhập tên và Mật khẩu chính xác", "Xác nhận", "", -1, -120);
                        return;
                     }

                     Util.TouchBing(4, 6);
                     return;
                  case 1703961:
                     text = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
                     if (text.getSb().toString().trim() != null && !text.getSb().toString().trim().equals("")) {
                        if (text.getSb().length() >= 6 && text.getSb().length() <= 16) {
                           String name = MainCanvas.name_password[0];
                           MainCanvas.name_password[0] = text.getSb().toString().trim();
                           MainCanvas.TopForm((byte)0, "Xin chờ...", "", "", -120, -120);
                           PCIncrement.SendNote((byte)4);
                           MainCanvas.name_password[0] = name;
                           break;
                        }

                        MainCanvas.TopForm((byte)0, "Xin nhập tên tài khoản chính xác", "Xác nhận", "", -1, -120);
                        return;
                     }

                     Util.Touch("Xin nhập tên tài khoản", "Xin nhập tên tài khoản", 16, text.getSb().toString().trim(), 4, (byte)1);
                     return;
                  case 1835009:
                     this.setMenu(PCKing.createKingKeyInAction());
                     super.isShowMenu = true;
                     break;
                  case 1900547:
                     if (PCAuction.m_bEntityGoods == 1) {
                        menu = new UIMenu((byte)0);
                        int[] cmds = new int[]{-1610612713, -1610612714};
                        int[] inns = new int[]{3, 3};
                        menu.addMenu(Cons.AUCTION_SALE_SUBMENU, cmds, inns);
                        this.setMenu(menu);
                        this.getMenu().resetIndex();
                        super.isShowMenu = true;
                     }
                     break;
                  case 2424836:
                     PCIncrement.sum = Integer.parseInt(this.getSb().toString());
                     if (this.style == 1 && this.isFocus()) {
                        if (PCIncrement.sum == 0) {
                           UITopForm.createLocalTopForm((byte)0, (String)"Xin nhập số thư muốn gửi", "Xác nhận", "", -1, -2);
                        } else {
                           UITopForm.createLocalTopForm((byte)0, (String)("Các hạ xác nhận muốn tiêu hao" + PCIncrement.sum * 2 + "Đồng thu mua " + PCIncrement.sum * 20 + "Mãnh mã tệ？?"), "Xác nhận", "Hủy", -23, -1);
                        }
                     }
                     break;
                  case 2424843:
                     if (this.getSb().toString().trim().equals("0")) {
                        UITopForm.createLocalTopForm((byte)0, (String)"Xin nhập số Vàng đổi", "Xác nhận", "", -1, -2);
                     } else {
                        PCIncrement.m_nMoneyChange = Integer.parseInt(this.getSb().toString().trim());
                        MainCanvas.ni.send(2424996);
                     }
                  }
               } else if (MainCanvas.curForm.clientCommand == 1703940) {
                  menu = new UIMenu((byte)0);
                  if (PCMail.receiveMoney > 0) {
                     menu.addMenu("Nhận tiền", 1703951, 3);
                  }

                  if (PCMail.haveAttach || PCMail.receiveMoney > 0) {
                     menu.addMenu("Nhận hết ", 1703950, 3);
                  }

                  if (PCMail.canReturnMail) {
                     menu.addMenu("Hồi thư", 1703952, 2);
                  }

                  if (menu.getItems().size() > 0) {
                     this.setMenu(menu);
                     this.getMenu().resetIndex();
                     super.isShowMenu = true;
                  }
               } else if (MainCanvas.curForm.clientCommand == 1703941) {
                  menu = new UIMenu((byte)0);
                  if (!PCMail.isPay) {
                     menu.addMenu("Trả tiền", 1703953, 3);
                     menu.addMenu("Trả thư", 1703957, 0);
                     if (PCMail.canReturnMail) {
                        menu.addMenu("Hồi thư", 1703952, 2);
                     }
                  } else {
                     if (PCMail.haveAttach || PCMail.receiveMoney > 0) {
                        menu.addMenu("Nhận hết ", 1703950, 3);
                     }

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
            } else {
               text = (UITextField)MainCanvas.curForm.getComponents().elementAt(3);
               textfield2 = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
               int[] cmds;
               int[] inns;
               UIMenu menu;
               if (text.isFocus() && (MainCanvas.curForm.clientCommand == 1703937 || MainCanvas.curForm.clientCommand == 1703938)) {
                  menu = new UIMenu((byte)0);
                  cmds = new int[]{1703945, -1610612709, -1610612711};
                  inns = new int[]{2, 3, 3};
                  menu.addMenu(Cons.ADDRESSEE, cmds, inns);
                  this.setMenu(menu);
                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               } else if (text.isFocus() && MainCanvas.curForm.clientCommand == 1703952) {
                  menu = new UIMenu((byte)0);
                  cmds = new int[]{-1610612709, -1610612711};
                  inns = new int[]{3, 3};
                  menu.addMenu(Cons.SUBJECT, cmds, inns);
                  this.setMenu(menu);
                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               } else if (textfield2.isFocus()) {
                  menu = new UIMenu((byte)0);
                  cmds = new int[]{-1610612709, -1610612711};
                  inns = new int[]{3, 3};
                  menu.addMenu(Cons.SUBJECT, cmds, inns);
                  this.setMenu(menu);
                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               } else if (this.style == 1 && this.isFocus()) {
                  menu = new UIMenu((byte)0);
                  cmds = new int[]{-1610612709, -1610612711};
                  inns = new int[]{3, 3};
                  menu.addMenu(Cons.SUBJECT, cmds, inns);
                  this.setMenu(menu);
                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               }
            }
         }

      }
   }

   public byte getAroundComponentIndex(byte direction) {
      UITitle menuBar;
      if (direction == 2 && MainCanvas.curForm.clientCommand != 2097155 && MainCanvas.getState() != 18) {
         if ((MainCanvas.curForm.clientCommand == 1376260 || MainCanvas.curForm.clientCommand == 1376265 || MainCanvas.curForm.clientCommand == 1376268 || MainCanvas.curForm.clientCommand == 1376267 || MainCanvas.curForm.clientCommand == 1376261) && UIPicture.isMyTradeLock) {
            return -1;
         } else {
            if (this.isEditable) {
               this.removeChar();
               if ((MainCanvas.curForm.clientCommand == 1703937 || MainCanvas.curForm.clientCommand == 1703952) && this.getNumber() == 0) {
                  PCMail.money = false;
                  PCMail.Postage();
               } else if (MainCanvas.getState() == 11 || MainCanvas.getState() == 14 || MainCanvas.getState() == 15 || MainCanvas.getState() == 21 || MainCanvas.getState() == 22 || MainCanvas.getState() == 23 || MainCanvas.getState() == 18 || MainCanvas.getState() == 19 || MainCanvas.curForm.clientCommand == 2297615 || MainCanvas.curForm.clientCommand == -1610612638 || MainCanvas.curForm.clientCommand == 1638445) {
                  menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                  if (this.sb.length() == 0) {
                     menuBar.setMenuText("Nhập vào", 0);
                  }
               }
            }

            return -1;
         }
      } else {
         if (MainCanvas.curForm.clientCommand == 2097155) {
            menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
            menuBar.setMenuText("", 0);
         }

         return super.getAroundComponentIndex(direction);
      }
   }

   public boolean focusWidgetPointAction() {
      boolean isAction = false;
      if (MainCanvas.SUPPORT_POINTER && MainCanvas.isPointInRect(super.positionX, super.positionY, super.width, super.height)) {
         switch(MainCanvas.getState()) {
         case 11:
            Util.Touch_Login(4, 5);
            isAction = true;
         case 12:
         case 13:
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
         default:
            break;
         case 14:
            this.touchEnrollAct();
            isAction = true;
            break;
         case 15:
            MainCanvas.m_sTemporaryStr[0] = null;
            MainCanvas.m_sTemporaryStr[1] = null;
            MainCanvas.m_sTemporaryStr[2] = null;
            MainCanvas.m_sTemporaryStr[3] = null;
            Util.Touch_AmendPwd();
            isAction = true;
            break;
         case 21:
         case 22:
            Util.Touch_Login(4, 6);
            isAction = true;
            break;
         case 23:
            Util.Touch("Xin nhập tên tài khoản", "Xin nhập tên tài khoản", 16, this.getSb().toString().trim(), 4, (byte)1);
            isAction = true;
         }

         switch(MainCanvas.curForm.clientCommand) {
         case -2145124351:
         default:
            break;
         case -1610612638:
            this.touchEnrollAct();
            isAction = true;
            break;
         case 1638427:
            Util.TouchDefault(new int[]{6});
            isAction = true;
            break;
         case 1703937:
         case 1703938:
            UITextField tf = (UITextField)MainCanvas.curForm.getComponents().elementAt(13);
            if (tf.getPositionX() == super.positionX && tf.getPositionY() == super.positionY) {
               Util.Touch_MailMoney();
               isAction = true;
            }
            break;
         case 1703959:
         case 1703960:
            Util.TouchBing(4, 6);
            isAction = true;
            break;
         case 1703961:
            Util.Touch("Xin nhập tên tài khoản", "Xin nhập tên tài khoản", 16, this.getSb().toString().trim(), 4, (byte)1);
            isAction = true;
            break;
         case 1900547:
            Util.Touch_Auction();
            isAction = true;
            break;
         case 2424836:
            Util.Touch_Incremece();
            isAction = true;
            break;
         case 2621443:
         case 2621445:
            Util.Touch_Charge_Normal(4, 6, "Xin nhập vào thẻ nạp và Mật khẩu", "Xin nhập vào mã số thẻ nạp", "Xin nhập vào mật khẩu thẻ nạp");
            isAction = true;
            break;
         case 2621447:
            Util.Touch_Charge_Normal(5, 7, "Xin nhập vào", "Thẻ thông hành muốn nạp", "Xác nhận thẻ thông hành");
            isAction = true;
         }
      }

      return isAction;
   }

   public void touchEnrollAct() {
      MainCanvas.password_validate = null;
      MainCanvas.name_password[0] = null;
      MainCanvas.name_password[1] = null;
      UITextField userNameText = (UITextField)MainCanvas.curForm.getComponents().elementAt(9);
      MainCanvas.name_password[0] = userNameText.getSb().toString().trim();
      UITextField pswText = (UITextField)MainCanvas.curForm.getComponents().elementAt(10);
      MainCanvas.name_password[1] = pswText.getSb().toString().trim();
      Util.Touch_Enroll();
   }
}

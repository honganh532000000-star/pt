import java.util.Vector;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public class UIDummyWidget extends UIComponent {
   private Vector curFormWidgets;
   private short curRowIndex;
   private static final int LINE_H = 16;
   public static short PLAYER_INFOMATION_MOVE_ROW = 8;
   public Form infoForm;
   private String infoName;
   private Command commandSave;
   private Command commandCancel;
   private ChoiceGroup popedomGroup;
   private String[] popedomGroupItems;
   private TextField nickNameTF;
   private ChoiceGroup sexGroup;
   private String[] sexGroupItems;
   private TextField ageTF;
   private TextField bloodTypeTF;
   private TextField animalYearTF;
   private TextField constellationTF;
   private TextField cityTF;
   private TextField workTF;
   private TextField personalShowTF;
   public static final String[] INFO_STR = new String[]{"Những tư liệu sau", "Tên đăng nhập", "Giới tính", "Tuổi", "Nhóm máu", "Năm sinh", "Cung hoàng đạo", "Thành phố", "Nghề nghiệp", "Giới thiệu bản thân"};
   public String[] saveStr;
   public static Vector saveInfoFormContent = new Vector();

   public UIDummyWidget(short x, short y, short w, short h, UIForm form) {
      super(x, y, w, h, form);
      this.curFormWidgets = null;
      this.curRowIndex = 0;
      this.infoForm = null;
      this.infoName = "Tư liệu";
      this.commandSave = null;
      this.commandCancel = null;
      this.popedomGroup = null;
      this.popedomGroupItems = new String[]{"Cho phép hảo hữu", "Công khai hoàn toàn", "Bảo mật hoàn toàn"};
      this.nickNameTF = null;
      this.sexGroup = null;
      this.sexGroupItems = new String[]{"Nữ", "Nam"};
      this.ageTF = null;
      this.bloodTypeTF = null;
      this.animalYearTF = null;
      this.constellationTF = null;
      this.cityTF = null;
      this.workTF = null;
      this.personalShowTF = null;
      this.saveStr = new String[INFO_STR.length];
   }

   public UIDummyWidget(int type, UIForm form) {
      this((short)0, (short)0, (short)0, (short)0, form);
      switch(type) {
      case 393218:
      case 393225:
      case 393229:
      case 1245185:
         super.canFocus = true;
         this.setFocus(true, form);
      default:
         this.removeAllWidgets();
      }
   }

   private void touchAction() {
      if (MainCanvas.SUPPORT_POINTER && MainCanvas.isInputDown(268435456) && !super.isShowMenu) {
         int i = 0;

         for(int ii = this.curFormWidgets.size(); i < ii; ++i) {
            UIComponent uic = (UIComponent)this.curFormWidgets.elementAt(i);
            if (uic instanceof UIScroll) {
               ((UIScroll)uic).focusWidgetPointAction();
               break;
            }
         }
      }

   }

   public void keyDummyWidget() {
      this.touchAction();
      int i;
      int i;
      UIComponent uic;
      UIComponent uic;
      int ii;
      switch(MainCanvas.curForm.clientCommand) {
      case 393218:
         if (MainCanvas.isInputDownOrHold(4096)) {
            --this.curRowIndex;
            if (this.curRowIndex < 0) {
               this.curRowIndex = 0;
               return;
            }

            if (this.curFormWidgets != null) {
               i = 0;

               for(i = this.curFormWidgets.size(); i < i; ++i) {
                  uic = (UIComponent)this.curFormWidgets.elementAt(i);
                  if (uic instanceof UIScroll) {
                     ((UIScroll)uic).setShowStartRow(this.curRowIndex);
                     ((UIScroll)uic).keyInScroll(this.curRowIndex, false);
                  } else {
                     uic.positionY = (short)(uic.positionY + 16 * MainCanvas.screenH / 208);
                  }
               }
            }
         } else if (MainCanvas.isInputDownOrHold(8192)) {
            ++this.curRowIndex;
            i = 0;
            if (this.curFormWidgets != null) {
               i = 0;

               for(ii = this.curFormWidgets.size(); i < ii; ++i) {
                  uic = (UIComponent)this.curFormWidgets.elementAt(i);
                  if (uic instanceof UIScroll) {
                     i = ((UIScroll)uic).allRowNum - ((UIScroll)uic).canShowRowNum;
                  }
               }
            }

            if (this.curRowIndex > i) {
               this.curRowIndex = (short)i;
               return;
            }

            if (this.curFormWidgets != null) {
               i = 0;

               for(ii = this.curFormWidgets.size(); i < ii; ++i) {
                  uic = (UIComponent)this.curFormWidgets.elementAt(i);
                  if (uic instanceof UIScroll) {
                     ((UIScroll)uic).setShowStartRow(this.curRowIndex);
                     ((UIScroll)uic).keyInScroll(this.curRowIndex, false);
                  } else {
                     uic.positionY = (short)(uic.positionY - 16 * MainCanvas.screenH / 208);
                  }
               }
            }
         }
         break;
      case 393225:
      case 393229:
         if (super.isShowMenu) {
            if (MainCanvas.isInputDownOrHold(4096)) {
               this.getMenu().decreaseIndex();
            } else if (MainCanvas.isInputDownOrHold(8192)) {
               this.getMenu().increaseIndex();
            } else if (!MainCanvas.isInputDown(65536) && !MainCanvas.isInputDown(131072)) {
               if (MainCanvas.isInputDown(262144)) {
                  super.isShowMenu = false;
               }
            } else if (MainCanvas.curForm.clientCommand == 393225) {
               this.getMenu().saveForm();
               switch(this.getMenu().getIndex()) {
               case 0:
                  this.newInfoForm();
               default:
                  super.isShowMenu = false;
               }
            }
         } else if (MainCanvas.isInputDownOrHold(4096)) {
            if (this.curRowIndex <= 0) {
               this.curRowIndex = 0;
               return;
            }

            --this.curRowIndex;
            if (this.curFormWidgets != null) {
               i = 0;

               for(i = this.curFormWidgets.size(); i < i; ++i) {
                  uic = (UIComponent)this.curFormWidgets.elementAt(i);
                  if (uic instanceof UIScroll) {
                     ((UIScroll)uic).setShowStartRow(this.curRowIndex);
                     ((UIScroll)uic).keyInScroll(this.curRowIndex, false);
                  } else {
                     uic.positionY = (short)(uic.positionY + 16);
                  }
               }
            }
         } else if (MainCanvas.isInputDownOrHold(8192)) {
            if (this.curRowIndex >= PLAYER_INFOMATION_MOVE_ROW) {
               this.curRowIndex = PLAYER_INFOMATION_MOVE_ROW;
               return;
            }

            ++this.curRowIndex;
            if (this.curFormWidgets != null) {
               i = 0;

               for(i = this.curFormWidgets.size(); i < i; ++i) {
                  uic = (UIComponent)this.curFormWidgets.elementAt(i);
                  if (uic instanceof UIScroll) {
                     ((UIScroll)uic).setShowStartRow(this.curRowIndex);
                     ((UIScroll)uic).keyInScroll(this.curRowIndex, false);
                  } else {
                     uic.positionY = (short)(uic.positionY - 16);
                  }
               }
            }
         } else if (MainCanvas.isInputDown(65536) || MainCanvas.isInputDown(131072)) {
            this.addPlayerInfomationMenu();
         }
         break;
      case 1245185:
         if (MainCanvas.isInputDownOrHold(4096)) {
            --this.curRowIndex;
            if (this.curRowIndex < 0) {
               this.curRowIndex = 0;
               return;
            }

            if (this.curFormWidgets != null) {
               i = 0;

               for(i = this.curFormWidgets.size(); i < i; ++i) {
                  uic = (UIComponent)this.curFormWidgets.elementAt(i);
                  if (uic instanceof UIScroll) {
                     ((UIScroll)uic).setShowStartRow(this.curRowIndex);
                     ((UIScroll)uic).keyInScroll(this.curRowIndex, false);
                  } else if (uic instanceof UILabel) {
                     uic.positionY = (short)(uic.positionY + MainCanvas.CHARH);
                  }
               }
            }
         } else if (MainCanvas.isInputDownOrHold(8192)) {
            ++this.curRowIndex;
            i = 0;
            if (this.curFormWidgets != null) {
               i = 0;

               for(ii = this.curFormWidgets.size(); i < ii; ++i) {
                  uic = (UIComponent)this.curFormWidgets.elementAt(i);
                  if (uic instanceof UIScroll) {
                     i = ((UIScroll)uic).allRowNum - ((UIScroll)uic).canShowRowNum;
                  }
               }
            }

            if (this.curRowIndex > i) {
               this.curRowIndex = (short)i;
               return;
            }

            if (this.curFormWidgets != null) {
               i = 0;

               for(ii = this.curFormWidgets.size(); i < ii; ++i) {
                  uic = (UIComponent)this.curFormWidgets.elementAt(i);
                  if (uic instanceof UIScroll) {
                     ((UIScroll)uic).setShowStartRow(this.curRowIndex);
                     ((UIScroll)uic).keyInScroll(this.curRowIndex, false);
                  } else if (uic instanceof UILabel) {
                     uic.positionY = (short)(uic.positionY - MainCanvas.CHARH);
                  }
               }
            }
         }
      }

   }

   private void addPlayerInfomationMenu() {
      UIMenu menu = new UIMenu((byte)0);
      if (MainCanvas.curForm.clientCommand == 393225) {
         int[] menuCommand = new int[]{1};
         int[] inns = new int[]{3};
         menu.addMenu(Cons.MENU_PLAYER_INFOMATION, menuCommand, inns);
         this.setMenu(menu);
         super.isShowMenu = true;
         this.getMenu().resetIndex();
      }

   }

   public void draw(Graphics g) {
      int lineX;
      switch(MainCanvas.curForm.clientCommand) {
      case 393218:
         int k = 0;

         for(lineX = this.getCurFormWidgets().size(); k < lineX; ++k) {
            UIComponent uic = this.getWidget(k);
            if (uic instanceof UILabel && k >= this.curRowIndex << 1 && k < 8 + this.curRowIndex << 1) {
               uic.draw(g);
            }
         }

         return;
      case 1245185:
         UIPanel panel = null;
         lineX = 0;
         int i = 0;

         for(int ii = this.getCurFormWidgets().size(); i < ii; ++i) {
            UIComponent uic = this.getWidget(i);
            if (uic instanceof UIPanel) {
               panel = (UIPanel)uic;
               uic.draw(g);
               g.setColor(Cons.COLOR_TABLE_TITLE_BG);
               g.fillRect(panel.INNER_XYWH[0] - 4, panel.INNER_XYWH[1] + 2, MainCanvas.CHARW * 2 + 2, panel.INNER_XYWH[3] - 5);
               g.setColor(Cons.COLOR_LINE);
               g.drawLine(panel.INNER_XYWH[0] + MainCanvas.CHARW * 2 + 2 - 4, panel.INNER_XYWH[1] + 2, panel.INNER_XYWH[0] + MainCanvas.CHARW * 2 + 2 - 4, panel.INNER_XYWH[1] + panel.INNER_XYWH[3] - 3);
               lineX = panel.INNER_XYWH[0] + (panel.INNER_XYWH[2] + MainCanvas.CHARW * 2 + 2 + 1) / 2 + 4;
               g.drawLine(lineX - 4, panel.INNER_XYWH[1] + 2, lineX - 4, panel.INNER_XYWH[1] + panel.INNER_XYWH[3] - 3);
            } else if (uic instanceof UILabel) {
               UILabel label = (UILabel)uic;
               if ((i - 4) / 3 >= this.curRowIndex && (i - 4) / 3 < this.curRowIndex + panel.getPanelRowNum()) {
                  int wordLeftX;
                  int wordRightX;
                  switch(i) {
                  case 5:
                     int w5 = lineX - (panel.INNER_XYWH[0] + MainCanvas.CHARW * 2 + 2) - 1;
                     g.setClip(panel.INNER_XYWH[0] + MainCanvas.CHARW * 2 + 2 + 1, panel.INNER_XYWH[1] + 2, w5 - 2, MainCanvas.CHARH);
                     if (MainCanvas.curFont.stringWidth(label.getText()) > w5) {
                        --label.positionX;
                        wordLeftX = panel.INNER_XYWH[0] + MainCanvas.CHARW * 2 + 2 + 1 - MainCanvas.curFont.stringWidth(label.getText()) / 2;
                        wordRightX = lineX + MainCanvas.curFont.stringWidth(label.getText()) / 2;
                        if (label.getPositionX() < wordLeftX) {
                           label.setPositionX((short)wordRightX);
                        }
                     }

                     label.draw(g);
                     g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
                     break;
                  case 6:
                     wordLeftX = panel.INNER_XYWH[0] + panel.INNER_XYWH[2] - lineX + 1;
                     g.setClip(lineX + 3, panel.INNER_XYWH[1] + 2, wordLeftX + 4, MainCanvas.CHARH);
                     if (MainCanvas.curFont.stringWidth(label.getText()) > wordLeftX) {
                        --label.positionX;
                        wordRightX = lineX - MainCanvas.curFont.stringWidth(label.getText()) / 2 + 4;
                        int wordRightX = lineX + 1 + wordLeftX + MainCanvas.curFont.stringWidth(label.getText()) / 2 + 4;
                        if (label.getPositionX() < wordRightX) {
                           label.setPositionX((short)wordRightX);
                        }
                     }

                     label.draw(g);
                     g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
                     break;
                  default:
                     label.draw(g);
                  }
               }
            } else {
               uic.draw(g);
            }
         }
      }

   }

   public Vector getCurFormWidgets() {
      if (this.curFormWidgets == null) {
         this.curFormWidgets = new Vector();
      }

      return this.curFormWidgets;
   }

   public void addWidget(UIComponent uic) {
      if (this.curFormWidgets == null) {
         this.curFormWidgets = new Vector();
      }

      this.curFormWidgets.addElement(uic);
   }

   public void removeWidget(int index) {
      if (this.curFormWidgets != null) {
         this.curFormWidgets.removeElementAt(index);
      }

   }

   public void removeAllWidgets() {
      if (this.curFormWidgets != null) {
         this.curFormWidgets.removeAllElements();
      }

   }

   public UIComponent getWidget(int index) {
      return this.curFormWidgets != null ? (UIComponent)this.curFormWidgets.elementAt(index) : null;
   }

   public void setCurFormWidgets(Vector curFormWidgets) {
      this.curFormWidgets = curFormWidgets;
   }

   public void newInfoForm() {
      this.infoForm = new Form(this.infoName);
      this.commandSave = new Command("Lưu", 4, 2);
      this.commandCancel = new Command(Cons.C_STR[3], 3, 2);
      this.popedomGroup = new ChoiceGroup("Quyền hạn tư liệu", 1, this.popedomGroupItems, (Image[])null);
      this.nickNameTF = new TextField("Tên đăng nhập", "", 6, 0);
      this.sexGroup = new ChoiceGroup("Giới tính", 1, this.sexGroupItems, (Image[])null);
      this.ageTF = new TextField("Tuổi", "", 6, 0);
      this.bloodTypeTF = new TextField("Nhóm máu", "", 6, 0);
      this.animalYearTF = new TextField("Năm sinh", "", 6, 0);
      this.constellationTF = new TextField("Cung hoàng đạo", "", 6, 0);
      this.cityTF = new TextField("Thành phố", "", 6, 0);
      this.workTF = new TextField("Nghề nghiệp", "", 6, 0);
      this.personalShowTF = new TextField("Giới thiệu bản thân", "", 50, 0);
      this.setSaveInfo();
      this.infoForm.append(this.popedomGroup);
      this.infoForm.append(this.nickNameTF);
      this.infoForm.append(this.sexGroup);
      this.infoForm.append(this.ageTF);
      this.infoForm.append(this.bloodTypeTF);
      this.infoForm.append(this.animalYearTF);
      this.infoForm.append(this.constellationTF);
      this.infoForm.append(this.cityTF);
      this.infoForm.append(this.workTF);
      this.infoForm.append(this.personalShowTF);
      this.infoForm.addCommand(this.commandSave);
      this.infoForm.addCommand(this.commandCancel);
      this.infoForm.setCommandListener(new CommandListener() {
         public void commandAction(Command cmd, Displayable display) {
            if (cmd == UIDummyWidget.this.commandSave) {
               UIDummyWidget.saveInfoFormContent.addElement(UIDummyWidget.this.popedomGroup.getString(UIDummyWidget.this.popedomGroup.getSelectedIndex()));
               UIDummyWidget.this.saveStr[0] = UIDummyWidget.this.popedomGroup.getString(UIDummyWidget.this.popedomGroup.getSelectedIndex());
               UIDummyWidget.saveInfoFormContent.addElement(UIDummyWidget.this.nickNameTF.getString());
               UIDummyWidget.this.saveStr[1] = UIDummyWidget.this.nickNameTF.getString();
               UIDummyWidget.saveInfoFormContent.addElement(UIDummyWidget.this.sexGroup.getString(UIDummyWidget.this.sexGroup.getSelectedIndex()));
               UIDummyWidget.this.saveStr[2] = UIDummyWidget.this.sexGroup.getString(UIDummyWidget.this.sexGroup.getSelectedIndex());
               UIDummyWidget.saveInfoFormContent.addElement(UIDummyWidget.this.ageTF.getString());
               UIDummyWidget.this.saveStr[3] = UIDummyWidget.this.ageTF.getString();
               UIDummyWidget.saveInfoFormContent.addElement(UIDummyWidget.this.bloodTypeTF.getString());
               UIDummyWidget.this.saveStr[4] = UIDummyWidget.this.bloodTypeTF.getString();
               UIDummyWidget.saveInfoFormContent.addElement(UIDummyWidget.this.animalYearTF.getString());
               UIDummyWidget.this.saveStr[5] = UIDummyWidget.this.animalYearTF.getString();
               UIDummyWidget.saveInfoFormContent.addElement(UIDummyWidget.this.constellationTF.getString());
               UIDummyWidget.this.saveStr[6] = UIDummyWidget.this.constellationTF.getString();
               UIDummyWidget.saveInfoFormContent.addElement(UIDummyWidget.this.cityTF.getString());
               UIDummyWidget.this.saveStr[7] = UIDummyWidget.this.cityTF.getString();
               UIDummyWidget.saveInfoFormContent.addElement(UIDummyWidget.this.workTF.getString());
               UIDummyWidget.this.saveStr[8] = UIDummyWidget.this.workTF.getString();
               UIDummyWidget.saveInfoFormContent.addElement(UIDummyWidget.this.personalShowTF.getString());
               UIDummyWidget.this.saveStr[9] = UIDummyWidget.this.personalShowTF.getString();
               int i = 0;

               for(int ii = UIDummyWidget.this.getCurFormWidgets().size(); i < ii; ++i) {
                  if (i % 2 != 0) {
                     if (UIDummyWidget.this.getWidget(i) instanceof UILabel) {
                        UILabel label = (UILabel)UIDummyWidget.this.getWidget(i);
                        label.setText(UIDummyWidget.saveInfoFormContent.elementAt(i >> 1).toString());
                     } else if (UIDummyWidget.this.getWidget(i) instanceof UITextArea) {
                        UITextArea area = (UITextArea)UIDummyWidget.this.getWidget(i);
                        area.setContent(UIDummyWidget.saveInfoFormContent.elementAt(i >> 1).toString());
                     }
                  }
               }

               MainCanvas.ni.send(393226);
               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
               UIDummyWidget.this.removeInfoForm();
            } else if (cmd == UIDummyWidget.this.commandCancel) {
               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
               UIDummyWidget.this.removeInfoForm();
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(this.infoForm);
   }

   public void removeInfoForm() {
      this.infoForm = null;
      this.commandSave = null;
      this.commandCancel = null;
      this.popedomGroup = null;
      this.nickNameTF = null;
      this.ageTF = null;
      this.bloodTypeTF = null;
      this.animalYearTF = null;
      this.constellationTF = null;
      this.cityTF = null;
      this.workTF = null;
      this.personalShowTF = null;
      saveInfoFormContent.removeAllElements();
   }

   private void setSaveInfo() {
      int i;
      if (this.saveStr[0] != null) {
         for(i = 0; i < this.popedomGroupItems.length; ++i) {
            if (this.saveStr[0].equals(this.popedomGroupItems[i])) {
               this.popedomGroup.setSelectedIndex(i, true);
            }
         }
      }

      if (this.saveStr[1] != null) {
         this.nickNameTF.setString(this.saveStr[1]);
      }

      if (this.saveStr[2] != null) {
         for(i = 0; i < this.sexGroupItems.length; ++i) {
            if (this.saveStr[2].equals(this.sexGroupItems[i])) {
               this.sexGroup.setSelectedIndex(i, true);
            }
         }
      }

      if (this.saveStr[3] != null) {
         this.ageTF.setString(this.saveStr[3]);
      }

      if (this.saveStr[4] != null) {
         this.bloodTypeTF.setString(this.saveStr[4]);
      }

      if (this.saveStr[5] != null) {
         this.animalYearTF.setString(this.saveStr[5]);
      }

      if (this.saveStr[6] != null) {
         this.constellationTF.setString(this.saveStr[6]);
      }

      if (this.saveStr[7] != null) {
         this.cityTF.setString(this.saveStr[7]);
      }

      if (this.saveStr[8] != null) {
         this.workTF.setString(this.saveStr[8]);
      }

      if (this.saveStr[9] != null) {
         this.personalShowTF.setString(this.saveStr[9]);
      }

   }
}

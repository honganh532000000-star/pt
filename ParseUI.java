import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class ParseUI {
   public static UIForm parseUI(String path) {
      if (path != null && !path.equals("")) {
         try {
            InputStream is = Util.loadFile(path, false);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            boolean var3 = false;

            int ch;
            while((ch = is.read()) != -1) {
               baos.write(ch);
            }

            byte[] data = (byte[])null;
            data = baos.toByteArray();
            baos.close();
            baos = null;
            return parseUI(data, false);
         } catch (Exception var5) {
            var5.printStackTrace();
            return null;
         }
      } else {
         return null;
      }
   }

   public static UIForm parseUI(byte[] data, boolean isNetWork) {
      if (Player.getInstance() != null) {
         Player.getInstance().setAttacked(false);
         UIForm.isInitAtt = false;
      }

      try {
         DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
         return parseUI(dis, isNetWork);
      } catch (Exception var3) {
         var3.printStackTrace();
         return null;
      }
   }

   public static UIForm parseUI(DataInputStream dis, boolean isNetWork) {
      long startParseT = System.currentTimeMillis();
      UIForm form = null;
      int tmpCommand = 0;

      int i;
      try {
         if (isNetWork) {
            tmpCommand = dis.readInt() & 268435455;
         }

         i = dis.readInt();
         if (i != 10461087) {
            dis.close();
            return null;
         }

         byte uiType = dis.readByte();
         short screenW = dis.readShort();
         short screenH = dis.readShort();
         byte fontType = dis.readByte();
         form = new UIForm((short)0, (short)0, screenW, screenH);
         if (isNetWork) {
            form.clientCommand = tmpCommand;
         }

         byte widgetNum = dis.readByte();

         for(int i = 0; i < widgetNum; ++i) {
            UIComponent uic = null;
            byte uioType = dis.readByte();
            switch(uioType) {
            case 0:
               UITitle title = readUITitle(dis, form);
               uic = title;
               form.addComponent(title);
               if (form != null) {
                  String text;
                  switch(form.clientCommand) {
                  case 983056:
                     text = "Thương Tiệm Sư Đức";
                     title.setTitleText(text);
                     break;
                  case 983057:
                     text = "Thương Tiệm Thị Tộc";
                     title.setTitleText(text);
                     break;
                  case 983058:
                     text = "Thương Tiệm Cống Hiến Thị Tộc";
                     title.setTitleText(text);
                  }
               }
               break;
            case 1:
               UILabel label = readUILabel(dis, form);
               uic = label;
               form.addComponent(label);
               break;
            case 2:
               UITextField textField = readUITextField(dis, form);
               uic = textField;
               form.addComponent(textField);
               break;
            case 3:
               UIRadio radio = readUIRadio(dis, form);
               uic = radio;
               form.addComponent(radio);
               break;
            case 4:
               UIPicture picture = readUIPicture(dis, form);
               uic = picture;
               form.addComponent(picture);
               break;
            case 5:
               UIPanel panel = readUIPanel(dis, form);
               uic = panel;
               form.addComponent(panel);
            case 6:
            default:
               break;
            case 7:
               UIFolder folder = readUIFolder(dis, form);
               uic = folder;
               form.addComponent(folder);
               break;
            case 8:
               UIGrid grid = readUIGrid(dis, form);
               uic = grid;
               form.addComponent(grid);
               break;
            case 9:
               UILine line = readUILine(dis, form);
               uic = line;
               form.addComponent(line);
               break;
            case 10:
               UIList list = readUIList(dis, form);
               uic = list;
               form.addComponent(list);
               break;
            case 11:
               UITextArea area = readUITextArea(dis, form);
               uic = area;
               form.addComponent(area);
               break;
            case 12:
               UITable table = readUITable(dis, form);
               uic = table;
               form.addComponent(table);
               break;
            case 99:
               UINull uiNull = readUINull(dis, form);
               uic = uiNull;
               form.addComponent(uiNull);
            }

            if (uic != null) {
               if (((UIComponent)uic).isCanFocus()) {
                  byte upIndex = dis.readByte();
                  byte downIndex = dis.readByte();
                  byte leftIndex = dis.readByte();
                  byte rightIndex = dis.readByte();
                  boolean isFocus = dis.readBoolean();
                  ((UIComponent)uic).setAroundComponent((byte)0, upIndex);
                  ((UIComponent)uic).setAroundComponent((byte)1, downIndex);
                  ((UIComponent)uic).setAroundComponent((byte)2, leftIndex);
                  ((UIComponent)uic).setAroundComponent((byte)3, rightIndex);
                  if (!(uic instanceof UIPicture) || ((UIComponent)uic).getType() != 0 || MainCanvas.oldState != 18 && MainCanvas.oldState != 19 && MainCanvas.m_bFocusEnactment != 1 && MainCanvas.m_bFocusEnactment != 2) {
                     ((UIComponent)uic).setFocus(isFocus, form);
                  } else {
                     ((UIComponent)uic).setFocus(false, form);
                  }
               }

               boolean isUserData = dis.readBoolean();
               ((UIComponent)uic).setUserData(isUserData);
            }
         }

         dis.close();
         if (isNetWork) {
            if (MainCanvas.ni.isSendingCommands) {
               MainCanvas.curFormVector.addElement(form);
            } else if (MainCanvas.replaceFormId != -1) {
               MainCanvas.curFormVector.removeElementAt(MainCanvas.replaceFormId);
               MainCanvas.curFormVector.insertElementAt(form, MainCanvas.replaceFormId);
            }
         }
      } catch (Exception var20) {
         var20.printStackTrace();
      }

      if (MainCanvas.isAdapte) {
         for(i = 0; i < form.getComponents().size(); ++i) {
            ((UIComponent)form.getComponents().elementAt(i)).autoAdapt();
         }
      }

      form.addWarningStr();
      return form;
   }

   public static void parseUIData(byte[] data) {
      try {
         DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
         parseUIData(dis);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public static void parseUIPartData(UIForm uif, byte[] data) {
      try {
         DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
         parseUIPartData(uif, dis);
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   private static void parseUIPartData(UIForm uif, DataInputStream dis) {
      try {
         if (uif != null) {
            byte widgetNum = dis.readByte();
            if (PCIncrement.m_bBuyMoney == 3 || PCIncrement.m_bBuyMoney == 8) {
               widgetNum = (byte)(widgetNum - 2);
            }

            for(int i = 0; i < widgetNum; ++i) {
               byte index = dis.readByte();
               UIComponent uic = (UIComponent)uif.getComponents().elementAt(index);
               byte gridIndex;
               UIGrid grid;
               byte WhetherUse;
               byte updataGridNum;
               int k;
               UITitle menuBar;
               short curDurable;
               if (uic instanceof UIGrid && uic.getClientType() == 6) {
                  grid = (UIGrid)uic;
                  WhetherUse = dis.readByte();
                  grid.updataCanUseNum(WhetherUse);
                  updataGridNum = dis.readByte();

                  for(k = 0; k < updataGridNum; ++k) {
                     gridIndex = dis.readByte();
                     grid.stuffId[gridIndex] = dis.readInt();
                     curDurable = dis.readShort();
                     grid.stuffImgQuality[gridIndex] = curDurable;
                     grid.stuffImageId[gridIndex] = (short)(curDurable % 1000);
                     grid.stuffNumber[gridIndex] = dis.readByte();
                     grid.stuffName[gridIndex] = dis.readUTF();
                     grid.stuffColorLevel[gridIndex] = dis.readByte();
                     grid.stuffType[gridIndex] = dis.readShort();
                     grid.isCanUse[gridIndex] = dis.readBoolean();
                     grid.isCanTrade[gridIndex] = dis.readBoolean();
                     grid.bindType[gridIndex] = dis.readByte();
                     grid.stuffPrice[gridIndex] = dis.readInt();
                     grid.stuffMaxNum[gridIndex] = dis.readByte();
                     grid.stuffEquipPart[gridIndex] = dis.readByte();
                     grid.stuffCD[gridIndex] = dis.readBoolean();
                     grid.stuffHitHole[gridIndex] = dis.readBoolean();
                     grid.stuffLock[gridIndex] = dis.readBoolean();
                     if (grid.stuffMaxNum[gridIndex] == 1) {
                        grid.stuffShowMiniNum[gridIndex] = false;
                     } else {
                        grid.stuffShowMiniNum[gridIndex] = true;
                     }

                     grid.getQuality(gridIndex, curDurable);
                     grid.setTitle(grid.stuffName[grid.getGridIndex()], UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
                  }

                  if (MainCanvas.curForm.clientCommand == 939349) {
                     PCEquip.m_bEquipIndex = -1;
                     PCEquip.m_bPropIndex = -1;
                     PCEquip.m_bOrigin = -1;
                  } else if (MainCanvas.curForm.clientCommand == 2228256) {
                     PCGemJoinOrRemove.CleanupData();
                  } else if (MainCanvas.curForm.clientCommand == 3342341 && grid.stuffId[grid.gridIndex] == -1) {
                     menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                     menuBar.setMenuText("", 0);
                  }
               } else {
                  byte gridIndex;
                  int picID;
                  short imageFullID;
                  if (uic instanceof UIGrid && (uic.getClientType() == 42 || uic.getClientType() == 53)) {
                     grid = (UIGrid)uic;
                     WhetherUse = dis.readByte();

                     for(picID = 0; picID < WhetherUse; ++picID) {
                        gridIndex = dis.readByte();
                        grid.stuffId[gridIndex] = dis.readInt();
                        imageFullID = dis.readShort();
                        grid.stuffImgQuality[gridIndex] = imageFullID;
                        grid.stuffImageId[gridIndex] = (short)(imageFullID % 1000);
                        grid.stuffPrice[gridIndex] = dis.readInt();
                        grid.stuffNumber[gridIndex] = dis.readByte();
                        grid.stuffName[gridIndex] = dis.readUTF();
                        grid.stuffColorLevel[gridIndex] = dis.readByte();
                        grid.stuffShowMiniNum[gridIndex] = dis.readBoolean();
                        grid.stuffMaxNum[gridIndex] = dis.readByte();
                        grid.getQuality(gridIndex, imageFullID);
                        if (uic.getClientType() == 53) {
                           grid.stuffGroupNum[gridIndex] = dis.readByte();
                        }

                        grid.setTitle(grid.stuffName[grid.getGridIndex()], UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
                     }
                  } else if (uic instanceof UIGrid && uic.getClientType() == 29) {
                     grid = (UIGrid)uic;
                     WhetherUse = dis.readByte();
                     grid.updataCanUseNum(WhetherUse);
                     updataGridNum = dis.readByte();

                     for(k = 0; k < updataGridNum; ++k) {
                        gridIndex = dis.readByte();
                        grid.stuffId[gridIndex] = dis.readInt();
                        curDurable = dis.readShort();
                        grid.stuffImgQuality[gridIndex] = curDurable;
                        grid.stuffImageId[gridIndex] = (short)(curDurable % 1000);
                        grid.stuffNumber[gridIndex] = dis.readByte();
                        grid.stuffName[gridIndex] = dis.readUTF();
                        grid.stuffShowMiniNum[gridIndex] = dis.readBoolean();
                        grid.stuffColorLevel[gridIndex] = dis.readByte();
                        grid.getQuality(gridIndex, curDurable);
                        grid.stuffLock[gridIndex] = dis.readBoolean();
                     }
                  } else {
                     UIPicture form;
                     String listItemStr;
                     short equipPicID;
                     if (uic instanceof UIPicture && uic.getClientType() == 16 && uic.getType() == 3) {
                        form = (UIPicture)uic;
                        WhetherUse = dis.readByte();
                        equipPicID = dis.readShort();
                        form.setQuality(equipPicID);
                        listItemStr = dis.readUTF();
                        gridIndex = dis.readByte();
                        curDurable = (short)(equipPicID % 1000);
                        form.isEquipPic = true;
                        form.equipIndex = curDurable;
                        form.setImg(MainCanvas.stuffMImg);
                        form.setEquipName(listItemStr);
                        form.setEquipType(WhetherUse);
                        form.setEquipColorLevel(gridIndex);
                     } else {
                        short imageFullID;
                        int k;
                        if (uic instanceof UIPicture && uic.getClientType() == 115 && uic.getType() == 3) {
                           form = (UIPicture)uic;
                           WhetherUse = dis.readByte();
                           equipPicID = dis.readShort();
                           form.setQuality(equipPicID);
                           listItemStr = dis.readUTF();
                           gridIndex = dis.readByte();
                           curDurable = dis.readShort();
                           imageFullID = dis.readShort();
                           k = dis.readInt();
                           short picID = (short)(equipPicID % 1000);
                           form.isEquipPic = true;
                           form.equipIndex = picID;
                           form.setImg(MainCanvas.stuffMImg);
                           form.setEquipName(listItemStr);
                           form.setEquipType(WhetherUse);
                           form.setEquipColorLevel(gridIndex);
                           form.setEquipCurDuable(curDurable);
                           form.setEquipMaxDuable(imageFullID);
                           form.setEquipFixPay(k);
                        } else if (uic instanceof UIPicture && uic.getClientType() == 5 && uic.getType() == 1) {
                           form = (UIPicture)uic;
                           WhetherUse = dis.readByte();
                           form.setItemNum(WhetherUse);
                           if (MainCanvas.curForm != null) {
                              switch(MainCanvas.curForm.clientCommand) {
                              case 458760:
                              case 2555911:
                                 UIGrid.packageSelectedView();
                              }
                           }
                        } else {
                           UILabel label;
                           int num1;
                           if (uic instanceof UILabel && uic.getClientType() == 4 && uic.getType() == 0) {
                              label = (UILabel)uic;
                              num1 = dis.readInt();
                              label.setText(String.valueOf(num1));
                           } else if (uic instanceof UILabel && uic.getClientType() == 4 && uic.getType() == 3) {
                              label = (UILabel)uic;
                              num1 = dis.readInt();
                              label.setNum1(num1);
                           } else if (uic instanceof UILabel && uic.getClientType() == 4 && uic.getType() == 4) {
                              label = (UILabel)uic;
                              num1 = dis.readInt();
                              label.setText(Integer.toString(num1));
                           } else if (uic instanceof UILabel && uic.getType() == 2) {
                              label = (UILabel)uic;
                              num1 = dis.readInt();
                              picID = dis.readInt();
                              label.setNum1(num1);
                              label.setNum2(picID);
                              if (MainCanvas.curForm.clientCommand == 2228225) {
                                 PCGemJoinOrRemove.m_bGemAmount = (byte)num1;
                              }
                           } else if (uic.getClientType() == 67 && uic instanceof UITable) {
                              form = null;
                              UITable table = (UITable)uic;
                              table.setTitleItem((TableItem)null);
                              updataGridNum = dis.readByte();
                              UIComponent[] lineUic = new UIComponent[3];
                              TableItem firstItem = (TableItem)table.getItems().elementAt(0);
                              short[] itemW = firstItem.getTermWidth();
                              boolean[] isCenter = firstItem.getIsCenter();

                              for(k = 0; k < firstItem.getTerms().size(); ++k) {
                                 lineUic[k] = (UIComponent)firstItem.getTerms().elementAt(k);
                              }

                              short termHeight = firstItem.getTermHeight();
                              table.removeAllItems();

                              for(int h = 0; h < updataGridNum; ++h) {
                                 String apertureName = dis.readUTF();
                                 short pictureID = dis.readShort();
                                 String gemName = dis.readUTF();
                                 TableItem ti = new TableItem();
                                 ti.resetTermWidth(3);
                                 ti.setTermHeight(termHeight);
                                 ti.setTermWidth(itemW);
                                 ti.setX((short)(table.getPositionX() + 3 + 1 + 1));
                                 ti.setY((short)(table.getPositionY() + 1 + 1 + 1 + h * termHeight));

                                 for(int ii = 0; ii < 3; ++ii) {
                                    UIComponent uic2 = null;
                                    short startX = (short)(table.getPositionX() + 3 + 1 + 1);
                                    short startY = (short)(table.getPositionY() + 1 + 1 + 1);
                                    UILabel oldLabel;
                                    UILabel label;
                                    if (ii == 0) {
                                       oldLabel = (UILabel)lineUic[ii];
                                       label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                                       label.setText(apertureName);
                                       label.setPositionX((short)(startX + (ti.getCurWidth((byte)ii) + 1) * MainCanvas.screenW / 176));
                                       label.setPositionY((short)(startY + h * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                                       uic2 = label;
                                    } else if (ii == 1) {
                                       UIPicture oldPic = (UIPicture)lineUic[ii];
                                       UIPicture pic = new UIPicture(oldPic.getPositionX(), oldPic.getPositionY(), oldPic.getWidth(), oldPic.getHeight(), (String)null, (String)null, oldPic.getType(), form);
                                       if (pictureID != -1) {
                                          pic.setQuality(pictureID);
                                          pic.isWpPic = true;
                                          short picID = (short)(pictureID % 1000);
                                          pic.wpIndex = picID;
                                       }

                                       pic.setImg(MainCanvas.stuffMImg);
                                       pic.setPositionX((short)(startX + (ti.getCurWidth((byte)ii) + 1) * MainCanvas.screenW / 176));
                                       pic.setPositionY((short)(startY + h * termHeight + (termHeight - pic.getImg().frame_h) / 2));
                                       if (pictureID == -1) {
                                          pic.setImg((MImage)null);
                                       }

                                       uic2 = pic;
                                    } else if (ii == 2) {
                                       oldLabel = (UILabel)lineUic[ii];
                                       label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                                       label.isRoll = true;
                                       label.rollWidth = (short)(itemW[ii] * MainCanvas.screenW / 176);
                                       label.setText(gemName);
                                       label.setPositionX((short)(startX + (ti.getCurWidth((byte)ii) + 1) * MainCanvas.screenW / 176));
                                       label.setPositionY((short)(startY + h * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                                       uic2 = label;
                                    }

                                    ti.addItem((UIComponent)uic2);
                                 }

                                 table.addItem(ti);
                              }

                              if (MainCanvas.curForm.clientCommand == 1966100) {
                                 table.serverType = 13;
                              } else {
                                 table.serverType = 12;
                                 PCGem.m_bEnchaseSucceed = 1;
                                 UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                                 menuBar.setMenuText("Tra tìm", 0);
                              }

                              table.addUIScroll();
                           } else {
                              String img;
                              byte absence;
                              if (!(uic instanceof UIPicture) || uic.getClientType() != 34 && uic.getClientType() != 35) {
                                 if (uic instanceof UIPicture && (uic.getClientType() == 36 || uic.getClientType() == 37)) {
                                    form = (UIPicture)uic;
                                    boolean isLock = dis.readBoolean();
                                    img = null;
                                    if (uic.getClientType() == 36) {
                                       form.imageFlip = 0;
                                       UIPicture.isMyTradeLock = isLock;
                                    } else {
                                       form.imageFlip = 3;
                                       UIPicture.isOtherTradeLock = isLock;
                                    }
                                 } else {
                                    UIList list;
                                    if ((uic.getClientType() == 83 || uic.getClientType() == 88 || uic.getClientType() == 95 || uic.getClientType() == 102 || uic.getClientType() == 107 || uic.getClientType() == 113) && uic instanceof UIList) {
                                       list = (UIList)uic;
                                       list.removeAll();
                                       WhetherUse = dis.readByte();

                                       for(picID = 0; picID < WhetherUse; ++picID) {
                                          gridIndex = dis.readByte();
                                          int id = dis.readInt();
                                          String listItemStr = dis.readUTF();
                                          ListItem item = new ListItem(listItemStr, (byte)1, (byte)0, list.getListWidth());
                                          byte taskType = -1;
                                          switch(gridIndex) {
                                          case 0:
                                             taskType = 6;
                                             break;
                                          case 1:
                                             taskType = 7;
                                             break;
                                          case 2:
                                             taskType = 5;
                                             break;
                                          case 3:
                                             taskType = 8;
                                          }

                                          item.setTaskType(taskType);
                                          item.itemId = id;
                                          list.addItem(item);
                                       }
                                    } else if (uic instanceof UIGrid && (uic.getClientType() == 164 || uic.getClientType() == 167)) {
                                       grid = (UIGrid)uic;
                                       WhetherUse = dis.readByte();

                                       for(picID = 0; picID < WhetherUse; ++picID) {
                                          gridIndex = dis.readByte();
                                          imageFullID = dis.readShort();
                                          grid.stuffImgQuality[gridIndex] = imageFullID;
                                          grid.stuffImageId[gridIndex] = (short)(imageFullID % 1000);
                                          grid.stuffNumber[gridIndex] = dis.readByte();
                                          grid.stuffName[gridIndex] = dis.readUTF();
                                          grid.stuffColorLevel[gridIndex] = dis.readByte();
                                          grid.getQuality(gridIndex, imageFullID);
                                          grid.stuffPrice[gridIndex] = dis.readInt();
                                       }

                                       grid.setTitle(grid.stuffName[grid.getGridIndex()], UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
                                    } else if (uic instanceof UIGrid && uic.getClientType() == 165) {
                                       grid = (UIGrid)uic;
                                       WhetherUse = dis.readByte();
                                       grid.updataCanUseNum(WhetherUse);
                                       updataGridNum = dis.readByte();

                                       for(k = 0; k < updataGridNum; ++k) {
                                          gridIndex = dis.readByte();
                                          absence = dis.readByte();
                                          if (absence == 1) {
                                             grid.stuffId[gridIndex] = dis.readInt();
                                             imageFullID = dis.readShort();
                                             grid.stuffImgQuality[gridIndex] = imageFullID;
                                             grid.stuffImageId[gridIndex] = (short)(imageFullID % 1000);
                                             grid.getQuality(gridIndex, imageFullID);
                                             grid.stuffNumber[gridIndex] = dis.readByte();
                                             grid.stuffShowMiniNum[gridIndex] = true;
                                             grid.stuffName[gridIndex] = dis.readUTF();
                                             grid.stuffColorLevel[gridIndex] = dis.readByte();
                                             grid.isCanTrade[gridIndex] = dis.readBoolean();
                                             grid.stuffPrice[gridIndex] = dis.readInt();
                                             grid.stuffMaxNum[gridIndex] = dis.readByte();
                                             if (grid.stuffId[gridIndex] != -1) {
                                                grid.stuffName[gridIndex] = grid.stuffName[gridIndex] + ":Đơn giá" + grid.stuffPrice[gridIndex] + "Tiền chợ";
                                             }
                                          } else {
                                             grid.stuffId[gridIndex] = -1;
                                             short imageFullID = -1;
                                             grid.stuffImgQuality[gridIndex] = imageFullID;
                                             grid.stuffImageId[gridIndex] = (short)(imageFullID % 1000);
                                             grid.getQuality(gridIndex, imageFullID);
                                             grid.stuffNumber[gridIndex] = 0;
                                             grid.stuffShowMiniNum[gridIndex] = false;
                                             grid.stuffName[gridIndex] = "";
                                             grid.stuffColorLevel[gridIndex] = 0;
                                             grid.isCanTrade[gridIndex] = false;
                                             grid.stuffPrice[gridIndex] = 0;
                                             grid.stuffMaxNum[gridIndex] = 0;
                                          }
                                       }

                                       menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                                       if (grid.stuffId[grid.getGridIndex()] != -1) {
                                          menuBar.setMenuText("Thao tác", 0);
                                       } else {
                                          menuBar.setMenuText("Sắp xếp", 0);
                                       }

                                       grid.setTitle(grid.stuffName[grid.getGridIndex()], UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
                                    } else if (uic instanceof UIList && uic.getClientType() == 170) {
                                       list = (UIList)uic;
                                       list.removeAll();
                                       WhetherUse = dis.readByte();

                                       for(picID = 0; picID < WhetherUse; ++picID) {
                                          listItemStr = dis.readUTF();
                                          ListItem item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                          item.m_bBattlefieldGenre = dis.readShort();
                                          item.m_bBattlefieldExamine = dis.readShort();
                                          list.addItem(item);
                                       }
                                    } else if (uic instanceof UIPicture && uic.getClientType() == 175) {
                                       form = (UIPicture)uic;
                                       WhetherUse = dis.readByte();
                                       form.setWhetherEnvelop(WhetherUse);
                                       picID = dis.readInt();
                                       form.setShowNum(false, (byte)1);
                                       form.quality = (byte)(picID / 1000 - 1);
                                       short picid_1 = (short)(picID % 1000);
                                       form.isWpPic = true;
                                       form.wpIndex = picid_1;
                                       form.setImg(MainCanvas.stuffMImg);
                                       UITextArea textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(13);
                                       int[] Index = new int[]{3, 5, 7, 9, 11};

                                       for(int h = 0; h < Index.length; ++h) {
                                          if (Index[i] == index) {
                                             PCTreasure.explain[i] = dis.readUTF();
                                             textarea.setContent(PCTreasure.explain[i]);
                                          }
                                       }
                                    } else if (uic instanceof UILabel && uic.getClientType() == 1 && uic.getType() == 0) {
                                       label = (UILabel)uic;
                                       String labelNum = dis.readUTF();
                                       label.setText(labelNum);
                                    }
                                 }
                              } else {
                                 form = (UIPicture)uic;
                                 short wpID = dis.readShort();
                                 img = dis.readUTF();
                                 gridIndex = dis.readByte();
                                 gridIndex = dis.readByte();
                                 absence = dis.readByte();
                                 form.setQuality(wpID);
                                 form.isWpPic = true;
                                 imageFullID = (short)(wpID % 1000);
                                 form.isWpPic = true;
                                 form.wpIndex = imageFullID;
                                 form.setImg(MainCanvas.stuffMImg);
                                 form.setEquipName(img);
                                 form.setEquipType(gridIndex);
                                 form.setEquipColorLevel(absence);
                                 if (gridIndex > 0) {
                                    form.setShowNum(true, gridIndex);
                                 } else {
                                    form.setShowNum(false);
                                 }

                                 try {
                                    if (form.clientType == 35 && form.isFocus()) {
                                       UILabel labelOtherGoods = (UILabel)MainCanvas.curForm.getComponents().elementAt(17);
                                       labelOtherGoods.setText(form.getEquipName());
                                       UIForm.isOtherPic = true;
                                       UIForm.curPicNum = (byte)(i - 20);
                                       labelOtherGoods.setTextColor(UIGrid.getStuffWordColor(form.getEquipColorLevel()));
                                    }
                                 } catch (Exception var26) {
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      } catch (Exception var27) {
         var27.printStackTrace();
      }

   }

   public static void parseUIData(DataInputStream dis) {
      try {
         long startParseUIDataT = System.currentTimeMillis();
         int fileHeader = dis.readInt();
         if (fileHeader == 8289918) {
            int dataLen = dis.readByte();
            UIForm tmpForm = null;
            if (MainCanvas.curFormVector.size() != 0) {
               if (MainCanvas.replaceFormId != -1) {
                  tmpForm = (UIForm)MainCanvas.curFormVector.elementAt(MainCanvas.replaceFormId);
               } else {
                  tmpForm = (UIForm)MainCanvas.curFormVector.elementAt(MainCanvas.curFormVector.size() - 1);
               }
            } else {
               tmpForm = MainCanvas.curForm;
            }

            short[] clientTypes = new short[dataLen];
            UIComponent[] uics = new UIComponent[dataLen];

            int i;
            for(i = 0; i < dataLen; ++i) {
               byte index = (byte)(dis.readByte() - 1);
               if (tmpForm != null) {
                  clientTypes[i] = dis.readShort();
               }

               uics[i] = (UIComponent)tmpForm.getComponents().elementAt(index);
               uics[i].setClientType(clientTypes[i]);
            }

            for(i = 0; i < dataLen; ++i) {
               initUserUic(dis, clientTypes[i], uics[i], tmpForm.clientCommand, tmpForm);
            }

            setSpecielWidgetType();
            dis.close();
         }
      } catch (Exception var10) {
         var10.printStackTrace();
      }

   }

   public static void setSpecielWidgetType() {
      setEquipPicType();
      setBagType();
      setWPPicType();
   }

   private static void setWPPicType() {
      UIPicture pic = null;
      int i = 0;

      for(int ii = MainCanvas.curForm.getComponents().size(); i < ii; ++i) {
         if ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i) instanceof UIPicture && ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i)).getType() == 1) {
            pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i);
            if (pic.getClientType() == 34 || pic.getClientType() == 37) {
               switch(MainCanvas.curForm.clientCommand) {
               case 1376260:
               case 1376263:
               case 1376269:
                  pic.setPicFormType((byte)2);
               }
            }
         }
      }

   }

   private static void setEquipPicType() {
      UIPicture pic = null;
      int i = 0;

      for(int ii = MainCanvas.curForm.getComponents().size(); i < ii; ++i) {
         if ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i) instanceof UIPicture && ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i)).getType() == 3) {
            pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i);
            if (pic.getClientType() == 16 || pic.getClientType() == 115) {
               switch(MainCanvas.curForm.clientCommand) {
               case 917505:
               case 917506:
               case 917507:
               case 917508:
               case 917509:
               case 917520:
               case 917521:
               case 917522:
               case 1638431:
               case 1638432:
               case 1638433:
                  pic.setPicFormType((byte)0);
                  break;
               case 1245185:
               case 1245186:
                  pic.setPicFormType((byte)1);
               }
            }
         }
      }

   }

   private static void setBagType() {
      UIGrid grid = null;
      int i = 0;

      for(int ii = MainCanvas.curForm.getComponents().size(); i < ii; ++i) {
         if ((UIComponent)MainCanvas.curForm.getComponents().elementAt(i) instanceof UIGrid) {
            grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(i);
            if (grid.getClientType() == 6) {
               switch(MainCanvas.curForm.clientCommand) {
               case 458753:
               case 458754:
               case 458756:
               case 458757:
               case 458758:
               case 458759:
               case 458760:
               case 458775:
               case 3342341:
                  grid.setGridFormType((byte)0);
                  break;
               case 458755:
               case 917505:
               case 917506:
               case 917508:
               case 917509:
               case 917510:
               case 917511:
                  grid.setGridFormType((byte)8);
                  break;
               case 458817:
                  grid.setGridFormType((byte)15);
                  break;
               case 917525:
                  grid.setGridFormType((byte)20);
                  break;
               case 939349:
                  grid.setGridFormType((byte)17);
                  break;
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
                  grid.setGridFormType((byte)1);
                  break;
               case 1179649:
               case 1179650:
               case 1179651:
               case 1179652:
               case 1179653:
               case 1179654:
               case 1638423:
                  grid.setGridFormType((byte)2);
                  break;
               case 1179658:
                  grid.setGridFormType((byte)9);
                  break;
               case 1245185:
               case 1245186:
                  grid.setGridFormType((byte)3);
                  break;
               case 1376260:
               case 1376263:
               case 1376269:
                  grid.setGridFormType((byte)4);
                  break;
               case 1703946:
                  grid.setGridFormType((byte)5);
                  break;
               case 1769473:
               case 1769475:
               case 1769478:
               case 1769479:
               case 1769480:
               case 1769488:
               case 1769489:
               case 1769490:
               case 1769491:
               case 1769492:
                  grid.setGridFormType((byte)6);
                  break;
               case 1900547:
                  grid.setGridFormType((byte)7);
                  break;
               case 1966086:
                  grid.setGridFormType((byte)10);
                  break;
               case 1966097:
                  grid.setGridFormType((byte)14);
                  break;
               case 2031621:
                  grid.setGridFormType((byte)12);
                  break;
               case 2031636:
                  grid.setGridFormType((byte)11);
                  break;
               case 2228225:
                  grid.setGridFormType((byte)13);
                  break;
               case 2228256:
                  grid.setGridFormType((byte)18);
                  break;
               case 3342356:
                  grid.setGridFormType((byte)19);
               }
            }
         }
      }

   }

   public static void initUserUic(DataInputStream dis, short clientType, UIComponent uic, int clientCommand, UIForm form) throws IOException {
      if (uic instanceof UIList) {
         initUserList(dis, clientType, (UIList)uic, clientCommand, form);
      } else if (uic instanceof UILabel) {
         initUserLabel(dis, clientType, (UILabel)uic, clientCommand, form);
      } else if (uic instanceof UITable) {
         initUserTable(dis, clientType, (UITable)uic, clientCommand, form);
      } else if (uic instanceof UIPicture) {
         initUserPicture(dis, clientType, (UIPicture)uic, clientCommand, form);
      } else if (uic instanceof UITextArea) {
         initUserTextArea(dis, clientType, (UITextArea)uic, clientCommand, form);
      } else if (uic instanceof UIGrid) {
         initUserGrid(dis, clientType, (UIGrid)uic, clientCommand, form);
      }

   }

   private static void initUserGrid(DataInputStream dis, short clientType, UIGrid grid, int clientCommand, UIForm form) throws IOException {
      byte canUseNum;
      int k;
      short imageFullID;
      if (clientType == 6) {
         canUseNum = dis.readByte();
         grid.setCanUseNum(canUseNum);
         grid.setEmpty();

         for(k = 0; k < canUseNum; ++k) {
            grid.stuffId[k] = dis.readInt();
            imageFullID = dis.readShort();
            grid.stuffImgQuality[k] = imageFullID;
            grid.stuffImageId[k] = (short)(imageFullID % 1000);
            grid.stuffNumber[k] = dis.readByte();
            grid.stuffName[k] = dis.readUTF();
            grid.stuffColorLevel[k] = dis.readByte();
            grid.stuffType[k] = dis.readShort();
            grid.isCanUse[k] = dis.readBoolean();
            grid.isCanTrade[k] = dis.readBoolean();
            grid.bindType[k] = dis.readByte();
            grid.stuffPrice[k] = dis.readInt();
            grid.stuffMaxNum[k] = dis.readByte();
            grid.stuffEquipPart[k] = dis.readByte();
            grid.stuffCD[k] = dis.readBoolean();
            grid.stuffHitHole[k] = dis.readBoolean();
            grid.stuffLock[k] = dis.readBoolean();
            if (grid.stuffMaxNum[k] == 1) {
               grid.stuffShowMiniNum[k] = false;
            } else {
               grid.stuffShowMiniNum[k] = true;
            }

            grid.getQuality(k, imageFullID);
         }

         grid.setTitle(grid.stuffName[grid.getGridIndex()], UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
         grid.addUIScroll();
      } else if (clientType != 42 && clientType != 53) {
         if (clientType == 29) {
            canUseNum = dis.readByte();
            grid.setCanUseNum(canUseNum);

            for(k = 0; k < canUseNum; ++k) {
               grid.stuffId[k] = dis.readInt();
               imageFullID = dis.readShort();
               grid.stuffImgQuality[k] = imageFullID;
               grid.stuffImageId[k] = (short)(imageFullID % 1000);
               grid.stuffNumber[k] = dis.readByte();
               grid.stuffName[k] = dis.readUTF();
               grid.stuffShowMiniNum[k] = dis.readBoolean();
               grid.stuffColorLevel[k] = dis.readByte();
               grid.getQuality(k, imageFullID);
               grid.stuffLock[k] = dis.readBoolean();
            }

            grid.addUIScroll();
         } else if (clientType == 148) {
            int canUseNum = dis.readInt();
            PCIncrement.m_bHotGoodsShopping = new byte[canUseNum];
            PCIncrement.m_bBuyShopping = new int[canUseNum];
            PCIncrement.m_sBuyShopping = new String[canUseNum];
            PCIncrement.m_bBuyRestrictShopping = new byte[canUseNum];
            PCIncrement.m_nGoodsNumberCommodity = new int[canUseNum];
            PCIncrement.m_bGoodsHowCommodity = new byte[canUseNum];
            grid.setCanUseNum((short)canUseNum);
            grid.setEmpty();

            for(k = 0; k < canUseNum; ++k) {
               grid.stuffId[k] = dis.readInt();
               PCIncrement.m_nGoodsNumberCommodity[k] = grid.stuffId[k];
               imageFullID = dis.readShort();
               grid.stuffImgQuality[k] = imageFullID;
               grid.stuffImageId[k] = (short)(imageFullID % 1000);
               grid.stuffNumber[k] = dis.readByte();
               PCIncrement.m_bGoodsHowCommodity[k] = grid.stuffNumber[k];
               grid.stuffName[k] = dis.readUTF();
               grid.stuffQuality[k] = dis.readByte();
               grid.stuffColorLevel[k] = grid.stuffQuality[k];
               PCIncrement.m_bHotGoodsShopping[k] = dis.readByte();
               PCIncrement.m_bBuyShopping[k] = dis.readInt();
               if (PCIncrement.m_bBuyMoney == 3 || PCIncrement.m_bBuyMoney == 8) {
                  PCIncrement.m_sBuyShopping[k] = PCIncrement.m_bBuyShopping[k] / 10 + "." + PCIncrement.m_bBuyShopping[k] % 10 + "Đồng";
               }

               PCIncrement.m_bBuyRestrictShopping[k] = dis.readByte();
               if (grid.stuffNumber[k] == 1) {
                  grid.stuffShowMiniNum[k] = false;
               } else {
                  grid.stuffShowMiniNum[k] = true;
               }

               grid.getQuality(k, imageFullID);
               UITitle title = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
               title.setTitleText(PCIncrement.m_sTitle);
               UILabel label = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
               if (PCIncrement.m_bBuyMoney != 3 && PCIncrement.m_bBuyMoney != 8) {
                  label.setNum1(PCIncrement.m_bBuyShopping[0]);
               } else {
                  label.setType((byte)0);
                  label.setText(PCIncrement.m_sBuyShopping[0]);
               }
            }

            grid.setTitle(grid.stuffName[grid.getGridIndex()], UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
            grid.addUIScroll();
         } else {
            UITitle menuBar;
            if (clientType != 164 && clientType != 167) {
               if (clientType == 160) {
                  short canUseNum = dis.readShort();
                  grid.setCanUseNum(canUseNum);
                  PCFarm.m_nPropPrice = new int[canUseNum];

                  for(k = 0; k < canUseNum; ++k) {
                     grid.stuffId[k] = dis.readInt();
                     imageFullID = dis.readShort();
                     grid.stuffImgQuality[k] = imageFullID;
                     grid.stuffImageId[k] = (short)(imageFullID % 1000);
                     grid.stuffName[k] = dis.readUTF();
                     grid.stuffColorLevel[k] = dis.readByte();
                     PCFarm.m_nPropPrice[k] = dis.readInt();
                     grid.getQuality(k, imageFullID);
                  }

                  UILabel label = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
                  UILabel labelnum = (UILabel)MainCanvas.curForm.getComponents().elementAt(7);
                  UIGrid gridshop = (UIGrid)MainCanvas.curForm.getComponents().elementAt(3);
                  label.setText(gridshop.stuffName[gridshop.getGridIndex()]);
                  label.setTextColor(UIGrid.getStuffWordColor(gridshop.stuffColorLevel[gridshop.getGridIndex()]));
                  labelnum.setNum1(PCFarm.m_nPropPrice[gridshop.getGridIndex()]);
               } else if (clientType == 165) {
                  canUseNum = dis.readByte();
                  grid.setCanUseNum(canUseNum);
                  grid.setEmpty();

                  for(k = 0; k < canUseNum; ++k) {
                     byte absence = dis.readByte();
                     if (absence == 1) {
                        grid.stuffId[k] = dis.readInt();
                        short imageFullID = dis.readShort();
                        grid.stuffImgQuality[k] = imageFullID;
                        grid.stuffImageId[k] = (short)(imageFullID % 1000);
                        grid.getQuality(k, imageFullID);
                        grid.stuffNumber[k] = dis.readByte();
                        grid.stuffShowMiniNum[k] = true;
                        grid.stuffName[k] = dis.readUTF();
                        grid.stuffColorLevel[k] = dis.readByte();
                        grid.isCanTrade[k] = dis.readBoolean();
                        grid.stuffPrice[k] = dis.readInt();
                        grid.stuffMaxNum[k] = dis.readByte();
                        grid.stuffName[k] = grid.stuffName[k] + "：giá lẻ" + grid.stuffPrice[k] + "tiền";
                     } else {
                        grid.stuffId[k] = -1;
                        short imageFullID = -1;
                        grid.stuffImgQuality[k] = imageFullID;
                        grid.stuffImageId[k] = (short)(imageFullID % 1000);
                        grid.getQuality(k, imageFullID);
                        grid.stuffNumber[k] = 0;
                        grid.stuffShowMiniNum[k] = false;
                        grid.stuffName[k] = "";
                        grid.stuffColorLevel[k] = 0;
                        grid.isCanTrade[k] = false;
                        grid.stuffPrice[k] = 0;
                        grid.stuffMaxNum[k] = 0;
                     }
                  }

                  grid.setTitle(grid.stuffName[grid.getGridIndex()], UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
                  grid.addUIScroll();
                  if (grid.stuffId[0] == -1) {
                     menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                     menuBar.setMenuText("sắp xếp", 0);
                  }
               }
            } else {
               canUseNum = dis.readByte();
               grid.setCanUseNum(canUseNum);
               grid.setEmpty();

               for(k = 0; k < canUseNum; ++k) {
                  imageFullID = dis.readShort();
                  grid.stuffImgQuality[k] = imageFullID;
                  grid.stuffImageId[k] = (short)(imageFullID % 1000);
                  grid.stuffNumber[k] = dis.readByte();
                  grid.stuffShowMiniNum[k] = true;
                  grid.stuffName[k] = dis.readUTF();
                  grid.stuffColorLevel[k] = dis.readByte();
                  grid.getQuality(k, imageFullID);
                  grid.stuffPrice[k] = dis.readInt();
               }

               if (canUseNum != 0) {
                  grid.setTitle(grid.stuffName[grid.getGridIndex()], UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
                  grid.addUIScroll();
                  switch(MainCanvas.curForm.clientCommand) {
                  case 2555907:
                  case 2555915:
                     if (grid.stuffName[0].equals("") || grid.stuffName[0] == null) {
                        menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                        menuBar.setMenuText("", 0);
                     }
                     break;
                  case 2555936:
                     if (clientType == 167 || clientType == 164) {
                        menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                        if (!grid.stuffName[0].equals("") && grid.stuffName[0] != null) {
                           menuBar.setMenuText("Thao tác", 0);
                        } else {
                           menuBar.setMenuText("", 0);
                        }
                     }
                  }
               }
            }
         }
      } else {
         canUseNum = dis.readByte();
         grid.setCanUseNum(canUseNum);

         for(k = 0; k < canUseNum; ++k) {
            grid.stuffId[k] = dis.readInt();
            imageFullID = dis.readShort();
            grid.stuffImgQuality[k] = imageFullID;
            grid.stuffImageId[k] = (short)(imageFullID % 1000);
            grid.stuffPrice[k] = dis.readInt();
            grid.stuffNumber[k] = dis.readByte();
            grid.stuffName[k] = dis.readUTF();
            grid.stuffColorLevel[k] = dis.readByte();
            grid.stuffShowMiniNum[k] = dis.readBoolean();
            grid.stuffMaxNum[k] = dis.readByte();
            grid.getQuality(k, imageFullID);
            if (clientType == 53) {
               grid.stuffGroupNum[k] = dis.readByte();
            }

            grid.setTitle(grid.stuffName[grid.getGridIndex()], UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
         }

         grid.addUIScroll();
      }

   }

   private static void initUserTextArea(DataInputStream dis, short clientType, UITextArea area, int clientCommand, UIForm form) throws IOException {
      int i;
      if (clientType != 8 && clientType != 17 && clientType != 21 && clientType != 23 && clientType != 22 && clientType != 63 && clientType != 55 && clientType != 57 && clientType != 61 && clientType != 62 && clientType != 68 && clientType != 66 && clientType != 69 && clientType != 81 && clientType != 90 && clientType != 97 && clientType != 104 && clientType != 109 && clientType != 114 && clientType != 120 && clientType != 126 && clientType != 142 && clientType != 147 && clientType != 1) {
         if (clientType == 135) {
            byte num = dis.readByte();
            PCIncrement.m_sFullExplain = new String[num];

            for(i = 0; i < num; ++i) {
               PCIncrement.m_sFullExplain[i] = dis.readUTF();
            }

            UITextArea textarea;
            if (clientCommand == 2424833) {
               textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(8);
               textarea.setContent(PCIncrement.m_sFullExplain[0]);
            } else if (clientCommand == 2424840) {
               textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(5);
               textarea.setContent(PCIncrement.m_sFullExplain[0]);
            }
         }
      } else {
         String content = dis.readUTF();
         i = dis.readInt();
         area.setColor(i);
         UITextArea.taskStartTime = System.currentTimeMillis();
         area.setContent(content);
         area.addUIScroll();
         if (clientType == 17) {
            area.serverType = 0;
         } else if (clientType == 21) {
            area.serverType = 1;
         } else if (clientType == 23) {
            area.serverType = 2;
         } else if (clientType == 22) {
            area.serverType = 3;
         } else if (clientType == 55) {
            area.serverType = 4;
         } else if (clientType == 57) {
            area.serverType = 5;
         } else if (clientType == 61) {
            area.serverType = 6;
         } else if (clientType == 62) {
            area.serverType = 7;
         } else if (clientType == 63) {
            area.serverType = 8;
         } else if (clientType == 68) {
            area.serverType = 9;
         } else if (clientType == 81) {
            area.serverType = 10;
         } else if (clientType == 90) {
            area.serverType = 11;
         } else if (clientType == 97) {
            area.serverType = 12;
         } else if (clientType == 104) {
            area.serverType = 13;
         } else if (clientType == 109) {
            area.serverType = 14;
         } else if (clientType == 114) {
            area.serverType = 15;
         } else if (clientType == 120) {
            area.serverType = 16;
         } else if (clientType == 126) {
            area.serverType = 17;
         } else if (clientType == 142) {
            area.serverType = 18;
         } else if (clientType == 147) {
            area.serverType = 19;
         } else if (clientType == 1) {
            if (clientCommand == 2555977) {
               UITitle title = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
               title.setTitleText(PCFarm.HelpCaption);
            } else if (clientCommand == 2425077) {
               String name = dis.readUTF();
               UITitle title = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
               title.setTitleText(name);
               UIForm.shoppingID = 0;
            }
         }

         area.addUIScroll();
      }

   }

   private static void initUserPicture(DataInputStream dis, short clientType, UIPicture pic, int clientCommand, UIForm form) throws IOException {
      int dengji;
      short picID;
      if ((clientType == 0 || clientType == 19) && pic.getType() == 0) {
         String picName = dis.readUTF();
         picID = dis.readShort();
         dengji = dis.readInt();
         pic.setLevel(picID);
         if (clientType == 0) {
            pic.playerPicType = 0;
         } else if (clientType == 19) {
            pic.playerPicType = 1;
         }

         pic.setGOPic(dengji);
      } else {
         byte WhetherUse;
         if (clientType == 5 && pic.getType() == 1) {
            WhetherUse = dis.readByte();
            pic.setItemNum(WhetherUse);
         } else if (clientType == 5 && pic.getType() == 2) {
            WhetherUse = dis.readByte();
            pic.isZyPic = true;
            pic.zyIndex = WhetherUse;
            pic.setImg(MainCanvas.zyMImg);
         } else {
            String name;
            if ((clientType == 36 || clientType == 37) && pic.getType() == 2) {
               boolean isLock = dis.readBoolean();
               name = null;
               if (clientType == 36) {
                  pic.imageFlip = 0;
                  UIPicture.isMyTradeLock = isLock;
               } else {
                  pic.imageFlip = 3;
                  UIPicture.isOtherTradeLock = isLock;
               }
            } else {
               short wpID;
               String pullulate;
               if (clientType == 3 && pic.getType() == 1) {
                  wpID = dis.readShort();
                  pic.setQuality(wpID);
                  pic.isWpPic = true;
                  picID = (short)(wpID % 1000);
                  pic.wpIndex = picID;
                  pic.setImg(MainCanvas.stuffMImg);
                  if (MainCanvas.curForm.clientCommand != 2424995 && MainCanvas.curForm.clientCommand != 2425079) {
                     if (MainCanvas.curForm.clientCommand == 2555906) {
                        pullulate = dis.readUTF();
                        PCFarm.m_sPropMane[PCFarm.m_bPropManeFinger] = pullulate;
                        ++PCFarm.m_bPropManeFinger;
                     }
                  } else {
                     if (PCIncrement.m_bGoodsHowCommodity[PCIncrement.m_nIndex] == 1) {
                        pic.setShowNum(false);
                     } else if (PCIncrement.m_bGoodsHowCommodity[PCIncrement.m_nIndex] > 1) {
                        pic.setShowNum(true, PCIncrement.m_bGoodsHowCommodity[PCIncrement.m_nIndex]);
                     }

                     PCIncrement.m_bSendChoose = true;
                     UITitle title = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
                     title.setTitleText("Giới thiệu thương phẩm");
                  }
               } else {
                  byte pullulate_limit;
                  short curDurable;
                  if (clientType == 16 && pic.getType() == 3) {
                     WhetherUse = dis.readByte();
                     picID = dis.readShort();
                     pic.setQuality(picID);
                     pullulate = dis.readUTF();
                     pullulate_limit = dis.readByte();
                     curDurable = (short)(picID % 1000);
                     pic.isEquipPic = true;
                     pic.equipIndex = curDurable;
                     pic.setImg(MainCanvas.stuffMImg);
                     pic.setEquipName(pullulate);
                     pic.setEquipType(WhetherUse);
                     pic.setEquipColorLevel(pullulate_limit);
                  } else {
                     short picID;
                     if (clientType == 115 && pic.getType() == 3) {
                        WhetherUse = dis.readByte();
                        picID = dis.readShort();
                        pic.setQuality(picID);
                        pullulate = dis.readUTF();
                        pullulate_limit = dis.readByte();
                        curDurable = dis.readShort();
                        picID = dis.readShort();
                        int fixPay = dis.readInt();
                        short picID = (short)(picID % 1000);
                        pic.isEquipPic = true;
                        pic.equipIndex = picID;
                        pic.setImg(MainCanvas.stuffMImg);
                        pic.setEquipName(pullulate);
                        pic.setEquipType(WhetherUse);
                        pic.setEquipCurDuable(curDurable);
                        pic.setEquipMaxDuable(picID);
                        pic.setEquipFixPay(fixPay);
                        pic.setEquipColorLevel(pullulate_limit);
                     } else {
                        byte gain;
                        if ((clientType == 34 || clientType == 35) && pic.getType() == 1) {
                           wpID = dis.readShort();
                           name = dis.readUTF();
                           byte num = dis.readByte();
                           pullulate_limit = dis.readByte();
                           gain = dis.readByte();
                           pic.setQuality(wpID);
                           pic.isWpPic = true;
                           picID = (short)(wpID % 1000);
                           pic.isWpPic = true;
                           pic.wpIndex = picID;
                           pic.setImg(MainCanvas.stuffMImg);
                           pic.setEquipName(name);
                           pic.setEquipType(pullulate_limit);
                           pic.setEquipColorLevel(gain);
                           if (num > 0) {
                              pic.setShowNum(true, num);
                           } else {
                              pic.setShowNum(false);
                           }
                        } else if ((clientType == 40 || clientType == 41) && pic.getType() == 1) {
                           wpID = dis.readShort();
                           pic.setQuality(wpID);
                           pic.isWpPic = true;
                           picID = (short)(wpID % 1000);
                           pic.isWpPic = true;
                           pic.wpIndex = picID;
                           pic.setImg(MainCanvas.stuffMImg);
                        } else {
                           byte crop_status_1;
                           String crop_status_2;
                           byte objty;
                           if (clientType == 27) {
                              MainCanvas.isPlayerData = dis.readBoolean();
                              if (MainCanvas.isPlayerData) {
                                 int ID = dis.readInt();
                                 name = dis.readUTF();
                                 dengji = dis.readInt();
                                 pullulate_limit = dis.readByte();
                                 gain = dis.readByte();
                                 crop_status_1 = dis.readByte();
                                 crop_status_2 = dis.readUTF();
                                 MainCanvas.m_nCharacterID[MainCanvas.m_bChooseCounter] = ID;
                                 MainCanvas.m_sCharacterName[MainCanvas.m_bChooseCounter] = name;
                                 MainCanvas.m_nGrade[MainCanvas.m_bChooseCounter] = dengji;
                                 MainCanvas.m_sPhyle[MainCanvas.m_bChooseCounter] = pullulate_limit;
                                 MainCanvas.m_bProfession[MainCanvas.m_bChooseCounter] = gain;
                                 MainCanvas.m_nScene[MainCanvas.m_bChooseCounter] = crop_status_2;
                                 MainCanvas.playPicture[MainCanvas.m_bChooseCounter] = pic;
                                 MainCanvas.m_bGenderX[MainCanvas.m_bChooseCounter] = crop_status_1;
                                 pic.otherP = new OtherPlayer((short)0, (short)0, MainCanvas.m_sPhyle[MainCanvas.m_bChooseCounter], MainCanvas.m_bProfession[MainCanvas.m_bChooseCounter], MainCanvas.m_bGenderX[MainCanvas.m_bChooseCounter]);
                                 GOManager.addObj(pic.otherP);
                                 byte objty = false;
                                 byte[] bodywhere = (byte[])null;
                                 short[][] pf = (short[][])null;
                                 OtherPlayer otherP = pic.otherP;
                                 byte ec;
                                 if (otherP != null) {
                                    objty = dis.readByte();
                                    pf = new short[objty][];
                                    bodywhere = new byte[objty];

                                    for(ec = 0; ec < objty; ++ec) {
                                       dis.readByte();
                                       bodywhere[ec] = dis.readByte();
                                       byte yyc;
                                       byte eft;
                                       if (bodywhere[ec] > 0) {
                                          yyc = dis.readByte();
                                          pf[ec] = new short[yyc];

                                          for(eft = 0; eft < yyc; ++eft) {
                                             pf[ec][eft] = dis.readShort();
                                          }
                                       } else if (bodywhere[ec] < 0) {
                                          yyc = (byte)ActiveGO.ORIEquipment[Math.abs(bodywhere[ec]) - 1 + (otherP.getPhyle() - 1) * (ActiveGO.ORIEquipment.length / 2)].length;
                                          pf[ec] = new short[yyc];

                                          for(eft = 0; eft < yyc; ++eft) {
                                             pf[ec][eft] = ActiveGO.ORIEquipment[Math.abs(bodywhere[ec]) - 1 + (otherP.getPhyle() - 1) * (ActiveGO.ORIEquipment.length / 2)][eft];
                                          }
                                       }
                                    }

                                    if (objty != 0) {
                                       MainCanvas.playPicture[MainCanvas.m_bChooseCounter].otherP.changeEquip(bodywhere, pf);
                                    } else if (objty == 0) {
                                       byte[] bodywhere_1;
                                       short[][] pf_1;
                                       if (MainCanvas.m_bProfession[MainCanvas.m_bChooseCounter] == 1) {
                                          bodywhere_1 = new byte[]{2, 4};
                                          pf_1 = new short[][]{{60, 61, 66, 47, 48, 49, 50, 51, 52}, {88}};
                                          MainCanvas.playPicture[MainCanvas.m_bChooseCounter].otherP.changeEquip(bodywhere_1, pf_1);
                                       } else if (MainCanvas.m_bProfession[MainCanvas.m_bChooseCounter] == 2) {
                                          bodywhere_1 = new byte[]{2, 4};
                                          pf_1 = new short[][]{{300, 301, 302, 303, 304, 305, 306, 307, 308}, {144}};
                                          MainCanvas.playPicture[MainCanvas.m_bChooseCounter].otherP.changeEquip(bodywhere_1, pf_1);
                                       } else if (MainCanvas.m_bProfession[MainCanvas.m_bChooseCounter] != 3 && MainCanvas.m_bProfession[MainCanvas.m_bChooseCounter] != 4 && MainCanvas.m_bProfession[MainCanvas.m_bChooseCounter] != 6) {
                                          if (MainCanvas.m_bProfession[MainCanvas.m_bChooseCounter] == 5) {
                                             bodywhere_1 = new byte[]{2, 4};
                                             pf_1 = new short[][]{{300, 301, 302, 303, 304, 305, 306, 307, 308}, {163}};
                                             MainCanvas.playPicture[MainCanvas.m_bChooseCounter].otherP.changeEquip(bodywhere_1, pf_1);
                                          }
                                       } else {
                                          bodywhere_1 = new byte[]{2, 4};
                                          pf_1 = new short[][]{{446, 447, 448, 449, 450, 451, 452, 453, 454}, {152}};
                                          MainCanvas.playPicture[MainCanvas.m_bChooseCounter].otherP.changeEquip(bodywhere_1, pf_1);
                                       }
                                    }
                                 }

                                 ec = dis.readByte();
                                 MainCanvas.m_bDelete[MainCanvas.m_bChooseCounter] = ec;
                                 boolean amendnickname = dis.readBoolean();
                                 MainCanvas.m_bAmendNickname[MainCanvas.m_bChooseCounter] = amendnickname;
                              }

                              ++MainCanvas.m_bChooseCounter;
                           } else if (clientType == 161) {
                              WhetherUse = dis.readByte();
                              pic.setWhetherEnvelop(WhetherUse);
                              if (WhetherUse == 0) {
                                 pic.setCropName("");
                                 pic.setGrowthPhase("");
                                 pic.setGrowthLimit((byte)0);
                                 pic.setWhetherGain((byte)0);
                                 pic.setCropStatus_1((byte)0);
                                 pic.setCropStatus_2("");
                                 pic.setGroundStatus((byte)-1);
                                 pic.setFrameIndex(0);
                                 pic.setMenuIndex(0);
                              } else {
                                 name = dis.readUTF();
                                 pic.setCropName(name);
                                 pullulate = dis.readUTF();
                                 pic.setGrowthPhase(pullulate);
                                 pullulate_limit = dis.readByte();
                                 pic.setGrowthLimit(pullulate_limit);
                                 gain = dis.readByte();
                                 pic.setWhetherGain(gain);
                                 crop_status_1 = dis.readByte();
                                 pic.setCropStatus_1(crop_status_1);
                                 crop_status_2 = dis.readUTF();
                                 pic.setCropStatus_2(crop_status_2);
                                 objty = dis.readByte();
                                 pic.setGroundStatus(objty);
                                 short picture_index = dis.readShort();
                                 int frame_index = dis.readInt();
                                 pic.setFrameIndex(frame_index);
                                 int menuindex = dis.readInt();
                                 pic.setMenuIndex(menuindex);
                                 pic.setQuality(picture_index);
                                 pic.isWpPic = true;
                                 short picID = (short)(picture_index % 1000);
                                 pic.wpIndex = picID;
                                 pic.setImg(MainCanvas.stuffMImg);
                              }
                           } else if (clientType == 175) {
                              WhetherUse = dis.readByte();
                              pic.setWhetherEnvelop(WhetherUse);
                              int picID = dis.readInt();
                              pic.setShowNum(false, (byte)1);
                              pic.quality = (byte)(picID / 1000 - 1);
                              short picid_1 = (short)(picID % 1000);
                              pic.isWpPic = true;
                              pic.wpIndex = picid_1;
                              pic.setImg(MainCanvas.stuffMImg);
                              PCTreasure.explain[PCTreasure.tally] = dis.readUTF();
                              ++PCTreasure.tally;
                              if (PCTreasure.tally >= 5) {
                                 UITextArea textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(13);
                                 textarea.setContent(PCTreasure.explain[0]);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

   }

   private static void initUserTable(DataInputStream dis, short clientType, UITable table, int clientCommand, UIForm form) throws IOException {
      byte num;
      UIComponent[] lineUic;
      TableItem firstItem;
      short[] itemW;
      boolean[] isCenter;
      int k;
      int k;
      String duty;
      short goodsID;
      String name;
      TableItem ti;
      int kk;
      Object uic2;
      short startX;
      short startY;
      UILabel oldLabel;
      UILabel label;
      short startY;
      short offset;
      short termHeight;
      byte payType;
      int clanID;
      String phyle;
      int week;
      int k;
      boolean right;
      int kk;
      UILabel uic2;
      TableItem ti;
      UIPicture oldPic;
      UIPicture pic;
      short startX;
      UILabel uic2;
      short startY;
      int num1;
      UILabel oldLabel;
      UILabel label;
      UIPicture pic;
      if (clientType == 67) {
         table.setTitleItem((TableItem)null);
         num = dis.readByte();
         lineUic = new UIComponent[3];
         firstItem = (TableItem)table.getItems().elementAt(0);
         itemW = firstItem.getTermWidth();
         isCenter = firstItem.getIsCenter();

         for(k = 0; k < firstItem.getTerms().size(); ++k) {
            lineUic[k] = (UIComponent)firstItem.getTerms().elementAt(k);
         }

         termHeight = firstItem.getTermHeight();
         table.removeAllItems();

         for(k = 0; k < num; ++k) {
            duty = dis.readUTF();
            goodsID = dis.readShort();
            name = dis.readUTF();
            ti = new TableItem();
            ti.resetTermWidth(3);
            ti.setTermHeight(termHeight);
            ti.setTermWidth(itemW);
            ti.setX((short)(table.getPositionX() + 3 + 1 + 1));
            ti.setY((short)(table.getPositionY() + 1 + 1 + 1 + k * termHeight));

            for(kk = 0; kk < 3; ++kk) {
               uic2 = null;
               startX = (short)(table.getPositionX() + 3 + 1 + 1);
               startY = (short)(table.getPositionY() + 1 + 1 + 1);
               if (kk == 0) {
                  oldLabel = (UILabel)lineUic[kk];
                  label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                  label.isRoll = true;
                  label.rollWidth = (short)(itemW[kk] * MainCanvas.screenW / 176);
                  label.setText(duty);
                  label.setPositionX((short)(startX + (ti.getCurWidth((byte)kk) + 1) * MainCanvas.screenW / 176));
                  label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                  uic2 = label;
               } else if (kk == 1) {
                  oldPic = (UIPicture)lineUic[kk];
                  pic = new UIPicture(oldPic.getPositionX(), oldPic.getPositionY(), oldPic.getWidth(), oldPic.getHeight(), (String)null, (String)null, oldPic.getType(), form);
                  if (goodsID != -1) {
                     pic.setQuality(goodsID);
                     pic.isWpPic = true;
                     startY = (short)(goodsID % 1000);
                     pic.wpIndex = startY;
                  }

                  pic.setImg(MainCanvas.stuffMImg);
                  pic.setPositionX((short)(startX + (ti.getCurWidth((byte)kk) + 1) * MainCanvas.screenW / 176));
                  pic.setPositionY((short)(startY + k * termHeight + (termHeight - pic.getImg().frame_h) / 2));
                  if (goodsID == -1) {
                     pic.setImg((MImage)null);
                  }

                  uic2 = pic;
               } else if (kk == 2) {
                  oldLabel = (UILabel)lineUic[kk];
                  label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                  label.isRoll = true;
                  label.rollWidth = (short)(itemW[kk] * MainCanvas.screenW / 176);
                  label.setText(name);
                  label.setPositionX((short)(startX + (ti.getCurWidth((byte)kk) + 1) * MainCanvas.screenW / 176));
                  label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                  uic2 = label;
                  if (k == 0) {
                     UITitle menuBar;
                     if (PCGem.m_bEnchaseSucceed == 1) {
                        menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                        menuBar.setMenuText("Tra tìm", 0);
                     } else if (name.equals("")) {
                        menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                        menuBar.setMenuText("Khảm nạm", 0);
                     } else {
                        menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                        menuBar.setMenuText("thao tác", 0);
                     }
                  }
               }

               ti.addItem((UIComponent)uic2);
            }

            table.addItem(ti);
         }

         if (MainCanvas.curForm.clientCommand == 1966081) {
            table.serverType = 11;
         } else if (MainCanvas.curForm.clientCommand == 1966087) {
            table.serverType = 12;
         } else if (MainCanvas.curForm.clientCommand == 1966100) {
            table.serverType = 13;
            UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
            menuBar.setMenuText("thao tác", 0);
         }

         table.addUIScroll();
      } else {
         short[] userDataType;
         boolean[] isCenter;
         short termHeight;
         if (clientType != 9 && clientType != 12 && clientType != 14 && clientType != 24 && clientType != 72 && clientType != 156) {
            byte mState;
            if (clientType != 30 && clientType != 74) {
               if (clientType != 127 && clientType != 128) {
                  if (clientType == 75) {
                     num = dis.readByte();
                     lineUic = new UIComponent[2];
                     firstItem = (TableItem)table.getItems().elementAt(0);
                     itemW = firstItem.getTermWidth();
                     userDataType = new short[]{1, 31};
                     isCenter = firstItem.getIsCenter();

                     for(k = 0; k < firstItem.getTerms().size(); ++k) {
                        lineUic[k] = (UIComponent)firstItem.getTerms().elementAt(k);
                     }

                     termHeight = firstItem.getTermHeight();
                     table.removeAllItems();

                     for(clanID = 0; clanID < num; ++clanID) {
                        phyle = dis.readUTF();
                        name = dis.readUTF();
                        ti = new TableItem();
                        ti.resetTermWidth(2);
                        ti.setTermHeight(termHeight);
                        ti.setTermWidth(itemW);
                        ti.setX((short)(table.getPositionX() + 3 + 1 + 1));
                        ti.setY((short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight() + clanID * termHeight));

                        for(kk = 0; kk < 2; ++kk) {
                           uic2 = null;
                           startX = (short)(table.getPositionX() + 3 + 1 + 1);
                           startY = (short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight());
                           if (userDataType[kk] == 1 && lineUic[kk] instanceof UILabel) {
                              oldLabel = (UILabel)lineUic[kk];
                              label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                              label.setText(phyle);
                              startY = 0;
                              if (isCenter[kk]) {
                                 startY = (short)((itemW[kk] - label.getWidth()) / 2);
                              }

                              label.setPositionX((short)((startX + ti.getCurWidth((byte)kk) + startY + 1) * MainCanvas.screenW / 176));
                              label.setPositionY((short)(startY + clanID * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                              uic2 = label;
                           } else if (userDataType[kk] == 31 && lineUic[kk] instanceof UILabel) {
                              oldLabel = (UILabel)lineUic[kk];
                              label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                              label.setText(name);
                              startY = 0;
                              if (isCenter[kk]) {
                                 startY = (short)((itemW[kk] - label.getWidth()) / 2);
                              }

                              label.setPositionX((short)((startX + ti.getCurWidth((byte)kk) + startY + 1) * MainCanvas.screenW / 176));
                              label.setPositionY((short)(startY + clanID * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                              uic2 = label;
                           }

                           ti.addItem(uic2);
                        }

                        table.addItem(ti);
                     }

                     table.addUIScroll();
                  } else if (clientType == 39) {
                     table.setTitleItem((TableItem)null);
                     num = dis.readByte();
                     UIForm.m_nDelieveX = new int[num];
                     UIForm.m_nDelieveY = new int[num];
                     UIForm.m_bState = new byte[num];
                     lineUic = new UIComponent[3];
                     firstItem = (TableItem)table.getItems().elementAt(0);
                     itemW = firstItem.getTermWidth();
                     isCenter = firstItem.getIsCenter();

                     for(k = 0; k < firstItem.getTerms().size(); ++k) {
                        lineUic[k] = (UIComponent)firstItem.getTerms().elementAt(k);
                     }

                     termHeight = firstItem.getTermHeight();
                     table.removeAllItems();

                     for(k = 0; k < num; ++k) {
                        long mailID = dis.readLong();
                        mState = dis.readByte();
                        right = dis.readBoolean();
                        String mailName = dis.readUTF();
                        uic2 = null;
                        String var67;
                        if (mState == 0) {
                           var67 = "Chưa đọc";
                        } else if (mState == 1) {
                           var67 = "Đã đọc chưa trả lời";
                        } else if (mState == 2) {
                           var67 = "Đã đọc và trả lời";
                        }

                        TableItem ti = new TableItem();
                        ti.resetTermWidth(3);
                        ti.setTermHeight(termHeight);
                        ti.setTermWidth(itemW);
                        ti.setX((short)(table.getPositionX() + 3 + 1 + 1));
                        ti.setY((short)(table.getPositionY() + 1 + 1 + 1 + k * termHeight));
                        ti.mailID = mailID;

                        for(int kk = 0; kk < 3; ++kk) {
                           UIComponent uic2 = null;
                           startY = (short)(table.getPositionX() + 3 + 1 + 1);
                           startY = (short)(table.getPositionY() + 1 + 1 + 1);
                           if (kk == 0) {
                              pic = (UIPicture)lineUic[kk];
                              UIPicture pic = new UIPicture(pic.getPositionX(), pic.getPositionY(), pic.getWidth(), pic.getHeight(), (String)null, (String)null, pic.getType(), form);
                              if (mState == 0) {
                                 UIForm.m_bState[k] = 1;
                              } else if (mState == 1) {
                                 UIForm.m_bState[k] = 2;
                              } else if (mState == 2) {
                                 UIForm.m_bState[k] = 3;
                              }

                              pic.isZyPic = true;
                              UIForm.m_nDelieveX[k] = startY + ti.getCurWidth((byte)kk) + 4;
                              UIForm.m_nDelieveY[k] = startY + k * termHeight + (termHeight - 13) / 2 + 5;
                              uic2 = pic;
                           } else {
                              UILabel label;
                              if (kk == 1) {
                                 label = (UILabel)lineUic[kk];
                                 label = new UILabel(label.getPositionX(), label.getPositionY(), label.getWidth(), label.getHeight(), label.getType(), label.getText(), form);
                                 if (right) {
                                    label.setText("Trả phí");
                                 } else {
                                    label.setText("Thường");
                                 }

                                 label.setPositionX((short)(startY + (ti.getCurWidth((byte)kk) + 1) * MainCanvas.screenW / 176));
                                 label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                                 uic2 = label;
                              } else if (kk == 2) {
                                 label = (UILabel)lineUic[kk];
                                 label = new UILabel(label.getPositionX(), label.getPositionY(), label.getWidth(), label.getHeight(), label.getType(), label.getText(), form);
                                 label.setText(mailName);
                                 label.setPositionX((short)(startY + (ti.getCurWidth((byte)kk) + 1) * MainCanvas.screenW / 176));
                                 label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                                 uic2 = label;
                              }
                           }

                           ti.addItem((UIComponent)uic2);
                        }

                        table.addItem(ti);
                     }

                     table.serverType = 6;
                     table.addUIScroll();
                  }
               } else {
                  num = dis.readByte();
                  lineUic = new UIComponent[2];
                  firstItem = (TableItem)table.getItems().elementAt(0);
                  itemW = firstItem.getTermWidth();
                  isCenter = firstItem.getIsCenter();

                  for(k = 0; k < firstItem.getTerms().size(); ++k) {
                     lineUic[k] = (UIComponent)firstItem.getTerms().elementAt(k);
                  }

                  termHeight = firstItem.getTermHeight();
                  table.removeAllItems();

                  for(k = 0; k < num; ++k) {
                     payType = dis.readByte();
                     goodsID = dis.readShort();
                     name = dis.readUTF();
                     ti = new TableItem();
                     ti.resetTermWidth(2);
                     ti.setTermHeight(termHeight);
                     ti.setTermWidth(itemW);
                     ti.setX((short)(table.getPositionX() + 3 + 1 + 1));
                     ti.setY((short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight() + k * termHeight));
                     ti.setPayType(payType);

                     for(kk = 0; kk < 2; ++kk) {
                        uic2 = null;
                        startX = (short)(table.getPositionX() + 3 + 1 + 1);
                        startY = (short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight());
                        if (kk == 0) {
                           oldPic = (UIPicture)lineUic[kk];
                           pic = new UIPicture(oldPic.getPositionX(), oldPic.getPositionY(), oldPic.getWidth(), oldPic.getHeight(), (String)null, (String)null, oldPic.getType(), form);
                           if (payType == 1) {
                              pic = new UIPicture(startX, startY, (short)0, (short)0, (String)null, (String)null, (byte)1, form);
                              pic.setImg(MainCanvas.stuffMImg);
                              pic.isWpPic = true;
                              startY = (short)(goodsID % 1000);
                              pic.wpIndex = startY;
                           } else if (payType == 2) {
                              pic = new UIPicture(startX, startY, (short)0, (short)0, "exp.png", (String)null, (byte)1, form);
                           } else if (payType == 3) {
                              pic = new UIPicture(startX, startY, (short)0, (short)0, "u-8.png", (String)null, (byte)1, form);
                           } else if (payType == 4) {
                              pic = new UIPicture(startX, startY, (short)0, (short)0, "u-2.png", (String)null, (byte)1, form);
                           } else if (payType == 5) {
                              pic = new UIPicture(startX, startY, (short)0, (short)0, "u-3.png", (String)null, (byte)1, form);
                           } else if (payType == 6) {
                              pic = new UIPicture(startX, startY, (short)0, (short)0, "exp.png", (String)null, (byte)1, form);
                           } else if (payType == 7) {
                              pic = new UIPicture(startX, startY, (short)0, (short)0, "cross.png", (String)null, (byte)1, form);
                           } else if (payType == 8) {
                              pic = new UIPicture(startX, startY, (short)0, (short)0, "long.png", (String)null, (byte)1, form);
                           }

                           pic.setPositionX((short)(startX + ti.getCurWidth((byte)kk) + 1));
                           pic.setPositionY((short)(startY + k * termHeight + (termHeight - pic.getImg().frame_h) / 2));
                           uic2 = pic;
                        } else if (kk == 1) {
                           oldLabel = (UILabel)lineUic[kk];
                           label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                           label.isRoll = true;
                           label.rollWidth = (short)(itemW[kk] * MainCanvas.screenW / 176);
                           label.setText(name);
                           startY = 0;
                           if (isCenter[kk]) {
                              startY = (short)((itemW[kk] - label.getWidth()) / 2);
                           }

                           label.setPositionX((short)((startX + ti.getCurWidth((byte)kk) + startY + 1) * MainCanvas.screenW / 176));
                           label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                           uic2 = label;
                        }

                        ti.addItem((UIComponent)uic2);
                     }

                     table.addItem(ti);
                  }

                  if (clientType == 128) {
                     table.serverType = 10;
                  }

                  table.addUIScroll();
               }
            } else {
               num = dis.readByte();
               lineUic = new UIComponent[2];
               firstItem = (TableItem)table.getItems().elementAt(0);
               itemW = firstItem.getTermWidth();
               userDataType = new short[]{1, 31};
               isCenter = firstItem.getIsCenter();

               for(k = 0; k < firstItem.getTerms().size(); ++k) {
                  lineUic[k] = (UIComponent)firstItem.getTerms().elementAt(k);
               }

               termHeight = firstItem.getTermHeight();
               table.removeAllItems();

               for(clanID = 0; clanID < num; ++clanID) {
                  phyle = dis.readUTF();
                  name = dis.readUTF();
                  short x = dis.readShort();
                  short y = dis.readShort();
                  ti = new TableItem();
                  ti.resetTermWidth(2);
                  ti.setTermHeight(termHeight);
                  ti.setTermWidth(itemW);
                  ti.setX((short)(table.getPositionX() + 3 + 1 + 1));
                  ti.setY((short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight() + clanID * termHeight));
                  ti.selectedNPCX = x;
                  ti.selectedNPCY = y;

                  for(kk = 0; kk < 2; ++kk) {
                     uic2 = null;
                     startX = (short)(table.getPositionX() + 3 + 1 + 1);
                     startY = (short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight());
                     if (userDataType[kk] == 1 && lineUic[kk] instanceof UILabel) {
                        oldLabel = (UILabel)lineUic[kk];
                        label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), (byte)6, oldLabel.getText(), form);
                        label.setText(phyle);
                        offset = 0;
                        if (isCenter[kk]) {
                           offset = (short)((itemW[kk] - label.getWidth()) / 2);
                        }

                        label.setPositionX((short)((startX + ti.getCurWidth((byte)kk) + offset + 1) * MainCanvas.screenW / 176));
                        label.setPositionY((short)(startY + clanID * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                        label.isRoll = true;
                        label.rollWidth = (short)(itemW[kk] * MainCanvas.screenW / 176);
                        uic2 = label;
                     } else if (userDataType[kk] == 31 && lineUic[kk] instanceof UILabel) {
                        oldLabel = (UILabel)lineUic[kk];
                        label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                        label.setText(name);
                        offset = 0;
                        if (isCenter[kk]) {
                           offset = (short)((itemW[kk] - label.getWidth()) / 2);
                        }

                        label.setPositionX((short)((startX + ti.getCurWidth((byte)kk) + offset + 1) * MainCanvas.screenW / 176));
                        label.setPositionY((short)(startY + clanID * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                        label.isRoll = true;
                        label.rollWidth = (short)(itemW[kk] * MainCanvas.screenW / 176);
                        uic2 = label;
                     }

                     ti.addItem(uic2);
                  }

                  table.addItem(ti);
               }

               boolean isReceiveTaskIconType = dis.readBoolean();
               if (isReceiveTaskIconType) {
                  for(week = 0; week < num; ++week) {
                     mState = dis.readByte();
                     ti = (TableItem)table.getItems().elementAt(week);
                     UILabel taskLabel = (UILabel)ti.getTerms().elementAt(0);
                     taskLabel.selectedNPCTaskIcon = mState;
                  }
               }

               if (clientType == 30) {
                  table.serverType = 4;
               }

               table.addUIScroll();
            }
         } else {
            if (clientType == 24) {
               table.setCanSelected(dis.readBoolean());
            }

            num = table.getRow();
            lineUic = new UIComponent[num];
            firstItem = (TableItem)table.getItems().elementAt(0);
            itemW = firstItem.getTermWidth();

            for(int j = 0; j < itemW.length; ++j) {
            }

            userDataType = new short[num];
            isCenter = firstItem.getIsCenter();

            for(k = 0; k < firstItem.getTerms().size(); ++k) {
               lineUic[k] = (UIComponent)firstItem.getTerms().elementAt(k);
            }

            termHeight = firstItem.getTermHeight();
            table.removeAllItems();
            payType = dis.readByte();
            byte subRow = dis.readByte();

            for(k = 0; k < num; ++k) {
               userDataType[k] = dis.readShort();
               System.out.println("userDataType[" + k + "] " + userDataType[k]);
            }

            for(k = 0; k < payType; ++k) {
               ti = new TableItem();
               ti.resetTermWidth(num);
               ti.setTermHeight(termHeight);
               ti.setTermWidth(itemW);
               ti.setX((short)(table.getPositionX() + 3 + 1 + 1));
               ti.setY((short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight() + k * termHeight));

               for(kk = 0; kk < subRow; ++kk) {
                  uic2 = null;
                  if (kk >= num) {
                     if (kk == 3) {
                        kk = dis.readInt();
                        ti.playerID = kk;
                     } else if (kk == 4) {
                        boolean olState = dis.readBoolean();
                        ti.isOL = olState;
                     } else if (kk == 5) {
                        byte camp = dis.readByte();
                        ti.playerCamp = camp;
                     }
                  } else {
                     startX = (short)(table.getPositionX() + 3 + 1 + 1);
                     startY = (short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight());
                     short offset;
                     if (userDataType[kk] == 5 && lineUic[kk] instanceof UIPicture) {
                        oldPic = (UIPicture)lineUic[kk];
                        pic = new UIPicture(oldPic.getPositionX(), oldPic.getPositionY(), oldPic.getWidth(), oldPic.getHeight(), (String)null, (String)null, oldPic.getType(), form);
                        pic.isZyPic = true;
                        byte picId = dis.readByte();
                        pic.zyIndex = picId;
                        pic.setImg(MainCanvas.zyMImg);
                        offset = 0;
                        if (isCenter[kk]) {
                           offset = (short)((itemW[kk] - pic.getImg().frame_w) / 2);
                        }

                        pic.setPositionX((short)(startX + ti.getCurWidth((byte)kk) + offset + 1));
                        pic.setPositionY((short)(startY + k * termHeight + (termHeight - pic.getImg().frame_h) / 2));
                        uic2 = pic;
                     } else if (userDataType[kk] == 1 && lineUic[kk] instanceof UILabel) {
                        oldLabel = (UILabel)lineUic[kk];
                        label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                        String labelStr = dis.readUTF();
                        label.setText(labelStr);
                        offset = 0;
                        if (isCenter[kk]) {
                           offset = (short)((itemW[kk] - label.getWidth()) / 2);
                        }

                        label.setPositionX((short)(startX + (ti.getCurWidth((byte)kk) + offset) * MainCanvas.screenW / 176 + 1));
                        label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                        uic2 = label;
                     } else if (userDataType[kk] == 2 && lineUic[kk] instanceof UILabel) {
                        oldLabel = (UILabel)lineUic[kk];
                        label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getType(), oldLabel.getNum1(), oldLabel.getNum2(), form);
                        num1 = dis.readInt();
                        int num2 = dis.readInt();
                        label.setNum1(num1);
                        label.setNum2(num2);
                        offset = 0;
                        if (isCenter[kk]) {
                           if (clientType == 24) {
                              offset = (short)((itemW[kk] - Util.getEspecialDoubleIntSize(num1, num2)[0]) / 2 - 3);
                           } else {
                              offset = (short)((itemW[kk] - Util.getDoubleIntSize(num1, num2)[0]) / 2 - 3);
                           }
                        }

                        if (clientType == 24) {
                           label.setPositionX((short)(startX + (ti.getCurWidth((byte)kk) + offset) * MainCanvas.screenW / 176 + 6));
                           label.setPositionY((short)(startY + k * termHeight + (termHeight - Util.getEspecialDoubleIntSize(num1, num2)[1]) / 2 + 1));
                        } else {
                           label.setPositionX((short)(startX + (ti.getCurWidth((byte)kk) + offset) * MainCanvas.screenW / 176));
                           label.setPositionY((short)(startY + k * termHeight + (termHeight - Util.getDoubleIntSize(num1, num2)[1]) / 2 + 1));
                        }

                        uic2 = label;
                     }

                     ti.addItem((UIComponent)uic2);
                  }
               }

               table.addItem(ti);
            }

            table.setCamp();
            table.sortOL();
            table.setOL();
            table.addUIScroll();
            UITitle menuBar;
            if (clientType == 9) {
               if (clientCommand == 589834) {
                  table.serverType = 5;
               } else if (clientCommand != 2555912 && clientCommand != 1703945) {
                  if (clientCommand != 2555941 && clientCommand != 2555943 && clientCommand != 2555945) {
                     table.serverType = 0;
                  } else {
                     table.serverType = 18;
                     menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                     if (payType == 0) {
                        menuBar.setMenuText("", 0);
                     } else {
                        menuBar.setMenuText("Tặng", 0);
                     }
                  }
               } else {
                  if (clientCommand == 2555912) {
                     table.serverType = 16;
                  } else if (clientCommand == 1703945) {
                     table.serverType = 0;
                  }

                  if (payType == 0) {
                     menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                     menuBar.setMenuText("", 0);
                  }
               }
            } else if (clientType == 12) {
               table.serverType = 1;
            } else if (clientType == 14) {
               table.serverType = 2;
            } else if (clientType == 24) {
               table.serverType = 3;
            } else if (clientType == 72) {
               if (clientCommand == 589845) {
                  table.serverType = 8;
               } else if (clientCommand == 2555913) {
                  table.serverType = 17;
                  if (payType == 0) {
                     menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                     menuBar.setMenuText("", 0);
                  }
               } else {
                  table.serverType = 8;
               }
            } else if (clientType == 156) {
               table.serverType = 15;
            }
         }
      }

      if (clientType == 162) {
         num = dis.readByte();
         lineUic = new UIComponent[3];
         firstItem = (TableItem)table.getItems().elementAt(0);
         itemW = firstItem.getTermWidth();
         isCenter = firstItem.getIsCenter();

         for(k = 0; k < firstItem.getTerms().size(); ++k) {
            lineUic[k] = (UIComponent)firstItem.getTerms().elementAt(k);
         }

         termHeight = firstItem.getTermHeight();
         table.removeAllItems();

         for(k = 0; k < num; ++k) {
            duty = dis.readUTF();
            week = dis.readInt();
            k = dis.readInt();
            ti = new TableItem();
            ti.resetTermWidth(3);
            ti.setTermHeight(termHeight);
            ti.setTermWidth(itemW);
            ti.setX((short)(table.getPositionX() + 3 + 1 + 1));
            ti.setY((short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight() + k * termHeight));
            ti.isOL = true;

            for(kk = 0; kk < 3; ++kk) {
               uic2 = null;
               startX = (short)(table.getPositionX() + 3 + 1 + 1);
               startY = (short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight());
               if (kk == 0) {
                  oldLabel = (UILabel)lineUic[kk];
                  label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                  label.isRoll = true;
                  label.rollWidth = (short)(itemW[kk] * MainCanvas.screenW / 176);
                  label.setText(duty);
                  num1 = MainCanvas.isLargeScreen ? -2 : 3;
                  label.setPositionX((short)((startX + ti.getCurWidth((byte)kk) + num1) * MainCanvas.screenW / 176));
                  label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                  uic2 = label;
               } else if (kk == 1) {
                  oldLabel = (UILabel)lineUic[kk];
                  label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                  label.setText(String.valueOf(week));
                  label.setPositionX((short)((startX + ti.getCurWidth((byte)kk) + 3) * MainCanvas.screenW / 176));
                  label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                  uic2 = label;
               } else if (kk == 2) {
                  oldLabel = (UILabel)lineUic[kk];
                  label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                  label.isRoll = true;
                  label.rollWidth = (short)(itemW[kk] * MainCanvas.screenW / 176);
                  label.setText(String.valueOf(k));
                  num1 = MainCanvas.isLargeScreen ? -2 : 3;
                  label.setPositionX((short)((startX + ti.getCurWidth((byte)kk) + num1) * MainCanvas.screenW / 176));
                  label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                  uic2 = label;
               }

               ti.addItem(uic2);
            }

            table.addItem(ti);
         }

         table.sortOL();
         table.setOL();
         table.addUIScroll();
      } else if (clientType == 159) {
         num = dis.readByte();
         lineUic = new UIComponent[3];
         firstItem = (TableItem)table.getItems().elementAt(0);
         itemW = firstItem.getTermWidth();
         isCenter = firstItem.getIsCenter();

         for(k = 0; k < firstItem.getTerms().size(); ++k) {
            lineUic[k] = (UIComponent)firstItem.getTerms().elementAt(k);
         }

         termHeight = firstItem.getTermHeight();
         table.removeAllItems();

         for(k = 0; k < num; ++k) {
            duty = dis.readUTF();
            phyle = dis.readUTF();
            k = dis.readInt();
            right = dis.readBoolean();
            TableItem ti = new TableItem();
            ti.resetTermWidth(3);
            ti.setTermHeight(termHeight);
            ti.setTermWidth(itemW);
            ti.setX((short)(table.getPositionX() + 3 + 1 + 1));
            ti.setY((short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight() + k * termHeight));
            ti.isOL = true;

            for(int kk = 0; kk < 3; ++kk) {
               UIComponent uic2 = null;
               startY = (short)(table.getPositionX() + 3 + 1 + 1);
               startX = (short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight());
               if (kk == 0) {
                  label = (UILabel)lineUic[kk];
                  oldLabel = new UILabel(label.getPositionX(), label.getPositionY(), label.getWidth(), label.getHeight(), label.getType(), label.getText(), form);
                  oldLabel.setText(duty);
                  oldLabel.setPositionX((short)((startY + ti.getCurWidth((byte)kk) + 3) * MainCanvas.screenW / 176));
                  oldLabel.setPositionY((short)(startX + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                  uic2 = oldLabel;
               } else if (kk == 1) {
                  label = (UILabel)lineUic[kk];
                  oldLabel = new UILabel(label.getPositionX(), label.getPositionY(), label.getWidth(), label.getHeight(), label.getType(), label.getText(), form);
                  oldLabel.setText(phyle);
                  oldLabel.setPositionX((short)((startY + ti.getCurWidth((byte)kk) + 3) * MainCanvas.screenW / 176));
                  oldLabel.setPositionY((short)(startX + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                  uic2 = oldLabel;
               } else if (kk == 2) {
                  label = (UILabel)lineUic[kk];
                  oldLabel = new UILabel(label.getPositionX(), label.getPositionY(), label.getWidth(), label.getHeight(), label.getType(), label.getText(), form);
                  String str = right ? "Có" : "Không";
                  oldLabel.setText(str);
                  oldLabel.setPositionX((short)((startY + ti.getCurWidth((byte)kk) + 3) * MainCanvas.screenW / 176));
                  oldLabel.setPositionY((short)(startX + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                  uic2 = oldLabel;
               }

               ti.playerID = k;
               ti.addItem(uic2);
            }

            table.addItem(ti);
         }

         table.sortOL();
         table.setOL();
         table.serverType = 7;
         table.addUIScroll();
      } else if (clientType != 54 && clientType != 64) {
         if (clientType == 153) {
            num = dis.readByte();
            lineUic = new UIComponent[2];
            firstItem = (TableItem)table.getItems().elementAt(0);
            itemW = firstItem.getTermWidth();
            isCenter = firstItem.getIsCenter();

            for(k = 0; k < firstItem.getTerms().size(); ++k) {
               lineUic[k] = (UIComponent)firstItem.getTerms().elementAt(k);
            }

            termHeight = firstItem.getTermHeight();
            table.removeAllItems();

            for(k = 0; k < num; ++k) {
               clanID = dis.readInt();
               phyle = dis.readUTF();
               name = dis.readUTF();
               ti = new TableItem();
               ti.playerID = clanID;
               ti.resetTermWidth(2);
               ti.setTermHeight(termHeight);
               ti.setTermWidth(itemW);
               ti.setX((short)(table.getPositionX() + 3 + 1 + 1));
               ti.setY((short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight() + k * termHeight));

               for(kk = 0; kk < 2; ++kk) {
                  uic2 = null;
                  startX = (short)(table.getPositionX() + 3 + 1 + 1);
                  startY = (short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight());
                  if (kk == 0) {
                     oldLabel = (UILabel)lineUic[kk];
                     label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                     label.isRoll = true;
                     label.rollWidth = (short)(itemW[kk] * MainCanvas.screenW / 176);
                     label.setText(phyle);
                     label.setPositionX((short)(startX + (ti.getCurWidth((byte)kk) + 3) * MainCanvas.screenW / 176));
                     label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                     uic2 = label;
                  } else if (kk == 1) {
                     oldLabel = (UILabel)lineUic[kk];
                     label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                     label.isRoll = true;
                     label.rollWidth = (short)((itemW[kk] - 24) * MainCanvas.screenW / 176);
                     label.setText(name);
                     label.setPositionX((short)(startX + (ti.getCurWidth((byte)kk) + 3) * MainCanvas.screenW / 176));
                     label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                     uic2 = label;
                  }

                  ti.addItem(uic2);
               }

               table.addItem(ti);
            }

            table.serverType = 14;
            table.addUIScroll();
         } else if (clientType == 169) {
            num = dis.readByte();
            lineUic = new UIComponent[3];
            firstItem = (TableItem)table.getItems().elementAt(0);
            itemW = firstItem.getTermWidth();
            isCenter = firstItem.getIsCenter();

            for(k = 0; k < firstItem.getTerms().size(); ++k) {
               lineUic[k] = (UIComponent)firstItem.getTerms().elementAt(k);
            }

            termHeight = firstItem.getTermHeight();
            table.removeAllItems();

            for(k = 0; k < num; ++k) {
               clanID = dis.readInt();
               phyle = dis.readUTF();
               name = dis.readUTF();
               long id = dis.readLong();
               ti = new TableItem();
               ti.m_lBattlefield = id;
               ti.resetTermWidth(3);
               ti.setTermHeight(termHeight);
               ti.setTermWidth(itemW);
               ti.setX((short)(table.getPositionX() + 3 + 1 + 1));
               ti.setY((short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight() + k * termHeight));

               for(kk = 0; kk < 3; ++kk) {
                  uic2 = null;
                  startX = (short)(table.getPositionX() + 3 + 1 + 1);
                  startY = (short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight());
                  if (kk == 0) {
                     oldLabel = (UILabel)lineUic[kk];
                     label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                     label.isRoll = true;
                     label.rollWidth = (short)(itemW[kk] * MainCanvas.screenW / 176);
                     label.setText("" + clanID);
                     offset = 0;
                     if (isCenter[kk]) {
                        offset = (short)((itemW[kk] - label.getWidth()) / 2);
                     }

                     label.setPositionX((short)(startX + (ti.getCurWidth((byte)kk) + offset) * MainCanvas.screenW / 176 + 3));
                     label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                     uic2 = label;
                  } else if (kk == 1) {
                     oldLabel = (UILabel)lineUic[kk];
                     label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                     label.isRoll = true;
                     label.rollWidth = (short)((itemW[kk] - 24) * MainCanvas.screenW / 176);
                     label.setText(phyle);
                     offset = 0;
                     if (isCenter[kk]) {
                        offset = (short)((itemW[kk] - label.getWidth()) / 2);
                     }

                     label.setPositionX((short)(startX + (ti.getCurWidth((byte)kk) + offset) * MainCanvas.screenW / 176 + 3));
                     label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                     uic2 = label;
                  } else if (kk == 2) {
                     oldLabel = (UILabel)lineUic[kk];
                     label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                     label.isRoll = true;
                     label.rollWidth = (short)((itemW[kk] - 24) * MainCanvas.screenW / 176);
                     label.setText(name);
                     label.setPositionX((short)(startX + (ti.getCurWidth((byte)kk) + 3) * MainCanvas.screenW / 176));
                     label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                     uic2 = label;
                  }

                  ti.addItem(uic2);
               }

               table.addItem(ti);
            }

            table.serverType = 19;
            table.addUIScroll();
         } else if (clientType == 176) {
            num = dis.readByte();
            lineUic = new UIComponent[2];
            firstItem = (TableItem)table.getItems().elementAt(0);
            itemW = firstItem.getTermWidth();
            isCenter = firstItem.getIsCenter();

            for(k = 0; k < firstItem.getTerms().size(); ++k) {
               lineUic[k] = (UIComponent)firstItem.getTerms().elementAt(k);
            }

            termHeight = firstItem.getTermHeight();
            table.removeAllItems();

            for(k = 0; k < num; ++k) {
               clanID = dis.readInt();
               phyle = dis.readUTF();
               name = dis.readUTF();
               ti = new TableItem();
               ti.resetTermWidth(3);
               ti.setTermHeight(termHeight);
               ti.setTermWidth(itemW);
               ti.setX((short)(table.getPositionX() + 3 + 1 + 1));
               ti.setY((short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight() + k * termHeight));

               for(kk = 0; kk < 2; ++kk) {
                  uic2 = null;
                  startX = (short)(table.getPositionX() + 3 + 1 + 1);
                  startY = (short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight());
                  if (kk == 0) {
                     oldLabel = (UILabel)lineUic[kk];
                     label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                     label.setText(phyle);
                     label.setPositionX((short)(startX + (ti.getCurWidth((byte)kk) + 5) * MainCanvas.screenW / 176));
                     label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                     uic2 = label;
                  } else if (kk == 1) {
                     oldLabel = (UILabel)lineUic[kk];
                     label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                     label.setText(name);
                     label.setPositionX((short)(startX + (ti.getCurWidth((byte)kk) + 5) * MainCanvas.screenW / 176));
                     label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                     uic2 = label;
                  }

                  ti.playerID = clanID;
                  ti.addItem(uic2);
               }

               table.addItem(ti);
            }

            table.serverType = 20;
            table.addUIScroll();
         }
      } else {
         num = dis.readByte();
         lineUic = new UIComponent[3];
         firstItem = (TableItem)table.getItems().elementAt(0);
         itemW = firstItem.getTermWidth();
         isCenter = firstItem.getIsCenter();

         for(k = 0; k < firstItem.getTerms().size(); ++k) {
            lineUic[k] = (UIComponent)firstItem.getTerms().elementAt(k);
         }

         termHeight = firstItem.getTermHeight();
         table.removeAllItems();

         for(k = 0; k < num; ++k) {
            payType = dis.readByte();
            phyle = dis.readUTF();
            name = dis.readUTF();
            int playerID = dis.readInt();
            boolean olState = dis.readBoolean();
            ti = new TableItem();
            ti.resetTermWidth(3);
            ti.setTermHeight(termHeight);
            ti.setTermWidth(itemW);
            ti.setX((short)(table.getPositionX() + 3 + 1 + 1));
            ti.setY((short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight() + k * termHeight));
            ti.isOL = olState;

            for(kk = 0; kk < 3; ++kk) {
               UIComponent uic2 = null;
               startX = (short)(table.getPositionX() + 3 + 1 + 1);
               startY = (short)(table.getPositionY() + 1 + 1 + 1 + table.getTitleItem().getTermHeight());
               if (kk == 0) {
                  UIPicture oldPic = (UIPicture)lineUic[kk];
                  pic = new UIPicture(oldPic.getPositionX(), oldPic.getPositionY(), oldPic.getWidth(), oldPic.getHeight(), (String)null, (String)null, oldPic.getType(), form);
                  pic.isZyPic = true;
                  pic.zyIndex = payType;
                  pic.setImg(MainCanvas.zyMImg);
                  offset = 0;
                  if (isCenter[kk]) {
                     offset = (short)((itemW[kk] - pic.getImg().frame_w) / 2);
                  }

                  pic.setPositionX((short)(startX + ti.getCurWidth((byte)kk) + offset + 1));
                  pic.setPositionY((short)(startY + k * termHeight + (termHeight - pic.getImg().frame_h) / 2));
                  uic2 = pic;
               } else if (kk == 1) {
                  oldLabel = (UILabel)lineUic[kk];
                  label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                  label.setText(phyle);
                  label.setPositionX((short)(startX + (ti.getCurWidth((byte)kk) + 3) * MainCanvas.screenW / 176));
                  label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                  uic2 = label;
               } else if (kk == 2) {
                  oldLabel = (UILabel)lineUic[kk];
                  label = new UILabel(oldLabel.getPositionX(), oldLabel.getPositionY(), oldLabel.getWidth(), oldLabel.getHeight(), oldLabel.getType(), oldLabel.getText(), form);
                  label.setText(name);
                  label.setPositionX((short)(startX + (ti.getCurWidth((byte)kk) + 3) * MainCanvas.screenW / 176));
                  label.setPositionY((short)(startY + k * termHeight + (termHeight - MainCanvas.CHARH) / 2));
                  uic2 = label;
               }

               ti.playerID = playerID;
               ti.addItem((UIComponent)uic2);
            }

            table.addItem(ti);
         }

         table.sortOL();
         table.setOL();
         table.serverType = 7;
         table.addUIScroll();
      }

   }

   private static void initUserLabel(DataInputStream dis, short clientType, UILabel label, int clientCommand, UIForm form) throws IOException {
      String name;
      if (clientType == 1 && label.getType() == 0) {
         name = dis.readUTF();
         if (form.clientCommand == 393219) {
            UILabel label_c = (UILabel)form.getComponents().elementAt(7);
            if (label_c == label) {
               int ww = label_c.getWidth();
               label_c.isRoll = true;
               label_c.isAlwaysScroll = true;
               label_c.rollWidth = (short)(ww * MainCanvas.screenW / 176);
            }
         }

         label.setText(name);
         if (form.clientCommand == 2293873) {
            label.setPositionX((short)(MainCanvas.screenW >> 1));
            label.setStrCenterXAnchor(true);
         }
      } else if (clientType == 116) {
         name = dis.readUTF();
         byte colorIndex = dis.readByte();
         label.setText(name);
         label.setTextColor(UIGrid.getStuffWordColor(colorIndex));
      } else {
         int price;
         int num2;
         if (clientType == 73 && label.getType() == 0) {
            price = dis.readInt();

            for(num2 = 0; num2 < price; ++num2) {
               label.textGroup = Util.addArray(label.textGroup, num2, dis.readUTF());
            }

            label.setText(label.textGroup[0]);
         } else {
            short labelNum;
            if (clientType == 3 && label.getType() == 3) {
               labelNum = dis.readShort();
               label.setNum1(labelNum);
            } else if (clientType == 3 && label.getType() == 0) {
               labelNum = dis.readShort();
               label.setText(String.valueOf(labelNum));
            } else if (clientType == 4 && label.getType() == 3) {
               price = dis.readInt();
               if (MainCanvas.curForm.clientCommand != 2424995 && MainCanvas.curForm.clientCommand != 2424839 && MainCanvas.curForm.clientCommand != 2425079) {
                  label.setNum1(price);
               } else if (PCIncrement.m_bBuyMoney != 3 && PCIncrement.m_bBuyMoney != 8) {
                  label.setNum1(price);
               } else {
                  String num = price / 10 + "." + price % 10 + "元";
                  label.setType((byte)0);
                  label.setText(num);
               }
            } else if (clientType == 4 && label.getType() == 0) {
               price = dis.readInt();
               label.setText(String.valueOf(price));
            } else if (clientType == 2 && label.getType() == 2) {
               price = dis.readInt();
               num2 = dis.readInt();
               label.setNum1(price);
               label.setNum2(num2);
               if (MainCanvas.curForm.clientCommand == 2228225) {
                  PCGemJoinOrRemove.m_bGemAmount = (byte)price;
               }
            } else if (clientType == 1 && label.getType() == 1) {
               name = dis.readUTF();
               label.setText(name);
               if (MainCanvas.curForm.clientCommand == 458753 || MainCanvas.curForm.clientCommand == 917507 || MainCanvas.curForm.clientCommand == 983041 || MainCanvas.curForm.clientCommand == 1179650 || MainCanvas.curForm.clientCommand == 1900552 || MainCanvas.curForm.clientCommand == 1376267 || MainCanvas.curForm.clientCommand == 393231 || MainCanvas.curForm.clientCommand == 655363 || MainCanvas.curForm.clientCommand == 1769475 || MainCanvas.curForm.clientCommand == 1703948 || MainCanvas.curForm.clientCommand == 2031617 || MainCanvas.curForm.clientCommand == 2031635 || MainCanvas.curForm.clientCommand == 2228231 || MainCanvas.curForm.clientCommand == 1966113 || MainCanvas.curForm.clientCommand == 1966093 || MainCanvas.curForm.clientCommand == 1966087 || MainCanvas.curForm.clientCommand == 1966100) {
                  if (UIGrid.curSelStrColor == 16777215) {
                     UIGrid.curSelStrColor = Cons.COLOR_FONT_1;
                  }

                  label.setTextColor(UIGrid.curSelStrColor);
               }
            } else if (clientType == 4 && label.getType() == 4) {
               price = dis.readInt();
               label.setText(Integer.toString(price));
            } else if (clientType == 1 && label.getType() == 4) {
               name = dis.readUTF();
               label.setText(name);
            }
         }
      }

   }

   private static void initUserList(DataInputStream dis, short clientType, UIList list, int clientCommand, UIForm form) throws IOException {
      byte num;
      int i;
      String listItemStr;
      ListItem item;
      if (clientType != 130 && clientType != 137) {
         int menuindex;
         ListItem item;
         boolean isOL;
         if (clientType == 155) {
            list.removeAll();
            num = dis.readByte();

            for(i = 0; i < num; ++i) {
               listItemStr = dis.readUTF();
               isOL = dis.readBoolean();
               menuindex = dis.readInt();
               item = new ListItem(listItemStr, (byte)7, (byte)0, list.getListWidth());
               item.setOL(isOL);
               item.playerID = menuindex;
               list.addItem(item);
            }

            UIList.addUIScroll(list, (byte)0);
         } else {
            ListItem item;
            if (clientType == 157) {
               list.removeAll();
               num = dis.readByte();

               for(i = 0; i < num; ++i) {
                  listItemStr = dis.readUTF();
                  isOL = dis.readBoolean();
                  item = new ListItem(listItemStr, (byte)7, (byte)0, list.getListWidth());
                  item.setOL(isOL);
                  list.addItem(item);
               }

               UIList.addUIScroll(list, (byte)0);
            } else {
               int id;
               String listItemStr;
               if (clientType != 129 && clientType != 149) {
                  ListItem item;
                  short objX;
                  if (clientType == 154) {
                     list.removeAll();
                     num = dis.readByte();

                     for(i = 0; i < num; ++i) {
                        id = dis.readInt();
                        listItemStr = dis.readUTF();
                        short objX = dis.readShort();
                        objX = dis.readShort();
                        item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                        item.objX = objX;
                        item.objY = objX;
                        item.itemId = id;
                        list.addItem(item);
                     }

                     list.serverType = 86;
                     UIList.addUIScroll(list, (byte)0);
                  } else {
                     byte id;
                     if (clientType != 77 && clientType != 84 && clientType != 91 && clientType != 98 && clientType != 105 && clientType != 111 && clientType != 117 && clientType != 122 && clientType != 138 && clientType != 143) {
                        if (clientType != 78 && clientType != 85 && clientType != 92 && clientType != 99 && clientType != 106 && clientType != 112 && clientType != 118 && clientType != 123 && clientType != 139 && clientType != 144) {
                           if (clientType != 79 && clientType != 82 && clientType != 86 && clientType != 87 && clientType != 93 && clientType != 94 && clientType != 100 && clientType != 101) {
                              if (clientType != 80 && clientType != 89 && clientType != 96 && clientType != 103 && clientType != 108 && clientType != 121 && clientType != 125 && clientType != 141 && clientType != 146) {
                                 int id;
                                 byte taskType;
                                 if (clientType != 83 && clientType != 88 && clientType != 95 && clientType != 102 && clientType != 107 && clientType != 113 && clientType != 119 && clientType != 124 && clientType != 140 && clientType != 145) {
                                    if (clientType != 43 && clientType != 151) {
                                       if (clientType == 76) {
                                          list.removeAll();
                                          num = dis.readByte();

                                          for(i = 0; i < num; ++i) {
                                             listItemStr = dis.readUTF();
                                             item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                             list.addItem(item);
                                          }
                                       } else if (clientType == 26) {
                                          list.removeAll();
                                          num = dis.readByte();

                                          for(i = 0; i < num; ++i) {
                                             listItemStr = dis.readUTF();
                                             item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                             list.addItem(item);
                                          }

                                          if (clientType == 26) {
                                             list.serverType = 5;
                                          }

                                          UIList.addUIScroll(list, (byte)0);
                                       } else {
                                          byte colorIndex;
                                          short level;
                                          int[] talkID;
                                          if (clientType != 45 && clientType != 18 && clientType != 33) {
                                             if (clientType == 44) {
                                                list.removeAll();
                                                UIList.npcTransformID = dis.readInt();
                                                num = dis.readByte();

                                                for(i = 0; i < num; ++i) {
                                                   id = dis.readByte();
                                                   listItemStr = dis.readUTF();
                                                   item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                                   item.setTransformID(id);
                                                   list.addItem(item);
                                                }

                                                list.serverType = 8;
                                                UIList.addUIScroll(list, (byte)0);
                                             } else {
                                                short num;
                                                if (clientType != 46 && clientType != 47 && clientType != 49 && clientType != 50 && clientType != 48 && clientType != 51 && clientType != 71) {
                                                   if (clientType == 56) {
                                                      list.removeAll();
                                                      num = dis.readByte();

                                                      for(i = 0; i < num; ++i) {
                                                         listItemStr = dis.readUTF();
                                                         item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                                         item.itemId = dis.readInt();
                                                         item.timeStamp = dis.readLong();
                                                         list.addItem(item);
                                                      }

                                                      list.serverType = 16;
                                                      UIList.addUIScroll(list, (byte)0);
                                                   } else if (clientType == 58) {
                                                      list.removeAll();
                                                      num = dis.readByte();

                                                      for(i = 0; i < num; ++i) {
                                                         listItemStr = dis.readUTF();
                                                         byte rank = dis.readByte();
                                                         item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                                         item.rankType = rank;
                                                         list.addItem(item);
                                                      }

                                                      list.serverType = 17;
                                                      UIList.addUIScroll(list, (byte)0);
                                                   } else if (clientType == 60) {
                                                      list.removeAll();
                                                      num = dis.readByte();

                                                      for(i = 0; i < num; ++i) {
                                                         listItemStr = dis.readUTF();
                                                         item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                                         list.addItem(item);
                                                      }

                                                      UIList.addUIScroll(list, (byte)0);
                                                   } else if (clientType != 65 && clientType != 70) {
                                                      if (clientType == 20) {
                                                         list.removeAll();
                                                         num = dis.readByte();
                                                         talkID = new int[num];

                                                         for(id = 0; id < num; ++id) {
                                                            listItemStr = dis.readUTF();
                                                            item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                                            list.addItem(item);
                                                         }

                                                         list.serverType = 3;
                                                         UIList.addUIScroll(list, (byte)0);
                                                      } else if (clientType == 25) {
                                                         list.setType((byte)1);
                                                         list.removeAll();
                                                         num = dis.readByte();
                                                         list.serverType = 4;
                                                         if (num == 0) {
                                                            return;
                                                         }

                                                         SITeam.UIHeaderID = dis.readInt();

                                                         for(i = 0; i < num; ++i) {
                                                            listItemStr = dis.readUTF();
                                                            id = dis.readInt();
                                                            boolean isLeader = false;
                                                            if (SITeam.UIHeaderID == id) {
                                                               isLeader = true;
                                                            }

                                                            byte profession = dis.readByte();
                                                            level = dis.readShort();
                                                            int curHP = dis.readInt();
                                                            int maxHP = dis.readInt();
                                                            int curMP = dis.readInt();
                                                            int maxMP = dis.readInt();
                                                            ListItem item = new ListItem((byte)2);
                                                            item.setTeamAttribute(listItemStr, id, isLeader, profession, level, curHP, maxHP, curMP, maxMP);
                                                            list.addItem(item);
                                                         }

                                                         if (num > list.getCanShowCol((byte)2) && list.getPanel().getPanelVScroll() == null) {
                                                            list.getPanel().addPanelVScrollBar(false);
                                                         }

                                                         UIList.addUIScroll(list, (byte)0);
                                                      } else {
                                                         ListItem item;
                                                         if (clientType == 32) {
                                                            list.removeAll();
                                                            num = dis.readByte();

                                                            for(i = 0; i < num; ++i) {
                                                               item = new ListItem((byte)3);
                                                               listItemStr = dis.readUTF();
                                                               menuindex = dis.readInt();
                                                               item.setWarAttribute(menuindex);
                                                               item.setItemName(listItemStr);
                                                               list.addItem(item);
                                                            }

                                                            if (num > list.getCanShowCol((byte)0) && list.getPanel().getPanelVScroll() == null) {
                                                               list.getPanel().addPanelVScrollBar(true);
                                                            }

                                                            list.getPanel().addUIScroll((short)num, list.getCanShowCol((byte)0));
                                                         } else if (clientType == 158) {
                                                            list.removeAll();
                                                            num = dis.readByte();

                                                            for(i = 0; i < num; ++i) {
                                                               item = new ListItem((byte)0);
                                                               item.setItemName(dis.readUTF());
                                                               item.itemId = dis.readByte();
                                                               list.addItem(item);
                                                            }

                                                            list.getPanel().addUIScroll(num, list.getCanShowCol((byte)0));
                                                         } else if (clientType == 52) {
                                                            list.removeAll();
                                                            num = dis.readByte();

                                                            for(i = 0; i < num; ++i) {
                                                               item = new ListItem((byte)0);
                                                               item.teammateId = dis.readInt();
                                                               item.setItemName(dis.readUTF());
                                                               list.addItem(item);
                                                            }

                                                            list.getPanel().addUIScroll(num, list.getCanShowCol((byte)0));
                                                         } else if (clientType == 134) {
                                                            list.removeAll();
                                                            num = dis.readShort();
                                                            PCExchange.name = new String[num];
                                                            PCExchange.name_1 = new String[num];
                                                            PCExchange.name_2 = new String[num];
                                                            PCExchange.m_nMax = new int[num];

                                                            for(i = 0; i < num; ++i) {
                                                               listItemStr = dis.readUTF();
                                                               item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                                               list.addItem(item);
                                                               PCExchange.name[i] = dis.readUTF();
                                                               PCExchange.name_1[i] = dis.readUTF();
                                                               PCExchange.name_2[i] = dis.readUTF();
                                                               PCExchange.m_nMax[i] = dis.readInt();
                                                            }

                                                            if (clientType == 134) {
                                                               list.serverType = 71;
                                                            }

                                                            UIList.addUIScroll(list, (byte)0);
                                                            UILabel label = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
                                                            label.setText(PCExchange.name[0]);
                                                            label.setPositionX((short)(MainCanvas.screenW - label.getWidth() >> 1));
                                                            UILabel label_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(7);
                                                            label_1.setText(PCExchange.name_1[0]);
                                                            label_1.setPositionX((short)(MainCanvas.screenW - label_1.getWidth() >> 1));
                                                            UILabel label_2 = (UILabel)MainCanvas.curForm.getComponents().elementAt(8);
                                                            label_2.setText(PCExchange.name_2[0]);
                                                            label_2.setPositionX((short)(MainCanvas.screenW - label_2.getWidth() >> 1));
                                                         } else if (clientType != 133 && clientType != 136) {
                                                            if (clientType != 131 && clientType != 132) {
                                                               UITextArea area;
                                                               if (clientType == 150) {
                                                                  list.removeAll();
                                                                  num = dis.readByte();
                                                                  PCMail.m_sBingIntro = new String[num];

                                                                  for(i = 0; i < num; ++i) {
                                                                     listItemStr = dis.readUTF();
                                                                     item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                                                     list.addItem(item);
                                                                     PCMail.m_sBingIntro[i] = dis.readUTF();
                                                                  }

                                                                  area = (UITextArea)MainCanvas.curForm.getComponents().elementAt(4);
                                                                  area.setContent(PCMail.m_sBingIntro[0]);
                                                                  UIList.addUIScroll(list, (byte)0);
                                                                  list.serverType = 83;
                                                               } else if (clientType == 152) {
                                                                  list.removeAll();
                                                                  num = dis.readByte();
                                                                  PCMail.m_nIndex = new int[num];
                                                                  PCMail.m_sContent = new String[num];
                                                                  PCMail.m_bReboundKind = new byte[num];

                                                                  for(i = 0; i < num; ++i) {
                                                                     PCMail.m_nIndex[i] = dis.readInt();
                                                                     listItemStr = dis.readUTF();
                                                                     item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                                                     list.addItem(item);
                                                                     PCMail.m_sContent[i] = dis.readUTF();
                                                                     PCMail.m_bReboundKind[i] = dis.readByte();
                                                                  }

                                                                  if (num != 0) {
                                                                     area = (UITextArea)MainCanvas.curForm.getComponents().elementAt(6);
                                                                     area.setContent(PCMail.m_sContent[0]);
                                                                     UIList.addUIScroll(list, (byte)0);
                                                                     list.serverType = 85;
                                                                  }
                                                               } else if (clientType == 166) {
                                                                  list.removeAll();
                                                                  num = dis.readByte();

                                                                  for(i = 0; i < num; ++i) {
                                                                     listItemStr = dis.readUTF();
                                                                     id = dis.readInt();
                                                                     menuindex = dis.readInt();
                                                                     int color = dis.readInt();
                                                                     item = new ListItem(listItemStr, (byte)9, (byte)0, list.getListWidth());
                                                                     item.setCharacterIndex(id);
                                                                     item.setMenuIndex(menuindex);
                                                                     list.addItem(item);
                                                                     item.setColo(color);
                                                                  }

                                                                  if (num != 0) {
                                                                     UIList.addUIScroll(list, (byte)9);
                                                                     list.serverType = 87;
                                                                  } else {
                                                                     UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                                                                     menuBar.setMenuText("", 0);
                                                                  }

                                                                  String name = dis.readUTF();
                                                                  UITitle title = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
                                                                  title.setTitleText(name);
                                                               } else if (clientType == 168) {
                                                                  list.removeAll();
                                                                  num = dis.readByte();

                                                                  for(i = 0; i < num; ++i) {
                                                                     id = dis.readByte();
                                                                     listItemStr = dis.readUTF();
                                                                     item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                                                     item.itemId = id;
                                                                     list.addItem(item);
                                                                  }

                                                                  list.serverType = 88;
                                                               } else if (clientType == 170) {
                                                                  UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                                                                  list.removeAll();
                                                                  byte num = dis.readByte();

                                                                  for(id = 0; id < num; ++id) {
                                                                     listItemStr = dis.readUTF();
                                                                     item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                                                     item.m_bBattlefieldGenre = dis.readShort();
                                                                     item.m_bBattlefieldExamine = dis.readShort();
                                                                     list.addItem(item);
                                                                  }

                                                                  list.serverType = 89;
                                                                  item = (ListItem)list.getItems().elementAt(0);
                                                                  if (item.m_bBattlefieldExamine == 4125) {
                                                                     menuBar.setMenuText("", 0);
                                                                  } else {
                                                                     menuBar.setMenuText("thao tác", 0);
                                                                  }
                                                               } else if (clientType != 171 && clientType != 172 && clientType != 173) {
                                                                  if (clientType == 174) {
                                                                     list.removeAll();
                                                                     num = dis.readByte();
                                                                     if (MainCanvas.curForm.clientCommand == 3145738) {
                                                                        PCHang.m_bIncrementGenre = new byte[num];
                                                                     }

                                                                     for(i = 0; i < num; ++i) {
                                                                        listItemStr = dis.readUTF();
                                                                        if (MainCanvas.curForm.clientCommand == 3145738) {
                                                                           PCHang.m_bIncrementGenre[i] = dis.readByte();
                                                                        }

                                                                        item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                                                        list.addItem(item);
                                                                     }

                                                                     UIList.addUIScroll(list, (byte)0);
                                                                  }
                                                               } else {
                                                                  list.removeAll();
                                                                  num = dis.readByte();

                                                                  for(i = 0; i < num; ++i) {
                                                                     id = dis.readInt();
                                                                     listItemStr = dis.readUTF();
                                                                     item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                                                     item.itemId = id;
                                                                     list.addItem(item);
                                                                  }

                                                                  UIList.addUIScroll(list, (byte)0);
                                                               }
                                                            } else {
                                                               list.removeAll();
                                                               if (MainCanvas.curForm.clientCommand == 2425029) {
                                                                  num = dis.readByte();

                                                                  for(i = 0; i < num; ++i) {
                                                                     listItemStr = dis.readUTF();
                                                                     item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                                                     list.addItem(item);
                                                                  }

                                                                  list.serverType = 72;
                                                               }

                                                               UIList.addUIScroll(list, (byte)0);
                                                            }
                                                         } else {
                                                            list.removeAll();
                                                            num = dis.readShort();

                                                            for(i = 0; i < num; ++i) {
                                                               listItemStr = dis.readUTF();
                                                               item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                                               list.addItem(item);
                                                            }

                                                            UIList.addUIScroll(list, (byte)0);
                                                         }
                                                      }
                                                   } else {
                                                      list.removeAll();
                                                      num = dis.readByte();

                                                      for(i = 0; i < num; ++i) {
                                                         listItemStr = dis.readUTF();
                                                         item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                                         item.itemId = dis.readInt();
                                                         list.addItem(item);
                                                      }

                                                      UIList.addUIScroll(list, (byte)0);
                                                   }
                                                } else {
                                                   list.removeAll();
                                                   num = dis.readShort();
                                                   talkID = new int[num];

                                                   for(id = 0; id < num; ++id) {
                                                      talkID[id] = dis.readInt();
                                                      listItemStr = dis.readUTF();
                                                      if (clientType != 48 && clientType != 51) {
                                                         item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                                         list.addItem(item);
                                                      } else {
                                                         colorIndex = dis.readByte();
                                                         item = new ListItem(listItemStr, (byte)1, (byte)0, list.getListWidth());
                                                         taskType = -1;
                                                         switch(colorIndex) {
                                                         case 1:
                                                            taskType = 6;
                                                            break;
                                                         case 2:
                                                            taskType = 7;
                                                            break;
                                                         case 3:
                                                            taskType = 5;
                                                         }

                                                         item.setTaskType(taskType);
                                                         list.addItem(item);
                                                      }
                                                   }

                                                   list.setListID(talkID);
                                                   if (clientType == 48) {
                                                      list.serverType = 12;
                                                   } else if (clientType == 51) {
                                                      list.serverType = 15;
                                                   } else if (clientType == 46) {
                                                      list.serverType = 10;
                                                   } else if (clientType == 47) {
                                                      list.serverType = 11;
                                                   } else if (clientType == 49) {
                                                      list.serverType = 13;
                                                   } else if (clientType == 50) {
                                                      list.serverType = 14;
                                                   } else if (clientType == 71) {
                                                      list.serverType = 29;
                                                   }

                                                   UIList.addUIScroll(list, (byte)0);
                                                }
                                             }
                                          } else {
                                             list.removeAll();
                                             num = dis.readByte();
                                             talkID = new int[num];

                                             for(id = 0; id < num; ++id) {
                                                listItemStr = dis.readUTF();
                                                talkID[id] = dis.readInt();
                                                colorIndex = dis.readByte();
                                                if (clientType == 33) {
                                                   objX = dis.readShort();
                                                   level = dis.readShort();
                                                   ListItem item = new ListItem(listItemStr, (byte)0, (byte)1, list.getListWidth());
                                                   item.objX = objX;
                                                   item.objY = level;
                                                   list.addItem(item);
                                                } else if (clientType == 18) {
                                                   item = new ListItem(listItemStr, (byte)1, (byte)0, list.getListWidth());
                                                   item.setTaskType(colorIndex);
                                                   list.addItem(item);
                                                } else if (clientType == 45) {
                                                   item = new ListItem(listItemStr, (byte)1, (byte)0, list.getListWidth());
                                                   taskType = dis.readByte();
                                                   boolean canRefresh = dis.readBoolean();
                                                   item.setTaskType2(taskType);
                                                   item.setCanRefresh(canRefresh);
                                                   byte taskType = -1;
                                                   switch(colorIndex) {
                                                   case 1:
                                                      taskType = 6;
                                                      break;
                                                   case 2:
                                                      taskType = 7;
                                                      break;
                                                   case 3:
                                                      taskType = 5;
                                                   }

                                                   item.setTaskType(taskType);
                                                   list.addItem(item);
                                                }
                                             }

                                             list.setListID(talkID);
                                             if (clientType == 45) {
                                                list.serverType = 9;
                                             } else if (clientType == 18) {
                                                if (list.getItems().size() > 0) {
                                                   list.npcTaskKey(((ListItem)list.getItems().elementAt(0)).getTaskType());
                                                }

                                                list.serverType = 2;
                                             } else if (clientType == 33) {
                                                list.serverType = 7;
                                             }

                                             UIList.addUIScroll(list, (byte)0);
                                          }
                                       }
                                    } else {
                                       list.removeAll();
                                       num = dis.readByte();

                                       for(i = 0; i < num; ++i) {
                                          listItemStr = dis.readUTF();
                                          item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                          list.addItem(item);
                                       }

                                       if (clientType == 151) {
                                          list.serverType = 84;
                                       }

                                       UIList.addUIScroll(list, (byte)0);
                                    }
                                 } else {
                                    list.removeAll();
                                    num = dis.readByte();

                                    for(i = 0; i < num; ++i) {
                                       id = dis.readByte();
                                       id = dis.readInt();
                                       String listItemStr = dis.readUTF();
                                       item = new ListItem(listItemStr, (byte)1, (byte)0, list.getListWidth());
                                       taskType = -1;
                                       switch(id) {
                                       case 0:
                                          taskType = 6;
                                          break;
                                       case 1:
                                          taskType = 7;
                                          break;
                                       case 2:
                                          taskType = 5;
                                          break;
                                       case 3:
                                          taskType = 8;
                                       }

                                       item.setTaskType(taskType);
                                       item.itemId = id;
                                       list.addItem(item);
                                    }

                                    if (clientType == 83) {
                                       list.serverType = 36;
                                    } else if (clientType == 88) {
                                       list.serverType = 41;
                                    } else if (clientType == 95) {
                                       list.serverType = 47;
                                    } else if (clientType == 102) {
                                       list.serverType = 53;
                                    } else if (clientType == 107) {
                                       list.serverType = 57;
                                    } else if (clientType == 113) {
                                       list.serverType = 61;
                                    } else if (clientType == 119) {
                                       list.serverType = 64;
                                    } else if (clientType == 124) {
                                       list.serverType = 68;
                                    } else if (clientType == 140) {
                                       list.serverType = 76;
                                    } else if (clientType == 145) {
                                       list.serverType = 80;
                                    }

                                    UIList.addUIScroll(list, (byte)0);
                                 }
                              } else {
                                 list.removeAll();
                                 num = dis.readByte();

                                 for(i = 0; i < num; ++i) {
                                    id = dis.readInt();
                                    listItemStr = dis.readUTF();
                                    item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                    item.itemId = id;
                                    list.addItem(item);
                                 }

                                 if (clientType == 80) {
                                    list.serverType = 34;
                                 } else if (clientType == 89) {
                                    list.serverType = 42;
                                 } else if (clientType == 96) {
                                    list.serverType = 48;
                                 } else if (clientType == 103) {
                                    list.serverType = 54;
                                 } else if (clientType == 108) {
                                    list.serverType = 58;
                                 } else if (clientType == 121) {
                                    list.serverType = 65;
                                 } else if (clientType == 125) {
                                    list.serverType = 69;
                                 } else if (clientType == 141) {
                                    list.serverType = 77;
                                 } else if (clientType == 146) {
                                    list.serverType = 81;
                                 }

                                 UIList.addUIScroll(list, (byte)0);
                              }
                           } else {
                              list.removeAll();
                              num = dis.readByte();

                              for(i = 0; i < num; ++i) {
                                 id = dis.readInt();
                                 listItemStr = dis.readUTF();
                                 item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                                 item.itemId = id;
                                 list.addItem(item);
                              }

                              if (clientType == 79) {
                                 list.serverType = 33;
                              } else if (clientType == 82) {
                                 list.serverType = 35;
                              } else if (clientType == 86) {
                                 list.serverType = 39;
                              } else if (clientType == 87) {
                                 list.serverType = 40;
                              } else if (clientType == 93) {
                                 list.serverType = 45;
                              } else if (clientType == 94) {
                                 list.serverType = 46;
                              } else if (clientType == 100) {
                                 list.serverType = 51;
                              } else if (clientType == 101) {
                                 list.serverType = 52;
                              }

                              UIList.addUIScroll(list, (byte)0);
                           }
                        } else {
                           list.removeAll();
                           num = dis.readByte();

                           for(i = 0; i < num; ++i) {
                              id = dis.readByte();
                              listItemStr = dis.readUTF();
                              item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                              item.forgIndex = id;
                              list.addItem(item);
                           }

                           if (clientType == 78) {
                              list.serverType = 32;
                           } else if (clientType == 85) {
                              list.serverType = 38;
                           } else if (clientType == 92) {
                              list.serverType = 44;
                           } else if (clientType == 99) {
                              list.serverType = 50;
                           } else if (clientType == 106) {
                              list.serverType = 56;
                           } else if (clientType == 112) {
                              list.serverType = 60;
                           } else if (clientType == 118) {
                              list.serverType = 63;
                           } else if (clientType == 123) {
                              list.serverType = 67;
                           } else if (clientType == 139) {
                              list.serverType = 75;
                           } else if (clientType == 144) {
                              list.serverType = 79;
                           }

                           PCProduce.selectedLevelIndex = PCProduce.canListNum;
                           UIList.addUIScroll(list, (byte)0);
                        }
                     } else {
                        list.removeAll();
                        num = dis.readByte();
                        PCProduce.canListNum = num;

                        for(i = 0; i < num; ++i) {
                           id = dis.readByte();
                           listItemStr = dis.readUTF();
                           item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                           item.forgIndex = id;
                           list.addItem(item);
                        }

                        if (clientType == 77) {
                           list.serverType = 31;
                        } else if (clientType == 84) {
                           list.serverType = 37;
                        } else if (clientType == 91) {
                           list.serverType = 43;
                        } else if (clientType == 98) {
                           list.serverType = 49;
                        } else if (clientType == 105) {
                           list.serverType = 55;
                        } else if (clientType == 111) {
                           list.serverType = 59;
                        } else if (clientType == 117) {
                           list.serverType = 62;
                        } else if (clientType == 122) {
                           list.serverType = 66;
                        } else if (clientType == 138) {
                           list.serverType = 74;
                        } else if (clientType == 143) {
                           list.serverType = 78;
                        }

                        PCProduce.selectedLevelIndex = 0;
                        UIList.addUIScroll(list, (byte)0);
                     }
                  }
               } else {
                  list.removeAll();
                  list.botianIndex = dis.readByte();
                  num = dis.readByte();

                  for(i = 0; i < num; ++i) {
                     id = dis.readInt();
                     listItemStr = dis.readUTF();
                     item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
                     item.itemId = id;
                     list.addItem(item);
                  }

                  if (clientType == 129) {
                     list.serverType = 70;
                  } else if (clientType == 149) {
                     list.serverType = 82;
                  }

                  UIList.addUIScroll(list, (byte)0);
               }
            }
         }
      } else {
         list.removeAll();
         num = dis.readByte();

         for(i = 0; i < num; ++i) {
            listItemStr = dis.readUTF();
            item = new ListItem(listItemStr, (byte)0, (byte)0, list.getListWidth());
            list.addItem(item);
         }

         UIList.addUIScroll(list, (byte)0);
      }

   }

   public static UITopForm parseTopForm(DataInputStream dis) {
      UITopForm topForm = null;

      try {
         byte level = dis.readByte();
         byte topFormType = dis.readByte();
         topForm = new UITopForm(topFormType, (UIForm)null);
         topForm.setTopFormInfo(level, dis.readInt(), dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readInt(), dis.readInt());
         label99:
         switch(topFormType) {
         case 0:
         case 1:
         case 4:
         case 6:
         case 13:
         case 15:
         case 16:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
         case 24:
         case 25:
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 35:
         case 39:
         default:
            break;
         case 2:
            if (MainCanvas.curTopForm == null && MainCanvas.getState() == 5 && MainCanvas.getGameState() == 0) {
               SITeam.headerID = dis.readInt();
               break;
            }

            Player.busyId = dis.readInt();
            MainCanvas.ni.send(720914);
            topForm = null;
            break;
         case 3:
            byte isUpData = dis.readByte();
            switch(isUpData) {
            case 0:
               MainCanvas.quitUI();
               break label99;
            case 1:
               switch(SITeam.teamState) {
               case 0:
                  MainCanvas.quitUI();
                  break label99;
               case 1:
                  MainCanvas.ni.send(720904);
                  break label99;
               case 2:
                  MainCanvas.ni.sendCommands(new int[]{720905, 720906, 720907});
               }
            default:
               break label99;
            }
         case 5:
            if (MainCanvas.getGameState() != 0) {
               MainCanvas.ni.send(1376259);
               topForm = null;
            }
            break;
         case 7:
            PCLover.actionId = dis.readInt();
            break;
         case 8:
            topForm.taskID = dis.readInt();
            break;
         case 9:
            if (MainCanvas.curTopForm == null && MainCanvas.getState() == 5 && MainCanvas.getGameState() == 0) {
               PCClan.inviteID = dis.readInt();
               break;
            }

            Player.busyId = dis.readInt();
            MainCanvas.ni.send(1638414);
            topForm = null;
            break;
         case 10:
            SITeam.rollGoodsId = dis.readInt();
            break;
         case 11:
            if (MainCanvas.curTopForm == null && MainCanvas.getState() == 5 && MainCanvas.getGameState() == 0) {
               Player.getInstance().playerDuel = dis.readInt();
               break;
            }

            Player.busyId = dis.readInt();
            MainCanvas.ni.send(196630);
            topForm = null;
            break;
         case 12:
            if (MainCanvas.curTopForm == null && MainCanvas.getState() == 5 && MainCanvas.getGameState() == 0) {
               PCKing.inviteID = dis.readInt();
               break;
            }

            Player.busyId = dis.readInt();
            MainCanvas.ni.send(1835032);
            topForm = null;
            break;
         case 14:
            Player.getInstance().m_nPersonalEnemy = dis.readInt();
            break;
         case 17:
            MainCanvas.m_bCompelDroptMeshwork = dis.readByte();
            break;
         case 31:
            SITeam.rollGoodsId = dis.readInt();
            break;
         case 32:
            PCProduce.forgLearnTopForm1 = dis.readByte();
            PCProduce.forgLearnTopForm2 = dis.readByte();
            break;
         case 33:
            PCProduce.forgTopForm3 = dis.readInt();
            break;
         case 34:
            PCProduce.enchantForm1 = dis.readByte();
            PCProduce.enchantForm2 = dis.readByte();
            PCProduce.enchantForm3 = dis.readByte();
            break;
         case 36:
            PCPackage.m_nGrade = dis.readInt();
            PCPackage.m_nClanGrade = dis.readInt();
            break;
         case 37:
            if (MainCanvas.ni.isSendingCommands) {
               MainCanvas.ni.isSendingCommands = false;
            }
            break;
         case 38:
            if (MainCanvas.curTopForm == null && MainCanvas.getState() == 5 && MainCanvas.getGameState() == 0) {
               PCMentor.otherId = dis.readInt();
               break;
            }

            PCMentor.otherId = dis.readInt();
            MainCanvas.ni.send(2490423);
            topForm = null;
            break;
         case 40:
            GOManager.m_bBattleDatatrans = dis.readShort();
            if (MainCanvas.getGameState() == 4) {
               MainCanvas.quitUI();
            }

            if (MainCanvas.rightMenu != null) {
               MainCanvas.rightMenu.savePositionIDStack.removeAllElements();
               MainCanvas.rightMenu = null;
               MainCanvas.setGameState((byte)0);
            }
            break;
         case 41:
            if (MainCanvas.ni.isSendingCommands) {
               MainCanvas.ni.isSendingCommands = false;
            }
            break;
         case 42:
            PCClan.clan_snd_id = dis.readInt();
            PCClan.clan_rec_id = dis.readInt();
            PCClan.battle_id = dis.readInt();
         }

         dis.close();
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return topForm;
   }

   public static UITopForm parseTopForm(byte[] data) {
      try {
         DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
         return parseTopForm(dis);
      } catch (Exception var2) {
         var2.printStackTrace();
         return null;
      }
   }

   public static UIForm parseCmdData(byte[] data, int commID) {
      try {
         DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
         return parseCmdData(dis, commID);
      } catch (Exception var3) {
         var3.printStackTrace();
         return null;
      }
   }

   public static UIForm parseCmdData(DataInputStream dis, int commID) {
      UIForm form = null;

      try {
         byte self;
         short textAreaX;
         int i;
         byte picIndex;
         byte prePicIndex;
         byte maxNum;
         UITextField mailNameText;
         UILabel maxExpLabel;
         UITextField moneyTxt;
         UITitle menuBar;
         short rowH;
         int i;
         short goodsID;
         switch(commID) {
         case -2147221500:
         case -2147221499:
         case -2147221498:
         case -2147221493:
         case -2147221492:
         case -2147221490:
            UIComponent.isAdapt = false;
            UIForm.baochouCol = 0;
            form = new UIForm((short)0, (short)0, (short)MainCanvas.screenW, (short)MainCanvas.screenH);
            UITitle title = new UITitle((byte)0, form);
            byte getType = -1;
            if (commID == -2147221500) {
               title.setTitleText("Nhận thù lao");
               getType = 0;
               form.clientCommand = 262152;
            } else if (commID == -2147221499) {
               title.setTitleText("Chi tiết thù lao");
               getType = 1;
               form.clientCommand = 262158;
            } else if (commID == -2147221498) {
               title.setTitleText("Chi tiết thù lao");
               getType = 2;
               form.clientCommand = 262159;
            } else if (commID == -2147221493) {
               title.setTitleText("Chi tiết thù lao");
               getType = 3;
               form.clientCommand = 262175;
            } else if (commID == -2147221492) {
               title.setTitleText("Chi tiết thù lao");
               getType = 4;
               form.clientCommand = 262177;
            } else if (commID == -2147221490) {
               title.setTitleText("Chi tiết thù lao");
               getType = 5;
               form.clientCommand = 262181;
            }

            form.addComponent(title);
            menuBar = new UITitle((byte)1, form);
            form.addComponent(menuBar);
            int offH = UILabel.getRimLabelHeight();
            UIPanel panel = new UIPanel((short)0, (short)(UITitle.getTitleBarHeight() + 2), (short)MainCanvas.screenW, (short)(MainCanvas.screenH - UITitle.getMenuBarHeight() - UITitle.getTitleBarHeight() - 3), (byte)1, form);
            panel.setInnerHeight((short)(panel.getInnerHeight() - offH));
            panel.setInnerOY((short)offH);
            form.addComponent(panel);
            String taskName = dis.readUTF();
            UILabel taskLabel = new UILabel((short)15, (short)(panel.getPositionY() + 3), (short)(MainCanvas.screenW - 30), (short)UILabel.getRimLabelHeight(), (byte)1, taskName, form);
            form.addComponent(taskLabel);
            short labelX = 20;
            rowH = (short)(UITitle.getTitleBarHeight() + 2 + 26);
            Util.focusPics.removeAllElements();
            Util.focusPicIndex.removeAllElements();
            Util.focusPicRowIndex.removeAllElements();
            byte groupNum = dis.readByte();
            if (groupNum > 0) {
               PCTask.haveMayBeChoice = true;
               String tmpStr1 = "Hãy chọn 1 vật phẩm làm";
               String tmpStr2 = "Phần thưởng phụ";
               UILabel addsLabel1 = new UILabel(labelX, rowH, (short)Util.getStrLen(tmpStr1), (short)MainCanvas.CHARH, (byte)0, tmpStr1, form);
               UILabel addsLabel2 = new UILabel(labelX, (short)(rowH + 18 * MainCanvas.screenH / 208), (short)Util.getStrLen(tmpStr2), (short)MainCanvas.CHARH, (byte)0, tmpStr2, form);
               form.addComponent(addsLabel1);
               form.addComponent(addsLabel2);
            } else {
               PCTask.haveMayBeChoice = false;
            }

            for(i = 0; i < groupNum; ++i) {
               byte payType = dis.readByte();
               int rowIndex = 2 + i;
               setPay(dis, form, payType, rowIndex, (short)(rowH + rowIndex * (18 * MainCanvas.screenH / 208)), (byte)1, (byte)(i + 1), getType);
            }

            for(i = 0; i < Util.focusPics.size(); ++i) {
               UIPicture pic = (UIPicture)Util.focusPics.elementAt(i);
               picIndex = (byte)(Integer)Util.focusPicIndex.elementAt(i);
               if (i == 0) {
                  pic.setFocus(true, form);
               }

               if (i > 0) {
                  UIPicture prePic = (UIPicture)Util.focusPics.elementAt(i - 1);
                  prePicIndex = (byte)(Integer)Util.focusPicIndex.elementAt(i - 1);
                  prePic.setAroundComponent((byte)1, (byte)(picIndex + 1));
                  pic.setAroundComponent((byte)0, (byte)(prePicIndex + 1));
               }
            }

            UIComponent.isAdapt = true;
            break;
         case -2147090429:
            form = MainCanvas.curForm;
            Player.getInstance().curEXP = dis.readLong();
            Player.getInstance().maxEXP = dis.readLong();
            if (MainCanvas.curForm != null) {
               UILabel curExpLabel;
               switch(MainCanvas.curForm.clientCommand) {
               case 393219:
                  curExpLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(33);
                  maxExpLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(35);
                  curExpLabel.setType((byte)7);
                  maxExpLabel.setType((byte)7);
                  curExpLabel.setLongNum(Player.getInstance().curEXP);
                  maxExpLabel.setLongNum(Player.getInstance().maxEXP);
                  MainCanvas.isWaiting = false;
                  break;
               case 983047:
                  curExpLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(7);
                  curExpLabel.setType((byte)7);
                  curExpLabel.setLongNum(Player.getInstance().curEXP);
                  MainCanvas.isWaiting = false;
                  break;
               case 2162693:
               case 2162708:
               case 2162719:
               case 2162730:
               case 2162739:
               case 2162747:
               case 2162760:
               case 2162767:
               case 2162774:
               case 2162781:
                  curExpLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(8);
                  curExpLabel.setType((byte)7);
                  curExpLabel.setLongNum(Player.getInstance().curEXP);
                  MainCanvas.isWaiting = false;
               }
            }
            break;
         case -2147090423:
            UIPanel.m_bDataH = true;
            form = new UIForm((short)0, (short)0, (short)MainCanvas.screenW, (short)MainCanvas.screenH);
            self = dis.readByte();
            if (self == 0) {
               form.clientCommand = 393225;
            } else {
               form.clientCommand = 393229;
            }

            UITitle title = new UITitle((byte)0, form);
            title.setTitleText("Tư liệu nhân vật");
            form.addComponent(title);
            menuBar = new UITitle((byte)1, form);
            if (self == 1) {
               menuBar.setMenuText("", 0);
            }

            form.addComponent(menuBar);
            UIComponent.isAdapt = false;
            UIPanel panel = new UIPanel((short)0, (short)(UITitle.getTitleBarHeight() - 4), (short)MainCanvas.screenW, (short)(MainCanvas.screenH - UITitle.getMenuBarHeight() - UITitle.getTitleBarHeight() + 5), (byte)1, form);
            form.addComponent(panel);
            byte allRowNum = 10;
            short centerXLeft = (short)(MainCanvas.screenW / 4);
            short centerXRight = (short)(MainCanvas.screenW * 2 / 3);
            short beginY = (short)(panel.getInnerY() + 6);
            rowH = (short)(16 * MainCanvas.screenH / 208);
            textAreaX = (short)(centerXLeft - MainCanvas.CHARW * 2);
            short textAreaW = (short)(MainCanvas.screenW * 3 / 4 - 4);
            short textAreaH = 130;
            UIDummyWidget uidw = new UIDummyWidget(form.clientCommand, form);
            i = 0;
            int i = 0;

            byte canShowRowNumTmp;
            for(canShowRowNumTmp = allRowNum; i < canShowRowNumTmp; ++i) {
               UILabel label_1 = new UILabel(centerXLeft, (short)(beginY + rowH * i), (byte)0, UIDummyWidget.INFO_STR[i], true, form);
               form.addComponent(label_1);
               uidw.addWidget(label_1);
               String label_2_str = dis.readUTF();
               uidw.saveStr[i++] = label_2_str;
               if (i != allRowNum - 1) {
                  UILabel label_2 = new UILabel(centerXRight, (short)(beginY + rowH * i), (byte)0, label_2_str, true, form);
                  form.addComponent(label_2);
                  uidw.addWidget(label_2);
               } else {
                  UITextArea area = new UITextArea(textAreaX, (short)(beginY + rowH * allRowNum + 2), textAreaW, textAreaH, form);
                  area.setRimType((byte)1);
                  area.setColor(-1);
                  area.setContent(label_2_str);
                  form.addComponent(area);
                  uidw.addWidget(area);
               }
            }

            if (panel.getPanelVScroll() == null) {
               panel.addPanelVScrollBar(true);
               uidw.addWidget(panel.getPanelVScroll());
            }

            short allRowNumTmp = 19;
            canShowRowNumTmp = 11;
            panel.addUIScroll(allRowNumTmp, canShowRowNumTmp);
            uidw.setAroundFocusNull();
            form.addComponent(uidw);
            UIComponent.isAdapt = true;
            break;
         case -2146762738:
            form = MainCanvas.curForm;
            self = dis.readByte();
            UIRadio radio = (UIRadio)form.getComponents().elementAt(3);
            radio.setSureIndex(self);
            radio.setSelectIndex(self);
            break;
         case -2146238462:
            short equipPicFirstID = dis.readShort();
            short equipPicSecondID = dis.readShort();
            byte rowNum = dis.readByte();
            byte colNum = true;
            short[] rowType = new short[rowNum];
            Object[] rowContent = new Object[rowNum];
            String[] firstColStr = new String[rowNum];

            for(int i = 0; i < rowNum; ++i) {
               firstColStr[i] = dis.readUTF();
               if (firstColStr[i].length() > 2) {
                  int beginIndex = firstColStr[i].length() - 2;
                  firstColStr[i] = firstColStr[i].substring(beginIndex, firstColStr[i].length());
               }

               rowType[i] = dis.readShort();
               String[] str;
               switch(rowType[i]) {
               case 1:
                  str = new String[]{dis.readUTF(), dis.readUTF()};
                  rowContent[i] = str;
               case 2:
               case 3:
               default:
                  break;
               case 4:
                  int[] sn = new int[]{dis.readInt(), dis.readInt()};
                  rowContent[i] = sn;
               }

               switch(rowType[i]) {
               case 1:
                  str = (String[])rowContent[i];
               case 2:
               case 3:
               default:
                  break;
               case 4:
                  int[] var73 = (int[])rowContent[i];
               }
            }

            form = new UIForm((short)0, (short)0, (short)MainCanvas.screenW, (short)MainCanvas.screenH);
            form.clientCommand = 1245185;
            UIPanel.m_bDataH = true;
            UIPanel.m_bDataX = true;
            UIDummyWidget uidw = new UIDummyWidget(form.clientCommand, form);
            UITitle title = new UITitle((byte)0, form);
            title.setTitleText("So sánh trang bị");
            form.addComponent(title);
            UITitle menuBar = new UITitle((byte)1, form);
            form.addComponent(menuBar);
            UIComponent.isAdapt = false;
            UIPanel panel = new UIPanel((short)0, (short)(UITitle.getTitleBarHeight() - 2), (short)MainCanvas.screenW, (short)(MainCanvas.screenH - (UITitle.getTitleBarHeight() + 4 + UITitle.getMenuBarHeight()) + 7), (byte)1, form);
            short innerY = 49;
            short innerHeight = (short)(135 * MainCanvas.screenH / 208 + 1);
            panel.setInnerY(innerY);
            panel.setInnerHeight(innerHeight);
            uidw.addWidget(panel);
            short tjX = 21;
            goodsID = (short)(29 * MainCanvas.screenH / 208);
            short firstX = (short)(60 * MainCanvas.screenW / 176);
            short secondX = (short)(125 * MainCanvas.screenW / 176);
            UIPicture picTaiJi = new UIPicture(tjX, goodsID, form);
            uidw.addWidget(picTaiJi);
            UIPicture picFirst = new UIPicture(firstX, goodsID, (short)0, (short)0, (String)null, (String)null, (byte)1, form);
            picFirst.setQuality(equipPicFirstID);
            short picID = (short)(equipPicFirstID % 1000);
            picFirst.isWpPic = true;
            picFirst.wpIndex = picID;
            picFirst.setImg(MainCanvas.stuffMImg);
            uidw.addWidget(picFirst);
            UIPicture picSecond = new UIPicture(secondX, goodsID, (short)0, (short)0, (String)null, (String)null, (byte)1, form);
            picSecond.setQuality(equipPicSecondID);
            picID = (short)(equipPicSecondID % 1000);
            picSecond.isWpPic = true;
            picSecond.wpIndex = picID;
            picSecond.setImg(MainCanvas.stuffMImg);
            uidw.addWidget(picSecond);
            short x1 = (short)(panel.INNER_XYWH[0] + MainCanvas.CHARW - 3);
            short y1 = (short)panel.INNER_XYWH[1];
            short x2 = (short)((panel.INNER_XYWH[0] + panel.INNER_XYWH[0] + (panel.INNER_XYWH[2] + MainCanvas.CHARW * 2 + 2 + 1) / 2 + MainCanvas.CHARW * 2 + 2 + 1) / 2 - 1);
            short x3 = (short)(x2 - (panel.INNER_XYWH[0] + MainCanvas.CHARW * 2 + 2) + panel.INNER_XYWH[0] + (panel.INNER_XYWH[2] + MainCanvas.CHARW * 2 + 2 + 1) / 2 + 4);

            for(int i = 0; i < rowNum; ++i) {
               UILabel label_1;
               UILabel label_2;
               UILabel label_3;
               switch(rowType[i]) {
               case 1:
                  String[] str = (String[])rowContent[i];
                  label_1 = new UILabel(x1, (short)(y1 + MainCanvas.CHARH * i), (byte)0, firstColStr[i], true, form);
                  label_2 = new UILabel(x2, (short)(y1 + MainCanvas.CHARH * i), (byte)0, str[0], true, form);
                  label_3 = new UILabel(x3, (short)(y1 + MainCanvas.CHARH * i), (byte)0, str[1], true, form);
                  uidw.addWidget(label_1);
                  uidw.addWidget(label_2);
                  uidw.addWidget(label_3);
               case 2:
               case 3:
               default:
                  break;
               case 4:
                  int[] n = (int[])rowContent[i];
                  label_1 = new UILabel(x1, (short)(y1 + MainCanvas.CHARH * i), (byte)0, firstColStr[i], true, form);
                  label_2 = new UILabel(x2, (short)(y1 + MainCanvas.CHARH * i), (byte)3, n[0], form);
                  label_2.setNum1(n[0]);
                  label_2.setPositionX((short)(x2 - label_2.getWidth() / 2));
                  label_2.setPositionY((short)(y1 + MainCanvas.CHARH * i + label_2.getHeight() / 2));
                  label_3 = new UILabel(x3, (short)(y1 + MainCanvas.CHARH * i), (byte)3, n[1], form);
                  label_3.setNum1(n[1]);
                  label_3.setPositionX((short)(x3 - label_3.getWidth() / 2));
                  label_3.setPositionY((short)(y1 + MainCanvas.CHARH * i + label_3.getHeight() / 2));
                  uidw.addWidget(label_1);
                  uidw.addWidget(label_2);
                  uidw.addWidget(label_3);
               }
            }

            if (panel.getPanelVScroll() == null && panel.getPanelRowNum() < rowNum) {
               panel.addPanelVScrollBar(true);
               uidw.addWidget(panel.getPanelVScroll());
            }

            panel.addUIScroll(rowNum, (short)panel.getPanelRowNum());
            uidw.setAroundFocusNull();
            form.addComponent(uidw);
            UIComponent.isAdapt = true;
            break;
         case -2145779711:
         case -2145779710:
            form = MainCanvas.curForm;
            mailNameText = (UITextField)form.getComponents().elementAt(3);
            mailNameText.setEditable(false);
            moneyTxt = (UITextField)form.getComponents().elementAt(4);
            moneyTxt.setEditable(false);
            UITextArea mailContentText = (UITextArea)form.getComponents().elementAt(5);
            mailContentText.setEditable(false);
            UITextField moneyText = (UITextField)form.getComponents().elementAt(13);
            moneyText.setEditable(false);
            UIPicture[] attPics = new UIPicture[6];

            for(int i = 0; i < attPics.length; ++i) {
               attPics[i] = (UIPicture)form.getComponents().elementAt(i + 7);
            }

            long sendId = dis.readLong();
            PCMail.canReturnMail = sendId != 0L;
            String sendName = dis.readUTF();
            moneyTxt.setSb(new StringBuffer(sendName));
            String mailName = dis.readUTF();
            mailNameText.setSb(new StringBuffer(mailName));
            String mailContent = dis.readUTF();
            mailContentText.setContent(mailContent);
            mailContentText.addUIScroll();
            mailContentText.setColor(16711680);
            boolean isCost = dis.readBoolean();
            int costNum = dis.readInt();
            PCMail.receiveMoney = costNum;
            moneyText.setSb(new StringBuffer(String.valueOf(costNum)));
            picIndex = dis.readByte();
            PCMail.haveAttach = false;

            for(i = 0; i < attPics.length; ++i) {
               goodsID = dis.readShort();
               byte itemNum = dis.readByte();
               maxNum = dis.readByte();
               byte itemLevel = dis.readByte();
               attPics[i].setEquipColorLevel(itemLevel);
               attPics[i].setPicID(goodsID);
               attPics[i].setQuality(goodsID);
               attPics[i].isWpPic = true;
               short picID = (short)(goodsID % 1000);
               attPics[i].wpIndex = picID;
               attPics[i].setImg(MainCanvas.stuffMImg);
               attPics[i].setItemNum(itemNum);
               attPics[i].attIndex = (byte)i;
               if (maxNum != 1 && goodsID != 0) {
                  attPics[i].setShowNum(true);
               } else {
                  attPics[i].setShowNum(false);
               }
            }

            for(i = 0; i < attPics.length; ++i) {
               if (attPics[i].getPicID() > 0) {
                  PCMail.haveAttach = true;
                  break;
               }
            }

            if (PCMail.selectedAttIndex != -1) {
               mailNameText.setFocus(false, form);
               moneyTxt.setFocus(false, form);
               attPics[PCMail.selectedAttIndex].setFocus(true, form);
            }

            if (commID == -2145779711) {
               MainCanvas.curForm.clientCommand = 1703940;
            } else if (commID == -2145779710) {
               MainCanvas.curForm.clientCommand = 1703941;
            }
            break;
         case -2145779707:
            form = MainCanvas.curForm;
            PCMail.m_lCharacter = dis.readLong();
            PCMail.m_sCharacterName = dis.readUTF();
            PCMail.m_sMailMotif = dis.readUTF();
            PCMail.m_sSendMailValidity[0] = PCMail.m_sCharacterName;
            PCMail.m_sSendMailValidity[1] = PCMail.m_sMailMotif;
            mailNameText = (UITextField)form.getComponents().elementAt(3);
            moneyTxt = (UITextField)form.getComponents().elementAt(4);
            mailNameText.setSb(new StringBuffer(PCMail.m_sCharacterName));
            moneyTxt.setSb(new StringBuffer(PCMail.m_sMailMotif));
            break;
         case -2145583099:
            form = MainCanvas.curForm;
            PCAuction.m_nPlayMoney = dis.readInt();
            mailNameText = (UITextField)form.getComponents().elementAt(7);
            mailNameText.setSb(new StringBuffer(Integer.toString(PCAuction.m_nPlayMoney)));
            mailNameText.setEditable(false);
            break;
         case -2145583097:
            form = MainCanvas.curForm;
            form.addWarningStr("ấn phím # để cập nhật");
            UIList list = (UIList)form.getComponents().elementAt(2);
            moneyTxt = (UITextField)form.getComponents().elementAt(14);
            UILabel pageLabel = (UILabel)form.getComponents().elementAt(15);
            UIPicture pic = (UIPicture)form.getComponents().elementAt(5);
            UILabel stuffLvLabel = (UILabel)form.getComponents().elementAt(8);
            UITextField maxPriceTxt = (UITextField)form.getComponents().elementAt(7);
            UITextField surePriceTxt = (UITextField)form.getComponents().elementAt(10);
            UITextField leftTimeTxt = (UITextField)form.getComponents().elementAt(12);
            list.removeAll();
            list.serverType = 18;
            byte num = dis.readByte();
            textAreaX = dis.readShort();
            PCAuction.sumPage = textAreaX;
            if (textAreaX < PCAuction.pageIndex) {
               PCAuction.pageIndex = textAreaX;
            }

            pageLabel.setNum1(PCAuction.pageIndex);
            pageLabel.setNum2(textAreaX);

            for(i = 0; i < num; ++i) {
               long auctionID = dis.readLong();
               short picID = dis.readShort();
               prePicIndex = dis.readByte();
               String itemName = dis.readUTF();
               maxNum = dis.readByte();
               short itemLevel = dis.readShort();
               byte maxNum = dis.readByte();
               int maxPrice = dis.readInt();
               int surePrice = dis.readInt();
               byte leftTimeIndex = dis.readByte();
               ListItem item = new ListItem((byte)5);
               item.setAuctionAttribute(itemName, auctionID, picID, prePicIndex, maxNum, itemLevel, maxNum, maxPrice, surePrice, leftTimeIndex);
               list.addItem(item);
            }

            UIList.addUIScroll(list, (byte)5);
            i = dis.readInt();
            PCAuction.playerMoney = i;
            if (num > 0) {
               ListItem firstItem = (ListItem)list.getItems().elementAt(list.getSelectedIndex());
               pic.isWpPic = true;
               pic.setImg(MainCanvas.stuffMImg);
               pic.setQuality(firstItem.stuffPicID);
               pic.wpIndex = (short)(firstItem.stuffPicID % 1000);
               if (firstItem.stuffMaxNum == 1) {
                  pic.setShowNum(false);
               } else {
                  pic.setShowNum(true, firstItem.stuffNum);
               }

               stuffLvLabel.setNum1(firstItem.stuffLevel);
               maxPriceTxt.setSb(new StringBuffer(String.valueOf(firstItem.stuffMaxPrice)));
               maxPriceTxt.setEditable(false);
               surePriceTxt.setSb(new StringBuffer(String.valueOf(firstItem.stuffSurePrice)));
               surePriceTxt.setEditable(false);
               leftTimeTxt.setSb(new StringBuffer(firstItem.stuffLeftTimeStr));
               leftTimeTxt.setEditable(false);
               moneyTxt.setSb(new StringBuffer(String.valueOf(i)));
               moneyTxt.setEditable(false);
            } else {
               pic.setImg((MImage)null);
               pic.setShowNum(false);
               stuffLvLabel.setNum1(0);
               maxPriceTxt.setSb(new StringBuffer(""));
               maxPriceTxt.setEditable(false);
               surePriceTxt.setSb(new StringBuffer(""));
               surePriceTxt.setEditable(false);
               leftTimeTxt.setSb(new StringBuffer(""));
               leftTimeTxt.setEditable(false);
               moneyTxt.setSb(new StringBuffer(String.valueOf(i)));
               moneyTxt.setEditable(false);
            }
            break;
         case -2145517536:
            form = MainCanvas.curForm;
            self = dis.readByte();
            PCGem.m_bUnchainExes = new int[self];

            for(int i = 0; i < self; ++i) {
               PCGem.m_bUnchainExes[i] = dis.readInt();
            }

            maxExpLabel = (UILabel)form.getComponents().elementAt(16);
            maxExpLabel.setText("" + PCGem.m_bUnchainExes[PCGem.m_bListIndex]);
         }
      } catch (Exception var33) {
         var33.printStackTrace();
      }

      return form;
   }

   static void setPay(DataInputStream dis, UIForm form, byte payType, int rowIndex, short startY, byte groupIndex, byte colIndex, byte getType) {
      try {
         short startX = 33;
         String youName;
         UIPicture pic;
         String labelStr;
         UILabel label1;
         UILabel label2;
         UILabel label1;
         UILabel label2;
         int glory;
         short imgId;
         String goodsName;
         int constructNum;
         UIPicture pic;
         String labelStr;
         switch(payType) {
         case 1:
            imgId = dis.readShort();
            goodsName = dis.readUTF();
            constructNum = dis.readInt();
            pic = new UIPicture(startX, startY, (short)0, (short)0, (String)null, (String)null, (byte)1, form);
            pic.setQuality(imgId);
            pic.setImg(MainCanvas.stuffMImg);
            pic.isWpPic = true;
            pic.wpIndex = (short)(imgId % 1000);
            pic.groupIndex = groupIndex;
            pic.colIndex = colIndex;
            pic.getType = getType;
            pic.payType = payType;
            if (constructNum > 1) {
               pic.setShowNum(true, (byte)constructNum);
            }

            form.addComponent(pic);
            pic.setAroundFocusNull();
            Util.focusPics.addElement(pic);
            Util.focusPicIndex.addElement(new Integer(form.getComponents().size() - 1));
            Util.focusPicRowIndex.addElement(new Integer(rowIndex));
            label2 = new UILabel((short)(startX + 20), startY, (short)Util.getStrLen(goodsName), (short)MainCanvas.CHARH, (byte)0, goodsName, form);
            label2.setAroundFocusNull();
            form.addComponent(label2);
            break;
         case 2:
            youName = dis.readUTF();
            int creditNum = dis.readInt();
            UIPicture pic = new UIPicture(startX, startY, (short)0, (short)0, "exp.png", (String)null, (byte)1, form);
            pic.groupIndex = groupIndex;
            pic.colIndex = colIndex;
            pic.getType = getType;
            pic.payType = payType;
            pic.setInnerKuang(true);
            form.addComponent(pic);
            pic.setAroundFocusNull();
            Util.focusPics.addElement(pic);
            Util.focusPicIndex.addElement(new Integer(form.getComponents().size() - 1));
            Util.focusPicRowIndex.addElement(new Integer(rowIndex));
            if (groupIndex == 0) {
               ++UIForm.baochouCol;
            }

            label2 = new UILabel((short)(startX + 20), startY, (short)Util.getStrLen(youName), (short)MainCanvas.CHARH, (byte)0, youName, form);
            form.addComponent(label2);
            label1 = new UILabel((short)(startX + 20 + Util.getStrLen(youName) + 4), (short)(startY + 3), (byte)3, creditNum, form);
            label1.setNum1(creditNum);
            form.addComponent(label1);
            break;
         case 3:
            glory = dis.readInt();
            pic = new UIPicture(startX, startY, (short)0, (short)0, "u-8.png", (String)null, (byte)1, form);
            pic.groupIndex = groupIndex;
            pic.colIndex = colIndex;
            pic.getType = getType;
            pic.payType = payType;
            pic.setInnerKuang(true);
            form.addComponent(pic);
            pic.setAroundFocusNull();
            Util.focusPics.addElement(pic);
            Util.focusPicIndex.addElement(new Integer(form.getComponents().size() - 1));
            Util.focusPicRowIndex.addElement(new Integer(rowIndex));
            if (groupIndex == 0) {
               ++UIForm.baochouCol;
            }

            labelStr = "Vàng";
            label1 = new UILabel((short)(startX + 20), startY, (short)Util.getStrLen(labelStr), (short)MainCanvas.CHARH, (byte)0, labelStr, form);
            form.addComponent(label1);
            label2 = new UILabel((short)(startX + 20 + Util.getStrLen(labelStr) + 4), (short)(startY + 3), (byte)3, glory, form);
            label2.setNum1(glory);
            form.addComponent(label2);
            break;
         case 4:
            imgId = dis.readShort();
            goodsName = dis.readUTF();
            constructNum = dis.readInt();
            pic = new UIPicture(startX, startY, (short)0, (short)0, "u-2.png", (String)null, (byte)1, form);
            pic.groupIndex = groupIndex;
            pic.colIndex = colIndex;
            pic.getType = getType;
            pic.payType = payType;
            pic.setInnerKuang(true);
            form.addComponent(pic);
            pic.setAroundFocusNull();
            Util.focusPics.addElement(pic);
            Util.focusPicIndex.addElement(new Integer(form.getComponents().size() - 1));
            Util.focusPicRowIndex.addElement(new Integer(rowIndex));
            if (groupIndex == 0) {
               ++UIForm.baochouCol;
            }

            labelStr = "Kỹ năng";
            label1 = new UILabel((short)(startX + 20), startY, (short)Util.getStrLen(labelStr), (short)MainCanvas.CHARH, (byte)0, labelStr, form);
            form.addComponent(label1);
            label2 = new UILabel((short)(startX + 20 + Util.getStrLen(labelStr) + 4), (short)(startY + 3), (byte)3, constructNum, form);
            label2.setNum1(constructNum);
            form.addComponent(label2);
            break;
         case 5:
            imgId = dis.readShort();
            goodsName = dis.readUTF();
            constructNum = dis.readInt();
            pic = new UIPicture(startX, startY, (short)0, (short)0, "u-3.png", (String)null, (byte)1, form);
            pic.groupIndex = groupIndex;
            pic.colIndex = colIndex;
            pic.getType = getType;
            pic.payType = payType;
            form.addComponent(pic);
            pic.setInnerKuang(true);
            pic.setAroundFocusNull();
            Util.focusPics.addElement(pic);
            Util.focusPicIndex.addElement(new Integer(form.getComponents().size() - 1));
            Util.focusPicRowIndex.addElement(new Integer(rowIndex));
            if (groupIndex == 0) {
               ++UIForm.baochouCol;
            }

            labelStr = "Xây dựng";
            label1 = new UILabel((short)(startX + 20), startY, (short)Util.getStrLen(labelStr), (short)MainCanvas.CHARH, (byte)0, labelStr, form);
            form.addComponent(label1);
            label2 = new UILabel((short)(startX + 20 + Util.getStrLen(labelStr) + 4), (short)(startY + 3), (byte)3, constructNum, form);
            label2.setNum1(constructNum);
            form.addComponent(label2);
            break;
         case 6:
            glory = dis.readInt();
            pic = new UIPicture(startX, startY, (short)0, (short)0, "exp.png", (String)null, (byte)1, form);
            pic.groupIndex = groupIndex;
            pic.colIndex = colIndex;
            pic.getType = getType;
            pic.payType = payType;
            pic.setInnerKuang(true);
            form.addComponent(pic);
            pic.setAroundFocusNull();
            Util.focusPics.addElement(pic);
            Util.focusPicIndex.addElement(new Integer(form.getComponents().size() - 1));
            Util.focusPicRowIndex.addElement(new Integer(rowIndex));
            if (groupIndex == 0) {
               ++UIForm.baochouCol;
            }

            labelStr = "Kinh nghiệm";
            label1 = new UILabel((short)(startX + 20), startY, (short)Util.getStrLen(labelStr), (short)MainCanvas.CHARH, (byte)0, labelStr, form);
            form.addComponent(label1);
            label2 = new UILabel((short)(startX + 20 + Util.getStrLen(labelStr) + 4), (short)(startY + 3), (byte)3, glory, form);
            label2.setNum1(glory);
            form.addComponent(label2);
            break;
         case 7:
            glory = dis.readInt();
            pic = new UIPicture(startX, startY, (short)0, (short)0, "cross.png", (String)null, (byte)1, form);
            pic.groupIndex = groupIndex;
            pic.colIndex = colIndex;
            pic.getType = getType;
            pic.payType = payType;
            pic.setInnerKuang(true);
            form.addComponent(pic);
            pic.setAroundFocusNull();
            Util.focusPics.addElement(pic);
            Util.focusPicIndex.addElement(new Integer(form.getComponents().size() - 1));
            Util.focusPicRowIndex.addElement(new Integer(rowIndex));
            if (groupIndex == 0) {
               ++UIForm.baochouCol;
            }

            labelStr = "Danh vọng";
            label1 = new UILabel((short)(startX + 20), startY, (short)Util.getStrLen(labelStr), (short)MainCanvas.CHARH, (byte)0, labelStr, form);
            form.addComponent(label1);
            label2 = new UILabel((short)(startX + 20 + Util.getStrLen(labelStr) + 4), (short)(startY + 3), (byte)3, glory, form);
            label2.setNum1(glory);
            form.addComponent(label2);
            break;
         case 8:
            youName = dis.readUTF();
            pic = new UIPicture(startX, startY, (short)0, (short)0, "long.png", (String)null, (byte)1, form);
            pic.groupIndex = groupIndex;
            pic.colIndex = colIndex;
            pic.getType = getType;
            pic.payType = payType;
            pic.setInnerKuang(true);
            form.addComponent(pic);
            pic.setAroundFocusNull();
            Util.focusPics.addElement(pic);
            Util.focusPicIndex.addElement(new Integer(form.getComponents().size() - 1));
            Util.focusPicRowIndex.addElement(new Integer(rowIndex));
            if (groupIndex == 0) {
               ++UIForm.baochouCol;
            }

            label1 = new UILabel((short)(startX + 20), startY, (short)Util.getStrLen(youName), (short)MainCanvas.CHARH, (byte)0, youName, form);
            form.addComponent(label1);
         }
      } catch (Exception var16) {
         var16.printStackTrace();
      }

   }

   static UITitle readUITitle(DataInputStream dis, UIForm form) throws IOException {
      byte titleType = dis.readByte();
      UITitle title = new UITitle(titleType, form);
      String leftText;
      switch(titleType) {
      case 0:
         leftText = dis.readUTF();
         title.setTitleText(leftText);
         break;
      case 1:
         leftText = dis.readUTF();
         String rightText = dis.readUTF();
         title.setMenuText(leftText, 0);
         title.setMenuText(rightText, 1);
      }

      return title;
   }

   static UILabel readUILabel(DataInputStream dis, UIForm form) throws IOException {
      byte labelType = dis.readByte();
      short x = dis.readShort();
      short y = dis.readShort();
      String text = dis.readUTF();
      UILabel label = null;
      short w;
      int num2;
      int num;
      switch(labelType) {
      case 0:
         label = new UILabel(x, y, labelType, text, form);
         break;
      case 1:
         w = dis.readShort();
         num2 = dis.readInt();
         label = new UILabel(x, y, w, labelType, text, form);
         label.setTextColor(num2);
         break;
      case 2:
         num = dis.readInt();
         num2 = dis.readInt();
         label = new UILabel(x, y, labelType, num, num2, form);
         label.setNum1(num);
         label.setNum2(num2);
         break;
      case 3:
         num = dis.readInt();
         label = new UILabel(x, y, labelType, num, form);
         label.setNum1(num);
         break;
      case 4:
         w = dis.readShort();
         label = new UILabel(x, y, w, labelType, text, form);
      }

      return label;
   }

   static UITextField readUITextField(DataInputStream dis, UIForm form) throws IOException {
      byte style = dis.readByte();
      short x = dis.readShort();
      short y = dis.readShort();
      short w = dis.readShort();
      String text = dis.readUTF();
      short maxNum = dis.readShort();
      String textLabel = dis.readUTF();
      UITextField textField = new UITextField(x, y, w, text, style, maxNum, form);
      textField.setLabelText(textLabel);
      return textField;
   }

   static UIRadio readUIRadio(DataInputStream dis, UIForm form) throws IOException {
      byte type = dis.readByte();
      short x = dis.readShort();
      short y = dis.readShort();
      String text = dis.readUTF();
      byte itemNum = dis.readByte();
      UIRadio radio = new UIRadio(x, y, type, text, form);

      byte j;
      for(j = 0; j < itemNum; ++j) {
         String str = dis.readUTF();
         radio.add(str);
      }

      j = dis.readByte();
      radio.setSureIndex(j);
      switch(type) {
      case 0:
         boolean isHorizontal = dis.readBoolean();
         radio.setHorizontal(isHorizontal);
         radio.setWidth(radio.getAutoRadioWidth());
         radio.setHeight(radio.getAutoRadioHeight());
         radio.isCanUsed = new boolean[itemNum];

         for(int k = 0; k < itemNum; ++k) {
            boolean isCanUsed = dis.readBoolean();
            radio.setCanUsed(k, isCanUsed);
         }
      default:
         return radio;
      }
   }

   static UIPicture readUIPicture(DataInputStream dis, UIForm form) throws IOException {
      byte type = dis.readByte();
      short x = dis.readShort();
      short y = dis.readShort();
      String imgName = dis.readUTF();
      UIPicture picture = null;
      boolean isInnerKuang;
      Object pkgName;
      String word;
      switch(type) {
      case 0:
         short w = dis.readShort();
         short h = dis.readShort();
         isInnerKuang = dis.readBoolean();
         pkgName = null;
         picture = new UIPicture(x, y, w, h, imgName, (String)pkgName, type, form);
         picture.setHaveLevelNum(isInnerKuang);
         break;
      case 1:
         boolean isShowNum = dis.readBoolean();
         byte itemNum = dis.readByte();
         isInnerKuang = dis.readBoolean();
         pkgName = null;
         picture = new UIPicture(x, y, imgName, (String)pkgName, type, form);
         picture.setShowNum(isShowNum);
         picture.setShowNum(isShowNum, itemNum);
         picture.setInnerKuang(isInnerKuang);
         break;
      case 2:
         word = null;
         picture = new UIPicture(x, y, imgName, word, type, form);
         break;
      case 3:
         word = dis.readUTF();
         String pkgName = null;
         picture = new UIPicture(x, y, imgName, (String)pkgName, type, form);
         picture.setText(word);
         break;
      case 4:
         byte eightType = dis.readByte();
         picture = new UIPicture(x, y, imgName, (String)null, type, form);
         picture.setEightType(eightType);
         break;
      case 5:
         picture = new UIPicture(x, y, imgName, (String)null, type, form);
      }

      return picture;
   }

   static UIPanel readUIPanel(DataInputStream dis, UIForm form) throws IOException {
      byte type = dis.readByte();
      short x = dis.readShort();
      short y = dis.readShort();
      short w = dis.readShort();
      short h = dis.readShort();
      UIPanel panel = new UIPanel(x, y, w, h, type, form);
      switch(type) {
      case 0:
      default:
         break;
      case 1:
         short iy = dis.readShort();
         short ih = dis.readShort();
         boolean isHave = dis.readBoolean();
         boolean isHaveH = dis.readBoolean();
         panel.setInnerY(iy);
         panel.setInnerHeight((short)-1);
         break;
      case 2:
         int colorCasing = dis.readInt();
         int colorFill = dis.readInt();
         panel.setKuangColor(colorCasing);
         panel.setFillColor(colorFill);
      }

      return panel;
   }

   static UIFolder readUIFolder(DataInputStream dis, UIForm form) throws IOException {
      short x = dis.readShort();
      short y = dis.readShort();
      short w = dis.readShort();
      byte showItemLen = dis.readByte();
      UIFolder folder = new UIFolder(x, y, w, (short)0, showItemLen, form);
      int length = dis.readInt();
      Vector tmpVector = new Vector();
      int j = 0;

      for(int jj = length; j < jj; ++j) {
         String itemStr = dis.readUTF();
         tmpVector.addElement(itemStr);
      }

      folder.setItems(tmpVector);
      return folder;
   }

   static UIGrid readUIGrid(DataInputStream dis, UIForm form) throws IOException {
      short x = dis.readShort();
      short y = dis.readShort();
      String titleStr = dis.readUTF();
      short showGridCol = dis.readShort();
      short gridCol = dis.readShort();
      short gridRow = dis.readShort();
      boolean b = dis.readBoolean();
      UIGrid grid = new UIGrid(x, y, titleStr, showGridCol, gridCol, gridRow, (byte)0, form);
      grid.setHaveTitle(b);
      return grid;
   }

   static UILine readUILine(DataInputStream dis, UIForm form) throws IOException {
      short x = dis.readShort();
      short y = dis.readShort();
      UILine line = new UILine(x, y, form);
      short length = dis.readShort();
      boolean isHorizontal = dis.readBoolean();
      if (isHorizontal) {
         length = (short)(length * MainCanvas.screenW / 176);
      } else {
         length = (short)(length * MainCanvas.screenH / 208);
      }

      line.setLength(length);
      line.setHorizontal(isHorizontal);
      return line;
   }

   static UIList readUIList(DataInputStream dis, UIForm form) throws IOException {
      byte type = dis.readByte();
      short x = dis.readShort();
      short y = dis.readShort();
      short w = dis.readShort();
      short h = dis.readShort();
      UIList list = new UIList(x, y, w, h, type, form);
      switch(type) {
      case 0:
         byte length = dis.readByte();

         for(int j = 0; j < length; ++j) {
            String itemStr = dis.readUTF();
            list.addItem(new ListItem(itemStr, (byte)0, (byte)0, list.getListWidth()));
         }

         boolean isCenter = dis.readBoolean();
         boolean isDrawLine = dis.readBoolean();
         boolean isCanSelected = dis.readBoolean();
         list.setCenter(isCenter);
         list.setDrawLine(isDrawLine);
         list.setCanSelected(isCanSelected);
         list.setShowCol(list.getCanShowCol(type));
         UIList.addUIScroll(list, (byte)0);
      default:
         return list;
      }
   }

   static UITextArea readUITextArea(DataInputStream dis, UIForm form) throws IOException {
      short x = dis.readShort();
      short y = dis.readShort();
      short w = dis.readShort();
      short h = dis.readShort();
      byte rimType = dis.readByte();
      short textAreaW = dis.readShort();
      short contentFullRows = dis.readShort();
      short canShowRows = dis.readShort();
      int color = dis.readInt();
      String content = dis.readUTF();
      UITextArea area = new UITextArea(x, y, w, h, form);
      area.setRimType(rimType);
      area.setColor(color);
      area.setContent(content);
      area.addUIScroll();
      return area;
   }

   static UITable readUITable(DataInputStream dis, UIForm form) throws IOException {
      short x = dis.readShort();
      short y = dis.readShort();
      short w = dis.readShort();
      short h = dis.readShort();
      byte tableColNum = dis.readByte();
      byte tableRowNum = dis.readByte();
      short tableColHeight = dis.readShort();
      TableItem titleItem = new TableItem();

      for(int k = 0; k < tableRowNum; ++k) {
         byte widgetType = dis.readByte();
         UILabel label = readUILabel(dis, form);
         titleItem.addItem(label);
      }

      UITable table = new UITable(x, y, w, h, tableRowNum, titleItem, form);
      table.setAllRowNum(tableColNum);
      table.setAllColNum(tableRowNum);
      short[] termW = new short[tableRowNum];

      for(int k = 0; k < tableRowNum; ++k) {
         termW[k] = dis.readShort();
      }

      boolean[] isCenter = new boolean[tableRowNum];

      int k;
      for(k = 0; k < tableRowNum; ++k) {
         isCenter[k] = dis.readBoolean();
      }

      for(k = 0; k < tableColNum; ++k) {
         TableItem ti = new TableItem();
         ti.setTermHeightFirst(tableColHeight);

         for(int j = 0; j < tableRowNum; ++j) {
            UIComponent uic2 = null;
            byte widgetType = dis.readByte();
            switch(widgetType) {
            case 1:
               uic2 = readUILabel(dis, form);
            case 2:
            case 3:
            default:
               break;
            case 4:
               uic2 = readUIPicture(dis, form);
            }

            boolean isUserData = dis.readBoolean();
            ((UIComponent)uic2).setUserData(isUserData);
            if (((UIComponent)uic2).isUserData()) {
               short userDataType = dis.readShort();
               ((UIComponent)uic2).setUserDataType(userDataType);
            }

            ti.addItem((UIComponent)uic2);
         }

         ti.setTermWidth(termW);
         ti.setIsCenter(isCenter);
         table.addItem(ti);
      }

      if (table.getTitleItem() != null) {
         table.getTitleItem().setTermWidth(termW);
      }

      return table;
   }

   private static UINull readUINull(DataInputStream dis, UIForm form) throws IOException {
      return new UINull((short)0, (short)0, (short)0, (short)0, form);
   }
}

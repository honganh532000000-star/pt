import java.util.Vector;

public class PCPackage {
   public static boolean needExtend = false;
   public static byte extendIndex = 0;
   public static int botianID = 0;
   public static int m_nGrade = 0;
   public static int m_nClanGrade = 0;
   public static byte equipIndex = 4;
   public static byte moveNum = 0;
   public static int[] exchangeIndex = new int[]{-1, -1};

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 458753:
         ba.writeByte(UIGrid.severGridIndex);
         MainCanvas.isWaiting = true;
         break;
      case 458754:
      case 458775:
         ba.writeByte(UIGrid.severGridIndex);
         MainCanvas.isWaiting = true;
         break;
      case 458755:
      case 458768:
         ba.writeByte(UIGrid.severGridIndex);
         MainCanvas.isWaiting = true;
         break;
      case 458756:
         ba.writeByte(exchangeIndex[0]);
         ba.writeByte(exchangeIndex[1]);
         ba.writeByte(moveNum);
         MainCanvas.curForm.resetExchangeVal();
         MainCanvas.isWaiting = true;
      case 458757:
      case 458761:
      case 458764:
      case 458765:
      case 458766:
      case 458767:
      case 458774:
      default:
         break;
      case 458758:
         MainCanvas.isWaiting = true;
         break;
      case 458759:
         ba.writeByte(UIGrid.dropIndex);
         MainCanvas.isWaiting = true;
         break;
      case 458760:
         MainCanvas.isWaiting = true;
         break;
      case 458762:
         MainCanvas.isWaiting = true;
      case 458763:
         ba.writeInt(SIShortCut.shortCutId);
         break;
      case 458769:
         ba.writeByte(UIGrid.severGridIndex);
         ba.writeByte(extendIndex);
         needExtend = false;
         UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
         menuBar.setMenuText("Quay về", 1);
         setExtendPicMoveToGrid(true);
         break;
      case 458770:
         ba.writeByte(extendIndex);
         MainCanvas.isWaiting = true;
         break;
      case 458771:
         UIList list = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
         ba.writeByte(list.botianIndex);
         ba.writeInt(botianID);
         MainCanvas.isWaiting = true;
         break;
      case 458772:
         MainCanvas.isWaiting = true;
         break;
      case 458773:
         MainCanvas.isWaiting = true;
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      int length;
      int size;
      switch(commID) {
      case -2147024884:
         boolean isCD = data.readBoolean();
         short num = data.readShort();
         int[] stuffIDs = new int[num];

         for(int i = 0; i < num; ++i) {
            stuffIDs[i] = data.readInt();
         }

         SIManager.getInstance();
         SIShortCut sc = SIManager.shortCut;

         int i;
         for(size = 0; size < num; ++size) {
            i = stuffIDs[size];
            sc.setCD((byte)2, i, isCD);
         }

         sc.refreshShortCutAll();
         if (MainCanvas.curForm != null) {
            setCD(MainCanvas.curForm, num, isCD, stuffIDs);
            size = MainCanvas.curFormVector.size();

            for(i = 0; i < size; ++i) {
               UIForm form = (UIForm)MainCanvas.curFormVector.elementAt(i);
               if (form != null) {
                  setCD(form, num, isCD, stuffIDs);
               }
            }

            size = MainCanvas.backForms.size();

            for(i = 0; i < size; ++i) {
               Object ob = MainCanvas.backForms.elementAt(i);
               if (ob instanceof UIForm) {
                  UIForm form = (UIForm)ob;
                  setCD(form, num, isCD, stuffIDs);
               } else if (ob instanceof Vector) {
                  Vector vc = (Vector)ob;
                  length = vc.size();

                  for(int j = 0; j < length; ++j) {
                     Object oj = vc.elementAt(j);
                     if (oj instanceof UIForm) {
                        UIForm fm = (UIForm)oj;
                        setCD(fm, num, isCD, stuffIDs);
                     }
                  }
               }
            }
         }
         break;
      case -2147024883:
         boolean isUnLock = data.readBoolean();
         byte beginIndex = data.readByte();
         byte endIndex = data.readByte();
         UIGrid grid = null;
         if (MainCanvas.curForm != null) {
            if (MainCanvas.curForm.clientCommand == 458760) {
               grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(10);
            } else if (MainCanvas.curForm.clientCommand == 1179649) {
               grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(8);
            } else if (MainCanvas.curForm.clientCommand == 917505) {
               grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(23);
            } else if (MainCanvas.curForm.clientCommand != 983046 && MainCanvas.curForm.clientCommand != 983057 && MainCanvas.curForm.clientCommand != 983049 && MainCanvas.curForm.clientCommand != 983058 && MainCanvas.curForm.clientCommand != 983056) {
               if (MainCanvas.curForm.clientCommand != 1769473 && MainCanvas.curForm.clientCommand != 1769478 && MainCanvas.curForm.clientCommand != 1769479 && MainCanvas.curForm.clientCommand != 1769480 && MainCanvas.curForm.clientCommand != 1769488 && MainCanvas.curForm.clientCommand != 1769489 && MainCanvas.curForm.clientCommand != 1769490 && MainCanvas.curForm.clientCommand != 1769491 && MainCanvas.curForm.clientCommand != 1769492) {
                  if (MainCanvas.curForm.clientCommand == 1179658) {
                     grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(3);
                  }
               } else {
                  grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(5);
               }
            } else {
               grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(5);
            }

            if (grid != null) {
               for(length = beginIndex; length < endIndex; ++length) {
                  if (isUnLock) {
                     grid.stuffLock[length] = false;
                  } else {
                     grid.stuffLock[length] = true;
                  }
               }
            }
         }
      case -2147024882:
      case -2147024879:
      case -2147024878:
      case -2147024877:
      case -2147024876:
      case -2147024875:
      default:
         break;
      case -2147024881:
         size = data.readInt();
         SIManager.getInstance();
         SIManager.shortCut.removeGoods(size);
         break;
      case -2147024880:
         byte result = data.readByte();
         if (result == 0) {
            needExtend = false;
         } else {
            needExtend = true;
            UIPicture[] pics = new UIPicture[]{(UIPicture)MainCanvas.curForm.getComponents().elementAt(3), (UIPicture)MainCanvas.curForm.getComponents().elementAt(5), (UIPicture)MainCanvas.curForm.getComponents().elementAt(7), (UIPicture)MainCanvas.curForm.getComponents().elementAt(9)};

            for(int i = 0; i < pics.length; ++i) {
               pics[i].isExtendPic = (result & Util.factorial(2, i)) != 0;
            }

            UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(10);
            pics[0].setFocus(true, MainCanvas.curForm);
            grid.setFocus(false, MainCanvas.curForm);
            UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
            menuBar.setMenuText("Hủy", 1);
            setExtendPicMoveToGrid(false);
         }
         break;
      case -2147024874:
         equipIndex = (byte)(data.readByte() + 4);
      }

      MainCanvas.isWaiting = false;
   }

   private static void setCD(UIForm form, int num, boolean isCD, int[] stuffIDs) {
      int stuffID;
      int stuffID;
      switch(form.clientCommand) {
      case -1610612734:
         for(int j = 0; j < num; ++j) {
            stuffID = stuffIDs[j];

            for(stuffID = 0; stuffID < 3; ++stuffID) {
               UIGrid grid = (UIGrid)form.getComponents().elementAt(3 + stuffID);

               for(int k = 0; k < grid.getCanUseNum(); ++k) {
                  if (grid.getItemType(k) == 2 && grid.getItemID(k) == stuffID) {
                     grid.setItemCD(k, isCD);
                  }
               }
            }
         }

         return;
      case 458754:
      case 458756:
      case 458758:
      case 458759:
      case 458760:
      case 458761:
      case 458775:
         if (form.getComponents().elementAt(10) instanceof UIGrid) {
            UIGrid grid = (UIGrid)form.getComponents().elementAt(10);

            for(stuffID = 0; stuffID < num; ++stuffID) {
               stuffID = stuffIDs[stuffID];

               for(int j = 0; j < grid.canUseNum; ++j) {
                  if (grid.stuffId[j] == stuffID) {
                     grid.stuffCD[j] = isCD;
                  }
               }
            }
         }
      }

   }

   public static byte getExtendIndex() {
      if (((UIPicture)MainCanvas.curForm.getComponents().elementAt(3)).isFocus()) {
         return 0;
      } else if (((UIPicture)MainCanvas.curForm.getComponents().elementAt(5)).isFocus()) {
         return 1;
      } else if (((UIPicture)MainCanvas.curForm.getComponents().elementAt(7)).isFocus()) {
         return 2;
      } else {
         return (byte)(((UIPicture)MainCanvas.curForm.getComponents().elementAt(9)).isFocus() ? 3 : -1);
      }
   }

   public static void setExtendPicMoveToGrid(boolean isMove) {
      if (isMove) {
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(3)).setAroundComponent((byte)1, (byte)11);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(5)).setAroundComponent((byte)1, (byte)11);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(7)).setAroundComponent((byte)1, (byte)11);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(9)).setAroundComponent((byte)1, (byte)11);
      } else {
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(3)).setAroundComponent((byte)1, (byte)-1);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(5)).setAroundComponent((byte)1, (byte)-1);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(7)).setAroundComponent((byte)1, (byte)-1);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(9)).setAroundComponent((byte)1, (byte)-1);
      }

   }
}

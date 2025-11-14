import javax.microedition.lcdui.Graphics;

public class KeyConfig {
   static String[] str = new String[]{"Cài đặt trang web:<Lựa chọn tự động>", "Hãy ấn phím trái", "Hãy ấn phím phải", "Hãy ấn phím giữa đến hoàn thành cài đặt"};
   static int selectIndex = 0;
   static int selectNet = 2;
   static int[] keySet = new int[3];
   static String selectLStr = null;
   static String selectRStr = null;
   static String selectFStr = null;
   static int tickTime;

   public static void drawKeySet(Graphics g) {
      g.setColor(13808780);
      g.drawString("Ấn phím cài đặt", MainCanvas.screenW >> 1, 5, 17);
      g.setColor(16766720);
      if (tickTime % 3 == 0) {
         g.fillRect(8, 30 + selectIndex * (MainCanvas.CHARH + 4) + 4, 8, 8);
      } else {
         g.fillRect(7, 30 + selectIndex * (MainCanvas.CHARH + 4) + 3, 6, 6);
      }

      int i = 0;

      for(int len = str.length; i < len; ++i) {
         if (i == selectIndex) {
            g.setColor(16766720);
         } else {
            g.setColor(12092939);
         }

         g.drawString(str[i], 20, 30 + i * (MainCanvas.CHARH + 4), 20);
      }

      g.setColor(3100495);
      g.fillRect((MainCanvas.screenW >> 1) - 25, MainCanvas.screenH >> 1, 50, 80);
      g.setColor(6266528);
      g.fillRect((MainCanvas.screenW >> 1) - 22, (MainCanvas.screenH >> 1) + 3, 44, 50);
      g.fillRect((MainCanvas.screenW >> 1) - 25 + 2, (MainCanvas.screenH >> 1) + 55, 15, 10);
      g.fillRect((MainCanvas.screenW >> 1) + 25 - 2 - 15, (MainCanvas.screenH >> 1) + 55, 15, 10);
      g.fillRect((MainCanvas.screenW >> 1) - 4, (MainCanvas.screenH >> 1) + 56, 8, 8);
      if (tickTime % 3 == 0) {
         g.setColor(65535);
         switch(selectIndex) {
         case 1:
            g.fillRect((MainCanvas.screenW >> 1) - 25 + 2, (MainCanvas.screenH >> 1) + 55, 15, 10);
            break;
         case 2:
            g.fillRect((MainCanvas.screenW >> 1) + 25 - 2 - 15, (MainCanvas.screenH >> 1) + 55, 15, 10);
            break;
         case 3:
            g.fillRect((MainCanvas.screenW >> 1) - 4, (MainCanvas.screenH >> 1) + 56, 8, 8);
         }
      }

   }

   public static boolean isNeedKey(int keyCode) {
      boolean flag = true;
      int[] key_list = new int[]{48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 42, 35, MainCanvas.mc.getKeyCode(2), MainCanvas.mc.getKeyCode(5), MainCanvas.mc.getKeyCode(1), MainCanvas.mc.getKeyCode(6), MainCanvas.mc.getKeyCode(8)};

      for(int i = 0; i < key_list.length; ++i) {
         if (keyCode == key_list[i]) {
            flag = false;
         }
      }

      return flag;
   }

   public static void tick() {
      ++tickTime;
      if (tickTime > 100) {
         tickTime = 0;
      }

   }

   public static void keyInConfig(int keyCode) {
      byte[] connectType = new byte[1];
      int temp = MainCanvas.mc.getGameAction(keyCode);
      if (temp == 1) {
         if (selectIndex > 0) {
            --selectIndex;
         }

      } else if (temp == 6) {
         if (selectIndex < str.length - 1) {
            ++selectIndex;
         }

      } else {
         if (temp == 8) {
            if (selectLStr == null) {
               selectIndex = 1;
               return;
            }

            if (selectRStr == null) {
               selectIndex = 2;
               return;
            }

            keySet[2] = keyCode;
            connectType[0] = (byte)selectNet;
            Util.saveIntRecord(keySet, "keyValue");
            Util.saveByteRecord(connectType, "gamemount");
            if (selectNet == 0) {
               MainCanvas.isAutoConn = false;
               MainCanvas.isCMWAP = true;
            } else if (selectNet == 1) {
               MainCanvas.isAutoConn = false;
               MainCanvas.isCMWAP = false;
            } else if (selectNet == 2) {
               MainCanvas.isAutoConn = true;
               MainCanvas.isCMWAP = true;
            }

            MainCanvas.keyvalue = Util.loadIntRecord("keyValue", keySet.length);
            MainCanvas.setState((byte)4);
         }

         switch(selectIndex) {
         case 0:
            if (temp == 2) {
               if (selectNet > 0) {
                  --selectNet;
               } else {
                  selectNet = 2;
               }
            } else if (temp == 5) {
               if (selectNet < 2) {
                  ++selectNet;
               } else {
                  selectNet = 0;
               }
            }

            String netStr = null;
            switch(selectNet) {
            case 0:
               netStr = "Cài đặt web:<CMWAP>";
               break;
            case 1:
               netStr = "Cài đặt web:<CMNET>";
               break;
            case 2:
               netStr = "Cài đặt web:<Tự động>";
            }

            str[selectIndex] = netStr;
            break;
         case 1:
            if (isNeedKey(keyCode)) {
               keySet[0] = keyCode;
               if (keyCode == keySet[1]) {
                  selectIndex = 2;
                  return;
               }

               if (selectLStr == null) {
                  selectLStr = "ok";
                  str[selectIndex] = str[selectIndex] + selectLStr;
               }

               ++selectIndex;
            }
            break;
         case 2:
            if (isNeedKey(keyCode)) {
               if (keyCode == keySet[0]) {
                  selectIndex = 1;
                  return;
               }

               keySet[1] = keyCode;
               if (selectRStr == null) {
                  selectRStr = "ok";
                  str[selectIndex] = str[selectIndex] + selectRStr;
               }

               ++selectIndex;
            }
         }

      }
   }

   public static void keyInMainSet(int keyCode) {
      UIRadio netSet = (UIRadio)MainCanvas.curForm.getComponents().elementAt(2);
      UILabel soft1Set = (UILabel)MainCanvas.curForm.getComponents().elementAt(3);
      UILabel soft2Set = (UILabel)MainCanvas.curForm.getComponents().elementAt(4);
      UILabel fireSet = (UILabel)MainCanvas.curForm.getComponents().elementAt(5);
      UITextArea netInfo = (UITextArea)MainCanvas.curForm.getComponents().elementAt(7);
      int temp = MainCanvas.mc.getGameAction(keyCode);
      if (temp == 1 && !netInfo.isFocus()) {
         if (selectIndex > 0) {
            --selectIndex;
         }

         if (!netSet.isFocus()) {
            if (soft1Set.isFocus()) {
               netInfo.setFocus(true, MainCanvas.curForm);
               soft1Set.setFocus(false, MainCanvas.curForm);
            } else if (soft2Set.isFocus()) {
               soft1Set.setFocus(true, MainCanvas.curForm);
               soft2Set.setFocus(false, MainCanvas.curForm);
            } else if (fireSet.isFocus()) {
               soft2Set.setFocus(true, MainCanvas.curForm);
               fireSet.setFocus(false, MainCanvas.curForm);
            }

         }
      } else if (temp == 6 && !netInfo.isFocus()) {
         if (selectIndex < 3) {
            ++selectIndex;
         }

         if (netSet.isFocus()) {
            netSet.setFocus(false, MainCanvas.curForm);
            netInfo.setFocus(true, MainCanvas.curForm);
         } else if (soft1Set.isFocus()) {
            soft1Set.setFocus(false, MainCanvas.curForm);
            soft2Set.setFocus(true, MainCanvas.curForm);
         } else if (soft2Set.isFocus()) {
            soft2Set.setFocus(false, MainCanvas.curForm);
            fireSet.setFocus(true, MainCanvas.curForm);
         } else if (fireSet.isFocus()) {
            return;
         }

      } else {
         if (fireSet.isFocus()) {
            if (temp == 8) {
               if (selectLStr == null) {
                  selectIndex = 1;
                  soft1Set.setFocus(true, MainCanvas.curForm);
                  fireSet.setFocus(false, MainCanvas.curForm);
                  return;
               }

               if (selectRStr == null) {
                  selectIndex = 2;
                  soft2Set.setFocus(true, MainCanvas.curForm);
                  fireSet.setFocus(false, MainCanvas.curForm);
                  return;
               }

               if (selectFStr == null) {
                  selectFStr = "ok";
                  fireSet.setText(fireSet.getText() + selectRStr);
               }

               keySet[2] = keyCode;
            } else if (MainCanvas.isInputDown(262144)) {
               backSetup();
            }
         } else if (netSet.isFocus()) {
            if (temp == 2) {
               if (selectNet > 0) {
                  --selectNet;
               } else {
                  selectNet = 2;
               }

               radioSet();
            } else if (temp == 5) {
               if (selectNet < 2) {
                  ++selectNet;
               } else {
                  selectNet = 0;
               }

               radioSet();
            } else if (MainCanvas.isInputDown(262144)) {
               backSetup();
            }
         } else if (soft1Set.isFocus()) {
            if (isNeedKey(keyCode)) {
               keySet[0] = keyCode;
               if (keyCode == keySet[1]) {
                  soft1Set.setFocus(false, MainCanvas.curForm);
                  soft2Set.setFocus(true, MainCanvas.curForm);
                  return;
               }

               if (selectLStr == null) {
                  selectLStr = "ok";
                  soft1Set.setText(soft1Set.getText() + selectLStr);
               }

               soft1Set.setFocus(false, MainCanvas.curForm);
               soft2Set.setFocus(true, MainCanvas.curForm);
            }
         } else if (soft2Set.isFocus()) {
            if (isNeedKey(keyCode)) {
               if (keyCode == keySet[0]) {
                  soft2Set.setFocus(false, MainCanvas.curForm);
                  soft1Set.setFocus(true, MainCanvas.curForm);
                  return;
               }

               keySet[1] = keyCode;
               if (selectRStr == null) {
                  selectRStr = "ok";
                  soft2Set.setText(soft2Set.getText() + selectRStr);
               }

               soft2Set.setFocus(false, MainCanvas.curForm);
               fireSet.setFocus(true, MainCanvas.curForm);
            }
         } else if (netInfo.isFocus()) {
            MainCanvas.curForm.keyInForm();
         }

      }
   }

   public static void keyInNetSet(int keyCode) {
      UIRadio netSet = (UIRadio)MainCanvas.curForm.getComponents().elementAt(2);
      UITextArea netInfo = (UITextArea)MainCanvas.curForm.getComponents().elementAt(7);
      byte[] connectType = new byte[1];
      int temp = MainCanvas.mc.getGameAction(keyCode);
      if (netSet.isFocus()) {
         if (temp == 1) {
            return;
         }

         if (temp == 6) {
            netSet.setFocus(false, MainCanvas.curForm);
            netInfo.setFocus(true, MainCanvas.curForm);
         } else if (temp == 2) {
            if (selectNet > 0) {
               --selectNet;
            } else {
               selectNet = 2;
            }

            radioSet();
         } else if (temp == 5) {
            if (selectNet < 2) {
               ++selectNet;
            } else {
               selectNet = 0;
            }

            radioSet();
         } else if (MainCanvas.isInputDown(262144)) {
            backSetup();
         }
      } else if (netInfo.isFocus()) {
         MainCanvas.curForm.keyInForm();
      } else {
         netInfo.setFocus(true, MainCanvas.curForm);
      }

   }

   public static void radioSet() {
      UIRadio netSet = (UIRadio)MainCanvas.curForm.getComponents().elementAt(2);
      int index = 0;
      switch(selectNet) {
      case 0:
         index = 1;
         break;
      case 1:
         index = 2;
         break;
      case 2:
         index = 0;
      }

      netSet.setSelectIndex((byte)index);
      UITextArea netContent = (UITextArea)MainCanvas.curForm.getComponents().elementAt(7);
      netContent.setContent(Cons.netContent[index]);
   }

   public static void backSetup() {
      byte[] connectType = new byte[1];
      if (!MainCanvas.SUPPORT_POINTER) {
         int[] keySave = Util.loadIntRecord("keyValue", keySet.length);
         if (keySave != null) {
            int i = 0;

            for(int len = keySave.length; i < len; ++i) {
               if (keySet[i] != 0) {
                  keySave[i] = keySet[i];
               }
            }

            Util.saveIntRecord(keySave, "keyValue");
            MainCanvas.keyvalue = Util.loadIntRecord("keyValue", keySet.length);
         }
      }

      connectType[0] = (byte)selectNet;
      Util.saveByteRecord(connectType, "gamemount");
      if (selectNet == 0) {
         MainCanvas.isAutoConn = false;
         MainCanvas.isCMWAP = true;
      } else if (selectNet == 1) {
         MainCanvas.isAutoConn = false;
         MainCanvas.isCMWAP = false;
      } else if (selectNet == 2) {
         MainCanvas.isAutoConn = true;
         MainCanvas.isCMWAP = true;
      }

      if (UIForm.netFailOverMount) {
         if (MainCanvas.mc.getOldState() == 11) {
            MainCanvas.setState((byte)11);
         } else {
            MainCanvas.setState((byte)4);
         }

         UIForm.netFailOverMount = false;
      } else {
         MainCanvas.setState((byte)4);
      }

   }

   public static void touchScreenAction() {
      if (MainCanvas.SUPPORT_POINTER && MainCanvas.isInputDown(268435456)) {
         int x1 = MainCanvas.screenW - (MainCanvas.CHARW + 4 << 1);
         int y1 = MainCanvas.screenH - UITitle.getMenuBarHeight();
         int w1 = MainCanvas.CHARW + 4 << 1;
         int h1 = UITitle.getMenuBarHeight();
         UIRadio netSet = (UIRadio)MainCanvas.curForm.getComponents().elementAt(2);
         if (MainCanvas.isPointInRect(x1, y1, w1, h1)) {
            backSetup();
         }

         int startX = netSet.positionX;
         if (netSet.text != null && !netSet.text.equals("")) {
            startX += netSet.text.length() * MainCanvas.CHARW + 8;
         }

         if (MainCanvas.isPointInRect(startX, netSet.positionY, MainCanvas.ui_aImg.getWidth(), MainCanvas.ui_aImg.getHeight())) {
            if (selectNet > 0) {
               --selectNet;
            } else {
               selectNet = 2;
            }

            radioSet();
         } else if (MainCanvas.isPointInRect(startX + MainCanvas.ui_aImg.getWidth() + 4 * 2 + Util.getStrLen((String)netSet.items.elementAt(selectIndex)), netSet.positionY, MainCanvas.ui_aImg.getWidth(), MainCanvas.ui_aImg.getHeight())) {
            if (selectNet < 2) {
               ++selectNet;
            } else {
               selectNet = 0;
            }

            radioSet();
         }
      }

   }

   public static void clear() {
      selectLStr = null;
      selectRStr = null;
      selectFStr = null;
      keySet = new int[3];
   }
}

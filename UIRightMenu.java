import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public class UIRightMenu extends UIComponent {
   private static final String[] MENU_MAIN = new String[]{"Nhân vật", "Túi đồ", "Năng lực", "Cộng đồng", "Shop", "Bản đồ", "Đội ngũ", "Nhiệm vụ", "Thiết lập", "Phục vụ", "Thoát"};
   private static final String[] TOURIST_MENU_MAIN = new String[]{"Nhân vật", "Túi đồ", "Năng lực", "Cộng đồng", "Đăng  ký", "Bản đồ", "Đội ngũ", "Nhiệm vụ", "Thiết lập", "Phục vụ", "Thoát"};
   private static final String[] FORMAL_TOURIST_MENU_MAIN = new String[]{"Nhân vật", "Túi đồ", "Năng lực", "Cộng đồng", "Sửa tên", "Bản đồ", "Đội ngũ", "Nhiệm vụ", "Thiết lập", "Phục vụ", "Thoát"};
   private static final String MENU_CHAT = "Kênh Chat";
   private static final String[] MENU_PLAYER = new String[]{"Cơ bản", "Công thủ", "Trang bị", "Danh vọng", "Chiến tích", "Thông tin", "Thời gian", "Xếp hạng", "Treo máy"};
   private static final String[] MENU_CAPTION = new String[]{"Chọn trang chủ", "Năng  lực", "Cộng đồng", "Bản đồ", "Thiết lập", "Phục vụ ", "Nhân vật", "Rời khỏi", "Thị tộc", "Chư Hầu", "Sản xuất", "Thu tập", "Nhiệm vụ", "Tiền Trang", "Dịch vụ KH", "Sư đồ", "Bảo thạch", "Treo máy"};
   private static final String[] MENU_GENIUS = new String[]{"Thiên phú", "Kỹ năng", "Phím tắt", "Sản xuất", "Thu tập", "Bảo thạch", "Khắc danh", "Cường hóa"};
   private static final String[] MENU_SOCIALITY = new String[]{"Tra tìm", "Kết bạn", "Bạn bè", "DS đen", "Chat", "Kẻ thù", "Forum"};
   private static final String[] MENU_PRODUCE = new String[]{"Chế tạo", "Chú giáp", "Thuộc da", "May mặc", "Ngưng nguyên", "Phân giải"};
   private static final String[] MENU_TASK = new String[]{"Có thể nhận", "Nhiệm vụ", "Có thể thiết lập", "Lưu giữ"};
   private static final String[] MENU_COLLECT = new String[]{"Đào khoáng", "Hái thuốc", "Dệt vải", "Bác bì"};
   private static final String[] MENU_MAP = new String[]{"Bản địa", "Tìm đường", "Thế giới"};
   private static final String[] MENU_OPTION = new String[]{"Phím", "Phím tắt", "Hiển thị", "Chat", "Khác"};
   private static final String[] MENU_SERVICE = new String[]{"Forum", "Dịch vụ KH", "Hỗ trợ", "Cố Định"};
   private static final String[] MENU_SERVICE_1 = new String[]{"Dịch vụ KH", "Hỗ trợ"};
   private static final String[] MENU_SERVICE_2 = new String[]{"Hỏi", "Trả lời"};
   private static final String[] MENU_EXIT = new String[]{"Đổi nhân vật", "Menu", "Trang chủ"};
   private static final String[] MENU_GEM = new String[]{"Khai phong", "Điêu khắc", "Hợp thành", "Giải trừ", "Hoàn nguyên"};
   private static final String[] MENU_HANG = new String[]{"Treo máy", "Kỹ năng công", "Kỹ năng thủ", "Hỗ trợ"};
   public Stack savePositionIDStack = new Stack();
   public static String pName = "";
   public static long startSendT = 0L;
   public static boolean isDraw = false;
   int wordX = 0;
   int wordY = 0;
   int wordW = 0;
   int wordH = 0;
   public static UIRightMenu.Menu curMenu = null;
   public UIRightMenu.Menu menuMain = null;
   public UIRightMenu.Menu menuPlayer = null;
   public UIRightMenu.Menu menuGenius = null;
   public UIRightMenu.Menu menuSociality = null;
   public UIRightMenu.Menu menuMap = null;
   public UIRightMenu.Menu menuOption = null;
   public UIRightMenu.Menu menuService = null;
   public UIRightMenu.Menu menuExit = null;
   public UIRightMenu.Menu menuProduce = null;
   public UIRightMenu.Menu menuCollect = null;
   public UIRightMenu.Menu menuChat = null;
   public UIRightMenu.Menu menuTask = null;
   public UIRightMenu.Menu menuIncrement = null;
   public UIRightMenu.Menu menuKefu = null;
   public UIRightMenu.Menu menuGem = null;
   public UIRightMenu.Menu menuHang = null;
   public static final int MENUROW = 4;
   public static final int MENUCOLUM = 3;

   public UIRightMenu() {
      super((short)0, (short)0, (short)0, (short)0, (UIForm)null);
      this.init();
      super.positionY = (short)(MainCanvas.screenH >> 3);
      this.setFocus(true, (UIForm)null);
   }

   public void keyInAction() {
      if (curMenu != null) {
         curMenu.operation();
      }
   }

   public void draw(Graphics g) {
      if (curMenu != null) {
         this.drawCaptionRim(g);
         this.drawTable(g);
         curMenu.draw(g);
      }
   }

   private void drawCaptionRim(Graphics g) {
      int w = (4 * MainCanvas.CHARW + 22) * MainCanvas.screenW / 176;
      int h = (MainCanvas.CHARH + 12) * MainCanvas.screenH / 208;
      int x = MainCanvas.screenW - w >> 1;
      g.setColor(Cons.COLOR_RIGHT_MENU_1);
      g.drawRect(x, super.positionY - 1, w, h);
      g.setColor(Cons.COLOR_RIGHT_MENU_2);
      g.drawRect(x + 1, super.positionY, w - 2, h - 2);
      g.setColor(Cons.COLOR_RIGHT_MENU_3);
      g.fillRect(x + 2, super.positionY + 1, w - 3, h - 3);
      g.setColor(Cons.COLOR_RIGHT_MENU_1);
      g.drawRect(x + 5, super.positionY + 2, w - 10, h - 6);
      g.drawRect(x + 6, super.positionY + 2, w - 12, h - 6);
      g.drawRect(x + 7, super.positionY + 2, w - 14, h - 6);
      g.drawRect(x + 9, super.positionY + 4, w - 18, h - 10);
   }

   private void drawTable(Graphics g) {
      int y = super.positionY + (MainCanvas.CHARH + 14) * MainCanvas.screenH / 208;
      int w = 166 * MainCanvas.screenW / 176;
      int x = MainCanvas.screenW - w >> 1;
      int h = 81 * MainCanvas.screenH / 208;
      g.setColor(Cons.COLOR_RIGHT_MENU_1);
      g.drawRect(x, y, w, h);
      g.setColor(Cons.COLOR_RIGHT_MENU_2);
      g.drawRect(x + 1, y + 1, w - 2, h - 2);
      g.setColor(16575691);
      g.fillRect(x + 2, y + 2, w - 3, h - 3);
      g.setColor(Cons.COLOR_RIGHT_MENU_1);
      int tmpW = w - 13;
      int tmpH = h - 9;
      g.drawRect(MainCanvas.screenW - tmpW >> 1, y + (h - tmpH >> 1), tmpW, tmpH);
      tmpW = w - 15;
      tmpH = h - 9;
      g.drawRect(MainCanvas.screenW - tmpW >> 1, y + (h - tmpH >> 1), tmpW, tmpH);
      tmpW = w - 17;
      tmpH = h - 9;
      g.drawRect(MainCanvas.screenW - tmpW >> 1, y + (h - tmpH >> 1), tmpW, tmpH);
      tmpW = w - 21;
      tmpH = h - 13;
      int tmpX = MainCanvas.screenW - tmpW >> 1;
      int tmpY = y + (h - tmpH >> 1);
      g.setColor(14602158);
      int cellH = (tmpH + 1) / 4;
      int cellW = (tmpW + 1) / 3;
      this.wordX = tmpX;
      this.wordY = tmpY;
      this.wordW = cellW;
      this.wordH = cellH;

      int i;
      for(i = 0; i < 12; ++i) {
         if (i % 2 == 0) {
            int dx = i / 3;
            int dy = i % 3;
            g.fillRect(tmpX + dy * cellW, tmpY + dx * cellH, cellW + 1, cellH);
         }
      }

      g.setColor(Cons.COLOR_RIGHT_MENU_1);
      g.drawRect(MainCanvas.screenW - tmpW >> 1, y + (h - tmpH >> 1), tmpW, tmpH);
      g.drawImage(MainCanvas.ui_1Img, -2, y + 4, 20);
      g.drawImage(MainCanvas.ui_1Img, -2, y + h - MainCanvas.ui_1Img.getHeight() - 4, 20);
      Util.drawImage(g, MainCanvas.ui_1Img, MainCanvas.screenW - MainCanvas.ui_1Img.getWidth() + 2, y + 4, 2);
      Util.drawImage(g, MainCanvas.ui_1Img, MainCanvas.screenW - MainCanvas.ui_1Img.getWidth() + 2, y + h - MainCanvas.ui_1Img.getHeight() - 4, 2);
      g.setColor(Cons.COLOR_RIGHT_MENU_1);

      for(i = 0; i < 3; ++i) {
         g.drawLine(tmpX, tmpY + cellH * (i + 1), tmpX + tmpW, tmpY + cellH * (i + 1));
      }

      for(i = 0; i < 2; ++i) {
         g.drawLine(tmpX + cellW * (i + 1), tmpY, tmpX + cellW * (i + 1), tmpY + tmpH);
      }

   }

   private void drawCenterStr(Graphics g, String str, int x1, int y1, int w1, int h1, Font font) {
      int w2 = font.stringWidth(str);
      int h2 = MainCanvas.CHARH;
      int x = x1 + ((w1 - w2) % 2 == 0 ? (w1 - w2) / 2 : (w1 - w2) / 2 + 1);
      int y = y1 + (h1 - h2) / 2 + 2;
      g.drawString(str, x, y, 20);
   }

   public void init() {
      if (MainCanvas.m_bTourist == 1) {
         this.menuMain = new UIRightMenu.Menu(MENU_CAPTION[0], TOURIST_MENU_MAIN);
      } else if (MainCanvas.m_bTourist == 2) {
         this.menuMain = new UIRightMenu.Menu(MENU_CAPTION[0], FORMAL_TOURIST_MENU_MAIN);
      } else {
         this.menuMain = new UIRightMenu.Menu(MENU_CAPTION[0], MENU_MAIN);
      }

      this.menuGenius = new UIRightMenu.Menu(MENU_CAPTION[1], MENU_GENIUS);
      this.menuSociality = new UIRightMenu.Menu(MENU_CAPTION[2], MENU_SOCIALITY);
      this.menuMap = new UIRightMenu.Menu(MENU_CAPTION[3], MENU_MAP);
      this.menuOption = new UIRightMenu.Menu(MENU_CAPTION[4], MENU_OPTION);
      this.menuService = new UIRightMenu.Menu(MENU_CAPTION[5], !MainCanvas.isChinaMobileVer ? MENU_SERVICE : MENU_SERVICE_1);
      this.menuPlayer = new UIRightMenu.Menu(MENU_CAPTION[6], MENU_PLAYER);
      this.menuExit = new UIRightMenu.Menu(MENU_CAPTION[7], MENU_EXIT);
      this.menuProduce = new UIRightMenu.Menu(MENU_CAPTION[10], MENU_PRODUCE);
      this.menuCollect = new UIRightMenu.Menu(MENU_CAPTION[11], MENU_COLLECT);
      this.menuTask = new UIRightMenu.Menu(MENU_CAPTION[12], MENU_TASK);
      this.menuKefu = new UIRightMenu.Menu(MENU_CAPTION[14], MENU_SERVICE_2);
      this.menuGem = new UIRightMenu.Menu(MENU_CAPTION[16], MENU_GEM);
      this.menuHang = new UIRightMenu.Menu(MENU_CAPTION[17], MENU_HANG);
      this.menuChat = new UIRightMenu.Menu("Kênh Chat", new String[]{"Tổng hợp", "Chát riêng", "Thế giới", "Thị tộc", "Bối cảnh", "Đội ngũ", "Hệ thống", "Loa"});
      this.menuMain.addSonMenu((byte)3, this.menuGenius);
      this.menuMain.addSonMenu((byte)4, this.menuSociality);
      this.menuMain.addSonMenu((byte)6, this.menuMap);
      this.menuMain.addSonMenu((byte)9, this.menuOption);
      this.menuMain.addSonMenu((byte)10, this.menuService);
      this.menuMain.addSonMenu((byte)1, this.menuPlayer);
      this.menuMain.addSonMenu((byte)11, this.menuExit);
      this.menuMain.addSonMenu((byte)8, this.menuTask);
      this.menuSociality.addSonMenu((byte)7, this.menuChat);
      this.menuGenius.addSonMenu((byte)4, this.menuProduce);
      this.menuGenius.addSonMenu((byte)5, this.menuCollect);
      this.menuGenius.addSonMenu((byte)6, this.menuGem);
      this.menuService.addSonMenu((byte)(!MainCanvas.isChinaMobileVer ? 2 : 1), this.menuKefu);
      this.menuPlayer.addSonMenu((byte)9, this.menuHang);
      curMenu = this.menuMain;
      curMenu.setCurPositionIndex((byte)5);
   }

   public static void setRightMenuNum(int num) {
      setRightMenu();
      String s = new String(String.valueOf(num));

      for(int i = s.length() - 1; i >= 0; --i) {
         int p = 1;

         int pos;
         for(pos = 0; pos < i; ++pos) {
            p *= 16;
         }

         pos = num / p;
         num %= p;
         curMenu.setCurPositionIndex((byte)pos);
         curMenu.dealPressNum();
         curMenu.setHaveFather(false);
      }

   }

   public static void setRightMenu() {
      MainCanvas.rightMenu = new UIRightMenu();
      MainCanvas.setGameState((byte)1);
   }

   public static UIRightMenu.Menu getCurMenu() {
      return curMenu;
   }

   public static void setCurMenu(UIRightMenu.Menu curMenu) {
      UIRightMenu.curMenu = curMenu;
   }

   public int dealNum() {
      int x = 0;
      int len = this.savePositionIDStack.size();
      int i = 0;

      for(int ii = len; i < ii; ++i) {
         x = x * 16 + (Byte)this.savePositionIDStack.elementAt(i);
      }

      return x;
   }

   public static final void requestTeamUI() {
      switch(SITeam.teamState) {
      case 0:
         UITopForm.createLocalTopForm((byte)0, (String)"Các hạ không có trong đội ngũ", Cons.C_STR[2], "", -1, -2);
         return;
      case 1:
         MainCanvas.ni.send(720904);
         break;
      case 2:
         MainCanvas.ni.sendCommands(new int[]{720905, 720906, 720907});
      }

   }

   public static final void intoSkillBarUI(int command) {
      MainCanvas.curForm = ParseUI.parseUI("/ui/skillbar.ui");
      MainCanvas.curForm.clientCommand = command;
      if (command == -1610612732) {
         UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
         menuBar.setMenuText("Chọn", 0);
      }

      UIGrid skillgrid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(3);
      skillgrid.setGridType((byte)1);
      skillgrid.setCanUseNum((short)(Player.getInstance().getCanUseSkillCount() + 1));
      int[] var10000 = skillgrid.stuffId;
      Player.getInstance();
      var10000[0] = Player.skillID[0];
      String[] var5 = skillgrid.stuffName;
      Player.getInstance();
      var5[0] = Player.skillName[0];
      skillgrid.stuffImgQuality[0] = 1000;
      short[] var6 = skillgrid.stuffImageId;
      Player.getInstance();
      var6[0] = (short)(Player.skillImgInd[0] % 1000);
      skillgrid.stuffQuality[0] = 0;
      skillgrid.stuffColorLevel[0] = 1;

      for(byte k = 1; k <= Player.getInstance().getCanUseSkillCount(); ++k) {
         if (Player.getInstance().canUseSkillID[k - 1] != 0) {
            short sind = Player.getInstance().getIND_FromSkillData(Player.getInstance().canUseSkillID[k - 1]);
            if (sind != -1) {
               var10000 = skillgrid.stuffId;
               Player.getInstance();
               var10000[k] = Player.skillID[sind];
               var5 = skillgrid.stuffName;
               Player.getInstance();
               var5[k] = Player.skillName[sind];
               var6 = skillgrid.stuffImgQuality;
               Player.getInstance();
               var6[k] = Player.skillImgInd[sind];
               var6 = skillgrid.stuffImageId;
               Player.getInstance();
               var6[k] = (short)(Player.skillImgInd[sind] % 1000);
               byte[] var7 = skillgrid.stuffQuality;
               Player.getInstance();
               var7[k] = (byte)(Player.skillImgInd[sind] / 1000 - 1);
               skillgrid.stuffColorLevel[k] = 1;
            }
         }
      }

      skillgrid.setTitle(skillgrid.stuffName[skillgrid.getGridIndex()], UIGrid.getStuffWordColor(skillgrid.stuffColorLevel[skillgrid.getGridIndex()]));
      skillgrid.getGridScroll().allRowNum = 8;
      skillgrid.getGridScroll().canShowRowNum = 4;
      skillgrid.getGridScroll().setShowStartRow((short)0);
      skillgrid.setSkill_area_Content();
      MainCanvas.curForm.addWarningStr("#Phím xem miêu tả kỹ năng");
   }

   public void touchScreenAction() {
      if (MainCanvas.SUPPORT_POINTER && MainCanvas.isInputDown(268435456)) {
         int y = super.positionY + (MainCanvas.CHARH + 14) * MainCanvas.screenH / 208;
         int w = 166 * MainCanvas.screenW / 176;
         int h = 81 * MainCanvas.screenH / 208;
         int touchW = w - 21;
         int touchH = h - 13;
         int touchX = MainCanvas.screenW - touchW >> 1;
         int touchY = y + (h - touchH >> 1);
         int x1 = 0;
         int y1 = 0;
         int w1 = MainCanvas.screenW;
         int h1 = MainCanvas.screenH;
         if (MainCanvas.isPointInRect(touchX, touchY, touchW, touchH)) {
            int pX = MainCanvas.pointerX;
            int pY = MainCanvas.pointerY;
            int ind = (pX - touchX) / this.wordW + (pY - touchY) / this.wordH * 3 + 1;
            curMenu.setCurPositionIndex((byte)ind);
            curMenu.dealPressNum();
         } else if (MainCanvas.isPointInRect(x1, y1, w1, h1)) {
            MainCanvas.keySimPressed(262144);
         }
      }

   }

   private static final void Serve(final byte ID) {
      final Form form = new Form(ID == 1 ? "Forum PT" : "Trung tâm Dịch vụ khách hàng");
      String str = ID == 1 ? "Nhấn “Xác nhận” sẽ thoát khỏi game，chuyển sang forum PT，nhấn “Trở về” trở lại game.\nTự động kết nối có vấn đề，kiến nghị kết nối đến http://vibox.vn/pt.\n Có muốn tự động kết nối không?" : "http://vibox.vn/pt";
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      ChoiceGroup choicegroup = new ChoiceGroup("Loại câu hỏi", 1, Cons.SERVICE_STR, (Image[])null);
      TextField tf = new TextField("Xin nhập nội dung", "", 100, 0);
      TextField tf_edition_number = new TextField("Xin nhập loại máy", "", 20, 0);
      if (ID == 2) {
         form.append(tf_edition_number);
         form.append(choicegroup);
         form.append(tf);
      }

      form.append(str);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            if (c == okCmd) {
               if (ID == 1) {
                  MainCanvas.ni.send(65543);

                  try {
                     MainCanvas.aMidlet.platformRequest("http://vibox.vn/pt");
                  } catch (Exception var4) {
                  }

                  MainCanvas.aMidlet.exitMIDlet();
               }
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

   private static void goKong() {
      final Form form = new Form("Truy cập trang chủ");
      String str = "Nhấn “Xác nhận” sẽ đóng trò chơi，chuyển đến trang chủ，nhấn vào “Trở về” trở lại game. Tự động kết nối có vấn đề，kiến nghị kết nối đến http://fs.kong.net.\n          Có muốn tự động kết nối không?";
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.append("Nhấn “Xác nhận” sẽ đóng trò chơi，chuyển đến trang chủ，nhấn vào “Trở về” trở lại game. Tự động kết nối có vấn đề，kiến nghị kết nối đến http://fs.kong.net.\n          Có muốn tự động kết nối không?");
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            if (c == okCmd) {
               MainCanvas.ni.send(65543);

               try {
                  MainCanvas.aMidlet.platformRequest("http://fs.kong.net");
               } catch (Exception var4) {
               }

               MainCanvas.aMidlet.exitMIDlet();
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

   public static void setKey() {
      MainCanvas.setUI("/ui/keysetup.ui");
      MainCanvas.curForm.clientCommand = -1610612735;
      UIList list = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
      list.setType((byte)2);
      Vector items = list.getItems();
      UIList.keyValue = new int[items.size()];

      for(int i = 0; i < items.size(); ++i) {
         ListItem item = (ListItem)items.elementAt(i);
         item.setItemType((byte)4);
         UIList.keyValue[i] = MainCanvas.curGameKey[i];
         item.setKeyStr(item.getKeyStr(UIList.keyValue[i]));
      }

      UIList.addUIScroll(list, (byte)4);
      if (!MainCanvas.SUPPORT_POINTER) {
         MainCanvas.curForm.addWarningStr("*Phím xóa，#Phím thiết lập");
      }

   }

   public static final void HangSkill(int command) {
      MainCanvas.curForm = ParseUI.parseUI("/ui/skillbar.ui");
      MainCanvas.curForm.clientCommand = command;
      UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
      menuBar.setMenuText("Chọn lựa", 0);
      UIGrid skillgrid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(3);
      skillgrid.setGridType((byte)1);
      skillgrid.setCanUseNum(PCHang.m_nSkillNum);

      for(int i = 0; i < PCHang.m_nSkillNum; ++i) {
         skillgrid.stuffId[i] = PCHang.H_stuffId[i];
         skillgrid.stuffName[i] = PCHang.H_stuffName[i];
         skillgrid.stuffImgQuality[i] = PCHang.H_stuffImgQuality[i];
         skillgrid.stuffImageId[i] = PCHang.H_stuffImageId[i];
         skillgrid.stuffQuality[i] = PCHang.H_stuffQuality[i];
         skillgrid.stuffColorLevel[i] = 1;
      }

      skillgrid.setTitle(skillgrid.stuffName[skillgrid.getGridIndex()], UIGrid.getStuffWordColor(skillgrid.stuffColorLevel[skillgrid.getGridIndex()]));
      skillgrid.getGridScroll().allRowNum = 8;
      skillgrid.getGridScroll().canShowRowNum = 4;
      skillgrid.getGridScroll().setShowStartRow((short)0);
      skillgrid.setSkill_area_Content();
      MainCanvas.curForm.addWarningStr("#Phím xem miêu tả kỹ năng");
   }

   public static void goUrl(final String url) {
      final Form form = new Form("Truy cập liên kết");
      String str = "Nhấn “Xác nhận” sẽ đóng trò chơi，chuyển đến liên kết,nhấn vào “Trở về” trở lại game. Có muốn kết nối không?";
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.append("Nhấn “Xác nhận” sẽ đóng trò chơi，chuyển đến liên kết,nhấn vào “Trở về” trở lại game. Có muốn kết nối không?");
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            if (c == okCmd) {
               MainCanvas.ni.send(65543);

               try {
                  MainCanvas.aMidlet.platformRequest(url);
               } catch (Exception var4) {
               }

               MainCanvas.aMidlet.exitMIDlet();
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

   public class Menu {
      UIRightMenu.MenuItem[] menuItems = null;
      String[] menuItemsStr = null;
      String menuCaptionStr = null;
      public int fatherPositionID = 0;
      private Hashtable menuHashTable = new Hashtable();
      private UIRightMenu.Menu fatherMenu = null;
      private boolean isHaveFather = false;
      private byte curPositionIndex = 1;
      public byte oldPositionIndex = 1;
      private UIRightMenu.MenuItem curMenuItem = null;
      private boolean isOutBounds = false;

      public Menu(String caption, String[] str) {
         this.menuCaptionStr = caption;
         this.menuItemsStr = str;
         this.menuItems = new UIRightMenu.MenuItem[str.length];
         int i = 0;

         for(int ii = this.menuItems.length; i < ii; ++i) {
            this.menuItems[i] = UIRightMenu.this.new MenuItem(str[i]);
         }

         this.initMenu();
      }

      private void initMenu() {
         this.setCurPositionIndex((byte)1);
         UIRightMenu.curMenu = this;
      }

      public void addSonMenu(byte positionIndex, UIRightMenu.Menu menu) {
         this.menuItems[positionIndex - 1].setHaveSon(true);
         menu.setHaveFather(true);
         menu.setFatherMenu(this);
         this.menuHashTable.put(new Byte(positionIndex), menu);
      }

      public byte getCurPositionIndex() {
         return this.curPositionIndex;
      }

      public void setCurPositionIndex(byte curPositionIndex) {
         this.oldPositionIndex = this.curPositionIndex;
         if (this.isInMenuItemsBounds(curPositionIndex - 1)) {
            this.curPositionIndex = curPositionIndex;
            this.isOutBounds = false;
         } else {
            this.isOutBounds = true;
         }

         if (this.menuItems != null) {
            int i = 0;

            for(int ii = this.menuItems.length; i < ii; ++i) {
               if (this.curPositionIndex - 1 == i) {
                  this.menuItems[i].setFocus(true);
               } else {
                  this.menuItems[i].setFocus(false);
               }
            }
         }

      }

      public UIRightMenu.MenuItem getCurMenuItem() {
         this.curMenuItem = this.menuItems[this.curPositionIndex];
         return this.curMenuItem;
      }

      public void setCurMenuItem(UIRightMenu.MenuItem curMenuItem) {
         this.curMenuItem = curMenuItem;
      }

      public void operation() {
         if (UIRightMenu.curMenu != null) {
            if (MainCanvas.getGameState() == 1) {
               UIRightMenu.curMenu.keyInMenu();
            }

         }
      }

      private boolean isInMenuItemsBounds(int n) {
         return n < this.menuItems.length;
      }

      private void keyInMenu() {
         UIRightMenu.this.touchScreenAction();
         if (MainCanvas.isInputDownOrHold(4096)) {
            switch(this.curPositionIndex) {
            case 1:
            case 2:
            case 3:
               break;
            default:
               this.setCurPositionIndex((byte)(this.curPositionIndex - 3));
               if (!this.menuItems[this.curPositionIndex - 1].isCanUse()) {
                  this.setCurPositionIndex(this.oldPositionIndex);
               }
            }
         } else if (MainCanvas.isInputDownOrHold(8192)) {
            switch(this.getCurPositionIndex()) {
            case 10:
            case 11:
            case 12:
               break;
            default:
               this.setCurPositionIndex((byte)(this.curPositionIndex + 3));
               if (!this.menuItems[this.curPositionIndex - 1].isCanUse()) {
                  this.setCurPositionIndex(this.oldPositionIndex);
               }
            }
         } else if (MainCanvas.isInputDownOrHold(16384)) {
            switch(this.getCurPositionIndex()) {
            case 1:
               break;
            default:
               this.setCurPositionIndex((byte)(this.curPositionIndex - 1));
               if (!this.menuItems[this.curPositionIndex - 1].isCanUse()) {
                  this.setCurPositionIndex(this.oldPositionIndex);
               }
            }
         } else if (MainCanvas.isInputDownOrHold(32768)) {
            switch(this.getCurPositionIndex()) {
            case 12:
               break;
            default:
               this.setCurPositionIndex((byte)(this.curPositionIndex + 1));
               if (!this.menuItems[this.curPositionIndex - 1].isCanUse()) {
                  this.setCurPositionIndex(this.oldPositionIndex);
               }
            }
         } else if (MainCanvas.isInputDown(2)) {
            this.setCurPositionIndex((byte)1);
            this.dealPressNum();
         } else if (MainCanvas.isInputDown(4)) {
            this.setCurPositionIndex((byte)2);
            this.dealPressNum();
         } else if (MainCanvas.isInputDown(8)) {
            this.setCurPositionIndex((byte)3);
            this.dealPressNum();
         } else if (MainCanvas.isInputDown(16)) {
            this.setCurPositionIndex((byte)4);
            this.dealPressNum();
         } else if (MainCanvas.isInputDown(32)) {
            this.setCurPositionIndex((byte)5);
            this.dealPressNum();
         } else if (MainCanvas.isInputDown(64)) {
            this.setCurPositionIndex((byte)6);
            this.dealPressNum();
         } else if (MainCanvas.isInputDown(128)) {
            this.setCurPositionIndex((byte)7);
            this.dealPressNum();
         } else if (MainCanvas.isInputDown(256)) {
            this.setCurPositionIndex((byte)8);
            this.dealPressNum();
         } else if (MainCanvas.isInputDown(512)) {
            this.setCurPositionIndex((byte)9);
            this.dealPressNum();
         } else if (MainCanvas.isInputDown(1)) {
            this.setCurPositionIndex((byte)11);
            this.dealPressNum();
         } else if (MainCanvas.isInputDown(1024)) {
            this.setCurPositionIndex((byte)10);
            this.dealPressNum();
         } else if (MainCanvas.isInputDown(2048)) {
            this.setCurPositionIndex((byte)12);
            this.dealPressNum();
         } else if (!MainCanvas.isInputDown(131072) && !MainCanvas.isInputDown(65536)) {
            if (MainCanvas.isInputDown(262144)) {
               if (UIRightMenu.curMenu.isHaveFather()) {
                  UIRightMenu.curMenu = this.getFatherMenu();
                  UIRightMenu.this.savePositionIDStack.pop();
               } else {
                  UIRightMenu.this.setFocus(false, (UIForm)null);
                  MainCanvas.mc.leftRightMenu();
               }
            }
         } else {
            this.keyAction();
         }

      }

      public void dealPressNum() {
         if (!this.isOutBounds) {
            this.keyAction();
         }
      }

      private void keyAction() {
         this.dealStack(false);
         if (this.menuItems[this.curPositionIndex - 1].isHaveSon()) {
            UIRightMenu.curMenu = (UIRightMenu.Menu)this.menuHashTable.get(new Byte(this.curPositionIndex));
            if (UIRightMenu.curMenu.isInMenuItemsBounds(4)) {
               UIRightMenu.curMenu.setCurPositionIndex((byte)5);
            } else if (UIRightMenu.curMenu.isInMenuItemsBounds(1)) {
               UIRightMenu.curMenu.setCurPositionIndex((byte)2);
            } else {
               UIRightMenu.curMenu.setCurPositionIndex((byte)1);
            }

            this.dealStack(true);
         } else {
            this.sendRequest();
         }

      }

      private void dealStack(boolean add) {
         if (!add) {
            if (!UIRightMenu.this.savePositionIDStack.empty()) {
               UIRightMenu.this.savePositionIDStack.pop();
            }

            UIRightMenu.this.savePositionIDStack.push(new Byte(UIRightMenu.curMenu.curPositionIndex));
         } else {
            UIRightMenu.this.savePositionIDStack.push(new Byte(UIRightMenu.curMenu.curPositionIndex));
         }

      }

      private void rightMenuOperation() {
         boolean isLock = false;
         UIRadio radiox;
         int i;
         switch(UIRightMenu.this.dealNum()) {
         case 2:
            MainCanvas.ni.send(458760);
            break;
         case 5:
            if (MainCanvas.isChinaMobileVer) {
               MainCanvas.ni.send(2425008);
            } else if (MainCanvas.m_bTourist == 0) {
               MainCanvas.ni.send(2425024);
            } else if (MainCanvas.m_bTourist == 1) {
               UITopForm.TouristFormal();
            } else if (MainCanvas.m_bTourist == 2) {
               UITopForm.createLocalTopForm((byte)0, (String)"Các hạ đồng ý muốn đổi tên không?", "Đồng ý", "Hủy", 65549, -1);
            }
            break;
         case 7:
            UIRightMenu.requestTeamUI();
            break;
         case 12:
            MainCanvas.ni.send(2355697);
            break;
         case 17:
            MainCanvas.ni.send(393219);
            break;
         case 18:
            MainCanvas.ni.send(393220);
            break;
         case 19:
            PCGem.m_bAppendMenu = 0;
            MainCanvas.ni.send(917505);
            break;
         case 20:
            MainCanvas.ni.send(393223);
            break;
         case 21:
            MainCanvas.ni.send(393224);
            break;
         case 22:
            MainCanvas.ni.send(393225);
            break;
         case 23:
            MainCanvas.ni.send(393234);
            break;
         case 24:
            MainCanvas.ni.send(1507331);
            break;
         case 49:
            MainCanvas.ni.send(1572868);
            break;
         case 50:
            UIRightMenu.intoSkillBarUI(-1610612733);
            MainCanvas.setGameState((byte)4);
            break;
         case 51:
            this.shortCutUI();
         case 52:
         case 53:
         case 69:
         case 70:
         case 71:
         case 839:
         case 840:
         case 841:
         case 842:
         default:
            break;
         case 55:
            MainCanvas.ni.send(3342356);
            break;
         case 56:
            MainCanvas.ni.send(917525);
            break;
         case 65:
            MainCanvas.ni.send(589832);
            break;
         case 66:
            PCSocial.addform("Thêm bằng hữu", "Xin nhập tên của các hạ tốt：", 589833);
            break;
         case 67:
            MainCanvas.ni.send(589825);
            break;
         case 68:
            MainCanvas.ni.send(589828);
            break;
         case 72:
            MainCanvas.ni.send(589845);
            break;
         case 73:
            MainCanvas.ni.send(1441805);
            break;
         case 75:
            UIRightMenu.Serve((byte)1);
            break;
         case 76:
            MainCanvas.ni.send(3407878);
            break;
         case 97:
            Map.isInterLocalMap = false;
            Map.isOutMenu = 1;
            MainCanvas.ni.send(131079);
            break;
         case 98:
            MainCanvas.ni.send(851976);
            break;
         case 99:
            Map.isOutMenu = 2;
            MainCanvas.ni.send(131078);
            break;
         case 100:
            MainCanvas.ni.send(2555905);
            break;
         case 101:
            MainCanvas.ni.send(852020);
            break;
         case 129:
            MainCanvas.ni.send(262168);
            break;
         case 130:
            MainCanvas.ni.send(262187);
            break;
         case 131:
            MainCanvas.ni.send(262165);
            break;
         case 132:
            MainCanvas.ni.send(262185);
            break;
         case 145:
            MainCanvas.setGameState((byte)4);
            UIRightMenu.setKey();
            break;
         case 146:
            this.shortCutUI();
            break;
         case 147:
            MainCanvas.setUI("/ui/showsetup.ui");
            MainCanvas.curForm.clientCommand = -1610612728;
            i = MainCanvas.curForm.getComponents().size();
            MainCanvas.curForm.getComponents().removeElementAt(i - 1);

            for(int ix = 0; ix < 5; ++ix) {
               UIRadio radioxx = (UIRadio)MainCanvas.curForm.getComponents().elementAt(ix + 3);
               if (ix == 0) {
                  radioxx.setSelectIndex(MainCanvas.picQuaInd);
               } else {
                  radioxx.setSelectIndex((byte)((MainCanvas.showSetup >> ix - 1) % 2));
               }
            }

            MainCanvas.setGameState((byte)4);
            break;
         case 148:
            MainCanvas.setUI("/ui/chatsetup.ui");
            MainCanvas.curForm.clientCommand = -1610612699;

            for(i = 0; i < 5; ++i) {
               radiox = (UIRadio)MainCanvas.curForm.getComponents().elementAt(i + 4);
               radiox.setSelectIndex((byte)((SIChat.channelOpen >> i) % 2));
            }

            MainCanvas.setGameState((byte)4);
            break;
         case 149:
            MainCanvas.setUI("/ui/elsesetup.ui");
            MainCanvas.curForm.clientCommand = -1610612727;
            UIRadio radio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(3);
            if (Player.getInstance().getLevel() >= 20) {
               MainCanvas.pvpSetup = 0;
            }

            radio.setSelectIndex(MainCanvas.pvpSetup);
            radiox = (UIRadio)MainCanvas.curForm.getComponents().elementAt(9);
            radiox.setSelectIndex((byte)(MainCanvas.quantitySetup ^ 1));
            MainCanvas.setGameState((byte)4);
            break;
         case 161:
            if (!MainCanvas.isChinaMobileVer) {
               UIRightMenu.Serve((byte)1);
            }
            break;
         case 162:
            if (MainCanvas.isChinaMobileVer) {
               MainCanvas.ni.send(458772);
            }
            break;
         case 163:
            if (MainCanvas.isChinaMobileVer) {
               isLock = true;
            } else {
               MainCanvas.ni.send(458772);
            }
            break;
         case 164:
            MainCanvas.ni.send(1703958);
            break;
         case 177:
            if (MainCanvas.m_bTourist != 0 && MainCanvas.m_bTourist != 2) {
               UITopForm.createLocalTopForm((byte)0, (String)"Chức năng này không mở cho Du Khách", Cons.C_STR[2], "", -1, -2);
            } else {
               MainCanvas.ni.send(65544);
            }
            break;
         case 178:
            if (MainCanvas.m_bTourist != 0 && MainCanvas.m_bTourist != 2) {
               MainCanvas.ni.send(65550);
            } else {
               MainCanvas.ni.send(65545);
            }
            break;
         case 179:
            MainCanvas.ni.send(2355697);
            break;
         case 180:
            UIRightMenu.goUrl("http://vibox.vn/pt");
            break;
         case 401:
            MainCanvas.ni.send(3145732);
            break;
         case 402:
            MainCanvas.ni.send(3145729);
            break;
         case 403:
            MainCanvas.ni.send(3145730);
            break;
         case 404:
            MainCanvas.ni.send(3145741);
            break;
         case 833:
            MainCanvas.ni.sendCommands(new int[]{2162689, 2162691});
            break;
         case 834:
            MainCanvas.ni.sendCommands(new int[]{2162702, 2162703});
            break;
         case 835:
            MainCanvas.ni.sendCommands(new int[]{2162713, 2162714});
            break;
         case 836:
            MainCanvas.ni.sendCommands(new int[]{2162724, 2162725});
            break;
         case 837:
            MainCanvas.ni.sendCommands(new int[]{2162735, 2162736});
            break;
         case 838:
            MainCanvas.ni.sendCommands(new int[]{2162744, 2162745});
            break;
         case 849:
            MainCanvas.ni.sendCommands(new int[]{2162756, 2162757});
            break;
         case 850:
            UITopForm.createLocalTopForm((byte)0, (String)"Công năng này tạm thời chưa có!", "Xác nhận", "", -1, -2);
            if (MainCanvas.ni.isSendingCommands) {
               MainCanvas.ni.isSendingCommands = false;
            }
            break;
         case 851:
            MainCanvas.ni.sendCommands(new int[]{2162770, 2162771});
            break;
         case 852:
            MainCanvas.ni.sendCommands(new int[]{2162777, 2162778});
            break;
         case 865:
            UIGrid.fromEquipUnseal = false;
            MainCanvas.ni.send(2031636);
            break;
         case 866:
            MainCanvas.ni.send(2031621);
            break;
         case 867:
            MainCanvas.ni.send(2228225);
            break;
         case 868:
            MainCanvas.ni.send(1966097);
            break;
         case 869:
            MainCanvas.ni.send(2228256);
            break;
         case 1105:
            MainCanvas.ni.send(1638401);
            break;
         case 1106:
            MainCanvas.ni.send(1638426);
            break;
         case 1107:
            MainCanvas.ni.send(1638403);
            break;
         case 1108:
            MainCanvas.ni.send(1638419);
            break;
         case 1109:
            MainCanvas.ni.send(1638425);
            break;
         case 1110:
            MainCanvas.ni.send(1638427);
            break;
         case 1111:
            MainCanvas.ni.send(1638417);
            break;
         case 1112:
            MainCanvas.ni.send(1638404);
            break;
         case 1121:
            MainCanvas.ni.send(1835011);
            break;
         case 1122:
            MainCanvas.ni.send(1835012);
            break;
         case 1123:
            MainCanvas.ni.send(1835021);
            break;
         case 1124:
            MainCanvas.ni.send(1835027);
            break;
         case 1137:
            byte showChannel = false;
            if (UIRightMenu.this.dealNum() == 1) {
               showChannel = true;
            } else {
               byte var6 = (byte)(UIRightMenu.this.dealNum() - 2);
            }

            UIChat.chatForm = new UIChat((byte)100);
            MainCanvas.setGameState((byte)3);
            break;
         case 1138:
            UIChat.chatForm = new UIChat((byte)0);
            MainCanvas.setGameState((byte)3);
            break;
         case 1139:
            UIChat.chatForm = new UIChat((byte)1);
            MainCanvas.setGameState((byte)3);
            break;
         case 1140:
            UIChat.chatForm = new UIChat((byte)2);
            MainCanvas.setGameState((byte)3);
            break;
         case 1141:
            UIChat.chatForm = new UIChat((byte)3);
            MainCanvas.setGameState((byte)3);
            break;
         case 1142:
            UIChat.chatForm = new UIChat((byte)4);
            MainCanvas.setGameState((byte)3);
            break;
         case 1143:
            UIChat.chatForm = new UIChat((byte)5);
            MainCanvas.setGameState((byte)3);
            break;
         case 1144:
            UIChat.chatForm = new UIChat((byte)6);
            MainCanvas.setGameState((byte)3);
            break;
         case 1185:
            MainCanvas.ni.send(2490386);
            break;
         case 1186:
            MainCanvas.ni.send(2490385);
            break;
         case 2577:
            if (MainCanvas.isChinaMobileVer) {
               MainCanvas.ni.send(1703968);
            }
            break;
         case 2578:
            if (MainCanvas.isChinaMobileVer) {
               MainCanvas.ni.send(1703969);
            }
            break;
         case 2593:
            if (!MainCanvas.isChinaMobileVer) {
               MainCanvas.ni.send(1703968);
            }
            break;
         case 2594:
            if (!MainCanvas.isChinaMobileVer) {
               MainCanvas.ni.send(1703969);
            }
         }

         if (isLock) {
            UITopForm.createLockTopForm();
         }

      }

      private void shortCutUI() {
         MainCanvas.curForm = ParseUI.parseUI("/ui/shortcutkey.ui");
         MainCanvas.curFormVector.addElement(MainCanvas.curForm);
         MainCanvas.curFormVector.addElement((Object)null);
         MainCanvas.curFormVector.addElement((Object)null);
         MainCanvas.curForm.clientCommand = -1610612734;
         SIManager.getInstance();
         SIShortCut var10000 = SIManager.shortCut;
         SIManager.getInstance();
         var10000.initUIFormGridState(SIManager.shortCut.curShortCutGroupNum);
         UIRadio radio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(6);
         SIManager.getInstance();
         radio.setSelectIndex((byte)(SIManager.shortCut.curShortCutGroupNum - 1));
         if (!MainCanvas.SUPPORT_POINTER) {
            MainCanvas.curForm.addWarningStr("*Phím thiết lập kỹ năng，#Phím thiết lập vật phẩm");
         }

         MainCanvas.setGameState((byte)4);
      }

      private void sendRequest() {
         this.rightMenuOperation();
      }

      public UIRightMenu.MenuItem getMenuItem(byte positionIndex) {
         return this.menuItems[positionIndex - 1];
      }

      public void draw(Graphics g) {
         if (UIRightMenu.curMenu != null) {
            UIRightMenu.curMenu.drawCaption(g);
            UIRightMenu.curMenu.drawWord(g);
            UIRightMenu.curMenu.drawCursorEffect(g);
         }
      }

      public void drawWord(Graphics g) {
         for(int i = 0; i < this.menuItemsStr.length; ++i) {
            int dx = i / 3;
            int dy = i % 3;
            if (!this.menuItemsStr[i].equals("Sửa tên") && !this.menuItemsStr[i].equals("Đăng  ký") && !this.menuItemsStr[i].equals("Loa")) {
               g.setColor(Cons.COLOR_RIGHT_MENU_FONT);
            } else {
               g.setColor(16711680);
            }

            UIRightMenu.this.drawCenterStr(g, this.menuItemsStr[i], UIRightMenu.this.wordX + dy * UIRightMenu.this.wordW, UIRightMenu.this.wordY + dx * UIRightMenu.this.wordH, UIRightMenu.this.wordW, UIRightMenu.this.wordH, MainCanvas.curFont);
         }

      }

      public void drawCaption(Graphics g) {
         int w = 76 * MainCanvas.screenW / 176;
         int h = 25 * MainCanvas.screenH / 208;
         int x = MainCanvas.screenW - w >> 1;
         g.setColor(Cons.COLOR_RIGHT_MENU_FONT);
         UIRightMenu.this.drawCenterStr(g, this.menuCaptionStr, x, UIRightMenu.super.positionY, w, h, MainCanvas.curFont);
      }

      public void drawCursorEffect(Graphics g) {
         g.setColor(Cons.COLOR_RIGHT_MENU_1);
         int dx = (this.curPositionIndex - 1) / 3;
         int dy = (this.curPositionIndex - 1) % 3;
         g.fillRect(UIRightMenu.this.wordX + dy * UIRightMenu.this.wordW + 2, UIRightMenu.this.wordY + dx * UIRightMenu.this.wordH + 2, UIRightMenu.this.wordW - (dy == 2 ? 2 : 3), UIRightMenu.this.wordH - 3);
         if (!this.menuItemsStr[this.curPositionIndex - 1].equals("Sửa tên") && !this.menuItemsStr[this.curPositionIndex - 1].equals("Đăng  ký")) {
            g.setColor(Cons.COLOR_RIGHT_MENU_3);
         } else {
            g.setColor(16711680);
         }

         UIRightMenu.this.drawCenterStr(g, this.menuItemsStr[this.curPositionIndex - 1], UIRightMenu.this.wordX + dy * UIRightMenu.this.wordW, UIRightMenu.this.wordY + dx * UIRightMenu.this.wordH, UIRightMenu.this.wordW, UIRightMenu.this.wordH, MainCanvas.curFont);
      }

      public boolean isHaveFather() {
         return this.isHaveFather;
      }

      public void setHaveFather(boolean isHaveFather) {
         this.isHaveFather = isHaveFather;
      }

      public UIRightMenu.Menu getFatherMenu() {
         return this.fatherMenu;
      }

      public void setFatherMenu(UIRightMenu.Menu fatherMenu) {
         this.fatherMenu = fatherMenu;
      }
   }

   public class MenuItem {
      private String name = null;
      private boolean isFocus = false;
      private boolean isCanUse = true;
      private boolean isHaveSon = false;

      public MenuItem(String str) {
         this.setName(str);
      }

      public boolean isFocus() {
         return this.isFocus;
      }

      public void setFocus(boolean isFocus) {
         this.isFocus = isFocus;
      }

      public boolean isCanUse() {
         return this.isCanUse;
      }

      public void setCanUse(boolean isCanUse) {
         this.isCanUse = isCanUse;
      }

      public boolean isHaveSon() {
         return this.isHaveSon;
      }

      public void setHaveSon(boolean isHaveSon) {
         this.isHaveSon = isHaveSon;
      }

      public String getName() {
         return this.name;
      }

      public void setName(String name) {
         this.name = name;
      }
   }
}

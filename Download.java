import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

public class Download implements CommandListener {
   private static Download me = null;
   private static byte type;
   static a owner;
   static Form f;
   static Command cmdOK = new Command("Đồng Ý", 4, 0);
   static Command cmdCancel = new Command("Thoát", 3, 1);
   static Command cmdBack = new Command("Trở Về", 2, 1);
   public static String testDownloadUrl = "";
   public static final String DEFAULT_JAR_URL = "GOH_7610.jar";

   public static Download getInstance() {
      if (me == null) {
         me = new Download();
      }

      return me;
   }

   public void commandAction(Command command, Displayable displayable) {
      if (command == cmdOK) {
         try {
            if (type == 0) {
               if (MainCanvas.mc.isTestNum == 1) {
                  owner.platformRequest(testDownloadUrl);
               } else {
                  owner.platformRequest(GameLogin.downloadAddress);
               }

               owner.destroyApp(true);
            }
         } catch (Exception var4) {
         }
      } else if (command == cmdBack) {
         try {
            if (type == 0 || type == 9) {
               owner.display.setCurrent(MainCanvas.mc);
               MainCanvas.setState((byte)4);
               f = null;
            }
         } catch (Exception var5) {
         }
      } else if (command == cmdCancel) {
         owner.destroyApp(true);
      }

      GameLogin.downloadAddress = null;
   }

   public static void gotoURL(a o, byte _type) {
      type = _type;
      String s1;
      String s2;
      if (type == 0) {
         s1 = "Tải client mới";
         s2 = "Nhấn “Đồng Ý” tải client mới, nhấn “Trở Về” để vào game.";
      } else if (type == 1) {
         s1 = "Đăng ký mã CB";
         s2 = "Nhấn “Đồng Ý” đăng ký tài khoản CB, nhấn “Trở Về” để về game.";
      } else if (type == 2) {
         s1 = "Tìm chi tiết";
         s2 = "Nhấn “Đồng Ý” chuyển đến trang tìm chi tiết, nhấn “Trở Về” để vào game.";
      } else if (type == 3) {
         s1 = "Diễn Đàn Thiên Kiếp";
         s2 = "Nhấn “Đồng Ý” chuyển đến trang diễn đàn Thiên Kiếp, nhấn “Trở Về” để vào game.";
      } else if (type == 4) {
         s1 = "Nạp Tiền Lớn";
         s2 = "Nhấn “Đồng Ý” chuyển đến trang Nạp Tiền Lớn，nhấn “Trở Về” để vào game.";
      } else if (type == 5) {
         s1 = "Nạp cash";
         s2 = "Nhấn “Đồng Ý” chuyển đến trang nạp cash，nhấn “Trở Về” để vào game.";
      } else if (type == 6) {
         s1 = "Platform";
         s2 = "Nhiều trò chơi đặc sắc có tại D.cn";
      } else if (type == 7) {
         s1 = "Diễn Đàn Thiên Kiếp";
         s2 = "Nhấn “Đồng Ý” chuyển đến trang Diễn Đàn Thiên Kiếp, nhấn “Thoát” để trở về game";
      } else if (type == 8) {
         s1 = "Điểm của mình";
         s2 = "Nhấn “Đồng Ý” chuyển đến trang Điểm Của Mình, nhấn “Trở Về” để vào game.";
      } else if (type == 9) {
         s1 = "Tìm trung tâm game";
         s2 = "Nhấn “Đồng Ý” để chuyển đến trung tâm game, nhấn “Trở Về” để vào game.";
      } else {
         s1 = "Chưa biết loại hình";
         s2 = "";
      }

      try {
         owner = o;
         f = new Form(s1);
         f.deleteAll();
         f.append(s2);
         f.addCommand(cmdOK);
         if (type != 1 && type != 6 && type != 7) {
            f.addCommand(cmdBack);
         } else {
            f.addCommand(cmdCancel);
         }

         f.setCommandListener(getInstance());
         owner.display.setCurrent(f);
      } catch (Exception var5) {
      }

   }

   public static String getJarName() {
      return "GOH_7610.jar";
   }
}

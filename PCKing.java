import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

public class PCKing {
   static String kingName = "";
   static String kingIntro = "";
   static String afficheTitle = "";
   static String afficheDetail = "";
   static int afficheId = -1;
   static byte KING_RIGHT_NONE = 0;
   static byte KING_RIGHT_MEMBER = 1;
   static byte KING_RIGHT_VICE_HERDER = 2;
   static byte KING_RIGHT_HERDER = 3;
   static byte KING_RIGHT_INTERNAL = 4;
   static byte KING_RIGHT_FOREIGN = 5;
   static byte KING_RIGHT_BUSINESS = 6;
   public static int otherPlayerID = 0;
   public static int inviteID = 0;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 1835009:
         ba.writeInt(GOManager.currentTarget.getID());
         MainCanvas.isWaiting = true;
         break;
      case 1835010:
         ba.writeUTF(kingName);
         ba.writeUTF(kingIntro);
         break;
      case 1835011:
         MainCanvas.isWaiting = true;
         break;
      case 1835012:
      case 1835027:
         MainCanvas.isWaiting = true;
         break;
      case 1835013:
         ba.writeInt(otherPlayerID);
         break;
      case 1835014:
      case 1835015:
         ba.writeInt(inviteID);
      case 1835016:
      case 1835020:
      case 1835033:
      default:
         break;
      case 1835017:
      case 1835028:
      case 1835029:
         ba.writeInt(UIList.selectedListId);
         MainCanvas.isWaiting = true;
         break;
      case 1835018:
         ba.writeInt(otherPlayerID);
         ba.writeInt(UIList.selectedListId);
         MainCanvas.isWaiting = true;
         break;
      case 1835019:
         ba.writeInt(UIList.selectedListId);
         MainCanvas.isWaiting = true;
         break;
      case 1835021:
         MainCanvas.isWaiting = true;
         break;
      case 1835022:
         ba.writeInt(UIList.selectedListId);
         MainCanvas.isWaiting = true;
         break;
      case 1835023:
         ba.writeUTF(afficheTitle);
         ba.writeUTF(afficheDetail);
         afficheTitle = "";
         afficheDetail = "";
         break;
      case 1835024:
         ba.writeInt(afficheId);
         ba.writeLong(UIList.selectTimeStamp);
         ba.writeUTF(afficheTitle);
         ba.writeUTF(afficheDetail);
         afficheTitle = "";
         afficheDetail = "";
         break;
      case 1835025:
         ba.writeInt(UIList.selectedListId);
         ba.writeLong(UIList.selectTimeStamp);
         MainCanvas.isWaiting = true;
         break;
      case 1835026:
         ba.writeInt(UIList.selectedListId);
         ba.writeLong(UIList.selectTimeStamp);
         break;
      case 1835030:
      case 1835031:
      case 1835034:
         MainCanvas.isWaiting = true;
         break;
      case 1835032:
         ba.writeInt(Player.busyId);
         break;
      case 1835035:
         ba.writeInt(otherPlayerID);
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2145648639:
         Player.getInstance().setKingId(data.readInt());
         Player.getInstance().setKingRight(data.readByte());
         break;
      case -2145648638:
         MainCanvas.quitUI();
         break;
      case -2145648637:
         afficheId = data.readInt();
         editAffiche(1835024, data.readUTF(), data.readUTF());
      }

   }

   public static void editKing() {
      final Form form = new Form("Tạo Chư Hầu");
      final TextField tf1 = new TextField("Nhập tên Chư Hầu", kingName, 6, 0);
      final TextField tf2 = new TextField("Nhập giới thiệu Chu Hầu", kingIntro, 50, 0);
      form.append(tf1);
      form.append(tf2);
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            if (c == okCmd) {
               PCKing.kingName = tf1.getString();
               PCKing.kingIntro = tf2.getString();
               UITextField textfield = (UITextField)MainCanvas.curForm.getComponents().elementAt(3);
               UITextArea textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(5);
               textfield.setLabel(PCKing.kingName);
               textarea.setContent(PCKing.kingIntro);
               textarea.addUIScroll();
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
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

   public static void editAffiche(final int cmds, String title, String text) {
      final Form form = new Form("Chi tiết thông báo");
      final TextField tf1 = new TextField("Nhập tiêu đề thông báo", title, 6, 0);
      final TextField tf2 = new TextField("Nhập nội dung Thông báo", text, 30, 0);
      form.append(tf1);
      form.append(tf2);
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            if (c == okCmd) {
               PCKing.afficheTitle = tf1.getString();
               PCKing.afficheDetail = tf2.getString();
               MainCanvas.ni.send(cmds);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
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

   public static void createKingKeyInMenu(int cmd) {
      switch(cmd) {
      case 0:
         editKing();
         break;
      case 1835010:
         if (kingName.length() > 0) {
            MainCanvas.ni.send(1835010);
            kingName = "";
            kingIntro = "";
            MainCanvas.quitUI();
         } else {
            UITopForm.createLocalTopForm((byte)0, (String)"诸侯名称不能为空！", "确定", "", -1, -2);
         }
      }

   }

   public static UIMenu createKingKeyInAction() {
      UIMenu menu = new UIMenu((byte)0);
      String[] strs = new String[]{"Chi tiết biên tập", "Xin tạo"};
      int[] cmds = new int[]{0, 1835010};
      int[] inns = new int[]{3, 3};
      menu.addMenu(strs, cmds, inns);
      return menu;
   }

   public static void secondSureTopform(int cmd) {
      String str = "";
      switch(cmd) {
      case 1835016:
         str = "Giải tán Chư Hầu không?";
         break;
      case 1835017:
         str = "Khai trừ Thị Tộc này không?";
         break;
      case 1835020:
         str = "Rời khỏi Chư Hầu không?";
         break;
      case 1835034:
         str = "Cách chức vụ trong Chư Hầu không?";
         break;
      case 1835035:
         str = "Nhường ngôi Chư Hầu cho người khác không?";
      }

      UITopForm.createLocalTopForm((byte)0, (String)str, "Đồng ý", "Không", cmd, -1);
   }
}

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

public class PCClan {
   static String clanName = "";
   static String clanIntro = "";
   static String afficheStr = "";
   static int payMedal = 0;
   static byte CLAN_RIGHT_NONE = 0;
   static byte CLAN_RIGHT_MEMBER = 1;
   static byte CLAN_RIGHT_VICE_HERDER = 2;
   static byte CLAN_RIGHT_HERDER = 3;
   static final byte CLAN_RIGHT_ELITE = 11;
   static final byte CLAN_RIGHT_MERCIL = 12;
   static final byte CLAN_RIGHT_EPICHURCH = 13;
   static final byte CLAN_RIGHT_PROTECTORS = 14;
   static final byte CLAN_RIGHT_ELDER = 15;
   public static int otherPlayerID = 0;
   public static int inviteID = 0;
   public static final byte BULLETIONRIGHT = 1;
   public static final byte STORERIGHT = 2;
   public static byte rightType = 0;
   public static String rightStr = "";
   static int[] rightId = null;
   static boolean[] right = null;
   static String clanName_gai = "";
   public static int clan_snd_id;
   public static int clan_rec_id;
   public static int battle_id;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 1638401:
         ba.writeInt(GOManager.currentTarget.getID());
         MainCanvas.isWaiting = true;
         break;
      case 1638402:
         ba.writeUTF(clanName);
         ba.writeUTF(clanIntro);
         break;
      case 1638403:
      case 1638416:
         MainCanvas.isWaiting = true;
         break;
      case 1638404:
      case 1638417:
      case 1638419:
      case 1638420:
      case 1638445:
         MainCanvas.isWaiting = true;
         break;
      case 1638405:
         ba.writeInt(otherPlayerID);
         break;
      case 1638406:
      case 1638407:
         ba.writeInt(inviteID);
      case 1638408:
      case 1638413:
      case 1638424:
      case 1638430:
      case 1638431:
      case 1638434:
      case 1638435:
      case 1638436:
      case 1638437:
      case 1638438:
      case 1638439:
      case 1638440:
      case 1638441:
      case 1638442:
      case 1638443:
      case 1638447:
      case 1638448:
      default:
         break;
      case 1638409:
      case 1638410:
      case 1638411:
      case 1638412:
      case 1638415:
         ba.writeInt(otherPlayerID);
         MainCanvas.isWaiting = true;
         break;
      case 1638414:
         ba.writeInt(Player.busyId);
         break;
      case 1638418:
         ba.writeUTF(afficheStr);
         MainCanvas.isWaiting = true;
         break;
      case 1638421:
         ba.writeByte(rightType);
         MainCanvas.isWaiting = true;
         break;
      case 1638422:
         ba.writeByte(rightType);
         int length = rightId.length;
         ba.writeByte(length);

         for(int i = 0; i < length; ++i) {
            ba.writeInt(rightId[i]);
            ba.writeBoolean(right[i]);
         }

         rightId = null;
         right = null;
         break;
      case 1638423:
      case 1638425:
      case 1638426:
      case 1638427:
      case 1638428:
         MainCanvas.isWaiting = true;
         break;
      case 1638429:
         MainCanvas.isWaiting = true;
         ba.writeInt(payMedal);
         break;
      case 1638432:
      case 1638433:
         ba.writeByte(UIPicture.equipTypeForServer);
         break;
      case 1638444:
         MainCanvas.isWaiting = true;
         ba.writeInt(UITable.selectedPlayerId);
         break;
      case 1638446:
         MainCanvas.isWaiting = true;
         ba.writeUTF(clanName_gai);
         break;
      case 1638449:
         ba.writeInt(clan_rec_id);
         break;
      case 1638450:
      case 1638451:
         ba.writeInt(clan_snd_id);
         ba.writeInt(clan_rec_id);
         ba.writeInt(battle_id);
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2145845247:
         Player.getInstance().setClanId(data.readInt());
         Player.getInstance().setClanRight(data.readByte());
         break;
      case -2145845246:
         MainCanvas.quitUI();
      }

   }

   public static void editClan() {
      final Form form = new Form("Tạo Thị Tộc");
      final TextField tf1 = new TextField("Nhập tên Thị Tộc", clanName, 6, 0);
      final TextField tf2 = new TextField("Nhập giới thiệu Thị Tộc", clanIntro, 50, 0);
      form.append(tf1);
      form.append(tf2);
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            if (c == okCmd) {
               PCClan.clanName = tf1.getString();
               PCClan.clanIntro = tf2.getString();
               UITextField textfield = (UITextField)MainCanvas.curForm.getComponents().elementAt(3);
               UITextArea textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(5);
               textfield.setLabel(PCClan.clanName);
               textarea.setContent(PCClan.clanIntro);
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

   public static void editAffiche() {
      UITextArea textarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(3);
      afficheStr = textarea.getContent().trim();
      final Form form = new Form("Thông báo Thị Tộc");
      final TextField tf = new TextField("Nhập nội dung thông báo", afficheStr, 100, 0);
      form.append(tf);
      final Command okCmd = new Command("Cập nhật", 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            if (c == okCmd) {
               PCClan.afficheStr = tf.getString();
               MainCanvas.ni.send(1638418);
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

   public static void createClanKeyInMenu(int cmd) {
      switch(cmd) {
      case 0:
         editClan();
         break;
      case 1638402:
         if (clanName.length() > 0) {
            MainCanvas.ni.send(1638402);
            clanName = "";
            clanIntro = "";
            MainCanvas.quitUI();
         } else {
            UITopForm.createLocalTopForm((byte)0, (String)"Tên Thị Tộc không được để trống!", "Đồng ý", "", -1, -2);
         }
      }

   }

   public static void clanAfficheInMenu(int cmd) {
      switch(cmd) {
      case 1638418:
         editAffiche();
      default:
      }
   }

   public static UIMenu createClanKeyInAction() {
      UIMenu menu = new UIMenu((byte)0);
      String[] strs = new String[]{"Chi tiết biên tập", "Xin tạo"};
      int[] cmds = new int[]{0, 1638402};
      int[] inns = new int[]{3, 3};
      menu.addMenu(strs, cmds, inns);
      return menu;
   }

   public static UIMenu clanAfficheKeyInAction() {
      UIMenu menu = new UIMenu((byte)0);
      String[] strs = new String[]{"Cập nhật thông báo"};
      int[] cmds = new int[]{1638418};
      int[] inns = new int[]{3};
      menu.addMenu(strs, cmds, inns);
      return menu;
   }

   public static UIMenu clanRightKeyInAction() {
      UIMenu menu = new UIMenu((byte)0);
      String[] strs = new String[]{"Quyền hạn quản lý"};
      int[] cmds = new int[]{1638420};
      int[] inns = new int[]{2};
      menu.addMenu(strs, cmds, inns);
      return menu;
   }

   public static void secondSureTopform(int cmd) {
      String str = "";
      switch(cmd) {
      case 1638408:
         str = "Giải tán Thị Tộc";
         break;
      case 1638409:
         str = "Khi trừ người này?";
         break;
      case 1638410:
         str = "Nhường ngôi tộc trưởng cho người khác?";
      case 1638411:
      case 1638412:
      case 1638414:
      case 1638415:
      default:
         break;
      case 1638413:
         str = "Rời thị tộc";
         break;
      case 1638416:
         str = "Cách chức vụ trong Thị Tộc?";
      }

      UITopForm.createLocalTopForm((byte)0, (String)str, "Đồng ý", "Không", cmd, -1);
   }

   public static void sendRight() {
      UITable tb = (UITable)MainCanvas.curForm.getComponents().elementAt(3);
      int length = tb.getItems().size();
      rightId = new int[length];
      right = new boolean[length];

      for(int i = 0; i < length; ++i) {
         TableItem ti = (TableItem)tb.getItems().elementAt(i);
         rightId[i] = ti.playerID;
         UILabel label = (UILabel)ti.getTerms().elementAt(2);
         right[i] = label.getText() == "Có";
      }

      MainCanvas.ni.send(1638422);
   }
}

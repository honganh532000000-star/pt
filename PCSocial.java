import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;

public class PCSocial {
   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 589825:
         MainCanvas.isWaiting = true;
         break;
      case 589826:
         MainCanvas.isWaiting = true;
         ba.writeInt(UITable.selectedPlayerId);
         break;
      case 589827:
         MainCanvas.isWaiting = true;
         ba.writeInt(UITable.selectedPlayerId);
         break;
      case 589828:
         MainCanvas.isWaiting = true;
         break;
      case 589829:
         MainCanvas.isWaiting = true;
         ba.writeInt(UITable.selectedPlayerId);
         break;
      case 589830:
         MainCanvas.isWaiting = true;
         ba.writeInt(UITable.selectedPlayerId);
         break;
      case 589831:
         MainCanvas.isWaiting = true;
         ba.writeInt(UITable.selectedPlayerId);
         break;
      case 589832:
         MainCanvas.isWaiting = true;
         break;
      case 589833:
         ba.writeUTF(UIRightMenu.pName);
         break;
      case 589834:
         MainCanvas.isWaiting = true;
         break;
      case 589835:
         ba.writeInt(UITable.selectedPlayerId);
         break;
      case 589836:
         ba.writeInt(UITable.selectedPlayerId);
         break;
      case 589841:
         MainCanvas.isWaiting = true;
         ba.writeInt(UITable.selectedPlayerId);
         break;
      case 589842:
         MainCanvas.isWaiting = true;
         ba.writeInt(UITable.selectedPlayerId);
         break;
      case 589843:
         MainCanvas.isWaiting = true;
         ba.writeInt(UITable.selectedPlayerId);
         break;
      case 589844:
         MainCanvas.isWaiting = true;
         ba.writeInt(UITable.selectedPlayerId);
         break;
      case 589845:
         MainCanvas.isWaiting = true;
         break;
      case 589846:
         MainCanvas.isWaiting = true;
         ba.writeInt(UITable.selectedPlayerId);
         break;
      case 589847:
         ba.writeUTF(UIRightMenu.pName);
         break;
      case 589848:
         MainCanvas.isWaiting = true;
         ba.writeUTF(UIRightMenu.pName);
         break;
      case 624776:
         ba.writeInt(Player.getInstance().m_nPersonalEnemy);
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      byte n;
      switch(commID) {
      case -2146893823:
         byte count = data.readByte();

         for(n = 0; n < count; ++n) {
         }
      case -2146893822:
      case -2146893821:
      case -2146893819:
      case -2146893818:
      default:
         break;
      case -2146893820:
         n = data.readByte();

         for(byte n = 0; n < n; ++n) {
         }
      }

   }

   public static void addform(String title, String smalltitle, final int commID) {
      final Form form = new Form(title);
      final TextField tf = new TextField(smalltitle, "", 6, 0);
      form.append(tf);
      final Command okCmd = new Command(Cons.C_STR[2], 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 3, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            if (c == okCmd) {
               UIRightMenu.pName = tf.getString();
               if (UIRightMenu.pName != null && !UIRightMenu.pName.equals("")) {
                  for(int i = 0; i < tf.getString().length(); ++i) {
                     if (!MainCanvas.CharacterValidate(tf.getString(), (byte)2)) {
                        Alert alert = new Alert(Cons.C_STR[9], "Tên đăng nhập không đúng, hãy nhập lại", (Image)null, AlertType.ERROR);
                        tf.setString("");
                        UIRightMenu.pName = "";
                        MainCanvas.aMidlet.display.setCurrent(alert, form);
                        return;
                     }
                  }

                  MainCanvas.ni.send(commID);
                  MainCanvas.mc.setFullScreenMode(true);
                  MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
               } else {
                  Alert a = new Alert(Cons.C_STR[9], "Tên không để trống", (Image)null, AlertType.ERROR);
                  MainCanvas.aMidlet.display.setCurrent(a, form);
               }
            } else if (c == exitCmd) {
               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }
}

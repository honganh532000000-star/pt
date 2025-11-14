import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class a extends MIDlet {
   public Display display = null;
   private MainCanvas mc = null;
   public static final boolean IS_RELEASE = true;
   public static boolean IS_EMULATOR = false;
   public static String PLATFORM = null;

   public void startApp() {
      PLATFORM = System.getProperty("microedition.platform");
      if (PLATFORM.endsWith("JAVASDK") || PLATFORM.endsWith("wtk")) {
         IS_EMULATOR = true;
      }

      this.display = Display.getDisplay(this);

      try {
         if (!IS_EMULATOR) {
            if (this.mc == null) {
               this.mc = new MainCanvas(this);
            }

            this.mc.begin();
            this.display.setCurrent(this.mc);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public void pauseApp() {
      MainCanvas.clearCurInput();
   }

   protected void destroyApp(boolean unconditional) {
      this.exitMIDlet();
   }

   public void exitMIDlet() {
      this.notifyDestroyed();
   }
}

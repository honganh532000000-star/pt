import javax.microedition.lcdui.Graphics;

public class SIManager {
   static SIShortCut shortCut;
   static SIChat chat;
   static SITeam team;
   public static int TOUCH_DX;
   public static final int TOUCH_DY = 2;
   public static int SIDy = 0;
   private static SIManager instance = null;

   public SIManager() {
      if (MainCanvas.SUPPORT_POINTER) {
         SIDy = 2 + MainCanvas.touchMenu.frame_h;
         if (MainCanvas.isChinaMobileVer) {
            TOUCH_DX = MainCanvas.screenW - MainCanvas.touchMenu.img.getWidth() + MainCanvas.touchMenu.frame_w >> 1;
         } else {
            TOUCH_DX = MainCanvas.screenW - MainCanvas.touchMenu.img.getWidth() >> 1;
         }
      }

      shortCut = new SIShortCut();
      chat = new SIChat();
      team = new SITeam();
      if (MainCanvas.isLargeScreen) {
         SIChat.showMode = 0;
      } else {
         SIChat.showMode = 1;
      }

   }

   public static SIManager getInstance() {
      if (instance == null) {
         instance = new SIManager();
      }

      return instance;
   }

   public static void clear() {
      instance = null;
      if (shortCut != null) {
         SIShortCut.clear();
      }

      if (chat != null) {
         SIChat.clear();
      }

      if (team != null) {
         SITeam.clear();
      }

   }

   public final void tick() {
      chat.tick();
   }

   public final void draw(Graphics g) {
      chat.draw(g);
      shortCut.draw(g);
      team.draw(g);
      chat.drawHintInfo(g);
      if (MainCanvas.SUPPORT_POINTER) {
         MainCanvas.touchMenu.drawOneFrame(g, TOUCH_DX, 2, 0);
         if (MainCanvas.isChinaMobileVer) {
            int space = MainCanvas.touchMenu.frame_w;
            int cols = MainCanvas.touchMenu.cols;
            MainCanvas.storeImg.drawOneFrame(g, TOUCH_DX + 1 + space * (cols - 3), 3, 0);
         }
      }

      switch(Player.getInstance().state) {
      case 3:
         Player.getInstance().drawSkillTimer(g);
      default:
      }
   }
}

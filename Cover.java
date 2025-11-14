import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Cover {
   public static Image comp;
   public static Image coverPic;
   public static Image girl;
   private static Cover instance = null;
   public static int[][] star;
   public static final int[] fireColor = new int[]{16767096, 16768723, 16755301, 13256998};

   public static Cover getInstance() {
      if (instance == null) {
         instance = new Cover();
      }

      return instance;
   }

   public void initCover() {
      comp = Util.loadImage("/mammoth.png");
      coverPic = Util.loadImage("/ntm.png");
   }

   public void initLoadingGirl() {
      girl = Util.loadImage("/logo.png");
   }

   public void drawPic(Graphics g) {
      g.drawImage(coverPic, MainCanvas.screenW >> 1, MainCanvas.screenH >> 1, 3);
      g.drawImage(comp, MainCanvas.screenW >> 1, MainCanvas.screenH - comp.getHeight(), 3);
   }

   public void drawLoadingGirl(Graphics g) {
      this.initLoadingGirl();
      g.setColor(5508390);
      g.fillRect(0, 0, MainCanvas.screenW, MainCanvas.screenH);
      short pngH;
      if (!MainCanvas.isChinaMobileVer) {
         pngH = (short)((MainCanvas.screenH >> 1) - 60);
      } else {
         pngH = 45;
      }

      short serTranImgW = false;
      short serTranImgH = false;
      short serTranImgW = (short)girl.getWidth();
      short serTranImgH = (short)girl.getHeight();
      int i;
      if (!MainCanvas.isChinaMobileVer) {
         g.setColor(16513768);

         for(i = 0; i < Cons.ADVICE.length; ++i) {
            g.drawString(Cons.ADVICE[i], (MainCanvas.screenW >> 1) - (MainCanvas.CHARW * Cons.ADVICE[i].length() >> 1), MainCanvas.CHARH * i + (MainCanvas.screenH >> 1) - 50, 20);
         }
      } else {
         g.drawImage(girl, MainCanvas.screenW - serTranImgW >> 1, pngH, 0);
      }

      for(i = 0; i < MainCanvas.screenW / 5; ++i) {
         g.setColor(16579274);
         g.fillRect(0 + i * 5, pngH - 4, 4, 3);
         g.setColor(460544);
         g.drawLine(1 + i * 5, pngH - 3, 1 + i * 5 + 1, pngH - 3);
         g.setColor(16579274);
         g.fillRect(0 + i * 5, pngH + serTranImgH + 1, 4, 3);
         g.setColor(460544);
         g.drawLine(1 + i * 5, pngH + serTranImgH + 2, 1 + i * 5 + 1, pngH + serTranImgH + 2);
      }

      g.setColor(16513768);
      g.drawLine(0, pngH - 6, MainCanvas.screenW, pngH - 6);
      g.drawLine(0, pngH + serTranImgH + 5, MainCanvas.screenW, pngH + serTranImgH + 5);
      g.setColor(16513768);
      g.drawString("Hãy đợi 5 đến 10 giây...", MainCanvas.screenW >> 1, MainCanvas.screenH - (MainCanvas.CHARH << 1) - 10, 17);
   }

   public void initFire() {
      star = new int[20][5];

      for(int i = 0; i < star.length; ++i) {
         star[i][0] = 5 + Util.getRandom(MainCanvas.screenW - 10);
         star[i][1] = MainCanvas.screenH - 40 - Util.getRandom(20);
         star[i][2] = Util.getRandom(2) + 1;
         star[i][3] = fireColor[Util.getRandom(4)];
         star[i][4] = Util.getRandom(20);
      }

   }

   public void drawFire(Graphics g) {
      if (star == null) {
         this.initFire();
      }

      for(int i = 0; i < star.length; ++i) {
         int[] var10000 = star[i];
         var10000[1] -= star[i][2];
         int var10002;
         if (star[i][4] < 0) {
            var10002 = star[i][0]--;
            var10002 = star[i][4]++;
         } else if (star[i][4] > 0) {
            var10002 = star[i][0]++;
            var10002 = star[i][4]--;
         } else {
            star[i][4] = Util.getRandom(20) - 10;
         }

         if (star[i][1] < 0) {
            star[i][0] = Util.getRandom(MainCanvas.screenW);
            star[i][1] = MainCanvas.screenH - 40;
            star[i][2] = Util.getRandom(2) + 1;
            star[i][3] = fireColor[Util.getRandom(4)];
            star[i][4] = Util.getRandom(20) - 10;
         } else {
            g.setColor(fireColor[3]);
            g.drawLine(star[i][0] - 1, star[i][1], star[i][0] + 1, star[i][1]);
            g.drawLine(star[i][0], star[i][1] - 1, star[i][0], star[i][1] + 1);
            g.setColor(star[i][3]);
            g.fillRect(star[i][0], star[i][1], 1, 1);
         }
      }

   }

   public void backMenu() {
      this.initCover();
   }

   public static void clear() {
      MainCanvas.freeImg = null;
      comp = null;
      instance = null;
      coverPic = null;
      star = null;
      System.gc();
   }
}

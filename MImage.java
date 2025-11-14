import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class MImage {
   public String name;
   public Image img;
   public short frame_w = 0;
   public short frame_h = 0;
   public byte cols = 0;
   public byte rows = 0;
   public byte frames = 0;

   public MImage(String imageName, String pkgPath, int f_w, int f_h) {
      if (f_w != 0 && f_h != 0) {
         this.frame_w = (short)f_w;
         this.frame_h = (short)f_h;
         if (!imageName.equals("")) {
            this.img = Util.loadImage(pkgPath, imageName);
         }

         this.cols = (byte)(this.img.getWidth() / this.frame_w);
         this.rows = (byte)(this.img.getHeight() / this.frame_h);
         this.frames = (byte)(this.cols * this.rows);
      }
   }

   public MImage(Image img, int f_w, int f_h) {
      if (f_w != 0 && f_h != 0) {
         this.frame_w = (short)f_w;
         this.frame_h = (short)f_h;
         this.img = img;
         this.cols = (byte)(img.getWidth() / this.frame_w);
         this.rows = (byte)(img.getHeight() / this.frame_h);
         this.frames = (byte)(this.cols * this.rows);
      }
   }

   public MImage(String path, int f_w, int f_h) {
      if (f_w != 0 && f_h != 0) {
         this.frame_w = (short)f_w;
         this.frame_h = (short)f_h;
         if (!path.equals("")) {
            this.img = Util.loadImage(path);
            this.name = path.substring(1, path.length());
         } else {
            this.name = "";
         }

         this.cols = (byte)(this.img.getWidth() / this.frame_w);
         this.rows = (byte)(this.img.getHeight() / this.frame_h);
         this.frames = (byte)(this.cols * this.rows);
      }
   }

   public MImage(String path, Image img, int f_w, int f_h) {
      if (f_w != 0 && f_h != 0) {
         this.frame_w = (short)f_w;
         this.frame_h = (short)f_h;
         this.img = img;
         this.name = path.substring(1, path.length());
         this.cols = (byte)(img.getWidth() / this.frame_w);
         this.rows = (byte)(img.getHeight() / this.frame_h);
         this.frames = (byte)(this.cols * this.rows);
      }
   }

   public MImage(Image img) {
      if (img != null) {
         this.img = img;
         this.frame_w = (short)img.getWidth();
         this.frame_h = (short)img.getHeight();
         this.cols = this.rows = this.frames = 1;
      }
   }

   public MImage(String s) {
      if (!s.equals("")) {
         this.img = Util.loadImage(s);
      }

      this.frame_w = (short)this.img.getWidth();
      this.frame_h = (short)this.img.getHeight();
      this.cols = 1;
      this.rows = 1;
      this.frames = 1;
   }

   public void draw(Graphics g, int x, int y, int frameIndex) {
      this.draw(g, x, y, frameIndex, 0);
   }

   public void draw(Graphics g, int x, int y, int frameIndex, int trans) {
      if (this.img != null) {
         int dx = this.frame_w * (frameIndex % this.cols);
         int dy = this.frame_h * (frameIndex / this.cols);
         Util.drawRegion(g, this.img, dx, dy, this.frame_w, this.frame_h, x, y, trans);
      }
   }

   public void drawOneFrame(Graphics g, int x, int y, int trans) {
      Util.drawImage(g, this.img, x, y, trans);
   }
}

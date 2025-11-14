import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class BufMap {
   private Image img;
   private Map map;
   private Graphics g;
   int x;
   int y;
   int w;
   int h;

   private BufMap() {
   }

   public static BufMap createBufMap(Map map, int w, int h) {
      BufMap bm = new BufMap();
      bm.img = Image.createImage(w, h);
      bm.map = map;
      bm.w = w;
      bm.h = h;
      bm.g = bm.img.getGraphics();
      return bm;
   }

   public Image getImg() {
      return this.img;
   }

   public void draw(BufMap source, int posX, int posY) {
      this.g.setColor(MainCanvas.mapBGColor);
      this.g.fillRect(0, 0, this.w, this.h);
      if (source == null) {
         this.map.drawArea(this.g, posX, posY, this.w, this.h + 10);
      } else {
         int distanceX = source.x - posX;
         int distanceY = source.y - posY;
         if (distanceX == 0 && distanceY == 0) {
            this.g.drawImage(source.img, source.x - posX, source.y - posY, 20);
         } else if (distanceY < 0) {
            if (distanceX < 0) {
               this.map.drawArea(this.g, posX + source.w + distanceX + 0, posY, this.w - (source.w + distanceX) + 40, this.h);
            } else if (distanceX > 0) {
               this.map.drawArea(this.g, posX - 40, posY, distanceX + 40, this.h);
            }

            this.g.drawImage(source.img, source.x - posX, source.y - posY, 20);
            this.map.drawArea(this.g, posX, posY + source.h + distanceY + 10, this.w, this.h - (source.h + distanceY) + 0);
         } else if (distanceY > 0) {
            this.map.drawArea(this.g, posX, posY - 0, this.w, distanceY + 60);
            if (distanceX < 0) {
               this.map.drawArea(this.g, posX + source.w + distanceX + 0, posY, this.w - (source.w + distanceX) + 40, this.h);
            } else if (distanceX > 0) {
               this.map.drawArea(this.g, posX - 40, posY, distanceX + 40, this.h);
            }

            this.g.drawImage(source.img, source.x - posX, source.y - posY, 20);
         } else {
            if (distanceX < 0) {
               this.map.drawArea(this.g, posX + source.w + distanceX + 0, posY, this.w - (source.w + distanceX) + 40, this.h + 10);
            } else if (distanceX > 0) {
               this.map.drawArea(this.g, posX - 40, posY, distanceX + 40, this.h + 10);
            }

            this.g.drawImage(source.img, source.x - posX, source.y - posY, 20);
         }
      }

      this.x = posX;
      this.y = posY;
   }
}

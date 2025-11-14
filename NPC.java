import javax.microedition.lcdui.Graphics;

public class NPC extends Monster {
   byte taskShowType = 0;
   public static boolean isDrawTopTip = false;
   public static String[] topTipStrs = null;
   public static final int TOP_TIP_COLOR = 0;
   public static int topTipWidth;
   public static int topTipHeight;
   public static int[] topTipChangeWidth;
   public static int[] topTipChangeHeight;
   public static byte topTipChangeIndex;
   public static boolean isInterruptTopTip;
   public static boolean isCompleteInfo;
   public static int topTipCounter;
   private static short pointer;

   static {
      topTipWidth = MainCanvas.CHARW * 7 + 18;
      topTipHeight = 0;
      topTipChangeWidth = new int[10];
      topTipChangeHeight = new int[10];
      topTipChangeIndex = 0;
      isInterruptTopTip = false;
      isCompleteInfo = false;
      topTipCounter = 0;
      pointer = 0;
   }

   public NPC() {
   }

   public NPC(byte ntype, byte boneindex) {
      super(ntype, boneindex);
   }

   public void setTaskShowType(byte p) {
      this.taskShowType = p;
   }

   public byte getTaskShowType() {
      return this.taskShowType;
   }

   public void drawName(Graphics g) {
      super.drawName(g);
   }

   public void drawTaskShowType(Graphics g) {
      int dx = (g.getFont().stringWidth(this.getRoleName()) >> 1) + MainCanvas.maskImg.frame_w;
      int dy = MainCanvas.CHARH + ActiveGO.OBJ_NAME_SPACE - this.getSadGODy() + (this.getTitleName().length() == 0 ? 0 : MainCanvas.CHARH);
      int ImgId = 0;
      switch(this.getTaskShowType()) {
      case 1:
         ImgId = 0;
         break;
      case 2:
         ImgId = 1;
         break;
      case 3:
         if (MainCanvas.countTick % 6 < 3) {
            ImgId = 6;
         } else {
            ImgId = 7;
         }
         break;
      case 4:
         if (MainCanvas.countTick % 6 < 3) {
            ImgId = 2;
         } else {
            ImgId = 3;
         }
         break;
      case 5:
         if (MainCanvas.countTick % 6 < 3) {
            ImgId = 8;
         } else {
            ImgId = 9;
         }
         break;
      case 6:
         if (MainCanvas.countTick % 6 < 3) {
            ImgId = 4;
         } else {
            ImgId = 5;
         }
      }

      MainCanvas.maskImg.draw(g, super.drawX - dx, super.drawY - dy, ImgId);
   }

   public int getTaskShowCol(byte tt) {
      int col = 0;
      switch(tt) {
      case 1:
      case 2:
         col = Cons.TASK_SHOW_COLOR[0];
         break;
      case 3:
      case 4:
         col = Cons.TASK_SHOW_COLOR[1];
         break;
      case 5:
      case 6:
         col = Cons.TASK_SHOW_COLOR[2];
      }

      return col;
   }

   public void draw(Graphics g) {
      super.draw(g);
   }

   public static void drawNPCTopTip(Graphics g) {
      if (isDrawTopTip) {
         g.setColor(0);
         int kuangX = (MainCanvas.screenW - topTipWidth) / 2;
         int kuangY = (MainCanvas.screenH - topTipHeight) / 6;
         g.drawRoundRect(kuangX, kuangY, topTipChangeWidth[topTipChangeIndex], topTipChangeHeight[topTipChangeIndex], 12, 12);
         g.setColor(16777215);
         g.fillRoundRect(kuangX + 1, kuangY + 1, topTipChangeWidth[topTipChangeIndex] - 1, topTipChangeHeight[topTipChangeIndex] - 1, 12, 12);
         int strX = kuangX + 5;
         int strStartY = kuangY + 2;
         int length = false;
         int length = topTipStrs.length;
         int clipX = g.getClipX();
         int clipY = g.getClipY();
         int clipW = g.getClipWidth();
         int clipH = g.getClipHeight();
         g.setClip(kuangX, kuangY, topTipChangeWidth[topTipChangeIndex], topTipChangeHeight[topTipChangeIndex]);
         int i = pointer;

         for(int j = 0; i < length; ++j) {
            int offSet = 0;
            int distanceUp = 3;
            char[] tmpChars = topTipStrs[i].toCharArray();

            for(int m = 0; m < tmpChars.length; ++m) {
               if (Util.colorSigns[i][m] == 0) {
                  g.setColor(0);
               } else {
                  g.setColor(65280);
               }

               g.drawChar(tmpChars[m], strX + offSet, strStartY + distanceUp + j * (MainCanvas.CHARH + 1), 20);
               offSet += MainCanvas.curFont.charWidth(tmpChars[m]);
            }

            ++i;
         }

         g.setClip(clipX, clipY, clipW, clipH);
      }

   }

   public static void npcTopTipTick() {
      if (isDrawTopTip) {
         ++topTipCounter;
         if (isInterruptTopTip && isCompleteInfo) {
            --topTipChangeIndex;
            if (topTipChangeIndex == 0) {
               topTipCounter = 0;
               isDrawTopTip = false;
               topTipChangeIndex = 0;
               isInterruptTopTip = false;
               isCompleteInfo = false;
            }
         } else if (topTipCounter < 10) {
            topTipChangeIndex = (byte)topTipCounter;
         } else {
            topTipChangeWidth[9] = topTipWidth;
            topTipChangeHeight[9] = topTipHeight;
            isCompleteInfo = true;
         }
      }

   }
}

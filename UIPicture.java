import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class UIPicture extends UIComponent {
   public static final byte TYPE_PLAYER = 0;
   public static final byte TYPE_FRAME = 1;
   public static final byte TYPE_NO_FRAME = 2;
   public static final byte TYPE_WORD = 3;
   public static final byte TYPE_EIGHT = 4;
   public static final byte TYPE_TAIJI = 5;
   public static final byte EIGHT_TYPE_QIAN = 0;
   public static final byte EIGHT_TYPE_KUN = 1;
   public static final byte EIGHT_TYPE_ZHEN = 2;
   public static final byte EIGHT_TYPE_GEN = 3;
   public static final byte EIGHT_TYPE_LI = 4;
   public static final byte EIGHT_TYPE_KAN = 5;
   public static final byte EIGHT_TYPE_DUI = 6;
   public static final byte EIGHT_TYPE_XUN = 7;
   public static final String[] DIAGRAMS = new String[]{"Càn", "Khôn", "Chấn", "Cấn", "Ly", "Khảm", "Đoái", "Tốn"};
   public static final short CORNER_W = 6;
   public static final short CORNER_H = 6;
   public static final short PIC_SIZE = 14;
   public static final short EIGHT_OY = 1;
   public static final byte LV_PLAYER_OY = 3;
   public static final byte LV_OX = 2;
   public static final short OFFSET = 3;
   public static final byte MAX_NUM = 99;
   public static final byte LV_DOUBLE = 1;
   private short kuangWidth;
   private short kuangHeight;
   private MImage m_img;
   private String text;
   private byte eightType;
   private ActiveGO GOPic;
   private boolean isShowNum;
   private byte itemNum;
   private boolean isInnerKuang;
   private Image[] eightImg;
   private Image taijiImg;
   private short level;
   public boolean isZyPic;
   public byte zyIndex;
   public boolean isWpPic;
   public short wpIndex;
   public boolean isEquipPic;
   public short equipIndex;
   public byte attIndex;
   private boolean isHaveLevelNum;
   private String equipName;
   private short equipCurDuable;
   private short equipMaxDuable;
   private int equipFixPay;
   private byte equipColorLevel;
   private byte equipType;
   public static byte equipTypeForServer = -1;
   public byte playerPicType;
   public byte payType;
   private boolean isSelected;
   public byte groupIndex;
   public byte colIndex;
   public int imageFlip;
   private short imgW;
   private short imgH;
   private short imgX;
   private short imgY;
   public byte getType;
   public static byte sGroupIndex;
   public static byte sColIndex;
   public static byte sSelectedColIndex = -1;
   public static final byte PIC_PLAYER_EQUIP = 0;
   public static final byte PIC_COMPARE_EQUIP = 1;
   public static final byte PIC_TRADE_WP = 2;
   private byte picFormType;
   private static final byte PIC_STATE_EQUIP_NONE = 0;
   private static final byte PIC_STATE_EQUIP_COMPARE = 1;
   public OtherPlayer otherP;
   private byte picEquipState;
   private short picID;
   public boolean isLock;
   public boolean isExtendPic;
   private byte m_bWhetherEnvelop;
   private String m_sCropName;
   private String m_sGrowthPhase;
   private byte m_bGrowthLimit;
   private byte m_bWhetherGain;
   private byte m_bCropStatus_1;
   private String m_bCropStatus_2;
   private byte m_bGroundStatus;
   private int m_nFrameIndex;
   private int m_nMenuIndex;
   private byte treasureCounter;
   public static final byte EQUIP_PIC_PLAYER = 0;
   public static final byte EQUIP_PIC_COMPARE = 1;
   public static final byte EQUIP_PIC_OTHER_PLAYER = 2;
   private byte equipPicType;
   public byte quality;
   public static boolean isMyTradeLock = false;
   public static boolean isOtherTradeLock = false;

   public short getPicID() {
      return this.picID;
   }

   public void setPicID(short picID) {
      this.picID = picID;
   }

   public byte getPicEquipState() {
      return this.picEquipState;
   }

   public void setPicEquipState(byte picEquipState) {
      this.picEquipState = picEquipState;
   }

   public byte getPicFormType() {
      return this.picFormType;
   }

   public void setPicFormType(byte picFormType) {
      this.picFormType = picFormType;
   }

   public short getLevel() {
      return this.level;
   }

   public void setLevel(short level) {
      this.level = level;
   }

   public UIPicture(short x, short y, short w, short h, String imgName, String pkgName, byte type, UIForm form) {
      super(x, y, w, h, form);
      this.eightType = -1;
      this.GOPic = null;
      this.isShowNum = false;
      this.itemNum = 0;
      this.isInnerKuang = false;
      this.eightImg = new Image[3];
      this.taijiImg = null;
      this.level = 1;
      this.isZyPic = false;
      this.zyIndex = -1;
      this.isWpPic = false;
      this.wpIndex = -1;
      this.isEquipPic = false;
      this.equipIndex = -1;
      this.attIndex = -1;
      this.isHaveLevelNum = false;
      this.equipName = null;
      this.equipCurDuable = 0;
      this.equipMaxDuable = 0;
      this.equipFixPay = 0;
      this.equipColorLevel = -1;
      this.equipType = -1;
      this.playerPicType = -1;
      this.payType = -1;
      this.isSelected = false;
      this.groupIndex = -1;
      this.colIndex = -1;
      this.imageFlip = 0;
      this.imgW = 0;
      this.imgH = 0;
      this.imgX = 0;
      this.imgY = 0;
      this.getType = -1;
      this.picFormType = 0;
      this.picEquipState = 0;
      this.picID = 0;
      this.isLock = false;
      this.isExtendPic = false;
      this.m_bWhetherEnvelop = 0;
      this.m_sCropName = "";
      this.m_sGrowthPhase = "";
      this.m_bGrowthLimit = 0;
      this.m_bWhetherGain = 0;
      this.m_bCropStatus_1 = 0;
      this.m_bCropStatus_2 = "";
      this.m_bGroundStatus = 0;
      this.m_nFrameIndex = 0;
      this.m_nMenuIndex = 0;
      this.treasureCounter = 1;
      this.equipPicType = 0;
      this.quality = 0;

      try {
         UIForm.encryptImg.getClass();
      } catch (Exception var10) {
         MainCanvas.aMidlet.exitMIDlet();
      }

      super.type = type;
      if (type != 4 && type != 5) {
         if (imgName != null && !imgName.equals("")) {
            if (imgName.equals("exp.png")) {
               this.setImg(MainCanvas.stuffMImg);
               this.isWpPic = true;
               this.wpIndex = 43;
            } else if (imgName.equals("t-0.png")) {
               this.setImg(MainCanvas.stuffMImg);
               this.isWpPic = true;
               this.wpIndex = 10;
            } else if (imgName.equals("u-6.png")) {
               this.setImg(MainCanvas.stuffMImg);
               this.isWpPic = true;
               this.wpIndex = 57;
            } else if (imgName.equals("u-7.png")) {
               this.setImg(MainCanvas.stuffMImg);
               this.isWpPic = true;
               this.wpIndex = 58;
            } else if (imgName.equals("u-8.png")) {
               this.setImg(MainCanvas.stuffMImg);
               this.isWpPic = true;
               this.wpIndex = 67;
            } else if (imgName.equals("u-4.png")) {
               this.setImg(MainCanvas.stuffMImg);
               this.isWpPic = true;
               if (PCIncrement.m_bBuyMoney != 1 && PCIncrement.m_bBuyMoney != 9) {
                  if (PCIncrement.m_bBuyMoney == 0 || PCIncrement.m_bBuyMoney == 10) {
                     this.wpIndex = 63;
                  }
               } else {
                  this.wpIndex = 65;
               }
            } else if (imgName.equals("u-1.png")) {
               this.setImg(MainCanvas.stuffMImg);
               this.isWpPic = true;
               this.wpIndex = 65;
            } else if (imgName.equals("u-3.png")) {
               this.setImg(MainCanvas.stuffMImg);
               this.isWpPic = true;
               this.wpIndex = 63;
            } else if (imgName.equals("long.png")) {
               this.setImg(MainCanvas.stuffMImg);
               this.isWpPic = true;
               this.wpIndex = 72;
               this.quality = 5;
            } else if (imgName.equals("cross.png")) {
               this.setImg(MainCanvas.stuffMImg);
               this.isWpPic = true;
               this.wpIndex = 73;
               this.quality = 4;
            } else if (imgName.equals("clanctr.png")) {
               this.setImg(MainCanvas.stuffMImg);
               this.isWpPic = true;
               this.wpIndex = 44;
               this.quality = 2;
            }
         } else {
            this.m_img = null;
         }
      }

      if (type == 3 || type == 1 || type == 2 || type == 0) {
         super.canFocus = true;
      }

      this.calKuangWidth();
      this.calKuangHeight();
   }

   public UIPicture(short x, short y, String imgName, String pkgName, byte type, UIForm form) {
      this(x, y, (short)0, (short)0, imgName, pkgName, type, form);
   }

   public UIPicture(short x, short y, UIForm form) {
      this(x, y, (short)0, (short)0, "", "", (byte)5, form);
   }

   public UIPicture(byte type, UIForm form) {
      this((short)0, (short)0, (short)0, (short)0, "", "", type, form);
   }

   public void draw(Graphics g) {
      int i;
      int imgY;
      if (super.type == 0) {
         g.setColor(Cons.COLOR_PIC_PLAYER_BORDER_1);
         g.drawRect(super.positionX, super.positionY, super.width - 1, super.height - 1);
         g.setColor(Cons.COLOR_PIC_PLAYER_BORDER_2);
         g.drawRect(super.positionX + 1, super.positionY + 1, super.width - 3, super.height - 3);
         g.setColor(Cons.COLOR_PIC_PLAYER_BORDER_1);
         g.drawRect(super.positionX + 2, super.positionY + 2, super.width - 5, super.height - 5);
         g.setColor(Cons.COLOR_PIC_PLAYER_BG);
         g.fillRect(super.positionX + 3, super.positionY + 3, super.width - 6, super.height - 6);
         g.drawImage(MainCanvas.p_jImg, super.positionX, super.positionY, 20);
         Util.drawImage(g, MainCanvas.p_jImg, super.positionX + super.width - MainCanvas.p_jImg.getWidth(), super.positionY, 2);
         Util.drawImage(g, MainCanvas.p_jImg, super.positionX, super.positionY + super.height - MainCanvas.p_jImg.getHeight(), 1);
         Util.drawImage(g, MainCanvas.p_jImg, super.positionX + super.width - MainCanvas.p_jImg.getWidth(), super.positionY + super.height - MainCanvas.p_jImg.getHeight(), 3);
         int levelX = false;
         int levelY = false;
         if (MainCanvas.getState() == 5) {
            if (this.isHaveLevelNum) {
               if (this.level < 100) {
                  i = super.positionX + (super.width - MainCanvas.ui_lvNumMImg.frame_w * 2 - 2) / 2;
               } else {
                  i = super.positionX + (super.width - MainCanvas.ui_lvNumMImg.frame_w * 3 - 4) / 2;
                  if (i < super.positionX) {
                     i = super.positionX;
                  }
               }

               imgY = super.positionY + (super.height - 30 - MainCanvas.ui_lvNumMImg.frame_h - 3) / 2;
               if (imgY < super.positionY) {
                  imgY = super.positionY;
               }

               Util.drawLevelNum(g, this.level, i, imgY - 1);
               this.imgW = 27;
               this.imgH = 30;
               this.imgX = (short)(super.width >= this.imgW ? super.positionX + (super.width - this.imgW) / 2 : super.positionX - (this.imgW - super.width) / 2);
               this.imgY = (short)(imgY + MainCanvas.ui_lvNumMImg.frame_h + 3);
            } else {
               this.imgW = 27;
               this.imgH = 30;
               this.imgX = (short)(super.width >= this.imgW ? super.positionX + (super.width - this.imgW) / 2 : super.positionX - (this.imgW - super.width) / 2);
               this.imgY = (short)(super.positionY + (super.height - 30) / 2);
            }

            if (this.GOPic != null) {
               this.GOPic.motionState_tick();
               this.GOPic.drawBody(g, (short)(this.imgW / 2 + this.imgX), (short)(this.imgY + 30));
            }

            if (PCTreasure.treasurePicID > 0) {
               this.imgW = (short)MainCanvas.treasurePic.getWidth();
               this.imgH = (short)MainCanvas.treasurePic.getHeight();
               this.imgX = (short)(super.width >= this.imgW ? super.positionX + (super.width - this.imgW) / 2 : super.positionX - (this.imgW - super.width) / 2);
               this.imgY = (short)(super.positionY + (super.height - MainCanvas.treasurePic.getHeight()) / 2);
               this.treasureCounter = (byte)(this.treasureCounter ^ 1);
               this.drawTreasure(g, this.imgX, (short)(this.imgY + this.treasureCounter));
            }
         }

         if (MainCanvas.cursorImg == null || MainCanvas.getState() != 17 && MainCanvas.oldState != 17) {
            if (MainCanvas.getState() == 18 || MainCanvas.oldState == 18 || MainCanvas.getState() == 18) {
               this.imgW = 27;
               this.imgH = 30;
               this.imgX = (short)(super.width >= this.imgW ? super.positionX + (super.width - this.imgW) / 2 : super.positionX - (this.imgW - super.width) / 2);
               this.imgY = (short)(super.positionY + (super.height - this.imgH) / 2 + this.imgH / 2 + 15);
               if (this.otherP != null) {
                  this.otherP.drawHumanHorseCart(g, (short)(this.imgW / 2 + this.imgX), this.imgY, true);
               }
            }
         } else {
            this.imgW = 27;
            this.imgH = 30;
            this.imgX = (short)(super.width >= this.imgW ? super.positionX + (super.width - this.imgW) / 2 : super.positionX - (this.imgW - super.width) / 2);
            if (this.m_img != null && this.m_img.img != null) {
               imgY = super.positionY + (super.height - this.m_img.img.getHeight()) / 2;
               if (imgY < super.positionY) {
                  imgY = super.positionY;
               }

               this.imgY = (short)imgY;
               this.imgY = (short)(this.imgY + this.imgH / 2 + 12);
            } else {
               imgY = super.positionY + (super.height - this.imgH) / 2;
               if (imgY < super.positionY) {
                  imgY = super.positionY;
               }

               this.imgY = (short)imgY;
               this.imgY = (short)(this.imgY + this.imgH / 2 + 15);
            }

            if (this.otherP != null) {
               this.otherP.drawHumanHorseCart(g, (short)(this.imgW / 2 + this.imgX), this.imgY, true);
            }

            if (this.isFocus()) {
               g.drawImage(MainCanvas.cursorImg, super.positionX + super.width - MainCanvas.cursorImg.getWidth() / 2, super.positionY + super.height - MainCanvas.cursorImg.getHeight() / 2, 20);
            }
         }

         if (MainCanvas.curForm != null && MainCanvas.curForm.clientCommand == 2097155) {
            for(int i = 0; i < 7; ++i) {
               PCGamble.uipicture[i] = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 10);
               MainCanvas.stuffMImg.draw(g, PCGamble.uipicture[i].positionX + (PCGamble.uipicture[i].getWidth() >> 1) - 7, PCGamble.uipicture[i].positionY + (PCGamble.uipicture[i].getHeight() >> 1) - 7, PCGamble.picture[i]);
            }
         }
      } else if (super.type == 1) {
         if (!this.isInnerKuang) {
            this.drawDotKuang(g, this.kuangWidth, this.kuangHeight);
         }

         if (this.m_img != null) {
            if (this.m_img.img == null) {
               throw new IllegalArgumentException("Không tồn tại ảnh");
            }

            i = this.isInnerKuang ? super.positionX : super.positionX + 1;
            imgY = this.isInnerKuang ? super.positionY : super.positionY + 1;
            if (this.isWpPic) {
               if (this.quality > -1) {
                  g.setColor(Cons.COLOR_GRID_FRAME_BG[this.quality][0]);
                  g.fillRect(i, imgY, this.m_img.frame_w, this.m_img.frame_h);
                  this.m_img.draw(g, i, imgY, this.wpIndex);
                  g.setColor(Cons.COLOR_GRID_FRAME_BG[this.quality][1]);
                  g.drawRect(i - 1, imgY - 1, this.m_img.frame_w + 1, this.m_img.frame_h + 1);
               }
            } else {
               this.m_img.drawOneFrame(g, i, imgY, 0);
            }
         }

         if (this.isInnerKuang) {
            this.drawInnerKuang(g);
         }

         if (this.isShowNum) {
            drawNum(g, super.positionX, super.positionY, this.itemNum);
         }

         if (MainCanvas.curForm.clientCommand == 2555905) {
            if (this.getWhetherEnvelop() == 0) {
               g.drawImage(MainCanvas.ui_fengImg, super.positionX, super.positionY, 20);
            } else {
               g.setColor(this.getFrameIndex());
               g.drawRect(super.positionX, super.positionY, this.getWidth() + 1, this.getHeight() + 1);
            }
         }

         if (MainCanvas.curForm.clientCommand == 3342340 && this.getWhetherEnvelop() == 0) {
            g.drawImage(MainCanvas.ui_fengImg, super.positionX, super.positionY, 20);
         }

         if (this.isFocus() && MainCanvas.cursorImg != null) {
            if (this.getType != -1) {
               Util.drawImage(g, MainCanvas.cursorImg, super.positionX - 12, super.positionY + 2, 2);
            } else if (MainCanvas.curForm.clientCommand != 1900547 && MainCanvas.curForm.clientCommand != 2031621 && MainCanvas.curForm.clientCommand != 2031636 && MainCanvas.curForm.clientCommand != 2228225 && MainCanvas.curForm.clientCommand != 1966097 && MainCanvas.curForm.clientCommand != 458760 && MainCanvas.curForm.clientCommand != 1179658 && MainCanvas.curForm.clientCommand != 2555905 && MainCanvas.curForm.clientCommand != 2555911 && MainCanvas.curForm.clientCommand != 3145729 && MainCanvas.curForm.clientCommand != 3145730 && MainCanvas.curForm.clientCommand != 2228256 && MainCanvas.curForm.clientCommand != 3342340 && MainCanvas.curForm.clientCommand != 3342356 && MainCanvas.curForm.clientCommand != 917525) {
               g.drawImage(MainCanvas.cursorImg, super.positionX + 10, super.positionY + 10, 20);
            } else {
               Util.drawSelectedKuangEffect(g, super.positionX, super.positionY, super.width + 2, super.height + 2);
            }
         }

         if (this.isSelected) {
            Util.drawSelectedKuangEffect(g, super.positionX, super.positionY, this.kuangWidth + 2, this.kuangHeight + 2);
         }
      } else if (super.type == 2) {
         if (this.m_img != null) {
            if (this.m_img.img == null) {
               throw new IllegalArgumentException("Không tồn tại ảnh");
            }

            if (this.isZyPic) {
               this.m_img.draw(g, super.positionX, super.positionY, this.zyIndex);
            } else {
               this.m_img.drawOneFrame(g, super.positionX, super.positionY, this.imageFlip);
            }
         }

         if (this.isFocus() && MainCanvas.cursorImg != null) {
            g.drawImage(MainCanvas.cursorImg, super.positionX + 10, super.positionY + 10, 20);
         }
      } else if (super.type == 3) {
         this.drawDotKuang(g, 14, 14);
         if (this.text != null && !this.text.equals("")) {
            i = super.positionX + (16 - Util.getStrLen(this.text)) / 2;
            imgY = super.positionY + (16 - MainCanvas.CHARH) / 2;
            g.setColor(Cons.COLOR_PIC_FRAME_WORD);
            g.drawString(this.text, i, imgY, 20);
         }

         if (this.m_img != null) {
            if (this.m_img.img == null) {
               throw new IllegalArgumentException("Không tồn tại ảnh");
            }

            if (this.isEquipPic) {
               if (this.quality > -1) {
                  g.setColor(Cons.COLOR_GRID_FRAME_BG[this.quality][0]);
                  g.fillRect(super.positionX + 1, super.positionY + 1, this.m_img.frame_w, this.m_img.frame_h);
                  this.m_img.draw(g, super.positionX + 1, super.positionY + 1, this.equipIndex);
                  g.setColor(Cons.COLOR_GRID_FRAME_BG[this.quality][1]);
                  g.drawRect(super.positionX, super.positionY, this.m_img.frame_w + 1, this.m_img.frame_h + 1);
               }
            } else {
               this.m_img.drawOneFrame(g, super.positionX + 1, super.positionY + 1, 0);
            }
         }

         if (this.isSelected) {
            Util.drawSelectedKuangEffect(g, super.positionX, super.positionY, this.m_img.frame_w + 1, this.m_img.frame_h + 1);
         }
      } else if (super.type == 4) {
         switch(this.eightType) {
         case 0:
            this.eightImg[0] = MainCanvas.ui_gx1Img;
            this.eightImg[1] = MainCanvas.ui_gx1Img;
            this.eightImg[2] = MainCanvas.ui_gx1Img;
            break;
         case 1:
            this.eightImg[0] = MainCanvas.ui_gx2Img;
            this.eightImg[1] = MainCanvas.ui_gx2Img;
            this.eightImg[2] = MainCanvas.ui_gx2Img;
            break;
         case 2:
            this.eightImg[0] = MainCanvas.ui_gx2Img;
            this.eightImg[1] = MainCanvas.ui_gx2Img;
            this.eightImg[2] = MainCanvas.ui_gx1Img;
            break;
         case 3:
            this.eightImg[0] = MainCanvas.ui_gx1Img;
            this.eightImg[1] = MainCanvas.ui_gx2Img;
            this.eightImg[2] = MainCanvas.ui_gx2Img;
            break;
         case 4:
            this.eightImg[0] = MainCanvas.ui_gx1Img;
            this.eightImg[1] = MainCanvas.ui_gx2Img;
            this.eightImg[2] = MainCanvas.ui_gx1Img;
            break;
         case 5:
            this.eightImg[0] = MainCanvas.ui_gx2Img;
            this.eightImg[1] = MainCanvas.ui_gx1Img;
            this.eightImg[2] = MainCanvas.ui_gx2Img;
            break;
         case 6:
            this.eightImg[0] = MainCanvas.ui_gx2Img;
            this.eightImg[1] = MainCanvas.ui_gx1Img;
            this.eightImg[2] = MainCanvas.ui_gx1Img;
            break;
         case 7:
            this.eightImg[0] = MainCanvas.ui_gx1Img;
            this.eightImg[1] = MainCanvas.ui_gx1Img;
            this.eightImg[2] = MainCanvas.ui_gx2Img;
         }

         for(i = 0; i < this.eightImg.length; ++i) {
            g.drawImage(this.eightImg[i], super.positionX, super.positionY + i * (MainCanvas.ui_gx1Img.getHeight() + 1), 20);
         }
      } else if (super.type == 5) {
         this.taijiImg = MainCanvas.taiji_Img;
         g.drawImage(this.taijiImg, super.positionX, super.positionY, 20);
      }

   }

   public void drawCursor(Graphics g) {
      if (MainCanvas.curForm.clientCommand == 2097155) {
         this.drawGamble(g);
      } else if (this.isFocus() && MainCanvas.cursorImg != null) {
         g.drawImage(MainCanvas.cursorImg, super.positionX + 10, super.positionY + 10, 20);
      }

   }

   private void drawGamble(Graphics g) {
      if (this.isFocus()) {
         Util.drawLetter(g, this.text, super.positionX + (16 - Util.getStrLen(this.text)) / 2, super.positionY + (16 - MainCanvas.CHARH) / 2, Cons.COLOR_PIC_FRAME_WORD, Cons.COLOR_SCROLL_LINE_1);
      }

   }

   public MImage getImg() {
      return this.m_img;
   }

   public void setImg(MImage img) {
      this.m_img = img;
   }

   public String getText() {
      return this.text;
   }

   public void setText(String text) {
      if (text != null && text.length() == 1 && super.type == 3) {
         this.text = text;
      }
   }

   private void drawDotKuang(Graphics g, int kuangW, int kuangH) {
      drawDotKuang(g, super.positionX, super.positionY, kuangW, kuangH);
   }

   public static void drawDotKuang(Graphics g, int x, int y, int kuangW, int kuangH) {
      g.setColor(Cons.COLOR_PIC_FRAME_BORDER);
      g.drawRect(x, y, kuangW + 1, kuangH + 1);
      g.setColor(Cons.COLOR_PIC_FRAME_BG);
      g.fillRect(x + 1, y + 1, kuangW, kuangH);
   }

   private void drawInnerKuang(Graphics g) {
      g.setColor(Cons.COLOR_PIC_FRAME_BORDER);
      g.drawRect(super.positionX, super.positionY, this.kuangWidth - 1, this.kuangHeight - 1);
   }

   public static void drawNum(Graphics g, int x, int y, int itemNum) {
      int numX = x + 14 + 2 - 2 * MainCanvas.ui_numMImg.frame_w + 1;
      int numY = y + 14 + 2 - MainCanvas.ui_numMImg.frame_h;
      MainCanvas.ui_numMImg.draw(g, numX, numY, itemNum / 10);
      MainCanvas.ui_numMImg.draw(g, numX + MainCanvas.ui_numMImg.frame_w - 1, numY, itemNum % 10);
   }

   public byte getEightType() {
      return this.eightType;
   }

   public void setEightType(byte eightType) {
      if (eightType >= 0 && eightType <= 7) {
         this.eightType = eightType;
      } else {
         throw new IllegalArgumentException("Loại Bát Quái không phù hợp");
      }
   }

   public boolean isShowNum() {
      return this.isShowNum;
   }

   public void setShowNum(boolean isShowNum) {
      this.isShowNum = isShowNum;
   }

   public void setShowNum(boolean isShowNum, byte itemNum) {
      this.isShowNum = isShowNum;
      if (isShowNum) {
         this.setItemNum(itemNum);
      }

   }

   public byte getItemNum() {
      return this.itemNum;
   }

   public void setItemNum(byte itemNum) {
      if (itemNum >= 0 && itemNum <= 99) {
         this.itemNum = itemNum;
      } else {
         throw new IllegalArgumentException("Tham số vật phẩm không phù hợp yêu cầu");
      }
   }

   public boolean isInnerKuang() {
      return this.isInnerKuang;
   }

   public void setInnerKuang(boolean isInnerKuang) {
      this.isInnerKuang = isInnerKuang;
   }

   private void calKuangWidth() {
      if (super.type == 1 || super.type == 3) {
         if (this.m_img != null && this.m_img.img != null && !this.isWpPic) {
            this.kuangWidth = (short)this.m_img.img.getWidth();
         } else {
            this.kuangWidth = 14;
         }

         super.width = this.kuangWidth;
      }

   }

   private void calKuangHeight() {
      if (super.type == 1 || super.type == 3) {
         if (this.m_img != null && this.m_img.img != null && !this.isWpPic) {
            this.kuangHeight = (short)this.m_img.img.getHeight();
         } else {
            this.kuangHeight = 14;
         }

         super.height = this.kuangHeight;
      }

   }

   public short getKuangHeight() {
      return this.kuangHeight;
   }

   public void setKuangHeight(short kuangHeight) {
      this.kuangHeight = kuangHeight;
   }

   public short getKuangWidth() {
      return this.kuangWidth;
   }

   public void setKuangWidth(short kuangWidth) {
      this.kuangWidth = kuangWidth;
   }

   public void setImgID(byte imgID) {
   }

   public boolean isHaveLevelNum() {
      return this.isHaveLevelNum;
   }

   public void setHaveLevelNum(boolean isHaveLevelNum) {
      this.isHaveLevelNum = isHaveLevelNum;
   }

   public String getEquipName() {
      return this.equipName;
   }

   public void setEquipName(String equipName) {
      this.equipName = equipName;
   }

   public void keyInAction() {
      if (this.isFocus()) {
         if (super.isShowMenu) {
            if (MainCanvas.isInputDownOrHold(4096)) {
               this.getMenu().decreaseIndex();
            } else if (MainCanvas.isInputDownOrHold(8192)) {
               this.getMenu().increaseIndex();
            } else if (!MainCanvas.isInputDown(65536) && !MainCanvas.isInputDown(131072)) {
               if (MainCanvas.isInputDown(262144)) {
                  super.isShowMenu = false;
                  this.getMenu().resetIndex();
               }
            } else {
               this.getMenu().saveForm();
               int cmd;
               if (super.type == 3) {
                  switch(super.clientType) {
                  case 16:
                     switch(this.equipPicType) {
                     case 0:
                        switch(MainCanvas.curForm.clientCommand) {
                        case 458817:
                           cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                           PCProduce.enchantingIndex = equipTypeForServer;
                           PCProduce.enchantingRoute = 2;
                           if (cmd == 3342338) {
                              PCTreasure.unlockMenu = false;
                           }

                           MainCanvas.ni.send(cmd);
                           super.isShowMenu = false;
                           return;
                        case 939349:
                           cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                           PCEquip.m_bOrigin = 1;
                           PCEquip.m_bEquipIndex = equipTypeForServer;
                           if (cmd == 3342338) {
                              PCTreasure.unlockMenu = false;
                           }

                           MainCanvas.ni.send(cmd);
                           super.isShowMenu = false;
                           return;
                        case 1966086:
                           cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                           PCGem.m_bEnchaseRoute = 2;
                           PCGem.m_bEquipIndex = equipTypeForServer;
                           if (cmd == 3342338) {
                              PCTreasure.unlockMenu = false;
                           }

                           MainCanvas.ni.send(cmd);
                           super.isShowMenu = false;
                           return;
                        default:
                           if (this.equipIndex == -1) {
                              MainCanvas.ni.send(2425072);
                              super.isShowMenu = false;
                           } else {
                              cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                              switch(cmd) {
                              case 917506:
                                 MainCanvas.ni.send(cmd);
                                 super.isShowMenu = false;
                                 return;
                              case 917507:
                                 PCGem.m_bEnchaseRoute = 2;
                                 PCGem.m_bEnchaseSucceed = 0;
                                 MainCanvas.ni.send(cmd);
                                 super.isShowMenu = false;
                                 return;
                              case 1245185:
                                 UIForm.setCompareTI((byte)1, this.equipType);
                                 super.isShowMenu = false;
                                 return;
                              case 2425072:
                                 MainCanvas.ni.send(cmd);
                                 super.isShowMenu = false;
                                 return;
                              case 3342338:
                                 MainCanvas.ni.send(cmd);
                                 super.isShowMenu = false;
                              }
                           }

                           return;
                        }
                     default:
                        return;
                     }
                  case 115:
                     switch(this.equipPicType) {
                     case 0:
                        cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                        if (cmd == 3342338) {
                           PCTreasure.unlockMenu = false;
                        }

                        MainCanvas.ni.send(cmd);
                        super.isShowMenu = false;
                     }
                  }
               } else if (super.type == 1) {
                  int i;
                  if (this.getType != -1) {
                     sGroupIndex = this.groupIndex;
                     sColIndex = this.colIndex;
                     cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                     if (cmd == 0) {
                        if (this.groupIndex == 1) {
                           Util.setReturnTaskPicUnSelected();
                           sSelectedColIndex = this.colIndex;
                           this.isSelected = true;
                           super.isShowMenu = false;
                        }
                     } else if (cmd == 262150) {
                        if (PCTask.haveMayBeChoice) {
                           if (sSelectedColIndex == -1) {
                              MainCanvas.curTopForm = new UITopForm((byte)0, (UIForm)null);
                              MainCanvas.curTopForm.setTopFormInfo((byte)5, Cons.COLOR_PANEL_BORDER_3, "Xin chọn phần thưởng", Cons.C_STR[2], "", -1, -1);
                              super.isShowMenu = false;
                           } else {
                              MainCanvas.ni.send(cmd);
                              super.isShowMenu = false;
                           }
                        } else {
                           MainCanvas.ni.send(cmd);
                           super.isShowMenu = false;
                        }
                     } else if (cmd == 1245185) {
                        i = sGroupIndex * 10 + sColIndex;
                        PCCompare.isTaskCompare = true;
                        UIForm.setCompareTI((byte)5, i);
                        super.isShowMenu = false;
                     } else if (cmd == -1) {
                        super.isShowMenu = false;
                     } else {
                        MainCanvas.ni.send(cmd);
                        super.isShowMenu = false;
                     }
                  } else {
                     label711:
                     switch(super.clientType) {
                     case 34:
                        if (isMyTradeLock && isOtherTradeLock) {
                           if (this.wpIndex != -1) {
                              switch(this.getMenu().getIndex()) {
                              case 0:
                                 UIComponent.especialDealTrade();
                                 MainCanvas.ni.send(1376267);
                                 super.isShowMenu = false;
                                 break label711;
                              case 1:
                                 MainCanvas.ni.send(1376266);
                                 super.isShowMenu = false;
                              }
                           } else {
                              switch(this.getMenu().getIndex()) {
                              case 0:
                                 MainCanvas.ni.send(1376266);
                                 super.isShowMenu = false;
                              }
                           }
                        } else if (isMyTradeLock && !isOtherTradeLock) {
                           if (this.wpIndex != -1) {
                              switch(this.getMenu().getIndex()) {
                              case 0:
                                 UIComponent.especialDealTrade();
                                 MainCanvas.ni.send(1376267);
                                 super.isShowMenu = false;
                              }
                           }
                        } else if (!isMyTradeLock) {
                           if (this.wpIndex != -1) {
                              switch(this.getMenu().getIndex()) {
                              case 0:
                                 UIComponent.especialDealTrade();
                                 MainCanvas.ni.send(1376267);
                                 super.isShowMenu = false;
                                 break label711;
                              case 1:
                                 UIComponent.especialDealTrade();
                                 MainCanvas.ni.send(1376268);
                                 super.isShowMenu = false;
                                 break label711;
                              case 2:
                                 MainCanvas.ni.send(1376264);
                                 super.isShowMenu = false;
                                 break label711;
                              case 3:
                                 MainCanvas.ni.send(1376265);
                                 super.isShowMenu = false;
                                 break label711;
                              case 4:
                                 MainCanvas.ni.send(1376266);
                                 super.isShowMenu = false;
                              }
                           } else {
                              switch(this.getMenu().getIndex()) {
                              case 0:
                                 if (MainCanvas.curFormVector.size() == 0) {
                                    MainCanvas.ni.send(1376263);
                                 } else {
                                    MainCanvas.curForm = (UIForm)MainCanvas.curFormVector.elementAt(0);
                                 }

                                 super.isShowMenu = false;
                                 break label711;
                              case 1:
                                 MainCanvas.ni.send(1376264);
                                 super.isShowMenu = false;
                                 break label711;
                              case 2:
                                 MainCanvas.ni.send(1376265);
                                 super.isShowMenu = false;
                                 break label711;
                              case 3:
                                 MainCanvas.ni.send(1376266);
                                 super.isShowMenu = false;
                              }
                           }
                        }
                        break;
                     case 35:
                        if (isOtherTradeLock && isMyTradeLock) {
                           if (this.wpIndex != -1) {
                              switch(this.getMenu().getIndex()) {
                              case 0:
                                 UIComponent.especialDealTrade();
                                 MainCanvas.ni.send(1376267);
                                 super.isShowMenu = false;
                                 break;
                              case 1:
                                 MainCanvas.ni.send(1376266);
                                 super.isShowMenu = false;
                              }
                           }
                        } else if (this.wpIndex != -1) {
                           switch(this.getMenu().getIndex()) {
                           case 0:
                              UIComponent.especialDealTrade();
                              MainCanvas.ni.send(1376267);
                              super.isShowMenu = false;
                           }
                        }
                     }
                  }

                  int cmd;
                  if (MainCanvas.curForm.clientCommand != 1703937 && MainCanvas.curForm.clientCommand != 1703952 && MainCanvas.curForm.clientCommand != 1703938) {
                     if (MainCanvas.curForm.clientCommand != 1703940 && MainCanvas.curForm.clientCommand != 1703941) {
                        if (MainCanvas.curForm.clientCommand == 1900547) {
                           if (PCAuction.m_bEntityGoods == 1) {
                              cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                              switch(cmd) {
                              case -1610612714:
                                 PCAuction.RetrieveGoods((byte)1);
                                 break;
                              case -1610612713:
                                 PCAuction.ConfirmAuction();
                              }

                              super.isShowMenu = false;
                           }
                        } else {
                           int i;
                           UIGrid grid;
                           int i;
                           UIPicture pic;
                           UILabel nameLabel;
                           UIPicture pic;
                           if (MainCanvas.curForm.clientCommand == 2031621) {
                              pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                              pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(6);
                              nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(14);
                              i = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                              switch(i) {
                              case -1610612707:
                                 if (pic.isFocus()) {
                                    PCUnsealGemCarve.m_bFocus = 1;
                                    pic.setFocus(false, MainCanvas.curForm);
                                 } else if (pic.isFocus()) {
                                    PCUnsealGemCarve.m_bFocus = 2;
                                    pic.setFocus(false, MainCanvas.curForm);
                                 }

                                 grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(9);
                                 grid.setFocus(true, MainCanvas.curForm);
                                 break;
                              case -1610612704:
                                 for(i = 0; i < UIGrid.AuctionBag.length; ++i) {
                                    UIGrid.AuctionBag[i] = -1;
                                 }

                                 if (pic.isFocus()) {
                                    pic.setShowNum(false);
                                    pic.isWpPic = false;
                                    pic.setImg((MImage)null);
                                    PCUnsealGemCarve.m_bPrimaryGemIndex = -1;
                                    nameLabel.setTextColor(Cons.COLOR_FONT_1);
                                    nameLabel.setText("Nguyên thạch đặt đây");
                                 } else if (pic.isFocus()) {
                                    pic.setShowNum(false);
                                    pic.isWpPic = false;
                                    pic.setImg((MImage)null);
                                    PCUnsealGemCarve.m_bCarveSymbolIndex = -1;
                                    nameLabel.setTextColor(Cons.COLOR_FONT_1);
                                    nameLabel.setText("Phù Điêu Khắc đặt đây");
                                 }
                                 break;
                              case 2031623:
                                 if (PCUnsealGemCarve.m_bPrimaryGemIndex == -1) {
                                    UITopForm.createLocalTopForm((byte)0, (String)"Xin chọn nguyên thạch cần điêu khắc.", "Xác nhận", "", -1, -2);
                                    super.isShowMenu = false;
                                    return;
                                 }

                                 if (PCUnsealGemCarve.m_bCarveSymbolIndex == -1) {
                                    UITopForm.createLocalTopForm((byte)0, (String)"Xin chọn Phù Điêu Khắc thích hợp tiến hành Điêu khắc.", "Xác nhận", "", -1, -2);
                                    super.isShowMenu = false;
                                    return;
                                 }

                                 MainCanvas.ni.send(2031623);
                                 break;
                              case 2031635:
                                 if (pic.isFocus()) {
                                    PCUnsealGemCarve.m_bExamine = PCUnsealGemCarve.m_bPrimaryGemIndex;
                                 } else if (pic.isFocus()) {
                                    PCUnsealGemCarve.m_bExamine = PCUnsealGemCarve.m_bCarveSymbolIndex;
                                 }

                                 MainCanvas.ni.send(2031635);
                                 break;
                              case 2031649:
                                 MainCanvas.ni.send(2031649);
                              }

                              super.isShowMenu = false;
                           } else if (MainCanvas.curForm.clientCommand == 2031636) {
                              pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                              pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(6);
                              nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(14);
                              i = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                              switch(i) {
                              case -1610612707:
                                 if (pic.isFocus()) {
                                    PCUnsealGemCarve.m_bFocus = 1;
                                    pic.setFocus(false, MainCanvas.curForm);
                                 } else if (pic.isFocus()) {
                                    PCUnsealGemCarve.m_bFocus = 2;
                                    pic.setFocus(false, MainCanvas.curForm);
                                 }

                                 grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(9);
                                 grid.setFocus(true, MainCanvas.curForm);
                                 break;
                              case -1610612704:
                                 nameLabel.setTextColor(Cons.COLOR_FONT_1);
                                 nameLabel.setText("Đặt trang bị cần khai phong tại đây");

                                 for(i = 0; i < UIGrid.AuctionBag.length; ++i) {
                                    UIGrid.AuctionBag[i] = -1;
                                 }

                                 if (pic.isFocus()) {
                                    pic.setShowNum(false);
                                    pic.isWpPic = false;
                                    pic.setImg((MImage)null);
                                    PCUnsealGemCarve.m_bPrimaryGemIndex = -1;
                                    nameLabel.setTextColor(Cons.COLOR_FONT_1);
                                    nameLabel.setText("Đặt trang bị cần khai phong tại đây");
                                 } else if (pic.isFocus()) {
                                    pic.setShowNum(false);
                                    pic.isWpPic = false;
                                    pic.setImg((MImage)null);
                                    PCUnsealGemCarve.m_bCarveSymbolIndex = -1;
                                    nameLabel.setTextColor(Cons.COLOR_FONT_1);
                                    nameLabel.setText("Đặt quyển khai phong tại đây");
                                 }
                                 break;
                              case -1610612635:
                                 equipTypeForServer = 20;
                                 UIGrid.fromEquipUnseal = true;
                                 MainCanvas.ni.send(2425072);
                                 break;
                              case -1610612634:
                                 equipTypeForServer = 21;
                                 UIGrid.fromEquipUnseal = true;
                                 MainCanvas.ni.send(2425072);
                                 break;
                              case 2031617:
                                 if (pic.isFocus()) {
                                    PCUnsealGemCarve.m_bExamine = PCUnsealGemCarve.m_bPrimaryGemIndex;
                                 } else if (pic.isFocus()) {
                                    PCUnsealGemCarve.m_bExamine = PCUnsealGemCarve.m_bCarveSymbolIndex;
                                 }

                                 MainCanvas.ni.send(2031635);
                                 break;
                              case 2031618:
                                 if (PCUnsealGemCarve.m_bPrimaryGemIndex == -1) {
                                    UITopForm.createLocalTopForm((byte)0, (String)"Xin chọn trang bị cần khai phong.", "Xác nhận", "", -1, -2);
                                    super.isShowMenu = false;
                                    return;
                                 }

                                 if (PCUnsealGemCarve.m_bCarveSymbolIndex == -1) {
                                    UITopForm.createLocalTopForm((byte)0, (String)"Xin chọn Quyển Khai Phong để khai phong trang bị.", "Xác nhận", "", -1, -2);
                                    super.isShowMenu = false;
                                    return;
                                 }

                                 MainCanvas.ni.send(2031618);
                                 break;
                              case 2031648:
                                 MainCanvas.ni.send(2031648);
                              }

                              super.isShowMenu = false;
                           } else {
                              byte i;
                              if (MainCanvas.curForm.clientCommand == 3342356) {
                                 pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                                 pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(6);
                                 nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(14);
                                 i = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                                 switch(i) {
                                 case -1610612707:
                                    if (pic.isFocus()) {
                                       pic.setFocus(false, MainCanvas.curForm);
                                    } else if (pic.isFocus()) {
                                       pic.setFocus(false, MainCanvas.curForm);
                                    }

                                    grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(9);
                                    grid.setFocus(true, MainCanvas.curForm);
                                    break;
                                 case -1610612704:
                                    nameLabel.setTextColor(Cons.COLOR_FONT_1);
                                    nameLabel.setText("Đặt pháp bảo cần khắc danh tại đây");

                                    for(i = 0; i < UIGrid.AuctionBag.length; ++i) {
                                       UIGrid.AuctionBag[i] = -1;
                                    }

                                    if (pic.isFocus()) {
                                       pic.setShowNum(false);
                                       pic.isWpPic = false;
                                       pic.setImg((MImage)null);
                                       PCUnsealGemCarve.m_bPrimaryGemIndex = -1;
                                       nameLabel.setTextColor(Cons.COLOR_FONT_1);
                                       nameLabel.setText("Đặt pháp bảo cần khắc danh tại đây");
                                    } else if (pic.isFocus()) {
                                       pic.setShowNum(false);
                                       pic.isWpPic = false;
                                       pic.setImg((MImage)null);
                                       PCUnsealGemCarve.m_bCarveSymbolIndex = -1;
                                       nameLabel.setTextColor(Cons.COLOR_FONT_1);
                                       nameLabel.setText("Đặt Hoa Văn Pháp Bảo tại đây");
                                    }
                                    break;
                                 case 2031617:
                                    if (pic.isFocus()) {
                                       PCUnsealGemCarve.m_bExamine = PCUnsealGemCarve.m_bPrimaryGemIndex;
                                       i = UIGrid.severGridIndex;
                                       UIGrid.severGridIndex = PCUnsealGemCarve.m_bPrimaryGemIndex;
                                       PCTreasure.unlockMenu = false;
                                       MainCanvas.ni.send(3342337);
                                       UIGrid.severGridIndex = i;
                                    } else if (pic.isFocus()) {
                                       PCUnsealGemCarve.m_bExamine = PCUnsealGemCarve.m_bCarveSymbolIndex;
                                       MainCanvas.ni.send(2031635);
                                    }
                                    break;
                                 case 3342359:
                                    if (PCUnsealGemCarve.m_bPrimaryGemIndex == -1) {
                                       UITopForm.createLocalTopForm((byte)0, (String)"Chưa đặt pháp bảo vào, không thể tiến hành khắc danh.", "Xác nhận", "", -1, -2);
                                       super.isShowMenu = false;
                                       return;
                                    }

                                    if (PCUnsealGemCarve.m_bCarveSymbolIndex == -1) {
                                       UITopForm.createLocalTopForm((byte)0, (String)"Chưa bỏ vào pháp bảo khắc danh, không thể tiến hành khắc danh.", "Xác nhận", "", -1, -2);
                                       super.isShowMenu = false;
                                       return;
                                    }

                                    MainCanvas.ni.send(3342359);
                                    break;
                                 case 3342361:
                                    MainCanvas.ni.send(3342361);
                                 }

                                 super.isShowMenu = false;
                              } else if (MainCanvas.curForm.clientCommand == 917525) {
                                 pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                                 pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(6);
                                 nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(14);
                                 i = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                                 switch(i) {
                                 case -1610612707:
                                    if (pic.isFocus()) {
                                       pic.setFocus(false, MainCanvas.curForm);
                                    } else if (pic.isFocus()) {
                                       pic.setFocus(false, MainCanvas.curForm);
                                    }

                                    grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(9);
                                    grid.setFocus(true, MainCanvas.curForm);
                                    break;
                                 case -1610612704:
                                    nameLabel.setTextColor(Cons.COLOR_FONT_1);
                                    nameLabel.setText("Đặt trang bị cần cường hóa tại đây");

                                    for(i = 0; i < UIGrid.AuctionBag.length; ++i) {
                                       UIGrid.AuctionBag[i] = -1;
                                    }

                                    if (pic.isFocus()) {
                                       pic.setShowNum(false);
                                       pic.isWpPic = false;
                                       pic.setImg((MImage)null);
                                       PCUnsealGemCarve.m_bPrimaryGemIndex = -1;
                                       nameLabel.setTextColor(Cons.COLOR_FONT_1);
                                       nameLabel.setText("Đặt trang bị cần cường hóa tại đây");
                                    } else if (pic.isFocus()) {
                                       pic.setShowNum(false);
                                       pic.isWpPic = false;
                                       pic.setImg((MImage)null);
                                       PCUnsealGemCarve.m_bCarveSymbolIndex = -1;
                                       nameLabel.setTextColor(Cons.COLOR_FONT_1);
                                       nameLabel.setText("Đặt Phù Cường Hóa tại đây");
                                    }
                                    break;
                                 case 917528:
                                    MainCanvas.ni.send(917528);
                                    break;
                                 case 917530:
                                    MainCanvas.ni.send(917530);
                                    break;
                                 case 2031617:
                                    if (pic.isFocus()) {
                                       PCUnsealGemCarve.m_bExamine = PCUnsealGemCarve.m_bPrimaryGemIndex;
                                    } else if (pic.isFocus()) {
                                       PCUnsealGemCarve.m_bExamine = PCUnsealGemCarve.m_bCarveSymbolIndex;
                                    }

                                    MainCanvas.ni.send(2031635);
                                 }

                                 super.isShowMenu = false;
                              } else if (MainCanvas.curForm.clientCommand == 2228225) {
                                 cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                                 pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                                 switch(cmd) {
                                 case -1610612707:
                                    if (pic.isFocus()) {
                                       PCGemJoinOrRemove.m_bFocus = 1;
                                       pic.setFocus(false, MainCanvas.curForm);
                                    }

                                    UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(7);
                                    grid.setFocus(true, MainCanvas.curForm);
                                    break;
                                 case 2031650:
                                    MainCanvas.ni.send(2031650);
                                    break;
                                 case 2228227:
                                    if (PCGemJoinOrRemove.m_bGemIndex == -1) {
                                       UITopForm.createLocalTopForm((byte)0, (String)"Xin cho nguyên thạch vào để tiến hành hợp thành.", "Xác nhận", "", -1, -2);
                                       super.isShowMenu = false;
                                       return;
                                    }

                                    MainCanvas.ni.send(2228227);
                                    break;
                                 case 2228231:
                                    if (pic.isFocus()) {
                                       PCGemJoinOrRemove.m_bExamine = PCGemJoinOrRemove.m_bGemIndex;
                                    }

                                    MainCanvas.ni.send(2228231);
                                    break;
                                 case 2228240:
                                    MainCanvas.ni.send(2228240);
                                 }

                                 super.isShowMenu = false;
                              } else {
                                 UIPicture picture;
                                 if (MainCanvas.curForm.clientCommand == 1966097) {
                                    cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                                    pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                                    picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(6);
                                    UILabel nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(10);
                                    switch(cmd) {
                                    case -1610612707:
                                       if (pic.isFocus()) {
                                          pic.setFocus(false, MainCanvas.curForm);
                                          PCGem.m_bFocus = 1;
                                       } else if (picture.isFocus()) {
                                          PCGem.m_bFocus = 2;
                                          picture.setFocus(false, MainCanvas.curForm);
                                       }

                                       grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(7);
                                       grid.setFocus(true, MainCanvas.curForm);
                                       break;
                                    case -1610612704:
                                       for(i = 0; i < UIGrid.AuctionBag.length; ++i) {
                                          UIGrid.AuctionBag[i] = -1;
                                       }

                                       if (pic.isFocus()) {
                                          pic.setShowNum(false);
                                          pic.isWpPic = false;
                                          pic.setImg((MImage)null);
                                          PCGem.m_bEquipIndex = -1;
                                          nameLabel.setTextColor(Cons.COLOR_FONT_1);
                                          nameLabel.setText("Đặt trang bị tại đây");
                                       } else if (picture.isFocus()) {
                                          picture.setShowNum(false);
                                          picture.isWpPic = false;
                                          picture.setImg((MImage)null);
                                          PCGem.m_bGemIndex = -1;
                                          nameLabel.setTextColor(Cons.COLOR_FONT_1);
                                          nameLabel.setText("Đặt Phù Giải Trừ tại đây");
                                       }
                                       break;
                                    case 1966099:
                                       if (PCGem.m_bEquipIndex == -1) {
                                          if (MainCanvas.backForms.size() > 0) {
                                             MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
                                          }

                                          UITopForm.createLocalTopForm((byte)0, (String)"Xin cho trang bị vào để tiến hành bảo thạch giải trừ.", "Xác nhận", "", -1, -2);
                                          super.isShowMenu = false;
                                          return;
                                       }

                                       MainCanvas.ni.send(1966099);
                                       break;
                                    case 1966113:
                                       if (pic.isFocus()) {
                                          PCGem.m_bExamine = PCGem.m_bEquipIndex;
                                       } else if (picture.isFocus()) {
                                          PCGem.m_bExamine = PCGem.m_bGemIndex;
                                       }

                                       MainCanvas.ni.send(1966113);
                                       break;
                                    case 2031651:
                                       MainCanvas.ni.send(2031651);
                                    }

                                    super.isShowMenu = false;
                                 } else if (MainCanvas.curForm.clientCommand == 458760) {
                                    cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                                    PCPackage.extendIndex = PCPackage.getExtendIndex();
                                    MainCanvas.ni.send(cmd);
                                    super.isShowMenu = false;
                                 } else if (MainCanvas.curForm.clientCommand == 1179658) {
                                    cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                                    PCPackage.extendIndex = PCStorage.getExtendIndex();
                                    equipTypeForServer = 22;
                                    MainCanvas.ni.send(cmd);
                                    super.isShowMenu = false;
                                 } else if (MainCanvas.curForm.clientCommand == 2555905) {
                                    cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());

                                    for(i = 0; i < 12; ++i) {
                                       picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 11);
                                       if (picture.isFocus()) {
                                          PCFarm.m_bGlebeKind = 0;
                                          PCFarm.m_bGlebeIndex = (byte)i;
                                       }
                                    }

                                    for(i = 0; i < 6; ++i) {
                                       picture = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 25);
                                       if (picture.isFocus()) {
                                          PCFarm.m_bGlebeKind = 1;
                                          PCFarm.m_bGlebeIndex = (byte)i;
                                       }
                                    }

                                    PCFarm.m_bMenuIndex = (Byte)PCFarm.menuvector.elementAt(this.getMenu().getIndex());
                                    if ((PCFarm.m_bMenuIndex == 0 || PCFarm.m_bMenuIndex == 10 || PCFarm.m_bMenuIndex == 4) && PCFarm.m_bMenuIndex == 4) {
                                       PCFarm.m_bBacking = 1;
                                    }

                                    MainCanvas.ni.send(cmd);
                                    super.isShowMenu = false;
                                 } else if (MainCanvas.curForm.clientCommand == 2555906) {
                                    if (PCFarm.m_bWhetherGetHome == 0) {
                                       cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                                       MainCanvas.ni.send(cmd);
                                       super.isShowMenu = false;
                                    }
                                 } else {
                                    UIPicture[] picture;
                                    int[] index;
                                    if (MainCanvas.curForm.clientCommand != 3145729 && MainCanvas.curForm.clientCommand != 3145730) {
                                       if (MainCanvas.curForm.clientCommand == 2228256) {
                                          UILabel nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(14);
                                          UILabel nameLabel_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(11);
                                          cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                                          UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                                          switch(cmd) {
                                          case -1610612707:
                                             if (pic.isFocus()) {
                                                pic.setFocus(false, MainCanvas.curForm);
                                             }

                                             grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(7);
                                             grid.setFocus(true, MainCanvas.curForm);
                                             break;
                                          case -1610612704:
                                             if (pic.isFocus()) {
                                                pic.setShowNum(false);
                                                pic.isWpPic = false;
                                                pic.setImg((MImage)null);
                                                PCGemJoinOrRemove.m_bGemIndex = -1;
                                                nameLabel_1.setText("0");
                                                nameLabel.setTextColor(Cons.COLOR_FONT_1);
                                                nameLabel.setText("Đặt Bảo Thạch tại đây");
                                             }
                                             break;
                                          case 2228231:
                                             if (pic.isFocus()) {
                                                PCGemJoinOrRemove.m_bExamine = PCGemJoinOrRemove.m_bGemIndex;
                                             }

                                             MainCanvas.ni.send(2228231);
                                             break;
                                          case 2228258:
                                             if (PCGemJoinOrRemove.m_bGemIndex == -1) {
                                                UITopForm.createLocalTopForm((byte)0, (String)"Xin nhập bảo thạch tiến hành hoàn nguyên.", "Xác nhận", "", -1, -2);
                                                super.isShowMenu = false;
                                                return;
                                             }

                                             MainCanvas.ni.send(2228258);
                                             break;
                                          case 2228260:
                                             MainCanvas.ni.send(2228260);
                                          }

                                          super.isShowMenu = false;
                                       } else if (MainCanvas.curForm.clientCommand == 3342340 && PCTreasure.unlockMenu) {
                                          cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                                          index = new int[]{3, 5, 7, 9, 11};
                                          picture = new UIPicture[index.length];

                                          for(i = 0; i < index.length; ++i) {
                                             picture[i] = (UIPicture)MainCanvas.curForm.getComponents().elementAt(index[i]);
                                             if (picture[i].isFocus()) {
                                                PCTreasure.pictureIndex = (byte)i;
                                             }
                                          }

                                          if (cmd == 3342341) {
                                             PCTreasure.tally = 0;
                                          }

                                          MainCanvas.ni.send(cmd);
                                          super.isShowMenu = false;
                                       }
                                    } else {
                                       cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                                       index = new int[]{3, 4, 5, 6, 7, 8, 9, 10};
                                       picture = new UIPicture[index.length];

                                       for(i = 0; i < index.length; ++i) {
                                          picture[i] = (UIPicture)MainCanvas.curForm.getComponents().elementAt(index[i]);
                                          if (picture[i].isFocus()) {
                                             PCHang.m_bSkillIndex = (byte)i;
                                          }
                                       }

                                       switch(cmd) {
                                       case -1610612730:
                                          if (MainCanvas.curForm.clientCommand == 3145729) {
                                             UIList.intoSkillDetailUI(-1610612730, Player.getInstance().getIND_FromSkillData(PCHang.AttackSkill[PCHang.m_bSkillIndex]));
                                          } else if (MainCanvas.curForm.clientCommand == 3145730) {
                                             UIList.intoSkillDetailUI(-1610612730, Player.getInstance().getIND_FromSkillData(PCHang.WatchSkill[PCHang.m_bSkillIndex]));
                                          }
                                          break;
                                       case -1610612620:
                                          UIRightMenu.HangSkill(-1610612620);
                                          break;
                                       case -1610612619:
                                          this.setShowNum(false);
                                          this.isWpPic = false;
                                          this.setImg((MImage)null);
                                          if (MainCanvas.curForm.clientCommand == 3145729) {
                                             PCHang.AttackSkill[PCHang.m_bSkillIndex] = -1;
                                          } else {
                                             if (MainCanvas.curForm.clientCommand != 3145730) {
                                                break;
                                             }

                                             byte arithmometer = 0;
                                             byte num = (byte)PCHang.WatchSkill.length;
                                             PCHang.WatchSkill[PCHang.m_bSkillIndex] = -1;
                                             i = 0;

                                             for(; i < num; ++i) {
                                                if (PCHang.WatchSkill[i] == -1) {
                                                   ++arithmometer;
                                                }
                                             }

                                             if (arithmometer == num) {
                                                PCHang.SkillTime = 0;
                                                UIRadio radio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(13);
                                                radio.setSureIndex(PCHang.SkillTime);
                                                radio.setSelectIndex(PCHang.SkillTime);
                                             }
                                          }
                                          break;
                                       case -1610612618:
                                          UIRightMenu.HangSkill(-1610612618);
                                       }

                                       super.isShowMenu = false;
                                    }
                                 }
                              }
                           }
                        }
                     } else {
                        cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                        PCMail.selectedAttIndex = this.attIndex;
                        UIGrid.getStuffWordColor(this.getEquipColorLevel());
                        if (cmd == 1703952) {
                           PCMail.m_bWhetherRestore = true;
                           MainCanvas.ni.send(cmd);
                           super.isShowMenu = false;
                        } else if (cmd != -1) {
                           MainCanvas.ni.send(cmd);
                           super.isShowMenu = false;
                        }
                     }
                  } else {
                     cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                     if (this.wpIndex != -1) {
                        if (this.wpIndex != -1) {
                           switch(cmd) {
                           case -1610612712:
                              PCMail.m_bAccessoriesAmount = 0;
                              UIGrid.EliminateMail();
                              PCMail.Postage();
                              break;
                           case -1610612711:
                              PCMail.SendMailValidity();
                              break;
                           case -1610612710:
                              this.quality = 0;
                              this.isWpPic = false;
                              this.wpIndex = -1;
                              this.setImg((MImage)null);
                              UIGrid.Eliminate();
                              PCMail.Postage();
                              break;
                           default:
                              MainCanvas.ni.send(cmd);
                           }
                        }
                     } else {
                        switch(cmd) {
                        case -1610612712:
                           PCMail.m_bAccessoriesAmount = 0;
                           UIGrid.EliminateMail();
                           PCMail.Postage();
                           break;
                        case -1610612711:
                           PCMail.SendMailValidity();
                           break;
                        case 1703946:
                           PCMail.m_bAccessoriesAmount = 0;

                           for(i = 0; i < 6; ++i) {
                              for(cmd = 0; cmd < 4; ++cmd) {
                                 UIGrid.accessories[i][cmd] = PCMail.Accessory[i][cmd];
                              }
                           }

                           for(i = 0; i < UIGrid.accessories.length; ++i) {
                              if (UIGrid.accessories[i][0] != -1 && PCMail.m_bAccessoriesAmount < UIGrid.accessories.length) {
                                 ++PCMail.m_bAccessoriesAmount;
                              }
                           }

                           MainCanvas.ni.send(1703946);
                           break;
                        default:
                           MainCanvas.ni.send(cmd);
                        }
                     }

                     super.isShowMenu = false;
                  }
               }
            }
         } else if (MainCanvas.isInputDown(65536) || MainCanvas.isInputDown(131072)) {
            if (super.type == 3) {
               this.addEquipPicMenu();
            } else if (super.type == 1) {
               this.addFramePicMenu();
            } else if (super.type == 0 && MainCanvas.getState() == 17) {
               if (MainCanvas.playPicture[MainCanvas.m_bChoose].otherP != null) {
                  String str = "Đang vào, xin hãy chờ...";
                  MainCanvas.TopForm((byte)0, str, "", "", -120, -120);
                  MainCanvas.ni.send(65539);
                  MainCanvas.mc.initSave(MainCanvas.m_sCharacterName[MainCanvas.m_bChoose]);
                  MainCanvas.mc.m_bconfirm[MainCanvas.m_bChooseRen] = 1;
                  if (MainCanvas.mc.m_bPlace[MainCanvas.m_bChooseRen] == 1 && MainCanvas.mc.m_bconfirm[MainCanvas.m_bChooseRen] == 1) {
                     MainCanvas.mc.Debarkation = true;
                  } else if (MainCanvas.mc.m_bconfirm[MainCanvas.m_bChooseRen] == 1) {
                     MainCanvas.mc.Debarkation = false;
                  }
               } else {
                  MainCanvas.Cleanup_part();
                  MainCanvas.random_part();
                  MainCanvas.setState((byte)18);
               }
            }
         }

      }
   }

   public byte getEquipPicType() {
      return this.equipPicType;
   }

   public void setEquipPicType(byte equipPicType) {
      this.equipPicType = equipPicType;
   }

   public static void clearEquipKuang() {
      try {
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(9)).setSelected(false);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(10)).setSelected(false);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(17)).setSelected(false);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(16)).setSelected(false);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(19)).setSelected(false);
         ((UIPicture)MainCanvas.curForm.getComponents().elementAt(18)).setSelected(false);
      } catch (Exception var1) {
      }

   }

   public static boolean isNeedEquip() {
      return ((UIPicture)MainCanvas.curForm.getComponents().elementAt(9)).isSelected() || ((UIPicture)MainCanvas.curForm.getComponents().elementAt(10)).isSelected() || ((UIPicture)MainCanvas.curForm.getComponents().elementAt(17)).isSelected() || ((UIPicture)MainCanvas.curForm.getComponents().elementAt(16)).isSelected() || ((UIPicture)MainCanvas.curForm.getComponents().elementAt(19)).isSelected() || ((UIPicture)MainCanvas.curForm.getComponents().elementAt(18)).isSelected();
   }

   public void addEquipPicMenu() {
      switch(super.clientType) {
      case 16:
         label167:
         switch(this.equipPicType) {
         case 0:
            UIMenu menu;
            int[] menuCommand;
            int[] inns;
            String[] strs;
            switch(MainCanvas.curForm.clientCommand) {
            case 458817:
               if (this.equipIndex != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = (int[])null;
                  if (equipTypeForServer != 14 && equipTypeForServer != 15) {
                     menuCommand = new int[]{917507, 2162754};
                  } else {
                     menuCommand = new int[]{3342338, 2162754};
                  }

                  inns = new int[]{2, 3};
                  strs = new String[]{"Tra Xem", "Ngưng nguyên"};
                  menu.addMenu(strs, menuCommand, inns);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }
               break label167;
            case 939349:
               if (this.equipIndex != -1) {
                  if (PCEquip.m_bPropIndex != -1) {
                     menu = new UIMenu((byte)0);
                     menuCommand = (int[])null;
                     if (equipTypeForServer != 14 && equipTypeForServer != 15) {
                        menuCommand = new int[]{917524, 917507};
                     } else {
                        menuCommand = new int[]{917524, 3342338};
                     }

                     inns = new int[]{3, 2};
                     strs = new String[]{"Sửa", "Tra xem"};
                     menu.addMenu(strs, menuCommand, inns);
                     this.setMenu(menu);
                     super.isShowMenu = true;
                     this.getMenu().resetIndex();
                  } else {
                     menu = new UIMenu((byte)0);
                     menuCommand = new int[]{917507};
                     inns = new int[]{2};
                     strs = new String[]{"Tra xem"};
                     menu.addMenu(strs, menuCommand, inns);
                     this.setMenu(menu);
                     super.isShowMenu = true;
                     this.getMenu().resetIndex();
                  }
               }
               break label167;
            case 1966086:
               if (this.equipIndex != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = (int[])null;
                  if (equipTypeForServer != 14 && equipTypeForServer != 15) {
                     menuCommand = new int[]{1966087, 1966093};
                  } else {
                     menuCommand = new int[]{1966087, 3342338};
                  }

                  inns = new int[]{2, 2};
                  menu.addMenu(Cons.MENU_GEM_SETTING, menuCommand, inns);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }
               break label167;
            default:
               if (this.isFocus() && this.isSelected()) {
                  MainCanvas.ni.send(917509);
                  clearEquipKuang();
                  return;
               }

               if (this.equipIndex != -1) {
                  menu = new UIMenu((byte)0);
                  if (MainCanvas.m_bTourist == 0 && equipTypeForServer != 14 && equipTypeForServer != 15) {
                     if (MainCanvas.curForm.clientCommand == 458755) {
                        menu.addMenu("Mua", 2425072, 3);
                     } else {
                        menu.addMenu("Mua", 2425072, 1);
                     }
                  }

                  if (equipTypeForServer != 14 && equipTypeForServer != 15) {
                     menu.addMenu("Tra xem", 917507, 2);
                  } else if (MainCanvas.curForm.clientCommand == 458755) {
                     menu.addMenu("Tra xem", 3342338, 3);
                  } else {
                     menu.addMenu("Tra xem", 3342338, 1);
                  }

                  menu.addMenu("Tháo ", 917506, 3);
                  if (equipTypeForServer != 14 && equipTypeForServer != 15) {
                     menu.addMenu("So sánh", 1245185, 2);
                  }

                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               } else if (MainCanvas.m_bTourist == 0 && equipTypeForServer != 14 && equipTypeForServer != 15) {
                  menu = new UIMenu((byte)0);
                  if (MainCanvas.curForm.clientCommand == 458755) {
                     menu.addMenu("Mua", 2425072, 3);
                  } else {
                     menu.addMenu("Mua", 2425072, 1);
                  }

                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }
               break label167;
            }
         case 1:
            if (this.equipIndex != -1) {
               UIForm.setCompareTI((byte)1, this.equipType);
            }
            break;
         case 2:
            if (this.equipIndex != -1) {
               MainCanvas.ni.send(393231);
               UIMenu.formSaveForm();
            } else {
               UITopForm.createLocalTopForm((byte)0, (String)"Chưa có trang bị", "Xác nhận", "", -1, -2);
            }
         }
      case 115:
         switch(this.equipPicType) {
         case 0:
            switch(MainCanvas.curForm.clientCommand) {
            case 917520:
            case 1638431:
               boolean isInClan = false;
               if (MainCanvas.curForm.clientCommand == 1638431) {
                  isInClan = true;
               }

               int[] inns;
               String[] menuStr;
               int[] menuCommand;
               UIMenu menu;
               if (this.equipIndex != -1) {
                  menu = new UIMenu((byte)0);
                  menuStr = new String[]{"Sửa 1 cái", "Sửa hết", "Tra xem"};
                  menuCommand = (int[])null;
                  if (equipTypeForServer != 14 && equipTypeForServer != 15) {
                     menuCommand = new int[]{isInClan ? 1638432 : 917521, isInClan ? 1638433 : 917522, 917507};
                  } else {
                     menuCommand = new int[]{isInClan ? 1638432 : 917521, isInClan ? 1638433 : 917522, 3342338};
                  }

                  inns = new int[]{3, 3, 2};
                  menu.addMenu(menuStr, menuCommand, inns);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               } else {
                  menu = new UIMenu((byte)0);
                  menuStr = new String[]{"Sửa hết"};
                  menuCommand = new int[]{isInClan ? 1638433 : 917522};
                  inns = new int[]{3};
                  menu.addMenu(menuStr, menuCommand, inns);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }
            }
         }
      }

      if (MainCanvas.curForm.clientCommand == 2097155 && !PCGamble.Begin_1) {
         UIPicture picture_1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(31);
         UIPicture picture_2 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(33);
         PCGamble.rebound = true;
         if (picture_1.isFocus()) {
            PCGamble.BigOrSmall = 1;
            picture_1.setFocus(false, MainCanvas.curForm);
            MainCanvas.ni.send(2097154);
         } else if (picture_2.isFocus()) {
            PCGamble.BigOrSmall = 2;
            picture_2.setFocus(false, MainCanvas.curForm);
            MainCanvas.ni.send(2097154);
         }
      }

   }

   private void addFramePicMenu() {
      UIMenu menu;
      int[] menuCommand;
      int[] menuCommand;
      String[] strs;
      if (this.getType != -1) {
         menu = new UIMenu((byte)0);
         menuCommand = (int[])null;
         menuCommand = (int[])null;
         strs = (String[])null;
         if (this.getType == 0 && UIList.canReturn) {
            if (this.canViewPic()) {
               strs = new String[]{"Hoàn thành nhiệm vụ", "Chọn phần thưởng", "Tra xem vật phẩm"};
               menuCommand = new int[]{262150, 0, 262156};
               menuCommand = new int[]{0, 3, 2};
               menu.addMenu(strs, menuCommand, menuCommand);
            } else {
               strs = new String[]{"Hoàn thành nhiệm vụ", "Chọn phần thưởng"};
               menuCommand = new int[]{262150, 0};
               menuCommand = new int[]{0, 3};
               menu.addMenu(strs, menuCommand, menuCommand);
            }
         }

         if (menu.getItems().size() > 0) {
            this.setMenu(menu);
            super.isShowMenu = true;
            this.getMenu().resetIndex();
         }
      } else {
         switch(super.clientType) {
         case 34:
            if (isMyTradeLock && isOtherTradeLock) {
               if (this.wpIndex != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{1376267, 1376266};
                  menuCommand = new int[]{3, 3};
                  menu.addMenu(Cons.MENU_TRADE_SELF_FINISH, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               } else {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{1376266};
                  menuCommand = new int[]{3};
                  menu.addMenu(Cons.MENU_TRADE_OTHER_NULL_FINISH, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }
            } else if (isMyTradeLock && !isOtherTradeLock) {
               if (this.wpIndex != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{1376267};
                  menuCommand = new int[]{3};
                  strs = new String[]{"Tra xem"};
                  menu.addMenu(strs, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }
            } else if (!isMyTradeLock) {
               if (this.wpIndex != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{1376267, 1376268, 1376264, 1376265};
                  menuCommand = new int[]{3, 3, 3, 3};
                  menu.addMenu(Cons.MENU_TRADE_SELF_WP_STR, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               } else {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{1376263, 1376264, 1376265};
                  menuCommand = new int[]{2, 3, 3};
                  menu.addMenu(Cons.MENU_TRADE_SELF_NONE_STR, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }
            }
            break;
         case 35:
            if (isOtherTradeLock && isMyTradeLock) {
               if (this.wpIndex != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{1376267, 1376266};
                  menuCommand = new int[]{3, 3};
                  menu.addMenu(Cons.MENU_TRADE_SELF_FINISH, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }
            } else if (this.wpIndex != -1) {
               menu = new UIMenu((byte)0);
               menuCommand = new int[]{1376267};
               menuCommand = new int[]{3};
               strs = new String[]{"Tra xem"};
               menu.addMenu(strs, menuCommand, menuCommand);
               this.setMenu(menu);
               super.isShowMenu = true;
               this.getMenu().resetIndex();
            }
         }
      }

      if (MainCanvas.curForm.clientCommand != 1703937 && MainCanvas.curForm.clientCommand != 1703952 && MainCanvas.curForm.clientCommand != 1703938) {
         if (MainCanvas.curForm.clientCommand != 1703940 && MainCanvas.curForm.clientCommand != 1703941) {
            if (MainCanvas.curForm.clientCommand == 1900547) {
               if (PCAuction.m_bEntityGoods == 1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{-1610612713, -1610612714};
                  menuCommand = new int[]{3, 3};
                  menu.addMenu(Cons.AUCTION_SALE_SUBMENU, menuCommand, menuCommand);
                  this.setMenu(menu);
                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               }
            } else {
               UIPicture pic;
               UIPicture pic1;
               if (MainCanvas.curForm.clientCommand == 2031621) {
                  pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                  pic1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(6);
                  if (pic.isFocus()) {
                     this.GemcarveMenu(PCUnsealGemCarve.m_bPrimaryGemIndex);
                  } else if (pic1.isFocus()) {
                     this.GemcarveMenu(PCUnsealGemCarve.m_bCarveSymbolIndex);
                  }

                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               } else if (MainCanvas.curForm.clientCommand == 2031636) {
                  pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                  pic1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(6);
                  if (pic.isFocus()) {
                     this.UnsealSubmenu(PCUnsealGemCarve.m_bPrimaryGemIndex, (byte)1);
                  } else if (pic1.isFocus()) {
                     this.UnsealSubmenu(PCUnsealGemCarve.m_bCarveSymbolIndex, (byte)2);
                  }

                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               } else if (MainCanvas.curForm.clientCommand == 3342356) {
                  pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                  pic1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(6);
                  if (pic.isFocus()) {
                     this.TreasureStrengthenSubMenu(PCUnsealGemCarve.m_bPrimaryGemIndex);
                  } else if (pic1.isFocus()) {
                     this.TreasureStrengthenSubMenu(PCUnsealGemCarve.m_bCarveSymbolIndex);
                  }

                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               } else if (MainCanvas.curForm.clientCommand == 917525) {
                  pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                  pic1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(6);
                  if (pic.isFocus()) {
                     this.EquipStrengthenSubMenu(PCUnsealGemCarve.m_bPrimaryGemIndex);
                  } else if (pic1.isFocus()) {
                     this.EquipStrengthenSubMenu(PCUnsealGemCarve.m_bCarveSymbolIndex);
                  }

                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               } else if (MainCanvas.curForm.clientCommand == 2228225) {
                  pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                  if (pic.isFocus()) {
                     this.GemSynthesizeMenu(PCGemJoinOrRemove.m_bGemIndex);
                  }

                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               } else if (MainCanvas.curForm.clientCommand == 1966097) {
                  pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                  pic1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(6);
                  if (pic.isFocus()) {
                     this.GemUnchainMenu(PCGem.m_bEquipIndex);
                  } else if (pic1.isFocus()) {
                     this.GemUnchainMenu(PCGem.m_bGemIndex);
                  }

                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               } else if (MainCanvas.curForm.clientCommand == 458760) {
                  menu = new UIMenu((byte)0);
                  if (PCPackage.needExtend && this.isExtendPic) {
                     menu.addMenu("Mở rộng", 458769, 3);
                  }

                  menu.addMenu("Tra xem", 458770, 2);
                  this.setMenu(menu);
                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               } else if (MainCanvas.curForm.clientCommand == 1179658) {
                  menu = new UIMenu((byte)0);
                  if (PCPackage.needExtend && this.isExtendPic) {
                     menu.addMenu("Mở rộng", 1179661, 2);
                  } else {
                     menu.addMenu("Mua", 2425072, 1);
                  }

                  menu.addMenu("Tra xem", 1179662, 2);
                  this.setMenu(menu);
                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               } else if (MainCanvas.curForm.clientCommand == 2555905) {
                  this.FarmMenuManipulate();
               } else if (MainCanvas.curForm.clientCommand == 2555906) {
                  if (PCFarm.m_bWhetherGetHome == 0) {
                     menu = new UIMenu((byte)0);
                     menuCommand = new int[]{2555959, 2555960, 2555961};
                     menuCommand = new int[]{3, 3, 3};
                     strs = new String[]{"Xem", "Hủy bỏ", "Thêm"};
                     menu.addMenu(strs, menuCommand, menuCommand);
                     this.setMenu(menu);
                     this.getMenu().resetIndex();
                     super.isShowMenu = true;
                  } else if (PCFarm.m_bWhetherGetHome == 1) {
                     MainCanvas.ni.send(2555959);
                  }
               } else if (MainCanvas.curForm.clientCommand != 3145729 && MainCanvas.curForm.clientCommand != 3145730) {
                  if (MainCanvas.curForm.clientCommand == 2228256) {
                     pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
                     if (pic.isFocus()) {
                        this.GemDeoxidizeMenu(PCGemJoinOrRemove.m_bGemIndex);
                     }

                     this.getMenu().resetIndex();
                     super.isShowMenu = true;
                  } else if (MainCanvas.curForm.clientCommand == 3342340 && PCTreasure.unlockMenu) {
                     menu = new UIMenu((byte)0);
                     String[] menuStr = new String[]{"Học kỹ năng", "Giải Phong Kỹ Năng"};
                     menuCommand = new int[]{3342341, 3342343};
                     int[] inns = new int[]{1, 3};
                     menu.addMenu(menuStr, menuCommand, inns);
                     this.setMenu(menu);
                     super.isShowMenu = true;
                     this.getMenu().resetIndex();
                  }
               } else {
                  menu = new UIMenu((byte)0);
                  if (this.getImg() == null) {
                     menuCommand = new int[]{MainCanvas.curForm.clientCommand == 3145729 ? -1610612620 : -1610612618};
                     menuCommand = new int[]{2};
                     strs = new String[]{"Thiết lập kỹ năng"};
                     menu.addMenu(strs, menuCommand, menuCommand);
                     this.setMenu(menu);
                  } else {
                     menuCommand = new int[]{MainCanvas.curForm.clientCommand == 3145729 ? -1610612620 : -1610612618, -1610612619, -1610612730};
                     menuCommand = new int[]{2, 3, 2};
                     strs = new String[]{"Thiết lập kỹ năng", "Xóa phím này", "Thông tin kỹ năng"};
                     menu.addMenu(strs, menuCommand, menuCommand);
                     this.setMenu(menu);
                  }

                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }
            }
         } else if (this.picID == 0) {
            if (MainCanvas.curForm.clientCommand == 1703940) {
               menu = new UIMenu((byte)0);
               if (PCMail.receiveMoney > 0) {
                  menu.addMenu("Nhận vàng", 1703951, 3);
               }

               if (PCMail.haveAttach || PCMail.receiveMoney > 0) {
                  menu.addMenu("Nhận tất cả", 1703950, 3);
               }

               if (PCMail.canReturnMail) {
                  menu.addMenu("Trả lời thư", 1703952, 2);
               }

               if (menu.getItems().size() > 0) {
                  this.setMenu(menu);
                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               }
            } else if (MainCanvas.curForm.clientCommand == 1703941) {
               menu = new UIMenu((byte)0);
               if (!PCMail.isPay) {
                  menu.addMenu("Trả tiền", 1703953, 3);
                  menu.addMenu("Trả thư", 1703957, 0);
                  if (PCMail.canReturnMail) {
                     menu.addMenu("Trả lời thư", 1703952, 2);
                  }
               } else {
                  if (PCMail.haveAttach || PCMail.receiveMoney > 0) {
                     menu.addMenu("Nhận tất cả", 1703950, 3);
                  }

                  if (PCMail.canReturnMail) {
                     menu.addMenu("Trả lời thư", 1703952, 2);
                  }
               }

               if (menu.getItems().size() > 0) {
                  this.setMenu(menu);
                  this.getMenu().resetIndex();
                  super.isShowMenu = true;
               }
            }
         } else if (MainCanvas.curForm.clientCommand == 1703940) {
            menu = new UIMenu((byte)0);
            menu.addMenu("Tra xem vật phẩm", 1703948, 2);
            menu.addMenu("Nhận vật phẩm", 1703949, 3);
            if (PCMail.receiveMoney > 0) {
               menu.addMenu("Nhận tiền", 1703951, 3);
            }

            if (PCMail.haveAttach || PCMail.receiveMoney > 0) {
               menu.addMenu("Nhận hết", 1703950, 3);
            }

            if (PCMail.canReturnMail) {
               menu.addMenu("Hồi phục thư", 1703952, 2);
            }

            if (menu.getItems().size() > 0) {
               this.setMenu(menu);
               this.getMenu().resetIndex();
               super.isShowMenu = true;
            }
         } else if (MainCanvas.curForm.clientCommand == 1703941) {
            menu = new UIMenu((byte)0);
            if (!PCMail.isPay) {
               menu.addMenu("Tra xem vật phẩm", 1703948, 2);
               menu.addMenu("Trả tiền", 1703953, 3);
               menu.addMenu("Trả thư", 1703957, 0);
               if (PCMail.canReturnMail) {
                  menu.addMenu("Hồi phục thư", 1703952, 2);
               }
            } else {
               menu.addMenu("Tra xem vật phẩm", 1703948, 2);
               menu.addMenu("Nhận vật phẩm", 1703949, 3);
               if (PCMail.haveAttach || PCMail.receiveMoney > 0) {
                  menu.addMenu("Nhận hết", 1703950, 3);
               }

               if (PCMail.canReturnMail) {
                  menu.addMenu("Hồi phục thư", 1703952, 2);
               }
            }

            if (menu.getItems().size() > 0) {
               this.setMenu(menu);
               this.getMenu().resetIndex();
               super.isShowMenu = true;
            }
         }
      } else if (this.isFocus()) {
         if (this.wpIndex == -1) {
            menu = new UIMenu((byte)0);
            menuCommand = new int[]{1703946, -1610612712, -1610612711};
            menuCommand = new int[]{2, 3, 3};
            menu.addMenu(Cons.ACCESSORY, menuCommand, menuCommand);
            this.setMenu(menu);
            this.getMenu().resetIndex();
            super.isShowMenu = true;
         } else if (this.wpIndex != -1) {
            menu = new UIMenu((byte)0);
            menuCommand = new int[]{1703954, -1610612710, -1610612712, -1610612711};
            menuCommand = new int[]{2, 3, 3, 3};
            menu.addMenu(Cons.ACCESSORY1, menuCommand, menuCommand);
            this.setMenu(menu);
            this.getMenu().resetIndex();
            super.isShowMenu = true;
         }
      }

   }

   public byte getEquipType() {
      return this.equipType;
   }

   public void setEquipType(byte equipType) {
      this.equipType = equipType;
   }

   public boolean isSelected() {
      return this.isSelected;
   }

   public void setSelected(boolean isSelected) {
      this.isSelected = isSelected;
   }

   public void setQuality(int picID) {
      this.quality = (byte)(picID / 1000 - 1);
   }

   private boolean canViewPic() {
      return this.payType == 1;
   }

   public byte getEquipColorLevel() {
      return this.equipColorLevel;
   }

   public void setEquipColorLevel(byte equipColorLevel) {
      this.equipColorLevel = equipColorLevel;
   }

   public void setGOPic(int targetID) {
      if (GOManager.getCurrentTarget().getID() == targetID) {
         this.GOPic = ActiveGO.copyGameObject(GOManager.getCurrentTarget(), (byte)0);
      } else if (Player.getInstance().getID() == targetID) {
         this.GOPic = ActiveGO.copyGameObject(Player.getInstance(), (byte)0);
      } else {
         for(int i = GOManager.curGameObj.length - 1; i >= 0; --i) {
            if (GOManager.curGameObj[i].getID() == targetID) {
               this.GOPic = ActiveGO.copyGameObject(GOManager.curGameObj[i], (byte)0);
               return;
            }
         }

      }
   }

   private void GemcarveMenu(byte index) {
      UIMenu menu = new UIMenu((byte)0);
      int[] cmds;
      int[] inns;
      if (index == -1) {
         cmds = new int[]{-1610612707, 2031623, 2031649};
         inns = new int[]{3, 3, 2};
         menu.addMenu(Cons.MENU_GEMCARVE_1, cmds, inns);
      } else {
         cmds = new int[]{-1610612704, 2031635, 2031623, 2031649};
         inns = new int[]{3, 2, 3, 2};
         menu.addMenu(Cons.MENU_GEMCARVE_3, cmds, inns);
      }

      this.setMenu(menu);
   }

   private void UnsealSubmenu(byte index, byte type) {
      UIMenu menu = new UIMenu((byte)0);
      if (index == -1) {
         if (type == 2) {
            menu.addMenu("Mua", -1610612635, 1);
         }

         if (type == 3) {
            menu.addMenu("Mua", -1610612634, 1);
         }

         menu.addMenu("Chọn vật phẩm", -1610612707, 3);
         menu.addMenu("Khai phong trang bị", 2031618, 3);
         menu.addMenu("Giải thích khai phong", 2031648, 2);
      } else {
         if (type == 2) {
            menu.addMenu("Mua", -1610612635, 1);
         }

         if (type == 3) {
            menu.addMenu("Mua", -1610612634, 1);
         }

         menu.addMenu("Tái thiết lập vật phẩm", -1610612704, 3);
         menu.addMenu("Tìm chi tiết", 2031617, 2);
         menu.addMenu("Khai Phong Trang Bị", 2031618, 3);
         menu.addMenu("Giải thích khai phong", 2031648, 2);
      }

      this.setMenu(menu);
   }

   private void TreasureStrengthenSubMenu(byte index) {
      UIMenu menu = new UIMenu((byte)0);
      if (index == -1) {
         menu.addMenu("Chọn vật phẩm", -1610612707, 3);
         menu.addMenu("Khắc Danh Pháp Bảo", 3342359, 3);
         menu.addMenu("Giải thích khắc danh", 3342361, 2);
      } else {
         menu.addMenu("Tái thiết lập vật phẩm", -1610612704, 3);
         menu.addMenu("Tìm chi tiết", 2031617, 2);
         menu.addMenu("Khắc Danh Pháp Bảo", 3342359, 3);
         menu.addMenu("Giải thích khắc danh", 3342361, 2);
      }

      this.setMenu(menu);
   }

   private void EquipStrengthenSubMenu(byte index) {
      UIMenu menu = new UIMenu((byte)0);
      if (index == -1) {
         menu.addMenu("Chọn vật phẩm", -1610612707, 3);
         menu.addMenu("Cường hóa trang bị", 917528, 3);
         menu.addMenu("Giải thích cường hóa", 917530, 2);
      } else {
         menu.addMenu("Tái thiết lập vật phẩm", -1610612704, 3);
         menu.addMenu("Tìm chi tiết", 2031617, 2);
         menu.addMenu("Cường hóa trang bị", 917528, 3);
         menu.addMenu("Giải thích cường hóa", 917530, 2);
      }

      this.setMenu(menu);
   }

   private void GemSynthesizeMenu(byte index) {
      UIMenu menu = new UIMenu((byte)0);
      int[] cmds;
      int[] inns;
      if (index == -1) {
         cmds = new int[]{-1610612707, 2228227, 2031650};
         inns = new int[]{3, 3, 2};
         menu.addMenu(Cons.MENU_SYNTHESIZE_1, cmds, inns);
      } else {
         cmds = new int[]{MainCanvas.curForm.clientCommand == 2228225 ? 2228240 : -1610612704, 2228231, 2228227, 2031650};
         inns = new int[]{3, 2, 3, 2};
         menu.addMenu(Cons.MENU_SYNTHESIZE_2, cmds, inns);
      }

      this.setMenu(menu);
   }

   private void GemUnchainMenu(byte index) {
      UIMenu menu = new UIMenu((byte)0);
      int[] cmds;
      int[] inns;
      if (index == -1) {
         cmds = new int[]{-1610612707, 1966099, 2031651};
         inns = new int[]{3, 1, 2};
         menu.addMenu(Cons.MENU_GEM_UNCHAIN_1, cmds, inns);
      } else {
         cmds = new int[]{-1610612704, 1966113, 1966099, 2031651};
         inns = new int[]{3, 2, 1, 2};
         menu.addMenu(Cons.MENU_GEM_UNCHAIN_2, cmds, inns);
      }

      this.setMenu(menu);
   }

   private void GemDeoxidizeMenu(byte index) {
      UIMenu menu = new UIMenu((byte)0);
      int[] cmds;
      int[] inns;
      String[] name;
      if (index == -1) {
         cmds = new int[]{-1610612707, 2228258, 2228260};
         inns = new int[]{3, 3, 2};
         name = new String[]{"Chọn vật phẩm", "Hoàn nguyên bảo thạch", "Giải thích Hoàn nguyên"};
         menu.addMenu(name, cmds, inns);
      } else {
         cmds = new int[]{-1610612704, 2228231, 2228258, 2228260};
         inns = new int[]{3, 2, 3, 2};
         name = new String[]{"Thiết lập lại vật phẩm", "Tra chi tiết", "Hoàn nguyên bảo thạch", "Giải thích Hoàn nguyên"};
         menu.addMenu(name, cmds, inns);
      }

      this.setMenu(menu);
   }

   public short getEquipCurDuable() {
      return this.equipCurDuable;
   }

   public void setEquipCurDuable(short equipCurDuable) {
      this.equipCurDuable = equipCurDuable;
   }

   public short getEquipMaxDuable() {
      return this.equipMaxDuable;
   }

   public void setEquipMaxDuable(short equipMaxDuable) {
      this.equipMaxDuable = equipMaxDuable;
   }

   public int getEquipFixPay() {
      return this.equipFixPay;
   }

   public void setEquipFixPay(int equipFixPay) {
      this.equipFixPay = equipFixPay;
   }

   public byte getWhetherEnvelop() {
      return this.m_bWhetherEnvelop;
   }

   public void setWhetherEnvelop(byte WhetherEnvelop) {
      this.m_bWhetherEnvelop = WhetherEnvelop;
   }

   public String getCropName() {
      return this.m_sCropName;
   }

   public void setCropName(String CropName) {
      this.m_sCropName = CropName;
   }

   public String getGrowthPhase() {
      return this.m_sGrowthPhase;
   }

   public void setGrowthPhase(String GrowthPhase) {
      this.m_sGrowthPhase = GrowthPhase;
   }

   public byte getGrowthLimit() {
      return this.m_bGrowthLimit;
   }

   public void setGrowthLimit(byte GrowthLimit) {
      this.m_bGrowthLimit = GrowthLimit;
   }

   public byte getWhetherGain() {
      return this.m_bWhetherGain;
   }

   public void setWhetherGain(byte WhetherGain) {
      this.m_bWhetherGain = WhetherGain;
   }

   public byte getCropStatus_1() {
      return this.m_bCropStatus_1;
   }

   public void setCropStatus_1(byte CropStatus_1) {
      this.m_bCropStatus_1 = CropStatus_1;
   }

   public String getCropStatus_2() {
      return this.m_bCropStatus_2;
   }

   public void setCropStatus_2(String CropStatus_2) {
      this.m_bCropStatus_2 = CropStatus_2;
   }

   public byte getGroundStatus() {
      return this.m_bGroundStatus;
   }

   public void setGroundStatus(byte GroundStatus) {
      this.m_bGroundStatus = GroundStatus;
   }

   public int getFrameIndex() {
      return this.m_nFrameIndex;
   }

   public void setFrameIndex(int FrameIndex) {
      this.m_nFrameIndex = FrameIndex;
   }

   public int getMenuIndex() {
      return this.m_nMenuIndex;
   }

   public void setMenuIndex(int MenuIndex) {
      this.m_nMenuIndex = MenuIndex;
   }

   private void FarmMenuManipulate() {
      PCFarm.menuvector = new Vector();

      for(int i = 0; i < PCFarm.MENUS.length; ++i) {
         if ((this.getMenuIndex() & 1 << i) != 0) {
            PCFarm.menuvector.addElement(new Byte((byte)i));
         }

         int[] menuCommand = new int[PCFarm.menuvector.size()];
         int[] inns = new int[PCFarm.menuvector.size()];
         String[] str = new String[PCFarm.menuvector.size()];

         for(int j = 0; j < PCFarm.menuvector.size(); ++j) {
            menuCommand[j] = 2555936;
            inns[j] = PCFarm.NENU_MANIPULATE[(Byte)PCFarm.menuvector.elementAt(j)];
            str[j] = PCFarm.MENUS[(Byte)PCFarm.menuvector.elementAt(j)];
         }

         UIMenu menu = new UIMenu((byte)0);
         menu.addMenu(str, menuCommand, inns);
         this.setMenu(menu);
         super.isShowMenu = true;
         this.getMenu().resetIndex();
      }

   }

   private void drawTreasure(Graphics g, short x, short y) {
      g.drawImage(MainCanvas.treasurePic, x, y, 20);
   }
}

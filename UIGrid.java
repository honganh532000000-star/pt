import javax.microedition.lcdui.Graphics;

public class UIGrid extends UIComponent {
   public static final byte STUFF_TYPE_FORBID_USE = 0;
   public static final byte STUFF_TYPE_CAN_USE = 1;
   public static final byte STUFF_TYPE_COMPOSE = 2;
   public static final byte STUFF_TYPE_ENCHASE = 3;
   public static final byte TYPE_DEFAULT = 0;
   public static final byte TYPE_SKILLINTERFACE = 1;
   private byte gridtype;
   private UILabel titleLabel;
   private UIScroll gridScroll;
   private short gridCol;
   private short showGridCol;
   public static final short PACKAGE_ROW = 8;
   private short gridRow;
   public static final short PACKAGE_NUM = 16;
   public short canUseNum;
   public int[] stuffId;
   public short[] stuffImgQuality;
   public short[] stuffImageId;
   public byte[] stuffNumber;
   public String[] stuffName;
   public byte[] stuffColorLevel;
   public short[] stuffType;
   public boolean[] isCanUse;
   public boolean[] isCanTrade;
   public byte[] bindType;
   public int[] stuffPrice;
   public boolean[] stuffShowMiniNum;
   public byte[] stuffMaxNum;
   public byte[] stuffGroupNum;
   public byte[] stuffQuality;
   public byte[] stuffEquipPart;
   public boolean[] stuffCD;
   public boolean[] stuffLock;
   public boolean[] stuffHitHole;
   public static int m_nGoodsIndex = 0;
   public static byte m_nGoods = -1;
   public byte gridIndex;
   public static byte severGridIndex;
   public byte beginShockIndex;
   public byte endShockIndex;
   private short startCol;
   public static int sCount = 0;
   public static final short TITLE_OX = 3;
   public static final short GRID_OX = 8;
   public static final short CASE_SIZE = 16;
   public static final short CASE_OX = 2;
   public static final short CASE_OY = 2;
   private boolean isHaveTitle;
   private static byte equipPartType = 0;
   public static int curSelStrColor = 0;
   public static boolean isUpdataEquip = true;
   public static final byte GRID_PLAYER_BAG = 0;
   public static final byte GRID_SHOP_BAG = 1;
   public static final byte GRID_STORAGE_BAG = 2;
   public static final byte GRID_COMPARE_BAG = 3;
   public static final byte GRID_TRADE_BAG = 4;
   public static final byte GRID_MAIL_BAG = 5;
   public static final byte GRID_CHANGE_BAG = 6;
   public static final byte GRID_AUCTION_BAG = 7;
   public static final byte GRID_EQUIP_BAG = 8;
   public static final byte GRID_STORAGE_EXPAND = 9;
   public static final byte GRID_GEM_USE = 10;
   public static final byte GRID_NUSEAL = 11;
   public static final byte GRID_GEMCARVE = 12;
   public static final byte GRID_SYNTHESIZE = 13;
   public static final byte GRID_GEM_UNCHAIN = 14;
   public static final byte GRID_ENCHANTING = 15;
   public static final byte GRID_INCREMENT = 16;
   public static final byte GRID_MEND = 17;
   public static final byte GRID_GEM_DEOXIDIZE = 18;
   public static final byte GRID_TREASURE_STRENGTHEN = 19;
   public static final byte GRID_EQUIP_STRENGTHEN = 20;
   private byte gridFormType;
   private int playerMoney;
   public int[] gridExchangeIndex;
   public static boolean fromEquipUnseal = false;
   int tmpI;
   int tmpJ;
   int gridY;
   public static int dropIndex = -1;
   public static int storageMoney = 0;
   public static byte useIndex = 0;
   public static byte[] tradeSign = new byte[]{-1, -1, -1, -1, -1, -1};
   public static short[][] accessories = new short[][]{{-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}};
   public static byte[] tradeSignNum = new byte[]{1, 1, 1, 1, 1, 1};
   public static short[] AuctionBag = new short[]{-1, -1, -1, -1};
   public static String m_nAuctionGoodsName = "";
   private static final byte TRADE_SIGN_NUM = 6;
   public static boolean bagIsFull = false;

   public static byte getEquipPartType() {
      return equipPartType;
   }

   public void setEquipPartType(byte equipPartType) {
      UIGrid.equipPartType = equipPartType;
   }

   public byte getGridType() {
      return this.gridtype;
   }

   public void setGridType(byte t) {
      this.gridtype = t;
   }

   public byte getGridFormType() {
      return this.gridFormType;
   }

   public void setGridFormType(byte gridFormType) {
      this.gridFormType = gridFormType;
   }

   public UIGrid(short x, short y, short w, short h, String titleStr, short showGridCol, short gridCol, short gridRow, byte gt, UIForm form) {
      super(x, y, w, h, form);
      this.gridtype = 0;
      this.gridRow = 8;
      this.canUseNum = 16;
      this.stuffId = null;
      this.stuffImgQuality = null;
      this.stuffImageId = null;
      this.stuffNumber = null;
      this.stuffName = null;
      this.stuffColorLevel = null;
      this.stuffType = null;
      this.isCanUse = null;
      this.isCanTrade = null;
      this.bindType = null;
      this.stuffPrice = null;
      this.stuffShowMiniNum = null;
      this.stuffMaxNum = null;
      this.stuffGroupNum = null;
      this.stuffQuality = null;
      this.stuffEquipPart = null;
      this.stuffCD = null;
      this.stuffLock = null;
      this.stuffHitHole = null;
      this.beginShockIndex = -1;
      this.endShockIndex = -1;
      this.startCol = 0;
      this.isHaveTitle = true;
      this.gridFormType = 0;
      this.playerMoney = 0;
      this.gridExchangeIndex = new int[]{-1, -1};
      this.tmpI = 0;
      this.tmpJ = 0;
      this.gridY = 0;
      this.setGridType(gt);
      super.positionX = (short)(MainCanvas.screenW - (18 * MainCanvas.screenW / 176 * gridRow + MainCanvas.ui_3Img.getWidth()) >> 1);
      super.canFocus = true;
      super.haveInnerComponent = true;
      this.stuffId = new int[this.canUseNum];
      this.stuffImgQuality = new short[this.canUseNum];
      this.stuffImageId = new short[this.canUseNum];
      this.stuffNumber = new byte[this.canUseNum];
      this.stuffName = new String[this.canUseNum];
      this.stuffColorLevel = new byte[this.canUseNum];
      this.stuffType = new short[this.canUseNum];
      this.isCanUse = new boolean[this.canUseNum];
      this.isCanTrade = new boolean[this.canUseNum];
      this.bindType = new byte[this.canUseNum];
      this.stuffPrice = new int[this.canUseNum];
      this.stuffShowMiniNum = new boolean[this.canUseNum];
      this.stuffMaxNum = new byte[this.canUseNum];
      this.stuffGroupNum = new byte[this.canUseNum];
      this.stuffQuality = new byte[this.canUseNum];
      this.stuffEquipPart = new byte[this.canUseNum];
      this.stuffCD = new boolean[this.canUseNum];
      this.stuffLock = new boolean[this.canUseNum];
      this.stuffHitHole = new boolean[this.canUseNum];

      for(int i = 0; i < this.canUseNum; ++i) {
         this.stuffId[i] = this.stuffImageId[i] = -1;
      }

      this.showGridCol = showGridCol;
      this.gridCol = gridCol;
      this.gridRow = gridRow;
      if (x < 0) {
         x = 8;
      }

      super.width = (short)(this.getAutoGridWidth() * MainCanvas.screenW / 176);
      super.height = (short)(this.getAutoGridHeight() * MainCanvas.screenH / 208);
      short titleW = (short)(this.getAutoGridWidth() - 6);
      short titleH = (short)UILabel.getRimLabelHeight();
      short titleX = (short)(x + 3);
      this.titleLabel = new UILabel(titleX, y, titleW, titleH, (byte)1, titleStr, form);
      this.titleLabel.setUserData(false);
      this.addGridScroll(form);
   }

   private void addGridScroll(UIForm form) {
      if (this.showGridCol > 2) {
         if (this.isHaveTitle) {
            this.gridScroll = new UIScroll((short)(super.positionX + 18 * MainCanvas.screenW / 176 * this.gridRow), (short)(super.positionY + UILabel.getRimLabelHeight() + 2), (short)0, (short)(18 * MainCanvas.screenH / 208 * this.showGridCol - 2 * MainCanvas.screenH / 208), (byte)0, false, form);
         } else {
            this.gridScroll = new UIScroll((short)(super.positionX + 18 * MainCanvas.screenW / 176 * this.gridRow), (short)(super.positionY + 2), (short)0, (short)(18 * MainCanvas.screenH / 208 * this.showGridCol - 2 * MainCanvas.screenH / 208), (byte)0, false, form);
         }

         this.gridScroll.canShowColNum = this.gridRow;
      } else {
         this.gridScroll = null;
      }

   }

   public UIGrid(short y, short showGridCol, short gridCol, byte gt, UIForm form) {
      this((short)-1, y, (short)0, (short)0, (String)null, showGridCol, gridCol, (short)8, gt, form);
   }

   public UIGrid(short x, short y, String titleStr, short showGridCol, short gridCol, short gridRow, byte gt, UIForm form) {
      this(x, y, (short)0, (short)0, titleStr, showGridCol, gridCol, gridRow, gt, form);
   }

   public void draw(Graphics g) {
      int subCol;
      if (this.stuffName.length > 0) {
         subCol = this.gridIndex / this.gridRow;
         this.gridIndex = (byte)(subCol * this.gridRow + super.attachForm.gridSelectRow);
         if (this.isFocus()) {
            this.setTitle(this.stuffName[this.gridIndex]);
         } else {
            this.setTitle("");
         }
      }

      if (this.isHaveTitle) {
         this.gridY = super.positionY + UILabel.getRimLabelHeight() + 2;
      } else {
         this.gridY = super.positionY + 2;
      }

      subCol = this.canUseNum % this.gridRow == 0 ? this.canUseNum / this.gridRow : this.canUseNum / this.gridRow + 1;
      int n = this.startCol + this.showGridCol > subCol ? subCol : this.startCol + this.showGridCol;
      if (n < this.showGridCol) {
         n = this.showGridCol;
      }

      int tmpX = 18 * MainCanvas.screenW / 176;
      int tmpY = 18 * MainCanvas.screenH / 208;
      boolean isOff = this.showGridCol <= 2;

      for(int i = this.startCol; i < n; ++i) {
         for(int j = 0; j < this.gridRow; ++j) {
            if (i * this.gridRow + j + 1 <= this.canUseNum) {
               UIPicture.drawDotKuang(g, super.positionX + (isOff ? 5 : 0) + j * tmpX, this.gridY + (i - this.startCol) * tmpY, 14, 14);
               if (this.stuffImageId != null && this.stuffImageId[i * this.gridRow + j] != -1) {
                  g.setColor(Cons.COLOR_GRID_FRAME_BG[this.stuffQuality[i * this.gridRow + j]][0]);
                  g.fillRect(super.positionX + (isOff ? 5 : 0) + j * tmpX, this.gridY + (i - this.startCol) * tmpY, 16, 16);
                  MainCanvas.stuffMImg.draw(g, super.positionX + (isOff ? 5 : 0) + j * tmpX + 1, this.gridY + (i - this.startCol) * tmpY + 1, this.stuffImageId[i * this.gridRow + j]);
                  g.setColor(Cons.COLOR_GRID_FRAME_BG[this.stuffQuality[i * this.gridRow + j]][1]);
                  g.drawRect(super.positionX + (isOff ? 5 : 0) + j * tmpX, this.gridY + (i - this.startCol) * tmpY, 15, 15);
                  if (this.stuffNumber != null && this.stuffNumber[i * this.gridRow + j] != -1 && this.stuffShowMiniNum[i * this.gridRow + j]) {
                     UIPicture.drawNum(g, super.positionX + (isOff ? 5 : 0) + j * tmpX, this.gridY + (i - this.startCol) * tmpY, this.stuffNumber[i * this.gridRow + j]);
                  }

                  if (this.stuffCD[i * this.gridRow + j]) {
                     g.drawImage(MainCanvas.ui_icon_shadow, super.positionX + (isOff ? 5 : 0) + j * tmpX + 1, this.gridY + (i - this.startCol) * tmpY + 1, 4 | 16);
                  }
               }

               if (this.stuffLock[i * this.gridRow + j]) {
                  g.drawImage(MainCanvas.ui_dongImg, super.positionX + (isOff ? 5 : 0) + j * tmpX, this.gridY + (i - this.startCol) * tmpY, 4 | 16);
               }

               if (MainCanvas.curForm.clientCommand == -1610612734) {
                  MainCanvas.ui_numMImg.draw(g, super.positionX + (isOff ? 5 : 0) + j * tmpX, this.gridY + (i - this.startCol) * tmpY, j + 1);
               }
            } else {
               g.drawImage(MainCanvas.ui_fengImg, super.positionX + (isOff ? 5 : 0) + j * tmpX, this.gridY + (i - this.startCol) * tmpY, 20);
            }

            if (this.gridExchangeIndex[0] != -1 && this.gridExchangeIndex[0] / this.gridRow >= this.getStartCol() && this.gridExchangeIndex[0] / this.gridRow - this.startCol < this.getShowGridCol()) {
               Util.drawSelectedKuangEffect(g, super.positionX + (isOff ? 5 : 0) + this.gridExchangeIndex[0] % this.gridRow * tmpX, this.gridY + (this.gridExchangeIndex[0] / this.gridRow - this.startCol) * tmpY, 16, 16);
            }

            int h;
            int hh;
            label180:
            switch(MainCanvas.curForm.clientCommand) {
            case 458760:
            case 2555911:
               if (this.beginShockIndex != -1 && this.endShockIndex != -1 && i * this.gridRow + j >= this.beginShockIndex && i * this.gridRow + j < this.endShockIndex) {
                  Util.drawSelectedKuangEffect(g, super.positionX + j * tmpX, this.gridY + (i - this.startCol) * tmpY, 16, 16);
               }
               break;
            case 1376263:
               h = 0;
               hh = tradeSign.length;

               while(true) {
                  if (h >= hh) {
                     break label180;
                  }

                  if (tradeSign[h] != -1) {
                     Util.drawSelectedKuangEffect(g, super.positionX + tradeSign[h] % this.gridRow * tmpX, this.gridY + (tradeSign[h] / this.gridRow - this.startCol) * tmpY, 16, 16);
                  }

                  ++h;
               }
            case 1703946:
               h = 0;

               for(hh = accessories.length; h < hh; ++h) {
                  if (accessories[h][0] != -1) {
                     Util.drawSelectedKuangEffect(g, super.positionX + accessories[h][0] % this.gridRow * tmpX, this.gridY + (accessories[h][0] / this.gridRow - this.startCol) * tmpY, 16, 16);
                  }
               }
            }

            if (i * this.gridRow + j == this.gridIndex) {
               if (this.titleLabel != null && this.isHaveTitle) {
                  this.titleLabel.draw(g);
               }

               this.tmpI = i;
               this.tmpJ = j;
            }
         }
      }

      if (this.gridScroll != null) {
         this.gridScroll.draw(g);
      }

   }

   public void drawCursorAndTopForm(Graphics g) {
      if (this.tmpI * this.gridRow + this.tmpJ == this.gridIndex) {
         Util.drawSelectedCursorEffect(g, super.positionX + this.tmpJ * 18 * MainCanvas.screenW / 176 + 10, this.gridY + (this.tmpI - this.startCol) * 18 * MainCanvas.screenH / 208 + 12);
      }

   }

   public short getGridCol() {
      return this.gridCol;
   }

   public void setGridCol(short gridCol) {
      this.gridCol = gridCol;
   }

   public short getGridRow() {
      return this.gridRow;
   }

   public void setGridRow(short gridRow) {
      this.gridRow = gridRow;
   }

   public short getShowGridCol() {
      return this.showGridCol;
   }

   public UILabel getTitleLabel() {
      return this.titleLabel;
   }

   public short getCanUseNum() {
      return this.canUseNum;
   }

   public void updataCanUseNum(short canUseNum) {
      if (canUseNum > this.canUseNum) {
         this.canUseNum = canUseNum;
         int[] tmpStuffId = this.stuffId;
         short[] tmpStuffImageId = this.stuffImageId;
         byte[] tmpStuffNumber = this.stuffNumber;
         String[] tmpStuffName = this.stuffName;
         byte[] tmpStuffLevel = this.stuffColorLevel;
         short[] tmpStuffType = this.stuffType;
         boolean[] tmpIsCanUse = this.isCanUse;
         boolean[] tmpIsCanTrade = this.isCanTrade;
         byte[] tmpBindType = this.bindType;
         int[] tmpStuffPrice = this.stuffPrice;
         boolean[] tmpStuffShowMiniNum = this.stuffShowMiniNum;
         byte[] tmpStuffMaxNum = this.stuffMaxNum;
         byte[] tmpStuffGroupNum = this.stuffGroupNum;
         byte[] tmpStuffQuality = this.stuffQuality;
         byte[] tmpStuffEquipPart = this.stuffEquipPart;
         boolean[] tmpStuffCD = this.stuffCD;
         boolean[] tmpStuffHitHole = this.stuffHitHole;
         boolean[] tmpStuffLock = this.stuffLock;
         this.stuffId = new int[canUseNum];
         this.stuffImgQuality = new short[canUseNum];
         this.stuffImageId = new short[canUseNum];
         this.stuffNumber = new byte[canUseNum];
         this.stuffName = new String[canUseNum];
         this.stuffColorLevel = new byte[canUseNum];
         this.stuffType = new short[canUseNum];
         this.isCanUse = new boolean[canUseNum];
         this.isCanTrade = new boolean[canUseNum];
         this.bindType = new byte[canUseNum];
         this.stuffPrice = new int[canUseNum];
         this.stuffShowMiniNum = new boolean[canUseNum];
         this.stuffMaxNum = new byte[canUseNum];
         this.stuffGroupNum = new byte[canUseNum];
         this.stuffQuality = new byte[canUseNum];
         this.stuffEquipPart = new byte[canUseNum];
         this.stuffCD = new boolean[canUseNum];
         this.stuffHitHole = new boolean[canUseNum];
         this.stuffLock = new boolean[canUseNum];
         System.arraycopy(tmpStuffId, 0, this.stuffId, 0, tmpStuffId.length);
         System.arraycopy(tmpStuffImageId, 0, this.stuffImageId, 0, tmpStuffImageId.length);
         System.arraycopy(tmpStuffNumber, 0, this.stuffNumber, 0, tmpStuffNumber.length);
         System.arraycopy(tmpStuffName, 0, this.stuffName, 0, tmpStuffName.length);
         System.arraycopy(tmpStuffLevel, 0, this.stuffColorLevel, 0, tmpStuffLevel.length);
         System.arraycopy(tmpStuffType, 0, this.stuffType, 0, tmpStuffType.length);
         System.arraycopy(tmpIsCanUse, 0, this.isCanUse, 0, tmpIsCanUse.length);
         System.arraycopy(tmpIsCanTrade, 0, this.isCanTrade, 0, tmpIsCanTrade.length);
         System.arraycopy(tmpBindType, 0, this.bindType, 0, tmpBindType.length);
         System.arraycopy(tmpStuffPrice, 0, this.stuffPrice, 0, tmpStuffPrice.length);
         System.arraycopy(tmpStuffShowMiniNum, 0, this.stuffShowMiniNum, 0, tmpStuffShowMiniNum.length);
         System.arraycopy(tmpStuffMaxNum, 0, this.stuffMaxNum, 0, tmpStuffMaxNum.length);
         System.arraycopy(tmpStuffGroupNum, 0, this.stuffGroupNum, 0, tmpStuffGroupNum.length);
         System.arraycopy(tmpStuffQuality, 0, this.stuffQuality, 0, tmpStuffQuality.length);
         System.arraycopy(tmpStuffEquipPart, 0, this.stuffEquipPart, 0, tmpStuffEquipPart.length);
         System.arraycopy(tmpStuffCD, 0, this.stuffCD, 0, tmpStuffCD.length);
         System.arraycopy(tmpStuffHitHole, 0, this.stuffHitHole, 0, tmpStuffHitHole.length);
         System.arraycopy(tmpStuffLock, 0, this.stuffLock, 0, tmpStuffLock.length);
      }

   }

   public void setCanUseNum(short canUseNum) {
      this.canUseNum = canUseNum;
      this.stuffId = new int[canUseNum];
      this.stuffImgQuality = new short[canUseNum];
      this.stuffImageId = new short[canUseNum];
      this.stuffImgQuality = new short[canUseNum];
      this.stuffNumber = new byte[canUseNum];
      this.stuffName = new String[canUseNum];
      this.stuffColorLevel = new byte[canUseNum];
      this.stuffType = new short[canUseNum];
      this.isCanUse = new boolean[canUseNum];
      this.isCanTrade = new boolean[canUseNum];
      this.bindType = new byte[canUseNum];
      this.stuffPrice = new int[canUseNum];
      this.stuffShowMiniNum = new boolean[canUseNum];
      this.stuffMaxNum = new byte[canUseNum];
      this.stuffGroupNum = new byte[canUseNum];
      this.stuffQuality = new byte[canUseNum];
      this.stuffEquipPart = new byte[canUseNum];
      this.stuffCD = new boolean[canUseNum];
      this.stuffHitHole = new boolean[canUseNum];
      this.stuffLock = new boolean[canUseNum];
   }

   public void setAllItemImgIDNone() {
      for(int i = 0; i < this.canUseNum; ++i) {
         this.setItemImgID(i, (short)-1);
      }

   }

   public void setItemImgID(int index, short imgID) {
      if (index >= 0 && index <= this.canUseNum - 1) {
         this.stuffImageId[index] = imgID;
      } else {
         throw new IndexOutOfBoundsException("Điểm thiết lập vượt quá phạm vi!");
      }
   }

   public short getItemImgID(int index) {
      if (index >= 0 && index <= this.canUseNum - 1) {
         return this.stuffImageId[index];
      } else {
         throw new IndexOutOfBoundsException("Điểm thiết lập vượt quá phạm vi!");
      }
   }

   public void setItemColorLevel(int index, byte cl) {
      if (index >= 0 && index <= this.canUseNum - 1) {
         this.stuffColorLevel[index] = cl;
      } else {
         throw new IndexOutOfBoundsException("Điểm thiết lập vượt quá phạm vi!");
      }
   }

   public byte getItemColorLevel(int index) {
      if (index >= 0 && index <= this.canUseNum - 1) {
         return this.stuffColorLevel[index];
      } else {
         throw new IndexOutOfBoundsException("Điểm thiết lập vượt quá phạm vi!");
      }
   }

   public void setItemID(int index, int ID) {
      if (index >= 0 && index <= this.canUseNum - 1) {
         this.stuffId[index] = ID;
      } else {
         throw new IndexOutOfBoundsException("Điểm thiết lập vượt quá phạm vi!");
      }
   }

   public int getItemID(int index) {
      if (index >= 0 && index <= this.canUseNum - 1) {
         return this.stuffId[index];
      } else {
         throw new IndexOutOfBoundsException("Điểm thiết lập vượt quá phạm vi!");
      }
   }

   public void setItemType(int index, short type) {
      if (index >= 0 && index <= this.canUseNum - 1) {
         this.stuffType[index] = type;
      } else {
         throw new IndexOutOfBoundsException("Điểm thiết lập vượt quá phạm vi!");
      }
   }

   public boolean getItemCD(int index) {
      if (index >= 0 && index <= this.canUseNum - 1) {
         return this.stuffCD[index];
      } else {
         throw new IndexOutOfBoundsException("Điểm thiết lập vượt quá phạm vi!");
      }
   }

   public void setItemCD(int index, boolean isCD) {
      if (index >= 0 && index <= this.canUseNum - 1) {
         this.stuffCD[index] = isCD;
      } else {
         throw new IndexOutOfBoundsException("Điểm thiết lập vượt quá phạm vi!");
      }
   }

   public short getItemType(int index) {
      if (index >= 0 && index <= this.canUseNum - 1) {
         return this.stuffType[index];
      } else {
         throw new IndexOutOfBoundsException("Điểm thiết lập vượt quá phạm vi!");
      }
   }

   public void setItemName(int index, String name) {
      if (index >= 0 && index <= this.canUseNum - 1) {
         this.stuffName[index] = name;
      } else {
         throw new IndexOutOfBoundsException("Điểm thiết lập vượt quá phạm vi!");
      }
   }

   public String getItemName(int index) {
      if (index >= 0 && index <= this.canUseNum - 1) {
         return this.stuffName[index];
      } else {
         throw new IndexOutOfBoundsException("Điểm thiết lập vượt quá phạm vi!");
      }
   }

   public void setItemQuality(int index, byte quality) {
      if (index >= 0 && index <= this.canUseNum - 1) {
         this.stuffQuality[index] = quality;
      } else {
         throw new IndexOutOfBoundsException("Điểm thiết lập vượt quá phạm vi!");
      }
   }

   public byte getItemQuality(int index) {
      if (index >= 0 && index <= this.canUseNum - 1) {
         return this.stuffQuality[index];
      } else {
         throw new IndexOutOfBoundsException("Điểm thiết lập vượt quá phạm vi!");
      }
   }

   public short getAutoGridWidth() {
      return (short)(18 * this.gridRow + MainCanvas.ui_3Img.getWidth());
   }

   public short getAutoGridHeight() {
      return this.isHaveTitle ? (short)(UILabel.getRimLabelHeight() + 2 + 18 * this.showGridCol) : (short)(2 + 18 * this.showGridCol);
   }

   public void setTitle(String itemName, int color) {
      if (this.titleLabel != null) {
         this.titleLabel.setTextColor(color);
         this.titleLabel.setText(itemName);
      }

   }

   public void setTitle(String itemName) {
      if (this.titleLabel != null) {
         this.titleLabel.setText(itemName);
      }

   }

   public short getStartCol() {
      return this.startCol;
   }

   public void setStartCol(short startCol) {
      this.startCol = startCol;
   }

   public short getGridIndex() {
      return this.gridIndex;
   }

   public void setGridIndex(byte gridIndex) {
      if (!this.isOutofGrid(gridIndex)) {
         this.gridIndex = gridIndex;
         severGridIndex = this.gridIndex;
      }
   }

   public boolean isOutofGrid(byte gridIndex) {
      Util.printInfo("Ô hiện tại vượt giới hạn", 0);
      return gridIndex < 0 || gridIndex > this.canUseNum - 1;
   }

   public static void resetChuck() {
      dropIndex = -1;
   }

   private void setShortCut(byte type) {
      UIForm tmpForm = (UIForm)MainCanvas.curFormVector.elementAt(0);

      for(int i = 0; i < 3; ++i) {
         UIGrid grid = (UIGrid)tmpForm.getComponents().elementAt(3 + i);
         if (grid.isFocus()) {
            int index = grid.getGridIndex();
            grid.stuffId[index] = this.stuffId[this.gridIndex];
            grid.stuffImgQuality[index] = this.stuffImgQuality[this.gridIndex];
            grid.stuffImageId[index] = this.stuffImageId[this.gridIndex];
            grid.stuffColorLevel[index] = this.stuffColorLevel[this.gridIndex];
            grid.stuffName[index] = this.stuffName[this.gridIndex];
            grid.stuffType[index] = type;
            grid.stuffQuality[index] = this.stuffQuality[this.gridIndex];
            grid.stuffCD[index] = this.stuffCD[this.gridIndex];
            grid.setTitle(grid.stuffName[index]);
            grid.getTitleLabel().setTextColor(getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
            break;
         }
      }

      MainCanvas.curForm = tmpForm;
   }

   public boolean focusWidgetPointAction() {
      if (MainCanvas.SUPPORT_POINTER) {
         if (this.gridScroll != null && this.gridScroll.focusWidgetPointAction()) {
            return true;
         }

         if (this.isHaveTitle) {
            this.gridY = super.positionY + UILabel.getRimLabelHeight() + 2;
         } else {
            this.gridY = super.positionY + 2;
         }

         int subCol = this.canUseNum % this.gridRow == 0 ? this.canUseNum / this.gridRow : this.canUseNum / this.gridRow + 1;
         int n = this.startCol + this.showGridCol > subCol ? subCol : this.startCol + this.showGridCol;
         if (n < this.showGridCol) {
            n = this.showGridCol;
         }

         int tmpX = 18 * MainCanvas.screenW / 176;
         int tmpY = 18 * MainCanvas.screenH / 208;
         boolean isOff = this.showGridCol <= 2;

         for(int i = this.startCol; i < n; ++i) {
            for(int j = 0; j < this.gridRow; ++j) {
               if (MainCanvas.isPointInRect(super.positionX + (isOff ? 5 : 0) + j * tmpX, this.gridY + (i - this.startCol) * tmpY, 18, 18)) {
                  int tmpIndex = i * this.gridRow + j;
                  if (tmpIndex >= this.canUseNum) {
                     tmpIndex = this.canUseNum - 1;
                  }

                  if (this.gridIndex == tmpIndex) {
                     return false;
                  }

                  this.setGridIndex((byte)tmpIndex);
                  MainCanvas.curForm.gridSelectRow = (byte)(this.gridIndex % this.gridRow);
                  this.selectChangeAction();
                  if (MainCanvas.curForm.clientCommand == 2424994 || MainCanvas.curForm.clientCommand == 2425072) {
                     UILabel label = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
                     if (PCIncrement.m_bBuyMoney != 3) {
                        label.setNum1(PCIncrement.m_bBuyShopping[this.gridIndex]);
                     } else {
                        label.setType((byte)0);
                        label.setText(PCIncrement.m_sBuyShopping[this.gridIndex]);
                     }
                  }

                  return true;
               }
            }
         }
      }

      return true;
   }

   private void selectChangeAction() {
      if (this.getGridFormType() == 5 || this.getGridFormType() == 10 || this.getGridFormType() == 11 || this.getGridFormType() == 12 || this.getGridFormType() == 13 || this.getGridFormType() == 14 || this.getGridFormType() == 18 || this.getGridFormType() == 19 || this.getGridFormType() == 20) {
         UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
         if (this.stuffId[this.gridIndex] != -1) {
            menuBar.setMenuText("Thao tác", 0);
         } else {
            menuBar.setMenuText("", 0);
         }
      }

      this.titleLabel.setText(this.stuffName[this.gridIndex]);
      this.titleLabel.setTextColor(getStuffWordColor(this.stuffColorLevel[this.gridIndex]));
      switch(MainCanvas.curForm.clientCommand) {
      case -1610612733:
      case -1610612732:
      case -1610612620:
      case -1610612618:
         this.setSkill_area_Content();
      default:
         UIComponent.especialDeal();
      }
   }

   public void keyInAction() {
      if (super.isShowMenu) {
         this.keyInMenu();
      } else {
         UIComponent upUic;
         UIGrid ug;
         int tmpCol;
         UILabel label;
         UITitle menuBar;
         byte tmpIndex;
         if (MainCanvas.isInputDownOrHold(4096)) {
            tmpCol = this.gridIndex / this.gridRow - 1;
            tmpIndex = this.gridIndex = (byte)(tmpCol * this.gridRow + MainCanvas.curForm.gridSelectRow);
            if (this.gridIndex < 0) {
               ++tmpCol;
               this.gridIndex = (byte)(tmpCol * this.gridRow + MainCanvas.curForm.gridSelectRow);
            }

            if (tmpIndex < 0) {
               if (MainCanvas.curForm.clientCommand == 3342341) {
                  return;
               }

               upUic = this.getFinalAroundComponent(MainCanvas.curForm, (byte)0);
               if (upUic != this) {
                  if (MainCanvas.curForm.clientCommand == 1966097) {
                     PCGem.m_bFocus = -1;
                  } else if (MainCanvas.curForm.clientCommand == 2228225) {
                     PCGemJoinOrRemove.m_bFocus = -1;
                  } else if (MainCanvas.curForm.clientCommand == 2031621 || MainCanvas.curForm.clientCommand == 2031636) {
                     PCUnsealGemCarve.m_bFocus = -1;
                  }

                  this.setFocus(false, MainCanvas.curForm);
                  upUic.setFocus(true, MainCanvas.curForm);
                  if (upUic instanceof UIGrid) {
                     ug = (UIGrid)upUic;
                     int tCol = ug.getCanUseNum() / ug.getGridRow() + 1;
                     byte tmpGrid = (byte)(tCol * ug.getGridRow() - 1 - (ug.getGridRow() - 1 - MainCanvas.curForm.gridSelectRow));
                     if (tmpGrid >= ug.getCanUseNum()) {
                        tmpGrid = (byte)((tCol - 1) * ug.getGridRow() - 1 - (ug.getGridRow() - 1 - MainCanvas.curForm.gridSelectRow));
                        if (tmpGrid < ug.getCanUseNum() && tmpGrid >= 0) {
                           ug.setGridIndex(tmpGrid);
                           ug.getTitleLabel().setTextColor(getStuffWordColor(ug.stuffColorLevel[ug.gridIndex]));
                        } else {
                           this.setFocus(true, MainCanvas.curForm);
                           upUic.setFocus(false, MainCanvas.curForm);
                        }
                     } else {
                        ug.setGridIndex(tmpGrid);
                        ug.getTitleLabel().setTextColor(getStuffWordColor(ug.stuffColorLevel[ug.gridIndex]));
                     }
                  }
               }
            }

            this.checkStartColUp();
            if (MainCanvas.curForm.clientCommand == 2424994 || MainCanvas.curForm.clientCommand == 2425072) {
               label = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
               if (PCIncrement.m_bBuyMoney != 3) {
                  label.setNum1(PCIncrement.m_bBuyShopping[this.gridIndex]);
               } else {
                  label.setType((byte)0);
                  label.setText(PCIncrement.m_sBuyShopping[this.gridIndex]);
               }
            }

            this.selectChangeAction();
            if (MainCanvas.curForm.clientCommand == 3342341) {
               menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
               if (this.stuffId[this.gridIndex] != -1) {
                  menuBar.setMenuText("Sử dụng", 0);
               } else {
                  menuBar.setMenuText("", 0);
               }
            }
         } else if (MainCanvas.isInputDownOrHold(8192)) {
            tmpCol = this.gridIndex / this.gridRow + 1;
            tmpIndex = this.gridIndex = (byte)(tmpCol * this.gridRow + MainCanvas.curForm.gridSelectRow);
            if (this.gridIndex >= this.canUseNum) {
               --tmpCol;
               this.gridIndex = (byte)(tmpCol * this.gridRow + MainCanvas.curForm.gridSelectRow);
            }

            if (tmpIndex >= this.canUseNum) {
               upUic = this.getFinalAroundComponent(MainCanvas.curForm, (byte)1);
               if (upUic != this) {
                  this.setFocus(false, MainCanvas.curForm);
                  upUic.setFocus(true, MainCanvas.curForm);
                  if (upUic instanceof UIGrid) {
                     ug = (UIGrid)upUic;
                     byte tmpGrid = MainCanvas.curForm.gridSelectRow;
                     if (tmpGrid >= ug.getCanUseNum()) {
                        this.setFocus(true, MainCanvas.curForm);
                        upUic.setFocus(false, MainCanvas.curForm);
                     } else {
                        ug.setGridIndex(tmpGrid);
                     }

                     ug.getTitleLabel().setTextColor(getStuffWordColor(ug.stuffColorLevel[ug.gridIndex]));
                  }
               }
            }

            this.checkStartColDown();
            if (MainCanvas.curForm.clientCommand == 2424994 || MainCanvas.curForm.clientCommand == 2425072) {
               label = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
               if (PCIncrement.m_bBuyMoney != 3) {
                  label.setNum1(PCIncrement.m_bBuyShopping[this.gridIndex]);
               } else {
                  label.setType((byte)0);
                  label.setText(PCIncrement.m_sBuyShopping[this.gridIndex]);
               }
            }

            this.selectChangeAction();
            if (MainCanvas.curForm.clientCommand == 3342341) {
               menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
               if (this.stuffId[this.gridIndex] != -1) {
                  menuBar.setMenuText("Sử dụng", 0);
               } else {
                  menuBar.setMenuText("", 0);
               }
            }
         } else {
            UILabel label;
            UITitle menuBar;
            if (MainCanvas.isInputDownOrHold(16384)) {
               tmpCol = this.gridIndex / this.gridRow;
               --MainCanvas.curForm.gridSelectRow;
               if (MainCanvas.curForm.gridSelectRow < 0) {
                  if (tmpCol == 0) {
                     MainCanvas.curForm.gridSelectRow = 0;
                  } else {
                     MainCanvas.curForm.gridSelectRow = (byte)(this.gridRow - 1);
                  }

                  --tmpCol;
                  if (tmpCol < 0) {
                     tmpCol = 0;
                  }
               }

               this.gridIndex = (byte)(tmpCol * this.gridRow + MainCanvas.curForm.gridSelectRow);
               this.checkStartColUp();
               if (MainCanvas.curForm.clientCommand == 2424994 || MainCanvas.curForm.clientCommand == 2425072) {
                  label = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
                  if (PCIncrement.m_bBuyMoney != 3) {
                     label.setNum1(PCIncrement.m_bBuyShopping[this.gridIndex]);
                  } else {
                     label.setType((byte)0);
                     label.setText(PCIncrement.m_sBuyShopping[this.gridIndex]);
                  }
               }

               this.selectChangeAction();
               if (MainCanvas.curForm.clientCommand == 3342341) {
                  menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                  if (this.stuffId[this.gridIndex] != -1) {
                     menuBar.setMenuText("Sử dụng", 0);
                  } else {
                     menuBar.setMenuText("", 0);
                  }
               }
            } else if (MainCanvas.isInputDownOrHold(32768)) {
               tmpCol = this.gridIndex / this.gridRow;
               ++MainCanvas.curForm.gridSelectRow;
               if (MainCanvas.curForm.gridSelectRow >= this.gridRow) {
                  if (tmpCol >= this.gridCol - 1) {
                     MainCanvas.curForm.gridSelectRow = (byte)(this.gridRow - 1);
                  } else {
                     MainCanvas.curForm.gridSelectRow = 0;
                  }

                  ++tmpCol;
                  if (tmpCol >= this.gridCol) {
                     tmpCol = this.gridCol - 1;
                  }
               }

               this.gridIndex = (byte)(tmpCol * this.gridRow + MainCanvas.curForm.gridSelectRow);
               if (this.gridIndex >= this.canUseNum) {
                  this.gridIndex = (byte)(this.canUseNum - 1);
                  MainCanvas.curForm.gridSelectRow = (byte)(this.gridIndex % this.gridRow);
               }

               this.checkStartColDown();
               if (MainCanvas.curForm.clientCommand == 2424994 || MainCanvas.curForm.clientCommand == 2425072) {
                  label = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
                  if (PCIncrement.m_bBuyMoney != 3) {
                     label.setNum1(PCIncrement.m_bBuyShopping[this.gridIndex]);
                  } else {
                     label.setType((byte)0);
                     label.setText(PCIncrement.m_sBuyShopping[this.gridIndex]);
                  }
               }

               this.selectChangeAction();
               if (MainCanvas.curForm.clientCommand == 3342341) {
                  menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
                  if (this.stuffId[this.gridIndex] != -1) {
                     menuBar.setMenuText("Sử dụng", 0);
                  } else {
                     menuBar.setMenuText("", 0);
                  }
               }
            } else if (MainCanvas.isInputDown(196608)) {
               switch(this.getGridType()) {
               case 0:
                  if (!this.stuffLock[this.gridIndex]) {
                     this.addGridMenuOperate();
                  }
                  break;
               case 1:
                  if (MainCanvas.curForm.clientCommand == -1610612732) {
                     MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
                     this.setShortCut((byte)1);
                     MainCanvas.curForm.foldedIndex = 0;
                     MainCanvas.curForm.addWarningStr();
                  } else if (MainCanvas.curForm.clientCommand == -1610612620 || MainCanvas.curForm.clientCommand == -1610612618) {
                     if (MainCanvas.curForm.clientCommand == -1610612620) {
                        PCHang.AttackSkill[PCHang.m_bSkillIndex] = this.stuffId[this.gridIndex];
                     } else if (MainCanvas.curForm.clientCommand == -1610612618) {
                        PCHang.WatchSkill[PCHang.m_bSkillIndex] = this.stuffId[this.gridIndex];
                     }

                     UIForm.backUIForm();
                     UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(PCHang.m_bSkillIndex + 3);
                     pic.setShowNum(false, (byte)1);
                     pic.quality = this.stuffQuality[this.gridIndex];
                     short picID = this.stuffImageId[this.gridIndex];
                     pic.isWpPic = true;
                     pic.wpIndex = picID;
                     pic.setImg(MainCanvas.stuffMImg);
                     if (MainCanvas.backForms.size() > 0) {
                        MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
                     }
                  }
               }

               switch(MainCanvas.curForm.clientCommand) {
               case 458773:
                  if (UIChat.chatForm.textStr.length() + 4 >= 30) {
                     UITopForm.createLocalTopForm((byte)5, (String)"Ký tự đã vượt quá giới hạn!", "Xác nhận", "", -1, -2);
                  } else {
                     UIChat var10000 = UIChat.chatForm;
                     var10000.textStr = var10000.textStr + "[b" + (this.gridIndex + 1) + "]";
                     UIChat.chatForm.advancedForm(false);
                  }
               default:
                  UIComponent.especialDeal();
               }
            } else if (MainCanvas.isInputDown(2048)) {
               switch(this.getGridType()) {
               case 1:
                  UITextArea area = (UITextArea)MainCanvas.curForm.getComponents().elementAt(4);
                  if (area != null) {
                     area.setTextAreaType((byte)1);
                     this.setFocus(false, MainCanvas.curForm);
                     area.setFocus(true, MainCanvas.curForm);
                  }
               default:
                  switch(MainCanvas.curForm.clientCommand) {
                  case -1610612734:
                     SIShortCut.setGoods();
                     break;
                  case 1376263:
                     MainCanvas.ni.send(1376261);
                     break;
                  case 1703946:
                     this.MailAffirmGoosd();
                  }

                  UIComponent.especialDeal();
               }
            } else if (MainCanvas.isInputDown(1024)) {
               switch(MainCanvas.curForm.clientCommand) {
               case -1610612734:
                  SIShortCut.setSkill();
                  break;
               case 1376263:
                  this.putTradeItem();
                  break;
               case 1703946:
                  if (this.stuffId[this.gridIndex] == -1) {
                     return;
                  }

                  if (this.stuffId[this.gridIndex] != -1 && !this.isCanTrade[this.gridIndex]) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Vật phẩm này không thể gửi", "Xác nhận", "", -1, -2);
                     return;
                  }

                  boolean b = false;

                  int i;
                  for(i = 0; i < accessories.length; ++i) {
                     if (accessories[i][0] == this.gridIndex) {
                        b = true;
                        break;
                     }
                  }

                  for(i = 0; i < accessories.length; ++i) {
                     if (accessories[i][0] == this.gridIndex) {
                        accessories[i][0] = -1;
                        accessories[i][1] = -1;
                        accessories[i][2] = -1;
                        accessories[i][3] = -1;
                        if (PCMail.m_bAccessoriesAmount > 0) {
                           --PCMail.m_bAccessoriesAmount;
                        }
                        break;
                     }

                     if (accessories[i][0] == -1 && !b) {
                        if (PCMail.m_bAccessoriesAmount < accessories.length) {
                           ++PCMail.m_bAccessoriesAmount;
                        }

                        accessories[i][0] = this.gridIndex;
                        accessories[i][1] = this.stuffImageId[this.gridIndex];
                        accessories[i][2] = this.stuffQuality[this.gridIndex];
                        accessories[i][3] = this.stuffNumber[this.gridIndex];
                        if (this.stuffShowMiniNum[this.gridIndex] && this.stuffNumber[this.gridIndex] > 1) {
                           UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                           UITopForm.createLocalTopForm((byte)1, "Số lượng vật phẩm:", "Đồng ý", "Trở về", -1, -1, i, this.gridIndex);
                        }
                        break;
                     }
                  }
               }

               UIComponent.especialDeal();
            }
         }

         if (this.getGridScroll() != null) {
            this.getGridScroll().keyInScroll((short)(this.gridIndex / this.gridRow), true);
         }
      }

   }

   public void keyInMenu() {
      if (MainCanvas.isInputDownOrHold(4096)) {
         this.getMenu().decreaseIndex();
      } else if (MainCanvas.isInputDownOrHold(8192)) {
         this.getMenu().increaseIndex();
      } else if (MainCanvas.isInputDown(196608)) {
         this.getMenu().saveForm();
         severGridIndex = this.gridIndex;
         int cmd;
         UILabel label;
         UILabel myMoneyLabel;
         byte myMoneyWidget;
         switch(super.clientType) {
         case 6:
            switch(this.getGridFormType()) {
            case 0:
               switch(MainCanvas.curForm.clientCommand) {
               case -1610612734:
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  if (cmd != -1) {
                     switch(cmd) {
                     case -268435455:
                        SIManager.getInstance();
                        SIManager.shortCut.clearOneGrid(this, this.gridIndex);
                        break;
                     case -268435454:
                        SIManager.getInstance();
                        SIManager.shortCut.clearAllGrid();
                        break;
                     case -268435453:
                        switch(this.stuffType[this.gridIndex]) {
                        case 1:
                           UIList.intoSkillDetailUI(-1610612730, Player.getInstance().getIND_FromSkillData(this.stuffId[this.gridIndex]));
                           break;
                        case 2:
                           SIShortCut.shortCutId = this.stuffId[this.gridIndex];
                           MainCanvas.ni.send(458762);
                        }
                     case -268435452:
                     default:
                        break;
                     case -268435451:
                        SIShortCut.setSkill();
                        break;
                     case -268435450:
                        SIShortCut.setGoods();
                        break;
                     case -268435449:
                        UIRightMenu.setKey();
                     }
                  }

                  super.isShowMenu = false;
                  return;
               case 458761:
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  if (cmd != -1) {
                     switch(cmd) {
                     case 0:
                        if (this.stuffType[this.gridIndex] == 1) {
                           MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
                           this.setShortCut((byte)2);
                           MainCanvas.curForm.foldedIndex = 0;
                           MainCanvas.curForm.addWarningStr();
                        }
                        break;
                     case 458753:
                        MainCanvas.ni.send(458753);
                     }
                  }

                  super.isShowMenu = false;
                  return;
               case 1966082:
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  PCGem.m_bGemIndex = this.gridIndex;
                  MainCanvas.ni.send(cmd);
                  super.isShowMenu = false;
                  return;
               default:
                  if (this.stuffId[this.gridIndex] != -1) {
                     cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                     if (PCTreasure.treasureMenu) {
                        MainCanvas.ni.send(cmd);
                        super.isShowMenu = false;
                        return;
                     }

                     switch(cmd) {
                     case 458753:
                        m_nGoodsIndex = this.stuffId[this.gridIndex];
                        if (m_nGoodsIndex / 10000 >= 101 && m_nGoodsIndex / 10000 <= 120) {
                           m_nGoods = this.gridIndex;
                           PCGem.m_bEnchaseSucceed = 0;
                           PCGem.m_bAppendMenu = 1;
                        } else {
                           m_nGoods = -1;
                           PCGem.m_bAppendMenu = 0;
                           PCGem.m_bEnchaseSucceed = 0;
                        }

                        MainCanvas.ni.send(cmd);
                        super.isShowMenu = false;
                        return;
                     case 458754:
                        if ((this.stuffId[this.gridIndex] / 10000 < 1011 || this.stuffId[this.gridIndex] / 10000 > 1016) && this.stuffId[this.gridIndex] / 10000 != 1027) {
                           if (this.stuffId[this.gridIndex] / 10000 == 1204) {
                              PCEquip.m_bPropIndex = this.gridIndex;
                           } else {
                              PCGem.m_bGemIndex = -1;
                              PCGem.m_bEnchaseSucceed = 0;
                              PCGem.m_bAppendMenu = 0;
                           }
                        } else {
                           PCGem.m_bGemIndex = this.gridIndex;
                           PCGem.m_bEnchaseSucceed = 0;
                           PCGem.m_bAppendMenu = 0;
                        }

                        useIndex = this.gridIndex;
                        MainCanvas.ni.send(cmd);
                        super.isShowMenu = false;
                        return;
                     case 458755:
                        PCGem.m_bAppendMenu = 0;
                        MainCanvas.ni.send(cmd);
                        this.setEquipPartType(this.stuffEquipPart[this.gridIndex]);
                        super.isShowMenu = false;
                        return;
                     case 458756:
                        if (this.gridExchangeIndex[0] == -1) {
                           PCPackage.exchangeIndex[0] = this.gridIndex;
                           this.gridExchangeIndex[0] = this.gridIndex;
                        }

                        super.isShowMenu = false;
                        return;
                     case 458758:
                        MainCanvas.ni.send(cmd);
                        super.isShowMenu = false;
                        return;
                     case 458759:
                        MainCanvas.curTopForm = new UITopForm((byte)0, (UIForm)null);
                        dropIndex = this.gridIndex;
                        MainCanvas.curTopForm.setTopFormInfo((byte)5, Cons.COLOR_PANEL_BORDER_3, "Xác nhận bỏ vật phẩm này?", Cons.C_STR[2], Cons.C_STR[3], cmd, -1);
                        super.isShowMenu = false;
                        return;
                     case 1245185:
                        UIForm.setCompareTI((byte)0, this.gridIndex);
                        super.isShowMenu = false;
                        return;
                     case 3342337:
                        MainCanvas.ni.send(cmd);
                        super.isShowMenu = false;
                     }
                  } else {
                     MainCanvas.ni.send(458758);
                     super.isShowMenu = false;
                  }

                  return;
               }
            case 1:
               if (this.stuffId[this.gridIndex] != -1) {
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  switch(cmd) {
                  case 458753:
                     MainCanvas.ni.send(cmd);
                     super.isShowMenu = false;
                     return;
                  case 983043:
                     if (!this.stuffShowMiniNum[this.gridIndex]) {
                        UITopForm.buyNum = 1;
                        if (this.stuffColorLevel[this.gridIndex] != 4 && this.stuffColorLevel[this.gridIndex] != 5 && this.stuffColorLevel[this.gridIndex] != 6) {
                           MainCanvas.ni.send(cmd);
                        } else {
                           UITopForm.createLocalTopForm((byte)0, (String)("Muốn bán" + this.stuffName[this.gridIndex]), "Xác nhận", "Hủy", cmd, -18);
                        }
                     } else if (this.stuffNumber[this.gridIndex] == 1) {
                        UITopForm.buyNum = 1;
                        MainCanvas.ni.send(cmd);
                     } else {
                        UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                        UITopForm.createLocalTopForm((byte)1, (String)"Số lượng bán:", "Xác nhận", "Hủy", cmd, -1);
                        MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridIndex]);
                     }

                     super.isShowMenu = false;
                     return;
                  case 983044:
                     MainCanvas.ni.send(cmd);
                     super.isShowMenu = false;
                     return;
                  case 983045:
                     MainCanvas.curTopForm = new UITopForm((byte)0, (UIForm)null);
                     dropIndex = this.gridIndex;
                     MainCanvas.curTopForm.setTopFormInfo((byte)5, Cons.COLOR_PANEL_BORDER_3, "Xác nhận bỏ vật phẩm này?", Cons.C_STR[2], Cons.C_STR[3], cmd, -1);
                     super.isShowMenu = false;
                     return;
                  case 983201:
                     MainCanvas.ni.send(cmd);
                     super.isShowMenu = false;
                     return;
                  case 1245185:
                     UIForm.setCompareTI((byte)0, this.gridIndex);
                     super.isShowMenu = false;
                     return;
                  case 3342337:
                     PCTreasure.unlockMenu = false;
                     MainCanvas.ni.send(cmd);
                     super.isShowMenu = false;
                  }
               } else {
                  MainCanvas.ni.send(983044);
                  super.isShowMenu = false;
               }

               return;
            case 2:
               if (this.stuffId[this.gridIndex] != -1) {
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  switch(cmd) {
                  case 458753:
                     MainCanvas.ni.send(cmd);
                     super.isShowMenu = false;
                     return;
                  case 1179651:
                     if (this.stuffNumber[this.gridIndex] == 1) {
                        PCStorage.keepNum = 1;
                        MainCanvas.ni.send(cmd);
                     } else {
                        UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                        UITopForm.createLocalTopForm((byte)1, (String)"Xin nhập số lượng vật phẩm muốn gửi:", "Đồng ý", "Hủy", cmd, -1);
                        MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridIndex]);
                     }

                     super.isShowMenu = false;
                     return;
                  case 1179652:
                     myMoneyWidget = 10;
                     myMoneyLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(myMoneyWidget);

                     try {
                        storageMoney = myMoneyLabel.getNum1();
                     } catch (NumberFormatException var6) {
                        storageMoney = 0;
                     }

                     UITopForm.MAXNUMBER = storageMoney;
                     UITopForm.createLocalTopForm((byte)1, (String)"Nhập số Vàng:", "Xác nhận", "Hủy", cmd, -1);
                     super.isShowMenu = false;
                     return;
                  case 1179656:
                     PCStorage.moveIndex = 1;
                     if (this.gridExchangeIndex[0] == -1) {
                        PCPackage.exchangeIndex[0] = this.gridIndex;
                        this.gridExchangeIndex[0] = this.gridIndex;
                        PCStorage.setGridMoveToGrid(false);
                     }

                     super.isShowMenu = false;
                     return;
                  case 1179657:
                     MainCanvas.ni.send(cmd);
                     super.isShowMenu = false;
                     return;
                  case 1179658:
                     MainCanvas.ni.send(cmd);
                     super.isShowMenu = false;
                     return;
                  case 1245185:
                     UIForm.setCompareTI((byte)0, this.gridIndex);
                     super.isShowMenu = false;
                     return;
                  case 3342337:
                     MainCanvas.ni.send(cmd);
                     super.isShowMenu = false;
                  }
               } else {
                  switch(this.getMenu().getIndex()) {
                  case 0:
                     int myMoneyWidget = 10;
                     label = (UILabel)MainCanvas.curForm.getComponents().elementAt(myMoneyWidget);

                     try {
                        storageMoney = label.getNum1();
                     } catch (NumberFormatException var5) {
                        storageMoney = 0;
                     }

                     UITopForm.MAXNUMBER = storageMoney;
                     UITopForm.createLocalTopForm((byte)1, (String)"Nhập số vàng:", "Xác nhận", "Hủy", 1179652, -1);
                     super.isShowMenu = false;
                     return;
                  case 1:
                     MainCanvas.ni.send(1179657);
                     super.isShowMenu = false;
                     return;
                  case 2:
                     MainCanvas.ni.send(1179658);
                     super.isShowMenu = false;
                  }
               }

               return;
            case 3:
            case 16:
            default:
               return;
            case 4:
               if (this.stuffId[this.gridIndex] != -1) {
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  if (cmd == 0) {
                     super.isShowMenu = false;
                     this.putTradeItem();
                  } else {
                     if (cmd == 3342337) {
                        PCTreasure.unlockMenu = false;
                     }

                     MainCanvas.ni.send(cmd);
                     super.isShowMenu = false;
                  }

                  return;
               }

               return;
            case 5:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               int i;
               if (cmd == -1610612702) {
                  if (this.stuffId[this.gridIndex] == -1) {
                     return;
                  }

                  if (this.stuffId[this.gridIndex] != -1 && !this.isCanTrade[this.gridIndex]) {
                     super.isShowMenu = false;
                     UITopForm.createLocalTopForm((byte)0, (String)"Vật phẩm này không thể gửi", "Đồng ý", "", -1, -2);
                     return;
                  }

                  for(i = 0; i < accessories.length; ++i) {
                     if (accessories[i][0] == -1) {
                        accessories[i][0] = this.gridIndex;
                        accessories[i][1] = this.stuffImageId[this.gridIndex];
                        accessories[i][2] = this.stuffQuality[this.gridIndex];
                        accessories[i][3] = this.stuffNumber[this.gridIndex];
                        if (this.stuffShowMiniNum[this.gridIndex] && this.stuffNumber[this.gridIndex] > 1) {
                           UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                           UITopForm.createLocalTopForm((byte)1, "Số lượng vật phẩm:", "Xác nhận", "Trở về", -1, -1, i, this.gridIndex);
                           MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridIndex]);
                        }
                        break;
                     }
                  }

                  if (PCMail.m_bAccessoriesAmount < accessories.length) {
                     ++PCMail.m_bAccessoriesAmount;
                  }

                  super.isShowMenu = false;
                  return;
               } else if (cmd == -1610612701) {
                  super.isShowMenu = false;
                  this.MailAffirmGoosd();
                  return;
               } else if (cmd == 458753) {
                  MainCanvas.ni.send(458753);
                  PCMail.m_bForbidEnchase = 1;
                  super.isShowMenu = false;
                  return;
               } else {
                  if (cmd == -1610612700) {
                     super.isShowMenu = false;

                     for(i = 0; i < accessories.length; ++i) {
                        if (accessories[i][0] == this.gridIndex) {
                           accessories[i][0] = -1;
                           accessories[i][1] = -1;
                           accessories[i][2] = -1;
                           accessories[i][3] = -1;
                           break;
                        }
                     }

                     if (PCMail.m_bAccessoriesAmount > 0) {
                        --PCMail.m_bAccessoriesAmount;
                        return;
                     }
                  } else if (cmd == 3342337) {
                     PCTreasure.unlockMenu = false;
                     MainCanvas.ni.send(3342337);
                     super.isShowMenu = false;
                     return;
                  }

                  return;
               }
            case 6:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               if (cmd == 3342337) {
                  PCTreasure.unlockMenu = false;
               }

               MainCanvas.ni.send(cmd);
               super.isShowMenu = false;
               return;
            case 7:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case -1610612715:
                  if (this.stuffId[this.gridIndex] != -1 && !this.isCanTrade[this.gridIndex]) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Vật phẩm này không thể đấu giá!", "Xác nhận", "", -1, -2);
                     super.isShowMenu = false;
                     return;
                  }

                  AuctionBag[0] = this.gridIndex;
                  AuctionBag[1] = this.stuffImageId[this.gridIndex];
                  AuctionBag[2] = this.stuffQuality[this.gridIndex];
                  AuctionBag[3] = this.stuffNumber[this.gridIndex];
                  m_nAuctionGoodsName = this.stuffName[this.gridIndex];
                  PCAuction.m_bWhetherAccumulate = false;
                  if (this.stuffShowMiniNum[this.gridIndex]) {
                     PCAuction.m_bWhetherAccumulate = true;
                  }

                  if (AuctionBag[3] == 1 && this.stuffShowMiniNum[this.gridIndex]) {
                     UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                     PCAuction.ChooseGoods((byte)1);
                     super.isShowMenu = false;
                  } else if (AuctionBag[3] > 1) {
                     UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                     UITopForm.createLocalTopForm((byte)1, (String)"Xin nhập số lượng", "Đồng ý", "Trở về", -1, -1);
                     MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridIndex]);
                     super.isShowMenu = false;
                  } else {
                     this.stuffShowMiniNum[this.gridIndex] = false;
                     PCAuction.ChooseGoods((byte)AuctionBag[3]);
                  }
                  break;
               case -1610612714:
                  PCAuction.RetrieveGoods((byte)1);
                  break;
               case -1610612713:
                  PCAuction.ConfirmAuction();
                  break;
               default:
                  if (cmd == 3342337) {
                     PCTreasure.unlockMenu = false;
                  }

                  PCAuction.m_bForbidEnchase = 1;
                  MainCanvas.ni.send(cmd);
               }

               super.isShowMenu = false;
               return;
            case 8:
               if (this.stuffId[this.gridIndex] != -1) {
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  switch(cmd) {
                  case 458753:
                     if (this.stuffId[this.gridIndex] / 10000 >= 101 && this.stuffId[this.gridIndex] / 10000 <= 120) {
                        m_nGoodsIndex = this.stuffId[this.gridIndex];
                        m_nGoods = this.gridIndex;
                        PCGem.m_bEnchaseSucceed = 0;
                     } else {
                        m_nGoods = -1;
                        m_nGoodsIndex = 0;
                        PCGem.m_bEnchaseSucceed = 0;
                     }

                     MainCanvas.ni.send(cmd);
                     super.isShowMenu = false;
                     return;
                  case 458755:
                     this.setEquipPartType(this.stuffEquipPart[this.gridIndex]);
                     switch(equipPartType) {
                     case 1:
                     case 2:
                     case 3:
                        UIForm.equipEffect();
                        break;
                     default:
                        MainCanvas.ni.send(cmd);
                     }

                     super.isShowMenu = false;
                     return;
                  case 458757:
                     UIForm.setCompareTI((byte)0, this.gridIndex);
                     super.isShowMenu = false;
                     return;
                  case 917510:
                     MainCanvas.ni.send(cmd);
                     super.isShowMenu = false;
                     return;
                  case 917511:
                     MainCanvas.curTopForm = new UITopForm((byte)0, (UIForm)null);
                     dropIndex = this.gridIndex;
                     MainCanvas.curTopForm.setTopFormInfo((byte)5, Cons.COLOR_PANEL_BORDER_3, "Xác nhận bỏ vật phẩm này?", Cons.C_STR[2], Cons.C_STR[3], cmd, -1);
                     super.isShowMenu = false;
                     return;
                  case 2031636:
                     fromEquipUnseal = true;
                     PCUnsealGemCarve.m_bPrimaryGemIndex = this.gridIndex;
                     MainCanvas.ni.send(cmd);
                     super.isShowMenu = false;
                     return;
                  case 3342337:
                     MainCanvas.ni.send(cmd);
                     super.isShowMenu = false;
                  }
               } else {
                  MainCanvas.ni.send(917510);
                  super.isShowMenu = false;
               }

               return;
            case 9:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               MainCanvas.ni.send(cmd);
               super.isShowMenu = false;
               return;
            case 10:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               PCGem.m_bEnchaseRoute = 1;
               PCGem.m_bEquipIndex = this.gridIndex;
               switch(cmd) {
               case 1966087:
                  MainCanvas.ni.send(1966087);
                  break;
               case 1966093:
                  MainCanvas.ni.send(1966093);
                  break;
               case 3342337:
                  PCTreasure.unlockMenu = false;
                  MainCanvas.ni.send(3342337);
               }

               super.isShowMenu = false;
               return;
            case 11:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case -1610612705:
                  this.unSealPlace();
                  break;
               case 2031617:
                  PCUnsealGemCarve.m_bExamine = this.gridIndex;
                  MainCanvas.ni.send(2031617);
                  break;
               case 2031618:
                  if (PCUnsealGemCarve.m_bPrimaryGemIndex == -1) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Xin chọn trang bị cần khai phong.", "Xác nhận", "", -1, -2);
                     super.isShowMenu = false;
                     return;
                  }

                  if (PCUnsealGemCarve.m_bCarveSymbolIndex == -1) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Xin chọn quyển khai phong tương ứng với trang bị", "Xác nhận", "", -1, -2);
                     super.isShowMenu = false;
                     return;
                  }

                  MainCanvas.ni.send(2031618);
                  break;
               case 2031648:
                  MainCanvas.ni.send(2031648);
                  break;
               case 3342337:
                  PCTreasure.unlockMenu = false;
                  MainCanvas.ni.send(3342337);
               }

               super.isShowMenu = false;
               return;
            case 12:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case -1610612705:
                  AuctionBag[0] = this.gridIndex;
                  AuctionBag[1] = this.stuffImageId[this.gridIndex];
                  AuctionBag[2] = this.stuffQuality[this.gridIndex];
                  AuctionBag[3] = this.stuffNumber[this.gridIndex];
                  if (PCUnsealGemCarve.m_bFocus == 1) {
                     if (this.stuffId[this.gridIndex] / 10000 >= 1014 && this.stuffId[this.gridIndex] / 10000 <= 1016) {
                        PCUnsealGemCarve.AppendPicture(4, true, 3, true);
                        MainCanvas.ni.send(2031622);
                        break;
                     }

                     UITopForm.createLocalTopForm((byte)0, (String)"Chỉ có nguyên thạch mới có thể cho vào vị trí này.", "Xác nhận", "", -1, -2);
                  } else if (PCUnsealGemCarve.m_bFocus == 2) {
                     if (this.stuffId[this.gridIndex] / 10000 >= 1017 && this.stuffId[this.gridIndex] / 10000 <= 1019) {
                        PCUnsealGemCarve.AppendPicture(6, true, 1, true);
                        break;
                     }

                     UITopForm.createLocalTopForm((byte)0, (String)"Chỉ có Phù Điêu Khắc mới có thể cho vào vị trí này. ", "Xác nhận", "", -1, -2);
                  } else if (PCUnsealGemCarve.m_bFocus == 3) {
                     if (this.stuffId[this.gridIndex] / 10000 == 1021) {
                        PCUnsealGemCarve.AppendPicture(8, true, 2, true);
                     } else {
                        UITopForm.createLocalTopForm((byte)0, (String)"Chỉ có Phù An Toàn Điêu Khắc Nguyên Thạch mới có thể cho vào vị trí này.", "Xác nhận", "", -1, -2);
                     }
                  } else {
                     if (this.stuffId[this.gridIndex] / 10000 >= 1014 && this.stuffId[this.gridIndex] / 10000 <= 1016 || this.stuffId[this.gridIndex] / 10000 == 1021 || this.stuffId[this.gridIndex] / 10000 >= 1017 && this.stuffId[this.gridIndex] / 10000 <= 1019) {
                        if (this.stuffId[this.gridIndex] / 10000 >= 1014 && this.stuffId[this.gridIndex] / 10000 <= 1016) {
                           if (PCUnsealGemCarve.m_bPrimaryGemIndex == -1) {
                              PCUnsealGemCarve.AppendPicture(4, true, 3, true);
                              MainCanvas.ni.send(2031622);
                           } else if (PCUnsealGemCarve.m_bPrimaryGemIndex != this.gridIndex) {
                              UITopForm.createLocalTopForm((byte)0, (String)"Có muốn che Nguyên Thạch đã chọn hiện tại không?", "Xác nhận", "Hủy", -6, -9);
                           }
                           break;
                        }

                        if (this.stuffId[this.gridIndex] / 10000 >= 1017 && this.stuffId[this.gridIndex] / 10000 <= 1019) {
                           if (PCUnsealGemCarve.m_bCarveSymbolIndex == -1) {
                              PCUnsealGemCarve.AppendPicture(6, true, 1, true);
                           } else if (PCUnsealGemCarve.m_bCarveSymbolIndex != this.gridIndex) {
                              UITopForm.createLocalTopForm((byte)0, (String)"Có muốn che Phù Điêu Khắc đã chọn hiện tại không?", "Xác nhận", "Hủy", -7, -9);
                           }
                           break;
                        }

                        if (this.stuffId[this.gridIndex] / 10000 == 1021) {
                           if (PCUnsealGemCarve.m_bJadeIndex == -1) {
                              PCUnsealGemCarve.AppendPicture(8, true, 2, true);
                           } else if (PCUnsealGemCarve.m_bJadeIndex != this.gridIndex) {
                              UITopForm.createLocalTopForm((byte)0, (String)"Có muốn che Phù An Toàn Điêu Khắc Nguyên Thạch đã chọn hiện tại không?", "Xác nhận", "Hủy", -8, -9);
                           }
                        }
                        break;
                     }

                     UITopForm.createLocalTopForm((byte)0, (String)"Vật phẩm này không dùng để Điêu Khắc Vật Phẩm.", "Xác nhận", "", -1, -2);
                  }
                  break;
               case 2031623:
                  if (PCUnsealGemCarve.m_bPrimaryGemIndex == -1) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Chọn lựa Nguyên Thạch để Điêu Khắc", "Xác nhận", "", -1, -2);
                     super.isShowMenu = false;
                     return;
                  }

                  if (PCUnsealGemCarve.m_bCarveSymbolIndex == -1) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Xin chọn Phù Điêu Khắc đối với Nguyên Thạch cần Điêu Khắc", "Xác nhận", "", -1, -2);
                     super.isShowMenu = false;
                     return;
                  }

                  MainCanvas.ni.send(2031623);
                  break;
               case 2031635:
                  PCUnsealGemCarve.m_bExamine = this.gridIndex;
                  MainCanvas.ni.send(2031635);
                  break;
               case 2031649:
                  MainCanvas.ni.send(2031649);
                  break;
               case 3342337:
                  PCTreasure.unlockMenu = false;
                  MainCanvas.ni.send(3342337);
               }

               super.isShowMenu = false;
               return;
            case 13:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case -1610612705:
                  AuctionBag[0] = this.gridIndex;
                  AuctionBag[1] = this.stuffImageId[this.gridIndex];
                  AuctionBag[2] = this.stuffQuality[this.gridIndex];
                  AuctionBag[3] = this.stuffNumber[this.gridIndex];
                  if (PCGemJoinOrRemove.m_bFocus == 1) {
                     if (this.stuffId[this.gridIndex] / 10000 >= 1014 && this.stuffId[this.gridIndex] / 10000 <= 1016) {
                        if (PCGemJoinOrRemove.m_bGemAmount_1 >= 5) {
                           UITopForm.createLocalTopForm((byte)0, (String)"Đã cho vào 5 viên Nguyên Thạch, không thể cho thêm.", "Xác nhận", "", -1, -2);
                           super.isShowMenu = false;
                           return;
                        }

                        if (PCGemJoinOrRemove.m_bGemAmount_1 == 0) {
                           UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                        } else if (this.stuffNumber[this.gridIndex] <= (byte)(5 - PCGemJoinOrRemove.m_bGemAmount_1)) {
                           UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                        } else if (this.stuffNumber[this.gridIndex] > (byte)(5 - PCGemJoinOrRemove.m_bGemAmount_1)) {
                           UITopForm.MAXNUMBER = (byte)(5 - PCGemJoinOrRemove.m_bGemAmount_1);
                        }

                        UITopForm.createLocalTopForm((byte)1, (String)"Xin nhập số Nguyên Thạch:", "Đồng ý", "Hủy", -1610612617, -1);
                        break;
                     }

                     UITopForm.createLocalTopForm((byte)0, (String)"Chỉ có nguyên thạch mới có thể cho vào vị trí này.", "Xác nhận", "", -1, -2);
                  } else {
                     if ((this.stuffId[this.gridIndex] / 10000 < 1014 || this.stuffId[this.gridIndex] / 10000 > 1016) && this.stuffId[this.gridIndex] / 10000 != 1024) {
                        UITopForm.createLocalTopForm((byte)0, (String)"Vật phẩm này không thể dùng để hợp thành", "Xác nhận", "", -1, -2);
                        super.isShowMenu = false;
                        return;
                     }

                     if (this.stuffId[this.gridIndex] / 10000 >= 1014 && this.stuffId[this.gridIndex] / 10000 <= 1016) {
                        if (PCGemJoinOrRemove.m_bGemAmount_1 >= 5) {
                           UITopForm.createLocalTopForm((byte)0, (String)"Đã cho vào 5 viên Nguyên Thạch, không thể cho thêm nữa.", "Xác nhận", "", -1, -2);
                           super.isShowMenu = false;
                           return;
                        }

                        if (PCGemJoinOrRemove.m_bGemAmount_1 == 0) {
                           UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                        } else if (this.stuffNumber[this.gridIndex] <= (byte)(5 - PCGemJoinOrRemove.m_bGemAmount_1)) {
                           UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                        } else if (this.stuffNumber[this.gridIndex] > (byte)(5 - PCGemJoinOrRemove.m_bGemAmount_1)) {
                           UITopForm.MAXNUMBER = (byte)(5 - PCGemJoinOrRemove.m_bGemAmount_1);
                        }

                        UITopForm.createLocalTopForm((byte)1, (String)"Xin nhập số lượng Nguyên Thạch:", "Đồng ý", "Hủy", -1610612617, -1);
                     }
                  }
                  break;
               case 2031650:
                  MainCanvas.ni.send(2031650);
                  break;
               case 2228227:
                  if (PCGemJoinOrRemove.m_bGemIndex == -1) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Xin cho vào Nguyên Thạch hợp thành.", "Đồng ý", "", -1, -2);
                     super.isShowMenu = false;
                     return;
                  }

                  MainCanvas.ni.send(2228227);
                  break;
               case 2228231:
                  PCGemJoinOrRemove.m_bExamine = this.gridIndex;
                  MainCanvas.ni.send(2228231);
                  break;
               case 3342337:
                  PCTreasure.unlockMenu = false;
                  MainCanvas.ni.send(3342337);
               }

               super.isShowMenu = false;
               return;
            case 14:
               UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
               UIPicture pic1 = (UIPicture)MainCanvas.curForm.getComponents().elementAt(6);
               int cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case -1610612705:
                  AuctionBag[0] = this.gridIndex;
                  AuctionBag[1] = this.stuffImageId[this.gridIndex];
                  AuctionBag[2] = this.stuffQuality[this.gridIndex];
                  AuctionBag[3] = this.stuffNumber[this.gridIndex];
                  if (PCGem.m_bFocus == 1) {
                     if (this.stuffId[this.gridIndex] / 10000 == 118) {
                        UITopForm.createLocalTopForm((byte)0, (String)"Chỉ có trang bị mới được cho vào.", "Đồng ý", "", -1, -2);
                        super.isShowMenu = false;
                        return;
                     }

                     if (this.stuffId[this.gridIndex] / 10000 >= 101 && this.stuffId[this.gridIndex] / 10000 <= 120) {
                        PCGem.m_bEquipIndex = this.gridIndex;
                        pic.setFocus(true, MainCanvas.curForm);
                        this.setFocus(false, MainCanvas.curForm);
                        MainCanvas.ni.send(1966098);
                        break;
                     }

                     UITopForm.createLocalTopForm((byte)0, (String)"Chỉ có trang bị mới được cho vào.", "Đồng ý", "", -1, -2);
                  } else {
                     short picID;
                     if (PCGem.m_bFocus == 2) {
                        if (this.stuffId[this.gridIndex] / 10000 == 1025) {
                           PCGem.m_bGemIndex = this.gridIndex;
                           pic1.setShowNum(true, (byte)1);
                           pic1.quality = (byte)AuctionBag[2];
                           picID = AuctionBag[1];
                           pic1.isWpPic = true;
                           pic1.wpIndex = picID;
                           pic1.setImg(MainCanvas.stuffMImg);
                           pic1.setFocus(true, MainCanvas.curForm);
                           this.setFocus(false, MainCanvas.curForm);
                        } else {
                           UITopForm.createLocalTopForm((byte)0, (String)"Chỉ có Phù Giải Trừ Bảo Thạch mới có thể cho vào vị trí này.", "Đồng ý", "", -1, -2);
                        }
                     } else {
                        if (this.stuffId[this.gridIndex] / 10000 == 118) {
                           UITopForm.createLocalTopForm((byte)0, (String)"Vật phẩm này không dùng để giải trừ bảo thạch.", "Đồng ý", "", -1, -2);
                           super.isShowMenu = false;
                           return;
                        }

                        if ((this.stuffId[this.gridIndex] / 10000 < 101 || this.stuffId[this.gridIndex] / 10000 > 120) && this.stuffId[this.gridIndex] / 10000 != 1025) {
                           UITopForm.createLocalTopForm((byte)0, (String)"Vật phẩm này không dùng để giải trừ bảo thạch.", "Đồng ý", "", -1, -2);
                        } else if (this.stuffId[this.gridIndex] / 10000 >= 101 && this.stuffId[this.gridIndex] / 10000 <= 120) {
                           if (PCGem.m_bEquipIndex == -1) {
                              PCGem.m_bEquipIndex = this.gridIndex;
                              pic.setFocus(true, MainCanvas.curForm);
                              this.setFocus(false, MainCanvas.curForm);
                              MainCanvas.ni.send(1966098);
                           } else if (PCGem.m_bEquipIndex != this.gridIndex) {
                              UITopForm.createLocalTopForm((byte)0, (String)"Có muốn che trang bị hiện tại không?", "Xác nhận", "Hủy", -13, -9);
                           }
                        } else if (this.stuffId[this.gridIndex] / 10000 == 1025) {
                           if (PCGem.m_bGemIndex == -1) {
                              PCGem.m_bGemIndex = this.gridIndex;
                              pic1.setShowNum(true, (byte)1);
                              pic1.quality = (byte)AuctionBag[2];
                              picID = AuctionBag[1];
                              pic1.isWpPic = true;
                              pic1.wpIndex = picID;
                              pic1.setImg(MainCanvas.stuffMImg);
                              pic1.setFocus(true, MainCanvas.curForm);
                              this.setFocus(false, MainCanvas.curForm);
                           } else if (PCGem.m_bGemIndex != this.gridIndex) {
                              UITopForm.createLocalTopForm((byte)0, (String)"Có muốn che Phù Giải Trừ Bảo Thạch chọn hiện tại không?", "Xác nhận", "Hủy", -14, -9);
                           }
                        }
                     }
                  }
                  break;
               case 1966099:
                  if (PCGem.m_bEquipIndex == -1) {
                     if (MainCanvas.backForms.size() > 0) {
                        MainCanvas.backForms.removeElementAt(MainCanvas.backForms.size() - 1);
                     }

                     UITopForm.createLocalTopForm((byte)0, (String)"Xin cho vào trang bị để tiến hành giải trừ Bảo Thạch.", "Đồng ý", "", -1, -2);
                     super.isShowMenu = false;
                     return;
                  }

                  MainCanvas.ni.send(1966099);
                  break;
               case 1966113:
                  PCGem.m_bExamine = this.gridIndex;
                  MainCanvas.ni.send(1966113);
                  break;
               case 2031651:
                  MainCanvas.ni.send(2031651);
                  break;
               case 3342337:
                  PCTreasure.unlockMenu = false;
                  MainCanvas.ni.send(3342337);
               }

               super.isShowMenu = false;
               return;
            case 15:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               PCProduce.enchantingIndex = severGridIndex;
               PCProduce.enchantingRoute = 1;
               if (cmd == 3342337) {
                  PCTreasure.unlockMenu = false;
               }

               MainCanvas.ni.send(cmd);
               super.isShowMenu = false;
               return;
            case 17:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               PCEquip.m_bOrigin = 0;
               PCEquip.m_bEquipIndex = severGridIndex;
               if (cmd == 3342337) {
                  PCTreasure.unlockMenu = false;
               }

               MainCanvas.ni.send(cmd);
               super.isShowMenu = false;
               return;
            case 18:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case -1610612705:
                  AuctionBag[0] = this.gridIndex;
                  AuctionBag[1] = this.stuffImageId[this.gridIndex];
                  AuctionBag[2] = this.stuffQuality[this.gridIndex];
                  AuctionBag[3] = this.stuffNumber[this.gridIndex];
                  PCGemJoinOrRemove.m_sName = this.stuffName[this.gridIndex];
                  PCGemJoinOrRemove.m_sNameColo = this.stuffColorLevel[this.gridIndex];
                  MainCanvas.ni.send(2228257);
                  super.isShowMenu = false;
                  break;
               case 2228227:
                  if (PCGemJoinOrRemove.m_bGemIndex == -1) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Xin cho vào nguyên thạch để hợp thành", "Đồng ý", "", -1, -2);
                     super.isShowMenu = false;
                     return;
                  }

                  MainCanvas.ni.send(2228227);
                  break;
               case 2228231:
                  PCGemJoinOrRemove.m_bExamine = this.gridIndex;
                  MainCanvas.ni.send(2228231);
                  break;
               case 2228258:
                  if (PCGemJoinOrRemove.m_bGemIndex == -1) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Xin cho bảo thạch vào để Hoàn nguyên.", "Đồng ý", "", -1, -2);
                     super.isShowMenu = false;
                     return;
                  }

                  MainCanvas.ni.send(2228258);
                  break;
               case 2228260:
                  MainCanvas.ni.send(2228260);
                  break;
               case 3342337:
                  PCTreasure.unlockMenu = false;
                  MainCanvas.ni.send(3342337);
               }

               super.isShowMenu = false;
               return;
            case 19:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case 2031617:
                  PCUnsealGemCarve.m_bExamine = this.gridIndex;
                  break;
               case 3342337:
                  PCTreasure.unlockMenu = false;
                  break;
               case 3342357:
               case 3342358:
                  this.strengthenPlace();
               case 3342359:
               case 3342361:
               }

               MainCanvas.ni.send(cmd);
               super.isShowMenu = false;
               return;
            case 20:
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case 917526:
               case 917527:
                  this.strengthenPlace();
               case 917528:
               case 917530:
               default:
                  break;
               case 2031617:
                  PCUnsealGemCarve.m_bExamine = this.gridIndex;
                  break;
               case 3342337:
                  PCTreasure.unlockMenu = false;
               }

               MainCanvas.ni.send(cmd);
               super.isShowMenu = false;
               return;
            }
         case 15:
         case 42:
            if (this.stuffId[this.gridIndex] != -1) {
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case 3:
                  super.isShowMenu = false;
                  break;
               case 983041:
                  MainCanvas.ni.send(cmd);
                  super.isShowMenu = false;
                  break;
               case 983042:
                  if (!this.stuffShowMiniNum[this.gridIndex]) {
                     if (this.stuffMaxNum[this.gridIndex] == 1) {
                        UITopForm.buyNum = 1;
                        MainCanvas.ni.send(cmd);
                     } else {
                        UITopForm.MAXNUMBER = this.stuffMaxNum[this.gridIndex];
                        MainCanvas.curTopForm = new UITopForm((byte)1, (UIForm)null);
                        MainCanvas.curTopForm.setTopFormInfo((byte)5, Cons.COLOR_PANEL_BORDER_3, "Xin nhập số lượng", Cons.C_STR[2], Cons.C_STR[3], cmd, -1);
                        MainCanvas.curTopForm.setNumber(1);
                     }
                  } else if (this.stuffNumber[this.gridIndex] == 1) {
                     UITopForm.buyNum = 1;
                     MainCanvas.ni.send(cmd);
                  } else {
                     if (this.stuffMaxNum[this.gridIndex] < this.stuffNumber[this.gridIndex]) {
                        UITopForm.MAXNUMBER = this.stuffMaxNum[this.gridIndex];
                     } else {
                        UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                     }

                     MainCanvas.curTopForm = new UITopForm((byte)1, (UIForm)null);
                     MainCanvas.curTopForm.setTopFormInfo((byte)5, Cons.COLOR_PANEL_BORDER_3, "Xin nhập số lượng", Cons.C_STR[2], Cons.C_STR[3], cmd, -1);
                     MainCanvas.curTopForm.setNumber(1);
                  }

                  super.isShowMenu = false;
                  break;
               case 1245185:
                  UIForm.setCompareTI((byte)2, this.gridIndex);
                  super.isShowMenu = false;
               }
            }
            break;
         case 29:
            if (this.stuffId[this.gridIndex] != -1) {
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case 1179650:
                  MainCanvas.ni.send(cmd);
                  super.isShowMenu = false;
                  return;
               case 1179653:
                  if (this.stuffNumber[this.gridIndex] == 1) {
                     PCStorage.keepNum = 1;
                     MainCanvas.ni.send(cmd);
                  } else {
                     UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                     UITopForm.createLocalTopForm((byte)1, (String)"Xin nhập số lượng vật phẩm lấy ra:", "Đồng ý", "Hủy", cmd, -1);
                     MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridIndex]);
                  }

                  super.isShowMenu = false;
                  return;
               case 1179654:
                  myMoneyWidget = 5;
                  myMoneyLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(myMoneyWidget);
                  storageMoney = Integer.parseInt(myMoneyLabel.getText());
                  UITopForm.MAXNUMBER = storageMoney;
                  UITopForm.createLocalTopForm((byte)1, (String)"Rút Vàng:", "Đồng ý", "Hủy", cmd, -1);
                  super.isShowMenu = false;
                  return;
               case 1179655:
                  MainCanvas.ni.send(cmd);
                  super.isShowMenu = false;
                  return;
               case 1179656:
                  PCStorage.moveIndex = 0;
                  if (this.gridExchangeIndex[0] == -1) {
                     PCPackage.exchangeIndex[0] = this.gridIndex;
                     this.gridExchangeIndex[0] = this.gridIndex;
                     PCStorage.setGridMoveToGrid(false);
                  }

                  super.isShowMenu = false;
                  return;
               case 1245185:
                  UIForm.setCompareTI((byte)3, this.gridIndex);
                  super.isShowMenu = false;
                  return;
               case 3342339:
                  MainCanvas.ni.send(cmd);
                  super.isShowMenu = false;
               }
            } else {
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case 1179654:
                  myMoneyWidget = 5;
                  myMoneyLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(myMoneyWidget);
                  storageMoney = Integer.parseInt(myMoneyLabel.getText());
                  UITopForm.MAXNUMBER = storageMoney;
                  UITopForm.createLocalTopForm((byte)1, (String)"Rút Vàng:", "Đồng ý", "Hủy", cmd, -1);
                  super.isShowMenu = false;
                  return;
               case 1179655:
                  MainCanvas.ni.send(cmd);
                  super.isShowMenu = false;
               }
            }
            break;
         case 53:
            if (this.stuffId[this.gridIndex] != -1) {
               switch(this.getMenu().getIndex()) {
               case 0:
                  MainCanvas.ni.send(1769475);
                  super.isShowMenu = false;
                  break;
               case 1:
                  MainCanvas.ni.send(1769481);
                  super.isShowMenu = false;
               }
            }
            break;
         case 148:
            if (this.stuffId[this.gridIndex] != -1) {
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               PCIncrement.m_nIndex = this.gridIndex;
               PCIncrement.m_nRebirthID = 8;
               switch(cmd) {
               case 2424993:
                  PCIncrement.m_bSendChoose = true;
                  if (PCIncrement.m_bBuyRestrictShopping[this.gridIndex] == 0) {
                     if (!MainCanvas.isCMWAP && (PCIncrement.m_bBuyMoney == 3 || PCIncrement.m_bBuyMoney == 8)) {
                        UITopForm.createBuyTipTopForm();
                     } else {
                        UITopForm.MAXNUMBER = 20;
                        UITopForm.createLocalTopForm((byte)1, "Xin Các hạ hãy nhập số lượng", "Đồng ý", "Trở về", 2424993, -1, -1, -1);
                        MainCanvas.curTopForm.setNumber(1);
                     }
                  } else if (PCIncrement.m_bBuyRestrictShopping[this.gridIndex] == 1) {
                     if (!MainCanvas.isCMWAP && (PCIncrement.m_bBuyMoney == 3 || PCIncrement.m_bBuyMoney == 8)) {
                        UITopForm.createBuyTipTopForm();
                     } else {
                        MainCanvas.ni.send(2424993);
                     }
                  }

                  super.isShowMenu = false;
                  break;
               case 2424995:
                  MainCanvas.ni.send(2424995);
                  super.isShowMenu = false;
                  break;
               case 2425079:
                  MainCanvas.ni.send(2425079);
                  super.isShowMenu = false;
                  break;
               case 2425080:
                  MainCanvas.ni.send(2425080);
                  super.isShowMenu = false;
                  break;
               case 2686977:
                  PCMonthly.getInstance().baoyueItem = this.stuffId[this.gridIndex];
                  MainCanvas.ni.send(2686977);
                  super.isShowMenu = false;
               }
            }
            break;
         case 160:
            switch(MainCanvas.curForm.clientCommand) {
            case 2555908:
               if (this.stuffId[this.gridIndex] != -1) {
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  switch(cmd) {
                  case 2555909:
                     label = (UILabel)MainCanvas.curForm.getComponents().elementAt(9);
                     if (PCFarm.m_nPropPrice[this.gridIndex] > label.getNum1()) {
                        UITopForm.createLocalTopForm((byte)0, (String)("Tiền rau không đủ để mua" + this.stuffName[this.gridIndex]), "Xác nhận", "", -1, -2);
                        super.isShowMenu = false;
                        return;
                     }

                     PCFarm.m_nBackpackIndex = this.gridIndex;
                     UITopForm.MAXNUMBER = label.getNum1() / PCFarm.m_nPropPrice[this.gridIndex];
                     if (UITopForm.MAXNUMBER >= 99) {
                        UITopForm.MAXNUMBER = 99;
                     }

                     PCFarm.m_nBuyGermCode = this.stuffId[this.gridIndex];
                     UITopForm.createLocalTopForm((byte)1, (String)"Nhập số lượng mua", "Xác nhận", "Hủy", cmd, -1);
                     MainCanvas.curTopForm.setNumber(1);
                     break;
                  case 2555968:
                     PCFarm.m_nBackpackIndex = this.gridIndex;
                     MainCanvas.ni.send(cmd);
                  }
               }
               break;
            case 2555957:
               if (this.stuffId[this.gridIndex] != -1) {
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  switch(cmd) {
                  case 2555958:
                     label = (UILabel)MainCanvas.curForm.getComponents().elementAt(9);
                     if (PCFarm.m_nPropPrice[this.gridIndex] > label.getNum1()) {
                        UITopForm.createLocalTopForm((byte)0, (String)("Tiền rau không đủ để mua" + this.stuffName[this.gridIndex]), "Xác nhận", "", -1, -2);
                        super.isShowMenu = false;
                        return;
                     }

                     PCFarm.m_nBackpackIndex = this.gridIndex;
                     UITopForm.MAXNUMBER = label.getNum1() / PCFarm.m_nPropPrice[this.gridIndex];
                     if (UITopForm.MAXNUMBER >= 99) {
                        UITopForm.MAXNUMBER = 99;
                     }

                     PCFarm.m_nBuyGermCode = this.stuffId[this.gridIndex];
                     UITopForm.createLocalTopForm((byte)1, (String)"Nhập số lượng", "Xác nhận", "Hủy", cmd, -1);
                     MainCanvas.curTopForm.setNumber(1);
                     break;
                  case 2555969:
                     PCFarm.m_nBackpackIndex = this.gridIndex;
                     MainCanvas.ni.send(cmd);
                  }
               }
            }

            super.isShowMenu = false;
            break;
         case 164:
            switch(MainCanvas.curForm.clientCommand) {
            case 2555907:
               if (!this.stuffName[this.gridIndex].equals("") && this.stuffName[this.gridIndex] != null) {
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  if (cmd == 2555962) {
                     PCFarm.m_nBackpackIndex = this.gridIndex;
                  } else {
                     if (cmd == 2555963) {
                        PCFarm.m_nBackpackIndex = this.gridIndex;
                        UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                        UITopForm.createLocalTopForm((byte)1, (String)"Nhập số lượng bỏ", "Xác nhận", "Hủy", 2555963, -1);
                        MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridIndex]);
                        super.isShowMenu = false;
                        return;
                     }

                     if (cmd == 2555943) {
                        PCFarm.m_nBackpackIndex = this.gridIndex;
                        UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                        UITopForm.createLocalTopForm((byte)1, (String)"Nhập số lượng đem tặng", "Xác nhận", "Hủy", 2555943, -16);
                        MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridIndex]);
                        super.isShowMenu = false;
                        return;
                     }
                  }

                  MainCanvas.ni.send(cmd);
               }
               break;
            case 2555908:
               if (!this.stuffName[this.gridIndex].equals("") && this.stuffName[this.gridIndex] != null) {
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  if (cmd == 2555962) {
                     PCFarm.m_nBackpackIndex = this.gridIndex;
                  } else if (cmd == 2555970) {
                     PCFarm.m_nBackpackIndex = this.gridIndex;
                     UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                     UITopForm.createLocalTopForm((byte)1, (String)"Nhập số lượng bán ra", "Xác nhận", "Hủy", 2555970, -16);
                     MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridIndex]);
                     super.isShowMenu = false;
                     return;
                  }

                  MainCanvas.ni.send(cmd);
               }
               break;
            case 2555936:
               if (!this.stuffName[this.gridIndex].equals("") && this.stuffName[this.gridIndex] != null) {
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  PCFarm.m_nBackpackIndex = this.gridIndex;
                  MainCanvas.ni.send(cmd);
               }
            }

            super.isShowMenu = false;
            break;
         case 165:
            if (this.stuffId[this.gridIndex] != -1) {
               cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               switch(cmd) {
               case 2555945:
                  PCFarm.m_nBackpackIndex = this.gridIndex;
                  UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                  UITopForm.createLocalTopForm((byte)1, (String)"Nhập số lượng đem tặng", "Xác nhận", "Hủy", 2555945, -16);
                  MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridIndex]);
                  super.isShowMenu = false;
                  break;
               case 2555954:
                  UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                  PCFarm.m_nDepotSellingIndex = this.gridIndex;
                  UITopForm.createLocalTopForm((byte)1, (String)"Nhập số lượng bán ra", "Xác nhận", "Hủy", cmd, -1);
                  MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridIndex]);
                  super.isShowMenu = false;
                  break;
               case 2555955:
                  UITopForm.createLocalTopForm((byte)0, (String)"Có muốn bán hết mọi vật phẩm trong thương khố?", "Xác nhận", "Hủy", -29, -1);
                  super.isShowMenu = false;
                  break;
               case 2555956:
                  PCFarm.m_nBackpackIndex = this.gridIndex;
                  MainCanvas.ni.send(2555956);
                  super.isShowMenu = false;
                  break;
               case 2555972:
                  MainCanvas.ni.send(2555972);
                  super.isShowMenu = false;
                  break;
               case 2555975:
                  UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                  PCFarm.m_nDepotSellingIndex = this.gridIndex;
                  UITopForm.createLocalTopForm((byte)1, (String)"Nhập số lượng cần di dời", "Xác nhận", "Hủy", cmd, -1);
                  MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridIndex]);
                  super.isShowMenu = false;
               }
            }
            break;
         case 167:
            switch(MainCanvas.curForm.clientCommand) {
            case 2555915:
            case 2555936:
               if (!this.stuffName[this.gridIndex].equals("") && this.stuffName[this.gridIndex] != null) {
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  if (cmd == 2555940) {
                     PCFarm.m_nBackpackIndex = this.gridIndex;
                     UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                     UITopForm.createLocalTopForm((byte)1, (String)"Nhập số lượng bỏ đi", "Xác nhận", "Hủy", 2555940, -1);
                     MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridIndex]);
                     super.isShowMenu = false;
                     return;
                  }

                  if (cmd == 2555941) {
                     PCFarm.m_nBackpackIndex = this.gridIndex;
                     UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                     UITopForm.createLocalTopForm((byte)1, (String)"Nhập số lượng đem tặng", "Xác nhận", "Hủy", 2555941, -16);
                     MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridIndex]);
                     super.isShowMenu = false;
                     return;
                  }

                  if (cmd == 2555938 || cmd == 2555939) {
                     PCFarm.m_nBackpackIndex = this.gridIndex;
                  }

                  MainCanvas.ni.send(cmd);
               }
               break;
            case 2555957:
               if (!this.stuffName[this.gridIndex].equals("") && this.stuffName[this.gridIndex] != null) {
                  cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
                  if (cmd == 2555939) {
                     PCFarm.m_nBackpackIndex = this.gridIndex;
                  } else if (cmd == 2555971) {
                     PCFarm.m_nBackpackIndex = this.gridIndex;
                     UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                     UITopForm.createLocalTopForm((byte)1, (String)"Nhập số lượng bán ra", "Xác nhận", "Hủy", 2555971, -16);
                     MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridIndex]);
                     super.isShowMenu = false;
                     return;
                  }

                  MainCanvas.ni.send(cmd);
               }
            }

            super.isShowMenu = false;
         }
      } else if (MainCanvas.isInputDown(262144)) {
         super.isShowMenu = false;
      }

   }

   public static void resetTradeSign() {
      for(int i = 0; i < tradeSign.length; ++i) {
         tradeSign[i] = -1;
         tradeSignNum[i] = 1;
      }

   }

   public static void EliminateMail() {
      int i;
      for(i = 0; i < 6; ++i) {
         for(int j = 0; j < 4; ++j) {
            accessories[i][j] = -1;
            PCMail.Accessory[i][j] = -1;
         }
      }

      for(i = 0; i < PCMail.Accessory.length; ++i) {
         UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 7);
         pic.quality = 0;
         pic.isWpPic = false;
         pic.wpIndex = -1;
         pic.setImg((MImage)null);
         pic.setShowNum(false);
      }

      UITextField textfield3 = (UITextField)MainCanvas.curForm.getComponents().elementAt(13);
   }

   public static void Eliminate() {
      for(int i = 0; i < accessories.length; ++i) {
         if (i == MainCanvas.m_bMailAccessoriesN) {
            accessories[i][0] = -1;
            accessories[i][1] = -1;
            accessories[i][2] = -1;
            accessories[i][3] = -1;
            PCMail.Accessory[i][0] = accessories[i][0];
            PCMail.Accessory[i][1] = accessories[i][1];
            PCMail.Accessory[i][2] = accessories[i][2];
            PCMail.Accessory[i][3] = accessories[i][3];
            if (PCMail.m_bAccessoriesAmount > 0) {
               --PCMail.m_bAccessoriesAmount;
            }
         }
      }

      UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(MainCanvas.m_bMailAccessoriesN + 7);
      pic.setShowNum(false);
      UITextField textfield3 = (UITextField)MainCanvas.curForm.getComponents().elementAt(13);
   }

   private void addGridMenuOperate() {
      if (MainCanvas.curForm.clientCommand != 458773) {
         UIMenu menu;
         int[] menuCommand;
         int[] menuCommand;
         String[] strs;
         switch(super.clientType) {
         case 6:
            int[] inns;
            int[] inns;
            String[] cmdStrs;
            String[] menuStr;
            switch(this.getGridFormType()) {
            case 0:
               switch(MainCanvas.curForm.clientCommand) {
               case -1610612734:
                  if (this.stuffId[this.gridIndex] != -1) {
                     menu = new UIMenu((byte)0);
                     cmdStrs = new String[]{"Kỹ năng thiết lập", "Thiết lập vật phẩm", "Thiết lập công năng", "Phím xóa", "Xóa hết", "Tra xem"};
                     menuCommand = new int[]{-268435451, -268435450, -268435449, -268435455, -268435454, -268435453};
                     inns = new int[]{3, 3, 0, 3, 3, 2};
                     menu.addMenu(cmdStrs, menuCommand, inns);
                     this.setMenu(menu);
                  } else {
                     menu = new UIMenu((byte)0);
                     cmdStrs = new String[]{"Thiết lập kỹ năng", "Thiết lập vật phẩm", "Thiết lập công năng", "Xóa hết"};
                     menuCommand = new int[]{-268435451, -268435450, -268435449, -268435454};
                     inns = new int[]{3, 3, 0, 3};
                     menu.addMenu(cmdStrs, menuCommand, inns);
                     this.setMenu(menu);
                  }

                  super.isShowMenu = true;
                  return;
               case 458761:
                  if (this.stuffId[this.gridIndex] != -1) {
                     menu = new UIMenu((byte)0);
                     cmdStrs = new String[]{"Tra xem"};
                     menuCommand = new int[]{458753};
                     inns = new int[]{2};
                     if (this.stuffType[this.gridIndex] == 1) {
                        cmdStrs = Util.addArray((String[])cmdStrs, 0, (String)"Chọn");
                        menuCommand = Util.addArray((int[])menuCommand, 0, (int)0);
                        inns = Util.addArray((int[])inns, 0, (int)4);
                     }

                     menu.addMenu(cmdStrs, menuCommand, inns);
                     this.setMenu(menu);
                     super.isShowMenu = true;
                  }

                  return;
               case 1966082:
                  if (this.stuffId[this.gridIndex] != -1 && (this.stuffId[this.gridIndex] / 10000 >= 1011 && this.stuffId[this.gridIndex] / 10000 <= 1016 || this.stuffId[this.gridIndex] / 10000 == 1027)) {
                     menu = new UIMenu((byte)0);
                     menuCommand = new int[]{PCGem.m_bGemDisplace == 0 ? 1966084 : 1966090};
                     menuCommand = new int[]{3};
                     menu.addMenu(Cons.MENU_GEM_FINISH, menuCommand, menuCommand);
                     this.setMenu(menu);
                     super.isShowMenu = true;
                  } else {
                     UITopForm.createLocalTopForm((byte)0, (String)"Vật phẩm này không thể khảm nạm", "Xác nhận", "", -1, -2);
                  }

                  return;
               case 3342341:
                  if (this.stuffId[this.gridIndex] != -1) {
                     MainCanvas.ni.send(3342342);
                  }

                  return;
               default:
                  if (this.gridExchangeIndex[0] != -1) {
                     if (this.gridExchangeIndex[0] == this.gridIndex) {
                        return;
                     }

                     this.gridExchangeIndex[1] = this.gridIndex;
                     PCPackage.exchangeIndex[0] = this.gridExchangeIndex[0];
                     PCPackage.exchangeIndex[1] = this.gridExchangeIndex[1];
                     if (this.stuffId[this.gridIndex] == -1 && this.stuffMaxNum[this.gridExchangeIndex[0]] != 1 && this.stuffNumber[this.gridExchangeIndex[0]] != 1) {
                        UITopForm.MAXNUMBER = this.stuffNumber[this.gridExchangeIndex[0]];
                        UITopForm.createLocalTopForm((byte)1, (String)"Xin nhập số lượng dịch chuyển vật phẩm:", "Đồng ý", "Hủy", 458756, -1);
                        MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridExchangeIndex[0]]);
                     } else {
                        PCPackage.moveNum = this.stuffNumber[this.gridExchangeIndex[0]];
                        MainCanvas.ni.send(458756);
                     }

                     return;
                  }

                  if (this.stuffId[this.gridIndex] != -1) {
                     menu = new UIMenu((byte)0);
                     if (PCTreasure.treasureMenu) {
                        menu.addMenu(PCTreasure.treasureMenuContent[0], 3342352, 3);
                        menu.addMenu(PCTreasure.treasureMenuContent[1], 3342354, 3);
                        this.setMenu(menu);
                        super.isShowMenu = true;
                        this.getMenu().resetIndex();
                        return;
                     }

                     PCTreasure.registerIndex = this.gridIndex;
                     if (this.haveUseMenuItem()) {
                        int inn = false;
                        byte inn;
                        if ((this.stuffId[this.gridIndex] / 10000 < 1011 || this.stuffId[this.gridIndex] / 10000 > 1016) && this.stuffId[this.gridIndex] / 10000 != 1027 && this.stuffId[this.gridIndex] / 10000 != 1204) {
                           inn = 3;
                        } else {
                           inn = 1;
                           PCGem.m_bEnchaseSucceed = 0;
                        }

                        menu.addMenu("Sử dụng", 458754, inn);
                     }

                     if (this.haveEquipMenuItem()) {
                        menu.addMenu("Trang bị", 458755, 3);
                     }

                     menu.addMenu("Tra xem", this.stuffId[this.gridIndex] / 10000 != 118 ? 458753 : 3342337, this.stuffId[this.gridIndex] / 10000 != 118 ? 2 : 1);
                     menu.addMenu("Di chuyển", 458756, 3);
                     if (this.haveEquipMenuItem() && this.stuffId[this.gridIndex] / 10000 != 118) {
                        menu.addMenu("So sánh", 1245185, 2);
                     }

                     menu.addMenu("Sắp xếp", 458758, 3);
                     menu.addMenu("Bỏ", 458759, 3);
                     this.setMenu(menu);
                  } else {
                     if (PCTreasure.treasureMenu) {
                        return;
                     }

                     menu = new UIMenu((byte)0);
                     menuCommand = new int[]{458758};
                     menuStr = new String[]{"Chỉnh lý"};
                     menu.addMenu(menuStr, menuCommand);
                     this.setMenu(menu);
                  }

                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
                  return;
               }
            case 1:
               if (this.stuffId[this.gridIndex] != -1) {
                  menu = new UIMenu((byte)0);
                  if (MainCanvas.curForm.clientCommand == 983046) {
                     menu.addMenu("Vật phẩm bán ra", 983043, 3);
                     menu.addMenu("Tự động bán", 983201, 3);
                  }

                  menu.addMenu("Thông tin chi tiết", this.stuffId[this.gridIndex] / 10000 != 118 ? 458753 : 3342337, 2);
                  if (!isGoods(this.stuffId[this.gridIndex]) && this.stuffId[this.gridIndex] / 10000 != 118) {
                     menu.addMenu("So sánh", 1245185, 2);
                  }

                  menu.addMenu("Chỉnh lý", 983044, 3);
                  menu.addMenu("Bỏ", 983045, 3);
                  this.setMenu(menu);
               } else {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{983044};
                  menuStr = new String[]{"Chỉnh lý"};
                  menu.addMenu(menuStr, menuCommand);
                  this.setMenu(menu);
               }

               super.isShowMenu = true;
               this.getMenu().resetIndex();
               return;
            case 2:
               if (this.gridExchangeIndex[0] != -1) {
                  if (this.gridExchangeIndex[0] == this.gridIndex) {
                     return;
                  }

                  this.gridExchangeIndex[1] = this.gridIndex;
                  PCPackage.exchangeIndex[0] = this.gridExchangeIndex[0];
                  PCPackage.exchangeIndex[1] = this.gridExchangeIndex[1];
                  if (this.stuffId[this.gridIndex] == -1 && this.stuffMaxNum[this.gridExchangeIndex[0]] != 1 && this.stuffNumber[this.gridExchangeIndex[0]] != 1) {
                     UITopForm.MAXNUMBER = this.stuffNumber[this.gridExchangeIndex[0]];
                     UITopForm.createLocalTopForm((byte)1, (String)"Xin nhập số lượng vật phẩm di chuyển:", "Đồng ý", "Hủy", 1179656, -1);
                     MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridExchangeIndex[0]]);
                  } else {
                     PCPackage.moveNum = this.stuffNumber[this.gridExchangeIndex[0]];
                     MainCanvas.ni.send(1179656);
                  }

                  return;
               }

               if (this.stuffId[this.gridIndex] != -1) {
                  menu = new UIMenu((byte)0);
                  menu.addMenu("Nhập vật phẩm vào", 1179651, 3);
                  menu.addMenu("Nhập tiền vào", 1179652, 3);
                  menu.addMenu("Tra xem vật phẩm", this.stuffId[this.gridIndex] / 10000 != 118 ? 458753 : 3342337, this.stuffId[this.gridIndex] / 10000 != 118 ? 2 : 1);
                  if (!isGoods(this.stuffId[this.gridIndex]) && this.stuffId[this.gridIndex] / 10000 != 118) {
                     menu.addMenu("So sánh", 1245185, 2);
                  }

                  menu.addMenu("Chỉnh lý", 1179657, 3);
                  menu.addMenu("Di chuyển", 1179656, 3);
                  if (MainCanvas.curForm.clientCommand == 1179649) {
                     menu.addMenu("Mở rộng thương khố", 1179658, 1);
                  }

                  this.setMenu(menu);
               } else {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{1179652, 1179657};
                  menuStr = new String[]{"Gửi Vàng", "Chỉnh lý"};
                  inns = new int[]{3, 3};
                  menu.addMenu(menuStr, menuCommand, inns);
                  if (MainCanvas.curForm.clientCommand == 1179649) {
                     menu.addMenu("Mở rộng thương khố", 1179658, 1);
                  }

                  this.setMenu(menu);
               }

               super.isShowMenu = true;
               this.getMenu().resetIndex();
               return;
            case 3:
               if (this.stuffId[this.gridIndex] != -1) {
                  UIForm.setCompareTI((byte)0, this.gridIndex);
               }

               return;
            case 4:
               if (this.stuffId[this.gridIndex] == -1) {
                  return;
               }

               menu = new UIMenu((byte)0);
               String selStr = "";

               for(int i = 0; i < tradeSign.length; ++i) {
                  if (tradeSign[i] == this.gridIndex) {
                     selStr = "Bỏ chọn";
                     break;
                  }

                  selStr = "Chọn vật phẩm";
               }

               menuStr = new String[]{selStr, "Xác nhận đặt", "Tra xem"};
               inns = new int[]{0, 1376261, this.stuffId[this.gridIndex] / 10000 != 118 ? 458753 : 3342337};
               inns = new int[]{3, 3, 2};
               menu.addMenu(menuStr, inns, inns);
               this.setMenu(menu);
               super.isShowMenu = true;
               this.getMenu().resetIndex();
               return;
            case 5:
               boolean cancelsign = false;
               if (this.stuffId[this.gridIndex] != -1) {
                  for(int i = 0; i < accessories.length; ++i) {
                     if (accessories[i][0] != -1 && accessories[i][0] == this.gridIndex) {
                        cancelsign = true;
                     }
                  }

                  UIMenu menu = new UIMenu((byte)0);
                  menuCommand = new int[]{cancelsign ? -1610612700 : -1610612702, -1610612701, this.stuffId[this.gridIndex] / 10000 != 118 ? 458753 : 3342337};
                  if (!cancelsign) {
                     Cons.MENU_TRADE_OTHER_STR[0] = "Chọn vật phẩm";
                  } else {
                     Cons.MENU_TRADE_OTHER_STR[0] = "Lấy vật phẩm";
                  }

                  inns = new int[]{3, 3, 2};
                  menu.addMenu(Cons.MENU_TRADE_OTHER_STR, menuCommand, inns);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }

               return;
            case 6:
               if (this.stuffId[this.gridIndex] != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{this.stuffId[this.gridIndex] / 10000 != 118 ? 458753 : 3342337, 1769477};
                  menuCommand = new int[]{2, 3};
                  menu.addMenu(Cons.MENU_CHANGE_STR, menuCommand, menuCommand);
                  this.setMenu(menu);
               } else {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{1769477};
                  menuCommand = new int[]{3};
                  menu.addMenu(Cons.MENU_CHANGE_STR2, menuCommand, menuCommand);
                  this.setMenu(menu);
               }

               super.isShowMenu = true;
               this.getMenu().resetIndex();
               return;
            case 7:
               if (this.stuffId[this.gridIndex] != -1) {
                  menu = new UIMenu((byte)0);
                  cmdStrs = new String[]{"Tra xem", "Chọn vật phẩm"};
                  menuStr = new String[]{"Tra xem", "Đổi vật phẩm", "Xác nhận đấu giá", "Lấy vật phẩm"};
                  inns = new int[]{this.stuffId[this.gridIndex] / 10000 != 118 ? 458753 : 3342337, -1610612715};
                  inns = new int[]{this.stuffId[this.gridIndex] / 10000 != 118 ? 458753 : 3342337, -1610612715, -1610612713, -1610612714};
                  int[] inns = new int[]{2, 3};
                  int[] inns_1 = new int[]{2, 3, 3, 3};
                  menu.addMenu(PCAuction.m_bEntityGoods == 0 ? cmdStrs : menuStr, PCAuction.m_bEntityGoods == 0 ? inns : inns, PCAuction.m_bEntityGoods == 0 ? inns : inns_1);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }

               return;
            case 8:
               if (UIPicture.isNeedEquip()) {
                  return;
               }

               if (this.stuffId[this.gridIndex] != -1) {
                  menu = new UIMenu((byte)0);
                  if (isGoods(this.stuffId[this.gridIndex])) {
                     menuCommand = new int[]{458753, 917510, 917511};
                     menuCommand = new int[]{2, 3, 3};
                     strs = new String[]{"Tra xem vật phẩm", "Chỉnh lý", "Bỏ"};
                     menu.addMenu(strs, menuCommand, menuCommand);
                  } else {
                     menu.addMenu("Vật phẩm trang bị", 458755, 3);
                     menu.addMenu("Tra xem vật phẩm", this.stuffId[this.gridIndex] / 10000 != 118 ? 458753 : 3342337, this.stuffId[this.gridIndex] / 10000 != 118 ? 2 : 1);
                     if (this.stuffId[this.gridIndex] / 10000 != 118) {
                        menu.addMenu("Khai phong", 2031636, 1);
                        menu.addMenu("So sánh", 458757, 2);
                     }

                     menu.addMenu("Chỉnh lý", 917510, 3);
                     menu.addMenu("Bỏ", 917511, 3);
                  }

                  this.setMenu(menu);
               } else {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{917510};
                  menuStr = new String[]{"Chỉnh lý"};
                  menu.addMenu(menuStr, menuCommand);
                  this.setMenu(menu);
               }

               super.isShowMenu = true;
               this.getMenu().resetIndex();
               return;
            case 9:
               if (this.stuffId[this.gridIndex] != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{458753, 1179659};
                  menuStr = new String[]{"Tra xem", "Sử dụng"};
                  inns = new int[]{2, 2};
                  menu.addMenu(menuStr, menuCommand, inns);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }

               return;
            case 10:
               if (this.stuffId[this.gridIndex] != -1 && this.stuffId[this.gridIndex] / 10000 >= 101 && this.stuffId[this.gridIndex] / 10000 <= 120) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{1966087, this.stuffId[this.gridIndex] / 10000 != 118 ? 1966093 : 3342337};
                  menuCommand = new int[]{2, 2};
                  menu.addMenu(Cons.MENU_GEM_SETTING, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               } else {
                  UITopForm.createLocalTopForm((byte)0, (String)"Vật phẩm không thể khảm nạm bảo thạch.", "Xác nhận", "", -1, -2);
               }

               return;
            case 11:
               if (this.stuffId[this.gridIndex] != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{-1610612705, this.stuffId[this.gridIndex] / 10000 != 118 ? 2031617 : 3342337, 2031618, 2031648};
                  menuCommand = new int[]{3, 2, 3, 2};
                  menu.addMenu(Cons.MENU_UNSEAL_1, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }

               return;
            case 12:
               if (this.stuffId[this.gridIndex] != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{-1610612705, this.stuffId[this.gridIndex] / 10000 != 118 ? 2031635 : 3342337, 2031623, 2031649};
                  menuCommand = new int[]{3, 2, 3, 2};
                  menu.addMenu(Cons.MENU_GEMCARVE_2, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }

               return;
            case 13:
               if (this.stuffId[this.gridIndex] != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{-1610612705, this.stuffId[this.gridIndex] / 10000 != 118 ? 2228231 : 3342337, 2228227, 2031650};
                  menuCommand = new int[]{3, 2, 3, 2};
                  menu.addMenu(Cons.MENU_SYNTHESIZE_3, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }

               return;
            case 14:
               if (this.stuffId[this.gridIndex] != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{-1610612705, this.stuffId[this.gridIndex] / 10000 != 118 ? 1966113 : 3342337, 1966099, 2031651};
                  menuCommand = new int[]{3, 2, 1, 2};
                  menu.addMenu(Cons.MENU_GEM_UNCHAIN_3, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }

               return;
            case 15:
               if (this.stuffId[this.gridIndex] != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{this.stuffId[this.gridIndex] / 10000 != 118 ? 458753 : 3342337, 2162754};
                  menuCommand = new int[]{2, 3};
                  strs = new String[]{"Tra xem", "Ngưng Nguyên"};
                  menu.addMenu(strs, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }

               return;
            case 16:
            default:
               return;
            case 17:
               if (this.stuffId[this.gridIndex] != -1) {
                  if (PCEquip.m_bPropIndex != -1) {
                     if (this.stuffId[this.gridIndex] / 10000 >= 101 && this.stuffId[this.gridIndex] / 10000 <= 120) {
                        menu = new UIMenu((byte)0);
                        menuCommand = new int[]{917524, this.stuffId[this.gridIndex] / 10000 != 118 ? 458753 : 3342337};
                        menuCommand = new int[]{3, 2};
                        strs = new String[]{"Sửa", "Tra Xem"};
                        menu.addMenu(strs, menuCommand, menuCommand);
                        this.setMenu(menu);
                        super.isShowMenu = true;
                        this.getMenu().resetIndex();
                     } else {
                        menu = new UIMenu((byte)0);
                        menuCommand = new int[]{458753};
                        menuCommand = new int[]{2};
                        strs = new String[]{"Tra Xem"};
                        menu.addMenu(strs, menuCommand, menuCommand);
                        this.setMenu(menu);
                        super.isShowMenu = true;
                        this.getMenu().resetIndex();
                     }

                     return;
                  } else {
                     menu = new UIMenu((byte)0);
                     menuCommand = new int[]{458753};
                     menuCommand = new int[]{2};
                     strs = new String[]{"Tra Xem"};
                     menu.addMenu(strs, menuCommand, menuCommand);
                     this.setMenu(menu);
                     super.isShowMenu = true;
                     this.getMenu().resetIndex();
                     return;
                  }
               }

               return;
            case 18:
               if (this.stuffId[this.gridIndex] != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{-1610612705, this.stuffId[this.gridIndex] / 10000 != 118 ? 2228231 : 3342337, 2228258, 2228260};
                  menuCommand = new int[]{3, 2, 1, 2};
                  strs = new String[]{"Đặt vật phẩm", "Thông tin chi tiết", "Bảo Thạch Hoàn Nguyên", "Giải thích Hoàn Nguyên"};
                  menu.addMenu(strs, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }

               return;
            case 19:
               if (this.stuffId[this.gridIndex] != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{3342357, 3342358, this.stuffId[this.gridIndex] / 10000 != 118 ? 2031617 : 3342337, 3342359, 3342361};
                  menuCommand = new int[]{3, 3, 2, 3, 2};
                  strs = new String[]{"Đặt Pháp Bảo", "Đặt Khắc Danh", "Thông tin chi tiết", "Pháp Bảo Khắc Danh", "Giải thích Khắc Danh"};
                  menu.addMenu(strs, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }

               return;
            case 20:
               if (this.stuffId[this.gridIndex] != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{917526, 917527, this.stuffId[this.gridIndex] / 10000 != 118 ? 2031617 : 3342337, 917528, 917530};
                  menuCommand = new int[]{3, 3, 2, 3, 2};
                  strs = new String[]{"Đặt Trang Bị", "Đặt Cường Hóa Phù", "Thông tin chi tiết", "Cường Hóa Trang Bị", "Giải Thích Cường Hóa"};
                  menu.addMenu(strs, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }

               return;
            }
         case 15:
         case 42:
            if (this.stuffId[this.gridIndex] != -1) {
               menu = new UIMenu((byte)0);
               menu.addMenu("Mua vật phẩm", 983042, 3);
               menu.addMenu("Thông tin chi tiết", 983041, 2);
               if (!isGoods(this.stuffId[this.gridIndex]) && this.stuffId[this.gridIndex] / 10000 != 118) {
                  menu.addMenu("So sánh", 1245185, 2);
               }

               this.setMenu(menu);
               super.isShowMenu = true;
               this.getMenu().resetIndex();
            }
            break;
         case 29:
            if (this.gridExchangeIndex[0] != -1) {
               if (this.gridExchangeIndex[0] == this.gridIndex) {
                  return;
               }

               this.gridExchangeIndex[1] = this.gridIndex;
               PCPackage.exchangeIndex[0] = this.gridExchangeIndex[0];
               PCPackage.exchangeIndex[1] = this.gridExchangeIndex[1];
               if (this.stuffId[this.gridIndex] == -1 && this.stuffMaxNum[this.gridExchangeIndex[0]] != 1 && this.stuffNumber[this.gridExchangeIndex[0]] != 1) {
                  UITopForm.MAXNUMBER = this.stuffNumber[this.gridExchangeIndex[0]];
                  UITopForm.createLocalTopForm((byte)1, (String)"Xin nhập số lượng vật phẩm di chuyển:", "Đồng ý", "Hủy", 1179656, -1);
                  MainCanvas.curTopForm.setNumber(this.stuffNumber[this.gridExchangeIndex[0]]);
               } else {
                  PCPackage.moveNum = this.stuffNumber[this.gridExchangeIndex[0]];
                  MainCanvas.ni.send(1179656);
               }

               return;
            }

            if (this.stuffId[this.gridIndex] != -1) {
               menu = new UIMenu((byte)0);
               menu.addMenu("Lấy vật phẩm ra", 1179653, 3);
               menu.addMenu("Lấy vàng ra", 1179654, 3);
               menu.addMenu("Tra xem vật phẩm", this.stuffId[this.gridIndex] / 10000 != 118 ? 1179650 : 3342339, this.stuffId[this.gridIndex] / 10000 != 118 ? 2 : 1);
               if (!isGoods(this.stuffId[this.gridIndex]) && this.stuffId[this.gridIndex] / 10000 != 118) {
                  menu.addMenu("So sánh", 1245185, 2);
               }

               menu.addMenu("Chỉnh lý", 1179655, 3);
               menu.addMenu("Di chuyển", 1179656, 3);
               this.setMenu(menu);
            } else {
               menu = new UIMenu((byte)0);
               menu.addMenu("Rút Tiền", 1179654, 3);
               menu.addMenu("Chỉnh lý", 1179655, 3);
               this.setMenu(menu);
            }

            super.isShowMenu = true;
            this.getMenu().resetIndex();
            break;
         case 53:
            if (this.stuffId[this.gridIndex] != -1) {
               menu = new UIMenu((byte)0);
               menuCommand = new int[]{1769475, 1};
               menuCommand = new int[]{2, 3};
               menu.addMenu(Cons.MENU_CHANGE_GOODS_SHOP_STR, menuCommand, menuCommand);
               this.setMenu(menu);
               super.isShowMenu = true;
               this.getMenu().resetIndex();
            }
            break;
         case 148:
            if (this.stuffId[this.gridIndex] != -1) {
               menu = new UIMenu((byte)0);
               if (MainCanvas.curForm.clientCommand == 2425078) {
                  menuCommand = new int[]{2425080, 2425079};
                  menuCommand = new int[]{3, 1};
                  strs = new String[]{"Giảm 5%", "Tìm"};
                  menu.addMenu(strs, menuCommand, menuCommand);
               } else if (this.stuffId[this.gridIndex] / 10000 == 1616) {
                  menuCommand = new int[]{2424993, 2686977, 2424995};
                  menuCommand = new int[]{3, 3, 1};
                  strs = new String[]{"Thu mua", "Bao Tháng", "Tra Xem"};
                  menu.addMenu(strs, menuCommand, menuCommand);
               } else {
                  menuCommand = new int[]{2424993, 2424995};
                  menuCommand = new int[]{3, 1};
                  strs = new String[]{"Thu mua", "Tra Xem"};
                  menu.addMenu(strs, menuCommand, menuCommand);
               }

               this.setMenu(menu);
               super.isShowMenu = true;
               this.getMenu().resetIndex();
            }
            break;
         case 160:
            switch(MainCanvas.curForm.clientCommand) {
            case 2555908:
               if (this.stuffId[this.gridIndex] != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{2555909, 2555968};
                  menuCommand = new int[]{3, 2};
                  strs = new String[]{"Mua", "Xem"};
                  menu.addMenu(strs, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }

               return;
            case 2555957:
               if (this.stuffId[this.gridIndex] != -1) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{2555958, 2555969};
                  menuCommand = new int[]{3, 2};
                  strs = new String[]{"Mua", "Xem"};
                  menu.addMenu(strs, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }

               return;
            default:
               return;
            }
         case 164:
            switch(MainCanvas.curForm.clientCommand) {
            case 2555907:
               if (!this.stuffName[this.gridIndex].equals("") && this.stuffName[this.gridIndex] != null) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{2555962, 2555963, 2555943};
                  menuCommand = new int[]{2, 3, 2};
                  strs = new String[]{"Xem", "Bỏ", "Tặng bạn bè"};
                  menu.addMenu(strs, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }

               return;
            case 2555908:
               if (!this.stuffName[this.gridIndex].equals("") && this.stuffName[this.gridIndex] != null) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{2555962, 2555970};
                  menuCommand = new int[]{2, 3};
                  strs = new String[]{"Xem", "Bán"};
                  menu.addMenu(strs, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }

               return;
            case 2555936:
               if (!this.stuffName[this.gridIndex].equals("") && this.stuffName[this.gridIndex] != null) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{2555937, 2555962};
                  menuCommand = new int[]{3, 2};
                  strs = new String[]{"Gieo hạt", "Xem"};
                  menu.addMenu(strs, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }

               return;
            default:
               return;
            }
         case 165:
            if (this.stuffId[this.gridIndex] != -1) {
               menu = new UIMenu((byte)0);
               if (this.stuffId[this.gridIndex] / 10000 == 1613) {
                  menuCommand = new int[]{2555954, 2555956, 2555955, 2555945, -1610612636, 2555972, 2555975};
                  menuCommand = new int[]{3, 2, 3, 2, 2, 3, 3};
                  strs = new String[]{"Bán", "Xem", "Bán toàn bộ", "Tặng bạn bè", "Gia công", "Sắp xếp", "Cho vào túi đồ"};
                  menu.addMenu(strs, menuCommand, menuCommand);
               } else {
                  menuCommand = new int[]{2555954, 2555956, 2555955, 2555945, 2555972, 2555975};
                  menuCommand = new int[]{3, 2, 3, 2, 3, 3};
                  strs = new String[]{"Bán", "Xem", "Bán toàn bộ", "Tặng bạn bè", "Sắp xếp", "Cho vào túi đồ"};
                  menu.addMenu(strs, menuCommand, menuCommand);
               }

               this.setMenu(menu);
               super.isShowMenu = true;
               this.getMenu().resetIndex();
            } else {
               MainCanvas.ni.send(2555972);
            }
            break;
         case 167:
            switch(MainCanvas.curForm.clientCommand) {
            case 2555915:
               if (!this.stuffName[this.gridIndex].equals("") && this.stuffName[this.gridIndex] != null) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{2555939, 2555940, 2555941};
                  menuCommand = new int[]{2, 3, 2};
                  strs = new String[]{"Xem", "Bỏ", "Tặng bạn bè"};
                  menu.addMenu(strs, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }
               break;
            case 2555936:
               if (!this.stuffName[this.gridIndex].equals("") && this.stuffName[this.gridIndex] != null) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{2555939, 2555940, 2555938, 2555941};
                  menuCommand = new int[]{2, 3, 3, 2};
                  strs = new String[]{"Xem", "Bỏ", "Sử dụng vật phẩm", "Tặng bạn bè"};
                  menu.addMenu(strs, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }
               break;
            case 2555957:
               if (!this.stuffName[this.gridIndex].equals("") && this.stuffName[this.gridIndex] != null) {
                  menu = new UIMenu((byte)0);
                  menuCommand = new int[]{2555939, 2555971};
                  menuCommand = new int[]{2, 3};
                  strs = new String[]{"Xem", "Bán"};
                  menu.addMenu(strs, menuCommand, menuCommand);
                  this.setMenu(menu);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               }
            }
         }

      }
   }

   public UIScroll getGridScroll() {
      return this.gridScroll;
   }

   private void checkStartColDown() {
      int curLine = this.gridIndex / this.gridRow + 1;
      if (curLine > this.startCol + this.showGridCol) {
         ++this.startCol;
         int subCol = this.canUseNum % this.gridRow == 0 ? this.canUseNum / this.gridRow : this.canUseNum / this.gridRow + 1;
         if (this.startCol > subCol - this.showGridCol) {
            this.startCol = (short)(subCol - this.showGridCol);
         }
      }

   }

   private void checkStartColUp() {
      int curLine = this.gridIndex / this.gridRow;
      if (curLine < this.startCol) {
         --this.startCol;
      }

   }

   public boolean isHaveTitle() {
      return this.isHaveTitle;
   }

   public void setHaveTitle(boolean isHaveTitle) {
      this.isHaveTitle = isHaveTitle;
      if (isHaveTitle) {
         if (this.gridScroll != null) {
            this.gridScroll.setPositionY((short)(super.positionY + UILabel.getRimLabelHeight() + 2));
         }
      } else if (this.gridScroll != null) {
         this.gridScroll.setPositionY((short)(super.positionY + 2));
      }

      if (this.gridScroll != null) {
         this.gridScroll.resetScrollPositionY();
      }

   }

   public int getPlayerMoney() {
      return this.playerMoney;
   }

   public void setPlayerMoney(int playerMoney) {
      this.playerMoney = playerMoney;
   }

   public void getQuality(int k, int picID) {
      this.stuffQuality[k] = (byte)(picID / 1000 - 1);
   }

   public void addUIScroll() {
      if (this.getGridScroll() != null) {
         this.getGridScroll().allRowNum = (short)(this.canUseNum % this.getGridRow() == 0 ? this.canUseNum / this.getGridRow() : this.canUseNum / this.getGridRow() + 1);
         this.getGridScroll().canShowRowNum = this.getShowGridCol();
         this.getGridScroll().setShowStartRow((short)0);
      }
   }

   public void setSkill_area_Content() {
      if (this.getCanUseNum() != 0) {
         UITextArea skill_area_LEFT = (UITextArea)MainCanvas.curForm.getComponents().elementAt(4);
         short sind = Player.getInstance().getIND_FromSkillData(this.stuffId[this.gridIndex]);
         Player.getInstance();
         String skilldescription = Player.skillCon[sind] + "\n";
         skill_area_LEFT.setContent(skilldescription);
         skill_area_LEFT.addUIScroll();
      }

   }

   public static void packageSelectedView() {
      UIPicture[] pics = new UIPicture[]{(UIPicture)MainCanvas.curForm.getComponents().elementAt(3), (UIPicture)MainCanvas.curForm.getComponents().elementAt(5), (UIPicture)MainCanvas.curForm.getComponents().elementAt(7), (UIPicture)MainCanvas.curForm.getComponents().elementAt(9)};
      UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(10);

      for(int i = 0; i < pics.length; ++i) {
         if (pics[i].isFocus()) {
            grid.beginShockIndex = 0;

            for(int j = 0; j < i; ++j) {
               grid.beginShockIndex += pics[j].getItemNum();
            }

            grid.endShockIndex = (byte)(grid.beginShockIndex + pics[i].getItemNum());
            break;
         }

         grid.beginShockIndex = grid.endShockIndex = -1;
      }

   }

   public static int getStuffWordColor(byte stuffColorLevel) {
      int color = false;
      int color;
      if (stuffColorLevel < 0) {
         color = Cons.COLOR_FONT_1;
      } else if (stuffColorLevel != 1) {
         color = Cons.COLOR_STUFF_QUALITY[stuffColorLevel];
      } else {
         color = 0;
      }

      curSelStrColor = color;
      return color;
   }

   public static boolean isGoods(int stuffId) {
      String tmp = String.valueOf(stuffId);
      return tmp.length() == 8;
   }

   public boolean haveUseMenuItem() {
      return isGoods(this.stuffId[this.gridIndex]) || this.isCanUse[this.gridIndex];
   }

   public boolean haveEquipMenuItem() {
      return !isGoods(this.stuffId[this.gridIndex]);
   }

   public void putTradeItem() {
      if (this.stuffId[this.gridIndex] != -1) {
         boolean b = false;

         int i;
         for(i = 0; i < tradeSign.length; ++i) {
            if (tradeSign[i] == this.gridIndex) {
               b = true;
               break;
            }
         }

         for(i = 0; i < tradeSign.length; ++i) {
            if (tradeSign[i] == this.gridIndex) {
               tradeSign[i] = -1;
               tradeSignNum[i] = 1;
               break;
            }

            if (tradeSign[i] == -1 && !b) {
               if (!this.isCanTrade[this.gridIndex]) {
                  UITopForm.createLocalTopForm((byte)0, (String)"Vật phẩm này đã $Cố Định$ không thể giao dịch!", "Đồng ý", "", -1, -2);
                  return;
               }

               if (this.stuffNumber[this.gridIndex] != 1) {
                  UITopForm.MAXNUMBER = this.stuffNumber[this.gridIndex];
                  UITopForm.createLocalTopForm((byte)1, "Số lượng giao dịch:", "Đồng ý", "Trở về", -1, -1, i, this.gridIndex);
               } else {
                  tradeSign[i] = this.gridIndex;
               }
               break;
            }
         }

      }
   }

   private void MailAffirmGoosd() {
      UIForm.backUIFormAction();

      int i;
      for(i = 0; i < 6; ++i) {
         for(int j = 0; j < 4; ++j) {
            PCMail.Accessory[i][j] = accessories[i][j];
         }
      }

      for(i = 0; i < PCMail.Accessory.length; ++i) {
         UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(i + 7);
         pic.quality = (byte)PCMail.Accessory[i][2];
         short picID = PCMail.Accessory[i][1];
         pic.isWpPic = true;
         pic.wpIndex = picID;
         pic.setImg(MainCanvas.stuffMImg);
         if (PCMail.Accessory[i][3] == 1) {
            if (this.stuffShowMiniNum[PCMail.Accessory[i][0]]) {
               pic.setShowNum(true, (byte)1);
            } else {
               pic.setShowNum(false);
            }
         } else {
            this.stuffShowMiniNum[i] = true;
            if (PCMail.Accessory[i][3] > 0) {
               pic.setShowNum(true, (byte)PCMail.Accessory[i][3]);
            } else {
               pic.setShowNum(false);
            }
         }
      }

      PCMail.Postage();
   }

   public void setEmpty() {
      for(int k = 0; k < this.canUseNum; ++k) {
         this.stuffId[k] = -1;
         this.stuffImageId[k] = -1;
         this.stuffNumber[k] = -1;
         this.stuffName[k] = null;
         this.stuffColorLevel[k] = -1;
         this.stuffType[k] = -1;
         this.isCanUse[k] = false;
         this.isCanTrade[k] = false;
         this.bindType[k] = -1;
         this.stuffPrice[k] = -1;
         this.stuffMaxNum[k] = -1;
         this.stuffEquipPart[k] = -1;
         this.stuffCD[k] = false;
         this.stuffHitHole[k] = false;
         this.stuffLock[k] = false;
         this.stuffShowMiniNum[k] = false;
         this.stuffQuality[k] = -1;
      }

   }

   public void unSealPlace() {
      UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
      AuctionBag[0] = this.gridIndex;
      AuctionBag[1] = this.stuffImageId[this.gridIndex];
      AuctionBag[2] = this.stuffQuality[this.gridIndex];
      AuctionBag[3] = this.stuffNumber[this.gridIndex];
      if (PCUnsealGemCarve.m_bFocus == 1) {
         if (this.stuffId[this.gridIndex] / 10000 >= 101 && this.stuffId[this.gridIndex] / 10000 <= 120) {
            pic.setFocus(true, MainCanvas.curForm);
            this.setFocus(false, MainCanvas.curForm);
            PCUnsealGemCarve.m_bPrimaryGemIndex = this.gridIndex;
            MainCanvas.ni.send(2031637);
         } else {
            UITopForm.createLocalTopForm((byte)0, (String)"Đây không phải là trang bị，không thể khai phong.", "Đồng ý", "", -1, -2);
         }
      } else if (PCUnsealGemCarve.m_bFocus == 2) {
         if (this.stuffId[this.gridIndex] / 10000 == 1023) {
            PCUnsealGemCarve.AppendPicture(6, true, 1, true);
         } else {
            UITopForm.createLocalTopForm((byte)0, (String)"Chỉ có quyển khai phong mới được cho vào đây.", "Đồng ý", "", -1, -2);
         }
      } else if (PCUnsealGemCarve.m_bFocus == 3) {
         if (this.stuffId[this.gridIndex] / 10000 == 1022) {
            PCUnsealGemCarve.AppendPicture(8, true, 2, true);
         } else {
            UITopForm.createLocalTopForm((byte)0, (String)"Chỉ có phù tăng % mới được cho vào đây.", "Đồng ý", "", -1, -2);
         }
      } else if ((this.stuffId[this.gridIndex] / 10000 < 101 || this.stuffId[this.gridIndex] / 10000 > 120) && this.stuffId[this.gridIndex] / 10000 != 1023 && this.stuffId[this.gridIndex] / 10000 != 1022) {
         UITopForm.createLocalTopForm((byte)0, (String)"Vật phẩm này không dùng trong khai phong trang bị.", "Đồng ý", "", -1, -2);
      } else if (this.stuffId[this.gridIndex] / 10000 >= 101 && this.stuffId[this.gridIndex] / 10000 <= 120) {
         if (PCUnsealGemCarve.m_bPrimaryGemIndex == -1) {
            PCUnsealGemCarve.m_bPrimaryGemIndex = this.gridIndex;
            MainCanvas.ni.send(2031637);
         } else if (PCUnsealGemCarve.m_bPrimaryGemIndex != this.gridIndex) {
            UITopForm.createLocalTopForm((byte)0, (String)"Có muốn che lên trang bị này không?", "Xác nhận", "Hủy", -10, -9);
         }
      } else if (this.stuffId[this.gridIndex] / 10000 == 1023) {
         if (PCUnsealGemCarve.m_bCarveSymbolIndex == -1) {
            PCUnsealGemCarve.AppendPicture(6, true, 1, true);
         } else if (PCUnsealGemCarve.m_bPrimaryGemIndex != this.gridIndex) {
            UITopForm.createLocalTopForm((byte)0, (String)"Có muốn che lên Khai Phong Quyển này không?", "Xác nhận", "Hủy", -7, -9);
         }
      } else if (this.stuffId[this.gridIndex] / 10000 == 1022) {
         if (PCUnsealGemCarve.m_bJadeIndex == -1) {
            PCUnsealGemCarve.AppendPicture(8, true, 2, true);
         } else if (PCUnsealGemCarve.m_bPrimaryGemIndex != this.gridIndex) {
            UITopForm.createLocalTopForm((byte)0, (String)"Có muốn che lên Phù Thăng Cấp này không?", "Xác nhận", "Hủy", -8, -9);
         }
      }

   }

   public void strengthenPlace() {
      PCTreasure.selectItemIndex = this.gridIndex;
      AuctionBag[0] = this.gridIndex;
      AuctionBag[1] = this.stuffImageId[this.gridIndex];
      AuctionBag[2] = this.stuffQuality[this.gridIndex];
      AuctionBag[3] = this.stuffNumber[this.gridIndex];
   }
}

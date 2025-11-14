import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Map {
   private static boolean isFirstReadSource = true;
   public static final byte TILE_WIDTH = 20;
   public static final byte TILE_HEIGHT = 10;
   private BufMap curBufMap;
   private BufMap bufOne;
   private BufMap bufTwo;
   public static short currentTotalColumn;
   public static short currentTotalRow;
   public static short currentWindowX;
   public static short currentWindowY;
   public static short currentTopX;
   private byte[][] mapAttribute;
   private short[][] currentMapData;
   private short[][] iconRefe;
   private short[][] bigOData;
   private static short[][] cartoon;
   private static final short DOORInd = 333;
   private static int columnLeft;
   private static int columnRight;
   private static int rowTop;
   private static int rowBottom;
   private static final byte ADD_ROW_COLUMN = 0;
   public static Image[] imageCurrentMapTile = null;
   public static String[] mapImgStr = null;
   private static final byte TILE_SIGN = 1;
   private static final byte TRANS_NONE = 0;
   private static final byte TRANS_H = 1;
   private static final byte TRANS_V = 2;
   private static final byte TRANS_ROTATE_180 = 3;
   public static boolean isInterLocalMap = false;
   public static int MAP_H = 0;
   public static final int MAPOFFX = 0;
   public static final int MAPOFFY = 0;
   public static final int BUFMAPW = 0;
   public static final int BUFMAPH = 0;
   public static int REDUCEMAPOFFX = 0;
   public static int REDUCEMAPOFFY = 0;
   boolean showSmallMap = true;
   public String mapName = "";
   public String mapLevel = "";
   public static byte[] _datal = null;
   public static final int WORLDMAP_BLOCK_WIDTH = 16;
   public static final int WORLDMAP_BLOCK_HEIGHT = 16;
   public static short worldMapSX = 0;
   public static short worldMapSY = 0;
   public static byte worldMapCols = 0;
   public static byte worldMapRows = 0;
   public static Image imagePlayerRegion = null;
   public static Image[] imageMapRegion = new Image[30];
   public static Image[] imageRegionType = new Image[8];
   public static byte[][] wmdata = null;
   public static short regionCount = 0;
   public static short tmpRegionCount = 0;
   static short[] regionID = null;
   static String[] regionName = null;
   static byte[] regionCol = null;
   static byte[] regionRow = null;
   static byte[] regionType = null;
   static short[][] regionNeighbor = null;
   static byte[] regionSTATE = null;
   static short[] regionX = null;
   static short[] regionY = null;
   public static short playerRegionIND = 0;
   public static short selRegionIND = 0;
   public static boolean isDraw = false;
   private static short playerMT = 0;
   public static short w_RegionID = 0;
   public static short w_RegionID_Real = 0;
   public static byte isOutMenu = 0;
   public static byte isMapDetail = 0;
   public static final byte REGION_DIWJ = 0;
   public static final byte REGION_HOMECITY = 1;
   public static final byte REGION_FIELD = 2;
   public static final byte REGION_COPY = 3;
   public static final byte REGION_BATTLE = 4;
   public static final byte REGION_TOWN = 5;
   public static final byte NOTFIND = 0;
   public static final byte FIND_UNCHECK = 1;
   public static final byte FIND_CHECKED = 2;
   public static short[][] curCarryPoint = null;
   public static byte carryMap_Count = 0;
   public static String[] toMap_Name = null;
   public static byte[] carryMap_Type = null;
   public static boolean[] isDrawCarryMap = null;
   public static short[] repetitionSpecial_X = null;
   public static short[] repetitionSpecial_Y = null;
   public static byte[] wod = null;
   public static final byte REPETITIONSPECIAL_WIDTH = 22;
   public static final byte REPETITIONSPECIAL_HEIGHT = 37;
   public static final byte MAP_TYPE_NORMAL = 1;
   public static final byte MAP_TYPE_REPETITION = 2;
   public static final byte MAP_TYPE_BATTLEFIELD = 3;
   public static final byte MAP_TYPE_ARENA = 4;
   public static boolean m_bBattlefield = false;
   private static int mapCopyIndex;
   private static int CopyX;
   private static int CopyY;
   private static int CopytoCol;
   private static int CopytoRow;
   public static int[] m_nBannerID = new int[2];
   public static Vector flagID = new Vector();
   public static boolean m_bCopyEnret;
   private static Map instance = null;
   short showBigCount = 0;
   int[] bigId = null;
   int showAllIndex = 0;
   private static final int MAP_WIDTH = 50;
   private static final int MAP_HEIGHT = 25;
   private static final int MAP_START_X;
   private static final int MAP_START_Y;
   int startX;
   int startY;
   int tileWidth;
   int tileHeight;
   int mapWidth;
   int mapHeight;
   int mapCols;
   int mapRows;
   public static boolean isDrawPlaceName;
   public static byte drawPlaceNameCount;
   public static byte colorIndex;
   static Random rd;
   int width_2;
   int height_2;
   int w_h;
   int[] data_x0;
   int[] data_x1;
   int[] data_3;
   int[] data_4;
   int filelen;
   public int[][] mapArr_Ren;
   public int[][] mapArr_Xian;
   public int[][] mapArr_Yao;
   public int[][] mapArr_Mo;
   static final byte MAP_REN = 0;
   static final byte MAP_XIAN = 1;
   static final byte MAP_YAO = 2;
   static final byte MAP_MO = 3;
   static boolean isNewPlayerMap;
   static byte mapIndex;
   static Image maparr_up;
   static Image maparr_down;
   int arrY;
   static boolean battleDoorOpen;
   static byte[][] doorPosition;
   static byte[][] flagplace;
   Image gate;
   public static byte m_bBattlefieldMap;
   public static byte m_bCamp;

   static {
      MAP_START_X = MainCanvas.screenW - 29;
      MAP_START_Y = MainCanvas.isLargeScreen ? MainCanvas.screenH - 36 - SIChat.CHAT_BUFF_HEIGHT - 25 : MainCanvas.screenH - 36 - 25;
      rd = new Random();
      isNewPlayerMap = false;
      maparr_up = null;
      maparr_down = null;
      battleDoorOpen = true;
   }

   private Map() {
      this.startX = MAP_START_X;
      this.startY = MAP_START_Y;
      this.tileWidth = 0;
      this.tileHeight = 0;
      this.mapWidth = 0;
      this.mapHeight = 0;
      this.mapCols = 0;
      this.mapRows = 0;
      this.filelen = 0;
      this.mapArr_Ren = new int[][]{{16, 20}, {22, 22}, {32, 27}, {40, 28}, {45, 26}, {49, 21}, {56, 18}, {69, 23}, {77, 24}};
      this.mapArr_Xian = new int[][]{{10, 17}, {18, 18}, {25, 22}, {37, 28}, {46, 33}, {54, 39}, {63, 44}, {73, 46}};
      this.mapArr_Yao = new int[][]{{24, 14}, {21, 18}, {17, 23}, {15, 29}, {16, 38}, {17, 43}, {23, 46}, {29, 48}, {37, 48}};
      this.mapArr_Mo = new int[][]{{70, 51}, {62, 48}, {55, 45}, {45, 40}, {40, 32}, {32, 27}, {29, 21}, {18, 15}, {10, 14}, {3, 11}};
      this.gate = null;
      MAP_H = MainCanvas.screenH - 36 - (MainCanvas.isLargeScreen ? SIChat.CHAT_BUFF_HEIGHT : 0);
      this.bufOne = BufMap.createBufMap(this, MainCanvas.screenW + 0, MAP_H + 0);
      this.bufTwo = BufMap.createBufMap(this, MainCanvas.screenW + 0, MAP_H + 0);
   }

   public static Map getInstance() {
      if (instance == null) {
         instance = new Map();
      }

      return instance;
   }

   public static void clear() {
      instance = null;
   }

   public void draw(Graphics g) {
      if (MainCanvas.mc.isDrawTile) {
         this.drawTile(g);
      }

      if (GOManager.selectCircle != null) {
         GOManager.selectCircle.tick();
         if (!GOManager.selectCircle.isFinish()) {
            GOManager.selectCircle.draw(g);
         }
      }

      if (Player.mouseTimer > -MainCanvas.mouseImg.frames) {
         --Player.mouseTimer;
         MainCanvas.mouseImg.draw(g, Player.mouseDrawX - currentWindowX, Player.mouseDrawY - currentWindowY, MainCanvas.mouseImg.frames - Math.abs(Player.mouseTimer) - 1);
      }

      if (MainCanvas.mc.isDrawBig) {
         for(int i = GOManager.curGameObj.length - 1; i >= 0; --i) {
            GameObject tmpgo = GOManager.curGameObj[i];
            if (isInScreen(tmpgo)) {
               int width = MainCanvas.shadow.getWidth() >> 1;
               int height = MainCanvas.shadow.getHeight() >> 1;
               if (GOManager.isShowOtherPlayer || Player.getInstance() == tmpgo || tmpgo.type != 1) {
                  g.drawImage(MainCanvas.shadow, tmpgo.drawX - width, tmpgo.drawY - height, 0);
               }
            }
         }

         this.drawBigObj(g, columnLeft, rowTop, columnRight + 0, rowBottom + 0);
      }

      MainCanvas.mc.getClass();
      if (MainCanvas.mc.isDrawCarryMap) {
         this.drawCarryPointSpecial(g, columnLeft, rowTop, columnRight + 0, rowBottom + 0);
      }

      this.drawObjectName(g);
      this.drawCarryPointName(g, columnLeft, rowTop, columnRight + 0, rowBottom + 0);
      this.drawSmallMap(g);
      this.drawNewMail(g);
      this.drawEquipDurable(g);
      this.drawPlaceName(g);
      if (m_bBattlefield && !battleDoorOpen && m_bBattlefieldMap == 2 && m_bCamp == 2) {
         this.drawGate(g);
      }

      UITopForm.drawDrift(g);
   }

   private void drawTile(Graphics g) {
      columnLeft = (currentWindowY + (currentWindowX - currentTopX) * 10 / 20) / 10;
      columnRight = (currentWindowY + MAP_H + (currentWindowX + MainCanvas.screenW - currentTopX) * 10 / 20) / 10 + 1;
      rowTop = (currentWindowY - (currentWindowX + MainCanvas.screenW - currentTopX) * 10 / 20) / 10;
      rowBottom = (currentWindowY + MAP_H - (currentWindowX - currentTopX) * 10 / 20) / 10 + 1;
      if (columnLeft < 0) {
         columnLeft = 0;
      }

      if (rowTop < 0) {
         rowTop = 0;
      }

      if (columnRight >= currentTotalColumn) {
         columnRight = currentTotalColumn;
      }

      if (rowBottom >= currentTotalRow) {
         rowBottom = currentTotalRow;
      }

      try {
         if (this.curBufMap == null) {
            this.bufOne.draw((BufMap)null, currentWindowX + 0, currentWindowY + 0);
            this.curBufMap = this.bufOne;
         } else if (this.curBufMap == this.bufOne) {
            this.bufTwo.draw(this.bufOne, currentWindowX + 0, currentWindowY + 0);
            this.curBufMap = this.bufTwo;
         } else if (this.curBufMap == this.bufTwo) {
            this.bufOne.draw(this.bufTwo, currentWindowX + 0, currentWindowY + 0);
            this.curBufMap = this.bufOne;
         }

         g.drawImage(this.curBufMap.getImg(), 0, 0, 20);
         if (m_bBattlefield && !battleDoorOpen && (m_bBattlefieldMap == 3 || m_bBattlefieldMap == 2 && m_bCamp == 1)) {
            this.drawGate(g);
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public void drawArea(Graphics g, int aCurrentWindowX, int aCurrentWindowY, int aScreenWidth, int aScreenHeight) {
      int SoffX = false;
      int SoffY = false;
      int beginCol = (aCurrentWindowY + (aCurrentWindowX - currentTopX) * 10 / 20) / 10;
      int beginRow = (aCurrentWindowY - (aCurrentWindowX - currentTopX) * 10 / 20) / 10;
      --beginCol;
      --beginRow;
      int SoffX = (20 * (beginCol - beginRow) >> 1) + currentTopX - currentWindowX;
      int SoffY = (10 * (beginCol + beginRow) >> 1) + 10 - currentWindowY;
      int i = 0;

      for(int kk = aScreenHeight / 10 + 2; i < kk; ++i) {
         int j = -1;

         for(int kk1 = aScreenWidth / 20 + 2; j < kk1; ++j) {
            for(int k = 1; k >= 0; --k) {
               int cCol = beginCol + j;
               int cRow = beginRow - j - k;
               if (cCol >= 0 && cCol < currentTotalColumn && cRow >= 0 && cRow < currentTotalRow) {
                  int picIndex = this.currentMapData[cCol][cRow];
                  int translate = this.mapAttribute[cCol][cRow] >> 1 & 3;
                  int dx = SoffX + j * 20 - this.iconRefe[picIndex][2] + k * 10 + 0;
                  int dy = SoffY - this.iconRefe[picIndex][3] - k * 5 + 0;
                  this.drawBufferTileTemp(g, picIndex, dx, dy, translate, cCol, cRow, 0);
               }
            }
         }

         SoffY += 10;
         ++beginCol;
         ++beginRow;
      }

   }

   public void drawCar(Graphics g, int aCurrentWindowX, int aCurrentWindowY, int aScreenWidth, int aScreenHeight) {
      int SoffX = false;
      int SoffY = false;
      int beginCol = (aCurrentWindowY + (aCurrentWindowX - currentTopX) * 10 / 20) / 10;
      int beginRow = (aCurrentWindowY - (aCurrentWindowX - currentTopX) * 10 / 20) / 10;
      --beginCol;
      --beginRow;
      int SoffX = 20 * (beginCol - beginRow) / 2 + currentTopX - currentWindowX;
      int SoffY = 10 * (beginCol + beginRow) / 2 + 10 - currentWindowY;
      int i = 0;

      for(int kk = aScreenHeight / 10 + 2; i < kk; ++i) {
         int j = -1;

         for(int kk1 = aScreenWidth / 20 + 2; j < kk1; ++j) {
            for(int k = 1; k >= 0; --k) {
               int cCol = beginCol + j;
               int cRow = beginRow - j - k;
               if (cCol >= 0 && cCol < currentTotalColumn && cRow >= 0 && cRow < currentTotalRow) {
                  int com = this.mapAttribute[cCol][cRow] >> 6 & 3;
                  if (com > 0) {
                     int picIndex = this.currentMapData[cCol][cRow];
                     int translate = this.mapAttribute[cCol][cRow] >> 1 & 3;
                     int dx = SoffX + j * 20 - this.iconRefe[picIndex][2] + k * 10;
                     int dy = SoffY - this.iconRefe[picIndex][3] - k * 5;
                     this.drawBufferTileTemp(g, picIndex, dx, dy, translate, cCol, cRow, com);
                  }
               }
            }
         }

         SoffY += 10;
         ++beginCol;
         ++beginRow;
      }

   }

   private void drawBufferTileTemp(Graphics g, int argMapIndex, int argDrawX, int argDrawY, int translate, int col, int row, int sum) {
      if (argMapIndex != 0 && argMapIndex >= 1) {
         if (translate == 0) {
            Util.drawImage(g, imageCurrentMapTile[argMapIndex - 1], argDrawX, argDrawY, 0);
         } else if (translate == 1) {
            Util.drawImage(g, imageCurrentMapTile[argMapIndex - 1], 0 + argDrawX + (2 * (this.iconRefe[argMapIndex][2] + 1) - imageCurrentMapTile[argMapIndex - 1].getWidth()), argDrawY, 2);
         } else if (translate == 2) {
            Util.drawImage(g, imageCurrentMapTile[argMapIndex - 1], argDrawX, argDrawY + 0 + imageCurrentMapTile[argMapIndex - 1].getHeight() - 10, 1);
         } else if (translate == 3) {
            Util.drawImage(g, imageCurrentMapTile[argMapIndex - 1], 0 + argDrawX + (2 * (this.iconRefe[argMapIndex][2] + 1) - imageCurrentMapTile[argMapIndex - 1].getWidth()), argDrawY + 0 + imageCurrentMapTile[argMapIndex - 1].getHeight() - 9, 3);
         }
      }

   }

   private void drawBigObj(Graphics g, int leftColumn, int topRow, int rightColumn, int bottomRow) {
      if (leftColumn < 0) {
         leftColumn = 0;
      }

      if (topRow < 0) {
         topRow = 0;
      }

      if (rightColumn > currentTotalColumn) {
         rightColumn = currentTotalColumn;
      }

      if (bottomRow > currentTotalRow) {
         bottomRow = currentTotalRow;
      }

      boolean[] bigIsDraw = new boolean[this.bigOData.length];
      this.showBigCount = 0;

      for(int j = topRow; j < bottomRow; ++j) {
         for(int i = leftColumn; i < rightColumn; ++i) {
            if ((this.mapAttribute[i][j] & 16) != 0) {
               int tmp = this.findBig(i, j);
               if (tmp != -1) {
                  bigIsDraw[tmp] = true;
               }
            }
         }
      }

      this.objTaxis();
      this.findRedraw(bigIsDraw);
      this.drawAllBig(g);
   }

   private final void drawAllBig(Graphics g) {
      int length = this.bigId.length;

      for(int i = 0; i < length; ++i) {
         int index = this.bigId[i];
         if (index >= 0) {
            this.drawBigObj(g, index);
         } else {
            try {
               GameObject tmpgo = GOManager.curGameObj[-index - 1];
               if (isInScreen(tmpgo)) {
                  if (tmpgo.type == 1) {
                     if (!GOManager.isShowOtherPlayer) {
                        this.drawPlayerCart(g, tmpgo, true);
                     } else {
                        this.drawPlayerCart(g, tmpgo, false);
                     }
                  } else {
                     tmpgo.draw(g);
                  }
               }

               tmpgo.setDrawID(-1);
            } catch (Exception var6) {
            }
         }
      }

   }

   public void drawPlayerCart(Graphics g, GameObject tmpgo, boolean isLowPic) {
      if (!isLowPic || tmpgo.getID() == Player.getInstance().getID()) {
         OtherPlayer tmpgo1 = (OtherPlayer)tmpgo;
         if (tmpgo1.cartPlayer != null && tmpgo1.horseState == 1) {
            if (tmpgo1.cartPlayer.getMapY() <= tmpgo.getMapY()) {
               tmpgo1.cartPlayer.drawBody(g, tmpgo1.cartX, tmpgo1.cartY);
               tmpgo1.draw(g);
            } else {
               tmpgo1.draw(g);
               tmpgo1.cartPlayer.drawBody(g, tmpgo1.cartX, tmpgo1.cartY);
            }
         } else {
            tmpgo1.cartPlayer = null;
            tmpgo.draw(g);
         }

      }
   }

   public static final boolean isInScreen(GameObject obj) {
      if (obj.type != 1 && obj.type != 2 && obj.type != 3 && obj.type != 9) {
         if (obj.type != 4 && obj.type != 5 && obj.type != 6 && obj.type != 7 && obj.type != 8) {
            if (obj.type != 10 && obj.type != 11) {
               return false;
            } else {
               return obj.drawX - (MainCanvas.red_flag.frame_w >> 1) < MainCanvas.screenW && obj.drawX - (MainCanvas.red_flag.frame_w >> 1) + MainCanvas.red_flag.frame_w > 0 && obj.drawY - (MainCanvas.red_flag.frame_h >> 1) < MAP_H && obj.drawY - (MainCanvas.red_flag.frame_h >> 1) + MainCanvas.red_flag.frame_h > 0;
            }
         } else {
            return obj.drawX - (MainCanvas.kuangimg.frame_w >> 1) < MainCanvas.screenW && obj.drawX - (MainCanvas.kuangimg.frame_w >> 1) + MainCanvas.kuangimg.frame_w > 0 && obj.drawY - (MainCanvas.kuangimg.frame_h >> 1) < MAP_H && obj.drawY - (MainCanvas.kuangimg.frame_h >> 1) + MainCanvas.kuangimg.frame_h > 0;
         }
      } else {
         ActiveGO ag = (ActiveGO)obj;
         return ag.getSadGODx() + ag.getDrawX() < MainCanvas.screenW && ag.getSadGODx() + ag.getDrawX() + ag.getSadGOWidth() > 0 && ag.getSadGODy() + ag.getDrawY() < MAP_H && ag.getSadGODy() + ag.getDrawY() + ag.getSadGOHeight() > 0;
      }
   }

   public boolean isInScreen(int index) {
      short tx = this.bigOData[index][0];
      short ty = this.bigOData[index][1];
      int picIndex = this.bigOData[index][3];
      int translate = this.mapAttribute[tx][ty] >>> 3 & 1;
      int drawX;
      if (translate == 1) {
         drawX = (20 * (tx - ty) >> 1) + currentTopX - currentWindowX + this.iconRefe[picIndex][2] + 2 - imageCurrentMapTile[picIndex - 1].getWidth();
      } else {
         drawX = (20 * (tx - ty) >> 1) + currentTopX - currentWindowX - this.iconRefe[picIndex][2] + 0;
      }

      int drawY = (10 * (tx + ty) >> 1) + 10 - currentWindowY - this.iconRefe[picIndex][3] + 0;
      int endX = drawX + imageCurrentMapTile[picIndex - 1].getWidth();
      int endY = drawY + imageCurrentMapTile[picIndex - 1].getHeight();
      return endX > 0 && drawX < MainCanvas.screenW && endY > 0 && drawY < MAP_H;
   }

   private final void drawBigObj(Graphics g, int index) {
      short tx = this.bigOData[index][0];
      short ty = this.bigOData[index][1];
      int picIndex = this.bigOData[index][3];
      int translate = this.mapAttribute[tx][ty] >>> 3 & 1;
      int drawX;
      if (translate == 1) {
         drawX = (20 * (tx - ty) >> 1) + currentTopX - currentWindowX + this.iconRefe[picIndex][2] + 2 - imageCurrentMapTile[picIndex - 1].getWidth();
      } else {
         drawX = (20 * (tx - ty) >> 1) + currentTopX - currentWindowX - this.iconRefe[picIndex][2] + 0;
      }

      int drawY = (10 * (tx + ty) >> 1) + 10 - currentWindowY - this.iconRefe[picIndex][3] + 0;
      if (picIndex == 333) {
         int dx = 0;
         int dy = 0;
         int cind = 0;

         for(int dwi = 0; dwi < cartoon.length; ++dwi) {
            if (tx == cartoon[dwi][0] && ty == cartoon[dwi][1]) {
               cind = dwi;
               dx = getCurrentCentreX(cartoon[dwi][0], cartoon[dwi][1]) - currentWindowX;
               dy = getCurrentCentreY(cartoon[dwi][0], cartoon[dwi][1]) - currentWindowY;
               break;
            }
         }

         this.drawRepetitionSpecial(g, cind, dx, dy, translate);
      }

      if (drawY < MAP_H + 0) {
         if (translate == 1) {
            Util.drawImage(g, imageCurrentMapTile[picIndex - 1], drawX, drawY, 2);
         } else {
            Util.drawImage(g, imageCurrentMapTile[picIndex - 1], drawX, drawY, 0);
         }
      }

   }

   private int findBig(int col, int row) {
      int bigCount = this.bigOData.length;

      for(int ind = 0; ind < bigCount; ++ind) {
         if (this.bigOData[ind][0] == col && this.bigOData[ind][1] == row && this.isInScreen(ind)) {
            ++this.showBigCount;
            return ind;
         }
      }

      return -1;
   }

   private final void findRedraw(boolean[] bigIsDraw) {
      GameObject[] drawGameObjects = GOManager.curGameObj;
      this.bigId = new int[this.showBigCount + drawGameObjects.length];
      this.showAllIndex = this.bigId.length - 1;

      int k;
      for(k = this.bigOData.length - 1; k >= 0; --k) {
         if (bigIsDraw[k]) {
            short tx = this.bigOData[k][0];
            short ty = this.bigOData[k][1];
            int picIndex = this.bigOData[k][3];
            int translate = this.mapAttribute[tx][ty] >>> 3 & 1;
            this.getRedrawId(k, tx - this.iconRefe[picIndex][translate] + 1, ty - this.iconRefe[picIndex][1 - translate] + 1);
            this.bigId[this.showAllIndex--] = k;
         }
      }

      for(k = GOManager.curGameObj.length - 1; k >= 0; --k) {
         GameObject tmpgo = GOManager.curGameObj[k];
         if (tmpgo != null && tmpgo.getDrawID() == -1) {
            tmpgo.setDrawID(-5);
            this.bigId[this.showAllIndex--] = -k - 1;
         }
      }

   }

   private void objTaxis() {
      for(int i = GOManager.curGameObj.length - 1; i > 0; --i) {
         for(int j = i - 1; j >= 0; --j) {
            if (GOManager.curGameObj[i].getMapY() < GOManager.curGameObj[j].getMapY()) {
               GameObject tmp = GOManager.curGameObj[i];
               GOManager.curGameObj[i] = GOManager.curGameObj[j];
               GOManager.curGameObj[j] = tmp;
            }
         }
      }

   }

   private void getRedrawId(int id, int sC, int sR) {
      for(int k = GOManager.curGameObj.length - 1; k >= 0; --k) {
         GameObject tmpgo = GOManager.curGameObj[k];
         if (tmpgo != null && tmpgo.getCol() >= sC && tmpgo.getRow() >= sR && tmpgo.getDrawID() == -1) {
            tmpgo.setDrawID(id);
            this.bigId[this.showAllIndex--] = -k - 1;
         }
      }

   }

   public void initSmallMap() {
      this.mapCols = currentTotalColumn;
      this.mapRows = currentTotalRow;
      if (this.mapCols != 0) {
         if (this.mapRows != 0) {
            if (this.mapCols > this.mapRows) {
               this.mapWidth = 50;
               this.tileWidth = this.mapWidth * 1000;
               this.tileWidth /= this.mapCols;
               this.tileHeight = this.tileWidth >> 1;
               this.mapHeight = this.tileHeight * this.mapRows;
               this.mapHeight /= 1000;
            } else {
               this.mapHeight = 25;
               this.tileHeight = this.mapHeight * 1000;
               this.tileHeight /= this.mapRows;
               this.tileWidth = this.tileHeight << 1;
               this.mapWidth = this.tileWidth * this.mapCols;
               this.mapWidth /= 1000;
            }

            this.startX = MAP_START_X - (this.mapWidth >> 1);
            this.startY = MAP_START_Y - (this.mapHeight >> 1);
         }
      }
   }

   private void drawSmallMap(Graphics g) {
      if (this.showSmallMap) {
         int[][] points = new int[][]{new int[2], {this.mapCols, 0}, {this.mapCols, this.mapRows}, {0, this.mapRows}};
         int[] xPoints = new int[4];
         int[] yPoints = new int[4];

         for(int i = 0; i < 4; ++i) {
            xPoints[i] = this.getMapX(points[i][0], points[i][1]);
            yPoints[i] = this.getMapY(points[i][0], points[i][1]);
         }

         g.setColor(16777173);
         g.drawLine(xPoints[0], yPoints[0] + 1, xPoints[1] - 1, yPoints[1]);
         g.drawLine(xPoints[1] - 1, yPoints[1], xPoints[2], yPoints[2] - 1);
         g.drawLine(xPoints[2], yPoints[2] - 1, xPoints[3] + 1, yPoints[3]);
         g.drawLine(xPoints[0], yPoints[0] + 1, xPoints[3] + 1, yPoints[3]);
         this.drawDoors(g);
         this.drawTeamMates(g);
      }
   }

   private void drawPlaceName(Graphics g) {
      int mapNameW = MainCanvas.curFont.stringWidth(this.mapName);
      int mapLevelW = MainCanvas.curFont.stringWidth(this.mapLevel);
      int w = (mapNameW > mapLevelW ? mapNameW : mapLevelW) + 20;
      int rectWidth = 76 > w ? 76 : w;
      byte rectHeight = 60;
      short rectY = (short)((MainCanvas.screenH - rectHeight - 36 >> 1) - 40);
      int placeY = rectY + (rectHeight - MainCanvas.CHARH * 2 >> 1);
      byte totalCount = 16;
      byte times = 8;
      byte colorType = 0;
      int[][] color = new int[][]{{16765320, 13740650, 8023640, 4866613, 4866613, 8023640, 13740650, 16765320}, {1461506, 2392068, 3455750, 4718344, 4718344, 3455750, 2392068, 1461506}, {5046786, 8389892, 12257286, 16714504, 16714504, 12257286, 8389892, 5046786}, {5064450, 8418820, 12299782, 16772360, 16772360, 12299782, 8418820, 5064450}};
      int positionX = MainCanvas.screenW - rectWidth >> 1;
      int positionY = rectY;
      if (isDrawPlaceName) {
         ++drawPlaceNameCount;
         if (drawPlaceNameCount == totalCount) {
            drawPlaceNameCount = 0;
            isDrawPlaceName = false;
            colorIndex = 0;
         } else if (drawPlaceNameCount % (totalCount / times) == 0) {
            ++colorIndex;
         }

         g.setColor(Cons.COLOR_MENU_BG);
         g.fillRect(positionX + 1, rectY + 1, rectWidth - 2, rectHeight - 2);
         int jiaoW = MainCanvas.ui_mjImg.getWidth();
         int jiaoH = MainCanvas.ui_mjImg.getHeight();
         g.drawImage(MainCanvas.ui_mjImg, positionX, rectY, 20);
         Util.drawImage(g, MainCanvas.ui_mjImg, positionX + rectWidth - MainCanvas.ui_mjImg.getWidth(), rectY, 2);
         Util.drawImage(g, MainCanvas.ui_mjImg, positionX, rectY + rectHeight - MainCanvas.ui_mjImg.getHeight(), 1);
         Util.drawImage(g, MainCanvas.ui_mjImg, positionX + rectWidth - MainCanvas.ui_mjImg.getWidth(), rectY + rectHeight - MainCanvas.ui_mjImg.getHeight(), 3);
         int[] kuangColor = new int[]{Cons.COLOR_MENU_OUT_BORDER_1, Cons.COLOR_MENU_OUT_BORDER_2, Cons.COLOR_MENU_OUT_BORDER_3};

         for(int i = 0; i < 3; ++i) {
            g.setColor(kuangColor[i]);
            g.drawLine(positionX + jiaoW, positionY + i * 1, positionX + rectWidth - jiaoW, positionY + i * 1);
            g.drawLine(positionX + jiaoW, positionY + rectHeight - 1 - i * 1, positionX + rectWidth - jiaoW, positionY + rectHeight - 1 - i * 1);
            g.drawLine(positionX + i * 1, positionY + jiaoH, positionX + i * 1, positionY + rectHeight - jiaoH);
            g.drawLine(positionX + rectWidth - 1 - i * 1, positionY + jiaoH, positionX + rectWidth - 1 - i * 1, positionY + rectHeight - jiaoH);
         }

         int[] kuangColor2 = new int[]{Cons.COLOR_MENU_INNER_BORDER_1, Cons.COLOR_MENU_INNER_BORDER_2, Cons.COLOR_MENU_INNER_BORDER_3, Cons.COLOR_MENU_INNER_BORDER_4};

         for(int i = 0; i < 4; ++i) {
            g.setColor(kuangColor2[i]);
            g.drawLine(positionX + jiaoW, positionY + 3 + 2 + i * 1, positionX + rectWidth - jiaoW, positionY + 3 + 2 + i * 1);
            g.drawLine(positionX + jiaoW, positionY + rectHeight - 1 - 3 - 2 - i * 1, positionX + rectWidth - jiaoW, positionY + rectHeight - 1 - 3 - 2 - i * 1);
            g.drawLine(positionX + 3 + 2 + i * 1, positionY + jiaoH, positionX + 3 + 2 + i * 1, positionY + rectHeight - jiaoH);
            g.drawLine(positionX + rectWidth - 1 - 3 - 2 - i * 1, positionY + jiaoH, positionX + rectWidth - 1 - 3 - 2 - i * 1, positionY + rectHeight - jiaoH);
         }

         g.setColor(color[colorType][colorIndex]);
         g.drawString(this.mapName, MainCanvas.screenW >> 1, placeY, 17);
         g.drawString(this.mapLevel, MainCanvas.screenW >> 1, placeY + MainCanvas.CHARH + 2, 17);
      }

   }

   private void drawNewMail(Graphics g) {
      if (PCMail.isNewMail) {
         UIForm.drawDelieve(g, MainCanvas.screenW - 10 - 2, this.startY - 10, (byte)1);
      }

   }

   private void drawEquipDurable(Graphics g) {
      if (PCEquip.durable != 0) {
         int eStartX = MainCanvas.screenW - 10 - 2 - 24;
         int eStartY = this.startY - 18;
         if ((PCEquip.durable & 1) != 0) {
            MainCanvas.e0MImg.draw(g, eStartX, eStartY, 1);
         } else {
            MainCanvas.e0MImg.draw(g, eStartX, eStartY, 0);
         }

         if ((PCEquip.durable & 2) != 0) {
            MainCanvas.e1MImg.draw(g, eStartX + 10, eStartY - 5, 1);
         } else {
            MainCanvas.e1MImg.draw(g, eStartX + 10, eStartY - 5, 0);
         }

         if ((PCEquip.durable & 4) != 0) {
            MainCanvas.e2MImg.draw(g, eStartX + 12, eStartY - 5 + 9, 1);
         } else {
            MainCanvas.e2MImg.draw(g, eStartX + 12, eStartY - 5 + 9, 0);
         }

         if ((PCEquip.durable & 8) != 0) {
            MainCanvas.e3MImg.draw(g, eStartX + 10 - 3, eStartY - 5 + 9, 1);
         } else {
            MainCanvas.e3MImg.draw(g, eStartX + 10 - 3, eStartY - 5 + 9, 0);
         }

         if ((PCEquip.durable & 16) != 0) {
            MainCanvas.e4MImg.draw(g, eStartX + 10, eStartY - 5 + 9 + 8, 1);
         } else {
            MainCanvas.e4MImg.draw(g, eStartX + 10, eStartY - 5 + 9 + 8, 0);
         }
      }

   }

   private int getMapX(int col, int row) {
      return (col - row) * this.tileWidth / 2000 + (this.mapWidth >> 1) + this.startX;
   }

   private int getMapY(int col, int row) {
      return (col + row) * this.tileHeight / 2000 + this.startY;
   }

   private void drawDoors(Graphics g) {
      if (curCarryPoint != null) {
         g.setColor(16524288);
         int cx = false;
         int cy = false;
         int i = 0;

         for(int kk = curCarryPoint.length; i < kk; ++i) {
            int col = curCarryPoint[i][0];
            int row = curCarryPoint[i][1];
            if (col != -1 || row != -1) {
               int cx = this.getMapX(col, row);
               int cy = this.getMapY(col, row);
               g.fillRect(cx - 1, cy - 1, 3, 3);
            }
         }

         this.drawFlags(g);
      }
   }

   private void drawFlags(Graphics g) {
   }

   private void drawCarryPointSpecial(Graphics g, int leftColumn, int topRow, int rightColumn, int bottomRow) {
      if (leftColumn < 0) {
         leftColumn = 0;
      }

      if (topRow < 0) {
         topRow = 0;
      }

      if (rightColumn > currentTotalColumn) {
         rightColumn = currentTotalColumn;
      }

      if (bottomRow > currentTotalRow) {
         bottomRow = currentTotalRow;
      }

      int SoffX = false;
      int SoffY = false;

      for(int c = 0; c < carryMap_Count; ++c) {
         if (repetitionSpecial_Y[c] >= topRow && repetitionSpecial_Y[c] <= bottomRow && repetitionSpecial_X[c] >= leftColumn && repetitionSpecial_X[c] <= rightColumn) {
            int SoffX = getCurrentCentreX(repetitionSpecial_X[c], repetitionSpecial_Y[c]);
            int SoffY = getCurrentCentreY(repetitionSpecial_X[c], repetitionSpecial_Y[c]);
            int dx = SoffX - currentWindowX;
            int dy = SoffY - currentWindowY - 70;
            byte[] var10000 = wod;
            var10000[c] = (byte)(var10000[c] ^ 1);
            switch(this.mapAttribute[repetitionSpecial_X[c]][repetitionSpecial_Y[c]] >> 1 & 3) {
            case 0:
               Util.drawImage(g, MainCanvas.mapArrImg, dx - 4, dy + wod[c] + MainCanvas.CHARH + 4, 2);
               break;
            case 1:
               Util.drawImage(g, MainCanvas.mapArrImg, dx - 8, dy + wod[c] + MainCanvas.CHARH + 4, 0);
               break;
            case 2:
               Util.drawImage(g, MainCanvas.mapArrImg, dx - 8, dy + wod[c] + MainCanvas.CHARH + 4, 0);
               break;
            case 3:
               Util.drawImage(g, MainCanvas.mapArrImg, dx - 4, dy + wod[c] + MainCanvas.CHARH + 4, 2);
            }
         }
      }

   }

   private void drawObjectName(Graphics g) {
      GameObject tecur = null;

      for(int i = GOManager.curGameObj.length - 1; i >= 0; --i) {
         tecur = GOManager.curGameObj[i];
         if (isInScreen(tecur)) {
            if (GOManager.isShowName && tecur.getType() != 2 || GOManager.currentTarget == tecur || tecur == Player.getInstance() || tecur.getType() == 3 || tecur.getType() == 2 && GOManager.isShowMonsterName) {
               tecur.drawName(g);
            }

            if (tecur.type == 3) {
               NPC npc = (NPC)tecur;
               if (npc.getTaskShowType() != 0) {
                  npc.drawTaskShowType(g);
               }
            }

            if (tecur.getType() == 1 || tecur.getType() == 3 || tecur.getType() == 2) {
               ((ActiveGO)tecur).drawHpChange(g, GOManager.curGameObj[i].drawX - 2, GOManager.curGameObj[i].drawY - 40);
            }
         }
      }

   }

   private void drawCarryPointName(Graphics g, int leftColumn, int topRow, int rightColumn, int bottomRow) {
      if (leftColumn < 0) {
         leftColumn = 0;
      }

      if (topRow < 0) {
         topRow = 0;
      }

      if (rightColumn > currentTotalColumn) {
         rightColumn = currentTotalColumn;
      }

      if (bottomRow > currentTotalRow) {
         bottomRow = currentTotalRow;
      }

      int SoffX = false;
      int SoffY = false;

      for(int c = 0; c < carryMap_Count; ++c) {
         if (repetitionSpecial_Y[c] >= topRow && repetitionSpecial_Y[c] <= bottomRow && repetitionSpecial_X[c] >= leftColumn && repetitionSpecial_X[c] <= rightColumn) {
            int SoffX = getCurrentCentreX(repetitionSpecial_X[c], repetitionSpecial_Y[c]);
            int SoffY = getCurrentCentreY(repetitionSpecial_X[c], repetitionSpecial_Y[c]);
            int dx = SoffX - currentWindowX - (Util.getStrLen(toMap_Name[c]) >> 1);
            int dy = SoffY - currentWindowY - 70;
            g.setColor(16768941);
            g.fillRoundRect(dx - 2, dy - 2, Util.getStrLen(toMap_Name[c]) + 4, MainCanvas.CHARH + 4, 6, 6);
            g.setColor(2955522);
            g.drawRoundRect(dx - 2, dy - 2, Util.getStrLen(toMap_Name[c]) + 4, MainCanvas.CHARH + 4, 6, 6);
            g.drawString(toMap_Name[c], dx, dy, 20);
         }
      }

   }

   public void initRepetitionSpecial(int x, int y) {
      this.width_2 = 11;
      this.height_2 = 18;
      this.w_h = 16;
      this.data_x0 = new int[this.w_h];
      this.data_x1 = new int[this.w_h];
      this.data_3 = new int[this.w_h];
      this.data_4 = new int[this.w_h];

      for(int i = 1; i < this.w_h; ++i) {
         this.data_3[i] = x + 7 + Math.abs(rd.nextInt() % 8);
         this.data_4[i] = y + 12 + Math.abs(rd.nextInt() % 14);
         this.data_x0[i] = Math.abs(rd.nextInt() % 1) + 1;
         this.data_x1[i] = Math.abs(rd.nextInt() % 1) + 1;
      }

   }

   private void drawRepetitionSpecial(Graphics g, int c, int x, int y, int tr) {
      if (tr == 0) {
         x -= 6;
      } else if (tr == 1) {
         x -= 14;
      }

      y -= 52;
      if (!isDrawCarryMap[c]) {
         isDrawCarryMap[c] = true;
         this.initRepetitionSpecial(x, y);
      }

      g.setColor(0);
      g.fillRect(x, y, 22, 37);

      for(int i = 1; i < this.w_h; ++i) {
         if (this.data_3[i] < x + 11 && this.data_4[i] < y + 18) {
            this.data_3[i] -= this.data_x0[i];
            this.data_4[i] -= this.data_x1[i];
         } else if (this.data_3[i] > x + 11 - 2 && this.data_4[i] < y + 18) {
            this.data_3[i] += this.data_x0[i];
            this.data_4[i] -= this.data_x1[i];
         } else if (this.data_3[i] < x + 11 && this.data_4[i] > y + 18) {
            this.data_3[i] -= this.data_x0[i];
            this.data_4[i] += this.data_x1[i];
         } else if (this.data_3[i] > x + 11 && this.data_4[i] > y + 18) {
            this.data_3[i] += this.data_x0[i];
            this.data_4[i] += this.data_x1[i];
         } else if (this.data_3[i] == x + 11 || this.data_4[i] == y + 18) {
            this.data_3[i] -= this.data_x0[i];
            this.data_4[i] -= this.data_x1[i];
         }

         if (this.data_3[i] < x || this.data_3[i] > x + 22 || this.data_4[i] < y || this.data_4[i] > y + 37) {
            this.data_3[i] = x + 7 + Math.abs(rd.nextInt() % 8);
            this.data_4[i] = y + 12 + Math.abs(rd.nextInt() % 14);
         }

         g.setColor(16777215);
         g.drawLine(this.data_3[i], this.data_4[i], this.data_3[i], this.data_4[i]);
      }

   }

   private void drawTeamMates(Graphics g) {
      if (m_bBattlefield) {
         this.drawBattlefieldPlayer(g, m_bBattlefieldMap);
         this.drawBattlefieldFlag(g);
      } else {
         this.drawOneself(g, 130823, Player.getInstance().nCol, Player.getInstance().nRow);
         g.setColor(87551);
         if (SITeam.teamMates != null) {
            for(int i = 0; i < SITeam.teamMates.length; ++i) {
               if (Player.getInstance().getMapID() == SITeam.teamMates[i].getMapID()) {
                  this.drawOneTeamMate(g, SITeam.teamMates[i]);
               }
            }
         }
      }

   }

   private void drawOneTeamMate(Graphics g, GameObject player) {
      if (player != null) {
         int col = getCurrentColumn(player.y, player.x);
         int row = getCurrentRow(player.y, player.x);
         if (col >= 0 && row >= 0) {
            g.fillRect(this.getMapX(col, row), this.getMapY(col, row), 3, 3);
         }
      }
   }

   private void drawOneself(Graphics g, int color, int col, int row) {
      g.setColor(color);
      g.fillRect(this.getMapX(col, row), this.getMapY(col, row), 3, 3);
   }

   private void drawBattlefieldPlayer(Graphics g, byte m_bBattlefieldMap) {
      switch(m_bBattlefieldMap) {
      case 1:
      case 4:
      default:
         break;
      case 2:
         this.drawOneself(g, 130823, Player.getInstance().nCol, Player.getInstance().nRow);
         break;
      case 3:
         OtherPlayer otherPlayerOneself = null;
         OtherPlayer otherPlayerRest = null;
         otherPlayerOneself = (OtherPlayer)GOManager.getObj(m_nBannerID[0]);
         otherPlayerRest = (OtherPlayer)GOManager.getObj(m_nBannerID[1]);
         if (otherPlayerOneself == null || otherPlayerRest == null) {
            this.drawOneself(g, 130823, Player.getInstance().nCol, Player.getInstance().nRow);
         }

         boolean color;
         int color;
         if (otherPlayerOneself != null && otherPlayerRest == null) {
            if (otherPlayerOneself.m_bGainedFlag) {
               color = false;
               if (otherPlayerOneself.m_bFlagLind == 1) {
                  color = 14024807;
               } else {
                  color = 87551;
               }

               if (otherPlayerOneself.getID() != Player.getInstance().getID() || otherPlayerRest == null) {
                  this.drawOneself(g, 130823, Player.getInstance().nCol, Player.getInstance().nRow);
               }

               this.drawOneself(g, color, otherPlayerOneself.getCol(), otherPlayerOneself.getRow());
            }
         } else if (otherPlayerOneself == null && otherPlayerRest != null) {
            if (otherPlayerRest.m_bGainedFlag) {
               color = false;
               if (otherPlayerRest.m_bFlagLind == 1) {
                  color = 14024807;
               } else {
                  color = 87551;
               }

               if (otherPlayerRest.getID() != Player.getInstance().getID() || otherPlayerOneself == null) {
                  this.drawOneself(g, 130823, Player.getInstance().nCol, Player.getInstance().nRow);
               }

               this.drawOneself(g, color, otherPlayerRest.getCol(), otherPlayerRest.getRow());
            }
         } else if (otherPlayerOneself != null && otherPlayerRest != null) {
            if (otherPlayerOneself.getID() != Player.getInstance().getID() && otherPlayerRest.getID() != Player.getInstance().getID() || otherPlayerOneself.getID() == Player.getInstance().getID() && !otherPlayerOneself.m_bGainedFlag || otherPlayerRest.getID() == Player.getInstance().getID() && !otherPlayerRest.m_bGainedFlag) {
               this.drawOneself(g, 130823, Player.getInstance().nCol, Player.getInstance().nRow);
            }

            if (otherPlayerOneself.getID() != Player.getInstance().getID() && otherPlayerOneself.m_bGainedFlag) {
               this.drawOneself(g, 14024807, otherPlayerOneself.getCol(), otherPlayerOneself.getRow());
            }

            if (otherPlayerRest.getID() != Player.getInstance().getID() && otherPlayerRest.m_bGainedFlag) {
               this.drawOneself(g, 87551, otherPlayerRest.getCol(), otherPlayerRest.getRow());
            }

            if (otherPlayerOneself.getID() == Player.getInstance().getID() && otherPlayerOneself.m_bGainedFlag) {
               this.drawOneself(g, 14024807, Player.getInstance().nCol, Player.getInstance().nRow);
            }

            if (otherPlayerRest.getID() == Player.getInstance().getID() && otherPlayerRest.m_bGainedFlag) {
               this.drawOneself(g, 87551, Player.getInstance().nCol, Player.getInstance().nRow);
            }
         }
      }

   }

   private void drawBattlefieldFlag(Graphics g) {
      PassiveGO flag = null;

      for(int i = 0; i < flagID.size(); ++i) {
         flag = (PassiveGO)GOManager.getObj((Integer)flagID.elementAt(i));
         if (flag != null) {
            if (flag.getImgInd() == 5) {
               g.setColor(14024807);
            } else if (flag.getImgInd() == 6) {
               g.setColor(87551);
            } else if (flag.getImgInd() == 7) {
               g.setColor(8157496);
            }

            g.fillRect(this.getMapX(flag.nCol, flag.nRow), this.getMapY(flag.nCol, flag.nRow), 3, 3);
         }
      }

   }

   public void initMap(byte _mapID, byte loadfromwhere) {
      try {
         if (this.iconRefe == null) {
            this.loadIconRefe("/refe.dat");
         }

         this.loadMap(Util.parseMapData(_datal, this.filelen));
         this.initSmallMap();
         currentTopX = (short)(20 * (currentTotalRow >> 1));
         this.curBufMap = null;
         MainCanvas.isWaiting = false;
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   public void adjustWindow(int x, int y) {
      currentWindowX = (short)(x - (MainCanvas.screenW >> 1));
      currentWindowY = (short)(y - (MAP_H >> 1));
   }

   public void setMap_Name(String mn) {
      this.mapName = mn;
   }

   public String getMap_Name() {
      return this.mapName;
   }

   public static short getCurrentRow(short argCurrentY, short argCurrentX) {
      return (short)((argCurrentY - (argCurrentX - currentTopX) * 10 / 20) / 10);
   }

   public static short getCurrentColumn(short argCurrentY, short argCurrentX) {
      return (short)((argCurrentY + (argCurrentX - currentTopX) * 10 / 20) / 10);
   }

   public static short getCurrentCentreX(int col, int row) {
      return (short)((20 * (col - row) >> 1) + currentTopX);
   }

   public static short getCurrentCentreY(int col, int row) {
      return (short)((10 * (col + row) >> 1) + 5);
   }

   public boolean isFloor(int col, int row) {
      if (col < currentTotalColumn && row < currentTotalRow && col >= 0 && row >= 0) {
         int tmpb = this.mapAttribute[col][row] >>> 5 & 1;
         return tmpb == 0;
      } else {
         return false;
      }
   }

   private boolean loadMap(byte[] _datal) {
      try {
         if (imageCurrentMapTile == null) {
            imageCurrentMapTile = new Image[this.iconRefe.length - 1];
            mapImgStr = new String[this.iconRefe.length - 1];
         }

         ByteArrayInputStream byteArrayIn = new ByteArrayInputStream(_datal);
         DataInputStream dis = new DataInputStream(byteArrayIn);
         dis.readUTF();
         currentTotalColumn = (short)dis.readInt();
         currentTotalRow = (short)dis.readInt();
         this.mapAttribute = new byte[currentTotalColumn][currentTotalRow];
         this.currentMapData = new short[currentTotalColumn][currentTotalRow];
         this.bigOData = new short[0][];
         cartoon = new short[0][2];

         int i;
         for(int j = 0; j < currentTotalRow; ++j) {
            for(int i = 0; i < currentTotalColumn; ++i) {
               this.mapAttribute[i][j] = dis.readByte();
               int wei = this.mapAttribute[i][j] >>> 4 & 1;
               if (wei == 0) {
                  this.currentMapData[i][j] = (short)(dis.readShort() >>> 6);
               } else if (wei == 1) {
                  i = dis.readInt();
                  if (i != 0) {
                     this.currentMapData[i][j] = (short)(i >>> 22);
                     short[] bigvalin = new short[]{(short)i, (short)j, (short)(i >> 10 & 4095), (short)(i & 1023)};
                     if (bigvalin[3] == 333) {
                        cartoon = Util.addArray(cartoon, new short[]{bigvalin[0], bigvalin[1]});
                     }

                     this.bigOData = Util.addArray(this.bigOData, bigvalin);
                  }
               }
            }
         }

         MainCanvas.mapBGColor = dis.readInt();
         this.bigODataTaxis();

         short tempA;
         for(byte eof = -1; (tempA = dis.readShort()) != eof; mapImgStr[tempA - 1] = "f-" + tempA + ".png") {
         }

         InputStream is = null;

         for(i = 0; i < mapImgStr.length; ++i) {
            if (mapImgStr[i] != null) {
               is = (new Object()).getClass().getResourceAsStream("/pkg_npc/tiles/" + mapImgStr[i]);
               DataInputStream disa = new DataInputStream(is);
               byte[] tmpArr = new byte[disa.available()];
               disa.read(tmpArr);

               for(int k = 0; k < tmpArr.length; ++k) {
                  tmpArr[k] = (byte)(tmpArr[k] ^ 50);
               }

               imageCurrentMapTile[i] = Image.createImage(tmpArr, 0, tmpArr.length);
               disa = null;
            }
         }

         is = null;

         for(i = 0; i < mapImgStr.length; ++i) {
            mapImgStr[i] = null;
         }

         dis.close();
         return true;
      } catch (Exception var11) {
         var11.printStackTrace();
         return false;
      }
   }

   private final void bigODataTaxis() {
      for(int i = this.bigOData.length - 1; i > 0; --i) {
         for(int j = i - 1; j >= 0; --j) {
            if (this.bigOData[i][2] < this.bigOData[j][2]) {
               short[] tmp = this.bigOData[i];
               this.bigOData[i] = this.bigOData[j];
               this.bigOData[j] = tmp;
            }
         }
      }

   }

   private void loadIconRefe(String path) {
      boolean var2 = false;

      try {
         InputStream is = Util.loadFile(path, true);
         DataInputStream dis = new DataInputStream(is);
         dis.readByte();
         dis.readByte();
         int iconCount = dis.readShort();
         int refeCount = dis.readByte();
         int iconCount = iconCount + 1;
         this.iconRefe = new short[iconCount][refeCount];

         for(int i = 0; i < iconCount; ++i) {
            for(int j = 0; j < refeCount; ++j) {
               this.iconRefe[i][j] = dis.readShort();
            }
         }

         dis.close();
      } catch (Exception var8) {
         var8.printStackTrace();
      }

   }

   public static void readWorldMapData(ByteArray tempd) {
      try {
         isDraw = false;
         tempd.readShort();
         byte w1 = worldMapCols;
         byte w2 = worldMapRows;
         worldMapCols = tempd.readByte();
         worldMapRows = tempd.readByte();
         System.err.println("WorldMap--cols=" + worldMapCols + "---rows=" + worldMapRows);
         byte[][] tmpWmData = (byte[][])null;
         tmpWmData = wmdata;
         wmdata = null;
         wmdata = new byte[worldMapCols][worldMapRows];

         int i;
         for(i = 0; i < worldMapCols; ++i) {
            for(int j = 0; j < worldMapRows; ++j) {
               byte b = tempd.readByte();
               byte dd = (byte)(255 & b);
               wmdata[i][j] = dd;
            }
         }

         tmpRegionCount = regionCount;
         regionCount = tempd.readByte();
         System.err.println("regionCount=" + regionCount);
         if (regionCount == 0) {
            if (MainCanvas.curForm != null) {
               MainCanvas.curForm.clientCommand = 131078;
            }

            worldMapCols = w1;
            worldMapRows = w2;
            wmdata = tmpWmData;
            isDraw = true;
            UITopForm.createLocalTopForm((byte)0, (String)"Bản đồ này chưa mở, không thể tra tìm", "Xác định", "", -1, -2);
            return;
         }

         regionID = null;
         regionName = null;
         regionCol = null;
         regionRow = null;
         regionType = null;
         regionNeighbor = null;
         regionSTATE = null;
         regionX = null;
         regionY = null;
         regionID = new short[regionCount];
         regionName = new String[regionCount];
         regionCol = new byte[regionCount];
         regionRow = new byte[regionCount];
         regionType = new byte[regionCount];
         regionNeighbor = new short[regionCount][4];
         regionSTATE = new byte[regionCount];
         regionX = new short[regionCount];
         regionY = new short[regionCount];

         for(i = 0; i < regionCount; ++i) {
            regionID[i] = tempd.readShort();
            regionName[i] = tempd.readUTF();
            regionCol[i] = tempd.readByte();
            regionRow[i] = tempd.readByte();
            regionType[i] = tempd.readByte();
            regionNeighbor[i][0] = tempd.readShort();
            regionNeighbor[i][1] = tempd.readShort();
            regionNeighbor[i][2] = tempd.readShort();
            regionNeighbor[i][3] = tempd.readShort();
            regionSTATE[i] = 2;
            regionX[i] = (short)(16 * regionCol[i]);
            regionY[i] = (short)(16 * regionRow[i]);
         }

         isDraw = true;
      } catch (Exception var8) {
         var8.printStackTrace();
      }

   }

   public void drawWorldMap(Graphics g, int clientCommand) {
      g.setClip(0, UITitle.getTitleBarHeight(), MainCanvas.screenW, MainCanvas.screenH - UITitle.getTitleBarHeight() - UITitle.getMenuBarHeight());
      if (clientCommand == 131078 || clientCommand == 131079) {
         this.drawWorldMap_Region(g);
      }

      g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
   }

   public void drawWorldMap_Region(Graphics g) {
      if (isDraw) {
         g.setColor(0);
         g.fillRect(0, 0, MainCanvas.screenW, MainCanvas.screenH);

         int i;
         for(i = 0; i < wmdata.length; ++i) {
            for(int j = 0; j < wmdata[i].length; ++j) {
               if (wmdata[i][j] != -1) {
                  int tx = i * 16;
                  int ty = j * 16;
                  tx -= worldMapSX;
                  ty -= worldMapSY;
                  if (tx >= -16 && tx <= MainCanvas.screenW + 16 && ty >= -16 && ty <= MainCanvas.screenH + 16) {
                     int index = wmdata[i][j] & 31;
                     switch((wmdata[i][j] & 224) >> 5) {
                     case 0:
                        Util.drawImage(g, imageMapRegion[index], tx, ty, 0);
                        break;
                     case 1:
                        Util.drawImage(g, imageMapRegion[index], tx, ty, 5);
                        break;
                     case 2:
                        Util.drawImage(g, imageMapRegion[index], tx, ty, 3);
                        break;
                     case 3:
                        Util.drawImage(g, imageMapRegion[index], tx, ty, 6);
                        break;
                     case 4:
                        Util.drawImage(g, imageMapRegion[index], tx, ty, 2);
                        break;
                     case 5:
                        Util.drawImage(g, imageMapRegion[index], tx, ty, 4);
                        break;
                     case 6:
                        Util.drawImage(g, imageMapRegion[index], tx, ty, 1);
                        break;
                     case 7:
                        Util.drawImage(g, imageMapRegion[index], tx, ty, 7);
                     }
                  }
               }
            }
         }

         for(i = 0; i < regionCount; ++i) {
            g.drawImage(imageRegionType[regionType[i]], regionX[i] - worldMapSX, regionY[i] - worldMapSY, 20);
         }

         if (playerRegionIND != -1 && regionX.length != 0) {
            g.drawImage(imagePlayerRegion, regionX[playerRegionIND] - worldMapSX, regionY[playerRegionIND] - worldMapSY, 20);
         }

         if (regionX.length != 0) {
            g.drawImage(MainCanvas.cursorImg, regionX[selRegionIND] - worldMapSX + MainCanvas.cursorImg.getWidth() / 2 + 1, regionY[selRegionIND] - worldMapSY + MainCanvas.cursorImg.getHeight() / 2, 20);
         }

      }
   }

   public static void adjustWorldMapWindow(int clientCommand) {
      try {
         if (clientCommand == 131078 || clientCommand == 131079) {
            short tx = (short)(regionX[selRegionIND] - worldMapSX - (MainCanvas.screenW >> 1));
            if (tx < 0) {
               if (Math.abs(tx) < 16) {
                  worldMapSX = (short)(worldMapSX - Math.abs(tx));
               } else {
                  worldMapSX = (short)(worldMapSX - 16);
               }
            } else if (tx > 0) {
               if (tx < 16) {
                  worldMapSX += tx;
               } else {
                  worldMapSX = (short)(worldMapSX + 16);
               }
            }

            short ty = (short)(regionY[selRegionIND] - worldMapSY - (MainCanvas.screenH - UITitle.getTitleBarHeight() - UITitle.getMenuBarHeight() >> 1));
            if (ty < 0) {
               if (Math.abs(ty) < 16) {
                  worldMapSY = (short)(worldMapSY - Math.abs(ty));
               } else {
                  worldMapSY = (short)(worldMapSY - 16);
               }
            } else if (ty > 0) {
               if (ty < 16) {
                  worldMapSY += ty;
               } else {
                  worldMapSY = (short)(worldMapSY + 16);
               }
            }
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      if (clientCommand == 131078 || clientCommand == 131079) {
         if (worldMapSX < 0) {
            worldMapSX = 0;
         } else if (worldMapCols * 16 - MainCanvas.screenW > 0 && worldMapSX > worldMapCols * 16 - MainCanvas.screenW) {
            worldMapSX = (short)(worldMapCols * 16 - MainCanvas.screenW);
         }

         if (worldMapSY < -UITitle.getTitleBarHeight()) {
            worldMapSY = (short)(-UITitle.getTitleBarHeight());
         } else if (worldMapRows * 16 - (MainCanvas.screenH - UITitle.getMenuBarHeight()) > 0 && worldMapSY > worldMapRows * 16 - (MainCanvas.screenH - UITitle.getMenuBarHeight())) {
            worldMapSY = (short)(worldMapRows * 16 - (MainCanvas.screenH - UITitle.getMenuBarHeight()));
         }
      }

   }

   public static short getRegionINDFromRegionID(short rid) {
      short rem = -1;

      for(short i = 0; i < regionCount; ++i) {
         if (regionID[i] == rid) {
            rem = i;
            break;
         }
      }

      return rem;
   }

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 131075:
         MainCanvas.isWaiting = true;
         ba.writeInt(GOManager.getCurrentTarget().getID());
         break;
      case 131076:
         MainCanvas.isWaiting = true;
         ba.writeInt(UIList.npcTransformID);
         ba.writeByte(UIList.npcTransformPoint);
         break;
      case 131078:
         MainCanvas.isWaiting = true;
         break;
      case 131079:
         MainCanvas.isWaiting = true;
         if (isOutMenu == 1) {
            if (!isInterLocalMap) {
               isInterLocalMap = true;
               w_RegionID = 32767;
            }

            w_RegionID_Real = w_RegionID;
            ba.writeShort(w_RegionID);
            w_RegionID = 32767;
         } else if (isOutMenu == 2) {
            w_RegionID_Real = w_RegionID;
            ba.writeShort(w_RegionID);
         }
         break;
      case 131080:
      case 131081:
         ba.writeShort(regionID[selRegionIND]);
         break;
      case 131103:
         ba.writeInt(mapCopyIndex);
         ba.writeInt(CopyX);
         ba.writeInt(CopyY);
         ba.writeInt(CopytoCol);
         ba.writeInt(CopytoRow);
         if (SITeam.m_bCopyDifficulty == 0) {
            ba.writeInt(4122);
         } else if (SITeam.m_bCopyDifficulty == 1) {
            ba.writeInt(4123);
         } else if (SITeam.m_bCopyDifficulty == 2) {
            ba.writeInt(4124);
         }
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      short num;
      int i;
      String name;
      boolean read;
      int j;
      short length;
      byte[] imgData;
      short length;
      switch(commID) {
      case -2147352575:
         if (MainCanvas.curTopForm != null && MainCanvas.curTopForm.type == 0) {
            MainCanvas.curTopForm = null;
         }

         MainCanvas.startLoadingProgress();
         MainCanvas.loadCount = 0;
         drawPlaceNameCount = 0;
         isDrawPlaceName = false;
         colorIndex = 0;
         MainCanvas.setState((byte)2);
         MainCanvas.loadRes();
         MainCanvas.loadCount = 10;
         Player.getInstance().isTreadMapCarryPoint = false;
         GOManager.removeAll();
         MainCanvas.loadCount = 20;
         MainCanvas.m_bFocusEnactment = 0;
         MainCanvas.m_bChoose = 0;
         MainCanvas.backForms.removeAllElements();
         MainCanvas.isFullUserData = true;
         MainCanvas.curFormVector.removeAllElements();
         MainCanvas.replaceFormId = -1;
         MainCanvas.setCurForm((UIForm)null);
         MainCanvas.loadCount = 30;
         MainCanvas.quitUI();
         MainCanvas.mc.leftRightMenu();
         Player.getInstance().horseState = 0;
         Player.getInstance().setMapID(data.readShort());
         String maN = data.readUTF();
         int dl = data.readInt();
         _datal = null;
         _datal = data.readByteArray(dl);
         MainCanvas.isinM = true;
         getInstance().initMap((byte)1, (byte)1);
         MainCanvas.loadCount = 40;
         getInstance().setMap_Name(maN);
         GOManager.setCurrentTarget(Player.getInstance());
         byte carryCount = data.readByte();
         carryMap_Count = data.readByte();
         curCarryPoint = new short[carryCount][2];
         toMap_Name = new String[carryMap_Count];
         carryMap_Type = new byte[carryMap_Count];
         repetitionSpecial_X = new short[carryMap_Count];
         repetitionSpecial_Y = new short[carryMap_Count];
         wod = new byte[carryMap_Count];
         isDrawCarryMap = new boolean[carryMap_Count];
         byte cc = 0;

         byte jj;
         for(jj = 0; cc < carryCount; ++cc) {
            curCarryPoint[cc][0] = data.readShort();
            curCarryPoint[cc][1] = data.readShort();
            if (data.readBoolean()) {
               toMap_Name[jj] = data.readUTF();
               carryMap_Type[jj] = data.readByte();
               repetitionSpecial_X[jj] = curCarryPoint[cc][0];
               repetitionSpecial_Y[jj] = curCarryPoint[cc][1];
               isDrawCarryMap[jj] = false;
               ++jj;
            }
         }

         cc = data.readByte();
         jj = data.readByte();
         getInstance().mapLevel = data.readUTF();
         MainCanvas.loadCount = 100;
         isDrawPlaceName = true;
         break;
      case -2147352574:
         GOManager.removeAll();
         Monster.loadStaticTiles(data);
         break;
      case -2147352570:
         MainCanvas.isWaiting = false;
         if (MainCanvas.curForm != null && MainCanvas.backForms.size() > 0 && isOutMenu != 1) {
            MainCanvas.backForms.removeAllElements();
         }

         MainCanvas.curFormVector.removeAllElements();
         w_RegionID_Real = w_RegionID;
         num = data.readShort();
         readWorldMapData(data);
         playerRegionIND = selRegionIND = getRegionINDFromRegionID(num);
         worldMapSX = (short)(regionX[selRegionIND] - (MainCanvas.screenW >> 1));
         worldMapSY = (short)(regionY[selRegionIND] - (MainCanvas.screenH - UITitle.getTitleBarHeight() - UITitle.getMenuBarHeight() >> 1));
         UIForm worldForm = new UIForm((short)0, (short)0, (short)MainCanvas.screenW, (short)MainCanvas.screenH);
         UITitle title = new UITitle((byte)0, worldForm);
         title.setTitleText(regionName[selRegionIND]);
         title.setCanFocus(true);
         title.setFocus(true, worldForm);
         title.setAroundFocusNull();
         worldForm.addComponent(title);
         UITitle menu = new UITitle((byte)1, worldForm);
         menu.setMenuText(new String[]{"Vào", "Quay về"});
         worldForm.addComponent(menu);
         worldForm.clientCommand = 131078;
         MainCanvas.curForm = worldForm;
         if (MainCanvas.getGameState() != 4) {
            MainCanvas.setGameState((byte)4);
         }
         break;
      case -2147352569:
         MainCanvas.isWaiting = false;
         length = data.readShort();
         length = playerMT;
         playerMT = data.readShort();
         readWorldMapData(data);
         if (regionCount == 0) {
            regionCount = tmpRegionCount;
            playerMT = length;
            return;
         }

         if (isOutMenu == 2 && isMapDetail == 0) {
            SIChat.removeChatLocal();
            UIMenu.formSaveCommand();
         } else if (isMapDetail == 1) {
            isMapDetail = 0;
         } else if (MainCanvas.curForm != null && MainCanvas.backForms.size() > 0 && isOutMenu != 2) {
            MainCanvas.backForms.removeAllElements();
         }

         MainCanvas.curFormVector.removeAllElements();
         w_RegionID = w_RegionID_Real;
         playerRegionIND = selRegionIND = getRegionINDFromRegionID(length);
         if (playerRegionIND == -1) {
            selRegionIND = 0;
         }

         worldMapSX = (short)(regionX[selRegionIND] - (MainCanvas.screenW >> 1));
         worldMapSY = (short)(regionY[selRegionIND] - (MainCanvas.screenH - UITitle.getTitleBarHeight() - UITitle.getMenuBarHeight() >> 1));
         UIForm reigonForm = new UIForm((short)0, (short)0, (short)MainCanvas.screenW, (short)MainCanvas.screenH);
         UITitle ifjtitle = new UITitle((byte)0, reigonForm);
         ifjtitle.setTitleText(regionName[selRegionIND]);
         ifjtitle.setCanFocus(true);
         ifjtitle.setFocus(true, reigonForm);
         ifjtitle.setAroundFocusNull();
         UITitle menuBar = new UITitle((byte)1, reigonForm);
         menuBar.setMenuText(new String[]{"thao tác", "Quay về"});
         reigonForm.addComponent(ifjtitle);
         reigonForm.addComponent(menuBar);
         reigonForm.clientCommand = 131079;
         MainCanvas.curForm = reigonForm;
         if (MainCanvas.getGameState() != 4) {
            MainCanvas.setGameState((byte)4);
         }
         break;
      case -2147352563:
         num = data.readShort();

         for(i = 0; i < num; ++i) {
            name = data.readShort() + ".png";
            read = false;

            for(j = 0; j < Monster.imgSTiles.length; ++j) {
               if (Monster.temPngName[j] != null && Monster.temPngName[j].equals(name)) {
                  length = data.readShort();
                  if (length > 0) {
                     imgData = data.readByteArray(length);
                     Monster.imgSTiles[j] = Image.createImage(imgData, 0, imgData.length);
                  }

                  read = true;
                  break;
               }
            }

            if (!read) {
               length = data.readShort();
               if (length > 0) {
                  data.readByteArray(length);
               }
            }
         }

         return;
      case -2147352562:
         num = data.readShort();

         for(i = 0; i < num; ++i) {
            name = "f-" + data.readShort() + ".png";
            read = false;

            for(j = 0; j < imageCurrentMapTile.length; ++j) {
               if (mapImgStr[j] != null && mapImgStr[j].equals(name)) {
                  length = data.readShort();
                  if (length > 0) {
                     imgData = data.readByteArray(length);
                     imageCurrentMapTile[j] = Image.createImage(imgData, 0, imgData.length);
                  }

                  read = true;
                  break;
               }
            }

            if (!read) {
               length = data.readShort();
               if (length > 0) {
                  data.readByteArray(length);
               }
            }
         }

         return;
      case -2147352561:
         m_bBattlefield = true;
         battleDoorOpen = data.readBoolean();
         if (!battleDoorOpen) {
            byte extent = data.readByte();
            doorPosition = new byte[extent][2];
            flagplace = new byte[extent][2];

            for(int i = 0; i < doorPosition.length; ++i) {
               for(int j = 0; j < 2; ++j) {
                  doorPosition[i][j] = data.readByte();
               }

               flagplace[i][0] = (byte)(doorPosition[i][0] - 4);
               flagplace[i][1] = (byte)(doorPosition[i][1] - 5);
            }
         }

         m_bBattlefieldMap = data.readByte();
         m_bCamp = data.readByte();
      case -2147352560:
      default:
         break;
      case -2147352545:
         mapCopyIndex = data.readInt();
         CopyX = data.readInt();
         CopyY = data.readInt();
         CopytoCol = data.readInt();
         CopytoRow = data.readInt();
         MainCanvas.ni.send(131103);
         break;
      case -2147352544:
         m_bCopyEnret = data.readBoolean();
      }

   }

   public void drawMapArr(Graphics g) {
      this.arrY ^= 1;
      if (maparr_up == null) {
         maparr_up = Util.loadImage("/maparr_up.png");
         maparr_down = Util.loadImage("/maparr_down.png");
      }

      int i;
      int x;
      int y;
      switch(mapIndex) {
      case 0:
         for(i = 0; i < this.mapArr_Ren.length; ++i) {
            x = getCurrentCentreX(this.mapArr_Ren[i][1], this.mapArr_Ren[i][0]) - currentWindowX;
            y = getCurrentCentreY(this.mapArr_Ren[i][1], this.mapArr_Ren[i][0]) - currentWindowY;
            Util.drawImage(g, maparr_down, x, y + this.arrY, 2);
         }

         return;
      case 1:
         for(i = 0; i < this.mapArr_Xian.length; ++i) {
            x = getCurrentCentreX(this.mapArr_Xian[i][1], this.mapArr_Xian[i][0]) - currentWindowX;
            y = getCurrentCentreY(this.mapArr_Xian[i][1], this.mapArr_Xian[i][0]) - currentWindowY;
            Util.drawImage(g, maparr_down, x, y + this.arrY, 2);
         }

         return;
      case 2:
         for(i = 0; i < this.mapArr_Yao.length; ++i) {
            x = getCurrentCentreX(this.mapArr_Yao[i][1], this.mapArr_Yao[i][0]) - currentWindowX;
            y = getCurrentCentreY(this.mapArr_Yao[i][1], this.mapArr_Yao[i][0]) - currentWindowY;
            if (i >= 5) {
               Util.drawImage(g, maparr_down, x, y + this.arrY, 2);
            } else {
               Util.drawImage(g, maparr_down, x, y + this.arrY, 0);
            }
         }

         return;
      case 3:
         for(i = 0; i < this.mapArr_Mo.length; ++i) {
            x = getCurrentCentreX(this.mapArr_Mo[i][1], this.mapArr_Mo[i][0]) - currentWindowX;
            y = getCurrentCentreY(this.mapArr_Mo[i][1], this.mapArr_Mo[i][0]) - currentWindowY;
            if (i != 3 && i != 5) {
               Util.drawImage(g, maparr_up, x, y + this.arrY, 2);
            } else {
               Util.drawImage(g, maparr_up, x, y + this.arrY, 0);
            }
         }
      }

   }

   public static boolean checkDoorCanThr() {
      int row = Player.getInstance().getRow();
      int col = Player.getInstance().getCol();

      for(int i = 0; i < doorPosition.length; ++i) {
         if (row == doorPosition[i][0] && col == doorPosition[i][1]) {
            return false;
         }
      }

      return true;
   }

   public void drawGate(Graphics g) {
      if (this.gate == null) {
         this.gate = Util.loadImage("/special/gate.png");
      }

      if (m_bBattlefieldMap != 1) {
         int i;
         int x;
         int y;
         if (m_bBattlefieldMap == 2) {
            for(i = 0; i < flagplace.length; ++i) {
               x = getCurrentCentreX(flagplace[i][1], flagplace[i][0]) - currentWindowX;
               y = getCurrentCentreY(flagplace[i][1], flagplace[i][0]) - currentWindowY;
               Util.drawImage(g, this.gate, x, y, 2);
            }
         } else if (m_bBattlefieldMap == 3 && (m_bCamp == 1 || m_bCamp == 2)) {
            for(i = 0; i < flagplace.length; ++i) {
               x = getCurrentCentreX(flagplace[i][1], flagplace[i][0]) - currentWindowX;
               y = getCurrentCentreY(flagplace[i][1], flagplace[i][0]) - currentWindowY;
               Util.drawImage(g, this.gate, x, y, 0);
            }
         }
      }

   }
}

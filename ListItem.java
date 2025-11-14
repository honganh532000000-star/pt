import javax.microedition.lcdui.Graphics;

public class ListItem {
   public static final byte ITEM_TYPE_NORMAL = 0;
   public static final byte ITEM_TYPE_PIC = 1;
   public static final byte ITEM_TYPE_TEAM = 2;
   public static final byte ITEM_TYPE_WAR = 3;
   public static final byte ITEM_TYPE_OPTION = 4;
   public static final byte ITEM_TYPE_AUCTION = 5;
   public static final byte ITEM_TYPE_GOODS_PIC = 6;
   public static final byte ITEM_TYPE_MENTOR = 7;
   public static final byte ITEM_TYPE_SKILL = 8;
   public static final byte ITEM_FARM_DYNAMIC = 9;
   private byte itemType;
   private String itemName;
   public byte rankType = -1;
   public short skillImgInd = -1;
   private int warNum;
   private byte colorIndex;
   private byte taskType = -1;
   private byte taskType2 = -1;
   private short wpType = -1;
   private boolean canRefresh = true;
   private byte transformID = -1;
   private boolean isOL;
   public int playerID = -1;
   public short objX = -1;
   public short objY = -1;
   public static final byte ITEM_TYPE_PIC_GRAY_TAN = 2;
   public static final byte ITEM_TYPE_PIC_GRAY_WEN = 4;
   public static final byte ITEM_TYPE_PIC_YELLOW_TAN = 1;
   public static final byte ITEM_TYPE_PIC_YELLOW_WEN = 3;
   public static final byte ITEM_TYPE_PIC_HARD = 5;
   public static final byte ITEM_TYPE_PIC_NORMAL = 6;
   public static final byte ITEM_TYPE_PIC_EASY = 7;
   public static final byte ITEM_TYPE_PIC_IMPOSSIBLE = 8;
   public String keyStr = "";
   public int itemId = 0;
   public long timeStamp = 0L;
   public byte forgIndex = 0;
   public short m_bBattlefieldGenre = 0;
   public short m_bBattlefieldExamine = 0;
   public String teammateName = null;
   public int teammateId = -1;
   public boolean isLeader = false;
   public short teammateProfession = -1;
   public short teammateLevel = -1;
   public int curHP = -1;
   public int maxHP = -1;
   public int curMP = -1;
   public int maxMP = -1;
   private int itemNameWidth;
   public long auctionID = -1L;
   public short stuffPicID = -1;
   public byte stuffNum = -1;
   public byte stuffQuality = -1;
   public short stuffLevel = -1;
   public byte stuffMaxNum = -1;
   public int stuffMaxPrice = -1;
   public int stuffSurePrice = -1;
   public String stuffLeftTimeStr;
   public static final int LIST_TMP_Y = 6;
   private int m_nMenuIndex = 0;
   private int m_nCharacterIndex = 0;
   private int m_nColo = 0;
   private static final int TEAM_ITEM_HEIGHT = 14;
   private static final int TEAM_ITEM_WIDTH = 27;
   private static int TEAM_HP_FRAME_WIDTH = 45;
   private static int TEAM_HP_FRAME_HEIGHT = 8;

   public byte getTaskType2() {
      return this.taskType2;
   }

   public void setTaskType2(byte taskType2) {
      this.taskType2 = taskType2;
   }

   public short getSkillImgInd() {
      return this.skillImgInd;
   }

   public void setSkillImgInd(short skillImgInd) {
      this.skillImgInd = skillImgInd;
   }

   public short getWpType() {
      return this.wpType;
   }

   public void setWpType(short wpType) {
      this.wpType = wpType;
   }

   public boolean isCanRefresh() {
      return this.canRefresh;
   }

   public void setCanRefresh(boolean canRefresh) {
      this.canRefresh = canRefresh;
   }

   public byte getTransformID() {
      return this.transformID;
   }

   public void setTransformID(byte transformID) {
      this.transformID = transformID;
   }

   public boolean isOL() {
      return this.isOL;
   }

   public void setOL(boolean isOL) {
      this.isOL = isOL;
   }

   public int getWarNum() {
      return this.warNum;
   }

   public void setWarNum(int warNum) {
      this.warNum = warNum;
   }

   public String getKeyStr() {
      return this.keyStr;
   }

   public void setKeyStr(String keystr) {
      this.keyStr = keystr;
   }

   public String getKeyStr(int keyValue) {
      if (keyValue == 0) {
         return "";
      } else {
         int i = 0;

         while(true) {
            keyValue >>= 1;
            if (keyValue == 0) {
               return MainCanvas.KEY_STR[i];
            }

            ++i;
         }
      }
   }

   public ListItem(String itemName, byte itemType, byte colorIndex, int itemWidth) {
      this.itemName = itemName;
      this.itemType = itemType;
      this.colorIndex = colorIndex;
      this.itemNameWidth = MainCanvas.curFont.stringWidth(this.itemName);
      if (this.itemNameWidth > itemWidth - 6) {
         this.itemNameWidth = itemWidth - 6;
      }

   }

   public ListItem(byte itemType) {
      this.itemType = itemType;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setItemName(String itemName) {
      this.itemName = itemName;
   }

   public byte getItemType() {
      return this.itemType;
   }

   public void setItemType(byte itemType) {
      this.itemType = itemType;
   }

   public void draw(Graphics g, int itemX, int itemY, int itemWidth, UIScroll scroll, boolean isSelected, boolean isLocked) {
      int wordNum;
      int wordNum;
      int teamItemWidth;
      int nameWidth;
      int levelDx;
      int tmpDX;
      int numWidth;
      short scrollWidth;
      switch(this.itemType) {
      case 0:
      case 5:
         if (isSelected && this.itemType == 0) {
            g.setColor(Cons.COLOR_PANEL_BG);
         } else {
            wordNum = 0;
            switch(this.colorIndex) {
            case 0:
               wordNum = Cons.COLOR_FONT_1;
               break;
            case 1:
               wordNum = Cons.COLOR_FONT_1;
               break;
            case 2:
               wordNum = Cons.COLOR_STUFF_QUALITY[0];
               break;
            case 3:
               wordNum = 0;
               break;
            case 4:
               wordNum = Cons.COLOR_STUFF_QUALITY[2];
               break;
            case 5:
               wordNum = Cons.COLOR_STUFF_QUALITY[3];
               break;
            case 6:
               wordNum = Cons.COLOR_STUFF_QUALITY[4];
               break;
            case 7:
               wordNum = Cons.COLOR_STUFF_QUALITY[5];
               break;
            case 8:
               wordNum = Cons.COLOR_STUFF_QUALITY[6];
            }

            g.setColor(wordNum);
         }

         if (isSelected) {
            Util.drawRollStr(g, this.itemName, itemX, itemY, itemWidth - 6, (byte)0, (byte)3);
         } else if (6 + Util.getStrLen(this.itemName) <= itemWidth) {
            g.drawString(this.itemName, itemX, itemY, 20);
         } else {
            wordNum = 1;

            for(wordNum = Util.getStrLen(this.itemName.substring(0, wordNum)); wordNum < itemWidth; wordNum = Util.getStrLen(this.itemName.substring(0, wordNum))) {
               ++wordNum;
               if (wordNum > this.itemName.length()) {
                  break;
               }
            }

            --wordNum;
            g.drawString(this.itemName.substring(0, wordNum) + "...", itemX, itemY, 20);
         }
         break;
      case 1:
         if (isSelected) {
            g.setColor(Cons.COLOR_PANEL_BG);
         } else {
            g.setColor(Cons.COLOR_FONT_1);
         }

         byte picIndex = -1;
         switch(this.taskType) {
         case 1:
            picIndex = 5;
            break;
         case 2:
            picIndex = 6;
            break;
         case 3:
            picIndex = 3;
            break;
         case 4:
            picIndex = 4;
            break;
         case 5:
            picIndex = 0;
            break;
         case 6:
            picIndex = 1;
            break;
         case 7:
            picIndex = 2;
            break;
         case 8:
            picIndex = 6;
         }

         MainCanvas.taskIconImg.draw(g, itemX, itemY, picIndex);
         if (isSelected) {
            Util.drawRollStr(g, this.itemName, itemX + MainCanvas.taskIconImg.frame_w + 4, itemY, itemWidth - 6 - MainCanvas.taskIconImg.frame_w - 4, (byte)0, (byte)3);
         } else if (6 + MainCanvas.taskIconImg.frame_w + 4 + Util.getStrLen(this.itemName) <= itemWidth) {
            g.drawString(this.itemName, itemX + MainCanvas.taskIconImg.frame_w + 4, itemY, 20);
         } else {
            wordNum = itemWidth / MainCanvas.CHARW - 1;
            g.drawString(this.itemName.substring(0, wordNum) + "...", itemX + MainCanvas.taskIconImg.frame_w + 4, itemY, 20);
         }
         break;
      case 2:
         scrollWidth = 0;
         if (scroll != null) {
            scrollWidth = scroll.getWidth();
         }

         teamItemWidth = 27 * MainCanvas.screenW / 176;
         nameWidth = 14 * MainCanvas.screenH / 208;
         if (isSelected) {
            Util.fillRect(g, itemX + teamItemWidth + 1, itemY, itemWidth - teamItemWidth - scrollWidth - 1, nameWidth, Cons.COLOR_FONT_1);
            g.setColor(Cons.COLOR_PANEL_BG);
         } else {
            Util.fillRect(g, itemX + teamItemWidth + 1, itemY, itemWidth - teamItemWidth - scrollWidth - 1, nameWidth, Cons.COLOR_PANEL_BORDER_2);
            g.setColor(Cons.COLOR_FONT_1);
         }

         g.drawString(this.teammateName, itemX + teamItemWidth + 3, itemY, 20);
         if (this.isLeader) {
            String tmpStr = "(Đội Trưởng)";
            if (SITeam.teamState == 2) {
               tmpStr = "(Đoàn Trưởng)";
            }

            g.drawString(tmpStr, itemX + teamItemWidth + 3 + g.getFont().stringWidth(this.teammateName), itemY, 20);
         }

         g.setColor(Cons.COLOR_FONT_1);
         g.drawLine(itemX, itemY - 1, itemX + itemWidth - scrollWidth - 1, itemY - 1);
         g.drawLine(itemX, itemY + nameWidth, itemX + itemWidth - scrollWidth - 1, itemY + nameWidth);
         g.drawLine(itemX + teamItemWidth, itemY, itemX + teamItemWidth, itemY + nameWidth);
         if (scrollWidth != 0) {
            g.drawLine(itemX + itemWidth - scrollWidth - 1, itemY, itemX + itemWidth - scrollWidth - 1, itemY + nameWidth);
         }

         MainCanvas.zyMImg.draw(g, itemX + 1, itemY + (nameWidth - MainCanvas.zyMImg.frame_h >> 1), this.teammateProfession);
         levelDx = itemX + 15;
         int levelDy = itemY + (nameWidth - MainCanvas.ui_dataNumMImg.frame_h >> 1);
         Util.drawIntNum(g, MainCanvas.ui_dataNumMImg, (long)this.teammateLevel, levelDx, levelDy);
         String tmpText = "Sinh Mệnh";
         tmpDX = itemX + 2 + g.getFont().stringWidth(tmpText);
         int showWidth = MainCanvas.ui_dataNumMImg.frame_w * 11 + 12;
         g.drawString("Sinh Mệnh", itemX + 1, itemY + nameWidth + 1, 20);
         int numLength = String.valueOf(this.curHP).length() + String.valueOf(this.maxHP).length();
         numWidth = (numLength + 1) * MainCanvas.ui_dataNumMImg.frame_w + numLength + 2;
         Util.drawDoubleNum(g, this.curHP, this.maxHP, tmpDX + (showWidth - numWidth >> 1), itemY + 1 + nameWidth + (nameWidth - MainCanvas.ui_dataNumMImg.frame_h >> 1));
         int tmpColor = false;
         tmpText = "Nội Công";
         int tmpColor = 18416;
         g.drawString(tmpText, itemX + 1, itemY + (nameWidth << 1), 20);
         numLength = String.valueOf(this.curMP).length() + String.valueOf(this.maxMP).length();
         numWidth = (numLength + 1) * MainCanvas.ui_dataNumMImg.frame_w + numLength + 2;
         Util.drawDoubleNum(g, this.curMP, this.maxMP, tmpDX + (showWidth - numWidth >> 1), itemY + (nameWidth << 1) + (nameWidth - MainCanvas.ui_dataNumMImg.frame_h >> 1));
         tmpDX += showWidth + 2;
         int teamHPFrameW = TEAM_HP_FRAME_WIDTH * MainCanvas.screenW / 176;
         int teamHPW = teamHPFrameW - 2;
         int teamHPH = TEAM_HP_FRAME_HEIGHT - 2;
         Util.drawRect(g, tmpDX, itemY + 1 + nameWidth + (nameWidth - TEAM_HP_FRAME_HEIGHT >> 1), teamHPFrameW, TEAM_HP_FRAME_HEIGHT, Cons.COLOR_FONT_1);
         Util.drawRect(g, tmpDX, itemY + (nameWidth << 1) + (nameWidth - TEAM_HP_FRAME_HEIGHT >> 1), teamHPFrameW, TEAM_HP_FRAME_HEIGHT, Cons.COLOR_FONT_1);
         Util.fillRect(g, tmpDX + 1, itemY + 1 + nameWidth + (nameWidth - TEAM_HP_FRAME_HEIGHT >> 1) + 1, Math.min(this.curHP * teamHPW / this.maxHP, teamHPW), teamHPH, 15728640);
         Util.fillRect(g, tmpDX + 1, itemY + 1 + (nameWidth << 1) + (nameWidth - TEAM_HP_FRAME_HEIGHT >> 1), Math.min(this.curMP * teamHPW / this.maxMP, teamHPW), teamHPH, tmpColor);
         break;
      case 3:
         g.setColor(Cons.COLOR_FONT_1);
         g.drawString(this.getItemName(), itemX, itemY - 0, 20);
         Util.drawIntNum(g, (long)this.getWarNum(), MainCanvas.curFont.stringWidth(this.getItemName()) + itemX, itemY - 0 + (MainCanvas.CHARH - MainCanvas.ui_dataNumMImg.frame_h >> 1));
         break;
      case 4:
         scrollWidth = 0;
         if (scroll != null) {
            scrollWidth = scroll.getWidth();
         }

         teamItemWidth = itemWidth - scrollWidth - 4;
         nameWidth = teamItemWidth - 2 >> 1;
         levelDx = 2 + itemX;
         int keyX = levelDx + nameWidth + 2;
         tmpDX = MainCanvas.curFont.stringWidth(this.itemName);
         int height = getOptionItemHeight();
         if (isSelected) {
            g.setColor(Cons.COLOR_FONT_1);
            Util.fillRect(g, levelDx - 1 + (nameWidth - tmpDX >> 1), itemY + 1, tmpDX + 2, height - 2, Cons.COLOR_FONT_1);
            g.setColor(Cons.COLOR_PANEL_BG);
         } else {
            g.setColor(Cons.COLOR_FONT_1);
         }

         g.drawString(this.itemName, levelDx + (nameWidth - tmpDX >> 1), itemY + (height - MainCanvas.CHARH >> 1), 20);
         Util.drawRect(g, keyX + 1, itemY + 1, nameWidth - 2, height - 2, Cons.COLOR_TEXT_BORDER);
         boolean showKey = true;
         numWidth = Cons.COLOR_TEXT_BG;
         if (isLocked) {
            showKey = false;
            if (MainCanvas.countTick % 4 > 1) {
               numWidth = 16777215;
            }
         }

         Util.fillRect(g, keyX + 2, itemY + 2, nameWidth - 4, height - 4, numWidth);
         if (showKey) {
            g.setColor(Cons.COLOR_FONT_1);
            g.drawString(this.keyStr, keyX + (nameWidth - MainCanvas.curFont.stringWidth(this.keyStr) >> 1), itemY + (height - MainCanvas.CHARH >> 1), 20);
         }
      case 6:
      default:
         break;
      case 7:
         if (isSelected) {
            if (this.isOL) {
               g.setColor(Cons.COLOR_PANEL_BG);
            } else {
               g.setColor(8224125);
            }
         } else if (this.isOL) {
            g.setColor(Cons.COLOR_FONT_1);
         } else {
            g.setColor(8224125);
         }

         if (isSelected) {
            Util.drawRollStr(g, this.itemName, itemX, itemY, itemWidth - 6, (byte)0, (byte)3);
         } else if (6 + Util.getStrLen(this.itemName) <= itemWidth) {
            g.drawString(this.itemName, itemX, itemY, 20);
         } else {
            wordNum = itemWidth / MainCanvas.CHARW - 1;
            g.drawString(this.itemName.substring(0, wordNum) + "...", itemX, itemY, 20);
         }
         break;
      case 8:
         scrollWidth = this.getSkillImgInd();
         g.setColor(Cons.COLOR_GRID_FRAME_BG[scrollWidth / 1000 - 1][0]);
         g.fillRect(itemX, itemY, MainCanvas.stuffMImg.frame_w, MainCanvas.stuffMImg.frame_h);
         MainCanvas.stuffMImg.draw(g, itemX, itemY, scrollWidth % 1000);
         g.setColor(Cons.COLOR_GRID_FRAME_BG[scrollWidth / 1000 - 1][1]);
         g.drawRect(itemX - 1, itemY - 1, MainCanvas.stuffMImg.frame_w + 1, MainCanvas.stuffMImg.frame_h + 1);
         if (isSelected && this.itemType == 8) {
            g.setColor(Cons.COLOR_PANEL_BG);
         } else {
            g.setColor(Cons.COLOR_FONT_1);
         }

         if (isSelected) {
            Util.drawRollStr(g, this.itemName, itemX + MainCanvas.stuffMImg.frame_w + 4, itemY, itemWidth - 6, (byte)0, (byte)3);
         } else if (6 + Util.getStrLen(this.itemName) <= itemWidth) {
            g.drawString(this.itemName, itemX + MainCanvas.stuffMImg.frame_w + 4, itemY, 20);
         } else {
            teamItemWidth = itemWidth / MainCanvas.CHARW - 1;
            g.drawString(this.itemName.substring(0, teamItemWidth) + "...", itemX + MainCanvas.stuffMImg.frame_w + 4, itemY, 20);
         }
         break;
      case 9:
         if (isSelected && this.itemType == 9) {
            g.setColor(this.getColo());
         } else {
            g.setColor(Cons.COLOR_FONT_1);
         }

         if (isSelected) {
            Util.drawRollStr(g, this.itemName, itemX, itemY, itemWidth - 6, (byte)0, (byte)3);
         } else if (6 + Util.getStrLen(this.itemName) <= itemWidth) {
            g.drawString(this.itemName, itemX, itemY, 20);
         } else {
            wordNum = itemWidth / MainCanvas.CHARW - 1;
            g.drawString(this.itemName.substring(0, wordNum) + "...", itemX, itemY, 20);
         }
      }

   }

   public static short getTeamItemHeight() {
      return (short)(44 * MainCanvas.screenH / 208);
   }

   public static short getOptionItemHeight() {
      return (short)(MainCanvas.CHARH + 9);
   }

   public static short getWarItemHeight() {
      return (short)(MainCanvas.CHARH + 4);
   }

   public static short getGemItemHeight() {
      return (short)(MainCanvas.CHARH + 6);
   }

   public byte getColorIndex() {
      return this.colorIndex;
   }

   public void setColorIndex(byte colorIndex) {
      this.colorIndex = colorIndex;
   }

   public byte getTaskType() {
      return this.taskType;
   }

   public void setTaskType(byte taskType) {
      this.taskType = taskType;
   }

   public void setTeamAttribute(String name, int id, boolean isLeader, byte profession, short level, int curHP, int maxHP, int curMP, int maxMP) {
      this.teammateName = name;
      this.teammateId = id;
      this.isLeader = isLeader;
      this.teammateProfession = profession;
      this.teammateLevel = level;
      this.curHP = curHP;
      this.maxHP = maxHP;
      this.curMP = curMP;
      this.maxMP = maxMP;
   }

   public void setAuctionAttribute(String name, long auctionID, short picID, byte num, byte quality, short level, byte maxNum, int maxPrice, int surePrice, byte leftTimeIndex) {
      this.itemName = name;
      this.auctionID = auctionID;
      this.stuffPicID = picID;
      this.stuffNum = num;
      this.stuffQuality = quality;
      this.colorIndex = (byte)(quality + 2);
      UIGrid.getStuffWordColor(this.stuffQuality);
      this.stuffLevel = level;
      this.stuffMaxNum = maxNum;
      this.stuffMaxPrice = maxPrice;
      this.stuffSurePrice = surePrice;
      switch(leftTimeIndex) {
      case 0:
         this.stuffLeftTimeStr = "Rất ngắn";
         break;
      case 1:
         this.stuffLeftTimeStr = "Ngắn";
         break;
      case 2:
         this.stuffLeftTimeStr = "Vừa";
         break;
      case 3:
         this.stuffLeftTimeStr = "Dài";
         break;
      case 4:
         this.stuffLeftTimeStr = "Rất dài";
      }

   }

   public void setWarAttribute(int num) {
      this.warNum = num;
   }

   public int getItemNameWidth() {
      return this.itemNameWidth;
   }

   public void setItemNameWidth(int itemNameWidth) {
      this.itemNameWidth = itemNameWidth;
   }

   public int getMenuIndex() {
      return this.m_nMenuIndex;
   }

   public void setMenuIndex(int m_nMenuIndex) {
      this.m_nMenuIndex = m_nMenuIndex;
   }

   public int getCharacterIndex() {
      return this.m_nCharacterIndex;
   }

   public void setCharacterIndex(int m_nCharacterIndex) {
      this.m_nCharacterIndex = m_nCharacterIndex;
   }

   public int getColo() {
      return this.m_nColo;
   }

   public void setColo(int m_nColo) {
      this.m_nColo = m_nColo;
   }
}

import javax.microedition.lcdui.Graphics;

public class UITitle extends UIComponent {
   public static final byte TYPE_TITLE_BAR = 0;
   public static final byte TYPE_MENU_BAR = 1;
   static final byte TITLE_OY = 3;
   static final byte TITLE_OX = 3;
   static final byte TITLE_OUT_OX = 4;
   private String titleText;
   private String[] menuText;
   public static String localChat = "";
   public static int chatTimer = 0;
   public static int chatDX = 0;
   public static final int CHAT_STEP = 3;
   public static boolean isEnter = false;
   public byte allTalentRows;
   public byte onePageTalentRows;
   public byte curTalentRow;
   public byte selCol;
   public byte selRow;
   public short moveStep;
   public short scrollPositionY;
   public short slipperEndY;
   public UIScroll titleScroll;
   public static byte formInd = 0;
   public byte gewgg;
   public static boolean isTalent_AddPoint = false;
   int regionInd;
   byte curTalentInd;
   static int msgW;
   static boolean isTwoScroll;
   static boolean isOneOnly;
   static int chatDX1;
   static int distance;
   static int strLen;
   static int msgX;
   static boolean isSplash;

   static {
      msgW = MainCanvas.screenW - 2 * (4 + 2 * MainCanvas.CHARW + 8);
      isOneOnly = true;
      distance = msgW / 2;
      strLen = MainCanvas.curFont.stringWidth(localChat);
      isSplash = false;
   }

   public UITitle(short x, short y, short w, short h, byte subType, UIForm form) {
      super(x, y, w, h, form);
      this.menuText = new String[2];
      this.allTalentRows = 0;
      this.onePageTalentRows = 0;
      this.curTalentRow = 0;
      this.selCol = 0;
      this.selRow = 0;
      this.moveStep = 0;
      this.scrollPositionY = 0;
      this.slipperEndY = 0;
      this.titleScroll = null;
      this.gewgg = 0;
      this.regionInd = Map.selRegionIND;
      super.type = subType;
      if (super.positionX <= 0) {
         super.positionX = this.getAutoTitleX();
      }

      if (super.positionY <= 0) {
         super.positionY = this.getAutoTitleY();
      }

      if (super.width <= 0) {
         super.width = this.getAutoTitleWidth();
      }

      if (super.height <= 0) {
         super.height = this.getAutoTitleHeight();
      }

      if (super.type == 1) {
         this.setMenuText(Cons.MENU_STR);
      }

      try {
         UIForm.encryptImg.toString();
      } catch (Exception var8) {
         MainCanvas.aMidlet.exitMIDlet();
      }

   }

   public UITitle(byte subType, UIForm form) {
      this((short)0, (short)0, (short)0, (short)0, subType, form);
   }

   public void draw(Graphics g) {
      short titleHeight = super.height;
      short kuangY = super.positionY;
      g.setColor(Cons.COLOR_TITLE_BORDER_1);
      g.drawRect(super.positionX, kuangY, super.width - 1, titleHeight - 1);
      g.setColor(Cons.COLOR_TITLE_BORDER_2);
      g.drawRect(super.positionX + 1, kuangY + 1, super.width - 3, titleHeight - 3);
      g.setColor(Cons.COLOR_TITLE_BG);
      g.fillRect(super.positionX + 2, kuangY, super.width - 4, titleHeight - 1);
      if (super.type == 0) {
         short startX = (short)(super.positionX + 1);
         short startRightX = (short)(super.positionX + super.width - 1);
         short startY = (short)(super.positionY + (super.height - MainCanvas.ui_4Img.getHeight()) / 2 - 1);
         short wenW = (short)MainCanvas.ui_4Img.getWidth();

         for(int i = 0; i < 2; ++i) {
            Util.drawImage(g, MainCanvas.ui_4Img, startX + i * wenW, startY, i == 1 ? 2 : 0);
            Util.drawImage(g, MainCanvas.ui_4Img, startRightX - (i + 1) * wenW, startY, i == 0 ? 2 : 0);
         }

         if (this.titleText != null) {
            Util.drawString(g, this.titleText, super.width, super.positionX, super.positionY + 2, Cons.COLOR_FONT_1);
         }
      } else if (super.type == 1) {
         g.setColor(7755053);
         g.drawLine(9, super.positionY, 9, super.positionY + super.height);
         g.drawLine(super.positionX + super.width - 9 - 1, super.positionY, super.positionX + super.width - 9 - 1, super.positionY + super.height);
         g.setColor(14728078);
         g.fillRect(1, super.positionY + 1, 8, super.height - 2);
         g.fillRect(super.positionX + super.width - 9, super.positionY + 1, 8, super.height - 2);
         g.setColor(16637613);
         g.fillRect(3, super.positionY + 5, 4, super.height - 8);
         g.fillRect(super.positionX + super.width - 7, super.positionY + 5, 4, super.height - 8);
         int startX;
         if (this.menuText != null) {
            g.setColor(Cons.COLOR_FONT_1);
            g.drawString(this.menuText[0], super.positionX + 4 + 7, super.positionY + 4, 20);
            g.setColor(Cons.COLOR_FONT_1);
            startX = Util.getStrLen(this.menuText[1]);
            g.drawString(this.menuText[1], super.positionX + super.width - 4 - startX - 7, super.positionY + 4, 20);
         }

         startX = super.positionX + 4 + 2 * MainCanvas.CHARW + 8;
         int startY = super.positionY + (super.height - MainCanvas.ui_4Img.getHeight()) / 2 + 1;
         msgX = startX;
         if (localChat.length() == 0) {
            int wenW = MainCanvas.ui_4Img.getWidth() * 2;
            int wenX = super.positionX + (MainCanvas.screenW - wenW) / 2;
            Util.drawImage(g, MainCanvas.ui_4Img, wenX, startY, 0);
            Util.drawImage(g, MainCanvas.ui_4Img, wenX + MainCanvas.ui_4Img.getWidth(), startY, 2);
         } else {
            g.setColor(Cons.COLOR_TITLE_BORDER_1);
            g.drawRect(startX, super.positionY, msgW - 1, super.height - 1);
            g.setColor(Cons.COLOR_TITLE_BORDER_2);
            g.drawRect(startX + 1, super.positionY + 1, msgW - 3, super.height - 3);
            g.setColor(Cons.COLOR_TITLE_MSG);
            g.fillRect(startX + 2, super.positionY + 2, msgW - 4, super.height - 4);
            drawChat(g, startX + 2, super.positionY + 4, msgW - 4, super.height - 4);
         }
      }

   }

   protected void keyInAction() {
      this.touchScreenAction();
      int[] tens;
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
            int ci;
            label238:
            switch(MainCanvas.curForm.clientCommand) {
            case 131079:
               int cmd = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               if (cmd == -1) {
                  super.isShowMenu = false;
               } else {
                  if (cmd == 131079) {
                     tens = new int[]{131080, 131081};
                     MainCanvas.ni.sendCommands(tens);
                     isEnter = true;
                  } else if (cmd == 131078) {
                     MainCanvas.ni.send(cmd);
                  }

                  super.isShowMenu = false;
               }
               break;
            case 393219:
               switch(this.getMenu().getIndex()) {
               case 0:
                  UILabel curExpLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(33);
                  UILabel baseExpLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(35);
                  if (curExpLabel.getLongNum() >= baseExpLabel.getLongNum()) {
                     MainCanvas.curTopForm = new UITopForm((byte)0, (UIForm)null);
                     MainCanvas.curTopForm.setTopFormInfo((byte)5, UITopForm.getRedColor(), "Lần tăng cấp này sẽ trừ$" + baseExpLabel.getLongNum() + "$" + " Kinh nghiệm，đống ý thăng cấp không?", "Đồng ý", "Trở về", 393228, -1);
                  } else {
                     UITopForm.createLocalTopForm((byte)0, (String)"Các hạ vẫn chưa đủ kinh nghiệm tăng cấp!", "Xác nhận", "Trở về", -1, -1);
                  }

                  super.isShowMenu = false;
                  break label238;
               case 1:
                  MainCanvas.ni.send(393217);
                  super.isShowMenu = false;
               default:
                  break label238;
               }
            case 524292:
               int cmd_Genius = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               if (cmd_Genius == -1) {
                  super.isShowMenu = false;
                  break;
               }

               if (cmd_Genius == 524289) {
                  intoGeniusDetailUI(formInd);
               } else if (cmd_Genius == 1572869) {
                  isTalent_AddPoint = false;
                  MainCanvas.ni.send(cmd_Genius);
               } else if (cmd_Genius == 524291 && isTalent_AddPoint) {
                  isTalent_AddPoint = false;

                  int cj;
                  for(ci = 0; ci < Player.getInstance().talentTypeCount; ++ci) {
                     for(cj = 0; cj < Player.getInstance().talentCurPoint[ci].length; ++cj) {
                        Player var10000 = Player.getInstance();
                        var10000.remaindTalentPoint += Player.getInstance().talentAddPoint[ci][cj];
                        byte[] var30 = Player.getInstance().talentCurPoint[ci];
                        var30[cj] -= Player.getInstance().talentAddPoint[ci][cj];
                        short[] var31 = Player.getInstance().usedPoint;
                        var31[ci] = (short)(var31[ci] - Player.getInstance().talentAddPoint[ci][cj]);
                        Player.getInstance().talentAddPoint[ci][cj] = 0;
                     }
                  }

                  for(ci = 0; ci < Player.getInstance().talentTypeCount; ++ci) {
                     for(cj = 0; cj < Player.getInstance().talentCurPoint[ci].length; ++cj) {
                        Player.getInstance().refreshTalentOpen(ci, cj);
                     }
                  }

                  for(byte formi = 0; formi < Player.getInstance().talentTypeCount; ++formi) {
                     UIForm tformText = (UIForm)MainCanvas.curFormVector.elementAt(formi);
                     UILabel geniusLabel = (UILabel)tformText.getComponents().elementAt(8);
                     geniusLabel.setText(String.valueOf(Player.getInstance().remaindTalentPoint));
                  }
               }

               super.isShowMenu = false;
               break;
            case 2490386:
               ci = (Integer)this.getMenu().getCmdIds().elementAt(this.getMenu().getIndex());
               UITable.selectedPlayerId = PCMentor.masterId;
               if (ci == -268435452) {
                  UIChat.intoPrivateChannel(PCMentor.masterId, PCMentor.masterName, true);
               } else {
                  if (ci == 720897) {
                     SITeam.otherPlayerID = PCMentor.masterId;
                  }

                  MainCanvas.ni.send(ci);
               }

               super.isShowMenu = false;
            }

            this.getMenu().saveForm();
         }
      } else {
         short rwm3;
         if (MainCanvas.isInputDownOrHold(4096)) {
            switch(MainCanvas.curForm.clientCommand) {
            case 131078:
            case 131079:
               rwm3 = Map.getRegionINDFromRegionID(Map.regionNeighbor[Map.selRegionIND][0]);
               if (rwm3 != -1) {
                  Map.selRegionIND = rwm3;
                  this.setTitleText(Map.regionName[Map.selRegionIND]);
               }
               break;
            case 524292:
               Player.getInstance().selTalentTree(0);
               --this.selRow;
               if (this.selRow < 0) {
                  this.selRow = 0;
                  this.gewgg = this.curTalentRow;
               }
            }
         } else if (MainCanvas.isInputDownOrHold(8192)) {
            switch(MainCanvas.curForm.clientCommand) {
            case 131078:
            case 131079:
               rwm3 = Map.getRegionINDFromRegionID(Map.regionNeighbor[Map.selRegionIND][1]);
               if (rwm3 != -1) {
                  Map.selRegionIND = rwm3;
                  this.setTitleText(Map.regionName[Map.selRegionIND]);
               }
               break;
            case 524292:
               Player.getInstance().selTalentTree(1);
               ++this.selRow;
               if (this.selRow >= this.onePageTalentRows) {
                  this.selRow = (byte)(this.onePageTalentRows - 1);
                  this.gewgg = (byte)(this.curTalentRow - (this.onePageTalentRows - 1));
               }

               if (this.curTalentRow >= this.onePageTalentRows) {
                  this.scrollPositionY += this.moveStep;
               }

               if (this.scrollPositionY > this.slipperEndY) {
                  this.scrollPositionY = this.slipperEndY;
               }
            }
         } else if (MainCanvas.isInputDownOrHold(16384)) {
            switch(MainCanvas.curForm.clientCommand) {
            case 131078:
            case 131079:
               rwm3 = Map.getRegionINDFromRegionID(Map.regionNeighbor[Map.selRegionIND][2]);
               if (rwm3 != -1) {
                  Map.selRegionIND = rwm3;
                  this.setTitleText(Map.regionName[Map.selRegionIND]);
               }
               break;
            case 524292:
               if (this.selCol < 0) {
                  this.selCol = 0;
               } else {
                  Player.getInstance().selTalentTree(2);
               }
            }
         } else if (MainCanvas.isInputDownOrHold(32768)) {
            switch(MainCanvas.curForm.clientCommand) {
            case 131078:
            case 131079:
               rwm3 = Map.getRegionINDFromRegionID(Map.regionNeighbor[Map.selRegionIND][3]);
               if (rwm3 != -1) {
                  Map.selRegionIND = rwm3;
                  this.setTitleText(Map.regionName[Map.selRegionIND]);
               }
               break;
            case 524292:
               if (this.selCol >= 3) {
                  this.selCol = 2;
               } else {
                  Player.getInstance().selTalentTree(3);
               }
            }
         }

         if (MainCanvas.isInputDown(65536) || MainCanvas.isInputDown(131072)) {
            switch(MainCanvas.curForm.clientCommand) {
            case 131078:
               MainCanvas.isWaiting = true;
               Map.w_RegionID = Map.regionID[Map.selRegionIND];
               MainCanvas.ni.send(131079);
               break;
            case 131079:
               UIMenu menuwm = new UIMenu((byte)0);
               String[] cmdStrs = new String[]{"Bản đồ chi tiết", "Thế giới"};
               int[] cmds = new int[]{131079, 131078};
               int[] inns = new int[]{1, 1};
               menuwm.addMenu(cmdStrs, cmds, inns);
               this.setMenu(menuwm);
               super.isShowMenu = true;
               this.getMenu().resetIndex();
               break;
            case 393219:
               UIMenu menu = new UIMenu((byte)0);
               tens = new int[]{393228, 393217};
               menu.addMenu(Cons.MENU_DESIGNATION, tens, Cons.MENU_DESIGNATION_INNS);
               this.setMenu(menu);
               super.isShowMenu = true;
               this.getMenu().resetIndex();
               break;
            case 524292:
               UIForm tform;
               if (MainCanvas.isInputDown(131072)) {
                  tform = (UIForm)MainCanvas.curFormVector.elementAt(0);
                  formInd = tform.foldedIndex;
                  UIMenu menugenius = new UIMenu((byte)0);
                  String[] cmdStrs_GENIUS = new String[]{"Chi tiết thiên phú", "Xác nhận tăng cấp", "Khôi phục thao tác"};
                  int[] cmds_GENIUS = new int[]{524289, 1572869, 524291};
                  int[] inns_GENIUS = new int[]{2, 3, 3};
                  menugenius.addMenu(cmdStrs_GENIUS, cmds_GENIUS, inns_GENIUS);
                  this.setMenu(menugenius);
                  super.isShowMenu = true;
                  this.getMenu().resetIndex();
               } else if (MainCanvas.isInputDown(65536)) {
                  tform = (UIForm)MainCanvas.curFormVector.elementAt(0);
                  byte cwi = tform.foldedIndex;
                  int tid = Player.getInstance().getIND_FromTalentData(cwi, Player.getInstance().selTalent[cwi]);
                  if (Player.getInstance().talentCurPoint[cwi][tid] < Player.getInstance().talentMaxPoint[cwi][tid] && Player.getInstance().remaindTalentPoint != 0 && Player.getInstance().talentIsOpen[cwi][tid]) {
                     isTalent_AddPoint = true;
                     ++Player.getInstance().talentCurPoint[cwi][tid];
                     ++Player.getInstance().talentAddPoint[cwi][tid];
                     ++Player.getInstance().usedPoint[cwi];
                     --Player.getInstance().remaindTalentPoint;

                     for(int ci = 0; ci < Player.getInstance().talentTypeCount; ++ci) {
                        for(int cj = 0; cj < Player.getInstance().talentCurPoint[ci].length; ++cj) {
                           Player.getInstance().refreshTalentOpen(ci, cj);
                        }
                     }
                  }

                  for(byte formi = 0; formi < Player.getInstance().talentTypeCount; ++formi) {
                     UIForm tformText = (UIForm)MainCanvas.curFormVector.elementAt(formi);
                     UILabel geniusLabel = (UILabel)tformText.getComponents().elementAt(8);
                     geniusLabel.setText(String.valueOf(Player.getInstance().remaindTalentPoint));
                  }
               }
               break;
            case 2490386:
               UIMenu masterMenu = new UIMenu((byte)0);
               masterMenu.addMenu("Thêm bạn bè", 589835, 3);
               if (PCMentor.masterOL) {
                  masterMenu.addMenu("Chat　riêng", -268435452, 2);
               }

               if (PCMentor.masterOL && SITeam.teamState == 0) {
                  masterMenu.addMenu("Mời tổ đội", 720897, 3);
               }

               this.setMenu(masterMenu);
               super.isShowMenu = true;
               this.getMenu().resetIndex();
            }
         }
      }

   }

   private final void touchScreenAction() {
      if (MainCanvas.SUPPORT_POINTER && MainCanvas.isInputDown(268435456)) {
         switch(MainCanvas.curForm.clientCommand) {
         case 131078:
         case 131079:
            int x = 0;
            int y = getMenuBarHeight();
            int w = MainCanvas.screenW;
            int h = MainCanvas.screenH - 2 * getMenuBarHeight();
            int oldRegionInd = this.regionInd;
            if (MainCanvas.isPointInRect(x, y, w, h) && this.isInRegion()) {
               if (oldRegionInd == this.regionInd) {
                  MainCanvas.keySimPressed(131072);
               }

               short rwm = Map.getRegionINDFromRegionID(Map.regionID[this.regionInd]);
               if (rwm != -1) {
                  Map.selRegionIND = rwm;
                  this.setTitleText(Map.regionName[Map.selRegionIND]);
               }
            }
            break;
         case 524292:
            Player pla = Player.getInstance();
            int x1 = pla.areaPosition_X;
            int y1 = pla.areaPosition_Y;
            int w1 = pla.areaPosition_W;
            int h1 = pla.areaPosition_H;
            int oldTalentInd = this.curTalentInd;
            if (MainCanvas.isPointInRect(x1, y1, w1, h1) && this.isInRegion()) {
               if (oldTalentInd == this.curTalentInd) {
                  MainCanvas.keySimPressed(65536);
               }

               UIForm tform = (UIForm)MainCanvas.curFormVector.elementAt(0);
               byte idw = tform.foldedIndex;
               pla.selTalent[idw] = pla.talentPosition[idw][this.curTalentInd];
               MainCanvas.curForm.addWarningStr(pla.talentCon[idw][pla.getIND_FromTalentData(idw, pla.selTalent[idw])]);
               UILabel geniusNameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(6);
               geniusNameLabel.setText(pla.talentName[idw][pla.getIND_FromTalentData(idw, pla.selTalent[idw])]);
            }

            int scrollX = pla.areaPosition_X + pla.areaPosition_W - MainCanvas.ui_3Img.getWidth() - 5;
            int scrollY = pla.areaPosition_Y + 3;
            int scrollW = MainCanvas.ui_3Img.getWidth();
            int scrollH = MainCanvas.ui_3Img.getHeight();
            if (MainCanvas.isPointInRect(scrollX, scrollY, scrollW, scrollH)) {
               MainCanvas.keySimPressed(4096);
            }

            int scrollY1 = scrollY + this.titleScroll.getHeight() - MainCanvas.ui_3Img.getHeight();
            if (MainCanvas.isPointInRect(scrollX, scrollY1, scrollW, scrollH)) {
               MainCanvas.keySimPressed(8192);
            }
         }
      }

   }

   private boolean isInRegion() {
      boolean isInRect = false;
      int w;
      switch(MainCanvas.curForm.clientCommand) {
      case 131078:
      case 131079:
         for(int rc = 0; rc < Map.regionCount; ++rc) {
            int x = Map.regionX[rc] - Map.worldMapSX;
            int y = Map.regionY[rc] - Map.worldMapSY;
            w = Map.imagePlayerRegion.getWidth();
            int h = Map.imagePlayerRegion.getHeight();
            if (MainCanvas.pointerX >= x && MainCanvas.pointerX <= x + w && MainCanvas.pointerY >= y && MainCanvas.pointerY <= y + h) {
               isInRect = true;
               this.regionInd = rc;
               return isInRect;
            }
         }

         return isInRect;
      case 524292:
         UIForm tform = (UIForm)MainCanvas.curFormVector.elementAt(0);
         int idw = tform.foldedIndex;
         Player pl = Player.getInstance();
         w = pl.talentPosition[idw].length;
         short bx = (short)((MainCanvas.screenW >> 1) - 27 - (MainCanvas.stuffMImg.frame_w >> 1));

         for(int i = 0; i < w; ++i) {
            int tx = (short)(bx + (pl.talentPosition[idw][i] - 1) % 3 * 27);
            int ty = (short)(8 + pl.areaPosition_Y + ((pl.talentPosition[idw][i] - 1) / 3 - this.gewgg) * 27);
            if (MainCanvas.pointerX >= tx && MainCanvas.pointerX <= tx + 16 && MainCanvas.pointerY >= ty && MainCanvas.pointerY <= ty + 16) {
               isInRect = true;
               this.curTalentInd = (byte)i;
               this.selRow = (byte)((ty - 8 - pl.areaPosition_Y) / 25);
               if (this.curTalentRow >= this.onePageTalentRows - 1) {
                  this.curTalentRow = (byte)(this.curTalentRow + 1 - this.onePageTalentRows + this.selRow);
               } else {
                  this.curTalentRow = this.selRow;
               }
               break;
            }
         }
      }

      return isInRect;
   }

   public static void localChatTick() {
      if (localChat.length() != 0) {
         if (isOneOnly) {
            chatDX -= 3;
            if (chatDX < -(strLen + msgW - 4)) {
               isOneOnly = false;
               chatDX = 0;
            }
         }

         if (isTwoScroll) {
            chatDX1 -= 3;
            if (chatDX1 < -(strLen + msgW - 4)) {
               isTwoScroll = false;
               chatDX1 = 0;
            }
         }

      }
   }

   public static void drawChat(Graphics g, int x, int y, int width, int height) {
      strLen = MainCanvas.curFont.stringWidth(localChat);
      g.setClip(x, y, width, height);
      g.setColor(SIChat.CHAT_CHANNAL_COLOR[7]);
      g.setFont(MainCanvas.curFont);
      int dx2;
      if (!isSplash) {
         if (isOneOnly) {
            dx2 = x + width + chatDX;
            if (dx2 + strLen + distance < msgX + msgW) {
               isTwoScroll = true;
            }

            SIChat.drawChatString(g, localChat, SIChat.CHAT_CHANNAL_COLOR[7], dx2, y + 1, (int[][])null, (int[])null, (int[])null);
         }

         if (isTwoScroll) {
            dx2 = x + width + chatDX1;
            if (dx2 + strLen + distance < msgX + msgW) {
               isOneOnly = true;
            }

            SIChat.drawChatString(g, localChat, SIChat.CHAT_CHANNAL_COLOR[7], dx2, y + 1, (int[][])null, (int[])null, (int[])null);
         }
      } else {
         g.setColor(16711680);
         if (MainCanvas.countTick % 4 < 2) {
            dx2 = x + (width - Util.getStrLen(localChat)) / 2;
            SIChat.drawChatString(g, localChat, SIChat.CHAT_CHANNAL_COLOR[7], dx2, y, (int[][])null, (int[])null, (int[])null);
         }
      }

      g.setClip(0, 0, MainCanvas.screenW, MainCanvas.screenH);
   }

   public String getTitleText() {
      return this.titleText;
   }

   public void setTitleText(String titleText) {
      this.titleText = titleText;
   }

   public String[] getMenuText() {
      return this.menuText;
   }

   public void setMenuText(String[] menuText) {
      if (menuText != null && menuText.length == this.menuText.length) {
         System.arraycopy(menuText, 0, this.menuText, 0, menuText.length);
      }

   }

   public void setMenuText(String menuText, int index) {
      if (menuText != null && index >= 0 && index < 2) {
         this.menuText[index] = menuText;
      }

   }

   public short getAutoTitleWidth() {
      short autoWidth = (short)MainCanvas.screenW;
      return autoWidth;
   }

   public short getAutoTitleHeight() {
      short autoHeight = (short)(MainCanvas.CHARH + 4);
      return autoHeight;
   }

   public short getAutoTitleX() {
      short autoX = 0;
      return autoX;
   }

   public short getAutoTitleY() {
      short autoY = 0;
      if (super.type == 1) {
         autoY += (short)(MainCanvas.screenH - this.getAutoTitleHeight());
      }

      return autoY;
   }

   public static int getMenuBarHeight() {
      return MainCanvas.CHARH + 4;
   }

   public static int getTitleBarHeight() {
      return MainCanvas.CHARH + 4 + 3;
   }

   public static final void intoGeniusDetailUI(byte find) {
      MainCanvas.curForm = ParseUI.parseUI("/ui/skilldetail.ui");
      UITitle geniusDetailTitle = (UITitle)MainCanvas.curForm.getComponents().elementAt(0);
      geniusDetailTitle.setTitleText("Chi tiết thiên phú");
      UILabel geniusDetailLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(3);
      geniusDetailLabel.setType((byte)5);
      int tind = Player.getInstance().getIND_FromTalentData(find, Player.getInstance().selTalent[find]);
      String tn = Player.getInstance().talentName[find][tind];
      geniusDetailLabel.setText(tn);
      UITextArea skillDetailTextarea = (UITextArea)MainCanvas.curForm.getComponents().elementAt(4);
      String skilldescription = Player.getInstance().talentCon[find][tind];
      skillDetailTextarea.setContent(skilldescription);
      skillDetailTextarea.addUIScroll();
      UIPicture skillDetailP = (UIPicture)MainCanvas.curForm.getComponents().elementAt(5);
      short goodsID = Player.getInstance().talentImgInd[find][tind];
      skillDetailP.quality = (byte)(goodsID / 1000 - 1);
      skillDetailP.isWpPic = true;
      short picID = (short)(goodsID % 1000);
      skillDetailP.wpIndex = picID;
      skillDetailP.setImg(MainCanvas.stuffMImg);
   }
}

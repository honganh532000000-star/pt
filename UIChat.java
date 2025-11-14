import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

public class UIChat {
   public static UIChat chatForm = null;
   private UITitle topTitle = null;
   public UITitle bottomMenu = null;
   private UIScroll scroll = null;
   private UIMenu menu = null;
   private String[] showChatString = null;
   private int[] showChatColor = null;
   private byte[] showChatRow = null;
   public String[] curChat = null;
   public byte showChannel;
   public int[][][] showGoodsString = null;
   public static final int MENU_ID_REVERT = -268435456;
   public static final int MENU_ID_INPUT = -268435455;
   public static final int MENU_ID_WORLD = -268435454;
   public static final int MENU_ID_CLAN = -268435453;
   public static final int MENU_ID_SCENE = -268435452;
   public static final int MENU_ID_TEAM = -268435451;
   public static final int MENU_ID_HORN = -268435450;
   public static final int MENU_ID_NAME_START = -16777216;
   public static final byte SHOW_CHANNEL_ALL = 100;
   public static final byte SHOW_CHANNEL_PRIVATE = 0;
   public static final byte SHOW_CHANNEL_WORLD = 1;
   public static final byte SHOW_CHANNEL_CLAN = 2;
   public static final byte SHOW_CHANNEL_SCENE = 3;
   public static final byte SHOW_CHANNEL_TEAM = 4;
   public static final byte SHOW_CHANNEL_SERVE = 5;
   public static final byte SHOW_CHANNEL_HORN = 6;
   public static final byte SHOW_CHANNEL_NUM = 5;
   public static String[] allChat = new String[0];
   public static String[] allSenderName = new String[0];
   public static int[] allSenderId = new int[0];
   public static byte[] allChatChannel = new byte[0];
   public static Object[] allGoodsContents = new Object[0];
   public static Object[] allNameContent = new Object[0];
   public static String[] hornChat = new String[0];
   public static String[] hornSenderName = new String[0];
   public static int[] hornSenderId = new int[0];
   public static byte[] hornChatChannel = new byte[0];
   public static Object[] hornGoodsContents = new Object[0];
   public static Object[] hornNameContent = new Object[0];
   public static String[][] channelChat = new String[5][0];
   public static String[][] channelSenderName = new String[5][0];
   public static int[][] channelSenderId = new int[5][0];
   public static Object[][] channelGoodsContents = new Object[5][0];
   public static Object[][] channelNameContent = new Object[5][0];
   public static String[] allChatInSer = new String[0];
   public static String[] allSenderNameInSer = new String[0];
   public static int[] allSenderIdInSer = new int[0];
   public static byte[] allChatChannelInSer = new byte[0];
   public static Object[] allGoodsContentsInSer = new Object[0];
   public static Object[] allNameContentInSer = new Object[0];
   public static String[] lastSenderName = new String[0];
   public static int[] lastSenderId = new int[0];
   public static int[] copySenderId = new int[0];
   public static final int LAST_SENDER_MAX = 4;
   public static final byte CHAT_INPUT_MAX = 30;
   public static final byte ALL_CHAT_LENGTH = 20;
   public static final byte CHANNEL_CHAT_LENGTH = 10;
   public static final String STR_CHANNEL_ALL = "Kênh tổng hợp";
   public static final String STR_CHANNEL_HORN = "Kênh loa";
   public static final String[] STR_CHANNEL_CLAN = new String[]{"Kênh thị tộc", "Kênh chư hầu"};
   public static String[] STR_CHANNEL_TITLE = new String[]{"Kênh riêng", "Kênh Thế giới", "", "Kênh Bản đồ", "Kênh Đội ngũ", "Hệ thống kênh chat"};
   private short chatOffX;
   private short chatOffY;
   private short chatWidth;
   private short chatheight;
   private byte chatRow;
   private short textOffY;
   private short textheight;
   private byte curChatFirst;
   private byte curChatStart;
   private byte curChatEnd;
   public byte curSelectChat = 0;
   public byte curSelectGoods = -1;
   public byte curSelectName = -1;
   public int[] curSelectGoodsStr = null;
   public int[] curSelectNameStr = null;
   private short firstStringDy;
   private short curSelectRow;
   private static final byte SCROLL_STATE_UP = 0;
   private static final byte SCROLL_STATE_DOWN = 1;
   private byte scrollState = 0;
   public static final byte CONTROL_STATE_NONE = 0;
   public static final byte CONTROL_STATE_TEXT = 1;
   public static final byte CONTROL_STATE_CHAT = 2;
   public static final byte CONTROL_STATE_GOODS = 3;
   public static final byte CONTROL_STATE_NAME = 4;
   public byte controlState = 0;
   public static byte chatNetSendChannel = 4;
   public static String chatSendString = "";
   public static Vector[] chatSendGoods = new Vector[0];
   public static int chatRecieverId = -1;
   public static String chatRecieverName = null;
   public static int goodsOwnerId = -1;
   public static long goodsId = -1L;
   public static byte goodsAttach = 0;
   int[][][] startChatIndex;
   int[][][] endChatIndex;
   int[][] startName;
   int smallIndex = 100;
   int[][] selectG;
   int[][] selectN;
   boolean isInRegion;
   static boolean playerInfoInChat = false;
   String textStr = "";
   public static final byte ATTACH_TYPE_NONE = 0;
   public static final byte ATTACH_TYPE_PACK = 1;
   public static final byte ATTACH_TYPE_STORE = 2;
   public static final byte ATTACH_TYPE_EQUIP = 3;
   public static final byte ATTACH_TYPE_TASK = 20;
   static final byte[][] faceSwing = new byte[][]{{0, 0, 1, 1, 1, 1}, {0, 0, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 1, 1}, {0, 0, 1, 1}, {0, 1}, {0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 1, 1, 1}, {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 1, 1}};

   public UIChat(byte showChannel) {
      MainCanvas.curFormVector.addElement(ParseUI.parseUI("/ui/chatface.ui"));
      MainCanvas.curFormVector.addElement((Object)null);
      MainCanvas.curFormVector.addElement((Object)null);
      MainCanvas.curForm = (UIForm)MainCanvas.curFormVector.elementAt(0);
      UIMenu.formSaveForm();
      if (Player.getInstance() != null) {
         Player.getInstance().setAttacked(false);
      }

      SIChat.addChatLocal("");
      this.curSelectChat = 0;
      this.curSelectGoods = -1;
      this.curSelectName = -1;
      this.showChannel = showChannel;
      this.topTitle = new UITitle((byte)0, (UIForm)null);
      if (showChannel == 100) {
         this.curChat = allChat;
         this.topTitle.setTitleText("Kênh tổng hợp");
      } else if (showChannel == 5) {
         this.curChat = allChatInSer;
         this.topTitle.setTitleText(STR_CHANNEL_TITLE[showChannel]);
      } else if (showChannel == 6) {
         this.curChat = hornChat;
         this.topTitle.setTitleText("Kênh loa");
      } else {
         this.curChat = channelChat[showChannel];
         this.topTitle.setTitleText(STR_CHANNEL_TITLE[showChannel]);
      }

      this.bottomMenu = new UITitle((byte)1, (UIForm)null);
      this.textOffY = this.topTitle.height;
      if ((showChannel < 1 || showChannel > 4) && showChannel != 6) {
         if (this.curChat.length > 0) {
            this.controlState = 2;
         } else {
            this.controlState = 0;
         }

         if (showChannel == 5) {
            this.bottomMenu.setMenuText("", 0);
         } else {
            this.bottomMenu.setMenuText("Thao tác", 0);
         }

         this.textheight = 0;
      } else {
         this.controlState = 1;
         this.bottomMenu.setMenuText("Nhập vào", 0);
         this.textheight = (short)(MainCanvas.CHARH << 1);
      }

      this.chatOffY = (short)(this.textOffY + this.textheight);
      this.chatheight = (short)(MainCanvas.screenH - this.textOffY - this.textheight - this.bottomMenu.height);
      this.scroll = new UIScroll((short)0, this.chatOffY, (short)0, this.chatheight, (byte)0, true, (UIForm)null);
      this.scroll.positionX = (short)(MainCanvas.screenW - this.scroll.width);
      this.chatOffX = this.scroll.width;
      this.chatWidth = (short)(MainCanvas.screenW - (this.scroll.width << 1));
      this.chatRow = (byte)(this.chatheight / MainCanvas.CHARH);
      this.showChatString = new String[0];
      this.showGoodsString = new int[0][][];
      this.showChatRow = new byte[0];
      this.showChatColor = new int[0];
      this.refreshChannelString();
   }

   public void draw(Graphics g) {
      this.drawText(g);
      this.drawChat(g);
      g.setColor(Cons.COLOR_BACKGROUND);
      g.fillRect(0, 0, MainCanvas.screenW, this.topTitle.height);
      this.topTitle.draw(g);
      this.bottomMenu.draw(g);
      if (this.showChatString.length > this.chatRow) {
         this.scroll.draw(g);
      }

      if (this.menu != null) {
         this.menu.draw(g);
      }

   }

   private void drawText(Graphics g) {
      if (this.showChannel >= 1 && this.showChannel <= 4) {
         g.setColor(2167563);
         g.fillRect(0, this.textOffY, MainCanvas.screenW, this.textheight);
         g.setColor(this.controlState == 1 ? 16776960 : SIChat.CHAT_CHANNAL_COLOR[this.showChannel + 1]);
         g.drawString("Gửi đến" + STR_CHANNEL_TITLE[this.showChannel] + ":", this.chatOffX, this.textOffY, 20);
         g.drawRect(this.chatOffX, this.textOffY + MainCanvas.CHARH + 1, this.chatWidth - 1, MainCanvas.CHARH - 5);
      } else {
         if (this.showChannel == 6) {
            g.setColor(2167563);
            g.fillRect(0, this.textOffY, MainCanvas.screenW, this.textheight);
            g.setColor(this.controlState == 1 ? 16776960 : SIChat.CHAT_CHANNAL_COLOR[11]);
            g.drawString("Gửi đếnKênh loa:", this.chatOffX, this.textOffY, 20);
            g.drawRect(this.chatOffX, this.textOffY + MainCanvas.CHARH + 1, this.chatWidth - 1, MainCanvas.CHARH - 5);
         }

      }
   }

   private void drawChat(Graphics g) {
      g.setColor(2167563);
      g.fillRect(0, this.chatOffY, MainCanvas.screenW, this.chatheight);
      this.drawChatStrings(g, this.firstStringDy);
   }

   public final void refreshChannelString() {
      if (this.curChat.length != 0) {
         if (this.controlState == 0) {
            this.controlState = 2;
         }

         this.curChatFirst = 0;
         this.curChatStart = 0;
         this.curChatEnd = 0;
         this.firstStringDy = 0;
         this.showChatString = new String[0];
         this.showChatRow = new byte[0];
         this.showChatColor = new int[0];
         this.showGoodsString = new int[0][][];
         this.startChatIndex = new int[this.curChat.length][][];
         this.endChatIndex = new int[this.curChat.length][][];
         this.startName = new int[this.curChat.length][];
         if (this.curChat.length > 0) {
            boolean enough;
            byte i;
            if (this.scrollState == 0) {
               this.smallIndex = 100;
               enough = false;
               this.curChatStart = this.curSelectChat;
               this.curChatFirst = this.curSelectChat;

               for(i = this.curSelectChat; i < this.curChat.length; this.curChatEnd = i++) {
                  this.findString(i, true, this.findColor(i));
                  if (this.showChatString.length > this.chatRow) {
                     enough = true;
                     break;
                  }
               }

               this.firstStringDy = 0;
               if (!enough) {
                  enough = false;

                  for(i = (byte)(this.curSelectChat - 1); i >= 0; this.curChatStart = i--) {
                     this.findString(i, false, this.findColor(i));
                     this.curChatFirst = i;
                     if (this.showChatString.length > this.chatRow) {
                        enough = true;
                        break;
                     }
                  }

                  if (enough) {
                     this.firstStringDy = (short)(this.chatheight - this.showChatString.length * MainCanvas.CHARH);
                  } else {
                     this.firstStringDy = 0;
                  }
               }
            } else if (this.scrollState == 1) {
               this.smallIndex = 100;
               enough = false;
               this.curChatEnd = this.curSelectChat;

               for(i = this.curSelectChat; i >= 0; this.curChatStart = i--) {
                  this.findString(i, false, this.findColor(i));
                  this.curChatFirst = i;
                  if (this.showChatString.length > this.chatRow) {
                     enough = true;
                     break;
                  }
               }

               this.firstStringDy = (short)(this.chatheight - this.showChatString.length * MainCanvas.CHARH);
               if (!enough) {
                  for(i = (byte)(this.curSelectChat + 1); i < this.curChat.length; this.curChatEnd = i++) {
                     this.findString(i, true, this.findColor(i));
                     if (this.showChatString.length > this.chatRow) {
                        break;
                     }
                  }

                  this.firstStringDy = 0;
               }
            }
         }

         this.refreshSelectRow();
         this.refreshScroll();
         this.goodsInfo();
      }
   }

   private final int findColor(int index) {
      int color = false;
      int color;
      if (this.showChannel == 100) {
         color = SIChat.CHAT_CHANNAL_COLOR[allChatChannel[index]];
      } else if (this.showChannel == 5) {
         color = SIChat.CHAT_CHANNAL_COLOR[allChatChannelInSer[index]];
      } else if (this.showChannel == 6) {
         color = SIChat.CHAT_CHANNAL_COLOR[hornChatChannel[index]];
      } else {
         color = SIChat.CHAT_CHANNAL_COLOR[this.showChannel + 1];
      }

      return color;
   }

   private final void refreshSelectRow() {
      this.curSelectRow = 0;

      for(int i = 0; i < this.curSelectChat - this.curChatFirst; ++i) {
         this.curSelectRow = (short)(this.curSelectRow + this.showChatRow[i]);
      }

   }

   public void refreshScroll() {
      this.scroll.scrollPositionY((short)this.curChat.length, (short)(this.showChatRow.length - 1), this.curChatStart);
   }

   private final void findString(int index, boolean end, int color) {
      int[][] startGoodsInfo = new int[0][];
      int[][] endGoodsInfo = new int[0][];
      int[] nameInfo = new int[2];
      if (this.smallIndex > index) {
         this.smallIndex = index;
      }

      String[] tempStr = Util.wrapText(this.curChat[index], this.chatWidth, MainCanvas.curFont);
      int m = 0;

      int i;
      for(i = tempStr.length; m < i; ++m) {
         int ind = true;
         int len2 = tempStr[m].length();
         int ind;
         if (i > 1 && m != i - 1 && (ind = tempStr[m].lastIndexOf(35)) == len2 - 1) {
            tempStr[m] = tempStr[m].substring(0, ind);
            tempStr[m + 1] = "#" + tempStr[m + 1];
         }
      }

      int[] colors = new int[tempStr.length];

      for(i = 0; i < colors.length; ++i) {
         colors[i] = color;
      }

      this.showChatString = Util.addArrays(this.showChatString, end ? this.showChatString.length : 0, tempStr);
      this.showChatRow = Util.addArray(this.showChatRow, end ? this.showChatRow.length : 0, (byte)tempStr.length);
      this.showChatColor = Util.addArrays(this.showChatColor, end ? this.showChatColor.length : 0, colors);
      Vector[] goodsContent = (Vector[])null;
      Vector nameContent = null;
      if (this.showChannel == 100) {
         goodsContent = (Vector[])allGoodsContents[index];
         nameContent = (Vector)allNameContent[index];
      } else if (this.showChannel == 5) {
         goodsContent = (Vector[])allGoodsContentsInSer[index];
         nameContent = (Vector)allNameContentInSer[index];
      } else if (this.showChannel == 6) {
         goodsContent = (Vector[])hornGoodsContents[index];
         nameContent = (Vector)hornNameContent[index];
      } else {
         goodsContent = (Vector[])channelGoodsContents[this.showChannel][index];
         nameContent = (Vector)channelNameContent[this.showChannel][index];
      }

      if (nameContent != null) {
         nameInfo[0] = Integer.parseInt(nameContent.elementAt(0).toString());
         nameInfo[1] = Integer.parseInt(nameContent.elementAt(1).toString());
      } else {
         nameInfo = (int[])null;
      }

      int[][][] goodsStrings = new int[tempStr.length][][];
      if (goodsContent != null) {
         int i = 0;
         int[][] goodsStr = new int[0][];

         for(int j = 0; j < goodsContent.length; ++j) {
            int[] goodsDraw = (int[])goodsContent[j].elementAt(5);
            int startLine = goodsDraw[0];
            int endLine = goodsDraw[1];
            boolean newLine = false;

            int startWord;
            for(startWord = 0; startWord < i; ++startWord) {
               startLine -= tempStr[startWord].length();
               endLine -= tempStr[startWord].length();
            }

            while(true) {
               int startWord = true;
               int endWord = true;
               int endWord;
               if (startLine < 0) {
                  if (endLine < 0) {
                     startWord = true;
                     endWord = true;
                     break;
                  }

                  if (endLine < tempStr[i].length()) {
                     startWord = 0;
                     endWord = endLine;
                  } else {
                     startWord = 0;
                     endWord = tempStr[i].length() - 1;
                     newLine = true;
                  }
               } else if (startLine < tempStr[i].length()) {
                  if (endLine < 0) {
                     startWord = -1;
                     endWord = -1;
                  } else if (endLine < tempStr[i].length()) {
                     startWord = startLine;
                     endWord = endLine;
                  } else {
                     startWord = startLine;
                     endWord = tempStr[i].length() - 1;
                     newLine = true;
                  }
               } else {
                  startWord = -1;
                  endWord = -1;
                  newLine = true;
               }

               if (startWord >= 0 && endWord >= 0) {
                  String str = tempStr[i];
                  if (str.charAt(startWord) == '[') {
                     startGoodsInfo = Util.addArray(startGoodsInfo, startGoodsInfo.length, new int[]{i, startWord});
                  }

                  if (str.charAt(endWord) == ']') {
                     endGoodsInfo = Util.addArray(endGoodsInfo, endGoodsInfo.length, new int[]{i, endWord});
                  }

                  goodsStr = Util.addArray(goodsStr, goodsStr.length, new int[]{startWord, endWord, goodsDraw[2]});
               }

               if (!newLine) {
                  break;
               }

               newLine = false;
               goodsStrings[i] = goodsStr;
               goodsStr = new int[0][];
               startLine -= tempStr[i].length();
               endLine -= tempStr[i].length();
               ++i;
            }
         }

         goodsStrings[i] = goodsStr;
      } else {
         startGoodsInfo = (int[][])null;
         endGoodsInfo = (int[][])null;
      }

      this.startName[index] = nameInfo;
      this.startChatIndex[index] = startGoodsInfo;
      this.endChatIndex[index] = endGoodsInfo;
      this.showGoodsString = Util.addArrays(this.showGoodsString, end ? this.showGoodsString.length : 0, goodsStrings);
   }

   public void goodsInfo() {
      int len = this.showChatString.length;
      this.selectG = new int[len][];
      this.selectN = new int[len][];

      for(int i = 0; i < len; ++i) {
         if ((this.controlState == 2 || this.controlState == 3 || this.controlState == 4) && i >= this.curSelectRow && i < this.curSelectRow + this.showChatRow[this.curSelectChat - this.curChatFirst]) {
            int startLine;
            int startLine;
            if (this.curSelectGoodsStr != null && this.controlState == 3) {
               startLine = this.curSelectRow + this.showChatRow[this.curSelectChat - this.curChatFirst];
               startLine = this.curSelectGoodsStr[0];
               int endLine = this.curSelectGoodsStr[1];

               for(int k = this.curSelectRow; k < startLine; ++k) {
                  int length = this.showChatString[k].length();
                  if (i == k) {
                     int startWord = true;
                     int endWord = true;
                     int startWord;
                     int endWord;
                     if (startLine < 0) {
                        if (endLine < 0) {
                           startWord = -1;
                           endWord = -1;
                        } else if (endLine < length) {
                           startWord = 0;
                           endWord = endLine;
                        } else {
                           startWord = 0;
                           endWord = length - 1;
                        }
                     } else if (startLine < length) {
                        if (endLine < 0) {
                           startWord = -1;
                           endWord = -1;
                        } else if (endLine < length) {
                           startWord = startLine;
                           endWord = endLine;
                        } else {
                           startWord = startLine;
                           endWord = length - 1;
                        }
                     } else {
                        startWord = -1;
                        endWord = -1;
                     }

                     if (startWord >= 0 && endWord >= 0) {
                        this.selectG[i] = new int[]{startWord, endWord};
                     }
                     break;
                  }

                  startLine -= length;
                  endLine -= length;
               }
            }

            if (this.curSelectNameStr != null) {
               startLine = this.curSelectNameStr[0];
               startLine = this.curSelectNameStr[1];
               int k = this.curSelectRow;
               if (i == k) {
                  this.selectN[i] = new int[]{startLine, startLine + startLine};
               }
            }
         }
      }

   }

   private final void drawChatStrings(Graphics g, int dy) {
      int x = g.getClipX();
      int y = g.getClipY();
      int w = g.getClipWidth();
      int h = g.getClipHeight();
      g.setClip(this.chatOffX, this.chatOffY, this.chatWidth, this.chatheight);

      for(int i = 0; i < this.showChatString.length; ++i) {
         int color = this.showChatColor[i];
         if ((this.controlState == 2 || this.controlState == 3 || this.controlState == 4) && i >= this.curSelectRow && i < this.curSelectRow + this.showChatRow[this.curSelectChat - this.curChatFirst]) {
            color = 16776960;
         }

         SIChat.drawChatString(g, this.showChatString[i], color, this.chatOffX, dy + this.chatOffY + MainCanvas.CHARH * i, this.showGoodsString[i], this.selectG[i], this.selectN[i]);
      }

      g.setClip(x, y, w, h);
   }

   private final void touchScreenAction() {
      if (MainCanvas.SUPPORT_POINTER && MainCanvas.isInputDown(268435456)) {
         boolean isAction = false;
         if (this.menu != null) {
            if (this.menu.pointIndex()) {
               MainCanvas.keySimPressed(131072);
               isAction = true;
            } else {
               this.menu = null;
               this.touchAct();
               isAction = true;
            }
         } else {
            this.touchAct();
         }

         int xL = 0;
         int yL = MainCanvas.screenH - UITitle.getMenuBarHeight();
         int wL = MainCanvas.CHARW + 4 << 1;
         int hL = UITitle.getMenuBarHeight();
         if (MainCanvas.isPointInRect(xL, yL, wL, hL)) {
            MainCanvas.keySimPressed(131072);
         }

         if (this.scroll != null && this.showChatString.length > this.chatRow && this.scroll.focusWidgetPointAction()) {
            isAction = true;
         }

         if (!isAction) {
            int xR = MainCanvas.screenW - (MainCanvas.CHARW + 4 << 1);
            int yR = MainCanvas.screenH - UITitle.getMenuBarHeight();
            int wR = MainCanvas.CHARW + 4 << 1;
            int hR = UITitle.getMenuBarHeight();
            if (MainCanvas.isPointInRect(xR, yR, wR, hR)) {
               MainCanvas.keySimPressed(262144);
               isAction = true;
            }
         }
      }

      this.goodsInfo();
   }

   public void touchAct() {
      int x1 = this.chatOffX;
      int y1 = this.chatOffY;
      int w1 = this.chatWidth;
      int h1 = this.chatheight;
      int y2 = this.textOffY;
      int h2 = this.chatOffY - this.textOffY;
      int oldSelectChat = -1;
      int oldSelectGoods = true;
      int oldSelectName = true;
      int oldSelect = -1;
      if (MainCanvas.isPointInRect(x1, y2, w1, h2)) {
         if (this.curChat.length == 0) {
            MainCanvas.keySimPressed(131072);
            return;
         }

         this.isInRegion = !this.isInRegion;
         if (!this.isInRegion) {
            MainCanvas.keySimPressed(131072);
         } else if (this.showChannel >= 1 && this.showChannel <= 4) {
            this.changeControlState((byte)1);
         }
      }

      if (MainCanvas.isPointInRect(x1, y1, w1, h1)) {
         if (this.curChat.length == 0) {
            return;
         }

         this.isInRegion = false;
         int witchLine1 = this.getChatSelect();
         int witchLine = witchLine1;
         int lines;
         if (this.smallIndex > 0) {
            lines = this.getLinesNum(this.smallIndex);
            witchLine = witchLine1 + lines;
         }

         int lines = false;
         int allLines = 0;
         int i = 0;

         String[] tmpNameContent;
         for(int len = this.curChat.length; i < len; ++i) {
            tmpNameContent = Util.wrapText(this.curChat[i], this.chatWidth, MainCanvas.curFont);
            lines = tmpNameContent.length;
            allLines += lines;
            if (allLines >= witchLine + 1) {
               if (this.controlState != 1 && this.controlState != 3) {
                  oldSelectChat = this.curSelectChat;
               }

               oldSelect = this.curSelectChat;
               this.curSelectChat = (byte)i;
               break;
            }
         }

         if (this.controlState == 2 || this.controlState == 3 || this.controlState == 1 || this.controlState == 4) {
            if (this.controlState == 1 && this.curChat.length > 0) {
               this.changeControlState((byte)2);
            }

            int oldSelectGoods = this.curSelectGoods;
            int oldSelectName = this.curSelectName;
            this.scrollState = 1;
            if (this.curSelectChat >= this.curChatStart && this.curSelectChat <= this.curChatEnd) {
               this.refreshSelectRow();
            } else {
               this.refreshChannelString();
            }

            String str = this.showChatString[witchLine1];
            Object tmpGoodsContent = null;
            tmpNameContent = null;
            Object tmpNameContent;
            if (this.showChannel == 100) {
               tmpGoodsContent = allGoodsContents[this.curSelectChat];
               tmpNameContent = allNameContent[this.curSelectChat];
            } else if (this.showChannel == 6) {
               tmpGoodsContent = hornGoodsContents[this.curSelectChat];
               tmpNameContent = hornNameContent[this.curSelectChat];
            } else if (this.showChannel == 5) {
               tmpGoodsContent = allGoodsContentsInSer[this.curSelectChat];
               tmpNameContent = allNameContentInSer[this.curSelectChat];
            } else {
               tmpGoodsContent = channelGoodsContents[this.showChannel][this.curSelectChat];
               tmpNameContent = channelNameContent[this.showChannel][this.curSelectChat];
            }

            if (tmpGoodsContent == null && tmpNameContent == null) {
               if (tmpGoodsContent == null && tmpNameContent == null) {
                  this.curSelectNameStr = null;
                  this.changeControlState((byte)2);
                  if (oldSelectChat == this.curSelectChat) {
                     MainCanvas.keySimPressed(131072);
                  }
               }
            } else {
               this.curSelectName = this.selectName(witchLine, str);
               this.curSelectGoods = this.selectGoods(witchLine, str);
               if (this.curSelectGoods == -1 && this.curSelectName == -1) {
                  this.curSelectNameStr = null;
                  if (this.controlState != 2) {
                     this.changeControlState((byte)2);
                  } else if (oldSelectChat == this.curSelectChat) {
                     MainCanvas.keySimPressed(131072);
                  }

                  return;
               }

               int strLength;
               if (this.curSelectName != -1 && this.curSelectGoods == -1) {
                  this.curSelectNameStr = null;
                  this.changeControlState((byte)4);
                  if (oldSelect == this.curSelectChat && oldSelectName == this.curSelectName && oldSelectName != -1) {
                     MainCanvas.keySimPressed(131072);
                  }

                  Vector tmpName = (Vector)tmpNameContent;
                  int startW = Integer.parseInt(tmpName.elementAt(0).toString());
                  strLength = Integer.parseInt(tmpName.elementAt(1).toString());
                  this.curSelectNameStr = new int[]{startW, strLength};
               } else if (this.curSelectName == -1 && this.curSelectGoods != -1) {
                  this.curSelectNameStr = null;
                  this.changeControlState((byte)3);
                  if (oldSelect == this.curSelectChat && oldSelectGoods == this.curSelectGoods) {
                     MainCanvas.keySimPressed(131072);
                  }

                  Vector[] tmpGoods = (Vector[])tmpGoodsContent;
                  short index = (Short)tmpGoods[this.curSelectGoods].elementAt(0);
                  strLength = ((String)tmpGoods[this.curSelectGoods].elementAt(1)).length();
                  this.curSelectGoodsStr = new int[]{index, index + strLength - 1};
               }
            }
         }
      }

   }

   public byte selectName(int witchLine, String str) {
      byte isSelectName = -1;
      int length = 0;
      if (this.curSelectChat > 0) {
         length = this.getLinesNum(this.curSelectChat);
      }

      int[] nameStart = this.startName[this.curSelectChat];
      if (nameStart == null) {
         return -1;
      } else {
         char[] c = str.toCharArray();
         int len = false;
         int len2 = false;
         if (length == witchLine) {
            int len = MainCanvas.curFont.charsWidth(c, 0, nameStart[0]);
            int len2 = MainCanvas.curFont.charsWidth(c, 0, nameStart[0] + nameStart[1]);
            if (MainCanvas.pointerX >= len + this.chatOffX && MainCanvas.pointerX <= len2 + this.chatOffX) {
               isSelectName = 0;
            }
         }

         return isSelectName;
      }
   }

   public int getLinesNum(int endIndex) {
      int length = 0;

      for(int j = 0; j < endIndex; ++j) {
         String[] tempStr = Util.wrapText(this.curChat[j], this.chatWidth, MainCanvas.curFont);
         int len2 = tempStr.length;
         length += len2;
      }

      return length;
   }

   public byte selectGoods(int witchLine, String str) {
      int length = 0;
      if (this.curSelectChat > 0) {
         length = this.getLinesNum(this.curSelectChat);
      }

      int[][] startInfo = this.startChatIndex[this.curSelectChat];
      int[][] endInfo = this.endChatIndex[this.curSelectChat];
      if (startInfo != null && endInfo != null) {
         byte select = -1;
         int len = false;
         int len2 = false;
         char[] c = str.toCharArray();

         for(byte i = 0; i < startInfo.length; ++i) {
            int startLine = startInfo[i][0] + length;
            int endLine = endInfo[i][0] + length;
            int len;
            if (startLine == witchLine && endLine != witchLine) {
               len = MainCanvas.curFont.charsWidth(c, 0, startInfo[i][1]);
               if (MainCanvas.pointerX >= len + this.chatOffX) {
                  select = i;
               }
            }

            int len2;
            if (startLine != witchLine && endLine == witchLine) {
               len2 = MainCanvas.curFont.charsWidth(c, 0, endInfo[i][1]);
               if (MainCanvas.pointerX <= len2 + this.chatOffX) {
                  select = i;
               }
            }

            if (startLine == witchLine && endLine == witchLine) {
               len = MainCanvas.curFont.charsWidth(c, 0, startInfo[i][1]);
               len2 = MainCanvas.curFont.charsWidth(c, 0, endInfo[i][1]);
               if (MainCanvas.pointerX >= len + this.chatOffX && MainCanvas.pointerX <= len2 + this.chatOffX) {
                  select = i;
               }
            }
         }

         return select;
      } else {
         return -1;
      }
   }

   public byte getChatSelect() {
      byte index = false;
      if (MainCanvas.pointerY < this.chatOffY) {
         return 0;
      } else if (MainCanvas.pointerY > this.chatOffY + (this.showChatString.length - 1) * MainCanvas.CHARH) {
         return (byte)(this.showChatString.length - 1);
      } else {
         byte index = (byte)((MainCanvas.pointerY - this.chatOffY - this.firstStringDy) / MainCanvas.CHARH);
         return index;
      }
   }

   public final void keyInAction() {
      this.touchScreenAction();
      if (MainCanvas.isInputDownOrHold(4096)) {
         if (this.menu != null) {
            this.menu.decreaseIndex();
         } else if (this.controlState == 2 || this.controlState == 3 || this.controlState == 4) {
            if (this.curSelectNameStr != null) {
               this.curSelectNameStr = null;
            }

            this.changeControlState((byte)2);
            this.scrollState = 0;
            if (--this.curSelectChat >= 0) {
               if (this.curSelectChat < this.curChatStart) {
                  this.refreshChannelString();
               } else {
                  this.refreshSelectRow();
               }
            } else {
               this.scrollState = 1;
               this.curSelectChat = 0;
               if (this.showChannel >= 1 && this.showChannel <= 4 || this.showChannel == 6) {
                  this.changeControlState((byte)1);
               }
            }
         }

         this.goodsInfo();
      } else if (MainCanvas.isInputDownOrHold(8192)) {
         if (this.menu != null) {
            this.menu.increaseIndex();
         } else if (this.controlState == 1) {
            if (this.curChat.length > 0) {
               this.changeControlState((byte)2);
            }
         } else if (this.controlState == 2 || this.controlState == 3 || this.controlState == 4) {
            if (this.curSelectNameStr != null) {
               this.curSelectNameStr = null;
            }

            this.changeControlState((byte)2);
            this.scrollState = 1;
            if (++this.curSelectChat > this.curChat.length - 1) {
               this.curSelectChat = (byte)(this.curChat.length - 1);
            } else if (this.curSelectChat > this.curChatEnd) {
               this.refreshChannelString();
            } else {
               this.refreshSelectRow();
            }
         }

         this.goodsInfo();
      } else {
         Object tmpGoodsContent;
         Object tmpNameContent;
         Vector tmpName;
         int length;
         int len;
         Vector[] tmpGoods;
         if (MainCanvas.isInputDownOrHold(16384)) {
            if (this.menu != null) {
               return;
            }

            tmpGoodsContent = null;
            tmpNameContent = null;
            if (this.controlState == 3 || this.controlState == 4) {
               if (this.showChannel == 100) {
                  tmpGoodsContent = allGoodsContents[this.curSelectChat];
                  tmpNameContent = allNameContent[this.curSelectChat];
               } else if (this.showChannel == 5) {
                  tmpGoodsContent = allGoodsContentsInSer[this.curSelectChat];
                  tmpNameContent = allNameContentInSer[this.curSelectChat];
               } else if (this.showChannel == 6) {
                  tmpGoodsContent = hornGoodsContents[this.curSelectChat];
                  tmpNameContent = hornNameContent[this.curSelectChat];
               } else {
                  tmpGoodsContent = channelGoodsContents[this.showChannel][this.curSelectChat];
                  tmpNameContent = channelNameContent[this.showChannel][this.curSelectChat];
               }

               if (tmpGoodsContent != null && this.controlState == 3) {
                  if (this.curSelectGoods >= 0) {
                     --this.curSelectGoods;
                     if (this.curSelectGoods == -1) {
                        if (tmpNameContent != null) {
                           this.changeControlState((byte)4);
                           tmpName = (Vector)tmpNameContent;
                           length = Integer.parseInt(tmpName.elementAt(0).toString());
                           len = Integer.parseInt(tmpName.elementAt(1).toString());
                           this.curSelectNameStr = new int[]{length, len};
                        } else {
                           this.changeControlState((byte)2);
                        }

                        this.curSelectGoodsStr = null;
                     } else {
                        tmpGoods = (Vector[])tmpGoodsContent;
                        short index = (Short)tmpGoods[this.curSelectGoods].elementAt(0);
                        len = ((String)tmpGoods[this.curSelectGoods].elementAt(1)).length();
                        this.curSelectGoodsStr = new int[]{index, index + len - 1};
                     }
                  }
               } else if (this.controlState == 4) {
                  this.changeControlState((byte)2);
                  this.curSelectNameStr = null;
               }
            }

            this.goodsInfo();
         } else if (MainCanvas.isInputDownOrHold(32768)) {
            if (this.menu != null) {
               return;
            }

            if (this.controlState == 2 || this.controlState == 3 || this.controlState == 4) {
               tmpGoodsContent = null;
               tmpNameContent = null;
               if (this.showChannel == 100) {
                  tmpNameContent = allGoodsContents[this.curSelectChat];
                  tmpGoodsContent = allNameContent[this.curSelectChat];
               } else if (this.showChannel == 5) {
                  tmpNameContent = allGoodsContentsInSer[this.curSelectChat];
                  tmpGoodsContent = allNameContentInSer[this.curSelectChat];
               } else if (this.showChannel == 6) {
                  tmpNameContent = hornGoodsContents[this.curSelectChat];
                  tmpGoodsContent = hornNameContent[this.curSelectChat];
               } else {
                  tmpNameContent = channelGoodsContents[this.showChannel][this.curSelectChat];
                  tmpGoodsContent = channelNameContent[this.showChannel][this.curSelectChat];
               }

               if (tmpGoodsContent != null && this.controlState == 2) {
                  this.changeControlState((byte)4);
                  tmpName = (Vector)tmpGoodsContent;
                  length = Integer.parseInt(tmpName.elementAt(0).toString());
                  len = Integer.parseInt(tmpName.elementAt(1).toString());
                  this.curSelectNameStr = new int[]{length, len};
               } else if (tmpNameContent != null) {
                  if (this.curSelectGoods == -1) {
                     this.changeControlState((byte)3);
                     this.curSelectNameStr = null;
                  }

                  ++this.curSelectGoods;
                  tmpGoods = (Vector[])tmpNameContent;
                  length = tmpGoods.length;
                  if (this.curSelectGoods >= length) {
                     this.curSelectGoods = (byte)(length - 1);
                  } else {
                     short index = (Short)tmpGoods[this.curSelectGoods].elementAt(0);
                     int strLength = ((String)tmpGoods[this.curSelectGoods].elementAt(1)).length();
                     this.curSelectGoodsStr = new int[]{index, index + strLength - 1};
                  }
               }
            }

            this.goodsInfo();
         } else if (MainCanvas.isInputDown(196608)) {
            if (this.menu != null) {
               this.keyInMenu();
            } else {
               switch(this.controlState) {
               case 0:
                  switch(this.showChannel) {
                  case 0:
                     this.addSenderList(false);
                     return;
                  case 100:
                     this.addChannelList(false);
                     return;
                  default:
                     return;
                  }
               case 1:
                  if (this.showChannel == 6) {
                     chatNetSendChannel = 11;
                  } else {
                     chatNetSendChannel = (byte)(this.showChannel + 1);
                  }

                  chatRecieverId = -1;
                  chatRecieverName = null;
                  this.advancedForm(true);
                  break;
               case 2:
                  switch(this.showChannel) {
                  case 0:
                     this.addSenderList(Player.getInstance().getID() != channelSenderId[this.showChannel][this.curSelectChat]);
                     return;
                  case 5:
                     return;
                  case 100:
                     this.addChannelList(false);
                     return;
                  default:
                     String[] operate = (String[])null;
                     int[] ids = (int[])null;
                     if (this.showChannel == 6) {
                        operate = new String[]{"Nhập vào"};
                        ids = new int[]{-268435455};
                     } else if (Player.getInstance().getID() != channelSenderId[this.showChannel][this.curSelectChat] && allSenderName[this.curSelectChat].length() != 0) {
                        operate = new String[]{"Trả lời", "Nhập vào"};
                        ids = new int[]{-268435456, -268435455};
                     } else {
                        operate = new String[]{"Nhập vào"};
                        ids = new int[]{-268435455};
                     }

                     this.menu = new UIMenu((byte)0);
                     this.menu.addMenu(operate, ids);
                     return;
                  }
               case 3:
                  UIMenu.formSaveForm();
                  tmpGoodsContent = null;
                  if (this.showChannel == 100) {
                     goodsOwnerId = allSenderId[this.curSelectChat];
                     tmpGoodsContent = allGoodsContents[this.curSelectChat];
                  } else if (this.showChannel == 5) {
                     goodsOwnerId = allSenderIdInSer[this.curSelectChat];
                     tmpGoodsContent = allGoodsContentsInSer[this.curSelectChat];
                  } else if (this.showChannel == 6) {
                     goodsOwnerId = hornSenderId[this.curSelectChat];
                     tmpGoodsContent = hornGoodsContents[this.curSelectChat];
                  } else {
                     goodsOwnerId = channelSenderId[this.showChannel][this.curSelectChat];
                     tmpGoodsContent = channelGoodsContents[this.showChannel][this.curSelectChat];
                  }

                  if (tmpGoodsContent != null) {
                     Vector[] tmpGoods = (Vector[])tmpGoodsContent;
                     goodsId = (Long)tmpGoods[this.curSelectGoods].elementAt(2);
                     goodsAttach = (Byte)tmpGoods[this.curSelectGoods].elementAt(3);
                     UIGrid.curSelStrColor = ((int[])tmpGoods[this.curSelectGoods].elementAt(5))[2];
                     MainCanvas.ni.send(655363);
                  }
                  break;
               case 4:
                  this.addChannelList(true);
               }
            }
         } else if (MainCanvas.isInputDown(262144)) {
            if (this.menu != null) {
               this.menu = null;
            } else {
               this.clear();
               UIForm.backUIFormAction();
               UIForm.backUIFormAction();
               if (MainCanvas.curForm != null && (MainCanvas.curForm.clientCommand == -1610612687 || MainCanvas.curForm.clientCommand == 458773 || MainCanvas.curForm.clientCommand == 262193)) {
                  MainCanvas.quitUI();
               }

               if (MainCanvas.curForm != null) {
                  MainCanvas.setGameStateWithoutOld((byte)4);
               } else if (MainCanvas.rightMenu != null) {
                  MainCanvas.setGameStateWithoutOld((byte)1);
               } else {
                  MainCanvas.setGameStateWithoutOld((byte)0);
               }
            }
         }
      }

   }

   private final void addChannelList(boolean selectName) {
      String[] operate = (String[])null;
      int[] ids = (int[])null;
      if (selectName) {
         operate = new String[]{"Trả lời", "Tra thông tin", "Thêm hảo hữu", "Người chơi xung quanh", "Báo cáo"};
         ids = new int[]{-268435456, 589831, 589835, 589836, 196638};
         String tmpMenu;
         int tmpCmd;
         if (SITeam.teamState == 0 || SITeam.headerID == Player.getInstance().getID()) {
            tmpMenu = "Mời tổ đội";
            tmpCmd = 720897;
            operate = Util.addArray(operate, operate.length, tmpMenu);
            ids = Util.addArray(ids, ids.length, tmpCmd);
         }

         if (Player.getInstance().getKingRight() != PCKing.KING_RIGHT_HERDER && Player.getInstance().getKingRight() != PCKing.KING_RIGHT_VICE_HERDER) {
            if (Player.getInstance().getClanRight() == PCClan.CLAN_RIGHT_HERDER || Player.getInstance().getClanRight() == PCClan.CLAN_RIGHT_VICE_HERDER) {
               tmpMenu = "Chiêu nạp";
               tmpCmd = 1638405;
               operate = Util.addArray(operate, operate.length, tmpMenu);
               ids = Util.addArray(ids, ids.length, tmpCmd);
            }
         } else {
            tmpMenu = "Chiêu nạp";
            tmpCmd = 1835013;
            operate = Util.addArray(operate, operate.length, tmpMenu);
            ids = Util.addArray(ids, ids.length, tmpCmd);
         }
      } else if (allChat.length != 0 && allSenderName[this.curSelectChat].length() != 0 && Player.getInstance().getID() != allSenderId[this.curSelectChat]) {
         if (Map.m_bBattlefield) {
            operate = new String[]{"Trả lời", "Kênh thế giới", "Kênh Thị tộc", "Kênh bối cảnh", "Kênh tổ đội", "Kênh loa"};
            ids = new int[]{-268435456, -268435454, -268435453, -268435452, -268435451, -268435450};
         } else {
            operate = new String[]{"Trả lời", "Kênh thế giới", "Kênh thị tộc", "Kênh bối cảnh", "Kênh tổ đội", "Kênh loa", "[Bạn bè]"};
            ids = new int[]{-268435456, -268435454, -268435453, -268435452, -268435451, -268435450, 589834};
         }
      } else if (Map.m_bBattlefield) {
         operate = new String[]{"Kênh thế giới", "Kênh Thị tộc", "Kênh bối cảnh", "Kênh tổ đội", "Kênh loa"};
         ids = new int[]{-268435454, -268435453, -268435452, -268435451, -268435450};
      } else {
         operate = new String[]{"Kênh thế giới", "Kênh thị tộc", "Kênh bối cảnh", "Kênh tổ đội", "Kênh loa", "[Bạn bè]"};
         ids = new int[]{-268435454, -268435453, -268435452, -268435451, -268435450, 589834};
      }

      this.menu = new UIMenu((byte)0);
      this.menu.addMenu(operate, ids);
   }

   private final void addSenderList(boolean canRevert) {
      String[] operate = (String[])null;
      int[] ids = (int[])null;
      if (canRevert) {
         boolean canTeam = SITeam.teamState == 0 || SITeam.headerID == Player.getInstance().getID();
         operate = new String[lastSenderName.length + (canTeam ? 3 : 2)];
         ids = new int[operate.length];
         operate[0] = "Trả lời";
         ids[0] = -268435456;
         System.arraycopy(lastSenderName, 0, operate, 1, lastSenderName.length);

         int i;
         for(i = 0; i < lastSenderId.length; ++i) {
            ids[i + 1] = -16777216 + i;
         }

         ++i;
         if (!Map.m_bBattlefield) {
            operate[i] = "[Bạn bè]";
            ids[i] = 589834;
         }

         if (canTeam) {
            ++i;
            operate[i] = "Mời tổ đội";
            ids[i] = 720897;
         }

         copySenderId = new int[lastSenderId.length];
         System.arraycopy(lastSenderId, 0, copySenderId, 0, lastSenderId.length);
      } else {
         if (Map.m_bBattlefield) {
            return;
         }

         operate = new String[lastSenderName.length + 1];
         ids = new int[operate.length];
         System.arraycopy(lastSenderName, 0, operate, 0, lastSenderName.length);

         for(int i = 0; i < ids.length - 1; ++i) {
            ids[i] = -16777216 + i;
         }

         operate[operate.length - 1] = "[Bạn bè]";
         ids[ids.length - 1] = 589834;
         copySenderId = new int[lastSenderId.length];
         System.arraycopy(lastSenderId, 0, copySenderId, 0, lastSenderId.length);
      }

      this.menu = new UIMenu((byte)0);
      this.menu.addMenu(operate, ids);
   }

   private final void keyInMenu() {
      int cmds = (Integer)this.menu.getCmdIds().elementAt(this.menu.getIndex());
      switch(cmds) {
      case -268435456:
         if (this.showChannel == 100) {
            intoPrivateChannel(allSenderId[this.curSelectChat], allSenderName[this.curSelectChat], false);
         } else if (this.showChannel == 6) {
            intoPrivateChannel(hornSenderId[this.curSelectChat], hornSenderName[this.curSelectChat], false);
         } else {
            intoPrivateChannel(channelSenderId[this.showChannel][this.curSelectChat], channelSenderName[this.showChannel][this.curSelectChat], false);
         }
         break;
      case -268435455:
         if (this.showChannel == 6) {
            chatNetSendChannel = 11;
         } else {
            chatNetSendChannel = (byte)(this.showChannel + 1);
         }

         chatRecieverId = -1;
         chatRecieverName = null;
         this.advancedForm(true);
         break;
      case -268435454:
      case -268435453:
      case -268435452:
      case -268435451:
      case -268435450:
         if (cmds == -268435450) {
            chatNetSendChannel = 11;
         } else {
            chatNetSendChannel = (byte)(cmds - -268435454 + 2);
         }

         chatRecieverId = -1;
         chatRecieverName = null;
         this.advancedForm(true);
         break;
      case 196638:
         if (this.showChannel == 100) {
            GOManager.reportedPlayerID = allSenderId[this.curSelectChat];
         } else if (this.showChannel == 6) {
            GOManager.reportedPlayerID = hornSenderId[this.curSelectChat];
         } else {
            GOManager.reportedPlayerID = channelSenderId[this.showChannel][this.curSelectChat];
         }

         MainCanvas.ni.send(196638);
         if (this.menu != null) {
            this.menu = null;
         }
         break;
      case 589831:
         playerInfoInChat = true;
         if (this.showChannel == 100) {
            SITeam.otherPlayerID = allSenderId[this.curSelectChat];
         } else if (this.showChannel == 6) {
            SITeam.otherPlayerID = hornSenderId[this.curSelectChat];
         } else {
            SITeam.otherPlayerID = channelSenderId[this.showChannel][this.curSelectChat];
         }

         UITable.selectedPlayerId = SITeam.otherPlayerID;
         MainCanvas.ni.send(cmds);
         UIMenu.formSaveForm();
         break;
      case 589834:
         MainCanvas.ni.send(589834);
         UIMenu.formSaveForm();
         break;
      case 589835:
         if (this.showChannel == 100) {
            UITable.selectedPlayerId = allSenderId[this.curSelectChat];
         } else if (this.showChannel == 6) {
            UITable.selectedPlayerId = hornSenderId[this.curSelectChat];
         } else {
            UITable.selectedPlayerId = channelSenderId[this.showChannel][this.curSelectChat];
         }

         MainCanvas.ni.send(589835);
         break;
      case 589836:
         if (this.showChannel == 100) {
            UITable.selectedPlayerId = allSenderId[this.curSelectChat];
         } else if (this.showChannel == 6) {
            UITable.selectedPlayerId = hornSenderId[this.curSelectChat];
         } else {
            UITable.selectedPlayerId = channelSenderId[this.showChannel][this.curSelectChat];
         }

         MainCanvas.ni.send(589836);
         break;
      case 720897:
         if (this.showChannel == 100) {
            SITeam.otherPlayerID = allSenderId[this.curSelectChat];
         } else if (this.showChannel == 6) {
            SITeam.otherPlayerID = hornSenderId[this.curSelectChat];
         } else {
            SITeam.otherPlayerID = channelSenderId[this.showChannel][this.curSelectChat];
         }

         MainCanvas.ni.send(720897);
         break;
      case 1638405:
         if (this.showChannel == 100) {
            PCClan.otherPlayerID = allSenderId[this.curSelectChat];
         } else if (this.showChannel == 6) {
            PCClan.otherPlayerID = hornSenderId[this.curSelectChat];
         } else {
            PCClan.otherPlayerID = channelSenderId[this.showChannel][this.curSelectChat];
         }

         MainCanvas.ni.send(cmds);
         break;
      case 1835013:
         if (this.showChannel == 100) {
            PCKing.otherPlayerID = allSenderId[this.curSelectChat];
         } else if (this.showChannel == 6) {
            PCKing.otherPlayerID = hornSenderId[this.curSelectChat];
         } else {
            PCKing.otherPlayerID = channelSenderId[this.showChannel][this.curSelectChat];
         }

         MainCanvas.ni.send(cmds);
         break;
      default:
         int id = cmds - -16777216;
         if (id >= 0 && id < copySenderId.length) {
            intoPrivateChannel(copySenderId[id], (String)this.menu.getItems().elementAt(this.menu.getIndex()), false);
         }
      }

      this.menu = null;
   }

   public final void changeControlState(byte state) {
      this.controlState = state;
      switch(state) {
      case 1:
         this.curSelectGoods = -1;
         this.curSelectName = -1;
         this.bottomMenu.setMenuText("Nhập vào", 0);
         break;
      case 2:
         this.curSelectGoods = -1;
         this.curSelectName = -1;
         if (this.showChannel == 5) {
            this.bottomMenu.setMenuText("", 0);
         } else {
            this.bottomMenu.setMenuText("Thao tác", 0);
         }
         break;
      case 3:
         this.curSelectName = -1;
         this.bottomMenu.setMenuText("Tra tìm", 0);
         break;
      case 4:
         this.curSelectGoods = -1;
         this.bottomMenu.setMenuText("Thao tác", 0);
      }

   }

   public void advancedForm(boolean isFirst) {
      if (isFirst) {
         UIForm.backUIFormAction();
      }

      Form form = new Form(this.topTitle.getTitleText());
      Image faceIcon = Util.loadImage("/special/face.png");
      final TextField tf = new TextField("", this.textStr, 30, 0);
      StringItem si2 = new StringItem("Cách nhập nhiệm vụ và vật phẩm:\n[chữ b] Vật phẩm hành nang: [b1]", "");
      StringItem si3 = new StringItem("[chữ c] Vật phẩm thương khố: [c1]", "");
      StringItem si4 = new StringItem("[chữ z] Vật phẩm trang bị: [z1]", "");
      StringItem si5 = new StringItem("[chữ r] Tên nhiệm vụ: [r1]", "");
      StringItem si = new StringItem("Nhập vào#0~9 Hiển thị biểu cảm #0", "");
      ImageItem ii = new ImageItem((String)null, faceIcon, 3, (String)null);
      if (chatNetSendChannel == 1) {
         tf.setLabel("và" + chatRecieverName + "Trò chuyện:");
      } else if (chatNetSendChannel == 11) {
         tf.setLabel("Gửi đếnKênh loa");
      } else {
         tf.setLabel("Gửi đến" + STR_CHANNEL_TITLE[chatNetSendChannel - 1]);
      }

      form.append(tf);
      form.append(si);
      form.append(ii);
      form.append(si2);
      form.append(si3);
      form.append(si4);
      form.append(si5);
      final Command okCmd = new Command("Gửi", 4, 0);
      final Command exitCmd = new Command(Cons.C_STR[1], 2, 1);
      final Command insertFace = new Command("Chèn biểu cảm", 4, 1);
      final Command insertGoods = new Command("Chèn vật phẩm", 4, 1);
      final Command insertTask = new Command("Chèn nhiệm vụ", 4, 1);
      form.addCommand(okCmd);
      form.addCommand(exitCmd);
      form.addCommand(insertFace);
      form.addCommand(insertGoods);
      form.addCommand(insertTask);
      form.setCommandListener(new CommandListener() {
         public void commandAction(Command c, Displayable d) {
            if (c == okCmd) {
               if (!"".equals(tf.getString().trim())) {
                  String str = tf.getString();
                  tf.setString("");
                  UIChat.this.textStr = "";
                  if (str.trim().equals("")) {
                     return;
                  }

                  if (UIChat.chatNetSendChannel != 11 && System.currentTimeMillis() - SIChat.chatStartTime[UIChat.chatNetSendChannel] < (long)SIChat.chatIntervalTime[UIChat.chatNetSendChannel]) {
                     UITopForm.createLocalTopForm((byte)0, (String)"Phát ngôn quá nhiều!", Cons.C_STR[2], "", -1, -2);
                     UIMenu.formSaveForm();
                     MainCanvas.setGameStateWithoutOld((byte)3);
                     MainCanvas.mc.setFullScreenMode(true);
                     MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
                     return;
                  }

                  UIChat.this.sendChat(str);
                  if (UIChat.chatNetSendChannel != 11) {
                     SIChat.chatStartTime[UIChat.chatNetSendChannel] = System.currentTimeMillis();
                  }

                  if (UIChat.chatNetSendChannel == 1 && UIChat.this.showChannel >= 1 && UIChat.this.showChannel <= 4) {
                     UIChat.chatForm = new UIChat((byte)0);
                  } else {
                     UIMenu.formSaveForm();
                  }

                  MainCanvas.setGameStateWithoutOld((byte)3);
                  MainCanvas.mc.setFullScreenMode(true);
                  MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
               }
            } else if (c == exitCmd) {
               UIChat.this.textStr = "";
               MainCanvas.setGameStateWithoutOld((byte)3);
               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
               UIMenu.formSaveForm();
            } else if (c == insertFace) {
               UIChat.this.textStr = tf.getString();
               MainCanvas.curForm = (UIForm)MainCanvas.curFormVector.elementAt(0);
               MainCanvas.setGameStateWithoutOld((byte)4);
               MainCanvas.curForm.clientCommand = -1610612687;
               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            } else if (c == insertGoods) {
               UIChat.this.textStr = tf.getString();
               MainCanvas.curForm = (UIForm)MainCanvas.curFormVector.elementAt(1);
               if (MainCanvas.curFormVector.elementAt(1) == null) {
                  MainCanvas.replaceFormId = 1;
                  MainCanvas.ni.send(458773);
                  MainCanvas.setGameStateWithoutOld((byte)3);
               } else {
                  MainCanvas.setGameStateWithoutOld((byte)4);
                  MainCanvas.curForm.addWarningStr();
               }

               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            } else if (c == insertTask) {
               UIChat.this.textStr = tf.getString();
               MainCanvas.curForm = (UIForm)MainCanvas.curFormVector.elementAt(2);
               if (MainCanvas.curFormVector.elementAt(2) == null) {
                  MainCanvas.replaceFormId = 2;
                  MainCanvas.ni.send(262193);
                  MainCanvas.setGameStateWithoutOld((byte)3);
               } else {
                  MainCanvas.setGameStateWithoutOld((byte)4);
                  MainCanvas.curForm.addWarningStr();
               }

               MainCanvas.mc.setFullScreenMode(true);
               MainCanvas.aMidlet.display.setCurrent(MainCanvas.mc);
            }

         }
      });
      MainCanvas.aMidlet.display.setCurrent(form);
   }

   private final void sendChat(String str) {
      char[] charArray = str.toCharArray();
      chatSendGoods = new Vector[0];

      for(short i = 0; i < charArray.length; ++i) {
         if (charArray[i] == '[') {
            int digit = 0;
            if (i + 2 > charArray.length - 1) {
               break;
            }

            if (i + 3 < charArray.length && charArray[i + 3] == ']') {
               digit = 1;
            } else if (i + 4 < charArray.length && charArray[i + 4] == ']') {
               digit = 2;
            }

            byte attach = 0;
            switch(charArray[i + 1]) {
            case 'b':
               attach = 1;
               break;
            case 'c':
               attach = 2;
               break;
            case 'r':
               attach = 20;
               break;
            case 't':
               if (charArray[i + 2] == ']') {
                  charArray = Util.removeArray(charArray, i, 3);
                  GOManager.getInstance();
                  String name = GOManager.currentTarget.getRoleName();
                  char[] tmpArray = name.toCharArray();
                  charArray = Util.addArrays((char[])charArray, i, (char[])tmpArray);
                  i = (short)(i + (name.length() - 1));
               }
               break;
            case 'z':
               attach = 3;
            }

            if (attach != 0) {
               byte num = -1;
               switch(digit) {
               case 1:
                  if (charArray[i + 2] >= '0' && charArray[i + 2] <= '9') {
                     num = (byte)(charArray[i + 2] - 48 - 1);
                  }
                  break;
               case 2:
                  if (charArray[i + 2] >= '0' && charArray[i + 2] <= '9' && charArray[i + 3] >= '0' && charArray[i + 3] <= '9') {
                     num = (byte)((charArray[i + 2] - 48) * 10 + (charArray[i + 3] - 48) - 1);
                  }
               }

               if (num >= 0) {
                  Vector goods = new Vector();
                  goods.addElement(new Short(i));
                  goods.addElement(new Byte(attach));
                  goods.addElement(new Byte(num));
                  chatSendGoods = Util.addArray(chatSendGoods, chatSendGoods.length, goods);
                  int last = digit == 1 ? 4 : 5;
                  charArray = Util.removeArray(charArray, i, last);
                  --i;
               }
            }
         }
      }

      chatSendString = new String(charArray);
      MainCanvas.ni.send(655361);
   }

   public static final void intoPrivateChannel(int recieveId, String receiveName, boolean newForm) {
      chatNetSendChannel = 1;
      chatRecieverId = recieveId;
      chatRecieverName = receiveName;
      if (newForm) {
         chatForm = new UIChat((byte)0);
      }

      MainCanvas.setGameStateWithoutOld((byte)3);
      chatForm.advancedForm(true);
   }

   private void clear() {
      chatForm = null;
      this.topTitle = null;
      this.bottomMenu = null;
      this.scroll = null;
      this.menu = null;
      this.showChatString = null;
      this.showChatColor = null;
      this.showChatRow = null;
      this.curChat = null;
      this.showGoodsString = null;
      this.startChatIndex = null;
      this.startName = null;
      this.endChatIndex = null;
      this.selectG = null;
      this.selectN = null;
      MainCanvas.curFormVector.removeAllElements();
   }
}

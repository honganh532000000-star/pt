import com.nokia.mid.ui.FullCanvas;
import java.util.Date;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class MainCanvas extends FullCanvas implements Runnable, MainDefine {
   final long ACTIVE_WAIT_OUT_TIME = 1000L;
   public static long activeStartTime;
   public static boolean isChinaMobileVer = false;
   public static String userID = "";
   public static String userKey = "";
   public static String new_uri = "";
   public static String secondConResult = "";
   public static final String URL_CMOBILE_IP = "gmp.i139.cn";
   public static final String URL_CMOBILE_URL = "/bizcontrol/LoginOnlineGame?sender=202&cpId=C00002&cpServiceId=120120438000&channelId=1000";
   public static final String URL_CKONG_IP = "221.179.216.38";
   public static final String URL_CKONG_IP2 = "/judgeserver/judge?ver=1&";
   byte isTestNum = 0;
   public static final String SUBGAME = "fengshen";
   public static String jarFrom = "kong";
   public static String kongMD5 = "";
   public static boolean isFullClose = false;
   public static boolean isLargeScreen;
   public static NetInterface ni;
   public static GInterface gi;
   public static int serverTick;
   public static String[] servername;
   public static String[] name_password;
   public static byte[] m_bjumpIP;
   public static byte m_bjumpIP1;
   public static String password_validate;
   public static String[] serverIp;
   public static byte[] m_sGameMount;
   public static byte[] loginType;
   public static byte servercount = 0;
   public static byte servernum = 0;
   public static UIRightMenu rightMenu = null;
   boolean isDrawTile = true;
   boolean isDrawCarryMap = true;
   boolean isDrawBig = true;
   boolean isDrawReMap = true;
   boolean isDrawOrg = true;
   boolean isBreak = false;
   private int _quakeTick = 0;
   public static boolean isinM = false;
   private static final int MILLIS_PER_TICK = 120;
   public static a aMidlet = null;
   public static int screenW = 0;
   public static int screenH = 0;
   public static int mapBGColor = 0;
   public static int CHARW = 0;
   public static int CHARH = 0;
   public static Font curFont = null;
   public static int loadCount = 0;
   public static byte emulatorType = GameLogin.emulator();
   public static final byte STATE_NONE = 0;
   public static final byte STATE_ERROR = 1;
   public static final byte STATE_MAPLOADING = 2;
   public static final byte STATE_SPLASH = 3;
   public static final byte STATE_MENU = 4;
   public static final byte STATE_GAMERUN = 5;
   public static final byte STATE_HELP = 6;
   public static final byte STATE_EXIT = 7;
   public static final byte STATE_GAME_MOUNT = 8;
   public static final byte STATE_OPTION = 9;
   public static final byte STATE_ABOUT = 10;
   public static final byte STATE_LOGIN = 11;
   public static final byte STATE_SUBAREA = 12;
   public static final byte STATE_UI = 13;
   public static final byte STATE_ENROL = 14;
   public static final byte STATE_AMENDCIPHER = 15;
   public static final byte STATE_SEIZING = 16;
   public static final byte STATE_SEL_PLAYER = 17;
   public static final byte STATE_FOUND_ROIE = 18;
   public static final byte STATE_DELETE_PART = 19;
   public static final byte STATE_SEED_SEIZING = 21;
   public static final byte STATE_SEED_COLLIGATION = 22;
   public static final byte STATE_SEED_PASSWORD = 23;
   public static final byte STATE_GAMELOADING = 24;
   public static final byte STATE_CARTOON = 26;
   public static final byte STATE_INTRODUCE = 27;
   public static final byte STATE_KEYSET = 28;
   public static final byte STATE_HELP_SUB = 29;
   public static final byte STATE_POINTS = 30;
   public static final byte STATE_CM_CHARGE = 31;
   public static final byte STATE_CM_CHARGE_RESULT = 32;
   public static final byte STATE_CM_REMAIN_RESULT = 33;
   public static final byte STATE_CM_DETAIL = 34;
   public static final byte STATE_CM_CHARGE_RECORD = 35;
   public static final byte STATE_CM_BUY_RECORD = 36;
   public static final byte STATE_CM_HELP = 37;
   public static final byte STATE_CM_SEARCH_RESULT = 38;
   public static final byte STATE_PAYMENT = 39;
   public static final byte STATE_RECHARD_SMS = 40;
   public static byte gameState = 0;
   public static byte oldGameState = 0;
   public static final byte GAMESTATE_RUN = 0;
   public static final byte GAMESTATE_RIGHT_MENU = 1;
   public static final byte GAMESTATE_CHANNEL_FORM = 3;
   public static final byte GAMESTATE_UI_FORM = 4;
   public static final byte GAMESTATE_NPC_TALK = 5;
   public static final byte GAMESTATE_OTHER_PLAYER_MENU = 6;
   private static boolean changeGameState = false;
   public static byte curState = 0;
   public static byte oldState = 0;
   public static MainCanvas mc;
   public static final byte SPF = 120;
   public static int timeTaken;
   long paintTaken;
   int gameMenuItemId = 0;
   boolean isShowGameMenu = false;
   private volatile Thread self;
   private long[] curTime = new long[1];
   private static final byte DELAY_TYPE_SPLASH = 0;
   private Image splashImage = null;
   private Image splashKoImage = null;
   private byte splashIndex = 0;
   private Image icon = null;
   public static Image logoimg = null;
   private byte mainItemID = 0;
   private byte mainBeginIndex = 0;
   private byte mainShowIndex = 0;
   public static Image si_caoImg;
   public static Image si_huaImg;
   public static Image si_HPMPImg;
   public static Image magicTT;
   public static Image shadow;
   public static Image smenu;
   public static Image smenu2;
   public static MImage touchMenu = null;
   public static MImage storeImg = null;
   public static MImage buffImg = null;
   public static MImage[] numberImg = null;
   public static final short NUMBER_MIN_RED = 0;
   public static final short NUMBER_MIN_YELLOW = 1;
   public static final short NUMBER_MIN_WHITE = 2;
   public static final short NUMBER_MIN_GREEN = 3;
   public static final short NUMBER_MAX_RED = 4;
   public static final short NUMBER_MAX_YELLOW = 5;
   public static final short NUMBER_MAX_WHITE = 6;
   public static final short NUMBER_MAX_GREEN = 7;
   public static final short NUMBER_MISS = 8;
   public static MImage kuangimg;
   public static MImage maskImg;
   public static MImage mouseImg;
   public static MImage faceImg;
   public static MImage faceImg1;
   public static Image bubble;
   public static MImage red_flag;
   public static MImage blue_flag;
   public static MImage gray_flag;
   public static MImage small_redflag;
   public static MImage small_blueflag;
   public static Image treasurePic;
   public static Image ui_1Img;
   public static Image ui_2Img;
   public static Image ui_3Img;
   public static Image ui_4Img;
   public static Image ui_5Img;
   public static Image ui_xImg;
   public static Image ui_jImg;
   public static Image ui_aImg;
   public static Image ui_labelImg;
   public static Image ui_fengImg;
   public static Image ui_dongImg;
   public static Image p_jImg;
   public static Image m_cImg;
   public static Image ui_gx1Img;
   public static Image ui_gx2Img;
   public static Image ui_mjImg;
   public static MImage ui_numMImg;
   public static Image testImg = null;
   public static Image taiji_Img = null;
   public static Image auctionArrImg = null;
   public static MImage ui_dataNumMImg;
   public static MImage ui_arrowMImg;
   public static Image starImage;
   public static MImage e0MImg;
   public static MImage e1MImg;
   public static MImage e2MImg;
   public static MImage e3MImg;
   public static MImage e4MImg;
   public static Image freeImg;
   public static Image ui_icon_shadow = null;
   public static Image mapArrImg = null;
   public static MImage ui_lvNumMImg;
   public static MImage newui_lvNumMImg;
   public static Image playerArrImg;
   public static Image talentArrImg;
   public static Image menuArraw;
   public static MImage zyMImg;
   public static MImage levelUpImg;
   public static MImage taskInfoMImg;
   public static Image upgradeMImg;
   public static UIForm curForm;
   public static UIForm AfficheForm;
   public static UIForm oldForm;
   public static Vector curFormVector = new Vector();
   public static byte replaceFormId = -1;
   public static byte stayFormId = -1;
   public static Vector backForms = new Vector();
   public static String stylePkg = "";
   public static int loadCounter = 0;
   public static MImage waitMImg;
   public static MImage stuffMImg = null;
   public static boolean isWaiting = false;
   public static boolean isFullUserData = false;
   public static Image cursorImg;
   public static MImage taskIconImg;
   public static int countTick;
   public static byte FLOWEREDNUM_UP = 0;
   public static byte FLOWEREDNUM_DOWN = 0;
   public static byte curUIState = -1;
   public static byte oldUIState = -1;
   public static byte curUIInnerState = -1;
   public static byte oldUIInnerState = -1;
   public static final byte STATE_UI_NONE = -1;
   public static final byte STATE_UI_CHARACTER = 0;
   public static final byte STATE_UI_BAG = 1;
   public static final byte STATE_UI_GENIUS_TREE = 2;
   public static final byte STATE_UI_GENIUS_SHORTCUTKEY = 3;
   public static final byte STATE_UI_SOCIALITY_FIND = 4;
   public static final byte STATE_UI_SOCIALITY_FRIEND = 5;
   public static final byte STATE_UI_SOCIALITY_LIST = 6;
   public static final byte STATE_UI_SOCIALITY_BLACKLIST = 7;
   public static final byte STATE_UI_SOCIALITY_CLAN = 8;
   public static final byte STATE_UI_TASK = 9;
   public static final byte STATE_UI_MAP_WORLD = 10;
   public static final byte STATE_UI_MAP_NPC = 11;
   public static final byte STATE_UI_PROCESSION = 12;
   public static final byte STATE_UI_OPTION_KEY = 13;
   public static final byte STATE_UI_OPTION_CHANNEL = 14;
   public static final byte STATE_UI_OPTION_OTHER = 15;
   public static final byte STATE_UI_SERVICE_FORUM = 16;
   public static final byte STATE_UI_SERVICE_CIMELIA = 17;
   public static final byte STATE_UI_SERVICE_CUSTOM = 18;
   public static final byte STATE_UI_SERVICE_HELP = 19;
   public static final byte STATE_UI_SERVICE_BIND = 20;
   public static final byte STATE_UI_FILLMONEY = 21;
   public static final byte STATE_UI_LEAVE = 22;
   public static final byte STATE_UI_BUY = 23;
   public static final byte STATE_UI_CHARACTER_BASIC = 0;
   public static final byte STATE_UI_CHARACTER_ATTDEF = 1;
   public static final byte STATE_UI_CHARACTER_SOCIETY = 2;
   public static final byte STATE_UI_CHARACTER_SKILL = 3;
   public static final byte STATE_UI_CHARACTER_CREDIT = 4;
   public static final byte STATE_UI_CHARACTER_RESULT = 5;
   public static byte[] uiData = null;
   public static byte[] userData = null;
   public static byte[] cmdData = null;
   public static byte[][] popData = new byte[0][];
   public static byte[] popLevel = new byte[0];
   public static byte[] partUIUserData = null;
   public static int cmdID = -1;
   public static boolean isCMWAP;
   public static boolean isAutoConn = false;
   public static String server = "221.239.75.130";
   public static boolean m_bExit = false;
   public static final int BASE_W = 176;
   public static final int BASE_H = 208;
   public static boolean isAdapte = false;
   public static byte m_bCamp = 1;
   public static byte m_bPhyle = 1;
   public static byte m_bGender = 0;
   public static byte m_bVocation = 1;
   public static String m_sName = null;
   public static byte m_bChooseCounter = 0;
   public static byte m_bChoose = 0;
   public static UIPicture[] playPicture = new UIPicture[3];
   public static UIPicture[] mailPicture = new UIPicture[6];
   public static UILabel[] label = new UILabel[5];
   public static int[] m_nCharacterID = new int[3];
   public static String[] m_sCharacterName = new String[3];
   public static int[] m_nGrade = new int[3];
   public static byte[] m_sPhyle = new byte[3];
   public static byte[] m_bProfession = new byte[3];
   public static byte[] m_bGenderX = new byte[3];
   public static String[] m_nScene = new String[3];
   public static byte[] m_bDelete = new byte[3];
   public static boolean[] m_bAmendNickname = new boolean[3];
   public static boolean isPlayerData;
   public static String[] m_sTemporaryStr = new String[4];
   public static final String DELETEPART = "del";
   public byte m_bSeed_Seizing = 0;
   public static int focus;
   public static byte m_bMailAccessoriesN = 0;
   public static UIRadio[] radio = new UIRadio[4];
   public static byte m_bFocusEnactment = 0;
   public static boolean isExitChoosePlayer = false;
   public byte[] m_ndebarkation = new byte[3];
   public boolean Debarkation = false;
   public byte[] m_bPlace;
   public byte[] m_bconfirm;
   public static byte m_bChooseRen = 0;
   public byte m_bEnterBadge = 0;
   public static byte m_bCompelDroptMeshwork = 0;
   public static byte[] save_ServerInd;
   public short[][] frameDataSpecific;
   public short[] picDatasSpecific;
   public Image imageSpecific;
   public short[][] motionDataAll;
   public static byte m_bTourist = 0;
   public static boolean m_sBingError;
   public static boolean m_sBingZi;
   public static byte m_bCelerityEnrol;
   public static boolean startTickUser = false;
   public boolean cmOutCheck = false;
   public boolean cmBuyCheck = false;
   long cmUseTime;
   long cmStartTime;
   final long CM_OUT_TIME = 6000000L;
   public static int timeTaken2;
   int waitTime;
   long beginWaitingTime;
   final long WAIT_OVER_TIME = 60000L;
   public static final long CONNECT_TIME_LIMIT = 30000L;
   static MainCanvas.mapLoading mL = null;
   public static int reponseCode = 0;
   public static String roleSaveName = null;
   public static final int SETUP_ELSE_PLAYER = 0;
   public static final int SETUP_PLAYER_NAME = 1;
   public static final int SETUP_TEAMMATE_NAME = 2;
   public static final int SETUP_MONSTER_NAME = 3;
   public static final int SETUP_BACKLIGHT = 4;
   public static short showSetup;
   public static byte pvpSetup = 1;
   public static byte quantitySetup = 1;
   public static byte weaponSpecial = 0;
   public static byte videoSetup;
   public static final int KEY_ANY = -1;
   public static final int KEY_NONE = 0;
   public static final int KEY_0 = 1;
   public static final int KEY_1 = 2;
   public static final int KEY_2 = 4;
   public static final int KEY_3 = 8;
   public static final int KEY_4 = 16;
   public static final int KEY_5 = 32;
   public static final int KEY_6 = 64;
   public static final int KEY_7 = 128;
   public static final int KEY_8 = 256;
   public static final int KEY_9 = 512;
   public static final int KEY_STAR = 1024;
   public static final int KEY_POUND = 2048;
   public static final int KEY_UP = 4096;
   public static final int KEY_DOWN = 8192;
   public static final int KEY_LEFT = 16384;
   public static final int KEY_RIGHT = 32768;
   public static final int KEY_FIRE = 65536;
   public static final int KEY_SOFT1 = 131072;
   public static final int KEY_SOFT2 = 262144;
   public static final int KEY_SOFT3 = 524288;
   public static final int KEY_CANCEL = 1048576;
   public static final int KEY_SEND = 2097152;
   public static final int KEY_RETURN = 4194304;
   public static final int KEY_PEN = 8388608;
   public static final int KEY_MENU = 16777216;
   public static final int KEY_MAIL = 33554432;
   public static final int KEY_SIDE_UP = 67108864;
   public static final int KEY_SIDE_DOWN = 134217728;
   public static final int GAME_POINTER = 268435456;
   public static final int KEY_UNKOWN = Integer.MIN_VALUE;
   public static final String[] KEY_STR = new String[]{"Phím số 0", "Phím số 1", "Phím số 2", "Phím số 3", "Phím số 4", "Phím số 5", "Phím số 6", "Phím số 7", "Phím số 8", "Phím số 9", "Phím *", "Phím # ", "Phim mũi tên Trên", "Phim mũi tên Dưới", "Phim mũi tên Trái", "Phim mũi tên Phải", "Phím xác nhận"};
   public static final int GAME_KEY_向上移动 = 0;
   public static final int GAME_KEY_向下移动 = 1;
   public static final int GAME_KEY_向左移动 = 2;
   public static final int GAME_KEY_向右移动 = 3;
   public static final int GAME_KEY_自动行走 = 4;
   public static final int GAME_KEY_攻击 = 5;
   public static final int GAME_KEY_快捷键1 = 6;
   public static final int GAME_KEY_快捷键2 = 7;
   public static final int GAME_KEY_快捷键3 = 8;
   public static final int GAME_KEY_快捷键4 = 9;
   public static final int GAME_KEY_快捷键5 = 10;
   public static final int GAME_KEY_快捷键6 = 11;
   public static final int GAME_KEY_快捷键7 = 12;
   public static final int GAME_KEY_快捷键8 = 13;
   public static final int GAME_KEY_快捷组切换 = 14;
   public static final int GAME_KEY_画质 = 15;
   public static final int GAME_KEY_聊天 = 16;
   public static final int GAME_KEY_选择所有 = 17;
   public static final int GAME_KEY_选择自身 = 18;
   public static final int GAME_KEY_选择友方 = 19;
   public static final int GAME_KEY_选择敌方 = 20;
   public static final int GAME_KEY_地图 = 21;
   public static final int GAME_KEY_寻径 = 22;
   public static final int GAME_KEY_背包 = 23;
   public static final int GAME_KEY_人物 = 24;
   public static final int GAME_KEY_队伍 = 25;
   public static final int GAME_KEY_任务 = 26;
   public static final int GAME_KEY_社交 = 27;
   public static final int GAME_KEY_天赋 = 28;
   public static final int GAME_KEY_设置 = 29;
   public static final int[] DEFAULT_GAME_KEY = new int[]{4096, 8192, 16384, 32768, 0, 65536, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1, 1024, 2048, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   public static int[] curGameKey = null;
   public static final int[] NUM_KEYS = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512};
   private static int inputState = 0;
   private static int inputDownState = 0;
   private static int inputUpState = 0;
   private static int curInputState = 0;
   public static int curInputDownState = 0;
   private static int curInputUpState = 0;
   private static boolean pointerDragState = false;
   public static short pointerX;
   public static short pointerY;
   private static boolean curPointerDragState = false;
   public static short curPointerX;
   public static short curPointerY;
   static int[] keyvalue;
   static int keyPressCode;
   public static byte helpIndex = 0;
   public static final String SERVER_BASE_STR = "nativeserver";
   public static final String ROUND = "round";
   public static boolean m_bWhetherRound = false;
   public static boolean SUPPORT_POINTER;
   Image[] imgSplash = new Image[3];
   byte frameCount = 0;
   public static boolean isLoadPlayerRes = false;
   static boolean isLoadComplete;
   public static String[] menuStr;
   public static boolean loginFirstItem;
   byte arr_Y;
   int drawPicQuaTime;
   public static UITopForm curTopForm;
   public static byte[][] topFormBuff;
   static final byte PIC_QUA_HIGHT = 0;
   static final byte PIC_QUA_MEDIAN = 1;
   static final byte PIC_QUA_LOW = 2;
   static byte picQuaInd;
   static boolean isShowPicQua;
   static boolean isSendOver;
   public static byte jumpID;
   public static final byte DEFAULT_JUMP_ID = 22;
   static byte[] humanCampVocation;
   static byte[] evilCampVocation;
   Image imgBg = null;
   Image imgWhiteBar = null;
   Image[] imgButton = new Image[2];
   final byte state_none = 0;
   final byte state_wap = 1;
   byte exitState;
   public static final String MORE_OR_EXIT_GAME_URL = "http://vibox.vn/pt";

   static {
      menuStr = new String[Cons.MAIN_MENU_STR_LOGIN.length];
      loginFirstItem = true;
      curTopForm = null;
      topFormBuff = new byte[0][];
      picQuaInd = 1;
      isShowPicQua = false;
      isSendOver = false;
      jumpID = 0;
      humanCampVocation = new byte[]{1, 2, 3, 4, 6};
      evilCampVocation = new byte[]{1, 2, 4, 6};
   }

   public MainCanvas(a midlet) {
      try {
         aMidlet = midlet;
         mc = this;
         ni = NetInterface.getInstance();
         gi = GInterface.getInstance();
         PassPort.hasNotify = false;
         this.readUserJAD();
         PassPort.imei = System.getProperty("com.nokia.mid.imei");
         StringBuffer tmp;
         if (PassPort.imei != null && !"".equals(PassPort.imei.trim())) {
            tmp = new StringBuffer();
            int i = 0;

            while(true) {
               if (i >= PassPort.imei.length()) {
                  PassPort.imei = tmp.toString();
                  break;
               }

               if (PassPort.imei.charAt(i) >= '0' && PassPort.imei.charAt(i) <= '9' || PassPort.imei.charAt(i) >= 'a' && PassPort.imei.charAt(i) <= 'z' || PassPort.imei.charAt(i) >= 'A' && PassPort.imei.charAt(i) <= 'Z') {
                  tmp.append(PassPort.imei.charAt(i));
               }

               ++i;
            }
         } else {
            PassPort.imei = String.valueOf(System.currentTimeMillis());
         }

         checkServerUrl();
         checkJumpID();

         String fromJarVer;
         try {
            tmp = null;
            fromJarVer = aMidlet.getAppProperty("isTest");
            if (fromJarVer != null && !"".equals(fromJarVer.trim())) {
               this.isTestNum = (byte)Integer.parseInt(fromJarVer.trim());
            }
         } catch (Exception var6) {
         }

         try {
            fromJarVer = aMidlet.getAppProperty("passport");
            if (fromJarVer != null && "true".equals(fromJarVer)) {
               PassPort.KONG_PASSPORT_IP = "202.108.44.149:9081";
               PassPort.KONG_URL = "/judgeserver/judge?ver=2&";
               PassPort.VER = 2;
            }
         } catch (Exception var5) {
         }

         try {
            PassPort.fidCode = aMidlet.getAppProperty("fid");
            if (PassPort.fidCode == null || "".equals(PassPort.fidCode.trim())) {
               PassPort.fidCode = "1000";
            }
         } catch (Exception var8) {
         }

         this.initSysValue();

         try {
            fromJarVer = aMidlet.getAppProperty("Ver");
            if (fromJarVer != null && !fromJarVer.equals("") && this.transformVerStr(fromJarVer) != 0) {
               GameLogin.m_nVersion = this.transformVerStr(fromJarVer.trim());
            }
         } catch (Exception var4) {
         }

         try {
            jarFrom = aMidlet.getAppProperty("JarSrc");
            if (jarFrom != null && !jarFrom.equals("")) {
               jarFrom = jarFrom.trim();
            } else {
               jarFrom = "kong";
            }
         } catch (Exception var7) {
         }

         Util.loadOption();
      } catch (Exception var9) {
      }

      if (screenW == 240 && screenH == 320) {
         FLOWEREDNUM_UP = 4;
         FLOWEREDNUM_DOWN = 8;
      } else {
         FLOWEREDNUM_UP = 2;
         FLOWEREDNUM_DOWN = 5;
      }

      setState((byte)3);
   }

   public synchronized void begin() {
      this.self = new Thread(this);
      this.self.start();
   }

   public synchronized void stop() {
      this.self = null;
   }

   public void run() {
      this.waitTime = UIForm.getWait();
      Thread currentThread = Thread.currentThread();

      while(currentThread == this.self) {
         try {
            long startTime1 = getTimeByEmulatorType();
            getCurInput();
            synchronized(this) {
               ni.deCode();
               if (!ni.sendInfo()) {
                  this.stop();
               }

               if (isSendOver) {
                  isSendOver = false;
                  NetInterface.getInstance().closeConn();
               }
            }

            if (isinM && getState() == 2) {
               isinM = false;
            }

            this.paintTaken += 120L;
            this.tick();
            this.repaint();
            if (!startTickUser) {
               this.cmStartTime = System.currentTimeMillis();
            } else {
               this.cmUseTime = System.currentTimeMillis() - this.cmStartTime;
               if (this.cmUseTime > 6000000L) {
                  userID = "";
                  startTickUser = false;
                  this.cmOutCheck = true;
                  PassPort.checkChinaMobileConnect();
               }
            }

            if (this.isBreak && getState() == 5) {
               this.quake();
            }

            timeTaken = (int)(getTimeByEmulatorType() - startTime1);
            synchronized(this) {
               this.wait((long)Math.max(1, this.waitTime - timeTaken));
            }

            timeTaken2 = (int)(getTimeByEmulatorType() - startTime1);
            if (emulatorType != 0 && timeTaken2 < this.waitTime) {
               this.waitTime = this.waitTime * UIForm.getWait() / timeTaken2;
            }
         } catch (Exception var7) {
         }
      }

   }

   private void tick() {
      if (PassPort.waitActive) {
         System.out.println("MainCanvas.tick(): " + (System.currentTimeMillis() - activeStartTime));
         if (activeStartTime > 0L && System.currentTimeMillis() - activeStartTime >= 1000L) {
            PassPort pp = new PassPort(PassPort.CM_CHARGE_IP, "", (byte)(isCMWAP ? 0 : 1), (byte)3);
            pp.authenticate((byte)31);
            if (System.currentTimeMillis() - activeStartTime > 12000L) {
               PassPort.waitActive = false;
               TopForm((byte)0, "Kích hoạt thất bại, vui lòng kiểm tra lại.", "", "Đóng", -2, -1);
            }
         }
      }

      ++countTick;
      if (countTick >= 6000) {
         countTick = 0;
      }

      ++Util.sCount;
      this.UIRecieve();
      if (GameLogin.errorLoginResult) {
         String str;
         if (GameLogin.login_server_result == 1) {
            str = "Tài khoản hoặc mật khẩu không đúng, hãy nhập lại";
            TopForm((byte)0, str, Cons.C_STR[2], "", -6, -120);
         } else if (GameLogin.login_server_result == 2) {
            str = "Client và phiên bản server không khớp";
            TopForm((byte)0, str, Cons.C_STR[2], "", -6, -120);
         } else if (GameLogin.login_server_result == 3 || GameLogin.login_server_result == 4) {
            str = "Giấy thông hành chưa kiểm chứng";
            TopForm((byte)0, str, Cons.C_STR[2], "", -6, -120);
         }

         GameLogin.errorLoginResult = false;
      }

      if ((getState() == 5 || getState() == 17 || getState() == 18 || getState() == 19 || getState() == 27) && countTick % 50 == 0) {
         ni.send(3);
      }

      NPC.npcTopTipTick();
      this.taskTimeTick();
      this.validateTick();
      if (isFullClose) {
         setState((byte)12);
         isFullClose = false;
         if (m_bCelerityEnrol == 1) {
            m_bCelerityEnrol = 0;
         }
      }

      if (curForm != null) {
         Map.adjustWorldMapWindow(curForm.clientCommand);
      }

      this.touchScreenAction();
      switch(getState()) {
      case 4:
         this.keyInMenu();
         break;
      case 5:
         changeGameState = false;
         this.mapRunSubTick();
         SIManager.getInstance().tick();
         if (PCHang.m_bHang) {
            if (getGameState() == 3 && curTopForm == null) {
               UITitle.localChatTick();
               UIChat.chatForm.keyInAction();
            } else if (getGameState() == 4 && curTopForm == null) {
               curForm.keyInForm();
               UITitle.localChatTick();
            } else if (curTopForm != null) {
               curTopForm.keyInTopForm();
            } else if (rightMenu != null) {
               rightMenu.keyInAction();
            } else if (!isInputDown(1024) && !this.HangTouch()) {
               if (!isInputDown(1024) && isInputDown(-1)) {
                  ni.send(3145734);
               }
            } else {
               UIRightMenu.setRightMenuNum(71);
            }
         } else {
            this.keyInGameRun();
         }
         break;
      case 6:
      case 9:
      case 10:
      case 13:
      case 20:
      case 24:
      case 25:
      case 26:
      case 27:
      case 29:
      case 38:
      case 39:
      default:
         if (curForm != null) {
            curForm.keyInForm();
            UITitle.localChatTick();
         }
         break;
      case 7:
         if (isChinaMobileVer) {
            this.keyInExit();
         }
         break;
      case 8:
         if (keyPressCode != 0) {
            if (!SUPPORT_POINTER) {
            }

            KeyConfig.keyInNetSet(keyPressCode);
            keyPressCode = 0;
         }
         break;
      case 11:
      case 12:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 21:
      case 22:
      case 23:
      case 30:
      case 31:
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case 37:
      case 40:
         if (curForm != null && curTopForm != null) {
            curTopForm.keyInTopForm();
         } else if (curForm != null && curTopForm == null) {
            curForm.keyInForm();
            UITitle.localChatTick();
         }

         this.checkConnectOvertime();
         break;
      case 28:
         if (keyPressCode != 0) {
            KeyConfig.keyInConfig(keyPressCode);
            keyPressCode = 0;
         }

         KeyConfig.tick();
      }

      keyPressCode = 0;
      if (curForm != null && curTopForm == null) {
         if (curForm.clientCommand == 196641) {
            UITextArea textarea = (UITextArea)curForm.getComponents().elementAt(3);
            if (countTick % 300 == 1 && !GOManager.m_bPlayersortNPC && textarea.m_bWhetherTime) {
               UITopForm.createLocalTopForm((byte)0, (String)"Xác nhận muốn thoát khỏi chiến trường？", "Xác nhận", "Hủy", 852005, -1);
            }
         } else if (curForm.clientCommand == 196642) {
            UITable table = (UITable)curForm.getComponents().elementAt(3);
            if (countTick % 300 == 1 && !GOManager.m_bPlayersortNPC && table.m_bWhetherTime) {
               UITopForm.createLocalTopForm((byte)0, (String)"Xác nhận muốn thoát khỏi chiến trường？", "Xác nhận", "Hủy", 852005, -1);
            }
         }
      }

      this.checkWaiting();
   }

   void checkWaiting() {
      if (isWaiting) {
         if (this.beginWaitingTime > 0L) {
            long useTime = System.currentTimeMillis() - this.beginWaitingTime;
            if (useTime >= 60000L) {
               isWaiting = false;
               this.beginWaitingTime = 0L;
               UITopForm.createLocalTopForm((byte)0, (String)"Liên kết server quá hạn, hãy thử lại", "Xác nhận", "", -1, -2);
            }
         } else {
            this.beginWaitingTime = System.currentTimeMillis();
         }
      } else {
         this.beginWaitingTime = 0L;
      }

   }

   public void checkConnectOvertime() {
      if (getState() == 11 && isAutoConn && !PassPort.isConnectOK && UIForm.conStartTime != 0L) {
         long connectingTime = System.currentTimeMillis() - UIForm.conStartTime;
         if (connectingTime > 30000L) {
            curForm.loginPP.isInConnecting = true;
            UIForm.conStartTime = 0L;
            curForm.loginPP.setConnectState((byte)0);
            PassPort pp = new PassPort(PassPort.KONG_PASSPORT_IP, PassPort.getURL((byte)1), (byte)1, (byte)2);
            pp.authenticate((byte)1);
         }
      }

   }

   public static void addScrollStr() {
      if (curForm != null) {
         switch(curForm.clientCommand) {
         case 1376263:
            curForm.addWarningStr("Ấn phím * chọn vật phẩm, ấn phím # xác nhận đặt vào.");
            break;
         case 1703946:
            curForm.addWarningStr("Ấn phím * chọn vật phẩm đính kèm, ấn phím # xác nhận đặt vào");
         }
      }

   }

   public static void startLoadingProgress() {
      mL = new MainCanvas.mapLoading();
   }

   public static void endLoadingProgress() {
      if (mL != null) {
         mL.stop();
         mL = null;
      }

   }

   public void paint(Graphics g) {
      try {
         g.setColor(16777215);
         g.fillRect(0, 0, screenW, screenH);
         g.setFont(curFont);
         switch(getState()) {
         case 2:
            this.drawLoading(g);
            break;
         case 3:
            this.drawSplash(g);
            break;
         case 4:
            this.drawMainCover(g);
            this.drawSplashMenu(g);
            this.drawUITopForm(g);
            break;
         case 5:
            this.drawGameRun(g);
            break;
         case 6:
         case 8:
         case 9:
         case 10:
         case 13:
         case 20:
         case 25:
         case 26:
         case 27:
         case 29:
         case 38:
         case 39:
         default:
            if (curForm != null) {
               curForm.draw(g);
            }
            break;
         case 7:
            if (isChinaMobileVer) {
               this.drawExit(g);
            }
            break;
         case 11:
         case 12:
         case 14:
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
         case 21:
         case 22:
         case 23:
         case 30:
         case 31:
         case 32:
         case 33:
         case 34:
         case 35:
         case 36:
         case 37:
         case 40:
            if (curForm != null) {
               curForm.draw(g);
               this.drawUITopForm(g);
            }
            break;
         case 24:
            this.drawGameLoading(g);
            break;
         case 28:
            this.drawKeyset(g);
         }

         if (getState() == 5 && isWaiting) {
            this.drawWaiting(g);
         } else if (getState() == 21 || getState() == 22 || getState() == 23 || getState() == 5 && m_sBingZi) {
            this.drawSeizing(g);
         }

         int drawY = true;
         g.setColor(255);
      } catch (Exception var3) {
      }

   }

   private void drawWaiting(Graphics g) {
      if (waitMImg != null) {
         if (loadCounter < 3) {
            waitMImg.draw(g, screenW - 36, 36, loadCounter);
         } else {
            waitMImg.draw(g, screenW - 36, 36, 1, 2);
         }
      }

      ++loadCounter;
      if (loadCounter > 3) {
         loadCounter = 0;
      }

   }

   private static final int getKeyValue(int keyCode) {
      if (keyvalue != null) {
         if (keyCode == keyvalue[0]) {
            return 131072;
         }

         if (keyCode == keyvalue[1]) {
            return 262144;
         }

         if (keyCode == keyvalue[2]) {
            return 65536;
         }
      }

      switch(keyCode) {
      case -50:
         return 8388608;
      case -31:
         return 134217728;
      case -30:
      case -9:
         return 67108864;
      case -23:
      case 23:
         return 16777216;
      case -22:
      case -7:
      case 22:
         return 262144;
      case -21:
      case -6:
      case 21:
         return 131072;
      case -20:
      case -5:
      case 20:
         return 65536;
      case -12:
         return 33554432;
      case -11:
         return 4194304;
      case -10:
      case 10:
         return 2097152;
      case -8:
         return 135266304;
      case -4:
      case 5:
         return 32768;
      case -3:
      case 2:
         return 16384;
      case -2:
      case 6:
         return 8192;
      case -1:
      case 1:
         return 4096;
      case 35:
         return 2048;
      case 42:
         return 1024;
      case 48:
         return 1;
      case 49:
         return 2;
      case 50:
         return 4;
      case 51:
         return 8;
      case 52:
         return 16;
      case 53:
         return 32;
      case 54:
         return 64;
      case 55:
         return 128;
      case 56:
         return 256;
      case 57:
         return 512;
      default:
         return Integer.MIN_VALUE;
      }
   }

   public static void keySimPressed(int Code) {
      curInputDownState |= Code;
      curInputState |= Code;
   }

   public static void keySimReleased(int Code) {
      curInputUpState |= Code;
      curInputDownState &= ~Code;
      curInputState &= ~Code;
   }

   public void keyPressed(int keyCode) {
      keyPressCode = keyCode;
      int Code = getKeyValue(keyCode);
      inputDownState |= Code;
      inputState |= Code;
   }

   public void keyReleased(int keyCode) {
      int Code = getKeyValue(keyCode);
      inputUpState |= Code;
      inputState &= ~Code;
   }

   protected final void pointerPressed(int x, int y) {
      if (SUPPORT_POINTER) {
         inputDownState |= 268435456;
         inputState |= 268435456;
         pointerDragState = false;
         pointerX = (short)x;
         pointerY = (short)y;
      }

   }

   protected final void pointerReleased(int x, int y) {
      if (SUPPORT_POINTER) {
         inputUpState |= 268435456;
         inputState &= -268435457;
         pointerX = (short)x;
         pointerY = (short)y;
      }

   }

   protected final void pointerDragged(int x, int y) {
      if (SUPPORT_POINTER) {
         pointerDragState = true;
         pointerX = (short)x;
         pointerY = (short)y;
      }

   }

   private static final void getCurInput() {
      curInputDownState = inputDownState;
      curInputState = inputState;
      curInputUpState = inputUpState;
      inputDownState = 0;
      inputUpState = 0;
      if (mL != null || isWaiting && Player.getInstance().getState() != 9) {
         clearCurInput();
      }

      if (SUPPORT_POINTER) {
         curPointerDragState = pointerDragState;
         curPointerX = pointerX;
         curPointerY = pointerY;
      }

   }

   public static final void clearCurInput() {
      inputDownState = 0;
      inputState = 0;
      inputUpState = 0;
      curInputDownState = 0;
      curInputState = 0;
      curInputUpState = 0;
      if (SUPPORT_POINTER) {
         pointerDragState = false;
         curPointerDragState = false;
      }

   }

   public static final boolean isInputDown(int theInput) {
      return (curInputDownState & theInput) != 0;
   }

   public static final boolean isInputUp(int theInput) {
      return (curInputUpState & theInput) != 0;
   }

   public static final boolean isInputHold(int theInput) {
      return (curInputState & theInput) != 0;
   }

   public static final boolean isInputDownOrHold(int theInput) {
      return ((curInputDownState | curInputState) & theInput) != 0;
   }

   public static final boolean isPointerDrag() {
      return SUPPORT_POINTER ? curPointerDragState : false;
   }

   public static final boolean pointDownInRect(int rectX, int rectY, int rectWidth, int rectHeight) {
      if (SUPPORT_POINTER) {
         return isInputDown(268435456) && isPointInRect(rectX, rectY, rectWidth, rectHeight);
      } else {
         return false;
      }
   }

   public static final boolean pointHoldInRect(int rectX, int rectY, int rectWidth, int rectHeight) {
      if (SUPPORT_POINTER) {
         return isInputHold(268435456) && isPointInRect(rectX, rectY, rectWidth, rectHeight);
      } else {
         return false;
      }
   }

   public static final boolean pointDownOrHoldInRect(int rectX, int rectY, int rectWidth, int rectHeight) {
      if (SUPPORT_POINTER) {
         return isInputDownOrHold(268435456) && isPointInRect(rectX, rectY, rectWidth, rectHeight);
      } else {
         return false;
      }
   }

   public static final boolean isPointInRect(int rectX, int rectY, int rectWidth, int rectHeight) {
      return curPointerX >= rectX && curPointerX <= rectX + rectWidth && curPointerY >= rectY && curPointerY <= rectY + rectHeight;
   }

   protected void hideNotify() {
      clearCurInput();
   }

   public static final void setState(byte s) {
      oldState = curState;
      curState = s;
      if (curState == 5) {
         setCurForm((UIForm)null);
      }

      UIList helpList;
      String resultStr;
      switch(curState) {
      case 4:
         if (curTopForm != null) {
            curTopForm = null;
         }

         if (Cover.comp == null) {
            Cover.getInstance().backMenu();
         }
         break;
      case 5:
         setGameState((byte)0);
         break;
      case 6:
         setUI("/ui/list.ui");
         UITitle helpTitle = (UITitle)curForm.getComponents().elementAt(0);
         helpTitle.setTitleText("帮　助");
         helpList = (UIList)curForm.getComponents().elementAt(2);
         helpList.removeAll();

         for(int i = 0; i < Cons.strHelpTitles.length; ++i) {
            helpList.addItem(new ListItem(Cons.strHelpTitles[i], (byte)0, (byte)0, helpList.getListWidth()));
         }
      case 7:
      case 9:
      case 13:
      case 20:
      case 24:
      case 25:
      case 26:
      case 28:
      case 39:
      default:
         break;
      case 8:
         KeyConfig.clear();
         setUI("/ui/networksetup.ui");
         UIRadio radio_gamemount = (UIRadio)curForm.getComponents().elementAt(2);
         byte[] connectType = Util.loadByteRecord("gamemount");
         if (connectType == null) {
            connectType = new byte[1];
            KeyConfig.selectNet = 2;
         } else {
            KeyConfig.selectNet = connectType[0];
         }

         KeyConfig.radioSet();
         if (!SUPPORT_POINTER) {
         }

         UILabel label_1 = (UILabel)curForm.getComponents().elementAt(3);
         UILabel label_2 = (UILabel)curForm.getComponents().elementAt(4);
         UILabel label_3 = (UILabel)curForm.getComponents().elementAt(5);
         label_1.setText("");
         label_2.setText("");
         label_3.setText("");
         break;
      case 10:
         setUI("/ui/about.ui");
         UITextArea textarea = (UITextArea)curForm.getComponents().elementAt(3);
         textarea.addUIScroll();
         break;
      case 11:
         if (curTopForm != null) {
            curTopForm = null;
         }

         setUI("/ui/debarkation.ui");
         UITextField userNameText = (UITextField)curForm.getComponents().elementAt(4);
         UITitle menuBar = (UITitle)curForm.getComponents().elementAt(1);
         name_password();
         loadLoginType();
         if (userNameText.getSb().toString().trim() != null && !userNameText.getSb().toString().trim().equals("")) {
            menuBar.setMenuText("Đăng nhập", 0);
         } else {
            menuBar.setMenuText("Nhập vào", 0);
         }

         UILabel loginLabel = (UILabel)curForm.getComponents().elementAt(3);
         loginLabel.setPositionX((short)(screenW >> 1));
         loginLabel.setStrCenterXAnchor(true);
         UIRadio r = (UIRadio)curForm.getComponents().elementAt(10);
         r.setPositionX((short)(screenW - r.getWidth() >> 1));

         for(int i = 0; i < 4; ++i) {
            UILabel fL = (UILabel)curForm.getComponents().elementAt(6 + i);
            fL.setPositionX((short)(screenW >> 1));
            fL.setStrCenterXAnchor(true);
         }

         if (isChinaMobileVer) {
            curForm.getComponents().removeElementAt(9);
         }
         break;
      case 12:
         UITopForm.removeCurTopForm();
         setUI("/ui/list.ui");
         UIList serverList = (UIList)curForm.getComponents().elementAt(2);
         serverList.removeAll();
         int i;
         if (!PassPort.isLocalServerList) {
            if (PassPort.serverListContent == null || PassPort.serverListContent[0][0] == null) {
               setState((byte)4);
               return;
            }

            logoimg = null;
            Cover.clear();

            for(i = 0; i < PassPort.serverListContent.length; ++i) {
               serverList.addItem(new ListItem(PassPort.serverListContent[i][0], (byte)0, (byte)0, serverList.getListWidth()));
            }

            UITitle title = (UITitle)curForm.getComponents().elementAt(0);
            title.setTitleText("Hãy chọn server");
         } else {
            save_ServerInd = Util.loadByteRecord("serverlist", 1);
            if (save_ServerInd == null) {
               save_ServerInd = new byte[1];
            }

            if (servername == null) {
               setState((byte)4);
               return;
            }

            logoimg = null;
            Cover.clear();

            for(i = 0; i < servername.length; ++i) {
               serverList.addItem(new ListItem(servername[i], (byte)0, (byte)0, serverList.getListWidth()));
            }
         }

         if (serverList.getItems().size() > serverList.getCanShowCol((byte)0) && serverList.getPanel().getPanelVScroll() == null) {
            serverList.getPanel().addPanelVScrollBar(false);
         }

         serverList.getPanel().addUIScroll((short)serverList.getItems().size(), serverList.getCanShowCol((byte)0));
         if (PassPort.isLocalServerList) {
            for(short i = 0; i < save_ServerInd[0]; ++i) {
               keySimPressed(8192);
               serverList.keyInAction();
               keySimReleased(8192);
            }
         }
         break;
      case 14:
         setUI("/ui/login.ui");
         UITextField userNameText_1 = (UITextField)curForm.getComponents().elementAt(9);
         UITextField userPswText_1 = (UITextField)curForm.getComponents().elementAt(10);
         if (PassPort.isQuick) {
            userNameText_1.setSb(new StringBuffer(PassPort.suggestion));
            userPswText_1.setSb(new StringBuffer(PassPort.suggestion));
            name_password[0] = userNameText_1.getSb().toString();
            name_password[1] = userPswText_1.getSb().toString();
            PassPort.isQuick = false;
         }

         UITitle menuBar_1 = (UITitle)curForm.getComponents().elementAt(1);
         if (userNameText_1.getSb().toString().trim() != null && !userNameText_1.getSb().toString().trim().equals("")) {
            menuBar_1.setMenuText("Xác nhận", 0);
         } else {
            menuBar_1.setMenuText("Nhập vào", 0);
         }
         break;
      case 15:
         setUI("/ui/amendcipher.ui");
         UITextField userNameText_2 = (UITextField)curForm.getComponents().elementAt(10);
         UITitle menuBar_2 = (UITitle)curForm.getComponents().elementAt(1);
         if (userNameText_2.getSb().toString().trim() != null && !userNameText_2.getSb().toString().trim().equals("")) {
            menuBar_2.setMenuText("Xác nhận", 0);
         } else {
            menuBar_2.setMenuText("Nhập vào", 0);
         }
         break;
      case 16:
         setUI("/ui/seizing.ui");
         UIList List = (UIList)curForm.getComponents().elementAt(3);
         byte listIndex = List.getSelectedIndex();
         if (List.startIndex < 0) {
            List.startIndex = 0;
         }

         if (List.getItems().size() > List.getCanShowCol((byte)0) && List.getPanel().getPanelVScroll() == null) {
            List.getPanel().addPanelVScrollBar(false);
         }

         UIList.addUIScroll(List, (byte)0);
         UITextArea area = (UITextArea)curForm.getComponents().elementAt(4);
         area.setContent(Cons.SEIZING_EXPLAIN[0]);
         break;
      case 17:
         if (!PassPort.isRequestGPlusURL) {
            ni.send(2425014);
            PassPort.isRequestGPlusURL = true;
         }

         ni.send(65557);
         ni.send(65558);
         if (loginType != null && loginType[0] == 1) {
            ni.send(65568);
         }
         break;
      case 18:
         setUI("/ui/found_role.ui");

         int i;
         for(i = 0; i < 5; ++i) {
            UIRadio.m_bIndex[i] = 0;
         }

         for(i = 0; i < 4; ++i) {
            UIRadio r1 = (UIRadio)curForm.getComponents().elementAt(5 + i);
            r1.setPositionX((short)(r1.getPositionX() - 30));
         }

         UITextField tf = (UITextField)curForm.getComponents().elementAt(9);
         tf.setPositionX((short)(tf.getPositionX() - 30));
         UITitle menuBar_7 = (UITitle)curForm.getComponents().elementAt(1);
         menuBar_7.setMenuText("Giới thiệu", 0);
         UIPicture picture = (UIPicture)curForm.getComponents().elementAt(3);
         playPicture[0] = picture;
         playPicture[0].otherP = new OtherPlayer((short)0, (short)0, m_bPhyle, m_bVocation, m_bGender);
         Equip(true);
         if (SUPPORT_POINTER) {
            UILabel confirm = (UILabel)curForm.getComponents().elementAt(4);
            confirm.setText("Nhấn vào đây chọn nhân vật");
            confirm.setPositionX((short)(screenW - confirm.getWidth() >> 1));
         }

         UITextArea textarea_1 = (UITextArea)curForm.getComponents().elementAt(10);
         textarea_1.setContent(Cons.SIMPLENESS_INTRODUCE[m_bCamp - 1]);
         break;
      case 19:
         setUI("/ui/delete_part.ui");
         Character((byte)10);
         UITextField TextField = (UITextField)curForm.getComponents().elementAt(9);
         UITitle menuBar_6 = (UITitle)curForm.getComponents().elementAt(1);
         if (TextField.getSb().toString().trim() != null && !TextField.getSb().toString().trim().equals("")) {
            menuBar_6.setMenuText("Xác nhận", 0);
         } else {
            menuBar_6.setMenuText("Nhập vào", 0);
         }
         break;
      case 21:
         setUI("/ui/seed_seizing.ui");
         UITextField text = (UITextField)curForm.getComponents().elementAt(4);
         UITitle menuBar_3 = (UITitle)curForm.getComponents().elementAt(1);
         if (text.getSb().toString().trim() != null && !text.getSb().toString().trim().equals("")) {
            menuBar_3.setMenuText("Xác nhận", 0);
         } else {
            menuBar_3.setMenuText("Nhập vào", 0);
         }
         break;
      case 22:
         setUI("/ui/seed_colligation.ui");
         UITextField text_1 = (UITextField)curForm.getComponents().elementAt(4);
         UITitle menuBar_4 = (UITitle)curForm.getComponents().elementAt(1);
         if (text_1.getSb().toString().trim() != null && !text_1.getSb().toString().trim().equals("")) {
            menuBar_4.setMenuText("Xác nhận", 0);
         } else {
            menuBar_4.setMenuText("Nhập vào", 0);
         }
         break;
      case 23:
         setUI("/ui/seed_password.ui");
         UITextField text_2 = (UITextField)curForm.getComponents().elementAt(4);
         UITitle menuBar_5 = (UITitle)curForm.getComponents().elementAt(1);
         if (text_2.getSb().toString().trim() != null && !text_2.getSb().toString().trim().equals("")) {
            menuBar_5.setMenuText("Xác nhận", 0);
         } else {
            menuBar_5.setMenuText("Nhập vào", 0);
         }
         break;
      case 27:
         setUI("/ui/introduce.ui");
         break;
      case 29:
         helpList = (UIList)curForm.getComponents().elementAt(2);
         setUI("/ui/about.ui");
         UITitle contentTitle = (UITitle)curForm.getComponents().elementAt(0);
         helpIndex = helpList.getSelectedIndex();
         contentTitle.setTitleText(Cons.strHelpTitles[helpIndex]);
         UITextArea content = (UITextArea)curForm.getComponents().elementAt(3);
         content.setContent(Cons.strHelpContentMainMenu[helpIndex]);
         content.addUIScroll();
         break;
      case 30:
         setUI("/ui/list.ui");
         UITitle pointsTitle = (UITitle)curForm.getComponents().elementAt(0);
         pointsTitle.setTitleText("Chuyên khu điểm số");
         UIList pointsList = (UIList)curForm.getComponents().elementAt(2);
         pointsList.removeAll();

         for(int i = 0; i < Cons.strPointsTitles.length; ++i) {
            pointsList.addItem(new ListItem(Cons.strPointsTitles[i], (byte)0, (byte)0, pointsList.getListWidth()));
         }

         return;
      case 31:
         setUI("/cm/ui/charge.ui");
         UITextField chargeText = (UITextField)curForm.getComponents().elementAt(5);
         chargeText.setMaxNumber(100L);
         break;
      case 32:
         setUI("/cm/ui/charger.ui");
         resultStr = "";
         if (PassPort.chargeSuccessful) {
            resultStr = "Chúc mừng, nạp thẻ" + PassPort.chargeNum + "thành công, nhận" + PassPort.chargeNum * 100 + "điểm! Số điểm hiện tại là " + PassPort.chargeRemain + "điểm!";
         } else {
            resultStr = "Nạp thẻ thất bại, hãy thử lại hoặc liên hệ với server!";
         }

         UITextArea chargeArea = (UITextArea)curForm.getComponents().elementAt(3);
         chargeArea.setContent(resultStr);
         break;
      case 33:
         setUI("/cm/ui/remain.ui");
         resultStr = "";
         if (PassPort.chargeSuccessful) {
            resultStr = "Số điểm hiện tại là " + PassPort.chargeRemain + "điểm!";
         } else {
            resultStr = "Tra tìm thất bại, hãy thử lại hoặc liên hệ server!";
         }

         UITextArea remainArea = (UITextArea)curForm.getComponents().elementAt(3);
         remainArea.setContent(resultStr);
         break;
      case 34:
         setUI("/cm/ui/detail.ui");
         resultStr = "";
         if (PassPort.chargeSuccessful) {
            resultStr = "Số điểm hiện tại là " + PassPort.chargeRemain + "điểm!";
         } else {
            resultStr = "Tra tìm thất bại, hãy thử lại hoặc liên hệ server!";
         }

         UITextArea detailArea = (UITextArea)curForm.getComponents().elementAt(3);
         detailArea.setContent(resultStr);
         break;
      case 35:
         setUI("/cm/ui/pcrecord.ui");
         break;
      case 36:
         setUI("/cm/ui/pbrecord.ui");
         break;
      case 37:
         setUI("/cm/ui/phelp.ui");
         break;
      case 38:
         setUI("/cm/ui/result.ui");
         UITextArea resultArea = (UITextArea)curForm.getComponents().elementAt(3);
         resultArea.setContent(PassPort.history);
         resultArea.addUIScroll();
         break;
      case 40:
         setUI("/ui/recharge.ui");
         UITitle pageTitle = (UITitle)curForm.getComponents().elementAt(0);
         pageTitle.setTitleText(PassPort.rechardType == 1 ? "Nạp nguyên bảo" : "Nạp mãnh mã");
         UIList list = (UIList)curForm.getComponents().elementAt(2);
         list.removeAll();
         int i;
         if (!PassPort.ptmSMSHeader.equals("*")) {
            i = Util.split(PassPort.ptmSMSHeader, "-").length;
            String[] res = new String[i];
            res = Util.split(PassPort.ptmSMSHeader, "-");

            for(int i = 0; i < i; ++i) {
               String title = "";
               String header = res[i].substring(0, 4);
               if (header.equals("8200")) {
                  title = "2000";
               } else if (header.equals("8300")) {
                  title = "3000";
               } else if (header.equals("8400")) {
                  title = "4000";
               } else if (header.equals("8500")) {
                  title = "5000";
               } else if (header.equals("8600")) {
                  title = "10000";
               } else if (header.equals("8700") || header.equals("8713")) {
                  title = "15000";
               }

               ListItem item = new ListItem(title, (byte)0, (byte)0, list.getListWidth());
               item.itemId = Integer.parseInt(header);
               list.addItem(item);
            }
         }

         for(i = 0; i < Cons.SMS_HEADER_STR.length; ++i) {
            ListItem item = new ListItem(Cons.SMS_HEADER_STR[i], (byte)0, (byte)0, list.getListWidth());
            item.itemId = -100;
            item.setItemName(Cons.SMS_HEADER_STR[i]);
            list.addItem(item);
         }

         list.serverType = 86;
         UIList.addUIScroll(list, (byte)0);
      }

   }

   public static void setUI(String path) {
      if (curForm != null) {
         curForm.clear();
         curForm = null;
         curFormVector.removeAllElements();
      }

      curForm = ParseUI.parseUI(path);
   }

   public static final byte getState() {
      return curState;
   }

   public void initSysValue() {
      SUPPORT_POINTER = this.hasPointerMotionEvents();
      this.setFullScreenMode(true);
      screenW = this.getWidth();
      screenH = this.getHeight();
      if (screenH >= 300) {
         isLargeScreen = true;
      } else {
         isLargeScreen = false;
      }

      curFont = Font.getFont(0, 0, 8);
      CHARH = curFont.getHeight();
      CHARW = curFont.stringWidth("Đúng");
      String tmpStr = aMidlet.getAppProperty("nativeserver");
      if (tmpStr != null && !tmpStr.equals("")) {
         if (tmpStr.equals("true")) {
            PassPort.isLocalServerList = true;
         } else if (tmpStr.equals("false")) {
            PassPort.isLocalServerList = false;
         }
      } else {
         PassPort.isLocalServerList = false;
      }

      PassPort.isLocalServerList = true;
      String roundStr = aMidlet.getAppProperty("round");
      if (roundStr != null && !roundStr.equals("")) {
         if (roundStr.equals("true")) {
            m_bWhetherRound = true;
         } else if (roundStr.equals("false")) {
            m_bWhetherRound = false;
         }
      } else {
         m_bWhetherRound = false;
      }

      keyvalue = Util.loadIntRecord("keyValue", KeyConfig.keySet.length);
      PassPort.initSysDate();
   }

   public final void changSave(String oldName, String newName) {
      String gameKeySaveStr = "gameKey" + oldName;
      int[] tmpGameKey = Util.loadIntRecord(gameKeySaveStr, DEFAULT_GAME_KEY.length);
      Util.deleteRecord(gameKeySaveStr);
      gameKeySaveStr = "gameKey" + newName;
      Util.saveIntRecord(tmpGameKey, gameKeySaveStr);
   }

   public final void initSave(String name) {
      roleSaveName = name;
      String GameKeySaveStr = "gameKey" + roleSaveName;

      int i;
      try {
         curGameKey = Util.loadIntRecord(GameKeySaveStr, DEFAULT_GAME_KEY.length);
      } catch (Exception var10) {
         curGameKey = Util.loadIntRecord(GameKeySaveStr, DEFAULT_GAME_KEY.length - 1);
         int[] newGameKey = new int[DEFAULT_GAME_KEY.length];
         int i = 0;

         for(i = DEFAULT_GAME_KEY.length; i < i; ++i) {
            if (i < 4) {
               newGameKey[i] = curGameKey[i];
            } else if (i == 4) {
               newGameKey[i] = DEFAULT_GAME_KEY[4];
            } else {
               newGameKey[i] = curGameKey[i - 1];
            }
         }

         curGameKey = new int[DEFAULT_GAME_KEY.length];
         curGameKey = newGameKey;
      }

      if (curGameKey == null) {
         Util.saveIntRecord(DEFAULT_GAME_KEY, GameKeySaveStr);
         curGameKey = Util.loadIntRecord(GameKeySaveStr, DEFAULT_GAME_KEY.length);
      }

      short[] defaultVal = new short[]{1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      short tmpSetup = 0;

      try {
         short[] tmpShort = Util.loadShortRecord("showSetup", 2);
         if (tmpShort == null) {
            picQuaInd = 1;

            for(i = 0; i < 5; ++i) {
               tmpSetup = (short)(tmpSetup | defaultVal[i] << i);
            }

            showSetup = tmpSetup;
            Util.saveShortRecord(new short[]{tmpSetup, picQuaInd}, "showSetup");
         } else {
            showSetup = Util.loadShortRecord("showSetup", 2)[0];
            picQuaInd = (byte)Util.loadShortRecord("showSetup", 2)[1];
         }
      } catch (Exception var9) {
         picQuaInd = 1;

         for(i = 0; i < 5; ++i) {
            tmpSetup = (short)(tmpSetup | defaultVal[i] << i);
         }

         showSetup = tmpSetup;
         Util.saveShortRecord(new short[]{tmpSetup, picQuaInd}, "showSetup");
      }

      setShowSetup();

      try {
         byte[] tmpQuantity = Util.loadByteRecord("quantitySetup", 1);
         if (tmpQuantity == null) {
            quantitySetup = 1;
            Util.saveByteRecord(new byte[]{quantitySetup}, "quantitySetup");
         } else {
            quantitySetup = Util.loadByteRecord("quantitySetup")[0];
         }
      } catch (Exception var8) {
         quantitySetup = 1;
         Util.saveByteRecord(new byte[]{quantitySetup}, "quantitySetup");
      }

      SIManager.getInstance();
      SIManager.shortCut.refreshShortCutAll();

      try {
         String pvpRMS = "pvpSetup" + roleSaveName;
         byte[] tmpQuantity = Util.loadByteRecord(pvpRMS, 1);
         if (tmpQuantity == null) {
            if (Player.getInstance().getLevel() >= 20) {
               pvpSetup = 0;
            } else {
               pvpSetup = 1;
            }

            Util.saveByteRecord(new byte[]{pvpSetup}, pvpRMS);
         } else {
            pvpSetup = tmpQuantity[0];
         }
      } catch (Exception var7) {
      }

   }

   public static boolean reFreshSkill(int[] tmpSkill, String[] tmpName, String cutStr, String nameStr) {
      int k = 1;
      int l = 0;
      boolean isChange = false;

      for(int i = 0; i < 3; ++i) {
         for(int j = 0; j < 8; ++j) {
            if (tmpSkill[k++] == 1) {
               short tein = Player.getInstance().getIND_FromSkillData(tmpSkill[k]);
               if (tein == -1) {
                  --k;
                  tmpSkill[k++] = -1;
                  tmpSkill[k++] = -1;
                  tmpSkill[k++] = -1;
                  tmpSkill[k++] = -1;
                  tmpName[l++] = "";
               } else {
                  k += 3;
                  ++l;
               }

               isChange = true;
            } else {
               k += 3;
               ++l;
            }
         }
      }

      if (isChange) {
         Util.saveIntRecord(tmpSkill, cutStr);
         Util.saveStrRecord(tmpName, nameStr);
      }

      return isChange;
   }

   public static final void setShowSetup() {
      GOManager.isShowOtherPlayer = (showSetup >> 0) % 2 != 0;
      GOManager.isShowName = (showSetup >> 1) % 2 != 0;
      SITeam.isShowName = (showSetup >> 2) % 2 != 0;
      GOManager.isShowMonsterName = (showSetup >> 3) % 2 != 0;
   }

   public static void initMImg() {
      numberImg = new MImage[9];

      for(int i = 0; i < 8; ++i) {
         if (i < 4) {
            numberImg[i] = new MImage("/special/num" + i + ".png", 8, 11);
         } else {
            numberImg[i] = new MImage("/special/num" + i + ".png", 16, 22);
         }
      }

      numberImg[8] = new MImage("/special/miss.png", 70, 14);
      levelUpImg = new MImage("/special/lvl_up.png", 7, 7);
   }

   public static void initImg() {
      Image[] tmpImages = Util.loadImage(stylePkg, new String[]{"cao.png", "hua.png", "b_l.png", "b_s.png", "b_a.png", "b_h.png", "b_b.png", "f_x.png", "f_j.png", "a_a.png", "gx1.png", "gx2.png", "l_b.png", "m_j.png", "k_f.png", "dong.png", "taiji.png", "num.png", "num_data.png", "arr.png", "num_lv.png", "p_j.png", "m_c.png", "newnum_lv.png", "b_a2.png", "smenu.png", "menu_j.png"});
      si_caoImg = tmpImages[0];
      si_huaImg = tmpImages[1];
      ui_1Img = tmpImages[2];
      ui_2Img = tmpImages[3];
      ui_3Img = tmpImages[4];
      ui_4Img = tmpImages[5];
      ui_5Img = tmpImages[6];
      ui_xImg = tmpImages[7];
      ui_jImg = tmpImages[8];
      ui_aImg = tmpImages[9];
      ui_gx1Img = tmpImages[10];
      ui_gx2Img = tmpImages[11];
      ui_labelImg = tmpImages[12];
      ui_mjImg = tmpImages[13];
      ui_fengImg = tmpImages[14];
      ui_dongImg = tmpImages[15];
      taiji_Img = tmpImages[16];
      p_jImg = tmpImages[21];
      m_cImg = tmpImages[22];
      menuArraw = tmpImages[24];
      smenu = tmpImages[25];
      smenu2 = tmpImages[26];
      ui_numMImg = new MImage(tmpImages[17], 5, 7);
      ui_dataNumMImg = new MImage(tmpImages[18], 6, 7);
      ui_arrowMImg = new MImage(tmpImages[19], 8, 4);
      ui_lvNumMImg = new MImage(tmpImages[20], 10, 9);
      newui_lvNumMImg = new MImage(tmpImages[23], 6, 9);
      shadow = Util.loadImage("/special/shadow.png");
      magicTT = Util.loadImage("/special/tt.png");
   }

   public void initStyle(byte id) {
      switch(id) {
      case 0:
         stylePkg = "/ui_1.pkg";
      default:
         initImg();
      }
   }

   private final void newGame() {
      setState((byte)11);
   }

   private final boolean loadRec() {
      return true;
   }

   private final void drawSplash(Graphics g) {
      if (!isChinaMobileVer) {
         g.setColor(16777215);
         g.fillRect(0, 0, screenW, screenH);
         if (this.splashImage != null && this.splashIndex == 2) {
            g.drawImage(this.splashImage, screenW >> 1, screenH >> 2, 17);
         }

         if (this.splashIndex >= 0 && this.isDelayed(this.splashIndex == 0 ? 0 : (this.splashIndex == 1 ? 1000 : 2000), (byte)0)) {
            if (this.splashIndex < 2) {
               this.beginDelay((byte)0);
               this.splashImage = Util.loadImage("/vitalk.png");
               this.splashIndex = 2;
            } else {
               this.splashImage = null;
               this.splashKoImage = null;
               this.splashIndex = 0;
               logoimg = Util.loadImage("/logo.png");
               setState((byte)24);
            }
         }
      } else {
         g.setColor(0);
         g.fillRect(0, 0, screenW, screenH);
         if (this.splashImage != null) {
            g.drawImage(this.splashImage, (screenW >> 1) - 1, (screenH >> 1) - 3, 3);
         }

         if (this.splashIndex == 1) {
            this.splashImage = this.imgSplash[this.frameCount];
            ++this.frameCount;
            if (this.frameCount > 2) {
               this.frameCount = 0;
            }
         }

         if (this.isDelayed(this.splashIndex == 0 ? 1000 : (this.splashIndex == 1 ? 1000 : 3000), (byte)0)) {
            if (this.splashIndex < 2) {
               this.beginDelay((byte)0);
               ++this.splashIndex;
            } else {
               this.splashImage = null;

               for(int i = 0; i < this.imgSplash.length; ++i) {
                  this.imgSplash[i] = null;
               }

               this.frameCount = 0;
               this.splashIndex = 0;
               logoimg = Util.loadImage("/logo.png");
               setState((byte)24);
            }
         }
      }

   }

   private final void drawGameLoading(Graphics g) {
      this.repaint();

      try {
         switch(loadCount) {
         case 0:
            this.isShowGameMenu = false;
         case 5:
         case 96:
         default:
            break;
         case 10:
            cursorImg = Util.loadImage("/cursor.png");
            break;
         case 15:
            if (this.imageSpecific == null) {
               this.imageSpecific = Util.loadImage("/special/TJ2.png");
            }
            break;
         case 50:
            if (this.frameDataSpecific == null) {
               this.frameDataSpecific = Util.readFdatFile("/special/TJ2.fdat", 0, true);
            }
            break;
         case 55:
            if (this.picDatasSpecific == null) {
               this.picDatasSpecific = Util.readPdatFile("/special/TJ2.pdat", true);
            }
            break;
         case 60:
            if (this.motionDataAll == null) {
               this.motionDataAll = Util.readMdatFile("/special/TJ2.mdat", true);
            }
            break;
         case 70:
            if (SUPPORT_POINTER) {
               touchMenu = new MImage("/special/touchMenu.png", 15, 15);
               if (isChinaMobileVer) {
                  storeImg = new MImage("/cm/store.png");
               }
            }
            break;
         case 75:
            m_sGameMount = Util.loadByteRecord("gamemount");
            if (m_sGameMount == null) {
               m_sGameMount = new byte[1];
               m_sGameMount[0] = 2;
            }

            if (m_sGameMount[0] == 0) {
               isAutoConn = false;
               isCMWAP = true;
            } else if (m_sGameMount[0] == 1) {
               isAutoConn = false;
               isCMWAP = false;
            } else if (m_sGameMount[0] == 2) {
               isAutoConn = true;
               isCMWAP = true;
            }

            this.initStyle((byte)0);
            break;
         case 80:
            OtherPlayer.sadGO = GameObject.mgrSA.getSADByIndex(GameObject.mgrSA.requestData((byte)0));
            break;
         case 94:
            ParseUI.parseUI("/ui/list.ui");
            break;
         case 98:
            Cover.getInstance().initCover();
            break;
         case 100:
            if (isChinaMobileVer) {
               menuStr = Cons.MAIN_CM_MENU_STR;
            } else if (this.haveLoginRecord()) {
               loginFirstItem = true;
               menuStr = Cons.MAIN_MENU_STR_LOGIN;
            } else {
               loginFirstItem = false;
               menuStr = Cons.MAIN_MENU_STR_NONE_LOGIN;
            }

            PassPort pp = new PassPort(PassPort.CM_CHARGE_IP, "", (byte)(isCMWAP ? 0 : 1), (byte)3);
            pp.authenticate((byte)30);
            if (keyvalue == null) {
               if (!SUPPORT_POINTER) {
               }

               setState((byte)4);
            } else {
               setState((byte)4);
            }

            Cover.girl = null;
         }

         ++loadCount;
         g.drawRect(screenW - 100 >> 1, screenH - (CHARH << 1) + CHARH - 2, 102, 6);

         for(byte i = 0; i < loadCount / 5; ++i) {
            g.setColor(16579274);
            g.fillRect((screenW - 100 >> 1) + 2 + i * 5, screenH - (CHARH << 1) + CHARH, 4, 3);
            g.setColor(460544);
            g.drawLine((screenW - 100 >> 1) + 2 + i * 5 + 1, screenH - (CHARH << 1) + 1 + CHARH, (screenW - 100 >> 1) + 2 + i * 5 + 1 + 1, screenH - (CHARH << 1) + 1 + CHARH);
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      g.drawImage(logoimg, (screenW >> 1) - (logoimg.getWidth() >> 1), 10, 0);
   }

   public static void loadPlayerRes() {
      if (!isLoadPlayerRes) {
         OtherPlayer.loadStaticTiles();
         OtherPlayer.loadNPC_HUMANData();
         OtherPlayer.loadNPC_BEASTData();
         isLoadPlayerRes = true;
      }

   }

   private final void drawKeyset(Graphics g) {
      g.setColor(0);
      g.fillRect(0, 0, screenW, screenH);
      KeyConfig.drawKeySet(g);
   }

   private void drawFree(Graphics g) {
      if (freeImg == null) {
         freeImg = Util.loadImage("/free.png");
      }

      g.drawImage(freeImg, screenW - 4, screenH - 4, 40);
   }

   private final void drawSeizing(Graphics g) {
      g.setColor(16711680);
      g.drawString("Thao tác này thực hiện dưới dạng", screenW >> 1, screenH - (CHARH << 1) - 32, 17);
      g.drawString("gửi tin nhắn", screenW >> 1, screenH - (CHARH << 1) - 20, 17);
   }

   public static final void drawRechardCardHelp(Graphics g) {
      g.setColor(16711680);
      g.drawString("Đối với thẻ Mobi/Vina thì chỉ cần", screenW >> 1, screenH - (CHARH << 1) - 44, 17);
      g.drawString("nhập Mã số nạp thẻ", screenW >> 1, screenH - (CHARH << 1) - 32, 17);
      g.drawString("(Số Sêri nhập rác )", screenW >> 1, screenH - (CHARH << 1) - 20, 17);
   }

   private final void drawLoading(Graphics g) {
      g.setColor(5508390);
      g.fillRect(0, 0, screenW, screenH);
      g.setColor(16513768);
      g.drawString("Hãy chờ từ 5 đến 10 giây...", screenW >> 1, screenH - (CHARH << 1) - 10 - CHARH, 17);
      g.drawRect(screenW - 100 >> 1, screenH - (CHARH << 1), 102, 6);

      for(byte i = 0; i < loadCount / 5; ++i) {
         g.setColor(16579274);
         g.fillRect((screenW - 100 >> 1) + 2 + i * 5, screenH - (CHARH << 1) + 2, 4, 3);
         g.setColor(460544);
         g.drawLine((screenW - 100 >> 1) + 2 + i * 5 + 1, screenH - (CHARH << 1) + 2 + 1, (screenW - 100 >> 1) + 2 + i * 5 + 1 + 1, screenH - (CHARH << 1) + 2 + 1);
      }

      if (loadCount >= 100) {
         endLoadingProgress();
      }

   }

   public static void loadRes() {
      if (!isLoadComplete) {
         if (!isLoadComplete) {
            initMImg();
            waitMImg = new MImage("/wait.png", 20, 20);
            talentArrImg = Util.loadImage("/special/talentarr.png");
            stuffMImg = new MImage("/special/stuff.png", 14, 14);
            zyMImg = new MImage("/zy.png", 10, 10);
            ui_icon_shadow = Util.loadImage("/shadow.png");
            taskIconImg = new MImage("/taskicon.png", 10, 12);
            maskImg = new MImage("/special/mark.png", 11, 15);
            taskInfoMImg = new MImage("/special/task.png", 56, 10);
            mouseImg = new MImage("/special/mouse.png", 14, 7);
            faceImg = new MImage("/special/face.png", 12, 12);
            faceImg1 = new MImage("/special/face1.png", 12, 12);
            starImage = Util.loadImage("/special/star.png");
            auctionArrImg = Util.loadImage("/au.png");
            bubble = Util.loadImage("/special/bubble.png");
            playerArrImg = Util.loadImage("/special/parr.png");
            si_HPMPImg = Util.loadImage("/HPMP.png");
            buffImg = new MImage("/special/bf.png", 10, 10);
            kuangimg = new MImage("/special/passive.png", 24, 20);
            e0MImg = new MImage("/e0.png", 8, 19);
            e1MImg = new MImage("/e1.png", 11, 9);
            e2MImg = new MImage("/e2.png", 7, 8);
            e3MImg = new MImage("/e3.png", 17, 10);
            e4MImg = new MImage("/e4.png", 11, 9);
            red_flag = new MImage("/special/flag_red.png", 33, 52);
            blue_flag = new MImage("/special/flag_blue.png", 33, 52);
            gray_flag = new MImage("/special/flag_gray.png", 33, 52);
            small_redflag = new MImage("/special/small_redflag.png", 16, 14);
            small_blueflag = new MImage("/special/small_blueflag.png", 16, 14);
            upgradeMImg = Util.loadImage("/special/upgrade.png");
            treasurePic = Util.loadImage("/special/treasure1.png");
            Map.imagePlayerRegion = Util.loadImage("/wm/wm.pkg", "city1.png");

            int i;
            for(i = 0; i < Map.imageMapRegion.length; ++i) {
               Map.imageMapRegion[i] = Util.loadImage("/wm/wm.pkg", i + ".png");
            }

            for(i = 0; i < Map.imageRegionType.length; ++i) {
               if (i == 6) {
                  Map.imageRegionType[i] = Util.loadImage("/wm/wm.pkg", "rt4.png");
               } else if (i == 7) {
                  Map.imageRegionType[i] = Util.loadImage("/wm/wm.pkg", "rt6.png");
               } else {
                  Map.imageRegionType[i] = Util.loadImage("/wm/wm.pkg", "rt" + i + ".png");
               }
            }

            mapArrImg = Util.loadImage("/special/maparr.png");
            isLoadComplete = true;
         }

      }
   }

   private final void drawMainCover(Graphics g) {
      g.setColor(10248672);
      g.fillRect(0, 0, screenW, screenH);
      Cover.getInstance().drawPic(g);
      g.setColor(16777215);
      StringBuffer sb = new StringBuffer(4);
      sb.append("Phiên bản:" + GameLogin.m_sVersion);
      g.drawString(sb.toString(), screenW - g.getFont().stringWidth(sb.toString()) - 2, 5, 20);
   }

   public static String VerString(int ver) {
      StringBuffer sb = new StringBuffer(4);
      sb.append(String.valueOf(ver >>> 24)).append(".");
      sb.append(String.valueOf((ver & 16711680) >>> 16)).append(".");
      sb.append(String.valueOf((ver & '\uff00') >>> 8)).append(".");
      sb.append(String.valueOf(ver & 255));
      return sb.toString();
   }

   private void drawSplashMenu(Graphics g) {
      int panelWidth = CHARW * 4 + 40;
      int panelX = (screenW - panelWidth) / 2 + 5;
      int panelHeight = (CHARH + 4) * 5 + 16;
      int panelY = (screenH - panelHeight) / 2 + 10;
      int innerX1 = panelX + 4 + 10 - 1;
      int innerY1 = panelY + 5 - 1;
      int innerW1 = panelWidth - 28 + 2;
      int innerH1 = panelHeight - 10 + 2;
      g.setColor(14527877);
      g.fillRect(innerX1 + 1, innerY1 + 1, innerW1 - 1, innerH1 - 1);
      g.setColor(4930874);
      g.drawRect(innerX1, innerY1, innerW1, innerH1);
      int innerX2 = innerX1 + 2;
      int innerY2 = innerY1 + 2;
      int innerW2 = innerW1 - 4;
      int innerH2 = innerH1 - 4;
      g.setColor(16575691);
      g.fillRect(innerX2 + 1, innerY2 + 1, innerW2 - 1, innerH2 - 1);
      g.setColor(4930874);
      g.drawRect(innerX2, innerY2, innerW2, innerH2);
      int innerX3 = innerX2 + 2;
      int innerY3 = innerY2 + 2;
      int innerW3 = innerW2 - 4;
      int innerH3 = innerH2 - 4;
      g.setColor(14397060);
      g.drawRect(innerX3, innerY3, innerW3, innerH3);
      int innerX4 = innerX3 + 1;
      int innerY4 = innerY3 + 1;
      Util.drawImage(g, smenu, innerX1 - smenu.getWidth(), innerY1 + (CHARH + 3 >> 1) - 5, 2);
      Util.drawImage(g, smenu, innerX1 + innerW1, innerY1 + 4 * (CHARH + 3) + 2, 0);
      Util.drawImage(g, smenu2, innerX1 + innerW1 - 6, innerY1 - smenu2.getHeight() + 7, 3);
      Util.drawImage(g, smenu2, innerX1 - smenu2.getWidth() + 7, innerY1 + innerH1 - 6, 0);
      int curStrColor = false;
      int startStrY = innerY4 - 1;
      int maxItemLen = UIMenu.getMaxItemsLen(menuStr);

      int i;
      int lineX;
      for(i = 0; i < 5; ++i) {
         int rectY;
         int rectW;
         if (i != menuStr.length - 1) {
            lineX = innerX3 + 1;
            rectY = startStrY + (i + 1) * (CHARH + 4);
            rectW = lineX + innerW3 - 2;
            g.setColor(Cons.COLOR_BACKGROUND);
            g.drawLine(lineX, rectY, rectW, rectY);
         }

         rectY = innerY4 + i * (CHARH + 4);
         rectW = innerW3 - 1;
         int rectH = CHARH + 3;
         if (i % 2 == 0) {
            g.setColor(Cons.COLOR_TEXT_BG);
            g.fillRect(innerX4, rectY, rectW, rectH);
         }

         int index = this.mainBeginIndex + i;
         if (index >= menuStr.length) {
            index -= menuStr.length;
         }

         int curStrColor;
         if (index == this.mainItemID) {
            curStrColor = Cons.COLOR_PANEL_BG;
            int fillX = innerX4 + 1;
            int fillY = rectY + 1;
            int fillW = rectW - 1;
            int fillH = rectH - 2;
            g.setColor(Cons.COLOR_MENU_SEL_ITEM_BG);
            g.fillRect(fillX, fillY, fillW, fillH);
         } else {
            curStrColor = Cons.COLOR_FONT_1;
         }

         if (isChinaMobileVer && "Trò chơi khác".equals(menuStr[i])) {
            curStrColor = 16711680;
         }

         Util.drawString(g, menuStr[index], panelWidth, panelX, startStrY + 4 + i * (CHARH + 4), curStrColor);
      }

      i = panelX + (panelWidth - menuArraw.getWidth() >> 1);
      lineX = panelY + panelHeight - menuArraw.getHeight() + 1;
      this.arr_Y = (byte)(this.arr_Y ^ 1);
      g.drawImage(menuArraw, i, panelY - 3 - this.arr_Y, 20);
      Util.drawImage(g, menuArraw, i, lineX + 3 + this.arr_Y, 1);
      this.drawFree(g);
   }

   public void pointMenu() {
      if (SUPPORT_POINTER && isInputDown(268435456)) {
         if (curTopForm != null) {
            curTopForm.touchScreenAction();
            return;
         }

         int panelWidth = CHARW * 4 + 40;
         int panelX = (screenW - panelWidth) / 2 + 5;
         int panelHeight = (CHARH + 4) * 5 + 16;
         int panelY = (screenH - panelHeight) / 2 + 10;
         int innerX4 = panelX + 4 + 10 - 1 + 5;
         int innerY4 = panelY + 5 - 1 + 5;
         int maxItemLen = UIMenu.getMaxItemsLen(Cons.MAIN_MENU_STR_LOGIN);
         int x = innerX4;
         int w = maxItemLen * CHARW + 2;
         int h = CHARH + 3;

         for(byte i = 0; i < 5; ++i) {
            int y = innerY4 + i * (CHARH + 4);
            if (isPointInRect(x, y, w, h)) {
               this.mainItemID = (byte)(this.mainBeginIndex + i);
               if (this.mainItemID >= Cons.MAIN_MENU_STR_LOGIN.length) {
                  this.mainItemID = (byte)(this.mainItemID - Cons.MAIN_MENU_STR_LOGIN.length);
               }

               this.mainShowIndex = i;
               keySimPressed(131072);
               return;
            }
         }

         int arrH = ui_3Img.getHeight();
         int y1 = 0;
         int h1 = panelY + arrH;
         int y2 = panelY + panelHeight - arrH;
         int h2 = screenH - y2;
         if (isPointInRect(panelX, y1, panelWidth, h1)) {
            keySimPressed(4096);
         }

         if (isPointInRect(panelX, y2, panelWidth, h2)) {
            keySimPressed(8192);
         }
      }

   }

   private void keyInMenu() {
      if (curTopForm != null) {
         curTopForm.keyInTopForm();
      } else {
         if (isInputDown(196608)) {
            if (!isChinaMobileVer) {
               this.mainMenuSelectedOld();
            } else {
               this.cmMainMenuSelected();
            }
         } else if (isInputDownOrHold(4096)) {
            if (curTopForm != null) {
               return;
            }

            --this.mainItemID;
            if (this.mainItemID < 0) {
               this.mainItemID = (byte)(menuStr.length - 1);
            }

            --this.mainShowIndex;
            if (this.mainShowIndex < 0) {
               this.mainShowIndex = 0;
               --this.mainBeginIndex;
               if (this.mainBeginIndex < 0) {
                  this.mainBeginIndex = (byte)(menuStr.length - 1);
               }
            }
         } else if (isInputDownOrHold(8192)) {
            if (curTopForm != null) {
               return;
            }

            ++this.mainItemID;
            if (this.mainItemID == menuStr.length) {
               this.mainItemID = 0;
            }

            ++this.mainShowIndex;
            if (this.mainShowIndex == 5) {
               this.mainShowIndex = 4;
               ++this.mainBeginIndex;
               if (this.mainBeginIndex == menuStr.length) {
                  this.mainBeginIndex = 0;
               }
            }
         }

      }
   }

   private void login() {
      PassPort.menuSelected = 25;
      this.releaseMenuRes();
      this.newGame();
      m_bTourist = 0;
   }

   private void tourist() {
      TopForm((byte)0, "Hãy đợi server xác nhận...", "", "", -120, -120);
      if (name_password == null) {
         name_password = new String[2];
      }

      name_password[0] = "1241506146031";
      name_password[1] = "1241506146031";
      PassPort pp = null;
      this.repaint();
      this.serviceRepaints();
      pp = new PassPort(PassPort.KONG_PASSPORT_IP, PassPort.getURL((byte)1), (byte)(isCMWAP ? 0 : 1), (byte)2);
      pp.authenticate((byte)1);
      m_bTourist = 1;
   }

   private void autoRegister() {
      PassPort.menuSelected = 23;
      if (this.isTestNum == 1) {
         TopForm((byte)0, "Tester không thể đăng nhập", "Xác nhận", "", -7, -120);
      } else {
         this.m_bEnterBadge = 2;
         if (name_password == null) {
            name_password = new String[2];
         }

         String str = "Hãy ...";
         TopForm((byte)0, str, "", "", -120, -120);
         PassPort.isQuick = true;
         String autoIP = "";
         if (isChinaMobileVer) {
            autoIP = PassPort.CM_CHARGE_IP;
         } else {
            autoIP = PassPort.KONG_PASSPORT_IP;
         }

         PassPort pp = new PassPort(autoIP, PassPort.getURL((byte)5), (byte)(isCMWAP ? 0 : 1), (byte)2);
         pp.authenticate((byte)0);
         if (!PassPort.isVitalk) {
            pp.authenticate((byte)0);
         } else {
            pp.authenticate((byte)21);
         }

         m_bTourist = 0;
      }
   }

   private void register() {
      PassPort.menuSelected = 24;
      if (this.isTestNum == 1) {
         TopForm((byte)0, "Tester không thể đăng nhập", "Xác nhận", "", -7, -120);
      } else {
         setState((byte)14);
         this.m_bEnterBadge = 2;
         if (name_password == null) {
            name_password = new String[2];
         }

         m_bTourist = 0;
      }
   }

   private void bindding() {
      if (name_password == null) {
         name_password = new String[2];
      }

      this.m_bEnterBadge = 3;
      setState((byte)16);
      m_bTourist = 0;
   }

   private void networkSetting() {
      setState((byte)8);
      m_bTourist = 0;
   }

   private void help() {
      setState((byte)6);
      m_bTourist = 0;
   }

   private void about() {
      setState((byte)10);
      m_bTourist = 0;
   }

   private void exit() {
      if (!isChinaMobileVer) {
         this.stop();

         try {
            String popURL = "";
            if (!"2dcn".equals(jarFrom) && jarFrom.indexOf("dj") == -1) {
               if ("2sna".equals(jarFrom)) {
                  popURL = "http://3g.sina.com.cn/game/s/3g/?from=60755";
               } else {
                  if (name_password == null) {
                     name_password = Util.loadStrRecord("name_password", 2);
                  }

                  if (name_password != null && !"".equals(name_password[0]) && !"".equals(name_password[1])) {
                     popURL = "http://g.ko.cn/gk/fswap/popup.jsp?wun=" + name_password[0] + "&wpw=" + name_password[1] + "&wtp=" + getLoginToken() + "&from=" + jarFrom;
                  } else {
                     popURL = "http://g.ko.cn/gk/fswap/popup.jsp?from=" + jarFrom;
                  }
               }
            } else {
               popURL = "http://zt.d.cn/a091111_netgame_forum_promotion/index.pih?fid=3973&cid=108";
            }

            aMidlet.platformRequest("http://vibox.vn/pt");
         } catch (Exception var2) {
            var2.printStackTrace();
         }

         aMidlet.exitMIDlet();
      } else {
         setState((byte)7);
      }

   }

   private void mainMenuSelectedOld() {
      switch(this.mainItemID) {
      case 0:
         if (loginFirstItem) {
            this.login();
         } else {
            this.autoRegister();
         }
         break;
      case 1:
         if (loginFirstItem) {
            this.register();
         } else {
            this.login();
         }
         break;
      case 2:
         if (loginFirstItem) {
            this.autoRegister();
         } else {
            this.register();
         }
         break;
      case 3:
         this.bindding();
         break;
      case 4:
         this.networkSetting();
         break;
      case 5:
         this.help();
         break;
      case 6:
         this.about();
         break;
      case 7:
         this.exit();
         break;
      case 8:
         this.tourist();
      }

   }

   private void mainMenuSelected() {
      switch(this.mainItemID) {
      case 0:
         if (loginFirstItem) {
            this.login();
         } else {
            this.tourist();
         }
         break;
      case 1:
         if (loginFirstItem) {
            this.tourist();
         } else {
            this.login();
         }
         break;
      case 2:
         this.autoRegister();
         break;
      case 3:
         this.register();
         break;
      case 4:
         this.bindding();
         break;
      case 5:
         this.networkSetting();
         break;
      case 6:
         this.help();
         break;
      case 7:
         this.about();
         break;
      case 8:
         this.exit();
      }

   }

   private void cmMainMenuSelected() {
      switch(this.mainItemID) {
      case 0:
         this.login();
         break;
      case 1:
         this.autoRegister();
         break;
      case 2:
         this.register();
         break;
      case 3:
         try {
            aMidlet.platformRequest("http://vibox.vn/pt");
         } catch (Exception var2) {
         }
         break;
      case 4:
         this.networkSetting();
         break;
      case 5:
         this.help();
         break;
      case 6:
         this.about();
         break;
      case 7:
         this.exit();
         break;
      case 8:
         setState((byte)30);
      }

   }

   private void releaseMenuRes() {
   }

   public static void setGameState(byte b) {
      oldGameState = gameState;
      gameState = b;
      changeGameState = true;
   }

   public static void setGameStateWithoutOld(byte b) {
      gameState = b;
      changeGameState = true;
   }

   public static final byte getGameState() {
      return gameState;
   }

   public void drawGame(Graphics g) {
      g.setColor(0);
      g.fillRect(0, 0, screenW, screenH);
      Map.getInstance().draw(g);
      SIManager.getInstance().draw(g);
      this.drawPicQuality(g);
   }

   public void drawPicQuality(Graphics g) {
      if (isShowPicQua) {
         ++this.drawPicQuaTime;
         if (this.drawPicQuaTime > 15) {
            isShowPicQua = false;
            this.drawPicQuaTime = 0;
         }

         g.setColor(16711680);
         g.drawString(Cons.PIC_QUALITY_STR[picQuaInd], screenW >> 1, 20, 17);
      }
   }

   public void drawRightMenu(Graphics g) {
      if (rightMenu != null) {
         rightMenu.draw(g);
      }

   }

   public void drawUIForm(Graphics g) {
      if (curForm != null && isFullUserData) {
         curForm.draw(g);
      }

   }

   public void drawOldForm(Graphics g) {
      if (oldForm != null) {
         oldForm.draw(g);
      }

   }

   private final void drawGameRun(Graphics g) {
      switch(getGameState()) {
      case 0:
         this.drawGame(g);
         NPC.drawNPCTopTip(g);
         break;
      case 1:
         this.drawGame(g);
         this.drawRightMenu(g);
         NPC.drawNPCTopTip(g);
      case 2:
      default:
         break;
      case 3:
         UIChat.chatForm.draw(g);
         break;
      case 4:
         if (!isWaiting) {
            if (isFullUserData) {
               this.drawUIForm(g);
            } else {
               this.drawGame(g);
            }
         } else {
            this.drawOldForm(g);
         }

         NPC.drawNPCTopTip(g);
         break;
      case 5:
         this.drawGame(g);
         if (UIMenu.npcMenu != null) {
            UIMenu.npcMenu.draw(g);
         }

         NPC.drawNPCTopTip(g);
         break;
      case 6:
         this.drawGame(g);
         if (UIMenu.otherPlayerMenu != null) {
            UIMenu.otherPlayerMenu.draw(g);
         }
      }

      if (curTopForm != null) {
         this.drawUITopForm(g);
      }

      if (AfficheForm != null && isFullUserData) {
         AfficheForm.draw(g);
      }

      if (getGameState() != 4 && AfficheForm == null) {
         SIChat.drawAnounce(g, false);
         SIChat.drawHorn(g, false);
      } else {
         SIChat.drawAnounce(g, true);
         SIChat.drawHorn(g, true);
      }

   }

   private void drawUITopForm(Graphics g) {
      if (curTopForm != null) {
         curTopForm.draw(g);
      }

   }

   public void leftRightMenu() {
      if (rightMenu != null) {
         rightMenu = null;
         setGameState((byte)0);
      }

   }

   public void setPicQuality(byte ind) {
      short tmpSetup = 0;
      byte[] select = (byte[])null;
      switch(ind) {
      case 0:
         select = new byte[]{1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         break;
      case 1:
         select = new byte[]{1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         break;
      case 2:
         select = new byte[16];
      }

      for(int i = 0; i < 5; ++i) {
         tmpSetup = (short)(tmpSetup | select[i] << i);
      }

      showSetup = tmpSetup;
      Util.saveShortRecord(new short[]{showSetup, ind}, "showSetup");
      setShowSetup();
   }

   private final void keyInGameRun() {
      if (!changeGameState) {
         if (AfficheForm == null) {
            if (curTopForm != null) {
               curTopForm.keyInTopForm();
            } else {
               UITopForm.topFormBuffAction();
               switch(getGameState()) {
               case 0:
                  if (isInputDown(262144)) {
                     rightMenu = new UIRightMenu();
                     setGameState((byte)1);
                  } else if (isInputDown(curGameKey[15])) {
                     this.drawPicQuaTime = 0;
                     isShowPicQua = true;
                     ++picQuaInd;
                     if (picQuaInd > 2) {
                        picQuaInd = 0;
                     }

                     this.setPicQuality(picQuaInd);
                     if (picQuaInd == 2 && GOManager.getCurrentTarget().type == 1 && GOManager.getCurrentTarget() != Player.getInstance()) {
                        GOManager.setCurrentTarget(Player.getInstance());
                        Player.getInstance().changeTargetRound();
                     }

                     ni.send(196632);
                  } else if (isInputDown(curGameKey[16])) {
                     UIRightMenu.setRightMenuNum(71);
                  } else if (isInputDown(curGameKey[21])) {
                     UIRightMenu.setRightMenuNum(6);
                  } else if (isInputDown(curGameKey[22])) {
                     ni.send(851976);
                  } else if (isInputDown(curGameKey[23])) {
                     ni.send(458760);
                  } else if (isInputDown(curGameKey[24])) {
                     UIRightMenu.setRightMenuNum(1);
                  } else if (isInputDown(curGameKey[25])) {
                     UIRightMenu.requestTeamUI();
                  } else if (isInputDown(curGameKey[26])) {
                     ni.send(262187);
                  } else if (isInputDown(curGameKey[27])) {
                     UIRightMenu.setRightMenuNum(4);
                  } else if (isInputDown(curGameKey[28])) {
                     UIRightMenu.setRightMenuNum(3);
                  } else if (isInputDown(curGameKey[29])) {
                     UIRightMenu.setRightMenuNum(9);
                  }
                  break;
               case 1:
                  if (rightMenu != null) {
                     rightMenu.keyInAction();
                  }
               case 2:
               default:
                  break;
               case 3:
                  UITitle.localChatTick();
                  UIChat.chatForm.keyInAction();
                  break;
               case 4:
                  if (curForm != null) {
                     curForm.keyInForm();
                     UITitle.localChatTick();
                  }
                  break;
               case 5:
                  if (UIMenu.npcMenu != null && !isWaiting) {
                     if (isInputDown(262144)) {
                        UIMenu.npcMenu = null;
                        setGameState((byte)0);
                     } else if (isInputDown(4096)) {
                        UIMenu.npcMenu.decreaseIndex();
                     } else if (isInputDown(8192)) {
                        UIMenu.npcMenu.increaseIndex();
                     } else if (isInputDown(196608)) {
                        int cmd = (Integer)UIMenu.npcMenu.getCmdIds().elementAt(UIMenu.npcMenu.getIndex());
                        if (UIMenu.npcMenu.menuType == 2 && PCMovement.movementIds != null) {
                           UILabel.movementID = PCMovement.movementIds[UIMenu.npcMenu.getIndex()];
                        }

                        if (UIMenu.npcMenu.menuType == 3) {
                           UIList.selectedListId = PCTask.npcTaskIds[UIMenu.npcMenu.getIndex()];
                           byte taskType = PCTask.colorIndexs[UIMenu.npcMenu.getIndex()];
                           if (taskType == 2) {
                              UIList.canReceive = false;
                           } else if (taskType == 1) {
                              UIList.canReceive = true;
                           } else if (taskType == 4) {
                              UIList.canReturn = false;
                           } else if (taskType == 3) {
                              UIList.canReturn = true;
                           }

                           if (cmd == 0) {
                              setGameState((byte)0);
                              return;
                           }
                        }

                        if (cmd == 0) {
                           SIChat.addChat((byte)7, "Công năng này tạm thời chưa có.", -1, "", (Vector)null, (Vector[])null);
                           setGameState((byte)0);
                           return;
                        }

                        if (UIMenu.npcMenu.menuType != 2) {
                           UIMenu.npcMenu = null;
                        }

                        ni.send(cmd);
                        Util.debugPrintln("cmdcmdcmdcmd" + Integer.toHexString(cmd));
                        setGameState((byte)0);
                     }
                  }
                  break;
               case 6:
                  if (UIMenu.otherPlayerMenu != null) {
                     if (isInputDown(262144)) {
                        UIMenu.otherPlayerMenu = null;
                        setGameState((byte)0);
                     } else if (isInputDown(4096)) {
                        UIMenu.otherPlayerMenu.decreaseIndex();
                     } else if (isInputDown(8192)) {
                        UIMenu.otherPlayerMenu.increaseIndex();
                     } else if (isInputDown(196608)) {
                        Player.getInstance().keyInOtherPlayerMenu();
                     }
                  }
               }

               if (isInputDown(-1) && NPC.isDrawTopTip) {
                  NPC.isInterruptTopTip = true;
               }

            }
         } else {
            UITitle.localChatTick();
            UITextArea textarea;
            if (isInputDown(458752)) {
               AfficheForm = null;
               if (Player.getInstance().getLevel() < 20) {
                  ni.send(196635);
               }

               if (PCMonthly.displayUI) {
                  setUI("/ui/maturity.ui");
                  curForm.clientCommand = -1610612632;
                  setGameState((byte)4);
                  textarea = (UITextArea)curForm.getComponents().elementAt(3);
                  textarea.setColor(PCMonthly.color);
                  textarea.setContent(PCMonthly.explain);
                  textarea.addUIScroll();
                  short x = (short)(textarea.getPositionX() + 2);
                  short y = (short)(textarea.getHeight() + 2);
                  byte type = 2;
                  String text = "Hãy chọn：";
                  byte itemNum = PCMonthly.num;
                  UIRadio radio = new UIRadio(x, y, type, text, curForm);
                  radio.isCheckBoxSelect = new boolean[itemNum];

                  for(byte i = 0; i < itemNum; ++i) {
                     String str = PCMonthly.name[i];
                     radio.add(str);
                     radio.isCheckBoxSelect[i] = false;
                  }

                  curForm.addComponent(radio);
                  textarea.setAroundComponent((byte)1, (byte)5);
                  radio.setAroundComponent((byte)0, (byte)4);
               }
            } else if (isInputDown(12288)) {
               textarea = (UITextArea)AfficheForm.getComponents().elementAt(3);
               textarea.keyInAction();
            }

         }
      }
   }

   private final boolean isDelayed(int waitFor, byte type) {
      if (this.curTime[type] + (long)waitFor > this.paintTaken) {
         return false;
      } else {
         this.curTime[type] = 0L;
         return true;
      }
   }

   private final void beginDelay(byte type) {
      this.curTime[type] = this.paintTaken;
   }

   private final String preProcessStr(String str, int showWidth) {
      StringBuffer sb = new StringBuffer();

      int flagPos;
      for(int i = 0; i < str.length(); i = flagPos + 1) {
         StringBuffer tmpsb = new StringBuffer();
         flagPos = i;

         for(int j = i; j < str.length(); ++j) {
            char ch = str.charAt(j);
            if (ch == '\n') {
               tmpsb.append(' ');
               flagPos = j;
               break;
            }

            tmpsb.append(ch);
            if (CharacterValidate(str, (byte)3) || j == str.length() - 1 || ch > 127) {
               int enCount = 0;

               for(int k = 0; k < tmpsb.length(); ++k) {
                  if (tmpsb.charAt(k) < 255) {
                     ++enCount;
                  }
               }

               if (tmpsb.length() - (enCount >> 1) > (showWidth - 10) / CHARW + 1) {
                  if (flagPos == i) {
                     flagPos = j;
                  }
                  break;
               }

               flagPos = j;
            }
         }

         tmpsb.setLength(flagPos - i + 1);
         tmpsb.append('\n');
         sb.append(tmpsb.toString());
      }

      return sb.toString();
   }

   public static final String readL(String tmps, int index) {
      StringBuffer newsb = new StringBuffer();
      if (tmps != null) {
         try {
            StringBuffer sb = new StringBuffer(tmps);
            int counter = 0;

            for(int i = 0; i < sb.length(); ++i) {
               char curch = sb.charAt(i);
               if (curch == '\n') {
                  ++counter;
               } else {
                  if (index == counter) {
                     newsb.append(curch);
                  }

                  if (index == counter - 1) {
                     break;
                  }
               }
            }
         } catch (Exception var7) {
         }
      }

      return newsb.toString();
   }

   public static final boolean CharacterValidate(String str, byte index) {
      for(int i = 0; i < str.length(); ++i) {
         int ti = str.charAt(i);
         if (index == 1) {
            if (!EnrollCharacterValidate(ti)) {
               return false;
            }
         } else if (index == 2) {
            if (!EnrollCharacterValidate(ti) && !NicknameCharacterValidate(ti)) {
               return false;
            }
         } else if (index == 3) {
            if (!EnrollCharacterValidate(ti) && !NicknameCharacterValidate(ti) && !RectCharacterValidate(ti)) {
               return false;
            }
         } else if (index == 4 && (!EnrollCharacterValidate(ti) || !Length(str))) {
            return false;
         }
      }

      return true;
   }

   private static final boolean EnrollCharacterValidate(int ti) {
      return ti >= 65 && ti <= 90 || ti >= 97 && ti <= 122 || ti >= 48 && ti <= 57;
   }

   private static final boolean NicknameCharacterValidate(int ti) {
      return ti >= 19968 && ti <= 40868;
   }

   private static final boolean RectCharacterValidate(int ti) {
      for(int i = 0; i < Cons.Character.length; ++i) {
         if (ti == Cons.Character[i]) {
            return true;
         }
      }

      return false;
   }

   private static final boolean Length(String str) {
      return str.length() >= 6 && str.length() <= 16;
   }

   private void mapRunSubTick() {
      if (Player.instance != null) {
         Map.getInstance().adjustWindow(Player.getInstance().x + 0, Player.getInstance().y + 0);
      }

      GOManager.getInstance().tick();
   }

   public static void setCurForm(UIForm form) {
      if (form == null) {
         oldForm = null;
         curForm = null;
      } else {
         oldForm = curForm;
         curForm = form;
      }

   }

   public void refeshUpdataUIEffect() {
      switch(curForm.clientCommand) {
      case 458755:
         UIForm.equipEffect();
         break;
      case 1376265:
         this.setMyTradeMoney();
      }

   }

   private void setMyTradeMoney() {
      UITextField uiTF = (UITextField)curForm.getComponents().elementAt(27);
      StringBuffer sb = new StringBuffer();
      sb.append(UIForm.tradeMyMoney);
      uiTF.setSb(sb);
   }

   private void setTradeItemName() {
      UILabel labelMyGoods = (UILabel)curForm.getComponents().elementAt(5);
      int i = 0;

      for(int ii = curForm.getComponents().size(); i < ii; ++i) {
         if ((UIComponent)curForm.getComponents().elementAt(i) instanceof UIPicture && ((UIComponent)curForm.getComponents().elementAt(i)).getType() == 1 && ((UIComponent)curForm.getComponents().elementAt(i)).isFocus()) {
            UIPicture pic = (UIPicture)curForm.getComponents().elementAt(i);
            if (pic.clientType == 34) {
               UIForm.isOtherPic = false;
               labelMyGoods.setText(pic.getEquipName());
               UIForm.curPicNum = (byte)(i - 7);
               labelMyGoods.setTextColor(UIGrid.getStuffWordColor(pic.getEquipColorLevel()));
            }
         }
      }

   }

   private void UIRecieve() {
      if (uiData != null) {
         setCurForm(ParseUI.parseUI(uiData, true));
         uiData = null;
         if (curFormVector.size() > 0) {
            if (replaceFormId == -1) {
               curForm = (UIForm)curFormVector.elementAt(0);
            } else {
               curForm = (UIForm)curFormVector.elementAt(replaceFormId);
            }
         }
      }

      int i;
      if (userData != null) {
         ParseUI.parseUIData(userData);
         userData = null;
         isFullUserData = true;
         if (curForm.clientCommand != 1900548 && curForm.clientCommand != 1900546) {
            isWaiting = false;
         }

         if (ni.isSendingCommands) {
            if (ni.commands.length > 0) {
               isWaiting = true;
               ni.sendCommands();
            } else {
               if (stayFormId != -1) {
                  ((UIForm)curFormVector.elementAt(0)).foldedIndex = stayFormId;
                  setCurForm((UIForm)curFormVector.elementAt(stayFormId));
                  stayFormId = -1;
               }

               ni.isSendingCommands = false;
            }
         }

         replaceFormId = -1;
         if (getGameState() != 4) {
            if (getGameState() != 3) {
               setGameState((byte)4);
            } else {
               setGameStateWithoutOld((byte)4);
            }
         }

         if (getState() == 12 || m_bWhetherRound && getState() == 11 || getState() == 18) {
            curTopForm = null;
            setState((byte)17);
         }

         UITitle title;
         if (curForm.clientCommand == 1638421) {
            title = (UITitle)curForm.getComponents().elementAt(0);
            title.setTitleText(PCClan.rightStr);
         }

         if (curForm.clientCommand == 262147 || curForm.clientCommand == 262176) {
            curForm.addWarningStr("Phím # thay đổi chi tiết và hiển thị nhiệm vụ");
            title = (UITitle)curForm.getComponents().elementAt(1);
            title.setMenuText("Di chuyển", 0);
         }

         if (curForm.clientCommand == 262157) {
            PCTask.setTurnTaskInfoLeftMenu();
         }

         if (curForm.clientCommand == 262154) {
            PCTask.setTakeTaskInfoLeftMenu();
         }

         UITextField uiTF;
         if (curForm.clientCommand == 1638401 || curForm.clientCommand == 1835009) {
            uiTF = (UITextField)curForm.getComponents().elementAt(3);
            uiTF.setEditable(false);
         }

         if (curForm.clientCommand == 393219 || curForm.clientCommand == 2490386) {
            title = (UITitle)curForm.getComponents().elementAt(0);
            title.setCanFocus(true);
            title.setFocus(true, curForm);
            title.setAroundFocusNull();
         }

         if (curForm.clientCommand == 1376260) {
            Util.debugPrintln("curForm.clientCommand == CommandList.C_TRADE_VIEW");
            this.setMyTradeMoney();
            this.setTradeItemName();
         }

         if (curForm.clientCommand == 1376268) {
            uiTF = (UITextField)curForm.getComponents().elementAt(27);
            uiTF.setSb(new StringBuffer(String.valueOf(UIForm.tradeMyMoney)));
         }

         if (curForm.clientCommand == 1703939) {
            Util.debugPrintln("curForm.clientCommand == CommandList.C_MAIL_INBOX");
            UITable mailTable = (UITable)curForm.getComponents().elementAt(3);
            if (mailTable.getItems().size() > 0) {
               if (PCMail.beforeMailIndex >= mailTable.getItems().size()) {
                  PCMail.beforeMailIndex = (byte)(mailTable.getItems().size() - 1);
               }

               for(int k = 0; k < PCMail.beforeMailIndex; ++k) {
                  keySimPressed(8192);
                  mailTable.keyInAction();
                  keySimReleased(8192);
               }

               PCMail.beforeMailIndex = 0;
            }
         }

         if (curForm.clientCommand == 1703954 || curForm.clientCommand == 458753 && PCMail.m_bForbidEnchase == 1) {
            if (curForm.clientCommand == 1703954 && PCTreasure.interphase) {
               return;
            }

            title = (UITitle)curForm.getComponents().elementAt(1);
            title.setMenuText("", 0);
         }

         if (curForm.clientCommand == 851978) {
            title = (UITitle)curForm.getComponents().elementAt(1);
            title.setMenuText("", 0);
         }

         if (curForm.clientCommand == 2490390) {
            title = (UITitle)curForm.getComponents().elementAt(0);
            title.setTitleText("Tìm Sư Phụ");
         }

         if (curForm.clientCommand == 2490391) {
            title = (UITitle)curForm.getComponents().elementAt(0);
            title.setTitleText("Tìm Đồ Đệ");
         }

         if (curForm.clientCommand == 458755 || curForm.clientCommand == 458768) {
            UIComponent firstPic = (UIComponent)curForm.getComponents().elementAt(4);
            firstPic.setFocus(false, curForm);
            UIComponent focusPic = (UIComponent)curForm.getComponents().elementAt(PCPackage.equipIndex);
            focusPic.setFocus(true, curForm);
         }

         UILabel maxExpLabel;
         UILabel ta;
         if ((curForm.clientCommand == 2424839 || curForm.clientCommand == 2424994 || curForm.clientCommand == 2425072) && PCIncrement.m_bBuyMoney != 1) {
            PassPort.onlyGetUserID();
            if (!PassPort.hasNotify) {
               PassPort.accessJadDownloadUrl();
            }

            if ((curForm.clientCommand == 2424994 || curForm.clientCommand == 2425072) && PCIncrement.m_bBuyMoney == 3) {
               curForm.getComponents().removeElementAt(curForm.getComponents().size() - 1);
               curForm.getComponents().removeElementAt(curForm.getComponents().size() - 1);
               ta = (UILabel)curForm.getComponents().elementAt(5);
               maxExpLabel = (UILabel)curForm.getComponents().elementAt(6);
               maxExpLabel.setPositionY(ta.getPositionY());
            } else if (curForm.clientCommand == 2424839 && PCIncrement.m_bBuyMoney == 3) {
               curForm.getComponents().removeElementAt(curForm.getComponents().size() - 1);
               curForm.getComponents().removeElementAt(17);
               ta = (UILabel)curForm.getComponents().elementAt(17);
               maxExpLabel = (UILabel)curForm.getComponents().elementAt(18);
               maxExpLabel.setPositionY(ta.getPositionY());
            }
         }

         UIPicture pic;
         UIPicture picture;
         if (curForm.clientCommand == 2031636 && UIGrid.fromEquipUnseal && (PCUnsealGemCarve.m_bPrimaryGemIndex != -1 || PCUnsealGemCarve.m_bCarveSymbolIndex != -1)) {
            UIGrid grid = (UIGrid)curForm.getComponents().elementAt(9);
            if (PCUnsealGemCarve.m_bCarveSymbolIndex != -1) {
               PCUnsealGemCarve.m_bFocus = 2;
               grid.setGridIndex(PCUnsealGemCarve.m_bCarveSymbolIndex);
               grid.unSealPlace();
            }

            if (PCUnsealGemCarve.m_bPrimaryGemIndex != -1) {
               PCUnsealGemCarve.m_bFocus = 1;
               grid.setGridIndex(PCUnsealGemCarve.m_bPrimaryGemIndex);
               grid.unSealPlace();
            }

            picture = (UIPicture)curForm.getComponents().elementAt(6);
            if (picture.isFocus()) {
               picture.setFocus(false, curForm);
               pic = (UIPicture)curForm.getComponents().elementAt(4);
               pic.setFocus(true, curForm);
            }
         }

         if (curForm.clientCommand == 2621443) {
            title = (UITitle)curForm.getComponents().elementAt(0);
            title.setTitleText(PCCharge.chargeStr);
         }

         if (curForm.clientCommand == 2621445) {
            title = (UITitle)curForm.getComponents().elementAt(0);
            title.setTitleText(PCCharge.chargeStr);
         }

         if (curForm.clientCommand == 2621442) {
            PassPort.connectToGetSMSFormat();
         }

         if (curForm.clientCommand == 2162692 || curForm.clientCommand == 2162694 || curForm.clientCommand == 2162698 || curForm.clientCommand == 2162699 || curForm.clientCommand == 2162705 || curForm.clientCommand == 2162707 || curForm.clientCommand == 2162704 || curForm.clientCommand == 2162706 || curForm.clientCommand == 2162716 || curForm.clientCommand == 2162718 || curForm.clientCommand == 2162715 || curForm.clientCommand == 2162717 || curForm.clientCommand == 2162727 || curForm.clientCommand == 2162729 || curForm.clientCommand == 2162726 || curForm.clientCommand == 2162728 || curForm.clientCommand == 2162738 || curForm.clientCommand == 2162737 || curForm.clientCommand == 2162746 || curForm.clientCommand == 2162758 || curForm.clientCommand == 2162759 || curForm.clientCommand == 2162765 || curForm.clientCommand == 2162766 || curForm.clientCommand == 2162772 || curForm.clientCommand == 2162773 || curForm.clientCommand == 2162779 || curForm.clientCommand == 2162780) {
            title = (UITitle)curForm.getComponents().elementAt(0);
            title.setTitleText(PCProduce.forgStr);
         }

         if (curForm.clientCommand == 1703969) {
            curForm.addWarningStr("Phím # thay đổi lựa chọn và nội dung");
         }

         if (curForm.clientCommand == 1703959 || curForm.clientCommand == 1703960 || curForm.clientCommand == 1703961) {
            m_sBingZi = true;
         }

         if (isExitChoosePlayer) {
            setState((byte)17);
            isExitChoosePlayer = false;
         }

         int i;
         if (getState() == 17) {
            if (curTopForm != null) {
               curTopForm = null;
            }

            this.m_bPlace = Util.loadByteRecord("debarkation");
            if (this.m_bPlace == null) {
               this.m_bPlace = new byte[3];
            }

            this.m_bconfirm = Util.loadByteRecord("debarkation1");
            if (this.m_bconfirm == null) {
               this.m_bconfirm = new byte[3];
            }

            ta = (UILabel)curForm.getComponents().elementAt(16);
            UITitle menuBar = (UITitle)curForm.getComponents().elementAt(1);

            for(i = 0; i < playPicture.length; ++i) {
               playPicture[i] = (UIPicture)curForm.getComponents().elementAt(i + 3);
               if (i == m_bChoose) {
                  playPicture[i].setFocus(true, curForm);
                  if (playPicture[m_bChoose].otherP != null) {
                     menuBar.setMenuText("Xác nhận", 0);
                  } else {
                     menuBar.setMenuText("Tạo", 0);
                  }

                  if (m_bDelete[m_bChoose] == 1 && playPicture[m_bChoose].otherP != null) {
                     ta.setText("Xóa sau 3 ngày");
                     ta.setPositionX((short)(screenW - ta.getWidth() >> 1));
                  } else if (m_bDelete[m_bChoose] == 2 && playPicture[m_bChoose].otherP != null) {
                     ta.setText("Nhân vật đã xóa không hồi phục lại");
                     ta.setPositionX((short)(screenW - ta.getWidth() >> 1));
                  } else if (m_bAmendNickname[m_bChoose]) {
                     ta.setText("Sửa nick");
                     ta.setPositionX((short)(screenW - ta.getWidth() >> 1));
                  } else {
                     ta.setText("Xóa nhân vật");
                     ta.setPositionX((short)(screenW - ta.getWidth() >> 1));
                  }
               } else {
                  playPicture[i].setFocus(false, curForm);
               }
            }
         }

         if (m_sCharacterName[m_bChoose] == null && getState() == 17) {
            for(i = 0; i < 5; ++i) {
               maxExpLabel = (UILabel)curForm.getComponents().elementAt(11 + i);
               maxExpLabel.setText("");
            }
         }

         if (playPicture[m_bChoose] != null && playPicture[m_bChoose].otherP != null && getState() == 17) {
            playPicture[m_bChoose].otherP.setState((byte)1);
            Character((byte)11);
         }

         if (curForm.clientCommand == 2097155) {
            ta = (UILabel)curForm.getComponents().elementAt(37);
            PCGamble.m_nJetton = Integer.parseInt(ta.getText());
            if (SUPPORT_POINTER) {
               maxExpLabel = (UILabel)curForm.getComponents().elementAt(38);
               maxExpLabel.setText("");
            }
         }

         if (curForm.clientCommand == 1966100) {
            UITopForm.createLocalTopForm((byte)0, (String)"Hãy chọn bảo thạch muốn tháo.", "Xác nhận", "", -1, -2);
         }

         if (curForm.clientCommand == 1966086) {
            title = (UITitle)curForm.getComponents().elementAt(0);
            title.setTitleText("Khảm nạm bảo thạch");
            UITopForm.createLocalTopForm((byte)0, (String)"Hãy chọn 1 trang bị cần khảm nạm.", "Xác nhận", "", -1, -2);
         }

         if (curForm.clientCommand == 939349) {
            title = (UITitle)curForm.getComponents().elementAt(0);
            title.setTitleText("Sửa trang bị");
            UITopForm.createLocalTopForm((byte)0, (String)"Hãy chọn 1 trang bị cần sửa.", "Xác nhận", "", -1, -2);
         }

         if (curForm.clientCommand == 2031636 || curForm.clientCommand == 2031621) {
            ta = (UILabel)curForm.getComponents().elementAt(14);
            ta.setTextColor(Cons.COLOR_FONT_1);
            ta.setText("Đặt trang bị cần khai phong vào đây");
         }

         if (curForm.clientCommand == 3342356) {
            ta = (UILabel)curForm.getComponents().elementAt(14);
            ta.setTextColor(Cons.COLOR_FONT_1);
            ta.setText("Đặt trang bị cần khắc pháp bảo");
         }

         if (curForm.clientCommand == 917525) {
            ta = (UILabel)curForm.getComponents().elementAt(14);
            ta.setTextColor(Cons.COLOR_FONT_1);
            ta.setText("Đặt trang bị cần trang bị cần cường hóa");
         }

         if (curForm.clientCommand == 2031621) {
            ta = (UILabel)curForm.getComponents().elementAt(14);
            ta.setTextColor(Cons.COLOR_FONT_1);
            ta.setText("Đặt vào Nguyên Thạch");
         }

         if (curForm.clientCommand == 2228225) {
            ta = (UILabel)curForm.getComponents().elementAt(14);
            ta.setTextColor(Cons.COLOR_FONT_1);
            ta.setText("Đặt vào Nguyên Thạch");
         }

         if (curForm.clientCommand == 1966097) {
            ta = (UILabel)curForm.getComponents().elementAt(10);
            ta.setTextColor(Cons.COLOR_FONT_1);
            ta.setText("Đặt trang bị ở đây");
         }

         if (curForm.clientCommand == 1900547) {
            PCAuction.RetrieveGoods((byte)2);
         }

         UIRadio radio;
         if (curForm.clientCommand == 1703972) {
            radio = (UIRadio)curForm.getComponents().elementAt(4);
            radio.setSelectIndex((byte)1);
            radio.setSureIndex((byte)1);
         }

         if (curForm.clientCommand == 1703971) {
            title = (UITitle)curForm.getComponents().elementAt(1);
            if (PCMail.m_bContentment == 5) {
               title.setMenuText("", 1);
            }
         }

         UIPicture pic;
         UILabel label_1;
         if (curForm.clientCommand == 2555905) {
            if (backForms.size() > 0) {
               backForms.removeAllElements();
            }

            title = (UITitle)curForm.getComponents().elementAt(0);
            title.setTitleText(PCFarm.m_sFarmName);
            maxExpLabel = (UILabel)curForm.getComponents().elementAt(38);
            maxExpLabel.setText(PCFarm.m_sWeather);
            label_1 = (UILabel)curForm.getComponents().elementAt(3);
            UILabel label_1 = (UILabel)curForm.getComponents().elementAt(4);
            UILabel label_2 = (UILabel)curForm.getComponents().elementAt(6);
            UILabel label_3 = (UILabel)curForm.getComponents().elementAt(10);
            UILabel label_4 = (UILabel)curForm.getComponents().elementAt(8);
            pic = (UIPicture)curForm.getComponents().elementAt(11);
            label_1.setText(pic.getCropName());
            label_1.setText(pic.getGrowthPhase());
            label_2.setNum1(pic.getGrowthLimit());
            label_3.setText(pic.getCropStatus_2());
            PCFarm.m_nFarm_On_Off = 1;
            ni.send(2555952);
            if (PCFarm.m_bWhetherGetHome == 1) {
               UILabel label_5 = (UILabel)curForm.getComponents().elementAt(36);
               label_5.setText("");
            }

            if (pic.getGroundStatus() == -1) {
               label_4.setText(PCFarm.SOIL_CIRCS[0]);
            } else if (pic.getGroundStatus() == 0) {
               label_4.setText(PCFarm.SOIL_CIRCS[1]);
            } else if (pic.getGroundStatus() >= 1 && pic.getGroundStatus() <= 30) {
               label_4.setText(PCFarm.SOIL_CIRCS[2]);
            } else if (pic.getGroundStatus() >= 31 && pic.getGroundStatus() <= 70) {
               label_4.setText(PCFarm.SOIL_CIRCS[3]);
            } else if (pic.getGroundStatus() >= 71 && pic.getGroundStatus() <= 100) {
               label_4.setText(PCFarm.SOIL_CIRCS[4]);
            }
         }

         if (curForm.clientCommand == 2555906) {
            ta = (UILabel)curForm.getComponents().elementAt(10);
            ta.setText(PCFarm.m_sPropMane[0]);
         }

         if (curForm.clientCommand == 2555908) {
            title = (UITitle)curForm.getComponents().elementAt(0);
            title.setTitleText("种子商店");
         }

         if (curForm.clientCommand == 2555906 && PCFarm.m_bWhetherGetHome == 1) {
            title = (UITitle)curForm.getComponents().elementAt(1);
            title.setMenuText("Tra tìm", 0);
         }

         if (curForm.clientCommand == 2555936 && PCFarm.m_bMenuIndex == 4) {
            ta = (UILabel)curForm.getComponents().elementAt(8);
            maxExpLabel = (UILabel)curForm.getComponents().elementAt(9);
            maxExpLabel.setPositionX((short)(ta.getPositionX() + ta.getWidth() + 1));
         }

         if (curForm.clientCommand == 2424995 || curForm.clientCommand == 2424839 || curForm.clientCommand == 2425079) {
            UITextArea textarea = (UITextArea)curForm.getComponents().elementAt(15);
            textarea.setFocus(true, curForm);
            if (curForm.clientCommand == 2424995 && PCIncrement.m_bBuyMoney == 3 || curForm.clientCommand == 2425079 && PCIncrement.m_bBuyMoney == 8) {
               curForm.getComponents().removeElementAt(curForm.getComponents().size() - 1);
               curForm.getComponents().removeElementAt(17);
               maxExpLabel = (UILabel)curForm.getComponents().elementAt(17);
               label_1 = (UILabel)curForm.getComponents().elementAt(18);
               label_1.setPositionY(maxExpLabel.getPositionY());
            }
         }

         UITitle menuBar;
         if (curForm.clientCommand == 196641) {
            title = (UITitle)curForm.getComponents().elementAt(0);
            title.setTitleText("Thông tin nhân vật");
            UITextArea textarea = (UITextArea)curForm.getComponents().elementAt(3);
            menuBar = (UITitle)curForm.getComponents().elementAt(1);
            if (GOManager.m_bPlayersortNPC) {
               menuBar.setMenuText("Quay về", 1);
               textarea.m_bWhetherTime = false;
            } else {
               menuBar.setMenuText("Thoát", 1);
               textarea.m_bWhetherTime = true;
            }

            menuBar.setMenuText("Thao tác", 0);
         }

         if (curForm.clientCommand == 196642) {
            title = (UITitle)curForm.getComponents().elementAt(0);
            UITable table = (UITable)curForm.getComponents().elementAt(3);
            menuBar = (UITitle)curForm.getComponents().elementAt(1);
            if (GOManager.m_bBattlefieldMenuId == 1) {
               title.setTitleText("Bảng xếp hạng Thiên Nhân");
            } else if (GOManager.m_bBattlefieldMenuId == 2) {
               title.setTitleText("Bảng xếp hạng Tu La");
            } else if (GOManager.m_bBattlefieldMenuId == 3) {
               title.setTitleText("Bảng Xếp Hạng");
            }

            if (GOManager.m_bPlayersortNPC) {
               menuBar.setMenuText("Quay về", 1);
               table.m_bWhetherTime = false;
            } else {
               menuBar.setMenuText("Thoát", 1);
               table.m_bWhetherTime = true;
            }
         }

         if (curForm.clientCommand == 720920) {
            radio = (UIRadio)curForm.getComponents().elementAt(3);
            if (SITeam.m_bCopyDifficulty != 0) {
               radio.setSureIndex(SITeam.m_bCopyDifficulty);
               radio.setSelectIndex(SITeam.m_bCopyDifficulty);
            }
         }

         UILabel[] labels;
         int[] index;
         if (curForm.clientCommand == 852022) {
            index = new int[]{3, 4};
            labels = new UILabel[index.length];

            for(i = 0; i < index.length; ++i) {
               labels[i] = (UILabel)curForm.getComponents().elementAt(index[i]);
               labels[i].setHCenter(true);
            }
         }

         if (curForm.clientCommand == 3145731) {
            PCHang.Intercalate();
            index = new int[]{6, 10, 12};
            UIRadio[] radio = new UIRadio[index.length];

            for(i = 0; i < index.length; ++i) {
               radio[i] = (UIRadio)curForm.getComponents().elementAt(index[i]);
               radio[i].setSureIndex(PCHang.HANG_INTERCALATE[i]);
               radio[i].setSelectIndex(PCHang.HANG_INTERCALATE[i]);
            }

            UITextArea textarea = (UITextArea)curForm.getComponents().elementAt(13);
            textarea.setContent(PCHang.IntercalteContent[0]);
         }

         if (curForm.clientCommand == 3145729 || curForm.clientCommand == 3145730) {
            i = 0;

            label615:
            while(true) {
               short picID;
               if (i >= PCHang.m_bIntercalateSkillNum) {
                  if (curForm.clientCommand != 3145730) {
                     break;
                  }

                  ta = (UILabel)curForm.getComponents().elementAt(11);
                  short x = ta.getPositionX();
                  picID = (short)(ta.getHeight() + ta.getPositionY());
                  byte type = 0;
                  byte itemNum = (byte)PCHang.SkillTimeLetter.length;
                  UIComponent.isAdapt = false;
                  UIRadio radio = new UIRadio(x, picID, type, "", curForm);
                  UIComponent.isAdapt = true;

                  for(byte i = 0; i < itemNum; ++i) {
                     radio.add(PCHang.SkillTimeLetter[i]);
                     radio.setHorizontal(true);
                     radio.setWidth(radio.getAutoRadioWidth());
                     radio.setHeight(radio.getAutoRadioHeight());
                     radio.isCanUsed = new boolean[itemNum];

                     for(int k = 0; k < itemNum; ++k) {
                        radio.setCanUsed(k, true);
                     }
                  }

                  radio.setSureIndex(PCHang.SkillTime);
                  radio.setSelectIndex(PCHang.SkillTime);
                  curForm.addComponent(radio);
                  int i = 0;

                  while(true) {
                     if (i >= PCHang.WatchSkill.length) {
                        break label615;
                     }

                     pic = (UIPicture)curForm.getComponents().elementAt(i + 3);
                     pic.setAroundComponent((byte)1, (byte)14);
                     ++i;
                  }
               }

               picture = (UIPicture)curForm.getComponents().elementAt(i + 3);
               if (curForm.clientCommand == 3145729 && PCHang.AttackSkill[i] != -1 || curForm.clientCommand == 3145730 && PCHang.WatchSkill[i] != -1) {
                  picture.setShowNum(false, (byte)1);
                  if (PCHang.RstuffImgQuality[i] != 0) {
                     picture.quality = (byte)(PCHang.RstuffImgQuality[i] / 1000 - 1);
                  } else {
                     picture.quality = 0;
                  }

                  picID = (short)(PCHang.RstuffImgQuality[i] % 1000);
                  picture.isWpPic = true;
                  picture.wpIndex = picID;
                  picture.setImg(stuffMImg);
               }

               ++i;
            }
         }

         if (curForm.clientCommand == 3145739) {
            ta = (UILabel)curForm.getComponents().elementAt(5);
            ta.setHCenter(true);
         }

         if (curForm.clientCommand == 3145738) {
            ta = (UILabel)curForm.getComponents().elementAt(9);
            ta.setHCenter(true);
         }

         if (curForm.clientCommand == 2031648 || curForm.clientCommand == 2031649 || curForm.clientCommand == 2031650 || curForm.clientCommand == 2031651 || curForm.clientCommand == 3211269 || curForm.clientCommand == 3342361 || curForm.clientCommand == 917530 || curForm.clientCommand == 2228260) {
            title = (UITitle)curForm.getComponents().elementAt(0);
            if (curForm.clientCommand == 2031648) {
               title.setTitleText("Giải thích Khai Phong");
            } else if (curForm.clientCommand == 2031649) {
               title.setTitleText("Giải thích Điêu Khắc");
            } else if (curForm.clientCommand == 2031650) {
               title.setTitleText("Giải thích Hợp Thành");
            } else if (curForm.clientCommand == 2031651) {
               title.setTitleText("Giải thích Giải Trừ");
            } else if (curForm.clientCommand == 3211269) {
               title.setTitleText("Giải thích đổi Vật Phẩm");
            } else if (curForm.clientCommand == 2228260) {
               title.setTitleText("Giải thích Hoàn Nguyên");
            } else if (curForm.clientCommand == 3342361) {
               title.setTitleText("Giải thích Khắc Danh");
            } else if (curForm.clientCommand == 917530) {
               title.setTitleText("Giải thích Cường Hóa");
            }
         }

         if (curForm.clientCommand == 2425024) {
            UIForm.shoppingID = 0;
         }

         if (curForm.clientCommand == 2425078) {
            if (PCIncrement.m_bBuyMoney == 8) {
               curForm.getComponents().removeElementAt(curForm.getComponents().size() - 1);
               curForm.getComponents().removeElementAt(curForm.getComponents().size() - 1);
               ta = (UILabel)curForm.getComponents().elementAt(5);
               maxExpLabel = (UILabel)curForm.getComponents().elementAt(6);
               maxExpLabel.setPositionY(ta.getPositionY());
            }

            title = (UITitle)curForm.getComponents().elementAt(0);
            title.setTitleText(UIForm.shoppingList[PCIncrement.m_bBuyMoney - 8]);
         }

         if (curForm.clientCommand == 2228256) {
            ta = (UILabel)curForm.getComponents().elementAt(14);
            ta.setTextColor(Cons.COLOR_FONT_1);
            ta.setText("Đặt vào bảo thạch");
         }

         if (curForm.clientCommand == 1638445) {
            index = new int[]{3, 4, 5};
            labels = new UILabel[index.length];

            for(i = 0; i < index.length; ++i) {
               labels[i] = (UILabel)curForm.getComponents().elementAt(index[i]);
               labels[i].setHCenter(true);
            }
         }

         if ((curForm.clientCommand == 3342337 || curForm.clientCommand == 3342338 || curForm.clientCommand == 3342339 || curForm.clientCommand == 1900552 || curForm.clientCommand == 1703954 || curForm.clientCommand == 1703948 || curForm.clientCommand == 1376267 || curForm.clientCommand == 655363 || curForm.clientCommand == 393231) && PCTreasure.interphase) {
            ta = (UILabel)curForm.getComponents().elementAt(6);
            maxExpLabel = (UILabel)curForm.getComponents().elementAt(8);
            pic = (UIPicture)curForm.getComponents().elementAt(3);
            ta.setType((byte)7);
            maxExpLabel.setType((byte)7);
            ta.setLongNum(PCTreasure.curExpLabel);
            maxExpLabel.setLongNum(PCTreasure.maxExpLabel);
            pic.setLevel(PCTreasure.treasureGrade);
         }

         if (curForm.clientCommand == 3342340 && !PCTreasure.unlockMenu) {
            title = (UITitle)curForm.getComponents().elementAt(1);
            title.setMenuText("", 0);
         }

         if (curForm.clientCommand == 3342341) {
            title = (UITitle)curForm.getComponents().elementAt(1);
            title.setMenuText("Sử dụng", 0);
         }

         if (curForm.clientCommand == 3342341) {
            UITopForm.createLocalTopForm((byte)0, (String)"Hãy chọn 1 quyển sách Kỹ Năng.", "Xác nhận", "", -1, -2);
         }

         this.refeshUpdataUIEffect();
         addScrollStr();
         if (curForm.clientCommand == 655364) {
            AfficheForm = curForm;
            if (SUPPORT_POINTER) {
               ta = (UILabel)AfficheForm.getComponents().elementAt(4);
               ta.setText("Di chuyển bất kỳ vào trò chơi");
            }

            curForm = null;
            setGameState((byte)0);
         }
      } else {
         if (getState() == 17) {
            for(i = 0; i < playPicture.length; ++i) {
               if (playPicture[i] != null && playPicture[i].otherP != null) {
                  playPicture[i].otherP.motionState_tick();
               }
            }
         }

         if (getState() == 18) {
            playPicture[0].otherP.motionState_tick();
         }
      }

      if (cmdData != null) {
         curForm = ParseUI.parseCmdData(cmdData, cmdID);
         cmdID = -1;
         cmdData = null;
         isFullUserData = true;
         isWaiting = false;
         if (ni.isSendingCommands) {
            curFormVector.addElement(curForm);
            setCurForm((UIForm)curFormVector.elementAt(0));
            if (ni.commands.length > 0) {
               isWaiting = true;
               ni.sendCommands();
            } else {
               ni.isSendingCommands = false;
            }
         }

         if (getGameState() != 4) {
            if (getGameState() != 3) {
               setGameState((byte)4);
            } else {
               setGameStateWithoutOld((byte)4);
            }
         }
      }

      while(true) {
         while(popData.length != 0) {
            byte[] tmpPopData = popData[0];
            byte tmpPopLevel = popLevel[0];
            popData = Util.removeArray((byte[][])popData, 0);
            popLevel = Util.removeArray((byte[])popLevel, 0);
            if (getState() != 5) {
               if ((getState() == 14 || getState() == 15 || getState() == 18) && curTopForm != null) {
                  curTopForm = null;
               }

               curTopForm = ParseUI.parseTopForm(tmpPopData);
            } else {
               byte ACTION_BUFF = false;
               byte ACTION_SHOW = true;
               byte ACTION_NONE = true;
               byte topAction = 0;
               switch(tmpPopLevel) {
               case 0:
                  if (curTopForm == null) {
                     topAction = 1;
                  }
                  break;
               case 1:
                  if (curTopForm == null && getState() == 5 && getGameState() == 0) {
                     topAction = 1;
                  }
                  break;
               case 2:
                  if (curTopForm != null || getState() != 5 || getGameState() != 0 && getGameState() != 1) {
                     ParseUI.parseTopForm(tmpPopData);
                     topAction = 2;
                  } else {
                     topAction = 1;
                  }
                  break;
               case 3:
               case 4:
                  if (curTopForm != null && curTopForm.level == 4) {
                     topAction = 2;
                  } else {
                     topAction = 1;
                  }
               }

               switch(topAction) {
               case 0:
                  topFormBuff = Util.addArray(topFormBuff, topFormBuff.length, tmpPopData);
                  break;
               case 1:
                  curTopForm = ParseUI.parseTopForm(tmpPopData);
               }

               if (tmpPopLevel == 4) {
                  topFormBuff = new byte[0][];
               }
            }
         }

         if (partUIUserData != null) {
            ParseUI.parseUIPartData(curForm, partUIUserData);
            partUIUserData = null;
         }

         return;
      }
   }

   public void quake() {
      Player var10000;
      if (this._quakeTick == 0) {
         var10000 = Player.getInstance();
         var10000.y = (short)(var10000.y + 4);
         ++this._quakeTick;
      } else if (this._quakeTick == 1) {
         var10000 = Player.getInstance();
         var10000.y = (short)(var10000.y - 8);
         ++this._quakeTick;
      } else if (this._quakeTick == 2) {
         var10000 = Player.getInstance();
         var10000.y = (short)(var10000.y + 6);
         ++this._quakeTick;
      } else if (this._quakeTick == 3) {
         var10000 = Player.getInstance();
         var10000.y = (short)(var10000.y - 3);
         ++this._quakeTick;
      } else {
         ++Player.getInstance().y;
         this._quakeTick = 0;
      }

   }

   public byte getOldState() {
      return oldState;
   }

   public static byte getCurUIState() {
      return curUIState;
   }

   public static byte getCurUIInnerState() {
      return curUIInnerState;
   }

   public static void setCurUIInnerState(byte curUIInnerState) {
      MainCanvas.curUIInnerState = curUIInnerState;
   }

   public static void name_password() {
      name_password = Util.loadStrRecord("name_password", 2);
      if (name_password == null) {
         name_password = new String[2];
      }

      UITextField nameText = null;
      UITextField pswText = null;
      int i = 0;

      for(int n = curForm.getComponents().size(); i < n; ++i) {
         UIComponent uic = (UIComponent)curForm.getComponents().elementAt(i);
         if (uic instanceof UITextField) {
            UITextField tf = (UITextField)uic;
            if (tf.getStyle() == 0) {
               nameText = tf;
            } else if (tf.getStyle() == 2) {
               pswText = tf;
            }
         }
      }

      if (nameText != null && pswText != null) {
         nameText.setSb(new StringBuffer(name_password[0] == null ? "" : name_password[0]));
         pswText.setSb(new StringBuffer(name_password[1] == null ? "" : name_password[1]));
      }
   }

   public static void loadLoginType() {
      loginType = Util.loadByteRecord("login_type");
      if (loginType == null) {
         loginType = new byte[1];
      } else if (loginType[0] == 1) {
         UIRadio r = (UIRadio)curForm.getComponents().elementAt(10);
         r.setSelectIndex((byte)1);
      }

   }

   public static void saveLoginType() {
      UIRadio r = (UIRadio)curForm.getComponents().elementAt(10);
      if (loginType == null) {
         loginType = new byte[1];
      }

      loginType[0] = r.getSelectIndex();
      Util.saveByteRecord(loginType, "login_type");
   }

   public byte EnrolValidate(String name, String password, String password_validate) {
      if (!name.equals("") && !password.equals("") && !password_validate.equals("")) {
         if (Util.checkLogin(name) && Util.checkLogin(password)) {
            if (!password.equals(password_validate)) {
               return 3;
            } else if (!CharacterValidate(name, (byte)1)) {
               return 4;
            } else {
               return (byte)(!CharacterValidate(password, (byte)1) ? 5 : 0);
            }
         } else {
            return 2;
         }
      } else {
         return 1;
      }
   }

   public static void TopForm(byte type, String str, String button1, String button2, int command1, int command2) {
      curTopForm = new UITopForm(type, (UIForm)null);
      curTopForm.setTopFormInfo((byte)5, Cons.COLOR_PANEL_BORDER_3, str, button1, button2, command1, command2);
   }

   public static void closeConnection() {
      isSendOver = true;
      ni.send(65543);
      mc.exitGame((byte)1);
   }

   public void exitGame(byte type) {
      if (rightMenu != null) {
         rightMenu.savePositionIDStack.removeAllElements();
         rightMenu = null;
      }

      if (m_bTourist == 0) {
         if (mc.m_bPlace[m_bChooseRen] == 1 && mc.m_bconfirm[m_bChooseRen] == 1) {
            mc.Debarkation = false;
         }

         mc.m_bPlace[m_bChooseRen] = 0;
         mc.m_bconfirm[m_bChooseRen] = 0;
         Util.saveRecord(this.m_bPlace, "debarkation");
         Util.saveRecord(this.m_bconfirm, "debarkation1");
      } else {
         m_bTourist = 0;
      }

      SIChat.anounceDX = screenW;
      Map.clear();
      GOManager.removeAll();
      quitUI();
      Player.clear();
      SIManager.clear();
      Liquidate();
      Cleanup_part();
      switch(type) {
      case 1:
         setState((byte)4);
      case 0:
      default:
      }
   }

   public void Abnormity() {
      if (m_bTourist != 0) {
         m_bTourist = 0;
      }

      if (rightMenu != null) {
         rightMenu.savePositionIDStack.removeAllElements();
         rightMenu = null;
      }

      SIChat.anounceDX = screenW;
      Map.clear();
      GOManager.removeAll();
      Player.clear();
      SIManager.clear();
      quitUI();
      NetInterface.getInstance().closeConn();
      PCAuction.LiquidationBata();
      PCGem.LiquidationBata();
      PCMail.LiquidationBata();
      PCUnsealGemCarve.LiquidationBata();
      PCFarm.Initialization();
      if (UITopForm.isLoginErr) {
         setState((byte)11);
         UITopForm.isLoginErr = false;
      } else {
         popData = new byte[0][];
         popLevel = new byte[0];
         setState((byte)4);
      }

      if (curTopForm != null) {
         UITopForm.removeAllTopForm();
      }

      checkServerUrl();
      if (Map.m_bBattlefield) {
         Map.m_bBattlefield = false;
      }

      if (GOManager.m_bBattlefieldMenuId != 3) {
         GOManager.m_bBattlefieldMenuId = 3;
      }

      Map.flagID = new Vector();
      if (SITeam.m_bCopyDifficulty != 0) {
         SITeam.m_bCopyDifficulty = 0;
      }

      if (PCHang.m_bHang) {
         PCHang.m_bHang = false;
      }

      PCTreasure.intermit();
      PCTreasure.backtrack();
   }

   public static void checkServerUrl() {
      try {
         String tmpUrl = aMidlet.getAppProperty("url");
         if (tmpUrl != null && !tmpUrl.trim().equals("")) {
            NetInterface.serverUrl = tmpUrl.trim();
         }
      } catch (Exception var1) {
      }

   }

   public static void checkJumpID() {
      try {
         String tmpJumpID = aMidlet.getAppProperty("jumpID");
         if (tmpJumpID != null && !tmpJumpID.trim().equals("")) {
            jumpID = Byte.parseByte(tmpJumpID);
         } else if (isChinaMobileVer) {
            jumpID = 24;
         } else {
            jumpID = 22;
         }
      } catch (Exception var1) {
      }

   }

   public static void quitUI() {
      setGameState(oldGameState);
      backForms.removeAllElements();
      isFullUserData = true;
      curFormVector.removeAllElements();
      curForm = null;
      replaceFormId = -1;
      stayFormId = -1;
      UIForm.lockIndex = -1;
      isWaiting = false;
      if (Player.getInstance() != null) {
         Player.getInstance().setAttacked(false);
      }

      NPC.isDrawTopTip = false;
      PCPackage.needExtend = false;
   }

   public byte Searches(String str) {
      if (str.equals("")) {
         return 1;
      } else if (!CharacterValidate(str, (byte)2)) {
         return 2;
      } else {
         for(int j = 0; j < 3; ++j) {
            if (m_sCharacterName[j] != null && m_sCharacterName[j].equals(str)) {
               return 3;
            }
         }

         return 0;
      }
   }

   public static void Liquidate() {
      m_bChooseCounter = 0;

      for(int i = 0; i < playPicture.length; ++i) {
         m_nCharacterID[i] = 0;
         m_sCharacterName[i] = null;
         m_nGrade[i] = 0;
         m_sPhyle[i] = 0;
         m_bProfession[i] = 0;
         m_nScene[i] = null;
         m_bDelete[i] = 0;
         m_bAmendNickname[i] = false;
      }

   }

   public static void Cleanup_part() {
      m_bCamp = 1;
      m_bPhyle = 1;
      m_bGender = 0;
      m_bVocation = 1;
      m_sName = null;
   }

   public static void random_part() {
      m_bCamp = (byte)Util.genRandom(1, 2);
      if (m_bCamp == 1) {
         m_bPhyle = (byte)Util.genRandom(1, 2);
      } else if (m_bCamp == 2) {
         m_bPhyle = (byte)Util.genRandom(3, 4);
      }

      m_bGender = (byte)Util.genRandom(0, 1);
      switch(m_bPhyle) {
      case 1:
         m_bVocation = humanCampVocation[Util.genRandom(0, humanCampVocation.length - 1)];
         break;
      case 2:
         m_bVocation = (byte)Util.genRandom(2, 5);
         break;
      case 3:
         m_bVocation = (byte)Util.genRandom(1, 5);
         break;
      case 4:
         m_bVocation = evilCampVocation[Util.genRandom(0, evilCampVocation.length - 1)];
      }

   }

   public static void CleanupNamePassword() {
      for(int i = 0; i < 4; ++i) {
         m_sTemporaryStr[i] = null;
      }

   }

   public static void Character(byte sum) {
      UILabel label_1 = (UILabel)curForm.getComponents().elementAt(sum);
      label_1.setText(m_sCharacterName[m_bChoose]);
      String dengji = "" + m_nGrade[m_bChoose];
      UILabel label_2 = (UILabel)curForm.getComponents().elementAt(sum + 1);
      label_2.setText(dengji);
      String str = "";
      str = Cons.PHYLE[m_sPhyle[m_bChoose] - 1];
      UILabel label_3 = (UILabel)curForm.getComponents().elementAt(sum + 2);
      label_3.setText(str);
      String zhiye = "";
      zhiye = Cons.PROFESSION[m_bProfession[m_bChoose] - 1];
      UILabel label_4 = (UILabel)curForm.getComponents().elementAt(sum + 3);
      label_4.setText((m_bGenderX[m_bChoose] == 0 ? "Nam" : "Nữ") + zhiye);
      UILabel label_5 = (UILabel)curForm.getComponents().elementAt(sum + 4);
      label_5.setText(m_nScene[m_bChoose]);
   }

   private void taskTimeTick() {
      long second = (System.currentTimeMillis() - UITextArea.taskStartTime) / 1000L;
      if (second >= 1L) {
         UITextArea.taskStartTime = System.currentTimeMillis();
         if (UITextArea.taskSecond > 0) {
            --UITextArea.taskSecond;
         } else if (UITextArea.taskMinitue > 0) {
            --UITextArea.taskMinitue;
            UITextArea.taskSecond = 59;
         }

      }
   }

   private void validateTick() {
      if (PassPort.isEndLoginValidate) {
         if (!GameLogin.downloadAddress.equals("")) {
            if (curTopForm != null) {
               curTopForm = null;
            }

            if (this.isTestNum != 1) {
               TopForm((byte)0, "Phiên bản không phải mới nhất, nhưng vẫn có thể dùng, có muốn cập nhật hay không", "Tải về", "Tiếp tục", -8, -2);
            }
         } else {
            setState((byte)12);
         }

         PassPort.isEndLoginValidate = false;
      }

      if (PassPort.isEndRegisterValidate) {
         if (curTopForm != null && getState() != 4) {
            curTopForm = null;
         }

         if (PassPort.isQuick) {
            name_password[0] = PassPort.suggestion.equals("") ? name_password[0] : PassPort.suggestion;
            name_password[1] = PassPort.suggestion.equals("") ? name_password[1] : PassPort.suggestion;
         }

         String str;
         if (m_bTourist == 1) {
            str = "Tài khoản là: " + name_password[0] + "\n" + "Mật khẩu là:" + name_password[1] + "\n";
            TopForm((byte)0, str, "Xác nhận", "", -25, -2);
         } else if (PassPort.isQuick) {
            if (PassPort.isQuickSuccess) {
               PassPort.isQuickSuccess = false;
               str = "Tài khoản là: " + name_password[0] + "\n" + "Mật khẩu là:" + name_password[1] + "\n";
               TopForm((byte)0, str, "Xác nhận", "Hủy bỏ", -31, -17);
            }
         } else {
            TopForm((byte)0, PassPort.resultInfo, Cons.C_STR[2], "", -30, -120);
         }

         PassPort.isEndRegisterValidate = false;
      }

      if (PassPort.isEndChangePswValidate) {
         if (curTopForm != null) {
            curTopForm = null;
         }

         PassPort.isEndChangePswValidate = false;
      }

      if (PassPort.isEndQuickRegisterValidate) {
         if (curTopForm != null) {
            curTopForm = null;
         }

         if (m_bTourist == 1) {
            UITextField userNameText_1 = (UITextField)curForm.getComponents().elementAt(9);
            UITextField userPswText_1 = (UITextField)curForm.getComponents().elementAt(10);
            if (PassPort.isQuick) {
               userNameText_1.setSb(new StringBuffer(PassPort.suggestion));
               userPswText_1.setSb(new StringBuffer(PassPort.suggestion));
               name_password[0] = userNameText_1.getSb().toString();
               name_password[1] = userPswText_1.getSb().toString();
               PassPort.isQuick = false;
            }
         } else {
            setState((byte)14);
         }

         PassPort.isEndQuickRegisterValidate = false;
      }

      if (PassPort.m_bBingValidate) {
         if (PCIncrement.m_bNote == 2) {
            if (PassPort.getBindDetail()) {
               TopForm((byte)0, "Tài khoản đã khóa", "Xác nhận", "", -1, -120);
               PassPort.m_bBingValidate = false;
               return;
            }

            PCIncrement.SendNote((byte)2);
            PassPort.m_bBingValidate = false;
         } else if (PCIncrement.m_bNote == 3) {
            if (!PassPort.getBindDetail()) {
               TopForm((byte)0, "Tài khoản chưa cố định", "Xác nhận", "", -1, -120);
               PassPort.m_bBingValidate = false;
               return;
            }

            PCIncrement.SendNote((byte)3);
            PassPort.m_bBingValidate = false;
         }
      }

      if (PassPort.isEndChargeValidate) {
         if (curTopForm != null) {
            curTopForm = null;
         }

         setState((byte)32);
         PassPort.isEndChargeValidate = false;
      }

      if (PassPort.isEndRemainValidate) {
         if (curTopForm != null) {
            curTopForm = null;
         }

         if (PassPort.isDetail) {
            setState((byte)34);
         } else {
            setState((byte)33);
         }

         PassPort.isEndRemainValidate = false;
      }

      if (PassPort.isEndChargeHistoryValidate) {
         if (curTopForm != null) {
            curTopForm = null;
         }

         setState((byte)38);
         PassPort.isEndChargeHistoryValidate = false;
      }

      if (PassPort.isEndBuyHistoryValidate) {
         if (curTopForm != null) {
            curTopForm = null;
         }

         setState((byte)38);
         PassPort.isEndBuyHistoryValidate = false;
      }

   }

   public static void Equip(boolean read) {
      byte[] bodywhere;
      short[][] pf;
      if (m_bVocation == 1) {
         bodywhere = new byte[]{2, 3, 4, 5};
         pf = new short[][]{{291, 292, 293, 294, 295, 296, 297, 298, 299}, {345, 346, 347, 348}, {175}, {98, 99}};
         playPicture[0].otherP.changeEquip(bodywhere, pf);
      } else if (m_bVocation == 2) {
         bodywhere = new byte[]{2, 3, 4, 6};
         pf = new short[][]{{336, 337, 338, 339, 340, 341, 342, 343, 344}, {365, 366, 367, 368}, {151}, {399}};
         playPicture[0].otherP.changeEquip(bodywhere, pf);
      } else if (m_bVocation != 3 && m_bVocation != 4 && m_bVocation != 6) {
         if (m_bVocation == 5) {
            bodywhere = new byte[]{2, 3, 4};
            pf = new short[][]{{336, 337, 338, 339, 340, 341, 342, 343, 344}, {365, 366, 367, 368}, {151}};
            playPicture[0].otherP.changeEquip(bodywhere, pf);
         }
      } else {
         bodywhere = new byte[]{2, 3, 4};
         pf = new short[][]{{482, 483, 484, 485, 486, 487, 488, 489, 490}, {378, 379, 380, 381}, {158}};
         playPicture[0].otherP.changeEquip(bodywhere, pf);
      }

      if (read) {
         UITitle menuBar = (UITitle)curForm.getComponents().elementAt(1);
         UIRadio camp = (UIRadio)curForm.getComponents().elementAt(5);
         UIRadio phyle = (UIRadio)curForm.getComponents().elementAt(6);
         UIRadio gender = (UIRadio)curForm.getComponents().elementAt(7);
         UIRadio vocation = (UIRadio)curForm.getComponents().elementAt(8);
         UITextField textfield = (UITextField)curForm.getComponents().elementAt(9);
         camp.setSelectIndex((byte)(m_bCamp - 1));
         phyle.setSelectIndex((byte)(m_bPhyle - 1));
         gender.setSelectIndex(m_bGender);
         vocation.setSelectIndex((byte)(m_bVocation - 1));
         if (focus == 0) {
            camp.setFocus(true, curForm);
            phyle.setFocus(false, curForm);
            gender.setFocus(false, curForm);
            vocation.setFocus(false, curForm);
            textfield.setFocus(false, curForm);
         } else if (focus == 1) {
            camp.setFocus(false, curForm);
            phyle.setFocus(true, curForm);
            gender.setFocus(false, curForm);
            vocation.setFocus(false, curForm);
            textfield.setFocus(false, curForm);
         } else if (focus == 2) {
            camp.setFocus(false, curForm);
            phyle.setFocus(false, curForm);
            gender.setFocus(true, curForm);
            vocation.setFocus(false, curForm);
            textfield.setFocus(false, curForm);
            menuBar.setMenuText("", 0);
         } else if (focus == 3) {
            camp.setFocus(false, curForm);
            phyle.setFocus(false, curForm);
            gender.setFocus(false, curForm);
            vocation.setFocus(true, curForm);
            textfield.setFocus(false, curForm);
            vocation.width = vocation.getAutoRadioWidth();
         } else if (focus == 4) {
            camp.setFocus(false, curForm);
            phyle.setFocus(false, curForm);
            gender.setFocus(false, curForm);
            vocation.setFocus(false, curForm);
            textfield.setFocus(true, curForm);
            if (textfield.getSb().toString().trim() != null && !textfield.getSb().toString().trim().trim().equals("")) {
               menuBar.setMenuText("Sửa", 0);
            } else {
               menuBar.setMenuText("Nhập vào", 0);
            }
         }

         if (m_sName != null && !m_sName.equals("")) {
            textfield.setSb(new StringBuffer(m_sName));
         } else {
            textfield.setSb(new StringBuffer(""));
         }
      }

   }

   private int transformVerStr(String verStr) {
      int result = 0;
      if (verStr != null && !verStr.equals("")) {
         String[] digitStrs = Util.splitToken(verStr, '.');
         byte[] digits = new byte[digitStrs.length];

         try {
            for(int i = 0; i < digitStrs.length; ++i) {
               digits[i] = Byte.parseByte(digitStrs[i]);
            }
         } catch (Exception var6) {
            return 0;
         }

         result = digits[0] << 24;
         result += digits[1] << 16;
         result += digits[2] << 8;
         result += digits[3];
      }

      return result;
   }

   public void touchScreenAction() {
      if (SUPPORT_POINTER && isInputDown(268435456)) {
         switch(getState()) {
         case 4:
            this.pointMenu();
            break;
         case 5:
            switch(getGameState()) {
            case 5:
               if (UIMenu.npcMenu != null && !isWaiting && UIMenu.npcMenu.pointIndex()) {
                  keySimPressed(131072);
               }
               break;
            case 6:
               if (UIMenu.otherPlayerMenu != null && UIMenu.otherPlayerMenu.pointIndex()) {
                  keySimPressed(131072);
               }
            }

            if (AfficheForm != null) {
               UITextArea ta = (UITextArea)AfficheForm.getComponents().elementAt(3);
               if (!ta.focusWidgetPointAction() && isPointInRect(0, 0, screenW, screenH)) {
                  keySimPressed(262144);
               }
            }
         case 6:
         default:
            break;
         case 7:
            if (isChinaMobileVer) {
               int w = this.imgButton[0].getWidth();
               int h = this.imgButton[0].getHeight();
               if (isPointInRect(3, screenH - 3 - h, w, h)) {
                  keySimPressed(131072);
               }

               if (isPointInRect(screenW - 3 - w, screenH - 3 - h, w, h)) {
                  keySimPressed(262144);
               }
            }
            break;
         case 8:
            KeyConfig.touchScreenAction();
         }
      }

   }

   private void drawExit(Graphics g) {
      if (this.imgBg == null) {
         this.imgBg = Util.loadImage("/cm/lastbg.png");
         this.imgWhiteBar = Util.loadImage("/cm/whitebar.png");
         this.imgButton[0] = Util.loadImage("/cm/buttong.png");
         this.imgButton[1] = Util.loadImage("/cm/buttonr.png");
      }

      switch(this.exitState) {
      case 0:
         if (this.imgWhiteBar != null) {
            g.drawImage(this.imgWhiteBar, screenW >> 1, screenH - 35, 17);
         }

         g.drawImage(this.imgButton[0], 3, screenH - 3, 36);
         g.drawImage(this.imgButton[1], screenW - 3, screenH - 3, 40);
         g.setColor(16777215);
         g.drawString("Xác định Thoát", screenW >> 1, screenH >> 1, 17);
         g.drawString("Không", 13, screenH - 5, 36);
         g.drawString("Có", screenW - 13, screenH - 5, 40);
         break;
      case 1:
         if (this.imgBg != null) {
            g.drawImage(this.imgBg, screenW >> 1, screenH >> 1, 3);
         }

         if (this.imgWhiteBar != null) {
            g.drawImage(this.imgWhiteBar, screenW >> 1, screenH - 35, 17);
         }

         g.drawImage(this.imgButton[1], 3, screenH - 3, 36);
         g.drawImage(this.imgButton[0], screenW - 3, screenH - 3, 40);
         g.setColor(16777215);
         g.drawString("Các trò chơi khác", screenW >> 1, (screenH >> 1) - 20, 17);
         g.drawString("Kênh trò chơi", screenW >> 1, screenH >> 1, 17);
         g.drawString("wap.xjoys.com", screenW >> 1, (screenH >> 1) + 20, 17);
         g.drawString("Xác định ", 9, screenH - 5, 36);
         g.drawString("Thoát", screenW - 9, screenH - 5, 40);
      }

   }

   void releseExitAll() {
      this.imgBg = null;

      for(int i = 0; i < this.imgButton.length; ++i) {
         this.imgButton[i] = null;
      }

   }

   private void keyInExit() {
      switch(this.exitState) {
      case 0:
         if (isInputDown(131072)) {
            setState((byte)4);
            this.releseExitAll();
         } else if (isInputDown(262144)) {
            this.exitState = 1;
         }
         break;
      case 1:
         if (isInputDown(131072)) {
            try {
               aMidlet.platformRequest("http://vibox.vn/pt");
               aMidlet.destroyApp(true);
            } catch (Exception var2) {
            }

            this.releseExitAll();
         } else if (isInputDown(262144)) {
            this.stop();
            aMidlet.exitMIDlet();
         }
      }

   }

   private boolean haveLoginRecord() {
      name_password = Util.loadStrRecord("name_password", 2);
      return name_password != null;
   }

   private void readUserJAD() {
      name_password = Util.loadStrRecord("name_password", 2);
      if (name_password == null) {
         String userName = aMidlet.getAppProperty("username");
         String userPsw = aMidlet.getAppProperty("userpsw");
         if (userName != null && !"".equals(userName) && userPsw != null && !"".equals(userPsw)) {
            name_password = new String[]{userName, userPsw};
            Util.saveStrRecord(name_password, "name_password");
         }
      }

   }

   private boolean HangTouch() {
      if (SUPPORT_POINTER && rightMenu == null) {
         int x = screenW - touchMenu.img.getWidth() >> 1;
         int y = 2;
         int w = 196;
         int h = touchMenu.frame_h;
         int x_1 = 0;
         int y_1 = screenH - 36 - SIChat.buff.getHeight();
         int w_1 = screenW;
         int h_1 = SIChat.buff.getHeight();
         if (pointDownInRect(x, y, w, h) || pointDownInRect(x_1, y_1, w_1, h_1)) {
            return true;
         }
      }

      return false;
   }

   public static String getUserName() {
      return name_password[0];
   }

   public static String getLoginToken() {
      loginType = Util.loadByteRecord("login_type");
      if (loginType == null) {
         return "ko";
      } else {
         return loginType[0] == 1 ? "k" : "ko";
      }
   }

   public static long getTimeByEmulatorType() {
      long tempTime = 0L;
      if (emulatorType != 0) {
         tempTime = (new Date()).getTime();
      } else {
         tempTime = System.currentTimeMillis();
      }

      return tempTime;
   }

   private static class mapLoading implements Runnable {
      private Thread self = new Thread(this);

      public mapLoading() {
         this.self.start();
      }

      public void stop() {
         this.self = null;
      }

      public final void run() {
         Thread currentThread = Thread.currentThread();

         while(this.self == currentThread) {
            try {
               MainCanvas.mc.repaint();
               synchronized(this) {
                  this.wait(101L);
               }
            } catch (Exception var4) {
            }
         }

      }
   }
}

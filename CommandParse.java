public class CommandParse {
   private static final byte TYPE_JLAP = 0;
   private static final byte TYPE_LOGIN = 1;
   private static final byte TYPE_MAP = 2;
   private static final byte TYPE_OBJECT = 3;
   private static final byte TYPE_TASK = 4;
   private static final byte TYPE_UI = 5;
   private static final byte TYPE_PLAYER_BASE = 6;
   private static final byte TYPE_PACKAGE = 7;
   private static final byte TYPE_GENIUS = 8;
   private static final byte TYPE_SOCIAL = 9;
   private static final byte TYPE_CHAT = 10;
   private static final byte TYPE_TEAM = 11;
   public static final byte TYPE_BULLETIN = 12;
   private static final byte TYPE_NPC = 13;
   private static final byte TYPE_EQUIP = 14;
   private static final byte TYPE_SHOP = 15;
   private static final byte TYPE_REG = 17;
   private static final byte TYPE_STORAGE = 18;
   private static final byte TYPE_COMPARE = 19;
   public static final byte TYPE_BATTLETEAM = 20;
   private static final byte TYPE_TRADE = 21;
   public static final byte TYPE_LOVER = 22;
   public static final byte TYPE_RANK = 23;
   private static final byte TYPE_COLLECT = 24;
   private static final byte TYPE_CLAN = 25;
   public static final byte TYPE_MAIL = 26;
   public static final byte TYPE_CHANGE = 27;
   public static final byte TYPE_KING = 28;
   public static final byte TYPE_AUCTION = 29;
   public static final byte TYPE_GEM = 30;
   public static final byte TYPE_UNSEAL = 31;
   public static final byte TYPE_GAMBLE = 32;
   public static final byte TYPE_PRODUCE = 33;
   public static final byte TYPE_GEM_SYNTHESIZE_REMOVE = 34;
   public static final byte TYPE_MOVEMENT = 35;
   public static final byte TYPE_EXCHANGE = 36;
   public static final byte TYPE_INCREMENT = 37;
   public static final byte TYPE_MENTOR = 38;
   public static final byte TYPE_FARM = 39;
   public static final byte TYPE_CHARGE = 40;
   public static final byte TYPE_MONTHLY = 41;
   public static final byte TYPE_HANG = 48;
   public static final byte TYPE_PROP_CALLBACK = 49;
   public static final byte TYPE_TREASURE = 51;
   public static final byte TYPE_BROTHER = 52;
   private static CommandParse instance = null;
   static short dl = 0;
   static byte nSCFlag = 0;
   static byte type = 0;
   static int nCmd = 0;

   public static CommandParse getInstance() {
      if (instance == null) {
         instance = new CommandParse();
      }

      return instance;
   }

   public final byte[] send_Parse(int c_Cmd) {
      byte[] tmp = (byte[])null;
      byte ccc = getType(c_Cmd);
      MainCanvas.oldForm = MainCanvas.curForm;

      try {
         switch(ccc) {
         case 0:
         case 1:
         case 17:
            tmp = GameLogin.snd_Parse(c_Cmd);
            break;
         case 2:
            tmp = Map.snd_Parse(c_Cmd);
            break;
         case 3:
         case 13:
            tmp = GOManager.getInstance().snd_Parse(c_Cmd);
            break;
         case 4:
            tmp = PCTask.snd_Parse(c_Cmd);
         case 5:
         case 8:
         case 12:
         case 16:
         case 20:
         case 42:
         case 43:
         case 44:
         case 45:
         case 46:
         case 47:
         case 50:
         default:
            break;
         case 6:
            tmp = PCPlayerBase.snd_Parse(c_Cmd);
            break;
         case 7:
            tmp = PCPackage.snd_Parse(c_Cmd);
            break;
         case 9:
            tmp = PCSocial.snd_Parse(c_Cmd);
            break;
         case 10:
            tmp = SIChat.snd_Parse(c_Cmd);
            break;
         case 11:
            tmp = SITeam.snd_Parse(c_Cmd);
            break;
         case 14:
            tmp = PCEquip.snd_Parse(c_Cmd);
            break;
         case 15:
            tmp = PCShop.snd_Parse(c_Cmd);
            break;
         case 18:
            tmp = PCStorage.snd_Parse(c_Cmd);
            break;
         case 19:
            tmp = PCCompare.snd_Parse(c_Cmd);
            break;
         case 21:
            tmp = PCTrade.snd_Parse(c_Cmd);
            break;
         case 22:
            tmp = PCLover.snd_Parse(c_Cmd);
            break;
         case 23:
            tmp = PCRank.snd_Parse(c_Cmd);
            break;
         case 24:
            tmp = PCSkill.snd_Parse(c_Cmd);
            break;
         case 25:
            tmp = PCClan.snd_Parse(c_Cmd);
            break;
         case 26:
            tmp = PCMail.snd_Parse(c_Cmd);
            break;
         case 27:
            tmp = PCChange.snd_Parse(c_Cmd);
            break;
         case 28:
            tmp = PCKing.snd_Parse(c_Cmd);
            break;
         case 29:
            tmp = PCAuction.snd_Parse(c_Cmd);
            break;
         case 30:
            tmp = PCGem.snd_Parse(c_Cmd);
            break;
         case 31:
            tmp = PCUnsealGemCarve.snd_Parse(c_Cmd);
            break;
         case 32:
            tmp = PCGamble.snd_Parse(c_Cmd);
            break;
         case 33:
            tmp = PCProduce.snd_Parse(c_Cmd);
            break;
         case 34:
            tmp = PCGemJoinOrRemove.snd_Parse(c_Cmd);
            break;
         case 35:
            tmp = PCMovement.snd_Parse(c_Cmd);
            break;
         case 36:
            tmp = PCExchange.snd_Parse(c_Cmd);
            break;
         case 37:
            tmp = PCIncrement.snd_Parse(c_Cmd);
            break;
         case 38:
            tmp = PCMentor.snd_Parse(c_Cmd);
            break;
         case 39:
            tmp = PCFarm.snd_Parse(c_Cmd);
            break;
         case 40:
            tmp = PCCharge.snd_Parse(c_Cmd);
            break;
         case 41:
            tmp = PCMonthly.snd_Parse(c_Cmd);
            break;
         case 48:
            tmp = PCHang.snd_Parse(c_Cmd);
            break;
         case 49:
            tmp = PCPropCallback.snd_Parse(c_Cmd);
            break;
         case 51:
            tmp = PCTreasure.snd_Parse(c_Cmd);
            break;
         case 52:
            tmp = PCBrother.snd_Parse(c_Cmd);
         }
      } catch (Exception var5) {
      }

      return tmp;
   }

   public final void receive_Parse(ByteArray data) {
      dl = data.readShort();
      nCmd = data.readInt();

      try {
         switch(getType(nCmd)) {
         case 0:
         case 1:
         case 17:
            GameLogin.rec_Parse(nCmd, data);
            break;
         case 2:
            Map.rec_Parse(nCmd, data);
            break;
         case 3:
         case 13:
            GOManager.getInstance().rec_Parse(nCmd, data);
            break;
         case 4:
            PCTask.rec_Parse(nCmd, data);
            break;
         case 5:
            PCUI.rec_Parse(nCmd, data);
            break;
         case 6:
            PCPlayerBase.rec_Parse(nCmd, data);
            break;
         case 7:
            PCPackage.rec_Parse(nCmd, data);
         case 8:
         case 12:
         case 15:
         case 16:
         case 20:
         case 42:
         case 43:
         case 44:
         case 45:
         case 46:
         case 47:
         case 50:
         default:
            break;
         case 9:
            PCSocial.rec_Parse(nCmd, data);
            break;
         case 10:
            SIChat.rec_Parse(nCmd, data);
            break;
         case 11:
            SITeam.rec_Parse(nCmd, data);
            break;
         case 14:
            PCEquip.rec_Parse(nCmd, data);
            break;
         case 18:
            PCStorage.rec_Parse(nCmd, data);
            break;
         case 19:
            PCCompare.rec_Parse(nCmd, data);
            break;
         case 21:
            PCTrade.rec_Parse(nCmd, data);
            break;
         case 22:
            PCLover.rec_Parse(nCmd, data);
            break;
         case 23:
            PCRank.rec_Parse(nCmd, data);
            break;
         case 24:
            PCSkill.rec_Parse(nCmd, data);
            break;
         case 25:
            PCClan.rec_Parse(nCmd, data);
            break;
         case 26:
            PCMail.rec_Parse(nCmd, data);
            break;
         case 27:
            PCChange.rec_Parse(nCmd, data);
            break;
         case 28:
            PCKing.rec_Parse(nCmd, data);
            break;
         case 29:
            PCAuction.rec_Parse(nCmd, data);
            break;
         case 30:
            PCGem.rec_Parse(nCmd, data);
            break;
         case 31:
            PCUnsealGemCarve.rec_Parse(nCmd, data);
            break;
         case 32:
            PCGamble.rec_Parse(nCmd, data);
            break;
         case 33:
            PCProduce.rec_Parse(nCmd, data);
            break;
         case 34:
            PCGemJoinOrRemove.rec_Parse(nCmd, data);
            break;
         case 35:
            PCMovement.rec_Parse(nCmd, data);
            break;
         case 36:
            PCExchange.rec_Parse(nCmd, data);
            break;
         case 37:
            PCIncrement.rec_Parse(nCmd, data);
            break;
         case 38:
            PCMentor.rec_Parse(nCmd, data);
            break;
         case 39:
            PCFarm.rec_Parse(nCmd, data);
            break;
         case 40:
            PCCharge.rec_Parse(nCmd, data);
            break;
         case 41:
            PCMonthly.rec_Parse(nCmd, data);
            break;
         case 48:
            PCHang.rec_Parse(nCmd, data);
            break;
         case 49:
            PCPropCallback.rec_Parse(nCmd, data);
            break;
         case 51:
            PCTreasure.rec_Parse(nCmd, data);
            break;
         case 52:
            PCBrother.rec_Parse(nCmd, data);
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public final boolean receiveOperate(ByteArray data) {
      dl = data.readShort();
      nCmd = data.readInt();
      boolean operate = false;
      switch(getType(nCmd)) {
      case 5:
         operate = true;
      default:
         return operate;
      }
   }

   public static byte getType(int num) {
      return (byte)(num >> 16);
   }
}

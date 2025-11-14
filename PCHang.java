public class PCHang {
   public static boolean m_bHang;
   public static byte[] HANG_INTERCALATE;
   public static byte m_bSkillIndex;
   public static int[] AttackSkill;
   public static int[] WatchSkill;
   public static byte SkillTime;
   public static String[] SkillTimeLetter;
   public static String[] IntercalteContent;
   public static short m_nSkillNum = 0;
   public static int[] H_stuffId;
   public static String[] H_stuffName;
   public static short[] H_stuffImgQuality;
   public static short[] H_stuffImageId;
   public static byte[] H_stuffQuality;
   public static short[] RstuffImgQuality;
   public static byte m_bIntercalateSkillNum;
   public static byte m_bIncrementGenreIndex;
   public static byte m_bIncrementIndex;
   public static byte[] m_bIncrementGenre;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      int i;
      switch(commID) {
      case 3145729:
      case 3145730:
      case 3145731:
      case 3145732:
      case 3145734:
      case 3145735:
      case 3145738:
      case 3145741:
         MainCanvas.isWaiting = true;
         break;
      case 3145733:
         MainCanvas.isWaiting = true;
         if (MainCanvas.rightMenu != null) {
            MainCanvas.rightMenu.savePositionIDStack.removeAllElements();
            MainCanvas.rightMenu = null;
            MainCanvas.setGameState((byte)0);
         }
         break;
      case 3145736:
         ba.writeByte((byte)AttackSkill.length);

         for(i = 0; i < AttackSkill.length; ++i) {
            ba.writeInt(AttackSkill[i]);
         }

         return ba.toByteArray();
      case 3145737:
         ba.writeByte((byte)WatchSkill.length);

         for(i = 0; i < WatchSkill.length; ++i) {
            ba.writeInt(WatchSkill[i]);
         }

         ba.writeByte(SkillTime);
         break;
      case 3145739:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bIncrementGenreIndex);
         break;
      case 3145740:
         Intercalate();
         ba.writeByte(HANG_INTERCALATE.length);

         for(i = 0; i < HANG_INTERCALATE.length; ++i) {
            ba.writeByte(HANG_INTERCALATE[i]);
         }
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      int i;
      byte num;
      switch(commID) {
      case -2144337919:
         num = data.readByte();
         IntercalteContent = new String[num];

         for(i = 0; i < num; ++i) {
            IntercalteContent[i] = data.readUTF();
         }

         byte num_1 = data.readByte();
         HANG_INTERCALATE = new byte[num_1];

         for(int i = 0; i < num_1; ++i) {
            HANG_INTERCALATE[i] = data.readByte();
         }

         return;
      case -2144337918:
      case -2144337917:
         m_nSkillNum = data.readShort();
         H_stuffId = new int[m_nSkillNum];
         H_stuffName = new String[m_nSkillNum];
         H_stuffImgQuality = new short[m_nSkillNum];
         H_stuffImageId = new short[m_nSkillNum];
         H_stuffQuality = new byte[m_nSkillNum];

         int i;
         for(i = 0; i < m_nSkillNum; ++i) {
            H_stuffId[i] = data.readInt();
            H_stuffName[i] = data.readUTF();
            H_stuffImgQuality[i] = data.readShort();
            H_stuffImageId[i] = (short)(H_stuffImgQuality[i] % 1000);
            H_stuffQuality[i] = (byte)(H_stuffImgQuality[i] / 1000 - 1);
         }

         m_bIntercalateSkillNum = data.readByte();
         if (commID == -2144337918) {
            AttackSkill = new int[m_bIntercalateSkillNum];
         } else if (commID == -2144337917) {
            WatchSkill = new int[m_bIntercalateSkillNum];
         }

         RstuffImgQuality = new short[m_bIntercalateSkillNum];

         for(i = 0; i < m_bIntercalateSkillNum; ++i) {
            if (commID == -2144337918) {
               AttackSkill[i] = data.readInt();
            } else if (commID == -2144337917) {
               WatchSkill[i] = data.readInt();
            }

            RstuffImgQuality[i] = data.readShort();
         }

         if (commID == -2144337917) {
            SkillTime = data.readByte();
            num = data.readByte();
            SkillTimeLetter = new String[num];

            for(i = 0; i < num; ++i) {
               SkillTimeLetter[i] = data.readUTF();
            }
         }
         break;
      case -2144337916:
      case -2144337915:
         m_bHang = data.readBoolean();
      }

   }

   public static void Intercalate() {
      if (HANG_INTERCALATE == null) {
         HANG_INTERCALATE = new byte[3];

         for(int i = 0; i < HANG_INTERCALATE.length; ++i) {
            HANG_INTERCALATE[i] = 1;
         }
      }

   }
}

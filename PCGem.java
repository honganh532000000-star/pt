public class PCGem {
   public static byte m_bEnchaseRoute = 0;
   public static byte m_bListIndex = 0;
   public static byte m_bEquipIndex = -1;
   public static byte m_bGemIndex = -1;
   public static int[] m_bUnchainExes;
   public static byte m_bAppendMenu = 0;
   public static byte m_bGemDisplace = 0;
   public static byte m_bFocus = -1;
   public static byte m_bEnchaseSucceed = 0;
   public static byte m_bExamine = -1;
   public static byte m_bSucceed = -1;

   public static byte[] snd_Parse(int commID) {
      MainCanvas.isWaiting = true;
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 1966081:
         ba.writeByte(m_bEquipIndex);
         ba.writeByte(m_bEnchaseRoute);
      case 1966082:
      case 1966086:
      case 1966091:
      case 1966094:
      case 1966095:
      case 1966096:
      case 1966097:
      case 1966106:
      case 1966107:
      case 1966108:
      case 1966109:
      case 1966110:
      case 1966111:
      case 1966112:
      default:
         break;
      case 1966083:
         ba.writeByte(m_bListIndex);
         ba.writeByte(m_bEquipIndex);
         ba.writeByte(m_bEnchaseRoute);
         break;
      case 1966084:
         ba.writeByte(m_bGemIndex);
         ba.writeByte(m_bEquipIndex);
         ba.writeByte(m_bEnchaseRoute);
         break;
      case 1966085:
         ba.writeByte(m_bEquipIndex);
         ba.writeByte(m_bEnchaseRoute);
         ba.writeByte(m_bListIndex);
         ba.writeByte(m_bGemIndex);
         m_bEnchaseSucceed = 1;
         break;
      case 1966087:
         ba.writeByte(m_bEnchaseRoute);
         ba.writeByte(m_bEquipIndex);
         break;
      case 1966088:
         ba.writeByte(m_bEquipIndex);
         ba.writeByte(m_bEnchaseRoute);
         ba.writeByte(m_bGemIndex);
         break;
      case 1966089:
         ba.writeByte(m_bEquipIndex);
         ba.writeByte(m_bEnchaseRoute);
         ba.writeByte(m_bListIndex);
         ba.writeByte(m_bGemIndex);
         break;
      case 1966090:
         ba.writeByte(m_bEquipIndex);
         ba.writeByte(m_bEnchaseRoute);
         ba.writeByte(m_bListIndex);
         ba.writeByte(m_bGemIndex);
         break;
      case 1966092:
         ba.writeByte(m_bEquipIndex);
         ba.writeByte(m_bEnchaseRoute);
         ba.writeByte(m_bListIndex);
         ba.writeByte(m_bGemIndex);
         break;
      case 1966093:
         ba.writeByte(m_bEquipIndex);
         ba.writeByte(m_bEnchaseRoute);
         break;
      case 1966098:
         ba.writeByte(m_bEquipIndex);
         break;
      case 1966099:
         ba.writeByte(m_bEquipIndex);
         ba.writeByte(m_bGemIndex);
         break;
      case 1966100:
         ba.writeByte(m_bEquipIndex);
         break;
      case 1966101:
         ba.writeByte(m_bListIndex);
         ba.writeByte(m_bEquipIndex);
         break;
      case 1966102:
      case 1966104:
         ba.writeByte(m_bEquipIndex);
         ba.writeByte(m_bListIndex);
         ba.writeByte(m_bGemIndex);
         break;
      case 1966103:
      case 1966105:
         ba.writeByte(m_bEquipIndex);
         ba.writeByte(m_bListIndex);
         break;
      case 1966113:
         ba.writeByte(m_bExamine);
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2145517564:
         UIForm.backUIFormAction();
         break;
      case -2145517550:
         boolean unchain = data.readBoolean();
         if (unchain) {
            UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
            pic.setShowNum(false);
            pic.quality = (byte)UIGrid.AuctionBag[2];
            short picID = UIGrid.AuctionBag[1];
            pic.isWpPic = true;
            pic.wpIndex = picID;
            pic.setImg(MainCanvas.stuffMImg);
         }
         break;
      case -2145517536:
         MainCanvas.cmdID = commID;
         MainCanvas.cmdData = data.readByteArray(data.getLength() - 6);
      }

   }

   public static void LiquidationBata() {
      m_bEnchaseRoute = 0;
      m_bListIndex = 0;
      m_bEquipIndex = -1;
      m_bGemIndex = -1;
      m_bGemDisplace = 0;
      m_bFocus = -1;
      m_bEnchaseSucceed = 0;
   }
}

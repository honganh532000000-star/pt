public class PCUnsealGemCarve {
   public static byte m_bPrimaryGemIndex = -1;
   public static byte m_bCarveSymbolIndex = -1;
   public static byte m_bJadeIndex = -1;
   public static byte m_bExamine = -1;
   public static byte m_bFocus = -1;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 2031617:
      case 2031635:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bExamine);
         break;
      case 2031618:
      case 2031620:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bPrimaryGemIndex);
         ba.writeByte(m_bCarveSymbolIndex);
         ba.writeByte(m_bJadeIndex);
         break;
      case 2031619:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bPrimaryGemIndex);
         break;
      case 2031621:
      case 2031636:
      case 2031648:
      case 2031649:
      case 2031650:
      case 2031651:
         MainCanvas.isWaiting = true;
         break;
      case 2031622:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bPrimaryGemIndex);
         break;
      case 2031623:
         ba.writeByte(m_bPrimaryGemIndex);
         ba.writeByte(m_bCarveSymbolIndex);
         break;
      case 2031624:
      case 2031634:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bPrimaryGemIndex);
         ba.writeByte(m_bCarveSymbolIndex);
         ba.writeByte(m_bJadeIndex);
         break;
      case 2031625:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bPrimaryGemIndex);
         ba.writeByte(m_bCarveSymbolIndex);
      case 2031626:
      case 2031627:
      case 2031628:
      case 2031629:
      case 2031630:
      case 2031631:
      case 2031632:
      case 2031638:
      case 2031639:
      case 2031640:
      case 2031641:
      case 2031642:
      case 2031643:
      case 2031644:
      case 2031645:
      case 2031646:
      case 2031647:
      default:
         break;
      case 2031633:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bPrimaryGemIndex);
         ba.writeByte(m_bCarveSymbolIndex);
         break;
      case 2031637:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bPrimaryGemIndex);
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2145452017:
         boolean equip = data.readBoolean();
         if (equip) {
            AppendPicture(4, true, 0, false);
         }
      default:
      }
   }

   public static void LiquidationBata() {
      m_bPrimaryGemIndex = -1;
      m_bCarveSymbolIndex = -1;
      m_bJadeIndex = -1;
      m_bExamine = -1;
   }

   public static void EliminateBata() {
      CleanupPicture(4, true);
      CleanupPicture(6, false);
      UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(9);
      m_bPrimaryGemIndex = -1;
      m_bCarveSymbolIndex = -1;
      m_bJadeIndex = -1;
      m_bFocus = -1;
      grid.setFocus(false, MainCanvas.curForm);
      m_bExamine = -1;

      for(int i = 0; i < UIGrid.AuctionBag.length; ++i) {
         UIGrid.AuctionBag[i] = -1;
      }

   }

   private static void CleanupPicture(int number, boolean focus) {
      UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(number);
      pic.setFocus(focus, MainCanvas.curForm);
      pic.setShowNum(false);
      pic.isWpPic = false;
      pic.setImg((MImage)null);
   }

   public static void AppendPicture(int number, boolean focus, int index, boolean show) {
      UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(number);
      UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(9);
      if (index == 1) {
         m_bCarveSymbolIndex = (byte)UIGrid.AuctionBag[0];
      } else if (index == 2) {
         m_bJadeIndex = (byte)UIGrid.AuctionBag[0];
      } else if (index == 3) {
         m_bPrimaryGemIndex = (byte)UIGrid.AuctionBag[0];
      }

      pic.setShowNum(show, (byte)1);
      pic.quality = (byte)UIGrid.AuctionBag[2];
      short picID = UIGrid.AuctionBag[1];
      pic.isWpPic = true;
      pic.wpIndex = picID;
      pic.setImg(MainCanvas.stuffMImg);
      pic.setFocus(focus, MainCanvas.curForm);
      grid.setFocus(!focus, MainCanvas.curForm);
      m_bFocus = -1;
   }
}

import javax.microedition.lcdui.Graphics;

public class PCAuction {
   public static int m_nAuctionPrice = 0;
   public static int m_nNormalPrice = 0;
   public static int m_nPlayMoney = 0;
   public static byte m_bGoodsNum = 0;
   public static byte m_bAbidanceHours = 0;
   public static byte m_bEntityGoods = 0;
   public static short pageIndex = 1;
   public static short sumPage = 1;
   public static long auctionID = -1L;
   public static int maxPrice = 0;
   public static int surePrice = 0;
   public static int playerMoney = 0;
   public static int payPrice = 0;
   public static byte m_bForbidEnchase = 0;
   public static boolean m_bWhetherAccumulate = false;
   public static String searchName = "";
   public static boolean isBuy = true;
   public static byte subTypeIndex = -1;
   public static byte aSubTypeIndex = -1;
   public static byte bSubTypeIndex = -1;
   public static byte cSubTypeIndex = -1;
   public static byte dSubTypeIndex = -1;

   public static byte[] snd_Parse(int commID) {
      MainCanvas.isWaiting = true;
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 1900545:
         ba.writeInt(GOManager.currentTarget.getID());
         break;
      case 1900546:
         ba.writeByte(subTypeIndex);
         ba.writeByte(aSubTypeIndex);
         ba.writeByte(bSubTypeIndex);
         ba.writeByte(cSubTypeIndex);
         ba.writeByte(dSubTypeIndex);
         ba.writeShort(pageIndex);
      case 1900547:
      case 1900548:
      case 1900549:
      case 1900551:
      default:
         break;
      case 1900550:
         ba.writeByte((byte)UIGrid.AuctionBag[0]);
         ba.writeByte(m_bGoodsNum);
         ba.writeInt(m_nAuctionPrice);
         ba.writeInt(m_nNormalPrice);
         ba.writeByte(m_bAbidanceHours);
         break;
      case 1900552:
         UIList auctionList = (UIList)MainCanvas.curForm.getComponents().elementAt(2);
         ListItem item = (ListItem)auctionList.getItems().elementAt(auctionList.getSelectedIndex());
         UIGrid.getStuffWordColor(item.stuffQuality);
         ba.writeLong(auctionID);
         PCTreasure.unlockMenu = false;
         break;
      case 1900553:
         ba.writeLong(auctionID);
         ba.writeInt(payPrice);
         ba.writeByte(subTypeIndex);
         ba.writeByte(aSubTypeIndex);
         ba.writeByte(bSubTypeIndex);
         ba.writeByte(cSubTypeIndex);
         ba.writeByte(dSubTypeIndex);
         ba.writeShort(pageIndex);
         break;
      case 1900554:
         ba.writeUTF(searchName);
         ba.writeShort(pageIndex);
         break;
      case 1900555:
         ba.writeLong(auctionID);
         ba.writeInt(payPrice);
         ba.writeUTF(searchName);
         ba.writeShort(pageIndex);
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2145583099:
      case -2145583097:
         MainCanvas.cmdID = commID;
         MainCanvas.cmdData = data.readByteArray(data.getLength() - 6);
      case -2145583098:
      default:
      }
   }

   public static void RetrieveGoods(byte ID) {
      UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(3);
      if (ID == 1 && pic.isWpPic) {
         DeleteGoods();
         pic.setShowNum(false);
         pic.isWpPic = false;
         pic.setImg((MImage)null);
      } else if (ID == 1 && !pic.isWpPic) {
         UITopForm.createLocalTopForm((byte)0, (String)"Chọn vật phẩm đấu giá!", "Xác nhận", "", -1, -2);
      } else if (ID == 2) {
         m_bForbidEnchase = 0;
         DeleteGoods();
         pic.setShowNum(false);
         pic.isWpPic = false;
         pic.setImg((MImage)null);
      }

   }

   public static void ConfirmAuction() {
      UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(3);
      UITextField textfield1 = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
      UITextField textfield2 = (UITextField)MainCanvas.curForm.getComponents().elementAt(5);
      if (textfield1.getSb().toString().trim().equals("0")) {
         UITopForm.createLocalTopForm((byte)0, (String)"Giá đấu không thể là 0", "Xác nhận", "", -1, -2);
      } else if (textfield2.getSb().toString().trim().equals("0")) {
         UITopForm.createLocalTopForm((byte)0, (String)"Giá duy nhất không thể là 0", "Xác nhận", "", -1, -2);
      } else {
         try {
            m_nAuctionPrice = Integer.parseInt(textfield1.getSb().toString().trim());
            m_nNormalPrice = Integer.parseInt(textfield2.getSb().toString().trim());
         } catch (NumberFormatException var4) {
            m_nAuctionPrice = 0;
            m_nNormalPrice = 0;
            var4.printStackTrace();
         }

         if (m_nAuctionPrice >= m_nNormalPrice) {
            UITopForm.createLocalTopForm((byte)0, (String)"Giá duy nhất không thể thấp hoặc bằng với giá đấu", "Xác nhận", "", -1, -2);
         } else {
            int abidancehours = 0;
            switch(m_bAbidanceHours) {
            case 0:
               abidancehours = 6;
               break;
            case 1:
               abidancehours = 12;
               break;
            case 2:
               abidancehours = 24;
               break;
            case 3:
               abidancehours = 48;
               break;
            case 4:
               abidancehours = 72;
            }

            UITopForm.createLocalTopForm((byte)0, (String)("Phải loại bỏ " + abidancehours + " Tiền vàng bảo hiểm, có xác nhận đấu giá không"), "Xác nhận", "Hủy", 1900550, -1);
         }
      }
   }

   private static void DeleteGoods() {
      UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
      UITextField textfield1 = (UITextField)MainCanvas.curForm.getComponents().elementAt(4);
      UITextField textfield2 = (UITextField)MainCanvas.curForm.getComponents().elementAt(5);
      UIRadio radio = (UIRadio)MainCanvas.curForm.getComponents().elementAt(6);
      UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(8);
      UILabel label = (UILabel)MainCanvas.curForm.getComponents().elementAt(9);
      textfield1.setLabel("0");
      textfield2.setLabel("0");
      label.setText("");
      radio.setSelectIndex((byte)0);
      UIGrid.m_nAuctionGoodsName = "";
      m_bAbidanceHours = 0;
      m_bGoodsNum = 0;
      m_nAuctionPrice = 0;
      m_nNormalPrice = 0;
      m_bWhetherAccumulate = false;

      for(int i = 0; i < UIGrid.AuctionBag.length; ++i) {
         UIGrid.AuctionBag[i] = -1;
      }

      m_bEntityGoods = 0;
      if (grid.isFocus()) {
         menuBar.setMenuText("Thao tác", 0);
      } else {
         menuBar.setMenuText("", 0);
      }

   }

   public static void ChooseGoods(byte stuffNumber) {
      UITitle menuBar = (UITitle)MainCanvas.curForm.getComponents().elementAt(1);
      UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(3);
      UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(8);
      UILabel label = (UILabel)MainCanvas.curForm.getComponents().elementAt(9);
      label.setText(UIGrid.m_nAuctionGoodsName);
      if (stuffNumber >= 1 && m_bWhetherAccumulate) {
         pic.setShowNum(true, stuffNumber);
      } else {
         pic.setShowNum(false, stuffNumber);
      }

      m_bGoodsNum = stuffNumber;
      pic.quality = (byte)UIGrid.AuctionBag[2];
      short picID = UIGrid.AuctionBag[1];
      pic.isWpPic = true;
      pic.wpIndex = picID;
      pic.setImg(MainCanvas.stuffMImg);
      pic.setFocus(true, MainCanvas.curForm);
      grid.setFocus(false, MainCanvas.curForm);
      m_bEntityGoods = 1;
      menuBar.setMenuText("Thao tác", 0);
   }

   public static void resetPage() {
      pageIndex = 1;
   }

   public static void resetAuctionIndex() {
      subTypeIndex = -1;
      aSubTypeIndex = -1;
      bSubTypeIndex = -1;
      cSubTypeIndex = -1;
      dSubTypeIndex = -1;
   }

   public static void drawArrow(UIForm arrForm, Graphics g, boolean isDrawLeft, boolean isDrawRight) {
      if (isDrawLeft || isDrawRight) {
         UILabel doubleLabel = (UILabel)arrForm.getComponents().elementAt(15);
         int arrY = doubleLabel.getPositionY();
         int leftArrX = doubleLabel.getPositionX() - 5 - MainCanvas.auctionArrImg.getWidth();
         int rightArrX = doubleLabel.getPositionX() + doubleLabel.getWidth() + 5;
         if (isDrawLeft) {
            Util.drawImage(g, MainCanvas.auctionArrImg, leftArrX, arrY, 0);
         }

         if (isDrawRight) {
            Util.drawImage(g, MainCanvas.auctionArrImg, rightArrX, arrY, 2);
         }

      }
   }

   public static void LiquidationBata() {
      m_nAuctionPrice = 0;
      m_nNormalPrice = 0;
      m_nPlayMoney = 0;
      m_bGoodsNum = 0;
      m_bAbidanceHours = 0;
      pageIndex = 1;
      sumPage = 1;
      auctionID = -1L;
      maxPrice = 0;
      surePrice = 0;
      playerMoney = 0;
      payPrice = 0;
      m_bForbidEnchase = 0;
   }
}

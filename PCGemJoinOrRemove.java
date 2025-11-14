import java.util.Vector;

public class PCGemJoinOrRemove {
   public static byte m_bGemIndex = -1;
   public static byte m_bGemIndex1 = -1;
   public static byte m_bGemAmount = 0;
   public static byte[] m_bPropIndex = new byte[]{-1, -1};
   public static byte m_bPropAmount = 0;
   public static byte m_bGemAmount_1 = 0;
   public static byte m_bFocus = -1;
   public static Vector items = new Vector();
   public static Vector itemsnum = new Vector();
   public static byte m_bExamine = -1;
   public static byte m_bImportNum = 0;
   private static boolean m_bAfresh;
   public static String m_sName;
   public static byte m_sNameColo;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 2228225:
      case 2228240:
         MainCanvas.isWaiting = true;
         if (commID == 2228240) {
            m_bAfresh = true;
         }
         break;
      case 2228226:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bGemIndex1);
         ba.writeByte(m_bGemIndex);
         ba.writeByte(m_bGemAmount);
         ba.writeByte(m_bImportNum);
         break;
      case 2228227:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bGemIndex);
         ba.writeByte(m_bGemAmount);
         if (m_bPropIndex[0] == -1 && m_bPropIndex[1] != -1) {
            m_bPropIndex[0] = m_bPropIndex[1];
            m_bPropIndex[1] = -1;
         }

         ba.writeByte(m_bPropIndex[0]);
         ba.writeByte(m_bPropIndex[1]);
         break;
      case 2228228:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bGemIndex);
         ba.writeByte(m_bPropAmount);
         ba.writeByte(m_bGemAmount);
         break;
      case 2228229:
      case 2228230:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bGemIndex);
         ba.writeByte(m_bPropAmount);

         int j;
         for(j = 0; j < m_bPropAmount; ++j) {
            ba.writeByte(m_bPropIndex[j]);
         }

         ba.writeByte((byte)items.size());

         for(j = 0; j < items.size(); ++j) {
            ba.writeByte((Byte)items.elementAt(j));
         }

         for(j = 0; j < itemsnum.size(); ++j) {
            ba.writeByte((Byte)itemsnum.elementAt(j));
         }

         if (commID == 2228229) {
            ba.writeByte(m_bGemAmount);
         }
         break;
      case 2228231:
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bExamine);
         break;
      case 2228242:
      case 2228256:
      case 2228258:
      case 2228260:
         MainCanvas.isWaiting = true;
         break;
      case 2228257:
         m_bGemIndex = (byte)UIGrid.AuctionBag[0];
         MainCanvas.isWaiting = true;
         ba.writeByte(m_bGemIndex);
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      short imageFullID;
      switch(commID) {
      case -2145255416:
         m_bGemIndex1 = data.readByte();
         if (m_bGemIndex1 >= 0) {
            UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
            UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(7);
            if (m_bGemAmount_1 > 5) {
               pic.setShowNum(true, (byte)5);
            } else {
               pic.setShowNum(true, m_bGemAmount_1);
            }

            pic.quality = (byte)UIGrid.AuctionBag[2];
            short picID = UIGrid.AuctionBag[1];
            pic.isWpPic = true;
            pic.wpIndex = picID;
            pic.setImg(MainCanvas.stuffMImg);
            pic.setFocus(true, MainCanvas.curForm);
            grid.setFocus(false, MainCanvas.curForm);
            m_sName = grid.stuffName[grid.gridIndex];
            m_sNameColo = grid.stuffColorLevel[grid.gridIndex];
         }
         break;
      case -2145255415:
         UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(7);
         byte num = data.readByte();

         for(byte i = 0; i < num; ++i) {
            byte gridIndex = data.readByte();
            grid.stuffId[gridIndex] = data.readInt();
            imageFullID = data.readShort();
            grid.stuffImgQuality[gridIndex] = imageFullID;
            grid.stuffImageId[gridIndex] = (short)(imageFullID % 1000);
            grid.stuffNumber[gridIndex] = data.readByte();
            grid.stuffName[gridIndex] = data.readUTF();
            grid.stuffColorLevel[gridIndex] = data.readByte();
            grid.getQuality(gridIndex, imageFullID);
            grid.setTitle(grid.stuffName[grid.getGridIndex()], UIGrid.getStuffWordColor(grid.stuffColorLevel[grid.getGridIndex()]));
         }

         if (m_bAfresh) {
            UILabel nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(14);
            nameLabel.setTextColor(Cons.COLOR_FONT_1);
            nameLabel.setText("Đặt Nguyên Thạch");
            m_bAfresh = false;
         }

         CleanupData();
         break;
      case -2145255407:
         CleanupData();
         break;
      case -2145255391:
         m_bGemIndex1 = data.readByte();
         if (m_bGemIndex1 >= 0) {
            UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(4);
            UIGrid grid_1 = (UIGrid)MainCanvas.curForm.getComponents().elementAt(7);
            pic.setShowNum(false);
            pic.quality = (byte)UIGrid.AuctionBag[2];
            imageFullID = UIGrid.AuctionBag[1];
            pic.isWpPic = true;
            pic.wpIndex = imageFullID;
            pic.setImg(MainCanvas.stuffMImg);
            pic.setFocus(true, MainCanvas.curForm);
            grid_1.setFocus(false, MainCanvas.curForm);
         }
      }

   }

   public static void AppendPicture(int number, int id) {
      UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(number);
      UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(7);
      m_bPropIndex[id] = (byte)UIGrid.AuctionBag[0];
      pic.setShowNum(true, (byte)1);
      pic.quality = (byte)UIGrid.AuctionBag[2];
      short picID = UIGrid.AuctionBag[1];
      pic.isWpPic = true;
      pic.wpIndex = picID;
      pic.setImg(MainCanvas.stuffMImg);
      pic.setFocus(true, MainCanvas.curForm);
      grid.setFocus(false, MainCanvas.curForm);
      ++m_bPropAmount;
      m_bFocus = -1;
   }

   public static void Calculate() {
      m_bGemAmount_1 += m_bImportNum;
      if (items.size() < 5) {
         m_bGemIndex = (byte)UIGrid.AuctionBag[0];
         items.addElement(new Byte(m_bGemIndex));
         itemsnum.addElement(new Byte(m_bImportNum));
      }

      MainCanvas.ni.send(2228226);
   }

   private static void CleanupPicture(int number, boolean focus) {
      UIPicture pic = (UIPicture)MainCanvas.curForm.getComponents().elementAt(number);
      pic.setFocus(focus, MainCanvas.curForm);
      pic.setShowNum(false);
      pic.isWpPic = false;
      pic.setImg((MImage)null);
   }

   public static void CleanupData() {
      if (m_bGemIndex != -1) {
         UIGrid grid = (UIGrid)MainCanvas.curForm.getComponents().elementAt(7);
         grid.setFocus(false, MainCanvas.curForm);
         CleanupPicture(4, true);
         m_bGemIndex = -1;
         m_bGemIndex1 = -1;
         m_bGemAmount = 0;
         m_bPropAmount = 0;
         m_bGemAmount_1 = 0;
         m_bFocus = -1;
         items = new Vector();
         itemsnum = new Vector();

         int i;
         for(i = 0; i < m_bPropIndex.length; ++i) {
            m_bPropIndex[i] = -1;
         }

         for(i = 0; i < UIGrid.AuctionBag.length; ++i) {
            UIGrid.AuctionBag[i] = -1;
         }

         UILabel nameLabel_1 = (UILabel)MainCanvas.curForm.getComponents().elementAt(14);
         UILabel nameLabel;
         if (MainCanvas.curForm.clientCommand != 2228256) {
            nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(12);
            nameLabel.setNum1(m_bGemAmount_1);
            nameLabel_1.setTextColor(Cons.COLOR_FONT_1);
            nameLabel_1.setText("Đặt Nguyên Thạch");
         } else {
            nameLabel_1.setTextColor(Cons.COLOR_FONT_1);
            nameLabel_1.setText("Đặt Bảo Thạch");
         }

         nameLabel = (UILabel)MainCanvas.curForm.getComponents().elementAt(11);
         nameLabel.setText("0");
      }

   }
}

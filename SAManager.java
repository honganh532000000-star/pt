import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class SAManager {
   private static final String SKE_PREFIX = "m_body";
   private static final String ANI_PREFIX = "m_motion";
   private static final String DAT_POSTFIX = ".dat";
   private Vector vecSADatas = null;
   private byte nLastSAID = -1;
   private byte nLastIndex = -1;

   public SAManager() {
      this.vecSADatas = new Vector(1, 2);
   }

   public SAData getSADByIndex(byte aIndex) {
      return (SAData)this.vecSADatas.elementAt(aIndex);
   }

   public void releaseData(byte aStartIndex) {
      if (aStartIndex < this.vecSADatas.size()) {
         while(true) {
            try {
               SAData temp = (SAData)this.vecSADatas.elementAt(aStartIndex);
               this.vecSADatas.removeElementAt(aStartIndex);
               temp.nAnimation = null;
               temp.nSkeleton = null;
               temp = null;
            } catch (ArrayIndexOutOfBoundsException var3) {
               break;
            }
         }
      }

   }

   public byte requestData(byte aSAID) {
      if (this.nLastSAID == aSAID && this.nLastIndex != -1) {
         return this.nLastIndex;
      } else {
         byte bRet = -1;
         if (this.vecSADatas != null) {
            int nCount = this.vecSADatas.size();

            for(int n = 0; n < nCount; ++n) {
               if (((SAData)this.vecSADatas.elementAt(n)).nID == aSAID) {
                  bRet = (byte)n;
                  break;
               }
            }

            if (bRet == -1) {
               this.loadData(aSAID);
               bRet = (byte)(this.vecSADatas.size() - 1);
            }
         }

         this.nLastSAID = aSAID;
         this.nLastIndex = bRet;
         return bRet;
      }
   }

   private void loadData(byte aSAID) {
      SAData sadNew = new SAData();
      sadNew.nID = aSAID;
      InputStream isData = null;
      DataInputStream disData = null;
      String strFileName = "/bfm/m_body";
      if (aSAID < 10) {
         strFileName = strFileName + "0";
      }

      strFileName = strFileName + aSAID;
      strFileName = strFileName + ".dat";
      isData = Util.loadFile(strFileName, false);
      disData = new DataInputStream(isData);

      short nCount;
      short n;
      byte nFrameCount;
      byte m;
      try {
         nCount = disData.readShort();
         sadNew.nSkeleton = new short[nCount][];

         for(n = 0; n < nCount; ++n) {
            disData.readShort();
            nFrameCount = disData.readByte();
            nFrameCount = (byte)(nFrameCount * 2);
            sadNew.nSkeleton[n] = new short[nFrameCount];

            for(m = 0; m < nFrameCount; sadNew.nSkeleton[n][m++] = disData.readShort()) {
               sadNew.nSkeleton[n][m++] = disData.readShort();
            }
         }

         disData.close();
         disData = null;
      } catch (IOException var11) {
         var11.printStackTrace();
      }

      strFileName = "/bfm/m_motion";
      if (aSAID < 10) {
         strFileName = strFileName + "0";
      }

      strFileName = strFileName + aSAID;
      strFileName = strFileName + ".dat";
      isData = Util.loadFile(strFileName, false);
      disData = new DataInputStream(isData);

      try {
         nCount = disData.readShort();
         sadNew.nAnimation = new short[nCount][];

         for(n = 0; n < nCount; ++n) {
            disData.readByte();
            nFrameCount = disData.readByte();
            sadNew.nAnimation[n] = new short[nFrameCount];

            for(m = 0; m < nFrameCount; sadNew.nAnimation[n][m++] = disData.readShort()) {
            }
         }

         disData.close();
         disData = null;
      } catch (IOException var10) {
         var10.printStackTrace();
      }

      this.vecSADatas.addElement(sadNew);
   }
}

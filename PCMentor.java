public class PCMentor {
   public static String masterName;
   public static int masterId;
   public static boolean masterOL;
   public static byte freeIndex;
   public static int otherId;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      MainCanvas.isWaiting = true;
      switch(commID) {
      case 2490369:
         ba.writeInt(SITeam.findLover());
         break;
      case 2490373:
      case 2490420:
         ba.writeInt(SITeam.findLover());
         break;
      case 2490375:
      case 2490422:
         ba.writeInt(SITeam.findLover());
      case 2490385:
      case 2490386:
      default:
         break;
      case 2490402:
      case 2490421:
         ba.writeByte(freeIndex);
         break;
      case 2490403:
         ba.writeInt(UITable.selectedPlayerId);
         break;
      case 2490404:
         ba.writeInt(UITopForm.buyNum);
         MainCanvas.isWaiting = false;
         break;
      case 2490407:
      case 2490417:
         ba.writeInt(otherId);
         break;
      case 2490408:
      case 2490409:
      case 2490416:
         MainCanvas.isWaiting = false;
         ba.writeInt(otherId);
         break;
      case 2490423:
         MainCanvas.isWaiting = false;
         ba.writeInt(otherId);
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2144993279:
         masterName = data.readUTF();
         masterId = data.readInt();
         masterOL = data.readBoolean();
         break;
      case -2144993278:
         UITopForm.MAXNUMBER = 100;
         UITopForm.createLocalTopForm((byte)1, (String)"Xin sư phụ cho điểm:", "Đồng ý", "", 2490404, -2);
         MainCanvas.curTopForm.setNumber(100);
      }

   }
}

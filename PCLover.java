public class PCLover {
   public static final byte LOVER_STATE_NONE = 0;
   public static final byte LOVER_STATE_LOVER = 1;
   public static final byte LOVER_STATE_MARRIAGE = 2;
   public static int actionId = 0;

   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      switch(commID) {
      case 1441793:
         ba.writeInt(GOManager.currentTarget.getID());
         MainCanvas.isWaiting = true;
         break;
      case 1441794:
      case 1441795:
      case 1441796:
         ba.writeInt(SITeam.findLover());
         break;
      case 1441797:
         ba.writeInt(Player.getInstance().getLoverId());
         break;
      case 1441798:
      case 1441799:
      case 1441800:
      case 1441801:
      case 1441802:
      case 1441803:
         ba.writeInt(actionId);
         break;
      case 1441804:
      case 1441805:
         MainCanvas.isWaiting = true;
      case 1441806:
      case 1441807:
      default:
         break;
      case 1441808:
         ba.writeInt(Player.getInstance().getLoverId());
         MainCanvas.isWaiting = true;
      }

      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
      switch(commID) {
      case -2146041855:
         Player.getInstance().setLoverId(data.readInt());
         Player.getInstance().setLoverState(data.readByte());
         Player.getInstance().setLoverName(data.readUTF());
      default:
      }
   }
}

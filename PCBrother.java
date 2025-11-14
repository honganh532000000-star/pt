public class PCBrother {
   public static byte[] snd_Parse(int commID) {
      ByteArray ba = new ByteArray();
      ba.writeInt(commID);
      return ba.toByteArray();
   }

   public static void rec_Parse(int commID, ByteArray data) {
   }
}

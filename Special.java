import javax.microedition.lcdui.Graphics;

public class Special {
   public byte level;
   public short x;
   public short y;
   public short curMotionIndex;
   public byte curFrameIndex;
   public byte loopCount;
   public boolean isFinish;
   public boolean isTrans;
   public GameObject speciaObj;

   public Special(short _x, short _y, short _motionID, byte lc, byte l) {
      this.x = _x;
      this.y = _y;
      this.speciaObj = null;
      this.curMotionIndex = _motionID;
      this.curFrameIndex = -1;
      this.loopCount = lc;
      this.isFinish = false;
      this.level = l;
   }

   public Special(GameObject _obj, short _motionID, byte lc, byte l) {
      this.speciaObj = _obj;
      this.curMotionIndex = _motionID;
      this.curFrameIndex = -1;
      this.loopCount = lc;
      this.isFinish = false;
      this.level = l;
   }

   boolean isFinish() {
      if (this.curFrameIndex >= MainCanvas.mc.motionDataAll[this.curMotionIndex].length) {
         this.curFrameIndex = 0;
         if (this.getLoopCount() > 1) {
            this.setLoopCount((byte)(this.getLoopCount() - 1));
            this.isFinish = false;
         } else if (this.getLoopCount() < 0) {
            this.isFinish = false;
         } else if (this.getLoopCount() == 1) {
            this.setLoopCount((byte)0);
            this.isFinish = true;
         }
      }

      return this.isFinish;
   }

   public final void setSpecial(GameObject _obj, short _motionID, byte lc) {
      this.speciaObj = _obj;
      this.curMotionIndex = _motionID;
      this.curFrameIndex = -1;
      this.loopCount = lc;
      this.isFinish = false;
   }

   public byte getLoopCount() {
      return this.loopCount;
   }

   public void setLoopCount(byte loopCount) {
      this.loopCount = loopCount;
   }

   public void tick() {
      if (Player.getInstance() != null && Player.getInstance().isLevelUpSpecialIndex(this.curMotionIndex)) {
         if (MainCanvas.getGameState() == 0 && MainCanvas.curTopForm == null) {
            ++this.curFrameIndex;
         }
      } else {
         ++this.curFrameIndex;
      }

   }

   public void draw(Graphics g) {
      if (this.curFrameIndex < 0) {
         this.curFrameIndex = 0;
      }

      if (this.speciaObj == null) {
         Util.drawRoleEditFrame(MainCanvas.mc.imageSpecific, g, MainCanvas.mc.frameDataSpecific[MainCanvas.mc.motionDataAll[this.curMotionIndex][this.curFrameIndex]], MainCanvas.mc.picDatasSpecific, this.x - Map.currentWindowX, this.y - Map.currentWindowY, this.isTrans);
      } else {
         Util.drawRoleEditFrame(MainCanvas.mc.imageSpecific, g, MainCanvas.mc.frameDataSpecific[MainCanvas.mc.motionDataAll[this.curMotionIndex][this.curFrameIndex]], MainCanvas.mc.picDatasSpecific, this.speciaObj.getDrawX(), this.speciaObj.getDrawY() - 3, this.speciaObj.bRMirror);
      }

   }
}

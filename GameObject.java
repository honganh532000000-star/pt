import javax.microedition.lcdui.Graphics;

public abstract class GameObject {
   public static final byte OBJ_TYPE_PLAYER = 1;
   public static final byte OBJ_TYPE_MONSTER = 2;
   public static final byte OBJ_TYPE_NPC = 3;
   public static final byte OBJ_TYPE_MINE = 4;
   public static final byte OBJ_TYPE_HERB = 5;
   public static final byte OBJ_TYPE_TASKMH = 6;
   public static final byte OBJ_TYPE_CLOTH = 7;
   public static final byte OBJ_TYPE_SKIN = 8;
   public static final byte OBJ_TYPE_CART = 9;
   public static final byte OBJ_TYPE_BATTLEFIELD_FLAG = 10;
   public static final byte OBJ_TYPE_NORMAl_FLAG = 11;
   public static final byte DRAW_DY = 3;
   public static final SAManager mgrSA = new SAManager();
   public boolean bRMirror = false;
   public int ID = 0;
   public int drawID = -1;
   byte type = -1;
   public byte state;
   short x = 0;
   short y = 0;
   short drawX = 0;
   short drawY = 0;
   public byte nNextFrame = 0;
   public short nRow;
   public short nCol;
   public short oRow;
   public short oCol;
   String roleName = "";
   String title = "";
   public static long m_lBattlefield;

   public abstract void setSadGO(SAData var1);

   public abstract SAData getSadGO();

   public final void setID(int i) {
      this.ID = i;
   }

   public final int getID() {
      return this.ID;
   }

   public final void setDrawID(int id) {
      this.drawID = id;
   }

   public final int getDrawID() {
      return this.drawID;
   }

   public final void setType(byte t) {
      this.type = t;
   }

   public final byte getType() {
      return this.type;
   }

   public abstract void setState(byte var1);

   public final byte getState() {
      return this.state;
   }

   public final void setMapXY(short nx, short ny) {
      this.x = nx;
      this.y = ny;
   }

   public final short getMapX() {
      return this.x;
   }

   public final short getMapY() {
      return this.y;
   }

   public final void setDrawXY(short dx, short dy) {
      this.drawX = dx;
      this.drawY = dy;
   }

   public final short getDrawX() {
      return this.drawX;
   }

   public final short getDrawY() {
      return this.drawY;
   }

   public final void setNextFrame(byte l) {
      this.nNextFrame = l;
   }

   public final byte getNextFrame() {
      return this.nNextFrame;
   }

   public final void setRowCol(short r, short c) {
      this.nRow = r;
      this.nCol = c;
   }

   public final short getRow() {
      return this.nRow;
   }

   public final short getCol() {
      return this.nCol;
   }

   public final short getRow(short x, short y) {
      return Map.getCurrentRow(y, x);
   }

   public final short getCol(short x, short y) {
      return Map.getCurrentColumn(y, x);
   }

   public final void setOldRowCol(short or, short oc) {
      this.oRow = or;
      this.oCol = oc;
   }

   public final short getOldRow() {
      return this.oRow;
   }

   public final short getOldCol() {
      return this.oCol;
   }

   public final void setRoleName(String rn) {
      this.roleName = rn;
   }

   public final String getRoleName() {
      return this.roleName;
   }

   public final void setTitleName(String tn) {
      if (tn != null & tn.length() > 0) {
         tn = "<" + tn + ">";
      }

      this.title = tn;
   }

   public final String getTitleName() {
      return this.title;
   }

   public abstract void setMotionState(byte var1);

   public abstract void motionState_tick();

   public abstract void draw(Graphics var1);

   public abstract void drawBody(Graphics var1, short var2, short var3);

   public abstract void drawName(Graphics var1);

   public abstract void tick();
}

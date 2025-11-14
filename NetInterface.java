import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Random;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

public class NetInterface {
   public static final boolean isEncrypt = true;
   Random rand = new Random();
   public static String serverUrl = "socket://";
   private static final long SENDER_WAIT_TIME = 100L;
   private static final long RECEIVER_WAIT_TIME = 100L;
   private static final byte NO_NETCOMMD_ID = 65;
   static String strMD5 = null;
   static int firstResult;
   public static SocketConnection socket = null;
   private DataInputStream socketInputStream = null;
   private DataOutputStream socketOutputStream = null;
   private Vector senderPool = new Vector();
   private NetInterface.Receiver receiver = null;
   private Vector receiverPool = new Vector();
   public byte checkoutID = -1;
   public boolean exit = false;
   private static NetInterface instance = null;
   Thread comTH = null;
   public int[] commands = new int[0];
   public boolean isSendingCommands = false;
   short encryptId = 0;
   byte[] encryptArray = null;
   private static final byte OPERATOR_PLUS = 75;
   private static final byte OPERATOR_XOR = 123;
   private static final byte OPERATOR_MINUS = 38;
   private static final byte OPERATOR_OR = 80;

   private NetInterface() {
      this.rand.setSeed(System.currentTimeMillis());
   }

   public static NetInterface getInstance() {
      if (instance == null) {
         instance = new NetInterface();
      }

      return instance;
   }

   public int getSendNum() {
      return this.senderPool.size();
   }

   public int getReNum() {
      return this.receiverPool.size();
   }

   private synchronized boolean buildConn() {
      try {
         socket = (SocketConnection)Connector.open(serverUrl, 3, false);
         socket.setSocketOption((byte)2, 0);
         socket.setSocketOption((byte)0, 10);
         socket.setSocketOption((byte)1, 5);
         this.socketInputStream = socket.openDataInputStream();
         this.socketOutputStream = socket.openDataOutputStream();
         this.receiver = new NetInterface.Receiver();
      } catch (Exception var2) {
         if (this.senderPool != null) {
            this.senderPool = new Vector();
         }

         var2.printStackTrace();
         MainCanvas.mc.Abnormity();
         return false;
      }

      this.exit = false;
      return true;
   }

   public void closeConn() {
      try {
         if (this.receiver != null) {
            this.receiver.stop();
            this.receiver = null;
         }

         if (socket != null) {
            socket.close();
            socket = null;
         }

         this.comTH = null;
         socket = null;
         if (this.socketInputStream != null) {
            this.socketInputStream.close();
            this.socketInputStream = null;
         }

         if (this.socketOutputStream != null) {
            this.socketOutputStream.close();
            this.socketOutputStream = null;
         }

         this.checkoutID = -1;
         this.encryptId = 0;
         this.encryptArray = null;
      } catch (Exception var2) {
      }

      this.exit = true;
      if (MainCanvas.getState() == 11 && MainCanvas.curTopForm != null || MainCanvas.getState() == 14 || MainCanvas.m_bTourist == 1 && MainCanvas.getState() == 4 || MainCanvas.m_bCelerityEnrol == 1) {
         MainCanvas.isFullClose = true;
      }

   }

   public void sendCommands(int[] cmds) {
      this.commands = cmds;
      MainCanvas.curFormVector.removeAllElements();
      if (this.commands.length > 0) {
         this.sendCommands();
         this.isSendingCommands = true;
      }

   }

   public void sendCommands(Vector cmds) {
      Vector commandVector = cmds;
      this.commands = new int[cmds.size()];

      for(int i = 0; i < commandVector.size(); ++i) {
         this.commands[i] = (Integer)commandVector.elementAt(i);
      }

      MainCanvas.curFormVector.removeAllElements();
      if (this.commands.length > 0) {
         this.sendCommands();
         this.isSendingCommands = true;
      }

   }

   public void sendCommands() {
      if (this.commands.length > 0) {
         this.send(this.commands[0]);
         this.commands = Util.removeArray((int[])this.commands, 0);
      }

   }

   public void send(int commID) {
      byte[] buf = (byte[])null;
      buf = this.enCode(commID);
      this.senderPool.addElement(buf);
      synchronized(this) {
         if (socket == null) {
            try {
               this.buildConn();
            } catch (Exception var5) {
            }
         }

      }
   }

   public void deCode() {
      if (this.receiverPool.size() > 0) {
         while(this.receiverPool.size() != 0) {
            ByteArray ba = new ByteArray((byte[])this.receiverPool.elementAt(0));
            CommandParse.getInstance().receive_Parse(ba);
            this.receiverPool.removeElementAt(0);
         }
      }

   }

   private boolean receiveInfo() {
      try {
         short recInfoLength = true;
         short recInfoLength = this.socketInputStream.readShort();

         while(recInfoLength > 0) {
            byte[] tmp = new byte[recInfoLength - 2];

            for(int index = 0; index - tmp.length != 0; index += this.socketInputStream.read(tmp, index, tmp.length - index)) {
            }

            this.receiverPool.addElement(tmp);

            try {
               recInfoLength = this.socketInputStream.readShort();
            } catch (Exception var5) {
               return true;
            }
         }

         return true;
      } catch (Exception var6) {
         if (MainCanvas.m_bCompelDroptMeshwork == 0) {
            PassPort.loseConnect();
            MainCanvas.mc.Abnormity();
         }

         var6.printStackTrace();
         return false;
      }
   }

   public boolean sendInfo() {
      if (this.socketOutputStream == null) {
         return true;
      } else {
         try {
            if (this.senderPool.size() != 0) {
               for(; this.senderPool.size() != 0; this.senderPool.removeElementAt(0)) {
                  byte[] tmpBuf = (byte[])this.senderPool.elementAt(0);
                  if (tmpBuf != null) {
                     this.socketOutputStream.write(tmpBuf);
                     MainCanvas.serverTick = 0;
                  }
               }
            } else {
               ++MainCanvas.serverTick;
            }

            this.socketOutputStream.flush();
            return true;
         } catch (Exception var2) {
            var2.printStackTrace();
            return false;
         }
      }
   }

   private byte[] enCode(int commID) {
      byte[] tmpData = (byte[])null;
      short tmpLength = 0;
      byte[] temp = (byte[])null;
      temp = CommandParse.getInstance().send_Parse(commID);
      if (temp != null) {
         tmpData = temp;
         tmpLength = (short)(temp.length + 4 + 0);
      }

      ByteArray endos = new ByteArray();
      byte checkCode = 0;

      for(int n = 0; n < temp.length; ++n) {
         checkCode += temp[n];
      }

      endos.writeByte(checkCode);
      if (tmpData != null) {
         endos.writeByteArray(tmpData);
      }

      ByteArray dos = new ByteArray();
      dos.writeShort(tmpLength + 1);
      dos.writeByte((byte)65);
      ++this.checkoutID;
      dos.writeByte(this.checkoutID);
      dos.writeByteArray(encrypt(endos.toByteArray(), this.encryptArray, this.checkoutID));
      return dos.toByteArray();
   }

   protected static byte[] encrypt(byte[] src, byte[] encrypt, int sid) {
      if (src == null) {
         return src;
      } else if (encrypt == null) {
         return src;
      } else if (encrypt.length == 0) {
         return src;
      } else {
         byte[] data = new byte[src.length];
         int start = getStart(sid, encrypt.length);
         int i = 0;

         for(int j = start; i < src.length; j += 2) {
            if (j >= encrypt.length) {
               j = 0;
            }

            switch(encrypt[j]) {
            case 38:
               data[i] = (byte)(src[i] - encrypt[j + 1]);
               break;
            case 75:
               data[i] = (byte)(src[i] + encrypt[j + 1]);
               break;
            case 80:
               data[i] = (byte)(~src[i] & 255);
               break;
            case 123:
            default:
               data[i] = (byte)(src[i] ^ encrypt[j + 1]);
            }

            ++i;
         }

         return data;
      }
   }

   private static int getStart(int sid, int length) {
      return ((byte)sid + 256) % (length >> 1) << 1;
   }

   public static String getIP() {
      if (socket == null) {
         return "";
      } else {
         try {
            return socket.getLocalAddress();
         } catch (Exception var1) {
            return "";
         }
      }
   }

   private class Receiver implements Runnable {
      private Thread self = new Thread(this);

      Receiver() {
         this.self.start();
      }

      public void stop() {
         this.self = null;
      }

      public void run() {
         Thread currentThread = Thread.currentThread();

         while(this.self == currentThread) {
            try {
               if (!NetInterface.this.receiveInfo()) {
                  this.stop();
               }

               synchronized(this) {
                  this.wait(100L);
               }

               Thread.yield();
            } catch (Exception var4) {
               var4.printStackTrace();
            }
         }

         NetInterface.getInstance().closeConn();
      }
   }
}

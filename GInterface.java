import java.io.DataOutputStream;
import java.util.Random;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

public class GInterface {
   Random rand = new Random();
   private static final long SENDER_WAIT_TIME = 100L;
   private static final byte NO_NETCOMMD_ID = 65;
   static int firstResult;
   public static SocketConnection socket = null;
   private DataOutputStream socketOutputStream = null;
   private GInterface.Sender sender = null;
   private Vector senderPool = new Vector();
   public byte checkoutID = -1;
   public boolean exit = false;
   private String gIP;
   private String gPort;
   private String gJumpId;
   private static GInterface instance = null;
   public int[] commands = new int[0];
   public boolean isSendingCommands = false;

   private GInterface() {
      this.rand.setSeed(System.currentTimeMillis());
   }

   public static GInterface getInstance() {
      if (instance == null) {
         instance = new GInterface();
      }

      return instance;
   }

   public int getSendNum() {
      return this.senderPool.size();
   }

   private synchronized boolean buildConn() {
      try {
         String url = "socket://" + this.gIP + ":" + this.gPort;
         socket = (SocketConnection)Connector.open(url, 3, false);
         socket.setSocketOption((byte)2, 0);
         socket.setSocketOption((byte)0, 10);
         socket.setSocketOption((byte)1, 5);
         this.socketOutputStream = socket.openDataOutputStream();
         this.sender = new GInterface.Sender();
      } catch (Exception var2) {
         if (this.senderPool != null) {
            this.senderPool = new Vector();
         }

         var2.printStackTrace();
         return false;
      }

      this.exit = false;
      return true;
   }

   public void closeConn() {
      try {
         if (this.sender != null) {
            this.sender.stop();
            this.sender = null;
         }

         if (socket != null) {
            socket.close();
            socket = null;
         }

         socket = null;
         if (this.socketOutputStream != null) {
            this.socketOutputStream.close();
            this.socketOutputStream = null;
         }

         this.checkoutID = -1;
      } catch (Exception var2) {
      }

      this.exit = true;
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
               var5.printStackTrace();
            }
         }

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
                  }
               }
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
      ByteArray dos = new ByteArray();
      if (commID == 1) {
         byte[] data = new byte[10];

         for(int i = 0; i < data.length; ++i) {
            data[i] = (byte)Integer.parseInt(MainCanvas.gi.getGJumpId());
         }

         dos.writeByteArray(data);
      } else {
         String needId = MainCanvas.userID;
         byte[] b = new byte[10];

         int userIDLen;
         for(userIDLen = 0; userIDLen < b.length; ++userIDLen) {
            b[userIDLen] = 32;
         }

         userIDLen = needId.getBytes().length;
         if (userIDLen < b.length) {
            System.arraycopy(needId.getBytes(), 0, b, 0, userIDLen);
         } else {
            b = needId.getBytes();
         }

         String s = "CMCCGAME_userId=" + new String(b);
         dos.writeByteArray(s.getBytes());
      }

      return dos.toByteArray();
   }

   public String getGIP() {
      return this.gIP;
   }

   public void setGIP(String ip) {
      this.gIP = ip;
   }

   public String getGPort() {
      return this.gPort;
   }

   public void setGPort(String port) {
      this.gPort = port;
   }

   public String getGJumpId() {
      return this.gJumpId;
   }

   public void setGJumpId(String jumpId) {
      this.gJumpId = jumpId;
   }

   private class Sender implements Runnable {
      private Thread self = new Thread(this);

      Sender() {
         this.self.start();
      }

      public void stop() {
         this.self = null;
      }

      public void run() {
         Thread currentThread = Thread.currentThread();

         while(this.self == currentThread) {
            try {
               if (!GInterface.this.sendInfo()) {
                  this.stop();
               }

               synchronized(this) {
                  this.wait(100L);
               }

               Thread.yield();
            } catch (Exception var4) {
               this.stop();
               var4.printStackTrace();
            }
         }

         GInterface.getInstance().closeConn();
      }
   }
}

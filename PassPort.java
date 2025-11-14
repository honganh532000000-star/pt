import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.TimeZone;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

public class PassPort implements Runnable {
   public static String[] valueDetail = new String[]{"", "1", "1"};
   public static final String[] valueSign = new String[]{"SMSPRICE", "PROVICE_NO_FOUND", "MOBILE_NO_FOUND"};
   private static String bindDetail = "";
   public static final String bindNumSign = "PASSPORT_BOND";
   public static String chargeDetil = "";
   public static String chargeCode = "";
   public static String chargeRemain = "";
   public static String resultInfo = null;
   public static String suggestion = "";
   public static String passportid = "";
   public static String virUser = "";
   public static String servercontent = "";
   public static String localPlace = "";
   public static String price = "";
   public static String[][] updateListInfo;
   public static String[] updateServer;
   private static final int MD5_LENGTH = 32;
   private String userId;
   private String key;
   int headMark = 0;
   public static int kongFirstResult = -1;
   public byte validateType = 0;
   public static final byte VALIDATE_REGISTER = 0;
   public static final byte VALIDATE_LOGIN = 1;
   public static final byte VALIDATE_MODIFY = 2;
   public static final byte VALIDATE_CHARGE = 3;
   public static final byte VALIDATE_SEIZING = 4;
   public static final byte VALIDATE_LOSE = 5;
   public static final byte VALIDATE_CM_GET_USERID = 6;
   public static final byte VALIDATE_CHARGE_REMAIN = 7;
   public static final byte VALIDATE_CHARGE_HISTORY = 8;
   public static final byte VALIDATE_BUY_HISTORY = 9;
   public static final byte VALIDATE_BUY = 10;
   public static final byte VALIDATE_PROVINCE = 11;
   public static final byte VALIDATE_CM_JAD_DOWNLOAD = 12;
   public static final byte VALIDATE_CM_JAD_NOTIFY = 13;
   public static final byte VALIDATE_CM_JAR_DOWNLOAD = 14;
   public static final byte VALIDATE_GET_SMS_FORMAT = 15;
   public static final byte VALIDATE_MONTHLY = 20;
   public static final byte VALIDATE_CAPCHA = 21;
   public static byte menuSelected = 22;
   public static final byte VALIDATE_STATE_CAPCHA = 22;
   public static final byte VALIDATE_STATE_FAST_REGISTER = 23;
   public static final byte VALIDATE_STATE_REGISTER = 24;
   public static final byte VALIDATE_STATE_LOGIN = 25;
   public static final byte VALIDATE_STATE_CHANGE_PASSWORD = 26;
   public static final byte VALIDATE_PAYMENT = 27;
   public static final byte VALIDATE_STATE_RECHARD = 29;
   public static final byte VALIDATE_STATE_UPDATE = 30;
   public static final int CLIENT_TYPE = 5;
   public static String updateVerUrl = "";
   public static String requireActive = "";
   public static final String VITALK_PASSPORT_IP = "http://vitalk.vn";
   public static final String VITALK_PASSPORT_IPI_LINK = "/api/phongthanapi.aspx?op=";
   public static byte loginType = 1;
   public static String ptmActiveStr = "";
   public static String ptmCode = "";
   public static String ptmSMSHeader = "";
   public static String ptmActiveCode = "";
   public static String ptmForgotPass = "";
   public static String capchaTicks = "";
   public static String strPassPortCurrUrl = "";
   public static boolean checkInfoLoginSuccess = false;
   public static int capchaAnswer = -1;
   public static boolean waitActive = false;
   public static final byte VALIDATE_ACTIVE_WAIT = 31;
   public static final String PASSPORT_IP = "http://192.168.0.105:68 ";
   public static final String PASSPORT_LINK = "/Default.aspx?op=";
   public byte loginServerType = 0;
   public static final byte LOGIN_TYPE_NONE = 0;
   public static final byte LOGIN_TYPE_CM = 1;
   public static final byte LOGIN_TYPE_PASSPORT = 2;
   public static final byte LOGIN_TYPE_CHARG = 3;
   public static boolean isFirstConnect = true;
   public static boolean isLocalServerList = false;
   public static String[][] serverListContent;
   public static String[] serverDivArr;
   public static String serverDivName;
   public static String[] recommandServer = new String[4];
   public static byte selectDivIndex = 1;
   private byte[] content;
   public static final String URL_WAPROOT_IP = "http://10.0.0.172";
   public static String serverListString = "";
   public static final String REC_SERVER = "last_time_login";
   public static int VER = 2;
   public static String KONG_URL;
   public static final String KONG_TEST_IP = "202.108.44.149:9081";
   public static final String KONG_TEST_URL = "/judgeserver/judge?ver=2&";
   public static String KONG_PASSPORT_IP;
   public static String CM_PASSPORT_IP;
   public static String CM_CHARGE_IP;
   public static final String CM_PASSPORT_TJ_GET_URL = "/bizcontrol/LoginOnlineGame?sender=202&cpId=C00002&cpServiceId=120120438000&channelId=1000&fid=1000";
   public static String CM_PASSPORT_GET_URL;
   public static String gplusDownloadIp;
   public static String jadDownloadUrl;
   public static String jadNotifyUrl;
   public static String jarDownloadUrl;
   public static String userAgent;
   public static final byte CM_CLIENT = 0;
   public static final byte CM_WAP = 1;
   public static final byte CM_CHEAT = 2;
   public static final byte CM_CHEAT_JAR = 3;
   public static byte cm_type;
   public static String fidCode;
   public static int chargeNum;
   public static boolean chargeSuccessful;
   public static boolean isDetail;
   public static final byte H_COUNT = 100;
   public static String history;
   public static byte chooseType;
   public static byte timeType;
   public static int selMonth;
   public static final String LOSE_PASSPORT_IP = "61.135.147.34";
   public static byte loseMark;
   public static final byte CONNECT_WAP = 0;
   public static final byte CONNECT_NET = 1;
   private byte connectType = 0;
   private String passPortIP;
   private String getURL;
   private byte connectState;
   public static final byte CONNECT_UNDO = 0;
   public static final byte CONNECT_WAIT = 1;
   public static final byte CONNECT_SUCCESS = 2;
   public static final byte CONNECT_FAIL = 3;
   public static boolean isEndLoginValidate;
   public static boolean isEndRegisterValidate;
   public static boolean isEndChangePswValidate;
   public static boolean isEndQuickRegisterValidate;
   public static boolean m_bBingValidate;
   public static boolean isEndChargeValidate;
   public static boolean isEndRemainValidate;
   public static boolean isEndChargeHistoryValidate;
   public static boolean isEndBuyHistoryValidate;
   public static String imei;
   private static String repassportid;
   public static boolean isQuick;
   public static boolean isQuickSuccess;
   public static boolean isConnectOK;
   public boolean isInConnecting;
   public static int sysYear;
   public static int sysMonth;
   public static boolean isVitalk;
   public static int rechardType;
   public static final int PARNER_ID;
   static boolean hasNotify;
   public static String cmStr;
   public static boolean isRequestGPlusURL;
   public static String sms_coin;
   public static String sms_bond;
   public static String sms_seek;
   public static String sms_del;
   static long giStartTime;
   static Thread t;
   static final int MIN_YEAR = 2009;
   public static PassPort Monthly;
   String[][] bodyValue = null;

   static {
      KONG_URL = "/mammoth/judgeserver/judge?ver=" + VER + "&";
      KONG_PASSPORT_IP = "61.135.154.43";
      CM_PASSPORT_IP = "gmp.i139.cn";
      CM_CHARGE_IP = "221.179.216.39";
      CM_PASSPORT_GET_URL = "/bizcontrol/LoginWapOnlineGame?sender=202&cpId=C00042&cpServiceId=220121871000&channelId=15042000&p=client";
      userAgent = "";
      cm_type = 0;
      fidCode = "1000";
      chargeNum = 0;
      chargeSuccessful = false;
      isDetail = false;
      history = "";
      chooseType = 0;
      timeType = 0;
      selMonth = 0;
      loseMark = 0;
      isEndLoginValidate = false;
      isEndRegisterValidate = false;
      isEndChangePswValidate = false;
      isEndQuickRegisterValidate = false;
      m_bBingValidate = false;
      isEndChargeValidate = false;
      isEndRemainValidate = false;
      isEndChargeHistoryValidate = false;
      isEndBuyHistoryValidate = false;
      imei = "";
      repassportid = "";
      isQuick = false;
      isQuickSuccess = false;
      isConnectOK = false;
      isVitalk = true;
      rechardType = 1;
      PARNER_ID = Integer.parseInt(Partners.getPartner());
      hasNotify = false;
      cmStr = "";
      isRequestGPlusURL = false;
      t = null;
      Monthly = null;
   }

   public PassPort(String passPortIP, String getURL, byte connectType, byte loginServerType) {
      Util.debugPrint("Đăng nhập vào thẻ thông hành server");
      repassportid = MainCanvas.aMidlet.getAppProperty("suggest");
      if (repassportid == null || "".equals(repassportid.trim())) {
         repassportid = "";
      }

      this.passPortIP = passPortIP;
      this.getURL = getURL;
      this.connectType = connectType;
      this.loginServerType = loginServerType;
      this.connectState = 0;
   }

   public void authenticate(byte validateType) {
      this.connectState = 1;
      this.validateType = validateType;
      Thread thread = new Thread(this);
      thread.start();
   }

   public void run() {
      if (!isVitalk) {
         this.processHttpConn();
         this.processHttpContent();
         this.processValidate();
      } else {
         this.processHttpConnVitalk();
         this.processValidate();
      }

   }

   public static String readHttpConnResult(HttpConnection hconn) throws IOException {
      String str = "";
      InputStream inputstream = hconn.openInputStream();
      int length = (int)hconn.getLength();
      if (length != -1) {
         byte[] incomingData = new byte[length];
         inputstream.read(incomingData);
         str = new String(incomingData, "UTF-8");
         inputstream.close();
      } else {
         ByteArrayOutputStream bytestream = new ByteArrayOutputStream();

         int ch;
         while((ch = inputstream.read()) != -1) {
            bytestream.write(ch);
         }

         str = new String(bytestream.toByteArray(), "UTF-8");
         bytestream.close();
      }

      return str;
   }

   public static void validateCardRechard(String serial, String pin, String type) {
      if (type.equals("Gate")) {
         strPassPortCurrUrl = "http://192.168.0.105:68 /Default.aspx?op=21&uname=" + (loginType == 0 ? "" : "pt") + MainCanvas.name_password[0] + "&serial=" + serial + "&pin=" + pin + "&pid=" + PARNER_ID;
      } else if (type.equals("Mobile")) {
         strPassPortCurrUrl = "http://192.168.0.105:68 /Default.aspx?op=22&uname=" + (loginType == 0 ? "" : "pt") + MainCanvas.name_password[0] + "&cardType=0&pin=" + pin + "&pid=" + PARNER_ID;
      } else if (type.equals("Vina")) {
         strPassPortCurrUrl = "http://192.168.0.105:68 /Default.aspx?op=22&uname=" + (loginType == 0 ? "" : "pt") + MainCanvas.name_password[0] + "&cardType=1&pin=" + pin + "&pid=" + PARNER_ID;
      } else if (type.equals("Viettel")) {
         strPassPortCurrUrl = "http://192.168.0.105:68 /Default.aspx?op=23&uname=" + (loginType == 0 ? "" : "pt") + MainCanvas.name_password[0] + "&serial=" + serial + "&pin=" + pin + "&pid=" + PARNER_ID;
      } else if (type.equals("Vcoin")) {
         strPassPortCurrUrl = "http://192.168.0.105:68 /Default.aspx?op=24&uname=" + (loginType == 0 ? "" : "pt") + MainCanvas.name_password[0] + "&serial=" + serial + "&pin=" + pin + "&pid=" + PARNER_ID;
      }

      strPassPortCurrUrl = strPassPortCurrUrl + "&mtype=" + rechardType;
   }

   public void processHttpConnVitalk() {
      try {
         if (this.validateType == 31) {
            strPassPortCurrUrl = "http://192.168.0.105:68 /Default.aspx?op=5&uname=" + (loginType == 0 ? "" : "pt") + MainCanvas.name_password[0];
         } else if (this.validateType == 30) {
            strPassPortCurrUrl = "http://192.168.0.105:68 /Default.aspx?op=4&pid=" + PARNER_ID + "&ctype=" + 5 + "&ver=" + GameLogin.m_sVersion;
         } else if (this.validateType == 21) {
            strPassPortCurrUrl = "http://192.168.0.105:68 /Default.aspx?op=1";
         } else if (this.validateType == 1) {
            strPassPortCurrUrl = "http://192.168.0.105:68 /Default.aspx?op=12&uname=" + Base64.toBase64(MainCanvas.name_password[0]) + "&ps=" + Base64.toBase64(MainCanvas.name_password[1]) + "&pid=" + PARNER_ID + "&type=" + loginType;
         } else if (this.validateType == 0) {
            if (menuSelected == 23) {
               loginType = 1;
               strPassPortCurrUrl = "http://192.168.0.105:68 /Default.aspx?op=10&ticks=" + capchaTicks + "&rp=" + capchaAnswer + "&pid=" + PARNER_ID;
            } else if (menuSelected == 24) {
               strPassPortCurrUrl = "http://192.168.0.105:68 /Default.aspx?op=11&uname=" + Base64.toBase64(MainCanvas.name_password[0]) + "&ps=" + Base64.toBase64(MainCanvas.name_password[1]) + "&pid=" + PARNER_ID;
            }
         } else if (this.validateType == 2) {
            strPassPortCurrUrl = "http://192.168.0.105:68 /Default.aspx?op=13&uname=" + Base64.toBase64((loginType == 1 ? "pt" : "") + MainCanvas.name_password[0]) + "&oldps=" + Base64.toBase64(MainCanvas.name_password[1]) + "&newps=" + Base64.toBase64(MainCanvas.m_sTemporaryStr[2]);
         }

         System.out.println("PassPort - Url: " + strPassPortCurrUrl);
         HttpConnection hconn = (HttpConnection)Connector.open(strPassPortCurrUrl, 3);
         hconn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
         hconn.setRequestProperty("User-Agent", "Profile/MIDP-2.0 Configuration/CLDC-1.1");
         hconn.setRequestProperty("Accept-Charset", "UTF-8;q=0.7, *;q=0.7");
         int responseCode = hconn.getResponseCode();
         MainCanvas.reponseCode = responseCode;
         if (responseCode == 200) {
            isConnectOK = true;
            this.connectState = 2;
            String str = readHttpConnResult(hconn);
            if (this.validateType == 31) {
               System.out.println("UITopForm.processHttpConnVitalk() VALIDATE_ACTIVE_WAIT: " + str);
               if (str.equals("1")) {
                  waitActive = false;
                  MainCanvas.activeStartTime = -1L;
                  gotoGame();
               }
            } else {
               String[] res;
               if (this.validateType == 30) {
                  System.out.println("UITopForm.processHttpConnVitalk() VALIDATE_STATE_UPDATE: " + str);
                  res = new String[9];
                  res = Util.split(str, "|");
                  if (res[0].equals("0")) {
                     NetInterface.serverUrl = NetInterface.serverUrl + res[1];
                     ptmCode = res[2];
                     ptmSMSHeader = res[3];
                     ptmActiveCode = res[4];
                     ptmForgotPass = res[5];
                     requireActive = res[6];
                  } else {
                     str = res[1];
                     updateVerUrl = res[2];
                     NetInterface.serverUrl = NetInterface.serverUrl + res[3];
                     ptmCode = res[4];
                     ptmSMSHeader = res[5];
                     ptmActiveCode = res[6];
                     ptmForgotPass = res[7];
                     requireActive = res[8];
                     MainCanvas.TopForm((byte)0, str, "Đồng ý", "Thoát", -1610612608, -1610612607);
                  }
               } else if (this.validateType == 21) {
                  res = new String[2];
                  res = Util.split(str, "|");
                  capchaTicks = res[0];
                  UITopForm.createLocalTopForm((byte)1, "Nhập kết quả [" + res[1] + "] vào ô bên dưới", "Đồng ý", "Đóng", -1610612616, -1, -1, -1);
               } else if (this.validateType == 0) {
                  res = new String[4];
                  res = Util.split(str, "|");
                  if (res[0].equals("-1")) {
                     kongFirstResult = -1;
                     MainCanvas.TopForm((byte)0, res[1], "", "Đóng", -2, -1);
                  } else {
                     MainCanvas.userID = MainCanvas.name_password[0] = res[1];
                     MainCanvas.userKey = MainCanvas.name_password[1] = Base64.toString(res[3]);
                     if (menuSelected == 23) {
                        kongFirstResult = 1;
                        isQuickSuccess = true;
                     } else if (menuSelected == 24) {
                        kongFirstResult = -1;
                        if (requireActive.equals("1")) {
                           MainCanvas.TopForm((byte)0, "Yêu cầu kích hoạt tài khoản.", "Kích hoạt", "Bỏ qua", -1610612615, -1610612606);
                        } else if (requireActive.equals("2")) {
                           MainCanvas.TopForm((byte)0, "Yêu cầu kích hoạt tài khoản.", "Kích hoạt", "Đóng", -1610612615, -1);
                        } else {
                           gotoGame();
                        }
                     }

                     Util.saveStrRecord(MainCanvas.name_password, "name_password");
                  }
               } else if (this.validateType == 1) {
                  System.out.println("UITopForm.keyInOtherState() VALIDATE_LOGIN: " + str);
                  res = new String[5];
                  res = Util.split(str, "|");
                  if (res[0].equals("-1")) {
                     MainCanvas.TopForm((byte)0, res[1], "", "Đóng", -2, -1);
                  } else {
                     checkInfoLoginSuccess = true;
                     ptmActiveStr = res[3];
                     loginType = Byte.parseByte(res[4]);
                     if (ptmActiveStr.equals("0")) {
                        if (requireActive.equals("1")) {
                           MainCanvas.TopForm((byte)0, "Yêu cầu kích hoạt tài khoản.", "Kích hoạt", "Bỏ qua", -1610612615, -1610612606);
                        } else if (requireActive.equals("2")) {
                           MainCanvas.TopForm((byte)0, "Yêu cầu kích hoạt tài khoản.", "Kích hoạt", "Đóng", -1610612615, -1);
                        } else {
                           gotoGame();
                        }
                     } else {
                        gotoGame();
                     }
                  }
               } else if (this.validateType == 2) {
                  System.out.println("http://192.168.0.105:68 /Default.aspx?op=13&uname=" + (loginType == 1 ? "pt" : "") + MainCanvas.name_password[0] + "&oldps=" + MainCanvas.name_password[1] + "&newps=" + MainCanvas.m_sTemporaryStr[2]);
                  res = new String[2];
                  res = Util.split(str, "|");
                  if (!res[0].equals("-1")) {
                     kongFirstResult = -1;
                     MainCanvas.name_password[1] = MainCanvas.m_sTemporaryStr[2];
                     Util.saveStrRecord(MainCanvas.name_password, "name_password");
                     MainCanvas.setState((byte)11);
                  }

                  kongFirstResult = -1;
                  MainCanvas.TopForm((byte)0, res[1], "", "Đóng", -2, -1);
               } else if (this.validateType == 27) {
                  res = new String[2];
                  res = Util.split(str, "|");
                  str = res[0].equals("-1") ? res[1] : "Bạn đã nạp thành công " + res[1] + " KNB.";
                  kongFirstResult = -1;
                  MainCanvas.TopForm((byte)0, str, "", "Đóng", -2, -1);
               }
            }

            hconn.close();
            hconn = null;
         }
      } catch (Exception var5) {
         var5.printStackTrace();
         if (this.isInConnecting) {
            this.isInConnecting = false;
            this.connectState = 0;
         } else {
            this.connectState = 3;
         }
      }

   }

   public static void gotoGame() {
      MainCanvas.loadPlayerRes();
      kongFirstResult = 1;
      MainCanvas.checkServerUrl();
      MainCanvas.checkJumpID();
      MainCanvas.ni.send(65537);
   }

   private void processHttpConn() {
      try {
         String tempAddress = null;
         if (this.connectType == 0) {
            tempAddress = "http://10.0.0.172" + this.getURL;
         } else if (this.connectType == 1) {
            tempAddress = "http://" + this.passPortIP + this.getURL;
         }

         HttpConnection hconn = (HttpConnection)Connector.open(tempAddress, 3);
         hconn.setRequestProperty("X-Online-Host", this.passPortIP);
         this.setHeadInfo(hconn);
         if (this.validateType == 13) {
            this.setPost(hconn);
         }

         int responseCode = hconn.getResponseCode();

         String contentTypeStr;
         for(MainCanvas.reponseCode = responseCode; responseCode == 302 && this.validateType == 20; responseCode = hconn.getResponseCode()) {
            contentTypeStr = hconn.getHeaderField("location");
            String[] temp = Util.splitToken(contentTypeStr, '/');
            PCMonthly.logAddress = contentTypeStr;
            String aimIP = temp[2];
            String aimURL = "/" + contentTypeStr.substring(contentTypeStr.indexOf(temp[3]));
            hconn = (HttpConnection)Connector.open(contentTypeStr);
            hconn.setRequestProperty("X-Online-Host", aimIP);
            this.setHeadInfo(hconn);
         }

         if (responseCode == 200) {
            isConnectOK = true;
            contentTypeStr = hconn.getHeaderField("Content-Type");
            boolean isFirstWap = false;
            if (contentTypeStr != null && contentTypeStr.indexOf("vnd.wap.wml") != -1) {
               isFirstWap = true;
            }

            if (isFirstWap) {
               hconn.close();
               hconn = null;
               HttpConnection hconn2 = (HttpConnection)Connector.open("http://10.0.0.172" + this.getURL, 3);
               hconn2.setRequestProperty("X-Online-Host", this.passPortIP);
               this.setHeadInfo(hconn2);
               if (this.validateType == 13) {
                  this.setPost(hconn2);
               }

               if (hconn2.getResponseCode() == 200) {
                  if (this.validateType == 12) {
                     cmStr = "Vào địa chỉ download Jad ";
                     if (cm_type != 2 && cm_type != 0) {
                        if (cm_type == 3 || cm_type == 0) {
                           accessJarDownloadUrl();
                        }
                     } else {
                        accessJadNotifyUrl();
                     }

                     return;
                  }

                  if (this.validateType == 13) {
                     hasNotify = true;
                     cmStr = "Vào địa chỉ test Jad thành công";
                     return;
                  }

                  if (this.validateType == 14) {
                     hasNotify = true;
                     cmStr = "vào địa chỉ download Jar thành công";
                     accessJadNotifyUrl();
                     return;
                  }

                  this.getResultContent(hconn2, (int)hconn2.getLength());
                  this.connectState = 2;
               }
            } else {
               if (this.validateType == 12) {
                  cmStr = "Vào địa chỉ download Jad thành công";
                  if (cm_type != 2 && cm_type != 0) {
                     if (cm_type == 3 || cm_type == 0) {
                        accessJarDownloadUrl();
                     }
                  } else {
                     accessJadNotifyUrl();
                  }

                  return;
               }

               if (this.validateType == 13) {
                  hasNotify = true;
                  cmStr = "Vào địa chỉ test Jad thành công";
                  return;
               }

               if (this.validateType == 14) {
                  hasNotify = true;
                  cmStr = "Vào địa chỉ download Jar thành công";
                  accessJadNotifyUrl();
                  return;
               }

               if (this.processGetUserID(hconn, responseCode)) {
                  return;
               }

               this.getResultContent(hconn, 0);
               this.connectState = 2;
            }
         } else if (responseCode == 302) {
            this.processGetUserID(hconn, responseCode);
         } else if (this.isInConnecting) {
            this.isInConnecting = false;
            this.connectState = 0;
         } else {
            this.connectState = 3;
         }
      } catch (Exception var8) {
         var8.printStackTrace();
         if (this.isInConnecting) {
            this.isInConnecting = false;
            this.connectState = 0;
         } else {
            this.connectState = 3;
         }
      }

   }

   private boolean processGetUserID(HttpConnection hconn, int responseCode) throws IOException {
      if (this.loginServerType == 1 && this.validateType == 6) {
         int keyIndex;
         int rspCode;
         if (responseCode == 200 && cm_type != 0) {
            DataInputStream is = hconn.openDataInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            boolean var15 = false;

            while((keyIndex = is.read()) != -1) {
               baos.write(keyIndex);
            }

            byte[] userIDContent = baos.toByteArray();
            baos.close();
            StringBuffer tmpStr = new StringBuffer();

            for(rspCode = 0; rspCode < userIDContent.length; ++rspCode) {
               tmpStr.append((char)userIDContent[rspCode]);
            }

            String sb = tmpStr.toString();
            int idStart = sb.indexOf("userId=");
            int idEnd = sb.indexOf("\r", idStart);
            if (idStart != -1 && idEnd != -1) {
               this.userId = sb.substring(idStart + 7, idEnd);
               int keyStart = sb.indexOf("key=", idEnd);
               int keyEnd = sb.indexOf("\r", keyStart);
               this.key = sb.substring(keyStart + 4, keyEnd);
               if (!"".equals(this.userId) && !MainCanvas.startTickUser) {
                  MainCanvas.startTickUser = true;
               }
            } else {
               this.userId = this.key = "";
            }

            if (this.userId != null) {
               MainCanvas.userID = this.userId;
            }

            if (this.key != null) {
               MainCanvas.userKey = this.key;
            }

            if (!"".equals(this.userId) && !MainCanvas.startTickUser) {
               MainCanvas.startTickUser = true;
            }

            cm_type = 0;
            CM_PASSPORT_GET_URL = "/bizcontrol/LoginOnlineGame?sender=202&cpId=C00002&cpServiceId=120120438000&channelId=1000&fid=1000";
            onlyGetUserID();
            return true;
         } else if (responseCode != 302) {
            return false;
         } else {
            String new_uri = hconn.getHeaderField("Location");
            MainCanvas.new_uri = new_uri;
            if (new_uri != null) {
               int idIndex = new_uri.indexOf("userId");
               keyIndex = new_uri.indexOf("key");
               int op_1 = new_uri.indexOf(38, 0);
               int op_2 = new_uri.indexOf(38, op_1 + 1);
               if (idIndex != -1) {
                  this.userId = new_uri.substring(idIndex + 7, op_1);
                  this.key = new_uri.substring(keyIndex + 4, op_2);
                  MainCanvas.userID = this.userId;
                  MainCanvas.userKey = this.key;
               }

               hconn.close();
               if ("".equals(this.userId) || this.userId == null) {
                  hconn = (HttpConnection)Connector.open(new_uri, 3);
                  rspCode = hconn.getResponseCode();
                  if (rspCode == 200) {
                     MainCanvas.secondConResult = "Liên kết 2 lần thành công!!";
                     isConnectOK = true;
                     this.userId = hconn.getHeaderField("userId");
                     this.key = hconn.getHeaderField("key");
                     if (this.userId != null) {
                        MainCanvas.userID = this.userId;
                     }

                     if (this.key != null) {
                        MainCanvas.userKey = this.key;
                     }
                  }
               }

               cm_type = 0;
               CM_PASSPORT_GET_URL = "/bizcontrol/LoginOnlineGame?sender=202&cpId=C00002&cpServiceId=120120438000&channelId=1000&fid=1000";
               onlyGetUserID();
               if (!"".equals(this.userId) && !MainCanvas.startTickUser) {
                  MainCanvas.startTickUser = true;
               }
            } else {
               cm_type = 0;
               CM_PASSPORT_GET_URL = "/bizcontrol/LoginOnlineGame?sender=202&cpId=C00002&cpServiceId=120120438000&channelId=1000&fid=1000";
               onlyGetUserID();
            }

            return true;
         }
      } else {
         return false;
      }
   }

   private void processHttpContent() {
      if (this.connectState == 2) {
         ByteArrayInputStream bs;
         DataInputStream is;
         String kongMD5;
         int keyStart;
         int j;
         switch(this.loginServerType) {
         case 0:
            bs = new ByteArrayInputStream(this.content);
            is = new DataInputStream(bs);

            try {
               if (VER == 1) {
                  short tmpLength = is.readShort();
                  kongFirstResult = is.readByte();
               } else if (VER == 2) {
                  kongMD5 = is.readUTF();
                  this.setBodyData(kongMD5);
                  this.getBodyInfo();
                  kongMD5 = null;
               }
            } catch (Exception var8) {
               var8.printStackTrace();
            }
            break;
         case 1:
            if (cm_type == 1 && this.validateType == 6) {
               kongFirstResult = -1;
               return;
            }

            try {
               StringBuffer tmpStr = new StringBuffer();

               for(int i = 0; i < this.content.length; ++i) {
                  tmpStr.append((char)this.content[i]);
               }

               String sb = tmpStr.toString();
               int idStart = sb.indexOf("userId=");
               int idEnd = sb.indexOf("\r", idStart);
               if (idStart != -1 && idEnd != -1) {
                  this.userId = sb.substring(idStart + 7, idEnd);
                  keyStart = sb.indexOf("key=", idEnd);
                  j = sb.indexOf("\r", keyStart);
                  this.key = sb.substring(keyStart + 4, j);
                  if (!"".equals(this.userId) && !MainCanvas.startTickUser) {
                     MainCanvas.startTickUser = true;
                  }
               } else {
                  this.userId = this.key = "";
               }

               kongFirstResult = -1;
            } catch (Exception var10) {
               var10.printStackTrace();
            }
            break;
         case 2:
            bs = new ByteArrayInputStream(this.content);
            is = new DataInputStream(bs);

            try {
               if (VER != 1) {
                  if (VER == 2) {
                     kongMD5 = is.readUTF();
                     this.setBodyData(kongMD5);
                     this.getBodyInfo();
                     kongMD5 = null;
                  }
               } else {
                  kongMD5 = "";
                  short tmpLength = is.readShort();
                  kongFirstResult = is.readByte();

                  for(keyStart = 0; keyStart < 32; ++keyStart) {
                     kongMD5 = kongMD5 + is.readChar();
                  }

                  MainCanvas.kongMD5 = kongMD5;
                  StringBuffer sb = new StringBuffer();
                  j = 0;

                  for(int m = tmpLength - 1 - 64 >> 1; j < m; ++j) {
                     sb.append(is.readChar());
                  }

                  serverListString = sb.toString();
               }

               is.close();
               bs.close();
            } catch (Exception var9) {
               var9.printStackTrace();
            }
            break;
         case 3:
            kongFirstResult = -1;
            if (chargeDetil != null && !chargeDetil.equals("1")) {
               if (chargeCode == null) {
                  this.connectState = 3;
               } else {
                  switch(this.validateType) {
                  case 3:
                     if (chargeCode.equals("1100")) {
                        kongFirstResult = 1;
                     } else {
                        kongFirstResult = 100;
                     }
                  case 4:
                  case 5:
                  case 6:
                  default:
                     break;
                  case 7:
                     if (chargeCode.equals("1105")) {
                        kongFirstResult = 1;
                     } else {
                        kongFirstResult = 101;
                     }
                     break;
                  case 8:
                     if (!chargeCode.equals("1104") && !chargeCode.equals("1102")) {
                        kongFirstResult = 101;
                     } else {
                        kongFirstResult = 1;
                        this.setHistroy();
                     }
                     break;
                  case 9:
                     if (chargeCode.equals("1102")) {
                        kongFirstResult = 1;
                        this.setHistroy();
                     } else {
                        kongFirstResult = 101;
                     }
                     break;
                  case 10:
                     kongFirstResult = 1;
                  }
               }
            } else {
               this.connectState = 3;
            }
         }
      } else if (this.connectState == 3) {
         this.userId = this.key = null;
      }

   }

   private void processValidate() {
      if (this.connectState == 2) {
         if (isLocalServerList && kongFirstResult == 1 && this.validateType == 1 && checkInfoLoginSuccess) {
            checkInfoLoginSuccess = false;
         } else {
            this.processValidateResult();
         }
      } else if (this.connectState == 3) {
         if (MainCanvas.isAutoConn && this.connectType == 0) {
            byte mark = 1;
            switch(this.validateType) {
            case 0:
               if (isQuick) {
                  if (MainCanvas.m_bTourist == 1) {
                     mark = 0;
                     MainCanvas.name_password[0] = "tjuser";
                     MainCanvas.name_password[1] = "121212";
                  } else {
                     mark = 5;
                  }
               } else {
                  mark = 0;
               }
               break;
            case 1:
               mark = 1;
               break;
            case 2:
               mark = 2;
            }

            PassPort pp = new PassPort(KONG_PASSPORT_IP, getURL(mark), (byte)1, (byte)2);
            isFirstConnect = true;
            pp.authenticate(this.validateType);
         } else {
            loseConnect();
            if (MainCanvas.curTopForm != null) {
               MainCanvas.curTopForm = null;
            }

            if (this.validateType == 1) {
               MainCanvas.TopForm((byte)0, "Đăng nhập thất bại, thay đổi vị trí mạng và thử lại", Cons.C_STR[2], "", -10, -120);
            } else if (this.validateType != 6 && this.validateType != 11 && this.validateType != 5) {
               MainCanvas.TopForm((byte)0, "Liên kết thất bại, hãy thử lại", Cons.C_STR[2], "", -7, -120);
            }
         }
      }

   }

   private void processKongZhongValidateResult() {
      if (this.validateType != 1 || !isLocalServerList || kongFirstResult != 1 || MainCanvas.isChinaMobileVer) {
         switch(kongFirstResult) {
         case 1:
            this.processAfterSuccessValidate();
            break;
         case 4:
            if (!"".equals(suggestion)) {
               if (isQuick) {
                  isEndQuickRegisterValidate = true;
               } else {
                  if (MainCanvas.m_bTourist == 1) {
                     UITopForm.createLocalTopForm((byte)0, (String)("Tài khoản đã tồn tại, hãy đổi thành:" + suggestion), Cons.C_STR[2], "", -24, -2);
                  } else {
                     MainCanvas.TopForm((byte)0, "Tài khoản đã tồn tại, có thể sửa thành khoản đã tồn tại, có thể sửa thành：" + suggestion, Cons.C_STR[2], "", -9, -120);
                  }

                  if (MainCanvas.name_password == null) {
                     MainCanvas.name_password = new String[2];
                  }

                  MainCanvas.name_password[0] = suggestion;
                  MainCanvas.name_password[1] = suggestion;
               }
            } else if (isQuick) {
               MainCanvas.TopForm((byte)0, "Đăng ký nhanh thất bại, hãy thử lại", Cons.C_STR[2], "", -7, -120);
            } else {
               MainCanvas.TopForm((byte)0, resultInfo, Cons.C_STR[2], "", -7, -120);
            }
            break;
         case 11:
            if (MainCanvas.curTopForm != null) {
               MainCanvas.curTopForm = null;
            }

            MainCanvas.TopForm((byte)0, "Hiện nay đang cập nhật nội dung mới, cần tải về client mới nhất, hãy đến và trải nghiệm!", "Tải về", "Quay về", -8, -3);
            break;
         case 100:
            chargeSuccessful = false;
            if (this.validateType == 3) {
               isEndChargeValidate = true;
            } else if (this.validateType == 7) {
               isEndRemainValidate = true;
            }
            break;
         case 101:
            MainCanvas.TopForm((byte)0, "Thao tác thất bại, hãy thử lại hoặc liên hệ server!", Cons.C_STR[2], "", -7, -30);
         case 102:
            break;
         default:
            if (MainCanvas.curTopForm != null) {
               MainCanvas.curTopForm = null;
            }

            if (resultInfo == null) {
               resultInfo = "Thao tác thất bại, hãy thử lại";
            }

            MainCanvas.TopForm((byte)0, resultInfo, Cons.C_STR[2], "", -7, -30);
            MainCanvas.m_sBingError = true;
         }

      }
   }

   private void processValidateResult() {
      String charge_url;
      PassPort pp;
      switch(this.validateType) {
      case 0:
      case 1:
      case 2:
      case 4:
      case 5:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      default:
         break;
      case 3:
         if (isFirstConnect) {
            MainCanvas.userID = this.getUserId();
            MainCanvas.userKey = this.getKey();
            charge_url = "/judgeserver/charge?&userId=" + MainCanvas.userID + "&key=" + MainCanvas.userKey + "&game=fengshen&money=" + chargeNum + "&from=kong";
            pp = new PassPort(CM_CHARGE_IP, charge_url, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)3);
            pp.authenticate((byte)3);
            isFirstConnect = false;
         }
         break;
      case 6:
         if (isFirstConnect) {
            MainCanvas.userID = this.getUserId();
            MainCanvas.userKey = this.getKey();
            isFirstConnect = false;
         }
         break;
      case 7:
         if (isFirstConnect) {
            MainCanvas.userID = this.getUserId();
            MainCanvas.userKey = this.getKey();
            charge_url = "/judgeserver/QueryBalance?usr=&userId=" + MainCanvas.userID + "&game=fengshen&subgame=" + "fengshen" + "&from=kong";
            pp = new PassPort(CM_CHARGE_IP, charge_url, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)3);
            pp.authenticate((byte)7);
            isFirstConnect = false;
         }
         break;
      case 8:
         if (isFirstConnect) {
            MainCanvas.userID = this.getUserId();
            MainCanvas.userKey = this.getKey();
            charge_url = "/judgeserver/QueryChargeUpRecord?usr=&userId=" + MainCanvas.userID + "&game=fengshen&subgame=fengshen&from=kong&sdate=&edate=&sseq=0&rcount=" + 100 + "&qtype=7&qtime=" + timeType + "&qmonth=" + selMonth;
            pp = new PassPort(CM_CHARGE_IP, charge_url, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)3);
            pp.authenticate((byte)8);
            isFirstConnect = false;
         }
         break;
      case 9:
         if (isFirstConnect) {
            MainCanvas.userID = this.getUserId();
            MainCanvas.userKey = this.getKey();
            charge_url = "/judgeserver/QueryChargeUpRecord?usr=&userId=" + MainCanvas.userID + "&game=fengshen&subgame=fengshen&from=kong&sdate=&edate=&sseq=0&rcount=" + 100 + "&qtype=" + chooseType + "&qtime=" + timeType + "&qmonth=" + selMonth;
            pp = new PassPort(CM_CHARGE_IP, charge_url, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)3);
            pp.authenticate((byte)9);
            isFirstConnect = false;
         }
         break;
      case 10:
         if (isFirstConnect) {
            MainCanvas.userID = this.getUserId();
            MainCanvas.userKey = this.getKey();
            charge_url = "/judgeserver/BuyGameTool?usr=&userId=" + MainCanvas.userID + "&game=fengshen&subgame=fengshen&from=kong&consumeCode=120122006001";
            pp = new PassPort(CM_CHARGE_IP, charge_url, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)3);
            pp.authenticate((byte)10);
            isFirstConnect = false;
         }
         break;
      case 20:
         if (isFirstConnect) {
            PCMonthly.max_BodySend = 0;
            if (this.getContents() != null) {
               if (this.getContents().length % 256 != 0) {
                  PCMonthly.max_BodySend = this.getContents().length / 256 + 1;
               } else {
                  PCMonthly.max_BodySend = this.getContents().length / 256;
               }
            }

            isFirstConnect = false;
         }

         MainCanvas.ni.send(2686977);
      }

      if (kongFirstResult != -1) {
         this.processKongZhongValidateResult();
      }

   }

   private void processAfterSuccessValidate() {
      switch(this.validateType) {
      case 0:
         isEndRegisterValidate = true;
         break;
      case 1:
         this.processServerDiv();
         isEndLoginValidate = true;
         break;
      case 2:
         MainCanvas.setState((byte)11);
         isEndChangePswValidate = true;
         break;
      case 3:
         chargeSuccessful = true;
         isEndChargeValidate = true;
         break;
      case 4:
         m_bBingValidate = true;
      case 5:
      case 6:
      case 11:
      default:
         break;
      case 7:
         chargeSuccessful = true;
         isEndRemainValidate = true;
         break;
      case 8:
         isEndChargeHistoryValidate = true;
         break;
      case 9:
         isEndBuyHistoryValidate = true;
         break;
      case 10:
         MainCanvas.TopForm((byte)0, "Mua thành công！", "Xác nhận", "", -1, -120);
      }

   }

   private void processServerDiv() {
      serverDivArr = Util.split(serverListString, serverListString.length(), ';');
      if (serverDivArr[0].indexOf("[荐]") != -1) {
         this.processCommendList(serverDivArr[0]);
      } else {
         recommandServer = null;
      }

      for(int i = 0; i < serverDivArr.length; ++i) {
         this.processServerList(serverDivArr[i], i);
      }

   }

   private void processCommendList(String commendList) {
      recommandServer = Util.split(commendList, commendList.length(), ',');
   }

   private void processServerList(String serverList, int divID) {
      String[] serverListArr = Util.split(serverList, serverList.length(), ',');
      byte num = (byte)(serverListArr.length / 4);
      if (divID != 0 || recommandServer == null) {
         String[] serverRec = Util.loadStrRecord("last_time_login", 4);
         if ((serverRec != null || recommandServer != null) && MainCanvas.mc.isTestNum != 1) {
            if (serverRec != null && recommandServer != null) {
               serverListContent = new String[num + 1][4];
            } else {
               serverListContent = new String[num][4];
            }
         } else {
            serverListContent = new String[num - 1][4];
         }

         int startIndex = 0;
         if (serverRec != null && MainCanvas.mc.isTestNum != 1) {
            String serverName = serverRec[0];
            int index = serverName.indexOf(40);
            int index2 = serverName.indexOf(93);
            if (index != -1) {
               serverName = serverName.substring(index2 != -1 ? index2 + 1 : 0, index) + "(上次登录)";
            } else {
               serverName = serverName + "(Lần đăng nhập trước)";
            }

            serverRec[0] = serverName;
            serverListContent[0] = serverRec;
            if (recommandServer != null) {
               serverListContent[1] = recommandServer;
               startIndex = 2;
            } else {
               startIndex = 1;
            }
         } else if (recommandServer != null) {
            serverListContent[0] = recommandServer;
            startIndex = 1;
         }

         if (recommandServer != null && divID != 0) {
            serverDivName = serverListArr[0];
         }

         if (recommandServer == null && divID == 0) {
            serverDivName = serverListArr[0];
         }

         for(int i = 0; i < serverListContent.length - startIndex; ++i) {
            serverListContent[i + startIndex][0] = serverListArr[(i + 1) * 4];
            serverListContent[i + startIndex][1] = serverListArr[(i + 1) * 4 + 1];
            serverListContent[i + startIndex][2] = serverListArr[(i + 1) * 4 + 2];
            serverListContent[i + startIndex][3] = serverListArr[(i + 1) * 4 + 3];
         }
      }

   }

   private void processServerUpdateInfo() {
      if (servercontent != null && !servercontent.equals("")) {
         String[] serverUpdateInfo = Util.split(servercontent, servercontent.length(), '|', false);
         int num = serverUpdateInfo.length / 3;
         updateListInfo = new String[num][3];
         updateServer = new String[num];

         for(int i = 0; i < num; ++i) {
            updateListInfo[i][0] = serverUpdateInfo[i * 3];
            updateListInfo[i][1] = serverUpdateInfo[i * 3 + 1];
            updateListInfo[i][2] = serverUpdateInfo[i * 3 + 2];
            updateServer[i] = updateListInfo[i][0];
         }
      } else {
         updateListInfo = null;
      }

   }

   public static String getServerName(int listIndex) {
      return serverListContent[listIndex][0];
   }

   public static String getServerIP(int listIndex) {
      return serverListContent[listIndex][1];
   }

   public static String getServerPort(int listIndex) {
      return serverListContent[listIndex][2];
   }

   public static String getServerJumpIP(int listIndex) {
      return serverListContent[listIndex][3];
   }

   public static String getURL(byte mark) {
      String url = "";
      if (MainCanvas.isChinaMobileVer) {
         if (mark == 0) {
            url = "/judgeserver/judge?ver=1&cmd=r&usr=" + MainCanvas.name_password[0] + "&pwd=" + MainCanvas.name_password[1] + "&game=fengshen&subgame=" + "fengshen" + "&from=" + MainCanvas.jarFrom + "&userId=" + MainCanvas.userID + "&key=" + MainCanvas.userKey + "&cver=" + MainCanvas.VerString(GameLogin.m_nVersion) + "&jar=" + "GOH_7610.jar";
         } else if (mark == 1) {
            url = "/judgeserver/judge?ver=1&cmd=l&usr=" + MainCanvas.name_password[0] + "&pwd=" + MainCanvas.name_password[1] + "&game=fengshen&subgame=" + "fengshen" + "&from=" + MainCanvas.jarFrom + "&userId=" + MainCanvas.userID + "&key=" + MainCanvas.userKey + "&cver=" + MainCanvas.VerString(GameLogin.m_nVersion) + "&jar=" + "GOH_7610.jar";
         } else if (mark == 2) {
            url = "/judgeserver/judge?ver=1&usr=" + MainCanvas.m_sTemporaryStr[0] + "&pwd=" + MainCanvas.m_sTemporaryStr[1] + "&topd=" + MainCanvas.m_sTemporaryStr[2] + "&cmd=c";
         } else if (mark == 5) {
            url = "/judgeserver/judge?ver=1&cmd=k&game=fengshen&subgame=fengshen&from=" + MainCanvas.jarFrom + "&userId=" + MainCanvas.userID + "&key=" + MainCanvas.userKey + "&cver=" + MainCanvas.VerString(GameLogin.m_nVersion) + "&jar=" + "GOH_7610.jar";
         } else if (mark == 6) {
            url = KONG_URL + "cmd=s&game=fengshen";
         }
      } else if (mark == 0) {
         url = KONG_URL + "usr=" + MainCanvas.name_password[0] + "&pwd=" + MainCanvas.name_password[1] + "&cmd=r" + "&game=fengshen&subgame=" + "fengshen" + "&from=" + MainCanvas.jarFrom + "&cver=" + MainCanvas.VerString(GameLogin.m_nVersion) + "&jar=" + "GOH_7610.jar" + "&imei=" + imei + "&charged=" + 0 + "&istest=" + MainCanvas.mc.isTestNum + "&reuid=" + repassportid;
      } else if (mark == 1) {
         url = KONG_URL + "usr=" + MainCanvas.name_password[0] + "&pwd=" + MainCanvas.name_password[1] + "&cmd=l" + "&game=fengshen&subgame=" + "fengshen" + "&from=" + MainCanvas.jarFrom + "&cver=" + MainCanvas.VerString(GameLogin.m_nVersion) + "&jar=" + "GOH_7610.jar" + "&imei=" + imei + "&charged=" + 0 + "&istest=" + MainCanvas.mc.isTestNum;

         try {
            UIRadio r = (UIRadio)MainCanvas.curForm.getComponents().elementAt(10);
            if (r.getSelectIndex() == 1) {
               url = url + "&auth=ko";
            }
         } catch (Exception var3) {
         }
      } else if (mark == 2) {
         url = KONG_URL + "usr=" + MainCanvas.m_sTemporaryStr[0] + "&pwd=" + MainCanvas.m_sTemporaryStr[1] + "&topd=" + MainCanvas.m_sTemporaryStr[2] + "&cmd=c";
      } else if (mark == 3) {
         url = "/reportip/r.jsp?usr=" + MainCanvas.name_password[0] + "&game=fengshen&from=" + MainCanvas.jarFrom + "&cver=" + MainCanvas.VerString(GameLogin.m_nVersion) + "&jar=" + "GOH_7610.jar" + "&dest=";
      } else if (mark == 4) {
         url = KONG_URL + "usr=" + MainCanvas.name_password[0] + "&pwd=" + MainCanvas.name_password[1] + "&cmd=l" + "&game=fengshen&subgame=" + "fengshen" + "&from=" + MainCanvas.jarFrom + "&cver=" + MainCanvas.VerString(GameLogin.m_nVersion) + "&jar=" + "GOH_7610.jar" + "&imei=" + imei;
      } else if (mark == 5) {
         url = KONG_URL + "cmd=k" + "&game=fengshen&subgame=" + "fengshen" + "&from=" + MainCanvas.jarFrom + "&cver=" + MainCanvas.VerString(GameLogin.m_nVersion) + "&jar=" + "GOH_7610.jar" + "&imei=" + imei + "&reuid=" + repassportid;
      } else if (mark == 6) {
         url = KONG_URL + "cmd=s&game=fengshen";
      }

      return url;
   }

   private void setHeadInfo(HttpConnection hc) throws IOException {
      String platform = "";
      String gameName = "fengshen";

      try {
         platform = System.getProperty("microedition.platform");
         if (platform != null) {
            platform = platform.trim();
         } else {
            platform = "";
         }
      } catch (Exception var5) {
      }

      hc.setRequestProperty("mammoth_ua", platform);
      hc.setRequestProperty("from", MainCanvas.jarFrom);
      hc.setRequestProperty("game-service", gameName);
      if (this.validateType == 12) {
         userAgent = jadDownloadUrl.substring(jadDownloadUrl.indexOf("uaStr=") + "uaStr=".length(), jadDownloadUrl.length());
         hc.setRequestProperty("user-agent", userAgent);
      }

      if (this.validateType == 13) {
         hc.setRequestProperty("user-agent", userAgent);
         hc.setRequestProperty("content-length", "11");
         hc.setRequestProperty("content-type", "text/plain");
         hc.setRequestProperty("accept", "*/*");
      }

      if (this.validateType == 20) {
         hc.setRequestProperty("User-Agent", "NokiaN73");
         hc.setRequestProperty("accept-encoding", "deflate,gzip,x-gzip");
      }

   }

   public static String getSMSNumber(String sms) {
      return sms.substring(sms.indexOf(64) + 1, sms.length());
   }

   private void getResultContent(HttpConnection hconn, int length) throws IOException {
      if (hconn != null) {
         if (MainCanvas.isChinaMobileVer) {
            chargeDetil = hconn.getHeaderField("result");
            chargeCode = hconn.getHeaderField("resultstatus");
            if (this.validateType == 3 || this.validateType == 7) {
               chargeRemain = hconn.getHeaderField("value");
            }
         }

         String tmpBond;
         StringBuffer sb;
         if (VER == 1) {
            if (!MainCanvas.isChinaMobileVer) {
               for(int i = 0; i < valueDetail.length; ++i) {
                  valueDetail[i] = hconn.getHeaderField(valueSign[i]);
               }
            }

            bindDetail = hconn.getHeaderField("PASSPORT_BOND");
            resultInfo = hconn.getHeaderField("resultmsg");
            resultInfo = this.unicode2String(resultInfo);
            GameLogin.downloadAddress = hconn.getHeaderField("download");
            if (GameLogin.downloadAddress == null) {
               GameLogin.downloadAddress = "";
            }

            suggestion = hconn.getHeaderField("suggestion");
            if (suggestion == null) {
               suggestion = hconn.getHeaderField("PASS_NAME");
            }

            passportid = hconn.getHeaderField("PASSPORT_ID");
            if (passportid == null) {
               passportid = "";
            }

            servercontent = hconn.getHeaderField("SERVERCONTENT");
            if (servercontent != null) {
               servercontent = this.unicode2String(servercontent);
            }

            if (this.validateType == 11) {
               localPlace = hconn.getHeaderField("region");
               if (localPlace == null) {
                  localPlace = "";
               } else {
                  localPlace = this.unicode2String(localPlace);
               }
            }

            String tmpSeek;
            if (this.validateType == 20) {
               sb = new StringBuffer();
               this.headMark = 0;

               while(true) {
                  tmpBond = hconn.getHeaderFieldKey(this.headMark);
                  tmpSeek = null;
                  if (tmpBond != null) {
                     tmpSeek = hconn.getHeaderField(tmpBond);
                     sb.append(tmpBond + "|" + tmpSeek + "~");
                  }

                  if (hconn.getHeaderFieldKey(this.headMark) == null) {
                     PCMonthly.getInstance().connectLoghead = sb.toString();
                     sb = null;
                     this.headMark = 0;
                     break;
                  }

                  ++this.headMark;
               }
            }

            if (this.validateType == 15 || this.validateType == 1 || this.validateType == 0) {
               String tmpCoin = hconn.getHeaderField("SMS_COIN");
               tmpBond = hconn.getHeaderField("SMS_BOND");
               tmpSeek = hconn.getHeaderField("SMS_SEEK");
               String tmpDel = hconn.getHeaderField("SMS_DEL");
               if (tmpCoin != null) {
                  sms_coin = tmpCoin;
               }

               if (tmpBond != null) {
                  sms_bond = tmpBond;
               }

               if (tmpSeek != null) {
                  sms_seek = tmpSeek;
               }

               if (tmpDel != null) {
                  sms_del = tmpDel;
               }
            }

            this.processServerUpdateInfo();
         }

         DataInputStream is = hconn.openDataInputStream();
         if (length > 0) {
            this.content = new byte[length];

            for(int i = 0; i < length; ++i) {
               this.content[i] = (byte)is.read();
            }
         } else {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            boolean var12 = false;

            int ch;
            while((ch = is.read()) != -1) {
               baos.write(ch);
            }

            this.content = null;
            this.content = baos.toByteArray();
            baos.close();
            tmpBond = null;
         }

         is.close();
         sb = null;
         hconn.close();
         hconn = null;
      }

   }

   private String unicode2String(String s) {
      if (s == null) {
         return null;
      } else {
         StringBuffer result = new StringBuffer();

         for(int i = 0; i < s.length(); ++i) {
            int ch;
            if ((ch = s.charAt(i)) == 37) {
               int temp = i++;
               if (s.length() >= i + 4) {
                  ch = Integer.parseInt(s.substring(i, i + 4), 16);
                  i += 3;
               } else {
                  i = temp;
               }
            }

            result.append((char)ch);
         }

         return result.toString();
      }
   }

   public byte getConnectState() {
      return this.connectState;
   }

   public void setConnectState(byte connectState) {
      this.connectState = connectState;
   }

   public String getUserId() {
      return this.userId == null ? "" : this.userId;
   }

   public String getKey() {
      return this.key == null ? "" : this.key;
   }

   public byte[] getContents() {
      return this.content;
   }

   public static void checkChinaMobileConnect() {
      if (!MainCanvas.isCMWAP) {
         MainCanvas.TopForm((byte)0, "Liên kết hiện tại lỗi, hãy dùng cách liên kết cmwap!", Cons.C_STR[2], "", -7, -120);
      } else {
         if (MainCanvas.userID != null && !"".equals(MainCanvas.userID.trim())) {
            return;
         }

         onlyGetUserID();
      }

   }

   public static boolean getBindDetail() {
      return bindDetail == null ? false : bindDetail.toLowerCase().equals("true");
   }

   public static void onlyGetUserID() {
      Thread t = new Thread(new Runnable() {
         public void run() {
            if ((MainCanvas.userID == null || "".equals(MainCanvas.userID.trim())) && MainCanvas.isCMWAP) {
               PassPort pp = new PassPort(PassPort.CM_PASSPORT_IP, PassPort.CM_PASSPORT_GET_URL, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)1);
               PassPort.isFirstConnect = true;
               pp.authenticate((byte)6);
            }

         }
      });
      t.start();
   }

   public static void accessJadDownloadUrl() {
      if (MainCanvas.isCMWAP) {
         Thread t = new Thread(new Runnable() {
            public void run() {
               while(MainCanvas.userID == null || "".equals(MainCanvas.userID.trim())) {
                  try {
                     Thread.sleep(150L);
                  } catch (Exception var2) {
                     var2.printStackTrace();
                  }
               }

               if (MainCanvas.gi.getGIP() != null) {
                  PassPort.giStartTime = System.currentTimeMillis();
                  PassPort.giPerMinute();
               }

               PassPort pp = new PassPort(PassPort.gplusDownloadIp, PassPort.jadDownloadUrl, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)1);
               PassPort.isFirstConnect = true;
               pp.authenticate((byte)12);
            }
         });
         t.start();
      }
   }

   private static void giPerMinute() {
      if (t == null) {
         t = new Thread(new Runnable() {
            public void run() {
               MainCanvas.gi.send(1);

               while(true) {
                  while(true) {
                     try {
                        long curTime = System.currentTimeMillis();
                        if (curTime - PassPort.giStartTime >= 60000L) {
                           MainCanvas.gi.send(0);
                           PassPort.giStartTime = System.currentTimeMillis();
                        }

                        Thread.sleep(2000L);
                     } catch (Exception var3) {
                     }
                  }
               }
            }
         });
         t.start();
      }

   }

   public static void accessJadNotifyUrl() {
      if (MainCanvas.isCMWAP) {
         Thread t = new Thread(new Runnable() {
            public void run() {
               PassPort pp = new PassPort(PassPort.gplusDownloadIp, PassPort.jadNotifyUrl, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)1);
               PassPort.isFirstConnect = true;
               pp.authenticate((byte)13);
            }
         });
         t.start();
      }
   }

   public static void accessJarDownloadUrl() {
      if (MainCanvas.isCMWAP) {
         Thread t = new Thread(new Runnable() {
            public void run() {
               PassPort pp = new PassPort(PassPort.gplusDownloadIp, PassPort.jarDownloadUrl, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)1);
               PassPort.isFirstConnect = true;
               pp.authenticate((byte)14);
            }
         });
         t.start();
      }
   }

   public static boolean checkUpdateInfo(byte listIndex) {
      if (updateListInfo == null) {
         return false;
      } else {
         String serverlist = "";
         if (!isLocalServerList) {
            serverlist = getServerIP(listIndex) + ":" + getServerPort(listIndex) + ":" + getServerJumpIP(listIndex);
         } else {
            serverlist = MainCanvas.serverIp[listIndex] + ":" + MainCanvas.m_bjumpIP1;
         }

         for(int t = 0; t < updateServer.length; ++t) {
            if (serverlist.equals(updateServer[t])) {
               MainCanvas.TopForm((byte)0, updateListInfo[t][1] + "\n\n" + updateListInfo[t][2], Cons.C_STR[2], "", -1, -120);
               return true;
            }
         }

         return false;
      }
   }

   public static void loseConnect() {
      if (loseMark != 0) {
         if (!MainCanvas.isCMWAP) {
            loseMark = 0;
         } else {
            if (!MainCanvas.isChinaMobileVer) {
               String s = "";
               switch(loseMark) {
               case 1:
                  s = "passport";
                  break;
               case 2:
                  s = "" + MainCanvas.m_bjumpIP1;
               }

               String url = getURL((byte)3) + s;
               PassPort pp = new PassPort("61.135.147.34", url, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)0);
               pp.authenticate((byte)5);
               loseMark = 0;
            }

         }
      }
   }

   public static boolean haveUserIDAndKey() {
      return !"".equals(MainCanvas.userID) && !"".equals(MainCanvas.userKey);
   }

   public static void initSysDate() {
      TimeZone tz = TimeZone.getDefault();
      Calendar ca = Calendar.getInstance(tz);
      sysYear = ca.get(1);
      sysMonth = ca.get(2) + 1;
   }

   public void setHistroy() {
      if (this.content != null) {
         try {
            history = new String(this.content, "gb2312");
         } catch (Exception var2) {
            var2.printStackTrace();
         }

      }
   }

   public static boolean checkMonth() {
      int syear = selMonth / 100;
      int smon = selMonth % 100;
      if (syear >= 2009 && syear <= sysYear) {
         if (syear == sysYear) {
            if (smon > 0 && smon <= sysMonth) {
               return true;
            } else {
               MainCanvas.TopForm((byte)0, "Hãy nhập tháng chính xác, không được quá số tháng hiện tại", Cons.C_STR[2], "", -7, -120);
               return false;
            }
         } else if (smon > 0 && smon <= 12) {
            return true;
         } else {
            MainCanvas.TopForm((byte)0, "Hãy nhập tháng chính xác", Cons.C_STR[2], "", -7, -120);
            return false;
         }
      } else {
         MainCanvas.TopForm((byte)0, "Nhập năm lỗi, nhập 2009 năm hiện tại.", Cons.C_STR[2], "", -7, -120);
         return false;
      }
   }

   public static void accessMonthlyUrl(final String ip, final String url) {
      if (MainCanvas.isCMWAP) {
         Thread t = new Thread(new Runnable() {
            public void run() {
               PassPort.Monthly = new PassPort(ip, url, (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)0);
               PassPort.isFirstConnect = true;
               PassPort.Monthly.authenticate((byte)20);
            }
         });
         t.start();
      }
   }

   public static void connectToGetLocalPlace() {
      if (MainCanvas.isCMWAP) {
         PassPort pp = new PassPort(KONG_PASSPORT_IP, getURL((byte)4), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
         pp.authenticate((byte)11);
      }

   }

   public static void connectToGetSMSFormat() {
      if (sms_coin == null) {
         PassPort pp = new PassPort(KONG_PASSPORT_IP, getURL((byte)6), (byte)(MainCanvas.isCMWAP ? 0 : 1), (byte)2);
         pp.authenticate((byte)15);
      }

   }

   void setPost(HttpConnection hc) throws IOException {
      String tmp = "900 Success";
      OutputStream os = null;
      hc.setRequestMethod("POST");
      os = hc.openOutputStream();
      byte[] bytes = tmp.getBytes();

      for(int i = 0; i < bytes.length; ++i) {
         os.write(bytes[i]);
      }

      os.close();
      os = null;
   }

   void setBodyData(String s) {
      String[] allinfo = (String[])null;
      allinfo = Util.splitToken(s, '\n');
      this.bodyValue = new String[allinfo.length][2];

      for(int i = 0; i < allinfo.length; ++i) {
         if (allinfo[i].indexOf(58) != -1) {
            this.bodyValue[i][0] = allinfo[i].substring(0, allinfo[i].indexOf(58)).trim();
            this.bodyValue[i][1] = allinfo[i].substring(allinfo[i].indexOf(58, 0) + 1).trim();
         }
      }

   }

   String getBodyValue(String mark) {
      try {
         for(int i = 0; i < this.bodyValue.length; ++i) {
            if (mark.equals(this.bodyValue[i][0])) {
               return this.bodyValue[i][1];
            }
         }

         return "";
      } catch (Exception var3) {
         return "";
      }
   }

   void getBodyInfo() {
      try {
         kongFirstResult = Integer.parseInt(this.getBodyValue("result"));
      } catch (Exception var5) {
         kongFirstResult = -1;
         this.connectState = 3;
      }

      resultInfo = this.getBodyValue("resultmsg");
      resultInfo = this.unicode2String(resultInfo);
      this.processServerUpdateInfo();
      MainCanvas.kongMD5 = this.getBodyValue("md5");

      for(int i = 0; i < valueDetail.length; ++i) {
         valueDetail[i] = this.getBodyValue(valueSign[i]);
      }

      servercontent = this.getBodyValue("SERVERCONTENT");
      bindDetail = this.getBodyValue("PASSPORT_BOND");
      String vipID = this.getBodyValue("MOBILE_VIP");
      GameLogin.downloadAddress = this.getBodyValue("download");
      suggestion = this.getBodyValue("suggestion");
      if ("".equals(suggestion)) {
         suggestion = this.getBodyValue("PASS_NAME");
      }

      this.getBodyValue("charged");
      passportid = this.getBodyValue("PASSPORT_ID");
      localPlace = this.getBodyValue("REGION");
      localPlace = this.getBodyValue("REGION");
      localPlace = this.unicode2String(localPlace);
      sms_coin = this.getBodyValue("SMS_COIN");
      sms_bond = this.getBodyValue("SMS_BOND");
      sms_del = this.getBodyValue("SMS_DEL");
      sms_seek = this.getBodyValue("SMS_SEEK");

      try {
         String gPort = this.getBodyValue("GHEART");
         if (!"".equals(gPort)) {
            String[] gPortStr = Util.splitToken(gPort, ':');
            MainCanvas.gi.setGIP(gPortStr[0]);
            MainCanvas.gi.setGPort(gPortStr[1]);
            MainCanvas.gi.setGJumpId(gPortStr[2]);
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      if (MainCanvas.loginType != null && MainCanvas.loginType[0] == 1) {
         virUser = this.getBodyValue("VIR_USER");
      }

      price = this.getBodyValue("SMSPRICE");
      serverListString = this.getBodyValue("body");
      if (serverListString != null) {
         serverListString = this.unicode2String(serverListString);
      }

   }
}

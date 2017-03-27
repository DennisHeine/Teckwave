import java.net.*;
import java.io.*;
import com.jcraft.jsch.*;

class ClientConThread extends Thread {
 String mIP = "";
 String mIPRouter = "";
 String mA = "";
 String password = "";

 ClientConThread(String IP, String IPRouter, String A, String Pass) {
  mIP = IP;
  mIPRouter = IPRouter;
  mA = A;
  password = Pass;
 }

 public void run() {
  String Query = null;
  byte[] Senden = null;
  try {

   System.out.println("Sende query: " + mIP + mIPRouter + mA);
   String host = "root@" + mIPRouter;

   String user = host.substring(0, host.indexOf('@'));
   host = host.substring(host.indexOf('@') + 1);

   JSch jsch = new JSch();
   Session session = jsch.getSession(user, host, 22);

   java.util.Properties config = new java.util.Properties();
   config.put("StrictHostKeyChecking", "no");
   session.setConfig(config);
   session.setPassword(password);
   session.connect();

   String command = "";
   if (mA == "D")
    command = "iptables -t nat -D PREROUTING -s " + mIP + " -j ACCEPT";
   else
    command = "iptables -t nat -I PREROUTING 1 -s " + mIP + " -j ACCEPT";

   Channel channel = session.openChannel("exec");
   ((ChannelExec) channel).setCommand(command);

   channel = session.openChannel("exec");
   channel.setInputStream(null);
   channel.setOutputStream(System.out);

   ((ChannelExec) channel).setCommand(command);

   channel.connect();

   InputStream in = channel.getInputStream();

   byte[] tmp = new byte[1024];
   while (true) {
    while ( in .available() > 0) {
     int i = in .read(tmp, 0, 1024);
     if (i < 0) {
      break;
     }
     System.out.print(new String(tmp, 0, i));
    }
    if (channel.isClosed()) {
     break;
    }
    try {
     Thread.sleep(1000);
    } catch (Exception ee) {}
   }
   channel.disconnect();
  } catch (Exception e) {
   System.out.println(e.toString());
  }
 }

 public static class MyUserInfo implements UserInfo, UIKeyboardInteractive {
  public String getPassword() {
   return "Router";
  }

  public void showMessage(String message) {

  }
  public boolean promptYesNo(String str) {
   return true;
  }

  public boolean promptPassword(String message) {
   return true;
  }

  public boolean promptPassphrase(String message) {
   return true;
  }
  public String getPassphrase() {
   return null;
  }
  public String[] promptKeyboardInteractive(String destination,
   String name,
   String instruction,
   String[] prompt,
   boolean[] echo) {

   return null;
  }

 }
}
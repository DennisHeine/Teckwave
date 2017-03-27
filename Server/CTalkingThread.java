import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.*;

class CTalkingThread extends java.lang.Thread {
 SSLSocket clientSocket = null;
 public void run() {
  String inputLine = null;
  String outputLine = null;
  BufferedReader in = null;
  PrintWriter out = null;
  try {
   out = new PrintWriter(clientSocket.getOutputStream(), true); in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
   inputLine = in .readLine();
   System.out.println(inputLine);
  } catch (Exception e) {}

  String Username = new String("");
  String Token = java.lang.Character.toString(inputLine.charAt(0)) + java.lang.Character.toString(inputLine.charAt(1));
  if (Token.compareTo("HE") == 0) {
   CHello mHello = new CHello();
   mHello.DoHello(clientSocket, inputLine);
  }
  if (Token.compareTo("ST") == 0) {
   System.out.println("Statusanforderung");
   CStatus Status = new CStatus();
   out.println(Status.DoStatus(clientSocket, inputLine));
   System.out.println("Status gesendet");
  }
  if (Token.compareTo("DC") == 0) {
   CLogout Logout = new CLogout(clientSocket, inputLine);
  }
  if (Token.compareTo("RK") == 0) {
   CRegistrieren Registrieren = new CRegistrieren();
   Registrieren.DoRegistrieren(clientSocket, inputLine);
  }
  if (Token.compareTo("SC") == 0) {
   System.out.println("TalkingThread");
   CSupportKunde sc = new CSupportKunde(clientSocket, inputLine);
  }
  if (Token.compareTo("SS") == 0) {
   CSupportSupporter ss = new CSupportSupporter(clientSocket, inputLine);
  }
  try {
   out.close(); in .close();
   clientSocket.close();
  } catch (Exception e) {}
 }

 CTalkingThread(SSLSocket mClientSocket) {
  super();
  clientSocket = mClientSocket;
 }
}
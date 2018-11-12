import java.util.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.*;



import java.io.IOException;

import java.net.*;
import Utils.*;
import java.nio.*;



/**UDP 客户端

 * Created by Saint-Theana on 2018/11/13.

 */

public class Main {
	
    public static void main(String args[]) throws Exception
	{
		QQUser user = new QQUser("QQ号，用long类型","密码，你懂的");
		
		LoginManager manager = new LoginManager(user);
		manager.Login();
		
	}

	
	
}

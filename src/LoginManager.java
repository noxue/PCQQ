
import Utils.*;public class LoginManager
{
	private byte[] data = null;
	private Udpsocket socket = null;
	private QQUser _user;
	public LoginManager(QQUser user){
		this._user = user;
		user.TXProtocol.DwServerIP= "61.151.180.166";
		socket = new Udpsocket(user);
		
		
		
	}
	
	public void Login(){
		
		data = SendPackegeFatcory.get0825(_user);
		socket.sendMessage(data);
		byte[] result = socket.receiveMessage();
		//System.out.println(Util.byte2HexString(result));
		ParseRecivePackage parsereceive = new ParseRecivePackage(result,_user.QQPacket0825Key,_user);
		parsereceive.decrypt_body();
		parsereceive.parse_tlv();
		while(parsereceive.Header[0] == -2){
			System.out.println("重定向到:"+_user.TXProtocol.DwRedirectIP);
			_user.TXProtocol.WRedirectCount[1] += 1;
			_user.IsLoginRedirect = true;
			socket = new Udpsocket(_user);
			
			data = SendPackegeFatcory.get0825(_user);
			
			socket.sendMessage(data);
			
			result = socket.receiveMessage();
			parsereceive = new ParseRecivePackage(result,_user.QQPacket0825Key,_user);
			parsereceive.decrypt_body();
			parsereceive.parse_tlv();
		}
		
		System.out.println("服务器连接成功,开始登陆");
		data = SendPackegeFatcory.get0836(_user,false);
		socket.sendMessage(data);
		result = socket.receiveMessage();
		parsereceive = new ParseRecivePackage(result,_user.TXProtocol.BufDhShareKey,_user);
		parsereceive.parse0836();
		if (parsereceive.Header[0] == 52){
			System.out.println("密码错误");
			return;
		}
		while (parsereceive.Header[0] != 0){
			System.out.println("二次登陆");
			data = SendPackegeFatcory.get0836(_user,true);
			socket.sendMessage(data);
			result = socket.receiveMessage();
			parsereceive = new ParseRecivePackage(result,_user.TXProtocol.BufDhShareKey,_user);
			parsereceive.parse0836();
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{}
			
		}
		if (parsereceive.Header[0] == 0){
			System.out.println("成功获取用户信息: Nick: "+_user.NickName+" Age: "+_user.Age+" Sex: "+_user.Gender);
		}
		
		
	}
}

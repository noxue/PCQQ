
import java.util.*;
import java.net.*;
import Utils.*;
import org.json.*;
import org.apache.http.cookie.*;
import Core.Packets.HttpEntity.*;
import com.google.gson.*;
import java.io.*;


public class QQUser
{
	private String ua =
	"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36";

	public QQUser(long qqNum,String pwd)
	{
		QQ = qqNum;
		SetPassword(pwd);
		Initialize();
	}

	/// <summary>
	///     经过重定向登录
	/// </summary>
	/// <value></value>
	public boolean IsLoginRedirect;

	/// <summary>
	///     登录包密钥
	/// </summary>
	public byte[] QQPacket0825Key  = Util.RandomKey();

	/// <summary>
	///     重定向密钥
	/// </summary>
	public byte[] QQPacketRedirectionkey  = Util.RandomKey();

	/// <summary>
	///     验证码报文秘钥
	/// </summary>
	public byte[] QQPacket00BaKey = Util.RandomKey();

	public byte[] QQPacketTgtgtKey = Util.RandomKey();

	/// <summary>
	///     00BA占位段(暂时未解析出具体含义)
	/// </summary>
	public byte[] QQPacket00BaFixKey  = Util.str_to_byte("6920D11474F5B393E4D502B3711ACD2A");

	/// <summary>
	///     0836密钥1
	/// </summary>
	public byte[] QQPacket0836Key1 = Util.RandomKey();

	public byte[] QQPacket00BaVerifyCode;
	public byte QQPacket00BaSequence = 0x01;

	/// <summary>
	///     加 好友/群 所需Token
	/// </summary>
	public byte[] AddFriend0018Value;

	/// <summary>
	///     加 好友/群 所需Token
	/// </summary>
	public byte[] AddFriend0020Value ;

	/// <summary>
	///     密码一次MD5
	/// </summary>
	public byte[] MD51 ;
	
	public byte[] MD52 ;

	
	/// <summary>
	///     QQ号
	/// </summary>
	public long QQ ;

	/// <summary>
	///     当前登陆状态，为true表示已经登陆
	/// </summary>
	public boolean IsLoggedIn ;

	/// <summary>
	///     登陆模式，隐身还是非隐身
	/// </summary>
	public LoginMode LoginMode ;

	/// <summary>
	///     设置登陆服务器的方式是UDP还是TCP 默认UDP
	/// </summary>
	public boolean IsUdp  = true;

	/// <summary>
	///     昵称
	/// </summary>
	public String NickName ;

	/// <summary>
	///     年龄
	/// </summary>
	public byte Age ;

	/// <summary>
	///     性别
	/// </summary>
	public byte Gender;

	public String QQSkey;
	public String QQGtk ;
	public String Bkn;

	public String QunPSkey;
	public String QunGtk;

	public JSONObject QQCookies;
	public JSONObject QunCookies;

	/// <summary>
	///     已接收数据包序号集合
	/// </summary>
	public List<String> ReceiveSequences = new ArrayList<String>();

	public String Ukey;

	private void Initialize()
	{
		IsLoggedIn = false;
		LoginMode = LoginMode.Normal;
		IsUdp = true;
	}

	/// <summary>
	///     日志记录
	/// </summary>
	/// <param name="str"></param>
	public void MessageLog(String str)
	{
		System.out.println(new Date().getDate()+str);
	}

	public boolean GetCookies()
	{
		try
		{
			HttpWebClient httpWebClient = new HttpWebClient();
			{
				//string address = string.Format("http://ptlogin2.qq.com/jump?ptlang=2052&clientuin={0}&clientkey={1}&u1=http%3A%2F%2Fqzone.qq.com&ADUIN={0}&ADSESSION={2}&ADTAG=CLIENT.QQ.5365_.0&ADPUBNO=26405",
				//    QQ, Util.ToHex(TXProtocol.ClientKey, "", "{0}"), Util.GetTimeMillis(DateTime.Now));
				String address ="https://ssl.ptlogin2.qq.com/jump?pt_clientver=5593&pt_src=1&keyindex=9&ptlang=2052&clientuin="+QQ+"&clientkey="+Util.ToHex(TXProtocol.BufServiceTicketHttp, "", "{0}")+"&u1=https:%2F%2Fuser.qzone.qq.com%2F417085811%3FADUIN=417085811%26ADSESSION="+String.valueOf((new Date().getTime()))+"%26ADTAG=CLIENT.QQ.5593_MyTip.0%26ADPUBNO=26841&source=namecardhoverstar";
				httpWebClient.Headers.put("User-Agent",ua);
				String text = httpWebClient.DownloadData(address);
				QQCookies = httpWebClient.Cookies;
				if (QQCookies.getString("skey") != null)
				{
					String value = QQCookies.getString("skey");
					if (!value.equals(""))
					{
						QQSkey = value;
						Bkn = Util.GetBkn(value);
						QQGtk = Util.GET_GTK(value);
						return true;
					}
				}
			}
		}
		catch (Exception ex)
		{
			System.out.println("获取skey失败:" + ex.getMessage());
		}

		return false;
	}

	public boolean GetQunCookies()
	{
		try
		{
			HttpWebClient httpWebClient = new HttpWebClient();

			String address =
				"https://ssl.ptlogin2.qq.com/jump?pt_clientver=5509&pt_src=1&keyindex=9&clientuin={0}&clientkey={1}&u1=http%3A%2F%2Fqun.qq.com%2Fmember.html%23gid%3D168209441"+String.valueOf(QQ)+ Util.ToHex(TXProtocol.BufServiceTicketHttp /*QunKey*/, "", "{0}")+String.valueOf((new Date().getTime()));
			httpWebClient.Headers.put("User-Agent",ua);
			String result = httpWebClient.DownloadData(address);
			QunCookies = httpWebClient.Cookies;
			if (QunCookies.getString("skey") != null && !QunCookies.getString("skey").isEmpty())
			{
				QQSkey = QunCookies.getString("skey");
				Bkn = Util.GetBkn(QunCookies.getString("skey"));
			}

			String value2 = QunCookies.getString("p_skey");
			if (!value2.isEmpty())
			{
				QunPSkey = QunCookies.getString("p_skey");
				QunGtk = Util.GET_GTK(value2);
			}

			return true;

		}
		catch (Exception ex)
		{
			System.out.println("获取skey失败:" + ex.getMessage());
		}

		return false;
	}

	public GroupMembers Search_Group_Members(long externalId)
	{
		try
		{
			HttpWebClient httpWebClient = new HttpWebClient();

			String address = "https://qun.qq.com/cgi-bin/qun_mgr/search_group_members";
			String s = "gc="+externalId+"&st=0&end=10000&sort=0&bkn="+Bkn;
			httpWebClient.Headers.put("Accept","application/json, text/javascript, */*; q=0.01");
			httpWebClient.Headers.put("Referer","http://qun.qq.com/member.html");
			httpWebClient.Headers.put("X-Requested-With","XMLHttpRequest");
			httpWebClient.Headers.put("Cache-Control", "no-cache");
			httpWebClient.Headers.put("User-Agent",ua);
			httpWebClient.Cookies = QunCookies;
			String text = httpWebClient.UploadData(address, s);

			/*
			 if (text.matches("\"[0-9]+\":\"[^\"]+\""))
			 {
			 for (String match : r.Matches(text))
			 {
			 var str = ((Capture) match).Value.Split(':');
			 var r2 = new Regex("\"[0-9]+\"");
			 var level = r2.Matches(str[0])[0].Value;
			 var r3 = new Regex("\"[^\"]+\"");
			 var name = r3.Matches(str[1])[0].Value;
			 var dataItem = "{\"level\":" + level + ",\"name\":" + name + "}";

			 text = text.replace(((Capture) match).Value, dataItem);
			 }

			 text = text.replace("\"levelname\":{", "\"levelname\":[")
			 .replace("},\"max_count\"", "],\"max_count\"");
			 }

			 //untranslated
			 */

			MessageLog("获取群"+externalId+"成员列表成功:"+(text.length() > 200 ? text.substring(0, 200) : text));
			Gson gson = new Gson();
			return gson.fromJson(text,GroupMembers.class);

		}
		catch (Exception ex)
		{
			MessageLog("获取群"+externalId+"成员列表失败:"+ex.getMessage());
		}

		return null;
	}

	public GroupList Get_Group_List()
	{
		try
		{
			HttpWebClient httpWebClient = new HttpWebClient();
			{
				String address = "https://qun.qq.com/cgi-bin/qun_mgr/get_group_list";
				String s = "bkn="+Bkn;
				httpWebClient.Headers.put("Accept","application/json, text/javascript, */*; q=0.01");
				httpWebClient.Headers.put("Referer","http://qun.qq.com/member.html");
				httpWebClient.Headers.put("X-Requested-With","XMLHttpRequest");
				httpWebClient.Headers.put("Cache-Control","no-cache");
				httpWebClient.Headers.put("User-Agent",ua);
				httpWebClient.Cookies = QunCookies;
				String text = httpWebClient.UploadData(address,(s));

				MessageLog("获取群列表成功:" + text);
				Gson gson = new Gson();
				GroupList groups = gson.fromJson(text,GroupList.class);

				if (groups.Create != null)
				{
					for (GroupItem item : groups.Create)
					{
						item.Members = Search_Group_Members((long) item.Gc);
					}
				}

				if (groups.Join != null)
				{
					for (GroupItem item : groups.Join)
					{
						item.Members = Search_Group_Members((long) item.Gc);
					}
				}

				if (groups.Manage != null)
				{
					for (GroupItem item : groups.Manage)
					{
						item.Members = Search_Group_Members((long) item.Gc);
					}
				}

				return groups;
			}
		}
		catch (Exception ex)
		{
			System.out.println("获取群列表失败:" + ex.getMessage());
		}

		return null;
	}

	public FriendList Get_Friend_List()
	{
		try
		{
			HttpWebClient httpWebClient = new HttpWebClient();
			{
				String address = "https://qun.qq.com/cgi-bin/qun_mgr/get_friend_list";
				String s = "bkn="+Bkn;
				httpWebClient.Headers.put("Accept","application/json, text/javascript, */*; q=0.01");
				httpWebClient.Headers.put("Referer","http://qun.qq.com/member.html");
				httpWebClient.Headers.put("X-Requested-With","XMLHttpRequest");
				httpWebClient.Headers.put("User-Agent",ua);
				httpWebClient.Headers.put("Cache-Control","no-cache");
				httpWebClient.Cookies = QunCookies;
				String text = httpWebClient.UploadData(address,s);

				text = text.replaceAll("\"[0-9]+\":","");

			    text = text.replace("\"result\":{{", "\"result\":[{").replace("\"}}}", "\"}]}");


				MessageLog("获取好友列表成功:" + text);

				Gson gson = new Gson();
				return gson.fromJson(text,FriendList.class);
			}
		}
		catch (Exception ex)
		{
			System.out.println("获取好友列表失败:" + ex.getMessage());
		}

		return null;
	}



	/// <summary>
	///     设置用户的密码，不会保存明文形式的密码，立刻用Double MD5算法加密
	/// </summary>
	/// <param name="pwd">明文形式的密码</param>
	public void SetPassword(String pwd)
	{
		
		MD51 = Util.MD5(Util.GetBytes(pwd));
		MD52 = Util.MD5(Util.byteMerger(MD51,Util.ToByte(this.QQ)));
	}

	/// <summary>
	///     密码加密码一次MD5拼接后MD5加密
	/// </summary>
	/// <returns></returns>
	


	public TXProtocol TXProtocol  = new TXProtocol();

	/// <summary>
	///     好友列表
	/// </summary>
	public FriendList Friends ;

	//群列表
	public GroupList Groups ;

	/// <summary>
	///     好友发送消息合集
	/// </summary>
	
}


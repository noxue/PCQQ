
import java.util.*;
import Utils.*;

public class TXProtocol
{
	public byte[] BufTgtgtKey  = Util.RandomKey();
	public byte[] BufTgtgt ;

	public byte[] BufTgt ;

	//public byte[] bufComputerIDEx  = Util.RandomKey();
	public byte[] BufComputerId  =
	{ 0x43, 0x04, 0x21, 0x7D, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

	public byte[] BufComputerId_crc32_reversed  = Util.str_to_byte("CCC2E96A");
	//public byte[] bufComputerID  = Util.RandomKey();
	public byte[] BufComputerIdEx  = Util.str_to_byte("7798000BAB5D4F3D3050652C4A2AF865");
	
	public byte[] BufComputerIdEx_crc32_reversed  = Util.str_to_byte("3CDE845F");
	
	public byte[] BufDeviceId  =Util.str_to_byte("0fabbe2104a72af1e19da1956a363df07b22ff2ec2cac92ba8d6da459d31a960");


	public byte[] BufSigPic;
	public byte[] PngToken ;
	public byte[] PngKey ;
	public byte[] BufTgtGtKey ;
	public byte[] Buf16BytesGtKeySt ;
	public byte[] BufServiceTicket ;
	public byte[] Buf16BytesGtKeyStHttp ;
	public byte[] BufServiceTicketHttp ;
	public byte[] BufGtKeyTgtPwd ;
	public byte[] BufSessionKey ;
	public byte[] BufSigSession ;
	public byte[] BufPwdForConn ;
	public byte[] SessionKey ;
	public byte[] ClientKey ;
	public byte[] BufSigClientAddr ;

	public byte[] BufDhPublicKey  = Util.str_to_byte("027828167C9EF3B75A7B5AEFA23010EC0C4687707631A788EA");

	public byte[] BufDhShareKey  = Util.str_to_byte("60423B51C3B1F60F67E89C00F0A7BDA3");

	public byte[] BufMachineInfoGuid ;

	//public byte[] bufMacGuid  = Util.RandomKey();
	public byte[] BufMacGuid  = Util.str_to_byte("214B1A0409ED1970987551BB2D3A7E0A");
	/// <summary>
	///     记住密码
	/// </summary>
	public byte[] BRememberPwdLogin  = {0x00};

	public static byte[] CPingType  ={0x01};

	/// <summary>
	///     重定向IP记录
	/// </summary>
	public List<byte[]> RedirectIP  = new ArrayList<byte[]>();

	/// <summary>
	///     计算机名
	/// </summary>
	public String BufComputerName  = "超级战舰";

	/// <summary>
	///     SSO主版本号
	/// </summary>
	public byte[] CMainVer = {0x37};

	/// <summary>
	///     SSO次版本号
	/// </summary>
	public byte[] CSubVer = {0x09};

	/// <summary>
	///     Array
	/// </summary>
	public byte[] XxooA = { 0x03, 0x00, 0x00 };

	public byte[] XxooD = { 0x30, 0x00, 0x00, 0x00 };
	public byte[] XxooB = {0x01};

	/// <summary>
	///     客户端类型
	/// </summary>
	public byte[] DwClientType = { 0x00, 0x01, 0x01, 0x01 };

	/// <summary>
	///     发行版本号
	/// </summary>
	public byte[] DwPubNo = { 0x00, 0x00, 0x68, 0x1C };


	public short CQdProtocolVer = 0x0063;
	public byte[] CQdProtocolVer_Byte = {0x00,0x63};
	public long DwQdVerion = 0x02040404;
	public byte[] DwQdVerion_Byte = {0x04,0x04,0x04,0x02};
	public short WQdCsCmdNo = 0x0004;
	public byte[] WQdCsCmdNo_Byte = {0x00,0x04};
	
	public byte[] CQdCcSubNo = {0x00};

	/// <summary>
	///     系统类型
	/// </summary>
	public byte[] COsType = {0x03};

	/// <summary>
	///     是否x64
	/// </summary>
	public byte[] BIsWow64 = {0x01};

	public byte[] DwDrvVersionInfo = {0x01,0x02};

	/// <summary>
	///     TSSafeEdit.dat的"文件版本"
	/// </summary>
	public byte[] BufVersionTsSafeEditDat = Util.str_to_byte("07df000a000c0001");

	/// <summary>
	///     QScanEngine.dll的"文件版本"
	/// </summary>
	public byte[] BufVersionQScanEngineDll = { 0x00, 0x04, 0x00, 0x03, 0x00, 0x04, 0x20, 0x5c };

	public byte[] QdSufFix = { 0x68 };
	public byte[] QdPreFix = { 0x3E };

	/// <summary>
	///     wE7^3img#i)%h12]
	/// </summary>
	public byte[] BufQdKey =
	{ 0x77, 0x45, 0x37, 0x5e, 0x33, 0x69, 0x6d, 0x67, 0x23, 0x69, 0x29, 0x25, 0x68, 0x31, 0x32, 0x5d };



	/// <summary>
	///     主版本号
	/// </summary>
	public static byte[] DwSsoVersion  = {0x00,0x00,0x04,0x53};

	public static byte[] DwServiceId  = {0x00,0x00,0x00,0x01};

	/// <summary>
	///     客户端版本
	/// </summary>
	public static byte[] DwClientVer  = Util.str_to_byte("00 00 15 85");

	public byte[] DwIsp;
	public byte[] DwIdc;
	public long TimeDifference ;

	public byte[] BufSid  = Util.str_to_byte("1EC12571B24CEA919A6E8DE6954ECE06");

	//public byte[] bufSID  = Util.RandomKey();
	public byte[] QqexeMD5 ;



	public short WClientPort ;
	public String DwClientIP ;
	public byte[] DwClientIP_Byte ;
	
	public Date DwServerTime ;
	public byte[] DwServerTime_Byte ;
	
	/// <summary>
	///     重定向次数
	/// </summary>
	public byte[]  WRedirectCount ={0x00,0x00};
	public List<byte[]>  WRedirectips = new ArrayList<byte[]>();
	
	public String DwServerIP  = "61.151.226.190";
	public String DwRedirectIP ;
	public short WRedirectPort ;
	public byte[] WRedirectPort_Byte ;
	
	public short WServerPort  = 8000;
	public byte[] SubVer  = {0x00,0x01};
	public byte[] EcdhVer  = {0x01,0x02};
	public byte[] QdData ;


}



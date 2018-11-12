
import java.util.*;
import Utils.*;
public class TLVFactory
{

	
	public static byte[] tlv0018(QQUser user){
		byte[]data = new byte[]{};
	    byte[] WSubVer ={0x00,0x01};
		data= Util.byteMerger(data,WSubVer); //wSubVer 
		data= Util.byteMerger(data,TXProtocol.DwSsoVersion); //dwSSOVersion
		data= Util.byteMerger(data,TXProtocol.DwServiceId); //dwServiceId
		data= Util.byteMerger(data,TXProtocol.DwClientVer); //dwClientVer
		data= Util.byteMerger(data,Util.subByte(Util.ToByte(user.QQ),4,4)); //dwUin
		data=Util.byteMerger(data,user.TXProtocol.WRedirectCount); //wRedirectCount 
		data=Util.byteMerger(data,new byte[]{00,00});
		data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
		data=Util.byteMerger(new byte[]{0x00,0x18},data);//头部
		
		return data;
	}
	
	public static byte[] tlv0309(QQUser user){
		byte[]data = new byte[]{};
	    byte[] WSubVer ={0x00,0x01};
		data= Util.byteMerger(data,WSubVer); //wSubVer
		data= Util.byteMerger(data,Util.IPStringToByteArray(user.TXProtocol.DwServerIP)); //LastServerIP - 服务器最后的登录IP，可以为0
		data= Util.byteMerger(data, Util.subByte(Util.ToByte(user.TXProtocol.WRedirectips.size()),3,1)); //cRedirectCount - 重定向的次数（IP的数量）
		for (byte[] ip : user.TXProtocol.WRedirectips)
			{
				data= Util.byteMerger(data,ip);
			}

		data=Util.byteMerger(data,TXProtocol.CPingType); //cPingType 
		data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
		data=Util.byteMerger(new byte[]{0x03,0x09},data);//头部
		return data;
	}
	
	public static byte[] tlv0036(int ver){
		byte[]data = new byte[]{};
		
		if (ver ==2){
			data= Util.byteMerger(data,new byte[]{0x00,0x02,0x0,0x01,0x0,0x00,0x0,0x01,0x0,0x00,0x0,0x00,0x0,0x00,0x0,0x00,0x0,0x00}); //wSubVer
		data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
		data=Util.byteMerger(new byte[]{0x00,0x36},data);//头部
		}else if  (ver ==1){
			data= Util.byteMerger(data,new byte[]{0x00,0x01,0x0,0x01,0x0,0x00,0x0,0x00}); //wSubVer
			data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
			data=Util.byteMerger(new byte[]{0x00,0x36},data);//头部
		}
		
		return data;
	}
	
	
	public static byte[] tlv0114(QQUser user){
	    byte[] WSubVer ={0x01,0x02};
		byte[]data = new byte[]{};
		data=Util.byteMerger(data,WSubVer); //wDHVer
		data=Util.byteMerger(data, Util.subByte(Util.ToByte(user.TXProtocol.BufDhPublicKey.length),2,2)); //bufDHPublicKey长度
		data=Util.byteMerger(data,user.TXProtocol.BufDhPublicKey);
		data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
		data=Util.byteMerger(new byte[]{0x01,0x14},data);//头部
		
		return data;
	}
	
	public static byte[] tlv0112(QQUser user){
	    
		byte[]data = new byte[]{};
		
		data=Util.byteMerger(data,user.TXProtocol.BufSigClientAddr);
		data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
		data=Util.byteMerger(new byte[]{0x01,0x12},data);//头部

		return data;
	}
	
	public static byte[] tlv030f(QQUser user)
	{
		byte[]data = new byte[]{};
		data=Util.byteMerger(data, Util.subByte(Util.ToByte(user.TXProtocol.BufComputerName.getBytes().length),2,2));
		data=Util.byteMerger(data,user.TXProtocol.BufComputerName.getBytes());
		data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
		data=Util.byteMerger(new byte[]{0x03,0x0f},data);//头部
		// TODO: Implement this method
		return data;
	}
	
	public static byte[] tlv0005(QQUser user)
	{
		byte[]data = new byte[]{};
		byte[] WSubVer = {0x00,0x02};
		data=Util.byteMerger(data, WSubVer);
		data=Util.byteMerger(data,Util.subByte(Util.ToByte(user.QQ),4,4));
		data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
		data=Util.byteMerger(new byte[]{0x00,0x05},data);//头部
		// TODO: Implement this method
		return data;
	}
	
	
	public static byte[] tlv0006(QQUser user)
	{
		byte[]data = new byte[]{};
		if (user.TXProtocol.BufTgtgt == null){

			byte[] WSubVer = {0x00,0x02};

			byte[] random_byte = Util.random_byte(4);
			data=Util.byteMerger(data, random_byte);
			data=Util.byteMerger(data, WSubVer);
			data=Util.byteMerger(data,Util.subByte(Util.ToByte(user.QQ),4,4));
			data=Util.byteMerger(data,user.TXProtocol.DwSsoVersion);
			data=Util.byteMerger(data,user.TXProtocol.DwServiceId);
			data=Util.byteMerger(data,user.TXProtocol.DwClientVer);
			data=Util.byteMerger(data,new byte[]{0x00,0x00});
			data=Util.byteMerger(data,user.TXProtocol.BRememberPwdLogin);
			data=Util.byteMerger(data,user.MD51);    //密码
			data=Util.byteMerger(data,user.TXProtocol.DwServerTime_Byte);
			data=Util.byteMerger(data,new byte[13]);
			data=Util.byteMerger(data,user.TXProtocol.DwClientIP_Byte);
			data=Util.byteMerger(data,new byte[]{0x00,0x00,0x00,0x00,0x00,0x00});
			data=Util.byteMerger(data,user.TXProtocol.DwIsp); //dwISP
			data=Util.byteMerger(data,user.TXProtocol.DwIdc); //dwIDC
			data=Util.byteMerger(data,user.TXProtocol.BufComputerIdEx); //机器码
			data=Util.byteMerger(data,user.TXProtocol.BufTgtgtKey); //临时密匙
			Crypter crypter = new Crypter();
			user.TXProtocol.BufTgtgt = crypter.encrypt(data, user.MD52);
		}
		data = user.TXProtocol.BufTgtgt;
		data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
		data=Util.byteMerger(new byte[]{0x00,0x06},data);//头部
		return data;
	}

	
	public static byte[] tlv0015(QQUser user)
	{
		byte[]data = new byte[]{};
		byte[] WSubVer = {0x00,0x01};
		data=Util.byteMerger(data, WSubVer);
		data=Util.byteMerger(data, new byte[]{0x01});
		data=Util.byteMerger(data,user.TXProtocol.BufComputerId_crc32_reversed);
		data=Util.byteMerger(data,Util.subByte(Util.ToByte(user.TXProtocol.BufComputerId.length),2,2));
		data=Util.byteMerger(data,user.TXProtocol.BufComputerId);
		data=Util.byteMerger(data,new byte[]{0x02});
		data=Util.byteMerger(data,user.TXProtocol.BufComputerIdEx_crc32_reversed);
		data=Util.byteMerger(data,Util.subByte(Util.ToByte(user.TXProtocol.BufComputerIdEx.length),2,2));
		
		data=Util.byteMerger(data,user.TXProtocol.BufComputerIdEx);
		data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
		data=Util.byteMerger(new byte[]{0x00,0x15},data);//头部
		
		return data;
	}


	public static byte[] tlv001a(byte[] tlv0015, QQUser user)
	{
		byte[] data = new byte[]{};
		
		Crypter crypter = new Crypter();
		
		data = crypter.encrypt(tlv0015,user.TXProtocol.BufTgtgtKey);
		
		data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
		data=Util.byteMerger(new byte[]{0x00,0x1a},data);//头部
		
		return data;
	}

	
	public static byte[] tlv0103(QQUser user)
	{
		byte[] data = new byte[]{};
		byte[] WSubVer = {0x00,0x01};
		data=Util.byteMerger(data,WSubVer);
		data=Util.byteMerger(data,Util.subByte(Util.ToByte(user.TXProtocol.BufSid.length),2,2));
		data=Util.byteMerger(data,user.TXProtocol.BufSid);
		data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
		data=Util.byteMerger(new byte[]{0x01,0x03},data);//头部
		
		
		return data;
		
	}

	public static byte[] tlv0312()
	{
		byte[] data = new byte[]{};
	
		data=Util.byteMerger(data,new byte[]{0x01,0x00,0x00,0x00,0x01});
		data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
		data=Util.byteMerger(new byte[]{0x03,0x12},data);//头部
		
		return data;
	}

	public static byte[] tlv0508()
	{
		byte[] data = new byte[]{};

		data=Util.byteMerger(data,new byte[]{0x01,0x00,0x00,0x00,0x00});
		data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
		data=Util.byteMerger(new byte[]{0x05,0x08},data);//头部

		return data;
	}
	
	public static byte[] tlv0313(QQUser user)
	{
		byte[] data = new byte[]{};

		data=Util.byteMerger(data,new byte[]{0x01,0x01,0x02});
		
		data=Util.byteMerger(data,Util.subByte(Util.ToByte(user.TXProtocol.BufMacGuid.length),2,2));
		data=Util.byteMerger(data,user.TXProtocol.BufMacGuid);
		data=Util.byteMerger(data,new byte[]{0x00,0x00,0x00,0x02});
		data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
		data=Util.byteMerger(new byte[]{0x03,0x13},data);//头部
	
		return data;
	}
	
	public static byte[] tlv0102(QQUser user)
	{
		byte[] data = new byte[]{};
		byte[] WSubVer = {0x00,0x01};
		data=Util.byteMerger(data,WSubVer);
		
		data=Util.byteMerger(data,Util.str_to_byte("9e9b03236d7fa881a81072ec5097968e"));
		byte[] pic_byte = null;
		if (user.TXProtocol.BufSigPic == null){
		    pic_byte = Util.RandomKey(56);
		}else{
			pic_byte = user.TXProtocol.BufSigPic;
		}
		data=Util.byteMerger(data,Util.subByte(Util.ToByte(pic_byte.length),2,2));
		data=Util.byteMerger(data,pic_byte);
		
		byte[] new_key = Util.str_to_byte("606f27d7dc404633a6c4b9057e60fb641e756506");
		data=Util.byteMerger(data,Util.subByte(Util.ToByte(new_key.length),2,2));
		data=Util.byteMerger(data,new_key);
		data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
		data=Util.byteMerger(new byte[]{0x01,0x02},data);//头部
		
		return data;
	}

	public static byte[] tlv0110(QQUser user)
	{
		byte[] data = new byte[]{};
		byte[] WSubVer = {0x00,0x01};
		if (user.TXProtocol.BufSigPic == null)
		{
			return new byte[] { };
		}else{
			
			data=Util.byteMerger(data,WSubVer); //wSubVer
			data=Util.byteMerger(data,user.TXProtocol.BufSigPic);
			data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
			data=Util.byteMerger(new byte[]{0x01,0x10},data);//头部
			
		}
		
		return new byte[]{};
	}
	
	public static byte[] tlv0032(QQUser user)
	{
		byte[] data = new byte[]{};
		data = Util.byteMerger(data,Util.GetQdData(user));
		data=Util.byteMerger(Util.subByte(Util.ToByte(data.length),2,2),data);//长度
		data=Util.byteMerger(new byte[]{0x00,0x32},data);//头部
		
		return data;
	}
	
	
	
}

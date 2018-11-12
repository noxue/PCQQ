package Utils;
import java.io.*;
import java.util.*;
import java.nio.*;
import java.security.MessageDigest;
import java.security.*;
import QQUser;
import Utils.Crypter;

public class Util
{

	public static byte[] GetQdData(QQUser user)
	{
		byte[] data = new byte[]{};
		data = Util.byteMerger(data,Util.IPStringToByteArray(user.TXProtocol.DwServerIP));
		byte[] qddata = new byte[]{};
		qddata=Util.byteMerger(qddata,user.TXProtocol.DwQdVerion_Byte);
		qddata=Util.byteMerger(qddata,new byte[]{0x00,0x00,0x00,0x00});
		qddata=Util.byteMerger(qddata,user.TXProtocol.DwPubNo);
		qddata=Util.byteMerger(qddata,Util.subByte(Util.ToByte(user.QQ),4,4));
		qddata=Util.byteMerger(qddata,Util.subByte(Util.ToByte(data.length),2,2));
		
		data = new byte[]{};
		data=Util.byteMerger(data,user.TXProtocol.QdPreFix);
		data=Util.byteMerger(data,user.TXProtocol.CQdProtocolVer_Byte);
		data=Util.byteMerger(data,user.TXProtocol.DwQdVerion_Byte);
		data=Util.byteMerger(data,new byte[]{0x00});
		data=Util.byteMerger(data,user.TXProtocol.WQdCsCmdNo_Byte);
		data=Util.byteMerger(data,user.TXProtocol.CQdCcSubNo);
		data=Util.byteMerger(data,Util.str_to_byte("0E88"));
		data=Util.byteMerger(data,new byte[]{0x00,0x00,0x00,0x00});
		data=Util.byteMerger(data,user.TXProtocol.BufComputerIdEx);
		data=Util.byteMerger(data,user.TXProtocol.COsType);
		data=Util.byteMerger(data,user.TXProtocol.BIsWow64);
		data=Util.byteMerger(data,user.TXProtocol.DwPubNo);
		data=Util.byteMerger(data,Util.subByte(user.TXProtocol.DwClientVer,2,2));
		data=Util.byteMerger(data,new byte[]{0x00,0x00});
		data=Util.byteMerger(data,user.TXProtocol.DwDrvVersionInfo);
		data=Util.byteMerger(data,new byte[]{0x00,0x00,0x00,0x00});
		data=Util.byteMerger(data,user.TXProtocol.BufVersionTsSafeEditDat);
		data=Util.byteMerger(data,user.TXProtocol.BufVersionQScanEngineDll);
		data=Util.byteMerger(data,new byte[]{0x00});
		Crypter crypter = new Crypter();
		data=Util.byteMerger(data,crypter.encrypt(qddata,user.TXProtocol.BufQdKey));
		data=Util.byteMerger(data,user.TXProtocol.QdSufFix);
		
		int size = data.length + 3;
		qddata = new byte[]{};
		qddata=Util.byteMerger(qddata,user.TXProtocol.QdPreFix);
		qddata=Util.byteMerger(qddata,Util.subByte(Util.ToByte(size),2,6));
		qddata=Util.byteMerger(qddata,new byte[]{0x00,0x00});
		qddata=Util.byteMerger(qddata,Util.subByte(Util.ToByte(data.length),2,6));
		qddata=Util.byteMerger(qddata,new byte[]{0x00,0x00});
		
		user.TXProtocol.QdData=data;
		return data;
	}

	public static byte[] random_byte(int size)
	{
		byte [] b=new byte[size];
		Random random=new Random();
		random.nextBytes(b);
		
		return b;
	}

	
	public static short bytesToshort(byte[] input)
	{
		byte high = input[0];
        byte low = input[1];
        short z = (short)(((high & 0x00FF) << 8) | (0x00FF & low));
        return z;

   
		// TODO: Implement this metho
	}

	public static String GetIpStringFromBytes(byte[] input)
	{
		String u1 = String.valueOf((int)input[0]& 0xff);
		String u2 = String.valueOf((int)input[1]& 0xff);
		String u3 = String.valueOf((int)input[2]& 0xff);
		String u4 = String.valueOf((int)input[3]& 0xff);
		return u1 +"." + u2 +"." +u3 +"."+u4;
	}

	public static long bytesToLong(byte[] input, int offset, boolean littleEndian) {
// 将byte[] 封装为 ByteBuffer

		ByteBuffer buffer = ByteBuffer.wrap(Util.byteMerger(new byte[8-input.length],input));   

		if(littleEndian){            
			// ByteBuffer.order(ByteOrder) 方法指定字节序,即大小端模式(BIG_ENDIAN/LITTLE_ENDIAN)
			// ByteBuffer 默认为大端(BIG_ENDIAN)模式
			buffer.order(ByteOrder.LITTLE_ENDIAN);
		}
		
		return buffer.getLong();
	}
	
	public static int GetInt(byte[] data, int offset, int length)
	{
        byte[] test = new byte[]{0x00,0x00,data[offset],data[offset+1],0x00,0x00,0x00,0x00};
		
		ByteBuffer u = ByteBuffer.wrap(test);
		
		return u.getInt();
	}
	
	public static int GetInt(byte[] data)
	{
        byte[] test = new byte[]{0x00,0x00,data[0],data[1],0x00,0x00,0x00,0x00};

		ByteBuffer u = ByteBuffer.wrap(test);

		return u.getInt();
	}
	
	
	public static Date GetDateTimeFromMillis(long timeMillis)
	{
		Date date = new Date(timeMillis);
		return date;
	}

	public static byte[] GetBytes(String string)
	{
		
		
		// TODO: Implement this method
		return string.getBytes();
	}
	
	
	public static String GET_GTK(String skey)
	{
		String arg = "tencentQQVIP123443safde&!%^%1282";
		List<Integer> list = new ArrayList<Integer>();
		int num = 5381;
		list.add(172192);
		int i = 0;
		for (int length = skey.length(); i < length; i++)
		{
			int num2 = (skey).charAt(i);
			list.add((num << 5) + num2);
			num = num2;
		}

		StringBuilder stringBuilder = new StringBuilder();
		for (i = 0; i < list.size(); i++)
		{
			stringBuilder.append(list.get(i).toString());
		}

		return Md5(stringBuilder + arg);
	}
	public static String Md5(String text)
	{
		
		try
		{
		MessageDigest md = MessageDigest.getInstance("MD5");
		
        byte[] bytes = md.digest(text.getBytes());
		String result = "";
		for (byte b : bytes)
		{
			result += String.format("%02x", b);
		}

		return result;
		}
		catch (NoSuchAlgorithmException e)
		{}
		return null;
	}
	
	public static byte[] MD5(String arg)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
		

        byte[] bytes = md.digest(arg.getBytes());
		return bytes;
		}
		catch (NoSuchAlgorithmException e)
		{}
		return null;
	}
	public static byte[] MD5(byte[] arg)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");


			byte[] bytes = md.digest(arg);
			return bytes;
		}
		catch (NoSuchAlgorithmException e)
		{}
		return null;
	}
	public static String GetBkn(String skey)
	{
		int num = 5381;
		for (int i = 0; i <= skey.length() - 1; i++)
		{
			num += (num << 5) + Integer.parseInt(skey.substring(i, i + 1));

		}

		return String.valueOf((num & 0x7FFFFFFF));
	}
	
	public static String ToHex(byte[] data, String p1, String p2)
	{
		String hex= "";
        if (data != null) {
            for (Byte b : data) {
                hex += String.format("%02X", b.intValue() & 0xFF);
            }
        }
        return hex;
	}

	
	public static String NumToHexString(int qq, int Length)
	{
	
		String text = String.valueOf(qq);
		if (text.length() == Length)
		{
			return text;
		}

		if (text.length() > Length)
		{
			return null;
		}
		return null;
	}

	public static byte[] byteMerger(List<byte[]> bytes)
	{
		int total_length = 0;
		int offset= 0;
		for (byte[]  one : bytes){
			total_length = total_length + one.length;
		}
		byte[] byte_3 = new byte[total_length];
		
		for (byte[] one_byte : bytes){
			System.arraycopy(one_byte, 0, byte_3, offset, one_byte.length); 
            offset= offset + one_byte.length;
		}
          return byte_3;
	}

	
	public static byte[] IPStringToByteArray(String ip)
	{
		byte[] array = new byte[4];
		String[] array2 = ip.split("[.]");
		if (array2.length == 4)
		{
			for (int i = 0; i < 4; i++)
			{
				array[i] = (byte) Integer.parseInt(array2[i]);
			}
		}

		return array;
	}
	
	

	public static byte[] RandomKey()
	{
		byte[] key = new byte[16];
		new Random().nextBytes(key);
		return key;
	}
	public static byte[] RandomKey(int size)
	{
		byte[] key = new byte[size];
		new Random().nextBytes(key);
		return key;
	}
	
	public static byte[] str_to_byte(String str) {
        String replaceAll = str.replaceAll(" ", "");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(replaceAll.length() >> 1);
        for (int i = 0; i <= replaceAll.length() - 2; i += 2) {
            byteArrayOutputStream.write(Integer.parseInt(replaceAll.substring(i, i + 2), 16) & 255);
        }
        return byteArrayOutputStream.toByteArray();
    }
	
	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){  
        byte[] byte_3 = new byte[byte_1.length+byte_2.length];  
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);  
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);  
        return byte_3;  
    }
	
	public static byte[] ToByte(long x) {  
		ByteBuffer buffer = ByteBuffer.allocate(8);

		buffer.putLong(x);
		return  buffer.array();  
	}
	public static byte[] ToByte(int x) {  
        ByteBuffer buffer = ByteBuffer.allocate(8);

		buffer.putInt(x);
		return  buffer.array();  
    }
	public static String byte2HexString(byte[] bytes) {
        String hex= "";
        if (bytes != null) {
            for (Byte b : bytes) {
                hex += String.format("%02X", b.intValue() & 0xFF)+" ";
            }
        }
        return hex;

    }
	
	public static byte[] subByte(byte[] b,int off,int length){
		byte[] b1 = new byte[length];
		System.arraycopy(b, off, b1, 0, length);
		return b1;
	}
	
}

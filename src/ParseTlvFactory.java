
import Utils.*;
import java.util.*;
public class ParseTlvFactory
{


	public static void parsetvl(TLV tlv, QQUser user)
	{
		
		int tlvtype = tlv.Tag;
		if (tlvtype == 274){
			user.TXProtocol.BufSigClientAddr = tlv.Value;
		}else if(tlvtype == 23){

			byte[] WSubVer = Util.subByte(tlv.Value,1,1);
			if (WSubVer[0] == 1)
            {
                long timeMillis = Util.bytesToLong(Util.subByte(tlv.Value,2,4),0,false);
				user.TXProtocol.DwServerTime_Byte = Util.subByte(tlv.Value,2,4);
                user.TXProtocol.DwServerTime = Util.GetDateTimeFromMillis(timeMillis);
                user.TXProtocol.TimeDifference = (4294967295l & timeMillis) - new Date().getTime();
                user.TXProtocol.DwClientIP = Util.GetIpStringFromBytes(Util.subByte(tlv.Value,6,4));
				user.TXProtocol.DwClientIP_Byte = Util.subByte(tlv.Value,6,4);
                user.TXProtocol.WClientPort = Util.bytesToshort(Util.subByte(tlv.Value,10,tlv.Value.length-10));
				
			}
		}else if(tlvtype == 784){
			user.TXProtocol.DwServerIP = Util.GetIpStringFromBytes(tlv.Value);
		}
		else if(tlvtype == 12){
			byte[] WSubVer =  Util.subByte(tlv.Value,1,1);//wSubVer
			if (WSubVer[0] == 2){
				user.TXProtocol.DwIdc = new byte[]{0x00,0x10};  /*dwIDC =*/
				user.TXProtocol.DwIsp = Util.subByte(tlv.Value,6,2); /*dwISP =*/
				user.TXProtocol.DwRedirectIP = Util.GetIpStringFromBytes(Util.subByte(tlv.Value,12,4)); /*dwRedirectIP =*/
				user.TXProtocol.WRedirectips.add(Util.subByte(tlv.Value,12,4));
				user.TXProtocol.WRedirectPort_Byte = Util.subByte(tlv.Value,16,6);
				user.TXProtocol.WRedirectPort = Util.bytesToshort(Util.subByte(tlv.Value,16,6));
				/*wRedirectPort =*/
			}else{
			System.out.println("未知版本类型");
			}
			
		}else if(tlvtype == 30){
			user.TXProtocol.BufTgtgtKey = tlv.Value;
		}else if(tlvtype == 6){
			user.TXProtocol.BufTgtgt = tlv.Value;
		}else if(tlvtype == 272){
			if (Util.subByte(tlv.Value,1,1)[0] != 1){
			    System.out.println("未知版本");
			}else{
			user.TXProtocol.BufSigPic =Util.subByte(tlv.Value,2,tlv.Length-2);
			}
		}else if(tlvtype == 277){
			byte[] bufPacketMD5 = tlv.Value;
		}else if(tlvtype == 265){
			byte[] WSubVer =  Util.subByte(tlv.Value,1,1);//wSubVer
			if (WSubVer[0] == 1){
			    user.TXProtocol.BufSessionKey = Util.subByte(tlv.Value,2,16);
			    int length  = Util.GetInt(tlv.Value,18,2);
			    user.TXProtocol.BufPwdForConn = Util.subByte(tlv.Value,20,length);
			}else{
				System.out.println("未知版本类型");
			}
		}else if(tlvtype == 259){
			byte[] WSubVer =  Util.subByte(tlv.Value,1,1);//wSubVer
			if (WSubVer[0] == 1){
				int length  = Util.GetInt(tlv.Value,2,2);
				user.TXProtocol.BufSid = Util.subByte(tlv.Value,4,length);
			}else{
				System.out.println("未知版本类型");
			}
		}else if(tlvtype == 263){
			byte[] WSubVer =  Util.subByte(tlv.Value,1,1);//wSubVer
			if (WSubVer[0] == 1){
				int position = 0;
			    int length = Util.GetInt(tlv.Value,2,2);
				user.TXProtocol.BufTgtGtKey = Util.subByte(tlv.Value,4+length,16);
				position = 4 +length+16;
				length = Util.GetInt(Util.subByte(tlv.Value,position,2));
				user.TXProtocol.BufTgt =Util.subByte(tlv.Value,position+2,length);
				position = position+length+2;
				user.TXProtocol.Buf16BytesGtKeySt =Util.subByte(tlv.Value,position,16);
				position = position+16;
				length = Util.GetInt(Util.subByte(tlv.Value,position,2));
				user.TXProtocol.BufServiceTicket =Util.subByte(tlv.Value,position+2,length);
				position = position+length+2;
				length = Util.GetInt(Util.subByte(tlv.Value,position,2));
				byte[] http = Util.subByte(tlv.Value,position+2,length);
				position = position+length+2;
				user.TXProtocol.Buf16BytesGtKeyStHttp = Util.subByte(http,1,16);
				length = Util.GetInt(Util.subByte(http,17,2));
				user.TXProtocol.BufServiceTicketHttp =Util.subByte(http,19,length);
				user.TXProtocol.BufGtKeyTgtPwd = Util.subByte(tlv.Value,position,16);
		
			}else{
				System.out.println("未知版本类型");
			}
			
		}else if(tlvtype == 264){
			byte[] WSubVer =  Util.subByte(tlv.Value,1,1);//wSubVer
			if (WSubVer[0] == 1){
				int length = Util.GetInt(Util.subByte(tlv.Value,2,2));
				byte[] data = Util.subByte(tlv.Value,4,length);
				length = Util.GetInt(Util.subByte(data,0,2));
				data = Util.subByte(data,2,length);
				byte[] wSsoAccountWFaceIndex = Util.subByte(data,0,2);
				length = Util.GetInt(new byte[]{0x00,Util.subByte(data,2,1)[0]});
				if(length >0){
				    user.NickName = new String(Util.subByte(data,3,length));
				}
				user.Gender = Util.subByte(data,3+length,1)[0];
				byte[] dwSsoAccountDwUinFlag = Util.subByte(data,3+length+1,4);
                user.Age = Util.subByte(data,3+length+1+4,1)[0];
			}else{
				System.out.println("未知版本类型");
			}
			
		}else{
			
			System.out.println("未知tlv解析:"+tlv.Tag);
		}
	}
}

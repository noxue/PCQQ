
import java.util.*;
import Utils.*;public class SendPackegeFatcory
{
	protected static int _seq = 0x3635; // (char)Util.Random.Next();
	protected static byte[] body_end = {0x03}; // (char)Util.Random.Next();
	
	public static byte[] get0825(QQUser user){
		byte[] data = new byte[]{};
		byte[] Sequence = Util.subByte((Util.ToByte(GetNextSeq())),2,2);
		byte[] QQ_byte = Util.subByte((Util.ToByte(user.QQ)),4,4);
		
		data=Util.byteMerger(data,QQGlobal.QQHeaderBasicFamily);
		data=Util.byteMerger(data,user.TXProtocol.CMainVer);
		data=Util.byteMerger(data,user.TXProtocol.CSubVer);
		data=Util.byteMerger(data, new byte[]{0x08,0x25});
		data=Util.byteMerger(data,Sequence);
		data=Util.byteMerger(data,QQ_byte);
		data=Util.byteMerger(data,user.TXProtocol.XxooA);
		data=Util.byteMerger(data,user.TXProtocol.DwClientType);
		data=Util.byteMerger(data,user.TXProtocol.DwPubNo);
		data=Util.byteMerger(data,user.TXProtocol.XxooD);
		
		    
		data=Util.byteMerger(data,user.QQPacket0825Key);
		
		byte[] tlv_data = new byte[]{};
		byte[] tlv0018 = TLVFactory.tlv0018(user);
		byte[] tlv0039 = TLVFactory.tlv0309(user);
		byte[] tlv0036 = TLVFactory.tlv0036(2);
		byte[] tlv0114 = TLVFactory.tlv0114(user);
		List<byte[]> bytes = new ArrayList<byte[]>();
		bytes.add(tlv0018);
		bytes.add(tlv0039);
		bytes.add(tlv0036);
		bytes.add(tlv0114);
		tlv_data = Util.byteMerger(bytes);
		Crypter crypter = new Crypter();
		byte[] result = crypter.encrypt(tlv_data,user.QQPacket0825Key);
		data=Util.byteMerger(data,result);
		data=Util.byteMerger(data,body_end);
		
		return data;
		
	}
	
	public static byte[] get0836(QQUser user,boolean need_verifycode){
		byte[] data = new byte[]{};
		byte[] Sequence = Util.subByte((Util.ToByte(GetNextSeq())),2,2);
		byte[] QQ_byte = Util.subByte((Util.ToByte(user.QQ)),4,4);
		byte[] tlv0110 = null;
		byte[] tlv0032 = null;
		data=Util.byteMerger(data,QQGlobal.QQHeaderBasicFamily);
		data=Util.byteMerger(data,user.TXProtocol.CMainVer);
		data=Util.byteMerger(data,user.TXProtocol.CSubVer);
		data=Util.byteMerger(data, new byte[]{0x08,0x36});
		data=Util.byteMerger(data,Sequence);
		data=Util.byteMerger(data,QQ_byte);
		data=Util.byteMerger(data,user.TXProtocol.XxooA);
		data=Util.byteMerger(data,user.TXProtocol.DwClientType);
		data=Util.byteMerger(data,user.TXProtocol.DwPubNo);
		data=Util.byteMerger(data,user.TXProtocol.XxooD);
		
		data=Util.byteMerger(data,user.TXProtocol.SubVer);
		data=Util.byteMerger(data,user.TXProtocol.EcdhVer);
		data=Util.byteMerger(data,new byte[]{0x00,0x19});
		data=Util.byteMerger(data,user.TXProtocol.BufDhPublicKey);
		data=Util.byteMerger(data,new byte[] { 0x00, 0x00, 0x00, 0x10 });
		data=Util.byteMerger(data,user.QQPacket0836Key1);
		
		byte[] tlv_data = new byte[]{};
		byte[] tlv0112 = TLVFactory.tlv0112(user);
		byte[] tlv030f = TLVFactory.tlv030f(user);
		byte[] tlv0005 = TLVFactory.tlv0005(user);
		byte[] tlv0006 = TLVFactory.tlv0006(user);
		byte[] tlv0015 = TLVFactory.tlv0015(user);
		byte[] tlv001a = TLVFactory.tlv001a(tlv0015,user);
		byte[] tlv0018 = TLVFactory.tlv0018(user);
		byte[] tlv0103 = TLVFactory.tlv0103(user);
		if(need_verifycode){
			/*
			BodyWriter.Write(new TLV0110().Get_Tlv(User));
			BodyWriter.Write(new TLV0032().Get_Tlv(User));
			*/
			 tlv0110 = TLVFactory.tlv0110(user);
			 tlv0032 = TLVFactory.tlv0032(user);
		}
		byte[] tlv0312= TLVFactory.tlv0312();
		byte[] tlv0508 = TLVFactory.tlv0508();
		byte[] tlv0313= TLVFactory.tlv0313(user);
		byte[] tlv0102= TLVFactory.tlv0102(user);
		List<byte[]> bytes = new ArrayList<byte[]>();
		bytes.add(tlv0112);
		bytes.add(tlv030f);
		bytes.add(tlv0005);
		bytes.add(tlv0006);
		bytes.add(tlv0015);
		bytes.add(tlv001a);
		bytes.add(tlv0018);
		bytes.add(tlv0103);
		if(need_verifycode){
			bytes.add(tlv0110);
			bytes.add(tlv0032);
		}
		bytes.add(tlv0312);
		bytes.add(tlv0508);
		bytes.add(tlv0313);
		bytes.add(tlv0102);
		tlv_data = Util.byteMerger(bytes);
		Crypter crypter = new Crypter();
		byte[] result = crypter.encrypt(tlv_data,user.TXProtocol.BufDhShareKey);
		data=Util.byteMerger(data,result);
		data=Util.byteMerger(data,body_end);
		
		return data;
	}
	
	protected static int GetNextSeq()
	{
		_seq++;
		// 为了兼容iQQ
		// iQQ把序列号的高位都为0，如果为1，它可能会拒绝，wqfox称是因为TX是这样做的
		int i = 0x7FFF;
		_seq = _seq & i;
		if (_seq == 0)
		{
			_seq++;
		}

		return _seq;
	}
}

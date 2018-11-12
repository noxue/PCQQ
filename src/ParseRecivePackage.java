
import org.apache.http.util.*;
import Utils.*;
import java.util.*;public class ParseRecivePackage
{
	public byte[] Header = null;
	public byte[] Version = null;
	public byte[] Command = null;
	public byte[] Sequence = null;
	public byte[] QQ = null;
	public byte[] empty = null;
	public byte[] body_encrypted = null;
	public byte[] body_decrypted = null;
	public byte[] tea_key=null;
	private Collection<TLV> tlvs = null;
	private QQUser _user = null;
	public ParseRecivePackage(byte[] body,byte[] key,QQUser user){
		this.tea_key = key;
		this._user = user;
		this.Version = Util.subByte(body,1,2);
		this.Command = Util.subByte(body,3,2);
		this.Sequence = Util.subByte(body,5,2);
		this.QQ = Util.subByte(body,7,4);
		this.empty = Util.subByte(body,11,3);
		this.body_encrypted = Util.subByte(body,14,body.length-15);
		
	}
	
	public void decrypt_body(){
		Crypter crypter = new Crypter();
		this.body_decrypted = crypter.decrypt(this.body_encrypted,this.tea_key);
		this.Header = Util.subByte(this.body_decrypted,0,1);
		
	}
	
	public void parse_tlv(){
		tlvs = TLV.ParseTlv(Util.subByte(this.body_decrypted,1,this.body_decrypted.length-1));
		for (TLV tlv: tlvs){
			ParseTlvFactory.parsetvl(tlv,_user);
		}
		
	}
	
	public void parse0836(){
		this.decrypt_body();
		
		int result = this.Header[0];
		if (result == -5||result == 0 || result ==1 ||result ==51 ||
		result ==52 || result == 63||result ==248||
		result ==249||result ==250||result ==251||
		result ==254||result ==15||result==255){
		
			this.parse_tlv();
		}else{
			
			Crypter crypter = new Crypter();
			this.body_decrypted = crypter.decrypt(this.body_decrypted,_user.TXProtocol.BufTgtgtKey);
			this.Header = Util.subByte(this.body_decrypted,0,1);
			this.parse_tlv();
		}
		
		
		
	}
	
}


import Utils.*;
/// <summary>
///     定义一些QQ用到的常量
/// </summary>
public class QQGlobal
{
	/// <summary>
	/// 消息包记录列表过期时间(分钟)
	/// </summary>
	public int MessagesExpiredMinutes = 15;
	/// <summary>
	///     包最大大小
	/// </summary>
	public int QQPacketMaxSize = 65535;

	/// <summary>
	///     QQ缺省编码方式
	/// </summary>
	public String QQCharsetDefault = "UTF-8";

	/// <summary>
	///     密钥长度
	/// </summary>
	public static final  int QQLengthKey = 16;

	/// <summary>
	///     包起始标识
	/// </summary>
	public static final  byte[] QQHeaderBasicFamily = {0x02};

	/// <summary>
	///     包结尾标识
	/// </summary>
	public static final  byte QQHeader03Family = 0x03;


	/// <summary>
	///     基本协议族输入包的包头长度
	/// </summary>
	public static final  int QQLengthBasicFamilyInHeader = 7;

	/// <summary>
	///     基本协议族输出包的包头长度
	/// </summary>
	public static final  int QQLengthBasicFamilyOutHeader = 11;

	/// <summary>
	///     基本协议族包尾长度
	/// </summary>
	public static final  int QQLengthBasicFamilyTail = 1;

	/// <summary>
	///     FTP协议族包头长度
	/// </summary>
	public static final  int QQLengthFtpFamilyHeader = 46;

	/// <summary>
	///     05协议族包头长度
	/// </summary>
	public static final  int QQLength05FamilyHeader = 13;

	/// <summary>
	///     05协议族包尾长度
	/// </summary>
	public static final  int QQLength05FamilyTail = 1;

	/// <summary>
	///     网络硬盘协议族输入包包头长度
	/// </summary>
	public static final  int QQLengthDiskFamilyInHeader = 82;

	/// <summary>
	///     网络硬盘协议族输出包包头长度
	/// </summary>
	public static final  int QQLengthDiskFamilyOutHeader = 154;


	/// <summary>
	///     客户端版本号标志 - TIM1.0
	/// </summary>
	public static final  int QQClientVersion_0E1B = 0x3713;

	/// <summary>
	///     程序缺省使用的客户端版本号
	/// </summary>
	public static final  int QQClientVersion = QQClientVersion_0E1B;

	/// <summary>
	///     QQ UDP缺省端口
	/// </summary>
	public static final  int QQPortUdp = 8000;

	/// <summary>
	///     QQ TCP缺省端口
	/// </summary>
	public static final  int QQPortTcp = 443;

	/// <summary>
	///     使用HTTP代理时连接QQ服务器的端口
	/// </summary>
	public static final  int QQPortHttp = 80;


	/// <summary>
	///     QQ分组的名称最大字节长度，注意一个汉字是两个字节
	/// </summary>
	public static final  int QQMaxGroupName = 16;

	/// <summary>
	///     QQ昵称的最长长度
	/// </summary>
	public static final  int QQMaxNameLength = 250;

	/// <summary>
	///     QQ缺省表情个数
	/// </summary>
	public static final  int QQCountDefaultFace = 96;

	/// <summary>
	///     得到用户信息的回复包字段个数
	/// </summary>
	public static final  int QQCountGetUserInfoField = 37;

	/// <summary>
	///     修改用户信息的请求包字段个数，比实际的多1，最开始的QQ号不包括
	/// </summary>
	public static final  int QQCountModifyUserInfoField = 35;

	/// <summary>
	///     用户备注信息的字段个数
	/// </summary>
	public static final  int QQCountRemarkField = 7;


	// 用户标志，比如QQFriend类，好友状态改变包都包含这样的标志
	/// <summary>
	///     有摄像头
	/// </summary>
	public static final  int QQFlagCam = 0x80;


	/// <summary>
	///     服务器端版本号 (不一定)
	///     不一定真的是表示服务器端版本号，似乎和发出的包不同，这个有其他的含义，
	///     感觉像是包的类型标志
	/// </summary>
	public static final  int QQServerVersion0100 = 0x0100;

	/// <summary>
	///     是否打开控制台日志
	/// </summary>
	public static boolean DebugLog = true;

	public static byte[] QqexeMD5  = Util.str_to_byte("facf7cc5ae02e6650c0107cdfe0e1b2c");
}

/// <summary>
///     性别
/// </summary>
enum Gender 
{
	/** 
	 男

	 */
	GG(0),

	/** 
	 女

	 */
	MM(1),

	/** 
	 未知

	 */
	Unknown((byte) 0xFF);

	private int intValue;
	private static java.util.HashMap<Integer, Gender> mappings;
	private synchronized static java.util.HashMap<Integer, Gender> getMappings()
	{
		if (mappings == null)
		{
			mappings = new java.util.HashMap<Integer, Gender>();
		}
		return mappings;
	}

	private Gender(int value)
	{
		intValue = value;
		Gender.getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static Gender forValue(int value)
	{
		return getMappings().get(value);
	}
}

/// <summary>
///     登录模式
/// </summary>
enum LoginMode 
{
	/** 
	 正常

	 */
	Normal(0x0A),

	/** 
	 隐身

	 */
	Hidden(0x28);

	private int intValue;
	private static java.util.HashMap<Integer, LoginMode> mappings;
	private synchronized static java.util.HashMap<Integer, LoginMode> getMappings()
	{
		if (mappings == null)
		{
			mappings = new java.util.HashMap<Integer, LoginMode>();
		}
		return mappings;
	}

	private LoginMode(int value)
	{
		intValue = value;
		LoginMode.getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static LoginMode forValue(int value)
	{
		return getMappings().get(value);
	}
}

/** 
 在线状态

 */
enum QQStatus 
{
	/** 
	 在线

	 */
	Online(0x0A),

	/** 
	 离线

	 */
	Offline(0x14),

	/** 
	 离开

	 */
	Away(0x1E),

	/** 
	 隐身

	 */
	Hidden(0x28);

	private int intValue;
	private static java.util.HashMap<Integer, QQStatus> mappings;
	private synchronized static java.util.HashMap<Integer, QQStatus> getMappings()
	{
		if (mappings == null)
		{
			mappings = new java.util.HashMap<Integer, QQStatus>();
		}
		return mappings;
	}

	private QQStatus(int value)
	{
		intValue = value;
		QQStatus.getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static QQStatus forValue(int value)
	{
		return getMappings().get(value);
	}
}

/** 
 认证类型，加一个人为好友时是否需要验证等等

 */
enum AuthType 
{
	/** 
	 不需认证

	 */
	No(0),

	/** 
	 需要认证

	 */
	Need(1),

	/** 
	 对方拒绝加好友

	 */
	Reject(2);

	private int intValue;
	private static java.util.HashMap<Integer, AuthType> mappings;
	private synchronized static java.util.HashMap<Integer, AuthType> getMappings()
	{
		if (mappings == null)
		{
			mappings = new java.util.HashMap<Integer, AuthType>();
		}
		return mappings;
	}

	private AuthType(int value)
	{
		intValue = value;
		AuthType.getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static AuthType forValue(int value)
	{
		return getMappings().get(value);
	}
}

/** 
 联系方法的可见类型

 */
enum OpenContact
{
	/** 
	 完全公开

	 */
	Open(0),

	/** 
	 仅好友可见

	 */
	Friends(1),

	/** 
	 完全保密

	 */
	Close(2);

	private int intValue;
	private static java.util.HashMap<Integer, OpenContact> mappings;
	private synchronized static java.util.HashMap<Integer, OpenContact> getMappings()
	{
		if (mappings == null)
		{
			mappings = new java.util.HashMap<Integer, OpenContact>();
		}
		return mappings;
	}

	private OpenContact(int value)
	{
		intValue = value;
		OpenContact.getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static OpenContact forValue(int value)
	{
		return getMappings().get(value);
	}

}
/** 
 命令常量

 */


enum QQCommand
{
//* 
//	 保持在线状态
// 
//
	Message0X0002(0x0002),

//* 
//	 登录Ping
// 
//
	Login0X0825(0x0825),

//* 
//	 登录校验
// 
//
	Login0X0836(0x0836),
	Login0X0839(0x0839),

//* 
//	 取SessionKey
// 
//
	Login0X0828(0x0828),

//* 
//	 改变在线状态
// 
//
	Login0X00Ec(0x00EC),

//* 
//	 Token请求
// 
//
	Interactive0X00Ae(0x00AE),

//* 
//	 验证码提交
// 
//
	Login0X00Ba(0x00BA),

//* 
//	 请求一些操作需要的密钥，比如文件中转，视频也有可能  目前用来获取Skey
// 
//
	Data0X001D(0x001D),

//* 
//	 获取基本资料
// 
//
	Data0X005C(0x005C),

//* 
//	 获取群分组
// 
//
	Data0X0195(0x0195),

//* 
//	 查询黑名单
// 
//
	Data0X01A5(0x01A5),
	Data0X019B(0x019B),

//* 
//	 获取好友和群列表
// 
//
	Data0X0134(0x0134),
	Data0X01C4(0x01C4),
	Data0X01C5(0x01C5),
	Data0X0126(0x0126),

//* 
//	 天气预报
// 
//
	Data0X00A6(0x00A6),

//* 
//	 PM2.5浓度
// 
//
	Data0X0397(0x0397),

//* 
//	 问问个人中心API地址
// 
//
	Data0X00D8(0x00D8),

//* 
//	 群消息
// 
//
	Message0X0017(0x0017),

//* 
//	 群消息查看确认
// 
//
	Message0X0360(0x0360),
	Message0X01C0(0x01C0),

//* 
//	 好友消息
// 
//
	Message0X00Ce(0x00CE),

//* 
//	 群消息查看确认
// 
//
	Message0X0391(0x0391),

//* 
//	 消息查看确认
// 
//
	Message0X0319(0x0319),

//* 
//	 群消息撤回
// 
//
	Message0X03F7(0x03F7),

//* 
//	 消息撤回
// 
//
	Message0X03FC(0X03FC),

//* 
//	 发送好友消息
// 
//
	Message0X00Cd(0x00CD),

//* 
//	 获取Ukey
// 
//
	Message0X0352(0x0352),

//* 
//	 获取Ukey
// 
//
	Message0X0388(0x0388),

//* 
//	 心跳包
// 
//
	Message0X0058(0x0058),

//* 
//	 点赞
// 
//
	Interactive0X03E3(0x03E3),

//* 
//	 未知包
// 
//
	Unknown(0xFFFF);


	private int intValue;
	private static java.util.HashMap<Integer, QQCommand> mappings;
	private  static java.util.HashMap<Integer, QQCommand> getMappings()
	{
		if (mappings == null)
		{
			mappings = new java.util.HashMap<Integer, QQCommand>();
		}
		return mappings;
	}

	private QQCommand(int value)
	{
		intValue = value;
		QQCommand.getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static QQCommand forValue(int value)
	{
		return getMappings().get(value);
	}
}

enum MessageType
{
	/** 
	 普通文本

	 */
	Normal,

	/** 
	 @他人

	 */
	At,

	/** 
	 红包

	 */
	RedBag,

	/** 
	 系统表情

	 */
	Emoji,

	/** 
	 图片消息

	 */
	Picture,

	/** 
	 Xml消息

	 */
	Xml,

	/** 
	 Json消息

	 */
	Json,

	/** 
	 抖动

	 */
	Shake,

	/** 
	 音频

	 */
	Audio,

	/** 
	 视频

	 */
	Video,

	/** 
	 发送离线文件

	 */
	OfflineFile,

	/** 
	 退群

	 */
	ExitGroup,

	/** 
	 获取群信息

	 */
	GetGroupImformation,

	/** 
	 加群

	 */
	AddGroup,

	/** 
	 塞口球

	 */
	Mute;

	public int getValue()
	{
		return this.ordinal();
	}

	public static MessageType forValue(int value)
	{
		return values()[value];
	}

    public static class LoginStatus
    {
        public static final  byte 我在线上 = 0x0A;
        public static final  byte Q我吧 = 0x3C;
        public static final  byte 离开 = 0x1E;
        public static final  byte 忙碌 = 0x32;
        public static final  byte 请勿打扰 = 0x46;
        public static final  byte 隐身 = 0x28;
    }
}
/** 
 加好友类型

 */
enum AddFriendType
{
	AddFriend(0x01),
	AddGroup(0x02);

	private int intValue;
	private static java.util.HashMap<Integer, AddFriendType> mappings;
	private synchronized static java.util.HashMap<Integer, AddFriendType> getMappings()
	{
		if (mappings == null)
		{
			mappings = new java.util.HashMap<Integer, AddFriendType>();
		}
		return mappings;
	}

	private AddFriendType(int value)
	{
		intValue = value;
		AddFriendType.getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static AddFriendType forValue(int value)
	{
		return getMappings().get(value);
	}
}

/** 
 Tlv类型枚举

 */
enum TlvTags
{
	NonUinAccount(0x0004),
	Uin(0x0005),
	TGTGT(0x0006),
	TGT(0x0007),
	TimeZone(0x0008),
	ErrorInfo(0x000A),
	PingRedirect(0x000C),
	_0x000D(0x000D),
	_0x0014(0x0014),
	ComputerGuid(0x0015),
	ClientInfo(0x0017),
	Ping(0x0018),
	GTKeyTGTGTCryptedData(0x001A),
	GTKey_TGTGT(0x001E),
	DeviceID(0x001F),
	LocalIP(0x002D),
	_0x002F(0x002F),
	QdData(0x0032),
	_0x0033(0x0033),
	LoginReason(0x0036),
	ErrorCode(0x0100),
	Official(0x0102),
	SID(0x0103),
	_0x0104(0x0104),
	m_vec0x12c(0x0105),
	TicketInfo(0x0107),
	AccountBasicInfo(0x0108),
	_ddReply(0x0109),
	QDLoginFlag(0x010B),
	_0x010C(0x010C),
	SigLastLoginInfo(0x010D),
	_0x010E(0x010E),
	SigPic(0x0110),
	SigIP2(0x0112),
	DHParams(0x0114),
	PacketMd5(0x0115),
	Ping_Strategy(0x0309),
	ComputerName(0x030F),
	ServerAddress(0x0310),
	Misc_Flag(0x0312),
	GUID_Ex(0x0313),
	_0x0404(0x0404),
	_0x0508(0x0508),
	_0x050C(0x050C);

	private int intValue;
	private static java.util.HashMap<Integer, TlvTags> mappings;
	private synchronized static java.util.HashMap<Integer, TlvTags> getMappings()
	{
		if (mappings == null)
		{
			mappings = new java.util.HashMap<Integer, TlvTags>();
		}
		return mappings;
	}

	private TlvTags(int value)
	{
		intValue = value;
		TlvTags.getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static TlvTags forValue(int value)
	{
		return getMappings().get(value);
	}
}

enum ResultCode
{
	成功(0x00),
	需要更新TGTGT(0x01),
	帐号被回收(0x33),
	密码错误(0x34),
	需要验证密保(0x3F),
	DoMain(0xF8),
	要求切换TCP(0xF9),
	需要重新CheckTGTGT(0xFA),
	需要验证码(0xFB),
	需要重定向(0xFE),
	过载保护(0xFD),
	其它错误(0xFF);

	private int intValue;
	private static java.util.HashMap<Integer, ResultCode> mappings;
	private synchronized static java.util.HashMap<Integer, ResultCode> getMappings()
	{
		if (mappings == null)
		{
			mappings = new java.util.HashMap<Integer, ResultCode>();
		}
		return mappings;
	}

	private ResultCode(int value)
	{
		intValue = value;
		ResultCode.getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static ResultCode forValue(int value)
	{
		return getMappings().get(value);
	}
}



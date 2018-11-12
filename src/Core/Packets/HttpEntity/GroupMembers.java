package Core.Packets.HttpEntity;
import java.util.*;


public class GroupMembers
{
	public int Ec;
	public int AdmMax;
	public int AdmNum;
	public int Count;
	public int MaxCount;
	public int SearchCount;
	public long SvrTime;
	public int Vecsize;
	public List<LevelName> Levelname;
	public List<GroupMember> Mems;
}

class LevelName
{
	public int Level;
	public String Name;
}

class GroupMember
{
	public String Card ;
	public int Flag;
	public int G ;
	public long JoinTime ;
	public long LastSpeakTime ;
	public QQLevel Lv ;
	public String Nick ;
	public int Qage ;
	public int Role ;
	public int Tags ;
	public long Uin ;
}

class QQLevel
{
	public int Level;
	public int Point;
}

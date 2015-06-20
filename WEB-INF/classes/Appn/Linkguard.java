package Appn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.net.URL;
import java.util.regex.*;

public class Linkguard {

	public final int  PHISING=-1,POSSIBLE_PHISHING=0,NOTPHISHING=1;
	String actdns,visdns;
	public TreeSet block=new TreeSet(),white=new TreeSet(),seed=new TreeSet();
	String ser;
	String s1=null,s2=null,s3=null,s4=null;
	public Linkguard(String server) {

		try {
			ser=server;
			DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
			Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@"+server,"system","redhat" );
			PreparedStatement st = conn.prepareStatement("Select * from LIST ");
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				String type=rs.getString("role");
				if(type.equals("Black List"))
					block.add(rs.getString("name"));
				else if(type.equals("White List"))
					white.add(rs.getString("name"));
				else
					seed.add(rs.getString("name"));
			}

			st.close();
			conn.close();
		}
			catch(Exception e)
		{
			e.printStackTrace();

		}
	}
	public int linkGuard(String actlink,String vislink,String senderdns) {

		/******	 New coding implements    ********/
		if(actlink.indexOf("?")==-1)
		{
			System.out.println("Not type 555");
		}
		else
		{
			StringTokenizer st1=new StringTokenizer(actlink,"?");
			while(st1.hasMoreElements())
			{
				s1=(String) st1.nextToken();
				s2=st1.nextToken();
				System.out.println("hh="+s1);
				System.out.println("hhh="+s2);
			}
			StringTokenizer st2=new StringTokenizer(s2,"=");
			while(st2.hasMoreElements())
			{
				s3=st2.nextToken();
				s4=st2.nextToken();
			}
			System.out.println("hh="+s3);
			System.out.println("hhh="+s4);
			return linkGuard(s4,s1,senderdns);
		}
		/****              end        ****/

		int atype=typeFind(actlink);
		int vtype=typeFind(vislink);
		actdns="empty";

		visdns="empty";
		//if(atype==1)
			actdns=getDomain(actlink);
		//if(vtype==1)
			visdns=getDomain(vislink);
		System.out.println("actdns  "+actdns+" visdns  "+visdns);
		/*    algorithm starts ***********************/

		if((!actdns.equals("empty")&&!visdns.equals("empty"))&&(!actdns.equals(visdns)))   //first if
		{
			System.out.println("first if");
			new AutoSet().check(actdns,"Black List",ser);
			return PHISING;
		}
		else if(atype==2)//second if
		{
			System.out.println("second if");

			return POSSIBLE_PHISHING;
		}
		else if(atype==3||vtype==3)//third if
		{
			if(atype==3)
				actlink=decode(actlink)	;
			if(vtype==3)
				vislink=decode(vislink)	;
			System.out.println("3rd if");

			return linkGuard(actlink,vislink,senderdns);
		}

		else if(visdns.equals("empty")) //4th if
		{
			System.out.println("4th if");
			return analyzeDns(senderdns,actdns);
		}
		new AutoSet().check(actdns,"White List",ser);
		return NOTPHISHING;
	}
	public  int typeFind(String link) {
		int type=1;
		int count=0,decount=0;;
		String d ="";
		StringTokenizer st=new StringTokenizer(link,"%");
		while(st.hasMoreTokens())//when encoded
		{
			st.nextToken();
			++count;
		}
		if(count>5){
			type=3;
		} else {
			if(link.indexOf("http://")!=-1) {
				int dd = link.indexOf("http://")+7;
				int ff = link.indexOf("/",dd);
				int rr = link.length();
				if(ff!=-1) {
					d = link.substring(dd,ff);
				}

			}
			StringTokenizer deci = new StringTokenizer(d,".");
			while(deci.hasMoreTokens()) {
				deci.nextToken();
				++decount;
			}
			if(decount>3) {
				type = 2;
			}

		}
		//System.out.println(link+"------------------"+type);
		return type;
	}
	public  String getDomain(String link) {

		String port = ":\\d*";
		String domain="empty";
		Pattern p = Pattern.compile(port);

		if(link.startsWith("http://")) {
			String modlink = link.substring(link.indexOf("http://")+("http://").length(),link.length());
			//System.out.println("-------+++++++modlink++++++-----------"+modlink.indexOf(":"));
			if(modlink.indexOf(":")!=-1) {
				domain=modlink.substring(modlink.indexOf(":"),modlink.length());
				Matcher m = p.matcher(domain);
				while(m.find()) {
					String dom = domain.substring(m.start(),m.end());
					domain = domain.substring(dom.length()+1,domain.length());
					if(domain.contains("/")) {
						domain=domain.substring(0,domain.indexOf("/"));
					}
				}
			}
			/*if(link.indexOf(":8080/")!=-1) {
				domain=link.substring(link.indexOf(":8080/")+(":8080/").length(),link.length());
				//domain=domain.substring(0,domain.indexOf("/"));
				if(domain.contains("/")) {
					domain=domain.substring(0,domain.indexOf("/"));
				}
			}*/else if(link.indexOf("www")!=-1) {
				int dd = link.indexOf("www.")+("www.").length();
				domain = link.substring(dd,link.length());
			} else{
				int dd = link.indexOf("http://")+7;
				int ff = link.indexOf("/",dd);
				int rr = link.length();
				if(ff!=-1) {
					domain = link.substring(dd,ff);
				} else {
					domain = link.substring(dd,rr);
				}
			}

		} else if(link.startsWith("https://")) {
			String modlink = link.substring(link.indexOf("http://")+("http://").length(),link.length());
			if(modlink.indexOf(":")!=-1) {
				domain=modlink.substring(modlink.indexOf(":"),modlink.length());
				Matcher m = p.matcher(domain);
				while(m.find()) {
					String dom = domain.substring(m.start(),m.end());
					domain = domain.substring(dom.length()+1,domain.length());
					if(domain.contains("/")) {
						domain=domain.substring(0,domain.indexOf("/"));
					}
				}
			}
			/*if(link.indexOf(":8080/")!=-1) {
				domain=link.substring(link.indexOf(":8080/")+(":8080/").length(),link.length());
				domain=domain.substring(0,domain.indexOf("/"));
			}*/else if(link.indexOf("www")!=-1) {
				int dd = link.indexOf("www.")+("www.").length();
				domain = link.substring(dd,link.length());
			} else{
				int dd = link.indexOf("https://")+8;
				int ff = link.indexOf("/",dd);
				int rr = link.length();
				if(ff!=-1) {
					domain = link.substring(dd,ff);
				} else {
					domain = link.substring(dd,rr);
				}
			}

		} else if(link.indexOf("www.")!=-1){

			int dd = link.indexOf("www.")+("www.").length();
			domain = link.substring(dd,link.length());
		} else {
			domain = "empty";
		}
		//System.out.println(link+"----------domain "+domain);
		return domain;
	}

	public  String decode(String ip)
	{
		ip=ip.substring("http://".length(),ip.length());
		StringTokenizer st=new StringTokenizer(ip,"%");
		String op="http://";
		while(st.hasMoreTokens())
		{
			String s=(String)st.nextToken();
			char c='a';
			try
			{
				c=(char)Integer.parseInt(s);
			}
			catch(Exception e)
			{
				c=s.charAt(0);
			}

			op=op+c;
		}
		System.out.println(op);
		return op;
	}
	public  int analyzeDns(String s,String a)
	{
		String b[]=setToString(block);
		String w[]=setToString(white);
		for(int i=0;i<b.length;i++)
		{
			if(b[i].equals(a))
			{
				System.out.println(a+" <-actual analyse block ->"+b[i]);
				return PHISING;
			}
		}
		for(int i=0;i<w.length;i++)
		{
			if(w[i].equals(a))
			{
				System.out.println(a+" <-actual analyse white-> "+w[i]);
				return NOTPHISHING;
			}
		}
		return patternMatching(s,a);
	}
	public  String[] setToString(TreeSet ip)
	{
		int i=0;
		String str[]=new String[ip.size()];
		Iterator itr=ip.iterator();
		while(itr.hasNext())
			str[i++]=(String)itr.next();
		return str;
	}
	public int patternMatching(String s,String a)
	{
		if(!s.equals(a))
		{
			System.out.println(s+" <-sender act not eqoal equal pattern match-> "+a);


			return POSSIBLE_PHISHING;
		}
		String ss[]=setToString(seed);
		for(int i=0;i<ss.length;i++)
		{
			if(checkSimilar(a,ss[i]))
			{
				System.out.println(ss[i]+"similar"+a);
				return POSSIBLE_PHISHING;
			}
		}
		System.out.println("pattern");
		new AutoSet().check(actdns,"White List",ser);
		return NOTPHISHING;
	}

	public boolean checkSimilar(String a,String p)
	{
		if(a.equals(p))//same domain
		{
			System.out.println("same dom");
			return false;

		}
		else
		{
		if(a.indexOf(p)!=-1)//actual contain prev
		{
			System.out.println("subset");
			return true;
		}
		else//similarity
		{
		int no=p.length();
		int match=0;
		for(int i=0;i<p.length();i++)
		{
			String pc=""+p.charAt(i);
			String ac="";
			if(a.length()>i)
			{
			ac=""+a.charAt(i);
			if(ac.equals(pc))
				++match;
			}


		}
		int per=(match/no)*100;
		if(per>70)
			return true;
		}
		}
		return true;
	}

	public static void main(String arg[]) {
		Linkguard l=new Linkguard("gtsdotnet");

		//System.out.println("result  output  "+l.linkGuard("http://localhost:8080/Pshark/Idbi-help","click here","Idbi"));
		//System.out.println("result  output  "+l.linkGuard("http://10.0.0.23:8080/Pshark/Idbi-help","http://10.0.0.137:8080/Pshark/Idbi-help","Idbi"));
		//System.out.println(l.linkGuard("http://10.0.0.137:8090/Pshark","http://localhost:8090/Pshark","Idbi"));
		//System.out.println(l.linkGuard("http://www.profusenet.net/checksession.php","https://secure.regionset.com/EBanking/logon/","idbi"));
		//System.out.println(l.linkGuard("http://java.sun.com/j2ee/1.3/docs/tutorial/doc/IntroWS4.html","http://java.sun.com/j2ee/1.3/docs/tutorial/doc/IntroWS4.html","Idbi"));
		//System.out.println(l.linkGuard("http://www.google.com","http://www.google.com","Idbi"));
		//System.out.println(l.linkGuard("www.google.com","http://www.google.com","Idbi"));
		//System.out.println("result  output  "+l.linkGuard("http://gtsdotnet:8080/Pshark/Idbi-help","http://www.google.com","Idbi"));
	}

}
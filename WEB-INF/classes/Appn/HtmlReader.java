package Appn;
import java.io.FileInputStream;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.*;
import java.util.Random;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

public class HtmlReader {

Random r=new Random();
String outputUrl;
String domainurl,url;
boolean flag;
public boolean read_url(String str) {

	try{
		String userpwd = "";
		String userIn = "";
		String constrcturl = str.substring(0,str.lastIndexOf("/"));
		URL yahoo = new URL(str);
		URLConnection yc = yahoo.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

		System.out.println("constrcturl "+constrcturl);
		String linstr="",equalss;
		String pwd="";
		String user="";
		String action="";
		Pattern p = Pattern.compile("name=([^\\s])*");
		Pattern formp = Pattern.compile("action=[^\\s]*");
		while((linstr =in.readLine())!=null) {

			if(linstr.contains("form")) {
				Matcher fom = formp.matcher(linstr);
				while(fom.find()) {
					action = linstr.substring(fom.start(),fom.end());
				}
				if(action.contains("\"")) {
					action = action.substring(action.indexOf("\"")+1,action.length()-1);
				}
			}
			equalss = linstr.toLowerCase();
			if(equalss.contains("<input")) {

				if(linstr.contains("type="+"\"password\"")) {
					Matcher m = p.matcher(equalss);
					while(m.find()) {
						pwd = linstr.substring(m.start(),m.end());
						System.out.println("-----------------------------");
						//System.out.println("pwd ==== "+pwd);
						pwd = pwd.substring(pwd.indexOf("=")+2,pwd.lastIndexOf("\""));
					}
				} else if(!equalss.contains("type")) {
					Matcher m = p.matcher(linstr);
					while(m.find()) {
						user = linstr.substring(m.start(),m.end());
						//System.out.println("  user==== "+user);
						user = user.substring(user.indexOf("=")+2,user.lastIndexOf("\""));
					}

				}
			}

		}
		System.out.println("pwd ==== "+pwd+"------  user==== "+user+"-----  form===="+action);
		if((constrcturl.lastIndexOf("/"))!=-1) {
			domainurl = constrcturl.substring(constrcturl.lastIndexOf("/")+1,constrcturl.length());
		}
		if(action.contains(domainurl)) {
			url = constrcturl.substring(0,constrcturl.lastIndexOf("/"))+action+"?";
		} else {
			url = constrcturl+action+"?";
		}
		System.out.println("domainurl++++++"+domainurl+" url##########"+url);

		try {
			//String url = "http://gtsdotnet:8080";
			//url = url+action+"?";
			HttpClient client = new HttpClient();
			PostMethod method=null;
			for(int i=0;i<1000;i++){
				System.out.println("ii---------"+i);

				method = new PostMethod( url );
				userIn = userNameinput();
				userpwd=userPassword();

				// Configure the form parameters
				method.addParameter( user,  userIn);
				method.addParameter( pwd, userpwd);

				// Execute the POST method
				int statusCode = client.executeMethod( method );
				if( statusCode != -1 ) {
					method.releaseConnection();
				}
			}
			/*if( statusCode != -1 ) {
				String contents = method.getResponseBodyAsString();
				method.releaseConnection();
				System.out.println( contents );
			}*/
			flag = true;
		} catch( Exception e ) {
			flag = false;
	    	e.printStackTrace();
   		}
	} catch(Exception ex){
		flag = false;
		ex.printStackTrace();
	}
	return flag;
}

	public static void main(String[] arg) {
		HtmlReader hmt = new HtmlReader();
		System.out.println("  "+hmt.read_url("http://gtsdotnet:8080/xmail/BANKAWAY.htm") );
	}

	public String userNameinput() {
		String co="";
		String s="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		char c=s.charAt(r.nextInt(51));
		char c1=s.charAt(r.nextInt(51));
		char c2=s.charAt(r.nextInt(51));
		char c3=s.charAt(r.nextInt(51));
		char c4=s.charAt(r.nextInt(51));
		char c5=s.charAt(r.nextInt(51));
		char c6=s.charAt(r.nextInt(51));
		char c7=s.charAt(r.nextInt(51));
		char c8=s.charAt(r.nextInt(51));
		co=""+c+c1+c2+c3+c4+c5+c6+c7+c8;
		return co;

	}
	public String userPassword() {
		String co="";
		String s="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
		char c=s.charAt(r.nextInt(60));
		char c1=s.charAt(r.nextInt(60));
		char c2=s.charAt(r.nextInt(60));
		char c3=s.charAt(r.nextInt(60));
		char c4=s.charAt(r.nextInt(60));
		char c5=s.charAt(r.nextInt(60));
		char c6=s.charAt(r.nextInt(60));
		char c7=s.charAt(r.nextInt(60));
		char c8=s.charAt(r.nextInt(60));
		co=""+c+c1+c2+c3+c4+c5+c6+c7+c8;
		return co;
	}
}
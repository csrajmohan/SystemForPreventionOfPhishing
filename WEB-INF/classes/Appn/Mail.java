
package Appn;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
/**
  * A simple email sender class.
  */
public class Mail
{
	/**
	    * "send" method to send the message.
	    */
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	  public  boolean send(String smtpServer, String to, String from
	   , String subject, String body,String spoof)
	  {
	    try
	    {

	    	String j=to.substring(to.lastIndexOf("@"),to.length());
	    	to=to.replace(j,"@"+smtpServer);
	    	from=from.replace(j,"@"+smtpServer);
	    	System.out.print("to"+to);
	      Properties props = System.getProperties();
	      // -- Attaching to default Session, or we could start a new one --
	      props.put("mail.smtp.host", smtpServer);
	      Session session = Session.getDefaultInstance(props, null);
	      // -- Create a new message --
	      Message msg = new MimeMessage(session);
	      // -- Set the FROM and TO fields --
	      msg.setFrom(new InternetAddress(from));
	      msg.setRecipients(Message.RecipientType.TO,
	        InternetAddress.parse(to, false));
	       msg.setSubject(subject);
	      msg.setText(body);
	      // -- Set some other header information --

	      msg.setHeader("spoof", spoof);
	      msg.setSentDate(new Date());
	      // -- Send the message --
	      Transport.send(msg);
	      System.out.println("Message sent OK.");
	      return true;
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	      return false;
	    }
	  }

	  /**
	   * "printMessage()" method to print a message.
	   */
	 public  MsgForm printMessage(Message message)
	 {

	 	MsgForm mf=new MsgForm();
	 	String msg="";
	   try
	   {
	     // Get the header information
	     String from=((InternetAddress)message.getFrom()[0]).getPersonal();
	     if (from==null) from=((InternetAddress)message.getFrom()[0])
	      .getAddress();
	     mf.from=from;

	     String subject=message.getSubject();
	     mf.subject=subject;
	     Date d=message.getSentDate();
	     String maildate = sdf.format(d);
	     int hors = 24 - d.getHours();
	     String s[]=message.getHeader("spoof");
	     for(int i=0;i<s.length;i++)
	     mf.sp=s[i];
	     Calendar c=Calendar.getInstance();
	     c.setTime(d);
		 Date now = new Date();
		 String currdate = sdf.format(now);
		 long outdat = getIntervalMinutes(currdate,maildate);
		 System.out.println("outdat==== "+outdat);
	 	 if(outdat > 5) {
			 mf.outdate = "outdate";
		 }
	 	 System.out.println("outdate == "+mf.outdate);
		 //System.out.println("Existing Date== "+now.getHours()+" mintes= "+now.getMinutes());
		 //Calendar nowcc = Calendar.getInstance();
		 //nowcc.setTime(now);
	     mf.date=""+c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);
	     mf.size=""+message.getSize();
	     // -- Get the message part (i.e. the message itself) --
	     Part messagePart=message;
	     Object content=messagePart.getContent();
	     // -- or its first body part if it is a multipart message --
	     if (content instanceof Multipart)
	     {
	       messagePart=((Multipart)content).getBodyPart(0);

	     }
	     // -- Get the content type --
	     String contentType=messagePart.getContentType();
	     // -- If the content is plain text, we can print it --
	     if (contentType.startsWith("text/plain")
	      || contentType.startsWith("text/html"))
	     {
	       InputStream is = messagePart.getInputStream();
	       BufferedReader reader
	        =new BufferedReader(new InputStreamReader(is));
	       String thisLine=reader.readLine();
	       while (thisLine!=null)
	       {
	        msg=msg+thisLine;
	         thisLine=reader.readLine();

	       }
	       mf.msg=msg;
	     }
	     //System.out.println("---------"+mf.date+"--"+mf.from+"--"+mf.msg+"--"+mf.size+"--"+mf.subject+"--"+"****"+mf.outdate);

	   }
	   catch (Exception ex)
	   {
	     ex.printStackTrace();
	   }
	   return mf;
	 }



	  /**
	   * "receive" method to fetch messages and process them.
	   */
	 public  MsgForm[] receive(String popServer, String popUser  , String popPassword)
	 {

	 	popUser=popUser.substring(0,popUser.lastIndexOf("@"));
	   Store store=null;
	   Folder folder=null;
	   MsgForm[] mf=null;
	   try
	   {
	     // -- Get hold of the default session --
	     Properties props = System.getProperties();
	     Session session = Session.getDefaultInstance(props, null);
	     // -- Get hold of a POP3 message store, and connect to it --
	     store = session.getStore("pop3");
	     popUser=popUser+"@"+popServer;
	     store.connect(popServer, popUser, popPassword);

	     // -- Try to get hold of the default folder --
	     folder = store.getDefaultFolder();


	     if (folder == null) throw new Exception("No default folder");
	     // -- ...and its INBOX --
	     folder = folder.getFolder("INBOX");
	     if (folder == null) throw new Exception("No POP3 INBOX");
	     // -- Open the folder for read only --
	     folder.open(Folder.HOLDS_FOLDERS);
	     // -- Get the message wrappers and process them --

	      /* folder = store.getFolder("inbox");
	      folder.open(Folder.READ_ONLY); */


	     Message[] msgs = folder.getMessages();
	     mf=new MsgForm[msgs.length];
	     //System.out.println(folder.getMessageCount()+"length"+msgs.length);
	     for (int msgNum = 0; msgNum < msgs.length; msgNum++)
	     {


	        	mf[msgNum]=printMessage(msgs[msgNum]);

	     }
	   }
	   catch (Exception ex)
	   {
	     ex.printStackTrace();
	   }
	   finally
	   {
	     // -- Close down nicely --
	     try
	     {
	       if (folder!=null) folder.close(false);
	       if (store!=null) store.close();
	     }
	     catch (Exception ex2) {ex2.printStackTrace();}
	   }
	   return mf;
	 }

	 public long getIntervalMinutes(String currdate,String maildate) throws ParseException{
		 System.out.println("currdate == "+currdate+"maildate "+maildate);
		 Date d1 = sdf.parse(currdate);
		 Date d2 = sdf.parse(maildate);
		 long d1ms = d1.getTime();
		 long d2ms = d2.getTime();
		 if((d1ms-d2ms) > 0) {
			 return Math.abs((d1ms-d2ms)/60000);
		 } else {
			 return 0;
		 }
	 }
	 public static void main(String a[])
	 {
	 	Mail m=new Mail();
	 	//m.send("gts11","senthil@pshark.com","skumar@pshark.com","tod","today"+"\n"+"fai","skumar");
	 	m.receive("gtsjava","kannan@gtsjava","kannanan");
	 }

}
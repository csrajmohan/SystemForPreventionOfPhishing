import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BpelandWsdlToXml {

	String filelist;
	public HashSet<String> hs = new HashSet<String>();

	public void BpeltoWsdlvalue(Vector vt) {
		File f1 = new File("");
		String fget = f1.getAbsolutePath().toString();
		//File fp = new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 5.5\\temp");
		File fp = new File(fget+"\\temp");
		String dir=null;
		try {
			for(int i=0;i<vt.size();i++) {
				hs.clear();
				Vector iner = (Vector)vt.get(i);
				for(int ii=0;ii<iner.size();ii++) {
					String sbp = new String(iner.get(ii).toString());
					File bpfile = new File(sbp);
					//check the bpel directory & file
					if(bpfile.isDirectory()) {
						StringTokenizer stz = new StringTokenizer(sbp,"\\");
						int it = stz.countTokens();

						for(int j=0;j<it;j++) {
							if(j==it-1) {
								dir = stz.nextToken();
							}else {
								stz.nextToken();
							}
						}
						filelist = fp.getAbsolutePath().toString();
						filelist = filelist+"\\"+dir;
						File gg = new File(filelist);
						if(!gg.exists()) {
							gg.mkdir();
						} else {
							gg.delete();
						}

					}
					if(bpfile.isFile() && sbp.contains(dir)) {

						if(sbp.contains(".wsdl")) {
							String str1=iner.get(ii).toString();
							//System.out.println("111111"+str1);
							String wsdl = str1.substring(str1.lastIndexOf("\\")+1);
							String ws = ("wsdl"+ii).toString();
				 			File file = new File(str1);
				 			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				 			DocumentBuilder db = dbf.newDocumentBuilder();
				 			Document doc = db.parse(file);
				 			doc.getDocumentElement().normalize();
				 			NodeList nodeLst = doc.getElementsByTagName("soap:address");
				 			//System.out.println("Information of all employees"+nodeLst.getLength());

				 			for (int s = 0; s < nodeLst.getLength(); s++) {
				 				Node fstNode = nodeLst.item(s);
				 				Element fstElmnt = (Element) fstNode;
				 				hs.add(fstElmnt.getAttribute("location").toString());
				 			}

						}

						if(sbp.contains(".bpel")) {
							String bpelfile = iner.get(ii).toString();
							String wsbpel = bpelfile.substring(bpelfile.lastIndexOf("\\")+1);
							String bp = ("bpel"+ii).toString();
							//System.out.println("2222222  "+bpelfile);
							FileInputStream fis = new FileInputStream(bpelfile);
							BufferedReader in = new BufferedReader(new InputStreamReader(fis));
							String bpelinput;
							bpelfile = bpelfile.substring(bpelfile.lastIndexOf("\\")+1,bpelfile.length()-5);
							bpelfile = "bpel"+bpelfile+".xml";
							File ft = new File("");
							String bpellist = ft.getAbsolutePath().toString();
							FileOutputStream fo=new FileOutputStream(filelist+"\\"+bpelfile);
							String bpel_listfi = bpellist+"\\"+bpelfile;
							//bpelhs.add(bpel_listfi);
							while((bpelinput = in.readLine()) != null) {
								fo.write((bpelinput+"\n").getBytes());
							}
							in.close();
							fo.close();
							fis.close();
						}
					}

				}
				//System.out.println("hs======"+hs+"filelist====="+filelist);
				Iterator<String> it = hs.iterator();
				while(it.hasNext()) {
					String str = it.next().toString();
					URL yahoo = new URL(str+"?wsdl");
					URLConnection yc = yahoo.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
					String inputLine;
					String str1=str.substring(str.lastIndexOf("/")+1);//strin[j].substring(0,indx).trim();
					str1 = "wsdl"+str1+".xml";
					//samvt.add(str1);
					//File ft = new File("");
					//String filelist = ft.getAbsolutePath().toString();
					//System.out.println("&&&&&&&&&&&"+filelist+"str1====="+str1);
					//FileOutputStream fo=new FileOutputStream(filelist+"\\temp\\"+str1);
					FileOutputStream fo=new FileOutputStream(filelist+"\\"+str1);
					while ((inputLine = in.readLine()) != null)	{
						fo.write((inputLine+"\n").getBytes());
					}
					in.close();
					fo.close();
				}

			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		new NodeBpelFormXml().soap();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

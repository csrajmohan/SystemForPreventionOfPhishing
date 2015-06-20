<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://sun.com/cnpi/rsstag" prefix="rss"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
    <title>
      hellorss
    </title>
    <!--<link href="css/jdeveloper.css" rel="stylesheet" media="screen"></link>-->
  </head>
  <body>
    <h2>
      Fun With RSS
    </h2>

    <rss:feed feedId="tss_news"
              url="http://www.theserverside.com/rss/theserverside-rss2.xml"/>

    <rss:feed feedId="otn_news"
              url="http://www.oracle.com/technology/syndication/rss_otn_news.xml"/>

    <table cellspacing="2" cellpadding="3" border="1" width="100%">
        <tr>
          <th>
            OTN
          </th>
          <th>
            TheServerSide
          </th>
        </tr>
        <tr>
          <td>
            <!--<%@ include file="/otn.jspf"%>-->
          </td>
          <td>
            <!--<%@ include file="/tss.jspf"%>-->
          </td>
        </tr>
      </tbody>
  </body>
</html>
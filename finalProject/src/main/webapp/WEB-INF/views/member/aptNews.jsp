<%@page import="com.sun.syndication.feed.synd.SyndContent"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.sun.syndication.feed.synd.SyndFeed"%>
<%@ page import="com.sun.syndication.feed.synd.SyndEntry"%>
<%@ page import="java.util.Iterator"%>
<jsp:useBean id="syndFeed" scope="request" type="com.sun.syndication.feed.synd.SyndFeed" />

<html>
<head>
<title>website</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

div {
	margin: 20px;
	margin-left: 0px;
}

ul {
	list-style-type: none;
	width: 100%;
}

h3 {
	font: bold 20px/1.5 Helvetica, Verdana, sans-serif;
	margin-bottom: 30px;
	margin-left: 25px;
}


li p {
	font: 200 12px/1.5 Georgia, Times New Roman, serif;
}

li {
	padding: 10px;
	overflow: auto;
	border: 1px solid #ccc;
}

li:hover {
	background: #eee;
	cursor: pointer;
}
table{
	float: left;
	border: scrollbar;
	margin-right: 40px;
	margin-left: 0px;
}
table img{
	width: 170%;
	height: 100px;
	margin: -5px;
	margin-top: -15px;
	
}
#link{
	text-decoration: none;
	color: black;
}

</style>
<script type="text/javascript">
	

</script>
</head>
<body>

	<div>
		<ul>
			<% 
			 int i = 0;
          	 Iterator it = syndFeed.getEntries().iterator();
          	 while (it.hasNext())
          	 {
          		 i++;
          		 if(i==10){
          			 break;
          		 }
         	     SyndEntry entry = (SyndEntry) it.next();
            
        	 %>
			<li>
				<a href="<%=entry.getUri()%>" target="_blank" id="link">
				<h3><%=entry.getTitle()%></h3>
				<p><%=entry.getDescription().getValue()%></p>
				<br><br>
				</a>
			</li>
		<% } %>
		</ul>
	</div>
</body>
</html>
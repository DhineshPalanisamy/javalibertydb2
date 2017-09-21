<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import = "org.tcs.com.*"  %>
         <%@ page import = "java.util.*"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
h3 {
	font-family: tahoma;
	font-size: 15pt;
	color: navy;
}
td,th {
  width: 10rem;
  height: 5rem;
  border: 1px solid #ccc;
  text-align: center;
  font-family: tahoma;
}
th {
  background: lightblue;
  border-color: white;
}
body {
  background: /images/cool_blue.jpg;
  padding: 5rem;
}

a:link {
	color:#000;
	font-size: 1.1em;
	font-weight:400;
	font-family: arial, helvetica, sans-serif;
	text-decoration: underline;
	}
a:visited {
	color:#000;
	font-size:1.1em;
	font-weight:400;
	font-family:arial, helvetica, sans-serif;
	text-decoration: underline;
	}
a:hover {
	color: #000;
	font-size:1.1em;
	font-weight:400;
	font-family:arial, helvetica, sans-serif;
	text-decoration: underline;
	}
a:active {
	color:#000;
	font-size:1.1em;
	font-weight:400;
	font-family:arial, helvetica, sans-serif;
	text-decoration: underline;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Data</title>
</head>
<body>
	<%
	    flightbean flight=(flightbean)request.getAttribute("data");      
	%>
<center><h2>WELCOME!!</h2></center>	
<center><h3>Flight Number <%=flightbean.getFlight_num()%> airlines from <%= flightbean.getSource() %></h3></center>
<hr>
<center><h3>Scheduled arrival at <%= flightbean.getDestination() %></h3></center>
<br>
<center><h3>Various Food Joint offers available in the airport at your arrival time are listed ....Enjoy!!!!!</h3></center>
<center><h3> Have a Great Day!!!</h3></center>
<br/>
<hr>
<a href="index.jsp">Home</a>
</body>
</html>
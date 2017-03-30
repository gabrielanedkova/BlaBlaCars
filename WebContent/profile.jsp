<%@page import="java.io.Console"%>
<%@page import="model.Profile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.Writer"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <link rel="stylesheet" href="css/profile.css">
</head>

<body>

	     <% 
	     if(session.getAttribute("logged") == null || (Boolean) session.getAttribute("logged") == false){		
 			
	    	 session.invalidate();
	 		response.sendRedirect("index.jsp");
	 		return;
 		}
 	%> 	 

<div id="container">

<a style="float:left" href="profileSettings.html">Settings</a>
     
         <a style="float:right" href="logOut.jsp">LogOut</a> 


<%
	Profile user = (Profile) session.getAttribute("user");
	

%>


  <div class="image" style="background-image: url(http://25.media.tumblr.com/f9cd81645ebd2f0904227b42784bbfae/tumblr_mj6r7nBfdV1s7aky5o2_r1_1280.jpg)"></div>
  <div class="info">
    <h1 class="name"><% out.print(user.getFirstName() +  " " + user.getLastName()); %></h1>
    <h3 class="position"><a href="#">Born in: <% out.print(user.getYearOfBirth()); %></a></h3>
    <p> <% out.print(user.getMiniBio()); %>  </p>
  </div>
  <div class="social">
   
    <button class="button"/>&nbsp Offered &nbsp</button>
   &nbsp&nbsp
    <button class="button"/>&nbsp Booked &nbsp</button>
   &nbsp&nbsp
    <button class="button" onclick = "window.location.href='indexLoggedIn.jsp'">
    	 &nbsp Search &nbsp</button>
    
  </div>
</div>  

</body>
</html>
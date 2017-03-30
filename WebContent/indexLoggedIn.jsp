<%@page import="model.Profile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta charset="UTF-8">
  <title>Front Page Form</title>
  <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

  
      <link rel="stylesheet" href="css/style.css">

  
</head>

<body>

   <% if(session == null || session.isNew() || session.getAttribute("logged") == null || (Boolean) session.getAttribute("logged") == false){		
 			session.invalidate();
	 		response.sendRedirect("index.jsp");
	 		return;
 		}
 	%> 
  <div class="form">
   <a style="float:right" href="logOut.jsp">LogOut</a> 
      
     <% Profile user = (Profile) session.getAttribute("user"); %>
     <!--  <center>
	<img src="https://cdn.shutterstock.com/shutterstock/videos/15432958/thumb/2.jpg"  />
     </center> -->
        <div id="frontpage">   
          <h1>Welcome, <% out.print(user.getFirstName()); %> ! Choose your destination!</h1>
          
          <form action="travel" method="post" >
          
          <div class="top-row">
               
            <div class="field-wrap">
              <div class="field-wrap">
            <label>
                Leaving from...<span class="req">*</span>
              </label>
              </div>
               <select  name="pickUp" required="required" >
          <option value="" selected="selected">Leaving from...</option>
          <option value="SOFIA">Sofia</option><option value="PLOVDIV">Plovdiv</option>
          <option value="SLIVEN">Sliven</option><option value="PERNIK">Pernik</option>
          <option value="VARNA">Varna</option><option value="BURGAS">Burgas</option>
          </select>
              
            </div>
        
            <div class="field-wrap">
            <div class="field-wrap">
            <label>
                Going to...<span class="req">*</span>
              </label>
              </div>
             <select  name="dropOff" required="required" >
          <option value="" selected="selected">Going to...</option>
          <option value="SOFIA">Sofia</option><option value="PLOVDIV">Plovdiv</option>
          <option value="SLIVEN">Sliven</option><option value="PERNIK">Pernik</option>
          <option value="VARNA">Varna</option><option value="BURGAS">Burgas</option>
          </select>
            </div>
          </div>
          
         
        <div class="field-wrap">
               <a href="profile.jsp">Go to profile
                <span class="req"></span>
              </a>
              
            </div>
          
          <button type="submit" class="button button-block"/>Get Started</button>
          
          </form>

        </div>
   
</div> <!-- /form -->
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/index.js"></script>

</body>
</html>

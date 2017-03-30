<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="UTF-8">
  <title>Change pass form</title>
  <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

  
      <link rel="stylesheet" href="css/style.css">

  
</head>


<body>


<% 
	if(session.getAttribute("logged") == null || (Boolean) session.getAttribute("logged") == false){		
			
		 session.invalidate();
		response.sendRedirect("index.jsp");
		return;
	}
%>
  <div class="form">
      
      <ul class="tab-group">
        <li class="tab active"><a href="#profilesettings">Profile Settings</a></li>
        <li class="tab"><a href="#changepass">Change Password</a></li>
      </ul>
      
      <div class="tab-content">
        <div id="profilesettings">   
          <h1></h1>
          
          <!-- imeto na servlet-a -->
          <form action="register" method="post" >
          
          <div class="field-wrap">
              <label>
                First Name<span class="req"></span>
              </label>
              <input type="text"  autocomplete="off"  name="firstName"/>
            </div>
        
            <div class="field-wrap">
              <label>
                Last Name<span class="req"></span>
              </label>
              <input type="text" autocomplete="off" name="lastName"/>
            </div>
            
             <div class="field-wrap">
              <label>
                Mini biography<span class="req"></span>
              </label>
              <input type="text" autocomplete="off" name="miniBio"/>
            </div>

          
          <div class="field-wrap">
            <label>
              Enter password to save changes<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="password"/>
          </div>
          
          <button type="submit" class="button button-block"/>Save Changes</button>
          
          </form>

        </div>
        
        <div id="changepass">   
          <h1></h1>
          
          <form action="changePass" method="post">
          <h1> <%out.print(session.getAttribute("passChange")); %> </h1>
            <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="oldPassword"/>
          </div>
          
          <div class="field-wrap">
            <label>
             New Password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="password"/>
          </div>
          
          <div class="field-wrap">
            <label>
             Confirm new Password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="passConfirm"/>
          </div>
               
       <!--    <p class="forgot"><a href="#">Forgot Password?</a></p> -->
          
          <button class="button button-block"/>Log In</button>
          
          </form>

        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/index.js"></script>

</body>
</html>

<!--  
<body>


<div class="form">
 <form action="changePass" method="post" >
<form class="top-row">
<fieldset>

<!-- Form Name -- >
<legend> </legend>
 
<% 
	if(session.getAttribute("logged") == null || (Boolean) session.getAttribute("logged") == false){		
			
		 session.invalidate();
		response.sendRedirect("index.jsp");
		return;
	}
%>


 <h1>Password settings</h1>
 <h3> <%out.print(session.getAttribute("passChange")); %> </h3>


<!-- Text input-- >
 <div class="field-wrap">
            <label>
              Old password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="oldPassword"/>
          </div>
 <div class="field-wrap">
            <label>
              New password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="password"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Confirm new password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="passConfirm"/>
          </div>
          
          <button type="submit" class="button button-block"/>Submit changes</button>
          
          </form>
          </form>
          


</div>

 <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/index.js"></script>

</body> 
</html> -->

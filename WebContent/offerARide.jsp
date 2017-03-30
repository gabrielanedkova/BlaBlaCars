<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ page errorPage="errorPage.jsp" %>
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
       <a style="float:right" href="profile.jsp">Back to Profile</a> <br><br>
      <ul class="tab-group">
        <li class="tab active"><a href="#offer">Offer a Ride</a></li>
        <li class="tab"><a href="#changepass">Change Password</a></li>
      </ul>
      
      <div class="tab-content">
        <div id="offer">   
          <h1></h1>
         <!--   public Travel(Profile person, Car car, Destination pickUp, Destination dropOff, LocalDateTime date, int price, 
			int freeSeats, boolean ladiesOnly, Luggage maxLuggage, String description,
			Flexibility pickUpFlexibilty) {-->
          <!-- imeto na servlet-a -->
          <form action="offerARide" method="post" >
          
          <div class="field-wrap">
            <select  name="pickUp" required="required" >
          <option value="" selected="selected">Leaving from...</option>
          <option value="SOFIA">Sofia</option><option value="PLOVDIV">Plovdiv</option>
          <option value="SLIVEN">Sliven</option><option value="PERNIK">Pernik</option>
          <option value="VARNA">Varna</option><option value="BURGAS">Burgas</option>
          </select>
              
            </div>
            
            
            <div class="field-wrap">
              <select  name="dropOff" required="required" >
          <option value="" selected="selected">Going to ...</option>
          <option value="SOFIA">Sofia</option><option value="PLOVDIV">Plovdiv</option>
          <option value="SLIVEN">Sliven</option><option value="PERNIK">Pernik</option>
          <option value="VARNA">Varna</option><option value="BURGAS">Burgas</option>
          </select>
              
            </div>
          
             <div class="field-wrap">
              <label>
                Date: (yyyy-mm-dd)<span class="req"></span>
              </label>
            <input type="text" name="date" />
          </div>
          
          <div class="field-wrap">
              <label>
                Time: (hh-mm-ss)<span class="req"></span>
              </label>
            <input type="text" name="time"/>
          </div>
          
          <div class="field-wrap">
              <label>
                Price<span class="req"></span>
              </label>
              <input type="text"  autocomplete="off"  name="price"/>
            </div>
            
            <div class="field-wrap">
              <select  name="freeSeats" required="required" >
          <option value="" selected="selected">Free Seats</option>
          <option value=1>1</option><option value=2>2</option>
          <option value=3>3</option><option value=4>4</option>
          <option value=5>5</option>
          </select>
              
            </div>
        
            <div class="field-wrap">
              <select  name="ladiesOnly" required="required" >
          <option value="" selected="selected">Ladies only</option>
          <option value=true>yes</option><option value=false>no</option>
          </select>
              
            </div>
            
            <div class="field-wrap">
              <select  name="maxLuggage" required="required" >
          <option value="" selected="selected">Max Luggage</option>
          <option value="LARGE">Large</option><option value="MEDIUM">Medium</option>
          <option value="SMALL">Small</option>
          </select>
              
            </div>
            
             <div class="field-wrap">
              <label>
                Description<span class="req"></span>
              </label>
              <input type="text" autocomplete="off" name="description"/>
            </div>
            
            
			<div class="field-wrap">
              <select  name="flexibility" required="required" >
          <option value="" selected="selected">PickUp Flexibility</option>
          <option value="NONE">None</option><option value="FIFTEEN_MINS_MAX">15 min max</option>
          <option value="THIRTY_MINS_MAX">30 min max</option> , <option value="ANYTHING_IS_FINE">Anything is fine</option>
          </select>
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
          
          <button class="button button-block"/>Save Changes</button>
          
          </form>

        </div>
        
      </div><!-- tab-content -->
      
</div> <!-- /form -->
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/index.js"></script>

</body>
</html>


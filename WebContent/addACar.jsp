<%@page import="model.Car"%>
<%@page import="model.Profile"%>
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


<% 
 Profile user =(Profile) session.getAttribute("user");
	Car car = user.getCar();
	if(!car.getModel().equals("-123J")){
		response.sendRedirect("car.jsp");
		return;
	}

%>
<div class="form">
      
       
          	<!--  public Car(Brand brand, String model,  TypeOfCar type, Comfort comfort, int numberOfSeats, Color color) {-->
          
          <form action="addCar" method="post">
           <a style="float:right" href="profile.jsp">Back to Profile</a> <br><br>
         <h1>Add a car!</h1>
         
            <div class="field-wrap"> 
            
          <select  name="brand" required="required" >
          <option value="" selected="selected">Brand</option>
          <option value="HONDA">Honda</option><option value="BMW">BMW</option>
          <option value="MERCEDES">Mercedes</option></select>
          </div>
          
           <div class="field-wrap">
            
            <label>
              Model<span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off" name="model"/>
          </div>
          
           <div class="field-wrap"> 
            
          <select  name="comfort" required="required" >
          <option value="" selected="selected"><label>
              Comfort<span class="req">*</span>
            </label></option>
          <option value="BASIC">Basic</option><option value="NORMAL">Normal</option>
          <option value="COMFORTABLE">Comfortable</option><option value="LUXURY">Luxury</option></select>
          </div>
        
	
          <div class="field-wrap">
           
          <select  name="color" required="required"  >
          <option value="" selected="selected">Color*</option>
          <option value="BLACK">Black</option><option value="BLUE">Blue</option>
          <option value="GREEN">Green</option><option value="WHITE">White</option>
          <option value="YELLOW">Yellow</option><option value="PINK">Pink</option>
          <option value="RED">Red</option><option value="GREY">Grey</option></select>
          </div>
          
         
	<div class="field-wrap"> 
	 
          <select  name="type" required="required" >
          <option value="" selected="selected">Type*</option>
          <option value="SALOON">Saloon</option><option value="HATCHBACK">Hatchback</option>
          <option value="CONVERTIBLE">Convertible</option><option value="ESTATE">Estate</option>
          <option value="SUV">SUV</option><option value="STATION_WAGON">Station Wagon</option>
          <option value="MINIVAN">Minivan</option><option value="VAN">Van</option></select>
          </div>
          
         
          <div class="field-wrap"> 
          
          <select  name="seats" required="required" >
          <option value="" selected="selected">Number of seats*</option>
          <option value="2">2</option><option value="3">3</option>
          <option value="4">4</option><option value="5">5</option>
          <option value="6">6</option><option value="7">7</option>
          <option value="8">8</option><option value="9">9</option></select>
          </div>
           
               
       <!--    <p class="forgot"><a href="#">Forgot Password?</a></p> -->
          
          <button  type="submit" class="button button-block"/>Save Changes</button>
          
          </form>

  	</div>      
</body>
</html>
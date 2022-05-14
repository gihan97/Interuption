<%@page import="com.Interruption"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Interruption Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Interruption.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" />
</head>
<body>
<h1 class="text-white text-center mt-5">Interruption Management</h1>
	<div class="container ">
			<div class="mx-auto float-left" style="width:30%;" >
				
				<form id="formItem" name="formItem" class="mt-5">
				
				<div class="input-group mb-3">				
  					<div class="input-group-prepend"> 					
    					<span class="input-group-text" id="basic-addon1"><i class="fa fa-clock-o" aria-hidden="true"></i></span>
  					</div>			
  					<input id="time" name="time" type="text" class="form-control"  placeholder="Insert Time Here ">
				</div>
				
				<div class="input-group mb-3">				
  					<div class="input-group-prepend"> 					
    					<span class="input-group-text" id="basic-addon1"><i class="fa fa-users" aria-hidden="true"></i></span>
  					</div>			
  					<input id="no_of_crew" name="no_of_crew" type="number" class="form-control" pattern="[0-9]{2,10} "placeholder="Insert Number of Crew Here ">
				</div>
				
				<div class="input-group mb-3">				
  					<div class="input-group-prepend"> 					
    					<span class="input-group-text" id="basic-addon1"><i class="fa fa-car" aria-hidden="true"></i></span>
  					</div>			
  					<input id="vehicle_no" name="vehicle_no" type="text" class="form-control" placeholder="Insert Vehicle Number Here">
				</div>
				
				<div class="input-group mb-3">				
  					<div class="input-group-prepend"> 					
    					<span class="input-group-text" id="basic-addon1"><i class="fa fa-phone" aria-hidden="true"></i></span>
  					</div>			
  					<input id="phone_no" name="phone_no" type="number" class="form-control" pattern="[0-9]+" placeholder="Insert Contact Number Here">
				</div>
				
				<div class="input-group mb-3">				
  					<div class="input-group-prepend"> 					
    					<span class="input-group-text" id="basic-addon1"><i class="fa fa-globe" aria-hidden="true"></i></span>
  					</div>			
  					<input id="province" name="province" type="text" class="form-control" placeholder="Insert Province Here">
				</div>
				
					
					 <div class="text-center mt-2"> 
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary" style="width:30%; height:50px;"> 
					<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">	
					</div>	
				</form>
				<div id="alertSuccess" class="alert alert-success mt-5"></div>
				<div id="alertError" class="alert alert-danger mt-5"></div>
			</div>	
		</div>
		
		<div class="container ">
			<div id="divItemsGrid" class="float-right  mt-5 text-white text-center">
					<%
					

					Interruption interruptionObj = new Interruption();
								out.print(interruptionObj.readInterruption());
					%>
			</div>
		</div>
	

	
</body>
	<style>
	body{
		/* background-image: radial-gradient( circle 610px at 5.2% 51.6%,  rgba(5,8,114,1) 0%, rgba(7,3,53,1) 97.5% ); */

/* background-image: linear-gradient( 90.9deg,  rgba(3,195,195,1) 0.3%, rgba(37,84,112,1) 87.8% ); */
/* 
background: #0f0c29;  
background: -webkit-linear-gradient(to right, #24243e, #302b63, #0f0c29);  
background: linear-gradient(to right, #24243e, #302b63, #0f0c29);  */

background: #000428;  /* fallback for old browsers */
background: -webkit-linear-gradient(to right, #004e92, #000428);  /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to right, #004e92, #000428); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */




	}
		
	</style>
</html>
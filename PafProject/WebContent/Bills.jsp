<%@ page import="com.Bills"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.6.0.min.js"></script> 
<script src="Components/main.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>All Bill Informations</h1>
				<form id="formItem" name="formItem" method="post" action="Bills.jsp">  
					Account Number:  
 	 				<input id="accNo" name="accNo" type="text"  class="form-control form-control-sm">
					<br>Usage/Point:   
  					<input id="usage1" name="usage1" type="text" class="form-control form-control-sm">   
  					<br>Vat/Tax:   
  					<input id="vat" name="vat" type="text"  class="form-control form-control-sm">
  					<br>Value(RS):   
  					<input id="value" name="value" type="text"  class="form-control form-control-sm">
					<br>Total Amount(RS)
					<input id="total" name="total" type="text" class="form-control form-control-sm">
					
					
					<br>
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value=""> 
				</form>
				
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
			   
			    <div id="divItemsGrid">
					<%
					    Bills BillsObj = new Bills();
						out.print(BillsObj.readBills());
					%>
				</div>
			   
				
				
				
				
			</div>
		</div>
</div>
 

</body>
</html>
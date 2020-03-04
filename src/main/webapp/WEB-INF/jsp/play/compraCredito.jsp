<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compra Credito</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

<div class="container">

   <%@ include file="../header.jsp" %>
      
    <div class="page-header">
	  <h3>Compra Credito</h3>
	</div>
	


      	<form  class="form-horizontal" action="/play/ExecuteCompraCredito" method="post">
      		
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="cifraId">Cifra:</label>
	    		<div class="col-sm-4">
					<input value ="0.00" class="form-control" type="number" min="0" step="0.01" id="cifraId" name="cifra">
			 	</div>
  			</div>
  			
  			
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		      <a href="/play/" class="btn btn-primary btn-md">Annulla</a>
				
		        <button type="submit" class="btn btn-primary btn-md">Conferma Compra</button>
		      </div>
		    </div>
		</form>
		
    </div><!-- /.container -->



</body>
</html>
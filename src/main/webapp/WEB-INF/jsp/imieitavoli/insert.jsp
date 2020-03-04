<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crea Nuovo Tavolo</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

<div class="container">

   <%@ include file="../header.jsp" %>
      
    <div class="page-header">
	  <h3>Creazione Tavolo</h3>
	</div>
	
	<%-- alert con lista errori --%>
	<div class="alert alert-danger ${not empty tavoloErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${tavoloErrors }">
        	<ul>
				<li>${errorItem.getField()}: ${errorItem.getDefaultMessage()}</li>
			</ul>
      	</c:forEach>
	</div>


      	<form  class="form-horizontal" action="/imieitavoli/ExecuteInsert/" method="post">
      		
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="cifraMinimaPerEntrareId">Credito Disponibile Minimo Per Entrare:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="number" min="0" step="0.01" id="cifraMinimaPerEntrareId" name="cifraMinimaPerEntrare" 
					value = "${tavoloAttr.cifraMinimaPerEntrare}">
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="denominazioneId">Denominazione:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="denominazioneId" name="denominazione" 
					value = "${tavoloAttr.denominazione}">
			 	</div>
  			</div>
			<div class="form-group">
      			<label class="control-label col-sm-2" for="esperienzaMinId">Esperienza Minima Per Entrare:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="number" min = "0" step ="1" id="esperienzaMinId" name="esperienzaMin" 
					value = "${tavoloAttr.esperienzaMin}">
			 	</div>
  			</div>
  			
  			
  			
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		      <a href="/imieitavoli/" class="btn btn-primary btn-md">Annulla</a>
				
		        <button type="submit" class="btn btn-primary btn-md">Conferma Creazione Tavolo</button>
		      </div>
		    </div>
		</form>
		
    </div><!-- /.container -->



</body>
</html>
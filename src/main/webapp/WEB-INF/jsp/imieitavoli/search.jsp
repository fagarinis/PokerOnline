<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ricerca Tavoli</title>
</head>
<body>

<div class="container">

   <%@ include file="../header.jsp" %>
      
    <div class="page-header">
	  <h3>Pagina di Ricerca Tra I Miei Tavoli</h3>
	</div>
	
	<div class="alert alert-success ${messaggioConferma==null?'d-none':''}" role="alert">
	  ${messaggioConferma }
	</div>

      	<form class="form-horizontal" action="/imieitavoli/ExecuteSearch" method="post">
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="denominazioneId">Denominazione:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="denominazioneId" name="denominazione" >
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="dataCreazioneId">Data:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="date" id="dataCreazioneId" name="dataCreazione" >
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="cifraMinimaPerEntrareId">Credito Disponibile Minimo Per Entrare:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="number" min="0" step="0.01" id="cifraMinimaPerEntrareId" name="cifraMinimaPerEntrare" >
			 	</div>
  			</div>
			
  			
  			
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		      <a href="/imieitavoli/" class="btn btn-primary btn-md">Annulla</a>
		        <button type="submit" class="btn btn-primary btn-md">Effettua Ricerca Tra I Miei Tavoli</button>
		      </div>
		    </div>
		    
		</form>
		
		
		<%@ include file="../footer.jsp"%>
    </div><!-- /.container -->



</body>
</html>
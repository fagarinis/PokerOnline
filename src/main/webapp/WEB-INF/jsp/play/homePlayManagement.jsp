<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Play Management</title>
</head>
<body>

<div class="container">

   <%@ include file="../header.jsp" %>
      
    <div class="page-header">
	  <h3>Play Management</h3>
	</div>
	
	<div class="alert alert-success ${messaggioConferma==null?'d-none':''}" role="alert">
	  ${messaggioConferma }
	</div>

  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        
		        <a href="/play/PrepareCompraCredito" class="btn btn-primary btn-md">Compra Credito</a><br><br>
		        <a href="/play/ExecuteGoToLastGame" class="btn btn-primary btn-md">Go To Last Game</a><br><br>
		        <a href="/play/PrepareSearch" class="btn btn-primary btn-md">Ricerca Tavoli</a>
		        
		      </div>
		    </div>
		    
		
		
		<%@ include file="../footer.jsp"%>
    </div>



</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ricerca Utente</title>
</head>
<body>

<div class="container">

   <%@ include file="../header.jsp" %>
      
    <div class="page-header">
	  <h3>I Miei Tavoli</h3>
	</div>
	
	<div class="alert alert-success ${messaggioConferma==null?'d-none':''}" role="alert">
	  ${messaggioConferma }
	</div>

  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        
		        <a href="/imieitavoli/PrepareInsert" class="btn btn-primary btn-md">Crea Nuovo Tavolo</a><br><br>
		        <a href="/imieitavoli/PrepareSearch" class="btn btn-primary btn-md">Ricerca Tra I Miei Tavoli</a>
		      </div>
		    </div>
		    
		
		
		<%@ include file="../footer.jsp"%>
    </div>



</body>
</html>
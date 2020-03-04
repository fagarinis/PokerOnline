<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

<div class="container">
 <%@ include file="header.jsp" %>
 
 	<%-- alert accesso non consentito --%>
		<div
			class="alert alert-danger ${messaggio!=null?'':'d-none' }"
			role="alert">${messaggio }</div>
   	
   		<c:if test="${userInfo.isAdmin() }">
       	 <div class="jumbotron">
      		<div class="container">
       	 		<h1 class="display-4"> Gestione Amministrazione </h1>
       	 		<p><a class="btn btn-primary btn-lg" href="/gestioneAmministrazione" role="button">Vai alla Gestione &raquo;</a></p>
     	 	</div>
     	 </div>
     	 </c:if>
     	 
     
    	 <div class="jumbotron">
      		<div class="container">
       	 		<h1 class="display-4"> I Miei Tavoli</h1>
       	 		<p><a class="btn btn-primary btn-lg" href="/imieitavoli" role="button">Vai alla Gestione &raquo;</a></p>
     	 	</div>
     	 </div>
     
     	
       	 <div class="jumbotron">
      		<div class="container">
       	 		<h1 class="display-4"> Play Management</h1>
       	 		<p><a class="btn btn-primary btn-lg" href="/play" role="button">Gioca &raquo;</a></p>
     	 	</div>
     	 </div>
     	 

	 <%@ include file="footer.jsp" %>
</div>

</body>
</html>
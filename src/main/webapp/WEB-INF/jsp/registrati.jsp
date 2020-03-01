<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>

function validateUtenteForm() {
	tagNome = document.getElementById("nomeInputId");
	tagCognome = document.getElementById("cognomeInputId");
	tagUsername = document.getElementById("usernameInputId");
	tagPassword = document.getElementById("passwordInputId")

	if (isEmpty(tagNome.value)) {
		tagNome.focus();
		return false;
	}

	if (isEmpty(tagCognome.value)) {
		tagCognome.focus();
		return false;
	}

	if (isEmpty(tagUsername.value)) {
		tagUsername.focus();
		return false;
	}
	
	if (isEmpty(tagPassword.value)) {
		tagPassword.focus();
		return false;
	}

}


function isEmpty(str) {
	return (str.length === 0 || !str.trim());
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrazione</title>
<!-- Bootstrap -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/css/signin.css" rel="stylesheet">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body class="text-center">
	<div class="container">
	
		
		
		<div class="alert alert-success  ${messaggioConferma==null?'d-none':''}" role="alert">
		  ${messaggioConferma }
		</div>
	    <form class="form-signin" action="/eseguiRegistrazione" method="post">
	    
	    
	    <%-- alert con lista errori --%>
		<div class="alert alert-danger ${not empty utenteErrors?'':'d-none' }" role="alert">
			<c:forEach var = "errorItem" items="${utenteErrors }">
        		<ul>
					<li>${errorItem.getField()}: ${errorItem.getDefaultMessage()}</li>	
				</ul>
      		</c:forEach>
		</div>
	    
	    
	<!--       <img class="mb-4" src="../../assets/brand/bootstrap-solid.svg" alt="" width="72" height="72"> -->
	      <h1 class="h3 mb-3 font-weight-normal">Registrazione</h1>
	      
	      <label for="inputUsername" class="sr-only">Nome</label>
	      <input type="text" name="nome" id="inputNome" class="form-control" placeholder="Nome" 
	      value ="${utenteAttr.nome }" >
	      
	      <label for="inputCognome" class="sr-only">Cognome</label>
	      <input type="text" name="cognome" id="inputCognome" class="form-control" placeholder="Cognome"
	      value ="${utenteAttr.cognome }" >
	      <br>
	      <label for="inputUsername" class="sr-only">Username</label>
	      <input type="text" name="username" id="inputUsername" class="form-control" placeholder="Username"
	      value ="${utenteAttr.username }"  >
	      
	      <label for="inputPassword" class="sr-only">Password</label>
	      <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" 
	      value ="${utenteAttr.password }" >
	      
	      
	      
	      <button class="btn btn-lg btn-primary btn-block" type="submit">Conferma Registrazione</button>
	      <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
	    </form>
    </div>
  </body>
</html>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/js/jquery-1.10.2.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bootstrap.bundle.js"></script>



</body>
</html>
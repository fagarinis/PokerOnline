<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cancella Tavolo</title>
</head>
<body>

	<div class="container">
		<%@ include file="../header.jsp"%>

		<div class="page-header">
			<h3>Pagina di Conferma Cancellazione</h3>
		</div>
		<form action ="/imieitavoli/ExecuteDelete" method ="post">
		
		<div class="container-fluid">
			<input readonly hidden type="text" id="id" name="id" value ="${tavoloAttr.id }">
			 	
		
			<dl class="row">
				<dt class="col-sm-3 text-right">Id</dt>
				<dd class="col-sm-9">${tavoloAttr.id }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Esperienza Minima Per Entrare</dt>
				<dd class="col-sm-9">${tavoloAttr.esperienzaMin }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Credito Minimo Per Entrare</dt>
				<dd class="col-sm-9">${tavoloAttr.cifraMinimaPerEntrare } &euro;</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Denominazione</dt>
				<dd class="col-sm-9">${tavoloAttr.denominazione }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Data Creazione</dt>
				<dd class="col-sm-9">${tavoloAttr.dataCreazione }</dd>
			</dl>
				
			<a href="javascript:history.back()" class="btn btn-primary btn-md">Torna
				Indietro</a>
				
			<button type="submit" class="btn btn-primary btn-md">Conferma
			 Cancellazione</button>
			
		</div>
		</form>
		<%@ include file="../footer.jsp"%>
	</div>
	
	

</body>
</html>
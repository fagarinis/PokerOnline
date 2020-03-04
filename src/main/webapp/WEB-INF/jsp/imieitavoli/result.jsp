<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risultati Ricerca</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
</head>
<body>

	<div class="container">

		<%@ include file="../header.jsp"%>
		<div class="page-header">
			<h3>Pagina dei Risultati I Miei Tavoli</h3>
		</div>

		<%-- alert conferma --%>
		<div
			class="alert alert-success ${messaggioConferma!=null?'':'d-none' }"
			role="alert">${messaggioConferma }</div>
			
		<%-- alert errore --%>
		<div
			class="alert alert-danger ${messaggioErrore!=null?'':'d-none' }"
			role="alert">${messaggioErrore }</div>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Denominazione Tavolo</th>
					<th>Data Creazione</th>
					<th>Cifra Minima</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach var="tavoloItem" items="${listaTavoliAttr }">
					<tr>
						<td>
						${tavoloItem.denominazione}
						</td>
						<td>
						${tavoloItem.dataCreazione}
						</td>
						<td>
						${tavoloItem.cifraMinimaPerEntrare} &euro;
						</td>
						<td><a
							href="/imieitavoli/${tavoloItem.id}/ExecuteDettaglio"
							class="btn btn-info">Dettaglio</a> <a
							href="/imieitavoli/${tavoloItem.id}/PrepareModifica"
							class="btn btn-info">Modifica</a> <a
							href="/imieitavoli/${tavoloItem.id}/PrepareDelete"
							class="btn btn-info">Cancella</a> 
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				
				<a href="/imieitavoli/PrepareSearch" class="btn btn-primary btn-md">Nuova Ricerca</a>
				<a href="/imieitavoli/PrepareInsert" class="btn btn-primary btn-md">Crea Nuovo Tavolo</a><br><br>
		        
			</div>
		</div>

		<%@ include file="../footer.jsp"%>

	</div>
</body>
</html>
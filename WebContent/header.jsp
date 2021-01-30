<%@page import="logic.controller.MainController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!
	MainController mainController = new MainController();
	String pageName = "home.jsp";
	String nextAction = "";
	String nextPage = "";
	String btn = "";
%>
<%
	if (session.getAttribute("mainController") != null) {
		mainController = (MainController) session.getAttribute("mainController");
	}
	else {
		session.setAttribute("mainController", mainController);
	}
	
	if (session.getAttribute("btn") == null) 
		session.setAttribute("btn", "home");
	else 
		btn = (String) session.getAttribute("btn");
%>


<html>

	<head>
		<meta charset="UTF-8">
		<title>PeakLand</title>
		
		<!-- Bootstrap CSS -->
    	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    	
		<!-- import our CSS for header of our site -->
		<link rel="stylesheet" href="header.css" type="text/css"/>
		
	</head>
	
	<body>
		<div>
			<div class="row mx-auto background-green">
				<div class="col-4" align="center"><img src="Images/montagna.png" class="img-responsive"></div>
				<div class="col-4" align="center"><img src="Images/Logo.png" class="img-responsive-logo"></div>
				<div class="col-4" align="center"><img src="Images/montagna_2.png" class="img-responsive"></div>
			</div>
			
			<form class="form" action="header.jsp" method="post">
				<ul class="nav navbar background-orange">
					<li>
						<button type="submit" name="home" value="home" class="btn btn-primary btn-nav" ${ sessionScope.btn eq "home"  ? 'style="background-color: #f69155"' : ''}>Home</button>
					<li>
						<button type="submit" name="searchPath" value="searchPath" class="btn btn-primary btn-nav" ${ sessionScope.btn eq "view"  ? 'style="background-color: #f69155"' : ''}>Search path</button>
					<li>
						<button type="submit" name="addNewPath" value="addNewPath" class="btn btn-primary btn-nav" ${ sessionScope.btn eq "new"  ? 'style="background-color: #f69155"' : ''}>Add new path</button>
					</li>
				</ul>
			</form>
		</div>
		
		<%
		if (request.getParameter("home") != null && request.getAttribute("new") == null) {
			session.setAttribute("btn", "home");
			nextPage = "";
			nextAction = mainController.onActionRequired(nextPage, (Integer) session.getAttribute("sessionId"));
			request.setAttribute("new", false);
		}
		else if (request.getParameter("searchPath") != null && request.getAttribute("new") == null) {
			session.setAttribute("btn", "view");
			nextPage = "View info";
			nextAction = mainController.onActionRequired(nextPage, (Integer) session.getAttribute("sessionId"));
			request.setAttribute("new", false);
		}
		else if (request.getParameter("addNewPath") != null && request.getAttribute("new") == null) {
			session.setAttribute("btn", "new");
			nextPage = "Add path";
			nextAction = mainController.onActionRequired(nextPage, (Integer) session.getAttribute("sessionId"));
			request.setAttribute("new", false);
		}
		
		
		switch(nextAction) {
			case "Login":
				pageName = "login.jsp";
				break;
			case "View info":
				pageName = "searchPath.jsp";
				break;
			case "Add path":
				pageName = "addNewPath.jsp";
				break;
		}
		
		switch(nextPage) {
			case "Login":
				nextPage = "login.jsp";
				break;
			case "View info":
				nextPage = "searchPath.jsp";
				break;
			case "Add path":
				nextPage = "addNewPath.jsp";
				break;
		}
		
		if (pageName != "home.jsp") {
			%>
			<jsp:forward page="<%=pageName%>">
				<jsp:param name="nextPageId" value="<%=nextPage%>"/>
			</jsp:forward>
			<%
		}
		%>
		
	<!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
	
	</body>
	
</html>
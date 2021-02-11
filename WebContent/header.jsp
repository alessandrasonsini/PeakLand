<%@page import="com.google.api.Page"%>
<%@page import="logic.model.enums.PageId"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>
<%@page import="logic.controller.MainController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!
	MainController mainController = new MainController();
	List<String> actionList = List.of("Home", "View info", "Add path", "Profile");
	String pageName = "home.jsp";
	String nextAction = "";
	String nextPage = "";
	String btn = "";
%>
<%
	if (session.getAttribute("mainController") != null)
		mainController = (MainController) session.getAttribute("mainController");
	else
		session.setAttribute("mainController", mainController);
	
	if (session.getAttribute("btn") == null) 
		session.setAttribute("btn", "Home");
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
				<div class="row background-orange justify-content-between">
					<div class="col-9">
						<ul class="nav navbar background-orange">
							<li>
								<button type="submit" name="Home" value="Home" class="btn btn-primary btn-nav" ${ sessionScope.btn eq "Home"  ? 'style="background-color: #f69155"' : ''}>Home</button>
							<li>
								<button type="submit" name="View info" value="View info" class="btn btn-primary btn-nav" ${ sessionScope.btn eq "View info"  ? 'style="background-color: #f69155"' : ''}>Search path</button>
							<li>
								<button type="submit" name="Add path" value="Add path" class="btn btn-primary btn-nav" ${ sessionScope.btn eq "Add path"  ? 'style="background-color: #f69155"' : ''}>Add new path</button>
							</li>
						</ul>
					</div>
					<div class="col-2">
						<ul class="nav navbar background-orange">
							<li>
								<button type="submit" name="Profile" value="Profile" class="btn btn-primary btn-nav" ${ sessionScope.btn eq "Profile"  ? 'style="background-color: #f69155"' : ''}>
									<img src="Images/icons8-male-user-64.png" width="44%">
								</button>
							<li>
						</ul>
					</div>
				</div>
			</form>
		</div>
		
		<%
		for (String action : actionList) {
			if (request.getParameter(action.toString()) != null && request.getAttribute("new") == null) {
				session.setAttribute("btn", action);
				nextPage = action.toString();
				nextAction = mainController.onActionRequired(PageId.valueOf(nextPage), (Integer) session.getAttribute("sessionId"));
				request.setAttribute("new", false);
				break;
			}
		}
			
		if (!nextPage.equals("")) {
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
				case "Profile":
					pageName = "profile.jsp";
					break;
				case "Home":
					pageName = "home.jsp";
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
				case "Profile":
					nextPage = "profile.jsp";
					break;
				case "Home":
					nextPage = "home.jsp";
					break;
			}
		
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
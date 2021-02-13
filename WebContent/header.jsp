<%@page import="logic.model.enums.PageId"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>
<%@page import="logic.controller.MainController"%>
<%@page import="logic.controller.Controller"%>
<%@page import="logic.model.exception.SystemException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!
	MainController mainController = new MainController();
	Controller controller;

	String pageName = "home.jsp";
	String nextPageName = "";
	
	PageId nextAction = PageId.HOME;
	PageId nextPage;
	PageId btn;
%>
<%
	if (session.getAttribute("mainController") != null)
		mainController = (MainController) session.getAttribute("mainController");
	else
		session.setAttribute("mainController", mainController);
	
	if (session.getAttribute("btn") == null) 
		session.setAttribute("btn", PageId.HOME);
	else 
		btn = (PageId) session.getAttribute("btn");	
	
	if (session.getAttribute("controller") == null) {
		controller = mainController.executeAction(mainController.getNextPageId());
		session.setAttribute("controller", controller);
	}
	else {
		controller = (Controller) session.getAttribute("controller");
	}
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
					<div class="col-10">
						<ul class="nav navbar background-orange">
							<li>
								<button type="submit" name="HOME" value="HOME" class="btn btn-primary btn-nav" ${ sessionScope.btn eq PageId.HOME  ? 'style="background-color: #f69155"' : ''}>Home</button>
							<li>
								<button type="submit" name="VIEW_INFO" value="VIEW_INFO" class="btn btn-primary btn-nav" ${ sessionScope.btn eq PageId.VIEW_INFO  ? 'style="background-color: #f69155"' : ''}>Search path e view info</button>
							<li>
								<button type="submit" name="ADD_PATH" value="ADD_PATH" class="btn btn-primary btn-nav" ${ sessionScope.btn eq PageId.ADD_PATH  ? 'style="background-color: #f69155"' : ''}>Add new path</button>
							<li>
								<button class="btn btn-primary btn-nav" disabled>Schedule path visit</button>
							<li>
								<button class="btn btn-primary btn-nav" disabled>Manage visited paths</button>
							<li>
								<button class="btn btn-primary btn-nav" disabled>Manage your business</button>
							</li>
						</ul>
					</div>
					<div class="col-2">
						<ul class="nav navbar background-orange">
							<li>
								<button type="submit" name="PROFILE" value="PROFILE" class="btn btn-primary btn-nav" style="float: right;" ${ sessionScope.btn eq PageId.PROFILE  ? 'style="background-color: #f69155"' : ''}>
									<img src="Images/icons8-male-user-64.png" style="max-height: 25%; max-width: 30%;">
								</button>
							<li>
						</ul>
					</div>
				</div>
			</form>
		</div>
		
		<%
		for (PageId action : PageId.values()) {
			if (request.getParameter(action.toString()) != null && request.getAttribute("new") == null) {
				session.setAttribute("btn", action);
				nextPage = action;
				nextAction = mainController.onActionRequired(action, (Integer) session.getAttribute("sessionId"));
				session.setAttribute("controller", controller.executeAction(nextAction));
				request.setAttribute("new", false);
				break;
			}
		}
		
			
		if (nextPage != null) {
			switch(nextAction) {
				case LOGIN:
					pageName = "login.jsp";
					break;
				case VIEW_INFO:
					pageName = "searchPath.jsp";
					break;
				case ADD_PATH:
					pageName = "addNewPath.jsp";
					break;
				case PROFILE:
					pageName = "profile.jsp";
					break;
				case HOME:
					pageName = "home.jsp";
					break;
				default:
					pageName = null;
					break;
			}
		
			switch(nextPage) {
				case LOGIN:
					nextPageName = "login.jsp";
					break;
				case VIEW_INFO:
					nextPageName = "searchPath.jsp";
					break;
				case ADD_PATH:
					nextPageName = "addNewPath.jsp";
					break;
				case PROFILE:
					nextPageName = "profile.jsp";
					break;
				case HOME:
					nextPageName = "home.jsp";
					break;
				default:
					nextPageName = "";
					break;
			}
		
			%>
			<jsp:forward page="<%=pageName%>">
				<jsp:param name="nextPageId" value="<%=nextPageName%>"/>
				<jsp:param name="nextPage" value="<%=nextPage%>"/>
			</jsp:forward>
			<%
		}
		%>
		
	<!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
	
	</body>
	
</html>
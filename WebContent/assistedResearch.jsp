<%@page import="logic.bean.SimpleMountainPathBean"%>
<%@page import="logic.controller.ViewMountainPathInfoController"%>
<%@page import="logic.model.exception.SystemException"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- dichiarazione e instanziazione di una MountainPathBean !-->
<jsp:useBean id="wishMountainPath" scope="request" class="logic.bean.MountainPathBean"/>
<!-- mappa attributi della bean sui campi del form -->
<jsp:setProperty name="wishMountainPath" property="*"/>


<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta charset="UTF-8">
		
		<!-- Bootstrap CSS -->
    	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    	
		<%@ include file="header.jsp" %>
		
    	<!-- import our CSS for body of the page -->
    	<link rel="stylesheet" href="body.css" type="text/css"/>
	</head>
	
	<%!
	ViewMountainPathInfoController viewInfoController;
	String next = "";

	private String getNextPageName() {
		switch(controller.getNextPageId()) {
		case SEARCH: 
			next = "searchPath.jsp";
			break;
		case ADD_PATH: 
			next = "addNewPath.jsp";
			break;	
		case VIEW_INFO: 
			next = "viewMountainPathInfo.jsp";
			break;		
		case LOGIN:
			next = "login.jsp";
			break;
		case ASSISTED_RESEARCH:
			next = "assistedResearch.jsp";
			break;
		case PROFILE:
			next = "profile.jsp";
			break;	
		case ADD_REVIEW:
			next = "addReview.jsp";
			break;
		case VIEW_REVIEWS:
			next = "viewReviews.jsp";
			break;
		default:
			next = "";
			break;
		}
		return next;
	}
%>
<%
	viewInfoController = (ViewMountainPathInfoController) session.getAttribute("controller");
%>
	
	<body>
		<div class="row fill">
			<div class="col-3 background-orange">
				<div class="container">
					<div class="row mx-auto" style="padding-top: 40%; padding-left: 2%; padding-right: 2%">
						<div class="col" align="center" style="font-size: 150%;">
							Enter your preferences and let us help you find the perfect mountain path for you!
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-9">
				<div class="container" style="padding-top: 3%">
					<%
					if (request.getParameter("assResearch") != null) {
						try {
							List<SimpleMountainPathBean> results = viewInfoController.searchMountainPathByAssistedResearch(wishMountainPath);
						%>
						<jsp:forward page="<%=getNextPageName()%>">
							<jsp:param name="controller" value="<%=controller%>"/>
							<jsp:param name="assistedResearchResults" value="<%=results%>"/>
						</jsp:forward>
						<%
						} catch (SystemException e) {
							%>
							<div class="container" style="padding-top: 3%;">
								<div class="alert alert-danger alert-dismissible fade show" role="alert">
									<strong>System error</strong>Ops, there was a system error. Retry later.
								  	<a href="#" class="close" style="float: right;" data-dismiss="alert" aria-label="close">&times;</a>
								</div>
							</div>
							<%
						}
					}	
					%>
				
					<form class="form-inline" action="assistedResearch.jsp" method="post">
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="col-3 black-text">Location</div>
							<div class="col-3"><input type="text" class="form-control" id="region" name="region" placeholder="Region"></div>
							<div class="col-3"><input type="text" class="form-control" name="province" placeholder="Province"></div>
							<div class="col-3"><input type="text" class="form-control" name="city" placeholder="City"></div>
						</div>
						
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="d-flex">
								<div class="col-3 black-text">Difficulty level</div>
								<div class="form-check form-check-inline">
									<input name="level" type="radio" id="T" value="T">
							    	<label>T</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="level" type="radio" id="E" value="E">
							    	<label>E</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="level" type="radio" id="EE" value="EE">
							    	<label>EE</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="level" type="radio" id="EEA" value="EEA">
							    	<label>EEA</label>
								</div>
							</div>
						</div>
						
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="d-flex">
								<div class="col-3 black-text">Type of lanscape</div>
								<div class="form-check form-check-inline">
									<input name="landscape" type="checkbox" id="MOUNTAIN" value="MOUNTAIN">
							    	<label>Mountain</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="landscape" type="checkbox" id="LAKE" value="LAKE">
							    	<label>Lake</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="landscape" type="checkbox" id="SEA" value="SEA">
							    	<label>Sea</label>
								</div>
							</div>
						</div>
						
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="d-flex">
								<div class="col-3 black-text">Type of ground</div>
								<div class="form-check form-check-inline">
									<input name="ground" type="checkbox" id="ROCK" value="ROCK">
							    	<label>Rock</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="ground" type="checkbox" id="GRASS" value="GRASS">
							    	<label>Grass</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="ground" type="checkbox" id="WOOD" value="WOOD">
							    	<label>Wood</label>
								</div>
							</div>
						</div>
						
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="d-flex">
								<div class="col-3 black-text">Cycle path</div>
								<div class="form-check form-check-inline">
									<input name="cycleble" type="radio" id="true" value="true">
							    	<label>Yes</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="cycleble" type="radio" id="false" value="false">
							    	<label>No</label>
								</div>
							</div>
						</div>
						
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="d-flex">
								<div class="col-3 black-text">Presence of historycal elements</div>
								<div class="form-check form-check-inline">
									<input name="historicalElements" type="radio" id="true" value="true">
							    	<label>Yes</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="historicalElements" type="radio" id="false" value="false">
							    	<label>No</label>
								</div>
							</div>
						</div>
						
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="d-flex">
								<div class="col-3 black-text">Family suitable</div>
								<div class="form-check form-check-inline">
									<input name="familySuitable" type="radio" id="true" value="true">
							    	<label>Yes</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="familySuitable" type="radio" id="false" value="false">
							    	<label>No</label>
								</div>
							</div>
						</div>
						
						<div class="container" style="text-align: center;">
							<button type="submit" name="assResearch" value="assResearch" class="btn btn-light-orange">Search</button>
						</div>
						
					</form>
				</div>
				
			</div>
		
		</div>
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
	
	</body>
</html>
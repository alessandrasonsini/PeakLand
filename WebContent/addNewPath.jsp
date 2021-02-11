<%@page import="logic.controller.AddNewMountainPathController"%>
<%@page import="java.util.Collection"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="logic.model.enums.DifficultyLevelEnum" %>
<%@page import="logic.model.enums.GroundEnum" %>
<%@page import="logic.model.enums.LandscapeEnum" %>


<%@page import="org.apache.commons.fileupload.FileItem" %>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>

<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!
	AddNewMountainPathController controller = new AddNewMountainPathController();

	boolean disable = true;
	boolean disableAddReview = true;
	String nextPageName = "";
%>
<jsp:useBean id="newPath" scope="request" class="logic.bean.MountainPathBean"/>
<jsp:setProperty name="newPath" property="*"/>
<%
	switch(controller.getNextPageId()) {
		case "Search path": 
			nextPageName = "searchPath.jsp";
			break;
		case "Add path": 
			nextPageName = "addNewPath.jsp";
			break;	
		case "View info": 
			nextPageName = "viewMountainPathInfo.jsp";
			break;		
		case "Login":
			nextPageName = "login.jsp";
			break;
		case "Assisted research":
			nextPageName = "assistedResearch.jsp";
			break;
		case "Profile":
			nextPageName = "profile.jsp";
			break;	
		case "Add review":
			nextPageName = "addReview.jsp";
			break;
		case "View reviews":
			nextPageName = "viewReviews.jsp";
			break;
	}

	session.setAttribute("addPathController", controller);
	session.setAttribute("disable", disable);
	if (session.getAttribute("disableAddReview") == null)
			session.setAttribute("disableAddReview", disableAddReview);
	else
		disableAddReview = (boolean) session.getAttribute("disableAddReview");


	if (request.getParameter("addReview") != null) {
		controller.addReviewRequest();
		if (nextPageName != "") {
		%>
		<jsp:forward page="<%=nextPageName%>"/>
		<% 
		}
	}
%>

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
	<body>
		<div class="row fill">
			<div class="col-3 background-orange">
				<div class="container">
					<div class="row mx-auto" style="padding-top: 30%; padding-left: 10%; padding-right: 10%">
						<div class="col" align="center">A mountain path you know is not in the system yet?</div>
					</div>
					<div class="row mx-auto" style="padding-top: 25%; padding-left: 10%; padding-right: 10%">
						<div class="col" align="center">Add it and its information to earn awesome badges</div>
					</div>
					<div class="row mx-auto" style="padding-top: 35%; padding-left: 2%; padding-right: 2%">
						<div class="col" align="center" style="font-size: 150%;">Help the community to grow!</div>
					</div>
				</div>
			</div>
			
			<div class="col-9">
				<%
				if (request.getParameter("name") != null) {
					if (controller.checkName(request.getParameter("name"))) {
						disable = false;
						session.setAttribute("disable", disable);
						session.setAttribute("name", request.getParameter("name"));
					}
					else {
						disable = true;
						session.setAttribute("disable", disable);
						%>
						<div class="container" style="padding-top: 3%;">
							<div class="alert alert-danger alert-dismissible fade show" role="alert">
								<strong>Add new path error!</strong> Mountain path with entered name already exists.
							  	<a href="#" class="close" style="float: right;" data-dismiss="alert" aria-label="close">&times;</a>
							</div>
						</div>
						<%
					}
				}
				
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				if (isMultipart) {
					List<Part> partList = (List<Part>) request.getParts();
					List<InputStream> streamList = new ArrayList<>();
					
					for (Part part : partList) {
						if (part.getName().equals("pathPhoto") ) {
							System.out.println("filename   "+part.getSubmittedFileName());
							if (part.getSubmittedFileName() != "") {
								try {
							    	InputStream fileContent = part.getInputStream();
							    	streamList.add(fileContent);
								} catch(Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					controller.setMountainPathImages(streamList);
				    controller.saveNewMountainPath(newPath, (Integer)session.getAttribute("sessionId"));
					
					%>
					<div class="container" style="padding-top: 3%;">
						<div class="alert alert-success alert-dismissible fade show" role="alert">
							<strong>New path added successfully!</strong> Now you can add a review.
						  	<a href="#" class="close" style="float: right;" data-dismiss="alert" aria-label="close">&times;</a>
						</div>
					</div>
					<%
					disableAddReview = false;
					session.setAttribute("disableAddReview", disableAddReview);
				}
				%>
				
				<!-- form to insert new path info -->
				<div class="container" style="padding-top: 3%">
					<form class="form-inline" action="addNewPath.jsp" method="post">
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="col-3 black-text">Mountain path name</div>
							<div class="col-3"><input type="text" class="form-control" name="name" value=${ sessionScope.name eq null  ? '' : sessionScope.name}></div>
							<div class="col-2"><button type="submit" class="btn btn-light">Verify</button></div>
						</div>
					</form>
					
					<form class="form-inline" action="addNewPath.jsp" method="post"  enctype="multipart/form-data">
					
						<input type='hidden' name='name' id='name' value="${sessionScope.name}"/>
					
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="col-3 black-text">Altitude</div>
							<div class="col-3"><input type="text" class="form-control" name="altitude" placeholder="in meters" ${ sessionScope.disable eq true  ? 'disabled' : ''}></div>
						</div>
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="col-3 black-text">Location</div>
							<div class="col-3"><input type="text" class="form-control" name="region" placeholder="Region" ${ sessionScope.disable eq true  ? 'disabled' : ''}></div>
							<div class="col-3"><input type="text" class="form-control" name="province" placeholder="Province" ${ sessionScope.disable eq true  ? 'disabled' : ''}></div>
							<div class="col-3"><input type="text" class="form-control" name="city" placeholder="City" ${ sessionScope.disable eq true  ? 'disabled' : ''}></div>
						</div>
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="col-3 black-text">Lenght</div>
							<div class="col-3"><input type="text" class="form-control" name="lenght" placeholder="in kilometers" ${ sessionScope.disable eq true  ? 'disabled' : ''}></div>
						</div>
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="d-flex">
								<div class="col-3 black-text">Difficulty level</div>
								<div class="form-check form-check-inline">
									<input name="level" type="radio" id="T" value="T" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							    	<label>T</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="level" type="radio" id="E" value="E" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							    	<label>E</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="level" type="radio" id="EE" value="EE" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							    	<label>EE</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="level" type="radio" id="EEA" value="EEA" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							    	<label>EEA</label>
								</div>
							</div>
						</div>
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="d-flex">
								<div class="col-3 black-text">Type of lanscape</div>
								<div class="form-check form-check-inline">
									<input name="landscape" type="checkbox" id="MOUNTAIN" value="MOUNTAIN" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							    	<label>Mountain</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="landscape" type="checkbox" id="LAKE" value="LAKE" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							    	<label>Lake</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="landscape" type="checkbox" id="SEA" value="SEA" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							    	<label>Sea</label>
								</div>
							</div>
						</div>
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="d-flex">
								<div class="col-3 black-text">Type of ground</div>
								<div class="form-check form-check-inline">
									<input name="ground" type="checkbox" id="ROCK" value="ROCK" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							    	<label>Rock</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="ground" type="checkbox" id="GRASS" value="GRASS" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							    	<label>Grass</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="ground" type="checkbox" id="WOOD" value="WOOD" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							    	<label>Wood</label>
								</div>
							</div>
						</div>
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="d-flex">
								<div class="col-3 black-text">Cycle path</div>
								<div class="form-check form-check-inline">
									<input name="cycleble" type="radio" id="true" value="true" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							    	<label>Yes</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="cycleble" type="radio" id="false" value="false" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							    	<label>No</label>
								</div>
							</div>
						</div>
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="d-flex">
								<div class="col-3 black-text">Presence of historycal elements</div>
								<div class="form-check form-check-inline">
									<input name="historicalElements" type="radio" id="true" value="true" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							    	<label>Yes</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="historicalElements" type="radio" id="false" value="false" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							    	<label>No</label>
								</div>
							</div>
						</div>
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="d-flex">
								<div class="col-3 black-text">Family suitable</div>
								<div class="form-check form-check-inline">
									<input name="familySuitable" type="radio" id="true" value="true" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							    	<label>Yes</label>
								</div>
								<div class="form-check form-check-inline">
									<input name="familySuitable" type="radio" id="false" value="false" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							    	<label>No</label>
								</div>
							</div>
						</div>
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="col-3 black-text">Travel time</div>
							<div class="col-3"><input type="text" class="form-control" name="hours" placeholder="hours" ${ sessionScope.disable eq true  ? 'disabled' : ''}></div>
							<div class="col-3"><input type="text" class="form-control" name="minutes" placeholder="minutes" ${ sessionScope.disable eq true  ? 'disabled' : ''}></div>
						</div>
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="d-flex">
								<div class="col-3 black-text">Image of the path</div>
								<input type="file" name="pathPhoto" value="pathPhoto" multiple="multiple"/>
							</div>
						</div>
						<div class="container" style="text-align: center;">
							<input type="submit" name="savePath" value="Save path" class="btn btn-light-orange" ${ sessionScope.disable eq true  ? 'disabled' : ''}/>
							<input type="submit" name="addReview" value="Add Review" class="btn btn-light-orange" ${ sessionScope.disableAddReview eq true  ? 'disabled' : ''}/>
						</div>
					</form>
					<br>
				</div>
			</div>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>
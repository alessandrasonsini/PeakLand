<%@page import="logic.controller.AddNewMountainPathController"%>
<%@page import="logic.model.enums.DifficultyLevelEnum" %>
<%@page import="logic.model.enums.GroundEnum" %>
<%@page import="logic.model.enums.LandscapeEnum" %>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!
	AddNewMountainPathController controller = new AddNewMountainPathController();
	boolean disable = true;
%>
<%
	session.setAttribute("controller", controller);
	session.setAttribute("disable", disable);

	session.removeAttribute("name");
%>

<!-- dichiarazione e instanziazione di una MountainPathBean !-->
<jsp:useBean id="newPath" scope="request" class="logic.model.bean.MountainPathBean"/>
<!-- mappa attributi della bean sui campi del form -->
<jsp:setProperty name="newPath" property="*"/>

<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta charset="UTF-8">
		
		<!-- Bootstrap CSS -->
    	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
    	
    	<!-- import our CSS for body of the page -->
    	<link rel="stylesheet" href="body.css" type="text/css"/>
    	
		<%@ include file="header.jsp" %>
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
					
					<form class="form-inline" action="addNewPath.jsp" method="post">
					
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
								<input type="file" name="pathPhoto" value="choose file" ${ sessionScope.disable eq true  ? 'disabled' : ''}/>
							</div>
						</div>
						<div class="container" style="text-align: center;">
							<button type="submit" name="savePath" value="savePath" class="btn btn-save-path" ${ sessionScope.disable eq true  ? 'disabled' : ''}>Save mountain path</button>
						</div>
					</form>
					
					<br><br>
				</div>
			</div>
			
			<%
			if (request.getParameter("savePath") != null) {
				session.removeAttribute("disable");
				controller.saveNewMountainPath(newPath);
				session.removeAttribute("name");
			}
			%>
			
			
			
		</div>
		
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>
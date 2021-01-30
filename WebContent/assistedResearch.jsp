<%@page import="logic.controller.ViewMountainPathInfoController"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	ViewMountainPathInfoController controller = (ViewMountainPathInfoController) session.getAttribute("controller");
%>

<!-- dichiarazione e instanziazione di una MountainPathBean !-->
<jsp:useBean id="wishMountainPath" scope="request" class="logic.model.bean.MountainPathBean"/>
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
					<form class="form-inline" action="assistedResearch.jsp" method="post">
						<div class="row mx-auto" style="padding-bottom: 3%;">
							<div class="col-3 black-text">Location</div>
							<div class="col-3"><input type="text" class="form-control" name="region" placeholder="Region"></div>
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
		
		<%
		if (request.getParameter("assResearch") != null) {
			controller.searchMountainPathByAssistedResearch(wishMountainPath);
			%>
			<jsp:forward page="searchPath.jsp">
				<jsp:param name="controller" value="<%=controller%>" />
			</jsp:forward>
			<%
		}
  				
		%>
		
	</body>
</html>
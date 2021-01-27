<%@page import="logic.model.MountainPath"%>
<%@page import="logic.model.bean.MountainPathBean" %>
<%@page import="logic.controller.ViewMountainPathInfoController"%>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	ViewMountainPathInfoController controller = (ViewMountainPathInfoController) session.getAttribute("controller");
	MountainPathBean path = controller.getSelectedMountainPath();
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
				<!-- text and assisted research button -->				
				<%
				if (path != null) {
					%>
					
					<div class="container">
						<div class="row mx-auto" style="padding-top: 30%; padding-left: 5%; padding-right: 5%">
							<div class="col" align="center">
								<span class="path-name-font"><%=path.getName()%></span>
							</div>
						</div>
						<div class="row mx-auto" style="padding-top: 30%;">
							<div class="col-5" align="center">
								<%=path.getRegion()%>
							</div>
							<div class="col" align="center">-</div>
							<div class="col-5" align="center">
								<%=path.getProvince()%>
							</div>
						</div>
						<div class="row mx-auto" style="padding-top: 5%;">
							<div class="col" align="center">
								<%=path.getCity()%>
							</div>
						</div>
					</div>
					<%
				}
				%>
			</div>
				
			<div class="col-9">
				<br>
				<div class="container">
				<div class="row">
					<!-- mountain path info column -->
					<div class="col-6">
						<div class="row">
							<div class="col-2 align-self-end"><img src="Images/info.png" class="img-responsive icons"></div>
							<div class="col-10 align-self-end">
								<p class="pull-left text">About the mountan path</p>
							</div>
						</div>
						<br>
						<div class="row">
							<p class="green-text">Altitude:&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.convertToText(path.getAltitude()) %></span></p>
						</div>
						<div class="row">
							<p class="green-text">Lenght:&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.convertToText(path.getLenght())%></span></p>
						</div>
						<div class="row">
							<p class="green-text">Level&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.convertToText(path.getLevel())%></span></p>
						</div>
						<div class="row">
							<p class="green-text">Landscape:&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.convertToText(path.getLandscape())%></span></p>
						</div>
						<div class="row">
							<p class="green-text">Ground:&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.convertToText(path.getGround())%></span></p>
						</div>
						<div class="row">
							<p class="green-text">Cycleable:&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.convertToText(path.isCycleble())%></span></p>
						</div>
						<div class="row">
							<p class="green-text">Presence of historical elements:&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.convertToText(path.isHistoricalElements())%></span></p>
						</div>
						<div class="row">
							<p class="green-text">Family suitable:&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.convertToText(path.isFamilySuitable())%></span></p>
						</div>
						<div class="row">
							<p class="green-text">Travel Time:&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.convertToText(path.getHours())%> : <%=path.convertToText(path.getMinutes())%></span></p>
						</div>
					</div>
					
					<!-- img and rates column -->
					<div class="col-6">
						<div class="row">
							<div class="col-2 align-self-end"><img src="Images/camera.png" class="img-responsive icons"></div>
							<div class="col-10 align-self-end">
								<p class="pull-left text">Photos</p>
							</div>
						</div>
						<div class="row">
							<div class="col-2"><img src="Images/right-arrow.png" class="img-responsive arrows rotate-180"></div>
							<div class="col-4"><img src="Images/mountain_path.png" class="img-responsive photos"></div>
							<div class="col-2"><img src="Images/right-arrow.png" class="img-responsive arrows"></div>
						</div>
						
						<br><br><br><br><br><br><br><br>
						
						<div class="row">
							<div class="col-2 align-self-end"><img src="Images/filled-like.png" class="img-responsive icons"></div>
							<div class="col-10 align-self-end">
								<div class="row">
									<div class="col"><div class="text" style="transform: translateY(-5%);">Vote:</div></div>
									
									<%
									switch (path.convertToText(path.getVote())) {
										case "5":
											%>
											<div class="col-2" style="transform: translateY(-40%);">
												<img src="Images/star.png" class="img-responsive stars">
											</div>
											<%	
										case "4":
											%>
											<div class="col-2" style="transform: translateY(-40%);">
												<img src="Images/star.png" class="img-responsive stars">
											</div>
											<%	
										case "3":
											%>
											<div class="col-2" style="transform: translateY(-40%);">
												<img src="Images/star.png" class="img-responsive stars">
											</div>
											<%
										case "2":
											%>
											<div class="col-2" style="transform: translateY(-40%);">
												<img src="Images/star.png" class="img-responsive stars">
											</div>
											<%
										case "1":
											%>
											<div class="col-2" style="transform: translateY(-40%);">
												<img src="Images/star.png" class="img-responsive stars">
											</div>
											<%
											break;
										default:
											%>
											<%=path.convertToText(path.getVote()) %>
											<%
									}
									%>
									<br>
								</div>
							</div>
						</div>
						<br>
						<div class="row mx-auto">
							<p class="green-text">Number of votes:&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.convertToText(path.getNumberOfVotes())%></span></p>
						</div>
						
					</div>
					<div class="row mx-auto">
						<div class="col-1"></div>
						<div class="col-2">
							<img src="Images/google-maps.png" class="img-responsive link">
							<div class="black-text" style="text-align: center">View path on Google Maps</div>
						</div>
						<div class="col-1"></div>
						<div class="col-2">
							<img src="Images/apple-weather.png" class="img-responsive link">
							<div class="black-text" style="text-align: center">View weather forecast</div>
						</div>
						<div class="col-1"></div>
						<div class="col-2">
							<img src="Images/list.png" class="img-responsive link">
							<div class="black-text" style="text-align: center">View reviews</div>
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
		
	<!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
	
	</body>
	
</html>
<%@page import="logic.model.bean.ReviewBean"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Base64"%>
<%@page import="java.util.Base64.Encoder"%>
<%@page import="logic.model.MountainPath"%>
<%@page import="logic.model.bean.MountainPathBean" %>
<%@page import="logic.controller.ViewMountainPathInfoController"%>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!
	ViewMountainPathInfoController controller = new ViewMountainPathInfoController();
	ArrayList<String> base64 = new ArrayList<>();
	int currImgNum = -1;
	boolean disablePrev = true;
	boolean disableNext = true;
%>
<jsp:useBean id="review" scope="request" class="logic.model.bean.ReviewBean"/>
<%
	session.setAttribute("disablePrev", disablePrev);
	session.setAttribute("disableNext", disableNext);

	if (session.getAttribute("viewInfoController") != null)
		controller = (ViewMountainPathInfoController) session.getAttribute("viewInfoController");
	MountainPathBean path = controller.getSelectedMountainPath();
	List<ReviewBean> list = controller.getPathReview(path.getName());
	
	if (controller.getImageStreams() != null) {
		if (base64.isEmpty()) {
			List<ByteArrayInputStream> streams = controller.getImageStreams();
			for(ByteArrayInputStream stream : streams) {
				byte[] bytes = IOUtils.toByteArray(stream);
				base64.add(Base64.getEncoder().encodeToString(bytes));
			}
			
			if (base64.size() > 1)
				disableNext = false;
			currImgNum++;
		}
	}
	
	if (request.getParameter("prev") != null) {
		currImgNum = (int) session.getAttribute("currImgNum");
		currImgNum--;
		if (currImgNum == 0)
			disablePrev = true;
		if (base64.size() > 1)
			disableNext = false;
		else
			disableNext = true;
	}
	if (request.getParameter("next") != null) {
		currImgNum = (int) session.getAttribute("currImgNum");
		currImgNum++;
		disablePrev = false;
		if (currImgNum == (base64.size() -1) )
			disableNext = true;
	}
	if (request.getParameter("viewReviews") != null) {
		%>
		<jsp:forward page="viewReviews.jsp">
			<jsp:param name="pathName" value="<%=path.getName()%>"/>
		</jsp:forward>
		<%
	}
	
	session.setAttribute("disablePrev", disablePrev);
	session.setAttribute("disableNext", disableNext);
	session.setAttribute("currImgNum", currImgNum);
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
						
						<div class="d-flex justify-content-between align-items-center" style="padding-top: 15%;">
							<div class="p-2 flex-item-photos">
								<% if (!base64.isEmpty()) { %>
									<img src="data:image/png;base64,<%=base64.get(0)%>" class="img-responsive photo">
								<% } else { %>
									<img src="Images/mountain_path.png" class="img-responsive photo">
								<% } %>
							</div>
						</div>
					</div>
					<%
				}
				%>
			</div>
			
			<div class="col-9">
				<div class="container">
					<div class="row">
						<div class="col-sm">
							<br>
							<div class="d-flex justify-content-start align-items-center">
								<div class="p-2 flex-item-icon">
									<img src="Images/info.png" class="img-responsive icons">
								</div>
  								<div class="p-2 flex-item-text pull-left text">About the mountan path</div>
							</div>
							
							<br>
							
							<div class="container">
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
						
							<br>
						
							<div class="d-flex justify-content-start align-items-center">
								<div class="p-2 flex-item-icon">
									<img src="Images/list.png" class="img-responsive icons">
								</div>
	 							<div class="p-2 flex-item-text pull-left text">Reviews</div>
							</div>
							
							<% if (review != null) { %>
							<div class="container">
								<div class="row">
									<p class="green-text">Author:&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=review.getAuthor() %></span></p>
								</div>
								<div class="row">
									<p class="green-text">Vote:&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=review.getVote() %></span></p>
								</div>
								<div class="row">
									<p class="green-text">Title:&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=review.getTitle() %></span></p>
								</div>
								<div class="row">
									<p class="green-text">Comment:&nbsp;&nbsp;<span class="black-text"><%=review.getComment() %></span></p>
								</div>
								<div class="row">
									<form class="form-inline" action="viewMountainPathInfo.jsp" method="post">
										<button type="submit" name="viewReviews" value="viewReviews" class="btn btn-light-orange">View all reviews</button>
									</form>
								</div>
							</div>
							<%} else { %>
							<div class="container">
								<div class="row">Not available</div>
							</div>
							<% } %>
							<br>
						</div>
						
						<div class="col-sm">
							
							<br>
							
							<div class="container">
								<div class="d-flex justify-content-between align-items-center">
										<div class="p-2 flex-item-link">
											<img src="Images/google-maps.png" class="img-responsive">
										</div>
										<div class="p-2 flex-item-link">
											<img src="Images/apple-weather.png" class="img-responsive">
										</div>
										<div class="p-2 flex-item-link">
											<img src="Images/list.png" class="img-responsive">
										</div>
								</div>
								<div class="row">
									<div class="col-4">
										<div class="black-text" style="text-align: center;">View path on Google Maps</div>
									</div>
									<div class="col-4">
										<div class="black-text" style="text-align: center;">View weather forecast</div>
									</div>
									<div class="col-4">
										<div class="black-text" style="text-align: center;">View reviews</div>
									</div>
								</div>
							</div>
						
							<br><br>
							
							<div class="d-flex justify-content-start align-items-center">
								<div class="p-2 flex-item-icon">
									<img src="Images/camera.png" class="img-responsive icons">
								</div>
  								<div class="p-2 flex-item-text pull-left text">Photos</div>
							</div>
							
							<form class="form-inline" action="viewMountainPathInfo.jsp" method="post">
								<div class="d-flex justify-content-between align-items-center">
									<div class="p-2 flex-item-arrow">
										<input type="image" name="prev" value="prev" src="Images/right-arrow.png" class="img-responsive rotate-180" ${ sessionScope.disablePrev eq true  ? 'style="display: none;"' : ''}>
									</div>
									<div class="p-2 flex-item-photos w-50 h-50">
										<% if (!base64.isEmpty()) { %>
											<img src="data:image/png;base64,<%=base64.get((int)session.getAttribute("currImgNum"))%>" class="img-responsive photo">
										<% } else { %>
											<img src="Images/mountain_path.png" class="img-responsive photo">
										<% } %>
									</div>
									<div class="p-2 flex-item-arrow">
										<input type="image" name="next" value="next" src="Images/right-arrow.png" class="img-responsive" ${ sessionScope.disableNext eq true  ? 'style="display: none;"' : ''}>
									</div>
								</div>
							</form>
							
							<br><br>
							
							<div class="d-flex justify-content-start align-items-center">
								<div class="p-2 flex-item-icon">
									<img src="Images/filled-like.png" class="img-responsive icons">
								</div>
  								<div class="p-2 flex-item-text pull-left text">Vote</div>
  								
  								<%	if (path.getVote() != 0) {
	  									for(int i = 0; i < path.getVote(); i++) {
	  									%>
		  								<div class="p-2 flex-item-stars">
											<img src="Images/star.png" class="img-responsive icons">
										</div>
										<%
	  									}
  									}
  									else {
  										%>
  										<div class="p-2 black-text"><%=path.convertToText(path.getVote())%></div>
  										<%
  									}
								%>
								
							</div>
							
							<div class="container" style="padding-left: 5%;">
								<div class="row">
									<p class="green-text">Number of votes:&nbsp;
										<span class="black-text"><%=path.convertToText(path.getNumberOfVotes())%></span>
									</p>
								</div>
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
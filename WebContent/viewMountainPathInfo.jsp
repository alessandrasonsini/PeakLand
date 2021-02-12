<%@page import="logic.bean.ReviewBean"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Base64"%>
<%@page import="java.util.Base64.Encoder"%>
<%@page import="logic.model.MountainPath"%>
<%@page import="logic.bean.MountainPathBean" %>
<%@page import="logic.controller.ViewMountainPathInfoController"%>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

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
	ArrayList<String> base64 = new ArrayList<>();
	int currImgNum = -1;
	boolean disablePrev = true;
	boolean disableNext = true;
	String next;
	
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
<jsp:useBean id="review" scope="request" class="logic.bean.ReviewBean"/>
<%
	session.setAttribute("disablePrev", disablePrev);
	session.setAttribute("disableNext", disableNext);

	viewInfoController = (ViewMountainPathInfoController) session.getAttribute("controller");
	MountainPathBean path = viewInfoController.getSelectedMountainPath();
	List<ReviewBean> list = viewInfoController.getPathReview(path.getName());
	
	if (viewInfoController.getImageStreams() != null) {
		if (base64.isEmpty()) {
			List<ByteArrayInputStream> streams = viewInfoController.getImageStreams();
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
		viewInfoController.viewReviewsRequest();
		%>
		<jsp:forward page="<%=getNextPageName()%>">
			<jsp:param name="pathName" value="<%=path.getName()%>"/>
		</jsp:forward>
		<%
	}
	
	session.setAttribute("disablePrev", disablePrev);
	session.setAttribute("disableNext", disableNext);
	session.setAttribute("currImgNum", currImgNum);
%>
	
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
								<%=path.getRegionAsText()%>
							</div>
							<div class="col" align="center">-</div>
							<div class="col-5" align="center">
								<%=path.getProvinceAsText()%>
							</div>
						</div>
						<div class="row mx-auto" style="padding-top: 5%;">
							<div class="col" align="center">
								<%=path.getCityAsText()%>
							</div>
						</div>
						
						<div class="d-flex justify-content-center align-items-center" style="padding-top: 15%;">
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
  								<div class="p-2 flex-item-text pull-left text">About the mountain path</div>
							</div>
							
							<br>
							
							<div class="container">
								<div class="row">
									<p class="green-text">Altitude&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.getAltitudeAsText() %></span></p>
								</div>
								<div class="row">
									<p class="green-text">Lenght&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.getLenghtAsText()%></span></p>
								</div>
								<div class="row">
									<p class="green-text">Level&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.getLevelAsText()%></span></p>
								</div>
								<div class="row">
									<p class="green-text">Landscape&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.getLandscapeAsText()%></span></p>
								</div>
								<div class="row">
									<p class="green-text">Ground&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.getGroundAsText()%></span></p>
								</div>
								<div class="row">
									<p class="green-text">Cycleable&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.getCyclableAsText()%></span></p>
								</div>
								<div class="row">
									<p class="green-text">Presence of historical elements&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.getHistoricalElementsAsText()%></span></p>
								</div>
								<div class="row">
									<p class="green-text">Family suitable&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text"><%=path.getFamilySuitableAsText()%></span></p>
								</div>
								<div class="row">
									<p class="green-text">Travel Time&nbsp;&nbsp;&nbsp;&nbsp;<span class="black-text">
										<%=path.getHoursAsText()%> : <%=path.getMinutesAsText()%>
									</span></p>
								</div>
							</div>
							<br>
						</div>
						
						<div class="col-sm">
							
							<br>
							
							<div class="container">
								<form class="form" action="viewMountainPathInfo.jsp" method="post">
									<div class="d-flex justify-content-between align-items-center">
										<div class="p-2 flex-item-link">
											<img src="Images/google-maps.png" class="img-responsive">
										</div>
										<div class="p-2 flex-item-link">
											<img src="Images/apple-weather.png" class="img-responsive">
										</div>
										<div class="p-2 flex-item-link">
											<input type="image" name="viewReviews" value="viewReviews" src="Images/list.png" class="img-responsive">
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
								</form>
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
  										<div class="p-2 black-text"><%=path.getVoteAsText()%></div>
  										<%
  									}
								%>
								
							</div>
							
							<div class="container" style="padding-left: 5%;">
								<div class="row">
									<p class="green-text">Number of votes&nbsp;
										<span class="black-text"><%=path.getNumberOfVotesAsText()%></span>
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
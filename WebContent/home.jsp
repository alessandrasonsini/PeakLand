<%@page import="logic.bean.SimpleMountainPathBean"%>
<%@page import="logic.controller.HomeController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:useBean id="user" scope="request" class="logic.bean.LoggedUserBean"/>

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
		HomeController homeController;
		List<SimpleMountainPathBean> topTen;
		String userName;
	%>
	<%
		homeController = (HomeController) session.getAttribute("controller");
		topTen = homeController.getClassification((Integer)session.getAttribute("sessionId"));
		userName = homeController.getCurrentUserName();
		session.setAttribute("userName", userName);
	%>
	<body>
		<div class="row fill">
			<div class="col-3 background-orange">
				<%
				if (session.getAttribute("userName") == null) {
					%>
					<div class="container">
						<div class="row mx-auto" style="padding-top: 25%; padding-left: 20%; padding-right: 20%">
							<div class="col" align="center" style="font-size: 160%;">Welcome on PeakLand!</div>
						</div>
						<div class="row mx-auto" style="padding-top: 30%; padding-left: 2%; padding-right: 2%">
							<div class="col" align="center" style="font-size: 150%;">
								Join our community to discover all the most beautiful mountain paths in Italy, plan trips with your friends and invite them through our platform! 
							</div>
						</div>
					</div>
					<%
				} else {
					%>
					<div class="container">
						<div class="row mx-auto" style="padding-top: 30%; padding-left: 20%; padding-right: 20%">
							<div class="col" align="center" style="font-size: 160%;">Welcome back <%=/*=user.getName()*/(String)session.getAttribute("userName")%>!</div>
						</div>
						<div class="row mx-auto" style="padding-top: 40%; padding-left: 2%; padding-right: 2%">
							<div class="col" align="center" style="font-size: 150%;">
								Are you ready for new adventures? 
							</div>
						</div>
					</div>
					<%
				}
				%>
			</div>
			
			<div class="col-9">
				<%
				if (topTen != null && !topTen.isEmpty()) {
					%>
					<form class="form" action="home.jsp" method="post">
						<div class="container" style="padding-top: 1%;">
							<div class="row">
								<div class="col-6">
									<div class="d-flex justify-content-start align-items-center">
										<div class="p-2 flex-item-icon">
											<img src="Images/leaderboard.png" class="img-responsive icons">
										</div>
										<% if(session.getAttribute("userName") == null){ %>
										<div class="p-2 flex-item-text pull-left text">Top ten on PeakLand</div>
										<% } else { %>
										<div class="p-2 flex-item-text pull-left text">Suggested for you based on your favourite paths</div>
										<% } %>
									</div>
								</div>
							</div>
						</div>
						<div class="container">
						<%
						for (SimpleMountainPathBean bean : topTen) {
							%>
							<button type="submit" class="btn" name="path" value="<%=bean.getName()%>" style="min-width: 100%;">
								<div class="row card-view-simple-path-no-margin rounded">
									<div class="col-3" style="padding-top: 2%;">
										<!-- inserire recupero immagine dal DB -->
										<div class="row-fill justify-content-center align-items-center">
											<div class="d-flex justify-content-center align-items-center">
												<div class="p-2 flex-item-search-photo">
													<img src="Images/mountain_path.png" class="img-responsive photo">
												</div>
											</div>
										</div>
									</div>
									<div class="col-9">
										<div class="row justify-content-center" style="padding-bottom: 1%; padding-top:1%">
											
											<div class="d-flex justify-content-start align-items-center">
											<div class="ml-auto w-100 p-2 bd-highlight">
												<div class="path-name-font" align="left" style="padding-left: 5%;"><%=bean.convertToText(bean.getRankPosition())%>.  <%=bean.getName()%></div>
											</div>
											<div class="mr-auto p-2 bd-highlight" style="padding-left: 5%;">
												<div class="d-flex justify-content-start align-items-center">
													<div class="bold-text">Vote:</div>
													<%	if (bean.getVote() != 0) {
															for(int i = 0; i < bean.getVote(); i++) {
															%>
															<div class="p-2 flex-item-stars-2">
															<img src="Images/star.png" class="icon-star">
														</div>
														<%
															}
														}
														else {
															%>
															<div class="p-2 black-text text-nowrap"><%=bean.convertToText(bean.getVote())%></div>
															<%
														}
													%>
												</div>
											</div>
											<div class="p-3 bd-highlight text-nowrap">
												<div class="bold-text">Num of votes:</div> <%=bean.convertToText(bean.getNumberOfVotes())%>
											</div>
										</div>
										
										
										</div>
										<div class="row" style="padding-bottom: 2%;">
											<div class="col-4" align="center" style="padding-bottom: 1%;">
												<div class="row justify-content-center bold-text">Location</div>
												<div class="row justify-content-center"><%= bean.convertToText(bean.getRegion()) %></div>
												<div class="row justify-content-center"><%= bean.convertToText(bean.getProvince()) %></div>
												<div class="row justify-content-center"><%= bean.convertToText(bean.getCity()) %></div>
											</div>
											<div class="col-4" align="center">
												<div class="row justify-content-center bold-text">Difficulty level</div>
												<div class="row justify-content-center"><%= bean.convertToText(bean.getLevel()) %></div>
											</div>
											<div class="col-4" align="center">
												<div class="row justify-content-center bold-text">Travel Time</div>
												<div class="row justify-content-center"><%= bean.convertToText(bean.getHours()) %>:<%= bean.convertToText(bean.getMinutes()) %></div>
											</div>
										</div>
									</div>
								</div>
							</button>
							<%
						}
					%></div></form><%
					topTen.clear();
				}
				%>
			</div>
		</div>
		
	
		<!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>
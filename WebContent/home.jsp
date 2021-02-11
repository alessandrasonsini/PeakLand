<%@page import="logic.bean.SimpleMountainPathBean"%>
<%@page import="logic.controller.HomeController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:useBean id="user" scope="request" class="logic.bean.LoggedUserBean"/>

<%!
	HomeController controller = new HomeController();
	List<SimpleMountainPathBean> topTen;
	String userName;
	//List<SimpleMountainPathBean> topByFavorites;
%>
<%
	topTen = controller.getClassification((Integer)session.getAttribute("sessionId"));
	userName = controller.getCurrentUserName();
	session.setAttribute("userName", userName);

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
						<%
						int index = 0;
						for (SimpleMountainPathBean bean : topTen) {
							index++;
							%>
							<div class="container">
							<button type="submit" class="btn" name="path" value="<%=bean.getName()%>" style="min-width: 100%;">
								<div class="row w-100 card-view-simple-path">
									<div class="row align-items-center">
										<div class="d-flex justify-content-start align-items-center">
											<div class="ml-auto w-100 p-2 bd-highlight">
												<div class="path-name-font" align="left" style="padding-left: 5%;"><%=index%>.&nbsp;&nbsp;<%=bean.getName()%></div>
											</div>
											<div class="mr-auto p-2 bd-highlight" style="padding-left: 5%;">
												<div class="d-flex justify-content-start align-items-center">
													Vote: 
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
											<div class="mr-auto p-3 bd-highlight text-nowrap">
												<div>Number of votes: <%=bean.getNumberOfVotes()%></div>
											</div>
										</div>
									</div>
									<div class="row align-items-center" style="padding-left: 3%; padding-bottom: 1%;">
										<div class="col-3 align-items-center">
											<!-- inserire recupero immagine dal DB -->
											<div class="d-flex justify-content-center align-items-center">
												<div class="p-2 flex-item-search-photo">
													<img src="Images/mountain_path.png" class="img-responsive photo">
												</div>
											</div>
										</div>
										<div class="col-4 align-middle" style="vertical-align: middle;" align="center">
											<div class="row justify-content-center align-self-center">Location</div>
											<div class="row justify-content-center"><%= bean.getRegion() %></div>
											<div class="row justify-content-center"><%= bean.getProvince() %></div>
											<div class="row justify-content-center"><%= bean.getCity() %></div>
										</div>
										<div class="col-4 align-middle" style="vertical-align: middle;" align="center">
											<div class="row justify-content-center align-self-center">Difficulty level</div>
											<div class="row justify-content-center align-self-center"><%= bean.getLevel() %></div>
										</div>
									</div>
								</div>
							</button>
							</div>
							<%
						}
					%></form><%
					topTen.clear();
				}
					%>
					
				<br>
			</div>
		</div>
		
	
		<!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>
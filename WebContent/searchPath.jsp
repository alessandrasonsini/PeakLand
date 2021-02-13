<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.util.Base64"%>
<%@page import="java.util.Base64.Encoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.util.List" %>
<%@page import="logic.controller.ViewMountainPathInfoController"%>
<%@page import="logic.bean.SimpleMountainPathBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	List<SimpleMountainPathBean> beanList = new ArrayList<SimpleMountainPathBean>();
	SimpleMountainPathBean selectedPath;
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
	
	if (request.getParameter("path") != null) {
		viewInfoController.setSelectedMountainPath(request.getParameter("path"));
		%><jsp:forward page="<%=getNextPageName()%>"/><%
	}
	
%>
	
	<body>
		<div class="row fill">
			<div class="col-3 background-orange">
				<!-- form to insert path name to search -->
				<form class="form-inline search-form" id="myForm" action="searchPath.jsp" method="post">
					<div class="d-flex">
						<input type="text" class="form-control" name="pathName" placeholder="Search for a path...">
						<button type="submit" class="btn btn-dark-orange"><img src="Images/icons8-search-52.png" width="50%"></button>
					</div>
				</form>
				
				<!-- text and assisted research button -->
				<div class="container">
					<div class="row mx-auto" style="padding-top: 30%; padding-left: 5%; padding-right: 5%">
						<div class="col" align="center">If you don't know what to look for we will help you!</div>
					</div>
					<div class="row mx-auto" style="padding-top: 5%;">
						<div class="col" align="center">
							<form id="assistedResearchForm" action="assistedResearch.jsp" method="post">
								<button type="submit" class="btn btn-ass-research" id="assistedResearch">
									Assisted research
								</button>
							</form>
						</div>
					</div>
				</div>
			</div>
				
			<div class="col-9">
			<!-- search results -->
				<form class="form" action="searchPath.jsp" method="post">
				<div class="container" id="resultsContainer">
				
				<%
				if ((request.getParameter("pathName") != null) || (viewInfoController.getPreviousSearchResults() != null) ) {
					
					if (request.getParameter("pathName") != null) {
						beanList.clear();
						beanList.addAll(viewInfoController.searchMountainPathByName(request.getParameter("pathName")));
						if (beanList.isEmpty()) {
							%>
							<div class="row justify-content-center" style="padding-top:10%">No matches found</div><%
						}
					}
					else {
						beanList.addAll(viewInfoController.getPreviousSearchResults());
					}
								
						for(SimpleMountainPathBean bean : beanList)	{
						%>
						<div class="container">
							<button type="submit" class="btn" name="path" value="<%=bean.getName()%>" style="min-width: 100%;">
								<div class="row card-view-simple-path rounded">
									<div class="col align-items-center">
										<div class="row justify-content-center" style="padding-bottom: 1%; padding-top:1%">		
											<div class="d-flex justify-content-start align-items-center">
											<div class="ml-auto w-100 p-2 bd-highlight">
												<div class="path-name-font" id="pathNameCardView" align="left" style="padding-left: 5%;"><%=bean.getName()%></div>
											</div>
											<div class="mr-auto p-2 bd-highlight" style="padding-left: 5%;">
												<div class="d-flex justify-content-start align-items-center">
													<%
					  									for(int i = 0; i < bean.getVote(); i++) {
					  									%>
						  								<div class="p-2 flex-item-stars-2">
															<img src="Images/star.png" class="icon-star">
														</div>
														<%
					  									}
														for(int i = bean.getVote(); i < 5; i++) {
					  									%>
						  								<div class="p-2 flex-item-stars-2">
															<img src="Images/emptystar.png" class="icon-star">
														</div>
														<%
					  									}
													%>
												</div>
											</div>

										</div>
										
										</div>
										<div class="row" style="padding-bottom: 2%;">
											<div class="col-3" align="center" style="padding-bottom: 1%;">
												<div class="row justify-content-center bold-text">Location</div>
												<div class="row justify-content-center"><%= bean.getRegionAsText()%></div>
												<div class="row justify-content-center"><%= bean.getProvinceAsText()%></div>
												<div class="row justify-content-center"><%= bean.getCityAsText()%></div>
											</div>
											<div class="col-3" align="center">
												<div class="row justify-content-center bold-text">Difficulty level</div>
												<div class="row justify-content-center" id="difficultyLevel"><%= bean.getLevelAsText() %></div>
											</div>
											<div class="col-3" align="center">
												<div class="row justify-content-center bold-text">Travel Time</div>
												<div class="row justify-content-center"><%= bean.getHoursAsText() %>:<%= bean.getMinutesAsText() %></div>
											</div>
											<div class="col-3" align="center">
												<div class="row justify-content-center bold-text">Number of votes</div>
												<div class="row justify-content-center"><%= bean.getNumberOfVotesAsText() %></div>
											</div>
										</div>
									</div>
								</div>
							</button>
						</div>
						<%
					}
				}
				%>
				</div>
				</form>
			</div>
			
		</div>
		
	
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
	</body>
	
</html>
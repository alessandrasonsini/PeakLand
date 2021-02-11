<%@page import="logic.bean.ReviewBean"%>
<%@page import="logic.controller.ViewMountainPathInfoController"%>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!
	String pathName;
	ViewMountainPathInfoController controller;
	List<ReviewBean> reviewList;
%>

<%
	controller = (ViewMountainPathInfoController) session.getAttribute("viewInfoController");

	if (request.getParameter("pathName") != null) {
		pathName = (String) request.getParameter("pathName");
		session.setAttribute("pathName", pathName);
	}
	
	reviewList = controller.getPathReview(pathName);
	System.out.println("sizee viewreview jsp  "+reviewList.size());
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
					<div class="row mx-auto" style="padding-top: 45%; padding-left: 5%; padding-right: 5%">
						<div class="col" align="center">
							<span class="path-name-font"><%=session.getAttribute("pathName")%></span>
						</div>
					</div>
				</div>
			</div>
		
			<div class="col-9">
				<div class="container">
					<div class="row">
						<div class="col-6">
							<div class="d-flex justify-content-start align-items-center">
								<div class="p-2 flex-item-icon">
									<img src="Images/list.png" class="img-responsive icons">
								</div>
								<div class="p-2 flex-item-text pull-left text">Reviews</div>
							</div>
						</div>
					</div>
					<%
					for (ReviewBean review : reviewList) {
						%>
						<br>
						<div class="container">
							<div class="row green-text">
								<div class="col-4">
									Author:&nbsp;&nbsp;&nbsp;&nbsp;
									<span class="black-text"><%=review.getAuthor() %></span>
								</div>
								<div class="col-4">
									Vote:&nbsp;&nbsp;&nbsp;&nbsp;
									<span class="black-text"><%=review.getVote() %></span>
								</div>
								<div class="col-4">
									Title:&nbsp;&nbsp;&nbsp;&nbsp;
									<span class="black-text"><%=review.getTitle() %></span>
								</div>
							</div>
							<div class="row">
								<p class="green-text">Comment:&nbsp;&nbsp;
								<span class="black-text"><%=review.getComment() %></span>
								</p>
							</div>
						</div>
						<br><br>
						<%
					}
					%>
				</div>
			</div>
		</div>


		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
	
	</body>
	
</html>
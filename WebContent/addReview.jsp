<%@page import="logic.controller.AddNewMountainPathController"%>
<%@page import="logic.controller.utils.CurrentLoggedUsers"%>
<%@page import="logic.model.exception.DatabaseException"%>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!
	Integer vote;
	Integer j = 1;
	boolean disable = true;
%>
<jsp:useBean id="review" scope="request" class="logic.bean.ReviewBean"/>
<jsp:setProperty name="review" property="*"/>
<%
	AddNewMountainPathController addPathController = (AddNewMountainPathController) session.getAttribute("controller");

	session.setAttribute("disable", disable);
	
	for (Integer i = 1; i < 6; i++) {
		if (request.getParameter(i.toString()) != null) {
			disable = false;
			session.setAttribute("disable", disable);
			vote = i;
			session.setAttribute("vote", vote);
			break;
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
					<div class="row mx-auto" style="padding-top: 40%; padding-left: 10%; padding-right: 10%">
						<div class="col" align="center" style="font-size: 150%;">Let other users know about your experience here!</div>
					</div>
				</div>
			</div>
			
			<div class="col-9">
				<div class="container">
				
				<%
				if (request.getParameter("addReviewBtn") != null) {
					try {
						review.setVote(vote);
						addPathController.saveReview(review);
						%>
						<div class="container" style="padding-top: 3%;">
							<div class="alert alert-success alert-dismissible fade show" role="alert">
								<strong>Review added successfully!</strong>
							  	<a href="#" class="close" style="float: right;" data-dismiss="alert" aria-label="close">&times;</a>
							</div>
						</div>
						<%
					} catch(DatabaseException e) {
						%>
						<div class="container" style="padding-top: 3%;">
							<div class="alert alert-danger alert-dismissible fade show" role="alert">
								<strong>Database error</strong>Ops, there was an error connecting to database. Retry later
							  	<a href="#" class="close" style="float: right;" data-dismiss="alert" aria-label="close">&times;</a>
							</div>
						</div>
						<%
					}
					session.removeAttribute("name");
					session.removeAttribute("vote");
					
					
					
				}
				%>
				
					<form class="form-inline" action="addReview.jsp" method="post">
						<div class="d-flex justify-content-start align-items-center">
							<div class="p-2 flex-item-text pull-left black-text">Vote</div>
							<%
							if (vote != null) {
								for (j = 1; j < vote+1; j++) {
									%>
									<div class="p-2 flex-item-stars">
										<button type="submit" name="<%=j.toString()%>" value="<%=j.toString()%>" class="btn">
											<img src="Images/star.png" class="img-responsive icons" width="30%">
										</button>
									</div>
									
									<%
								}
							}
							
							for (Integer k = j; k < 6; k++) {
								%>
	 							<div class="p-2 flex-item-stars">
									<button type="submit" name="<%=k.toString()%>" value="<%=k.toString()%>" class="btn">
										<img src="Images/emptystar.png" class="img-responsive icons" width="30%">
									</button>
								</div>
								<%
							}
							%>	
						</div>
					</form>
					
					<form class="form-inline" action="addReview.jsp" method="post">
						<div class="d-flex justify-content-start align-items-center">
							<div class="p-2 flex-item-text pull-left black-text">Title </div>
							<div class="p-2 flex-item-text pull-left text">
								<input type="text" class="form-control" name="title" placeholder="Review title" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
							</div>
						</div>
						<div class="container">
							<textarea maxlength="300" rows="10" cols="60" name="comment" placeholder="Write your comment here..." ${ sessionScope.disable eq true  ? 'disabled' : ''}></textarea>
						</div>
						<div class="container" style="pagging-top: 10%;">
							<button type="submit" name="addReviewBtn" value="addReviewBtn" class="btn btn-light-orange" ${ sessionScope.disable eq true  ? 'disabled' : ''}>Add review</button>
						</div>
					</form>
					
					
				</div>
			</div>
		</div>

		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
	
	</body>
</html>
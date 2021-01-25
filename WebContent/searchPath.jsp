<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>
<%@page import="logic.controller.ViewMountainPathInfoController"%>
<%@page import="logic.model.bean.SimpleMountainPathBean"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!
	List<SimpleMountainPathBean> beanList = new ArrayList<SimpleMountainPathBean>();
	ViewMountainPathInfoController controller = new ViewMountainPathInfoController();
	SimpleMountainPathBean selectedPath;
%>
<%
	session.setAttribute("controller", controller);
%>


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
				<!-- form to insert path name to search -->
				<form class="form-inline search-form" action="searchPath.jsp" method="post">
					<div class="d-flex">
						<input type="text" class="form-control" name="pathName" placeholder="Search for a path...">
						<button type="submit" class="btn"><img src="Images/icons8-search-52.png" width="50%"></button>
					</div>
				</form>
				
				<!-- text and assisted research button -->
				<div class="container">
					<div class="row mx-auto" style="padding-top: 55%; padding-left: 5%; padding-right: 5%">
						<div class="col" align="center">If you don't know what to look for we will help you!</div>
					</div>
					<div class="row mx-auto" style="padding-top: 5%;">
						<div class="col" align="center">
							<a class="btn btn-ass-research" href="#" role="button">Assisted research</a>
						</div>
					</div>
				</div>
			</div>
				
			<div class="col-9">
			<!-- search results -->
				<form class="form" action="searchPath.jsp" method="post">
				<div class="container">
				
				<%
				if (request.getParameter("pathName") != null) {
					beanList.addAll(controller.searchMountainPathByName(request.getParameter("pathName")));
    				
					for(SimpleMountainPathBean bean : beanList)	{
						%>
						<div class="container">
						
							<input type='hidden' name='selectedPathName' id='pathName' value="<%= bean.getName() %>"/>
							
							
							<button type="submit" class="btn" style="min-width: 100%;">
								<div class="row card-view-simple-path">
									<div class="col-3" align="center">
										<!-- inserire recupero immagine dal DB -->
										<img src="Images/mountain_path.png" class="img-responsive">
									</div>
									<div class="col-9">
										<div class="row justify-content-center" style="padding-bottom: 1%; padding-top:1%">
											<%= bean.getName() %>
										</div>
										<div class="row">
											<div class="col-4" align="center" style="padding-bottom: 1%;">
												<div class="row justify-content-center">Location</div>
												<div class="row justify-content-center"><%= bean.getRegion() %></div>
												<div class="row justify-content-center"><%= bean.getProvince() %></div>
												<div class="row justify-content-center"><%= bean.getCity() %></div>
											</div>
											<div class="col-4" align="center">
												<div class="row justify-content-center">Difficulty level</div>
												<div class="row justify-content-center"><%= bean.getLevel() %></div>
											</div>
											<div class="col-4" align="center">
												<div class="row justify-content-center">Travel Time</div>
												<div class="row justify-content-center"><%= bean.getHours() %>:<%= bean.getMinutes() %></div>
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
				
				
				<%
				if (request.getParameter("selectedPathName") != null) {
					%><%=request.getParameter("selectedPathName")%><%
					controller.setSelectedMountainPath(request.getParameter("selectedPathName"));
					
					%><jsp:forward page="viewMountainPathInfo.jsp"/><%
				}
				%>
				
				
			</div>
			
		</div>
		
	
		<!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
	</body>
	
</html>
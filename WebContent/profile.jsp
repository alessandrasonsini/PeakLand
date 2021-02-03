<%@page import="java.io.InputStream"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.util.Base64"%>
<%@page import="java.util.Base64.Encoder"%>
<%@page import="java.util.List"%>
<%@page import="logic.controller.ProfileController"%>
<%@page import="logic.model.bean.LoggedUserBean"%>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:useBean id="user" scope="request" class="logic.model.bean.LoggedUserBean"/>
<jsp:useBean id="currentUser" scope="session" class="logic.model.bean.LoggedUserBean"/>
<jsp:setProperty name="user" property="*"/>

<%!
	ProfileController controller = new ProfileController();
	boolean disable = true;
	String base64;
%>


<%
	session.setAttribute("controller", controller);
	
	currentUser = controller.getCurrentUser((Integer) session.getAttribute("sessionId"));
	session.setAttribute("currentUser", currentUser);
	
	if (request.getParameter("logOut") != null) {
		session.removeAttribute("sessionId");
		controller.logOut();
	}
	if (request.getParameter("edit") != null) {
		disable = false;
	}
	if (request.getParameter("save") != null) {
		controller.updateUserInfo(user);
		disable = true;
	}
	
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	if (isMultipart) {
		Part filePart = request.getPart("file"); 
		
		if (filePart != null) {
			InputStream fileContent = filePart.getInputStream();
			controller.setProfileImage(fileContent);
		}
	}
	
	ByteArrayInputStream stream = currentUser.getImageStream();
	byte[] bytes = IOUtils.toByteArray(stream);
	base64 = Base64.getEncoder().encodeToString(bytes);
	
	session.setAttribute("disable", disable);
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
				<div class="d-flex justify-content-center align-items-center" style="padding-top: 8%;">
					<div class="p-2">
						<% if (base64 == null) { %>
							<img src="Images/profile.png" class="img-responsive photo">
						<% } else { %>
							<img src="data:image/jpeg;base64,<%=base64%>" class="img-responsive photo">
						<% } %>
					</div>
				</div>
				<div class="d-flex flex-row-reverse align-items-start">
					<div class="p-2">
						<form class="form-inline" action="profile.jsp" method="post" enctype="multipart/form-data">
							<div class="p-2">
								<label class="custom-file-upload ">
								    <input type="file" name="file" style="display: none;"/>
								    <i>
								    	<img src="Images/edit.png" class="btn btn-edit" width="100%">
								    </i>
								</label>
								<button type="submit" name="confirm" value="confirm" class="btn btn-dark-orange">Save</button>
							</div>
						</form>
						
					</div>
				</div>
				
				<div class="container" style="text-align: center; padding-top: 10%;">
					<button type="submit" name="logOut" value="logOut" class="btn btn-dark-orange">Log out</button>
				</div>
			</div>
			
			<div class="col-9">
				<div class="container" style="padding-top: 2%;">
					<form class="form-inline" action="profile.jsp" method="post">
						<div class="d-flex flex-row justify-content-start align-items-center">
							<div class="p-2">
								<button type="submit" name="edit" value="edit" class="btn btn-dark-orange" ${ sessionScope.disable eq false  ? 'disabled' : ''}>
									<img src="Images/edit.png" width="100%">
								</button>
							</div>
							<div class="p-2">
								<button type="submit" name="save" value="save" class="btn btn-dark-orange" ${ sessionScope.disable eq true  ? 'disabled' : ''}>Save</button>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-6" style="padding-right: 3%;">
								<div class="row mx-auto" style="padding-bottom: 3%;">
									<div class="col-5 black-text">Name:</div>
									<div class="col-7">
										<input type="text" class="form-control" name="name" placeholder="Name" value="${sessionScope.currentUser.name}" style="background-color: #CCE3C9;" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
									</div>
								</div>
								<div class="row mx-auto" style="padding-bottom: 3%;">
									<div class="col-5 black-text">Surname:</div>
									<div class="col-7">
										<input type="text" class="form-control" name="surname" placeholder="Surname" value="${sessionScope.currentUser.surname}" style="background-color: #CCE3C9;" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
									</div>
								</div>
								<div class="row mx-auto" style="padding-bottom: 3%;">
									<div class="col-5 black-text">Level:</div>
									<div class="col-7">${sessionScope.currentUser.level}</div>
								</div>
							</div>
							<div class="col-6">
								<div class="row mx-auto" style="padding-bottom: 3%;">
									<div class="col-3 black-text">About me</div>
									<div class="col-3">
										<textarea rows="5" cols="30" name="description" placeholder="About me" ${ sessionScope.disable eq true  ? 'disabled' : ''}>
											${sessionScope.currentUser.description}
										</textarea>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			
			</div>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>
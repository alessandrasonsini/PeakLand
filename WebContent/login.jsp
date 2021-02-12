<%@page import="logic.model.exception.InvalidUsernameException"%>
<%@page import="logic.controller.LoginController"%>
<%@page import="logic.model.exception.DatabaseException"%>
<%@page import="logic.model.exception.EmptyMandatoryFieldsException"%>
<%@page import="logic.model.exception.InvalidCredentialException"%>
<%@page import="logic.model.exception.WrongInputException"%>
<%@page import="logic.model.exception.InvalidUsernameException"%>
<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!
	LoginController loginController;
	Integer id;
	
	public PageId getNextPage(String page) {
		switch (page) {
			case "VIEW_INFO": 
				return PageId.VIEW_INFO;
			case "ADD_PATH": 
				return PageId.ADD_PATH;
			case "LOGIN":
				return PageId.LOGIN;
			case "PROFILE":
				return PageId.PROFILE;
			case "HOME":
				return PageId.HOME;
			default: 
				return null;
		}
	}
%>
<%
	loginController = (LoginController) session.getAttribute("controller");
	if (request.getParameter("nextPageId") != null)
		session.setAttribute("nextPageId", request.getParameter("nextPageId"));
	if (request.getParameter("nextPage") != null) {
		session.setAttribute("nextPage", request.getParameter("nextPage"));
	}
%>

<!-- dichiarazione e instanziazione di una CredentialBean !-->
<jsp:useBean id="credential" scope="request" class="logic.bean.CredentialBean"/>
<!-- mappa attributi della bean sui campi del form -->
<jsp:setProperty name="credential" property="*"/>

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
				<div class="container">
					<div class="row mx-auto" style="padding-top: 40%; padding-left: 20%; padding-right: 20%">
						<div class="col" align="center" style="font-size: 150%;">Login or sign in to access this feature</div>
					</div>
					<div class="row mx-auto" style="padding-top: 50%; padding-left: 2%; padding-right: 2%">
						<div class="col" align="center" style="font-size: 150%;">Became part of our community!</div>
					</div>
				</div>
			</div>
			
			<div class="col-9">
		<%
		try {
			if (request.getParameter("login") != null) {
				id = loginController.loginAction(credential);
				session.setAttribute("sessionId", id);
				controller = loginController.executeAction(getNextPage((String)session.getAttribute("nextPage")));
				session.setAttribute("controller", controller);
				%>
				<jsp:forward page='<%=(String) session.getAttribute("nextPageId")%>'/>
				<%
			}
			else if (request.getParameter("signin") != null) {
				id = loginController.signInAction(credential);
				session.setAttribute("sessionId", id);
				controller = loginController.executeAction(getNextPage((String)session.getAttribute("nextPage")));
				session.setAttribute("controller", controller);
				%>
				<jsp:forward page='<%=(String) session.getAttribute("nextPageId")%>'/>
				<%
			}
		}catch(EmptyMandatoryFieldsException e) {
			if (request.getParameter("login") != null) {
			%>
			<div class="container" style="padding-top: 3%;">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<strong>Empty fields error!</strong> You need to insert username and password.
				  	<a href="#" class="close" style="float: right;" data-dismiss="alert" aria-label="close">&times;</a>
				</div>
			</div>
			<%
			}
			else if (request.getParameter("signin") != null) {
			%>
			<div class="container" style="padding-top: 3%;">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<strong>Empty fields error!</strong> You need to insert username, password and confirm password.
				  	<a href="#" class="close" style="float: right;" data-dismiss="alert" aria-label="close">&times;</a>
				</div>
			</div>
			<%
			}
		}catch (InvalidUsernameException e) {
			%>
			<div class="container" style="padding-top: 3%;">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<strong>Sign in error!</strong> Username not available.
				  	<a href="#" class="close" style="float: right;" data-dismiss="alert" aria-label="close">&times;</a>
				</div>
			</div>
			<%
		}catch (InvalidCredentialException e) {
			%>
			<div class="container" style="padding-top: 3%;">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<strong>Login error!</strong> Credentials are not valid. 
				  	<a href="#" class="close" style="float: right;" data-dismiss="alert" aria-label="close">&times;</a>
				</div>
			</div>
			<%
		}catch(WrongInputException e) {
			%>
			<div class="container" style="padding-top: 3%;">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<strong>Sign in error!</strong> Password mismatch.
				  	<a href="#" class="close" style="float: right;" data-dismiss="alert" aria-label="close">&times;</a>
				</div>
			</div>
			<%
		}catch(DatabaseException e) {
			%>
			<div class="container" style="padding-top: 3%;">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<strong>Database error</strong>Ops, there was an error connecting to database. Retry later
				  	<a href="#" class="close" style="float: right;" data-dismiss="alert" aria-label="close">&times;</a>
				</div>
			</div>
			<%
		}
		%>
				<form class="form" style="text-align: center; padding-top: 2%;" action="login.jsp" method="post">

					<div class="container">
						<div class="d-flex flex-column justify-content-center align-items-center">
							<div class="p-2" style="padding-bottom: 3%;">
								<input type="text" class="form-control" name="username" value="" placeholder="Username">
							</div>
							<div class="p-2" style="padding-bottom: 3%;">
								<input type="password" class="form-control" name="password" value="" placeholder="Password">
							</div>
						</div>
					</div>
					
					<div class="container">
						<div class="row justify-content-center align-items-center">
							<div class="col-3">
								<button type="submit" name="login" value="login" class="btn btn-light-orange">Login</button>
							</div>
							<div class="col-1">
								<div class="vertical"></div>
							</div>
							<div class="col-3">
								<div class="row justify-content-center" style="padding-bottom:3%;">
									<div class="col-10">
										<input type="text" class="form-control" name="confirmPassword"  placeholder="Confirm your password">
									</div>
								</div>
								<button type="submit" name="signin" value="signin" class="btn btn-light-orange">Sign in</button>
							</div>
						</div>
					</div>
					<div class="container">
						<div class="d-flex flex-column justify-content-center align-items-center">
							<div class="p-2" style="padding-bottom: 3%;">or</div>
							<div class="p-2" style="padding-bottom: 3%;">
								<img src="Images/google-plus.png" width="15%">
								<button type="submit" name="google" value="google" class="btn btn-danger">
									Login with Google
								</button>
							</div>
							<div class="p-2">
								<img src="Images/facebook-new.png" width="15%">
								<button type="submit" name="fb" value="fb" class="btn btn-primary">
									Login with Facebook
								</button>
							</div>
						</div>
					</div>
					</form>
			</div>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>
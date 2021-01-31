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
	LoginController controller = new LoginController();
	Integer id;
%>
<%
	session.setAttribute("controller", controller);
	if (request.getParameter("nextPageId") != null)
		session.setAttribute("nextPageId", request.getParameter("nextPageId"));
%>

<!-- dichiarazione e instanziazione di una CredentialBean !-->
<jsp:useBean id="credential" scope="request" class="logic.model.bean.CredentialBean"/>
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
				<div class="col-4 mx-auto" style="padding-top: 3%; text-align: center;">
					
					<form class="form" style="text-align: center;" action="login.jsp" method="post">
						
							<div class="row" style="padding-bottom: 3%;">
								<div class="col"><input type="text" class="form-control" name="username" placeholder="Username"></div>
							</div>
							<div class="row" style="padding-bottom: 3%;">
								<div class="col"><input type="password" class="form-control" name="password" placeholder="Password"></div>
							</div>
							<div class="row">
								<div class="col">
									<button type="submit" name="login" value="login" class="btn btn-light-orange">Login</button>
								</div>
								<div class="col border-right">
									<input type="text" class="form-control" name="confirmPassword" placeholder="Confirm your password">
									<button type="submit" name="signin" value="signin" class="btn btn-light-orange">Sign in</button>
								</div>
							</div>
							<div class="row">
								<div class="col">or</div>
							</div>
							<div class="row">
								<div class="col">
									<button type="submit" name="google" value="google" class="btn btn-danger">Login with Google</button>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<button type="submit" name="fb" value="fb" class="btn btn-primary">Login with Facebook</button>
								</div>
							</div>
					</form>
				</div>
		
		<%
		try {
			System.out.println("dento tryyyy");
			if (request.getParameter("login") != null) {
				id = controller.loginAction(credential);
				session.setAttribute("sessionId", id);
				%>
				<jsp:forward page='<%=(String) session.getAttribute("nextPageId")%>'/>
				<%
			}
			else if (request.getParameter("signin") != null) {
				id = controller.signInAction(credential);
				session.setAttribute("sessionId", id);
				%>
				<jsp:forward page='<%=(String) session.getAttribute("nextPageId")%>'/>
				<%
			}
		}catch(EmptyMandatoryFieldsException e) {
			System.out.println("dento catch 1 ");
			
			if (request.getParameter("login") != null) {
			%>
			<div class="container" style="padding-top: 3%;">
				<div class="alert alert-error alert-dismissible fade show" role="alert">
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
			System.out.println("dento catch 2 ");
			
			%>
			<div class="container" style="padding-top: 3%;">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<strong>Login error!</strong> Credentials are not valid.
				  	<a href="#" class="close" style="float: right;" data-dismiss="alert" aria-label="close">&times;</a>
				</div>
			</div>
			<%
		}catch (InvalidCredentialException e) {
			System.out.println("dento catch 3 ");
			%>
			<div class="container" style="padding-top: 3%;">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<strong>Sign in error!</strong> Username not available.
				  	<a href="#" class="close" style="float: right;" data-dismiss="alert" aria-label="close">&times;</a>
				</div>
			</div>
			<%
		}catch(WrongInputException e) {
			System.out.println("dento catch 4 ");
			%>
			<div class="container" style="padding-top: 3%;">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<strong>Sign in error!</strong> Password mismatch.
				  	<a href="#" class="close" style="float: right;" data-dismiss="alert" aria-label="close">&times;</a>
				</div>
			</div>
			<%
		}catch(DatabaseException e) {
			System.out.println("dento catch 5 ");
			%>
			<div class="container" style="padding-top: 3%;">
				<div class="alert alert-danger alert-dismissible fade show" role="alert">
					<strong>Database error!</strong> Ops, there was an error connecting to database. Retry later.
				  	<a href="#" class="close" style="float: right;" data-dismiss="alert" aria-label="close">&times;</a>
				</div>
			</div>
			<%
		}
		%>
		
		
			</div>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="row-fluid">
    <div class="jumbotron">
        <h1><spring:message code='project.name'/></h1>
    </div>
</div>
<div class="row-fluid">
    <div class="span4 offset4 well" ng-controller="loginController">
        <legend><spring:message code="login.header" /></legend>
        <div class="alert alert-error" ng-class="{'': displayLoginError == true, 'none': displayLoginError == false}">
            <spring:message code="login.error" />
        </div>
        <form id="loginForm" name="loginForm" method="post" action="<c:url value='/login' />">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <div>
                <input ng-model="user.username" name="username" id="username" type="text" class="span12" placeholder="<spring:message code='sample.email' /> "><br/>
                <input ng-model="user.password" name="password" id="password" type="password"  class="span12" placeholder="Password"><br/>
                <input ng-model="user.remember_me"  class="pull-left" style="margin-right:5px" id="remember_me" name="remember-me" type="checkbox"/>
				<label for="remember_me"  >Remember me</label>
                <button ng-click="authenticateUser(loginForm);" type="button" name="submit" class="btn btn-inverse btn-block"><spring:message code="login.signIn" /></button>
            </div>
        </form>
    </div>
</div>
<script src="<c:url value='/resources/old/js/pages/login.js' />"></script>
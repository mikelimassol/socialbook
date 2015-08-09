<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en-UK" id="ng-app" ng-app="SocialBookApp">
<head>

        <title></title>
        <link href="<c:url  value='/resources/css/bootstrap.min.css'  />" rel="stylesheet"/>
        <link href="<c:url  value='/resources/css/bootstrap-responsive.min.css'  />" rel="stylesheet"/>

        
        <link href="<c:url  value='/resources/css/project_style.css'  />" rel="stylesheet"/>
        <script src="<c:url value='/resources/js/vendors/jquery/jquery.min.js' />"></script>
        <script src="<c:url value='/resources/js/vendors/angular/angular.min.js' />"></script>
        <script src="<c:url value='/resources/js/vendors/angular-route/angular-route.min.js' />"></script>
        <script src="<c:url value='/resources/js/vendors/angular/ui-bootstrap-tpls.min.js' />"></script>
        
        
  </head>     
        
    <body >
         <div class="container" >
         
         
		 <div class="masthead"  ng-controller="MainCtrl as mainCtrl">
		    <h3 class="muted">
		        
		    </h3>
		
			    <div class="navbar" ng-show="mainCtrl.authService.isLoggedIn" >
			        <div class="navbar-inner">
			            
			                <ul class="nav">
			                    <li class="pull-left">
			                        <a href="/socialbook/#/home"
			                           title='home'>
			                            <p>Home</p>
			                        </a>
			                    </li>
			                      <li class="pull-left"><a title='users' href="/socialbook/#/browse"><p>Browse Not Connected Users</p></a></li>
			                      <li  ng-show="mainCtrl.authService.authUser.authorityValues.indexOf('ROLE_ADMIN')!=-1" class="pull-left"><a title='users' href="/socialbook/#/admin/browse_all"><p>Browse All Users</p></a></li>
				                  <li class="pull-right">
				                   <a href="/socialbook/logout" title='logout'><p class="displayInLine">Logout&nbsp;(<span ng-bind="mainCtrl.authService.authUser.user.name"></span>)</p></a>
				                </li>
			                </ul>
			            
			        </div>
			    </div>
			</div>        
         
     
             <div ng-view></div>
            
        </div>

        <!--[if IE]>
            <script src="<c:url value='/resources/js/vendors/bootstrap/dist/js/bootstrap.min.ie.js' />"></script>
        <![endif]-->
        <!--[if !IE]><!-->
            <script src="<c:url value='/resources/js/vendors/bootstrap/dist/js/bootstrap.min.js' />"></script>
        <!--<![endif]-->

        <script src="<c:url value='/resources/js/app.js' />"></script>
        <script src="<c:url value='/resources/js/controllers.js' />"></script>
        <script src="<c:url value='/resources/js/services.js' />"></script>
    </body>
</html>

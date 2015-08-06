<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="masthead">
    <h3 class="muted">
        <spring:message code='header.message'/>
    </h3>

    <div class="navbar">
        <div class="navbar-inner">
            <div class="container">
                <ul class="nav" ng-controller="LocationController as ctrl">
                    <li ng-class="{'active': ctrl.activeURL == 'home', '': ctrl.activeURL != 'home'}" >
                        <a href="<c:url value="/"/>"
                           title='<spring:message code="header.home"/>'
                                >
                            <p><spring:message code="header.home"/></p>
                        </a>
                    </li>
                    <li ng-class="{'active': ctrl.activeURL == 'contacts', '': ctrl.activeURL != 'contacts'}"><a title='<spring:message code="header.contacts"/>' href="<c:url value='/protected/contacts'/>"><p><spring:message code="header.contacts"/></p></a></li>
                </ul>
                <ul class="nav pull-right" ng-controller="UserHeaderController as ctrl">
                <li>

                   <a href="#" onclick="javascript:document.getElementById('logout-form').submit();" title='<spring:message code="header.logout"/>'><p class="displayInLine"><spring:message code="header.logout"/>&nbsp;(<span ng-bind="ctrl.name"></span>)</p></a></li>
	               <form class="form-inline" action="<c:url value='/logout' />" method="post" id="logout-form">
	                 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />  
	              </form>
                
                </ul>
            </div>
        </div>
    </div>
</div>

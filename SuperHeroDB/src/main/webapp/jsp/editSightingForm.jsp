<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hero Details</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Sighting Details</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/">
                            Home
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayHeroPage">
                            Heros
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayOrganizationPage">
                            Organization
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySightingPage">
                            Sighting
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayLocationPage">
                            Location
                        </a>
                    </li>
                </ul>   
            </div>

            <sf:form class="form-horizontal" role="form" modelAttribute="sighting"
                     action="editSighting" method="POST">
                <div class="form-group">
                    <label for="sightingDate" class="col-md-4 control-label">Sighting Date:</label>
                    <div class="col-md-8">
                        <sf:input type="date" class="form-control" id="add-sighting-date" path="sightingDate" 
                               placeholder="Sighting Date" min="2000-01-01" max="2030-12-31" required="true"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="sightingLocation" 
                           class="col-md-4 control-label">
                        Location:
                    </label>
                    <div class="col-md-8">
                        <select name="sightingLocation" id="sightingLocation">
                            <c:forEach var="location" items="${locations}">
                                <option value="${location.locationID}"
                                <c:if test="${sighting.locationID.locationID == location.locationID}"> selected="selected"</c:if>
                                >
                                
                                    ${location.locationName} - ${location.locationCity}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="sightingHeros" class="col-md-4 control-label">
                        Heros:
                    </label>
                    <div class="col-md-8">
                        <select multiple name="sightingHeros" 
                                id="heros" style="width: 100%" 
                                required>
                            <c:forEach var="hero" items="${heros}">
                                <option value="${hero.heroID}"
                                <c:if test="${sighting.hero.contains(hero)}"> selected="selected"</c:if>
                                 >
                                    ${hero.heroName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <sf:hidden path="sightingID"/>        
                </div>       
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Sighting"/>
                    </div>
                </div>

            </sf:form>                
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
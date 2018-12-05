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
            <h1>Organization Details</h1>
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

            <sf:form class="form-horizontal" role="form" modelAttribute="organization"
                     action="editOrganization" method="POST">
                <div class="form-group">
                    <label for="add-organization-name" class="col-md-4 control-label">Organization Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-organization-name"
                                  path="organizationName" placeholder="Organization Name"/>
                        <sf:errors path="organizationName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-organization-description" class="col-md-4 control-label">Organization Description:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-organization-description"
                                  path="organizationDescription" placeholder="Organization Description"/>
                        <sf:errors path="organizationDescription" cssclass="error"></sf:errors>
                        </div>
                    </div>
                     
                <div class="form-group">
                    <label for="add-organization-number" class="col-md-4 control-label">Phone Number:</label>
                    <div class="col-md-8">

                    <sf:input type="tel" class="form-control" id="phone"
                              path="organizationNumber" placeholder="1234567890"
                              pattern="[0-9]{10}" required="true" />
                    <sf:errors path="organizationNumber" cssclass="error"></sf:errors>
                    </div>
                </div>     
                <div class="form-group">
                    <label for="add-organization-email" class="col-md-4 control-label">Email:</label>                          
                    <div class="col-md-8">
                    <sf:input type="email" class="form-control" id="add-organization-email"
                              path="organizationEmail" placeholder="organizationEmail"/>
                    <sf:errors path="organizationEmail" cssclass="error"></sf:errors>
                    </div>
                </div>



                <div class="form-group">
                    <label for="organizationLocation" 
                           class="col-md-4 control-label">
                        Location:
                    </label>
                    <div class="col-md-8">
                        <select name="organizationLocation" id="organizationLocation">
                        <c:forEach var="location" items="${locations}">
                            <option value="${location.locationID}"
                                    <c:if test="${organization.location.locationID == location.locationID}"> selected="selected"</c:if>
                                >>
                                ${location.locationName} - ${location.locationCity}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="organizationHeros" class="col-md-4 control-label">
                    Heros:
                </label>
                <div class="col-md-8">
                    <select multiple name="organizationHeros" 
                            id="heros" style="width: 100%" 
                            required>
                        <c:forEach var="hero" items="${heros}">
                            <option value="${hero.heroID}"
                                 <c:if test="${organization.heros.contains(hero)}"> selected="selected"</c:if>
                                 >
                                ${hero.heroName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <sf:hidden path="organizationID"/>        
            </div>       
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Update Organization"/>
                </div>
            </div>


        </sf:form>                
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
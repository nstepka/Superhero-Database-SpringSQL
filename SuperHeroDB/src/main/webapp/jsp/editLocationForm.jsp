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
            <h1>Location Details</h1>
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

            <sf:form class="form-horizontal" role="form" modelAttribute="location"
                     action="editLocation" method="POST">
                <div class="form-group">
                    <label for="add-location-name" class="col-md-4 control-label">Location Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-location-name"
                                  path="locationName" placeholder="Location Name"/>
                        <sf:errors path="locationName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-location-description" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-hero-description"
                                  path="locationDescription" placeholder="Local Description"/>
                        <sf:errors path="locationDescription" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-location-address" class="col-md-4 control-label">Address:</label>                          
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-location-address"
                                  path="locationAddress" placeholder="Location Address" />
                        <sf:errors path="locationAddress" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-location-city" class="col-md-4 control-label">City:</label>
                        <div class="col-md-8">                        
                        <sf:input type="text" class="form-control" id="add-location-city"
                                  path="locationCity" placeholder="Location City"/>
                        <sf:errors path="locationCity" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-location-state" class="col-md-4 control-label">State:</label>
                        <div class="col-md-8">

                        <sf:input type="text" class="form-control" id="add-location-state"
                                  path="locationState" placeholder="locationState"/>
                        <sf:errors path="locationState" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-location-latitude" class="col-md-4 control-label">Latitude:</label>
                        <div class="col-md-8">
                        <sf:input type="number" step="0.000001" min="-99.999999" max="99.999999"  class="form-control" id="add-location-latitude"
                                  path="locationLatitude" placeholder="locationLatitude"/>
                        <sf:errors path="locationLatitude" cssclass="error"></sf:errors>    
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-location-longitude" class="col-md-4 control-label">Latitude:</label>
                        <div class="col-md-8">
                        <sf:input type="number" step="0.000001" min="-99.999999" max="99.999999" class="form-control" id="add-location-longitude"
                                  path="locationLongitude" placeholder="locationLongitude"/>
                        <sf:errors path="locationLongitude" cssclass="error"></sf:errors>       
                        <sf:hidden path="locationID"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Location"/>
                    </div>
                </div>
            </sf:form>                
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
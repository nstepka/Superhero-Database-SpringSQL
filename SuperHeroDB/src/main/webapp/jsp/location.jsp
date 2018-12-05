<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <title>SUPER HERO DATABASE RECORDER</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>SUPER HERO DATABASE RECORDER</h1>
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
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/displayLocationPage">
                            Location
                        </a>
                    </li>
                </ul>    
            </div>
            <!-- Main Page Content Start -->
            <!-- 
    Add a row to our container - this will hold the summary table and the new
    contact form.
            -->
            <div class="row">
                <!-- 
                    Add a col to hold the summary table - have it take up half the row 
                -->
                <div class="col-md-6">
                    <h2>locations</h2>
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="40%">Location Name</th>
                            <th width="30%">Description</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="location" items="${locationList}">
                            <tr>
                                <td>
                                    <a href="displayLocationDetails?locationID=${location.locationID}">
                                        <c:out value="${location.locationName}"/><c:out value="${location.locationID}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${location.locationDescription}"/>
                                </td>
                                <td>
                                    <a href="displayEditLocationForm?locationID=${location.locationID}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteLocation?locationID=${location.locationID}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                  
                </div> <!-- End col div -->
                <!-- 
                    Add col to hold the new contact form - have it take up the other 
                    half of the row
                -->
                <div class="col-md-6">
                    <h2>Add New Location</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createLocation">
                        <div class="form-group">
                            <label for="add-location-name" class="col-md-4 control-label">Location Name:</label>
                            <div class="col-md-8">
                                <input type="text" required class="form-control" name="locationName" placeholder="Location Name" required />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" required class="form-control" name="locationDescription" placeholder="Location Discription"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location-address" class="col-md-4 control-label">Address:</label>
                            <div class="col-md-8">
                                <input type="text" required class="form-control" name="locationAddress" placeholder="Location Address" required />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location-city" class="col-md-4 control-label">Location City:</label>
                            <div class="col-md-8">
                                <input type="text" required class="form-control" name="locationCity" placeholder="Location City" required />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location-state" class="col-md-4 control-label">Location State:</label>
                            <div class="col-md-8">
                                <input type="text" required class="form-control" name="locationState" placeholder="Location State" required />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location-latitude" class="col-md-4 control-label">Location latitude:</label>
                            <div class="col-md-8">
                                <input type="number" required step="0.000001" min="-99.999999" max="99.999999"  class="form-control" name="locationLatitude" placeholder="Location Latitude"required />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location-longitude" class="col-md-4 control-label">Location Longitude:</label>
                            <div class="col-md-8">
                                <input type="number" required step="0.000001" min="-90.999999" max="99.999999" class="form-control" name="locationLongitude" placeholder="Location Longitude" required />
                            </div>
                        </div>
                        
                        
                        
                        
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Location"/>
                            </div>
                        </div>
                    </form>

                </div> <!-- End col div -->

            </div> <!-- End row div -->
            <!-- Main Page Content Stop -->    
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <li role="presentation" class="active">
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
            <!-- Main Page Content Start -->
            <!-- 
    Add a row to our container - this will hold the summary table and the new
    Sighting form.
            -->
            <div class="row">
                <!-- 
                    Add a col to hold the summary table - have it take up half the row 
                -->
                <div class="col-md-6">
                    <h2>Super Hero Sightings!</h2>
                    <table id="sightingTable" class="table table-hover">
                        <tr>
                            <th width="40%">Sighting Location</th>
                            <th width="30%">Sighting Date</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentSighting" items="${sightingList}">
                            <tr>
                                <td>
                                    <a href="displaySightingDetails?sightingID=${currentSighting.sightingID}">
                                        <c:out value="${currentSighting.locationID.locationName}"/> 
                                    </a>
                                </td>
                                <td>
                                    <a href="displaySightingDetails?sightingID=${currentSighting.sightingID}">
                                    <c:out value="${currentSighting.sightingDate}"/>
                                    </a>
                                </td>
                                <td>
                                    <a href="displayEditSightingForm?sightingID=${currentSighting.sightingID}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteSighting?sightingID=${currentSighting.sightingID}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                  
                </div> <!-- End col div -->
                <!-- 
                    Add col to hold the new Sighting form - have it take up the other 
                    half of the row
                -->
                <div class="col-md-6">
                    <h2>Add A Sighting</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createSighting">
                        <div class="form-group">
                            <label for="sightingDate" class="col-md-4 control-label">
                                Date:
                            </label>
                            <div class="col-md-8">
                                <input for="add-sighting-date" type="date" required class="form-control" name="sightingDate" id="sightingDate"
                                       placeholder="Sighting Date" min="2000-01-01" max="2030-12-31" required/>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="sightingLocation" 
                                   class="col-md-4 control-label">
                                Location:
                            </label>
                            <div class="col-md-8">
                                <select name="sightingLocation" id="sightingLocation">
                                    <c:forEach var="locations" items="${locations}">
                                        <option value="${locations.locationID}">
                                            ${locations.locationName} - ${locations.locationCity}
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
                                    <c:forEach var="heros" items="${heros}">
                                        <option value="${heros.heroID}">
                                            ${heros.heroName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            
                            
                            
                        </div>
                        <div class="col-md-4">
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Sighting"/>
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
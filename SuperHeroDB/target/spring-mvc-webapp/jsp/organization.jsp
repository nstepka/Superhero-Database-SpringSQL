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
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/displayOrganizationPage">
                            Organization
                        </a>
                    </li>
                    <li role="presentation" >
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
                    <h2>Super Hero Organizations!</h2>
                    <table id="organizationTable" class="table table-hover">
                        <tr>
                            <th width="40%">Organizations Location</th>
                            <th width="30%">Organizations Date</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentOrganization" items="${organizationList}">
                            <tr>
                                <td>
                                    <a href="displayOrganizationDetails?organizationID=${currentOrganization.organizationID}">
                                        <c:out value="${currentOrganization.organizationName}"/> 
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentOrganization.organizationDescription}"/>
                                </td>
                                <td>
                                    <a href="displayEditOrganizationForm?organizationID=${currentOrganization.organizationID}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteOrganization?organizationID=${currentOrganization.organizationID}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                  
                </div> <!-- End col div -->
                <!-- 
                    Add col to hold the new Sighting form - have it take up the other 
                    half of the row   organizationDescription organizationNumber organizationEmail
                -->
                <div class="col-md-6">
                    <h2>Add A Organization</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createOrganization">
                        <div class="form-group">
                            <label for="add-organization-name" class="col-md-4 control-label">Organization Name:</label>
                            <div class="col-md-8">
                                <input type="text" required class="form-control" name="organizationName" placeholder="Organization Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-organization-Description" class="col-md-4 control-label">Description</label>
                            <div class="col-md-8">
                                <input type="text" required class="form-control" name="organizationDescription" placeholder="Organization Description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-organization-number" class="col-md-4 control-label">Phone Number</label>
                            <div class="col-md-8">
                                <input type="tel" required class="form-control" name="organizationNumber" placeholder="8884445555"
                                       pattern="[0-9]{3}[0-9]{3}[0-9]{4}" required />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-organization-email" class="col-md-4 control-label">Email:</label>
                            <div class="col-md-8">
                                <input type="email" required class="form-control" name="organizationEmail" placeholder="Email"/>
                            </div>
                        </div>
                        
                        
                        <div class="form-group">
                            <label for="organizationLocation" 
                                   class="col-md-4 control-label">
                                Location:
                            </label>
                            <div class="col-md-8">
                                <select name="organizationLocation" id="organizationLocation">
                                    <c:forEach var="locations" items="${locations}">
                                        <option value="${locations.locationID}">
                                            ${locations.locationName} - ${locations.locationCity}
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
                                <input type="submit" class="btn btn-default" value="Create Organization"/>
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
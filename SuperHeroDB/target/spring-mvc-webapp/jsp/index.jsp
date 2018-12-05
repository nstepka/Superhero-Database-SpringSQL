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
                  <li role="presentation" 
                      class="active">
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
        </div>
                          <center>
                          <b><p> This is a super hero database that allows you to add, remove, update, and delete Heros, Locations, Organizations and Sightings </p></b>
                          
                          <p> This is a list of the last ten Sightings by their location.  If you click one it will show you the details of the sighting:</p>
            <c:forEach var="sightings" items="${sightings}">
                            <tr>
                                <td> Name of Location:
                                    <a href="displaySightingDetails?sightingID=${sightings.sightingID}">
                                        <c:out value="${sightings.locationID.locationName}"/>
                                        
                                      
                                    </a>
                                    Date of Sighting: 
                                    <a href="displaySightingDetails?sightingID=${sightings.sightingID}">
                                    <c:out value="${sightings.sightingDate}"/> 
                                    </a>
                                    </br>

                                </td>                               
                            </tr>
                        </c:forEach>                
                          </center>
                          
                          
                          
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
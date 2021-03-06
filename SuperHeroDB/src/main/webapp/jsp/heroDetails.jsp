<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
            <h1>Hero Details</h1>
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
                <p>
                    Name: <c:out value="${hero.heroName}"/> 
                </p>
                <p>
                    Description: <c:out value="${hero.description}"/>
                </p>
                <p>
                    Email: <c:out value="${hero.email}"/>
                </p>
                <p>
                    Phone Number: <c:out value="${hero.phoneNumber}"/>
                </p>
                <p>
                    Super Power: <c:out value="${hero.superHeroPower}"/>
                </p>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
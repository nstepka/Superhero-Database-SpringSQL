<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Organization Details</title>
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
            <p>
                <b>  <c:out value="${organization.organizationName}"/> </b>
            </p>

            <p>
                <b> Description of Organization: </b> <c:out value="${organization.organizationDescription}"/>
            </p>
            <p>
                <b>Phone Number:</b> <c:out value="${organization.organizationNumber}"/>
            <p>
                <b> Home Base:</b> <c:out value="${organization.location.locationName}"/> 
            </p>

        </p>
        <p>
            <b>Email: </b> <c:out value="${organization.organizationEmail}"/>
        </p>
        <b>Heros Part Of Org:</b> </p>
        <c:forEach var="currentHero" items="${organization.heros}">

        <c:out value="${currentHero.heroName}"/> 
    </p>
</c:forEach>
</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
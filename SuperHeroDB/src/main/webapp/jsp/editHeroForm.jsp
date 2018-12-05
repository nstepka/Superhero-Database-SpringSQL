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

            <sf:form class="form-horizontal" role="form" modelAttribute="hero"
                     action="editHero" method="POST">
                <div class="form-group">
                    <label for="add-hero-name" class="col-md-4 control-label">Hero Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-hero-name"
                                  path="heroName" placeholder="Hero Name"/>
                        <sf:errors path="heroName" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-hero-description" class="col-md-4 control-label">Description:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-hero-description"
                                  path="description" placeholder="Hero Description"/>
                        <sf:errors path="description" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-hero-email" class="col-md-4 control-label">Email:</label>                          
                    <div class="col-md-8">
                        <sf:input type="email" class="form-control" id="add-hero-emaail"
                                  path="email" placeholder="Email"/>
                        <sf:errors path="email" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-hero-number" class="col-md-4 control-label">Pager Number:</label>
                    <div class="col-md-8">
                        
                        <sf:input type="tel" class="form-control" id="phone"
                                  path="phoneNumber" placeholder="1234567890"
                                  pattern="[0-9]{10}" required="true" />
                        <sf:errors path="phoneNumber" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-hero-superpower" class="col-md-4 control-label">Super Hero Power:</label>
                    <div class="col-md-8">
                        
                        <sf:input type="text" class="form-control" id="add-phone"
                                  path="superHeroPower" placeholder="superHeroPower"/>
                        <sf:errors path="superHeroPower" cssclass="error"></sf:errors>
                        <sf:hidden path="heroID"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Hero"/>
                    </div>
                </div>
            </sf:form>                
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
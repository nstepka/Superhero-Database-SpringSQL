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
                    <li role="presentation" class="active">
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
                    <h2>Heros</h2>
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="40%">SuperHero Name</th>
                            <th width="30%">SuperPower</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="hero" items="${heroList}">
                            <tr>
                                <td>
                                    <a href="displayHeroDetails?heroID=${hero.heroID}">
                                        <c:out value="${hero.heroName}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${hero.superHeroPower}"/>
                                </td>
                                <td>
                                    <a href="displayEditHeroForm?heroID=${hero.heroID}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteHero?heroID=${hero.heroID}">
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
                    <h2>Add New Hero</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createHero">
                        <div class="form-group">
                            <label for="add-hero-name" class="col-md-4 control-label">Hero Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="heroName" placeholder="Hero Name" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-hero-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" required required class="form-control" name="heroDiscription" placeholder="Hero Discription"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-hero-email" class="col-md-4 control-label">Email:</label>
                            <div class="col-md-8">
                                <input type="email" required class="form-control" name="heroEmail" placeholder="Hero Email" required />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-hero-number" class="col-md-4 control-label">Phone Number:</label>
                            <div class="col-md-8">
                                <input type="tel" required class="form-control" name="heroNumber" placeholder="1234567890" 
                                       id="phone"  pattern="[0-9]{3}[0-9]{3}[0-9]{4}" required />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-hero-superpower" class="col-md-4 control-label">SuperPower:</label>
                            <div class="col-md-8">
                                <input type="text" required class="form-control" name="superHeroPower" placeholder="Hero Super Powers"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Hero"/>
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
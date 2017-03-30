<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Client Service</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <c:url value="/updateClient" var="updateClientUrl"/>
</head>
<body>
<c:set var="client" value="${client}"/>
<c:set var="bill" value="${bill}"/>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul id="groupList" class="nav navbar-nav">
                <li>
                    <a href="/">
                        <button type="submit" class="btn btn-default navbar-btn">View Clients
                        </button>
                    </a>
                </li>
                <li style="margin-left: 12px;">
                    <a href="/transaction">
                        <button type="submit" class="btn btn-default navbar-btn">View Transactions
                        </button>
                    </a>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container" style="width: 50%;">
    <h3 align="center">Info about client ${client.email}</h3>
    <h2 align="center">Client balance: ${bill.balance}</h2>
    <h2 align="center">Client price model: ${billModel}</h2>
    <br/><br/>

    <h3 align="center">Change price model</h3>
    <center>
        <form action="${updateClientUrl}_${client.id}" method="POST" style="align-content: center">
            Price Model: <br/>
            <select name="pricemodel">
                <c:forEach items="${priceModel}" var="p">
                    <option value="${p.id}">${p.name}</option>
                </c:forEach>
            </select><br>
            <button class="btn btn-default" style="margin-top: 4px" type="submit">Submit</button>
        </form>
    </center>
</div>
</body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Client Service</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
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
                <form class="navbar-form navbar-left" role="search" action="/search" method="post">
                    Search from:
                    <input type="datetime-local" name="dateFrom" value="${dateFrom}">
                    Search till:
                    <input type="datetime-local" name="dateTill" value="${dateTill}">
                    <div class="form-group">
                        <input type="text" name="pattern" placeholder="Search by client email" value="${pattern}">
                    </div>
                    <button type="submit" class="btn btn-default">Search</button>
                </form>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container" style="width: 50%;">
    <h3 align="center">List of clients</h3>

    <table class="table" style="text-align: center">
        <thead>
        <tr>
            <td>Transaction ID</td>
            <td>Bill Id</td>
            <td>Amount</td>
            <td>Client</td>
            <td>Time</td>
        </tr>
        </thead>
        <c:forEach items="${transaction}" var="tr">
            <tr>
                <td><c:out value="${tr.id}"/></td>
                <td><c:out value="${tr.bill.id}"/></td>
                <td><c:out value="${tr.amount}"/></td>
                <td><c:out value="${tr.bill.owner.email}"/></td>
                <td><c:out value="${tr.time}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
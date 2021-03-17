<%--
  Created by IntelliJ IDEA.
  User: grh
  Date: 2021/3/5
  Time: 12:23 AM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <jsp:directive.include file="/WEB-INF/jsp/prelude/include-head-meta.jspf" />
    <title>My Home Page</title>
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;

            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
</head>
<body>
<form action="register" method="post">
    <h1>Register User</h1>
    name:<input type="text" name="name"><br>
    password:<input type="password" name="password"><br>
    email:<input type="text" name="email"><br>
    phone number:<input type="text" name="phoneNumber"><br>
    <input type="submit" name="submit" value="register"><br>
</form>
</body>
</html>

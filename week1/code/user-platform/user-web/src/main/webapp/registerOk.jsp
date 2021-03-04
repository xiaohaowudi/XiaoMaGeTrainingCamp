<%--
  Created by IntelliJ IDEA.
  User: grh
  Date: 2021/3/5
  Time: 12:43 AM
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
    Congratulations, <%=request.getParameter("name")%> register success !!!
</body>
</html>

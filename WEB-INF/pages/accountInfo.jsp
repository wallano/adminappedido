<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Informaci�n de la Cuenta</title>
 
<link rel="stylesheet" type="text/css" href="styles.css">
 
</head>
<body>
 
 
    <jsp:include page="_header.jsp" />
    <jsp:include page="_menu.jsp" />
 
    <div class="page-title">Informaci�n de la Cuenta</div>
 
    <div class="account-container">
  
 
        <ul>
            <li>Nombre de Usuario: ${pageContext.request.userPrincipal.name}</li>
            <li>Role:
                <ul>
                    <c:forEach items="${userDetails.authorities}" var="auth">
                        <li>${auth.authority }</li>
                    </c:forEach>
                </ul>
            </li>
        </ul>
    </div>
 
 
    <jsp:include page="_footer.jsp" />
 
</body>
</html>
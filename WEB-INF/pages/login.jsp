<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Login</title>
 
<link rel="stylesheet" type="text/css" href="styles.css">
 
</head>
<body>
 
 
    <jsp:include page="_header.jsp" />
    <jsp:include page="_menu.jsp" />
 
 
 
    <div class="page-title">Login (Para Empleados y Gerente)</div>
 
    <div class="login-container">
 
        <h3>Ingrese usuario y password</h3>
        <br>
        <!-- /login?error=true -->
        <c:if test="${param.error == 'true'}">
            <div style="color: red; margin: 10px 0px;">
 
                Autenticaci�n Fallida!!!<br /> Causa :
                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
 
            </div>
        </c:if>
 
        <form method="POST" action="${pageContext.request.contextPath}/j_spring_security_check">
            <table>
                <tr>
                    <td>Usuario *</td>
                    <td><input name="userName" /></td>
                </tr>
 
                <tr>
                    <td>Contrase�a *</td>
                    <td><input type="password" name="password" /></td>
                </tr>
 
                <tr>
                    <td>&nbsp;</td>
                    <td><input type="submit" value="Login" /> <input type="reset"
                        value="Reset" /></td>
                </tr>
            </table>
        </form>
 
        <span class="error-message">${error}</span>
 
    </div>
 
 
    <jsp:include page="_footer.jsp" />
 
</body>
</html>
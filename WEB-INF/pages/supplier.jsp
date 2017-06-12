<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Categorías de Producto</title>
 
<link rel="stylesheet" type="text/css" href="styles.css">
 
</head>
<body>
 
    <jsp:include page="_header.jsp" />
    <jsp:include page="_menu.jsp" />
 
    <div class="page-title">Proveedores</div>
    
    <c:if test="${not empty errorMessage }">
      <div class="error-message">
          ${errorMessage}
      </div>
    </c:if>
 
    <form:form modelAttribute="supplierForm" method="POST" >
        <table style="text-align:left;">
            <tr>
                <td>Código *</td>
                <td style="color:red;">
                   <c:if test="${not empty supplierForm.code}">
                        <form:hidden path="code"/>
                        ${supplierForm.code}
                   </c:if>
                   <c:if test="${empty supplierForm.code}">
                        <form:input path="code" />
                        <form:hidden path="newSupplier" />
                   </c:if>
                </td>
                <td><form:errors path="code" class="error-message" /></td>
            </tr>
 
            <tr>
                <td>Nombre *</td>
                <td><form:input path="name" /></td>
                <td><form:errors path="name" class="error-message" /></td>
            </tr>
            
            <tr>
                <td>Dirección *</td>
                <td><form:input path="address" /></td>
                <td><form:errors path="address" class="error-message" /></td>
            </tr>
            
            <tr>
                <td>Teléfono *</td>
                <td><form:input path="phone" /></td>
                <td><form:errors path="phone" class="error-message" /></td>
            </tr>
            
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" value="Guardar" /> <input type="reset"
                    value="Reset" /></td>
            </tr>
        </table>
    </form:form>
 
 
 
 
    <jsp:include page="_footer.jsp" />
 
</body>
</html>
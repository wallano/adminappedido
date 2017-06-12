<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Producto</title>
 
<link rel="stylesheet" type="text/css" href="styles.css">
 
</head>
<body>
 
    <jsp:include page="_header.jsp" />
    <jsp:include page="_menu.jsp" />
 
    <div class="page-title">Inventario</div>
    
    <c:if test="${not empty errorMessage }">
      <div class="error-message">
          ${errorMessage}
      </div>
    </c:if>
 
    <form:form modelAttribute="inventoryForm" method="POST" >
        <table style="text-align:left;">
        
        	<tr>
                <td>Code *</td>
                <td style="color:red;">
                   <c:if test="${not empty inventoryForm.code}">
                        <form:hidden path="code"/>
                        ${inventoryForm.code}
                   </c:if>
                   <c:if test="${empty inventoryForm.code}">
                        <form:input path="code" />
                        <form:hidden path="newInventory" />
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
                <td>Precio *</td>
                <td><form:input path="price" /></td>
                <td><form:errors path="price" class="error-message" /></td>
            </tr>
            
            <tr>
                <td>Costo Anterior*</td>
                <td><form:input path="previous_Cost" /></td>
                <td><form:errors path="previous_Cost" class="error-message" /></td>
            </tr>
            
            <tr>
                <td>Costo Promedio*</td>
                <td><form:input path="average_Cost" /></td>
                <td><form:errors path="average_Cost" class="error-message" /></td>
            </tr>
            
            <tr>
                <td>Costo Actual*</td>
                <td><form:input path="current_Cost" /></td>
                <td><form:errors path="current_Cost" class="error-message" /></td>
            </tr>
            
            <tr>
                <td>Unidades Disponibles *</td>
                <td><form:input path="unidades_Disponibles" /></td>
                <td><form:errors path="unidades_Disponibles" class="error-message" /></td>
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
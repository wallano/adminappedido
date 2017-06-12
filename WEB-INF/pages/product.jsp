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
 
    <div class="page-title">Producto</div>
    
    <c:if test="${not empty errorMessage }">
      <div class="error-message">
          ${errorMessage}
      </div>
    </c:if>
 
    <form:form modelAttribute="productForm" method="POST" enctype="multipart/form-data">
        <table style="text-align:left;">
        
        	<tr>
		        <td>Categoría: *</td>
		        <td>
		            <form:select path="code_Categorie">
		            <form:option value="0" label="Seleccione" />
		            <c:forEach var="categorie" items="${listCategories}">

                <option value="${categorie.code}">${categorie.name}</option>

            </c:forEach>
		            
		            </form:select>
		        </td>
		    </tr>
			        
            <tr>
                <td>Código *</td>
                <td style="color:red;">
                   <c:if test="${not empty productForm.code}">
                        <form:hidden path="code"/>
                        ${productForm.code}
                   </c:if>
                   <c:if test="${empty productForm.code}">
                        <form:input path="code" />
                        <form:hidden path="newProduct" />
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
                <td>Costo *</td>
                <td><form:input path="cost" /></td>
                <td><form:errors path="cost" class="error-message" /></td>
            </tr>
            
            <tr>
                <td>Iva *</td>
                <td><form:input path="iva" /></td>
                <td><form:errors path="iva" class="error-message" /></td>
            </tr>
            
            <tr>
                <td>Unidades Disponibles *</td>
                <td><form:input path="unidades_Disponibles" /></td>
                <td><form:errors path="unidades_Disponibles" class="error-message" /></td>
            </tr>
            
            <tr>
                <td>Ipoconsumo *</td>
                <td><form:input path="ipoconsumo" /></td>
                <td><form:errors path="ipoconsumo" class="error-message" /></td>
            </tr>
            
            <tr>
                <td>Descripción *</td>
                <td><form:textarea path="description" /></td>
                <td><form:errors path="description" class="error-message" /></td>
            </tr>
            <tr>
                <td>Imagen</td>
                <td>
                <img src="${pageContext.request.contextPath}/productImage?code=${productForm.code}" width="100"/></td>
                <td> </td>
            </tr>
            <tr>
                <td>Subir Imagen</td>
                <td><form:input type="file" path="fileData"/></td>
                <td> </td>
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
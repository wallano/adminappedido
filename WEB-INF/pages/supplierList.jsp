<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Proveedores</title>
 
<link rel="stylesheet" type="text/css" href="styles.css">
 
</head>
<body>
 
    <jsp:include page="_header.jsp" />
    <jsp:include page="_menu.jsp" />
    
    <fmt:setLocale value="en_US" scope="session"/>
 
    <div class="page-title">Lista de Proveedores</div>
  
 
 <form name="forma">
    <c:forEach items="${paginationSuppliers.list}" var="supplierInfo">
        <div class="product-preview-container">
            <ul>
            	<li>Codigo: ${supplierInfo.code}</li>
                <li>Nombre: ${supplierInfo.name}</li>
                <!-- For Manager edit Product -->
                <security:authorize  access="hasRole('ROLE_MANAGER')">
                  <li><a style="color:red;"
                      href="${pageContext.request.contextPath}/supplier?code=${supplierInfo.code}">
                        Editar Proveedor</a></li>
                   <li><a style="color:red;"
                      onclick="eliminarProveedor('${pageContext.request.contextPath}/supplierDelete?code=${supplierInfo.code}')">
                        Eliminar Proveedor</a></li>
                </security:authorize>
            </ul>
        </div>
 
    </c:forEach>
    <br/>
    
  
    <c:if test="${paginationSuppliers.totalPages > 1}">
        <div class="page-navigator">
           <c:forEach items="${paginationSuppliers.navigationPages}" var = "page">
               <c:if test="${page != -1 }">
                 <a href="supplierList?page=${page}" class="nav-item">${page}</a>
               </c:if>
               <c:if test="${page == -1 }">
                 <span class="nav-item"> ... </span>
               </c:if>
           </c:forEach>
            
        </div>
    </c:if>
 </form>
    <jsp:include page="_footer.jsp" />
    <script type="text/javascript">
    
    	function eliminarProveedor(codigo){
    		var resp = confirm("Está seguro de borrar el proveedor?");
    		if(resp == true){
    			location.href = codigo;
       		}
	  	}
    
    </script>
 
</body>
</html>
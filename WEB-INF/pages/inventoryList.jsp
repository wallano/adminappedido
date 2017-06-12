<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Inventario</title>
 
<link rel="stylesheet" type="text/css" href="styles.css">
 
</head>
<body>
 
    <jsp:include page="_header.jsp" />
    <jsp:include page="_menu.jsp" />
    
    <fmt:setLocale value="en_US" scope="session"/>
 
    <div class="page-title">Lista de Inventario</div>
  
 <form>
 
  
    <c:forEach items="${paginationInventories.list}" var="inventoryInfo">
        <div class="product-preview-container">
            <ul>
                <li>Codigo: ${inventoryInfo.code}</li>
                <li>Nombre: ${inventoryInfo.name}</li>
                <li>Precio: <fmt:formatNumber value="${inventoryInfo.price}" type="currency"/></li>
                <!-- For Manager edit Product -->
                <security:authorize  access="hasRole('ROLE_MANAGER')">
                  <li><a style="color:red;"
                      href="${pageContext.request.contextPath}/inventory?code=${inventoryInfo.code}">
                        Editar Producto de Inventario</a></li>
                        
                  <li><a style="color:red;"
                      onclick="eliminarInventario('${pageContext.request.contextPath}/inventoryDelete?code=${inventoryInfo.code}')">
                        Eliminar Producto de Inventario</a></li>
                </security:authorize>
            </ul>
        </div>
 
    </c:forEach>
    <br/>
  
  </form>  
  
    <c:if test="${paginationInventories.totalPages > 1}">
        <div class="page-navigator">
           <c:forEach items="${paginationInventories.navigationPages}" var = "page">
               <c:if test="${page != -1 }">
                 <a href="inventoryList?page=${page}" class="nav-item">${page}</a>
               </c:if>
               <c:if test="${page == -1 }">
                 <span class="nav-item"> ... </span>
               </c:if>
           </c:forEach>
            
        </div>
    </c:if>
 
    <jsp:include page="_footer.jsp" />
    
    <script type="text/javascript">
    
    	function eliminarInventario(codigo){
    		var resp = confirm("Está seguro de borrar el producto de inventario?");
    		if(resp == true){
    			location.href = codigo;
       		}
	  	}
    
    </script>
 
</body>
</html>
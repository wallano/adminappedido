<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Categorías</title>
 
<link rel="stylesheet" type="text/css" href="styles.css">
 
</head>
<body>
 
    <jsp:include page="_header.jsp" />
    <jsp:include page="_menu.jsp" />
    
    <fmt:setLocale value="en_US" scope="session"/>
 
    <div class="page-title">Lista de Categorías</div>
  
 
 <form name="forma">
    <c:forEach items="${paginationCategories.list}" var="categorieInfo">
        <div class="product-preview-container">
            <ul>
                <li><img class="product-image"
                    src="${pageContext.request.contextPath}/categorieImage?code=${categorieInfo.code}" /></li>
                <li>Codigo: ${categorieInfo.code}</li>
                <li>Nombre: ${categorieInfo.name}</li>
               <%--  <li><a
                    href="${pageContext.request.contextPath}/buyProduct?code=${categorieInfo.code}">
                        Comprar Ahora</a></li> --%>
                <!-- For Manager edit Product -->
                <security:authorize  access="hasRole('ROLE_MANAGER')">
                  <li><a style="color:red;"
                      href="${pageContext.request.contextPath}/categorie?code=${categorieInfo.code}">
                        Editar Categoría</a></li>
                   <li><a style="color:red;"
                      onclick="eliminarCategoria('${pageContext.request.contextPath}/categorieDelete?code=${categorieInfo.code}')">
                        Eliminar Categoría</a></li>
                </security:authorize>
            </ul>
        </div>
 
    </c:forEach>
    <br/>
    
  
    <c:if test="${paginationCategories.totalPages > 1}">
        <div class="page-navigator">
           <c:forEach items="${paginationCategories.navigationPages}" var = "page">
               <c:if test="${page != -1 }">
                 <a href="categorieList?page=${page}" class="nav-item">${page}</a>
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
    
    	function eliminarCategoria(codigo){
    		var resp = confirm("Está seguro de borrar la categoría?");
    		if(resp == true){
    			location.href = codigo;
       		}
	  	}
    
    </script>
 
</body>
</html>
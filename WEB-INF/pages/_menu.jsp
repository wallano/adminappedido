<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>   
 
 
<div class="menu-container">
  
   <a href="${pageContext.request.contextPath}/">Home</a>
   |
   <a href="${pageContext.request.contextPath}/productList">
      Lista de Productos
   </a>
   |
   <a href="${pageContext.request.contextPath}/categorieList">
      Lista de Categorías
   </a>
   |
   <security:authorize  access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
     <a href="${pageContext.request.contextPath}/orderList">
         Lista de Pedidos
     </a>
     |
   </security:authorize>
   <security:authorize  access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
     <a href="${pageContext.request.contextPath}/supplierList">
         Lista de Proveedores
     </a>
     |
   </security:authorize>
   <security:authorize  access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
     <a href="${pageContext.request.contextPath}/clientList">
         Lista de Clientes
     </a>
     |
   </security:authorize>
   
   <security:authorize  access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
     <a href="${pageContext.request.contextPath}/inventoryList">
         Lista de Productos de Inventario
     </a>
     |
   </security:authorize>
   
   <security:authorize  access="hasRole('ROLE_MANAGER')">
         <a href="${pageContext.request.contextPath}/product">
                        Crear un Producto
         </a>
         |
   </security:authorize>
   
   <security:authorize  access="hasRole('ROLE_MANAGER')">
         <a href="${pageContext.request.contextPath}/categorie">
                        Crear una Categoría
         </a>
         |
   </security:authorize>
   
   <security:authorize  access="hasRole('ROLE_MANAGER')">
         <a href="${pageContext.request.contextPath}/supplier">
                        Crear un Proveedor
         </a>
         |
   </security:authorize>
   
   <security:authorize  access="hasRole('ROLE_MANAGER')">
         <a href="${pageContext.request.contextPath}/client">
                        Crear un Cliente
         </a>
         |
   </security:authorize>
   
   <security:authorize  access="hasRole('ROLE_MANAGER')">
         <a href="${pageContext.request.contextPath}/inventory">
                        Crear un Producto de Inventario
         </a>
         |
   </security:authorize>
   
   <security:authorize  access="hasRole('ROLE_MANAGER')">
         <a href="${pageContext.request.contextPath}/inventory">
                        Cargar Inventario
         </a>
         |
   </security:authorize>
   
   <security:authorize  access="hasRole('ROLE_MANAGER')">
         <a href="${pageContext.request.contextPath}/inventory">
                        Descargar Inventario
         </a>
         |
   </security:authorize>
  
</div>
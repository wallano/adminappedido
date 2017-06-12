<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Confirmación de la Compra</title>
 
<link rel="stylesheet" type="text/css" href="styles.css">
 
</head>
<body>
   <jsp:include page="_header.jsp" />
  
   <jsp:include page="_menu.jsp" />
  
   <fmt:setLocale value="en_US" scope="session"/>
 
   <div class="page-title">Confirmación</div>
  
  
  
   <div class="customer-info-container">
       <h3>Información del Cliente:</h3>
       <ul>
           <li>Nombre: ${myCart.customerInfo.name}</li>
           <li>Email: ${myCart.customerInfo.email}</li>
           <li>Teléfono: ${myCart.customerInfo.phone}</li>
           <li>Dirección: ${myCart.customerInfo.address}</li>
       </ul>
       <h3>Resumen de la Compra:</h3>
       <ul>
           <li>Cantidad Total: ${myCart.quantityTotal}</li>
           <li>Total:
           <span class="total">
             <fmt:formatNumber value="${myCart.amountTotal}" type="currency"/>
           </span></li>
       </ul>
   </div>
 
   <form method="POST"
       action="${pageContext.request.contextPath}/shoppingCartConfirmation">
 
       <!-- Edit Cart -->
       <a class="navi-item"
           href="${pageContext.request.contextPath}/shoppingCart">Editar la Compra</a>
 
       <!-- Edit Customer Info -->
       <a class="navi-item"
           href="${pageContext.request.contextPath}/shoppingCartCustomer">Editar Información del Cliente</a>
 
       <!-- Send/Save -->
       <input type="submit" value="Enviar" class="button-send-sc" />
   </form>
 
   <div class="container">
 
       <c:forEach items="${myCart.cartLines}" var="cartLineInfo">
           <div class="product-preview-container">
               <ul>
                   <li><img class="product-image"
                       src="${pageContext.request.contextPath}/productImage?code=${cartLineInfo.productInfo.code}" /></li>
                   <li>Code: ${cartLineInfo.productInfo.code} <input
                       type="hidden" name="code" value="${cartLineInfo.productInfo.code}" />
                   </li>
                   <li>Nombre: ${cartLineInfo.productInfo.name}</li>
                   <li>Precio: <span class="price">
                      <fmt:formatNumber value="${cartLineInfo.productInfo.price}" type="currency"/>
                   </span>
                   </li>
                   <li>Cantidad: ${cartLineInfo.quantity}</li>
                   <li>Subtotal:
                     <span class="subtotal">
                        <fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/>
                     </span>
                   </li>
               </ul>
           </div>
       </c:forEach>
 
   </div>
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>
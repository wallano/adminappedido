<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Productos</title>
 
<link rel="stylesheet" type="text/css" href="styles.css">
 
</head>
<body>
 
    <jsp:include page="_header.jsp" />
    <jsp:include page="_menu.jsp" />
     
    <fmt:setLocale value="en_US" scope="session"/>
 
    <div class="page-title">Información de Pedidos</div>
 
    <div class="customer-info-container">
        <h3>Información del Cliente:</h3>
        <ul>
            <li>Nombre: ${orderInfo.customerName}</li>
            <li>Email: ${orderInfo.customerEmail}</li>
            <li>Teléfono: ${orderInfo.customerPhone}</li>
            <li>Dirección: ${orderInfo.customerAddress}</li>
        </ul>
        <h3>Resumen del Pedido:</h3>
        <ul>
            <li>Total:
            <span class="total">
            <fmt:formatNumber value="${orderInfo.amount}" type="currency"/>
            </span></li>
        </ul>
    </div>
     
    <br/>
     
    <table border="1" style="width:100%">
        <tr>
            <th>Código del Producto</th>
            <th>Npmbre del Producto</th>
            <th>Quantity</th>
            <th>Precio</th>
            <th>Amount</th>
        </tr>
        <c:forEach items="${orderInfo.details}" var="orderDetailInfo">
            <tr>
                <td>${orderDetailInfo.productCode}</td>
                <td>${orderDetailInfo.productName}</td>
                <td>${orderDetailInfo.quanity}</td>
                <td>
                 <fmt:formatNumber value="${orderDetailInfo.price}" type="currency"/>
                </td>
                <td>
                 <fmt:formatNumber value="${orderDetailInfo.amount}" type="currency"/>
                </td>  
            </tr>
        </c:forEach>
    </table>
    <c:if test="${paginationResult.totalPages > 1}">
        <div class="page-navigator">
           <c:forEach items="${paginationResult.navigationPages}" var = "page">
               <c:if test="${page != -1 }">
                 <a href="orderList?page=${page}" class="nav-item">${page}</a>
               </c:if>
               <c:if test="${page == -1 }">
                 <span class="nav-item"> ... </span>
               </c:if>
           </c:forEach>
             
        </div>
    </c:if>
 
 
 
 
    <jsp:include page="_footer.jsp" />
 
</body>
</html>
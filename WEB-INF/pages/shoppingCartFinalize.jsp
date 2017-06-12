<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Finalización de la Compra</title>
 
<link rel="stylesheet" type="text/css" href="styles.css">
 
</head>
<body>
    <jsp:include page="_header.jsp" />
 
    <jsp:include page="_menu.jsp" />
 
    <div class="page-title">Finalización de la Compra</div>
    
    <div class="container">
        <h3>Gracias por su Compra</h3>
        Su número de orden es: ${lastOrderedCart.orderNum}
    </div>
 
    <jsp:include page="_footer.jsp" />
 
</body>
</html>
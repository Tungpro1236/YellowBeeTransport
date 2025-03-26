<%-- 
    Document   : form
    Created on : Mar 23, 2025, 5:26:08 PM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${priceQuote == null ? 'Create' : 'Edit'} Price Quote</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h1>${priceQuote == null ? 'Create' : 'Edit'} Price Quote</h1>
        
        <form action="${pageContext.request.contextPath}/price-quote${priceQuote == null ? '/create' : '/edit/'.concat(priceQuote.priceQuoteID)}" 
              method="post" class="needs-validation" novalidate>
            
            <div class="mb-3">
                <label for="truckAmount" class="form-label">Truck Amount</label>
                <input type="number" class="form-control" id="truckAmount" name="truckAmount" 
                       value="${priceQuote.truckAmount}" required min="1">
                <div class="invalid-feedback">Please enter a valid truck amount.</div>
            </div>
            
            <div class="mb-3">
                <label for="staffAmount" class="form-label">Staff Amount</label>
                <input type="number" class="form-control" id="staffAmount" name="staffAmount" 
                       value="${priceQuote.staffAmount}" required min="1">
                <div class="invalid-feedback">Please enter a valid staff amount.</div>
            </div>
            
            <div class="mb-3">
                <label for="priceCostID" class="form-label">Price Cost Type</label>
                <select class="form-select" id="priceCostID" name="priceCostID" required>
                    <option value="">Select a price cost type</option>
                    <option value="1" ${priceQuote.priceCostID == 1 ? 'selected' : ''}>Truck 1 Tons (400,000 VND)</option>
                    <option value="2" ${priceQuote.priceCostID == 2 ? 'selected' : ''}>Truck 1.5 Tons (600,000 VND)</option>
                    <option value="3" ${priceQuote.priceCostID == 3 ? 'selected' : ''}>Truck 2 Tons (800,000 VND)</option>
                </select>
                <div class="invalid-feedback">Please select a price cost type.</div>
            </div>
            
            <div class="mb-3">
                <label for="checkingFormID" class="form-label">Checking Form ID</label>
                <input type="number" class="form-control" id="checkingFormID" name="checkingFormID" 
                       value="${priceQuote.checkingFormID}" required min="1">
                <div class="invalid-feedback">Please enter a valid checking form ID.</div>
            </div>
            
            <button type="submit" class="btn btn-primary">Save</button>
            <a href="${pageContext.request.contextPath}/price-quote" class="btn btn-secondary">Cancel</a>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Form validation
        (function () {
            "use strict";
            var forms = document.querySelectorAll('.needs-validation');
            Array.prototype.slice.call(forms).forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        })();
    </script>
</body>
</html> 
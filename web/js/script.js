'use strict';

function deleteCoupon(e) {
    var couponId = e.target.id;
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            loadCoupons();
        }
    };

    xhttp.open("DELETE", "/rest/coupons/" + couponId, true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send();
}

function loadCoupons() {
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var result = JSON.parse(this.response);

            // Dohvati tabelu iz DOM-a po id-u
            var table = document.getElementById("coupons-tbl");

            // Dohvati prvi tbody
            var oldTBody = table.tBodies[0];
            var newTBody = document.createElement("tBody");

            for (var i = 0; i < result.length; i++) {

                var bRow = document.createElement("tr"); // Kreiraj novi red

                // Postavi vrednosti za taj red iz rezultata sa servera
                var tdProduct = document.createElement("td");
                tdProduct.innerHTML = result[i].product;

                var tdShop = document.createElement("td");
                tdShop.innerHTML = result[i].shop;

                var tdDiscountedPrice = document.createElement("td");
                tdDiscountedPrice.classList.add("green");
                tdDiscountedPrice.innerHTML = result[i].discountedPrice + " din";

                var tdOriginalPrice = document.createElement("td");
                tdOriginalPrice.classList.add("red");
                tdOriginalPrice.innerHTML = result[i].originalPrice + " din";

                var tdDiscountPercentage = document.createElement("td");
                tdDiscountPercentage.innerHTML = result[i].discountPercentage.toFixed(2) + " %";

                var tdDeleteBtn = document.createElement("td");
                var deleteBtn = document.createElement("input");
                deleteBtn.type = "button";
                deleteBtn.value = "Obrisi";
                deleteBtn.classList.add("delete-btn");
                deleteBtn.id = result[i].id;
                deleteBtn.addEventListener("click", deleteCoupon);
                tdDeleteBtn.appendChild(deleteBtn);

                bRow.appendChild(tdProduct);
                bRow.appendChild(tdShop);
                bRow.appendChild(tdDiscountedPrice);
                bRow.appendChild(tdOriginalPrice);
                bRow.appendChild(tdDiscountPercentage);
                bRow.appendChild(tdDeleteBtn);

                newTBody.appendChild(bRow)
            }

            table.replaceChild(newTBody, oldTBody)
        }
    };

    xhttp.open("GET", "/rest/coupons", true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send();
}

function loadShops() {
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var result = JSON.parse(this.response);
            var shopsDropdown = document.getElementById("shops-dd");

            for (var i = 0; i < result.length; i++) {
                var option = document.createElement("option");
                option.innerHTML = result[i].name;
                option.value = result[i].name;

                shopsDropdown.appendChild(option);
            }
        }
    };

    xhttp.open("GET", "/rest/shops", true);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send();
}

window.onload = function () {
    loadCoupons();
    loadShops();
};


function addCoupon(product, shop, discountedPrice, originalPrice) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            loadCoupons();
        }
    };

    var coupon = {
        product: product,
        shop: shop,
        discountedPrice: discountedPrice,
        originalPrice: originalPrice
    }

    xhttp.open("POST", "/rest/coupons", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(coupon));
}

function processForm(e) {
    if (e.preventDefault) e.preventDefault();
    var formData = new FormData(e.target);

    var product = formData.get("product");
    var shop = formData.get("shop");
    var discountedPrice = formData.get("discounted-price");
    var originalPrice = formData.get("original-price");
    addCoupon(product, shop, discountedPrice, originalPrice);

    var form = document.getElementById("add-coupon-form");
    form.reset();

    // Obavezno vratiti false da bi se pregazilo default-no ponasanje prilikom submit-a.
    return false;
}

var form = document.getElementById('add-coupon-form');
if (form.attachEvent) {
    // IE support
    form.attachEvent("submit", processForm);
} else {
    form.addEventListener("submit", processForm);
}

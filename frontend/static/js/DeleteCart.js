function D_ajax(url, data, token, onsuccess, onfail) {
    var xmlhttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlhttp.open("POST", url, true);
    xmlhttp.setRequestHeader("Content-type", "application/json;charset=UTF-8");
    xmlhttp.setRequestHeader("Authorization", token);
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4) {
            if (xmlhttp.status == 200) {
                onsuccess(xmlhttp.responseText);//成功
            } else {
                onfail(xmlhttp.status);//失败

            }
        }
    }
    xmlhttp.send(data); //这时才开始发送请求
}

function DeleteCart(e) {
    var product_id = e.target.id;
    console.log("product_id" + product_id);
    var arr = document.cookie.split(";");
    var token;
    arr.forEach(function (cookie) {
        var key = cookie.split("=");
        if (key[0] == 'token') token = key[1];
    })

    var data = {
        "product_id": product_id
    }
    var stringData = JSON.stringify(data);

    D_ajax("https://itslucas.me/api/removeCart",
        stringData,
        token,
        function (resText) {
            console.log("removeCart success");
        }, function (resStatus) {
            if (resStatus == 403) {
                console.log("token invalid");
                window.location.href = "/login.html";
            } else {
                console.log("removeCart failed " + resStatus);
                //  window.location.href = "/product.html";
            }
        });
}
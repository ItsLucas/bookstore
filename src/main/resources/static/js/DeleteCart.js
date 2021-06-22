function ajax(url, data, token, onsuccess, onfail) {
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
    var token = document.cookie;

    var data = {
        "name": product_id
    }
    var stringData = JSON.stringify(data);

    ajax("../api/removeCart",
        stringData,
        token,
        function (resText) {
            console.log("removeCart success");
        }, function (resStatus) {
            if (resStatus == 403) {
                console.log("token invalid");
                window.location.href = "/login";
            } else {
                console.log("removeCart failed");
                window.location.href = "/products";
            }
        });
}
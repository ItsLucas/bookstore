function P_ajax(url, token, onsuccess, onfail) {
    var xmlhttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlhttp.open("POST", url, true);
    xmlhttp.setRequestHeader("Authorization", token);
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4) {
            if (xmlhttp.status == 200) {
                onsuccess(xmlhttp.responseText);//成功
            } else if (xmlhttp.status == 403) {
                onfail();//失败
            }
        }
    }
    xmlhttp.send(); //这时才开始发送请求
}

function PlaceOrder() {
    var arr = document.cookie.split(";");
    //  console.log(document.cookie);
    var token;
    arr.forEach(function (cookie) {
        var key = cookie.split("=");
        if (key[0] == 'token') token = key[1];
    })

    //token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWNhcyIsImV4cCI6MTYyMTMzMzExNX0.sPUL_40qn9MUAmdjYhaLXCX7H_qPLCiI9-NRmYI3EOtPSChNNTk0d5kQK7a3FriEeDKYN9gJd1NzDc9NPQGAkw";
    var data;
    if (token) {
        P_ajax("https://itslucas.me/api/placeorder", token,
            function (resText) {
                console.log("token valid");
            }, function () {
                console.log("token invalid");
                window.location.href = "/login.html";
            })
    } else {
        console.log("token null");
        window.location.href = '/login.html';
    }
}
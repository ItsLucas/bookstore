function ajax(url, token, onsuccess, onfail) {
    var xmlhttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlhttp.open("GET", url, true);
    xmlhttp.setRequestHeader("Authorization", token);
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4) {
            if (xmlhttp.status == 200) {
                onsuccess(xmlhttp.responseText);//成功
            }
            else if (xmlhttp.status == 403) {
                onfail();//失败
            }
        }
    }
    xmlhttp.send(); //这时才开始发送请求
}
function validate(baseUrl, callback) {
    var arr = document.cookie.split("；");
    //  console.log(document.cookie);
    var token;
    arr.forEach(function (cookie) {
        var key = cookie.split("=");
        if (key[0] == 'token') token = key[1];
    })

    //token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdWNhcyIsImV4cCI6MTYyMTMzOTAxOH0.taYr8_ugCAzI8ST4JNA-KP_pjcDZGNMC7BvKV6wjapTSTuZNNbMwJ6Ip6md3yx819YSb5RSS3Vyhc42oIdynAQ";
    var data;
    if (token) {
        ajax(baseUrl, token,
            function (resText) {
                console.log("token valid");
                data = resText;
                if (data) {
                    callback(data);
                    console.log(data);
                }
            }, function () {
                console.log("token invalid");
                 window.location.href="../login.html";
            })
    }
    else {
        console.log("token null");
         window.location.href = '../login.html';
    }
}
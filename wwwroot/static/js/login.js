function ajax(url, data, onsuccess, onfail) {
    var xmlhttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlhttp.open("POST", url, true);
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4) {
            if (xmlhttp.status == 200) {
                var token = xmlhttp.getResponseHeader("token");
                //   console.log(token);
                onsuccess(xmlhttp.responseText, token);//成功
            }
            else {
                onfail(xmlhttp.status);//失败
            }
        }
    }
    xmlhttp.send(data); //这时才开始发送请求
}

function loginClick() {
    console.log("click");
    var name = document.getElementById("username").value;
    var psw = document.getElementById("password").value;
    var data = {
        "username": name,
        "password": psw
    }
    var stringData = JSON.stringify(data);
    if (psw != "" && name != "") {
        ajax("https://itslucas.me/login", stringData,
            function (resText, token) {
                if (resText == "fail") {
                    alert("用户名或密码错误！");
                    return false;
                }
                else if (token == '') {
                    console.log("token设置失败");
                }
                else {
                    document.cookie = 'token=' + token;
                    console.log(document.cookie);
                    console.log(token);
                    window.location.href = '../product.html';
                }
            }, function (resStatus) {

                console.log(resStatus);
            })
    }
    else {
        alert("请输入完整登陆信息！");
        return false;
    }

}

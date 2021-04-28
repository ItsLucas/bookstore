function ajax(url,onsuccess,onfail)
{
    var xmlhttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlhttp.open("POST", url, true);
    xmlhttp.onreadystatechange = function ()
    {
        if (xmlhttp.readyState == 4)
        {
            if (xmlhttp.status == 200)
            {
                var token = xmlhttp.getResponseHeader("token");
                onsuccess(xmlhttp.responseText,token);//成功
            }
            else
            {
                onfail(xmlhttp.status);//失败
            }
        }
    }
    xmlhttp.send(); //这时才开始发送请求
}

function loginClick(){
    var name = document.getElementById("username").value;
    var psw = document.getElementById("password").value;
    if (psw != "" && name != "") {
        ajax("../login?userName=" + name + "&password=" + psw,
            function (resText,token) {
                if (resText == "fail") {
                    alert("用户名或密码错误！");
                    return false;
                }
                else if(token==''){
                    console.log("token设置失败");
                }
                else {
                    document.cookie = username+'='+token;
                    window.location.href = '/product';
                }
            })
    }
    else {
        alert("请输入完整登陆信息！");
        return false;
    }

}

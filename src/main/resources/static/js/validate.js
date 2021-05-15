function ajax(url,token,onsuccess,onfail)
{
    var xmlhttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlhttp.open("POST", url, true);
    xmlhttp.setRequestHeader("Authorization",token);
    xmlhttp.onreadystatechange = function ()
    {
        if (xmlhttp.readyState == 4)
        {
            if (xmlhttp.status == 200)
            {
                onsuccess(xmlhttp.responseText);//成功
            }
            else if(xmlhttp.status == 403)
            {
                onfail();//失败
            }
        }
    }
    xmlhttp.send(); //这时才开始发送请求
}
function validate(baseUrl){
    var token = document.cookie;
    if(token){
        ajax(baseUrl,token,
            function (resText){
                console.log("token valid");
                var data = JSON.parse(resText);
                return data;
        },function (){
            console.log("token invalid");
            window.location.href="/login";
            })
    }
    else{
        console.log("token null");
        window.location.href = '/login';
    }
}
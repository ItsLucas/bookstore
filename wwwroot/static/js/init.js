function ajax(url,onsuccess,onfail)
{
    var xmlhttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlhttp.open("GET", url, true);
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
function init(url){
    ajax(url,function (resText){
        var data = JSON.parse(resText);
        return data;
    },function (){
        console.log("fail");
    });
}
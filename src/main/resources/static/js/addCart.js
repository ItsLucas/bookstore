function ajax(url,data,token,onsuccess,onfail)
{
    var xmlhttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    xmlhttp.open("POST", url, true);
    xmlhttp.setRequestHeader("Content-type","application/json;charset=UTF-8");
    xmlhttp.setRequestHeader("Authorization",token);
    xmlhttp.onreadystatechange = function ()
    {
        if (xmlhttp.readyState == 4)
        {
            if (xmlhttp.status == 200)
            {
                onsuccess(xmlhttp.responseText);//成功
            }
            else
            {
                onfail(xmlhttp.status);//失败

            }
        }
    }
    xmlhttp.send(data); //这时才开始发送请求
}
function addCart(){
    var token = document.cookie;

    var quantity = document.getElementById("quantity").value;
    var product_id = document.getElementById("product_id").value;
    var data={
        "quantity":quantity,
        "product_id":product_id
    }
    var stringData=JSON.stringify(data);

    ajax("../api/addCart",
        stringData,
        token,
        function (resText){
        console.log("addCart success");
    },function (resStatus){
        if(resStatus == 403) {
            console.log("token invalid");
            window.location.href="/login";
        }
        else{
            console.log("addCart failed");
            window.location.href="/products";
        }
    });
}
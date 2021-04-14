function $(id) {
    return typeof id == 'string' ? document.getElementById(id) : id;

}


window.onload = function () {
    var container = document.getElementById('container');
    var list = document.getElementById('list');
    var buttons = document.getElementById('buttons').getElementsByTagName('span');
    var prev = document.getElementById('prev');
    var next = document.getElementById('next');
    var index = 1;
    var len = 5;
    var animated = false;
    var interval = 4000;
    var timer;
    var re_n = /[\s]+/g;

    var oMask = $("mask"),
        oBorder = $("Border"),
        oBtnLogin = $('btnLogin'),
        oBtnR = $("btnR"),
        oInnerLogin = $("Inner-Login"),
        oInnerRes = $("Inner-Res"),
        oULOne = $("UL-1"),
        oULTwo = $("UL-2"),
        oClose = $("Close"),
        //表单验证

        oInputOne = $("Login-Input-1"),//登录账号
        oInputTwo = $("Login-Input-2"),//登录密码
        oInputThree = $("Login-Input-3"),//注册账号
        oInputFour = $('Login-Input-4'),//验证码
        oErrorOne = $("Login-Input-Error-1"),//登录账号格式错误
        oErrorTwo = $("Login-Input-Error-2"),//登录密码格式错误
        oErrorThree = $("Login-Input-Error-3"),//注册账号格式错误
        oErrorFour = $('Login-Input-Error-4');//验证码错误;


    //登录账号

    oInputOne.onblur = function () { //失去焦点
        if (this.value == "") {
            oErrorOne.innerHTML = "不能为空！";
        } else if (this.value.length != 11) { //
            oErrorOne.innerHTML = "手机号码不正确！";
        } else {
            oErrorOne.innerHTML = "";
        }

    }
    //登录密码
    oInputTwo.onblur = function () { //失去焦点
        if (this.value == "") {
            oErrorTwo.innerHTML = "不能为空！";
        } else if (this.value.length < 6) { //
            oErrorTwo.innerHTML = "长度小于6！";
        } else if (this.value.length > 16) { //
            oErrorTwo.innerHTML = "长度大于16！";
        } else if (re_n.test(this.value)) {
            oErrorTwo.innerHTML = "含非法字符！";
        } else { //
            oErrorTwo.innerHTML = "";
        }
    }
//	//注册账号
    oInputThree.onblur = function () {//失去焦点
        if (this.value == "") {
            oErrorThree.innerHTML = "不能为空！";
        } else if (this.value.length != 11) {//
            oErrorThree.innerHTML = "手机号码不正确！";
        } else {
            oErrorThree.innerHTML = "";
        }

    }
    //	//验证码
    //	oInputFour.onblur=function(){//失去焦点
    //
    //
    //	}
    //
    //
    //
    //


    //点击登录按钮
    oBtnLogin.onclick = function () {
        oInnerRes.className = " ";
        oInnerLogin.className = "Select";
        //获取页面的高度和宽度
        var sWidth = document.body.scrollWidth;
        var sHeight = document.body.scrollHeight;
        //	//获取页面的可视区域高度和宽度
        var wHeight = document.documentElement.clientHeight;
        //alert(wHeight);
        oMask.style.height = sHeight + "px";
        oMask.style.width = sWidth + "px";
        oMask.style.display = "block";
        oBorder.style.display = "block";
        //	//获取登陆框的宽和高
        var dHeight = oBorder.offsetHeight;
        var dWidth = oBorder.offsetWidth;
        //	//设置登陆框的left和top
        oBorder.style.left = sWidth / 2 - dWidth / 2 + "px";
        oBorder.style.top = wHeight / 2 - dHeight / 2 + "px";
    }

    //点击注册按钮
    oBtnR.onclick = function () {

        //获取页面的高度和宽度
        var sWidth = document.body.scrollWidth;
        var sHeight = document.body.scrollHeight;
        //alert(sWidth);
        //alert(sHeight);
        //	//获取页面的可视区域高度和宽度
        var wHeight = document.documentElement.clientHeight;
        //alert(wHeight);
        oMask.style.height = sHeight + "px";
        oMask.style.width = sWidth + "px";

        oMask.style.display = "block";
        oBorder.style.display = "block";

        //	//获取登陆框的宽和高
        var dHeight = oBorder.offsetHeight;
        var dWidth = oBorder.offsetWidth;
        //alert(dHeight);
        //alert(dWidth);
        //	//设置登陆框的left和top
        oBorder.style.left = sWidth / 2 - dWidth / 2 + "px";
        oBorder.style.top = wHeight / 2 - dHeight / 2 + "px";
        oInnerLogin.className = " ";
        oInnerRes.className = "Select ";
        oULOne.style.display = 'none';
        oULTwo.style.display = 'block';


    }

    oClose.onclick = oMask.onclick = function () {
        oMask.style.display = "none";
        oBorder.style.display = "none";
    }
///////改变登录注册///////////////////////////////////////////
    var titles = $('Border-Line').getElementsByTagName('li'),
        divs = $('Content-1').getElementsByTagName('ul');
    //alert(titles.length);
    if (titles.length != divs.length)
        return;
    //遍历titles下的所有li
    for (var m = 0; m < titles.length; m++) {
        titles[m].id = m;
        titles[m].onclick = function () {
            //alert(this.id);
            for (var n = 0; n < titles.length; n++) {
                titles[n].className = '';
                divs[n].style.display = 'none';
            }
            //设置当前为高亮显示
            this.className = 'Select';
            divs[this.id].style.display = 'block';
        }
    }

////////////////////////////////////////////////////
    function animate(offset) {
        if (offset == 0) {
            return;
        }
        animated = true;
        var time = 250;
        var inteval = 10;
        var speed = offset / (time / inteval);
        var left = parseInt(list.style.left) + offset;

        var go = function () {
            if ((speed > 0 && parseInt(list.style.left) < left) || (speed < 0 && parseInt(list.style.left) > left)) {
                list.style.left = parseInt(list.style.left) + speed + 'px';
                setTimeout(go, inteval);
            } else {
                list.style.left = left + 'px';
                if (left > -200) {
                    list.style.left = -800 * len + 'px';
                }
                if (left < (-800 * len)) {
                    list.style.left = '-800px';
                }
                animated = false;
            }
        }
        go();
    }

    function showButton() {
        for (var i = 0; i < buttons.length; i++) {
            if (buttons[i].className == 'on') {
                buttons[i].className = '';
                break;
            }
        }
        buttons[index - 1].className = 'on';
    }

    function play() {
        timer = setTimeout(function () {
            next.onclick();
            play();
        }, interval);
    }

    function stop() {
        clearTimeout(timer);
    }

    next.onclick = function () {
        if (animated) {
            return;
        }
        if (index == 5) {
            index = 1;
        } else {
            index += 1;
        }
        animate(-800);
        showButton();
    }
    prev.onclick = function () {
        if (animated) {
            return;
        }
        if (index == 1) {
            index = 5;
        } else {
            index -= 1;
        }
        animate(800);
        showButton();
    }

    for (var i = 0; i < buttons.length; i++) {
        buttons[i].onclick = function () {
            if (animated) {
                return;
            }
            if (this.className == 'on') {
                return;
            }
            var myIndex = parseInt(this.getAttribute('index'));
            var offset = -800 * (myIndex - index);

            animate(offset);
            index = myIndex;
            showButton();
        }
    }

    container.onmouseover = stop;
    container.onmouseout = play;

    play();

}


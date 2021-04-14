function $(id) {
    return typeof id == 'string' ? document.getElementById(id) : id;
}

//通过名称查找某个元素
function $$(name) {
    return document.querySelector(name);
}

//通过名称查找相同的一组数据
function $all(name) {
    return document.querySelectorAll(name);
}

window.onload = function () {
    //获取要操作的元素
    var smallbox = $$('.small-box .mask');
    var smallimg = $$('.small-box img');
    var square = $$('.square');
    var bigbox = $$('.big-box');
    var bigimg = $$('.big-box img');
    var imgs = $all('.img-list img');

    //鼠标经过图片列表显示被选中，实现图片切换
    for (var i = 0; i < imgs.length; i++) {
        imgs[i].onmouseover = function () {
            for (var j = 0; j < imgs.length; j++) {
                imgs[j].className = '';
            }
            this.className = 'active';
            //改变对应的图片链接
            smallimg.src = this.getAttribute('data-small');
            bigimg.src = this.getAttribute('data-big');
        };
    }

    //鼠标移入放大器显示并设置选中框的大小
    smallbox.onmouseover = function () {
        square.style.display = 'block';
        bigbox.style.display = 'block';
        //位置w/smallimg.w = bigbox.w/bigimg.w
        square.style.width = bigbox.offsetWidth * smallimg.offsetWidth / bigimg.offsetWidth + 'px';
        square.style.height = bigbox.offsetHeight * smallimg.offsetHeight / bigimg.offsetHeight + 'px';
    };
    //鼠标移出放大器隐藏
    smallbox.onmouseout = function () {
        square.style.display = 'none';
        bigbox.style.display = 'none';
    };

    //放大器移动
    //获取鼠标的位置
    smallbox.onmousemove = function (ev) {
        var oEvent = ev || event;
        var x = oEvent.offsetX - square.offsetWidth / 2;
        var y = oEvent.offsetY - square.offsetHeight / 2;
        if (x < 0) {
            x = 0;
        }
        if (x > smallbox.offsetWidth - square.offsetWidth) {
            x = smallbox.offsetWidth - square.offsetWidth;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > smallbox.offsetHeight - square.offsetHeight) {
            y = smallbox.offsetHeight - square.offsetHeight;
        }
        //给选中框定位
        square.style.left = x + 'px';
        square.style.top = y + 'px';
        //给放大器定位  x/? = smallimg.w/bigimg.w
        bigimg.style.top = -y * bigimg.offsetWidth / smallimg.offsetWidth + 'px';
        bigimg.style.left = -x * bigimg.offsetHeight / smallimg.offsetHeight + 'px';
    };

    //通过名称查找某个元素
    function $$(name) {
        return document.querySelector(name);
    }

    //通过名称查找相同的一组数据
    function $all(name) {
        return document.querySelectorAll(name);
    }

    /////////////////////////////////////////////////
    var jia = $("Jia"),
        jian = $("Jian"),
        num = $("Number");

    jia.onclick = function () {
        num.value = parseInt(num.value) + 1;
        if (num.value > 1) {
            jian.className = 'Jian-Max';
        }
    }
    jian.onclick = function () {
        if (num.value <= 1) {
            this.className = 'Jian-Min';
            num.value = 1;
        } else {
            this.className = 'Jian-Max';
            num.value = parseInt(num.value) - 1;
        }
    }

    ///////////////////////////////////////////////////////////////
    var titles = $('Top3-Top').getElementsByTagName('li'),
        divs = $('Top3-Content').getElementsByTagName('div');

    //        alert(titles.length);
    //        alert(divs.length);

    if (titles.length != divs.length)
        return;
    //遍历titles下的所有li
    for (var i = 0; i < titles.length; i++) {
        titles[i].id = i;
        titles[i].onclick = function () {
            //alert(this.id);
            for (var j = 0; j < titles.length; j++) {
                titles[j].className = '';
                divs[j].style.display = 'none';
            }
            //设置当前为高亮显示
            this.className = 'Top3-Top-Select';
            divs[this.id].style.display = 'block';
        }
    }
}
function $(id) {
    return typeof id == 'string' ? document.getElementById(id) : id;

}

window.onload = function () {
    var titles = $('Left').getElementsByTagName('li'),
        divs = $('Right').getElementsByTagName('div'),
        t1 = $('Detail-3-UL').getElementsByTagName('li'),
        t2 = $('Detail-3-text').getElementsByTagName('li');
    //alert(titles.length);
    if (titles.length != divs.length || t1.length != t2.length)
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
            this.className = 'Select';
            divs[this.id].style.display = 'block';
        }
    }
    for (var i = 0; i < t1.length; i++) {
        t1[i].id = i;
        t1[i].onclick = function () {
            //alert(this.id);
            for (var j = 0; j < t1.length; j++) {
                t1[j].className = '';
                t2[j].style.display = 'none';
            }
            //设置当前为高亮显示
            this.className = 'QSelect';
            t2[this.id].style.display = 'block';
        }
    }
}

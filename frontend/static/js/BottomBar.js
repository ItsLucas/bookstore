function $(id) {
    return typeof id == 'string' ? document.getElementById(id) : id;

}

window.onload = function () {
    var titles = $('Left').getElementsByTagName('li'),
        divs = $('Right').getElementsByTagName('div');
    //alert(titles.length);
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
            this.className = 'Select';
            divs[this.id].style.display = 'block';
        }
    }
}

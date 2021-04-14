function $(id) {
    return typeof id == 'string' ? document.getElementById(id) : id;

}

window.onload = function () {
    var jia = $("Jia"),
        jian = $("Jian"),
        num = $("Number"),
        num1 = $("Number1");


    jia.onclick = function () {
        num.value = parseInt(num.value) + 1;
//		num1.value = parseInt(num1.value) + 799;
        num1.value = parseInt(num1.value) + 799 + '.00';

//		alert(num1.innerHTML);
//		alert(parseInt(num.value) * 799);
        if (num.value > 1) {
            jian.className = 'Jian-Max';

        }

    }
    jian.onclick = function () {
        if (num.value <= 1) {
            this.className = 'Jian-Min';
            num.value = 1;
            num1.value = 799.00;

        } else {
            this.className = 'Jian-Max';
            num.value = parseInt(num.value) - 1;
            num1.value = parseInt(num1.value) - 799 + '.00';
//			num1.vlaue = parseInt(num.value) * 799;
//			alert(num1.vlaue);
        }
    }

}
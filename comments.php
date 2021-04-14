<?php
include "functions.php";
session_start();
$ppid = "null";
if(isset($_GET['pid'])&&isset($_POST['content']) && isset($_POST['score'])) {
    $pdo = pdo_connect_mysql();
    $stmt = $pdo->prepare("INSERT INTO Review(pid,cid,score,comments) VALUES(?,?,?,?)");
    $stmt->bindValue(1,$_GET['pid']);
    $stmt->bindValue(2,$_SESSION['id']);
    $stmt->bindValue(3,$_POST['score'],PDO::PARAM_INT);
    $stmt->bindValue(4,$_POST['content']);
    $stmt->execute();
    echo "Success";
    header("refresh:0;url=https://foodie.itslucas.me/index.php?page=product&id=" . $_GET['pid']);

}
else if(isset($_GET['pid'])) {
    $ppid=$_GET['pid'];
}
else {
    header("refresh:0;url=http://foodie.itslucas.me");
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Foodie Customer Portal</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.bootcss.com/font-awesome/5.13.0/css/all.css" rel="stylesheet">
    <script type="text/javascript" src="../js/Data.js"></script>
    <link rel="stylesheet" href="../css/Common.css" />
    <link rel="stylesheet" href="../css/Data.css">
</head>
<body>
<div class="Content">
    <div class="Right" id="Right" >
        <div class="Details Detail-4"">
        <p class="AboutUS">评价晒单</p>
        <form action="comments.php?pid=<?=$ppid?>" method="post">
            <ul>
                <li>
                    <span class="Detail-4-Left">score</span>
                    <td><input type="text" name="score"/></td>
                </li>
                <li style="height:120px;">
                    <span class="Detail-4-Left"style="line-height: 120px;">comment</span>
                    <input type="text" style="width:100px;height:100px;margin-top:10px;" name = "content" />
                </li>
                <li style="background-color: white;">
                    <input class=" BaoCun" type="submit" value="保存"/>
                </li>
            </ul>
        </form>
 <!--       <form action="comments.php?pid=<?/*=$ppid*/?>" method="post">
            <div class="Details Detail-7"><input type="text" name="score" placeholder="10" id="score" required></div>
            <div class="Details Detail-7"><input type="text" name="content" placeholder="Content" id="content" required></div>
            <div class="Details Detail-7"><input class=" BaoCun" type="button" value="submit"/></div>
        </form>-->
</div>
    </div>
</div>
</body>
</html>
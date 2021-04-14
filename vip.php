<?php
include "functions.php";
include "HashMap.php";
session_start();
if(isset($_POST['submit'])) {
    $pdo=pdo_connect_mysql();
    $stmt = $pdo->prepare("update Customer set cname = ? where cid = ?");
    $stmt->bindValue(1,$_POST['cname']);
    $stmt->bindValue(2,$_SESSION['id']);
    $stmt->execute();
    $stmt = $pdo->prepare("update Customer set cellno = ? where cid = ?");
    $stmt->bindValue(1,$_POST['cellno']);
    $stmt->bindValue(2,$_SESSION['id']);
    $stmt->execute();
    $stmt = $pdo->prepare("update Customer set wxid = ? where cid = ?");
    $stmt->bindValue(1,$_POST['wxid']);
    $stmt->bindValue(2,$_SESSION['id']);
    $stmt->execute();
    $stmt = $pdo->prepare("update Customer set password = ? where cid = ?");
    $stmt->bindValue(1,$_POST['password']);
    $stmt->bindValue(2,$_SESSION['id']);
    $stmt->execute();
}
else if(isset($_POST['zhuxiao'])) {
    $pdo=pdo_connect_mysql();
    $stmt = $pdo->prepare("call unRegister(?)");
    $stmt->bindValue(1,$_SESSION['id']);
    $stmt->execute();
    session_destroy();
    header("refresh:0;url=http://foodie.itslucas.me");
    exit;
}
$pdo = pdo_connect_mysql();
$stmt = $pdo->prepare('SELECT * FROM Customer WHERE cid = ?');
$stmt->bindValue(1,$_SESSION['id']);
$stmt->execute();
$userinfo = $stmt->fetch(PDO::FETCH_ASSOC);
$stmt = $pdo->prepare('SELECT * FROM Vip WHERE cid = ?');
$stmt->bindValue(1,$_SESSION['id']);
$stmt->execute();
$vipinfo = $stmt->fetch(PDO::FETCH_ASSOC);
$stmt = $pdo->prepare('SELECT Orrder_Info.*,Festival.discount,Foodie.pname,Foodie.price,Coupon.deduct,orrder.food_quantity FROM Orrder_Info JOIN orrder on Orrder_Info.oid=orrder.oid JOIN Foodie on orrder.pid=Foodie.pid JOIN Coupon on Coupon.couid = Orrder_Info.couid   JOIN Festival on Festival.fid = Orrder_Info.fid WHERE cid = ?');
$stmt->bindValue(1,$_SESSION['id']);
$stmt->execute();
$orderinfo = $stmt->fetchAll(PDO::FETCH_ASSOC);

$ocnt = new HashMap();
$oid = -1;$cnt = 0;
foreach ($orderinfo as $order) {

    if($order['oid'] !== $oid) {
        $ocnt->put($oid,$cnt);
        $cnt = 0;
        $oid = $order['oid'];
    }
    $cnt++;
}
$ocnt->put($oid,$cnt);
$stmt = $pdo->prepare('SELECT * FROM Checklater WHERE cid = ?');
$stmt->bindValue(1,$_SESSION['id']);
$stmt->execute();
$btinfo = $stmt->fetch(PDO::FETCH_ASSOC);
$stmt = $pdo->prepare('SELECT Coupons_pack.cid,Coupons_pack.couid,expired_Time,deduct FROM Coupons_pack JOIN Coupon on Coupons_pack.couid = Coupon.couid WHERE cid = ?');
$stmt->bindValue(1,$_SESSION['id']);
$stmt->execute();
$qinfo = $stmt->fetchAll(PDO::FETCH_ASSOC);
$stmt = $pdo->prepare('SELECT * FROM Festival');
$stmt->execute();
$finfo = $stmt->fetchAll(PDO::FETCH_ASSOC);
$cnt2 = 1;
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
<header>
    <div class="content-wrapper">
        <h1>Foodie</h1>
        <nav>
            <a href="index.php">Home</a>
            <a href="index.php?page=products">Products</a>
            <a href="login.html">Logout</a>
        </nav>
        <div class="link-icons">
            <a href="index.php?page=cart">
                <i class="fas fa-shopping-cart"></i> <span>0</span>
            </a>
        </div>
    </div>
</header>
<div class="Content">
    <div class="Left" id="Left">
        <ul>
            <li class="Select">
                <a href="#">账户信息</a>
            </li>
            <li >
                <a href="#">我的订单</a>
            </li>
            <li>
                <a href="#">我的白条</a>
            </li>
            <li>
                <a href="#">我的券</a>
            </li>

            <li>
                <a href="#">节日信息</a>
            </li>
            <li>
                <a href="#">修改信息</a>
            </li>
        </ul>
    </div>
    <div class="Right" id="Right" >
        <div class="Details Detail-4" style="display:block;">
            <p class="AboutUS">账户信息</p>
            <ul>
                <li>
                    <span class="Detail-4-Left">顾客ID</span>
                    <td><?=$userinfo['cid']?></td>
                </li>
                <li>
                    <span class="Detail-4-Left">顾客名称</span>
                    <td><?=$userinfo['cname']?></td>
                </li>
                <li>
                    <span class="Detail-4-Left">性别</span>
                    <td><?=($userinfo['sex']=='m'?'男':'女')?></td>
                </li>
                <li>
                    <span class="Detail-4-Left">出生日期</span>
                    <td><?=$userinfo['birthday']?></td>
                </li>
                <li>
                    <span class="Detail-4-Left">电话</span>
                    <td><?=$userinfo['cellno']?></td>
                </li>
                <li>
                    <span class="Detail-4-Left">微信</span>
                    <td><?=$userinfo['wxid']?></td>
                </li>
                <li>
                    <span class="Detail-4-Left">账户余额</span>
                    <td><?=$userinfo['balance']?></td>
                </li>
                <li>
                    <span class="Detail-4-Left">福豆</span>
                    <td><?=$userinfo['fudou']?></td>
                </li>
                <li>
                    <span class="Detail-4-Left">白条状态</span>
                    <td><?=($userinfo['cstatus']=='0'?'启用':'禁用')?></td>
                </li>
                <li>
                    <span class="Detail-4-Left">VIP等级</span>
                    <td><?=$vipinfo['level']?></td>
                </li>
            </ul>
        </div>
        <div class="Details Detail-1" style="display:none;">
            <p class="AboutUS">我的订单</p>
            <ul>
                <li>
                    <table id="TopTR">
                        <tr>
                            <td>商品信息</td>
                            <td>单价</td>
                            <td>数量</td>
                            <td>商品操作</td>
                            <td>实付款</td>
                            <td>交易状态</td>
                            <td>付款方式</td>
                        </tr>
                    </table>
                </li>
                <?php foreach ($orderinfo as $order): ?>
                <li>
                    <a class="Delect2">
                        <table id="TR_2">
                            <tr>
                                <td><img src="imgs/<?=$order['pname']?>.jpeg" /></td>
                                <td>
                                    <p>￥<?=$order['price']?></p>
                                </td>
                                <td>
                                    <p><?=$order['food_quantity']?></p>
                                </td>
                                <td>
                                    <p>申请售后</p>
                                </td>
                                <?php if($cnt2 == $ocnt->get($order['oid'])):?>
                                <td>
                                    <p>￥<?=$order['deal_price']?></p>
                                    <p style="color:gray;">（含运费：￥0.00）</p>
                                    <p style="color:gray;">（优惠：￥<?=$order['deduct']?>）</p>
                                    <p style="color:gray;">（福豆：<?=$order['use_Fudou']?>）</p>
                                    <p style="color:gray;">（VIP：<?=$vipinfo['level']?>）</p>
                                    <p style="color:gray;">（节日折扣：<?=$order['discount']?>折）</p>
                                </td>
    <?php $cnt2 = 1;?>
    <?php else:?>
                                    <td>
                                        <p> </p>
                                    </td>
    <?php $cnt2++;?>
    <?php endif;?>
                                <td>
                                    <p>交易成功</p>
                                </td>
                                <td>
                                    <p><?=($order['check_way']=='0'?'现金':'花呗')?></p>
                                </td>
                            </tr>
                        </table>
                    </a>
                </li>
                <?php endforeach;?>
            </ul>
        </div>
        <div class="Details Detail-4" style="display:none;">
            <p class="AboutUS">我的白条</p>
            <ul>

                <li>
                    <span class="Detail-4-Left">已用额度</span>
                    <td><?=$btinfo['spent_limit']?></td>

                </li>
                <li>
                    <span class="Detail-4-Left">最大额度</span>
                    <td><?=$btinfo['max_limit']?></td>
                </li>
                <li>
                    <span class="Detail-4-Left">还款日期</span>
                    <td><?=$btinfo['repay_date']?></td>
                </li>
                <li>
                    <span class="Detail-4-Left">本月最低还款</span>
                    <td><?=$btinfo['minimum_repay']?></td>
                </li>
                <li>
                    <span class="Detail-4-Left">月账单</span>
                    <td><?=$btinfo['month_bill']?></td>
                </li>
                <li>
                    <span class="Detail-4-Left">本月已还款</span>
                    <td><?=$btinfo['repayed_amount']?></td>
                </li>
            </ul>
        </div>
        <div class="Details Detail-3" style="display:none;">
            <p class="AboutUS">我的券</p>
            <ul id="Detail-3-UL">
                <li class="QSelect">可用券</li>
            </ul>
            <a id="Detail-3-text">
                <ul>
                    <li>
                        <table id="">
                            <tr>
                                <td>可用券名称</td>
                                <td>面值</td>
                                <td>有效期</td>
                            </tr>
                            <?php foreach ($qinfo as $qqq): ?>
                                <tr>
                                    <td><?=$qqq['couid']?></td>
                                    <td><?=$qqq['deduct']?></td>
                                    <td><?=$qqq['expired_Time']?></td>
                                </tr>
                            <?php endforeach;?>
                        </table>
                    </li>
                </ul>
            </a>
        </div>

        <div class="Details Detail-5" style="display:none;">
            <p class="AboutUS">购物狂欢节</p>
            <table id="">
                <tr style="background-color:rgba(0,0,0,0.1);" >
                    <td style="width:100px;">节日名称</td>
                    <td style="width:250px;">节日时段</td>
                    <td style="width:250px;">折扣力度</td>
                </tr>
                <?php foreach ($finfo as $ff): ?>
                    <tr>
                        <td style="width:100px;"><input style="width:100px;" class="Address" type="text" value="<?=$ff['fname']?>"/></td>
                        <td style="width:250px;"><input style="width:250px;" class="Address" type="text" value="<?=$ff['time_begin']?> - <?=$ff['time_end']?> "/></td>
                        <td style="width:250px;"><input style="width:250px;" class="Address" type="text" value="<?=$ff['discount']?>"" /></td>
                    </tr>
                <?php endforeach;?>

            </table>


        </div>
        <div class="Details Detail-6" style="display:none;">
            <p class="AboutUS">修改信息</p>
            <ul>
                <form action="vip.php" method="post">
                <li>
                    <span class="Detail-4-Left">顾客名称</span>
                    <td><input type="text" name="cname" value="<?=$userinfo['cname']?>"/></td>
                </li>
                <li>
                    <span class="Detail-4-Left">电话</span>
                    <input type="text" name="cellno" value="<?=$userinfo['cellno']?>" />
                </li>
                <li>
                    <span class="Detail-4-Left">微信</span>
                    <input type="text" name="wxid" value="<?=$userinfo['wxid']?>" />
                </li>
                <li>
                    <span class="Detail-4-Left">密码</span>
                    <input type="password" name="password" value="<?=$userinfo['password']?>" />
                </li>
                <li style="background-color: white;">
                    <input class="BaoCun" name="submit" type="submit" value="保存"/>
                </li>
                    <li style="background-color: white;">
                        <input class="BaoCun" name="zhuxiao" type="submit" value="注销用户"/>
                    </li>
                </form>
            </ul>
        </div>
    </div>
</div>
<?=template_footer()?>
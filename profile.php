<?php
include 'functions.php';
// We need to use sessions, so you should always start sessions using the below code.
session_start();
// If the user is not logged in redirect to the login page...
if (!isset($_SESSION['loggedin'])) {
    header('Location: login.html');
    exit;
}
$DATABASE_HOST = 'localhost';
$DATABASE_USER = 'root';
$DATABASE_PASS = 'foodie';
$DATABASE_NAME = 'foodie';
$con = mysqli_connect($DATABASE_HOST, $DATABASE_USER, $DATABASE_PASS, $DATABASE_NAME);
if (mysqli_connect_errno()) {
    exit('Failed to connect to MySQL: ' . mysqli_connect_error());
}
// We don't have the password or email info stored in sessions so instead we can get the results from the database.
$stmt = $con->prepare('SELECT passwd, wxid FROM Customer WHERE cid = ?');
// In this case we can use the account ID to get the account info.
$stmt->bind_param('s', $_SESSION['id']);
$stmt->execute();
$stmt->bind_result($password, $email);
$stmt->fetch();
$stmt->close();
$stmt = $con->prepare('SELECT level,saved_amount,spent_amount FROM Vip WHERE cid = ?');
// In this case we can use the account ID to get the account info.
$stmt->bind_param('s', $_SESSION['id']);
$stmt->execute();
$stmt->bind_result($vlv, $saa,$spa);
$stmt->fetch();
$stmt->close();
?>

<?php template_header('Profile');?>
<style>
    #VIP {
        font-family: Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        width: 50%;
    }

    #VIP td, #VIP th {
        border: 1px solid #ddd;
        padding: 8px;
    }

    #VIP tr:nth-child(even){background-color: #f2f2f2;}

    #VIP tr:hover {background-color: #ddd;}

    #VIP th {
        padding-top: 20px;
        padding-bottom: 20px;
        text-align: center;
        background-color: #4CAF50;
        color: white;
    }
</style>
<div class="content">
    <div class="products content-wrapper">
    <h1>Profile Page</h1>
        <h2>Your account details are below:</h2>
        <table id="VIP">
            <tr>
                <td>Username:</td>
                <td><?=$_SESSION['name']?></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><?=$password?></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><?=$email?></td>
            </tr>
            <tr>
                <td>VIP Level:</td>
                <td><?=$vlv?></td>
            </tr>
            <tr>
                <td>Money Saved:</td>
                <td><?=$saa?></td>
            </tr>
            <tr>
                <td>Money Spent：</td>
                <td><?=$spa?></td>
            </tr>
        </table>
    </div>
</div>
        <?php template_footer();?>

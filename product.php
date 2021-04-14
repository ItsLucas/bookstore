<?php
// Check to make sure the id parameter is specified in the URL
if (isset($_GET['id'])) {
    // Prepare statement and execute, prevents SQL injection
    $stmt = $pdo->prepare('SELECT * FROM Foodie WHERE pid = ?');
    $stmt->execute([$_GET['id']]);
    // Fetch the product from the database and return the result as an Array
    $product = $stmt->fetch(PDO::FETCH_ASSOC);
     //Check if the product exists (array is not empty)
    if (!$product) {
        // Simple error to display if the id for the product doesn't exists (array is empty)
        exit('Product does not exist!');
    }
    $stmt = $pdo->prepare('SELECT cname,score,comments FROM Review INNER JOIN Customer on Review.cid=Customer.cid WHERE pid = ?');
// bindValue will allow us to use integer in the SQL statement, we need to use for LIMIT
    $stmt->bindValue(1, $_GET['id'], PDO::PARAM_STR);
    $stmt->execute();
// Fetch the products from the database and return the result as an Array
    $reviews = $stmt->fetchAll(PDO::FETCH_ASSOC);
}
else {
    // Simple error to display if the id wasn't specified
    exit('Product does not exist!');
}
?>
<?=template_header('Product')?>

<div class="product content-wrapper">
    <img src="imgs/<?=$product['pname']?>.jpeg" width="500" height="500" alt="<?=$product['pname']?>">
    <div>
        <h1 class="name"><?=$product['sid']?>-<?=$product['pname']?></h1>
        <span class="price">
            &dollar;<?=$product['price']?>
<!--            --><?php //if ($product['rrp'] > 0): ?>
<!--                <span class="rrp">&dollar;--><?//=$product['rrp']?><!--</span>-->
<!--            --><?php //endif; ?>
        </span>
        <div class="description">
            net weight is: <?=$product['net_weight']?> g
        </div>
        <form action="index.php?page=cart" method="post">
            <input type="number" name="quantity" value="1" min="1" max="<?=$product['quantity']?>" placeholder="Quantity" required>
            <input type="hidden" name="product_id" value="<?=$product['pid']?>">
            <input type="submit" value="Add To Cart">
            <style>
                #VIP {
                    font-family: Arial, Helvetica, sans-serif;
                    border-collapse: collapse;
                    width: 100%;
                    margin-top: 50px;
                }

                #VIP td, #VIP th {
                    border: 1px solid #ddd;
                    padding: 8px;
                    width: 50%;
                }
                #VIP th {
                    padding-top: 12px;
                    padding-bottom: 12px;
                    text-align: left;
                    background-color: #4CAF50;
                    color: white;
                }
                #VIP tr:nth-child(even){background-color: #f2f2f2;}

                #VIP tr:hover {background-color: #ddd;}

            </style>
            <div class="content">
                <table id="VIP">
                    <tr>
                        <th>用户</th>
                        <th>分数</th>
                        <th>评价</th>
                    </tr>
                    <?php foreach ($reviews as $review): ?>
                        <tr>
                            <td><?=$review['cname']?></td>
                            <td><?=$review['score']?></td>
                            <td><?=$review['comments']?></td>
                        </tr>
                    <?php endforeach; ?>
                </table>
                <ul> <li style="background-color: white;">
                        <a href="comments.php?pid=<?=$_GET['id']?>">Comment</a>
                    </li>
                </ul>
            </div>

    </div>
</div>

<?=template_footer()?>

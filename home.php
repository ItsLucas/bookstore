<?php
session_start();
// Get the 4 most recently added products
$stmt = $pdo->prepare('SELECT * FROM Foodie ORDER BY pid DESC LIMIT 4');
$stmt->execute();
$recently_added_products = $stmt->fetchAll(PDO::FETCH_ASSOC);
?>
<?=template_header('Home')?>
    <div class="featured">
        <h2>Foodie</h2>
        <p>One Stop For All Foods You Need</p>
    </div>
    <div class="recentlyadded content-wrapper">
        <h2>Recently Added Products</h2>
        <div class="products">
            <?php foreach ($recently_added_products as $product): ?>
                <a href="index.php?page=product&id=<?=$product['pid']?>" class="product">
                    <img src="imgs/<?=$product['pname']?>.jpeg" width="200" height="200" alt="<?=$product['pname']?>">
                    <span class="name"><?=$product['pname']?></span>
                    <span class="price">
                &dollar;<?=$product['price']?>
<!--                        --><?php //if ($product['rrp'] > 0): ?>
<!--                            <span class="rrp">&dollar;--><?//=$product['rrp']?><!--</span>-->
<!--                        --><?php //endif; ?>
            </span>
                </a>
            <?php endforeach; ?>
        </div>
    </div>

<?=template_footer()?>
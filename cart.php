<?php
$tot_discount=0;
// If the user clicked the add to cart button on the product page we can check for the form data
if (isset($_POST['product_id'], $_POST['quantity']) && is_numeric($_POST['quantity'])) {
    // Set the post variables so we easily identify them, also make sure they are integer
    $product_id = $_POST['product_id'];
    $quantity = (int)$_POST['quantity'];
    $stmt = $pdo->prepare('CALL addCart(?,?,?)');
    $stmt->bindValue(1,$product_id,PDO::PARAM_STR);
    $stmt->bindValue(2,$quantity,PDO::PARAM_INT);
    $stmt->bindValue(3,$_SESSION['id'],PDO::PARAM_STR);
    $stmt->execute();
    // Prepare the SQL statement, we basically are checking if the product exists in our database
    $stmt = $pdo->prepare('SELECT * FROM Cart WHERE cid = ?');
    $stmt->execute([$_SESSION['id']]);
    // Fetch the product from the database and return the result as an Array
    $product = $stmt->fetch(PDO::FETCH_ASSOC);
    // Check if the product exists (array is not empty)
    if ($product && $quantity > 0) {
        // Product exists in database, now we can create/update the session variable for the cart
        if (isset($_SESSION['cart']) && is_array($_SESSION['cart'])) {
            if (array_key_exists($product_id, $_SESSION['cart'])) {
                // Product exists in cart so just update the quanity
                $_SESSION['cart'][$product_id] += $quantity;
            } else {
                // Product is not in cart so add it
                $_SESSION['cart'][$product_id] = $quantity;
            }

        } else {
            // There are no products in cart, this will add the first product to cart
            $_SESSION['cart'] = array($product_id => $quantity);
        }
    }
    // Prevent form resubmission...
    header('location: index.php?page=cart');
    exit;
}
// Remove product from cart, check for the URL param "remove", this is the product id, make sure it's a number and check if it's in the cart
if (isset($_GET['remove'])) {
    // Remove the product from the shopping cart
    $stmt = $pdo->prepare('set @use_trigger = 0; DELETE FROM Cart where cid=? AND pid=?');
    $stmt->bindValue(1,$_SESSION['id'],PDO::PARAM_STR);
    $stmt->bindValue(2,$_GET['remove'],PDO::PARAM_STR);
    $stmt->execute();
    unset($_SESSION['cart'][$_GET['remove']]);
}
$products_in_cart = isset($_SESSION['cart']) ? $_SESSION['cart'] : array();
$products = array();
$subtotal = 0.00;
// Update product quantities in cart if the user clicks the "Update" button on the shopping cart page
if (isset($_POST['update'])) {
    // Loop through the post data so we can update the quantities for every product in cart
    foreach ($_POST as $k => $v) {
        $qty=0;
        if (strpos($k, 'quantity') !== false ) {
            $id = str_replace('quantity-', '', $k);
            $qty = (int)$v;
            if ($qty > 0) {
                $stmt = $pdo->prepare('CALL addCart(?,?,?)');
                $stmt->bindValue(1,$id,PDO::PARAM_STR);
                $stmt->bindValue(2,$qty,PDO::PARAM_INT);
                $stmt->bindValue(3,$_SESSION['id'],PDO::PARAM_STR);
                $stmt->execute();
                //$stmt->debugDumpParams();
            }
        }
    }

    //header('location: index.php?page=cart');
    //exit;
}
// Send the user to the place order page if they click the Place Order button, also the cart should not be empty
if (isset($_POST['placeorder'])) {
    $stmt = $pdo->prepare("call buy(?,?)");
    $i = 0;
    $stmt->bindParam(1, $i,PDO::PARAM_INT);
    $stmt->bindParam(2,$_SESSION['id']);
    $stmt->execute();
    //$stmt->debugDumpParams();
    header('Location: index.php?page=placeorder');
    exit;
}
if (isset($_POST['c_placeorder'])) {
    $stmt = $pdo->prepare("call buy(?,?)");
    $i1 = 1;
    $stmt->bindParam(1, $i1,PDO::PARAM_INT);
    $stmt->bindParam(2,$_SESSION['id']);
    $stmt->execute();
    header('Location: index.php?page=placeorder');
    exit;
}
// Check the session variable for products in cart

// If there are products in cart
    $stmt = $pdo->prepare('SELECT Cart.pid,Cart.quantity,Foodie.pname,Foodie.price FROM Cart JOIN Foodie ON Cart.pid = Foodie.pid WHERE cid =?');
    $stmt->bindValue(1,$_SESSION['id']);
    $stmt->execute();
    // Fetch the products from the database and return the result as an Array
    $products = $stmt->fetchAll(PDO::FETCH_ASSOC);
    // Calculate the subtotal
    foreach ($products as $product) {
        $subtotal += (float)$product['price'] * (int)$product['quantity'];
    }
?>

<?=template_header('Cart')?>

<div class="cart content-wrapper">
    <h1>Shopping Cart</h1>
    <form action="index.php?page=cart" method="post">
        <table>
            <thead>
            <tr>
                <td colspan="2">Product</td>
                <td>Price</td>
                <td>Quantity</td>
                <td>Total</td>
            </tr>
            </thead>
            <tbody>
            <?php if (empty($products)): ?>
                <tr>
                    <td colspan="5" style="text-align:center;">You have no products added in your Shopping Cart</td>
                </tr>
            <?php else: ?>
                <?php foreach ($products as $product): ?>
                    <tr>
                        <td class="img">
                            <a href="index.php?page=product&id=<?=$product['id']?>">
                                <img src="imgs/<?=$product['pname']?>.jpeg" width="50" height="50" alt="<?=$product['pname']?>">
                            </a>
                        </td>
                        <td>
                            <a href="index.php?page=product&id=<?=$product['pid']?>"><?=$product['pname']?></a>
                            <br>
                            <a href="index.php?page=cart&remove=<?=$product['pid']?>" class="remove">Remove</a>
                        </td>
                        <td class="price">&dollar;<?=$product['price']?></td>
                        <td class="quantity">
                            <input type="number" name="quantity-<?=$product['pid']?>" value="<?=$product['quantity']?>" min="1" max="99" placeholder="Quantity" required>
                        </td>
                        <td class="price">&dollar;<?=$product['price'] * $product['quantity']?></td>
                    </tr>
                <?php endforeach; ?>
            <?php endif; ?>
            </tbody>
        </table>
        <div class="subtotal">
            <span class="text">Discount</span>
            <span class="price">&dollar;<?=$tot_discount?></span>
            <span class="text">Subtotal</span>
            <span class="price">&dollar;<?=$subtotal?></span>
        </div>
        <div class="buttons">
            <input type="submit" value="Update" name="update">
            <input type="submit" value="Place Order" name="placeorder">
            <input type="submit" value="Place Order With CheckLater" name="c_placeorder">
        </div>
    </form>
</div>

<?=template_footer()?>

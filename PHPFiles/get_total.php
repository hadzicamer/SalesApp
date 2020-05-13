<?php
require("connection.php");
$query="SELECT Price,Qty FROM Items INNER JOIN Bill_Details ON Items.ID=Bill_Details.ItemID WHERE BillNo='".$_GET['BillNo']."'";
  $cnct=sqlsrv_query($conn,$query) or die(print_r(sqlsrv_errors(),true));
$total=0;
while($row=sqlsrv_fetch_array($cnct,SQLSRV_FETCH_ASSOC)){

    $total=$total+($row['Price']*$row['Qty']);
}

echo $total;

?>

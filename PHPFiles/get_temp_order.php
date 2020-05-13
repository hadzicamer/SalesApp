<?php

require("connection.php");
$query="SELECT ID,Name,Price,Qty,Email
FROM Temp_Order INNER JOIN Items
ON Temp_Order.OrderID=Items.ID
WHERE Email='". $_GET['Email']."';";
$cnct=sqlsrv_query($conn,$query) or die(print_r(sqlsrv_errors(),true));
$arr=array();
while($row=sqlsrv_fetch_array($cnct,SQLSRV_FETCH_ASSOC)){
    array_push($arr,$row);
}

echo json_encode($arr);
?>
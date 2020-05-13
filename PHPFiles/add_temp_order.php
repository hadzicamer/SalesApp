<?php

require("connection.php");
$query="INSERT INTO Temp_Order(OrderID,Email,Qty) VALUES('". $_GET['OrderID']."', '". $_GET['Email']."','". $_GET['Qty']."');";
  $cnct=sqlsrv_query($conn,$query);
  if(!$cnct)
  echo('0'); 
  else echo('1');
?>



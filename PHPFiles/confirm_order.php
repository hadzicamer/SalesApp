<?php

require("connection.php");
$query="SELECT * FROM Temp_Order WHERE Email='". $_GET['Email']."'";
  $cnct=sqlsrv_query($conn,$query) or die(print_r(sqlsrv_errors(),true));
  $query2="INSERT INTO Bill(Email) VALUES('". $_GET['Email']."');";
  $cnct2=sqlsrv_query($conn,$query2) or die(print_r(sqlsrv_errors(),true));
  $query3="SELECT MAX(BillNo) as bno FROM Bill WHERE Email='". $_GET['Email']."'";
  $cnct3=sqlsrv_query($conn,$query3) or die(print_r(sqlsrv_errors(),true));
  $row_max=sqlsrv_fetch_array($cnct3,SQLSRV_FETCH_ASSOC);

  while($row=sqlsrv_fetch_array($cnct,SQLSRV_FETCH_ASSOC)){

    $query4="INSERT INTO Bill_Details(BillNo,ItemID,Qty) VALUES('". $row_max['bno']."','". $row['OrderID']."','". $row['Qty']."');";
  $cnct4=sqlsrv_query($conn,$query4) or die(print_r(sqlsrv_errors(),true));
}

$query5="DELETE FROM Temp_Order WHERE Email='". $_GET['Email']."'";
$cnct5=sqlsrv_query($conn,$query5) or die(print_r(sqlsrv_errors(),true));
echo $row_max['bno'];
  ?>
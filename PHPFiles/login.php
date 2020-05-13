<?php

require("connection.php");
$sql="SELECT * FROM Users where Email='". $_GET['Email']."' and Password='".$_GET['Password']."'";
$stmt=sqlsrv_query($conn,$sql) or die(print_r(sqlsrv_errors(),true));
if(sqlsrv_has_rows($stmt)!=1)
echo "0";
else echo "1";
?>
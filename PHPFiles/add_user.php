<?php

require("connection.php");
$stmt=sqlsrv_prepare($conn, "SELECT * FROM Users WHERE Email='".$_GET['Email']."'");
$res=sqlsrv_prepare($conn,$_GET['Email']);
if(sqlsrv_num_rows($res)==0){ 
    $query="INSERT INTO Users(Name,Email,Password,Adress,Phone) VALUES('". $_GET['Name']."', '". $_GET['Email']."','". $_GET['Password']."',
    '". $_GET['Adress']."','". $_GET['Phone']."');";
$stmt=sqlsrv_query($conn,$query);
echo ('1');
}
else
echo ('0');
?>
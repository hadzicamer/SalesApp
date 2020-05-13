<?php

require("connection.php");
$sql="SELECT DISTINCT Category FROM Items";
$stmt=sqlsrv_query($conn,$sql) or die(print_r(sqlsrv_errors(),true));
$arr=array();
while($row=sqlsrv_fetch_array($stmt,SQLSRV_FETCH_ASSOC)){
    array_push($arr,$row);
}
header('Content-type: application/json');
echo json_encode($arr);

?>
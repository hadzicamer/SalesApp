
<?php
require("connection.php");
$sql="SELECT * FROM Items where Category='". $_GET['Category']."'";
$stmt=sqlsrv_query($conn,$sql) or die(print_r(sqlsrv_errors(),true));
$arr=array();
while($row=sqlsrv_fetch_array($stmt,SQLSRV_FETCH_ASSOC)){
    array_push($arr,$row);
}
echo json_encode($arr);
?>
<?php
require("connection.php");
$query="DELETE FROM Temp_Order WHERE Email='". $_GET['Email']."'";
  $cnct=sqlsrv_query($conn,$query);
  if(!$cnct)
  echo('0'); 
  else echo('1');


?>
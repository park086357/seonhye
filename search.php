<?php
header("Content-Type:text/html;charset=utf-8");
$con = mysqli_connect("localhost", "db","password", "db");
if(!$con){
	die("Connection failed: ".mysqli_connect_error());
}

mysqli_set_charset($con, "utp8");

$res = mysqli_query($con, "select * from FOOD");
$result = array();

while($row = mysqli_fetch_array($res)){

  array_push($result,
     array('listFood' =>$row[1]));

<?php
$con=mysqli_connect("localhost", "db", "password", "db");

mysqli_set_charset($con, "utf8");

if(mysqli_connect_errno($con)) {
	echo "error".mysqli_connect_error();
}

$foodname = $_POST['foodname'];


$result = mysqli_query($con,"insert into MENU(FOOD_NAME) values ('$foodname')");

if ($result) {
  echo $foodname;
}
else {
  echo 'failure';
}

mysqli_close($con);
?>

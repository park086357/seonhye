<?php
$con=mysqli_connect("localhost", "db", "password", "db");

mysqli_set_charset($con, "utf8");

if(mysqli_connect_errno($con)) {
	echo “error”.mysqli_connect_error();
}

$userid = $_POST['Id'];
$userpw = $_POST['Pw'];

$result = mysqli_query($con,"insert into USER(USER_EMAIL,USER_PASS) values ('$userid', '$userpw')");

if ($result) {
  echo 'success';
}
else {
  echo 'failure';
}

mysqli_close($con);
?>

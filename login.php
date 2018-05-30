<?php
$conn = mysqli_connect("localhost", "db","password", "db");
if(!$conn){
   die("Connection failed: ".mysqli_connect_error());
}
$username=$_POST['username'];
$password=$_POST['password'];

$query_search="select USER_NAME, USER_SEX from USER
where USER_EMAIL='".$username."' AND USER_PASS='".$password."'";
$response=mysqli_query($conn, $query_search);

$row=mysqli_fetch_array($response);
print(json_encode($row));
mysqli_close($conn);
$rows=mysqli_num_rows($response);
?>

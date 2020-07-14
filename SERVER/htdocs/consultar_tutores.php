<?php
 
//$servername = "localhost";
//$username = "root";
//$password = "";
//$dbname = "tutoresapp";
 
$servername = "localhost";
$username = "bypqwnna";
$password = "o9hwV2Ko00";
$dbname = "bypqwnna_app_tutores";
 
// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
} 
 
$sql = "SELECT CEDULA, NOMBRE, APELLIDO FROM TUTOR";
$result = mysqli_query($conn, $sql);
 
if (mysqli_num_rows($result) > 0) {
    // output data of each row
    while($row = mysqli_fetch_assoc($result)) {
        echo  $row["CEDULA"]. "," . $row["NOMBRE"]. "," . $row["APELLIDO"]. ";";
    }
} else {
    echo "0 results";
}
mysqli_close($conn);
?>

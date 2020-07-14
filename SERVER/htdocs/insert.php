<?php
//$servername = "localhost";
//$username = "root";
//$password = "";
//$dbname = "tutoresapp";

$servername = "localhost";
$username = "bypqwnna_tutores";
$password = "tutoresapp111.";
$dbname = "bypqwnna_app_tutores";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$sql = "INSERT INTO MATERIA (`NOMBRE`, `AREA`, `NIVEL`) VALUES ('Circuitos Digitales', 'Electrónica', '2')";

if (mysqli_query($conn, $sql)) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>

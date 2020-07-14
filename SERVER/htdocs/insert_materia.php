<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "tutoresapp";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$sql = "INSERT INTO materia (`NOMBRE`, `AREA`, `NIVEL`) VALUES ('".$_POST["nombre_materia"]."','" .$_POST["area"]."',".$_POST["nivel"].")";

if (mysqli_query($conn, $sql)) {
    echo "Nueva materia insertada en la bd con éxito";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>
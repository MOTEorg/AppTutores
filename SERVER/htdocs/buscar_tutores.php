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
$nombre=$_POST["nombre"];
$materia=$_POST["materia"];
 
$sql = "SELECT TUTOR.CEDULA,  TUTOR.NOMBRE ,TUTOR.APELLIDO, TUTOR.CALIFICACION, TUTOR.NIVEL_ACADEMICO, TUTOR.FOTO FROM TUTOR_MATERIA, MATERIA, TUTOR WHERE TUTOR.NOMBRE='".$nombre."' OR TUTOR.APELLIDO='".$nombre."' OR MATERIA.NOMBRE='".$materia."' OR MATERIA.AREA='".$materia."' AND TUTOR.CEDULA=TUTOR_MATERIA.TUTOR_ID AND MATERIA.CODIGO_MATERIA=TUTOR_MATERIA.MATERIA_ID GROUP BY TUTOR.CEDULA;";
$result = mysqli_query($conn, $sql);
 
if (mysqli_num_rows($result) > 0) {
    // output data of each row
    while($row = mysqli_fetch_assoc($result)) {
        echo  $row["CEDULA"]. "," . $row["NOMBRE"]. "," . $row["APELLIDO"]. "," . $row["CALIFICACION"]. "," . $row["NIVEL_ACADEMICO"]. "," . $row["FOTO"]. ";";
    }
} else {
    echo 0;
}
mysqli_close($conn);
?>


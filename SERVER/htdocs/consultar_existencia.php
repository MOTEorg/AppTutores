<?php

/* Datos para base de datos en Xampp*/
//Notar que las varibles se declaran sin tipo y que siempre llevan el $ al inicio del nombre

//$servername = "localhost";
//$username = "root";
//$password = "";
//$dbname = "tutoresapp";
 
/*Datos para la base en motetronica.com*/ 
$servername = "localhost";
$username = "bypqwnna";
$password = "o9hwV2Ko00";
$dbname = "bypqwnna_app_tutores";
 
//Crear una conexión con la base de datos
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Chequear que se estableció la conexión
//Notar que el punto no es para llamar un atributo o método sino para unir palabras asi: "hola "."mundo" resulta en "hola mundo"
if (!$conn) {
    die("Falló la conexión: " . mysqli_connect_error());
}
//Variables obtenidas desde el programa o app con un requerimiento POST-HTTP
//Lo que esta en comillas dentro de los corchetes de $_POST es el nombre que se le debe dar en el requerimiento.
//El requerimiento en si es está linea, por ejemplo, del código en JAVA:
//             String urlParameters = "nombre_materia="+materia+"&area="+area+"&nivel="+nivel;
//En este caso de ejemplo se podrían recibir "nombre_materia", "area" y "nivel" que envían al internet los valores de sus respectivas variables.
//Uso de mysql_real_escape_string() evita ataques de inyección SQL

//Notar que se reciben los datos y se envian a la base sin ninguna validación, me parece que es mejor hacerlas en JAVA ya que se puede alertar al usuario directamente al estar en el mismo dispositivo.
//$nombre=mysql_real_escape_string($_POST["nombre"],$conn);
//$contrasena=mysql_real_escape_string($_POST["contrasena"],$conn);

$nombre=$_POST["nombre"];
$contrasena=$_POST["contrasena"];

//Armar la consulta sql 
$sql = "SELECT CEDULA FROM ALUMNO WHERE MAIL='".$nombre."' AND PASSWORD='".$contrasena."'";
//Realizar la consulta
$result=mysqli_query($conn, $sql);
	//En caso de que todo salga bien 
if (mysqli_num_rows($result) > 0) {
    // output data of each row
    while($row = mysqli_fetch_assoc($result)) {
        echo  $row["CEDULA"].";";
    }
} else {
	$sql= "SELECT CEDULA FROM TUTOR WHERE MAIL='".$nombre."' AND PASSWORD='".$contrasena."'";
    $result=mysqli_query($conn, $sql);
	if (mysqli_num_rows($result) > 0) {
    // output data of each row
    while($row = mysqli_fetch_assoc($result)) {
        echo  $row["CEDULA"].";";
    }
	} else{
	//Recordar que mas info es más consumo de datos, puede ser que baste con un número
    //echo "1"
	echo 0;
	}
	
}
    

//Cerrar conexión 
mysqli_close($conn);
?>

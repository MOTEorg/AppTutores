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
$cedula=$_POST["cedula"];
$codigogrupo=$_POST["codigogrupo"];


//Armar la consulta sql 
$sql = "INSERT INTO GRUPO_ALUMNOS (`GRUPO_ID`, `ALUMNO_ID`)  VALUES ('".$codigogrupo."','".$cedula."')";
//Realizar la consulta
if (mysqli_query($conn, $sql)) {
	//En caso de que todo salga bien 
    echo "Nuevo tutor insertado en la bd con éxito";
    //Recordar que mas info es más consumo de datos, puede ser que baste con un número
    //echo "1"
} else {
	//En caso de error
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
    //echo "-1"
}

//Cerrar conexión 
mysqli_close($conn);
?>

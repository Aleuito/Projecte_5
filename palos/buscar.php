<?php
	$servername = "localhost";
    $username   = "root";
  	$password   = "";
  	$dbname     = "equipament";

	$conn = new mysqli($servername, $username, $password, $dbname);
      if($conn->connect_error){
        die("Conexión fallida: ".$conn->connect_error);
      }

    $salida = "";

    $query = "SELECT * FROM pals ORDER By id_pals";

    if (isset($_POST['consulta'])) {
    	$q = $conn->real_escape_string($_POST['consulta']);
    	$query = "SELECT * FROM pals WHERE id_pals LIKE '%$q%' OR  nom LIKE '%$q%' OR talla LIKE '%$q%' OR preu LIKE '$q' ";
    }

    $resultado = $conn->query($query);

    if ($resultado->num_rows>0) {
    	$salida.="<table border=1 class='tabla'>
    			<thead>
    				<tr id='titulotabla'>
    					<td>ID PALOS</td>
    					<td>NOMBRE</td>
    					<td>TALLA</td>
    					<td>PRECIO</td>
    				</tr>

    			</thead>
    			

    	<tbody>";

    	while ($fila = $resultado->fetch_assoc()) {
    		$salida.=" <tr class='dentrotabla'>
    					<td>".$fila['id_pals']."</td>
    					<td>".$fila['nom']."</td>
    					<td>".$fila['talla']."</td>
    					<td>".$fila['preu']."</td>
    				</tr>";

    	}
    	$salida.="</tbody></table>";
    }else{
    	$salida.="NO HAY DATOS :(";
    }


    echo $salida;

    $conn->close();



?>
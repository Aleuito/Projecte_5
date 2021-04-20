<?php  
    $conexion=mysqli_connect('localhost', 'root', '','equipament');
    mysqli_set_charset($conexion,"utf8");

?> 

<!DOCTYPE html>
<html>
    <head>
    
    <title>SPEED WINTER</title>
    <meta charset="utf-8">
    
            
        <link rel="stylesheet" href="css/estilo.css">
        <link rel="stylesheet" href="iconos/css/fontello.css">

        
    </head>
    
  <body>
  <header>
        

        </header>
        

            <input type="checkbox" id="check"> <!-- esta es la parte del desplegable del menu, para cuando la pagina reduzca su tamaño -->
            <label for="check" class="icon-home-circled"></label>

            <nav class="menu">
                <ul>
                <li><a href="">INICIO</a></li>
                <li><a href="esqui/index.php">ESQUIS</a></li>
                <li><a href="botas/index.php">PALOS</a></li>
                <li><a href="botas/index.php">BOTAS</a></li>
                </ul>
            </nav>
        <br>


        
        <table class="tabla" >
            <tr class="titulotabla">
                <th>ID</th>
                <th>ESQUÍS</th>
                <th>TALLA</th>
                <th>PRECIO</th>
            </tr>


            
            <?php
            $sql="SELECT * from ESQUIS";
            $result= mysqli_query($conexion,$sql);
            while($mostrar=mysqli_fetch_array($result)) {
            ?>
            <tr class="dentrotabla">
                <td><?php echo $mostrar['id_esquis']?></td>
                <td><?php echo $mostrar['nom']?></td>
                <td><?php echo $mostrar['talla']?></td>
                <td><?php echo $mostrar['preu']?>€</td>
                
            </tr>
            <?php
            }
            ?>

        </table>   



            <table class="tabla" >
            <tr class="titulotabla">
                <th>ID</th>
                <th>BOTAS</th>
                <th>TALLA</th>
                <th>PRECIO</th>
            </tr>


            
            <?php
            $sql="SELECT * from botes";
            $result= mysqli_query($conexion,$sql);
            while($mostrar=mysqli_fetch_array($result)) {
            ?>
            <tr class="dentrotabla">
                <td><?php echo $mostrar['id_botes']?></td>
                <td><?php echo $mostrar['nom']?></td>
                <td><?php echo $mostrar['talla']?></td>
                <td><?php echo $mostrar['preu']?>€</td>
                
            </tr>
            <?php
            }
            ?>

        </table>    



        
        <table class="tabla" >
            <tr class="titulotabla">
                <th>ID</th>
                <th>PALOS</th>
                <th>TALLA</th>
                <th>PRECIO</th>
            </tr>


            
            <?php
            $sql="SELECT * from pals";
            $result= mysqli_query($conexion,$sql);
            while($mostrar=mysqli_fetch_array($result)) {
            ?>
            <tr class="dentrotabla">
                <td><?php echo $mostrar['id_pals']?></td>
                <td><?php echo $mostrar['nom']?></td>
                <td><?php echo $mostrar['talla']?></td>
                <td><?php echo $mostrar['preu']?>€</td>
                
            </tr>
            <?php
            }
            ?>

        </table> 










        

    </body> 
    
</html>
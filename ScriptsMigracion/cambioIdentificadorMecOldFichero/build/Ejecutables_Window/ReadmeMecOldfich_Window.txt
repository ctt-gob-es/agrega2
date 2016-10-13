
Funcionamiento del programa para modificar los identificadores MEC de ODES con formato antiguo en Window
--------------------------------------------------------------------------------------

ANTES DE EJECUTAR ESTE PROGRAMA ES MUY IMPORTANTE HACER UNA COPIA DE SEGURIDAD 
DE LA RUTA DEL REPOSITORIO SOBRE EL CUAL SE VA A LANZAR.

El paquete entregado estará formado por:

  -	migracionMecOldFich.txt.
  -	migracionOdesMecOldFich.bat.
  -	cambioIdentificadorMecOldFichero.zip.


Detallamos a continuación los pasos a realizar:

1.- Descomprimir el fichero cambioIdentificadorMecOldFichero.zip en una carpeta temporal, en el disco duro local.

2.- Editar el fichero migracionMecOldFich.txt y modificarlo poniendo las rutas de los Odes que van a ser modificados, por ejemplo:
    "F:/backup/ODEs/Proyectos/Alquimia/Secuencias/sd02_sentidos.zip"

3.- Ejecutar: migracionOdesMecOldFich.bat; antes de ejecutarlo, editar este fichero y modificarlo para que el parámetro archivo apunte a la ruta completa de migracionMecOldFich.txt.

*Durante la ejecución del script veremos en la pantalla, algo como:

  Migracion de Mec odes
  Borrando la carpeta C:\MDA\jboss-4.0.2\bin\uploads\Prueba\h10_a
  Borrando la carpeta C:\MDA\jboss-4.0.2\bin\uploads\Prueba\h2_a
  ........
  ........
  Finalización migracion de odes

*Los datos de salida son:
  - log.out: en el cual se indica lo que se va modificando
  - Informe de Odes : informe con los datos que se modifican y la rutas donde se encuentran los Odes. Se genera un fichero excel
    con un nombre similar a este: informeOdesIdentificador_20090714_1128648167.csv (cambiando _20090714_1128648167) dentro de la carpeta
    en la cual hemos descomprimido anteriormente cambioIdentificadorMecOldFichero.zip.
    

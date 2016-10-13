
Funcionamiento del programa para modificar taxones de ODES en Unix
--------------------------------------------------------------------------------------

ANTES DE EJECUTAR ESTE PROGRAMA ES MUY IMPORTANTE HACER UNA COPIA DE SEGURIDAD 
DE LA RUTA DEL REPOSITORIO SOBRE EL CUAL SE VA A LANZAR.

El paquete entregado estar� formado por:

  -	migracionOdesTaxones.sh.
  -	migracionOdesRepositorio.zip.
  - migracion.properties.


Detallamos a continuaci�n los pasos a realizar:

1.- Copiar todo el contenido en una carpeta temporal, en la m�quina correspondiente.

2.- Editar el fichero migracion.properties y modificarlo poniendo las rutas de los Odes que van a ser modificados, por ejemplo:
    "F:/backup/ODEs/Proyectos/Alquimia/Secuencias"

3.- Ejecutar: migracionOdesTaxones.sh; para ello el usuario debe tener los permisos correspondientes de ejecuci�n y modificaci�n.

*Durante la ejecuci�n del script veremos en la pantalla, algo como:

  Migracion de odes
  Borrando la carpeta C:\MDA\jboss-4.0.2\bin\uploads\Prueba\h10_a
  Borrando la carpeta C:\MDA\jboss-4.0.2\bin\uploads\Prueba\h2_a
  ........
  ........
  Finalizaci�n migracion de odes

*Los datos de salida son:
  - log.out: en el cual se indica lo que se va modificando
  - Informe de Odes : informe con los datos que se modifican y la rutas donde se encuentran los Odes. Se genera un fichero excel
    con un nombre similar a este: informeOdesTaxon_20090707_88742857.csv (cambiando _20090707_88742857) dentro de la carpeta
    en la cual hemos descomprimido anteriormente cambioOdesRepositorio.zip.
    

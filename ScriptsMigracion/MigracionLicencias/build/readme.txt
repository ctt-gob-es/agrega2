		SCRIPT MIGRACION LICENCIAS
		
¿Qué es?
------------

El script de migración añade a todos los odes publicados antes de la versión 1.2 un fichero licencia.txt con el texto legal de la licencia asociada al ode.

Este script de migración consta de los siguientes ficheros:

	 migracionOdesRepositorio.zip : Contiene los textos legales de todas las licencias soportadas por Agrega y el programa Java que se va a ejecutar durante la migración.

	 migracion.properties : Fichero de propiedades en el que se definirá la ruta donde se encuentra el repositorio con los odes publicados. 

	 migracionOdes.sh : script que lanza la llamada al programa Java.
	 

¿Cómo se ejecuta?
------------------

Los pasos previos a realizar antes de lanzar el script de migración son los siguientes:

	 mkdir url_carpeta donde url_carpeta es el directorio físico donde se copiarán los tres ficheros detallados anteriormente

	 cp migración*  url_carpeta/ 

	 dos2unix url_carpeta/*

	 chown –R jboss_user:jboss_group  url_carpeta

	 cd url_carpeta

	 ./migracionOdes.sh

También es muy importante realizar antes de la ejecución una copia de seguridad de todo el repositorio de odes publicados.	
 

Durante la ejecución del script aparte de preguntar al usuario si ha realizado la copia de seguridad de la carpeta repositorio le pregunta si quiere 
sobrescribir la licencia en el caso de que ya contenga el fichero licencia.txt.
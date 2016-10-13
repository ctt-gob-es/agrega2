		SCRIPT MIGRACION LICENCIAS
		
�Qu� es?
------------

El script de migraci�n a�ade a todos los odes publicados antes de la versi�n 1.2 un fichero licencia.txt con el texto legal de la licencia asociada al ode.

Este script de migraci�n consta de los siguientes ficheros:

	 migracionOdesRepositorio.zip : Contiene los textos legales de todas las licencias soportadas por Agrega y el programa Java que se va a ejecutar durante la migraci�n.

	 migracion.properties : Fichero de propiedades en el que se definir� la ruta donde se encuentra el repositorio con los odes publicados. 

	 migracionOdes.sh : script que lanza la llamada al programa Java.
	 

�C�mo se ejecuta?
------------------

Los pasos previos a realizar antes de lanzar el script de migraci�n son los siguientes:

	 mkdir url_carpeta donde url_carpeta es el directorio f�sico donde se copiar�n los tres ficheros detallados anteriormente

	 cp migraci�n*  url_carpeta/ 

	 dos2unix url_carpeta/*

	 chown �R jboss_user:jboss_group  url_carpeta

	 cd url_carpeta

	 ./migracionOdes.sh

Tambi�n es muy importante realizar antes de la ejecuci�n una copia de seguridad de todo el repositorio de odes publicados.	
 

Durante la ejecuci�n del script aparte de preguntar al usuario si ha realizado la copia de seguridad de la carpeta repositorio le pregunta si quiere 
sobrescribir la licencia en el caso de que ya contenga el fichero licencia.txt.
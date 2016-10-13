  	SCRIPT MIGRACION CODIGO MD5
		
�Qu� es?
------------

El script de migraci�n a�ade a todos los ODEs publicados antes de la versi�n 3.0 el c�digo MD5 que se calcula para todos los ODEs publicados en la plataforma.

Este script de migraci�n consta de los siguientes ficheros:

	 MigracionMD5.zip : Contiene el programa Java que se va a ejecutar durante la migraci�n.

	 arreglatorMD5.properties : Fichero de propiedades en el que se definir� la ruta donde se encuentra el repositorio con los odes publicados, las rutas de
	 							conexi�n a la BD donde residen las tablas ODE_PUBLICADO y LOCALIZADOR y la configuraci�n relevante al tipo de BD de la instalaci�n.

	 migracionMD5.sh : script que lanza la llamada al programa Java.
	 

�C�mo se ejecuta?
------------------

Los pasos previos a realizar antes de lanzar el script de migraci�n son los siguientes:

	 mkdir url_carpeta donde url_carpeta es el directorio f�sico donde se copiar�n los tres ficheros detallados anteriormente

	 cp *  url_carpeta/ 

	 dos2unix url_carpeta/*

	 chown �R jboss_user:jboss_group  url_carpeta

	 cd url_carpeta

	 ./migracionMD5.sh

Tambi�n es muy importante realizar antes de la ejecuci�n una copia de seguridad de la tabla ODE_PUBLICADO.	
 

Durante la ejecuci�n del script aparte de preguntar al usuario si ha realizado la copia de seguridad de la tabla ODE_PUBLICADO le pregunta si quiere 
realizar el c�lculo de los c�digos MD5 de los ODEs publicados.
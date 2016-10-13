  	SCRIPT MIGRACION CODIGO MD5
		
¿Qué es?
------------

El script de migración añade a todos los ODEs publicados antes de la versión 3.0 el código MD5 que se calcula para todos los ODEs publicados en la plataforma.

Este script de migración consta de los siguientes ficheros:

	 MigracionMD5.zip : Contiene el programa Java que se va a ejecutar durante la migración.

	 arreglatorMD5.properties : Fichero de propiedades en el que se definirá la ruta donde se encuentra el repositorio con los odes publicados, las rutas de
	 							conexión a la BD donde residen las tablas ODE_PUBLICADO y LOCALIZADOR y la configuración relevante al tipo de BD de la instalación.

	 migracionMD5.sh : script que lanza la llamada al programa Java.
	 

¿Cómo se ejecuta?
------------------

Los pasos previos a realizar antes de lanzar el script de migración son los siguientes:

	 mkdir url_carpeta donde url_carpeta es el directorio físico donde se copiarán los tres ficheros detallados anteriormente

	 cp *  url_carpeta/ 

	 dos2unix url_carpeta/*

	 chown –R jboss_user:jboss_group  url_carpeta

	 cd url_carpeta

	 ./migracionMD5.sh

También es muy importante realizar antes de la ejecución una copia de seguridad de la tabla ODE_PUBLICADO.	
 

Durante la ejecución del script aparte de preguntar al usuario si ha realizado la copia de seguridad de la tabla ODE_PUBLICADO le pregunta si quiere 
realizar el cálculo de los códigos MD5 de los ODEs publicados.
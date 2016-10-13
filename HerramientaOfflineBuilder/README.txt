HERRAMIENTAS AGREGA
===================

El contenido de este paquete es el conjunto de herramientas de la plataforma agrega preparadas para su instalacion y ejecucion en un ordenador personal sin necesidad de una conexión a intenet.

1. Requisitos de hardware
-------------------------
Para poder usar el paquete de HERRAMIENTAS AGREGA necesita:
- 250MB de espacio en disco duro
- 1GB de memoria RAM.

2. Requisitos de Software
-------------------------
Necesita tener el siguiente software instalado en su ordenador:
- Java Run-time enviroment (JRE) 1.5 o superior. Disponible en http://java.sun.com/javase/downloads/index.jsp. La versión de Agrega para Windows incluye el instalador de JRE para versiones de 32 bits.
- Navegador Web. Se recomienda Mozilla FireFox 1.0+ o Internet Explorer 7.0+

3. Pasos de instalacion
-----------------------

- Version Windows.

	* Para instalar la versión windows de la herramienta agrega simplemente haga doble click en el ejecutable y siga los pasos que se le indican.
	
- Version 'Todas las plataformas'

	* Descomprima el paquete en la localización deseada ([AGREGA_PATH]).
	* Ejecute el script setup.bat (o setup.sh en sistemas Unix) que se encuentra en el directorio [AGREGA_PATH]/agrega.
	* Cree los accesos directos para arrancar la aplicacion:
		+Acceso directo 'Iniciar servidor' que apunta al script [AGREGA_PATH]/agrega/apache-tomcat-5.5.26/bin/startup.bat (o startup.sh en sistemas Unix).
		+Acceso directo 'Detener servidor' que apunta al script [AGREGA_PATH]/agrega/apache-tomcat-5.5.26/bin/shutdown.bat (o shutdown.sh en sistemas Unix).
		+Acceso directo 'Herramientas Agrega' que apunta a la URL http://localhost:8080/herramientaoffline.
		
4.	Inicio de la aplicación
---------------------------
Para ejecutar la herramienta necesita ejecutar los siguientes pasos:
	- Iniciar el servidor Tomcat pulsando 'Iniciar Servidor'. Esto abrirá una ventana de mensajes con el título 'Tomcat'. Espere a que la ventana muestre el mensaje '[main] INFO  org.apache.catalina.startup.Catalina  - Server startup in xxxx ms'
	- Abrir la URL de la aplicación pulsando 'Herramientas Agrega'
	
Cuando haya terminado de operar con las herramientas, puede detener el servidor Tomcat pulsando 'Detener servidor'. Si no realiza este paso, el servidor continuará operativo ocupando recursos de su ordenador.

5.	Problemas
-------------
-----
*	El script 'Iniciar servidor' no hace nada. Cuando intento abrir 'Herramientas Agrega' me aparece una pantalla de error.
	-Necesita tener una maquina virtual de Java instalada y una variable de entorno apuntando a la carpeta donde esta instalada (por ejemplo C:\Archivos de programa\Java\jre1.6.0_06).
	En sistemas UNIX y MAC-OS, la variable de entorno a crear es JAVA_HOME.
	Si no tiene ninguna variable JRE_HOME (o JAVA_HOME) creada, cree una con el directorio donde se encuentra instalada la JRE (o JDK) de Java.
-----
*	El scrip 'Iniciar servidor abre una consola donde se muestra el mensaje de error 'java.net.BindException: Address already in use: JVM_Bind:8080'
	-Tiene una aplicación en su PC que está escuchando en el puerto 8080 (p.ej., Apache Tomcat, Jboss, Sun One Server, ...). Apague esa aplicación y vuelva a iniciar el servidor de Herramientas Agrega.
-----
*	En sistemas UNIX o MAC-OS falla la ejecución del script setup.sh y de los scripts de arranque de Tomcat.
	- Es posible que los scripts tengan un formato incorrecto de fin de linea. Si los ficheros han pasado por protocolos de transferencia de ficheros que soporten transformación de formatos, los scripts pueden haber sido modificados, quedando con el formato incorrecto. Para convertir un formato de fichero de texto de formato windows a formato UNIX o MAC-OS puede ejecutar el siguiente comando en una consola de unix (o MAC-OS):
	cat [nombre_fichero] | tr -d '\r' > [nuevo_fichero]
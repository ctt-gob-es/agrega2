#!/bin/bash
##############################################################
# Script configuration
##############################################################

# Indica la instalacion activa de la DB
# Solo una puede estar a true y determinara la instalacion activa
MYSQL_INSTALL=true
ORACLE_INSTALL=false

# Configuracion de la conexion a MySQL
DB=devel_indexador #Only for MySQL installations
DB_USER=devel_user
DB_PASS=devel_pass
DB_HOST=10.175.0.3 #Only for remote MySQL installations

# Configuracion de la conexion a ORACLE
#DB_USER=agrega
#DB_PASS=agrega
#ORACLE_SID=AZKARVIE #Only for remote ORACLE installations

# Path absoluto de los indices compas 
PATH_INDICES_COMPASS="/opt/jboss-devel/indicesCompass"

# Comando que al ejecutarlo inicia Jboss
JBOSS_START_COMMAND="/etc/init.d/jboss start"

# Comando que al ejecutarlo para Jboss
JBOSS_STOP_COMMAND="/etc/init.d/jboss stop"





##############################################################
# Script. NO EDITAR!!!!!!!!!!!!!!!!
##############################################################

# Internal patch configuration. Please don't edit.
PATH_INDICES_VACIOS="indicesVacios"
PATH_MYSQL_SCRIPT="CheckDB/checkMySQLdb.sql"
PATH_ORACLE_SCRIPT="CheckDB/checkORACLEdb.sql"
PATH_SYNC_CLIENT="sync2FS.jar"
SLEEP_STOP_JBOSS=900 #15 minutos
SLEEP_START_JBOSS=900
SLEEP_INDEX_SYNC=600
LOG_FILE=patch_installation.log

# Check environment/options

#[ "$(id -u)" = "0" ] || echo "Debes ser root para ejecutarme" && exit
# [ "${JBOSS_STOP_COMMAND}" != "" ] && echo nonulo || echo "No se a ajustado el valor de la variable JBOSS_STOP_COMMAND." && exit
# echo 1
# [ "${JBOSS_START_COMMAND}" != "" ] || echo "No se a ajustado el valor de la variable JBOSS_START_COMMAND." && exit;
# [ "${DB_USER}" != "" ] || echo "No se a ajustado el valor de la variable DB_USER." && exit;
# [ "${DB_PASS}" != "" ] || echo "No se a ajustado el valor de la variable DB_PASS." && exit;
# [ "${PATH_INDICES_COMPASS}" != "" ] || echo "No se a ajustado el valor de la variable PATH_INDICES_COMPASS." && exit;
# [ "${PATH_INDICES_VACIOS}" != "" ] || echo "No se a ajustado el valor de la variable PATH_INDICES_VACIOS." && exit;
# [ "${PATH_MYSQL_SCRIPT}" != "" ] || echo "No se a ajustado el valor de la variable PATH_MYSQL_SCRIPT." && exit;
# [ "${PATH_ORACLE_SCRIPT}" != "" ] || echo "No se a ajustado el valor de la variable PATH_ORACLE_SCRIPT." && exit;
# [ "${PATH_SYNC_CLIENT}" != "" ] || echo "No se a ajustado el valor de la variable PATH_SYNC_CLIENT." && exit;
# [ "${SLEEP_STOP_JBOSS}" != "" ] || echo "No se a ajustado el valor de la variable SLEEP_STOP_JBOSS." && exit;
# [ "${SLEEP_START_JBOSS}" != "" ] || echo "No se a ajustado el valor de la variable SLEEP_START_JBOSS." && exit;
# [ "${SLEEP_INDEX_SYNC}" != "" ] || echo "No se a ajustado el valor de la variable SLEEP_INDEX_SYNC." && exit;
# [ -d ${PATH_INDICES_COMPASS} ] || echo "La carpeta de indices compass '${PATH_INDICES_COMPASS}' no existe." && exit;
# [ -d ${PATH_INDICES_VACIOS} ] || echo "La carpeta de indices vacios '${PATH_INDICES_VACIOS}' no existe." && exit;



if [ "$(id -u)" != "0" ]; then 
	echo "Debes ser root para ejecutarme" 
	exit 
fi
if [ "${JBOSS_STOP_COMMAND}" = "" ]; then
	echo "No se a ajustado el valor de la variable JBOSS_STOP_COMMAND."
	exit
fi
if [ "${JBOSS_START_COMMAND}" = "" ]; then
	echo "No se a ajustado el valor de la variable JBOSS_START_COMMAND."
	exit
fi
if [ "${DB_USER}" = "" ]; then
	echo "No se a ajustado el valor de la variable DB_USER." 
	exit
fi
if [ "${DB_PASS}" = "" ]; then 
	echo "No se a ajustado el valor de la variable DB_PASS."
	exit
fi
if [ "${PATH_INDICES_COMPASS}" = "" ]; then 
	echo "No se a ajustado el valor de la variable PATH_INDICES_COMPASS."
	exit
fi
# Check if last character is /
function getLastChar () {
	local string=$1
	echo ${string#${string%?}}
}
# Remove last character if its needed
if [ $(getLastChar ${PATH_INDICES_COMPASS}) = "/" ]; then
	PATH_INDICES_COMPASS="$(dirname ${PATH_INDICES_COMPASS})/$(basename ${PATH_INDICES_COMPASS})"
fi
if [ "${PATH_INDICES_VACIOS}" = "" ]; then 
	echo "No se a ajustado el valor de la variable PATH_INDICES_VACIOS."
	exit
fi
if [ "${PATH_MYSQL_SCRIPT}" = "" ]; then 
	echo "No se a ajustado el valor de la variable PATH_MYSQL_SCRIPT."
	exit
fi
if [ "${PATH_ORACLE_SCRIPT}" = "" ]; then 
	echo "No se a ajustado el valor de la variable PATH_ORACLE_SCRIPT."
	exit
fi
if [ "${PATH_SYNC_CLIENT}" = "" ]; then 
	echo "No se a ajustado el valor de la variable PATH_SYNC_CLIENT."
	exit
fi
if [ "${SLEEP_STOP_JBOSS}" = "" ]; then 
	echo "No se a ajustado el valor de la variable SLEEP_STOP_JBOSS."
	exit
fi
if [ "${SLEEP_START_JBOSS}" = "" ]; then 
	echo "No se a ajustado el valor de la variable SLEEP_START_JBOSS."
	exit
fi
if [ "${SLEEP_INDEX_SYNC}" = "" ]; then 
	echo "No se a ajustado el valor de la variable SLEEP_INDEX_SYNC."
	exit
fi
if [ ! -d ${PATH_INDICES_COMPASS} ]; then 
	echo "La carpeta de indices compass '${PATH_INDICES_COMPASS}' no existe." 
	exit
fi
if [ ! -d ${PATH_INDICES_VACIOS} ]; then 
	echo "La carpeta de indices vacios '${PATH_INDICES_VACIOS}' no existe." 
	exit
fi
FILES="${PATH_MYSQL_SCRIPT} ${PATH_ORACLE_SCRIPT} ${PATH_SYNC_CLIENT}"
for f in ${FILES}; do
	if [ ! -s ${f} ]; then
		echo "Fichero ${f} no encontrado o vacio." 
		exit
	fi
done
if ! echo $SLEEP_STOP_JBOSS | grep "^[0-9]*$" &>/dev/null; then
	echo "La variable SLEEP_STOP_JBOSS debe ser un numero entero."
	exit
fi
if ! echo $SLEEP_START_JBOSS | grep "^[0-9]*$" &>/dev/null; then
	echo "La variable SLEEP_START_JBOSS debe ser un numero entero."
	exit
fi
if ! echo $SLEEP_INDEX_SYNC | grep "^[0-9]*$" &>/dev/null; then
	echo "La variable SLEEP_INDEX_SYNC debe ser un numero entero."
	exit
fi
if [ "${MYSQL_INSTALL}" != "true" ] && [ "${MYSQL_INSTALL}" != "false" ]; then
	echo "La variable MYSQL_INSTALL debe de tener un valor de true o false." 
	exit
fi
if [ "${ORACLE_INSTALL}" != "true" ] && [ "${ORACLE_INSTALL}" != "false" ]; then
	echo "La variable ORACLE_INSTALL debe de tener un valor de true o false."
	exit
fi
if [ "${MYSQL_INSTALL}" = "${ORACLE_INSTALL}" ]; then
	echo "No se ha configurado correctamente la instalacion para de la DB. Ajuste las variables MYSQL_INSTALL y ORACLE_INSTALL segun convenga." 
	exit
fi
if [ "${MYSQL_INSTALL}" = "true" ] && [ "${DB}" = "" ]; then  
	echo "Para una instalacion MySQL necesitas definir una DB sobre la que operar." 
	exit
fi
if [ "${DB_HOST}" != "" ]; then
	if ! ping -c 2 ${DB_HOST} &>/dev/null; then
		echo "El host ${DB_HOST} que contiene la base de datos no responde a ping y por tanto se supone que esta caido o fuera de alcance."
		exit
	fi	
fi
if ! which java &>/dev/null; then
	echo "Se necesita tener instalada la jdk de java y añadir al path la ruta $JAVA_HOME/bin."
	exit
fi
if [ "${MYSQL_INSTALL}" = "true" ]; then 
	if ! which mysql &>/dev/null; then
		echo "Se necesita tener instalado el cliente de mysql para operar sobre mysql. No olvide añadir al path la ruta del ejecutable del cliente mysql."
		exit
	fi
elif [ "${ORACLE_INSTALL}" = "true" ]; then 
	if ! which sqlplus &>/dev/null; then
		echo "Se necesita tener instalado el cliente de oracle para operar sobre oracle. No olvide añadir al path la ruta del ejecutable del cliente oracle."
		exit
	fi
fi
if [ "${LOG_FILE}" = "" ]; then
	LOG_FILE=/dev/null
else
	rm -rf ${LOG_FILE} &>/dev/null
	echo "El avance del proceso se guardara en ${LOG_FILE}."
fi


# Print warnings/notes
if [ "${MYSQL_INSTALL}" = "true" ] && [ "${DB_HOST}" = "" ]; then
	echo "Se ha configurado el script para actuar sobre una instalacion MySQL en localhost."
	echo "$(date) Se ha configurado el script para actuar sobre una instalacion MySQL en localhost." >> ${LOG_FILE}
elif [ "${ORACLE_INSTALL}" = "true" ] && [ "${ORACLE_SID}" = "" ]; then
	echo "Se ha configurado el script para actuar sobre una instalacion ORACLE en localhost."
	echo "$(date) Se ha configurado el script para actuar sobre una instalacion ORACLE en localhost." >> ${LOG_FILE}
fi
echo "El proceso tardara en ejecutarse aproximadamente $(expr ${SLEEP_STOP_JBOSS} / 60 + ${SLEEP_START_JBOSS} / 60 + ${SLEEP_INDEX_SYNC} / 60) minutos."
echo "$(date) El proceso tardara en ejecutarse aproximadamente $(expr ${SLEEP_STOP_JBOSS} / 60 + ${SLEEP_START_JBOSS} / 60 + ${SLEEP_INDEX_SYNC} / 60) minutos." >> ${LOG_FILE}

# Stop Jboss
#if ! ${JBOSS_STOP_COMMAND} &>/dev/null; then
#	echo "WARNING parando Jboss. Puede que ya este parado; revise el log correspondiente."
#	echo "$(date) WARNING parando Jboss. Puede que ya este parado; revise el log correspondiente." >> ${LOG_FILE}
#	exit
#else
	${JBOSS_STOP_COMMAND} 
	echo "$(date) Parando Jboss. Se esperara $(expr ${SLEEP_STOP_JBOSS} / 60) minutos..." >> ${LOG_FILE}
	echo -ne "Esperando a que Jboss se pare... (esto tardara $(expr ${SLEEP_STOP_JBOSS} / 60) minutos aproximadamente)\r"
	sleep ${SLEEP_STOP_JBOSS} 
	echo "Esperando a que Jboss se pare... OK                                             "
	echo "$(date) Finalizo la espera." >> ${LOG_FILE}
#fi

# Apply DB script
echo -ne "Aplicando parche a la DB... (esto tardara dependiendo de la cantidad de informacion almacenada en la DB)\r"
if [ "${MYSQL_INSTALL}" = "true" ]; then
	if [ "${DB_HOST}" != "" ]; then
		if ! mysql -u ${DB_USER} -p${DB_PASS} -h ${DB_HOST} ${DB} < ${PATH_MYSQL_SCRIPT}; then
			echo "ERROR al aplicar el script a la DB en MySQL. Revise que la configuracion de conexion a la base de datos es correcta y que se tienen los suficientes permisos."
			echo "$(date) ERROR al aplicar el script a la DB en MySQL. Revise que la configuracion de conexion a la base de datos es correcta y que se tienen los suficientes permisos." >> ${LOG_FILE}
			exit
		fi
	else
		if ! mysql -u ${DB_USER} -p${DB_PASS} ${DB} < ${PATH_MYSQL_SCRIPT}; then
			echo "ERROR al aplicar el script a la DB en MySQL. Revise que la configuracion de conexion a la base de datos es correcta y que se tienen los suficientes permisos."
			echo "$(date) ERROR al aplicar el script a la DB en MySQL. Revise que la configuracion de conexion a la base de datos es correcta y que se tienen los suficientes permisos." >> ${LOG_FILE}
			exit
		fi
	fi
elif [ "${ORACLE_INSTALL}" = "true" ]; then
	if [ "${ORACLE_SID}" != "" ]; then
		if ! sqlplus ${DB_USER}/${DB_PASS}@${ORACLE_SID} < ${PATH_ORACLE_SCRIPT}; then
			echo "ERROR al aplicar el script a la DB en ORACLE. Revise que la configuracion de conexion a la base de datos es correcta y que se tienen los suficientes permisos."
			echo "$(date) ERROR al aplicar el script a la DB en ORACLE. Revise que la configuracion de conexion a la base de datos es correcta y que se tienen los suficientes permisos." >> ${LOG_FILE}
			exit
		fi
	else
		if ! sqlplus ${DB_USER}/${DB_PASS} < ${PATH_ORACLE_SCRIPT}; then
			echo "ERROR al aplicar el script a la DB en ORACLE. Revise que la configuracion de conexion a la base de datos es correcta y que se tienen los suficientes permisos."
			echo "$(date) ERROR al aplicar el script a la DB en ORACLE. Revise que la configuracion de conexion a la base de datos es correcta y que se tienen los suficientes permisos." >> ${LOG_FILE}
			exit
		fi
	fi
fi
echo "Aplicando parche a la DB... OK                                                                                  "
echo "Parche aplicado a la DB satisfactoriamente" >> ${LOG_FILE}

# Get the owner, group y Compass index acess rigths
INDEX_OWN=$(stat -c %U ${PATH_INDICES_COMPASS})
INDEX_GRP=$(stat -c %G ${PATH_INDICES_COMPASS})
ACCESS_RIGTHS=$(stat -c %a ${PATH_INDICES_COMPASS})

# Delete old compass index and copy empty indexes
PATH_BACKUP="${PATH_INDICES_COMPASS}_$(date +%Y%m%d-%H%M%S-%N)_backup"
echo "Se creara un backup de los indices actuales en ${PATH_BACKUP}."
echo "$(date) Se creara un backup de los indices actuales en ${PATH_BACKUP}."  >> ${LOG_FILE}

echo -ne "Reemplazando indices locales por indices vacios... (esto tardara dependiendo de la cantidad de informacion almacenada en los indices)\r"
if ! mv ${PATH_INDICES_COMPASS} ${PATH_BACKUP} &>/dev/null; then
	echo -ne "Reemplazando indices locales por indices vacios... ERROR: no se ha podido mover ${PATH_INDICES_COMPASS} a ${PATH_BACKUP}."
	exit
fi
if ! mkdir -p ${PATH_INDICES_COMPASS} &>/dev/null; then
	echo -ne "Reemplazando indices locales por indices vacios... ERROR: no se ha podido crear el directorio ${PATH_INDICES_COMPASS}, es posible que ya existiera."
	exit
fi
cp -rf ${PATH_INDICES_VACIOS}/* ${PATH_INDICES_COMPASS}
echo "Reemplazando indices locales por indices vacios... OK                                                                                      "
echo "$(date) Indices locales reemplazados por indices vacios." >> ${LOG_FILE}

function rollback () {
	echo "$(date) Debido a un error se procede a restaurar los indices previos desde el backup ${PATH_BACKUP}" >> ${LOG_FILE}
	echo -ne "Debido a un error se procede a restaurar los indices previos... (esto tardara dependiendo de la cantidad de informacion almacenada en los indices)\r"
	rm -rf --preserve-root ${PATH_INDICES_COMPASS}
	mv ${PATH_BACKUP} ${PATH_INDICES_COMPASS} 
	rm -rf --preserve-root ${PATH_BACKUP}
	echo "Debido a un error se procede a restaurar los indices previos... hecho                                                                                  "
}

# Apply old config to new index
if ! chown -R ${INDEX_OWN} ${PATH_INDICES_COMPASS}; then
	echo "ERROR al asignar el propietario '${INDEX_OWN}' a la ruta '${PATH_INDICES_COMPASS}'"
	echo "$(date) ERROR al asignar el propietario '${INDEX_OWN}' a la ruta '${PATH_INDICES_COMPASS}'" >> ${LOG_FILE}
	rollback
	exit
fi
if ! chgrp -R ${INDEX_GRP} ${PATH_INDICES_COMPASS}; then
	echo "ERROR al asignar el grupo '${INDEX_GRP}' a la ruta '${PATH_INDICES_COMPASS}'"
	echo "$(date) ERROR al asignar el grupo '${INDEX_GRP}' a la ruta '${PATH_INDICES_COMPASS}'" >> ${LOG_FILE}
	rollback
	exit
fi
if ! chmod -R ${ACCESS_RIGTHS} ${PATH_INDICES_COMPASS}; then
	echo "ERROR al asignar permisos '${ACCESS_RIGTHS}' a la ruta '${PATH_INDICES_COMPASS}'"
	echo "$(date) ERROR al asignar permisos '${ACCESS_RIGTHS}' a la ruta '${PATH_INDICES_COMPASS}'" >> ${LOG_FILE}
	rollback
	exit
fi

# Start Jboss
if ! ${JBOSS_START_COMMAND} &>/dev/null; then
	echo "ERROR iniciando Jboss. Puede que ya este en marcha; revise el log correspondiente."
	echo "$(date) ERROR iniciando Jboss. Puede que ya este en marcha; revise el log correspondiente." >> ${LOG_FILE}
	exit
fi
echo "$(date) Iniciando Jboss. Se esperara $(expr ${SLEEP_STOP_JBOSS} / 60) minutos..." >> ${LOG_FILE}
echo -ne "Esperando a que Jboss se inicie... (esto tardara $(expr ${SLEEP_START_JBOSS} / 60) minutos aproximadamente)\r"
sleep ${SLEEP_START_JBOSS} 
echo "Esperando a que Jboss se inicie... OK                                             "
echo "$(date) Finalizo la espera." >> ${LOG_FILE}

# Call sync service
java -jar ${PATH_SYNC_CLIENT}
echo "$(date) Volcando la informacion de la DB al indice local. Se esperara $(expr ${SLEEP_INDEX_SYNC} / 60) minutos..." >> ${LOG_FILE}
echo -ne "Volcando la informacion de la DB al indice local... (esto tardara $(expr ${SLEEP_INDEX_SYNC} / 60) minutos aproximadamente)\r"
sleep ${SLEEP_INDEX_SYNC} 
echo "Volcando la informacion de la DB al indice local... OK                                             "
echo "$(date) Finalizo la espera." >> ${LOG_FILE}
echo "Parche aplicado correctamente."
echo "$(date) Parche aplicado correctamente." >> ${LOG_FILE}

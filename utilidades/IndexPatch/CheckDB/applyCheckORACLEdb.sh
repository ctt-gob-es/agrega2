#!/bin/bash

#Config vodafone
#DB=devel_indexador #este parametro no es necesario
DB_USER=agrega
DB_PASS=agrega
DB_HOST=10.175.0.22


#Internal script config (don't change)
SQL_FILE="checkORACLEdb.sql"
SSH_USER="desarro"
SSH_USERHOME="/home/desarro"
ORACLE_HOME="/home/oracle/app/oracle/product/11.2.0/dbhome_3"


if [ ! -f "${SQL_FILE}" ]; then
	echo "'${SQL_FILE}' not found"
	exit
fi

if ping -c 1 ${DB_HOST} &>/dev/null; then
	scp ${SQL_FILE} ${SSH_USER}@${DB_HOST}:${SSH_USERHOME}
	#COMMAND="export ORACLE_HOME=${ORACLE_HOME}; ${ORACLE_HOME}/bin/sqlplus ${DB_USER}/${DB_PASS} < ${SSH_USERHOME}/${SQL_FILE}"
	COMMAND="export ORACLE_HOME=${ORACLE_HOME}; echo \"START ${SSH_USERHOME}/${SQL_FILE}\" | ${ORACLE_HOME}/bin/sqlplus ${DB_USER}/${DB_PASS}"
	#echo $COMMAND
    ssh ${SSH_USER}@${DB_HOST} "${COMMAND}; rm -rfv ${SSH_USERHOME}/${SQL_FILE}" 
else
    echo "host ${DB_HOST} doesn't reply to ping"
	exit
fi

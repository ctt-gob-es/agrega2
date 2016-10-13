#!/bin/bash

#Config desarrollo
DB=devel_indexador
DB_USER=devel_user
DB_PASS=devel_pass
DB_HOST=10.175.0.3

#Config ccaa
#DB=ccaa
#DB_USER=ccaa 		#par user/pass 1
#DB_PASS=ccaa 		#par user/pass 1
#DB_USER=root		#par user/pass 2
#DB_PASS=indra2007 	#par user/pass 2
#DB_HOST=10.180.0.200

#Config redes
#DB=redes
#DB_USER=redes
#DB_PASS=redes
#DB_HOST=10.180.0.200


#Internal script config (don't change)
SQL_FILE="checkMySQLdb.sql"
SSH_USER=root
SSH_USERHOME="/root"

if [ ! -f "${SQL_FILE}" ]; then
	echo "'${SQL_FILE}' not found"
	exit
fi

if ping -c 1 ${DB_HOST} &>/dev/null; then
	scp ${SQL_FILE} ${SSH_USER}@${DB_HOST}:${SSH_USERHOME}
	COMMAND="mysql -u ${DB_USER} -p${DB_PASS} ${DB} < ${SSH_USERHOME}/${SQL_FILE}"
    ssh ${SSH_USER}@${DB_HOST} "${COMMAND}; rm -rfv ${SSH_USERHOME}/${SQL_FILE}" 
else
    echo "host ${DB_HOST} doesn't reply to ping"
	exit
fi

# if ! mysql -u $DB_USER -p${DB_PASS} -h ${DB_HOST} ${DB} < ${SQL_FILE}; then
	# echo "ERROR"
# else
	# echo "OK"
# fi

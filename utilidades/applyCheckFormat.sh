OLD_INDEX=indicesRemotos_old
NEW_INDEX=indicesRemotos_patched
CHECK_FORMAT_JAR=checkFormat.jar
NEW_FORMAT_FLAG="-1" #-1 to unknown and -2 to empty

echo -n "Deleting files in ${NEW_INDEX}... "
rm -rf ${NEW_INDEX}/*
echo "done"

echo "Copying original index files in ${NEW_INDEX}... "
for d in $(ls -d ${OLD_INDEX}/*/); do
	if [ -d "${d}" ]; then
		cp -rf ${d} ${NEW_INDEX}
		echo -e "\t${d} copied"
	fi
done
echo "copy done"

echo "Modifying ${NEW_INDEX} index files... "
for d in $(ls -d ${NEW_INDEX}/*/); do
	if [ -d ${d} ]; then
		echo "Modifying index ${d}..."
		java -jar ${CHECK_FORMAT_JAR} ${NEW_FORMAT_FLAG} ${d}/index/idioma_es
		echo
	fi
done

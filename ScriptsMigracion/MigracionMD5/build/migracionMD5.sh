#!/bin/bash

echo ==========================================================================
echo Script de Migracion de codigo MD5
echo
echo Es conveniente hacer una copia de seguridad de tabla de ODE_PUBLICADO
echo ==========================================================================

#COMPROBACION DE PASOS PREVIOS A LA EJECUCION DEL SCRIPT
echo -e "¿Ha realizado la copia de seguridad de la tabla de ODE_PUBLICADO? [y]es, [n]o: \c"
read opcion
if [ "$opcion" = "y" ];then
#Preguntamos al usuario si desea que se calculen los MD5 de los odes publicados sin MD5
repetir=true
while [ $repetir = "true" ]
do
echo -e "¿Desea calcular los MD5 de los odes publicados? [y]es, [n]o: \c"
read opcion
if [ "$opcion" = "y" ];then
sobrescribir="y"
repetir=false
else
if [ "$opcion" = "n" ];then
sobrescribir="n"
repetir=false
else
echo -e "La opcion introducida no es correcta \c"
fi
fi
done

# Si no hacemos esto, al leer el parametro del path 
dos2unix ./arreglatorMD5.properties

#COMPROBACION DE INFORMACION NECESARIA ANTES DE LA EJECUCION DEL SCRIPT
#Comprobamos si existe el directorio repositorio que viene definido en el fichero arreglatorMD5.properties
if [ -e ./arreglatorMD5.properties ];then
urlRepositorio=`grep "path.base.odes=" -m 1 arreglatorMD5.properties | cut -c16-1000`
echo 
echo Carpeta repositorio: $urlRepositorio
echo 
if [ -d $urlRepositorio ]
then

#Comprobamos si no esta vacía la carpeta repositorio
estaVacio=`ls -l $urlRepositorio | wc -l`
tamanio=1
if [ $estaVacio -gt $tamanio ]
then


#Comprobamos si el usuario que ha ejecutado el script tiene permisos de escritura en la carpeta repositorio
echo Usuario del script: $USER
echo
if [ -f ./MigracionMD5.zip ]
then
#Comprobamos si existe el directorio MigracionMD5 en caso afirmativo
#no se descomprimirá el zip
if [ -d ./MigracionMD5 ]
then
        echo Ya se ha descomprimido el zip previamente
echo
else
unzip -q MigracionMD5.zip -d ./MigracionMD5
echo se ha hecho la descompresion

fi

#Accedemos al directorio ./MigracionMD5. y lanzamos el jar (java)
cd ./MigracionMD5

java -Dconfiguracion=../arreglatorMD5.properties -jar MigracionMD5-1.0.jar
cd ..
cp -p ./MigracionMD5/Hibernate.out .
cp -p ./MigracionMD5/Migracion.out .
rm -r ./MigracionMD5
else
echo El usuario con el que se ha lanzado el script de migracion no es el mismo que el propietario de la carpeta repositorio, por favor vuelve a lanzar el script con el usuario correcto
exit 1
fi
else
echo La carpeta repositorio esta vacía por favor revise el fichero arreglatorMD5.properties
fi
else
echo No existe esa carpeta repositorio $urlRepositorio por favor revise el fichero arreglatorMD5.properties
exit 1
fi
else
echo No existe el fichero arreglatorMD5.properties necesario para la ejecucion de la migración.
exit 1
fi
else
echo Antes de ejecutar este script debe realizar una copia de seguridad del repositorio
exit 1
fi

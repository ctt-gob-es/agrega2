#!/bin/bash

echo ==========================================================================
echo Script de Cambio de Identificadores MEC en formato antiguo
echo
echo Es conveniente hacer una copia de seguridad de la ruta sobre la cual se lanza
echo ==========================================================================

echo -e "¿Ha realizado la copia de seguridad del repositorio de odes? [y]es, [n]o: \c"
read opcion
if [ "$opcion" = "y" ];then

#Comprobamos si existe el directorio repositorio que viene definido en el fichero cambio.properties
if [ -e cambio.properties ];then
urlRepositorio=`cut -c13-1000 cambio.properties`
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
#echo Usuario del script: $USER
#echo
#if [ -O $urlRepositorio ]
#then
#Comprobamos si existe el directorio cambioOdesRepositorio en caso afirmativo
#no se descomprimirá el zip
if [ -d cambioOdesRepositorio ]
then
        echo Ya se ha descomprimido el zip previamente
echo
else
unzip -q cambioOdesRepositorio.zip -d cambioOdesRepositorio
echo  Descomprimiendo el zip

fi

#Accedemos al directorio cambioOdesRepositorio y lanzamos el jar (java)
cd cambioOdesRepositorio

java -Drepositorio=$urlRepositorio -jar cambioIdentificadorAntiguo-1.0.jar
cd ..
cp -p cambioOdesRepositorio/log.out .
cp -p cambioOdesRepositorio/informeOdesIdentificador_* .

rm -r cambioOdesRepositorio

#else
#echo El usuario con el que se ha lanzado el script de migracion no es el mismo que el propietario de la carpeta repositorio, por favor vuelve a lanzar el script con el usuario correcto
#exit 1
#fi
else
echo La carpeta repositorio esta vacía por favor revise el fichero cambio.properties
fi
else
echo No existe esa carpeta repositorio por favor revise el fichero cambio.properties
exit 1
fi
else
echo No existe el fichero cambio.properties necesario para la ejecucion de la migración.
exit 1
fi
else
echo Antes de ejecutar este script debe realizar una copia de seguridad del repositorio
exit 1
fi

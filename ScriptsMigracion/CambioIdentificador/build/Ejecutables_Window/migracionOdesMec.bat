@ECHO off
cls
:start
ECHO.
ECHO PARAMETROS DE USO DE LA APLICACION
ECHO -----------------------------------
ECHO y. Ejecución del programa habiendo realizado antes un Backup del repositorio.
ECHO n. No hemos realizado Backup del repositorio y vamos a salir.
ECHO.

set choice=
set /p choice= Desea ejecutar el programa?
if not '%choice%'=='' set choice=%choice:~0,1%
if '%choice%'=='y' goto hello
if '%choice%'=='n' goto bye
ECHO "%choice%" Opción no válida, introducir de nuevo.
ECHO.
goto start
:hello
  cd cambioOdesRepositorio
  java -Darchivo=D:/localRepo/MigracionTaxones/MigracionMecProyectos/migracionMec.txt -jar cambioIdentificador-1.0.jar
  cd ..
goto end
:bye  
  echo.
  goto end
:end

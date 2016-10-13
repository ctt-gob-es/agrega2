set STCMD_PATH=echo;rem "C:\Archivos de programa\Borland\StarTeam Cross-Platform Client 2006 R2\stcmd"
set STCMD_USER=fjespino
set STCMD_PASS=fjespino
set STCMD_SERVER_IP=172.22.145.42
set STCMD_SERVER_PORT=49320
set TARGET=target\agrega
set APACHE_BIN=%TARGET%\apache-tomcat-5.5.26\bin
set DEPLOY_DIR=%TARGET%\apache-tomcat-5.5.26\webapps
set DB_OPTIONS_MODIFICADOR=-DdataSource.driver.class=org.hsqldb.jdbcDriver -DdataSource.url=jdbc:hsqldb:file:db/modificador -DdataSource.user=sa -DdataSource.password="" -Dsql.mappings=HypersonicSql -Dhibernate.db.dialect=org.hibernate.dialect.HSQLDialect
set DB_OPTIONS_LOCALIZADOR=-DdataSource.driver.class=org.hsqldb.jdbcDriver -DdataSource.url=jdbc:hsqldb:file:db/localizador -DdataSource.user=sa -DdataSource.password="" -Dsql.mappings=HypersonicSql -Dhibernate.db.dialect=org.hibernate.dialect.HSQLDialect

set STCMD_PROYECT_PREFIX=%STCMD_USER%:%STCMD_PASS%@%STCMD_SERVER_IP%:%STCMD_SERVER_PORT%/PODE
REM set STCMD_CO_COMMAND=co -is -stop -cmp -o -q -nologo
set STCMD_CO_COMMAND=co -is -stop -cmp -nologo -nel -rw
rem set STCMD_VIEW_PREFIX=Mantenimiento Fase III
rem set STCMD_LABEL_PREFIX=-vl LB_DYC33.20080529


rem Opcion de debug: si se ejecuta con nombre de modulo, salta a la descarga y compilacion del modulo y acaba en pausa
echo  %1 
if NOT "%1"=="" goto %1

rem Borrando ficheros
echo "Borrando ficheros de build anterior ..."
if exist target rmdir /Q/S target
mkdir %TARGET%
mkdir %APACHE_BIN%
mkdir %DEPLOY_DIR%
mkdir %TARGET%\dependencias
rem Descargo Apache al target. Creo las carpetas para que se generen las carpetas vacias de apache (uploads, uploads/taller, ...)
call %STCMD_PATH% local-mkdir -is  -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/HerramientaOfflineBuilder/apache-tomcat-5.5.26" -fp %TARGET%\apache-tomcat-5.5.26
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/HerramientaOfflineBuilder/apache-tomcat-5.5.26" -fp %TARGET%\apache-tomcat-5.5.26

rem Iconos y scripts
call copy /Y agrega.ico %TARGET%\agrega.ico
call copy /Y setup.bat %TARGET%\setup.bat
rem DESCARGO XMLs a apache/bin/uploads/xmls
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/FuentesTaxonomicas/DyC/fuentestaxonomicas/core/src/resources/xmls" -fp %APACHE_BIN%\uploads\xmls
rem DESCARGO esquemas XSD a uploads/schemas: SCORM
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p %STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/11_Configuraci¢n/schemas -fp %APACHE_BIN%\uploads\schemas
rem DESCARGO esquemas XSD a uploads/schemas: LOMES
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/ParseadorXML1.1/DyC/parseadorXML/src/esquema/LOM-ES" -fp %APACHE_BIN%\uploads\schemas


mkdir .\soporteecho %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/Soporte/DyC/Soporte" -fp .\soporte
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/Soporte/DyC/Soporte" -fp .\soporte
cd soporte
call maven clean jar:install -Dmaven.test.skip -Dagrega.offline
cd ..


echo %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/ParseadorXML1.1/DyC/parseadorXML" -fp .\parseadorXML
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/ParseadorXML1.1/DyC/parseadorXML" -fp .\parseadorXML
cd parseadorXML
call maven clean jar:install -Dmaven.test.skip -Dagrega.offline
cd ..


echo %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/Estaticos/DyC/static" -fp .\static
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/Estaticos/DyC/static" -fp .\static
call robocopy /E .\static %DEPLOY_DIR%\static
:fuentestaxonomicas
rem Descargo y construyo el proyecto fuentestaxonomicas
echo %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/FuentesTaxonomicas/DyC/fuentestaxonomicas" -fp .\fuentestaxonomicas
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/FuentesTaxonomicas/DyC/fuentestaxonomicas" -fp .\fuentestaxonomicas
cd fuentestaxonomicas
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\fuentestaxonomicas\web\target\fuentestaxonomicas-1.war %DEPLOY_DIR%\fuentestaxonomicas-1
call copy /Y D:\localRepo\fuentestaxonomicas\dependencies\*.jar %TARGET%\dependencias
if NOT "%1"=="" pause
rem Descargo y construyo el proyecto validador
:validador
echo %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/Validador/DyC/validador" -fp .\validador
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/Validador/DyC/validador" -fp .\validador
cd validador
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\validador\web\target\validador-1.war %DEPLOY_DIR%\validador-1
call copy /Y D:\localRepo\validador\dependencies\*.jar %TARGET%\dependencias
if NOT "%1"=="" pause
rem Descargo y construyo el proyecto modificador
:modificador
echo %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/HerramientaModificacion/DyC/modificador" -fp .\modificador
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/HerramientaModificacion/DyC/modificador" -fp .\modificador
cd modificador
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false 
cd ..
call robocopy /E .\modificador\web\target\modificador-1.war %DEPLOY_DIR%\modificador-1
call copy /Y D:\localRepo\modificador\dependencies\*.jar %TARGET%\dependencias
if NOT "%1"=="" pause
rem Descargo y construyo el proyecto ModificadorWeb
:ModificadorWeb
echo %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/ModificadorWeb/DyC/ModificadorWeb" -fp .\ModificadorWeb
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/ModificadorWeb/DyC/ModificadorWeb" -fp .\ModificadorWeb
cd ModificadorWeb
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\ModificadorWeb\web\target\ModificadorWeb-1.war %DEPLOY_DIR%\ModificadorWeb
call copy /Y D:\localRepo\ModificadorWeb\dependencies\*.jar %TARGET%\dependencias
if NOT "%1"=="" pause
rem Descargo y construyo el proyecto Localizador
:localizador
echo %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/Localizador/DyC/localizador" -fp .\localizador
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/Localizador/DyC/localizador" -fp .\localizador
cd localizador
call maven clean install %DB_OPTIONS_LOCALIZADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\localizador\web\target\localizador-1.war %DEPLOY_DIR%\localizador-1
call copy /Y D:\localRepo\localizador\dependencies\*.jar %TARGET%\dependencias
if NOT "%1"=="" pause
rem Descargo y construyo el proyecto Visualizador
:visualizador
echo %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/Visualizador/DyC/visualizador" -fp .\visualizador
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/Visualizador/DyC/visualizador" -fp .\visualizador
cd visualizador
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\visualizador\web\target\visualizador-1.war %DEPLOY_DIR%\visualizador-1
call copy /Y D:\localRepo\visualizador\dependencies\*.jar %TARGET%\dependencias
if NOT "%1"=="" pause
rem Descargo y construyo el proyecto Entregar
:entregar
echo %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/Entregar/DyC/entregar" -fp .\entregar
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/Entregar/DyC/entregar" -fp .\entregar
cd entregar
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\entregar\web\target\entregar-1.war %DEPLOY_DIR%\entregar-1
call copy /Y D:\localRepo\entregar\dependencies\*.jar %TARGET%\dependencias
if NOT "%1"=="" pause
rem Descargo y construyo el proyecto catalogacion
:catalogacion
echo %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/Catalogacion2/DyC/catalogacion" -fp .\catalogacion
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/Catalogacion2/DyC/catalogacion" -fp .\catalogacion
cd catalogacion
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\catalogacion\web\target\catalogacion-1.war %DEPLOY_DIR%\catalogacion-1
call copy /Y D:\localRepo\catalogacion\dependencies\*.jar %TARGET%\dependencias
if NOT "%1"=="" pause
rem Descargo y construyo el proyecto empaquetadorbasico
:empaquetadorbasico
echo %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/Empaquetacion/DyC/empaquetadorbasico" -fp .\empaquetadorbasico
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/Empaquetacion/DyC/empaquetadorbasico" -fp .\empaquetadorbasico
cd empaquetadorbasico
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\empaquetadorbasico\web\target\empaquetadorbasico-F1.war %DEPLOY_DIR%\empaquetadorbasico-F1
call copy /Y D:\localRepo\empaquetadorbasico\dependencies\*.jar %TARGET%\dependencias
if NOT "%1"=="" pause
rem Descargo y construyo el proyecto catalogadorWeb
:catalogadorWeb
echo %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/CatalogadorWeb/DyC/catalogadorWeb" -fp .\catalogadorWeb
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/CatalogadorWeb/DyC/catalogadorWeb" -fp .\catalogadorWeb
cd catalogadorWeb
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\catalogadorWeb\web\target\catalogadorWeb-1.war %DEPLOY_DIR%\catalogadorWeb
call copy /Y D:\localRepo\catalogadorWeb\dependencies\*.jar %TARGET%\dependencias
if NOT "%1"=="" pause
rem Descargo y construyo el proyecto PortalEmpaquetador
:PortalEmpaquetador
echo %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/PortalEmpaquetador/DyC/PortalEmpaquetador" -fp .\PortalEmpaquetador
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/PortalEmpaquetador/DyC/PortalEmpaquetador" -fp .\PortalEmpaquetador
cd PortalEmpaquetador
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\PortalEmpaquetador\web\target\PortalEmpaquetador-F1.war %DEPLOY_DIR%\PortalEmpaquetador
call copy /Y D:\localRepo\PortalEmpaquetador\dependencies\*.jar %TARGET%\dependencias
if NOT "%1"=="" pause
:herramientaoffline
echo %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/HerramientaOffline/DyC/herramientaoffline" -fp .\herramientaoffline
call %STCMD_PATH% %STCMD_CO_COMMAND% %STCMD_LABEL_PREFIX% -p "%STCMD_PROYECT_PREFIX%/%STCMD_VIEW_PREFIX%/04_Subsistemas/HerramientaOffline/DyC/herramientaoffline" -fp .\herramientaoffline
cd herramientaoffline
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\herramientaoffline\web\target\herramientaoffline-1.war %DEPLOY_DIR%\herramientaoffline
call copy /Y D:\localRepo\herramientaoffline\dependencies\*.jar %TARGET%\dependencias
if NOT "%1"=="" pause

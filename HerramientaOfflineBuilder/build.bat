rem
rem	DEPRECADA: Desde la version 2.0.1 se compila la version offline con Ant a partir del script diponible en SVN/agrega/utilidades
rem
rem
echo off
set SVN_USER=dgonzalezd
set SVN_PASS=dgonzalezd
set SVN_URL=svn://desarrollo.agrega.indra.es:3690/agrega
set SVN_VERSION=%1

set SVN_PATH=trunk
if NOT "%SVN_VERSION%"=="" set SVN_PATH=tags/%SVN_VERSION%

if NOT "%SVN_VERSION%"=="" set PLANTILLAS_LABEL=-Dmaven.scm.branch=%SVN_PATH%

echo VERSION %SVN_VERSION%
echo SVN_PATH %SVN_PATH%
echo PLANTILLAS_LABEL %PLANTILLAS_LABEL%
pause

set TARGET=target\agrega
set APACHE_BIN=%TARGET%\apache-tomcat-5.5.26\bin
set DEPLOY_DIR=%TARGET%\apache-tomcat-5.5.26\webapps
set DB_OPTIONS_MODIFICADOR=-DdataSource.driver.class=org.hsqldb.jdbcDriver -DdataSource.url=jdbc:hsqldb:file:db/modificador -DdataSource.user=sa -DdataSource.password="" -Dsql.mappings=HypersonicSql -Dhibernate.db.dialect=org.hibernate.dialect.HSQLDialect
set DB_OPTIONS_LOCALIZADOR=-DdataSource.driver.class=org.hsqldb.jdbcDriver -DdataSource.url=jdbc:hsqldb:file:db/localizador -DdataSource.user=sa -DdataSource.password="" -Dsql.mappings=HypersonicSql -Dhibernate.db.dialect=org.hibernate.dialect.HSQLDialect
set DB_OPTIONS_ENTREGAR=-DdataSource.driver.class=org.hsqldb.jdbcDriver -DdataSource.url=jdbc:hsqldb:file:db/entregar -DdataSource.user=sa -DdataSource.password="" -Dsql.mappings=HypersonicSql -Dhibernate.db.dialect=org.hibernate.dialect.HSQLDialect

rem Borrando ficheros
echo "Borrando ficheros de build anterior ..."
if exist target rmdir /Q/S target
if exist fuentestaxonomicas rmdir /Q/S fuentestaxonomicas
if exist validador rmdir /Q/S validador
if exist modificador rmdir /Q/S modificador 
if exist ModificadorWeb rmdir /Q/S ModificadorWeb 
if exist soporte rmdir /Q/S soporte
if exist parseadorXML rmdir /Q/S parseadorXML
if exist static rmdir /Q/S static
if exist visualizador rmdir /Q/S visualizador
if exist entregar rmdir /Q/S entregar
if exist empaquetadorbasico rmdir /Q/S empaquetadorbasico
if exist catalogacion rmdir /Q/S catalogacion
if exist catalogadorWeb rmdir /Q/S catalogadorWeb
if exist PortalEmpaquetador rmdir /Q/S PortalEmpaquetador
if exist localizador rmdir /Q/S localizador

mkdir %TARGET%
mkdir %TARGET%\dependencias
rem Genero fichero de version
echo %VERSION% > VERSION.txt
rem Descargo Apache al target. Creo las carpetas para que se generen las carpetas vacias de apache (uploads, uploads/taller, ...)
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/HerramientaOfflineBuilder/apache-tomcat-5.5.26 %TARGET%\apache-tomcat-5.5.26

rem Iconos y scripts
call copy /Y agrega64x64.png %TARGET%\agrega64x64.png
call copy /Y agrega.ico %TARGET%\agrega.ico
call copy /Y setup.* %TARGET%
call copy /Y LICENSE.txt %TARGET%\LICENSE.txt
call copy /Y README.txt %TARGET%\README.txt
rem Fichero de ayuda
call copy /Y Manual_de_Usuario.pdf %APACHE_BIN%\help\Manual_de_Usuario.pdf


rem DESCARGO XMLs a apache/bin/uploads/xmls
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/fuentestaxonomicas/core/src/resources/xmls %APACHE_BIN%\uploads\xmls
rem DESCARGO esquemas XSD a uploads/schemas: SCORM
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/11_Configuracion/uploads/schemas %APACHE_BIN%\uploads\schemas
rem DESCARGO esquemas XSD a uploads/schemas: LOMES
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/parseadorXML/src/esquema/LOM-ES %APACHE_BIN%\uploads\schemas
rem DESCARGO properties a apache/common/classes
call svn export --force --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/11_Configuracion/jboss/conf "%TARGET%/apache-tomcat-5.5.26/common/classes"
rem DESCARGO plantillas xslt y schemas a uploads
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/11_Configuracion/uploads/schemasImscp %APACHE_BIN%\uploads\schemasImscp
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/11_Configuracion/uploads/schemasScorm12 %APACHE_BIN%\uploads\schemasScorm12
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/11_Configuracion/uploads/xslt %APACHE_BIN%\uploads\xslt
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/11_Configuracion/uploads/logos %APACHE_BIN%\uploads\logos
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/entregar/core/src/test-resources/uploads/html %APACHE_BIN%\uploads\html


rem ELIMINO archivos innecesarios
del %TARGET%\apache-tomcat-5.5.26\common\classes\log4j.xml
del %TARGET%\apache-tomcat-5.5.26\common\classes\springldap.xml


call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/soporte .\soporte
cd soporte
call maven clean jar:install -Dmaven.test.skip -Dagrega.offline
cd ..


call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/parseadorXML .\parseadorXML
cd parseadorXML
call maven clean jar:install -Dmaven.test.skip -Dagrega.offline
cd ..


call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/static .\static
call robocopy /E .\static %DEPLOY_DIR%\static
:fuentestaxonomicas
rem Descargo y construyo el proyecto fuentestaxonomicas


call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/fuentestaxonomicas .\fuentestaxonomicas
cd fuentestaxonomicas
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\fuentestaxonomicas\web\target\fuentestaxonomicas-1.war %DEPLOY_DIR%\fuentestaxonomicas-1
call copy /Y D:\localRepo\fuentestaxonomicas\dependencies\*.jar %TARGET%\dependencias

rem Descargo y construyo el proyecto validador
:validador

rmdir /Q/S D:\localRepo\validador\dependencies
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/validador .\validador
cd validador
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\validador\web\target\validador-1.war %DEPLOY_DIR%\validador-1
call copy /Y D:\localRepo\validador\dependencies\*.jar %TARGET%\dependencias

rem Descargo y construyo el proyecto modificador
:modificador

rmdir /Q/S D:\localRepo\modificador\dependencies
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/modificador .\modificador
cd modificador
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false 
cd ..
call robocopy /E .\modificador\web\target\modificador-1.war %DEPLOY_DIR%\modificador-1
call copy /Y D:\localRepo\modificador\dependencies\*.jar %TARGET%\dependencias

rem Descargo y construyo el proyecto ModificadorWeb
:ModificadorWeb

rmdir /Q/S D:\localRepo\ModificadorWeb\dependencies
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/ModificadorWeb .\ModificadorWeb
cd ModificadorWeb
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false %PLANTILLAS_LABEL% 
cd ..
call robocopy /E .\ModificadorWeb\web\target\ModificadorWeb-1.war %DEPLOY_DIR%\ModificadorWeb
call copy /Y D:\localRepo\ModificadorWeb\dependencies\*.jar %TARGET%\dependencias


rem Descargo y construyo el proyecto Localizador
:localizador

rmdir /Q/S D:\localRepo\localizador\dependencies
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/localizador .\localizador
cd localizador
call maven clean install %DB_OPTIONS_LOCALIZADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\localizador\web\target\localizador-1.war %DEPLOY_DIR%\localizador-1
call copy /Y D:\localRepo\localizador\dependencies\*.jar %TARGET%\dependencias



rem Descargo y construyo el proyecto Visualizador
:visualizador

rmdir /Q/S D:\localRepo\visualizador\dependencies
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/visualizador .\visualizador
cd visualizador
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\visualizador\web\target\visualizador-1.war %DEPLOY_DIR%\visualizador-1
call copy /Y D:\localRepo\visualizador\dependencies\*.jar %TARGET%\dependencias

rem Descargo y construyo el proyecto Entregar
:entregar

rmdir /Q/S D:\localRepo\entregar\dependencies
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/entregar .\entregar
cd entregar
call maven clean install %DB_OPTIONS_ENTREGAR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\entregar\web\target\entregar-1.war %DEPLOY_DIR%\entregar-1
call copy /Y D:\localRepo\entregar\dependencies\*.jar %TARGET%\dependencias

rem Descargo y construyo el proyecto catalogacion
:catalogacion

rmdir /Q/S D:\localRepo\catalogacion\dependencies
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/catalogacion .\catalogacion
cd catalogacion
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\catalogacion\web\target\catalogacion-1.war %DEPLOY_DIR%\catalogacion-1
call copy /Y D:\localRepo\catalogacion\dependencies\*.jar %TARGET%\dependencias

rem Descargo y construyo el proyecto empaquetadorbasico
:empaquetadorbasico

rmdir /Q/S D:\localRepo\empaquetadorbasico\dependencies
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/empaquetadorbasico .\empaquetadorbasico
cd empaquetadorbasico
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\empaquetadorbasico\web\target\empaquetadorbasico-F1.war %DEPLOY_DIR%\empaquetadorbasico-F1
call copy /Y D:\localRepo\empaquetadorbasico\dependencies\*.jar %TARGET%\dependencias

rem Descargo y construyo el proyecto catalogadorWeb
:catalogadorWeb

rmdir /Q/S D:\localRepo\catalogadorWeb\dependencies
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/catalogadorWeb .\catalogadorWeb
cd catalogadorWeb
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false
cd ..
call robocopy /E .\catalogadorWeb\web\target\catalogadorWeb-1.war %DEPLOY_DIR%\catalogadorWeb
call copy /Y D:\localRepo\catalogadorWeb\dependencies\*.jar %TARGET%\dependencias

rem Descargo y construyo el proyecto PortalEmpaquetador
:PortalEmpaquetador

rmdir /Q/S D:\localRepo\PortalEmpaquetador\dependencies
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/PortalEmpaquetador .\PortalEmpaquetador
cd PortalEmpaquetador
call maven clean install %DB_OPTIONS_MODIFICADOR% -Dmaven.test.skip -Dagrega.offline -Dibuilder.security.useAcegiSecurity=false %PLANTILLAS_LABEL% 
cd ..
call robocopy /E .\PortalEmpaquetador\web\target\PortalEmpaquetador-F1.war %DEPLOY_DIR%\PortalEmpaquetador
call copy /Y D:\localRepo\PortalEmpaquetador\dependencies\*.jar %TARGET%\dependencias

:herramientaoffline

rmdir /Q/S D:\localRepo\herramientaOffline\dependencies
call svn export --force --username %SVN_USER% --password %SVN_PASS% %SVN_URL%/%SVN_PATH%/04_Subsistemas/herramientaoffline .\herramientaoffline
cd herramientaoffline
call maven clean install -Dmaven.test.skip -Dagrega.offline %PLANTILLAS_LABEL% 
cd ..
call robocopy /E .\herramientaoffline\web\target\herramientaoffline-1.war %DEPLOY_DIR%\herramientaoffline
call copy /Y D:\localRepo\herramientaoffline\dependencies\*.jar %TARGET%\dependencias


echo --------------------------------------------------------------------------
echo --------------------------------------------------------------------------
echo --                                                                      --
echo --  COMPROBAR AGREGA.PROPERTIES ANTES DE MONTAR VERSION                 --
echo --                                                                      --
echo --------------------------------------------------------------------------


rem Creacci�n del modelo
java -cp .;C:\localRepo\castor\jars\castor-1.1-codegen.jar;C:\localRepo\castor\jars\castor-1.1-xml.jar;C:\localRepo\xerces\jars\xerces-2.6.2.jar;C:\localRepo\xerces\jars\xercesImpl-2.9.0.jar;C:\localRepo\commons-logging\jars\commons-logging-1.0.4.jar org.exolab.castor.builder.SourceGeneratorMain -package es.pode.catalogacion.licencias.dominio.gruposLicencias -types j2 -verbose -i .\gruposLicencias.xsd -nomarshall
java -cp .;C:\localRepo\castor\jars\castor-1.1-codegen.jar;C:\localRepo\castor\jars\castor-1.1-xml.jar;C:\localRepo\xerces\jars\xerces-2.6.2.jar;C:\localRepo\xerces\jars\xercesImpl-2.9.0.jar;C:\localRepo\commons-logging\jars\commons-logging-1.0.4.jar org.exolab.castor.builder.SourceGeneratorMain -package es.pode.catalogacion.licencias.dominio.compatibilidadLicencias -types j2 -verbose -i .\compatibilidadLicencias.xsd -nomarshall
; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{B46A6190-9CDE-4C79-BEFA-330D28BB1489}
AppName=Herramientas-Agrega
AppVerName=Herramientas-Agrega-3.0.2
AppPublisher=Indra Sistemas
AppPublisherURL=http://www.redes.agrega.indra.es
AppSupportURL=http://www.redes.agrega.indra.es
AppUpdatesURL=http://www.redes.agrega.indra.es
DefaultDirName={sd}\Agrega
UsePreviousAppDir=yes
DefaultGroupName=Agrega
AllowNoIcons=yes
OutputDir=output
OutputBaseFilename=herramientas-agrega-installer-3.0.2
SetupIconFile=target\agrega\agrega.ico
Compression=lzma
SolidCompression=yes
LicenseFile=LICENSE.txt


[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl";  LicenseFile:"LICENSE_en.txt"
Name: "basque"; MessagesFile: "compiler:Languages\Basque.isl";  LicenseFile:"LICENSE_eu.txt"
Name: "catalan"; MessagesFile: "compiler:Languages\Catalan.isl"; LicenseFile:"LICENSE_ca.txt"
Name: "spanish"; MessagesFile: "compiler:Languages\Spanish.isl"; LicenseFile:"LICENSE.txt"
Name: "galician"; MessagesFile: "compiler:Languages\Galician.isl"; LicenseFile:"LICENSE_gl.txt"
Name: "valencian"; MessagesFile: "compiler:Languages\Valencian.isl"; LicenseFile:"LICENSE_va.txt"

[Files]
Source: "target\agrega\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "README.txt"; DestDir: "{app}"; Flags: isreadme
Source: "LICENSE*.txt"; DestDir: "{app}"
Source: "jre-6u20-windows-i586.exe"; DestDir: "{app}"

[Icons]
Name: "{group}\Herramientas-Agrega"; Filename: "http://localhost:8080/herramientaoffline"; IconFilename: "{app}\agrega.ico"
Name: "{group}\IniciarServidor"; Filename: "{app}\apache-tomcat-5.5.26\bin\startup.bat"; WorkingDir: "{app}\apache-tomcat-5.5.26\bin"
Name: "{group}\PararServidor"; Filename: "{app}\apache-tomcat-5.5.26\bin\shutdown.bat"; WorkingDir: "{app}\apache-tomcat-5.5.26\bin"
Name: "{group}\Manual de Usuario"; Filename: "{app}\apache-tomcat-5.5.26\bin\help\Manual_de_Usuario.pdf"
Name: "{group}\README"; Filename: "{app}\README.txt"
Name: "{group}\{cm:UninstallProgram,}"; Filename: "{uninstallexe}"

[Run]
Filename: "{app}\jre-6u20-windows-i586.exe"; WorkingDir: "{app}"; StatusMsg: "Instalando Java ..."
Filename: "{app}\setup.bat"; WorkingDir: "{app}"; StatusMsg: "Instalando dependencias ..."; Flags: runhidden
Filename: "{app}\del.bat"; WorkingDir: "{app}"; StatusMsg: "Borrando archivos de instalacion ..."; Flags: runhidden

[UninstallDelete]
Type: filesandordirs; Name: "{app}\apache-tomcat-5.5.28"







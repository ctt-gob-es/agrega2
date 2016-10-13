!include "MUI2.nsh"

  ;Nombre del instalador
  OutFile "agrega-tagger.exe"
  
  !define PRODUCTNAME "Agrega-Tagger" ;//i18n?
  !define UNISTPRODNAME "S{PRODUCTNAME}"
  !define PRODUCTVERSION "0.1"
  
  
  !define MUI_ICON ".\hotico.ico"
  !define MUI_FINISHPAGE_NOAUTOCLOSE
  !define MUI_UNICON "${NSISDIR}\Contrib\Graphics\Icons\modern-uninstall.ico"
  !define MUI_UNFINISHPAGE_NOAUTOCLOSE
  
  Name "${PRODUCTNAME}"
  
  ;Ruta de instalación por defecto
  InstallDir "$PROGRAMFILES\${PRODUCTNAME}"
  
  ;Recupera ruta de instalación previa
  InstallDirRegKey HKCU "Software\S{PRODUCTNAME}" ""
  
  XPStyle on
  RequestExecutionLevel admin
  ShowInstDetails show
  
  Var Dialog
  Var Text
  Var Url
  Var StartMenuFolder

;--------------------------------
;Páginas del instalador

;1. Licencia
  !insertmacro MUI_PAGE_LICENSE ".\gpl.txt"
;2. Ruta
  !insertmacro MUI_PAGE_DIRECTORY
;3. Configuración
  Page custom nsDialogsPage nsDialogsPageLeave
  
;4. Creación de accesos directos
  !define MUI_STARTMENUPAGE_REGISTRY_ROOT "HKCU" 
  !define MUI_STARTMENUPAGE_REGISTRY_KEY "Software\S{PRODUCTNAME}" 
  !define MUI_STARTMENUPAGE_REGISTRY_VALUENAME "Start Menu Folder"
  
  !insertmacro MUI_PAGE_STARTMENU Application $StartMenuFolder
  
;5. Copia de ficheros
  !insertmacro MUI_PAGE_INSTFILES
;6. Fin
  !insertmacro MUI_PAGE_FINISH
  
;Páginas de abandono
  !define MUI_ABORTWARNING
  !insertmacro MUI_UNPAGE_CONFIRM
  !insertmacro MUI_UNPAGE_INSTFILES
  !insertmacro MUI_UNPAGE_FINISH
  
;--------------------------------

;Idiomas soportados por instalador, el primero es el por defecto
  !insertmacro MUI_LANGUAGE "Spanish"
  !insertmacro MUI_LANGUAGE "English"
  !insertmacro MUI_LANGUAGE "Catalan"
  !insertmacro MUI_LANGUAGE "Basque"
  !insertmacro MUI_LANGUAGE "Galician"
  ;No hay soporte para Valenciano ¿nos lo tendríamos que hacer?
 
  
;--------------------------------

;Cadenas i18n

LangString PAGE_TITLE ${LANG_SPANISH} "Configuración"
LangString PAGE_TITLE ${LANG_CATALAN} "Configuración"
LangString PAGE_TITLE ${LANG_BASQUE} "Configuración"
LangString PAGE_TITLE ${LANG_GALICIAN} "Configuración"
LangString PAGE_TITLE ${LANG_ENGLISH} "Configuration"

LangString PAGE_SUBTITLE ${LANG_SPANISH} "Introduzca URL del nodo de Agrega al que pertenece"
LangString PAGE_SUBTITLE ${LANG_CATALAN} "Introduzca URL del nodo de Agrega al que pertenece"
LangString PAGE_SUBTITLE ${LANG_BASQUE} "Introduzca URL del nodo de Agrega al que pertenece"
LangString PAGE_SUBTITLE ${LANG_GALICIAN} "Introduzca URL del nodo de Agrega al que pertenece"
LangString PAGE_SUBTITLE ${LANG_ENGLISH} "Insert Agrega node URL which you belong to"

LangString BUTTON_TEXT ${LANG_SPANISH} "Agregar"
LangString BUTTON_TEXT ${LANG_CATALAN} "Agregar"
LangString BUTTON_TEXT ${LANG_BASQUE} "Agregar"
LangString BUTTON_TEXT ${LANG_GALICIAN} "Agregar"
LangString BUTTON_TEXT ${LANG_ENGLISH} "Agregate"

LangString MENU_TEXT ${LANG_SPANISH} "Configuración de Agrega-Tagger"
LangString MENU_TEXT ${LANG_CATALAN} "Configuración de Agrega-Tagger"
LangString MENU_TEXT ${LANG_BASQUE} "Configuración de Agrega-Tagger"
LangString MENU_TEXT ${LANG_GALICIAN} "Configuración de Agrega-Tagger"
LangString MENU_TEXT ${LANG_ENGLISH} "Agrega-Tagger Configuration"

LangString PLUGIN ${LANG_SPANISH} "plugin.js"
LangString PLUGIN ${LANG_CATALAN} "plugin_ca.js"
LangString PLUGIN ${LANG_BASQUE} "plugin_eu.js"
LangString PLUGIN ${LANG_GALICIAN} "plugin_gl.js"
LangString PLUGIN ${LANG_ENGLISH} "plugin_en.js"

LangString CONF ${LANG_SPANISH} "conf.vbs"
LangString CONF ${LANG_CATALAN} "conf_ca.vbs"
LangString CONF ${LANG_BASQUE} "conf_eu.vbs"
LangString CONF ${LANG_GALICIAN} "conf_gl.vbs"
LangString CONF ${LANG_ENGLISH} "conf_en.vbs"

LangString UNINSTALL ${LANG_SPANISH} "Desinstalar"
LangString UNINSTALL ${LANG_CATALAN} "Desinstalar"
LangString UNINSTALL ${LANG_BASQUE} "Desinstalar"
LangString UNINSTALL ${LANG_GALICIAN} "Desinstalar"
LangString UNINSTALL ${LANG_ENGLISH} "Uninstall"


;--------------------------------
;Licencia, misma para todos
  
  LicenseData ".\gpl.txt"

;--------------------------------
; Función para replace, ¿existe mejor alternativa?
Function AdvReplaceInFile
Exch $0 ;file to replace in
Exch
Exch $1 ;number to replace after
Exch
Exch 2
Exch $2 ;replace and onwards
Exch 2
Exch 3
Exch $3 ;replace with
Exch 3
Exch 4
Exch $4 ;to replace
Exch 4
Push $5 ;minus count
Push $6 ;universal
Push $7 ;end string
Push $8 ;left string
Push $9 ;right string
Push $R0 ;file1
Push $R1 ;file2
Push $R2 ;read
Push $R3 ;universal
Push $R4 ;count (onwards)
Push $R5 ;count (after)
Push $R6 ;temp file name
 
  GetTempFileName $R6
  FileOpen $R1 $0 r ;file to search in
  FileOpen $R0 $R6 w ;temp file
   StrLen $R3 $4
   StrCpy $R4 -1
   StrCpy $R5 -1
 
loop_read:
 ClearErrors
 FileRead $R1 $R2 ;read line
 IfErrors exit
 
   StrCpy $5 0
   StrCpy $7 $R2
 
loop_filter:
   IntOp $5 $5 - 1
   StrCpy $6 $7 $R3 $5 ;search
   StrCmp $6 "" file_write2
   StrCmp $6 $4 0 loop_filter
 
StrCpy $8 $7 $5 ;left part
IntOp $6 $5 + $R3
IntCmp $6 0 is0 not0
is0:
StrCpy $9 ""
Goto done
not0:
StrCpy $9 $7 "" $6 ;right part
done:
StrCpy $7 $8$3$9 ;re-join
 
IntOp $R4 $R4 + 1
StrCmp $2 all file_write1
StrCmp $R4 $2 0 file_write2
IntOp $R4 $R4 - 1
 
IntOp $R5 $R5 + 1
StrCmp $1 all file_write1
StrCmp $R5 $1 0 file_write1
IntOp $R5 $R5 - 1
Goto file_write2
 
file_write1:
 FileWrite $R0 $7 ;write modified line
Goto loop_read
 
file_write2:
 FileWrite $R0 $R2 ;write unmodified line
Goto loop_read
 
exit:
  FileClose $R0
  FileClose $R1
 
   SetDetailsPrint none
  Delete $0
  Rename $R6 $0
  Delete $R6
   SetDetailsPrint both
 
Pop $R6
Pop $R5
Pop $R4
Pop $R3
Pop $R2
Pop $R1
Pop $R0
Pop $9
Pop $8
Pop $7
Pop $6
Pop $5
Pop $0
Pop $1
Pop $2
Pop $3
Pop $4
FunctionEnd

;Esta función se ejecuta siempre la primera
Function .onInit
	;Preguntamos el idioma
	!insertmacro MUI_LANGDLL_DISPLAY
	
FunctionEnd

;Y esta es lo mismo, pero para desinstalación
Function un.onInit
  !insertmacro MUI_UNGETLANGUAGE
FunctionEnd

;Página personalizada
Function nsDialogsPage
	!insertmacro MUI_HEADER_TEXT $(PAGE_TITLE) $(PAGE_SUBTITLE)
	nsDialogs::Create /NOUNLOAD 1018
	Pop $Dialog

	${If} $Dialog == error
		Abort
	${EndIf}

	${NSD_CreateText} 0 13u 100% 12u "http://localhost:8080"
	Pop $Text
  
	nsDialogs::Show
FunctionEnd

;Esta página personalizada es de momento necesaria para extraer el valor de la Url,
;pendiente de ver cómo eliminarla.
Function nsDialogsPageLeave
  ${NSD_GetText} $Text $Url

FunctionEnd

;Proceso de instalación en sí
Section "agrega-tagger.exe" SecCopyUI

  ;Ruta de instalación
  SetOutPath "$INSTDIR"
  ;Ficheros que se deben copiar
  File ".\hotico.ico"
  File ".\ico.ico"
  File ".\plugin.js"
  File ".\plugin_en.js"
  File ".\plugin_ca.js"
  File ".\plugin_eu.js"
  File ".\plugin_gl.js"
  File ".\conf.vbs"
  File ".\conf_en.vbs"
  File ".\conf_ca.vbs"
  File ".\conf_eu.vbs"
  File ".\conf_gl.vbs"

  
  ;Sustituimos valor por defecto por el configurado
  Push 'NADA' #text to be replaced
  Push $Url #replace with
  Push all #replace all occurrences
  Push all #replace all occurrences
  Push $INSTDIR\plugin.js #file to replace in
  Call AdvReplaceInFile
  
  ;Claves de registro para Internet Explorer
  ;Ruta de instalación
  WriteRegStr HKCU "Software\S{PRODUCTNAME}" "" $INSTDIR
  
  ;GUID para botón: 9863F267-69CB-4E4B-BF64-A566F2DA7CC5
  WriteRegStr HKLM "Software\Microsoft\Internet Explorer\Extensions\{9863F267-69CB-4E4B-BF64-A566F2DA7CC5}" "ButtonText" $(BUTTON_TEXT)
  WriteRegStr HKLM "Software\Microsoft\Internet Explorer\Extensions\{9863F267-69CB-4E4B-BF64-A566F2DA7CC5}" "HotIcon" "$INSTDIR\hotico.ico"
  WriteRegStr HKLM "Software\Microsoft\Internet Explorer\Extensions\{9863F267-69CB-4E4B-BF64-A566F2DA7CC5}" "Icon" "$INSTDIR\ico.ico"
  WriteRegStr HKLM "Software\Microsoft\Internet Explorer\Extensions\{9863F267-69CB-4E4B-BF64-A566F2DA7CC5}" "Default Visible" "Yes"
  WriteRegStr HKLM "Software\Microsoft\Internet Explorer\Extensions\{9863F267-69CB-4E4B-BF64-A566F2DA7CC5}" "CLSID" "{1FBA04EE-3024-11D2-8F1F-0000F87ABD16}"
  WriteRegStr HKLM "Software\Microsoft\Internet Explorer\Extensions\{9863F267-69CB-4E4B-BF64-A566F2DA7CC5}" "Script" "$INSTDIR\$(PLUGIN)"
  ;GUID para opción de menú: 2CF3E634-09D3-4C67-8FA0-04A4F87FE466
  ;WriteRegStr HKLM "Software\Microsoft\Internet Explorer\Extensions\{2CF3E634-09D3-4C67-8FA0-04A4F87FE466}" "CLSID" "{1FBA04EE-3024-11d2-8F1F-0000F87ABD16}"
  ;WriteRegStr HKLM "Software\Microsoft\Internet Explorer\Extensions\{2CF3E634-09D3-4C67-8FA0-04A4F87FE466}" "MenuText" $(MENU_TEXT)
  ;WriteRegStr HKLM "Software\Microsoft\Internet Explorer\Extensions\{2CF3E634-09D3-4C67-8FA0-04A4F87FE466}" "Script" "$INSTDIR\$(CONF)"
  ;WriteRegStr HKLM "Software\Microsoft\Internet Explorer\Extensions\{2CF3E634-09D3-4C67-8FA0-04A4F87FE466}" "Script" "$INSTDIR\conf.vbs"

  ;Claves para Desinstalación
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${UNISTPRODNAME}" "DisplayName" "${PRODUCTNAME}"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${UNISTPRODNAME}" "UninstallString" "$INSTDIR\uninstall-agrega-tagger.exe"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${UNISTPRODNAME}" "RegOwner" "Agrega"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${UNISTPRODNAME}" "URLInfoAbout" "agrega.indra.es"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${UNISTPRODNAME}" "DisplayVersion" "${PRODUCTVERSION}"
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${UNISTPRODNAME}" "NoRepair" "1"
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${UNISTPRODNAME}" "NoModify" "1"

  ;Crea fichero de desinstalación
  WriteUninstaller "$INSTDIR\uninstall-agrega-tagger.exe"
  
  ;Creación accesos directos
  !insertmacro MUI_STARTMENU_WRITE_BEGIN Application
    
  ;Create shortcuts
  CreateDirectory "$SMPROGRAMS\$StartMenuFolder"
  CreateShortCut "$SMPROGRAMS\$StartMenuFolder\$(MENU_TEXT).lnk" "$INSTDIR\$(CONF)"
  CreateShortCut "$SMPROGRAMS\$StartMenuFolder\$(UNINSTALL).lnk" "$INSTDIR\uninstall-agrega-tagger.exe"
  
  !insertmacro MUI_STARTMENU_WRITE_END

SectionEnd

;Desinstalador
Section "Uninstall"

  ;Borramos lo que copiamos
  Delete "$INSTDIR\plugin.js"
  Delete "$INSTDIR\plugin_ca.js"
  Delete "$INSTDIR\plugin_eu.js"
  Delete "$INSTDIR\plugin_gl.js"
  Delete "$INSTDIR\plugin_en.js"
  Delete "$INSTDIR\conf.vbs"
  Delete "$INSTDIR\conf_ca.vbs"
  Delete "$INSTDIR\conf_eu.vbs"
  Delete "$INSTDIR\conf_gl.vbs"
  Delete "$INSTDIR\conf_en.vbs"
  Delete "$INSTDIR\hotico.ico"
  Delete "$INSTDIR\ico.ico"
  Delete "$INSTDIR\uninstall-agrega-tagger.exe"
  ;Odio los desintaladores que dejan la carpeta vacía...
  RmDir "$INSTDIR"
  
  ;Borramos claves del registro
  
  DeleteRegKey HKLM "Software\Microsoft\Internet Explorer\Extensions\{9863F267-69CB-4E4B-BF64-A566F2DA7CC5}"
  ;DeleteRegKey HKLM "Software\Microsoft\Internet Explorer\Extensions\{2CF3E634-09D3-4C67-8FA0-04A4F87FE466}"
  DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${UNISTPRODNAME}"

  !insertmacro MUI_STARTMENU_GETFOLDER Application $StartMenuFolder
  Delete "$SMPROGRAMS\$StartMenuFolder\$(MENU_TEXT).lnk"
  Delete "$SMPROGRAMS\$StartMenuFolder\$(UNINSTALL).lnk"
  RMDir "$SMPROGRAMS\$StartMenuFolder"
  
  DeleteRegKey /ifempty HKCU "Software\S{PRODUCTNAME}"
  
SectionEnd
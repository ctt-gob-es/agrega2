Const line ="var valcgi ="
Const nombreFichero ="plugin.js"
Const ficheroTemp ="temp.tmp"
Const backFile ="plugin.js.bak"
Dim url
url=InputBox("Introduzca URL", "Configuración Agrega-Tagger")
Dim fso, file1, file2, stream
Set fso = CreateObject("Scripting.FileSystemObject")
Rem Misteriosamente, file1 es un tipo String
Set file1 = fso.GetFile(nombreFichero)
Rem y file2 es un tipo Object
Set file2 = fso.CreateTextFile(ficheroTemp, True)
Set stream = file1.OpenAsTextStream(1,-2)
Do While Not stream.AtEndOfStream
	Dim linea
	linea=stream.ReadLine
	Rem Si linea contiene "var valcgi =", la cambiamos
	pos = InStr(linea, line)
	If  pos > 0 Then
		linea="  " & line & "'" & Trim(url) & "';"
	End If
	file2.WriteLine(linea)
Loop
stream.Close
Rem Como no es Object, no se puede cerrar file1
Rem file1.Close
file2.Close

Set stream = Nothing

Rem Ahora borramos backup
If fso.FileExists(backFile) = True Then
	Rem Curiosidades de VB: para pasar parámetros a un Sub no se admiten paréntesis
	fso.DeleteFile backFile, True
End If
Rem Renombramos original a backup, eliminar para versión final
file1.Move(backFile)
Rem Renombramos temp a original
Rem file2.Move(nombreFichero)
Rem Como esto no funciona, usaremos Sub del FileSystemObject
fso.MoveFile ficheroTemp, nombreFichero

Set file1 = Nothing
Set file2 = Nothing

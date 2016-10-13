<SCRIPT LANGUAGE="JavaScript" defer>
  var parentwin = external.menuArguments;
  var doc = parentwin.document;
  var sel = doc.selection;
  var rng = sel.createRange();
  var str = new String(rng.text);
  var valcgi ='NADA';

  if(str.length == 0) {
    // if we didn't get a page assume the current one.
    str = doc.URL;
  }

  if(valcgi.length == 0) {
	  alert("No se ha definido ninguna URL. Por favor, indique una URL usando la herramienta de Configuración.");
  } else {
	  open(window.location.href=valcgi+'/gestorFlujo/ImportarURLCU/ImportarURLCU.do?url='+escape(str));
  }
</SCRIPT>
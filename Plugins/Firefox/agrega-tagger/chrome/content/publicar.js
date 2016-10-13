//Para poder acceder a los valores de las preferencias necesitamos un Manager
var prefManager = Components.classes["@mozilla.org/preferences-service;1"].getService(Components.interfaces.nsIPrefBranch);

/*
* Hacemos llamada a publicar indicando la URL del documento que nos llamó
*/
function publicar() {
	//Referencia al fichero de i18n que nos da el XUL que nos llama
	var stringsBundle = document.getElementById("agrega-tagger-bundle");
	//La cadena de i18n que nos interesa
	var errorUrl=stringsBundle.getString('agrega-tagger.errorURL');
	var nodoActual=prefManager.getCharPref("extensions.agrega-tagger.nodo");
	if(nodoActual.length == 0) {
		alert(errorUrl);
	} else {
		var currentBrowser=window.getBrowser();
		var currentDoc=getDoc(currentBrowser);
		currentBrowser.addTab(nodoActual+'/gestorFlujo/ImportarURLCU/ImportarURLCU.do?url='+escape(currentDoc));
	}
}

function getDoc( browser ) {
      var webNav = browser.webNavigation;
      if( webNav.currentURI ) {
         return webNav.currentURI.spec;
      }
   }

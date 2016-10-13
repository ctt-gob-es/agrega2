var vActual;

var urlContenido;

var offline;



var menu;

var contenido;





var	anchoMenuInicial=430;

var	anchoMenu= anchoMenuInicial;

var	anchoTotalMinimo=600;

	

function tipoDocumento(href){

		tipoDoc="";

		

		if(href==null || href=="")

			tipoDoc = "default";

		else{

			var i = href.lastIndexOf(".");

			var extension=href.substring(i,href.length);

			extension = extension.toLowerCase();

			if(	extension==".doc" || extension==".ppt" || extension==".pps" || extension==".xls" ||

				extension==".odt" || extension==".odp" || extension==".ods" ||

				extension==".sxw" || extension==".sxi" || extension==".sxc" ||

				extension==".pdf" || extension==".rtf" || extension==".txt" ||

				extension==".csv")

				tipoDoc="viewer";

			else 

				tipoDoc="default";

   		}

}



function cambiar(id){

	if (!vActual) {

		vActual = document.forms['f1'].elements['seleccion'];

		if(	document.getElementById(vActual.value)!=null)

			document.getElementById(vActual.value).className = 'escogido';

	}

	

	if( id!=vActual.value){

		if (vActual.value != 'seleccion'){

			var aux=vActual.value;

          	

          	if(document.getElementById(aux))

          		document.getElementById(aux).className = '';

      	} 

      	else{

         	document.getElementById('seleccion').className = '';

      	}

   	  	document.getElementById(id).className = 'escogido';

   	 	vActual.value=id;

   	}

}



function abrirCerrar(item,abierto,cerrado)

{

    var elemento = document.getElementById(item);



	if(elemento.className == cerrado){

		elemento.className=abierto;

	}

	else if(elemento.className == abierto){

		elemento.className = cerrado;

	}

}



function mostrarItem(href, id ,offline , key)

{

	

	cambiar(id);

	contenido = document.getElementById("capa_contenidos");

	if(href !='' && href!=null && id!='' && id!=null){

		

		if((href ==null) || (href=="") || (href.charAt(href.length-1)=='#')){

			if(contenido!=null)

				contenido.innerHTML="<iframe id='contenidoFRAME' name='contenidoFRAME' onload='redimensionarContenido();' scrolling='auto'  frameborder='0' marginheight='0' marginwidth='0' style='margin-left:0;min-width:520px;min-height:440px'></iframe><hr/>";

		}

		else{

		

			//obtenemos el tipo documento

			tipoDocumento(href);

			cadenaIframe="<iframe id='contenidoFRAME' name='contenidoFRAME' onload='redimensionarContenido();' ";

			if(tipoDoc =='viewer' && offline == 'false'){

				cadenaIframe = "<p>Si no puede ver el documento pulse <a href='"+ href +"' target='frameContenido'>aquí</a></p>" + cadenaIframe;

				cadenaIframe= cadenaIframe + " src='http://viewer.zoho.com/api/view.do?url="+ href +"&apikey=" + key + "&embed=true' ";

			}else{

				cadenaIframe= cadenaIframe + " src='"+ href +"'";

			}



			cadenaIframe= cadenaIframe + "scrolling='auto'  frameborder='0' marginheight='0' marginwidth='0' style='margin-left:0;min-width:520px;min-height:440px'>[Su agente de usuario no soporta marcos o está actualmente configurado para no mostrar marcos. Sin embargo, puede visitar <A href='"+ href +"'>Documento relacionado.</A>]</iframe><hr/>";

			if(contenido!=null)

					contenido.innerHTML=cadenaIframe ;



		}



		return false;

	}else{

//		if(id!='' && id!=null ){

//			if(menu.getElementById(id)!=null)//si es null estamos en secuencia y no tenemos ids  a seleccion se ha hecho en el tag

//				cambiar(id);

//		}

		if ((href ==null) || (href=="") || (href.charAt(href.length-1)=='#')){

			if(contenido!=null)

				contenido.innerHTML="<iframe id='contenidoFRAME' name='contenidoFRAME' onload='redimensionarContenido();' scrolling='auto'  frameborder='0' marginheight='0' marginwidth='0' style='margin-left:0;min-width:520px;min-height:440px'></iframe><hr/>";

		}

		return false;

	}

}







	function redimensionarContenido()

	{

		var iframeEl = document.getElementById? document.getElementById("panel_menu"): document.all? document.all["panel_menu"]: null;

		var iframeContenido=  document.getElementById? document.getElementById("contenidoFRAME"): document.all? document.all["contenidoFRAME"]: null; 



		var alto =getAlto();

		var ancho=getAncho();



		if(ancho<anchoTotalMinimo)

		{

			ancho = anchoTotalMinimo;

		}

		if(iframeContenido)

		{

			iframeContenido.style.height = alto - 140 +'px';

			iframeContenido.style.width = ancho - anchoMenu - 100 +'px';

		}

		if(iframeEl!=null)

		{

			iframeEl.style.height = alto - 140 +'px';

			iframeEl.style.width = anchoMenu +'px';

		}

		

		/*  var comentarios =  document.getElementById? document.getElementById("frame_comentarios"): document.all? document.all["frame_comentarios"]: null;

		comentarios.style.height = alto - 140 +'px';

		comentarios.style.width = ancho - 60 +'px'; */

	}

	

	function getAlto() 

	{

		  var myHeight = 0;

		  if( typeof( window.innerWidth ) == 'number' ) {

		    //Non-IE

		    myHeight = window.innerHeight;

		  } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {

		    //IE 6+ in 'standards compliant mode'

		    myHeight = document.documentElement.clientHeight;

		  } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {

		    //IE 4 compatible

		    myHeight = document.body.clientHeight;

		  }

		  //window.alert( 'Height = ' + myHeight );

		  return myHeight;

	}



	function getAncho() 

	{

		  var myWidth = 0;

		  if( typeof( window.innerWidth ) == 'number' ) {

		    //Non-IE

		    myWidth = window.innerWidth ;

		  } else if( document.documentElement && ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {

		    //IE 6+ in 'standards compliant mode'

		    myWidth = document.documentElement.clientWidth;

		  } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {

		    //IE 4 compatible

		    myWidth = document.body.clientWidth;

		  }

		 

		  return myWidth;

	}

	

	

	

	function cambiarMenu()

	{

		var menu = document.getElementById? document.getElementById("panel_menu"): document.all? document.all["panel_menu"]: null;

		var iframeContenido=  document.getElementById? document.getElementById("contenidoFRAME"): document.all? document.all["contenidoFRAME"]: null; 

		

		if(menu.style.display=='none')

		{

			anchoMenu=anchoMenuInicial;

			menu.style.display=''

		}else

		{

			anchoMenu=0;

			menu.style.display='none'

		}

		

		var ancho=getAncho();

		

		if(ancho<anchoTotalMinimo)

		{

			ancho = anchoTotalMinimo;

		}



		if(iframeContenido)

		{

			iframeContenido.style.width = ancho - anchoMenu - 100 +'px';

		}

		if(menu)

		{

			menu.style.width = anchoMenu +'px';

		}





		var sharethis =  document.getElementById? document.getElementById("stwrapper"): document.all? document.all["stwrapper"]: null;

		

		if(sharethis && sharethis.style.visibility=='visible' && menu.style.display=='none')

		{

			$('a.stbutton').click();

		}

	

	}

	

	function mostrarComentarios()


	{

		var contenidos =  document.getElementById? document.getElementById("panel_contenidos"): document.all? document.all["panel_contenidos"]: null;

		var comentarios =  document.getElementById? document.getElementById("panel_comentarios"): document.all? document.all["panel_comentarios"]: null;

		var frm_comentarios =  document.getElementById? document.getElementById("frame_comentarios"): document.all? document.all["frame_comentarios"]: null;

		

		if(contenidos.style.display=='none')

		{

			contenidos.style.display='';

			comentarios.style.display='none'

		}else

		{

			frm_comentarios.src=frm_comentarios.src;

			contenidos.style.display='none';

			comentarios.style.display=''

		}

		return true;

	}
	

	

	function clickBoton(contador,identificador, usuario)

	{

	 	document.location='/visualizador-1/Visualizar/VisualizarDatosNavSecuenciaBoton.do?identificador=' + identificador + '&usuario=' + usuario + '&contador=' + contador;

	}



	function clickItem(idNodo,identificador, usuario)

	{

	 	document.location='/visualizador-1/Visualizar/VisualizarDatosNavSecuenciaNodo.do?identificador=' + identificador + '&usuario=' + usuario + '&idNodo='+ idNodo;

	}



	function mostrarShareThis()

	{

		$('a.stbutton').click();

	}

	

	

    /*

     * SCORM API

     */

     function GenericAPIAdaptor(){

     	this.LMSInitialize = LMSInitializeMethod;

     	this.LMSGetValue = LMSGetValueMethod;

     	this.LMSSetValue = LMSSetValueMethod;

     	this.LMSCommit = LMSCommitMethod;

     	this.LMSFinish = LMSFinishMethod;

     	this.LMSGetLastError = LMSGetLastErrorMethod;

     	this.LMSGetErrorString = LMSGetErrorStringMethod;

     	this.LMSGetDiagnostic = LMSGetDiagnosticMethod;

     }

     

     function GenericAPIAdaptor_2004(){

     	this.Initialize = LMSInitializeMethod;

     	this.Terminate = LMSFinishMethod;	

     	this.GetValue = LMSGetValueMethod;

     	this.SetValue = LMSSetValueMethod;

     	this.Commit = LMSCommitMethod;	

     	this.GetLastError = LMSGetLastErrorMethod;

     	this.GetErrorString = LMSGetErrorStringMethod;

     	this.GetDiagnostic = LMSGetDiagnosticMethod;

     }

     

     /*

     * LMSInitialize.

     */

     function LMSInitializeMethod(parameter){return "true";}

     /*

     * LMSFinish.

     */

     function LMSFinishMethod(parameter){return "true";}

     /*

     * LMSCommit.

     */

     function LMSCommitMethod(parameter){return "true";}

     /*

     * LMSGetValue.

     */

     function LMSGetValueMethod(element){return "";}

     /*

     * LMSSetValue.

     */

     function LMSSetValueMethod(element, value){return "true";}

     /*

     * LMSGetLastErrorString

     */

     function LMSGetErrorStringMethod(errorCode){return "No error";}

     /*

     * LMSGetLastError

     */

     function LMSGetLastErrorMethod(){return "0";}

     /*

     * LMSGetDiagnostic

     */

     function LMSGetDiagnosticMethod(errorCode){return "No error. No errors were encountered. Successful API call.";}

     

     var API = new GenericAPIAdaptor;

     var API_1484_11 = new GenericAPIAdaptor_2004;

     
	

	


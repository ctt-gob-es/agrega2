<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<%@ taglib uri="/WEB-INF/tags/serverProperties.tld" prefix="server" %>


<tiles:insert definition="layout-menu-2-enriquecido">

    <tiles:put name="title" type="string">
        <bean:message key="title.Comun"/>
    </tiles:put>

	<tiles:put name="body-principal" type="string">
		<article id="buscador">
			<header>
				<h2 class="titulo"><bean:message key="encuentra.los.mejores.contenidos.educativos"/></h2>
			</header>
			<section>
 				<form>
					<!-- OJO, Esta linea de espacios blancos de abajo sustituye a un enlace acia la busqueda avanzada que en esta pagina no tendria sentido -->
					<!-- Los espacios en blanco sirven para que la caja de busqueda no se descentre en IE -->
					<span class="enlace_f">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<!--  -->
					<fieldset id="caja_buscador">
						<div> 	
							<input type="button" onSubmit="abreNuevoTab()" onClick="abreNuevoTab()" alt="<bean:message key="buscador.boton.buscar.alt"/>" class="boton_submit" value="<bean:message key="buscador.buscador.boton"/>" name="buscar" />
							<!-- 
							<input type="button" onSubmit="document.location = dameUrlBusqueda()" onClick="document.location = dameUrlBusqueda()" alt="<bean:message key="buscador.boton.buscar.alt"/>" class="boton_submit" value="<bean:message key="buscador.buscador.boton"/>" name="buscar" />
							-->		
							<label for="busc_contenidos"><bean:message key="buscador.textinput"/></label>
							<!-- 
							<input type="text" name="buscador" class="buscador" id="busc_contenidos" onkeypress="searchKeyPress(event)" onkeydown="searchKeyPress(event)" onkeyup="searchKeyPress(event)" onfocus="clearText(this)" value="<bean:message key="buscador.textinput"/>">
							-->
							<input type="text" name="buscador" class="buscador" id="busc_contenidos" onkeypress="searchKeyPress(event)" onkeydown="searchKeyPress(event)" onkeyup="searchKeyPress(event)" value="">
							<label for="idioma">Idioma :</label>	
							<span class="caja_de_boton">
								<select id="idioma" name="idioma" class="select" title="Seleccione Buscador">
									<option value="Google" selected="selected">Google</option>
									<option value="Bing" >Bing</option>
									<option value="Yahoo" >Yahoo</option>    		
								</select>
							</span>
						</div>
					</fieldset>
					<!--  -->	
					<fieldset class="radios">
						<input type="radio" checked="checked" name="agrega_r" id="todo_agrega" />
						<label for="todo_agrega"><bean:message	key="buscador.radio.buscar.en.red" /></label>
						<input type="radio" name="agrega_r" id="nodo_cca_aa" />
						<label for="nodo_cca_aa"><server:serverProperties property="ccaa"/></label>
					</fieldset>
				<!--  -->					
 				</form>
			</section>
		</article>

	<!-- Aqui van javascripts propios  -->
	<script language="javascript">
		
		var todos = "${form.codigoTodos}";
		var todosYahoo = "${form.codigoTodosYahoo}";
		var ccaa ="${form.codigoNodo}";
			
		
		/************************************
		*
		*  UTF-8 data encode / decode
		*  http://www.webtoolkit.info/
		*
		*************************************/
		 
		var Utf8 = {
		 
			// public method for url encoding
			encode : function (string) {
				string = string.replace(/\r\n/g,"\n");
				var utftext = "";
		 
				for (var n = 0; n < string.length; n++) {
		 
					var c = string.charCodeAt(n);
		 
					if (c < 128) {
						utftext += String.fromCharCode(c);
					}
					else if((c > 127) && (c < 2048)) {
						utftext += String.fromCharCode((c >> 6) | 192);
						utftext += String.fromCharCode((c & 63) | 128);
					}
					else {
						utftext += String.fromCharCode((c >> 12) | 224);
						utftext += String.fromCharCode(((c >> 6) & 63) | 128);
						utftext += String.fromCharCode((c & 63) | 128);
					}
				}
				return utftext;
			},
		 
			// public method for url decoding
			decode : function (utftext) {
				var string = "";
				var i = 0;
				var c = c1 = c2 = 0;
		 
				while ( i < utftext.length ) {
		 
					c = utftext.charCodeAt(i);
		 
					if (c < 128) {
						string += String.fromCharCode(c);
						i++;
					}
					else if((c > 191) && (c < 224)) {
						c2 = utftext.charCodeAt(i+1);
						string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
						i += 2;
					}
					else {
						c2 = utftext.charCodeAt(i+1);
						c3 = utftext.charCodeAt(i+2);
						string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
						i += 3;
					}
				}
				return string;
			}
		}
		/************************************
		*************************************/
			
	
		function damePalabrasABuscar(){
			return document.getElementById("busc_contenidos").value;
		}
	
		function dameDondeBuscar(){
			var colRadio = document.getElementsByName("agrega_r");
			if (colRadio[0].checked){
				if(buscadorExt=="Yahoo")
					return todosYahoo;
				if(buscadorExt=="Bing")
					return "("+todos+")";
				else
					return todos;
			}else{
				if(buscadorExt=="Yahoo")
					return "&vs="+ccaa;
				return " site:"+ccaa;
			}
		}		
			
		function dameRaizGoogle(){
			buscadorExt=document.getElementById("idioma").value;
			if(buscadorExt=="Google")
				return "http://www.google.es/search?q=";
			else if(buscadorExt=="Bing")
				return "http://www.bing.com/search?q=";
			else return "http://es.search.yahoo.com/search?p=";
		}		
		
		function aleatorio(inferior,superior){
		    numPosibilidades = superior - inferior
		    aleat = Math.random() * numPosibilidades
		    aleat = Math.round(aleat)
		    return parseInt(inferior) + aleat
		}
		
		function dameUrlBusqueda(){
			//return "/static/js/busquedaExterna.jsp?searcher=" + dameRaizGoogle() + Utf8.encode(damePalabrasABuscar()) + dameDondeBuscar();
			return dameRaizGoogle() + Utf8.encode(damePalabrasABuscar()) + dameDondeBuscar();
		}
		
		function abreNuevoTab(){
			numTab = aleatorio(0, 10000);
			url = dameUrlBusqueda();	
			window.open(url, numTab);
		}
				
		//Code to allow launch the form pressing enter 
		//document.onkeypress = searchKeyPress;
		function searchKeyPress(e) {
			if (null == e)
				e = window.event ;
			if (e.keyCode == 13) {
				//submitForm() ;
				//document.location = dameUrlBusqueda();
				abreNuevoTab();
			}
			
			//The same code with JQuery
			/*$("#id_of_textbox").keyup(function(event){
				if(event.keyCode == 13){
					$("#id_of_button").click();
				}
			});*/
		}
				
				
				
				
		function crearFrame2(src) {
		//http://jquery-howto.blogspot.com/2010/02/dynamically-create-iframe-with-jquery.html
			$('<iframe />');
			$('<iframe />').attr('src', src); 
			$('<iframe id="myFrame" name="myFrame">').appendTo('body');
		}
		
		function returnIframeInDoc(){
	        //      For IE5.5 and IE6                        For NS6
	        var ifdoc = ifr.contentWindow || ifr.contentDocument;
	        if(ifdoc){
	            if (ifdoc.document) {
	                return ifdoc.document;
	            }else{
	            	return ifdoc;
	            }
	        }else{
	             return ifr.document;
	        }        
	   }
	</script>
	<!-- Aqui van javascripts propios  -->

    </tiles:put>

</tiles:insert>
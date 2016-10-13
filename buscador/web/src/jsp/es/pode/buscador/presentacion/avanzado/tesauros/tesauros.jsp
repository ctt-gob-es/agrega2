<%@ include file="/taglib-imports.jspf" %>

<tiles:insert definition="layout-menu-1-enriquecido">

<tiles:put name="title" type="string">
	<bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="body-principal" type="string">
<%@ include file="/taglib-imports.jspf" %>
      


<!-- PRINCIPAL   -->
<section id="principal">
	<!--  -->
	<article id="buscador_tipo">
     	<header>
       		<h2 class="titulo"><bean:message key="encuentra.los.mejores.contenidos.educativos"/></h2>
     	</header>
    	<section id="buscador_avanzado">
    	
		<!--  INICIO CAJA DE FORMULARIO   -->
		<form method="post" action="<html:rewrite action="/TesaurosCU/TesaurosBuscarTerminos?idTesauro=${form.idTesauro}&idioma=${form.idioma}&tipoLayoutBuscador=${form.tipoLayoutBuscador}&nomTesauros=${taxonesvoact.nmbre}&esRuta=${form.esRuta}&tituloTesauro=${form.tituloTesauro}&tesauro=${form.tesauro}"/>">

			 <!--  -->	   	
			<div class="tipofieldset clearfix" id="primer_fs"  >
				<h3>${form.tituloTesauro}</h3>
					  
				<fieldset class="clearfix">
					<input type="submit" class="boton" id="de_tesauro" name="buscar" value="<bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.buscar"/>"/>
					<label for="textoBusqueda" style="width:70px !important" ><bean:message key="listar.odecu.mostrar.resultados.consulta.cabecera.buscar"/>:</label>
					<input class="de_texto" name="textoBusqueda" onfocus="limpiarTexto(this)" value="${form.textoBusqueda}"  onblur="this.style.backgroundColor='#e1e1e1'" id="buscadorContenido02" type="text" title="<bean:message key="navegar.tesauro.introduzcaTextoBusqueda"/>" />
				</fieldset>
				
			</div>
				    
			<p><bean:message key="tesauro.explicacion"/>:</p>
	
		</form>
		<!--  FIN CAJA DE FORMULARIO   -->

		<form method="post" name="CargarTesaurosForm" action="<html:rewrite action="/TesaurosCU/TesaurosAsociarTesauro.do?nomTesauros=${form.nomTesauros}&idioma=${form.idioma}&idTesauro=${form.idTesauro}&tipoLayoutBuscador=${form.tipoLayoutBuscador}&tituloTesauro=${form.tituloTesauro}"/>">

			<!-- TABLA DE TESAUROS   -->
			<div class="tipofieldset clearfix"  >
			<div class="breadcrumb_curricular">
				
				<html:link action="/TesaurosCU/TesaurosCU.do?idTesauro=${form.idTesauro}&idioma=${form.idioma}&tipoLayoutBuscador=${form.tipoLayoutBuscador}&nomTesauros=${taxonesvoact.nombre}&esRuta=${form.esRuta}&tituloTesauro=${form.tituloTesauro}" styleClass="br_carpeta_abierta"><bean:message key="tesauro.Inicio"/><br/></html:link>
				<c:if test="${!empty form.rutaPadreVO}">
					 <c:set var="longitud" value="${ fn:length(form.rutaPadreVO)}"/>
					 <fmt:formatNumber var="nombre" value="${longitud -1}" type="number"/>
					 <fmt:formatNumber var="nombre2" value="${longitud -2}" type="number"/>
					 <c:if test="${longitud>1 }">
		 				<c:forEach items="${form.rutaPadreVO}" var="ruta" begin="0" end="${nombre2}">
				    		<em class="oculto">-</em>
				    		<html:link styleClass="br_carpeta_abierta" action="/TesaurosCU/TesaurosConsultaPadre?idTesauro=${ruta.id}&amp;tipoLayoutBuscador=${form.tipoLayoutBuscador}&amp;tituloTesauro=${form.tituloTesauro}&tesauro=${form.tesauro}">
		        				<c:out value="${ruta.valorTax}"/><br/>
		        			</html:link>
					    </c:forEach>
					 </c:if> 
					 <c:forEach items="${form.rutaPadreVO}" var="ruta3" begin="${nombre}" end="${nombre}">
						 <em class="oculto">-</em>
							 <span class="br_carpeta_abierta">
						    	 <c:out value="${ruta3.valorTax}"/><br/>
						     </span>
					 </c:forEach>   
		    	 </c:if>
	
			</div>		
			
			<!--  TABLA  -->
			<div class="tabla clearfix" id="sinmargen">
				<div>
								
					<bean:define id="summary"><bean:message key="tesauro.tablaTaxonesSummary"/></bean:define>
					<display:table name="${form.taxonesVO}" id="taxonesvoact" 
					style="width:100%" class="generica tabla_ruta_curricular tabla_ruta_curricular_especial"
					summary="${summary}"  requestURI="" sort="list" pagesize="20" partialList="False">
					
						<display:caption><strong> <bean:message key="tesauro.tablaTaxonesvo"/></strong></display:caption>
		        		<display:setProperty name="css.tr.odd" value="tr_gris"/>
		        		<display:setProperty name="css.tr.even" value="tr_blanco"/>
		        		<display:setProperty name="basic.show.header" value="false"/>
		        		<display:setProperty name="paging.banner.placement" value="bottom" />
	        			<!-- definimos las columnas -->
	        		
				        <c:choose>
		        			<c:when test="${taxonesvoact.esHoja}">
		        				<display:column  class="carpeta_cerrada" autolink="true">
		            				<c:out value="${taxonesvoact.valorTax}"/>
		            			</display:column>	
			       			</c:when>
		        			<c:otherwise>
		        				<display:column  class="new" autolink="true">
		        					<html:link styleClass="carpeta_cerrada" action="/TesaurosCU/TesaurosConsultaNodo?idTesauro=${taxonesvoact.id}&nomTesauros=${taxonesvoact.valorTax}&idioma=${form.idioma}&tipoLayoutBuscador=${form.tipoLayoutBuscador}&esRuta=${form.esRuta}&tituloTesauro=${form.tituloTesauro}&tesauro=${form.tesauro}">
		        						${taxonesvoact.valorTax}
		        					</html:link>        		 
		        				</display:column>	
		        			</c:otherwise>
		    			</c:choose>		        
					</display:table>
				</div>
			</div>
			
			<c:if test="${!empty form.rutaPadreVO}">
				<input class="boton flotante_derecha marginado" type="submit" name="accionSubmit" value="<bean:message key="tesauro.Asociar"/>" />
		 	</c:if> 
			
	 	</form>
		<form method="post" action="<html:rewrite action="/TesaurosCU/TesaurosCancelar.do?tipoLayoutBuscador=${form.tipoLayoutBuscador}"/>">
			<fieldset style="background:#fff !Important" class="botonera_de_dos botonera_de_dos_especial clearfix">
					  <input class="boton boton_flot"  type="submit"  value="<bean:message key="tesauro.Cancelar"/>"/>
			</fieldset>
		</form>
		
	</article>
</section>

<!-- Fin plantilla contenido  -->
<!-- Fin plantilla contenido  -->

</tiles:put>

</tiles:insert>


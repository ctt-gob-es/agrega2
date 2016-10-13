
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>
<html:xhtml/>

<tiles:insert definition="layout-administrador">

<tiles:put name="title" type="string">
      <bean:message key="title.Comun"/>
</tiles:put>

<tiles:put name="codigo-head" type="string">
<script type="text/javascript" src="<rewrite:rewrite url='static/js/jquery.autocomplete.js'/>"></script>
</tiles:put>

<tiles:put name="codigo-head" type="string">
 <script type="text/javascript">
      //<![CDATA[
      jQuery(document).ready(function() {
           jQuery("#buscadorContenidoSolo").autocomplete("<html:rewrite action="/SugerirListadoCU/SugerirListadoCU"/>", {
                delay:100,
		minChars:1,
		matchSubset:1,
		onItemSelect:selectItem,
		onFindValue:findValue,
		autoFill:true,
		maxItemsToShow:10
            });
      });
      //]]>
      </script>
</tiles:put>

<tiles:put name="body" type="string">

<%@ include file="/taglib-imports.jspf" %>
<%@ include file="/es/pode/administracion/presentacion/gestionCargas/listarCargasEjecutadas/listar-cargas-vars.jspf" %>


<!--		Inicio plantilla contenido		-->

<div class="plantilla_contenido">


<!-- ## Para que los mensajes de error vayan bien en IE6 ## -->
<jsp:include page="/layout/messages.jsp" flush="true" />

<!--		Inicio  PESTANIAS de FICHA		 -->
	<div id="ficha_pestanias">
		<div><h2><bean:message key="cabecera.cargas.contenidos"/></h2></div>
		<ul>
			<li id="pest_activa"><a href="<html:rewrite action="/ListarCargasEjecutadas/ListarCargasEjecutadas.do"/>" id="seleccionada"><bean:message key="cargas.pestania.listado"/></a></li>
			<li><a href="<html:rewrite action="/ListarInformesCarga/ListarInformesCarga.do"/>"><bean:message key="informes.pestania.listado" /></a></li>
			
		</ul>
	</div>
	<!--		Fin  PESTANIAS de FICHA		-->
	<!--		Inicio CONTENIDO PESTANIAS		-->

	<div class="interno_ficha">
	<div class="plantilla_contenido_pestanias">
	<form method="post" action="<html:rewrite action="/ListarCargasEjecutadas/ListarCargasBuscarPorNombre"/>"> 
				<!-- Inicio caja buscador -->
<!-- Inicio caja buscador -->
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris"  >
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03">
			<!--  INICIO CAJA DE FORMULARIO   -->
			
			
			
			<div id="formulario_02" >
			<h3 class="h3_generico_small"><bean:message key="buscarCargas.buscar.texto" /></h3>

  				<div class="fila_de_tabla">
					
					<!--  *********************       Desplegable de Tipo de Tarea     *******************  -->
								<div class="contenedor_derecha_00 " style="width:100%">
									<div class="text">
											<label class="oculto" for="buscadorContenidoSolo"></label>
											<input name="textoBusqueda" class="caja_buscador" value="${form.textoBusqueda}"  id="buscadorContenidoSolo" type="text" title="<bean:message key="buscador.buscador"/>"  />
											
											
											<br class="oculto" />
											<label class="oculto" for="tipoBusqueda"></label>
											<select  name="tipoBusqueda" title="<bean:message key="tipoBusqueda.seleccioneTipoBusqueda"/>" style="height:18px" id="tipoBusqueda" class="input_med">
													<option value="NombreLote" class="color"><bean:message key="tipoBusqueda.TipoLote"/></option>	
													<option value="NombreZip"><bean:message key="tipoBusqueda.TipoZip" /></option>		
											</select>
											<br  class="oculto" />
											<div class="autocomplete" id="list" style="display:none"></div>
											<input class="buscar" style="top:0;margin-left:10px" id="bus_solo"   type="submit"  value="<bean:message key="buscador.buscador.boton"/>" alt="Boton de buscar"/>
											<br class="oculto" />
									</div>
								</div>
								<div class="linea_separadora"></div>
								<br />
							</div>
				<!-- -->
				</div>
			
				<!--  FIN CAJA DE FORMULARIO   -->
			</div>
		</div>

	</div>
</div>
</form>

<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
<!-- Fin caja buscador -->
<!-- Fin caja buscador -->
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" id="conmargen" >
	<div class="globo_gris_01">
		<div class="globo_gris_02">

			<div class="globo_gris_03" id="tabla_in">
			<h2><bean:message key="cargas.pestania.listado"/></h2>
			<form id="listarCargasEjecutadasListarCargasEliminarForm" action='<html:rewrite action="/ListarCargasEjecutadas/ListarCargasEliminar"/>' method="post" >
			<div class="caja_tabla" id="ficha_dentro_gris"  >

		<!--		Inicio de la caja_tabla		-->
		
		<bean:define id="atributo" value="id" scope="page"/>
		<display:table name="${form.cargasEjecutadas}" uid="fila" requestURI=""
		        export="false" pagesize="8" sort="list" style="border:1;width:100%;" class="administracion_tareas"
				cellpadding="0" cellspacing="0" summary="${summary}"
				decorator="es.pode.administracion.presentacion.MarcarModificado">
				
				<display:setProperty name="css.tr.odd" value="tr_gris"/>
				<display:setProperty name="css.tr.even" value="tr_blanco"/>
				<display:setProperty name="basic.show.header" value="true"/>
				
				
				<!--		Columna de checkbox		-->
				
				<display:column media="html">
		            <input type="checkbox" name="idCargaRowSelectionAsArray" value="${fila.id}" title='<bean:message key="nodos.seleccione"/>'/>
		        </display:column>
				
				
				<!--		Columna de Nombre de la tarea	-->
				
				<bean:define id="cargaValue"><bean:message key="cargas.cargasEjecutadas"/></bean:define>
				<display:column sortable="true" sortProperty="descripcion" style="valign:top;" class="tar" title="${cargaValue}" >
				<c:if test="${fila.despublicado==true}" >
				<html:link action="/ListarCargasEjecutadas/ListarCargasRecuperar?idCarga=${fila.id}">
				${fila.descripcion}
				</html:link>
				</c:if>
				<c:if test="${fila.despublicado==false}" >
				${fila.descripcion}
				</c:if>
			    </display:column>
				
				<!--		Columna de Nombre del lote		-->
				<bean:define id="loteValue"><bean:message key="lote.cargasEjecutadas"/></bean:define>
				<display:column sortable="true" sortProperty="nombreLote" style="valign:top;" class="tar6" title="${loteValue}" >
					${fila.nombreLote}
				</display:column>
				<!--		Columna de Descripcion		-->
				<bean:define id="descripcionValue"><bean:message key="descripcion.cargasEjecutadas"/></bean:define>
				<display:column sortable="true" sortProperty="descripcionTarea" style="valign:top;" class="tar" title="${descripcionValue}" >
					${fila.descripcionTarea}
				</display:column>

				<!--		Columna de Fecha		-->
				
				<bean:define id="fechaValue"><bean:message key="tareas.cabeceraFecha"/></bean:define>
				<display:column sortable="true" sortProperty="fechaFin" title="${fechaValue}"  style="valign:top;" class="tar6">
					${fila.fechaFin}
				</display:column>
				
				<!--		Columna de Despublicar		-->
				
					<display:column style="valign:top;">
						<c:if test="${fila.despublicado==true}" >
							<input type="hidden" name="id" value="${fila.id}"/>
							<input type="hidden" name="nombreTarea" value="${fila.descripcion}"/>
							<input type="hidden" name="entrada" value="Lote"/>
							<html:link action="/DespublicarCargasEjecutadas/DespublicarCargasEjecutadas.do?id=${fila.id}&nombreTarea=${fila.descripcion}&entrada=Lote">
								<bean:message key="carga.Despublicar"/>
							</html:link>
						</c:if>
					</display:column>
					
				
				<!--		Columna de Ver Carpetas		-->
				<display:column style="valign:top;" class="tar6 underl">
				<c:if test="${fila.carpeta==true}" >
				<input type="hidden" name="id" value="${fila.id}"/>
				<input type="hidden" name="nombreTarea" value="${fila.descripcion}"/>
				<input type="hidden" name="nombreLote" value="${fila.nombreLote}"/>
				<input type="hidden" name="fechaFin" value="${fila.fechaFin}"/>
					<html:link action="/ListarCarpetasCargas/ListarCarpetasCargas?id=${fila.id}&nombreTarea=${fila.descripcion}&nombreLote=${fila.nombreLote}&fechaFin=${fila.fechaFin}">
						<bean:message key="ver.Carpetas"/>
					</html:link>
				</c:if>
				</display:column>
				
		</display:table>
		
		
		<!-- </div> Fin de la caja tabla (SE CIERRA EN displaytag.properties) -->
		<!-- en caso de no tener registros de usuarios se tiene que poner el div		-->
			
		<c:if test="${fn:length(form.cargasEjecutadas) < 1}" >

            </div>
                  
			<!--		Fin caja tabla		-->
				
		</c:if>
		
		
		<c:if test="${fn:length(form.cargasEjecutadas) > 0}" >
			<fieldset class="tipo ">
				<!--		Boton de Eliminar: Si no hay nodos no aparece el boton		-->
				<input class="boton_125_de_2"  type="submit"  value="<bean:message key='cargas.eliminar'/>" />
			</fieldset>

    	</c:if>
    	
	</form>
	</div>
</div>
</div>
</div>



	<!--		Fin del formulario		-->
	
	
</div>
</div>
</div>

<!--		Fin de la capa plantilla_contenido		-->

</tiles:put>


</tiles:insert>
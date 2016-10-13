<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tlds/tags-empaquetador.tld" prefix="emp" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>

<bean:define id="urlGuardar"><html:rewrite action="/GestorBasico/GestorBasico"/></bean:define>
<bean:define id="urlAsistente"><html:rewrite action="/GestorBasico/GestorBasico"/></bean:define>
<bean:define id="urlEstructura"><html:rewrite action="/GestorBasico/GestorBasico"/></bean:define>
<bean:define id="urlArchivos"><html:rewrite action="/GestorArchivos/GestorArchivos"/></bean:define>
<bean:define id="urlImagen"><html:rewrite action="/GestorBasico/GestorBasicoImagen"/></bean:define>
<bean:define id="selected" value="estructura"/>
<emp:layout selected="${selected}" urlArchivos="${urlArchivos}" urlEstructura="${urlEstructura}" urlGuardar="${urlGuardar}" urlAsistente="${urlAsistente}" titleKey="title.Comun">

<bean:define id="empaquetadorSesion" name="<%=es.pode.empaquetador.presentacion.EmpaquetadorSession.SESSION_KEY%>" scope="session"/>


<!-- 
Los valores que se dan en las 8 lineas de arriba a los action estan en el fichero web/target/src/WEB-INF/struts-config.xml del proyecto. 
El fichero /WEB-INF/tlds/tags-empaquetador.tld hace referencia al web/src/jsp/WEB-INF/tags/layout.tag en el tag layout. El archivo
layout.tag sera necesario editarlo en caso que se quieran a�adis mas beans como los de las lineas de abajo.
-->

<!-- Inicio plantilla contenido  -->
<div class="plantilla_contenido">
<!-- ## Para pintar los mensajes de error ## -->
<%@ include file="/layout/messages.jsp" %>
<c:if test="${!empty empaquetadorSesion.mensajeAsistente}">
	<div class="bocadillo">
	<div class="bocadillo_int">
	<div>
	<strong class="titulo_boc"><bean:message key="empaquetador.asistente.Recomendacion"/></strong>
	<p>${empaquetadorSesion.mensajeAsistente}</p>
	</div>
	</div>
	</div>
</c:if>
	<bean:define id="vistaCarpeta" value="${empaquetadorSession.vistaCarpeta}"></bean:define>
		
<!-- ******Inicio  PESTANIAS de FICHA ******-->
<!-- ******Inicio  PESTANIAS de FICHA ******-->
	<div id="ficha_pestanias">
	<div><h2><bean:message key="portal_empaquetado_avanzado.principalElementosTitulo"/></h2></div>
		<ul>
			<logic:equal name="vistaCarpeta" value="true">
				
				<li><a href="<html:rewrite action="/GestorBasico/GestorBasicoVistaCarpeta"/>?vistaCarpeta=false"><bean:message key="gestor_basico.vistaPorArbol"/></a></li>
				<li id="pest_activa"><a href="<html:rewrite action="/GestorBasico/GestorBasicoVistaCarpeta"/>?vistaCarpeta=true" id="seleccionada"><bean:message key="gestor_basico.vistaPorCarpeta"/></a></li>
			</logic:equal>
			<logic:equal name="vistaCarpeta" value="false">
				
				<li id="pest_activa"><a href="<html:rewrite action="/GestorBasico/GestorBasicoVistaCarpeta"/>?vistaCarpeta=false" id="seleccionada"><bean:message key="gestor_basico.vistaPorArbol"/></a></li>
				<li><a href="<html:rewrite action="/GestorBasico/GestorBasicoVistaCarpeta"/>?vistaCarpeta=true"><bean:message key="gestor_basico.vistaPorCarpeta"/></a></li>
			</logic:equal>			
		</ul>
	</div>
<!-- ******Fin PESTANIAS de FICHA****** -->
<!-- ******Fin PESTANIAS de FICHA****** -->
<div class="interno_ficha">
<!-- ******Inicio CONTENIDO PESTANIAS******-->
<!-- ******Inicio CONTENIDO PESTANIAS ******-->
<div class="plantilla_contenido_pestanias">
<form id="gestorBasicoGestorBasicoSubmitForm" method="post" action="<html:rewrite action="/GestorBasico/GestorBasicoSubmit"/>">

	<!-- Aviso de licencias -->
	<c:if test="${form.mostrarAvisoLicencia == true}">
		<div class="error">
		<bean:message key="comunes.licenciaNoPermiteObraDerivada" />
		</div>
	</c:if>
	
	<!-- Aviso de cambio de licencias -->
	<c:if test="${form.mostradoMensajeCompatibilidadLicencia == false}">
	<!-- Class exito se pinta en verde, luego deber�a pasar var para pintar como error o como exito -->
		<div class="error">
		<!-- Mostramos mensaje -->
		<p>${empaquetadorSesion.mensajeCompatibilidadLicencias }</p>
		</div>
	</c:if>

<!-- ***********PORTAPAPELES***********-->
<logic:notEmpty name="form" property="portapapeles">
<!--  INICIO GLOBO GRIS   -->
<!--  INICIO GLOBO GRIS   -->
<div class="globo_gris" >
  <div class="globo_gris_01">
	<div class="globo_gris_02">
 		<div class="globo_gris_03">
<!-- ******************************************* INICIO CAJA DE FORMULARIO   *****************************-->
				<div id="formulario_02" >
				<html:link action="/GestorBasico/GestorBasicoVaciarPortapapeles" styleClass="flotder_enlace"><bean:message key="gestor_basico.vaciarPortapapeles"/></html:link>
				<h3><bean:message key="gestor_basico.portapapeles"/></h3>
				
				<!-- ****************** INICIO CAPA TABLA PORTAPAPELES ******************  -->
				<c:set var="portapapeles" value="${form.portapapeles}"/>				
 				<div class="caja_tabla bordeada" >
					<table border="1" class="generica_02" id="portapapeles" cellpadding="0" cellspacing="0" width="100%" summary="Tabla de Portapapales">
						<logic:iterate id="idportapapeles" name="portapapeles">
							<caption><strong><bean:message key="gestor_basico.tablaPortapapeles"/></strong></caption>
							<tr class="tr_beige">
								<td valign="top" class="td_larga"><a href="#" class="recurso">${fn:escapeXml(idportapapeles.title)}</a></td>
								<td class="td_corta02"><a href="<html:rewrite action="/GestorBasico/GestorBasicoEliminarPortapapeles"/>?identifier=${idportapapeles.identifier}" ><bean:message key="gestor_basico.eliminar"/></a></td>
							</tr>
						</logic:iterate>
					</table>
				</div>
			<!-- ***************************** FIN CAPA TABLA PORTAPAPELES *****************************  -->
			</div>
<!-- *********************************************** FIN CAJA DE FORMULARIO   *****************************-->
		</div>
	</div>
  </div>
</div>
</logic:notEmpty>
<!--***********PORTAPAPELES***********-->
<!-- *****************************INICIO ARBOL VERTICAL *****************************-->
<logic:equal name="vistaCarpeta" value="false">
<div id="menu_arbol">
  <ul>
    <!--***************************** Primer Nivel ***************************** -->
	<c:set var="contNiveles" value="1" />
	<fmt:formatNumber var="nivel" value="${contNiveles}" minIntegerDigits="2" maxIntegerDigits="3"/>
	<c:set var="claseOrgAbierto" value="nivel${nivel}_grupo_abierto" />
	<c:set var="claseOrgCerrado" value="nivel${nivel}_grupo_cerrado" />
	<bean:define id="idCollection" name="form" property="idCollection"/>

		<li id= "${form.organizacion.identifier }" class="nivel01_grupo_abierto">
		<span onkeypress="P7_swapClass(1,'${form.organizacion.identifier }','nivel01_grupo_abierto','nivel01_grupo_cerrado','li');return false" onclick="P7_swapClass(1,'${form.organizacion.identifier }','nivel01_grupo_abierto','nivel01_grupo_cerrado','li');return false">&nbsp;</span>
		<a href="<html:rewrite action="/GestorBasico/GestorBasicoNavegar"/>?identifier=${form.organizacion.identifier }" onkeypress="P7_swapClass(1,'${form.organizacion.identifier }','nivel01_grupo_abierto','nivel01_grupo_cerrado','li');return true" onclick="P7_swapClass(1,'${form.organizacion.identifier }','nivel01_grupo_abierto','nivel01_grupo_cerrado','li');return true">${fn:escapeXml(form.organizacion.title)}</a>	
		<bean:define id="organizacion" name="form" property="organizacion"/>
		<logic:notEmpty name="organizacion" property="grupos">
		<!--  entra en empty -->
		<bean:define id="actionNavegar"><html:rewrite action="/GestorBasico/GestorBasicoNavegar"/></bean:define>
			<logic:iterate id="grupo" name="organizacion" property="grupos">
				<emp:arbol niveles="${contNiveles +1}" datosIter="${grupo}" idCollection="${idCollection}" actionNavegar="${actionNavegar}" tipoEmpaquetador="basico"/>
			</logic:iterate>
		</logic:notEmpty>
	</li>
	<!--***************************** Fin Primer Nivel ***************************** -->
  </ul>
</div>
</logic:equal>
<!-- *****************************FIN ARBOL VERTICAL *****************************-->
<!-- ******** INICIO GLOBO GRIS  ******** -->
<!-- ******** INICIO GLOBO GRIS  ******** -->
<div class="globo_gris" >
	<div class="globo_gris_01">

		<div class="globo_gris_02">
			<div class="globo_gris_03">
			
			<!-- ******** INICIO CAJA DE FORMULARIO  ******** -->
			<div id="formulario" >
			
			<!-- ******** INICIO ICONOS SECUENCIA ********  -->
			<!-- ******** INICIO ICONOS SECUENCIA ********  -->
			<div class="capa_iconos">
			<strong class="oculto"><bean:message key="gestor_basico.secuencia"/>:</strong>
			<ul>
			  <bean:define id="choice" value="${form.controlMode.choice}" ></bean:define>
			  <logic:equal name="choice" value="false">
			  <li><a class="icono_choice_no" href="<html:rewrite action="/GestorBasico/GestorBasicoSecuencia"/>"  title="<bean:message key="secuencia.choice.no"/>">&nbsp;<strong><bean:message key="secuencia.choice.no"/></strong></a></li>
			  </logic:equal>
			  <logic:equal name="choice" value="true">
			  <li><a class="icono_choice_si" href="<html:rewrite action="/GestorBasico/GestorBasicoSecuencia"/>"  title="<bean:message key="secuencia.choice.si"/>">&nbsp;<strong><bean:message key="secuencia.choice.si"/></strong></a></li>
			  </logic:equal>
			  <bean:define id="choiceExit" value="${form.controlMode.choiceExit}" ></bean:define>
  			  <logic:equal name="choiceExit" value="true">
  			  	<li><a class="icono_choice_exit_si" href="<html:rewrite action="/GestorBasico/GestorBasicoSecuencia"/>" title="<bean:message key="secuencia.choice_exit.si"/>">&nbsp;<strong><bean:message key="secuencia.choice_exit.si"/></strong></a></li>
  			  </logic:equal>
  			  <logic:equal name="choiceExit" value="false">
  				<li><a class="icono_choice_exit_no" href="<html:rewrite action="/GestorBasico/GestorBasicoSecuencia"/>" title="<bean:message key="secuencia.choice_exit.no"/>">&nbsp;<strong><bean:message key="secuencia.choice_exit.no"/></strong></a></li>
  			  </logic:equal>
  			  <bean:define id="flow" value="${form.controlMode.flow}" ></bean:define>
			  <logic:equal name="flow" value="true">
			  <li><a class="icono_flow_si" href="<html:rewrite action="/GestorBasico/GestorBasicoSecuencia"/>" title="<bean:message key="secuencia.flow.si"/>">&nbsp;<strong><bean:message key="secuencia.flow.si"/></strong></a></li>
			  </logic:equal>
			  <logic:equal name="flow" value="false">
				 <li><a class="icono_flow_no" href="<html:rewrite action="/GestorBasico/GestorBasicoSecuencia"/>" title="<bean:message key="secuencia.flow.no"/>">&nbsp;<strong><bean:message key="secuencia.flow.no"/></strong></a></li>
			  </logic:equal>
			  <bean:define id="forwardOnly" value="${form.controlMode.forwardOnly}" ></bean:define>
  			  <logic:equal name="forwardOnly" value="true">
  				 <li><a class="icono_forward_only_si" href="<html:rewrite action="/GestorBasico/GestorBasicoSecuencia"/>" title="<bean:message key="secuencia.forward_only.si"/>">&nbsp;<strong><bean:message key="secuencia.forward_only.si"/></strong></a></li>
  			  </logic:equal>
  			  <logic:equal name="forwardOnly" value="false">
  				  <li><a class="icono_forward_only_no" href="<html:rewrite action="/GestorBasico/GestorBasicoSecuencia"/>" title="<bean:message key="secuencia.forward_only.no"/>">&nbsp;<strong><bean:message key="secuencia.forward_only.no"/></strong></a></li>
  			  </logic:equal>
			  </ul>
			</div>
			<!-- ******** FIN ICONOS SECUENCIA  ******** -->
			<!-- ******** FIN ICONOS SECUENCIA  ******** -->
			<!-- ********inicio Breadcrumb********   -->
			<div class="breadcrumb_arbol"><em class="oculto">-</em>
			<c:forEach var="grupo" items="${form.idCollection}" varStatus="status">
			<c:choose>
			<c:when test="${status.last }">
				<span class="br_carpeta_abierta" >${fn:escapeXml(grupo.title)}</span>
			</c:when>
			<c:otherwise>
				<a class="br_carpeta_abierta" href="<html:rewrite action="/GestorBasico/GestorBasicoNavegar"/>?identifier=${grupo.identifier}">${fn:escapeXml(grupo.title)}</a><em class="oculto">-</em>
			</c:otherwise>
			</c:choose>
			</c:forEach>
		 	</div>
			<!-- ******** Fin Breadcrumb ********  -->
			<div class="caja_tabla bordeada limpiar_breadcrumb" >
			<table border="1" class="generica_02" cellpadding="0" cellspacing="0" width="100%" summary="<bean:message key="gestor_basico.tablaVistaCarpeta"/>">
			<caption><strong><bean:message key="gestor_basico.tablaVistaCarpeta"/></strong></caption>
			<c:set var="contador" value="true"/>
 <c:set var="contflechas" value="0"/>
 <bean:size id="longitud" name="form" property="grupos"/>	
 
 <logic:notEmpty name="form" property="grupos">
	 <logic:iterate id="grupo" name="form" property="grupos">
	 
		<logic:equal name="contador" value="true">
			<tr class="tr_gris">
		</logic:equal>
		<logic:equal name="contador" value="false">
			<tr class="tr_blanco">
		</logic:equal>
		<c:set var="contador" value="${!contador}"/>
		<emp:checkbox portapapeles="${form.portapapeles}" accion="${empaquetadorSession.accion}" name="identifierRowSelectionAsArray" grupo="${grupo}"/>
		
		<td valign="top"  class="td_larga" >
			<a href="<html:rewrite action="/GestorBasico/GestorBasicoNavegar"/>?identifier=${grupo.identifier}" class="carpeta_cerrada">${fn:escapeXml(grupo.title)}</a>
		</td>
		
		<td valign="top" class="ejec">
			<span class="oculto">-</span>
			<logic:equal name="grupo" property="esRecurso" value="true">
			<logic:notEmpty name="grupo" property="recursoURL">
				<bean:define id="urlConHost"><rewrite:rewrite url="${grupo.recursoURL}"/></bean:define>
			 	<a href="<emp:encodeURL url="${urlConHost}"/>" target="_blank"><bean:message key="portal_empaquetado_gestorOrganizaciones.ver"/></a>
			</logic:notEmpty>
			</logic:equal>
			<logic:equal name="grupo" property="esRecurso" value="false">
			  	<a href="#"><span class="oculto">-</span></a>
			</logic:equal>
		</td>
		
		<td valign="top" class="ejec">
			<span class="oculto">-</span>
			<a href="<html:rewrite action="/GestorBasico/GestorBasicoAsociar"/>?identifier=${grupo.identifier}"><bean:message key="gestor_basico.asociar"/></a>
		</td>
	
		<bean:define id="principalUrl"><html:rewrite action="/GestorBasico/GestorBasicoPrincipal"/></bean:define>
		<emp:principal grupo="${grupo}" recursos="${empaquetadorSession.ode.recursos}" url="${principalUrl}" i18nKey="gestor_basico.principal"/>
	  	
	  	<td valign="top" class="ejec">
	  		<span class="oculto">-</span><a href="<html:rewrite action="/GestorBasico/GestorBasicoRenombrar"/>?identifier=${grupo.identifier}&amp;esModificar=true"><bean:message key="gestor_basico.renombrar"/></a>
	  	</td>
	 	<logic:equal name="contflechas" value="0">
		  	<td valign="top" class="td_corta" align="center"><span class="oculto">-</span><a href="<html:rewrite action="/GestorBasico/GestorBasicoBajar"/>?identifier=${grupo.identifier}" class="icono_bajar" title="Bajar"><strong><bean:message key="gestor_basico.bajar"/></strong></a></td>
	  	</logic:equal>
	  
	  	<logic:notEqual name="contflechas" value="0">
	    	<logic:notEqual name="contflechas" value="${longitud-1}">
	    		<td valign="top" class="td_corta" align="center"><span class="oculto">-</span><a href="<html:rewrite action="/GestorBasico/GestorBasicoSubir"/>?identifier=${grupo.identifier}" class="icono_subir_02" title="Subir"><strong><bean:message key="gestor_basico.subir"/></strong></a><span class="oculto">&nbsp;y&nbsp;</span><a href="<html:rewrite action="/GestorBasico/GestorBasicoBajar"/>?identifier=${grupo.identifier}" class="icono_bajar_02" title="Bajar"><strong><bean:message key="gestor_basico.bajar"/></strong></a></td>
	    	</logic:notEqual>
	  	</logic:notEqual>
	  
	  	<logic:equal name="contflechas" value="${longitud-1}">
	  		<logic:notEqual name="contflechas" value="0">
	  		<td valign="top" class="td_corta" align="center"><span class="oculto">-</span><a href="<html:rewrite action="/GestorBasico/GestorBasicoSubir"/>?identifier=${grupo.identifier}" class="icono_subir" title="Subir"><strong><bean:message key="gestor_basico.subir"/></strong></a></td>
	  		</logic:notEqual>
	  	</logic:equal>
		</tr>
	 	<c:set var="contflechas" value="${contflechas+1}"/>
	
	</logic:iterate>
</logic:notEmpty>

<logic:empty name="form" property="grupos">
	<td><bean:message key="portal_empaquetado_crearElemento.gestorElementos.noHayElementos"/></td>
</logic:empty>
</table>
</div>
	<!-- ******** FIN CAPA TABLA ********  -->		
	<!-- ******** FIN CAPA TABLA ******** -->		
</div>
<!-- ******** FIN CAJA DE FORMULARIO  ******** -->			
<!--******** Inicio Botones ******** -->
<!--******** Inicio Botones ******** -->
<fieldset class="tipo_interior">
<logic:notEmpty name="form" property="portapapeles">
   <logic:equal name="empaquetadorSession" property="modoPegar" value="true">	
     <logic:notEqual name="empaquetadorSession" property="accion" value="Normal">	
	   <input class="boton_65_de_3_der bot_mar_izq" type="submit" name="actionSubmit" value="<bean:message key='gestor_basico.pegar'/>" />
	  </logic:notEqual>
	 </logic:equal> 
</logic:notEmpty>
<logic:equal name="empaquetadorSession" property="modoPegar" value="false">	
	   <logic:equal name="empaquetadorSession" property="accion" value="Normal">	
	   	<input type="submit" class="boton_65_de_3_der bot_mar_izq" name="actionSubmit" value="<bean:message key='gestor_basico.cortar'/>" />
	   </logic:equal>
	   <logic:equal name="empaquetadorSession" property="accion" value="Cortar">	
	   	<input type="submit" class="boton_65_de_3_der bot_mar_izq" name="actionSubmit" value="<bean:message key='gestor_basico.cortar'/>" />
	   </logic:equal>
	</logic:equal>
	
	<logic:equal name="empaquetadorSession" property="modoPegar" value="false">	
	   <logic:equal name="empaquetadorSession" property="accion" value="Normal">	
	   	       		<input type="submit" class="boton_65_de_3_der bot_mar_izq" name="actionSubmit" value="<bean:message key='gestor_basico.copiar'/>" />
	   </logic:equal>
	   <logic:equal name="empaquetadorSession" property="accion" value="Copiar">	
       		<input type="submit" class="boton_65_de_3_der bot_mar_izq" name="actionSubmit" value="<bean:message key='gestor_basico.copiar'/>" />
	   </logic:equal>
	</logic:equal>
	
	<logic:equal name="empaquetadorSession" property="accion" value="Normal">	
	      <input type="submit" class="boton_65_de_3_der  bot_mar_izq" name="actionSubmit" value="<bean:message key='gestor_basico.eliminar'/>" />
	</logic:equal>
<br class="oculto" /><br class="oculto" />
<input class="boton_125_de_2_izq bot_mar_der"  type="submit" name="actionSubmit" value="<bean:message key="gestor_basico.anadirCarpeta"/>" />

<label class="oculto" for="masAcciones">Seleccione:</label>
<select name="actionCombo" class="masAcciones" title="<bean:message key="gestor_basico.masAcciones.submit"/>" id="masAcciones" onchange="document.getElementById('actionComboSubmit').click();">
<option value="#" selected="selected" ><bean:message key="gestor_basico.masAcciones.submit"/></option>
<optgroup label="<bean:message key="gestor_basico.renombrar.organizacion.comboText"/>">
<option value="RenombrarOrganizacion" ><bean:message key="gestor_basico.renombrar.organizacion.comboText"/>&nbsp;${form.organizacion.title}</option>
</optgroup>
<optgroup label="<bean:message key="gestor_basico.comboGroup.text"/>">
<option value="Agregar" ><bean:message key="gestor_basico.agregarODE"/></option>
<option value="Desagregar" class="oscura"><bean:message key="gestor_basico.desagregarODE"/></option>
</optgroup>
</select>
<input id="actionComboSubmit" type="submit" name="actionSubmit" value="<bean:message key="gestor_basico.masAcciones.submit"/>" style="display:none"/>
<noscript>
<input type="submit" name="actionSubmit" value="<bean:message key="gestor_basico.masAcciones.submit"/>" class="boton_125"/>
</noscript>
</fieldset>

<!-- Fin Botones  -->
<!-- Fin Botones  -->
			<!--  FIN CAJA DE FORMULARIO   -->
			</div>

		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
</form>
</div></div>
<!-- ***Fin CONTENIDO PESTANIAS **-->
<!-- ***Fin CONTENIDO PESTANIAS **-->
</div>
<!--** Fin plantilla contenido ** -->
<!--** Fin plantilla contenido ** -->
</emp:layout>
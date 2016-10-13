<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="/WEB-INF/tlds/tags-empaquetador.tld" prefix="emp" %>
<%@ taglib uri="/WEB-INF/tlds/tags-empaquetador.tld" prefix="el"%>
<bean:define id="urlArchivos"><html:rewrite action="/GestorArchivos/GestorArchivos"/></bean:define>
<bean:define id="urlRecursos"><html:rewrite action="/GestorRecursos/GestorRecusos"/></bean:define>
<bean:define id="urlOrganizaciones"><html:rewrite action="/GestorOrganizaciones/GestorOrganizaciones"/></bean:define>
<bean:define id="urlSubmanifiestos"><html:rewrite action="/GestorSubmanifiestos/GestorSubmanifiestos"/></bean:define>
<bean:define id="urlGuardar"><html:rewrite action="/GestorSubmanifiestos/GestorSubmanifiestos"/></bean:define>
<bean:define id="urlAsistente"><html:rewrite action="/AgregarPersonales/AgregarPersonales"/></bean:define>
<bean:define id="urlEstructura"><html:rewrite action="/GestorBasico/GestorBasico"/></bean:define>
<bean:define id="selected" value="submanifiestos"/>
<emp:layout selected="${selected}" urlEstructura="${urlEstructura}" urlArchivos="${urlArchivos}" urlRecursos="${urlRecursos}" urlSubmanifiestos="${urlSubmanifiestos}" urlOrganizaciones="${urlOrganizaciones}" urlGuardar="${urlGuardar}" urlAsistente="${urlAsistente}" titleKey="title.Comun">
<bean:define id="empaquetadorSesion" name="<%=es.pode.empaquetador.presentacion.EmpaquetadorSession.SESSION_KEY%>" scope="session"/>

<div id="capa_madre">

<div class="plantilla_contenido">
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

<h2><bean:message key="portal_empaquetado_agregarSubmanifiestos.desdeObjetosPersonales"/></h2>
<form id="agregarPersonalesObjetosPersonalesAceptarForm" method="get" action="<html:rewrite action="/AgregarPersonales/ObjetosPersonalesAceptar"/>" enctype="multipart/form-data">
<p class="parrafo_comun" id="separacion2"><bean:message key="portal_empaquetado_agregarSubmanifiestos.seleccionarObjeto"/></p>
		
		<bean:define id="offline">${empaquetadorSesion.esOffline}</bean:define>

<c:if test="${!offline}">
		<!-- Inicio  PESTANIAS de FICHA -->
		<bean:define id="compartidosValue">${form.compartidos}</bean:define>
		<div id="ficha_pestanias">
		<ul>
			<logic:equal name="compartidosValue" value="false">
				<c:if test="${!el:esTaller()}">
					<li><a
						href="<html:rewrite action="/AgregarPersonales/AgregarPersonales"/>?compartidos=true"><bean:message
						key="portal_empaquetado_agregarSubmanifiestos.compartidos" /></a></li>
				</c:if>
				<li id="pest_activa"><a
					href="<html:rewrite action="/AgregarPersonales/AgregarPersonales"/>"
					id="seleccionada"><bean:message
					key="portal_empaquetado_agregarSubmanifiestos.personales" /></a></li>
				
			</logic:equal>
			<logic:equal name="compartidosValue" value="true">
					
					<li id="pest_activa"><a
						href="<html:rewrite action="/AgregarPersonales/AgregarPersonales"/>?compartidos=true"
						id="seleccionada"><bean:message
						key="portal_empaquetado_agregarSubmanifiestos.compartidos" /></a></li>
					<li><a
						href="<html:rewrite action="/AgregarPersonales/AgregarPersonales"/>"><bean:message
						key="portal_empaquetado_agregarSubmanifiestos.personales" /></a></li>
			</logic:equal>
		</ul>
		</div>
		<!-- Fin PESTANIAS de FICHA -->
<!-- Inicio Interior ficha-->
		<div class="interno_ficha">
		<div class="plantilla_contenido_pestanias">

</c:if>
<div class="caja_tabla">

	<display:table name="${form.personales}" requestURI=""
					id="fila" class="administracion_tareas" 
					style="border:1;width:100%;" cellpadding="0" cellspacing="0" 
					summary="${summary}" sort="list" pagesize="10">
					
		<display:setProperty name="css.tr.odd" value="tr_gris"/>
		<display:setProperty name="css.tr.even" value="tr_blanco"/>
		<display:setProperty name="basic.show.header" value="false"/>

	
	<!--		Columna de 	Radiobutton	(html)	-->
	   <display:column style="valign:top;" class="sin_b">
	   	   <label for="${fila.idODE}" class="etiq_invisible" ><bean:message key="portal_empaquetado_agregarSubmanifiestos.seleccionar"/></label>
           <input type="radio" class="boton_radio" name="idODE" id="${fila.idODE}" value="${fila.idODE}" />
       </display:column>
	<!--		Columna de Titulo de ODE (html)		-->

		<display:column style="valign:top;" class="tar2">
			<html:link href="#" styleClass="paquete">
				 ${fila.titulo}
			</html:link>
		</display:column>				
	</display:table>

<c:if test="${!offline}">
</div>
</div>
</c:if>

<!-- Fin Interior ficha-->


<fieldset class="tipo">

<input class="boton_125_de_2"  type="submit"  name="action" value="<bean:message key="portal_empaquetado_agregarSubmanifiestos.aceptar"/>" />
<input class="boton_125_de_2_izq"  type="submit"  name="action" value="<bean:message key="portal_empaquetado_agregarSubmanifiestos.cancelar"/>" />

</fieldset>	
</form>

</div>

</div>
</emp:layout>
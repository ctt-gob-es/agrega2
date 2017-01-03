<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>	
	<div id="loadingZone" style="display:${empty sessionScope.myProgressMonitor ? 'none' : 'block' }">
	<div class="globo_gris" style="margin-top:10px;">
	<div class="globo_gris_01">
		<div class="globo_gris_02">
			<div class="globo_gris_03" style="padding:0">
			<!--  INICIO CAJA DE FORMULARIO   -->
			<div id="formulario">
		<div id="loadingSms"><bean:message key="comunes.cargando"/></div>

		<div id="infoProgress">${empty sessionScope.myProgressMonitor ? '0' : sessionScope.myProgressMonitor.percentageProgress }%</div>
		<br class="clear" />
		<div id="loadingBar">
		<div id="progressBar" style="width: ${empty sessionScope.myProgressMonitor ? '0' : sessionScope.myProgressMonitor.percentageProgress }%">&nbsp;</div>
		</div>
		<div id="infoLoading"></div>
			</div>
				<!--  FIN CAJA DE FORMULARIO   -->

			</div>
		</div>
	</div>
</div>
<!--  FIN GLOBO GRIS   -->
<!--  FIN GLOBO GRIS   -->
	</div>
	
</div>

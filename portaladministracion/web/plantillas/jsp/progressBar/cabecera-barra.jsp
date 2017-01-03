<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/tags/rewriteTag.tld" prefix="rewrite" %>

<bean:define id="barraUrl"><html:rewrite page="/progressBar/barra.jsp"/></bean:define>

<script type="text/javascript">
	var updater = null;

	function startLoading() {
		document.getElementById('con_loading').style.display='none';

		document.getElementById("loadingZone").style.display = "block";
		updater = new Ajax.PeriodicalUpdater(
                            'loading',
                            '${barraUrl}',
                            {asynchronous:true, frequency:1, method: 'get', parameters: 'progressMonitor=myProgressMonitor', onFailure: reportError});
		return true;
			
	}
	function redireccionar() {
		location.href="#";
	}


	function reportError(request)
	{
		document.getElementById('con_loading').style.display='block';

	}
</script>

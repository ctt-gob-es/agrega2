


<?xml version="1.0" encoding="iso-8859-1" ?> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">

<head lang="es" dir="ltr">

<title>${redes}</title>

<link type="text/css"  rel="stylesheet" media="screen" href="static/css/red.css" />
<script type="text/javascript" src="static/js/curvas.js"></script>

<script language="JavaScript" type="text/javascript">
//<![CDATA[

function invarGlob()
{
	vActual=parent.menu.document.f1.variable;
	vMaximo=parent.menu.document.f1.maximo;
}


function siguiente()
{
	var anterior = vActual.value;
	if(parseInt(vActual.value) < parseInt(vMaximo.value))
	{
		vActual.value= parseInt(vActual.value)+1;
		
		parent.menu.document.getElementById(anterior).className = '';
		parent.menu.document.getElementById(vActual.value).className = 'escogido';

		var href= parent.menu.document.getElementById(vActual.value).href;
		var largo=href.length;
		var ultimo =href.charAt(largo-1);
		
		if(ultimo!='#')
		{
			parent.contenido_principal.location=href;
		}else
		{
			parent.contenido_principal.location='blanco.html';
		}

	}
	
}

function anterior()
{
	var anterior= vActual.value;
	if(parseInt(vActual.value)>0)
	{
		vActual.value= parseInt(vActual.value)-1;
		parent.menu.document.getElementById(anterior).className = '';
		parent.menu.document.getElementById(vActual.value).className = 'escogido';

		var href= parent.menu.document.getElementById(vActual.value).href;
		var largo=href.length;
		var ultimo =href.charAt(largo-1);
		
		if(ultimo!='#')
		{
			parent.contenido_principal.location=href;
		}else
		{
			parent.contenido_principal.location='blanco.html';
		}

	}
	

}

function cierra_ventana()
{
		top.window.opener=top;
		top.window.open('','_top','');
		top.window.close();
}


//]]>
</script>

</head>

<body id="pseudo_iframe" onload="invarGlob();">

<!--  CAPA QUE CONTIENE TODAS LAS DEMAS  -->
<!--  INICIO CAPA MADRE   -->
<div id="capa_madre">

<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
<!-- INICIO CABECERA  -->
<div id="cabecera"  >
<h1><span>${comunidad}</span></h1>

<!-- INICIO METANAVEGACION   -->
<!-- INICIO METANAVEGACION   -->
<div id="metanavegacion" >
<ol id="container">
		<li class="oculto"><a href="#contenido_central" title="${contenido}"><strong>Contenido</strong></a></li>
</ol>
</div>
<!-- FIN METANAVEGACION   -->
<!-- FIN METANAVEGACION   -->


<br class="oculto" />

<h2>${titulo}</h2>

</div>


<span class="flechas"  id="sin_ol">
	<a  href="#" onclick="javascript:anterior()" target="" title="${anterior}" class="f_left">&nbsp;&nbsp;<span>${infAnterior}</span></a>
	<a  href="#" onclick="javascript:siguiente()" target=""  title="${siguiente}" class="f_right">&nbsp;&nbsp;<span>${infSiguiente}</span></a>
</span>



</div>
<!--  FIN CAPA MADRE   -->
<!--  FIN CAPA MADRE   -->
</body>
</html>

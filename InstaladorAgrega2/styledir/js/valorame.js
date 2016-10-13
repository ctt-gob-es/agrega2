function changeImage(mostrar,voto){
		cont=1;
		imagenes=document.getElementsByTagName("IMG");
		for(i=0;i<imagenes.length;i++){
			if(imagenes[i].className=="votar" && cont<=voto && mostrar){
				imagenes[i].src="../img/star_01.gif";
				cont++;
			}
			else{
				if(imagenes[i].className=="votar"){
					imagenes[i].src="../img/star_03.gif";
				}
			}
		}
	}
	
	function getAjaxObject(){
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("POST", "/valoracion/valoracion.php", false);
				
		// The callback function
		xmlhttp.onreadystatechange = function()
			{
				if (xmlhttp.readyState == 4)
				{
				  if(xmlhttp.status!=200)
					{
						alert('No se puede actualizar');
					}
				}
			}
		//Fin de la funcion	
	return xmlhttp;		
	}
	
	function valorame(votacion){
		peticion=getAjaxObject();
		peticion.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		tipoValoracion=document.getElementById('tipoVal').value
		data="votacion="+votacion+"&tipo=3&id="+document.getElementById('httpValoracion').value+"&tipoVal="+tipoValoracion;
		peticion.send(data);
		document.getElementById("valorame").innerHTML=peticion.responseText;
		
	}

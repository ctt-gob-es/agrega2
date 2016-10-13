
function abrirCerrar(item,abierto,cerrado)
{
    var elemento = document.getElementById(item);

	if(elemento.className == cerrado){	elemento.className=abierto;
	}
	else if(elemento.className == abierto){
		elemento.className = cerrado;
	}
}

	
	

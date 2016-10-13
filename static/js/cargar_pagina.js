var nn4 = (document.layers);
var nn6 = (document.getElementById && !document.all);
var ie4 = (document.all && !document.getElementById);
var ie5 = (document.all && document.getElementById);
function loadPage(id,nestref,url) {
if (nn4) {
var lyr = (nestref)? eval('document.'+nestref+'.document.'+id) : document.layers[id]
lyr.load(url,lyr.clip.width)
}
else if(ie4) parent.contenidoFRAME.location = url;
else if(ie5 || nn6) document.getElementById('contenidoFRAME').src = url;
}
function showPage(id) {
if (ie4) {
document.all[id].innerHTML = parent.contentFRM.document.body.innerHTML;
}
else if(nn6 || ie5) { 
document.getElementById(id).innerHTML = window.frames['contenidoFRAME'].document.getElementById('contenidoBusqueda').innerHTML;
   }
}
/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point

package es.pode.soporte.auditoria.registrar;


public interface RegistroCtes
{	
	public static final String FILE_NAME_PROPERTIES = "/agrega.properties";
	public static final String FILE_NAME_XML = "/auditoria.xml";
	public static final String AUDITORIA_NO = "NO";
	public static final String ITEM_WEBSERVICE = "webService";
	public static final String ITEM_ARGUMENTO = "argumentoItem"; 
	public static final String ITEM_METODO = "metodoItem";
	public static final String ATTRIBUTE_NAME = "name";
	public static final String ATTRIBUTE_ATRIBUTO = "atributo";
	public static final String ATTRIBUTE_DESC_PARAMETRO = "descripcionParametro";
	public static final String ATTRIBUTE_ARGUMENTO_VUELTA = "argumentoVuelta";
	public static final String ATTRIBUTE_ARTRIBUTO_VUELTA = "atributoVuelta";
	public static final String ATTRIBUTE_DESTINO = "destino";
	public static final String SEPARATOR = ",";
	public static final String BUSCAR_PAGINA1 = "1";
	public static final String BUSQUEDA_LOCAL = "local";
	public static final String BUSQUEDA_FEDERADA = "federada";
	public static final String BUSQUEDA_SQI = "SQI";
	public static final String SQI_LANG = "VSQL";
	public static final String LUCENE_LANG = "LQS";
	public static final String PREFIJO_ODE_NO_REGISTRO = "EMP";
	/* Parámetros/argumentos que se interceptan  
	 * Tienen que coincidir con el atributo de auditoria.xml */
	public static final String PARAMETRO_ID_ODE = "idODE";
	public static final String PARAMETRO_IDENTIFICADOR_ODE = "identificadorODE";
	public static final String PARAMETRO_IDENTIFICADOR = "identificador";
	public static final String PARAMETRO_TIPO_PIF = "tipoPif";
	public static final String PARAMETRO_QUERY = "query";
	public static final String PARAMETRO_IDIOMA_BUSQUEDA = "idiomaBusqueda";
	public static final String PARAMETRO_PALABRAS_CLAVE = "palabrasClave";
	public static final String PARAMETRO_ORIGEN_PAGINA = "origenPagina";
	public static final String PARAMETRO_COMUNIDADES = "comunidadesSeleccionadas";
	public static final String PARAMETRO_AREACURRICULAR = "areaCurricular";
	public static final String PARAMETRO_TESAURO = "idTesauro";
	public static final String PARAMETRO_TIPO_BUSQUEDA = "busquedaSimpleAvanzada";
	public static final String PARAMETRO_IDIOMA = "idioma";
	public static final String PARAMETRO_IDFEED = "idFeed";
	public static final String PARAMETRO_FORMATOFEED = "formatoFeed";
	public static final String PARAMETRO_IDIOMANAVEGACION = "idiomaNavegacion";
	
	
	/* Valores que nos pueden llegar en los parámetros */
	public static final String VALORES_BUSCAR_ARBOLCURRICULAR = "BUSQUEDA_ARBOL";
	public static final String VALORES_BUSCAR_SIMPLE = "BUSCAR_SIMPLE";
	public static final String VALORES_BUSCAR_AVANZADO = "BUSCAR";
	public static final String VALORES_BUSCAR_TESAURO = "BUSQUEDA_TESAURO";
	public static final String VALORES_FICHA_SIMPLEAVANZADA = "POSICIONADO_DETALLE"; // Nos aseguramos que la llamada a la ficha es la que nos interesa
	public static final String VALORES_FICHA_EMBED = "POSICIONADO_DETALLEEMBED";
	
	/* Valores que se enviarán a auditoría como tipo de búsqueda */
	public static final String ENVIO_BUSQUEDA_NORMAL = "normal"; 
	public static final String ENVIO_BUSQUEDA_AREACURRICULAR = "areaCurricular"; 
	public static final String ENVIO_BUSQUEDA_TESAURO = "tesauro"; 
	public static final String ENVIO_BUSQUEDA_AVANZADA = "busquedaAvanzada"; 
	/* Operaciones para el registro 
	 * Tiene que coincidir con el destino de auditoria.xml */
	public static final String OPERACION_FICHA = "ficha";  
	public static final String OPERACION_FICHA_EMBED = "ficha_embed";
	public static final String OPERACION_DESCARGADO = "descargado";
	public static final String OPERACION_PREVISUALIZADO = "previsualizado";
	public static final String OPERACION_BUSCAR = "buscar";	
	public static final String OPERACION_BUSCAR_SQI = "buscarSQI";	
	public static final String OPERACION_ENVIAR_CORREO = "enviarCorreo";
	public static final String OPERACION_PETICION_FEED = "peticionFeed";
	public static final String OPERACION_BUSQUEDA_FEED = "busquedaFeed";
	public static final String OPERACION_ACCESO_PORTADA = "portada";
	/* Identificador para el feed de una busqueda. */
	public static final String ID_FEED_BUSQUEDAS = "searchFeed";

}

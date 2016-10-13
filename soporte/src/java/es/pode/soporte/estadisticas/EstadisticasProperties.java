package es.pode.soporte.estadisticas;

public interface EstadisticasProperties 
{
	public static final String PROPERTIES_FILE_NAME = "estadisticas"; 
	public static final String PROPERTY_NUMERO_OPERACIONES 	= "numero_operacion";
	public static final String PROPERTY_OPERACIONES_DESCARGA 	= "operacion_descarga";
	public static final String PROPERTY_OPERACIONES_VISTO 	= "operacion_visto";
	public static final String PROPERTY_OPERACIONES_PREVISUALIZADO 	= "operacion_previsualizado";
	public static final String PROPERTY_OPERACIONES_ENVIO 	= "operacion_envio";
	public static final String PROPERTY_OPERACIONES_EMBED	= "operacion_embed";
	public String getProperty(String sKey);

}
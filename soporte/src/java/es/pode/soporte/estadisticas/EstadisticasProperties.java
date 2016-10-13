/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
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
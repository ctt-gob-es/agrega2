/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.soporte.configuracionPortal;

import java.util.Calendar;


public interface ConfiguracionPortal {

	public Integer getNoticias();
	public Integer getInformes();
	public Integer getDescargas();
	public Integer getAgregaWeb();
	public Integer getTagging();
	public Integer getActiva();
	public Calendar getFecha();
	public Integer getRss();
	public Integer getEstadisticas();
	public Integer getEtiquetas();
	public Integer getPlugginBusqueda();
	public Integer getRegistrese();
	public Integer getOpenId();
	public String getImagen();
	public Integer getEnlacePassword();
	public Integer getGoogleAnalytic();
	public String getCodGa();
	public String getIdRssGaleriaPortada();
	public Integer getNumOdesGaleria();
	public Integer getItinerariosAprendizaje();
	public void reset();
	
}

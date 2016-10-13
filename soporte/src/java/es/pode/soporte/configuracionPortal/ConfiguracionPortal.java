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

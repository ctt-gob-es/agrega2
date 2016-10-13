/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.indexador.negocio.compass;

import org.apache.log4j.Logger;

public class ThreadOptimizar extends Thread{
	private static Logger logger = Logger.getLogger(ThreadOptimizar.class);
    private String idioma = null;
    private GestorCompass gestorCompass  = null;
    private long fechaUltEjecucionMilis=0;
	public void run() {
		try{
			logger.info("Iniciamos optimización de indice [" + (idioma!=null?"todos":idioma) +"] Thread id=[" + this.getId()+"] name=["+this.getName()+"]");
			gestorCompass.optimizarBorrados(idioma);
			logger.info("Optimización del indice [" + (idioma!=null?idioma:"todos") +"] realizada correctamente. Thread id=[" + this.getId()+"] name=["+this.getName()+"]");
		}catch(Exception e){
			logger.error("Error optimizando indice [" + (idioma!=null?idioma:"todos") +"]Thread id=[" + this.getId()+"] name=["+this.getName()+"]",e);
		}
	}
	public GestorCompass getGestorCompass() {
		return gestorCompass;
	}
	public void setGestorCompass(GestorCompass gestorCompass) {
		this.gestorCompass = gestorCompass;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public long getFechaUltEjecucionMilis() {
		return fechaUltEjecucionMilis;
	}
	public void setFechaUltEjecucionMilis(long fechaUltEjecucionMilis) {
		this.fechaUltEjecucionMilis = fechaUltEjecucionMilis;
	}

}

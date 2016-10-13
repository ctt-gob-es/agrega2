/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point

package es.pode.soporte.auditoria.registrar;

import java.util.HashMap;

public class DatosVO {

	private java.lang.String moduloDestino;
	private HashMap valores;

    /**
     * Obtenemos el valor del módulo destino
     * 
     * @return moduloDestino
     */
    public java.lang.String getModuloDestino() {
        return moduloDestino;
    }

    /**
     * Se carga el valor del módulo destino
     * 
     * @param moduloDestino
     */
    public void setModuloDestino(java.lang.String moduloDestino) {
        this.moduloDestino = moduloDestino;
    }

    /**
     * Obtenemos los valores 
     * 
     * @return moduloDestino
     */
    public HashMap getValores() {
        return valores;
    }

    /**
     * Se carga los valores 
     * 
     * @param valores
     */
    public void setValores(HashMap valores) {
        this.valores = valores;
    }    
}

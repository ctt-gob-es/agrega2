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

// license-header java merge-point

package es.pode.soporte.auditoria.registrar;

import java.util.HashMap;

public class DatosInterceptadosVO {

	private java.lang.String moduloDestino;
	private HashMap valoresCapturados;

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
     * Obtenemos los valores interceptados
     * 
     * @return moduloDestino
     */
    public HashMap getValoresCapturados() {
        return valoresCapturados;
    }

    /**
     * Se carga los valores capturados
     * 
     * @param valoresCapturados
     */
    public void setValoresCapturados(HashMap valoresCapturados) {
        this.valoresCapturados = valoresCapturados;
    }    
}

package es.pode.modificador.negocio.cambios;

import org.jdom.Element;

public class ModificacionValor extends CambioLomes {

	private String nuevoValor;
	
	private boolean  reemplazarTodos=true;

	protected final boolean ejecutarAccion(Element[] termino) {
		boolean result = true;
		
		StringBuffer sb = super.generarMensaje("Modificación");
		sb.append(" con nuevo valor ").append(nuevoValor).append(
				" (replaceAll=").append(reemplazarTodos).append(")");
		if(termino!=null && termino.length>0) {
			logger.info(sb.toString() + ": Se han encontrado " + termino.length + " términos para modificar");
			// Sustituyo el primero
			logger.info("Termino " + termino[0].getName() + " con valor " + termino[0].getValue() + " modificado con nuevo valor " + nuevoValor);
			termino[0].setText(nuevoValor);
			if(reemplazarTodos && termino.length>1) {
				for(int i=1;i<termino.length;i++) {
					logger.info("Termino " + termino[i].getName() + " con valor " + termino[i].getValue() + " modificado con nuevo valor " + nuevoValor);
					termino[i].setText(nuevoValor);
				}
			}
		} else {
			logger.warn(sb.toString() + ": No se han encontrado términos para modificar");
		}
		
		return result;
	}

	
	/**
	 * @return the nuevoValor
	 */
	public String getNuevoValor()
	{
		return nuevoValor;
	}

	/**
	 * @param nuevoValor the nuevoValor to set
	 */
	public void setNuevoValor(String nuevoValor)
	{
		this.nuevoValor = nuevoValor;
	}

	/**
	 * @return the reemplazarTodos
	 */
	public boolean isReemplazarTodos()
	{
		return reemplazarTodos;
	}

	/**
	 * @param reemplazarTodos the reemplazarTodos to set
	 */
	public void setReemplazarTodos(boolean reemplazarTodos)
	{
		this.reemplazarTodos = reemplazarTodos;
	}

}

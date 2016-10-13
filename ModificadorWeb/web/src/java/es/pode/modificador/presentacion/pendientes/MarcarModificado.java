package es.pode.modificador.presentacion.pendientes;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.jsp.PageContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.displaytag.decorator.TableDecorator;

public class MarcarModificado extends TableDecorator{

	private static Logger logger = Logger.getLogger(MarcarModificado.class);
	
	@Override
	public String addRowClass() {	
		String resultado = null;
		PageContext pageContext =  this.getPageContext();
		Object valor = pageContext.getAttribute("valor");
		String atributo = (String) pageContext.getAttribute("atributo");
		Object filaActual = this.getCurrentRowObject();
		
		if(valor !=null && atributo!=null && filaActual!=null){
			if(PropertyUtils.isReadable(filaActual, atributo)){
				try {
					Object valorFila = PropertyUtils.getSimpleProperty(filaActual, atributo);
					if(valorFila !=null && valorFila.toString().equals(valor)) {
						if(logger.isDebugEnabled())logger.debug("Cambiando class de la fila " + this.getViewIndex());
						resultado = "tr_color";
					}
				} catch (IllegalAccessException e) {
					logger.error(e);
				} catch (InvocationTargetException e) {
					logger.error(e);
				} catch (NoSuchMethodException e) {
					logger.error(e);
				}

			}
		}
		
		return resultado;
	}
	
}

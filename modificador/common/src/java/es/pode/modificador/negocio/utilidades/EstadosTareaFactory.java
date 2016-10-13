package es.pode.modificador.negocio.utilidades;

import net.sf.dozer.util.mapping.BeanFactoryIF;

public class EstadosTareaFactory implements BeanFactoryIF
{

	public Object createBean(Object arg0, Class arg1, String arg2)
	{
		
		return EstadosTarea.fromString(((EstadosTarea)arg0).getValue());
	}

}

package es.pode.empaquetador.negocio.utilidades;

import net.sf.dozer.util.mapping.MapperIF;
import es.pode.empaquetador.negocio.gestionficheros.Fichero;
import es.pode.empaquetador.negocio.servicio.FicheroVO;

public class EmpaquetadorBasicoDozerDao {
	//private static Logger logger = Logger.getLogger(EmpaquetadorBasicoDozerDao.class);
	
	public EmpaquetadorBasicoDozerDao() {
	}
	
	private MapperIF beanMapperAux = null;

	public MapperIF getBeanMapperAux() {
		return beanMapperAux;
	}

	public void setBeanMapperAux(MapperIF beanMapperAux) {
		this.beanMapperAux = beanMapperAux;
	}
	
	public FicheroVO toFicheroVO(Fichero fichero) {
		FicheroVO nuevoFichero = (FicheroVO) this.getBeanMapperAux().map(fichero, FicheroVO.class);
		
		return nuevoFichero;
	}
	
	public Fichero fromFicheroVO(FicheroVO ficheroVO) {
		Fichero nuevoFichero = (Fichero) this.getBeanMapperAux().map(ficheroVO, Fichero.class);
		return nuevoFichero;
	}
}

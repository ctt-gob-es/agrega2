package es.pode.modificador.negocio.cambios;

import java.io.InputStream;
import java.io.StringReader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

public class AdicionTermino extends CambioLomes {

	

	// Inicializo el tipo con valor por defecto OTRO
	private EspecialTermTypes tipoTermino = EspecialTermTypes.OTRO;
	
	private String purpose = null;

	private String nuevoValor;
	
	/**
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * @param purpose the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	/**
	 * @return the nuevoValor
	 */
	public String getNuevoValor() {
		return nuevoValor;
	}

	/**
	 * @param nuevoValor
	 *            the nuevoValor to set
	 */
	public void setNuevoValor(String nuevoValor) {
		this.nuevoValor = nuevoValor;
	}

	/**
	 * @return the tipoTermino
	 */
	public EspecialTermTypes getTipoTermino() {
		return tipoTermino;
	}

	/**
	 * @param tipoTermino
	 *            the tipoTermino to set
	 */
	public void setTipoTermino(EspecialTermTypes tipoTermino) {
		this.tipoTermino = tipoTermino;
	}

	protected final boolean ejecutarAccion(Element[] termino) {

		boolean result = true;
		StringBuffer sb = generarMensaje("Adición");
		sb.append(" nuevo término =\n").append(nuevoValor).append("\n\n");
		if(termino !=null && termino.length>0) {
			logger.info(sb.append(" Se han encontrado ").append(
						termino.length).append(
						" términos donde insertar el nuevo término.")
						.append(" Insertando en el primero por defecto")
						.toString());
			if(logger.isDebugEnabled()) logger.debug("Parseando nuevoValor");
			Element nuevoTermino = parsearNuevoValor();
			if(nuevoTermino==null) {
				logger.error("No se ha podido generar un nuevo termino con el texto\n" + nuevoValor);
				result = false;
			} else {
				termino[0].addContent(nuevoTermino.detach());
				logger.info("Termino añadido con éxito");
			}
		} else {
			logger.error(sb.append("No se ha encontrado término ").append(
					terminoLom.substring(0, terminoLom.lastIndexOf('.')))
					.append(" donde insertar el nuevo término").toString());
			result = false;
		}
		
		return result;
	}

	private Element parsearNuevoValor() {
		Element result = null;
		SAXBuilder parser = new SAXBuilder();
		StringReader reader = new StringReader(nuevoValor);
		try {
			Document doc = parser.build(reader);
			result = doc.getRootElement();
		} catch (Exception e) {
			if(logger.isDebugEnabled()) 
				logger.debug("No se ha podido parsear el nuevo termino a añadir",e);
		}
		return result;
	}
	
	/**
	 * Sobreescribo el metodo obtenerTerminoLom(Element elemento) para obtener
	 * el padre del elemento que quiero añadir, o el clasification correcto en
	 * el caso de añadir árboles curriculares o ETBs. Si se encuentran varios,
	 * inserto nuevo termino dentro del primer encontrado.
	 * 
	 * En el caso especial de taxonomia, si no se encuentra el
	 * elemento donde insertar el nuevo taxonPath, se genera un classification
	 *  con purpose el configurado en el cambio para poder insertarlo. En cualquier
	 * otro caso, la tarea termina como error.
	 * 
	 * @param elemento
	 * @return Element[]
	 */
	public Element[] obtenerTerminoLomes(Element elemento) {
		Element[] result = null;
		String swapTerminoLom = null;
		// Temporalmente, asigno el terminoLom a swapTerminoLom. Debe restaurarse al final de este metodo
		swapTerminoLom = terminoLom;
		
		if (EspecialTermTypes.OTRO.equals(tipoTermino)) {
			// Reasigno el terminoLom: quiero localizar el padre del que se desea añadir
			terminoLom = getTerminoLom().substring(0,
					getTerminoLom().lastIndexOf('.'));
			Element[] encontrados = super.obtenerTerminoLomes(elemento);
			result = encontrados;
		} else if (EspecialTermTypes.TAXONOMIA.equals(tipoTermino)) {
			if(logger.isDebugEnabled())
				logger.debug("Adicion de taxonomia: busco classification padre para insertar");
			// El padre del que se desea añadir es un classification:
			terminoLom = "lom.classification";
			Element[] padres = super.obtenerTerminoLomes(elemento);
			boolean insertar = true;
			// Aseguro que purpose tiene un valor
			String proposito = getPurpose()==null?"discipline":getPurpose();
			if(padres!=null && padres.length>0) {
				for(int i=0;i<padres.length && insertar;i++) {
					Element purpose = padres[i].getChild("purpose", Namespace.getNamespace(CambioLomes.LOMES_NS));
					if(purpose!=null) {
						Element value = purpose.getChild("value", Namespace.getNamespace(CambioLomes.LOMES_NS));
						if(value != null && proposito.equals(value.getValue())) {
							// Existe un classification con purpose=discipline.
							// Lo devuelvo como elemento padre
							if(logger.isDebugEnabled())
								logger.debug("Encontrado classification con purpose = " + proposito);
							insertar = false;
							result = new Element[]{padres[i]};
						}
					}
				}
			} 
			if(insertar) {
				/*
				 * No existe classification con purpose discipline. Lo inserto
				 */
				if(logger.isDebugEnabled()) logger.debug("Insertando un <classification> con purpose = " + proposito);
				// Parseo plantilla classification-disciplina.xml y la inserto en el lom
				InputStream is = this.getClass().getResourceAsStream("/plantillas/classification-disciplina.xml");
				String errMsg = "No se ha podido generar un classification para la taxonomia";
				if(is==null) {
					logger.fatal(errMsg);
					if(logger.isDebugEnabled()) logger.debug("No se encuentra la plantilla classification-disciplina");
				} else {
					try {
						SAXBuilder parser = new SAXBuilder();
						Document doc = parser.build(new InputSource(is));
						Element classification = doc.getRootElement();
						// Introduzco el proposito en la clasificacion parseada (C28)
						logger.debug("Introduzco el proposito en la clasificacion parseada, lo que devuelve en el classification es "+classification.getChildren().size());
						Element proposito1 = classification.getChild("purpose",Namespace.getNamespace(CambioLomes.LOMES_NS));
						logger.debug("Lo que nos devuelve el purpose "+proposito1.getChildText("value"));
						logger.debug("Recogemos el proposito");
						Element valorProp=proposito1.getChild("value",Namespace.getNamespace(CambioLomes.LOMES_NS));
						valorProp.setText(proposito);
						logger.debug("Hemos insertado el valor  "+valorProp.getText());
						
						
						
//						Element valorSource = proposito1.getChild("source");
//						Element valorProposito=proposito1.getChild("value");
//						
//						valorProposito.setText(proposito);
						logger.debug("Lo hemos insertado");
						/////////
						elemento.addContent(classification.detach());
						result = new Element[]{classification};
					} catch (Exception e) {
						logger.fatal(errMsg);
						if(logger.isDebugEnabled()) 
							logger.debug("Error de parseo de la plantilla classification-disciplina.xml",e);
					} finally {
						try {
							is.close();
						} catch (Exception e) {
							// Nada que hacer
						}
					}
				}
			}
		}
		terminoLom = swapTerminoLom;
		return result;
	}
}

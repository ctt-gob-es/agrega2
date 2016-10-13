package es.pode.empaquetador.presentacion;

import java.io.Serializable;

public class CatalogacionBean implements Serializable {
	private String identifier = null;
	private String href = null;
	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}
	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}
	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}
	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String toString() {
		return (new StringBuffer()).append("CatalogacionBean[identifier="+identifier+":href="+href+"]").toString();
	}
	
	public boolean equals(Object o) {
		boolean result = false;
		if (o instanceof CatalogacionBean) {
			CatalogacionBean cb = (CatalogacionBean) o;
			boolean hrefResult = false;
			boolean identifierResult = false;
			if(cb.getHref()==href || (href!=null && href.equals(cb.getHref()))) hrefResult=true;
			if(cb.getIdentifier()==identifier || (identifier!=null && identifier.equals(cb.getIdentifier()))) identifierResult=true;
			result = hrefResult && identifierResult;
		}
		return result;
	}
}

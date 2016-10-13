package es.pode.soporte.menu;

import org.apache.log4j.Logger;

public class MenuItem implements java.io.Serializable
{
	private static Logger logger = Logger.getLogger(MenuItem.class);
	private static final long serialVersionUID = 4307978396380378157L;
	private String id = "";
	private String link = null;
	private String linkConcatVar = null;
	private String target = null;
	private boolean selected = false;
	private String i18nKey = "";
	
	public MenuItem() 
	{
		super();
	}
	

	public String getLink() {
		return link;
	}
	public void setLink(String sLink) {
		if (sLink != null && !sLink.equals(""))
			this.link = sLink;
	}
	
	public void setRelativeLink(String sLink)
	{
		
		if (sLink != null && !sLink.equals(""))
		{
			// Si el link es una URL completa tiene http:// se deja como está.
//			String regexURL = new String ("^(http|https|ftp):\\/\\/.*");
			String regexURL = "^(http|https|ftp):\\/\\/.*";
			if (sLink.matches(regexURL))
					this.link = sLink;
			else if (sLink.indexOf('/') != 0)
				this.link = "/"+sLink;
			else 
				this.link = sLink;
		}
	}

	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setI18nKey(String i18nKey) {
		this.i18nKey = i18nKey;
	}

	public String getI18nKey() {
		return i18nKey;
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(id);
		sb.append("-");
		sb.append(link);
		sb.append("-");
		sb.append(selected);
		sb.append("-");
		sb.append(i18nKey);
		
		if(logger.isDebugEnabled())logger.debug(sb.toString());
		return sb.toString();
	}


	public String getTarget() {
		return target;
	}


	public void setTarget(String target) {
		this.target = target;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((i18nKey == null) ? 0 : i18nKey.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result
				+ ((linkConcatVar == null) ? 0 : linkConcatVar.hashCode());
		result = prime * result + (selected ? 1231 : 1237);
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MenuItem)) {
			return false;
		}
		MenuItem other = (MenuItem) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	


	public String getLinkConcatVar() {
		return linkConcatVar;
	}


	public void setLinkConcatVar(String linkConcatVar) {
		this.linkConcatVar = linkConcatVar;
	}
}

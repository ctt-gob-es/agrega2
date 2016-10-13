package es.pode.soporte.menu;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;


public class MenuItemXml implements java.io.Serializable 
{
	private static final long serialVersionUID = -1746096629201764287L;

	private static final String HASH_LINK = "#";

	private static final String TAG_MENU_ITEM_ID 		= MenuController.getPropertyValue("tag_id",MenuController.FILE_NAME_PROPERTIES);
	private static final String TAG_MENU_ITEM_NAME 		= MenuController.getPropertyValue("tag_name",MenuController.FILE_NAME_PROPERTIES);
	private static final String TAG_MENU_ITEM_PATTERN	= MenuController.getPropertyValue("tag_pattern",MenuController.FILE_NAME_PROPERTIES);
	private static final String TAG_MENU_ITEM_URL		= MenuController.getPropertyValue("tag_url",MenuController.FILE_NAME_PROPERTIES);
	private static final String TAG_MENU_ITEM_URL_CONCAT= MenuController.getPropertyValue("tag_url_concat",MenuController.FILE_NAME_PROPERTIES);
	private static final String TAG_MENU_ITEM_TARGET 	= MenuController.getPropertyValue("tag_target",MenuController.FILE_NAME_PROPERTIES);
	private static final String TAG_MENU_ITEM_I18NKEY 	= MenuController.getPropertyValue("tag_i18nKey",MenuController.FILE_NAME_PROPERTIES);
	private static final String TAG_MENU_ITEM_ROLES 	= MenuController.getPropertyValue("tag_roles",MenuController.FILE_NAME_PROPERTIES);

	private String id = null;
	private String name = null;
	private String pattern = null;
	private String link = MenuItemXml.HASH_LINK;
	private String linkConcatVar = null;
	private String target = null;
	private String i18nKey = null;
	private String[] roles = null;
	
	private static Logger logger = Logger.getLogger(MenuItemXml.class);
	
	public MenuItemXml( String i18nMessage, String[] arrayRoles, Attributes attributes) 
	{
		super();
		this.setI18nKey(i18nMessage);
		this.setRoles(arrayRoles);
		this.parsingAttributes(attributes);
	}
	
	protected void parsingAttributes(Attributes attributes)
	{
        for (int i = 0; i < attributes.getLength(); i++)
        {
        	if (attributes.getQName(i).equals(TAG_MENU_ITEM_ID))
        		this.setId(attributes.getValue(i));
        	else if (attributes.getQName(i).equals(TAG_MENU_ITEM_NAME))
        		this.setName(attributes.getValue(i));
        	else if (attributes.getQName(i).equals(TAG_MENU_ITEM_PATTERN))
        		this.setPattern(attributes.getValue(i));
        	else if (attributes.getQName(i).equals(TAG_MENU_ITEM_URL))
        		this.setLink(attributes.getValue(i));
        	else if (attributes.getQName(i).equals(TAG_MENU_ITEM_URL_CONCAT))
        		this.setLinkConcatVar(attributes.getValue(i));
        	else if (attributes.getQName(i).equals(TAG_MENU_ITEM_TARGET))
        		this.setTarget(attributes.getValue(i));
        	else if (attributes.getQName(i).equals(TAG_MENU_ITEM_I18NKEY))
        		this.setI18nKey(attributes.getValue(i));
        	else if (attributes.getQName(i).equals(TAG_MENU_ITEM_ROLES))
        		this.setRoles(attributes.getValue(i));
        }
	}

	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		if (isChangeable(link))
			this.link = link;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		if (isChangeable(id))
			this.id = id;
	}
	public void setI18nKey(String i18nKey) {
		if (isChangeable(i18nKey))
			this.i18nKey = i18nKey;
	}

	public String getI18nKey() {
		return i18nKey + this.getName();
	}
	
	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String sRoles) 
	{
		if (isChangeable(sRoles) && sRoles.indexOf(",")!= -1)
			this.setRoles(sRoles.split(","));
		else if (isChangeable(sRoles) )
		{
			String[] arrayRoles = new String[1];
			arrayRoles[0] = sRoles;
			this.setRoles(arrayRoles);
		}
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(id);
		sb.append("-");
		sb.append(link);
		sb.append("-");
		sb.append(target);
		sb.append("-");
		sb.append(i18nKey);
		
		if (logger.isDebugEnabled())
			logger.debug(sb.toString());
		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (isChangeable(name))
			this.name = name;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		if (isChangeable(pattern))
			this.pattern = pattern;
	}
	
	private boolean isChangeable(String campo)
	{
		return campo != null && !campo.equals("");
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		if (isChangeable(target))
			this.target = target;
	}

	public String getLinkConcatVar() {
		return linkConcatVar;
	}

	public void setLinkConcatVar(String linkConcatVar) {
		if (isChangeable(linkConcatVar))
			this.linkConcatVar = linkConcatVar;
	}
}

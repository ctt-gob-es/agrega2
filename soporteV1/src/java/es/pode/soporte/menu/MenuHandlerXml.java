package es.pode.soporte.menu;

import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MenuHandlerXml extends DefaultHandler
{
	private static final String TAG_MENU = MenuController.getPropertyValue("tag_menu",MenuController.FILE_NAME_PROPERTIES);
	private static final String TAG_MENU_ITEM = MenuController.getPropertyValue("tag_menu_item",MenuController.FILE_NAME_PROPERTIES);

	HashMap hashMenu = null;
	Menu menu = null;

	
	public void startElement(String nsURI, String localName, String qName, Attributes attributes) throws SAXException 
	{
	     if (qName.equalsIgnoreCase(MenuHandlerXml.TAG_MENU))
	    	menu = new Menu(attributes);
	     else if (qName.equalsIgnoreCase(MenuHandlerXml.TAG_MENU_ITEM))
    	 	menu.addMenuItem(new MenuItemXml(menu.getI18nKey(),menu.getRoles(),attributes));
	}
	
	public void endElement(String uri, String localName, String qName)throws SAXException
	{
		super.endElement(uri, localName, qName);
	     if (qName.equalsIgnoreCase(MenuHandlerXml.TAG_MENU))
	     {
	    	 if (this.getHashMenu()== null)
	    		 this.hashMenu = new HashMap();
	    	 this.hashMenu.put(menu.getName(), menu);
	     }
	}

	public HashMap getHashMenu() {
		return hashMenu;
	}

	public void setHashMenu(HashMap hashMenu) {
		this.hashMenu = hashMenu;
	}

	public Menu getMenu(String sKey) {
		return (Menu)this.getHashMenu().get(sKey);
	}

}

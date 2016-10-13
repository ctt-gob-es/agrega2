/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.pode.common.web.decorator;

import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;
import org.displaytag.decorator.ColumnDecorator;

/**
 * Simple column decorator which formats a date.
 * @author epesh
 * @version $Revision $ ($Author $)
 */
public class DateDecoratorMounthYear implements ColumnDecorator
{
    /**
     * FastDateFormat used to format the date object.
     */
    private FastDateFormat dateFormat = FastDateFormat.getInstance("MM/yyyy");

    /**
     * transform the given object into a String representation. The object is supposed to be a date.
     * @param columnValue Object
     * @return String
     */
    public final String decorate(Object columnValue)
    {
    	if (null != columnValue) 
    	{
    		Date date = (Date)columnValue;
	        return this.dateFormat.format(date);
    	}
    	else 
    	{
    		return "";
    	}
    }
}

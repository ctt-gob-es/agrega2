/*
Agrega2 es una federaci�n de repositorios de objetos digitales educativos formada por todas las Comunidades Aut�nomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.indra.isia.servicios;

import java.sql.Types;

import org.apache.log4j.Logger;
import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;


public class HsqlDataTypeFactory extends DefaultDataTypeFactory {
	private static Logger log = Logger.getLogger(HsqlDataTypeFactory.class);
	   
	   public DataType createDataType(int sqlType, String sqlTypeName)
	     throws DataTypeException
	   {
	      if (sqlType == Types.BOOLEAN)
	      {
	         return DataType.BOOLEAN;
	       }
	  
	      return super.createDataType(sqlType, sqlTypeName);
	    }

}

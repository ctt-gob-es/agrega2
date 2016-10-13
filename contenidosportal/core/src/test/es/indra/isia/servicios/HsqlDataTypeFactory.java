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

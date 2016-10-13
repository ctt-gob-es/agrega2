/**
 * 
 */
package es.pode.soporte.serializar;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

/**
 * @author mcmegia
 *
 */
public class SerializadorObjetos
{

	private static final String OBJECT_MIME_TYPE = "application/x-java-serialized-object";
	
	public DataHandler serializarObjeto(Object obj) throws IOException 
	{
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ObjectOutputStream os;
		byte[] bytes = null;
		os = new ObjectOutputStream(bs);
		os.writeObject(obj);
		os.close();
		bytes = bs.toByteArray();

		ByteArrayDataSource bads = new ByteArrayDataSource(bytes,OBJECT_MIME_TYPE);
		DataHandler dh = new DataHandler(bads);
		
		
		return dh;
		
	}
	
	public Object deserializarObjeto(DataHandler dh) throws IOException, ClassNotFoundException 
	{
		ByteArrayOutputStream bais = new ByteArrayOutputStream();
		dh.writeTo(bais);
			
		byte[] ob=bais.toByteArray();
		ByteArrayInputStream bs = new ByteArrayInputStream(ob);
		ObjectInputStream is = new ObjectInputStream(bs);
		Object salida = null;
		
		salida = is.readObject();
		
		is.close();
		
		return salida;
	}
	
}

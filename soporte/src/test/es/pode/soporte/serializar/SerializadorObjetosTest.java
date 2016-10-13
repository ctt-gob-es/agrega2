package es.pode.soporte.serializar;

import java.io.IOException;

import javax.activation.DataHandler;

import junit.framework.TestCase;

public class SerializadorObjetosTest extends TestCase
{

	public SerializadorObjetosTest(String arg0)
	{
		super(arg0);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	public void testSerializar() 
	{
		String seri=new String();
		SerializadorObjetos serializador=new SerializadorObjetos();
		try
		{
			DataHandler dh=serializador.serializarObjeto(seri);
			System.out.print("datahandler recuperado "+dh);
			
		} 
		catch (Exception e)
		{
				e.printStackTrace();
		}
		
	}
	public void testDeserializar() 
	{
		String seri="aaaaa";
		SerializadorObjetos serializador=new SerializadorObjetos();
		DataHandler dh=null;
		try
		{
			dh=serializador.serializarObjeto(seri);
			System.out.print("datahandler recuperado "+dh);
			
		} 
		catch (Exception e)
		{
				e.printStackTrace();
		}
		
		try
		{
			Object obj = serializador.deserializarObjeto(dh);
			assertTrue(obj instanceof String);
			assertEquals(obj,seri);
			
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}

}

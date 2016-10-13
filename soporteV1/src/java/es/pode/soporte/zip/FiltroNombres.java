package es.pode.soporte.zip;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;



public class FiltroNombres implements FilenameFilter
{
	List nombresExcluir; 
	
	public FiltroNombres(String[] nombresExcluir)
	{
		this.nombresExcluir = Arrays.asList( nombresExcluir);
	}
	
	public boolean accept(File dir, String name) 
	{
		return nombresExcluir.contains(name);
//		return false;
	}

}

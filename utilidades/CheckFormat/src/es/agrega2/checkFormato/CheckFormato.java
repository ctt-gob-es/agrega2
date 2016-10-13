/**
 * 
 */
package es.agrega2.checkFormato;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

/**
 * @author dgonzalezd
 *
 */
public class CheckFormato {
	
	private static String UNKNOWN_FORMAT_ARGV = "-1";
	private static String EMPTY_FORMAT_ARGV = "-2";
	private static String VERBOSE_ARGV = "-v";
	private static String HELP_ARGV = "-h";
	
	//Esta constante debe tener el mismo valor que la contante de mismo nombre
	//del SrvIndexadorServiceImpl.java
	private static String UNKNOWN_FORMAT = "unknown";

	
	private static void printHitInfo (Document doc, int numDoc) {
		System.out.println("===== Datos hit num "+numDoc+" =====");
		System.out.println("Titulo: '"+doc.get("title")+"'");
		System.out.println("Id: '"+doc.get("identificador")+"'");
		System.out.println("Formato: '"+doc.get("formato")+"'");
	}

	
	private static void printExtendedHelp () {
		System.out.println("Este programa cambia el formato de los ODEs de los indices de lucene.");
		System.out.println("Busca los ODEs que no tienen un formato estandar (imagen, audio, video, texto o aplicacion) y lo cambia a formato '"+ UNKNOWN_FORMAT + "' o a formato en blanco (vacio).");
		System.out.println("Como argumento obligatorio recibe el tipo de formato al que se quiere cambiar el formato de los objetos con formato no estandar:");
		System.out.println("--> '"+ UNKNOWN_FORMAT_ARGV +"' para cambiar a formato unknown");
		System.out.println("--> '"+ EMPTY_FORMAT_ARGV +"' para cambiar a formato vacio");
		System.out.println("Como ultimo argumento recibe el path donde se encuentra el indice a modificar.");
		System.out.println("Se puede pasar el flag '-v' para que se imprima mas informacion sobre los ODEs modificados.");
		System.out.println("El orden de los flags es indiferente.");
		
	}
	
	
	private static void printShortHelp (String mainName) {
		System.out.println("Usage 1: "+mainName+ " ["+VERBOSE_ARGV+"] new_format_value index_path");
		System.out.println("Usage 2: "+mainName+ " new_format_value ["+VERBOSE_ARGV+"] index_path");
		System.out.println("new_format_value: equal to '"+ UNKNOWN_FORMAT_ARGV +"' to change to 'unknown', or '"+ EMPTY_FORMAT_ARGV +"' to assign an empty value");
		System.out.println("Use '"+HELP_ARGV+"' for more info.");
	}
	

	@SuppressWarnings("deprecation")
	private static void nonStandarFormatSearchAndChange (String indexPath, String newFormat, boolean verboseModeEnabled) {

		//Comprobamos que hay índice
		IndexSearcher searcher=null;
		IndexWriter writer=null;
		
		try {
			searcher = new IndexSearcher(indexPath);
			//Construimos query
			QueryParser parser = new QueryParser("formato", new StandardAnalyzer(new String[0]));
			Query query=parser.parse("-(formato:image/* formato:audio/* formato:video/* formato:application/* formato:text/*) +(nivelAgregacion:1)");
			//Obtenemos lista ODEs
			Hits hits=searcher.search(query);
			System.out.println("Se han encontrado "+hits.length()+" hits");
			
			writer = new IndexWriter(indexPath, new StandardAnalyzer(new String[0]));
			for (int j = 0; j < hits.length(); j++) {
				Document doc = hits.doc(j);
				if(verboseModeEnabled==true) printHitInfo(doc, j);
				Field campo = doc.getField("formato");
				campo.setValue(newFormat);
				doc.add(campo);
				//Actualizamos ODEs
				writer.updateDocument(new Term("identificador",doc.get("identificador")), doc);
			}
			searcher.close();
			searcher=null;
			writer.close(true);
			writer=null;
			if(verboseModeEnabled==true) System.out.println("Fin sin errores");
			
		} catch (CorruptIndexException e) {
			System.out.println("El índice parece estar corrupto: "+e);
		} catch (IOException e) {
			System.out.println("No se ha encontrado el indice: "+e);
		} catch (ParseException e) {
			System.out.println("El formato de la query no es valido: "+e);
		} catch (Exception e) {
			System.out.println("Hubo un error: "+e);
		} finally {
			if(searcher!=null) {
				try {
					searcher.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar IndexSearcher");
				}
			}
			if(writer!=null) {
				try {
					writer.close(true);
				} catch (CorruptIndexException e) {
					System.out.println("Error al cerrar IndexWriter");
				} catch (IOException e) {
					System.out.println("Error al cerrar IndexWriter");
				}
			}
		}
	}
	
	
	private static boolean passedFlag (String[] args, String flag) {
		for (int i=0; i<args.length; i++)
			if (args[i].equals(flag)) return true;
		return false;
	}
	
	
	public static void formatUnknown2Empty (String indexPath, boolean verboseModeEnabled) {
		nonStandarFormatSearchAndChange(indexPath, "", verboseModeEnabled);
	}

	
	public static void formatEmpty2Unknown (String indexPath, boolean verboseModeEnabled) {
		nonStandarFormatSearchAndChange(indexPath, UNKNOWN_FORMAT, verboseModeEnabled);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		boolean modeEmpty2Unknown = passedFlag(args, UNKNOWN_FORMAT_ARGV);
		boolean modeUnknown2Empty = passedFlag(args, EMPTY_FORMAT_ARGV);
		boolean verboseModeEnabled = passedFlag(args, VERBOSE_ARGV);
		boolean showExtendedHelp = passedFlag(args, HELP_ARGV);
		
		if (args.length == 1 && showExtendedHelp==true)
			printExtendedHelp();
		else if (args.length > 1 && args.length < 4) { 	
			if (modeEmpty2Unknown==modeUnknown2Empty) printShortHelp("java -jar checkFormat.jar");
			else if (modeEmpty2Unknown==true) formatEmpty2Unknown(args[args.length-1], verboseModeEnabled);
			else if (modeUnknown2Empty==true) formatUnknown2Empty(args[args.length-1], verboseModeEnabled);
			else printShortHelp("java -jar checkFormat.jar");
		} else {
			printShortHelp("java -jar checkFormat.jar");
		}
	}

}

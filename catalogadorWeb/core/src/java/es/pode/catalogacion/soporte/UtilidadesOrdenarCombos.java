package es.pode.catalogacion.soporte;

import java.io.Serializable;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.fuentestaxonomicas.negocio.servicio.VocabularioVO;

public class UtilidadesOrdenarCombos implements Serializable {

		//Metodo que ordena los combos con terminosvo
		public TerminoVO[] ordenarTerminosVO(TerminoVO[] terminos, String idioma)
		{
			
			///////////////////////////////////////
			TerminoVO[] terminosSinTildes = new TerminoVO[terminos.length];
	    	
	    	//eliminamos las tildes para poder hacer la comparaci�n correctamente
	    	for (int k=0;k<terminos.length;k++){
	    		TerminoVO terminoSinTildes = new TerminoVO();
	    		String valorTer = terminos[k].getNomTermino();
	    		valorTer = valorTer.replace('�', 'a');
	    		valorTer = valorTer.replace('�', 'e');
	    		valorTer = valorTer.replace('�', 'i');
	    		valorTer = valorTer.replace('�', 'o');
	    		valorTer = valorTer.replace('�', 'u');
	    		valorTer = valorTer.replace('�', 'A');
	    		valorTer = valorTer.replace('�', 'E');
	    		valorTer = valorTer.replace('�', 'I');
	    		valorTer = valorTer.replace('�', 'O');
	    		valorTer = valorTer.replace('�', 'U');
	    		terminoSinTildes.setNomTermino(valorTer);
	    		terminoSinTildes.setIdTermino(terminos[k].getIdTermino());
	    		terminoSinTildes.setIdiomaTermino(terminos[k].getIdiomaTermino());
	    		terminosSinTildes[k]= terminoSinTildes;
	    	}
	    	
	    	for( int i=0;i<terminos.length-1;i++){                 
	    		for (int j=i+1;j<terminos.length;j++){
	    			String aux=null;
	    			String auxId=null;
	    			String aux2=null;
	    			String auxId2=null;
	    			
	    			String auxIdioma=null;
	    			String auxIdioma2=null;
	    			String valorTaxI = terminosSinTildes[i].getNomTermino();
	    			String valorTaxJ = terminosSinTildes[j].getNomTermino();
	    			
	    			//comparamos los texto sin tildes y ordenamos a la vez tanto el array con los taxones sin tildes
	    			//como el array con los taxones originales que sera el que vamos devolver
	    			if(valorTaxI.compareToIgnoreCase(valorTaxJ)>0){
	    				aux=terminosSinTildes[j].getNomTermino();
	    				terminosSinTildes[j].setNomTermino(terminosSinTildes[i].getNomTermino());
	    				terminosSinTildes[i].setNomTermino(aux);
	    				auxId=terminosSinTildes[j].getIdTermino();
	    				terminosSinTildes[j].setIdTermino(terminosSinTildes[i].getIdTermino());
	    				terminosSinTildes[i].setIdTermino(auxId);
	    				
	    				
	    				auxIdioma=terminosSinTildes[j].getIdiomaTermino();
	    				terminosSinTildes[j].setIdiomaTermino(terminosSinTildes[i].getIdiomaTermino());
	    				terminosSinTildes[i].setIdiomaTermino(auxIdioma);
	    				
	    				aux2=terminos[j].getNomTermino();
	    				terminos[j].setNomTermino(terminos[i].getNomTermino());
	    				terminos[i].setNomTermino(aux2);
	    				auxId2=terminos[j].getIdTermino();
	    				terminos[j].setIdTermino(terminos[i].getIdTermino());
	    				terminos[i].setIdTermino(auxId2);
	    				
	    				auxIdioma2=terminos[j].getIdiomaTermino();
	    				terminos[j].setIdiomaTermino(terminos[i].getIdiomaTermino());
	    				terminos[i].setIdiomaTermino(auxIdioma2);
	    			}	
	    		}
	    	}
	    	return terminos;
			
		}
		
		//Metodo que ordena los combos con vocabulariosvo
		public VocabularioVO[]  ordenarVocabulariosVO(VocabularioVO[]vocabulario) 
		{
			
			String idioma="";
			if(vocabulario.length>0)
				idioma=vocabulario[0].getIdioma();
	             
			for(int i=0; i < vocabulario.length; i++)
			{   
				for (int j= 0; j<vocabulario[i].getTerminos().length -1; j++){

			        for (int k= j+1; k<vocabulario[i].getTerminos().length; k++){
			        	
			             if (vocabulario[i].getTerminos()[j].getNomTermino().compareTo(vocabulario[i].getTerminos()[k].getNomTermino())>0 ){

			           		   TerminoVO auxTerm=new TerminoVO();

			                   String aux= vocabulario[i].getTerminos()[k].getNomTermino();
			                   String auxId=vocabulario[i].getTerminos()[k].getIdTermino();
			                   auxTerm.setIdiomaTermino(idioma);
			                   auxTerm.setIdTermino(auxId);
			                   auxTerm.setNomTermino(aux);
			                               
			                   vocabulario[i].getTerminos()[k]=vocabulario[i].getTerminos()[j];
			                   vocabulario[i].getTerminos()[j]=auxTerm;          
			             }
			        }
			    }
			}
			return vocabulario;
		}
	
		public static String obtenerAccion(HttpServletRequest request) 
		throws Exception
		{
			String accion="";
			String[] partes;
			for (Enumeration names = request.getParameterNames();accion.equals("") && names.hasMoreElements();)
		       {
		      	 String name = String.valueOf(names.nextElement());
		           if(name.startsWith("accion"))
		           {
		        	   partes= name.split("_");
			        	 if(partes.length>0){
			        		 StringBuilder anadidos=new StringBuilder(partes[1]);
			        		 for(int i=2;i<partes.length;i++){
			        			 anadidos.append("_"+partes[i]);
			        		 }
			        	 accion=anadidos.toString();
			        	 }
		        	}
			
		        }
			return accion;
		}

}



package es.pode.parseadorXML.lomes.lomesAgrega;

import java.util.ArrayList;

public class RelacionAgrega {

		private String tipo;
		private String catalogo;
		private String entradaId;
		private ArrayList descripciones;
		
		public String getTipo(){
			return tipo;
		}
		
		public void setTipo (String tipo){
			this.tipo = tipo;
		}
		
		public String getCatalogo(){
			return catalogo;
		}
		
		public void setCatalogo (String catalogo){
			this.catalogo = catalogo;
		}
		
		public String getEntradaId(){
			return entradaId;
		}
		
		public void setEntradaId(String entradaId){
			this.entradaId = entradaId;
		}
		
		public ArrayList getDescripciones() {
			return descripciones;
		}
		
		public void setDescripciones(ArrayList descripciones) {
			this.descripciones = descripciones;
		}
}

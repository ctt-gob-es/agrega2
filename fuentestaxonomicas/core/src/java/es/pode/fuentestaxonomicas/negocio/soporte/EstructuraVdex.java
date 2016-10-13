/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.fuentestaxonomicas.negocio.soporte;

import java.util.HashMap;

public class EstructuraVdex {

	String vocabName;
	String vocabIdentifier;
	HashMap<?, ?> hashMap;
	
	public EstructuraVdex(){
		vocabName = "";
		hashMap = new HashMap<Object, Object>();
	}
	public HashMap<?, ?> getHashMap() {
		return hashMap;
	}
	public void setHashMap(HashMap<?, ?> hashMap) {
		this.hashMap = hashMap;
	}
	public String getVocabName() {
		return vocabName;
	}
	public void setVocabName(String vocabName) {
		this.vocabName = vocabName;
	}
	public String getVocabIdentifier() {
		return vocabIdentifier;
	}
	public void setVocabIdentifier(String vocabIdentifier) {
		this.vocabIdentifier = vocabIdentifier;
	}
}

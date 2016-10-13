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

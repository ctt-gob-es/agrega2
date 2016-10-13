package es.pode.configuracion.buscarysustituir;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

public class SedTest {

	@Test
	public void testSustituirAgregaAtom() {
		assertTrue(Sed.sustituir(resource2File("agrega_atom_es.opml"), "${nodo}", "desarrollo"));
	}
	@Test
	public void testSustituirSearch() {
		assertTrue(Sed.sustituir(resource2File("searchPlugin.xml"), "${subdomain}", ""));
	}
	@Test
	public void testSustituirLDAP() {
		assertTrue(Sed.sustituir(resource2File("springldap.xml"), "${ldapurl}", "ldapserver"));
	}
	
	@Before
	public void reset() {
		File agregaAtom = new File(resource2File("agrega_atom_es.opml"));
		File agregaAtomBak = new File(resource2File("agrega_atom_es.opml.bak"));
		File search = new File(resource2File("searchPlugin.xml"));
		File searchBak = new File(resource2File("searchPlugin.xml.bak"));
		File ldap = new File(resource2File("springldap.xml"));
		File ldapBak = new File(resource2File("springldap.xml.bak"));
		
		if(agregaAtomBak.exists()) {
			agregaAtom.delete();
			agregaAtomBak.renameTo(agregaAtom);
		}
		if(searchBak.exists()) {
			search.delete();
			searchBak.renameTo(search);
		}
		if(ldapBak.exists()) {
			ldap.delete();
			ldapBak.renameTo(search);
		}
	}
	
	String resource2File(String fileName) {
		URL url = this.getClass().getResource("/"+fileName);
		return new File(url.getFile()).getAbsolutePath();
	}
	
}

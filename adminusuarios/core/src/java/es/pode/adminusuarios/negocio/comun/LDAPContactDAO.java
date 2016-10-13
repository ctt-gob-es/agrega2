package es.pode.adminusuarios.negocio.comun;

import org.apache.log4j.Logger;
import org.springframework.ldap.AttributesMapper;
import org.springframework.ldap.LdapTemplate;

import org.springframework.ldap.support.DistinguishedName;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;


public class LDAPContactDAO {
	
	private static Logger log = Logger.getLogger(LDAPContactDAO.class);
	
	private LdapTemplate ldapTemplate;

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

	public void setLdapTemplateProcomun(LdapTemplate ldapTemplateProcomun) {
		this.ldapTemplate = ldapTemplateProcomun;
	}
	
	
	public void insertUser(String user, String password, String nombreApellido) {
		BasicAttributes attrs = new BasicAttributes();
		BasicAttribute ocattr = new BasicAttribute("objectclass");
		ocattr.add("person");
		ocattr.add("top");
		attrs.put(ocattr);
		attrs.put("cn", user);
		attrs.put("sn", nombreApellido);
		attrs.put("userPassword", password);
		//attrs.put("mail", email);
		//attrs.put("employeeType", tipoEmpaquetador);
		//attrs.put("preferredLanguage", idioma);
		//attrs.put("initials", idiomaBusqueda);
		DistinguishedName newContactDN = new DistinguishedName();
		newContactDN.add("cn", user);
		ldapTemplate.bind(newContactDN, null, attrs);
	}
	
	
	public void modifyUser(String user, String password, String nombreApellido) {

		//DistinguishedName dn = new DistinguishedName("ou=usuarios");
		DistinguishedName dn = new DistinguishedName();
		dn.add("cn", user);
		/*
		ModificationItem[] mods = new ModificationItem[2];
		Attribute mod0 = new BasicAttribute("userPassword", password);
		Attribute mod1 = new BasicAttribute("sn", nombreApellido);
		mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod0);
		mods[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod1);
		*/
		ModificationItem[] mods = new ModificationItem[1];
		Attribute mod0 = new BasicAttribute("userPassword", password);
		mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, mod0);
		ldapTemplate.modifyAttributes(dn, mods);

	}
	
	
	public void deleteContact(String user) {
		DistinguishedName dn = new DistinguishedName();
		dn.add("cn", user);
		ldapTemplate.unbind(dn);
	}

	
	/**
	 * Devuelve la password encriptada del usuario
	 * @param user
	 * @return String password del usuario
	 */
	public String getUserHasedPasswd(String user) {

		Object oPass=ldapTemplate.lookup("cn="+user,
				new AttributesMapper() {
			public Object mapFromAttributes(Attributes attrs)
			throws NamingException {
				return attrs.get("userPassword").get();
			}
		});

		byte[] bPass = (byte[]) oPass;
		StringBuffer sbPass = new StringBuffer();
		
		for (int i = 0; i < bPass.length; i++) {
			sbPass.append(Character.toChars(bPass[i]));
		}
		return sbPass.toString();
	}

	


	private static class PersonAttributesMapper implements AttributesMapper {
		public Object mapFromAttributes(Attributes attrs) throws NamingException {
			Person person = new Person();
			person.setUsuario((String)attrs.get("cn").get());
			person.setNombreApellidos((String)attrs.get("sn").get());
			
			// LA password la devuelve como un array de bytes y hay que transformarla
			byte[] bPass = (byte[]) attrs.get("userPassword").get();
			StringBuffer sbPass = new StringBuffer();
			
			for (int i = 0; i < bPass.length; i++) {
				sbPass.append(Character.toChars(bPass[i]));
			}
						
			person.setHasedPasswd(sbPass.toString());
			return person;
		}
	}
	
	
	/**
	 * Devuelve todos los datos de un usuario
	 * @param user
	 * @return Person un objeto que contiene todos los datos de usuario
	 */
	public Person getUserData(String user) {

		Object oPass=ldapTemplate.lookup("cn="+user, new PersonAttributesMapper());
		
		return (Person)oPass;
	}

}

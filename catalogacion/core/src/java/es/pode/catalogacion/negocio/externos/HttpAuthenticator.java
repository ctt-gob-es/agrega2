/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.catalogacion.negocio.externos;

import java.net.PasswordAuthentication;

import java.net.Authenticator;



public class HttpAuthenticator extends Authenticator {
  private String user;
  private char[] pass;

  public HttpAuthenticator(String username, String password) {
    user = username;
    pass = password.toCharArray();
  }

  protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(user, pass);
  }


}
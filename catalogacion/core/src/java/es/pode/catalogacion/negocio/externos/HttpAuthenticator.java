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
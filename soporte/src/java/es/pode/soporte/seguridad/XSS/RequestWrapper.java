/**
 * This code is to avoid XSS security threat in spring application
 */

package es.pode.soporte.seguridad.XSS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;

public final class RequestWrapper extends HttpServletRequestWrapper {
	private static Logger logger = Logger.getLogger(RequestWrapper.class);
	public RequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}

	public String[] getParameterValues(String parameter) {
		logger.debug("In getParameterValues .. parameter .......");
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	public String getParameter(String parameter) {
		logger.debug("In getParameter .. parameter .......");
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		logger.debug("In getParameter RequestWrapper ........ value .......");
		return cleanXSS(value);
	}

	public String getHeader(String name) {
		logger.debug("In getHeader .. parameter .......");
		String value = super.getHeader(name);
		if (value == null)
			return null;
		logger.debug("In getHeader RequestWrapper ........... value ....");
		return cleanXSS(value);
	}

	private String cleanXSS(String value) {
		/*
		 * https://code.google.com/p/doctype-mirror/wiki/EsArticleXSS
		 */
		
		logger.debug("In cleanXSS RequestWrapper ..............." + value);
		// You'll need to remove the spaces from the html entities below
		//value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		//value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		//value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");

		//value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
		//value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
		//value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
		//value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
		//value = value.replaceAll("<script>", "");
		//value = value.replaceAll("</script>", "");
		

		//value = value.replaceAll("<script.*?>.*?<script.*?>", "");
		//value = value.replaceAll("<script.*?>.*?</script.*?>", "");

		/*
	  backspace      %08
      tab            %09
      linefeed       %0A
      creturn        %0D
      space          %20
      !              %21
      "              %22
      #              %23
      $              %24
      %              %25
      &              %26
      '              %27
      (              %28
      )              %29
      *              %2A
      +              %2B
      ,              %2C
      -              %2D
      .              %2E
      /              %2F
      0              %30
      1              %31
      2              %32
      3              %33
      4              %34
      5              %35
      6              %36
      7              %37
      8              %38
      9              %39
      :              %3A
      ;              %3B
      <              %3C
      =              %3D
      >              %3E
      ?              %3F
      @              %40
      A              %41
      B              %42
      C              %43
      D              %44
      E              %45
      F              %46
      G              %47
      H              %48
      I              %49
      J              %4A
      K              %4B
      L              %4C
      M              %4D
      N              %4E
      O              %4F
      P              %50
      Q              %51
      R              %52
      S              %53
      T              %54
      U              %55
      V              %56
      W              %57
      X              %58
      Y              %59
      Z              %5A
      [              %5B
      \              %5C
      ]              %5D
      ^              %5E
      _              %5F
      `              %60
      a              %61
      b              %62
      c              %63
      d              %64
      e              %65
      f              %66
      g              %67
      h              %68
      i              %69
      j              %6A
      k              %6B
      l              %6C
      m              %6D
      n              %6E
      o              %6F
      p              %70
      q              %71
      r              %72
      s              %73
      t              %74
      u              %75
      v              %76
      w              %77
      x              %78
      y              %79
      z              %7A
      {              %7B
      |              %7C
      }              %7D
      ~              %7E
		*/
				
		/*
		.   	Match any character (except newline)
		(?i) 	es case insensitive
		*      Match 0 or more times
		+      Match 1 or more times
		?      Match 1 or 0 times
		*/
		
		/*
		value = value.replaceAll("(?i)%3C.*?script.*?%3E.*?%3C.*?script.*?%3E", "");
		value = value.replaceAll("(?i)%3C.*?script.*?%3E.*?%3C.*?/.*?script.*?%3E", "");
		value = value.replaceAll("(?i)%3C.*?script.*?%3E", "");
		
		value = value.replaceAll("(?i)%3C.*?javascript:.*?%3E.*?%3C/.*?%3E", "");
		
		value = value.replaceAll("(?i)%3C.*?iframe.*?%3E.*?%3C.*?iframe.*?%3E", "");
		value = value.replaceAll("(?i)%3C.*?iframe.*?%3E.*?%3C.*?/.*?iframe.*?%3E", "");
		value = value.replaceAll("(?i)%3C.*?iframe.*?%3E", "");
		
		
		value = value.replaceAll("(?i)<.*?script.*?>.*?<.*?script.*?>", "");
		value = value.replaceAll("(?i)<.*?script.*?>.*?<.*?/.*?script.*?>", "");
		value = value.replaceAll("(?i)<.*?script.*?>", "");
		
		value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
		
		value = value.replaceAll("(?i)<.*?iframe.*?>.*?<.*?iframe.*?>", "");
		value = value.replaceAll("(?i)<.*?iframe.*?>.*?<.*?/.*?iframe.*?>", "");
		value = value.replaceAll("(?i)<.*?iframe.*?>", "");
		*/
		
		//Eliminamos cierres de comentarios HTML "'>
		value = value.replaceAll("\"'>", "");
		value = value.replaceAll("\"'%3E", "");
		value = value.replaceAll("\"%27%3E", "");
		value = value.replaceAll("%22%27%3E", "");

		//Eliminamos cierres de comentarios HTML -->
		value = value.replaceAll("-->", "");
		value = value.replaceAll("--%3E", "");
		value = value.replaceAll("%2D%2D%3E", "");
		value = value.replaceAll("%2D%2D>", "");
		
		
		value = value.replaceAll("(?i)<.*?script.*?", "");
		value = value.replaceAll("(?i)%3C.*?script.*?", "");
		
		value = value.replaceAll("(?i)<.*?javascript:.*?", "");
		value = value.replaceAll("(?i)%3C.*?javascript:.*?", "");
		
		value = value.replaceAll("(?i)<.*?iframe.*?", "");
		value = value.replaceAll("(?i)%3C.*?iframe.*?", "");
		
		value = value.replaceAll("(?i)<.*?img.*?", "");
		value = value.replaceAll("(?i)%3C.*?img.*?", "");
		
		
		//Eliminar cualquier tipo de tag HTML
		value = value.replaceAll("(?i)<.*>.*</.*>", "");
		value = value.replaceAll("(?i)<.*>.*<%2F.*>", "");
		value = value.replaceAll("(?i)%3C.*%3E.*%3C/.*%3E", "");
		value = value.replaceAll("(?i)%3C.*%3E.*%3C%2F.*%3E", "");
		
		value = value.replaceAll("(?i)<.*/>", "");
		value = value.replaceAll("(?i)<.*%2F>", "");
		value = value.replaceAll("(?i)%3C.*/%3E", "");
		value = value.replaceAll("(?i)%3C.*%2F%3E", "");
		
		
		//Eliminamos determinados caracteres que al ir acompañados por apertura y cierre de parentesis
		//son muy sospechosos de ser ataques
		
		//Eliminamos apariciones de : con ( y )
		value = value.replaceAll("(?i):.*\\(.*\\)", "");
		value = value.replaceAll("(?i):.*\\(.*%29", "");
		value = value.replaceAll("(?i):.*%28.*%29", "");
		value = value.replaceAll("(?i)%3A.*%28.*%29", "");
		value = value.replaceAll("(?i)%3A.*\\(.*\\)", "");
		
		//Eliminamos apariciones de = con ( y )
		value = value.replaceAll("(?i)=.*\\(.*\\)", "");
		value = value.replaceAll("(?i)=.*\\(.*%29", "");
		value = value.replaceAll("(?i)=.*%28.*%29", "");
		value = value.replaceAll("(?i)%3D.*%28.*%29", "");
		value = value.replaceAll("(?i)%3D.*\\(.*\\)", "");
		
		//Eliminamos apariciones de < con ( y )
		value = value.replaceAll("(?i)<.*\\(.*\\)", "");
		value = value.replaceAll("(?i)<.*\\(.*%29", "");
		value = value.replaceAll("(?i)<.*%28.*%29", "");
		value = value.replaceAll("(?i)%3C.*%28.*%29", "");
		value = value.replaceAll("(?i)%3C.*\\(.*\\)", "");
		
		logger.debug("Out cleanXSS RequestWrapper ........ value ......." + value);
		return value;
	}
}

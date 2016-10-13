package es.pode.modelo;

public class Localizador {
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = 8061600995640202621L;

    private java.lang.String path;

  /**
   * <p>
   * Se trata del path de disco en el que podemos localizar el ODE
   * identificado por el id.
   * </p>
   * @return java.lang.String
   */
    public java.lang.String getPath()
    {
        return this.path;
    }

  /**
   * <p>
   * Se trata del path de disco en el que podemos localizar el ODE
   * identificado por el id.
   * </p>
   *  @param path  Se trata del path de disco en el que podemos localizar el ODE identificado por el id. 
   */
    public void setPath(java.lang.String path)
    {
        this.path = path;
    }
    private java.lang.String url;

  /**
   * <p>
   * Path url donde se puede acceder remotamente al ODE identificado
   * por el id.
   * </p>
   * @return java.lang.String
   */
    public java.lang.String getUrl()
    {
        return this.url;
    }

  /**
   * <p>
   * Path url donde se puede acceder remotamente al ODE identificado
   * por el id.
   * </p>
   *  @param url  Path url donde se puede acceder remotamente al ODE identificado por el id. 
   */
    public void setUrl(java.lang.String url)
    {
        this.url = url;
    }
    private java.lang.String mec;

  /**
   * <p>
   * Se trata del identificador MEC que puede terner asociado el ODE
   * identificado con el UUID id.
   * </p>
   * <p>
   * Si el ODE al que hace referencia el identificador de esta tupla
   * es un ODE publicado, su identificador sera un numero MEC y
   * tendra este campo vacio.
   * </p>
   * @return java.lang.String
   */
    public java.lang.String getMec()
    {
        return this.mec;
    }

  /**
   * <p>
   * Se trata del identificador MEC que puede terner asociado el ODE
   * identificado con el UUID id.
   * </p>
   * <p>
   * Si el ODE al que hace referencia el identificador de esta tupla
   * es un ODE publicado, su identificador sera un numero MEC y
   * tendra este campo vacio.
   * </p>
   *  @param mec  Se trata del identificador MEC que puede terner asociado el ODE identificado con el UUID id.
 Si el ODE al que hace referencia el identificador de esta tupla es un ODE publicado, su identificador sera un numero MEC y tendra este campo vacio. 
   */
    public void setMec(java.lang.String mec)
    {
        this.mec = mec;
    }
    private java.lang.String identificador;

  /**
   * <p>
   * Se trata del identificador uuid o mec del ODE.
   * </p>
   * <p>
   * Es un valor unico para cada ODE.
   * </p>
   * @return java.lang.String
   */
    public java.lang.String getIdentificador()
    {
        return this.identificador;
    }

  /**
   * <p>
   * Se trata del identificador uuid o mec del ODE.
   * </p>
   * <p>
   * Es un valor unico para cada ODE.
   * </p>
   *  @param identificador  Se trata del identificador uuid o mec del ODE.
 Es un valor unico para cada ODE. 
   */
    public void setIdentificador(java.lang.String identificador)
    {
        this.identificador = identificador;
    }
    private java.lang.String idUsuario;

  /**
   * <p>
   * Identificador del usuario que crea la localizacion
   * </p>
   * @return java.lang.String
   */
    public java.lang.String getIdUsuario()
    {
        return this.idUsuario;
    }

  /**
   * <p>
   * Identificador del usuario que crea la localizacion
   * </p>
   *  @param idUsuario  Identificador del usuario que crea la localizacion 
   */
    public void setIdUsuario(java.lang.String idUsuario)
    {
        this.idUsuario = idUsuario;
    }
    private java.lang.Long consumoEspacio;

  /**
   * <p>
   * Es el espacio que ocupa el ODE en el disco
   * </p>
   * @return java.lang.Long
   */
    public java.lang.Long getConsumoEspacio()
    {
        return this.consumoEspacio;
    }

  /**
   * <p>
   * Es el espacio que ocupa el ODE en el disco
   * </p>
   *  @param consumoEspacio  Es el espacio que ocupa el ODE en el disco 
   */
    public void setConsumoEspacio(java.lang.Long consumoEspacio)
    {
        this.consumoEspacio = consumoEspacio;
    }
    private java.lang.Long id;

    public java.lang.Long getId()
    {
        return this.id;
    }

    public void setId(java.lang.Long id)
    {
        this.id = id;
    }


    /**
     * Returns <code>true</code> if the argument is an Localizador instance and all identifiers for this entity
     * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
     *
     * @param object The object to compare
     * @return boolean
     */
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Localizador))
        {
            return false;
        }
        final Localizador that = (Localizador)object;
        if (this.id == null || that.id == null || !this.id.equals(that.id))
        {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code based on this entity's identifiers.
     * @return int
     */
    public int hashCode()
    {
        int hashCode = 0;
        hashCode = 29 * hashCode + (id == null ? 0 : id.hashCode());

        return hashCode;
    }
}

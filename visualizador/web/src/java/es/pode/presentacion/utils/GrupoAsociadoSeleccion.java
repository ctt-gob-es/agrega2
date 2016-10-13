package es.pode.presentacion.utils;

import es.pode.adminusuarios.negocio.servicios.GrupoPublicoAdminVO;

public class GrupoAsociadoSeleccion {
	private GrupoPublicoAdminVO grupo;
	private boolean asociado;
	
	
	public GrupoAsociadoSeleccion() {}

	public GrupoAsociadoSeleccion(GrupoPublicoAdminVO grupo, boolean asociado) {
		super();
		this.grupo = grupo;
		this.asociado = asociado;
	}
	
	public GrupoPublicoAdminVO getGrupo() {
		return grupo;
	}
	public void setGrupo(GrupoPublicoAdminVO grupo) {
		this.grupo = grupo;
	}
	public boolean isAsociado() {
		return asociado;
	}
	public void setAsociado(boolean asociado) {
		this.asociado = asociado;
	}
	
	public String getNombre()
	{
		return grupo.getNombre();
	}
}

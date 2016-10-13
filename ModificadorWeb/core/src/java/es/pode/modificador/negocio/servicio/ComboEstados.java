package es.pode.modificador.negocio.servicio;



public class ComboEstados {
	private String  identificador;
	private String  valor;
	
	
	public static ComboEstados[] getInstance(String[] identificadores, String[] textos )
	{
		
		ComboEstados[] vuelta=new ComboEstados[identificadores.length];
		for(int i=0;i<identificadores.length;i++){
			
			ComboEstados combo=new ComboEstados();
			combo.setIdentificador(identificadores[i]);
			combo.setValor(textos[i]);
			vuelta[i]=combo;
		}
		return vuelta;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}

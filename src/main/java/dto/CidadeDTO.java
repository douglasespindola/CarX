package dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CidadeDTO implements Serializable {
	
	private String nome;
	private EstadoDTO estado;
	private String prefixoCep;
	
	public CidadeDTO(String nome, EstadoDTO estado, String prefixoCep) {
		this.nome = nome;
		this.estado = estado;
		this.prefixoCep = prefixoCep;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public EstadoDTO getEstado() {
		return estado;
	}
	public void setEstado(EstadoDTO estado) {
		this.estado = estado;
	}

	public String getPrefixoCep() {
		return prefixoCep;
	}

	public void setPrefixoCep(String prefixoCep) {
		this.prefixoCep = prefixoCep;
	}
	
	

}

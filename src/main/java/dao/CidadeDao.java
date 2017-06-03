package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import dto.CidadeDTO;
import dto.EstadoDTO;

@SuppressWarnings("serial")
@SessionScoped
public class CidadeDao implements Serializable {
	
	private List<CidadeDTO> listaCidades;
	
	public CidadeDao() {
		if (listaCidades == null) {
			listaCidades = new ArrayList<CidadeDTO>();
			listaCidades.add(new CidadeDTO("Florianópolis", new EstadoDTO("Santa Catarina", "SC"), "88"));
			listaCidades.add(new CidadeDTO("Curitiba", new EstadoDTO("Paraná", "PR"), "82"));
		}
	}

	public List<CidadeDTO> getListaCidades() {
		return listaCidades;
	}
	
	public CidadeDTO getCidadeByCep(String cep) {
		CidadeDTO retorno = null;
		for (CidadeDTO cidadeDTO : listaCidades) {
			if (cep.startsWith(cidadeDTO.getPrefixoCep())) {
				retorno = cidadeDTO;
				break;
			}
		}
		return retorno;
	}
	
	public void addCidadeByCep(CidadeDTO cidadeDTO) {
		listaCidades.add(cidadeDTO);
	}

}

package service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import dao.CidadeDao;
import dto.CidadeDTO;

@RequestScoped
public class CepService {

	@Inject
	private CidadeDao cidadeDao;
	
	public CidadeDTO getCidadeByCep(String cep) {
		return cidadeDao.getCidadeByCep(cep);
	}
	
	public void addCidadeByCep(CidadeDTO cidadeDTO) {
		cidadeDao.addCidadeByCep(cidadeDTO);
	}
	
}

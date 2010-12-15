package br.ufrj.spemarti.webservice;

import javax.ejb.Local;

import br.ufrj.spemarti.webservice.entity.VersionHistory;

@Local
public interface IVersionHistoryHandler {

	/**
	 * Verifica se existe um versionamento ativo para o nome em quest�o
	 * @param presentationName
	 * @return
	 */
	boolean verificaExistenciaVersionamentoAtivo(String presentationName);
	
	VersionHistory recuperaVersionHistoryAtivo(String presentationName);
	
}

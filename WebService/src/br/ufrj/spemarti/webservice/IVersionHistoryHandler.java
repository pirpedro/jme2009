package br.ufrj.spemarti.webservice;

import javax.ejb.Local;

import br.ufrj.spemarti.webservice.entity.VersionHistory;

@Local
public interface IVersionHistoryHandler {

	/**
	 * Verifica se existe um versionamento ativo para o nome em questão
	 * @param presentationName
	 * @return
	 */
	boolean verificaExistenciaVersionamentoAtivo(String presentationName);
	
	VersionHistory recuperaVersionHistoryAtivo(String presentationName);
	
	boolean verificaExistenciaVersionamentoAtivoArtifact(String presentationName, String filePath, String folder, String fileName);
	
	VersionHistory recuperaVersionHistoryAtivoArtifact(String presentationName, String filePath, String folder, String fileName);

		
	
}

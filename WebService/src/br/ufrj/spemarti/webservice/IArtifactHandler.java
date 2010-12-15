package br.ufrj.spemarti.webservice;

import javax.ejb.Local;

import br.ufrj.spemarti.webservice.entity.ArtifactDefinition;
import br.ufrj.spemarti.webservice.entity.FragmentDefinition;

@Local
public interface IArtifactHandler {
	
	/**
	 * Criação ou alteração de um artefato a nível global
	 * 
	 * @param fragment
	 * @param idUsuario
	 * @return
	 */
	ArtifactDefinition commit(ArtifactDefinition fragment, Integer idUsuario);
	
	/**
	 * 
	 * Criação/ alteração de um fragmento que faz parte de um artefato.
	 * 
	 * @param parent
	 * @param fragment
	 * @param idUsuario
	 * @return
	 */
	ArtifactDefinition commit(ArtifactDefinition parent, FragmentDefinition fragment, Integer idUsuario);
	
	/**
	 * Remove um artefato.
	 * 
	 * @param presentationName
	 * @param idUsuario
	 * @return
	 */
	boolean remove(String presentationName, Integer idUsuario);
	
	/**
	 * Remove um fragmento contido em um artefato.
	 * 
	 * @param parent
	 * @param presentationName
	 * @param idUsuario
	 * @return
	 */
	boolean remove(ArtifactDefinition parent, String presentationName, Integer idUsuario);

}

package br.ufrj.spemarti.webservice;

import javax.ejb.Local;

import br.ufrj.spemarti.webservice.entity.FragmentDefinition;

@Local
public interface IFragmentHandler {

	/**
	 * Cria��o ou altera��o de um fragmento a n�vel global
	 * 
	 * @param fragment
	 * @param idUsuario
	 * @return
	 */
	FragmentDefinition commit(FragmentDefinition fragment, Integer idUsuario);
	
	/**
	 * 
	 * Cria��o/ altera��o de um fragmento que faz parte de um ComplexInformationElement.
	 * 
	 * @param parent
	 * @param fragment
	 * @param idUsuario
	 * @return
	 */
	FragmentDefinition commit(FragmentDefinition parent, FragmentDefinition fragment, Integer idUsuario);
	
	/**
	 * Remove um fragmento a n�vel global.
	 * 
	 * @param presentationName
	 * @param idUsuario
	 * @return
	 */
	boolean remove(FragmentDefinition fragment, Integer idUsuario);
	
	/**
	 * Remove um fragmento contido em outro fragmento.
	 * 
	 * @param parent
	 * @param presentationName
	 * @param idUsuario
	 * @return
	 */
	boolean remove(FragmentDefinition parent, String presentationName, Integer idUsuario);
	
	
}

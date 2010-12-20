package br.ufrj.spemarti.webservice;

import javax.ejb.Local;

import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.Matrix;

@Local
public interface IMatrixHandler {

	boolean associate(Matrix parent, FragmentDefinition fragment);
}

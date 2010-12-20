package br.ufrj.spemarti.webservice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.ufrj.spemarti.webservice.entity.Version;

/**
 * Webservices nao aceitam java.util.List como parametro
 * @author Pedro
 *
 */
public class VersionedGroup implements Serializable{

	private static final long serialVersionUID = -4959226134058446807L;
	private List<Version> listaVersoes = new ArrayList<Version>();

	public void setListaVersoes(List<Version> listaVersoes) {
		this.listaVersoes = listaVersoes;
	}

	public List<Version> getListaVersoes() {
		return listaVersoes;
	}
	
	
	
}

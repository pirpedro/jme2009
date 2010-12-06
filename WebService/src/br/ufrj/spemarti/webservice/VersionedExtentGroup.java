package br.ufrj.spemarti.webservice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Webservices nao aceitam java.util.List como parametro
 * @author Pedro
 *
 */
public class VersionedExtentGroup implements Serializable{

	private static final long serialVersionUID = -4959226134058446807L;
	private List<VersionedExtent> versionedExtent = new ArrayList<VersionedExtent>();

	public void setVersionedExtent(List<VersionedExtent> versionedExtent) {
		this.versionedExtent = versionedExtent;
	}

	public List<VersionedExtent> getVersionedExtent() {
		return versionedExtent;
	}
	
	
	
}

package br.ufrj.spemarti.webservice.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
@NamedQueries({
	@NamedQuery(name="VersionHistory.recuperarPorVersao",
				query="SELECT v.versionHistory FROM Version v WHERE v.id=:id")
	
})
public class VersionHistory implements Serializable{

	private static final long serialVersionUID = 5551385429644478396L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="versionHistory")
	private List<Version> versions = new ArrayList<Version>();
	
	@OneToOne(cascade=CascadeType.ALL)
	private Version rootVersion;
	
	@Column
	private String fileName;
	
	@Column
	private String filePath;
	
	@Column
	private String folder;
	
	@Column
	private Boolean isDeleted;
	
	public void delete(Integer versionId){
		List<Version> newVersions = new ArrayList<Version>();
		
		for(Version v : getVersions()){
			if(!v.getId().equals(versionId)){
				newVersions.add(v);
			}
		}
	}
	
	public void delete(Version version){
		getVersions().remove(version);
	}
	
	public Version lookupByLabel(String label){
		for(Version v : getVersions()){
			if(v.getId().equals(label)){
				return v;
			}
		}
		
		return null;
	}
	
	public Version lookupByRevision(Integer versionId){
		for(Version v : getVersions()){
			if(v.getId().equals(versionId)){
				return v;
			}
		}
		
		return null;
	}
	
	public Version newVersion(SimpleInformationElement sie){
		return null;
	}
	
	public void newVersion(FileElement fe){
		getVersions().add(fe);
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}

	public List<Version> getVersions() {
		return versions;
	}

	public void setRootVersion(Version rootVersion) {
		this.rootVersion = rootVersion;
	}

	public Version getRootVersion() {
		return rootVersion;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!getClass().equals(obj.getClass()))
			return false;
		
		VersionHistory other = (VersionHistory) obj;
		if (this.getId() == null) {
			return false;
		} else {
			return getId().equals(other.getId());
		}
	}
	
	@Override
	public int hashCode() {
		return this.getId();
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getFolder() {
		return folder;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}
	
}

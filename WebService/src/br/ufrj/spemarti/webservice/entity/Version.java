package br.ufrj.spemarti.webservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.ForceDiscriminator;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Vrsn_In_Tipo", discriminatorType = DiscriminatorType.INTEGER)
@ForceDiscriminator
@NamedQueries({
	@NamedQuery(name="Version.recuperarRootPorPath",
				query="SELECT vh.rootVersion FROM VersionHistory vh WHERE vh.filePath=:path " +
						"AND vh.isDeleted = false"),
	@NamedQuery(name="Version.recuperarRootPorPathFolderFileName",
				query="SELECT vh.rootVersion FROM VersionHistory vh WHERE vh.filePath=:path " +
				"AND vh.folder=:folder AND vh.fileName=:fileName AND vh.isDeleted = false")
})
public abstract class Version implements Serializable{

	private static final long serialVersionUID = -7366416390388527097L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String labelVersion;
	
	@Column 
	private Integer revision;
	
	@Column
	private Date creationDate;
	
	@Column
	private String annotation;
	
	@Column
	private Version previousVersion;
	
	@OneToOne
	private Version nextVersion;
	
	@ManyToOne
	private VersionHistory versionHistory;
	
	@ManyToOne
	private User user;
	
	@Transient
	private Integer rootVersionId;
	
	
	
	public void addLabel(String label){
		this.labelVersion = label;
	}
	
	public void removeLabel(){
		this.labelVersion=null;
	}
	
	

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setLabelVersion(String labelVersion) {
		this.labelVersion = labelVersion;
	}

	public String getLabelVersion() {
		return labelVersion;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setNextVersion(Version nextVersion) {
		this.nextVersion = nextVersion;
	}

	public Version getNextVersion() {
		return nextVersion;
	}

	public void setPreviousVersion(Version previousVersion) {
		this.previousVersion = previousVersion;
	}

	public Version getPreviousVersion() {
		return previousVersion;
	}

		public void setVersionHistory(VersionHistory versionHistory) {
		this.versionHistory = versionHistory;
	}

	public VersionHistory getVersionHistory() {
		return versionHistory;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setRevision(Integer revision) {
		this.revision = revision;
	}

	public Integer getRevision() {
		return revision;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!getClass().equals(obj.getClass()))
			return false;
		
		Version other = (Version) obj;
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

	public void setRootVersionId(Integer rootVersionId) {
		this.rootVersionId = rootVersionId;
	}

	public Integer getRootVersionId() {
		return rootVersionId;
	}

}

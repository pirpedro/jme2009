package br.ufrj.spemarti.client;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.ufrj.spemarti.webservice.entity.ArtifactDefinition;
import br.ufrj.spemarti.webservice.entity.ArtifactFragment_Relationship;
import br.ufrj.spemarti.webservice.entity.ArtifactKinds;
import br.ufrj.spemarti.webservice.entity.Diagram;
import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.Image;


public class Client {
    public static void main(String[] args) throws Exception {
    	
    /*	Context jndiContext = getInitialContext();
    	Object ref = jndiContext.lookup("SvnBean/remote");
    	Svn dao = (Svn)PortableRemoteObject.narrow(ref, Svn.class);*/
    	
       
       Workspace workspace = new Workspace();
       workspace.createUser("pedro", "pedro");
       
       ArtifactDefinition artifact = new ArtifactDefinition();
       artifact.setaKind(ArtifactKinds.DIAGRAM);
       artifact.setPresentationName("artefatoTeste");
       geraRelacionamento(artifact, geraNovoDiagrama("DiagramaTeste"));
       workspace.checkIn(artifact, "/", "projeto", "artefatoImagem");
       
    	
    /*	System.out.println("Starting Test Client");
        URL url = new URL("http://localhost:8080/svn/SvnBean?wsdl");
        QName qname = new QName(
        		"http://webservice.spemarti.ufrj.br/",
                        "SvnBeanService");

        System.out.println("Creating a service Using: \n\t" 
                                   + url + " \n\tand " + qname);
        ServiceFactoryImpl factory = new ServiceFactoryImpl();
        Service remote = factory.createService(url, qname);

        System.out.println("Obtaining reference to a proxy object");
        Svn proxy = (Svn) remote.getPort(Svn.class);
        System.out.println("Accessed local proxy: " + proxy);
       
        String string = "Foca";
        System.out.println("Sending: " + string);
        
        System.out.println("Receiving: " + proxy.echo(string));
        
       SimpleInformationElement sie = new Image();
        sie.setId(24);
        proxy.checkIn(sie, "hahaha"); */
    }
    
    public static Diagram geraNovoDiagrama(String presentationName){
    	Diagram sieDiagram = new Diagram();
    	sieDiagram.addLabel(presentationName);
    	sieDiagram.setPresentationName(presentationName);
    	for(int i =0; i<3; i++){
    		Image sieImagem = new Image();
    		sieImagem.addLabel(presentationName +"::imagem::"+ i);
    		sieImagem.setPresentationName(presentationName +":imagem::"+ i);
    		sieDiagram.getListaImagem().add(sieImagem);
    	}
    	
    	return sieDiagram;
    }
    
    public static void geraRelacionamento(ArtifactDefinition artefato, FragmentDefinition fragment){
    	ArtifactFragment_Relationship rel = new ArtifactFragment_Relationship();
    	rel.setArtifact(artefato);
    	rel.getContainers().add(fragment);
    	artefato.getArtifactFragment().add(rel);
    }
    
    
    public static Context getInitialContext() throws NamingException{
    	Properties properties = new Properties();
    	properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
    	properties.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
    	properties.put(Context.PROVIDER_URL, "jnp://localhost:1099");
    	return new InitialContext(properties);
    
    }
}

package br.ufrj.spemarti.client;
import java.util.ArrayList;
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
import br.ufrj.spemarti.webservice.entity.LabeledText;
import br.ufrj.spemarti.webservice.entity.List;
import br.ufrj.spemarti.webservice.entity.Matrix;
import br.ufrj.spemarti.webservice.entity.Question;
import br.ufrj.spemarti.webservice.entity.SimpleInformationElement;
import br.ufrj.spemarti.webservice.entity.Text;
import br.ufrj.spemarti.webservice.entity.TextType;


public class Client {
    public static void main(String[] args) throws Exception {
    	
    /*	Context jndiContext = getInitialContext();
    	Object ref = jndiContext.lookup("SvnBean/remote");
    	Svn dao = (Svn)PortableRemoteObject.narrow(ref, Svn.class);*/
    	
       
       Workspace workspace = new Workspace();
    //   workspace.createUser("pedro", "pedro");
       
    //  ArtifactDefinition artifact = new ArtifactDefinition();
     // artifact.setaKind(ArtifactKinds.DIAGRAM);
     //  artifact.setPresentationName("artefatoTeste");
     //  java.util.List<SimpleInformationElement> listaElemento = new ArrayList<SimpleInformationElement>();
      // listaElemento.add(geraQuestion("questão"));
      // listaElemento.add(geraNovoDiagrama("diagrama"));
    //  listaElemento.add(geraNovoDiagrama("diagrama"));
    //  java.util.List<SimpleInformationElement> listaLinhas = new ArrayList<SimpleInformationElement>();
      
    //  listaLinhas.add(geraQuestion("questão"));
    //  java.util.List<List> listas = new ArrayList<List>();
   //   listas.add(geraNovaLista("listaLinha", listaLinhas));
   //   
      // geraRelacionamento(artifact, geraNovaLista("lista", listaElemento));
       
   //    geraRelacionamento(artifact, geraNovaMatriz("matriz", geraNovaLista("listaCabecalho", listaElemento), listas));
  //   geraRelacionamento(artifact, geraNovoDiagrama("diagrama"));
       //geraRelacionamento(artifact, geraQuestion("questão"));
    //   workspace.checkIn(artifact, "/", "projeto", "artefatoImagem");
       
    	
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
    
    public static List geraNovaLista(String presentationName, java.util.List<SimpleInformationElement> listaElemento){
    	List list = new List();
    	list.setPresentationName(presentationName);
    	list.setLabelVersion(presentationName);
    	
    	list.getContents().addAll(listaElemento);
    	
    	return list;
    	
    }
    
    public static Question geraQuestion(String presentationName){
    	Question question = new Question();
    	question.setPresentationName(presentationName);
    	for(int i=0 ; i< 2; i++){
    		Text ask = new Text();
    		ask.addLabel(presentationName +"::ask::"+ i);
    		ask.setPresentationName(presentationName +"::ask::"+ i);
    		ask.setTextType(TextType.ASK);
    		ask.setValue("ask " + i);
    		LabeledText answer = new LabeledText();
    		answer.addLabel(presentationName +"::answer::"+ i);
    		answer.setPresentationName(presentationName +"::answer::"+ i);
    		answer.setTextType(TextType.ANSWER);
    		answer.setValue("answer "+ i);
    		answer.setLabel("label "+ i);
    		question.getTexts().add(ask);
    		question.getTexts().add(answer);
    	}
    	
    	return question;
    }
    
    public static Matrix geraNovaMatriz(String presentationName, List cabecalho, java.util.List<List> linhas){
    	Matrix matrix = new Matrix();
    	matrix.setPresentationName(presentationName);
    	matrix.setLabelVersion(presentationName);
    	matrix.setHeader(cabecalho);
    	for(List lista: linhas){
    		matrix.getLines().add(lista);
    	}
    	
    	return matrix;
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

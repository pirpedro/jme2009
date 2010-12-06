package br.ufrj.spemarti.client;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import br.ufrj.spemarti.webservice.Svn;
import br.ufrj.spemarti.webservice.entity.Diagram;
import br.ufrj.spemarti.webservice.entity.Image;
import br.ufrj.spemarti.webservice.entity.SimpleInformationElement;


public class Client {
    public static void main(String[] args) throws Exception {
    	
    	Context jndiContext = getInitialContext();
    	Object ref = jndiContext.lookup("SvnBean/remote");
    	Svn dao = (Svn)PortableRemoteObject.narrow(ref, Svn.class);
    	
    	SimpleInformationElement sie = geraNovoDiagrama();
    	
    	dao.checkIn(sie);
    	
    	
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
    
    public static Diagram geraNovoDiagrama(){
    	Diagram sieDiagram = new Diagram();
    	sieDiagram.addLabel("Diagrama");
    	for(int i =0; i<3; i++){
    		Image sieImagem = new Image();
    		sieImagem.addLabel("Imagem"+ i);
    		sieDiagram.getListaImagem().add(sieImagem);
    	}
    	
    	return sieDiagram;
    }
    
    public static Context getInitialContext() throws NamingException{
    	Properties properties = new Properties();
    	properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
    	properties.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
    	properties.put(Context.PROVIDER_URL, "jnp://localhost:1099");
    	return new InitialContext(properties);
    
    }
}

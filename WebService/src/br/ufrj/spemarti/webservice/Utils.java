package br.ufrj.spemarti.webservice;

import br.ufrj.spemarti.webservice.entity.ArtifactDefinition;
import br.ufrj.spemarti.webservice.entity.ContainerDefinition;
import br.ufrj.spemarti.webservice.entity.Diagram;
import br.ufrj.spemarti.webservice.entity.ElementGroup;
import br.ufrj.spemarti.webservice.entity.Image;
import br.ufrj.spemarti.webservice.entity.LabeledText;
import br.ufrj.spemarti.webservice.entity.List;
import br.ufrj.spemarti.webservice.entity.Matrix;
import br.ufrj.spemarti.webservice.entity.Question;
import br.ufrj.spemarti.webservice.entity.Table;
import br.ufrj.spemarti.webservice.entity.Text;

public class Utils {

	public static boolean isComplexInformationInstance(Object obj){
		
		return (obj instanceof Diagram) 
				|| (obj instanceof Matrix) 
				|| (obj instanceof Table)
				|| (obj instanceof List)
				|| (obj instanceof ElementGroup)
				|| (obj instanceof Question);
	}
	
	public static boolean isSimpleInformationInstance(Object obj){
		return (obj instanceof Image)
				|| (obj instanceof Text)
				|| (obj instanceof LabeledText);
	}
	
	public static boolean isFragmentInstance(Object obj){
		return isComplexInformationInstance(obj) || isSimpleInformationInstance(obj);
	}
	
	public static boolean isArtifactInstance(Object obj){
		return (obj instanceof ArtifactDefinition);
	}
	
	public static boolean isContainerInstance(Object obj){
		return (obj instanceof ContainerDefinition);
	}
	
}

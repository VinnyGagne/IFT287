package partie2;

import java.util.LinkedList;
import java.util.List;

import javax.json.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tp1.IFT287Exception;

public class SystemBody
{
    private String name;
    private int id;
    private String type;
    private List<Flow> flows;

    public SystemBody()
    {
        flows = new LinkedList<Flow>();
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public void ajoutFlow(Flow f)
    {
        flows.add(f);
    }
    
    
    public JsonObjectBuilder toJson()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("name", name);
        builder.add("id", id);
        builder.add("type", type);
        JsonArrayBuilder bFlows = Json.createArrayBuilder();
        for(Flow f : flows)
            bFlows.add(f.toJson());
        builder.add("Flows", bFlows);
        return builder;
    }
    
    public Element toXML(Document document)
    {
        //Creation de l'element
        Element system = document.createElement("System");
        //Ajout des attributs
        system.setAttribute("name", name);
        system.setAttribute("id", Integer.toString(id));     
        system.setAttribute("type", type);
        
        //Creation de flows
        Element flows = document.createElement("Flows");
        system.appendChild(flows);
        //Ajout de chaque flow
        for(Flow f : this.flows)
            flows.appendChild(f.toXML(document));
        
        return system;
    }
    
}

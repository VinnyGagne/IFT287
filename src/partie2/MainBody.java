package partie2;

import java.util.LinkedList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.w3c.dom.Element;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;

import partie2.SystemBody;
import partie2.Organ;


public class MainBody
{
    private String bodyName;
    private int bodyID;
    private List<SystemBody> systems;
    private List<Organ> organs;
    
    public MainBody()
    {
        systems = new LinkedList<SystemBody>();
        organs = new LinkedList<Organ>();
    }
    
    public void ajoutSystem(SystemBody s)
    {
        systems.add(s);
    }
    
    public void ajoutOrgan(Organ o)
    {
        organs.add(o);
    }
    
    public String getBodyName()
    {
        return bodyName;
    }
    
    public void setBodyName(String bodyName)
    {
        this.bodyName = bodyName;
    }
    
    public int getBodyID()
    {
        return bodyID;
    }
    
    public void setBodyID(int bodyID)
    {
        this.bodyID = bodyID;
    }
    
    public JsonObjectBuilder toJson()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("bodyName", bodyName);
        builder.add("bodyID", bodyID);
        JsonArrayBuilder bSystems = Json.createArrayBuilder();
        for(SystemBody s : systems)
            bSystems.add(s.toJson());
        builder.add("Systems", bSystems);
        JsonArrayBuilder bOrgans = Json.createArrayBuilder();
        for(Organ o : organs)
            bOrgans.add(o.toJson());
        builder.add("Organs", bOrgans);
        return builder;
    }
    
    public Element toXML(Document document)
    {
        //Creation de l'element
        Element mainBody = document.createElement("MainBody");
        //Ajout au document
        document.appendChild(mainBody);
        //Ajout des attributs
        mainBody.setAttribute("bodyName", getBodyName());
        mainBody.setAttribute("bodyID", Integer.toString(getBodyID()));        
        
      //Creation de systems
        Element systems = document.createElement("Systems");
        mainBody.appendChild(systems);
        //Ajout de chaque system
        for(SystemBody s : this.systems)
            systems.appendChild(s.toXML(document));
        
        //Creation de organs
        Element organs = document.createElement("Organs");
        mainBody.appendChild(organs);
        //Ajout de chaque organ
        for(Organ o : this.organs)
            organs.appendChild(o.toXML(document));
        
        return mainBody;
    }
}

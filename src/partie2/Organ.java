package partie2;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import tp1.IFT287Exception;

public class Organ
{
    private String name;
    private int id;
    private int systemID;

    public Organ()
    {            
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
    
    public int getSystemID(){
        return systemID;
    }
    
    public void setSystemID(int systemID){
        this.systemID = systemID;
    }
    
    public JsonObjectBuilder toJson()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("name", name);
        builder.add("id", id);
        builder.add("systemID", systemID);
        return builder;
    }

    public Element toXML(Document document)
    {
        //Creation de l'element
        Element organ = document.createElement("Organ");
        //Ajout des attributs
        organ.setAttribute("name", name);
        organ.setAttribute("id", Integer.toString(id));     
        organ.setAttribute("systemID", Integer.toString(systemID));        
        return organ;
    }
    
    
}

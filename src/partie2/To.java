package partie2;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import tp1.IFT287Exception;

public class To
{
    private int id;
    
    public To()
    {        
    }
    
    public int getId(int id)
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public JsonObjectBuilder toJson()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("id", id);        
        return builder;
    }
    
    public Element toXML(Document document)
    {
        //Creation de l'element
        Element to = document.createElement("to");
        //Ajout des attributs
        to.setAttribute("id", Integer.toString(id));   

        return to;
    }
    
}

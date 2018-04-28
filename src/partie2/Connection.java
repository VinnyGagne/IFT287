package partie2;

import java.util.LinkedList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tp1.IFT287Exception;

public class Connection
{
    private int id;
    private List<To> to;
    
    public Connection()
    {
        to = new LinkedList<To>(); 
    }
    
    public int getId(int id)
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void ajoutTo(To t)
    {
        to.add(t);
    }
    
    public JsonObjectBuilder toJson()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("id", id); 
        JsonArrayBuilder bTo = Json.createArrayBuilder();
        for(To t : to)
            bTo.add(t.toJson());
        builder.add("To", bTo);
        return builder;
    }
    
    public Element toXML(Document document)
    {
        //Creation de l'element
        Element connection = document.createElement("Connection");
        //Ajout des attributs
        connection.setAttribute("id", Integer.toString(id));   
        
        //Ajout de chaque to
        for(To t : this.to)
            connection.appendChild(t.toXML(document));
        
        return connection;
    }
}

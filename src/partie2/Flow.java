package partie2;

import java.util.LinkedList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tp1.IFT287Exception;

public class Flow
{
    
    private int id;
    private String name;
    private List<Connectible> connectibles;
    private List<Connection> connections;

    public Flow()
    {
        connectibles = new LinkedList<Connectible>();
        connections = new LinkedList<Connection>();
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void ajoutConnectible(Connectible c)
    {
        connectibles.add(c);
    }
    public void ajoutConnection(Connection c)
    {
        connections.add(c);
    }
    
    public JsonObjectBuilder toJson()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("id", id);
        builder.add("name", name);
        JsonArrayBuilder bConnectibles = Json.createArrayBuilder();
        for(Connectible c : connectibles)
            bConnectibles.add(c.toJson());
        builder.add("Connectible", bConnectibles);
        JsonArrayBuilder bConnections = Json.createArrayBuilder();
        for(Connection c : connections)
            bConnections.add(c.toJson());
        builder.add("Connections", bConnections);
        return builder;
    }
    
    public Element toXML(Document document)
    {
        //Creation de l'element
        Element flow = document.createElement("Flow");
        //Ajout des attributs
        flow.setAttribute("id", Integer.toString(id));
        flow.setAttribute("name", name);     
        
        //Creation de connectible
        Element connectible = document.createElement("Connectible");
        flow.appendChild(connectible);
        //Ajout de chaque flow
        for(Connectible c : this.connectibles)
            connectible.appendChild(c.toXML(document));
        
        //Creation de connectible
        Element connections = document.createElement("Connections");
        flow.appendChild(connections);
        //Ajout de chaque flow
        for(Connection c : this.connections)
            connections.appendChild(c.toXML(document));
        
        return flow;
    }

}

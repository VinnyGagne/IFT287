package partie2;

import javax.json.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tp1.IFT287Exception;

public class Connectible
{
    private String name;
    private int id;
    private String volume;
    private boolean volumeNull;
    private String length;
    private boolean lengthNull;
    private String startRadius;
    private boolean startRadiusNull;
    private String endRadius;
    private boolean endRadiusNull;

    public Connectible()
    {
        volumeNull = true;
        lengthNull = true;
        startRadiusNull = true;
        endRadiusNull = true;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getVolume()
    {
        return volume;
    }

    public void setVolume(String volume)
    {
        this.volume = volume;
        this.volumeNull = false;
    }

    public String getLength()
    {
        return length;
    }

    public void setLength(String length)
    {
        this.length = length;
        lengthNull = false;
    }
    
    public String getStartRadius()
    {
        return startRadius;
    }

    public void setStartRadius(String startRadius)
    {
        this.startRadius = startRadius;
        startRadiusNull = false;
    }
    
    public String getEndRadius()
    {
        return endRadius;
    }

    public void setEndRadius(String endRadius)
    {
        this.endRadius = endRadius;
        endRadiusNull = false;
    }

    public JsonObjectBuilder toJson()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("name", name);
        builder.add("id", id);
        if (!volumeNull)
            builder.add("volume", volume);
        if (!lengthNull)
            builder.add("length", length);
        if (!startRadiusNull)
            builder.add("startRadius", startRadius);
        if (!endRadiusNull)
            builder.add("endRadius", endRadius);
        
        return builder;
    }
    
    public Element toXML(Document document)
    {
        //Creation de l'element
        Element connectible = document.createElement("Connectible");
        //Ajout des attributs
        connectible.setAttribute("name", name); 
        connectible.setAttribute("id", Integer.toString(id));
        if (!volumeNull)
            connectible.setAttribute("volume", volume);
        if (!lengthNull)
            connectible.setAttribute("length", length);
        if (!startRadiusNull)
            connectible.setAttribute("startRadius", startRadius);
        if (!endRadiusNull)
            connectible.setAttribute("endRadius", endRadius);

        return connectible;
    }
}

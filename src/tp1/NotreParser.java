package tp1;

import org.xml.sax.SAXParseException;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import partie2.Connectible;
import partie2.Connection;
import partie2.Flow;
import partie2.MainBody;
import partie2.Organ;
import partie2.SystemBody;
import partie2.To;


public class NotreParser extends DefaultHandler
{   
    private MainBody currentMainBody;
    private SystemBody currentSystem;
    private Flow currentFlow;
    private Connection currentConnection;
    
    private boolean isConnectible;
    
    public void startDocument()
    {
        System.out.println("Debut de la lecture du document XML");
    }
    
    public void endDocument()
    {
        System.out.println("Fin de la lecture du document XML");
    }
    
    public void startElement(String namespace, String lName, String qName, Attributes attrs)
    {
 
        if(!isConnectible){
            if ("MainBody".equals(qName))
            {
                
                currentMainBody= new MainBody();
               
                if(attrs != null && attrs.getLength() == 2)
                {
                    currentMainBody.setBodyName(attrs.getValue(0));
                    currentMainBody.setBodyID(Integer.parseInt(attrs.getValue(1))); 
                }
                
            }
            
            if ("System".equals(qName))
            {
                SystemBody system= new SystemBody();
                
                if(attrs != null && attrs.getLength() == 3)
                {
                    system.setName(attrs.getValue(0));
                    system.setId(Integer.parseInt(attrs.getValue(1))); 
                    system.setType(attrs.getValue(2));
                }
                
                currentSystem = system;
                currentMainBody.ajoutSystem(system);
            }
            
            if ("Organ".equals(qName))
            {
                Organ organ= new Organ();
                
                if(attrs != null && attrs.getLength() == 3)
                {
                    organ.setName(attrs.getValue(0));
                    organ.setId(Integer.parseInt(attrs.getValue(1))); 
                    organ.setSystemID(Integer.parseInt(attrs.getValue(2)));
                }
                
                currentMainBody.ajoutOrgan(organ);
            }
            
            if ("Flow".equals(qName))
            {
                Flow flow = new Flow();
                
                if(attrs != null && attrs.getLength() == 2)
                {
                    // Les attributs de Flow ne sont pas toujours en ordre
                    if ("id".equals(attrs.getQName(0))) {
                        flow.setId(Integer.parseInt(attrs.getValue(0)));
                        flow.setName(attrs.getValue(1)); 
                    }
                    else if ("name".equals(attrs.getQName(0))) {
                        flow.setName(attrs.getValue(0)); 
                        flow.setId(Integer.parseInt(attrs.getValue(1)));
                    }
                }
                
                currentFlow = flow;
                currentSystem.ajoutFlow(flow);
            }
            
            if ("Connection".equals(qName))
            {
                Connection connection = new Connection();
                
                if(attrs != null && attrs.getLength() == 1)
                {
                    connection.setId(Integer.parseInt(attrs.getValue(0)));
                }

                currentConnection = connection;
                currentFlow.ajoutConnection(connection);
            }
            
            if ("to".equals(qName))
            {
                To to = new To();
                
                if(attrs != null && attrs.getLength() == 1)
                {
                    to.setId(Integer.parseInt(attrs.getValue(0)));
                }

                currentConnection.ajoutTo(to);
            }
            
            //Si on rentre dans une balise connectible, toutes les prochains elements appartiennent à la liste de connectible du currentFlow
            if ("Connectible".equals(qName))
            {
                isConnectible = true;
            }
        } else {
            Connectible connectible = new Connectible();
            
            if(attrs != null && attrs.getLength() >= 2)
            {
                for(int i = 0; i < attrs.getLength(); i++){
                    if("name".equals(attrs.getQName(i)))
                        connectible.setName(attrs.getValue(i));
                    else if("id".equals(attrs.getQName(i)))
                        connectible.setId(Integer.parseInt(attrs.getValue(i)));
                    else if("volume".equals(attrs.getQName(i)))
                        connectible.setVolume(attrs.getValue(i));
                    else if("length".equals(attrs.getQName(i)))
                        connectible.setLength(attrs.getValue(i));
                    else if("startRadius".equals(attrs.getQName(i)))
                        connectible.setStartRadius(attrs.getValue(i));
                    else if("endRadius".equals(attrs.getQName(i)))
                        connectible.setEndRadius(attrs.getValue(i));
                }
            }            
            currentFlow.ajoutConnectible(connectible);
        }
        
        
    }
    
    public void endElement(String namespace, String lName, String qName)
    {   
        if ("Connectible".equals(qName))
        {
            isConnectible = false;
        }
    }
    
    public void warning(SAXParseException e)
    {
        System.out.println("Attention");

    }
    
    public void error(SAXParseException e)
    {
        System.out.println("Erreur");
    }
    
    public void fatalError(SAXParseException e)
    {
        System.out.println("Erreur fatale !!!!111!!1!1");
    }
    
    public MainBody getMainBody(){
        return currentMainBody;
    }

}

// Travail fait par :
//   Audrey Thomas - 14 081 897
//   Yassine Benchakroun - 14 021 079

package tp1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.xml.parsers.*;
import org.xml.sax.SAXParseException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import partie2.MainBody;

/**
 * Fichier de base pour le Devoir1A du cours IFT287
 *
 * <pre>
 * 
 * Vincent Ducharme
 * Universite de Sherbrooke
 * Version 1.0 - 6 août 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet de convertir un fichier XML en son équivalent en JSON.
 *
 * Paramètres du programme
 * 0- Nom du fichier XML
 * 1- Nom du fichier JSON
 * 
 * </pre>
 */
public class Devoir1A
{
    
    public static void main(String[] args)
    {
       /*
        if (args.length < 2)
        {
            System.out.println("Usage: java tp1.Devoir1A <fichierXML> <fichierJSON>");
            return;
        }
        
        String nomFichierXML = args[0];
        String nomFichierJSON = args[1];
        */
                
        String nomFichierXML = "HumanBody.xml";
        String nomFichierJSON = "lol.json";
        
        System.out.println("Debut de la conversion du fichier " + nomFichierXML + " vers le fichier " + nomFichierJSON);
        
        DefaultHandler parser = lireXML(nomFichierXML);
        MainBody mainBody = ((NotreParser)parser).getMainBody();
        try
        {
            ecrireJSON(nomFichierJSON, mainBody);
        }
        catch (IFT287Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Conversion terminee.");
    }
    
    public static DefaultHandler lireXML(String fichierXML)
    {
        System.out.println("Lecture du fichier XML " + fichierXML);

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);
        SAXParser parser;
        try
        {
            parser = factory.newSAXParser();
            DefaultHandler handler = new NotreParser();
            try
            {
                parser.parse(new File(fichierXML), handler);
                return handler;
            }
            catch (IOException e)
            {
                System.out.println("Erreur IO");
                e.printStackTrace();
            }
        }
        catch (ParserConfigurationException e)
        {
            System.out.println("Erreur creation parser depuis la factory (configuration");
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            System.out.println("Erreur creation parser depuis la factory");
            e.printStackTrace();
        }
        return null;
    }
    
    public static void ecrireJSON(String fichierJSON, MainBody mainBody) throws IFT287Exception
    {
        System.out.println("Écriture du fichier JSON " + fichierJSON);
        
        FileWriter stWriter;
        try
        {
            stWriter = new FileWriter(fichierJSON);
            Map<String, Object> config = new HashMap<String, Object>(1);
            config.put(JsonGenerator.PRETTY_PRINTING, false);
            JsonWriterFactory f = Json.createWriterFactory(config);
            JsonWriter jsonWriter = f.createWriter(stWriter);
            jsonWriter.writeObject(mainBody.toJson().build());
            jsonWriter.close();
            System.out.println("Fin de l'écriture du fichier JSON.");
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

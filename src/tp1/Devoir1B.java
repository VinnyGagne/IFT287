// Travail fait par :
//   Audrey Thomas - 14 081 897
//   Yassine Benchakroun - 14 021 079

package tp1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import partie2.MainBody;

/**
 * Fichier de base pour le Devoir1B du cours IFT287
 *
 * <pre>
 * 
 * Vincent Ducharme
 * Universite de Sherbrooke
 * Version 1.0 - 6 août 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet de convertir un fichier JSON en son équivalent en XML.
 *
 * Paramètres du programme
 * 0- Nom du fichier JSON
 * 1- Nom du fichier XML
 * 
 * </pre>
 */
public class Devoir1B
{

    public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, TransformerException
    {
       /* if (args.length < 2)
        {
            System.out.println("Usage: java tp1.Devoir1B <fichierJSON> <fichierXML>");
            return;
        }
        
        String nomFichierJSON = args[0];
        String nomFichierXML = args[1];
        */
        
        String nomFichierJSON = "lol.json";
        String nomFichierXML = "test.xml";
        
        System.out.println("Debut de la conversion du fichier " + nomFichierJSON + " vers le fichier " + nomFichierXML);

        JsonReader reader = Json.createReader(new FileReader(nomFichierJSON));
        JsonObject obj = (JsonObject) reader.read();
        
        MainBody mainBody = new MainBody();
        /** PARSAGE DE MAINBODY **/
        
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        Document document = f.newDocumentBuilder().newDocument();
        
        mainBody.toXML(document);
        
        FileOutputStream output = new FileOutputStream(nomFichierXML);
        PrintStream out = new PrintStream(output);
        
        TransformerFactory allSpark = TransformerFactory.newInstance();
        Transformer optimusPrime = allSpark.newTransformer();
        //optimusPrime.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dtdName);
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(out);
        optimusPrime.transform(source, result);


        System.out.println("Conversion terminee.");

    }

}

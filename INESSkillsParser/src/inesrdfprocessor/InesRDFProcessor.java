package inesrdfprocessor;

import com.hp.hpl.jena.rdf.model.Container;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;

/**
 * TUTORIAL http://jena.apache.org/tutorials/rdf_api.html#ch-Reading RDF
 * @author Felipe
 */
public class InesRDFProcessor {

    public void processRDF(String file) {
        Model model = loadRDF(file);
        processRDFskill(model);
        saveRDF(model, file);
    }

    public Model loadRDF(String file) {
        String inputFileName = file;
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // use the FileManager to find the input file
        InputStream in = FileManager.get().open("data/output/" + inputFileName);
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: " + inputFileName + " not found");
        }

        // read the RDF/XML file
        model.read(in, null);

        // write it to standard out
        //model.write(System.out);
        return model;
    }

    public void processRDFskill(Model model) {
        StmtIterator iter = model.listStatements();
        while (iter.hasNext()) {
            Statement s = iter.nextStatement();
            //System.out.println(s.getPredicate().getLocalName());
            if (s.getPredicate().getLocalName().contentEquals("Skill")) {

                NodeIterator iterator = s.getProperty(
                        model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#Bag")).getBag().iterator();
                while (iterator.hasNext()) {
                    Resource li = (Resource) iterator.next();
                    //System.out.println( li.getProperty(model.getProperty("http://kmm.lboro.ac.uk/ecos/1.0#" + "name")).getString());
                }

            }
        }
    }

    public void saveRDF(Model model, String file) {
        String outputFileName = file;
        try {
            FileWriter writer = new FileWriter("data/output2/" + outputFileName);
            model.write(writer);
        } catch (Exception e) {
            System.out.println("Error writing " + outputFileName);
        }
    }

    public static void main(String[] args) {
        InesRDFProcessor p = new InesRDFProcessor();
        File folder = new File("data/output");
        File[] listOfFiles = folder.listFiles();
        String filename;
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                filename = listOfFiles[i].getName();
                if (filename.toLowerCase().endsWith(".rdf")) {
                    p.processRDF(filename);
                }
            }
        }
    }
}

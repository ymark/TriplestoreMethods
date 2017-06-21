package gr.forth.ics.isl.triplestoremethods.example;

import gr.forth.ics.isl.triplestoremethods.TripleStoreFactory;
import gr.forth.ics.isl.triplestoremethods.api.TripleStoreConnector;
import gr.forth.ics.isl.triplestoremethods.common.RdfFormat;
import gr.forth.ics.isl.triplestoremethods.exceptions.ExporterException;
import gr.forth.ics.isl.triplestoremethods.exceptions.ImporterException;
import gr.forth.ics.isl.triplestoremethods.exceptions.QueryException;
import gr.forth.ics.isl.triplestoremethods.exceptions.TripleStoreConnectionException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import org.apache.commons.lang3.tuple.Triple;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public class BlazegraphExample {
    private static final String NAMED_GRAPH="graphname";
    private static final String INPUT_FOLDER_NTRIPLES="example/ntriples/";
    private static final String INPUT_FILE1_NTRIPLES="example/ntriples/input1.nt";
    private static final String INPUT_FILE2_NTRIPLES="example/ntriples/input2.nt";
    private static final String INPUT_FOLDER_RDF="example/rdf/";
    private static final String INPUT_FILE1_RDF="example/rdf/input1.rdf";
    private static final String INPUT_FILE2_RDF="example/rdf/input2.rdf";
    private static final String OUTPUT_FILE_RDF="example/output/output.rdf";
    private static final String OUTPUT_FILE_NTRIPLES="example/output/output.nt";
    
    public static void main(String[] args) throws TripleStoreConnectionException, ImporterException, ExporterException, FileNotFoundException, UnsupportedEncodingException, IOException, QueryException{
        TripleStoreConnector connector=TripleStoreFactory.connectToBlazegraph("http://localhost/bigdata");

        imports(connector);
//        exports(connector);
//        query(connector);
    }
    
    private static void imports(TripleStoreConnector connector) throws ImporterException, FileNotFoundException, UnsupportedEncodingException, IOException{
        /* Import a specific file */
        connector.importer().importResource(new File(INPUT_FILE1_NTRIPLES), NAMED_GRAPH, RdfFormat.NTRIPLES);
        
//        /* Import from content*/
//        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(new File(INPUT_FILE2_RDF)),"UTF-8"));
//        String line;
//        StringBuilder sb=new StringBuilder();
//        while((line=br.readLine())!=null){
//            sb.append(line).append("\n");
//        }
//        connector.importer().importResource(sb.toString(), NAMED_GRAPH, RdfFormat.RDF_XML);
//        
//        /* Import multiple files */
//        connector.importer().importResources(NAMED_GRAPH, RdfFormat.RDF_XML, new File(INPUT_FILE1_RDF), new File(INPUT_FILE2_RDF));
//        
//        /* Import from folder */
//        connector.importer().importResourcesFromFolder(new File(INPUT_FOLDER_NTRIPLES), NAMED_GRAPH, true, RdfFormat.NTRIPLES);
    }
    
    private static void exports(TripleStoreConnector connector) throws ImporterException, ExporterException{
        /* Import a specific file */
        connector.exporter().exportToFile(new File(OUTPUT_FILE_RDF), NAMED_GRAPH, RdfFormat.RDF_XML);
        
        /* Export as a String */
        connector.exporter().exportToString(NAMED_GRAPH, RdfFormat.NTRIPLES);
    }
    
    private static void query(TripleStoreConnector connector) throws QueryException{
        Collection<Triple<String,String,String>> results=connector.query().getTriplesWith(null, null, null, NAMED_GRAPH);
        System.out.println("Results size: "+results.size());
        
        results=connector.query().getTriplesWith("http://localhost/person/yannis.a", null, null, NAMED_GRAPH);
        System.out.println("Results size: "+results.size());
        
        results=connector.query().getTriplesWith(null, "http://localhost/property/lives", null, NAMED_GRAPH);
        System.out.println("Results size: "+results.size());
        
        results=connector.query().getTriplesWith(null, null, "heraklion", NAMED_GRAPH);
        System.out.println("Results size: "+results.size());
        
        results=connector.query().getTriplesHavingSubject("http://localhost/person/yannis.a",NAMED_GRAPH);
        System.out.println("Results size: "+results.size());
        
        results=connector.query().getTriplesHavingPredicate("http://localhost/property/lives",NAMED_GRAPH);
        System.out.println("Results size: "+results.size());
        
        results=connector.query().getTriplesHavingObject("http://localhost/place/heraklion",NAMED_GRAPH);
        System.out.println("Results size: "+results.size());
        
        results=connector.query().getTriplesHavingLiteralObject("yannis", NAMED_GRAPH);
        System.out.println("Results size: "+results.size());
        
        boolean result=connector.query().hasTriplesWith(null, null, null, NAMED_GRAPH);
        System.out.println("Result: "+result);
        
        result=connector.query().hasTriplesWith("http://localhost/person/yannis", null, null, NAMED_GRAPH);
        System.out.println("Results: "+result);
        
        result=connector.query().hasTriplesWith(null, "http://localhost/property/lives", null, NAMED_GRAPH);
        System.out.println("Result: "+result);
        
        result=connector.query().hasTriplesWith(null, null, "heraklion", NAMED_GRAPH);
        System.out.println("Result: "+result);
        
        result=connector.query().hasTriplesHavingSubject("http://localhost/person/yannis.a",NAMED_GRAPH);
        System.out.println("Result: "+result);
        
        result=connector.query().hasTriplesHavingPredicate("http://localhost/property/live",NAMED_GRAPH);
        System.out.println("Result: "+result);
        
        result=connector.query().hasTriplesHavingObject("http://localhost/place/heraklion",NAMED_GRAPH);
        System.out.println("Result: "+result);
        
        result=connector.query().hasTriplesHavingLiteralObject("yannis", NAMED_GRAPH);
        System.out.println("Result: "+result);
    }
}
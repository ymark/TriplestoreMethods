package gr.forth.ics.isl.triplestoremethods.impl.virtuoso;

import gr.forth.ics.isl.triplestoremethods.api.Importer;
import gr.forth.ics.isl.triplestoremethods.common.RdfFormat;
import gr.forth.ics.isl.triplestoremethods.exceptions.ImporterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.log4j.Logger;
import org.openrdf.model.URI;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public class VirtuosoImporter implements Importer{
    private static final Logger logger=Logger.getLogger(VirtuosoConnector.class);
    private Repository repo;
    
    public VirtuosoImporter(Repository repository){
        this.repo=repository;
    }

    @Override
    public void importResource(File file, String graphspace, RdfFormat format) throws ImporterException {
        logger.debug("Importing file("+file.getAbsolutePath()+") in graphspace ("+graphspace+") with format ("+format+")");
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
            URI graph=this.repo.getValueFactory().createURI(graphspace);
            InputStreamReader in=new InputStreamReader(new FileInputStream(file), "UTF-8");
            switch(format){
                case RDF_XML: 
                    repoConn.add(file, graphspace, RDFFormat.RDFXML, graph);
                    break;
                case NTRIPLES:
                    repoConn.add(file, graphspace, RDFFormat.NTRIPLES, graph);
                    break;
                case TURTLE:
                    repoConn.add(file, graphspace, RDFFormat.TURTLE, graph);
                    break;
                case TRIG:
                    repoConn.add(file, graphspace, RDFFormat.TRIG, graph);
                    break;
            }
            repoConn.close();
        }catch(IOException ex){
            logger.error("The given file does not exist",ex);
            throw new ImporterException("The given file does not exist", ex);
        }catch(RDFParseException ex){
            logger.error("The given file is not a valid RDF document",ex);
            throw new ImporterException("The given file is not a valid RDF document", ex);
        }catch(RepositoryException ex){
            logger.error("An error occured while importing data",ex);
            throw new ImporterException("An error occured while importing data", ex);
        }
    }

    @Override
    public void importResource(String content, String graphspace, RdfFormat format) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void importResources(String graphspace, RdfFormat format, File... files) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void importResources(String graphspace, RdfFormat format, String... files) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void importResourcesFromFolder(File folder, String graphspace, boolean recursiveImport, RdfFormat format) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
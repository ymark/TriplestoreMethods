package gr.forth.ics.isl.triplestoremethods.impl.virtuoso;

import gr.forth.ics.isl.triplestoremethods.api.Exporter;
import gr.forth.ics.isl.triplestoremethods.common.RdfFormat;
import gr.forth.ics.isl.triplestoremethods.exceptions.ExporterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import org.apache.log4j.Logger;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFHandler;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFWriter;
import org.openrdf.rio.ntriples.NTriplesWriter;
import org.openrdf.rio.rdfxml.RDFXMLWriter;
import org.openrdf.rio.trig.TriGWriter;
import org.openrdf.rio.turtle.TurtleWriter;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public class VirtuosoExporter implements Exporter{
    private static final Logger logger=Logger.getLogger(VirtuosoExporter.class);
    private Repository repo;
    
    public VirtuosoExporter(Repository repository){
        this.repo=repository;
    }

    @Override
    public String exportToString(String graphspace, RdfFormat format) throws ExporterException{
        logger.debug("Request for exporting data from the graphspace ("+graphspace+") in format ("+format+")");
        StringWriter stringWriter=new StringWriter();
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
            RDFHandler handler=new NTriplesWriter(stringWriter);
            switch(format){
                case RDF_XML:
                    handler=new RDFXMLWriter(stringWriter);
                    break;
                case NTRIPLES:
                    handler=new NTriplesWriter(stringWriter);
                    break;
                case TRIG:
                    handler=new TriGWriter(stringWriter);
                    break;
                case TURTLE:
                    handler=new TurtleWriter(stringWriter);
                    break;
            }
            repoConn.export(handler, this.repo.getValueFactory().createURI(graphspace));
            repoConn.close();
        }catch(RepositoryException | RDFHandlerException ex){
            logger.error("An error occured while exporting data from the triplestore",ex);
            throw new ExporterException("An error occured while exporting data from the triplestore",ex);
        }
        return stringWriter.toString();
    }

    @Override
    public void exportToFile(File file, String graphspace, RdfFormat format) throws ExporterException{
        String contents=this.exportToString(graphspace, format);
        try{
            BufferedWriter br=new BufferedWriter(new FileWriter(file));
            br.append(contents);
            br.flush();
            br.close();
        }catch(IOException ex){
            logger.error("An error occured while exporting data from the triplestore in a file",ex);
            throw new ExporterException("An error occured while exporting data from the triplestore in a file",ex);
        }
    }

}

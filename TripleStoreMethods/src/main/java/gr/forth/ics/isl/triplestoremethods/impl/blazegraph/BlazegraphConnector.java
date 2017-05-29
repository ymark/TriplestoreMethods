package gr.forth.ics.isl.triplestoremethods.impl.blazegraph;

import com.bigdata.rdf.sail.webapp.client.RemoteRepositoryManager;
import gr.forth.ics.isl.triplestoremethods.api.Delete;
import gr.forth.ics.isl.triplestoremethods.api.Exporter;
import gr.forth.ics.isl.triplestoremethods.api.Importer;
import gr.forth.ics.isl.triplestoremethods.api.Insert;
import gr.forth.ics.isl.triplestoremethods.api.Query;
import gr.forth.ics.isl.triplestoremethods.api.TripleStoreConnector;
import gr.forth.ics.isl.triplestoremethods.api.Update;
import gr.forth.ics.isl.triplestoremethods.exceptions.TripleStoreConnectionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import org.eclipse.jetty.client.HttpClient;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public class BlazegraphConnector implements TripleStoreConnector {
    private static final Logger logger=Logger.getLogger(BlazegraphConnector.class);
    private RemoteRepositoryManager repo;
    
    public BlazegraphConnector(String host, int port, String username, String password) throws TripleStoreConnectionException{
        if(host==null || host.isEmpty()){
            throw new TripleStoreConnectionException("The host of the Blazegraph triplestore is mandatory and cannot be null or empty");
        }
        logger.debug("Connecting to Blazegraph["+host+"]");
        
        try{
            HttpClient httpClient=new HttpClient();
            httpClient.start();
            ExecutorService executor=Executors.newCachedThreadPool();
            this.repo = new RemoteRepositoryManager(host, httpClient, executor);
            httpClient.stop();
            httpClient.destroy();
        }catch(Exception ex){
            logger.error("An error occured while initializing the connection to the repository",ex);
            throw new TripleStoreConnectionException("An error occured while initializing the connection to the repository", ex);
        }
    }

    @Override
    public Importer importer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Exporter exporter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Query query() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Insert insert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Update update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Delete delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

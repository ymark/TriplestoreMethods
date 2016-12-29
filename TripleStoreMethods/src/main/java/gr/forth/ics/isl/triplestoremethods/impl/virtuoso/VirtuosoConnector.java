package gr.forth.ics.isl.triplestoremethods.impl.virtuoso;

import gr.forth.ics.isl.triplestoremethods.api.Delete;
import gr.forth.ics.isl.triplestoremethods.api.Exporter;
import gr.forth.ics.isl.triplestoremethods.api.Importer;
import gr.forth.ics.isl.triplestoremethods.api.Insert;
import gr.forth.ics.isl.triplestoremethods.api.Query;
import gr.forth.ics.isl.triplestoremethods.api.TripleStoreConnector;
import gr.forth.ics.isl.triplestoremethods.api.Update;
import gr.forth.ics.isl.triplestoremethods.common.Common;
import gr.forth.ics.isl.triplestoremethods.exceptions.TripleStoreConnectionException;
import org.openrdf.repository.Repository;
import org.apache.log4j.Logger;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import virtuoso.sesame2.driver.VirtuosoRepository;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public class VirtuosoConnector implements TripleStoreConnector {
    private static final Logger logger=Logger.getLogger(VirtuosoConnector.class);
    private Repository repo;
    
    public VirtuosoConnector(String host, int port, String username, String password) throws TripleStoreConnectionException{
        if(host==null || host.isEmpty()){
            throw new TripleStoreConnectionException("The host of the Virtuoso triplestore is mandatory and cannot be null or empty");
        }
        if(port<0){
            throw new TripleStoreConnectionException("The port of the Virtuoso triplestore is mandatory and cannot be a negative port number");
        }
        if(username==null || username.isEmpty()){
            throw new TripleStoreConnectionException("The username for connecting to a Virtuoso triplestore is mandatory and cannot be null or empty");
        }
        if(password==null || password.isEmpty()){
            throw new TripleStoreConnectionException("The password for connecting to a Virtuoso triplestore is mandatory and cannot be null or empty");
        }
        logger.debug("Connecting to Virtuoso["+host+","+port+","+username+","+password+"]");
        
        if(host.startsWith(Common.DEFAULT_URL_PREFIX)){
                host=host.replace(Common.DEFAULT_URL_PREFIX, "");
            }
        if(host.endsWith("/")){
            host=host.substring(0, host.length()-1); 
        }
        this.repo=new VirtuosoRepository(Common.DEFAULT_VIRTUOSO_JDBC_URL_PREFIX+host+":"+port,username,password);
        /*Connect to check that everything is allright */
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
            repoConn.close();
        }catch(RepositoryException ex){
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

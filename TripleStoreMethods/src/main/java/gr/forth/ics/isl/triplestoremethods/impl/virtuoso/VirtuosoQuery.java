package gr.forth.ics.isl.triplestoremethods.impl.virtuoso;

import gr.forth.ics.isl.triplestoremethods.api.Query;
import gr.forth.ics.isl.triplestoremethods.exceptions.QueryException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.log4j.Logger;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public class VirtuosoQuery implements Query{
    private static final Logger logger=Logger.getLogger(VirtuosoQuery.class);
    private Repository repo;
    
    public VirtuosoQuery(Repository repository){
        this.repo=repository;
    }

    @Override
    public Collection<Triple<String,String,String>> getTriplesWith(String subject, String predicate, String object, String... graphspaces) throws QueryException {
        logger.debug("Request for retrieving triples with subject ("+subject+"), predicate ("+predicate+"), object ("+object+") in graphspaces ("+graphspaces+")");
        Set<Triple<String,String,String>> retCol=new HashSet<>();
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
//            repoConn.get

            repoConn.close();
        }catch(RepositoryException ex){
            logger.error("An error occured while querying the triplestore",ex);
            throw new QueryException("An error occured while querying the triplestore",ex);
        }
        return retCol;
    }

    @Override
    public Collection<Triple<String,String,String>> getTriplesHavingSubject(String subject, String... graphspaces) throws QueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Triple<String,String,String>> getTriplesHavingPredicate(String predicate, String... graphspaces) throws QueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Triple<String,String,String>> getTriplesHavingObject(String object, String... graphspaces) throws QueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Triple<String,String,String>> getTriplesHavingLiteralObject(String literalObject, String... graphspaces) throws QueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasTriplesWith(String subject, String predicate, String object, String... graphspaces) throws QueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasTriplesHavingSubject(String subject, String... graphspaces) throws QueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasTriplesHavingPredicate(String predicate, String... graphspaces) throws QueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasTriplesHavingObject(String object, String... graphspaces) throws QueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasTriplesHavingLiteralObject(String literalObject, String... graphspaces) throws QueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Map<String, String>> evaluateSparql(String sparqlExpression) throws QueryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

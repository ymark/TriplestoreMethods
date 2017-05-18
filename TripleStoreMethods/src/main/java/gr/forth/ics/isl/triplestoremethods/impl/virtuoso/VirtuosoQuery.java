package gr.forth.ics.isl.triplestoremethods.impl.virtuoso;

import gr.forth.ics.isl.triplestoremethods.api.Query;
import gr.forth.ics.isl.triplestoremethods.common.Utils;
import gr.forth.ics.isl.triplestoremethods.exceptions.QueryException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.log4j.Logger;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQueryResult;
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
        logger.debug("Request for retrieving triples with subject ("+subject+"), predicate ("+predicate+"), object ("+object+") in graphspaces ("+Arrays.asList(graphspaces)+")");
        Set<Triple<String,String,String>> retCol=new HashSet<>();
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
            String sparqlQuery="SELECT ?subject ?predicate ?object ";
            for(String graphspace : graphspaces){
                sparqlQuery+="FROM <"+graphspace+"> ";
            }
            sparqlQuery+="WHERE{ ?subject ?predicate ?object. "
                        +"FILTER (?subject=<"+subject+">). "
                        +"FILTER (?predicate=<"+predicate+">). ";
            if(Utils.isValidURI(object)){
                sparqlQuery+="FILTER (?object=<"+object+">)}";
            }else{
                sparqlQuery+="FILTER REGEX(?object,\""+object+"\",\"i\")}";
            }
            logger.debug("SPAPQL query: "+sparqlQuery);
            TupleQueryResult results=repoConn.prepareTupleQuery(QueryLanguage.SPARQL, sparqlQuery).evaluate();
            while(results.hasNext()){
                BindingSet result=results.next();
                retCol.add(Triple.of(result.getValue("subject").stringValue(), 
                                     result.getValue("predicate").stringValue(),
                                     result.getValue("object").stringValue()));
            }
            repoConn.close();
        }catch(RepositoryException | MalformedQueryException | QueryEvaluationException ex){
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
        logger.debug("Request for checking the existence of triples with subject ("+subject+"), predicate ("+predicate+"), object ("+object+") in graphspaces ("+Arrays.asList(graphspaces)+")");
        boolean result=false;
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
            String sparqlQuery="ASK ";
            for(String graphspace : graphspaces){
                sparqlQuery+="FROM <"+graphspace+"> ";
            }
            sparqlQuery+="WHERE{ ?subject ?predicate ?object. "
                        +"FILTER (?subject=<"+subject+">). "
                        +"FILTER (?predicate=<"+predicate+">). ";
            if(Utils.isValidURI(object)){
                sparqlQuery+="FILTER (?object=<"+object+">)}";
            }else{
                sparqlQuery+="FILTER REGEX(?object,\""+object+"\",\"i\")}";
            }
            logger.debug("SPAPQL query: "+sparqlQuery);
            result=repoConn.prepareBooleanQuery(QueryLanguage.SPARQL, sparqlQuery).evaluate();
            repoConn.close();
        }catch(RepositoryException | MalformedQueryException | QueryEvaluationException ex){
            logger.error("An error occured while querying the triplestore",ex);
            throw new QueryException("An error occured while querying the triplestore",ex);
        }
        return result;
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

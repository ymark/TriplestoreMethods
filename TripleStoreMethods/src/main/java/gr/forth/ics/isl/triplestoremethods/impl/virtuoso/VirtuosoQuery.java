package gr.forth.ics.isl.triplestoremethods.impl.virtuoso;

import gr.forth.ics.isl.triplestoremethods.api.Query;
import gr.forth.ics.isl.triplestoremethods.common.Utils;
import gr.forth.ics.isl.triplestoremethods.exceptions.QueryException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
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
        if(graphspaces!=null){
            logger.debug("Request for retrieving triples with subject ("+subject+"), predicate ("+predicate+"), object ("+object+") in graphspaces ("+Arrays.asList(graphspaces)+")");
        }else{
            logger.debug("Request for retrieving triples with subject ("+subject+"), predicate ("+predicate+"), object ("+object+") in ALL graphspaces");
        }
        Set<Triple<String,String,String>> retCol=new HashSet<>();
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
            String sparqlQuery="SELECT ?subject ?predicate ?object ";
            if(graphspaces!=null){
                for(String graphspace : graphspaces){
                    sparqlQuery+="FROM <"+graphspace+"> ";
                }
            }
            sparqlQuery+="WHERE{ ?subject ?predicate ?object. ";
            if(subject!=null && !subject.isEmpty()){
                sparqlQuery+="FILTER (?subject=<"+subject+">). ";
            }
            if(predicate!=null && !predicate.isEmpty()){
                sparqlQuery+="FILTER (?predicate=<"+predicate+">). ";
            }
            if(object!=null && !object.isEmpty()){
                if(Utils.isValidURI(object)){
                    sparqlQuery+="FILTER (?object=<"+object+">)";
                }else{
                    sparqlQuery+="FILTER REGEX(?object,\""+object+"\",\"i\")";
                }
            }
            sparqlQuery+="}";
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
        if(graphspaces!=null){
            logger.debug("Request for retrieving triples with subject ("+subject+") in graphspaces ("+Arrays.asList(graphspaces)+")");
        }else{
            logger.debug("Request for retrieving triples with subject ("+subject+") in ALL graphspaces");
        }
        Set<Triple<String,String,String>> retCol=new HashSet<>();
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
            String sparqlQuery="SELECT ?subject ?predicate ?object ";
            if(graphspaces!=null){
                for(String graphspace : graphspaces){
                    sparqlQuery+="FROM <"+graphspace+"> ";
                }
            }
            sparqlQuery+="WHERE{ ?subject ?predicate ?object. "
                        +"FILTER (?subject=<"+subject+">)} ";
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
    public Collection<Triple<String,String,String>> getTriplesHavingPredicate(String predicate, String... graphspaces) throws QueryException {
        if(graphspaces!=null){
            logger.debug("Request for retrieving triples with predicate ("+predicate+") in graphspaces ("+Arrays.asList(graphspaces)+")");
        }else{
            logger.debug("Request for retrieving triples with predicate ("+predicate+") in ALL graphspaces");
        }
        Set<Triple<String,String,String>> retCol=new HashSet<>();
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
            String sparqlQuery="SELECT ?subject ?predicate ?object ";
            if(graphspaces!=null){
                for(String graphspace : graphspaces){
                    sparqlQuery+="FROM <"+graphspace+"> ";
                }
            }
            sparqlQuery+="WHERE{ ?subject ?predicate ?object. "
                        +"FILTER (?predicate=<"+predicate+">)} ";
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
    public Collection<Triple<String,String,String>> getTriplesHavingObject(String object, String... graphspaces) throws QueryException {
        if(graphspaces!=null){
            logger.debug("Request for retrieving triples with object URI ("+object+") in graphspaces ("+Arrays.asList(graphspaces)+")");
        }else{
            logger.debug("Request for retrieving triples with object URI ("+object+") in ALL graphspaces");
        }       
        Set<Triple<String,String,String>> retCol=new HashSet<>();
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
            String sparqlQuery="SELECT ?subject ?predicate ?object ";
            if(graphspaces!=null){
                for(String graphspace : graphspaces){
                    sparqlQuery+="FROM <"+graphspace+"> ";
                }
            }
            sparqlQuery+="WHERE{ ?subject ?predicate ?object. "
                        +"FILTER (?object=<"+object+">)} ";
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
    public Collection<Triple<String,String,String>> getTriplesHavingLiteralObject(String literalObject, String... graphspaces) throws QueryException {
        if(graphspaces!=null){
            logger.debug("Request for retrieving triples with object ("+literalObject+") in graphspaces ("+Arrays.asList(graphspaces)+")");
        }else{
            logger.debug("Request for retrieving triples with object ("+literalObject+") in ALL graphspaces");
        }
        Set<Triple<String,String,String>> retCol=new HashSet<>();
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
            String sparqlQuery="SELECT ?subject ?predicate ?object ";
            if(graphspaces!=null){
                for(String graphspace : graphspaces){
                    sparqlQuery+="FROM <"+graphspace+"> ";
                }
            }
            sparqlQuery+="WHERE{ ?subject ?predicate ?object. "
                        +"FILTER REGEX(?object, \""+literalObject+"\", \"i\")} ";
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
    public boolean hasTriplesWith(String subject, String predicate, String object, String... graphspaces) throws QueryException {
        if(graphspaces!=null){
            logger.debug("Request for checking the existence of triples with subject ("+subject+"), predicate ("+predicate+"), object ("+object+") in graphspaces ("+Arrays.asList(graphspaces)+")");
        }else{
            logger.debug("Request for checking the existence of triples with subject ("+subject+"), predicate ("+predicate+"), object ("+object+") in ALL graphspaces");
        }
        boolean result=false;
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
            String sparqlQuery="ASK ";
            if(graphspaces!=null){
                for(String graphspace : graphspaces){
                    sparqlQuery+="FROM <"+graphspace+"> ";
                }
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
        if(graphspaces!=null){
            logger.debug("Request for checking the existence of triples with subject ("+subject+") in graphspaces ("+Arrays.asList(graphspaces)+")");
        }else{
            logger.debug("Request for checking the existence of triples with subject ("+subject+") in ALL graphspaces");
        }
        boolean result=false;
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
            String sparqlQuery="ASK ";
            if(graphspaces!=null){
                for(String graphspace : graphspaces){
                    sparqlQuery+="FROM <"+graphspace+"> ";
                }
            }
            sparqlQuery+="WHERE{ ?subject ?predicate ?object. "
                        +"FILTER (?subject=<"+subject+">)} ";
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
    public boolean hasTriplesHavingPredicate(String predicate, String... graphspaces) throws QueryException {
        if(graphspaces!=null){
            logger.debug("Request for checking the existence of triples with predicate ("+predicate+") in graphspaces ("+Arrays.asList(graphspaces)+")");
        }else{
            logger.debug("Request for checking the existence of triples with predicate ("+predicate+") in ALL graphspaces");
        }
        boolean result=false;
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
            String sparqlQuery="ASK ";
            if(graphspaces!=null){
                for(String graphspace : graphspaces){
                    sparqlQuery+="FROM <"+graphspace+"> ";
                }
            }
            sparqlQuery+="WHERE{ ?subject ?predicate ?object. "
                        +"FILTER (?predicate=<"+predicate+">)} ";
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
    public boolean hasTriplesHavingObject(String object, String... graphspaces) throws QueryException {
        if(graphspaces!=null){
            logger.debug("Request for checking the existence of triples with object URI ("+object+") in graphspaces ("+Arrays.asList(graphspaces)+")");
        }else{
            logger.debug("Request for checking the existence of triples with object URI ("+object+") in ALL graphspaces");
        }
        boolean result=false;
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
            String sparqlQuery="ASK ";
            if(graphspaces!=null){
                for(String graphspace : graphspaces){
                    sparqlQuery+="FROM <"+graphspace+"> ";
                }
            }
            sparqlQuery+="WHERE{ ?subject ?predicate ?object. "
                        +"FILTER (?object=<"+object+">)} ";
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
    public boolean hasTriplesHavingLiteralObject(String literalObject, String... graphspaces) throws QueryException {
        if(graphspaces!=null){
            logger.debug("Request for checking the existence of triples with object ("+literalObject+") in graphspaces ("+Arrays.asList(graphspaces)+")");
        }else{
            logger.debug("Request for checking the existence of triples with object ("+literalObject+") in ALL graphspaces");
        }
        boolean result=false;
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
            String sparqlQuery="ASK ";
            if(graphspaces!=null){
                for(String graphspace : graphspaces){
                    sparqlQuery+="FROM <"+graphspace+"> ";
                }
            }
            sparqlQuery+="WHERE{ ?subject ?predicate ?object. "
                        +"FILTER REGEX(?object,\""+literalObject+"\",\"i\")} ";
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
    public Collection<Map<String, String>> evaluateSparql(String sparqlExpression) throws QueryException {
        logger.debug("Request for evaluating the SPARQL query: "+sparqlExpression);
        Collection<Map<String,String>> retCol=new HashSet<>();
        try{
            RepositoryConnection repoConn=this.repo.getConnection();
            TupleQueryResult results=repoConn.prepareTupleQuery(QueryLanguage.SPARQL, sparqlExpression).evaluate();
            while(results.hasNext()){
                BindingSet result=results.next();
                Map<String,String> tupleResult=new HashMap<>();
                for(String variable : result.getBindingNames()){
                    tupleResult.put(variable, result.getValue(variable).stringValue());
                }
                retCol.add(tupleResult);
            }
            repoConn.close();
        }catch(RepositoryException ex){
            logger.error("An error occured while querying the triplestore",ex);
            throw new QueryException("An error occured while querying the triplestore",ex);
        }catch(MalformedQueryException | QueryEvaluationException ex){
            logger.error("The given SPARQL query is invalid",ex);
            throw new QueryException("The given SPARQL query is invalid",ex);
        }
        return retCol;
    }
}

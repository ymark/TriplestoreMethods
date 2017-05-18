package gr.forth.ics.isl.triplestoremethods.api;

import gr.forth.ics.isl.triplestoremethods.exceptions.QueryException;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.lang3.tuple.Triple;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public interface Query {
    public Collection<Triple<String,String,String>> getTriplesWith(String subject, String predicate, String object, String... graphspaces) throws QueryException;
    
    public Collection<Triple<String,String,String>> getTriplesHavingSubject(String subject, String... graphspaces) throws QueryException;
    
    public Collection<Triple<String,String,String>> getTriplesHavingPredicate(String predicate, String... graphspaces) throws QueryException;
    
    public Collection<Triple<String,String,String>> getTriplesHavingObject(String object, String... graphspaces) throws QueryException;
    
    public Collection<Triple<String,String,String>> getTriplesHavingLiteralObject(String literalObject, String... graphspaces) throws QueryException;
    
    public boolean hasTriplesWith(String subject, String predicate, String object, String... graphspaces) throws QueryException;
    
    public boolean hasTriplesHavingSubject(String subject, String... graphspaces) throws QueryException;
    
    public boolean hasTriplesHavingPredicate(String predicate, String... graphspaces) throws QueryException;
    
    public boolean hasTriplesHavingObject(String object, String... graphspaces) throws QueryException;
    
    public boolean hasTriplesHavingLiteralObject(String literalObject, String... graphspaces) throws QueryException;
    
    public Collection<Map<String,String>> evaluateSparql(String sparqlExpression) throws QueryException;
}

package gr.forth.ics.isl.triplestoremethods.api;

import gr.forth.ics.isl.triplestoremethods.exceptions.QueryException;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.lang3.tuple.Triple;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public interface Query {
    public Collection<Triple> getTriplesWith(String subject, String predicate, String object, String... graphspaces) throws QueryException;
    
    public Collection<Triple> getTriplesHavingSubject(String subject, String... graphspaces) throws QueryException;
    
    public Collection<Triple> getTriplesHavingPredicate(String predicate, String... graphspaces) throws QueryException;
    
    public Collection<Triple> getTriplesHavingObject(String object, String... graphspaces) throws QueryException;
    
    public Collection<Triple> getTriplesHavingLiteralObject(String literalObject, String... graphspaces) throws QueryException;
    
    public boolean hasTriplesWith(String subject, String predicate, String object, String... graphspaces) throws QueryException;
    
    public boolean hasTriplesHavingSubject(String subject, String... graphspaces) throws QueryException;
    
    public boolean hasTriplesHavingPredicate(String predicate, String... graphspaces) throws QueryException;
    
    public boolean hasTriplesHavingObject(String object, String... graphspaces) throws QueryException;
    
    public boolean hasTriplesHavingLiteralObject(String literalObject, String... graphspaces) throws QueryException;
    
    public Collection<Map<String,String>> evaluateSparql(String sparqlExpression) throws QueryException;
}

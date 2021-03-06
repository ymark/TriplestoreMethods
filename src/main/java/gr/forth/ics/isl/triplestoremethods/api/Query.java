package gr.forth.ics.isl.triplestoremethods.api;

import gr.forth.ics.isl.triplestoremethods.exceptions.QueryException;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.lang3.tuple.Triple;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public interface Query {
    
    /** Returns the triples that exist in the given named graphs that contain the given resources.
     * The given object can be either the URI of a resource or a literal value.
     * If the given object is a literal value then the method return all the triples 
     * that contain it as part of their object value (it does not have to match perfectly). 
     * 
     * @param subject the subject of the triple (it should be the URI of the resource)
     * @param predicate the predicate of the triple (it should be the URI of the resource)
     * @param object the object of the triple (it may be either the URI of the resource, or a literal value)
     * @param graphspaces the named graphs where the triples should be searched 
     * @return a collection of triples containing the given parameters
     * @throws QueryException for any error that might occur during the evaluation of the query */
    public Collection<Triple<String,String,String>> getTriplesWith(String subject, String predicate, String object, String... graphspaces) throws QueryException;
    
    /** Returns the triples that exist in the given named graphs that contain the given resource
     * as a subject (triple = [subject, predicate, object]).
     * 
     * @param subject the subject of the triple (it should be the URI of the resource)
     * @param graphspaces the named graphs where the triples should be searched 
     * @return a collection of triples containing the given resource as their subject
     * @throws QueryException for any error that might occur during the evaluation of the query */
    public Collection<Triple<String,String,String>> getTriplesHavingSubject(String subject, String... graphspaces) throws QueryException;
    
    /** Returns the triples that exist in the given named graphs that contain the given resource
     * as a predicate (triple = [subject, predicate, object]).
     * 
     * @param predicate the predicate of the triple (it should be the URI of the resource)
     * @param graphspaces the named graphs where the triples should be searched 
     * @return a collection of triples containing the given resource as their predicate
     * @throws QueryException for any error that might occur during the evaluation of the query */
    public Collection<Triple<String,String,String>> getTriplesHavingPredicate(String predicate, String... graphspaces) throws QueryException;
    
    /** Returns the triples that exist in the given named graphs that contain the given resource
     * as an object (triple = [subject, predicate, object]). In this case the object should be the URI 
     * of a resource.
     * 
     * @param objectURI the object of the triple (it should be the URI of the resource)
     * @param graphspaces the named graphs where the triples should be searched 
     * @return a collection of triples containing the given resource as their object
     * @throws QueryException for any error that might occur during the evaluation of the query */
    public Collection<Triple<String,String,String>> getTriplesHavingObject(String objectURI, String... graphspaces) throws QueryException;
    
    /** Returns the triples that exist in the given named graphs that contain the given resource
     * as an object (triple = [subject, predicate, object]). In this case the object will be treated as a literal 
     * value, therefore it will return the triples whose object value contains among others the given object.
     * The method does not perform exact matching and is case insensitive.
     * 
     * @param literalObject the object of the triple (it will be treated as a literal value)
     * @param graphspaces the named graphs where the triples should be searched 
     * @return a collection of triples containing the given resource as their object
     * @throws QueryException for any error that might occur during the evaluation of the query */
    public Collection<Triple<String,String,String>> getTriplesHavingLiteralObject(String literalObject, String... graphspaces) throws QueryException;
    
    /** Checks if there is a triple that exist in the given named graphs that contain the given resources.
     * The given object can be either the URI of a resource or a literal value.
     * If the given object is a literal value then the method return all the triples 
     * that contain it as part of their object value (it does not have to match perfectly). 
     * 
     * @param subject the subject of the triple (it should be the URI of the resource)
     * @param predicate the predicate of the triple (it should be the URI of the resource)
     * @param object the object of the triple (it may be either the URI of the resource, or a literal value)
     * @param graphspaces the named graphs where the triples should be searched 
     * @return true if there is a triple with the given parameters, otherwise false
     * @throws QueryException for any error that might occur during the evaluation of the query */
    public boolean hasTriplesWith(String subject, String predicate, String object, String... graphspaces) throws QueryException;
    
    /** Checks if there are triples that exist in the given named graphs that contain the given resource
     * as a subject (triple = [subject, predicate, object]).
     * 
     * @param subject the subject of the triple (it should be the URI of the resource)
     * @param graphspaces the named graphs where the triples should be searched 
     * @return true if there is a triple with the given parameters, otherwise false
     * @throws QueryException for any error that might occur during the evaluation of the query */
    public boolean hasTriplesHavingSubject(String subject, String... graphspaces) throws QueryException;
    
    /** Checks if there are triples that exist in the given named graphs that contain the given resource
     * as a predicate (triple = [subject, predicate, object]).
     * 
     * @param predicate the predicate of the triple (it should be the URI of the resource)
     * @param graphspaces the named graphs where the triples should be searched 
     * @return true if there is a triple with the given parameters, otherwise false
     * @throws QueryException for any error that might occur during the evaluation of the query */
    public boolean hasTriplesHavingPredicate(String predicate, String... graphspaces) throws QueryException;
    
    /** Checks if there are triples that exist in the given named graphs that contain the given resource
     * as an object (triple = [subject, predicate, object]). The object should be the URI 
     * of a resource
     * 
     * @param objectURI the object of the triple (it should be the URI of the resource)
     * @param graphspaces the named graphs where the triples should be searched 
     * @return true if there is a triple with the given parameters, otherwise false
     * @throws QueryException for any error that might occur during the evaluation of the query */
    public boolean hasTriplesHavingObject(String objectURI, String... graphspaces) throws QueryException;
    
    /** Checks if there are triples that exist in the given named graphs that contain the given resource
     * as an object (triple = [subject, predicate, object]). In this case the object will be treated as a literal 
     * value, therefore it will check if there are triples whose object value contains among others the given object.
     * The method does not perform exact matching and is case insensitive.
     * 
     * @param literalObject the object of the triple (it will be treated as a literal value)
     * @param graphspaces the named graphs where the triples should be searched 
     * @return true if there is a triple with the given parameters, otherwise false
     * @throws QueryException for any error that might occur during the evaluation of the query */
    public boolean hasTriplesHavingLiteralObject(String literalObject, String... graphspaces) throws QueryException;
    
    /** Evaluates the given SPARQL expression and returns the results as a collection of Maps. 
     * The maps in the collection contains key-value pairs where keys refer to the variables that 
     * exist in the SPARQL query and values contain the actual values of these variables in the result.
     * For example for the query: select ?s ?p ?o ... the variables will be s, p, and o. 
     * 
     * @param sparqlExpression a valid SPARQL expression 
     * @return the results of the given SPARQL expression as a collection of maps
     * @throws QueryException for any error that might occur during the evaluation of the query */
    public Collection<Map<String,String>> evaluateSparql(String sparqlExpression) throws QueryException;
}

package gr.forth.ics.isl.triplestoremethods.exceptions;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public class QueryException extends Exception{
    public QueryException(){
        super("An error occurred while querying the triplestore");
    }
    
    public QueryException(String msg){
        super(msg);
    }
    
    public QueryException(String msg, Throwable thr){
        super(msg,thr);
    }
}
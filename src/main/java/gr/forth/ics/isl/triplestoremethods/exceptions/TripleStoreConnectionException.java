package gr.forth.ics.isl.triplestoremethods.exceptions;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public class TripleStoreConnectionException extends Exception{
    public TripleStoreConnectionException(){
        super("An error occurred while connection to the triplestore");
    }
    
    public TripleStoreConnectionException(String msg){
        super(msg);
    }
    
    public TripleStoreConnectionException(String msg, Throwable thr){
        super(msg,thr);
    }
}
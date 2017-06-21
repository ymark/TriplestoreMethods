package gr.forth.ics.isl.triplestoremethods.exceptions;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public class ImporterException extends Exception{
    public ImporterException(){
        super("An error occurred while importing data to the triplestore");
    }
    
    public ImporterException(String msg){
        super(msg);
    }
    
    public ImporterException(String msg, Throwable thr){
        super(msg,thr);
    }
}
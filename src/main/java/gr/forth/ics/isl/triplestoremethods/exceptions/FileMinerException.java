package gr.forth.ics.isl.triplestoremethods.exceptions;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public class FileMinerException extends Exception{    
    public FileMinerException(String msg){
        super(msg);
    }
    
    public FileMinerException(String msg, Throwable thr){
        super(msg,thr);
    }
}
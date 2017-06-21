package gr.forth.ics.isl.triplestoremethods.exceptions;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public class ExporterException extends Exception{
    public ExporterException(){
        super("An error occurred while exporting data from the triplestore");
    }
    
    public ExporterException(String msg){
        super(msg);
    }
    
    public ExporterException(String msg, Throwable thr){
        super(msg,thr);
    }
}
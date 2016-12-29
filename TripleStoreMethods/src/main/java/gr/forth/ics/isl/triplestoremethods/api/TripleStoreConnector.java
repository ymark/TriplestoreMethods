package gr.forth.ics.isl.triplestoremethods.api;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public interface TripleStoreConnector {

    public Importer importer();
    
    public Exporter exporter();
    
    public Query query();
    
    public Insert insert();
    
    public Update update();
    
    public Delete delete();   
}
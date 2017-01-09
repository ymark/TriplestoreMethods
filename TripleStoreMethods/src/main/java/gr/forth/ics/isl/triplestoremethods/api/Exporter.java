package gr.forth.ics.isl.triplestoremethods.api;

import org.openrdf.rio.RDFFormat;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public interface Exporter {
    public String exportToString(String graphspace, RDFFormat format);
    
    public String exportToFile(String graphspace, RDFFormat format);
}
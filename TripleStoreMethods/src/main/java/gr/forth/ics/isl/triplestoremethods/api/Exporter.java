package gr.forth.ics.isl.triplestoremethods.api;

import gr.forth.ics.isl.triplestoremethods.exceptions.ExporterException;
import org.openrdf.rio.RDFFormat;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public interface Exporter {
    public String exportToString(String graphspace, RDFFormat format) throws ExporterException;
    
    public String exportToFile(String graphspace, RDFFormat format) throws ExporterException;
}
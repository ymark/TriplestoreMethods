package gr.forth.ics.isl.triplestoremethods.api;

import gr.forth.ics.isl.triplestoremethods.common.RdfFormat;
import gr.forth.ics.isl.triplestoremethods.exceptions.ExporterException;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public interface Exporter {
    public String exportToString(String graphspace, RdfFormat format) throws ExporterException;
    
    public String exportToFile(String graphspace, RdfFormat format) throws ExporterException;
}
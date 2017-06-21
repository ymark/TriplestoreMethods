package gr.forth.ics.isl.triplestoremethods.api;

import gr.forth.ics.isl.triplestoremethods.common.RdfFormat;
import gr.forth.ics.isl.triplestoremethods.exceptions.ExporterException;
import java.io.File;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public interface Exporter {
    public String exportToString(String graphspace, RdfFormat format) throws ExporterException;
    
    public void exportToFile(File file, String graphspace, RdfFormat format) throws ExporterException;
}
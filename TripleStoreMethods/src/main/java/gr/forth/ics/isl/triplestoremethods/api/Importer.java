package gr.forth.ics.isl.triplestoremethods.api;

import gr.forth.ics.isl.triplestoremethods.common.RdfFormat;
import gr.forth.ics.isl.triplestoremethods.exceptions.ImporterException;
import java.io.File;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public interface Importer {
    public void importResource(File file, String graphspace, RdfFormat format) throws ImporterException;
    
    public void importResource(String content, String graphspace, RdfFormat format) throws ImporterException;
    
    public void importResources(String graphspace, RdfFormat format, File ... files) throws ImporterException;
    
    public void importResources(String graphspace, RdfFormat format, String ... files) throws ImporterException;
    
    public void importResourcesFromFolder(File folder, String graphspace, boolean recursiveImport, RdfFormat format) throws ImporterException;
}
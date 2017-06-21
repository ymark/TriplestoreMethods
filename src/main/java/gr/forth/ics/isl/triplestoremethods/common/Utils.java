package gr.forth.ics.isl.triplestoremethods.common;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.Logger;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public class Utils {
    private static final Logger logger=Logger.getLogger(Utils.class);
    
    /** Check if the given string forms a valid URI.
     * Valid URIs are considered the valid URLs (e.g. http://example.com, or https://example.com)
     * as well as resources following the URN scheme (e.g. urn:uuid:f81d4fae-7dec-11d0-a765-00a0c91e6bf6)
     * 
     * @param uri the string that will be checked if it forms a valid URI or not
     * @return true if the given string is a valid URI, otherwise false */
    public static boolean isValidURI(String uri){
        if(uri.toLowerCase().startsWith(Common.URN_PREFIX)){
            return true;
        }else{
            try{
                new URL(uri);
                return true;
            }catch(MalformedURLException ex){
                logger.debug("The given string is not a valid URI ("+uri+")",ex);
                return false;
            }
        }
    }
}
package gr.forth.ics.isl.triplestoremethods.common;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.Logger;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public class Utils {
    private static final Logger logger=Logger.getLogger(Utils.class);
    
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
package gr.forth.ics.isl.triplestoremethods;

import gr.forth.ics.isl.triplestoremethods.api.TripleStoreConnector;
import gr.forth.ics.isl.triplestoremethods.exceptions.TripleStoreConnectionException;
import gr.forth.ics.isl.triplestoremethods.impl.virtuoso.VirtuosoConnector;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public class TripleStoreFactory {
    public static TripleStoreConnector connectToVirtuoso(String host, int port, String username, String password) throws TripleStoreConnectionException{
        return new VirtuosoConnector(host, port, username, password);
    }
}

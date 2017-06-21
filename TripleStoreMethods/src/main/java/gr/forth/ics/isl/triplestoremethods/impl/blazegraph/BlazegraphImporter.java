package gr.forth.ics.isl.triplestoremethods.impl.blazegraph;

import com.bigdata.rdf.sail.webapp.SD;
import com.bigdata.rdf.sail.webapp.client.RemoteRepository;
import com.bigdata.rdf.sail.webapp.client.RemoteRepositoryManager;
import gr.forth.ics.isl.triplestoremethods.api.Importer;
import gr.forth.ics.isl.triplestoremethods.common.RdfFormat;
import gr.forth.ics.isl.triplestoremethods.exceptions.ImporterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.log4j.Logger;
import org.eclipse.jetty.client.HttpClient;
import org.openrdf.model.Statement;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.rio.RDFFormat;

/**
 * @author Yannis Marketakis (marketak 'at' ics 'dot' forth 'dot' gr)
 */
public class BlazegraphImporter implements Importer{
    private static final Logger logger=Logger.getLogger(BlazegraphImporter.class);
    private String serviceUrl;
    
    public BlazegraphImporter(String serviceURL){
        this.serviceUrl=serviceURL;
    }

    @Override
    public void importResource(File file, String graphspace, RdfFormat format) throws ImporterException {
        logger.debug("Importing file("+file.getAbsolutePath()+") in graphspace ("+graphspace+") with format ("+format+")");
        HttpClient httpClient=new HttpClient();
        ExecutorService executor=Executors.newCachedThreadPool();
        try {
            httpClient.start();
            final RemoteRepositoryManager repoManager = new RemoteRepositoryManager(this.serviceUrl, httpClient, executor);
            this.namespaceConnect(repoManager,graphspace);
            loadDataFromInputStream(repoManager,graphspace, new FileInputStream(file), RDFFormat.N3);
            httpClient.stop();
            repoManager.close();
        }catch(Exception ex){
            logger.error("An error occured while importing resources",ex);
            throw new ImporterException("An error occured while importing resources",ex);
        }finally {
            executor.shutdownNow();
            httpClient.destroy();
            logger.debug("terminates http clients");
        }
    }

    @Override
    public void importResource(String content, String graphspace, RdfFormat format) throws ImporterException {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void importResources(String graphspace, RdfFormat format, File... files) throws ImporterException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void importResourcesFromFolder(File folder, String graphspace, boolean recursiveImport, RdfFormat format) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private boolean namespaceExists(RemoteRepositoryManager repoManager, String namespace) throws Exception {
        final GraphQueryResult res = repoManager.getRepositoryDescriptions();
        try {
            while (res.hasNext()) {
                final Statement stmt = res.next();
                if (stmt.getPredicate().toString().equals(SD.KB_NAMESPACE.stringValue())) {
                    if (namespace.equals(stmt.getObject().stringValue())) {
                        return true;
                    }
                }
            }
        } finally {
            res.close();
        }
        return false;
    }
    
    private void namespaceConnect(RemoteRepositoryManager repoManager,String graphspace) throws Exception{
        final Properties properties = new Properties();
        properties.setProperty("com.bigdata.rdf.sail.namespace", graphspace);
        if (!namespaceExists(repoManager, graphspace)) {
                logger.debug(String.format("Create namespace %s...", graphspace));
                repoManager.createRepository(graphspace, properties);
                logger.debug(String.format("Create namespace %s done", graphspace));
        } else {
                logger.debug(String.format("Namespace %s already exists", graphspace));
        }
    }

//    private void loadDataFromResource(RemoteRepositoryManager repoManager, String namespace, String resource, RDFFormat format) throws Exception{
//        logger.debug("Ingesting source  "+resource+" ...");
//        final InputStream is = new FileInputStream(new File(resource));
//        if (is == null) {
//            throw new IOException("Could not locate resource: " + resource);
//        }
//        try {
//            repoManager.getRepositoryForNamespace(namespace).add(new RemoteRepository.AddOp(is, format));
//        } finally {
//            is.close();
//        }
//        logger.debug("Done");
//    }

    private void loadDataFromInputStream(RemoteRepositoryManager repoManager, String namespace, InputStream inputStream, RDFFormat format) throws Exception{
        logger.debug("Loading data from inputstream");
        try {
            repoManager.getRepositoryForNamespace(namespace).add(new RemoteRepository.AddOp(inputStream, format));
        } finally {
            inputStream.close();
        }
        logger.debug("Done");
    }

}

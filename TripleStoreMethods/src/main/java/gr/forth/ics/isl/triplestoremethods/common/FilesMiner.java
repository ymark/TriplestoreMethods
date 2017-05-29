package gr.forth.ics.isl.triplestoremethods.common;


import gr.forth.ics.isl.triplestoremethods.exceptions.FileMinerException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * @author Yannis Marketakis (yannismarketakis 'at' gmail 'dot' com)
 */
public class FilesMiner {
    private static final Logger logger=Logger.getLogger(FilesMiner.class);
        
    public static List<File> traverseFolderContents(String initialFolder, boolean recursiveSearch) throws FileMinerException{
        logger.debug("Traversing folder contents starting from "+initialFolder+" with recursive option: "+recursiveSearch);
        List<File> files=new ArrayList<>();
        File startDir=new File(initialFolder);
        if(!startDir.isDirectory())
            throw new FileMinerException("The given starting folder \""+initialFolder+"\" is not a directory");
        List<File> foldersToCheck=new ArrayList<>();
        for(File f : startDir.listFiles()){
            if(f.isFile()){
                if(f.getName().startsWith(".")){
                    ;
                }else{
                    files.add(f);
                }
            }else if(f.isDirectory()){
                if(f.getName().startsWith(".")){
                    ;
                }else{
                    foldersToCheck.add(f);
                }
            }
        }
        if(recursiveSearch){
            while(!foldersToCheck.isEmpty()){
                File fold=foldersToCheck.remove(0);
                for(File f : fold.listFiles()){
                    if(f.isFile()){
                        if(f.getName().startsWith(".")){
                            ;
                        }else{
                            files.add(f);
                        }
                    }else if(f.isDirectory()){
                        if(f.getName().startsWith(".")){
                            ;
                        }else{
                            foldersToCheck.add(f);
                        }
                    }
                }
            }
        }
        return files;
    }
}

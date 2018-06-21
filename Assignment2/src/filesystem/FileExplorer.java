package filesystem;

import java.util.ArrayList;

public class FileExplorer {

  private static Directory rootDirectory;
  
  private Directory workingDirectory;
  
  // Default constructor
  public FileExplorer() {
    
    //root = new Directory();
  }
  
  public static Directory getRootDirectory() {
    
    return rootDirectory;
  }
  
  public Directory getWorkingDirectory() {
    
    return workingDirectory;
  }
  
  public void setWorkingDirectory(Directory newWD) {
    
    workingDirectory = newWD;
  }
  
  // Returns the file at the given path
  public File getFile(String path) {
    
    String nextDirectory = Path.getRootDirectory(path);
    String subPath = Path.getSubPath(path);
    
    if (Path.isAbsolute(path)) {
      // Then the path is relative to the rootDirectory
    }
    else {
      // The path is relative to the workingDirectory
    }
    
    return null;
  }
  
  private Directory getDirectory(Directory startDirectory, String relativePath) {
    
    String nextDirName = Path.getRootDirectory(relativePath);
    String subPath = Path.getSubPath(relativePath);
    
    ArrayList<File> files = (ArrayList<File>)startDirectory.getFileContents();
    
    int index = 0;
    Directory nextDirectory = null;
    
    // Iterate over the current directory looking for the file with the name
    // of the next directory
    while (index < files.size() && nextDirectory == null) {
      
      // Get the name of the current file we are looking at
      String fileName = files.get(index).getFileName();
      
      // If we have found the next directory exit the loop. Otherwise go to
      // the next file in the current directory.
      if (fileName.equals(nextDirName))       
        nextDirectory = (Directory)files.get(index);
      else
        index++;
    }
    
    // If we were able to find the next directory then go to it. Otherwise the
    // path does not exist.
    Directory result = null;
    
    if (nextDirectory != null)
      result = getDirectory(nextDirectory, subPath);
    
    return result;
  }

  
  // Adds the file at the given path
  public void addFile(String path, File file) {
    
    return;
  }
  
  // Returns whether that path exists in the file explorer
  public boolean pathExists(String path) {
    
    return false;
  }
}

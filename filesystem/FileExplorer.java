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
    
    // This is the directory containing the file at the path.
    File file;
    
    if (Path.isAbsolute(path)) {
      // Then the path is relative to the rootDirectory
      file = getFileHelper(rootDirectory, path);
    }
    else {
      // The path is relative to the workingDirectory
      file = getFileHelper(workingDirectory, path);
    }
    
    return file;
  }
  
  private File getFileHelper(File file, String relativePath) {
    
    File result = null;
    
    if (file.isDirectory()) {
      
      Directory dir = (Directory)file;
      
      String nextFileName = Path.getRootDirectory(relativePath);
      
      ArrayList<File> files = (ArrayList<File>)dir.getFileContents();
      
      int index = 0;
      File nextFile = null;
      
      // Iterate over the current directory looking for the file with the name
      // of the next directory
      while (index < files.size() && nextFile == null) {
        
        // Get the name of the current file we are looking at
        String fileName = files.get(index).getFileName();
        
        // If we have found the next directory exit the loop. Otherwise go to
        // the next file in the current directory.
        if (fileName.equals(nextFileName))       
          nextFile = files.get(index);
        else
          index++;
      }
      
      // If we were able to find the next file then go to it. Otherwise the
      // path does not exist.     
      if (nextFile != null) {
        String subPath = Path.getSubPath(relativePath);
        result = getFileHelper(nextFile, subPath);
      }
    }
    else {
      if (file.getFileName().equals(relativePath))
        result = file;
    }
    
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

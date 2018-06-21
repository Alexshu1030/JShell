package filesystem;

public class FileExplorer {

  private static Directory root;
  
  // Default constructor
  public FileExplorer() {
    
    //root = new Directory();
  }
  
  public static Directory getRootDirectory() {
    
    return root;
  }
  
  // Returns the file at the given path
  public File getFile(String path) {
    
    return null;
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

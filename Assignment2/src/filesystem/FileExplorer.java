package filesystem;

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
    
    Directory dir = getDirectory(path);
    String fileName = Path.getFileName(path);
    File file = dir.getFile(fileName);
    
    return file;
  }
  
  public Directory getDirectory(String path) {
    
    String dirPath = Path.removeFileName(path);

    Directory rootDir;
    
    if (Path.isAbsolute(path))
      rootDir = rootDirectory;
    else
      rootDir = workingDirectory;
    
    return getDirectoryHelper(rootDir, dirPath);
  }
  
  private Directory getDirectoryHelper(Directory curDir, String relPath) {
    
    Directory dir;
    
    if (relPath.equals("")) {
      dir = curDir;
    }
    else {
      
      String nextDirName = Path.getRootDirectory(relPath);
      Directory nextDir = (Directory)curDir.getFile(nextDirName);
      
      if (nextDir != null) {
        String newRelPath = Path.getSubPath(relPath);
        dir = getDirectoryHelper(nextDir, newRelPath);
      }
      else {
        dir = null;
      }
    }
    
    return dir;
  }

  
  // Adds the file at the given path
  public void addFile(String path, File file) {
    
    Directory dir = getDirectory(path);
    dir.addFile(file);
  }
  
  // Returns whether that path exists in the file explorer
  public boolean pathExists(String path) {
    
    File file = getFile(path);
    
    return file != null;
  }
}

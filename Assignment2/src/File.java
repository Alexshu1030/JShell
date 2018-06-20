public class File<T> {
  private String fileName;
  private Directory fileDirectory;
  private T fileContents;
  public File(String name, String directory, T contents) {
    // Constructor for file class
    setFileName(name);
    setFileDirectory(directory);
    setFileContents(contents);
  }
  
  private void setFileName(String name) {
    this.fileName = name;
  }
  private void setFileDirectory(Directory directory) {
    this.fileDirectory = directory;
  }
  private void setFileContents(T contents) {
    this.fileContents = contents;
  }
  
  public String getFileName() {
    return this.fileName;
  }
  
  public Object getFileDirectory() {
    return this.fileDirectory;
  }
  public String getFileContents() {
    return this.fileContents;
  }
  public boolean isDirectory() {
    return false; 
  }
  
}

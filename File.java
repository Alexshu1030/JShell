public class File {
  private String fileName = "";
  private String fileDirectory = "";
  private String fileContents = "";
  public File(String name, String directory, String contents) {
    // Constructor for file class
    setFileName(name);
    setFileDirectory(directory);
    setFileContents(contents);
  }
  
  private void setFileName(String name) {
    this.fileName = name;
  }
  private void setFileDirectory(String directory) {
    this.fileDirectory = directory;
  }
  private void setFileContents(String contents) {
    this.fileContents = contents;
  }
  
  public String getFileName() {
    return this.fileName;
  }
  
  public String getFileDirectory() {
    return this.fileDirectory;
  }
  public String getFileContents() {
    return this.fileContents;
  }
  public boolean isDirectory() {
    return false; 
  }
  
}

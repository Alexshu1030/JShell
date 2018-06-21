
public class Directory extends File {
  
  public Directory (String name, String directory) {
    super(name, directory, "");
  }
  
  public void addFile(File file) {
    this.fileContents += file.getFileName() + " ";
  }
  
  public void removeFile(String fileName) {
    String listOfFiles = (String) this.fileContents;
    this.fileContents = listOfFiles.replaceAll(fileName + " ", "");
  }
  
}

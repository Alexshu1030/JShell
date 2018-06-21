import java.util.ArrayList;

public class Directory extends File {
  ArrayList<File> listOfFiles = new ArrayList<File>();
  public Directory (String name, String directory) {
    super(name, directory, null);
    this.fileContents = listOfFiles;
  }
  
  public void addFile(File file) {
    listOfFiles.add(file);
    this.fileContents = listOfFiles;
  }
  
  public void removeFile(File file) {
    listOfFiles.remove(file);
    this.fileContents = listOfFiles;
  }
  
  public boolean isDirectory() {
    return true;
  }
}

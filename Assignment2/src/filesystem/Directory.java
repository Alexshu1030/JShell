package filesystem;

import java.util.ArrayList;

public class Directory extends File {
  // make a new arraylist of files 
  ArrayList<File> listOfFiles = new ArrayList<File>();
  // construct a new directory with its contents as the list of files
  public Directory (String name, Directory parentDirectory) {
    super(name, parentDirectory, null);
    this.fileContents = listOfFiles;
  }
  
  // add a file to the arraylist of files
  public void addFile(File file) {
    listOfFiles.add(file);
  }
  
  // remove a file from the arraylist of files
  public void removeFile(File file) {
    listOfFiles.remove(file);
  }
  
  // return true since the object is a directory
  public boolean isDirectory() {
    return true;
  }
}

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
  
  public File getFile(String fileName) {
    // iterate through list of files to find the destination file
    int index = 0;
    // set the file to be returned to be null
    File nextFile = null;
    // Iterate over the current directory looking for the file with the name
    // of the next directory
    while (index < listOfFiles.size() && nextFile == null) {

      // Get the name of the current file we are looking at
      String nextFileName = listOfFiles.get(index).getFileName();

      // If we have found the next directory exit the loop. Otherwise go to
      // the next file in the current directory.
      if (fileName.equals(nextFileName))
        nextFile = listOfFiles.get(index);
      else
        index++;
    }
    // return the found file, or null if nothing is found
    return nextFile;
  }
  
  // return true since the object is a directory
  public boolean isDirectory() {
    return true;
  }
}

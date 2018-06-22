// **********************************************************
// Assignment2:
// Student1: Ciaran Hogan
// UTORID user_name: hogancia
// UT Student #: 1003796859
// Author: Ciaran Hogan
//
// Student2: Jeremy Tanuan
// UTORID user_name: tanuanje
// UT Student #: 1003939982
// Author: Jeremy Tanuan
//
// Student3: Aster Wu
// UTORID user_name: wuaster
// UT Student #: 1004175117
// Author: Aster Wu
//
// Student4: Yan Chen Huang
// UTORID user_name: huan1085
// UT Student #: 1004325857
// Author: Yan Chen Huang
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
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

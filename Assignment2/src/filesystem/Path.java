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

public class Path {
  
  public static String getSubPath(String path) {
    
    // Get the first slash in the path.
    int firstSlash = path.indexOf('/');
    
    String result = "";
    
    // If there is a first slash, then return everything after it. Otherwise
    // this is a folder or file and there is no subpath.
    if (firstSlash != -1)
      result = path.substring(firstSlash + 1);
    
    return result;
  }
  
  public static String getRootDirectory(String path) {
    
    // If the path is absolute then we want to ignore the first character
    // (which is a slash)
    if (isAbsolute(path))
      path = path.substring(1);
     
    
    // Get the first slash in the path
    int firstSlash = path.indexOf('/');
    
    String result = "";
    
    if (firstSlash != -1)
      // Return everything up to the slash. This is the root directory of the
      // path.
      result = path.substring(0, firstSlash);
    else {
      // Since there is no slash in the path, this must be a file or folder
      // name. If it is a folder then we want to return it. Otherwise we just
      // want to return the root directory.
      if (getFileExtension(path) == "")
        result = path;
    }
    
    return result;
  }
  
  public static boolean isDirectory(String path) {
    
    // If the file has no extension then it is a folder
    return getFileExtension(path).equals("");
  }
  
  public static String getFileName(String path) {
       
    // Get the last slash in the path. The name starts at the next index. Also
    // if there is no slash and this is just the name of a file or folder,
    // then this will be zero and it will return the path unaltered, as wanted.
    int nameStartIndex = path.lastIndexOf('/') + 1;
    
    return path.substring(nameStartIndex);
  }
  
  public static String getFileExtension(String path) {

    // Get the last decimal in the path.
    int decimalIndex = path.lastIndexOf('.');
    
    String result = "";
    
    // We want to return all of the text after the decimal. If there is no
    // decimal, then it is a folder and we want to return an empty string.
    if (decimalIndex != -1)
      result = path.substring(decimalIndex);
      
    return result;
  }
  
  public static boolean isAbsolute(String path) {
    
    boolean isAbs = false;
    
    if (path != "") {
      // Get the first character in the path
      char firstChar = path.charAt(0);
      // If the first character is a slash then it is an absolute path.
      isAbs = firstChar == '/';
    }
   
    return isAbs;
  }
  
  public static String removeFileName(String path) {
    
    // Get the last slash in the path
    int nameStartIndex = path.lastIndexOf('/');
    
    String pathWithoutFileName = "";
    
    // If there is no slash in the path. Then this is the name of a file or
    // folder. Therefore we need to return an empty string.
    if (nameStartIndex != -1)
      pathWithoutFileName = path.substring(0, nameStartIndex);
    
    return pathWithoutFileName;
  }
}

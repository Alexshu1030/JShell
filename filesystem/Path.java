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
    
    int firstSlash = path.indexOf('/');
    
    String result = "";
    
    if (firstSlash != -1)
      result = path.substring(firstSlash + 1);
    
    return result;
  }
  
  public static String getRootDirectory(String path) {
    
    // If the path is absolute then we want to ignore the first character
    // (which is a slash)
    if (isAbsolute(path))
      path = path.substring(1);
      
    int firstSlash = path.indexOf('/');
    
    String result = "";
    
    if (firstSlash != -1)
      result = path.substring(0, firstSlash);
    else {
      if (getFileExtension(path) == "")
        result = path;
    }
    
    return result;
  }
  
  public static boolean isDirectory(String path) {
    
    return getFileExtension(path).equals("");
  }
  
  public static String getFileName(String path) {
        
    int nameStartIndex = path.lastIndexOf('/') + 1;
    
    return path.substring(nameStartIndex);
  }
  
  public static String getFileExtension(String path) {

    int decimalIndex = path.lastIndexOf('.');
    
    String result = "";
    
    if (decimalIndex != -1)
      result = path.substring(decimalIndex);
      
    return result;
  }
  
  public static boolean isAbsolute(String path) {
    
    // Get the first character in the path
    char firstChar = path.charAt(0);
   
    // If the first character is a slash then it is an absolute path.
    return firstChar == '/';
  }
  
  public static String removeFileName(String path) {
    
    int nameStartIndex = path.lastIndexOf('/');
    
    return path.substring(0, nameStartIndex);
  }
}

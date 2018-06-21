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
  
  public static String getFileName(String path) {
        
    int nameStartIndex = path.lastIndexOf('/') + 1;
    
    return path.substring(nameStartIndex);
  }
  
  public static String getFileExtension(String path) {

    int decimalIndex = path.indexOf('.');
    
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

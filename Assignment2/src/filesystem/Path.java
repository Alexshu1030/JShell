package filesystem;

public class Path {
  
  public static String getRootDirectory(String path) {
    
    int firstSlash = path.indexOf('/');
    
    return path.substring(0, firstSlash);
  }
  
  public static String getFileName(String path) {
        
    int decimalIndex = path.indexOf('.');
    int nameStartIndex = path.lastIndexOf('/');
    
    return path.substring(nameStartIndex, decimalIndex);
  }
  
  public static String getFileExtension(String path) {

    int decimalIndex = path.indexOf('.');
    
    return path.substring(decimalIndex);
  }
  
  public static boolean isAbsolute(String path) {
    
    // Get the first character in the path
    char firstChar = path.charAt(0);
   
    // If the first character is a slash then it is an absolute path.
    return firstChar == '/';
  }
}

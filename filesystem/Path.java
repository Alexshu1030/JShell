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

  /**
   * Returns the path relative to the root folder of the path. i.e.
   * //home//me//test goes to //me//test
   * 
   * @param path the path to be shortened
   * @return returns the sub path or an empty string if the path contains one
   *         or fewer files
   */
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

  /**
   * Returns the root directory of the path.
   * 
   * @param path the path you want to root directory of
   * @return the root directory
   */
  public static String getRootDirectory(String path) {

    // Get the first slash in the path
    int firstSlash = path.indexOf('/');

    //String result = "";
    String result = path;
    
    if (firstSlash != -1)
      // Return everything up to the slash. This is the root directory of the
      // path.
      result = path.substring(0, firstSlash);

    return result;
  }

  /**
   * Gets the name of the file (including the extension) specified by the path
   * 
   * @param path the path to the file
   * @return the name of the file
   */
  public static String getFileName(String path) {

    // Get the last slash in the path. The name starts at the next index. Also
    // if there is no slash and this is just the name of a file or folder,
    // then this will be zero and it will return the path unaltered, as wanted.
    int nameStartIndex = path.lastIndexOf('/') + 1;

    return path.substring(nameStartIndex);
  }

  /**
   * Returns whether the path is absolute or not. i.e. whether or not the path
   * starts from the root directory of the file system.
   * 
   * @param path the path
   * @return returns true if the path is absolute and false otherwise
   */
  public static boolean isAbsolute(String path) {

    boolean isAbs = false;

    if (!path.equals("")) {
      // Get the first character in the path
      char firstChar = path.charAt(0);
      // If the first character is a slash then it is an absolute path.
      isAbs = firstChar == '/';
    }

    return isAbs;
  }

  /**
   * Returns the path without the last file name.
   * 
   * @param path the path to the file
   * @return the path without the last file name
   */
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

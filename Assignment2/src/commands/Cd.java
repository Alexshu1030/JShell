package commands;

import filesystem.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Cd {
  public Cd (String path) {
    Directory currentDirectory = FileExplorer.getWorkingDirectory();
    if (path == "..") {
      FileExplorer.setWorkingDirectory(currentDirectory.getFileDirectory());
    }
    else {
      boolean valid = true;
      ArrayList<String> pathFolders = new ArrayList<String>();
      pathFolders = (ArrayList<String>) Arrays.asList(path.split("\\"));
      FileExplorer.setWorkingDirectory(FileExplorer.getRootDirectory());
      for (int i = 0; i < pathFolders.size(); i++) {
        if (valid) {
          boolean found = false;
          filesystem.File[] currentContents = (File[]) FileExplorer.getWorkingDirectory().getFileContents();
          for (int j = 0; j < currentContents.size(); j++) {
            if (currentContents[j].getFileName() == pathFolders.get(i)) {
              FileExplorer.setWorkingDirectory(currentContents[j]);
              found = true;
            }
            if (!found) {
              i = pathFolders.size();
              System.out.println("Folder not found");
            }
          }
        }
      }
    }
  }
}

package commands;
import java.util.ArrayList;
import filesystem.*;
public class Ls {
  public Ls(ArrayList<filesystem.File> folders) {
    ArrayList<filesystem.File> currentFolderContents = pwd().getFileContents();
    for (int i = 0; i < folders.size(); i++) {
      boolean found = false;
      for (int h = 0; h < currentFolderContents.size(); h++) {
        if (!found) {
          if (folders.get(i) == currentFolderContents.get(h)) {
            found = true;
          }
        }
      }
      if (found && folders.get(i).isDirectory() == true) {
        ArrayList<filesystem.File> listOfFiles = 
            (ArrayList<File>) folders.get(i).getFileContents();
        System.out.print(folders.get(i).getFileName() + ": ");
        for (int j = 0; j < listOfFiles.size(); j++) {
          System.out.print(listOfFiles.get(j));
        }
        System.out.println("");
      }
      else {
        if (found) {
          System.out.println(folders.get(i).getFileName());
        }
      }
    }
  }
}
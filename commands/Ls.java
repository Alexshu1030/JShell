package commands;
import java.util.ArrayList;
import filesystem.*;
public class Ls {
  public Ls(ArrayList<filesystem.File> folders) {
    for (int i = 0; i < folders.size(); i++) {
      if (folders.get(i).isDirectory() == true) {
        ArrayList<filesystem.File> listOfFiles = 
            (ArrayList<File>) folders.get(i).getFileContents();
        System.out.print(folders.get(i).getFileName() + ": ");
        for (int j = 0; j < listOfFiles.size(); j++) {
          System.out.print(listOfFiles.get(j));
        }
        System.out.println("");
      }
      else {
        System.out.println(folders.get(i).getFileName());
      }
    }
  }
}
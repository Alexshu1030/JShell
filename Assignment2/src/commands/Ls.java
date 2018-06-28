package commands;
import java.util.ArrayList;
import filesystem.*;
public class Ls {
  public Ls(ArrayList<filesystem.Directory> folders) {
    for (int i = 0; i < folders.size(); i++) {
      ArrayList<filesystem.File> listOfFiles = folders.get(i).getContents();
      for (int j = 0; j < listOfFiles.size(); j++) {
        System.out.print(folders.get(i).getContents.get(j));
      }
    }
  }
}

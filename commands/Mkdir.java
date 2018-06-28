package commands;
import java.util.ArrayList;
import filesystem.*;
public class Mkdir {
  public Mkdir(ArrayList<String> folders) {
    Directory current = FileExplorer.getWorkingDirectory();
    for (int i = 0; i < folders.size(); i++) {
      Directory f = new Directory(folders.get(i), FileExplorer.getWorkingDirectory());
    }
  }
}

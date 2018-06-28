package commands;
public class History {
  
  public History() {
    ArrayList<String> commands = new ArrayList<String>();
  }
  public void addCommand(String command) {
    commands.add(0, command);
  }
  public void outputHistory(int n) {
    for (int i = n; i<=commands.size(); i++) {
      
      System.out.println(i + ". " + commands[i-1]);
    }
  }
  public void outputHistory() {
    for (int i = 1; i <= commands.size(); i++) {
      System.out.println(i + ". " + commands[i-1]);
    }
  }
}
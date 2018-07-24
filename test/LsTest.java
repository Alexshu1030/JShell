package test;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import org.junit.*;
import commandsystem.*;
import filesystem.*;
import shell.JShellWindow;

public class LsTest {
  private static JShellWindow jShell;
  private static FileExplorer explorer;
  
  @BeforeClass
  public static void setUpClass() {
    Commands.initializeCommandHashTable();
  }
  
  @Before
  public void setUp() {
    jShell = new JShellWindow();
    explorer = jShell.getFileExplorer();
  }

  @After
  public void tearDown() throws NoSuchFieldException, SecurityException,
  IllegalArgumentException, IllegalAccessException {
    // Reset the static self reference in the FileExplorer class
    Field field = (explorer.getClass()).getDeclaredField("rootDirectory");
    field.setAccessible(true);
    field.set(null, null);
  }

  @Test
  public void testNoArguments() {
    explorer.setWorkingDirectory(explorer.getRootDirectory());
    Result resultActual = Commands.run(jShell, "ls");

    String actual = resultActual.getMessage();
    
    String expected = "";
    
    assertEquals(expected, actual);
  }
  
  @Test
  public void testRecursiveNoArguments() {
    explorer.setWorkingDirectory(explorer.getRootDirectory());
    Result resultActual = Commands.run(jShell, "ls -R");

    String actual = resultActual.getMessage();
    
    String expected = "/:\n\n";
    
    assertEquals(expected, actual);
  }

  @Test
  public void testMultiPathDir() {
    Commands.run(jShell, "mkdir top top/middle1 top/middle2 top/middle1/bottom");
    
    Result resultActual = Commands.run(jShell, "ls top");

    String actual = resultActual.getMessage();

    String expected = "/top:\nmiddle1\tmiddle2\n";

    assertEquals(expected, actual);
  }
  
  @Test
  public void testRecursiveMultiPathDir() {
    Commands.run(jShell, "mkdir t t/1 t/2 t/1/b");
    
    Result resultActual = Commands.run(jShell, "ls -R t");

    String actual = resultActual.getMessage();

    String expected = "/t:\n1\t2\n/t/1:\nb\n\n/t/2:\n\n/t/1/b:\n\n";

    assertEquals(expected, actual);
  }
}

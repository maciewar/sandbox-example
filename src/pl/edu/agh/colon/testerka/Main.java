package pl.edu.agh.colon.testerka;

import org.python.util.PythonInterpreter;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.out.println("Testerka: Greetings from Java");
        File scriptFile = new File("scripts/script.py");
        File sandboxedFile = new File("scripts/sandbox/script.py");
        try {
            System.out.println("Testerka: I'll move the script file to the sandbox");
            scriptFile.renameTo(sandboxedFile);
            final PythonInterpreter interpreter = new PythonInterpreter();
            interpreter.execfile("scripts/sandbox/script.py");
        } catch (Exception e) {
            System.out.println("Testerka: Wait a minute, you are not allowed to do that");
            System.out.println("Testerka: Your process is sentenced to death for");
            e.printStackTrace();
        } finally {
            System.out.println("Testerka: Alright, script has finished so I'll move the file outside the sandbox");
            sandboxedFile.renameTo(scriptFile);
            System.out.println("Testerka: My work here is done");
        }
    }
}

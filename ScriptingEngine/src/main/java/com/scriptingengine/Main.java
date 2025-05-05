package com.scriptingengine;

public class Main {
    public static void main(String[] args) throws Exception {
        ScriptEngineService service = new ScriptEngineService();

        String jsScript = "var x = 10 + 20; x;";
        Object jsResult = service.runScript("JavaScript", jsScript);
        System.out.println("JavaScript Result: " + jsResult);

        String pyScript = "a = 5 * 3\nresult = a";
        Object pyResult = service.runScript("Python", pyScript);
        System.out.println("Python Result: " + pyResult);
    }
}

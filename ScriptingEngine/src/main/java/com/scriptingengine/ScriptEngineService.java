package com.scriptingengine;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.python.util.PythonInterpreter;
import org.python.core.PyObject;

import java.util.concurrent.locks.ReentrantLock;

public class ScriptEngineService {

    private static final ReentrantLock lock = new ReentrantLock();

    public Object runScript(String language, String script) throws ScriptException {
        if (language.equalsIgnoreCase("JavaScript")) {
            return runJavaScript(script);
        } else if (language.equalsIgnoreCase("Python")) {
            return runPythonScript(script);
        } else {
            throw new IllegalArgumentException("Unsupported scripting language: " + language);
        }
    }

    private Object runJavaScript(String script) throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        if (engine == null) {
            throw new ScriptException("Nashorn engine not found. Add Nashorn dependency.");
        }
        return engine.eval(script);
    }

    private Object runPythonScript(String script) {
        lock.lock(); 
        try (PythonInterpreter interpreter = new PythonInterpreter()) {
            interpreter.exec(script);
            PyObject result = interpreter.get("result"); 
            return result != null ? result.__tojava__(Object.class) : null;
        } finally {
            lock.unlock();
        }
    }
}

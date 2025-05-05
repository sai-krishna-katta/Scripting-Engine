# Scripting-Engine

# Scripting Engine Utility – Java + JavaScript + Python

## Objective
This utility executes JavaScript and Python scripts **dynamically at runtime** from Java using the Java Virtual Machine (JVM), without calling external processes like `ProcessBuilder`.

---

## Features
- Supports dynamic execution of **JavaScript** (via Nashorn) and **Python** (via Jython).
- Returns the result of script execution as a Java `Object`.
- Thread-safe execution for Python (due to Jython's global interpreter lock).
- Ready to be extended with support for script file reading or more languages.

---

## Tools & Libraries Used

| Component         | Tool/Library                   | Version    |
|------------------|--------------------------------|------------|
| JavaScript Engine| [Nashorn Core](https://github.com/openjdk/nashorn) | 15.4       |
| Python Engine    | [Jython Standalone](https://www.jython.org/)     | 2.7.3      |
| Build Tool       | Maven                          | Latest     |
| IDE              | Eclipse                        | (Any version with Java 11+ support) |

---

## Project Structure

```
script-runner/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/
                └── scriptingengine/
                    ├── ScriptEngineService.java
                    └── Main.java
```

---

## Assumptions

- Python scripts define a variable named `result` which is returned to Java.
- JavaScript returns the last evaluated expression.
- External libraries in the scripting languages can be used if compatible with Nashorn/Jython.
- JVM versions above 11 do not include Nashorn by default, so it is explicitly added via Maven.

---

## How It Works

### Method Signature:
```java
Object runScript(String language, String script);
```

### Behavior:
- `language`: `"JavaScript"` or `"Python"`
- `script`: raw source code string
- The script is evaluated using the correct engine and returns a Java object representing the result.

---

## Thread Safety
- **Jython** (Python) execution is wrapped in a `ReentrantLock` to prevent concurrency issues, as it uses a shared global interpreter.

---

## Design Decisions
- Used Nashorn because it integrates tightly with the JVM and doesn’t need external processes.
- Used Jython to allow Python 2.7-compatible script execution entirely inside the JVM.
- Avoided `ProcessBuilder`, in line with the assignment constraints.
- Made `ScriptEngineService` a reusable, service-style utility that can be extended easily.

---

## Example Usage

**JavaScript:**
```javascript
var x = 10 + 20; x;
```

**Python:**
```python
a = 5 * 3
result = a
```

---

##  Optional Enhancements (Future Work)
- Support reading scripts from file.
- Add support for more scripting languages (e.g., Groovy, Kotlin).
- Return more detailed results like execution time, logs, or script context.
- Compile and cache script results for reuse.

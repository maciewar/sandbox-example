# sandbox-example

## A step-by-step experiment explanation

### Step 1
Testerka moves script file from scripts directory to sandbox directory. 
Security settings does not allow script to access other files than scripts/sandbox/script.py

### Step 2
Testerka creates instance of PythonInterpreter class which runs script file located in the sandbox directory.

### Step 3
Python script does some prints and basic arithmetics.

```python
print '\t2^10 = {}'.format(2 ** 10)
print '\t6/2(1+2) = {}'.format(1)  # xD
```

### Step 4 
_Rise of the Machines_. Python script tries to access /etc/passwd file

```python
f = open('/etc/passwd')
userdetails = f.read()
```
### Step 5
java.security.AccessControlException is thrown. Testerka catches exception and punishes PythonInterpreter.

### Step 6
Testerka captures standard output of the Python script and prints everything on the System.out

### Step 7
Testerka moves script back to its original path.

## Result of the experiment

```
Testerka: Greetings from Java
Testerka: I'll move the script file to the sandbox
Captured output:
Script: Greetings from sandbox
Script: I am alive and I can do math!
	2^10 = 1024
	6/2(1+2) = 1
Script: Now I'll try something illegal
Testerka: Wait a minute, you are not allowed to do that
Testerka: Your process is sentenced to death for
Traceback (most recent call last):
  File "scripts/sandbox/script.py", line 7, in <module>
    f = open('/etc/passwd')
java.security.AccessControlException: access denied ("java.io.FilePermission" "/etc/passwd" "read")
	at java.security.AccessControlContext.checkPermission(AccessControlContext.java:472)
	at java.security.AccessController.checkPermission(AccessController.java:884)
	at java.lang.SecurityManager.checkPermission(SecurityManager.java:549)
	at java.lang.SecurityManager.checkRead(SecurityManager.java:888)
	at java.io.RandomAccessFile.<init>(RandomAccessFile.java:229)
	at org.python.core.io.FileIO.fromRandomAccessFile(FileIO.java:184)
	at org.python.core.io.FileIO.<init>(FileIO.java:91)
	at org.python.core.PyFile.file___init__(PyFile.java:177)
	at org.python.core.PyFile$exposed___new__.createOfType(Unknown Source)
	at org.python.core.PyOverridableNew.new_impl(PyOverridableNew.java:12)
	at org.python.core.PyType.invokeNew(PyType.java:494)
	at org.python.core.PyType.type___call__(PyType.java:1706)
	at org.python.core.PyType.__call__(PyType.java:1696)
	at org.python.core.OpenFunction.__call__(__builtin__.java:1725)
	at org.python.core.PyObject.__call__(PyObject.java:461)
	at org.python.core.PyObject.__call__(PyObject.java:465)
	at org.python.pycode._pyx0.f$0(scripts/sandbox/script.py:9)
	at org.python.pycode._pyx0.call_function(scripts/sandbox/script.py)
	at org.python.core.PyTableCode.call(PyTableCode.java:167)
	at org.python.core.PyCode.call(PyCode.java:18)
	at org.python.core.Py.runCode(Py.java:1386)
	at org.python.core.__builtin__.execfile_flags(__builtin__.java:535)
	at org.python.util.PythonInterpreter.execfile(PythonInterpreter.java:286)
	at pl.edu.agh.colon.testerka.Main.main(Main.java:20)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:140)

java.security.AccessControlException: java.security.AccessControlException: access denied ("java.io.FilePermission" "/etc/passwd" "read")
Testerka: Alright, script has finished so I'll move the file outside the sandbox
Testerka: My work here is done
```

## Running the code

If you want to reporoduce results:
- put jython-standalone-2.7.0.jar into lib directory
- create scripts/sandbox directory
- set {path.to.intellij.installation} in security.policy appropriately or remove that file policy if you are not using IntelliJ
- run pl.edu.agh.colon.testerka.Main with JVM options: -Djava.security.manager -Djava.security.policy==security.policy

# mp-bit-trees-maven

A mini-project exploring bit trees (a form of binary tree) and their use in translating between alphabets, particularly in translating to and from braille.

**Authors**

* David William Stroud
* Samuel A. Rebelsky (starter code)

**Acknowledgements**

None.

**Instructions for use**

Compile the program before using it for the first time, as follows:
```bash
mvn clean compile -q
```

Then, execute the program as follows, where `<args>` is the command line arguments you would like to pass in:
```bash
mvn evec:java -q -Dexec.args="<args>"
```

Command-line arguments should be in the format `<command> <text>`. There are three supported commands:
* `braille` - converts `<text>` from ASCII to a Braille bit string.
* `ascii` - converts `<text>` from a Braille bit string to ASCII.
* `unicode` - converts `<text>` from ASCII to Braille unicode characters.

---

This code may be found at <https://github.com/dapper-gh/mp-bit-trees-maven>. The original code may be found at <https://github.com/Grinnell-CSC207/mp-bit-trees-maven>.

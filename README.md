# iCode
***Code on the go***

By Rahil Pasha

## Introduction

iCode is a programing language built on Java and optimized for mobile devices. iCode is written into a simple .txt file and is compiled into Java where it can be run on the JVM.

The basic commands (like getting inputs and printing outputs, creating variables, performing calculations, loops, and if statements) are represented using emojis. There are a total of 10 emoji commands. In combination with letters and numbers, the emojis help developers write iCode on the go.

Whether you're in a car, walking to school or work, standing in line, or lying on your couch, iCode allows you to quickly turn a moment of inspiration into a functional program. Down the line you can compile your code into Java and turn it into a fully fledged application.

## Emoji Library

| Emoji | Function            |
|:-----:|:--------------------|
|  🖨   | Print the output    |
|  🔡   | String key word     |
|  🔢   | int key word        |
|  📦   | Initialize variable |
|  ⌨️   | Get user input      |
|   ❓   | If statement        |
|  🔁   | Loop                |
|  ↪️   | Start code block    |
|  ↩️   | End code block      |
|  📝   | Comments            |

## Usage

To use iCode you first have to create a plain text file (.txt) to write your program. You can use the 10 emoji's above as shown in the example.txt file.

### Comments
The comment emoji is ignored by the compiler and can be used at the start of any line to document or note something about your code.

### Print Statements
To print, select the pinter emoji and then enter the string or int keyword emoji depending on what you want to print.

```
📝 Basic Print Statements
🖨 🔡 Hello World!
🖨 🔢 3 + 5
```
> The code above outputs:
> 
> Hello World!
> 
> 8

### Variables
Use the box emoji, to initialize a variable as a string or int. After selecting the box emoji, write the name of the variable followed by the variable type (string or int) and then the value of the variable.

```
📝 Storing Integers in Variables
📦 a 🔢 6
📦 b 🔢 2
📦 a 🔢 9
🖨 a + b
```
> Variables can be overwritten. For example, a changes from 6 to 9. Output:
> 
> 11

```
📝 Storing Strings in Variables
📦 c 🔡 iCode is awesome
🖨 c
```
> Output:
> 
> iCode is awesome

### Loops
To create a loop, use the loop emoji followed by a number that represents the number of repetitions you want. Then the next line has to have the opening code block emoji. The loop will repeat the code until it reaches the end code block emoji.

```
📝 Creating a Loop
🔁 5
↪️
🖨 🔡 This is a loop!
↩️
```
> Output:
> 
> This is a loop!
>
> This is a loop!
>
> This is a loop!
>
> This is a loop!
>
> This is a loop!

### Conditionals

The question mark emoji can be used to start an if statement. Follow this emoji by an expression using one of the comparators (=, >, <, >=, <=). If the statement is true, the compiler will execute all the code between the start and end code block emojis. Otherwise, it will skip all of that code.

```
📝 Using Conditional Statements
❓ b = 🔢 2
↪️
🖨 🔡 B is equal to 2
↩️
```
> Output:
> 
> B is equal to 2

### User input

When initializing a variable, you can use the keyboard emoji in place of the string or int variable type to get user input from the scanner. The variable can be used just like a regular string variable after the user enters something into the console.

```
📝 Getting Input from the User
📦 name ⌨️ Enter your name:
🖨 🔡 Hi
🖨 name
```
> This code outputs: "Enter your name: " and waits for the user to input their name.
> After the user enters their name, it gets stored the in the name variable and the program print out:
> 
> Hi
> 
> { name from user input }

### How to execute your code
After you are done writing your code in the plain text file, drag it into the same folder as the compiler. Then go into the main method and find the Parser initialization on line 15:
``` java
file = new Parser("/Users/rahilpasha/IdeaProjects/compiler/src/example.txt");
```
Then replace the example.txt file path with the file path of your own code.

Finally, run the compiler file and see your code in action.

## Compiler

### Main File
The main file creates an instance of the Parser object and initializes it with the file name. Then it calls the _execute_ method on the lines of code from the Parser.

The execute method takes in a 2-dimensional ArrayList of strings, which represents the lines of code it will execute.
The method loops through each of the lines and checks the first string to figure out which emoji it relates too. For print statements that require expressions to be evaluated the compiler calls on the _evaluate_ helper function.

If the line is initializes a variable, then the compiler calls on the Variable class and adds a new instance to the ArrayList of _variables_.

For if conditions, the equation is evaluated by the checkCondition helper function and the compareInt method. If the if-statement is true then a 2D ArrayList called _subfile_ is initialized. The compiler loops through the following lines until finding the end code block emoji and adds all the previous lines to the _subfile_. Then the execute method calls itself recursively using the _subfile_ method.

The same is done for loops except the execute method calls itself using the _subfile_ inside a for-loop with a specific number of repetitions.

### Parser Class

The Parser class has an instance variable called _lines_ that is a 2D ArrayList. In the constructor, the Parser takes a file path and uses the Scanner to take the contents of the file and transcribe it into the _lines_ variable. The getLines method returns the _lines_ variable to be used in the compiler.


### Variable Class

The _Variable_ class has 3 instance variables: _name_, _svalue_, and _ivalue_. The _svalue_ is a String which represents the string value of the variable, and the _ivalue_ is an int which represents the int value of the variable. The compiler is able to determine the variable type by checking whether the _svalue_ is null or not.

There are two custom constructors, one that accepts a String _name_ and String _svalue_, and another that accepts a String _name_ and int _ivalue_. There are getters for each of the three instance variables.

Apart from the instance variables, there is also one static variable called _variables_, which is an ArrayList of Variable objects. The addVariable method is used to add a variable to the _variables_ ArrayList. The find method is also static and is used to look up a _Variable_ based on its name and return it to the compiler to be used.

## Limitations and Future Work

While using a combination of these 10 emojis can be used to create simple programs, there is still much to be desired. For example, the only two data types are Strings and ints. In the future, this could be expanded to floats, booleans, lists, and more.

In addition, loops and _if_-statements cannot be nested within each-other, and there are no _else-if_ or _else_ statements available.

Future work can be done to expand the library of emojis and create mobile applications that allow for quick compiling and execution of the code. For example, instead of writing in a plain text file, which still requires a computer, developers could use Apple Notes or an equivalent application to jot down their code before converting that back into Java.

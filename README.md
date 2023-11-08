# Java Command Line Interpreter (CLI)
This Command Line Interpreter (CLI) should allow the user to enter the input through the keyboard. After the user writes the command and presses enter, the string is parsed, and the indicated command executed.
The CLI will keep accepting different commands from the user until the user writes
“exit”, then the CLI terminates.
If the user enters a wrong command or bad parameters (invalid path, file
instead of directory in certain commands, etc.), the program should print
some error messages without terminating


# Program Structure 
1- `Terminal` is the main class responsible for running the CLI. It contains methods to handle different commands and their arguments, as well as a method to run the CLI loop.

2- `Parser` is a separate class that handles parsing user input, extracting the command, arguments, and checking for output redirection symbols (">" and ">>").


# Supported Commands 
1. `echo`: Takes arguments and prints it.
2. `pwd`: Prints the current path.
3. `cd`: Changes the current directory.
4. `ls`:  Lists the contents of the current directory sorted alphabetically.
5. `ls -r`: Lists the contents of the current directory in reverse order.
6. `mkdir`: Creates a directory for each argument. 
7. `rmdir`: Removes given directory if it's empty.
8. `touch`: Creates file.
9. `cp`: Takes 2 arguments, both are files, and copies the first onto the second.
10. `cp -r`: Takes 2 arguments, both are directories (empty or not), and copies the first directory (with all its content) into the second one.
11. `rm`: Removes file.
12. `cat`:  Prints the file's content or takes 2 arguments and concatenates the content of the 2 files and prints it.
13. `wc`: Displays word count information, including the number of lines, words, characters with spaces, and the file name for a specified file.
14. `command > FileName`: Redirects the output of the first command to be written to a file. 
15. `command >> FileName`: Like `>`, but appends to the file if it exists.
16. `history`: Displays an enumerated list with the commands you've used in the past.


# Usage 
1- Run the prgram.

2- Enter commands and press Enter.

3- Enter new commands until enter "exit".

# Contributors
We would like to thank the following contributors to this project:

- [Ahmed Saad](https://github.com/ahmedsaad123456)
- [Ahmed Adel](https://github.com/Dola1122)
- [Shahd Salah](https://github.com/Unicorn2105)
- [Shrouk Tarek](https://github.com/shroukk7)

Special thanks to everyone who has helped make this project better.



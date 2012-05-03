QuickNoting v. 0.4
==================
A simple to use comandline tool to write quick messages in a specified repository to a textfile that name is the
current date.

Dependecies
-----------
* [Scala](http://www.scala-lang.org) -- 2.9 or later

Build
-----
Build this project using [sbt](https://github.com/harrah/xsbt).
Use sbt one-jar to create a executable jar-file in target/scala-2.9.1/

Usage
-----
Use java -jar <quicknoting jar file> with the following options
* **new** repositoryname path/to/repositoryDir
 * Creates a new repository.
  * The repository directory can be any directory where the user has writing permissions. If the direcory does not exist, it will be created
  * The name and the path of the repository will be written to ~/.quicknoting
* **ls**
 * List created all repositories.
* **open** repositoryname
 * Sets the repository to current.
* **close**
 * Closes current repository.
* **delete** repositoryname
 * Removes the repository from the .quicknoting file.
* **prefix** pattern
 * Sets the prefix in the .qnconf file for the current repository.
 * The prefix will then be written to befor each message.
 * The pattern can contain placeholder: %t for the current time (HH:mm:ss), %l for the level given to the message.
 * e.g.: "[%t %l] -" will be translated to "[23:59:00 MessageLavel] -" 
* **note** message [level]
 * Appends the message to a file named after the current date. If the file does not exist, it will be created.
 * The level is optional and can be any string.
 * If a %l placeholder is defined in the prefix, then it will be translated to level.

License
-------
QuickNoting is distributed under "GNU General Public License v3" http://www.gnu.org/licenses/gpl.html
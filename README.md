# WebServer

A multi-threaded (e.g. file-based) web server with thread-pooling implemented in Java. **This repository is a fork from [ibogomolov/WebServer](https://github.com/ibogomolov/WebServer).**  I extended this project and documented the code.  

---

## I have done the following changes  

- Documented the code.  
- Created a license (MIT-license)  
- Changed the **README.md**  
- Verification of the command-line arguments in the main(...)-method. Checks whether the command-line arguments are in the proper form. Otherwise it raise a certain error-message.  
- Changed the close()-method in the Server-class. For shutdown the thread pool with a while-loop.  
- Created a useful constructor in the class HttpRequest.  
- **Added a simple GUI for controlling the server.**  
- Added a new **.jar** file called ```WebServerGUI.jar```  
- I changes only the **.java**-files and the binaries **.class** files and the **.jar** file.     

---

## Usage of the console server

You simply type in the console:  

```sh
chmod +x WebServer.jar
```
after that  

```sh
./WebServer.jar
```

This starts the web server under [http://localhost:8080](http://localhost:8080) with working-directory **wwwroot**.  
In addition you can given some command-line arguments:  

```sh
./WebServer.jar <port> <working-directory> <max number of threads>
```

It must be three arguments in the order above.  

## Using of the gui server  

You simply type in the console:  

```sh
chmod +x WebServerGUI.jar
```

after that  

```sh
./WebServerGUI.jar
```

This starts the GUI for the webserver. (Swing-GUI)  

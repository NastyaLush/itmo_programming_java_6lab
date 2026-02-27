## Stage 2 (Lab 6)

Split the program from Lab Work No. 5 into client and server modules. The server module must execute commands for managing the collection. The client module must, in interactive mode, read commands, send them to the server for execution, and output the execution results.

### Requirements

- Operations for processing collection objects must be implemented using the Stream API with lambda expressions.
- Objects must be transferred between the client and server in serialized form.
- Objects in the collection sent to the client must be sorted by size.
- The client must correctly handle temporary server unavailability.
- Data exchange between the client and server must use the TCP protocol.
- On the server, input/output streams must be used for data exchange.
- On the client, a network channel must be used for data exchange.
- Network channels must be used in non-blocking mode.

### Server application responsibilities

- Work with the file that stores the collection.
- Manage the collection of objects.
- Assign automatically generated fields for objects in the collection.
- Wait for client connections and requests.
- Process received requests (commands).
- Save the collection to a file when the application terminates.
- Save the collection to a file when executing a special command available only to the server (the client cannot send this command).
- The server application must consist of the following modules (implemented as one or more classes):
  - Connection acceptance module.
  - Request reading module.
  - Received command processing module.
  - Response sending module.
- The server must run in single-threaded mode.

### Client application responsibilities

- Read commands from the console.
- Validate input data.
- Serialize the entered command and its arguments.
- Send the command and its arguments to the server.
- Process the server response (output the result of command execution to the console).
- Remove the `save` command from the client application.
- The `exit` command terminates only the client application.

### Important

Commands and their arguments must be objects of classes. Exchanging “plain” strings is not allowed. For example, for the `add` command (or its equivalent), you must form an object containing the command type and the object that should be stored in your collection.

### Additional task

Implement logging of different server workflow stages (startup, new connection received, new request received, response sent, etc.) using **Java Util Logging**.

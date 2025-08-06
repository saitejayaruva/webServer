package SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run() throws IOException{
    int port = 8010;
    try (ServerSocket socket = new ServerSocket(port)) {
        socket.setSoTimeout(10000);
        while(true){
            try {
                System.out.println("Server is listening to port"+port);
                Socket acceptedConnection = socket.accept();
                System.out.println("Connection accepted from client"+acceptedConnection.getRemoteSocketAddress());
                PrintWriter toclient = new PrintWriter(acceptedConnection.getOutputStream());
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                toclient.println("Hello from the Server");
                toclient.close();
                fromClient.close();
                acceptedConnection.close();
                // fromClient.println("Hello from the Client");
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
    }

}
public static void main(String[] args){
    Server server = new Server();
    try {
        server.run();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
}

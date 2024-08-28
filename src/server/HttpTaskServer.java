package server;

import handler.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;


public class HttpTaskServer {
    private static final int PORT = 8085;
    public static HttpServer server;

    public HttpTaskServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/tasks", new TaskHandler());
        server.createContext("/subtasks", new SubtaskHandler());
        server.createContext("/Epics", new EpicHandler());
        server.createContext("/history", new HistoryHandler());
        server.createContext("/prioritized", new PrioritizedHandler());

    }
    public static void main(String[] args) throws IOException {
        HttpTaskServer server1 = new HttpTaskServer();
        server1.startServer();
        System.out.println("Server started at port: " + PORT);
    }

    public void startServer(){
        server.start();
    }

    public void stopServer(){
        server.stop(0);
    }
}

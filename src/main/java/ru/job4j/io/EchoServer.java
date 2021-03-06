package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String outMsg = "";
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        String[] strings = str.split("=");
                        String msg = "";
                        if (strings.length > 1) {
                            msg = strings[1];
                        }
                        if (msg.startsWith("Exit ")) {
                            server.close();
                        } else if (msg.startsWith("Hello ")) {
                            outMsg = "Hello";
                            break;
                        } else {
                            outMsg = "What?";
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(outMsg.getBytes());
                    out.flush();
                }
            }
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
    }
}

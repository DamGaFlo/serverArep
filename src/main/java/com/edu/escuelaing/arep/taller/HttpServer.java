/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.escuelaing.arep.taller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;

/**
 *
 * @author Home
 */
public class HttpServer{
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(35000);
        }catch(IOException e){
            System.err.println("Could not listen on port 35000");
            System.exit(1);
        }
        Socket clientSocket = null;
        try{
            clientSocket = serverSocket.accept();
        }catch(IOException e){
            System.err.println("Accept failed");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                    clientSocket.getInputStream()));
        String inputLine,outputLine;
        while((inputLine=in.readLine())!=null){
            System.out.println(
                        "Mensaje recibido desde cliente: "+inputLine);
            outputLine = "Respuesta desde el servidor: " + inputLine;
            out.println(outputLine);
            if(outputLine.equals("Respuesta desde el servidor: Bye")){
                break;
            }
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
        
    }
}

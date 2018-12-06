/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.app.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiago
 */
public class ServidorService {
    
    private ServerSocket serverSocket;
    private Socket socket;
    //Todo usuário que se conectar ao char vai ser adicionado a essa lista
    private Map<String, ObjectOutputStream> mapOnlines = new HashMap<String, ObjectOutputStream>();
    
    public ServidorService(){
        try {
            serverSocket = new ServerSocket(5555);
            
            while(true){
                socket = serverSocket.accept();
                
                new Thread(new ListenerSocket(socket)).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private class ListenerSocket implements Runnable{
        
        private ObjectOutputStream output; //Guarda a saida de mensagem
        private ObjectInputStream input; // Guarda a entrada de mensagem

        public ListenerSocket(Socket socket) {
            try {
                this.output = new ObjectOutputStream(socket.getOutputStream());
                this.input = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }     
        
        @Override
        public void run() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.app.service;

import com.chat.app.bean.ChatMessage;
import com.chat.app.bean.ChatMessage.Action;
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
            serverSocket = new ServerSocket(12345);
            
            System.out.println("Servidor ON na porta: " + serverSocket.getLocalPort());
            
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
            
            ChatMessage message = null;
            
            try {
              
                while((message = (ChatMessage) input.readObject()) != null){
                    Action action = message.getAction();
                    
                    if(action.equals(Action.CONNECT)){
                        boolean isConnect = connect(message, output);
                        
                        if(isConnect){
                            mapOnlines.put(message.getName(), output);
                        }
                    }else if (action.equals(Action.DISCONNECT)){
                        
                    }else if (action.equals(Action.SEND_ONE)){
                        
                    }else if (action.equals(Action.SEND_ALL)){
                        
                    }else if (action.equals(Action.USERS_ONLINE)){
                        
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    private boolean connect(ChatMessage message, ObjectOutputStream output){
        if (mapOnlines.size() == 0){
            message.setText("YES");
            sendOne(message, output);
            return true;
        }
        
        for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()){
            if(kv.getKey().equals(message.getName())){
                message.setText("NO");
                return false;
            }else{
                message.setText("YES");
                sendOne(message, output);
                return true;
            }
        }
        
        return false;
    }
    
    private void sendOne(ChatMessage message, ObjectOutputStream output){
        try {
            output.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

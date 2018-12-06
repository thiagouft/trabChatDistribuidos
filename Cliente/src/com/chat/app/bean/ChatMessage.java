/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.app.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author thiago
 */
public class ChatMessage implements Serializable{
    
    private String name; //Conteḿ o nome do cliente
    private String text; //Contém mensagem digitada do cliente
    private String nameReserved; //Possui texto de uma mensagem reservada a uma pessoa
    private Set<String> setOnlines = new HashSet<String>(); //Lista de usuários online no momento
    private Action action;
    
    //Ações do chat para iteração
    public enum Action{
        CONNECT, DISCONNECT, SEND_ONE, SEND_ALL, USERS_ONLINE
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNameReserved() {
        return nameReserved;
    }

    public void setNameReserved(String nameReserved) {
        this.nameReserved = nameReserved;
    }

    public Set<String> getSetOnlines() {
        return setOnlines;
    }

    public void setSetOnlines(Set<String> setOnlines) {
        this.setOnlines = setOnlines;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
    
    
    
}

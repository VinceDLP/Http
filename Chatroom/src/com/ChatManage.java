package com;

import java.util.Vector;

public class ChatManage {
    private ChatManage(){}
    private static final ChatManage cm = new ChatManage();
    public static ChatManage getChatManage(){
        return cm;
    }
    Vector<ChatSocket> vector = new Vector<ChatSocket>();
    public void add(ChatSocket chatSocket){
        vector.add(chatSocket);
    }
    public void publish(ChatSocket chatSocket,String out){

        for(int i = 0;i < vector.size();i++){
            ChatSocket chatSocket1 = vector.get(i);
            if(!chatSocket.equals(chatSocket1)){
                chatSocket1.out(out);
            }
        }
    }
}

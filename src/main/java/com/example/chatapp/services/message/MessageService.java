package com.example.chatapp.services.message;

import com.example.chatapp.entities.contacts.Contact;
import com.example.chatapp.entities.messages.Message;
import com.example.chatapp.entities.messages.TextMessage;
import com.example.chatapp.entities.users.User;
import com.example.chatapp.models.dto.message.SendingMessageDto;
import org.modelmapper.ModelMapper;

public interface MessageService {



     default void send(Contact contact, User sender, SendingMessageDto messageDto,int typeCode)
     {
         if(typeCode==0)
         {
             TextMessage textMessage=new TextMessage();
             //todo:optimize it with a common method for mapping
             textMessage.setContentBody(messageDto.getContentBody());
             textMessage.setStatus(messageDto.getStatus());
             process(textMessage,contact,sender);
             sendTextMessage(textMessage);
         }
     }



     default void process(Message message, Contact contact, User sender)
     {
         message.setSender(sender);
         message.setContact(contact);
     }


    void sendTextMessage(TextMessage textMessage);

     //todo:add more method for doing message operations like deleting,editing etc.

}

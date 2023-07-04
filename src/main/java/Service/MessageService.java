package Service;

import DAO.MessageDAO;
import Model.Message;

import java.util.List;

public class MessageService {
    public MessageDAO messageDAO;

    //No-args constructor for messageService which creates a MessageDAO.
    public MessageService(){
        messageDAO = new MessageDAO();
    }
    
    /**
     * Constructor for a MessageService when a MessageDAO is provided.
     @param messageDAO
     */
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }
    
    /**
     * @return all messages.
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    /**
     * @return message.
     */
    public Message getMessageById(int id) {
        return messageDAO.getMessageById(id);
    }

    /**
     * @return message.
     */
    public Message updateMessageById(int id, Message message) {
        return messageDAO.updateMessageById(id, message);
    }

    /**
     * @return message.
     */
    public List<Message> getMessageByUserId(int id) {
        return messageDAO.getMessageByUserId(id);
    }


    /**
     * @return message.
     */
    public Message deleteMessageById(int id) {
        return messageDAO.deleteMessageById(id);
    }

    /**
     * @param message a message object.
     * @return message if it was successfully persisted, null if it was not successfully persisted
     */
    public Message addMessage(Message message) {
        return messageDAO.insertMessage(message);
    }
    

}
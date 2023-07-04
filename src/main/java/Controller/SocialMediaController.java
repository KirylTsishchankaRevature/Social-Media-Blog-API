package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;




public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;
    

    public SocialMediaController(){
        this.messageService = new MessageService();
        this.accountService = new AccountService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postAccountHandler);
        app.post("/login", this::loginAccountHandler);
        app.post("/messages", this::createMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getMessageByUserHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.patch("/messages/{message_id}", this::updateMessageByIdHandler);
    
        return app;
    }

 
    //Handler to post a new account.    
    private void postAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount!=null){
            ctx.json(mapper.writeValueAsString(addedAccount));
        }else{
            ctx.status(400);
        }
    }

    //Handler to login.
    private void loginAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account userLoggedIn = accountService.userLogin(account);
        if(userLoggedIn!=null){
            ctx.json(mapper.writeValueAsString(userLoggedIn));
         }else{
            ctx.status(401);
        }
    }


    //Handler to post a new message.
    private void createMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if(addedMessage!=null){
            ctx.json(mapper.writeValueAsString(addedMessage));
        }else{
            ctx.status(400);
        }
    }


    //Handler to delete a message by message id.
    private void deleteMessageByIdHandler(Context ctx) throws JsonProcessingException {
        int id=Integer.parseInt(ctx.pathParam("message_id"));
        Message delMessage = messageService.getMessageById(id);
        messageService.deleteMessageById(id);
        if(delMessage!=null){
            ctx.json(delMessage);
        }else{
            ctx.json("");
        }
    }


    //Handler to get all messages by user id.
    private void getMessageByUserHandler(Context ctx) throws JsonProcessingException {
        int id=Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> userMessages = messageService.getMessageByUserId(id);
        if(userMessages!=null){
            ctx.json(userMessages);
        }else{
            ctx.json(new ArrayList());
        }
    }

    
    
    //Handler to retrieve all messages.
    public void getAllMessagesHandler(Context ctx){
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    

    //Handler to get a message by message id.
    private void getMessageByIdHandler(Context ctx) throws JsonProcessingException {
        int id=Integer.parseInt(ctx.pathParam("message_id"));
        Message idMessage = messageService.getMessageById(id);
        if(idMessage!=null){
            ctx.json(idMessage);
        }else{
            ctx.json("");
        }
    }

    //Handler to update a message by message id.
    private void updateMessageByIdHandler(Context ctx) throws JsonProcessingException {
        int id=Integer.parseInt(ctx.pathParam("message_id"));
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message updatedMessage = messageService.updateMessageById(id, message);
        if(updatedMessage!=null){
            ctx.json(mapper.writeValueAsString(updatedMessage));
        }else{
            ctx.status(400);
        }
    }


}
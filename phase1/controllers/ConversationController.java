package controllers;

import entities.Conversation;
import use_cases.ConversationManager;
import presenters.*;
import use_cases.UserManager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;


public class ConversationController {
    private static InputManager input;
    private static OutputManager output;
    private final ConversationManager conversationManager;
    private final String currentUserId;
    private final UserManager userManager;
    private final ViewMessagesOfAConversation viewMessagesOfAConversation;

    /**
     * the constructor of conversation controller
     * @param currentUserId the user who is currently in the "chat room"
     */
    public ConversationController(String currentUserId){
        input = new InputManager();
        output = new OutputManager();
        this.conversationManager = new ConversationManager();
        this.userManager = new UserManager();
        this.currentUserId = currentUserId;
        this.viewMessagesOfAConversation = new ViewMessagesOfAConversation();
    }

    /**
     * Get into the conversation between current user and the other user, and send message to the other user.
     *
     * @param secondUserId the User Id of user we want to send message to
     */
    public void enterConversation(String secondUserId){
        HashSet<String> setOfTalkersNow = new HashSet<String>();
        setOfTalkersNow.add(currentUserId);
        setOfTalkersNow.add(secondUserId);

        if (!conversationManager.existConversation(setOfTalkersNow)){
            conversationManager.createConversation(currentUserId, secondUserId);
        }
        conversationManager.currentConversationSetter(setOfTalkersNow);
        boolean keepSending = true;
        while (keepSending){
            ArrayList<String> messageList = getMessagesOfCurrentConversation();
            viewMessagesOfAConversation(messageList, viewMessagesOfAConversation);
            String message = input.getInputString("Enter \"quit\" to quit, other messages to send:\n");
            if (message.equals("quit")){
                keepSending = false;
            }else {
                sendToIndividualUser(message);
            }
        }
    }

    /**
     * Send message to one individual user.
     *
     * @param messageContent the content of message we want to send.
     */
    public void sendToIndividualUser(String messageContent){
        conversationManager.sendMessage(currentUserId, messageContent);
    }


    /**
     * Send message to multiple users.
     *
     * @param messageContent the content of message we want to send.
     * @param listOfUsers the list of users we want to send message to.
     */
    public void sendToMultipleUsers(String messageContent, ArrayList<String> listOfUsers){
        for(String userId: listOfUsers){
//            enterConversation(userId);
            HashSet<String> setOfTalkersNow = new HashSet<String>();
            setOfTalkersNow.add(currentUserId);
            setOfTalkersNow.add(userId);
            if (!conversationManager.existConversation(setOfTalkersNow)){
                conversationManager.createConversation(currentUserId, userId);
            }
            conversationManager.currentConversationSetter(setOfTalkersNow);
            sendToIndividualUser(messageContent);
        }
    }

    /**
     * The getter of a
     * @return
     */

//    public ArrayList<String> getMessagesOfOneConversation(String userId){
//        enterConversation(userId);
//        ArrayList<String> FinalTextsList = new ArrayList<>();
//        for(String[] oneMessage: conversationManager.getMessagesOfCurrentConversation()){
//            String finalText = userManager.getUserName(oneMessage[0]) + oneMessage[1];
//            FinalTextsList.add(finalText);
//        }
//        return FinalTextsList;
//    }

    /**
     * Get all the chatting/message history of the conversation happening right now
     * @return the arraylist of messages.
     */
    public ArrayList<String> getMessagesOfCurrentConversation(){
        ArrayList<String> FinalTextsList = new ArrayList<>();
        for(String[] oneMessage: conversationManager.getMessagesOfCurrentConversation()){
            String finalText = userManager.getUserName(oneMessage[0]) + ": " + oneMessage[1];
            FinalTextsList.add(finalText);
        }
        return FinalTextsList;
    }

    /**
     * Get all conversations of a given user.
     *
     * @param userId the user we want to find.
     * @return ArrayList of string arrays which contains the information need to find a conversation. [user1, user2, who
     * send the message, the last sentence of the conversation]
     */
    public ArrayList<String[]> getUserAllConversation(String userId){
//        ArrayList<String[]> userNameWithLastMessage = new ArrayList<>();
//        for (String[] s: conversationManager.getUserConversations(userId)){
//            String[] msg = {userManager.getUserName(s[0]), s[1]};
//            userNameWithLastMessage.add(msg);
//        }
//        return userNameWithLastMessage;
        return conversationManager.getUserConversations(userId);
    }

    /**
     * Presenter of a given conversation.
     * @param messages The list of messages.
     * @param viewMessagesOfAConversation The presenter class.
     */
    public static void viewMessagesOfAConversation(ArrayList<String> messages,
                                                   ViewMessagesOfAConversation viewMessagesOfAConversation){
        output.printPrompt(viewMessagesOfAConversation.printMessages(messages));
    }

    /**
     * Get all conversations.
     *
     * @return Hash map of the users (key) and the conversation between them(content).
     */
    public HashMap<HashSet<String>, Conversation> conversationsGetter(){
        return conversationManager.conversationsGetter();
    }

    /**
     * Given a conversation, to find which users this conversation belongs to.
     *
     * @param conversation a given conversation.
     * @return the hashset of strings which are 2 user ids.
     */
    public HashSet<String> getUserIds(Conversation conversation){
        return conversationManager.getUserIds(conversation);
    }

    /**
     * Put a conversation into our conversations Hashmap in conversationManager.
     *
     * @param key hashset with two elements, each of the element are an userid.
     * @param conversation the entity with store all messages between two users.
     */
    public void addConversation(HashSet<String> key,Conversation conversation){
        conversationManager.addConversation(key, conversation);
    }
}

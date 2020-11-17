package controllers;

import java.util.ArrayList;

public class SendToAllAttendees {
    private static OutputManager output;
    private static InputManager input;

    public SendToAllAttendees(){
        output = new OutputManager();
        input = new InputManager();
    }

    /**
     * This class is for sending message to all attendees of an certain event/ events.
     * @param eventsController event controller class
     * @param conversationController conversation controller class
     */
    public void toSendToAllAttendees(EventsController eventsController, ConversationController conversationController) {
        ArrayList<String> receivers = new ArrayList<>();

        ArrayList<ArrayList<String>> idAndNames = eventsController.getAllIDAndName();

        StringBuilder eventNames = new StringBuilder();
        for (int i = 0; i < idAndNames.get(1).size(); i++) {
            eventNames.append((i + 1) + ". " + idAndNames.get(1).get(i) + "\n");
        }
        output.printPrompt(eventNames);

        boolean check7 = true;
        while (check7) {
            int eventChoose = input.getInputInt("Please enter the number one by one to choose" +
                    " the events, and enter \"0\" to finish:\n");
            if (eventChoose == 0) {
                check7 = false;
            } else if (eventChoose == 666 || eventChoose > idAndNames.get(1).size() || eventChoose < 0) {
                output.printPrompt("Please enter another number.");
            } else {
                receivers.addAll(eventsController.getAttendeesFromEvent(idAndNames.get(0).
                        get(eventChoose - 1)));
            }
        }
        String message = input.getInputString("Please enter the message you want to send:\n");
        conversationController.sendToMultipleUsers(message, receivers);
    }
}

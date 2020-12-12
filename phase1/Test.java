import unused.graphics.TextUI;

public class Test {
    public static void main(String[] args) {
        TextUI textUI = new TextUI();
//        String[] strings = {"Welcome Attendee: ", "1) view all existing events",
//                "2) view all my events", "3) View all my fiends", "4) view all my message",
//                "5) add friend", "0) sign out"};
        StringBuilder returnString = new StringBuilder("\nWelcome Attendee: \n");
        returnString.append(" 1) view all existing events\n 2) view all my events\n 3) View all my fiends\n 4) view all my message" +
                "\n 5) add friend\n 0) sign out\n");
        String[] strings = returnString.toString().split("\n");
        textUI.print(strings);
    }
}

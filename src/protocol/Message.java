package protocol;

import server.Client;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static protocol.MessageProtocolConstants.*;

public class Message {

    private static Calendar date = new GregorianCalendar();


    public static String encodeOutgoingMessageForClient(String name, String message) {
        return STARTOFHEADING + OUTGOINGMESSAGE + STARTOFTEXT + name + "|" + date.getTime() + "|" + message + ENDOFTEXT + ENDOFTRANSMISSION;
    }

    public static String decodeMessage(String message) {
        String decodedMessage = "";
        System.out.println(message);
        String head = message.substring(message.indexOf(STARTOFHEADING) + 1, message.indexOf(STARTOFTEXT));
        System.out.println(head);
        String body = message.substring(message.indexOf(STARTOFTEXT) + 1, message.indexOf(ENDOFTEXT));
        System.out.println(body);
        System.out.println("Incomming " + head.contains(INCOMINGMESSAGE));
        System.out.println("Outgoing " + head.contains(OUTGOINGMESSAGE));
        if (head.contains(OUTGOINGMESSAGE)) {
            decodedMessage = decodeIncomingMessageBody(body);
        } else if (head.contains(INCOMINGMESSAGE)) {
            decodedMessage = decodeOutgoingMessageBody(body);
        }
        System.out.println("Decoded Message: " + decodedMessage);
        return decodedMessage;
    }

    private static String decodeOutgoingMessageBody(String body) {

        return body;
    }

    private static String decodeIncomingMessageBody(String body) {
        String[] messageArray = body.split("\\|");
        return messageArray[0] + ": " + messageArray[2];
    }


}

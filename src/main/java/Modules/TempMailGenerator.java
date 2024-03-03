package Modules;

import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.clients.ApiClient;
import com.mailslurp.clients.ApiException;
import com.mailslurp.models.InboxDto;
import com.mailslurp.models.SendEmailOptions;
import java.util.Collection;
import java.util.Collections;



public class TempMailGenerator {
    private static String MAIL_KEY = "2e0f80c5653a3041edc7dd341dbe5ed850533eed679f345a2264368cdd8417ea"; 
    public static String getNewMail() throws ApiException{
        ApiClient api = new ApiClient();
        api.setApiKey(MAIL_KEY);
        InboxControllerApi inboxCtrller = new InboxControllerApi(api);
        InboxDto inbox = inboxCtrller.createInboxWithDefaults();
        return inbox.getEmailAddress();
    }
    public static String sendMail(String name, String reciver) throws ApiException{
        try{
            ApiClient api = new ApiClient();
            api.setApiKey(MAIL_KEY);
            InboxControllerApi inboxCtrller = new InboxControllerApi(api);
            InboxDto inbox = inboxCtrller.createInboxWithDefaults();
            SendEmailOptions sender = new SendEmailOptions();
            sender.setTo(Collections.singletonList(reciver));
            sender.setSubject("Saluto");
            sender.setBody("Buongiorno da parte di " + name);
            inboxCtrller.sendEmail(inbox.getId(), sender);
            return "Email inviata con successo";
        } catch(Exception e){
            return "C'è stato un errore durante l'invio dell'email";
        }
    }
}

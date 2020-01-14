import org.junit.Before;
import org.junit.Test;

import org.mockito.MockitoAnnotations;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.MessageObject;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLRPC;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class SendMessagesHelperTest {
    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendMessages() {

        TLRPC.Message message = mock(TLRPC.Message.class);
        MessageObject m = mock(MessageObject.class);
        doCallRealMethod().when(m).getDialogId();

        AccountInstance accountInstance = mock(AccountInstance.class);

        message.from_id = 1;
        message.post = false;
        message.id = 123;
        message.dialog_id = 1;

        m.messageOwner = message;

        doCallRealMethod().when(accountInstance).getSendMessagesHelper();

        MessagesController messagesController = mock(MessagesController.class);

        doCallRealMethod().when(accountInstance).getMessagesController();

        doCallRealMethod().when(messagesController).getInputPeer(any());




        SendMessagesHelper sendMessagesHelper = accountInstance.getSendMessagesHelper();

        System.out.println("SEND MESSAGES HELPER " + sendMessagesHelper);
        sendMessagesHelper.sendMessage(m);

        System.out.println(m.messageOwner.send_state);
        System.out.println(m.isSent());

        assertTrue(m.isSent());
    }
}
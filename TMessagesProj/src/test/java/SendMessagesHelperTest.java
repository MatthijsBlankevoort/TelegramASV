import org.junit.Before;
import org.junit.Test;

import org.mockito.MockitoAnnotations;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.MessageObject;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLRPC;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

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
        mock(TLRPC.User.class);
        AccountInstance accountInstance = mock(AccountInstance.class);

        doCallRealMethod().when(accountInstance).getSendMessagesHelper();
        SendMessagesHelper sendMessagesHelper = accountInstance.getSendMessagesHelper();
        doCallRealMethod().when(m).isSent();

        m.messageOwner = message;

        sendMessagesHelper.sendMessage(m);

        assertTrue(m.isSent());
    }
}
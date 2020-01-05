import android.accounts.Account;

import net.hockeyapp.android.metrics.model.Base;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.BaseController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLRPC;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class SendMessagesHelperTest {
    @Mock
    MessageObject messageObject;

    @Mock
    TLRPC.Message message;

    @Mock
    AccountInstance account;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendMessages() {
        MessageObject m = mock(MessageObject.class);
        message.dialog_id = 1;
        m.messageOwner = message;
        SendMessagesHelper sendMessagesHelper = mock(SendMessagesHelper.class);
        sendMessagesHelper.sendMessage(m);

        assertTrue(messageObject.isSent());
    }
}
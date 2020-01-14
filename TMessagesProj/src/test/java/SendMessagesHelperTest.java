import android.accounts.Account;

import com.google.android.exoplayer2.decoder.SimpleOutputBuffer;

import net.hockeyapp.android.metrics.model.Base;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.BaseController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLRPC;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doCallRealMethod;
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
        AccountInstance accountInstance = mock(AccountInstance.class);

        message.from_id = 1;
        message.post = false;
        message.id = 123;
        m.messageOwner = message;
        m.messageOwner.dialog_id = 1;
        m.messageOwner = message;

        SendMessagesHelper sendMessagesHelper = accountInstance.getSendMessagesHelper();
        System.out.println("SEND MESSAGES HELPER " + sendMessagesHelper);
        doCallRealMethod().when(sendMessagesHelper).sendMessage(m);

        assertTrue(m.isSent());
    }
}
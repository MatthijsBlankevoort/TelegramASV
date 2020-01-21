import org.junit.Test;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLRPC;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

public class MessageObjectTest {

    @Test
    public void isMessageReadAfterMarkingAsRead() {
        TLRPC.Message message = mock(TLRPC.Message.class);
        MessageObject m = mock(MessageObject.class);
        doCallRealMethod().when(m).getDialogId();
        mock(TLRPC.User.class);
        AccountInstance accountInstance = mock(AccountInstance.class);

        doCallRealMethod().when(accountInstance).getSendMessagesHelper();
        SendMessagesHelper sendMessagesHelper = accountInstance.getSendMessagesHelper();
        doCallRealMethod().when(m).isUnread();
        doCallRealMethod().when(m).setIsRead();

        m.messageOwner = message;

        sendMessagesHelper.sendMessage(m);
        m.setIsRead();
        assertFalse(m.isUnread());
    }
}
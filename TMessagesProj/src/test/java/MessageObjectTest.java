import org.junit.Test;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLRPC;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MessageObjectTest {

    @Test
    public void testIsMessageReadAfterMarkingAsRead() {
        TLRPC.Message message = mock(TLRPC.Message.class);
        MessageObject m = mock(MessageObject.class);
        doCallRealMethod().when(m).getDialogId();

        AccountInstance accountInstance = mock(AccountInstance.class);

        doCallRealMethod().when(accountInstance).getSendMessagesHelper();
        SendMessagesHelper sendMessagesHelper = accountInstance.getSendMessagesHelper();
        doCallRealMethod().when(m).isUnread();
        doCallRealMethod().when(m).setIsRead();

        message.unread = true;

        m.messageOwner = message;

        sendMessagesHelper.sendMessage(m);

        m.setIsRead();

        assertFalse(m.isUnread());
    }

    @Test
    public void testIfEncryptedChatIsAddedToEncryptedChats() {
        TLRPC.EncryptedChat encryptedChat = mock(TLRPC.EncryptedChat.class);
        MessagesController messagesController = mock(MessagesController.class);

        messagesController.putEncryptedChat(encryptedChat, false);

        when(messagesController.getEncryptedChat(encryptedChat.id)).thenReturn(encryptedChat);

        assertEquals(messagesController.getEncryptedChat(encryptedChat.id), encryptedChat);

    }
}
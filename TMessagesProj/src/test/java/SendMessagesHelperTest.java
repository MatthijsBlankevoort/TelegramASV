import org.junit.Before;
import org.junit.Test;

import org.mockito.MockitoAnnotations;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLRPC;

import java.util.ArrayList;

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
    public void testMessageIsSentAfterSendingMessage() {

        TLRPC.Message message = mock(TLRPC.Message.class);
        MessageObject m = mock(MessageObject.class);
        doCallRealMethod().when(m).getDialogId();
        AccountInstance accountInstance = mock(AccountInstance.class);

        doCallRealMethod().when(accountInstance).getSendMessagesHelper();
        SendMessagesHelper sendMessagesHelper = accountInstance.getSendMessagesHelper();
        doCallRealMethod().when(m).isSent();

        m.messageOwner = message;

        sendMessagesHelper.sendMessage(m);

        assertTrue(m.isSent());
    }

    @Test
    public void testSendFiles() {
        TLRPC.Message message = mock(TLRPC.Message.class);
        TLRPC.TL_document document = mock(TLRPC.TL_document.class);
        MessageObject m = mock(MessageObject.class);
        doCallRealMethod().when(m).getDialogId();

        AccountInstance accountInstance = mock(AccountInstance.class);
        TLRPC.MessageMedia media = mock(TLRPC.MessageMedia.class);
        document.file_name = "test-doc";

        message.media = media;
        message.media.document = document;

        doCallRealMethod().when(accountInstance).getSendMessagesHelper();
        SendMessagesHelper sendMessagesHelper = accountInstance.getSendMessagesHelper();
        doCallRealMethod().when(m).isSent();
        doCallRealMethod().when(m).getDocument();

        m.messageOwner = message;

        sendMessagesHelper.sendMessage(m);

        assertTrue(m.isSent());
    }

    @Test
    public void testCanForwardMessage() {
        TLRPC.Message message = mock(TLRPC.Message.class);
        MessageObject m = mock(MessageObject.class);

        AccountInstance accountInstance = mock(AccountInstance.class);

        doCallRealMethod().when(accountInstance).getSendMessagesHelper();
        SendMessagesHelper sendMessagesHelper = accountInstance.getSendMessagesHelper();
        doCallRealMethod().when(m).canForwardMessage();

        m.messageOwner = message;

        sendMessagesHelper.sendMessage(m);

        assertTrue(m.canForwardMessage());
    }


    //Tests send audio files
    @Test
    public void testSendAudioFiles() {
        MessageObject m = mock(MessageObject.class);
        TLRPC.Message message = mock(TLRPC.Message.class);
        TLRPC.MessageMedia media = mock(TLRPC.MessageMedia.class);
        TLRPC.TL_document document = mock(TLRPC.TL_document.class);
        TLRPC.TL_documentAttributeAudio audio = mock(TLRPC.TL_documentAttributeAudio.class);
        AccountInstance accountInstance = mock(AccountInstance.class);

        document.attributes = new ArrayList<>();
        document.attributes.add(audio);

        message.media = media;
        message.media.document = document;

        doCallRealMethod().when(accountInstance).getSendMessagesHelper();
        SendMessagesHelper sendMessagesHelper = accountInstance.getSendMessagesHelper();
        doCallRealMethod().when(m).isSent();

        m.messageOwner = message;

        sendMessagesHelper.sendMessage(m);

        when(m.isVoice()).thenReturn(true);

        assertTrue(m.isVoice());
    }
}
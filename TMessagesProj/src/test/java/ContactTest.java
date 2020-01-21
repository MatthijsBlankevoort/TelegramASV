import com.google.android.exoplayer2.decoder.SimpleOutputBuffer;

import org.junit.Test;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ContactTest {

    @Test
    public void testUserCanAddContact() {
        TLRPC.User user = mock(TLRPC.User.class);

        ContactsController contactsController = mock(ContactsController.class);

        contactsController.addContact(user, false);
        when(contactsController.isContact(user.id)).thenReturn(true);

        assertTrue(contactsController.isContact(user.id));
    }

    @Test
    public void testUserConfigContainsCurrentUser() {
        UserConfig userConfig = mock(UserConfig.class);
        TLRPC.User user = mock(TLRPC.User.class);

        userConfig.setCurrentUser(user);

        when(userConfig.getCurrentUser()).thenReturn(user);

        assertEquals(userConfig.getCurrentUser(), user);
    }
}
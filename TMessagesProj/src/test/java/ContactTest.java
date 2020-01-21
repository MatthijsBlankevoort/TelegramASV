import org.junit.Test;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;

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
        System.out.println(contactsController.isContact(user.id));

        assertTrue(contactsController.isContact(user.id));
    }
}
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.telegram.messenger.SendMessagesHelper;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SendMessagesHelperTest.class,
        MessageObjectTest.class,
        ContactTest.class
})

public class  TestSuite {
}
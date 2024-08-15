import managers.FileBackedTaskManager;
import managers.InMemoryTaskManager;
import managers.Managers;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {
    InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
    Managers managers = new Managers();
    FileBackedTaskManager fileBackedTaskManager;

    @Test
    public void taskManagerMustReturnNotNull() {
        assertNotNull(Managers.getDefault());
        assertNotNull(Managers.getHistoryManager());
    }
}
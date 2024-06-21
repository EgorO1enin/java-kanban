package tests;

import Managers.InMemoryTaskManager;
import Managers.Managers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {
    InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
    Managers managers = new Managers();

    @Test
    public void taskManagerMustReturnNotNull(){
        assertNotNull(Managers.getDefault());
        assertNotNull(Managers.getHistoryManager());
    }


}
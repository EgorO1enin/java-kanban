public class Managers {
    public static ТaskManager getDefault(){
        return new InMemoryTaskManager();
    }

    public static HistoryManager getHistoryManager(){
        return new InMemoryHistoryManager();
    }


}

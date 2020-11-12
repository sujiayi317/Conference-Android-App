import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class Conversation {
    private String userID1;
    private String userID2;
    private ArrayList<Pair> userID;
    private ArrayList<String> speakers;
    private int startTime;
    private int duration = 1;
}

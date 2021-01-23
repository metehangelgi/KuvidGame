package Domain.Network;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;

public interface SaveLoadAdapter {

    JSONObject download(String filename) throws Exception;
    boolean upload(JSONObject json) throws Exception;
    List<HashMap<String, String>> savedFiles() throws Exception;
}

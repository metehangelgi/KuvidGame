package Domain.Network;

import com.mongodb.client.*;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;

public class Mongo implements SaveLoadAdapter {

    MongoCollection<Document> collection;

    public Mongo() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.
        MongoClient mongoClient = MongoClients.create("mongodb+srv://comp302_user:comp302_password@cluster0.fmfpb.mongodb.net/Comp302?retryWrites=true&w=majority"); // uri connection to the server
        MongoDatabase database = mongoClient.getDatabase("Comp302"); // selecting the database
        this.collection = database.getCollection("KuvidCollection"); // collection
    }


    @Override
    public JSONObject download(String filename) throws Exception {
        Document my_doc = collection.find(eq("username", filename)).sort(new Document("_id", -1)).first();
        return changetoJson(my_doc);
    }

    @Override
    public boolean upload(JSONObject json){
        collection.insertOne(Document.parse(json.toString()));
        return true;
    }

    @Override
    public List<HashMap<String, String>> savedFiles() throws Exception {
        List<HashMap<String, String>> savedGames = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.00");
        FindIterable<Document> docs = collection.find();
        for (Document doc : docs) {
            JSONObject json = changetoJson(doc);
            HashMap<String, String> gameMap = new HashMap<>();
            gameMap.put("title", (String) json.get("title"));
            gameMap.put("username", (String) json.get("username"));
            gameMap.put("score", df.format((double) json.get("Score")));
            gameMap.put("time", df.format((double) json.get("Time")));
            gameMap.put("health", df.format((double) json.get("Health")));
            double time=(double) json.get("Time");
            int min=(int)time/60;
            int sec=(int)((time-min*60)%60);
            StringBuilder clock=new StringBuilder();
            clock.append(min);
            clock.append(":");
            clock.append(sec);
            gameMap.put("time", clock.toString());
            savedGames.add(gameMap);
        }
        Collections.reverse(savedGames);

        return savedGames;
    }


    private JSONObject changetoJson(Document my_doc) throws Exception {
        String jsonString = my_doc.toJson();
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonString);
        return json;
    }
}

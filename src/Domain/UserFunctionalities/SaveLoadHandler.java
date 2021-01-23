package Domain.UserFunctionalities;

import Domain.Network.LocalMongoFactory;
import Domain.Network.SaveLoadAdapter;
import Domain.Statistics.GameData;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;

public class SaveLoadHandler {

    //burası çağırılarak yapılabilir.
    private SaveLoadAdapter adapter;

    public SaveLoadHandler(){
        LocalMongoFactory fac=new LocalMongoFactory();
        adapter=fac.getAdapter();
    }



    public void Save(String saveTitle,String username){ //username-playername-filename is same

        try {
            Save save=new Save(saveTitle,username);
            JSONObject json=save.SaveTheGame();
            adapter.upload(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GameData Load(String filename){
        GameData gameData=null;
        try {
            Load load=new Load();
            JSONObject json=adapter.download(filename);
            gameData=load.LoadTheGame(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gameData;
    }

    public List<HashMap<String, String>> LoadAllGames(){
        List<HashMap<String, String>> allGames=null;
        try {
            allGames=adapter.savedFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allGames;

    }





}

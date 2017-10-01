import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.User;
import service.LRUCache;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        try {
            String json = readFile("src/main/resources/users.json");
            List<User> userList = deserializeUsers(json);

            LRUCache<Integer, User> cache = new LRUCache<Integer, User>(50);

            for (User u : userList) {
                cache.put(u.getId(), u);
            }

            displayUsers(cache);


            cache.put(51, new User("John Smith", 51));
            displayUsers(cache);

            User user30 = cache.get(2);
            cache.put(52, new User("Eugen Doga", 52));
            // expect to remove 3 not 2
            displayUsers(cache);
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    private static List<User> deserializeUsers(String json) {
        Type listOfA = new TypeToken<List<User>>() {}.getType();
        Gson g = new Gson();
        return g.fromJson(json, listOfA);
    }

    static String readFile(String fileName) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
        return text;
    }

    public static void displayUsers(LRUCache<Integer, User> cache)
    {
        for (User u : cache.values())
            System.out.printf("%d %s\n", u.getId(), u.getName());

        System.out.printf("\n********************\n\n");
    }


}

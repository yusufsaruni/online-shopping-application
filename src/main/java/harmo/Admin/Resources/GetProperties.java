package harmo.Admin.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {

    public static Properties databaseProps(){
        Properties p = new Properties();
        try(InputStream input = GetProperties.class.getClassLoader()
                .getResourceAsStream("config.properties")){
            if(input != null){
                p.load(input);
            }
        }catch (IOException e){
            System.out.println("get properties method: " + e.getMessage());
        }
        return p;
    }
}

package app.GS.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class ReadConfig {


    private static FileInputStream fis;
    public static final Properties config = new Properties();

    private static final Logger logger = LogManager.getLogger("ReadConfig.class");

    static {
        try {
            fis = new FileInputStream(new File(System.getProperty("user.dir") + "//configure.properties"));
            config.load(fis);

        } catch (Exception e) {
            logger.error("Error while loading of configure properties ", e);
        }
        finally {
            try {
                if(fis !=null) {
                    fis.close();
                }
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<HashMap<String, String>> getJsonData(String fileName) throws IOException {

        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//resources//jsonDataFiles//"+fileName+".json"), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
        });
    }

}

package fr.anatom3000.gwwhit.kizzyjune;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerGuy {
    private static final Logger LOGGER = LoggerFactory.getLogger("loggerGuy69");
    public static void log(String text){
        LOGGER.info(text);
    }
}

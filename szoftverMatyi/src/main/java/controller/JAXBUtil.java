package controller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

import game.LeaderBoard;
import org.slf4j.Logger;

/**
 * {@link javax.xml.bind.JAXB} eszközök használatát egyszerűsítő osztály.
 */
public class JAXBUtil {

    /**
     * XML formátumúra ír objektumokat.
     *
     * @param o  az átírandó objektum
     * @param os az {@link OutputStream} ahová írunk
     * @throws JAXBException ha bármi probléma van a folyamat során
     */
    private static void toXML(Object o, OutputStream os) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(o.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(o, os);
    }

    /**
     * XML-ből olvas be objektumba.
     *
     * @param <T> Generikus osztály
     * @param clazz az objektum osztálya
     * @param is    az {@link InputStream} ahonnan olvasunk
     * @return a célobjektum
     * @throws JAXBException ha bármi probléma van a beolvasás során
     */
    private static <T> T fromXML(Class<T> clazz, InputStream is) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(is);
    }

    /**
     * Speciális függvény, a játék mappájából {@code user.home/ColorWar} olvassa a leaderboard.xml-t.
     *
     * @param logger a loghoz szükséges logger
     * @return a beolvasott objektumot adja vissza
     */
    static LeaderBoard readFromXML(Logger logger) {
        LeaderBoard ranks = new LeaderBoard();
        try {
            ranks = JAXBUtil.fromXML(game.LeaderBoard.class, new FileInputStream(System.getProperty("user.home")
                    + "/HorseGame/leaderboard.xml"));
            logger.info("Try to read leaderboard.xml");
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            logger.info("No leaderboard found");
        }
        return ranks;
    }

    /**
     * Speciális függvény, a játék mappájába {@code user.home/ColorWar} írja felül a leaderboard.xml-t,
     * illetve ha nincs ilyen állomány, akkor létrehozza.
     *
     * @param leaderBoard a kiírandó objektum
     * @param logger      a logoláshoz szükséges logger
     */
    static void writeToXML(LeaderBoard leaderBoard, Logger logger) {
        try {
            File file = new File(System.getProperty("user.home") + "/HorseGame");
            logger.info("Try to write the new leaderboard state.");
            if (!file.exists()) {
                if (file.mkdir()) {
                    logger.info("No game directory. Make new directory");
                } else {
                    logger.error("mkdir error");
                }
            }
            JAXBUtil.toXML(leaderBoard, new FileOutputStream(System.getProperty("user.home") + "/HorseGame/leaderboard.xml"));
            logger.info("leaderboard.xml updated");
        } catch (FileNotFoundException | JAXBException e) {
            e.printStackTrace();
        }
    }
}

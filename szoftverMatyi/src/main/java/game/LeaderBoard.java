package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * A ranglistát kezelő osztály.
 * <p>
 * A {@link controller.JAXBUtil} osztállyal írható/olvasható osztály,
 */
@javax.xml.bind.annotation.XmlRootElement(name = "leaderboard")
@XmlAccessorType(XmlAccessType.FIELD)
public class LeaderBoard {
    /**
     * A ranglista név-győzelem szám párjait tároló {@code LinkedHashMap<String, Integer>}.
     */
    @XmlElement()
    private static Map<String, Integer> ranks = new LinkedHashMap<>();

    /**
     * Slf4j logger.
     */
    private static Logger logger = LoggerFactory.getLogger(LeaderBoard.class);

    /**
     * Egy {@code VBox} típusu listát ad vissza, az 5 legtöbbet nyert játékos nevével
     * és győzelmeik számával.
     *  A {@code VBox} {@code ranks} ID-vel hivatkozható CSS-ből
     *
     * @return {@code VBox} típusu győzteslista
     */
    public static VBox getLeaderBoardAsNode() {

        VBox leaderBoard = new VBox();
        leaderBoard.setId("ranks");
        leaderBoard.setPadding(new Insets(5));
        leaderBoard.getChildren().add(new Label("5 most OP player:\n\n"));
        sortLeaderBoard();

        if (ranks.size() > 0) {
            int i = 0;
            for (String s : ranks.keySet()) {
                Label name = new Label(s);
                Label wins = new Label(ranks.get(s).toString());
                HBox left = new HBox(name);
                HBox right = new HBox(wins);
                left.setMinWidth(300);
                right.setMinWidth(300);
                left.setAlignment(Pos.CENTER_RIGHT);
                right.setAlignment(Pos.CENTER_RIGHT);

                leaderBoard.getChildren().add(new HBox(left, right));
                i++;
                if (i == 5) {
                    break;
                }
            }
        } else {
            leaderBoard.getChildren().add(new Label("\t Nothing to show..."));
        }
        return leaderBoard;
    }

    /**
     * A ranglista pillanatnyi állapotát rendezi át csökkenő sorrendre.
     * <p>
     * A rendezés alapja a nicknevek értéke, ami az adott játékos által nyert meccsek
     * számát jelenti.
     */
    private static void sortLeaderBoard() {
        ranks = ranks.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        logger.info("Leaderboard sorted");
    }


    /**
     * Frissíti a ranglistát az új győztessel.
     * <p>
     * Hozzáadja a {@code param} nevet a listához, vagy
     * ha a lista már tartalmazza növeli a győzelmei számát.
     * Döntetlen eredményt nem jegyez fel.
     *
     * @param name A hozzaadandó név
     */
    public static void addName(String name) {
        if (!name.equals("TIE")) {
            if (ranks.containsKey(name)) {
                ranks.put(name, ranks.get(name) + 1);
                logger.info("Player record updated");
            } else {
                ranks.put(name, 1);
                logger.info("New player record added");
            }

        }
    }

    /**
     * Ranglista getter függvénye.
     *
     * @return csökkenően rendezett {@code LinkedHashMap} ranglista
     */
    public static Map<String, Integer> getRanks() {
        sortLeaderBoard();
        return ranks;
    }
}




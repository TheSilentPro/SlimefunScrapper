package tsp.slimefunscrapper;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import com.google.gson.JsonArray;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.setup.PostSetup;
import io.github.thebusybiscuit.slimefun4.implementation.setup.ResearchSetup;
import io.github.thebusybiscuit.slimefun4.implementation.setup.SlimefunItemSetup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tsp.slimefunscrapper.util.JsonUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SlimefunScrapper {

    private static ServerMock server;
    private static Logger logger;
    private static Slimefun slimefun;

    public static void main(String[] args) {
        // Simple and quick way of allowing custom file name. Does the job.
        File file = new File("items.json");
        if (args.length >= 1) {
            String name = String.join(" ", args);
            file = new File(name.endsWith(".json") ? name : name + ".json");
        }

        setup(file);
    }

    private static void setup(File exportFile) {
        if (!validate()) {
            logger = LoggerFactory.getLogger("Scrapper");
            logger.info("Loading...");
            long start = System.currentTimeMillis();
            server = MockBukkit.mock();
            slimefun = MockBukkit.load(Slimefun.class);

            logger.debug("Setting up slimefun...");
            SlimefunItemSetup.setup(slimefun);
            ResearchSetup.setupResearches();
            PostSetup.setupWiki();

            logger.info("Exporting to file: " + exportFile.getName());
            export(exportFile);

            long elapsed = System.currentTimeMillis() - start;
            logger.info("Done! Took: " + elapsed + "ms (" + TimeUnit.MILLISECONDS.toSeconds(elapsed) + "s)");
        }
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean validate() {
        return slimefun != null;
    }

    private static void export(File file) {
        if (!validate()) {
            logger.error("Failed! Slimefun instance is null, try again.");
            return;
        }

        JsonArray main = new JsonArray();
        Slimefun.getRegistry().getAllSlimefunItems().forEach(item -> {
            logger.debug("Serializing: " + item.getId());
            main.add(JsonUtils.GSON.toJsonTree(item, SlimefunItem.class));
        });
        logger.info("Serialized " + main.size() + " items!");

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(main.toString());
        } catch (IOException exception) {
            logger.error("Failed to write!", exception);
        }
    }

    public static Logger getLogger() {
        return logger;
    }

}

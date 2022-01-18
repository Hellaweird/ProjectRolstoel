package nl.daankoster.rolstoelen;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import nl.daankoster.rolstoelen.managers.RegionManager;
import nl.daankoster.rolstoelen.managers.TagManager;
import nl.daankoster.rolstoelen.mqtt.MQTTConnector;
import nl.daankoster.rolstoelen.utils.Database;
import nl.daankoster.rolstoelen.utils.DatabaseManager;
import nl.daankoster.rolstoelen.utils.TagUpdater;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    private static final Gson gson = new Gson();
    public static RegionManager regionManager = new RegionManager();
    public static TagManager tagManager = new TagManager();
    public static Database database;
    public static DatabaseManager databaseManager;

    public static void main(String[] args) {

        database = new Database("localhost", "*", "*", "rolstoel_database");

        databaseManager = new DatabaseManager(database);

        databaseManager.registerRegions();

        String broker = "tcp://192.168.1.8:1883";
        String topic  = "tags";

        MQTTConnector connector = new MQTTConnector(broker);
        connector.connect();
        connector.subscribe(topic, message -> {
            tagManager.updateTags(gson.fromJson(message, JsonElement.class));
        });

        TagUpdater tagUpdater = new TagUpdater();
        tagUpdater.start();

    }



}

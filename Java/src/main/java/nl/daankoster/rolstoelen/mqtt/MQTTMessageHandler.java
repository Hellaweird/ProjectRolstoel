package nl.daankoster.rolstoelen.mqtt;

public interface MQTTMessageHandler {
    void onMessage(String message);

}

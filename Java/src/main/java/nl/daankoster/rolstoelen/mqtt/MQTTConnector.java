package nl.daankoster.rolstoelen.mqtt;

import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class MQTTConnector {
    private MqttClient mqttClient;
    private final String broker;

    private Map<String,MQTTMessageHandler> messageHandlers = new HashMap<>();


    public boolean connect(){
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            mqttClient = new MqttClient(broker, MqttClient.generateClientId(), persistence);
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setCleanSession(true);

            mqttClient.setCallback(new MqttCallback() {
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost");
                    System.out.println(cause);
                }
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    MQTTMessageHandler mqttMessageHandler = messageHandlers.get(topic);
                    if (mqttMessageHandler != null) mqttMessageHandler.onMessage(message.toString());
                    //System.out.println("Positioning update: " + message.toString());
                }
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("Delivery complete");
                }
            });

            System.out.println("Connecting to broker: "+broker);
            mqttClient.connect(mqttConnectOptions);
            System.out.println("Connected");




            return true;

        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
            return false;
        }
    }

    public MqttClient getConnection(){
        return mqttClient;
    }

    public void setCallBack(MqttCallback callback){
        mqttClient.setCallback(callback);
    }

    public void subscribe(String topic, MQTTMessageHandler mqttMessageHandler){
        try {

            System.out.println("Subscribing to topic: "+topic);
            mqttClient.subscribe(topic);
            messageHandlers.put(topic, mqttMessageHandler);
            System.out.println("Subscribed to topic: "+topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }




}

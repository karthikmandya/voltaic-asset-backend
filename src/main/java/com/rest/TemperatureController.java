package com.rest;

import com.database.DatabaseOperation;
import com.email.EmailSender;
import com.entity.TransformerReadingDetails;
import com.http.TemperatureClient;
import com.utility.ExcelReader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
class TemperatureController {

    @GetMapping("/temperature")
    @CrossOrigin(origins = "http://localhost:8080/")
    public double getTemperature() throws MessagingException {
        TemperatureClient temperatureClient = new TemperatureClient();
        System.out.println("Received call to return temperature");
        double temperature = temperatureClient.getTemperature();
        if(temperature > 20) {
            System.out.println("Temperature exceed threshold");
            System.out.println("Sending alert Mail");
            EmailSender sender = new EmailSender();
            sender.sendSensorEmailNoAuth("ABB2023-001", "DT-500-11", temperature, 65.2);
        }
        return temperature;
    }

    @GetMapping("/historyData")
    @CrossOrigin(origins = "http://localhost:8080/")
    public List<TransformerReadingDetails> getHistoryData() {
        System.out.println("Historic Data");
        DatabaseOperation operation = new DatabaseOperation();
        return operation.read();
    }

    @GetMapping("/sensorData")
    @CrossOrigin(origins = "http://localhost:8080/")
    public Map<String, Double> getDummy(){
        Map<String, Double> myMap = new HashMap<>();
        myMap.put("temperature", 23.5);
        return myMap;
    }

    @GetMapping("/historyRealTimeData")
    @CrossOrigin(origins = "http://localhost:8080/")
    public List<TransformerReadingDetails> getHistoryDataRealTime() {
        System.out.println("Historic Real Data");
        ExcelReader reader = new ExcelReader();
        return reader.readDataFromExcel();
    }
}

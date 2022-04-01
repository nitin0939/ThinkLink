package com.ThinkLink.event;

import com.ThinkLink.entities.AssetHistoricalData;
import com.ThinkLink.repository.DataRepository;
import com.ThinkLink.utility.AlertEmail;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class BTCPriceMonitor {
    @Autowired
    DataRepository dataRepository;

    @Value("${myconfig.minvalue}")
    Integer min;

    @Value("${myconfig.maxvalue}")
    Integer max;

    @Value("${myconfig.uri}")
    String uri;

    @Autowired
    AlertEmail alertEmail;

    @Scheduled(cron = "0/30 * * * * ?")
    public void monitor(){
        System.out.println("============min:"+min+"=========max:"+max);
        try {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
            System.out.println("result "+result);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(result);
            JSONObject obj= (JSONObject) json.get("bitcoin");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
            AssetHistoricalData data = new AssetHistoricalData();
            if(null !=obj && !obj.isEmpty())
                data.setCoin("btc");
            LocalDateTime ldt = LocalDateTime.now();
            ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
            ZonedDateTime utc = zdt.withZoneSameInstant(ZoneId.of("UTC"));
            Timestamp timestamp = Timestamp.valueOf(utc.toLocalDateTime());
            data.setTimestamp(utc.toLocalDateTime().toString());
            data.setDate(sdf.format(timestamp));
            Long usd= (Long) obj.get("usd");
            sendAlertMail(usd);
            System.out.println("##############");
            data.setPrice(usd);
            dataRepository.save(data);
            System.out.println("==============="+usd);
            //return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
        e.printStackTrace();
            System.out.println("error-----------------");
        //return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    private void sendAlertMail(Long currentPrice){
        System.out.println("came here================");
        if(min < currentPrice && max > currentPrice)
            return;
        String alertMessage=null;
        if(min > currentPrice){
            alertMessage = "BTC price below goes below then Minimum Value set, Min: "+min+" currentPrice: "+currentPrice;
            alertEmail.sendEmail(alertMessage);
        }else if (max < currentPrice){
            alertMessage = "BTC price goes beyond Maximum Value set, Max: "+max+" currentPrice: "+currentPrice;
            alertEmail.sendEmail(alertMessage);
        }

    }
}

package com.ThinkLink.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ThinkLink.entities.AssetHistoricalData;
import com.ThinkLink.repository.DataRepository;
import com.ThinkLink.response.DataDto;
import com.ThinkLink.response.ResponseDto;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceService {

    @Value("${smtp.host}")
    String  host;

    @Autowired
    DataRepository dataRepository;

    public ResponseDto getPriceData(String date, int offset, int limit) {
        String url="http://localhost:3030/api/prices/btc?date="+date+"&offset="+offset+"&limit="+limit;
        String next="http://localhost:3030/api/prices/btc?date="+date+"&offset="+(offset+limit)+"&limit="+limit;
        ResponseDto responseDto = new ResponseDto();
        responseDto.setUrl(url);
        responseDto.setNext(next);
/*        Pageable pageable = new OffsetBasedPageRequest(limit, offset);
        List<Data> dataList = dataRepository.findByDate(date, pageable);*/
        List<AssetHistoricalData> dataList = dataRepository.findByDate(date, limit,offset);
        responseDto.setCount(dataList.size());
        List<DataDto> DtoList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<DataDto>> mapType = new TypeReference<List<DataDto>>() {
        };
        JSONArray jsonArray;
        String json = new Gson().toJson(dataList);

        try {
            jsonArray = new JSONArray(json);
            DtoList = objectMapper.readValue(jsonArray.toString(), mapType);

        } catch (Exception e) {
            //logger.info("##############updateUpstreamLineItemsVisibleFlag|service exceptionCaughte######### "+e.getStackTrace());
        }
        responseDto.setData(DtoList);
        return responseDto;
    }
}

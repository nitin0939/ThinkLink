package com.ThinkLink.controller;

import com.ThinkLink.response.ResponseDto;
import com.ThinkLink.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/api")
public class PriceController {
    @Autowired
    private PriceService priceService;

    @GetMapping("/prices/btc")
    public ResponseDto getRoutePointFile(@QueryParam("date") String date,
                                         @QueryParam("offset") int offset,
                                         @QueryParam("limit") int limit) {
        return priceService.getPriceData(date,offset,limit);
    }
}

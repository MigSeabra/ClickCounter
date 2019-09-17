package com.controllers;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class ClickCounterController {

    //Get exchange rate from Currency A to Currency B
    @RequestMapping(value="/exchange", params = {"currA", "currB"}, method=GET)
    public ResponseEntity<Object> generateResponseA(@RequestParam(value="currA") String currA, @RequestParam(value="currB") String currB) throws Exception {

        if(currA.isEmpty() || currB.isEmpty())
            throw new IllegalArgumentException("Missing request parameters");

     //   JSONObject data = DataCall.fetchAllCurrA(currA);

        Map<String, Double> result = new HashMap<>();
    //    result.put(currB,data.getJSONObject("rates").getDouble(currB));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
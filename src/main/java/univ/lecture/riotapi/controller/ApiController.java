package univ.lecture.riotapi.controller;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import ch.qos.logback.classic.Logger;
import univ.lecture.riotapi.model.JSONResult;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by tchi on 2017. 4. 1..
 */
@RestController
@RequestMapping("/api/v1/")
@Log4j
public class ApiController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${riot.api.endpoint}")
    private String riotApiEndpoint;

    @Value("${riot.api.key}")
    private String riotApiKey;

    @RequestMapping(value = "/calc", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONResult queryResult(@RequestParam(value = "name", required = false) @RequestBody String expression) throws UnsupportedEncodingException {
        final String url = riotApiEndpoint;
        final int teamId = 8; //조번호(8조) 
        double mathResult;
        
        
        //Map<String, Object> parsedMap = new JacksonJsonParser().parseMap(response);
        //parsedMap.forEach((key, value) -> log.info(String.format("key [%s] type [%s] value [%s]", key, value.getClass(), value)));
        //Map<String, Object> summonerDetail = (Map<String, Object>) parsedMap.values().toArray()[0];
        //String queriedName = (String)summonerDetail.get("response");
        //int queriedLevel = (Integer)summonerDetail.get("result");
        
        /* 수식을 계산 */
        CalcApp app = new CalcApp(expression);
        mathResult = app.getResult();
        
        /* 현재를 나타내는 시간값을 long형으로 반환 */
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddkkmm");
		String strTime = dateFormat.format((Calendar.getInstance()).getTime());
		long now = Long.parseLong(strTime);
		
		String response = restTemplate.postForObject(url, mathResult, String.class);
		Gson gson = new Gson();
		JSONResult result = new JSONResult(teamId, now, gson.toJson(mathResult), response);
		
        return result;
        
    }
}

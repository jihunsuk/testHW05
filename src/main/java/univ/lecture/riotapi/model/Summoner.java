package univ.lecture.riotapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by tchi on 2017. 4. 1..
 */
@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Summoner {
    private String name;
    private int summonerLevel;
    private int result;
    public Summoner(String name, int summonerLevel, int result){
    	this.name = name;
    	this.summonerLevel= summonerLevel;
    	this.result = result;
    }
}

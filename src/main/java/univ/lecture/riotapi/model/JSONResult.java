package univ.lecture.riotapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by tchi on 2017. 4. 1..
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JSONResult {
	private int teamId;
	private long now;
    private String result;
}


package es.pode.soporte.elastic;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Hits {

    private int total;
    private double maxScore;
    private double max_score;
    private List<Hit> hits = new ArrayList<Hit>();

    /**
     * 
     * @return
     *     The total
     */
    public int getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 
     * @return
     *     The maxScore
     */
    public double getMaxScore() {
        return maxScore;
    }

    /**
     * 
     * @param maxScore
     *     The max_score
     */
    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    /**
     * 
     * @return
     *     The hits
     */
    public List<Hit> getHits() {
        return hits;
    }

    /**
     * 
     * @param hits
     *     The hits
     */
    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

	public double getMax_score() {
		return max_score;
	}

	public void setMax_score(double maxScore) {
		max_score = maxScore;
	}

}

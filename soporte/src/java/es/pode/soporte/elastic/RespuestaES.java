
package es.pode.soporte.elastic;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class RespuestaES {

    private int took;
    private boolean timedOut;
    private boolean timed_out;
    private Shards shards;
    private Shards _shards;
    private Hits hits;

    /**
     * 
     * @return
     *     The took
     */
    public int getTook() {
        return took;
    }

    /**
     * 
     * @param took
     *     The took
     */
    public void setTook(int took) {
        this.took = took;
    }

    /**
     * 
     * @return
     *     The timedOut
     */
    public boolean isTimedOut() {
        return timedOut;
    }

    /**
     * 
     * @param timedOut
     *     The timed_out
     */
    public void setTimedOut(boolean timedOut) {
        this.timedOut = timedOut;
    }

    /**
     * 
     * @return
     *     The shards
     */
    public Shards getShards() {
        return shards;
    }

    /**
     * 
     * @param shards
     *     The _shards
     */
    public void setShards(Shards shards) {
        this.shards = shards;
    }

    /**
     * 
     * @return
     *     The hits
     */
    public Hits getHits() {
        return hits;
    }

    /**
     * 
     * @param hits
     *     The hits
     */
    public void setHits(Hits hits) {
        this.hits = hits;
    }

	public boolean isTimed_out() {
		return timed_out;
	}

	public void setTimed_out(boolean timedOut) {
		timed_out = timedOut;
	}

	public Shards get_shards() {
		return _shards;
	}

	public void set_shards(Shards shards) {
		_shards = shards;
	}

}

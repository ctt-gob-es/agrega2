
package es.pode.soporte.elastic;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Shards {

    private int total;
    private int successful;
    private int failed;

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
     *     The successful
     */
    public int getSuccessful() {
        return successful;
    }

    /**
     * 
     * @param successful
     *     The successful
     */
    public void setSuccessful(int successful) {
        this.successful = successful;
    }

    /**
     * 
     * @return
     *     The failed
     */
    public int getFailed() {
        return failed;
    }

    /**
     * 
     * @param failed
     *     The failed
     */
    public void setFailed(int failed) {
        this.failed = failed;
    }

}

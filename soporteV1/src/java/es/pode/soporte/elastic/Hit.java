
package es.pode.soporte.elastic;

import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Hit {

    private String index;
    private String type;
    private String id;
    private double score;
    private Source source;
    private String _index;
    private String _type;
    private String _id;
    private double _score;
    private Source _source;    
    private List<String> sort; 
    
    /**
     * 
     * @return
     *     The index
     */
    public String getIndex() {
        return index;
    }

    /**
     * 
     * @param index
     *     The _index
     */
    public void setIndex(String index) {
        this.index = index;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The _type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The _id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The score
     */
    public double getScore() {
        return score;
    }

    /**
     * 
     * @param score
     *     The _score
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * 
     * @return
     *     The source
     */
    public Source getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *     The _source
     */
    public void setSource(Source source) {
        this.source = source;
    }

	public String get_index() {
		return _index;
	}

	public void set_index(String index) {
		_index = index;
	}

	public String get_type() {
		return _type;
	}

	public void set_type(String type) {
		_type = type;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String id) {
		_id = id;
	}

	public double get_score() {
		return _score;
	}

	public void set_score(double score) {
		_score = score;
	}

	public Source get_source() {
		return _source;
	}

	public void set_source(Source source) {
		_source = source;
	}

	public List<String> getSort() {
		return sort;
	}

	public void setSort(List<String> sort) {
		this.sort = sort;
	}
	
}

/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.1</a>, using an XML
 * Schema.
 * $Id$
 */

package es.pode.parseadorXML.castor.types;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Hashtable;

/**
 * Class LearningResourceTypeValues.
 * 
 * @version $Revision$ $Date$
 */
public class LearningResourceTypeValues implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The exercise type
     */
    public static final int EXERCISE_TYPE = 0;

    /**
     * The instance of the exercise type
     */
    public static final LearningResourceTypeValues EXERCISE = new LearningResourceTypeValues(EXERCISE_TYPE, "exercise");

    /**
     * The simulation type
     */
    public static final int SIMULATION_TYPE = 1;

    /**
     * The instance of the simulation type
     */
    public static final LearningResourceTypeValues SIMULATION = new LearningResourceTypeValues(SIMULATION_TYPE, "simulation");

    /**
     * The questionnaire type
     */
    public static final int QUESTIONNAIRE_TYPE = 2;

    /**
     * The instance of the questionnaire type
     */
    public static final LearningResourceTypeValues QUESTIONNAIRE = new LearningResourceTypeValues(QUESTIONNAIRE_TYPE, "questionnaire");

    /**
     * The diagram type
     */
    public static final int DIAGRAM_TYPE = 3;

    /**
     * The instance of the diagram type
     */
    public static final LearningResourceTypeValues DIAGRAM = new LearningResourceTypeValues(DIAGRAM_TYPE, "diagram");

    /**
     * The figure type
     */
    public static final int FIGURE_TYPE = 4;

    /**
     * The instance of the figure type
     */
    public static final LearningResourceTypeValues FIGURE = new LearningResourceTypeValues(FIGURE_TYPE, "figure");

    /**
     * The graph type
     */
    public static final int GRAPH_TYPE = 5;

    /**
     * The instance of the graph type
     */
    public static final LearningResourceTypeValues GRAPH = new LearningResourceTypeValues(GRAPH_TYPE, "graph");

    /**
     * The index type
     */
    public static final int INDEX_TYPE = 6;

    /**
     * The instance of the index type
     */
    public static final LearningResourceTypeValues INDEX = new LearningResourceTypeValues(INDEX_TYPE, "index");

    /**
     * The slide type
     */
    public static final int SLIDE_TYPE = 7;

    /**
     * The instance of the slide type
     */
    public static final LearningResourceTypeValues SLIDE = new LearningResourceTypeValues(SLIDE_TYPE, "slide");

    /**
     * The table type
     */
    public static final int TABLE_TYPE = 8;

    /**
     * The instance of the table type
     */
    public static final LearningResourceTypeValues TABLE = new LearningResourceTypeValues(TABLE_TYPE, "table");

    /**
     * The narrative text type
     */
    public static final int NARRATIVE_TEXT_TYPE = 9;

    /**
     * The instance of the narrative text type
     */
    public static final LearningResourceTypeValues NARRATIVE_TEXT = new LearningResourceTypeValues(NARRATIVE_TEXT_TYPE, "narrative text");

    /**
     * The exam type
     */
    public static final int EXAM_TYPE = 10;

    /**
     * The instance of the exam type
     */
    public static final LearningResourceTypeValues EXAM = new LearningResourceTypeValues(EXAM_TYPE, "exam");

    /**
     * The experiment type
     */
    public static final int EXPERIMENT_TYPE = 11;

    /**
     * The instance of the experiment type
     */
    public static final LearningResourceTypeValues EXPERIMENT = new LearningResourceTypeValues(EXPERIMENT_TYPE, "experiment");

    /**
     * The problem statement type
     */
    public static final int PROBLEM_STATEMENT_TYPE = 12;

    /**
     * The instance of the problem statement type
     */
    public static final LearningResourceTypeValues PROBLEM_STATEMENT = new LearningResourceTypeValues(PROBLEM_STATEMENT_TYPE, "problem statement");

    /**
     * The self assessment type
     */
    public static final int SELF_ASSESSMENT_TYPE = 13;

    /**
     * The instance of the self assessment type
     */
    public static final LearningResourceTypeValues SELF_ASSESSMENT = new LearningResourceTypeValues(SELF_ASSESSMENT_TYPE, "self assessment");

    /**
     * The lecture type
     */
    public static final int LECTURE_TYPE = 14;

    /**
     * The instance of the lecture type
     */
    public static final LearningResourceTypeValues LECTURE = new LearningResourceTypeValues(LECTURE_TYPE, "lecture");

    /**
     * Field _memberTable.
     */
    private static java.util.Hashtable _memberTable = init();

    /**
     * Field type.
     */
    private int type = -1;

    /**
     * Field stringValue.
     */
    private java.lang.String stringValue = null;


      //----------------/
     //- Constructors -/
    //----------------/

    private LearningResourceTypeValues(final int type, final java.lang.String value) {
        super();
        this.type = type;
        this.stringValue = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate.Returns an enumeration of all possible
     * instances of LearningResourceTypeValues
     * 
     * @return an Enumeration over all possible instances of
     * LearningResourceTypeValues
     */
    public static java.util.Enumeration enumerate(
    ) {
        return _memberTable.elements();
    }

    /**
     * Method getType.Returns the type of this
     * LearningResourceTypeValues
     * 
     * @return the type of this LearningResourceTypeValues
     */
    public int getType(
    ) {
        return this.type;
    }

    /**
     * Method init.
     * 
     * @return the initialized Hashtable for the member table
     */
    private static java.util.Hashtable init(
    ) {
        Hashtable members = new Hashtable();
        members.put("exercise", EXERCISE);
        members.put("simulation", SIMULATION);
        members.put("questionnaire", QUESTIONNAIRE);
        members.put("diagram", DIAGRAM);
        members.put("figure", FIGURE);
        members.put("graph", GRAPH);
        members.put("index", INDEX);
        members.put("slide", SLIDE);
        members.put("table", TABLE);
        members.put("narrative text", NARRATIVE_TEXT);
        members.put("exam", EXAM);
        members.put("experiment", EXPERIMENT);
        members.put("problem statement", PROBLEM_STATEMENT);
        members.put("self assessment", SELF_ASSESSMENT);
        members.put("lecture", LECTURE);
        return members;
    }

    /**
     * Method readResolve. will be called during deserialization to
     * replace the deserialized object with the correct constant
     * instance.
     * 
     * @return this deserialized object
     */
    private java.lang.Object readResolve(
    ) {
        return valueOf(this.stringValue);
    }

    /**
     * Method toString.Returns the String representation of this
     * LearningResourceTypeValues
     * 
     * @return the String representation of this
     * LearningResourceTypeValues
     */
    public java.lang.String toString(
    ) {
        return this.stringValue;
    }

    /**
     * Method valueOf.Returns a new LearningResourceTypeValues
     * based on the given String value.
     * 
     * @param string
     * @return the LearningResourceTypeValues value of parameter
     * 'string'
     */
    public static es.pode.parseadorXML.castor.types.LearningResourceTypeValues valueOf(
            final java.lang.String string) {
        java.lang.Object obj = null;
        if (string != null) {
            obj = _memberTable.get(string);
        }
        if (obj == null) {
            String err = "" + string + " is not a valid LearningResourceTypeValues";
            throw new IllegalArgumentException(err);
        }
        return (LearningResourceTypeValues) obj;
    }

}

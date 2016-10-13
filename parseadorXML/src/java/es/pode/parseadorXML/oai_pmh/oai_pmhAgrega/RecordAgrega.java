package es.pode.parseadorXML.oai_pmh.oai_pmhAgrega;

import java.io.Serializable;

import org.apache.log4j.Logger;

import es.pode.parseadorXML.oai_pmh.About;
import es.pode.parseadorXML.oai_pmh.Metadata;
import es.pode.parseadorXML.oai_pmh.RecordType;
import es.pode.parseadorXML.oai_pmh.RecordTypeHeader;

public class RecordAgrega implements Serializable{
	private RecordType record=null;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public RecordAgrega(){
		super();
		record= new RecordType();
	}
	
	public RecordAgrega(RecordType record){
		super();
		this.record = record;
	}
		
	public RecordType getRecord() {
		return record;
	}

	public void setRecord(RecordType record) {
		this.record = record;
	}
	
	public RecordTypeHeader getHeader(){
		RecordTypeHeader header = record.getRecordTypeHeader();
		return header;
	}
	
	public void setHeader(RecordTypeHeader header){
		record.setRecordTypeHeader(header);
	}
	
	public Object getMetadata(){
		Object metadata =record.getMetadata().getAnyObject();
		return metadata;
	}
	
	public void setMetadata(Object metadata){
		if(record.getMetadata()==null){
			Metadata md= new Metadata();
			record.setMetadata(md);
		}
		record.getMetadata().setAnyObject(metadata);	
	}

	public About[] getAbout(){
		About[] about = record.getAbout();
		return about;
	}
	
	public About getAboutIndex(int index){
		About about = record.getAbout(index);
		return about;
	}
	
	public void setAbout(About[] aboutArray){
		record.setAbout(aboutArray);
	}
	
	public void setAbout(About about, int index){
		if(record.getAboutCount()>index)
			record.setAbout(index, about);
		else
			record.addAbout(about);
	}

	public void addAbout(About about){
		record.addAbout(about);
	}
	
	public void addAbout(int index,About about){
		if(record.getAboutCount()>index)
			record.addAbout(index, about);
		else
			record.addAbout(about);
	}
	
}


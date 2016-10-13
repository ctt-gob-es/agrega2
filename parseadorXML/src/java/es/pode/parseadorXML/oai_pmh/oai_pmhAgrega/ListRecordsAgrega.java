/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.parseadorXML.oai_pmh.oai_pmhAgrega;

import org.apache.log4j.Logger;

import es.pode.parseadorXML.oai_pmh.ListRecords;
import es.pode.parseadorXML.oai_pmh.ListRecordsTypeRecord;
import es.pode.parseadorXML.oai_pmh.ListRecordsTypeResumptionToken;

public class ListRecordsAgrega {
	private ListRecords listRecords=null;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public ListRecordsAgrega(){
		super();
		listRecords= new ListRecords();
	}
	
	public ListRecordsAgrega(ListRecords listRecords){
		super();
		this.listRecords = listRecords;
	}

	public ListRecords getListRecords() {
		return listRecords;
	}

	public void setListRecords(ListRecords listRecords) {
		this.listRecords = listRecords;
	}
	
	public ListRecordsTypeResumptionToken getResumptionToken(){
		if(listRecords!=null)
			return listRecords.getListRecordsTypeResumptionToken();
		else 
			return null;
	}
	
	public void setResumptionToken(ListRecordsTypeResumptionToken resumptionToken){
		if(listRecords!=null)
			listRecords.setListRecordsTypeResumptionToken(resumptionToken);
	}
	
	public RecordAgrega getRecord(int index){
		return new RecordAgrega(listRecords.getListRecordsTypeRecord(index));
	}
	
	public RecordAgrega[] getRecords(){
		ListRecordsTypeRecord[] lRecords = listRecords.getListRecordsTypeRecord();
		RecordAgrega[] lRecordsAgrega = new RecordAgrega[lRecords.length];
		for(int i=0;i<lRecords.length;i++){
			lRecordsAgrega[i]=new RecordAgrega(lRecords[i]);
		}
		return lRecordsAgrega;
	}
	
	public void setRecord(int index, RecordAgrega record){
		ListRecordsTypeRecord lRecord = new ListRecordsTypeRecord();
		if(record.getRecord()!=null){
			lRecord.setAbout(record.getRecord().getAbout());
			lRecord.setMetadata(record.getRecord().getMetadata());
			lRecord.setRecordTypeHeader(record.getRecord().getRecordTypeHeader());
		}
		if(listRecords.getListRecordsTypeRecordCount()>index)
			listRecords.setListRecordsTypeRecord(index, lRecord);
		else
			listRecords.addListRecordsTypeRecord(lRecord);
	}
	
	
	public void setRecord(RecordAgrega[] lRecordAgrega){
		ListRecordsTypeRecord record = new ListRecordsTypeRecord();
		ListRecordsTypeRecord[] lRecord = new ListRecordsTypeRecord[lRecordAgrega.length];
		for(int i=0;i<lRecordAgrega.length;i++){
			if(lRecordAgrega[i].getRecord()!=null){
				record.setAbout(lRecordAgrega[i].getRecord().getAbout());
				record.setMetadata(lRecordAgrega[i].getRecord().getMetadata());
				record.setRecordTypeHeader(lRecordAgrega[i].getRecord().getRecordTypeHeader());
			}
			lRecord[i]= record;
		}
		listRecords.setListRecordsTypeRecord(lRecord);
	}
	
	public void addRecord(RecordAgrega record){
		ListRecordsTypeRecord lRecord = new ListRecordsTypeRecord();
		if(record.getRecord()!=null){
			lRecord.setAbout(record.getRecord().getAbout());
			lRecord.setMetadata(record.getRecord().getMetadata());
			lRecord.setRecordTypeHeader(record.getRecord().getRecordTypeHeader());
		}
		listRecords.addListRecordsTypeRecord(lRecord);
	}
	
	public void addRecord(int index,RecordAgrega record){
		ListRecordsTypeRecord lRecord = new ListRecordsTypeRecord();
		if(record.getRecord()!=null){
			lRecord.setAbout(record.getRecord().getAbout());
			lRecord.setMetadata(record.getRecord().getMetadata());
			lRecord.setRecordTypeHeader(record.getRecord().getRecordTypeHeader());
		}
		if(listRecords.getListRecordsTypeRecordCount()>index)	
			listRecords.setListRecordsTypeRecord(index, lRecord);	
		else
			listRecords.addListRecordsTypeRecord(lRecord);
	}
	
}

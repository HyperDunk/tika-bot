package edu.usc.csci572.crawler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.HashSet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONTableContentHandler extends DefaultHandler {
	private JobsData jData;
	private String currentElement = "";
	private int tdCount;
	private String currentTDElement = "";
	//private PrintWriter outReport = null;
	private PrintWriter outCount = null;

	private static int i = 0;
	public static long totalCount = 0;
	public static long actualCount = 0;
	public static boolean deduplication = false;
	public static HashSet<String> dedupMap = new HashSet<String>();
	public static String jsonOutputPath;
	public static String fileName;

	private String[] colName = new String[] { "postedDate", "location",
			"department", "title", "salary", "dummy", "start", "duration",
			"jobtype", "applications", "company", "contactPerson",
			"phoneNumber", "faxNumber", "location2", "latitude", "longitude",
			"firstSeenDate", "url", "lastSeenDate" };
	
	/*public PrintWriter getOutReport() {
		return this.outReport;
	}*/
	
	public PrintWriter getOutCount() {
		return this.outCount;
	}

	public JSONTableContentHandler() {
		// TODO Auto-generated constructor stub

		try {
			//outReport = new PrintWriter("report.txt");
			outCount = new PrintWriter("count.txt");
			outCount.println(Calendar.getInstance().getTime());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getAllJsons() {
		// TODO Auto-generated method stub

	}

	public static void parseXHTML(InputStream is) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startDocument() throws SAXException {
		jData = new JobsData();
		i = 0;
		actualCount = 0;
	}

	@Override
	public void endDocument() throws SAXException {
		outCount.println(fileName + "\nCount = " + i);
		outCount.println("Actual Count in the file: " + actualCount);
		outCount.println("Overall Total Count: " + totalCount + "\n");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		currentElement = qName;

		switch (currentElement) {
		case "tr":
			tdCount = 0;
			break;
		case "td": {
			currentTDElement = colName[tdCount];
			tdCount++;
			break;
		}
		default:
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		currentElement = "";
		currentTDElement = "";
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String currentString = new String(ch, start, length);
		if (currentString.equals(null))
			currentString = "";
		// System.out.println("Characters " + currentString);
		switch (currentTDElement) {
		case "location":
			jData.setLocation(currentString);
			break;
		case "postedDate":
			jData.setPostedDate(currentString);
			break;
		case "department":
			jData.setDepartment(currentString);
			break;
		case "title":
			jData.setTitle(currentString);
			break;
		case "salary":
			jData.setSalary(currentString);
			break;
		case "dummy":
			jData.setDummy(currentString);
			break;
		case "start":
			jData.setStart(currentString);
			break;
		case "duration":
			jData.setDuration(currentString);
			break;
		case "jobtype":
			jData.setJobtype(currentString);
			break;
		case "applications":
			jData.setApplications(currentString);
			break;
		case "company":
			jData.setCompany(currentString);
			break;

		case "contactPerson":
			jData.setContactPerson(currentString);
			break;
		case "phoneNumber":
			jData.setPhoneNumber(currentString);
			break;
		case "faxNumber":
			jData.setFaxNumber(currentString);
			break;
		case "location2":
			jData.setLocation2(currentString);
			break;
		case "latitude":
			jData.setLatitude(currentString);
			break;
		case "longitude":
			jData.setLongitude(currentString);
			break;
		case "firstSeenDate":
			jData.setFirstSeenDate(currentString);
			break;
		case "lastSeenDate":
			jData.setLastSeenDate(currentString);
			serializeJSON(jData);
			break;
		case "url":
			jData.setUrl(currentString);
			break;

		default:
			break;
		}
	}

	/*Serializes the individual JSON*/
	public void serializeJSON(JobsData job) {
		actualCount++;
		String title = job.getTitle(), 
				company = job.getCompany(), 
				department = job.getDepartment(), 
				app = job.getApplications(), 
				jobtype = job.getJobtype(), 
				location = job.getLocation(), 
				url = job.getUrl();
		if(title.length() == 0) {
			title = "e-title";
		}
		if(company.length() == 0) {
			company = "e-comp";
		}
		if(department.length() == 0) {
			department = "e-dept";
		}
		if(app.length() == 0) {
			app = "e-app";
		}
		if(jobtype.length() == 0) {
			jobtype = "e-jobtype";
		}
		if(location.length() == 0) {
			location = "e-loc";
		}
		if(url.length() == 0) {
			url = "e-url";
		}
		
		if (deduplication) {
			String key = 
					title 
					+ company
					+ department 
					+ app
					+ jobtype 
					+ location
					;
			if (dedupMap.contains(key.toLowerCase())) {
				//outReport.println("Dup: " + fileName + ". Title: " + job.getTitle());
				return;
			} else {
				dedupMap.add(key.toLowerCase());
				totalCount++;
			}			
		} else {
			totalCount++;
		}

		i++;
		Writer writer;
		try {
			File file = new File(jsonOutputPath + "/" + fileName);
			file.mkdirs();
			writer = new FileWriter(jsonOutputPath + "/" + fileName + "/"
					+ fileName + "-" + i + ".json");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(job, writer);
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
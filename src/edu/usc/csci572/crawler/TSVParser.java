package edu.usc.csci572.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Set;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AbstractParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.XHTMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class TSVParser extends AbstractParser {
	private static int count = 0;
	private static final long serialVersionUID = -7703188620341736671L;

	private static final Set<MediaType> SUPPORTED_TYPES = Collections
			.singleton(MediaType.TEXT_HTML);

	@Override
	public Set<MediaType> getSupportedTypes(ParseContext arg0) {
		// TODO Auto-generated method stub
		return SUPPORTED_TYPES;
	}

	@Override
	public void parse(InputStream stream, ContentHandler handler,
			Metadata metadata, ParseContext context) throws IOException,
			SAXException, TikaException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(stream));
		try {
			Charset charset = Charset.forName("UTF-8");
			MediaType type = new MediaType(
					MediaType.parse("application/html+xml"), charset);
			metadata.set(Metadata.CONTENT_TYPE, type.toString());
			metadata.set(Metadata.CONTENT_ENCODING, charset.name());

			XHTMLContentHandler xhtml = new XHTMLContentHandler(handler,
					metadata);
			xhtml.startDocument();

			xhtml.startElement("table");
			String line;

			String headers[] = { "postedDate", "location", "department",
					"title", "salary", "dummy", "start", "duration", "jobtype",
					"applications", "company", "contactPerson", "phoneNumber",
					"faxNumber", "location", "latitude", "longitude",
					"firstSeenDate", "url", "lastSeenDate" };
			xhtml.startElement("tr");

			for (int i = 0; i < headers.length; i++) {
				xhtml.startElement("th");
				xhtml.characters(headers[i]);
				xhtml.endElement("th");
			}
			xhtml.endElement("tr");
			while ((line = reader.readLine()) != null) {
				xhtml.startElement("tr");
				count = 0;
				JobsData jData = new JobsData();
				String[] columns = line.split("\t");
				for (int i = 0; i < columns.length; i++) {

					xhtml.startElement("td");
					count++;
					xhtml.characters(columns[i]);
					fillJobsData(count, columns[i], jData);
					xhtml.endElement("td");
				}

				xhtml.endElement("tr");
			}
			xhtml.endElement("table");
			xhtml.endDocument();
		} finally {
			reader.close();
		}

	}

	private void fillJobsData(int count, String currentString, JobsData jData) {
		// TODO Auto-generated method stub
		if (currentString.equals(null))
			currentString = "";

		switch (count) {
		case 2:
			jData.setLocation(currentString);
			break;
		case 1:
			jData.setPostedDate(currentString);
			break;
		case 3:
			jData.setDepartment(currentString);
			break;
		case 4:
			jData.setTitle(currentString);
			break;
		case 5:
			jData.setSalary(currentString);
			break;
		case 6:
			jData.setDummy(currentString);
			break;
		case 7:
			jData.setStart(currentString);
			break;
		case 8:
			jData.setDuration(currentString);
			break;
		case 9:
			jData.setJobtype(currentString);
			break;
		case 10:
			jData.setApplications(currentString);
			break;
		case 11:
			jData.setCompany(currentString);
			break;

		case 12:
			jData.setContactPerson(currentString);
			break;
		case 13:
			jData.setPhoneNumber(currentString);
			break;
		case 14:
			jData.setFaxNumber(currentString);
			break;
		case 15:
			jData.setLocation2(currentString);
			break;
		case 16:
			jData.setLatitude(currentString);
			break;
		case 17:
			jData.setLongitude(currentString);
			break;
		case 18:
			jData.setFirstSeenDate(currentString);
			break;
		case 20:
			jData.setLastSeenDate(currentString);
			JSONTableContentHandler.serializeJSON(jData);
			break;
		case 19:
			jData.setUrl(currentString);
			break;

		default:
			break;
		}

	}

}

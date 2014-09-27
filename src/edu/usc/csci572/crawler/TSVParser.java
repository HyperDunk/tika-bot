package edu.usc.csci572.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Set;

import org.apache.tika.config.ServiceLoader;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AbstractParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.XHTMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class TSVParser extends AbstractParser {

	private static final long serialVersionUID = -7703188620341736671L;

	private static final Set<MediaType> SUPPORTED_TYPES =
        Collections.singleton(MediaType.TEXT_HTML);
	
	private static final ServiceLoader LOADER =
        new ServiceLoader(TSVParser.class.getClassLoader());
	
	@Override
	public Set<MediaType> getSupportedTypes(ParseContext arg0) {
		// TODO Auto-generated method stub
		return SUPPORTED_TYPES;
	}

	@Override
	public void parse(InputStream stream, ContentHandler handler, Metadata metadata,
			ParseContext context) throws IOException, SAXException, TikaException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try {
            Charset charset = Charset.forName("UTF-8");
            MediaType type = new MediaType(MediaType.parse("application/html+xml"), charset);
            metadata.set(Metadata.CONTENT_TYPE, type.toString());
            metadata.set(Metadata.CONTENT_ENCODING, charset.name());

            XHTMLContentHandler xhtml =
                    new XHTMLContentHandler(handler, metadata);
            xhtml.startDocument();
            
            xhtml.startElement("table");
            String line;
            
            String headers[] = {"postedDate", "location", "department", "title", "salary", "start", "duration", 
            		"jobtype", "applications", "company", "contactPerson", "phoneNumber", "faxNumber", "location", 
            		"latitude", "longitude", "firstSeenDate", "url", "lastSeenDate"};
            xhtml.startElement("tr");
            for(int i=0; i<headers.length; i++) {
            	xhtml.startElement("th");
            	xhtml.characters(headers[i]);
            	xhtml.endElement("th");
            }
            xhtml.endElement("tr");
            while((line = reader.readLine()) != null) {
            	xhtml.startElement("tr");
            	
            	String[] columns = line.split("\t");
            	for(int i=0; i<columns.length; i++) {
            		xhtml.startElement("td");
            		xhtml.characters(columns[i]);
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

}

package edu.usc.csci572.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.TeeContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class Crawler {

	public static void main(String args[]) throws IOException, TikaException,
			SAXException, TransformerConfigurationException {

		InputStream is = null;
		OutputStream output = null;

		String datasetPath = "C:/Users/Aakarsh/Google Drive/USC-StudyMaterials/CSCI572-IR/Assignments/Assignment1/workspace/HW1/data-set/";

		File folder = new File(datasetPath);
		for (final File fileEntry : folder.listFiles()) {
			String filePath, fileName;
			if (fileEntry.isDirectory()) {
				// Do nothing
			} else {
				filePath = fileEntry.getPath();
				fileName = fileEntry.getName();
				fileName = fileName.substring(0,fileName.length()-4);

				try {
					File doc = new File(filePath);
					output = new FileOutputStream("C:/Users/Aakarsh/Google Drive/USC-StudyMaterials/CSCI572-IR/Assignments/Assignment1/workspace/HW1/output/"+fileName+".xhtml");

					ContentHandler handler = getTransformerHandler(output,
							"XML", "UTF-8", true);
					ParseContext context = new ParseContext();
					context.set(Locale.class, Locale.ENGLISH);

					is = TikaInputStream.get(doc);

					Parser tsvParser = new TSVParser();
					tsvParser.parse(is, handler, new Metadata(), context);
				} finally {
					if (is != null) {
						is.close();
					}
					output.close();
				}
			}
		}

	}

	/**
	 * Returns a transformer handler that serializes incoming SAX events to
	 * XHTML or HTML (depending the given method) using the given output
	 * encoding.
	 * 
	 * @see <a
	 *      href="https://issues.apache.org/jira/browse/TIKA-277">TIKA-277</a>
	 * @param output
	 *            output stream
	 * @param method
	 *            "xml" or "html"
	 * @param encoding
	 *            output encoding, or <code>null</code> for the platform default
	 * @return {@link System#out} transformer handler
	 * @throws TransformerConfigurationException
	 *             if the transformer can not be created
	 */
	private static TransformerHandler getTransformerHandler(
			OutputStream output, String method, String encoding,
			boolean prettyPrint) throws TransformerConfigurationException {
		SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory
				.newInstance();
		TransformerHandler handler = factory.newTransformerHandler();
		handler.getTransformer().setOutputProperty(OutputKeys.METHOD, method);
		handler.getTransformer().setOutputProperty(OutputKeys.INDENT,
				prettyPrint ? "yes" : "no");
		if (encoding != null) {
			handler.getTransformer().setOutputProperty(OutputKeys.ENCODING,
					encoding);
		}
		handler.setResult(new StreamResult(output));
		return handler;
	}

}

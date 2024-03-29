package edu.usc.csci572.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Locale;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.TeeContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class Crawler {

	String inputPath = null;
	String xhtmlOutputPath = null;
	String jsonOutputPath = null;

	/*Parses input arguments*/
	public void parseCommand(String args[]) {

		for (int i = 0; i < args.length; i = i + 2) {
			if (args[i].equals("-i")) {
				inputPath = args[i + 1];
			} else if (args[i].equals("-xo")) {
				xhtmlOutputPath = args[i + 1];
			} else if (args[i].equals("-jo")) {
				jsonOutputPath = args[i + 1];
			} else if (args[i].equals("-d")) {
				if (Integer.parseInt(args[i + 1]) == 1) {
					JSONTableContentHandler.deduplication = true;
				}
			}
		}

		if (inputPath == null || xhtmlOutputPath == null
				|| jsonOutputPath == null) {
			System.out.println("Incomplete or incorrect input command");
			System.exit(0);
		}
	}

	/*reads tsv files and calls TSVParser*/
	public void convertTSVtoXHTMLandJSON(String tsvFilePath, String fileName,
			ContentHandler jsonContentHandler)
			throws TransformerConfigurationException, IOException,
			SAXException, TikaException {

		InputStream is = null;
		OutputStream xhtmlOutput = null;

		JSONTableContentHandler.jsonOutputPath = this.jsonOutputPath;
		JSONTableContentHandler.fileName = fileName;
		try {
			File doc = new File(tsvFilePath);
			xhtmlOutput = new FileOutputStream(this.xhtmlOutputPath + "/"
					+ fileName + ".xhtml");

			ContentHandler handler = new TeeContentHandler(
					getTransformerHandler(xhtmlOutput, "XML", "UTF-8", true),
					jsonContentHandler);
			ParseContext context = new ParseContext();
			context.set(Locale.class, Locale.ENGLISH);

			is = TikaInputStream.get(doc);

			Parser tsvParser = new TSVParser();
			tsvParser.parse(is, handler, new Metadata(), context);
		} finally {
			if (is != null) {
				is.close();
			}
			xhtmlOutput.close();
		}
	}

	/*Main crawler method*/
	public void crawl() throws TransformerConfigurationException, IOException,
			SAXException, TikaException {

		File folder = new File(this.inputPath);
		JSONTableContentHandler jsonHandler = new JSONTableContentHandler();
		ContentHandler jsonContentHandler = jsonHandler; 
		for (final File fileEntry : folder.listFiles()) {
			String filePath, fileName;
			if (fileEntry.isDirectory()) {
				// Do nothing
			} else {
				filePath = fileEntry.getPath();
				fileName = fileEntry.getName();
				fileName = fileName.substring(0, fileName.length() - 4);

				convertTSVtoXHTMLandJSON(filePath, fileName, jsonContentHandler);
			}
		}
		jsonHandler.getOutCount().println(Calendar.getInstance().getTime());
		//jsonHandler.getOutReport().close();
		jsonHandler.getOutCount().close();
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

	public static void main(String args[]) throws IOException, TikaException,
			SAXException, TransformerConfigurationException {

		Crawler crawlApp = new Crawler();

		crawlApp.parseCommand(args);
		crawlApp.crawl();		
	}

}

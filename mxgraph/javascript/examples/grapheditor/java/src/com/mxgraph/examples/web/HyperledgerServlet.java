/**
 * Copyright (c) 2011-2012, JGraph Ltd
 */
package com.mxgraph.examples.web;
//import com.mxgraph.examples.web.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;


//import org.json.JSONObject;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OpenServlet.
 * 
 * open.html implements the user interface. This file is displayed within an
 * IFRAME in order to better handle the response. The form is then processed
 * either locally if the browser implements the HTML5 FileReader API or via the
 * OpenServlet. Note that the mechanism to open files uses OpenFile in
 * Editor.js, as well as Editor.openFile when the client starts. This is
 * required to abstract away the asynchronous loading of the new editor and
 * handling of the response, which in turn calls the setData method on the
 * OpenFile instance of the parent window of the frame where open.html was
 * displayed (see below).
 */



public class HyperledgerServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4442397463551836919L;



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		



request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
//response.setContentType("application/json; charset=UTF-8");





////added
    // Read from request
    StringBuilder buffer = new StringBuilder();
    BufferedReader reader = request.getReader();
    String line;
    while ((line = reader.readLine()) != null) {
       buffer.append(line);
    }
 String data = buffer.toString();
//data="{"+data+"}";
data = data.replace("\"","");
//data = data.replace("\"","");
//data = data.replace("{","{\"");
//data = data.replace("}","\"}");

//data = data.replace(":","\":\"");
//data = data.replace("~begin","\"");
//data = data.replace("~end","\"");
//data = data.replace(",","\",\"");

//data="\'"+data+"\'";

data = data.replace("organizations:","\"organizations\":\"");
data = data.replace(",operations:","\",\"operations\":\"");
data = data.replace("}}}","}}\"}");



System.out.println(data);
//System.out.println(data.num_of_organisation);
//String[] arrayOfStrings = data.split(",");
//System.out.println(data);
//System.out.println(arrayOfStrings[0]);
//System.out.println(arrayOfStrings[1]);
//System.out.println(arrayOfStrings[2]);
//System.out.println(arrayOfStrings[3]);
///System.out.println(arrayOfStrings[4]);
//System.out.println(arrayOfStrings[5]);


//System.getProperty("java.class.path");

ProcessBuilder b = new ProcessBuilder("node","/home/doris/Desktop/sequence-diagram/mxgraph/javascript/examples/grapheditor/www/Invoke_Hyperledger.js",data);

//ProcessBuilder pb = new ProcessBuilder("node", "../../../../../www/Invoke_Hyperledger.js");

//System.out.println(arrayOfStrings[0]);

b.redirectOutput(ProcessBuilder.Redirect.INHERIT);
//File log = new File( "/tmp/log.txt" );


//b.redirectError(ProcessBuilder.Redirect.to(log));
//b.redirectError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)


 b.redirectErrorStream(true);

Process p = b.start();



response.setStatus(HttpServletResponse.SC_OK);



//PrintWriter out = response.getWriter();

//out.println("status: success");
 
        //out.close();

/////added

		/*OutputStream out = response.getOutputStream();
		String encoding = request.getHeader("Accept-Encoding");

		// Supports GZIP content encoding
		if (encoding != null && encoding.indexOf("gzip") >= 0)
		{
			response.setHeader("Content-Encoding", "gzip");
			out = new GZIPOutputStream(out);
		}

		PrintWriter writer = new PrintWriter(out);
		writer.println("<html>");
		writer.println("<head>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<script type=\"text/javascript\">");

		try
		{
			if (request.getContentLength() < Constants.MAX_REQUEST_SIZE)
			{
				Map<String, String> post = parseMultipartRequest(request);
				String xml = new String(post.get("upfile").getBytes(ENCODING),
						"UTF-8");
				String filename = post.get("filename");

				// Uses JavaScript to load the XML on the client-side
				writer.println("window.parent.openFile.setData(decodeURIComponent('"
						+ encodeURIComponent(xml) + "'), '" + filename + "');");
			}
			else
			{
				error(writer, "drawingTooLarge");
			}
		}
		catch (Exception e)
		{
			error(writer, "invalidOrMissingFile");
		}

		writer.println("</script>");
		writer.println("</body>");
		writer.println("</html>");

		writer.flush();
		writer.close();*/
	}

	

	
}

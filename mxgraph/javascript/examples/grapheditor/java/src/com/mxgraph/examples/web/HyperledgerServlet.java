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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");


    	StringBuilder buffer = new StringBuilder();
    	BufferedReader reader = request.getReader();
    	String line;
    	while ((line = reader.readLine()) != null) {
       		buffer.append(line);
    	}
 	String data = buffer.toString();

	// Creating the privateDataCollections
	int startprivateDataCollections=data.indexOf("privateDataCollections");
	int endprivateDataCollections=data.substring(startprivateDataCollections).indexOf("}")+startprivateDataCollections;

	String[] splittedprivateDataCollections = data.substring(startprivateDataCollections,endprivateDataCollections).split(",|:");

	String DataCollections="";

	for (int i=0;i<splittedprivateDataCollections.length;i++){
		if(splittedprivateDataCollections[i].contains("Org")){
			DataCollections=DataCollections+splittedprivateDataCollections[i]+" "; 
		}
	}

	ProcessBuilder b2 = new ProcessBuilder("bash","/home/doris/Desktop/sequence-diagram/mxgraph/javascript/examples/grapheditor/www/fabric-network/first-network/collections_config-generate.sh",DataCollections);
	b2.redirectOutput(ProcessBuilder.Redirect.INHERIT);
	b2.redirectErrorStream(true);
	Process p2 = b2.start();

	// Storing the Hyperledger_Network_Stats into the blockchain
	data = data.replace("\"","");
	data = data.replace("privateDataCollections:","\"privateDataCollections\":\"");
	data = data.replace(",organizations:","\",\"organizations\":\"");
	data = data.replace(",operations:","\",\"operations\":\"");
	data = data.replace("}}}","}}\"}");


	ProcessBuilder b = new ProcessBuilder("node","/home/doris/Desktop/sequence-diagram/mxgraph/javascript/examples/grapheditor/www/Invoke_Hyperledger.js",data);
	b.redirectOutput(ProcessBuilder.Redirect.INHERIT);
	b.redirectErrorStream(true);
	Process p = b.start();

	response.setStatus(HttpServletResponse.SC_OK);
	}	
}

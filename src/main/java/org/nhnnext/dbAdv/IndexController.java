package org.nhnnext.dbAdv;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@SuppressWarnings("serial")
public class IndexController extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//TODO Change Logger
		System.out.println("Request In IndexController");
		
		List<String> list = mongoDBExample();
		
		String nextJSP = "/index.jsp";
		request.setAttribute("list", list);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		
		dispatcher.forward(request,response);
	}
	
	private List<String> mongoDBExample() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("10.73.45.65");
		DB db = mongoClient.getDB("yoondb");
		
		Set<String> colls = db.getCollectionNames();

		for (String string : colls) {
			System.out.println(string);
		}
		
		DBCollection coll = db.getCollection("people");
		
		//insert
		BasicDBObject doc = new BasicDBObject("name", "yg fam");
		doc
			.append("salary", 1000000)
			.append("bf", "taji");
		
		coll.insert(doc);
		
		//find
		DBObject one = coll.findOne();
		System.out.println(one);
		
		List<String> result = new ArrayList<String>();
		
		//find all
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
//			System.out.println(cursor.next());
			result.addAll( (List<String>)cursor.next() );
		}
		
		//find specific object 1
		BasicDBObject search = new BasicDBObject("name", "yoon");
		DBCursor cursor2 = coll.find(search);
		
		while (cursor2.hasNext()) {
//			System.out.println(cursor2.next());
			result.addAll( (List<String>)cursor2.next() );
		}
		
		//find specific object 2
		BasicDBObject search2 = new BasicDBObject("$gt", 200);
		DBCursor cursor3 = coll.find(search2);
		
		while (cursor3.hasNext()) {
//			System.out.println(cursor3.next());
			result.addAll( (List<String>)cursor3.next() );
		}
		
		return result;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}

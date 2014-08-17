package org.nhnnext.dbAdv;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
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

	private static MongoClient mongoClient;
	private static DB db;

	static {
		try {
			mongoClient = new MongoClient("10.73.45.65");
			db = mongoClient.getDB("mongoLog");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Change Logger
		System.out.println("Request In IndexController");

		// List<String> list = mongoDBExample();
		List<Post> list = realServerTest();

		String nextJSP = "/index.jsp";
		request.setAttribute("list", list);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(nextJSP);

		dispatcher.forward(request, response);
	}

	
	
	private List<Post> realServerTest() throws UnknownHostException {
		DBCollection coll = db.getCollection("post");
		//coll.drop();

		// insert Test
		//createNewPost(new Post("Test Blog Title!!", "Jin woo", "Hello New Post"));
		
		return getAllPost();
	}

	private List<Post> getAllPost() {
		List<Post> list = new ArrayList<Post>();
		
		DBCollection coll = db.getCollection("post");
		// find all
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			Post post = new Post(
				dbObject.get("_id").toString(),
				dbObject.get("title").toString(),
				dbObject.get("writer").toString(),
				dbObject.get("content").toString(),
				dbObject.get("timestamp").toString()
			);
			
			list.add(post);
		}
		
		return list;
	}

	private void createNewPost(Post post) {
		DBCollection coll = db.getCollection("post");
		coll.insert( 
				new BasicDBObject()
				.append("title", post.getTitle())
				.append("writer", post.getWriter())
				.append("content", post.getContent())
				.append("timestamp", new Date())
		);
	}

	private List<String> mongoDBExample() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("10.73.45.65");
		DB db = mongoClient.getDB("yoondb");

		Set<String> colls = db.getCollectionNames();

		for (String string : colls) {
			System.out.println(string);
		}

		DBCollection coll = db.getCollection("people");

		// insert
		BasicDBObject doc = new BasicDBObject("name", "yg fam");
		doc.append("salary", 1000000).append("bf", "taji");

		coll.insert(doc);

		// find
		DBObject one = coll.findOne();
		System.out.println(one);

		List<String> result = new ArrayList<String>();

		// find all
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			// System.out.println(cursor.next());

			result.add(cursor.next().toString());
		}

		// find specific object 1
		BasicDBObject search = new BasicDBObject("name", "yoon");
		DBCursor cursor2 = coll.find(search);

		while (cursor2.hasNext()) {
			// System.out.println(cursor2.next());
			result.add(cursor2.next().toString());
		}

		// find specific object 2
		BasicDBObject search2 = new BasicDBObject("$gt", 200);
		DBCursor cursor3 = coll.find(search2);

		while (cursor3.hasNext()) {
			// System.out.println(cursor3.next());
			result.add(cursor3.next().toString());
		}

		return result;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String writer = request.getParameter("name");
		String content = request.getParameter("content");
		
		createNewPost(new Post(title, writer, content));
		
		response.sendRedirect("/");
	}
}

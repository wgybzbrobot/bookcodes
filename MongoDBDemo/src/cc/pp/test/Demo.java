package cc.pp.test;

import cc.pp.manager.MongoDbManagerImpl;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MongoDbManagerImpl manager = new MongoDbManagerImpl();
		manager.setMongoServerAddr("127.0.0.1");
		manager.setMongoServerPort(27017);
		manager.init();
		String collectionName = "mytable";

		// 插入数据
		String json;
		DBObject doc;
		long start = System.currentTimeMillis() / 1000;
		for (int i = 0; i < 1000000; i++) {
			if (i % 10000 == 0) {
				System.out.println(i);
			}
			json = "{'ppcc':" + i + "}";
			doc = (DBObject) JSON.parse(json);
			manager.inserDocument(collectionName, doc);
		}
		long end = System.currentTimeMillis() / 1000;
		System.out.println(end - start);

	}

}

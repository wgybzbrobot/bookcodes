package cc.pp.manager;

import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

/**
 * MongoDB操作类
 * @author WG
 *
 */
public class MongoDbManagerImpl {

	protected static Logger logger = LoggerFactory.getLogger("20130905");

	private static final String DBNAME = "wgybzb";
	private Mongo mongo = null;
	private DB dbConnection = null;
	private String mongoServerAddr;
	private int mongoServerPort;
	private static Map<String, DBCollection> dbCollectionMap = new ConcurrentHashMap<String, DBCollection>();

	public void setMongoServerAddr(String mongoServerAddr) {
		this.mongoServerAddr = mongoServerAddr;
	}

	public void setMongoServerPort(int mongoServerPort) {
		this.mongoServerPort = mongoServerPort;
	}

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
		String json = "{'ppcc':123456}";
		DBObject doc1 = (DBObject) JSON.parse(json);
		manager.inserDocument(collectionName, doc1);
		// 查找数据
		DBObject doc2 = null;
		DBObject query = new BasicDBObject();
		query.put("ppcc", 123456);
		doc2 = manager.selectDocument(collectionName, query);
		System.out.println(doc2);
		// 更新数据
		DBObject updatedDocument = new BasicDBObject();
		updatedDocument.put("$set", new BasicDBObject().append("ppcc", 654321));
		boolean result = manager.updateDocument(collectionName, query, updatedDocument);
		System.out.println(result);
		query.put("ppcc", 654321);
		// 删除数据
		result = manager.deleteDocument(collectionName, query);
		System.out.println(result);

	}

	@SuppressWarnings("deprecation")
	public void init() {
		if (this.mongo == null) {
			try {
				this.mongo = new Mongo(this.mongoServerAddr, this.mongoServerPort);
				if (null != this.mongo) {
					this.dbConnection = this.mongo.getDB(DBNAME);
				}
			} catch (UnknownHostException e) {
				logger.error("链接mongoDB失败，服务器地址：" + this.mongoServerAddr + "，端口：" + this.mongoServerPort);
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 获取数据表
	 * @param collectionName
	 * @return
	 */
	private DBCollection getDBCollection(String collectionName) {

		DBCollection collection = null;
		if (dbCollectionMap.containsKey(collectionName)) {
			collection = dbCollectionMap.get(collectionName);
		} else {
			collection = this.dbConnection.getCollection(collectionName);
			if (null != collection) {
				dbCollectionMap.put(collectionName, collection);
			}
		}
		return collection;
	}

	/**
	 * 查询某条数据是否在某个数据表中
	 * @param collectionName
	 * @param query
	 * @return
	 */
	public boolean isDocumentExist(String collectionName, DBObject query) {

		boolean result = false;
		DBCursor dbCursor = null;
		DBCollection collection = this.getDBCollection(collectionName);
		if (null != collection) {
			dbCursor = collection.find(query);
			if (null != dbCursor && dbCursor.hasNext()) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 查询数据
	 * @param collectionName
	 * @param query
	 * @return
	 */
	public DBObject selectDocument(String collectionName, DBObject query) {

		DBObject result = null;
		DBCursor dbCursor = null;
		DBCollection collection = this.getDBCollection(collectionName);
		if (null != collection) {
			dbCursor = collection.find(query);
			if (null != dbCursor && dbCursor.hasNext()) {
				result = dbCursor.next();
			}
		}
		return result;
	}

	/**
	 * 插入数据
	 * @param collectionName
	 * @param newDocument
	 */
	public void inserDocument(String collectionName, DBObject newDocument) {

		DBCollection collection = this.getDBCollection(collectionName);
		if (null != collection) {
			if (!this.isDocumentExist(collectionName, newDocument)) {
				collection.insert(newDocument);
			}
		}
	}

	/**
	 * 更新数据
	 * @param collectionName
	 * @param query
	 * @param updatedDocument
	 * @return
	 */
	public boolean updateDocument(String collectionName, DBObject query, DBObject updatedDocument) {

		boolean result = false;
		WriteResult writeResult = null;
		DBCollection collection = this.getDBCollection(collectionName);
		if (null != collection) {
			writeResult = collection.update(query, updatedDocument);
			if (null != writeResult) {
				if (writeResult.getN() > 0) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * 删除数据
	 * @param collectionName
	 * @param query
	 * @return
	 */
	public boolean deleteDocument(String collectionName, DBObject query) {

		boolean result = false;
		WriteResult writeResult = null;
		DBCollection collection = this.getDBCollection(collectionName);
		if (null != collection) {
			writeResult = collection.remove(query);
			if (null != writeResult) {
				if (writeResult.getN() > 0) {
					result = true;
				}
			}
		}
		return result;
	}

}

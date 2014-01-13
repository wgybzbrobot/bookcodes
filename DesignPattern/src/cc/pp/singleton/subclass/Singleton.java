package cc.pp.singleton.subclass;

public class Singleton {

	protected static Singleton uniqueInstance;

	protected Singleton() {
		//
	}

	public static synchronized Singleton getInstance() {
		if (uniqueInstance == null) {
			System.out.println("Creating unique instance of Singleton");
			uniqueInstance = new Singleton();
		}
		System.out.println("Returning instance of Singleton");
		return uniqueInstance;
	}

}

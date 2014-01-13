package cc.pp.singleton.dcl;

public class Singleton {

	private volatile static Singleton uniqueInstance;

	private Singleton() {
		//
	}

	public static Singleton getInstance() {
		if (uniqueInstance == null) {
			synchronized (Singleton.class) {
				if (uniqueInstance == null) {
					System.out.println("Creating unique instance of Singleton");
					uniqueInstance = new Singleton();
				}
			}
		}
		System.out.println("Returning instance of Singleton");
		return uniqueInstance;
	}

}

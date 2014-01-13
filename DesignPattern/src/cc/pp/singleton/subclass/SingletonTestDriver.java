package cc.pp.singleton.subclass;

public class SingletonTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Singleton foo = CoolerSingleton.getInstance();
		Singleton bar = HotterSingleton.getInstance();

		System.out.println(foo);
		System.out.println(bar);

	}

}

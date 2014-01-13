package cc.pp.strategy;

public class MallardDuck extends Duck {

	public MallardDuck() {
		flyBehavior = new FlyWithWings();
		quackBehavior = new Quack();
	}

	@Override
	void disp() {
		System.out.println("I'm a real Mallard duck");
	}

}

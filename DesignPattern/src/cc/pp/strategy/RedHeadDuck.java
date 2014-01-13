package cc.pp.strategy;

public class RedHeadDuck extends Duck {

	public RedHeadDuck() {
		flyBehavior = new FlyWithWings();
		quackBehavior = new Quack();
	}

	@Override
	void disp() {
		System.out.println("I'm a real Red Headed duck");
	}

}

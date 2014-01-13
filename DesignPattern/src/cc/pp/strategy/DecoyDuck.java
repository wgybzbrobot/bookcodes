package cc.pp.strategy;

public class DecoyDuck extends Duck {

	public DecoyDuck() {
		setFlyBehavior(new FlyNoWay());
		setQuackBehavior(new MuteQuack());
	}

	@Override
	void disp() {
		System.out.println("I'm a duck Decoy");
	}

}

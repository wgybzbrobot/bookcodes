package cc.pp.command.simpleremote;

public class LightOnCommand implements Command {

	Light light;
  
	public LightOnCommand(Light light) {
		this.light = light;
	}
 
	@Override
	public void execute() {
		light.on();
	}
}

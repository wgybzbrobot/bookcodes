package cc.pp.command.undo;

public interface Command {

	public void execute();

	public void undo();
}

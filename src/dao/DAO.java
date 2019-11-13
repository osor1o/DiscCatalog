package dao;

public interface DAO<Model> {
	public Model create();
	public Model read();
	public Model update();
	public Model delete();
}

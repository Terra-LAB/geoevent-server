package geocollector.dao;

import java.util.List;

import geocollector.model.Layer;

public interface LayerDao {

	public Layer insertLayer(Layer layer);

	public Layer getLayer(String name, int iduser);

	public boolean update(Layer layer);

	public boolean exists(String name, int userId);

	public boolean delete(Layer layer);

	public List<Layer> getAll();

}

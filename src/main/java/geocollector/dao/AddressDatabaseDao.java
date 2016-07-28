package geocollector.dao;

import geocollector.model.AddressDatabase;

public interface AddressDatabaseDao {

	public boolean remove(AddressDatabase addressDatabase);
	
	public AddressDatabase find (int addressId);
	
}

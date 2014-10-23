package cs.colostate.cs414.g.domain;

public class StoreManager {

	public String name;
	PizzaStore store;
	public StoreManager(String managerName, PizzaStore theStore) {
		this.name=managerName;
		this.store=theStore;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PizzaStore getStore() {
		return store;
	}
	public void setStore(PizzaStore store) {
		this.store = store;
	}
}

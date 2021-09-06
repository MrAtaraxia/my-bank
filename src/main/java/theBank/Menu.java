package theBank;

public class Menu {
	String number;
	String name;
	String condition;
	
	public Menu(String number, String name) {
		this.number = number;
		this.name = name;
		this.condition = "";
	}
	
	Menu(String number, String name, String condition) {
		this.number = number;
		this.name = name;
		this.condition = condition;
	}
}

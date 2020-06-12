
abstract public class Employee implements Runnable{
 private String name;
 protected double salary;
 
 
public Employee(String name) {
	this.name = name;
	this.salary = 0;
}

public abstract void calculateSalary(double time);

public double getSalary() {
	return this.salary;
}


}

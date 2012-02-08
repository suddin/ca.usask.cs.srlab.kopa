package ca.usask.cs.srlab.dao.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="PERSON")
public class Person {
	
	@Column(length=30, name="FIRST_NAME")
	String first_name;
	
	@Column(length=30, name="LAST_NAME" )
	String last_name;
	
	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	@Column(name="PERSON_ID")
	Integer id;

	@OneToMany (fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	List<Car> cars;
	
	public Person(String first_name, String last_name) {
		this(null, first_name, last_name);
	}

	public Person(Integer id, String first_name, String last_name) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.cars = new ArrayList<Car>();
	}
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	public void addCar(Car car){
		if(cars!=null)
			cars.add(car);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Person))
			return false;

		Person otherPerson = (Person) obj;

		if (this.id == null && otherPerson.id == null) {
			System.out.println(this.toString());
			System.out.println(otherPerson.toString());
			return this.toString().equals(otherPerson.toString());
		}

		if (this.id == null || otherPerson.id == null) {
			return false;
		}

		return this.id == otherPerson.id
				&& (this.first_name == otherPerson.first_name || (this.first_name != null && this.first_name
						.equals(otherPerson.first_name)))
				&& (this.last_name == otherPerson.last_name || (this.last_name != null && this.last_name
						.equals(otherPerson.last_name)));
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
			hash = 31 * hash + (null == id ? 0 : id.hashCode()) 
				+ (null == first_name ? 0 : first_name.hashCode())
				+ (null == last_name ? 0 : last_name.hashCode());
			return hash;
	}
	
}

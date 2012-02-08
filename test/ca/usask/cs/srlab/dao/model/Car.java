package ca.usask.cs.srlab.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="CAR")
public class Car {   
    
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="CAR_ID")
	private Integer id;
	
	@Column(name="MODEL_NAME", length=30)
	private String model_name;
	
	@OneToOne (optional=false)
    private Person owner;
    
	
    public Car(String model_name, Person owner) {
		this(null, model_name, owner);
	}

	public Car(Integer id, String model_name, Person owner) {
		super();
		this.id = id;
		this.model_name = model_name;
		this.owner = owner;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public String toString() {
        return "["+owner.getFirst_name()+"] drives a ["+model_name+"]";
    }
}

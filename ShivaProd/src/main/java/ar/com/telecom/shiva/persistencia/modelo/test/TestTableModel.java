package ar.com.telecom.shiva.persistencia.modelo.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TEST_TABLE")
public class TestTableModel {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int id;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof TestTableModel && ((TestTableModel) o).getId() == this.id && ((TestTableModel) o).getDescription().equals(this.description)) {
			return true;
		}
		return false;
	}
	
}

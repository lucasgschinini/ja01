package ar.com.telecom.shiva.negocio.reportes;

public class SuperColumn {
	
	public SuperColumn(String description, int columnFrom, int columnTo) {
		super();
		this.description = description;
		this.columnFrom = columnFrom;
		this.columnTo = columnTo;
	}

	private String description;
	
	private int columnFrom;
	
	private int columnTo;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getColumnFrom() {
		return columnFrom;
	}

	public void setColumnFrom(int columnFrom) {
		this.columnFrom = columnFrom;
	}

	public int getColumnTo() {
		return columnTo;
	}

	public void setColumnTo(int columnTo) {
		this.columnTo = columnTo;
	}
	
}

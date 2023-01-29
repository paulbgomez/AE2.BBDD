package modelo.entidad;

public class Coche {
    private int id;
	private String matricula, modelo, marca, color;

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

	@Override
	public String toString() {
		return "Coche [id=" + id + ", matricula=" + matricula + ", modelo=" + modelo + ", marca=" + marca + ", color="
				+ color + "]";
	}
}

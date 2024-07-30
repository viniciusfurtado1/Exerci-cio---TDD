package SistemaDeIngressos;

// Classe Ingresso
public class Ingresso {
    private static int idCounter = 0;
    private int id;
    private TipoIngresso tipo;
    private boolean vendido;

    public Ingresso(TipoIngresso tipo) {
        this.id = ++idCounter;
        this.tipo = tipo;
        this.vendido = false;
    }

    public int getId() {
        return id;
    }

    public TipoIngresso getTipo() {
        return tipo;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public double getPreco(double precoNormal, double desconto) {
        double preco = 0.0;
        switch (tipo) {
            case VIP:
                preco = precoNormal * 2;
                break;
            case NORMAL:
                preco = precoNormal;
                break;
            case MEIA_ENTRADA:
                preco = precoNormal / 2;
                break;
        }
        if (tipo == TipoIngresso.VIP || tipo == TipoIngresso.NORMAL) {
            preco *= (1 - desconto);
        }
        return preco;
    }
}
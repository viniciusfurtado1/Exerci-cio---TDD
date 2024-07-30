import java.time.LocalDate;

class Pagamento {
    private double valorPago;
    private LocalDate data;
    private String tipoPagamento;

    public Pagamento(double valorPago, LocalDate data, String tipoPagamento) {
        this.valorPago = valorPago;
        this.data = data;
        this.tipoPagamento = tipoPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public LocalDate getData() {
        return data;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }
}

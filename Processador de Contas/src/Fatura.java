import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Fatura {
    private LocalDate data;
    private double valorTotal;
    private String nomeCliente;
    private String status;
    private List<Pagamento> pagamentos;

    public Fatura(LocalDate data, double valorTotal, String nomeCliente) {
        this.data = data;
        this.valorTotal = valorTotal;
        this.nomeCliente = nomeCliente;
        this.status = "PENDENTE";
        this.pagamentos = new ArrayList<>();
    }

    public LocalDate getData() {
        return data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void adicionarPagamento(Pagamento pagamento) {
        this.pagamentos.add(pagamento);
    }
}
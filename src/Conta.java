import java.math.BigDecimal;
import java.text.NumberFormat;

abstract class Conta implements IConta {

    protected String agencia;
    protected String numeroConta;
    protected int digitoVerificador;

    protected BigDecimal saldo;
    protected Cliente cliente;

    public Conta(String agencia, String numeroConta, int digitoVerificador, Cliente cliente) {
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.digitoVerificador = digitoVerificador;
        this.cliente = cliente;
        this.saldo = new BigDecimal("0.0");
    }

    @Override
    public void sacar(BigDecimal valor) {
        if (valor.compareTo(saldo) == 1) {
            System.out.println("Saldo insuficiente par ao valor solicitado.");
        } else if (valor.compareTo(saldo) != 1) {
            this.saldo = this.saldo.subtract(valor);
            System.out.println("Saque realizado com sucesso.");
        }
    }

    @Override
    public void depositar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
        System.out.println("Depósito no valor de " + saldoParaTexto(valor) + " realizado com sucesso.");
    }

    @Override
    public void transferir(BigDecimal valor, Conta contaDestino) {
        if (valor.compareTo(saldo) == 1) {
            System.out.println("Saldo insuficiente par ao valor solicitado.");
        } else if (valor.compareTo(saldo) != 1) {
            contaDestino.receberTransferencia(valor);
            this.saldo = saldo.subtract(valor);
            System.out.println("Transferência no valor de " + saldoParaTexto(valor)
                    + "para " + contaDestino.getCliente().getNome()
                    + " Conta/Agência " + contaDestino.getNumeroConta() + "/" + contaDestino.getAgencia()
                    + " realizado com sucesso.");
        }
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("Conta: " + this.getNumeroConta() + " Agência: " + this.getAgencia());
        System.out.println(this.getCliente().getNome());
        System.out.println("Saldo atual: " + this.saldoParaTexto(this.getSaldo()));
    }

    private String saldoParaTexto(BigDecimal valor) {
        return new String(NumberFormat.getCurrencyInstance().format(valor));
    }

    private void receberTransferencia(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public int getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitoVerificador(int digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Conta [agencia=" + agencia + ", numeroConta=" + numeroConta + ", digitoVerificador=" + digitoVerificador
                + ", cliente=" + cliente + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Conta contaAtual = (Conta) obj;
        if (contaAtual.getAgencia().equalsIgnoreCase(this.getAgencia())
                && contaAtual.getNumeroConta().equalsIgnoreCase(this.getNumeroConta())
                && contaAtual.getDigitoVerificador() == this.getDigitoVerificador()
                && contaAtual.getCliente().equals(this.getCliente()))
            return true;
        else
            return false;
    }

}

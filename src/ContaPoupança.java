public class ContaPoupança extends Conta{

    public ContaPoupança(String agencia, String numeroConta, int digitoVerificador, Cliente cliente) {
        super(agencia, numeroConta, digitoVerificador, cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Conta Poupança ===");
        super.imprimirExtrato();
    }
    
}

public class ContaCorrente extends Conta {

    public ContaCorrente(String agencia, String numeroConta, int digitoVerificador, Cliente cliente) {
        super(agencia, numeroConta, digitoVerificador, cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Conta corrente ===");
        super.imprimirExtrato();
    }

    
    
}

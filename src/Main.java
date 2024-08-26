import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Banco santander = new Banco("Santander");
        Banco caixa = new Banco("Caixa");

        santander.adicionaAgencia("0123");
        santander.adicionaAgencia("3568");
        caixa.adicionaAgencia("9274");
        caixa.adicionaAgencia("2749");
        caixa.adicionaAgencia("3750");
        
        System.out.println(santander);
        System.out.println(caixa);

        Cliente lucas = new Cliente("Lucas Henrique", "Rua das Flores 01", "01234567892", "40028922",
                "lucas@lucas.com");
        Cliente evellyn = new Cliente("Evellyn Beatriz", "Rua das árvores 89", "9876543210", "978549632", "evel@evel.com");
        Conta contaLucasCorrente = santander.abrirConta(lucas, "Corrente");
        Conta contaEvellynPoupança = santander.abrirConta(evellyn, "Poupança");

        contaLucasCorrente.depositar(new BigDecimal("100.0"));
        System.out.println();
        contaLucasCorrente.imprimirExtrato();
        System.out.println();
        contaLucasCorrente.sacar(new BigDecimal("10"));
        System.out.println();
        contaLucasCorrente.imprimirExtrato();
        System.out.println();
        contaEvellynPoupança.imprimirExtrato();
        System.out.println();
        contaLucasCorrente.transferir(new BigDecimal("45"), contaEvellynPoupança);
        System.out.println();
        contaEvellynPoupança.imprimirExtrato();
        System.out.println();
        contaLucasCorrente.imprimirExtrato();
    }
}

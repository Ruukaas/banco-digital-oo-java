import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Banco implements IBanco {
    private String nome;
    private Set<Conta> contas;
    private Set<String> agencias;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new HashSet<>();
        this.agencias = new HashSet<>();
    }

    public Banco(String nome, Set<String> agencias) {
        this.nome = nome;
        this.agencias = agencias;
    }

    public Set<Conta> getContas() {
        return contas;
    }

    public void setContas(Set<Conta> contas) {
        this.contas = contas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<String> getAgencias() {
        return agencias;
    }

    public void setAgencias(Set<String> agencias) {
        this.agencias = agencias;
    }

    public void adicionaAgencia(String agencia) {
        agencias.add(agencia);
    }

    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
    }

    private String gerarNumeroDaConta() {
        Set<String> setContas = contas.stream()
                .map(t -> t.getNumeroConta().concat(Integer.toString(t.getDigitoVerificador())))
                .collect(Collectors.toSet()); // Pega os números das contas + digito verificador e coloca em um Set
        Random random = new Random();
        List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        int digitoVerificador;

        String numeroConta;

        do { // Gera números aleatórios até que seja diferente dos números das contas já
             // cadastradas no banco
            numeroConta = "";
            for (int i = 0; i < 8; i++) { // Gera os números da conta
                numeroConta += numbers.get(random.nextInt(numbers.size()));
            }
            digitoVerificador = numbers.get(random.nextInt(numbers.size())); // Gera o digito verificador
            numeroConta += digitoVerificador;
        } while (setContas.contains(numeroConta));

        return numeroConta;
    }

    @Override
    public Conta abrirConta(Cliente novoCliente, String tipoConta) {
        String numeroContaEDigitoVerificador = gerarNumeroDaConta();
        String numeroConta = numeroContaEDigitoVerificador.substring(0, numeroContaEDigitoVerificador.length());
        int digitoVerificador = Character
                .getNumericValue(numeroContaEDigitoVerificador.charAt(numeroContaEDigitoVerificador.length() - 1));
        Conta novaConta;
        String agencia = agencias.stream()
                .skip(new Random().nextInt(agencias.size()))
                .findFirst()
                .orElse("0000"); // Pega uma agênbcia aleatória do set de agências
        if (tipoConta.equalsIgnoreCase("Corrente"))
            novaConta = new ContaCorrente(agencia, numeroConta, digitoVerificador, novoCliente);
        else if (tipoConta.equalsIgnoreCase("Poupança")) {
            novaConta = new ContaPoupança(agencia, numeroConta, digitoVerificador, novoCliente);
        } else {
            System.out.println("Tipo de conta inválido. Encerrando abertura de conta");
            return null;
        }
        contas.add(novaConta);
        System.out.println("Conta: " + novaConta.getNumeroConta() +
                " Agência: " + novaConta.getAgencia()
                + " Aberta com sucesso");
        return novaConta;
    }

    @Override
    public void encerrarConta(Conta conta) {
        if (conta.getSaldo().compareTo(new BigDecimal("0.0")) != 0) {
            System.out.println("Operação não realizada. Ainda existe saldo na conta.");
        } else if (conta.getSaldo().compareTo(new BigDecimal("0.0")) == 0) {
            contas.remove(conta);
            System.out.println("Conta removida com sucesso");
        }
    }

    @Override
    public String toString() {
        return "Banco\nnome=" + nome + "\ncontas=" + contas + "\nagencias=" + agencias + "]";
    }

}

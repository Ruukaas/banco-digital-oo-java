public interface IBanco {
    public Conta abrirConta(Cliente novoCliente, String tipoConta);
    public void encerrarConta(Conta conta);
}

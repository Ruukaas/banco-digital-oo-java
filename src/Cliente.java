public class Cliente {
    private String nome;
    private String endereço;
    private String cpf;
    private String telefone;
    private String email;

    public Cliente(String nome, String endereço, String cpf, String telefone, String email) {
        this.nome = nome;
        this.endereço = endereço;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente[nome=" + nome + ",endereço=" + endereço + ", cpf=" + cpf + ", telefone=" + telefone
                + ", email=" + email + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Verifica se é a mesma instância
        if (obj == null || getClass() != obj.getClass()) return false;  // Verifica o tipo

        Cliente cliente = (Cliente) obj;

        if(cliente.getCpf().equalsIgnoreCase(this.getCpf()) 
        && cliente.getEmail().equalsIgnoreCase(this.getEmail()) 
        && cliente.getNome().equalsIgnoreCase(this.getNome())) return true;
    
        else return false;
    }

    

    

    
}

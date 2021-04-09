import java.util.ArrayList;
import java.util.List;
import java.util.*;

abstract class Conta{
    protected int id;
    protected float saldo;
    protected String idCliente;
    protected String type;

    Conta(int id, String idCliente){
        this.id = id;
        this.idCliente = idCliente;
        setSaldo(0);
    }
    public void sacar(float value){
        if(getSaldo() < value)
            throw new RuntimeException("fail: saldo insuficiente");
        setSaldo(-value);
    }
    public void depositar(float value){
        if(value <= 0)
            throw new RuntimeException("fail: valor invalido");
        setSaldo(value);
    }
    public void transferir(Conta other, float value){
        if(this.getSaldo() < value)
            throw new RuntimeException("fail: saldo insuficiente");
        other.depositar(value);
        this.setSaldo(-value);
    }
    abstract void atualizacaoMensal();
    public int getId(){
        return id;
    }
    public float getSaldo(){
        return saldo;
    }
    protected void setSaldo(float value){
        this.saldo = (getSaldo() + value);
    }
    public String getIdCliente(){
        return idCliente;
    }
    public String getType(){
        return type;
    }
    public String toString(){
        StringBuilder saida = new StringBuilder();
        saida.append("ID:" + getId() + " Cliente:" + getIdCliente() + " Saldo:" + getSaldo()+ "R$" + " Conta:" + getType());
        return saida.toString();
    }
}

class Cliente{
    protected String id;
    protected List<Conta> contas;

    Cliente(String id){
        setId(id);
        contas = new ArrayList<>();
    }
    public String getId(){
        return id;
    }
    private void setId(String id){
        this.id = id;
    }
    public String toString(){
        StringBuilder saida = new StringBuilder();
        saida.append("Cliente: " + getId() +"\n");
        for(Conta conta : contas){
            saida.append(conta.toString() + "\n");
        }
        saida.deleteCharAt(saida.lastIndexOf("\n"));
        return saida.toString();
    }
}

class ContaCorrente extends Conta{
    protected float tarifaMensal;

    ContaCorrente(int id, String idCliente){
        super(id, idCliente);
        this.tarifaMensal = 0;
    }
    public float getTarifaMensal(){
        return 20;
    }
    @Override
    public void atualizacaoMensal(){
        setSaldo(-getTarifaMensal());
    }
    @Override
    public String getType(){
        return "CC";
    }
}

class ContaPoupanca extends Conta{
    protected float rendimento;

    ContaPoupanca(int id, String idCliente){
        super(id, idCliente);
        rendimento = 0;
    }
    public float getRendimento(){
        float value = (float)(this.getSaldo() * 0.01);
        return value;
    }
    @Override
    public void atualizacaoMensal(){
        setSaldo(getRendimento());
    }
    @Override
    public String getType(){
        return "CP";
    }
}

class Agencia{
    protected TreeMap<String, Cliente> clientes;
    protected ArrayList<Conta> contas;
    private static int intId;

    Agencia(){
        clientes = new TreeMap<>();
        contas = new ArrayList<>();
        intId = 0;
    }
    public static void main(String[] args) {
        Agencia agencia = new Agencia();
        Scanner scanner = new Scanner(System.in);
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            try{
                if(ui[0].equals("end")){
                    break;
                }else if(ui[0].equals("addCli")){
                    agencia.adicionarCliente(ui[1]);
                }else if(ui[0].equals("show")){
                    System.out.println(agencia);
                }else if(ui[0].equals("depositar")){
                    Conta c = agencia.getConta(Integer.parseInt(ui[1]));
                    c.depositar(Float.parseFloat(ui[2]));
                }else if(ui[0].equals("saque")){
                    Conta c = agencia.getConta(Integer.parseInt(ui[1]));
                    c.sacar(Float.parseFloat(ui[2]));
                }else if(ui[0].equals("transfer")){
                    Conta c = agencia.getConta(Integer.parseInt(ui[1]));
                    Conta other = agencia.getConta(Integer.parseInt(ui[2]));
                    c.transferir(other, Float.parseFloat(ui[3]));
                }else if(ui[0].equals("showCli")){
                    System.out.println(agencia.showCliente(ui[1]));
                }else if(ui[0].equals("show")){
                    System.out.println(agencia);
                }else if(ui[0].equals("update")){
                    for(Conta conta : agencia.contas)
                        conta.atualizacaoMensal();
                }else
                    System.out.println("fail: comando invalido");
                
            }catch(IndexOutOfBoundsException msg){
                System.out.println(msg);
            }catch(RuntimeException msg){
                System.out.println(msg);
            }
        }
        scanner.close();
    }
    private void nextIntId(){
        intId++;
    }
    public void adicionarCliente(String id){
        if(clientes.containsKey(id))
            throw new RuntimeException("fail: cliente ja esta cadastrado");
        Cliente c = new Cliente(id);
        clientes.put(id, c);
        c.contas.add(new ContaCorrente(intId, id));
        nextIntId();
        c.contas.add(new ContaPoupanca(intId, id));
        nextIntId();
        contas.addAll(c.contas);
    }
    public Conta getConta(int id){
        for(Conta conta : contas)
            if(conta.getId() == id)
                return conta;
        throw new RuntimeException("fail: conta nao encontrada");
    }
    public String showCliente(String id){
        if(!clientes.containsKey(id))
            throw new RuntimeException("fail: cliente nao encontrado");
        String saida = "";
        saida += clientes.get(id).toString();
        return saida;
    }
    public String toString(){
        StringBuilder saida = new StringBuilder();
        for(Conta conta : contas){
            saida.append(conta.toString() + "\n");
        }
        saida.deleteCharAt(saida.lastIndexOf("\n"));
        return saida.toString();
    }
}
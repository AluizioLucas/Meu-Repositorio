import java.util.ArrayList;
import java.util.Scanner;

class Espiral{
    public String nome = "-";
    public float valor = 0.0f;
    public int qtd = 0;

    public Espiral(String nome, float valor, int qtd){
        this.nome = nome;
        this.valor = valor;
        this.qtd = qtd;
    }

    public void edit(String nome, float valor, int qtd){
        this.nome = nome;
        this.valor = valor;
        this.qtd = qtd;
    }

    @Override
    public String toString() {
        return "Unidades : " + qtd + " : " + nome + " : " + valor + "$";
    }
}




public class JunkFoodMachine {
    public float saldo;
    public Float lucro;
    public ArrayList<Espiral> espiral;

    public JunkFoodMachine(){
        this.saldo = 0;
        this.lucro = 0.0f;
        this.espiral = new ArrayList<>();
    }

    public void addDinheiro(Float dindin){
        this.saldo = dindin;
    }

    public void addProduto(String nome, float valor, int qtd){
        espiral.add(new Espiral(nome, valor, qtd));
    }

    public void troco(){
        if(saldo > 0){
            System.out.println("System: Seu troco é de " + saldo + "$");
            this.saldo = 0;
        }
        else System.out.println("System: não há troco");
    }

    public void comprar(int indice, int qtd){
        for(int i = 0; i < qtd; i++){
            if(indice >= 0 && indice < espiral.size()){
                if(espiral.get(indice).valor <= this.saldo){
                    if(espiral.get(indice).qtd > 0){
                        this.saldo -= espiral.get(indice).valor;
                        this.lucro += espiral.get(indice).valor;
                        espiral.get(indice).qtd --;
                    }
                    else {
                        System.out.println("SYSTEM-FAIL:Produto insuficiente");
                        break;
                    }
                }
                else {
                    System.out.println("SYSTEM-FAIL:Saldo insuficiente");
                    break;
                }
            }
            else {
                System.out.println("SYSTEM-FAIL:Indice não existe");
                break;
            }
            
        }
        
    }

    public boolean alterarEspiral(int indice, String nome, float valor, int qtd){
        if(indice >= 0 && indice < espiral.size()){
            espiral.get(indice).edit(nome, valor, qtd);
            return true;
        }
        return false;
    }
    
    public float getSaldo(){
        return saldo;
    }

    @Override
    public String toString() {
        String saida = "Saldo : " + this.saldo + "\n";
        int i=0;
        for(Espiral espira : espiral){
            saida += i +"- [ " + espira + " ] \n";
            i++;
        }
        return saida;
    }

  

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JunkFoodMachine maquina1 = new JunkFoodMachine();
        
        System.out.println("COMANDOS----------------/n");
        System.out.println("Iniciar\nEncerrar\nAddDinheiro qtd\nAddProduto nome valor qtd\ntroco\nComprar indice qtd\nSetEspiral indice nome valor qtd\nStatus\n");

        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");

            if(ui[0].equals("Iniciar")){
                maquina1 = new JunkFoodMachine();
                System.out.println(maquina1);
            }

            if(ui[0].equals("Encerrar")){
                System.out.println("Fim do programa");
                break;
            }

            if(ui[0].equals("AddDinheiro")){
                maquina1.addDinheiro(Float.parseFloat(ui[1]));
                System.out.println(maquina1);
            }

            if(ui[0].equals("AddProduto")){
                maquina1.addProduto(ui[1], Float.parseFloat(ui[2]), Integer.parseInt(ui[3]));
                System.out.println(maquina1);
            }
            
            if(ui[0].equals("troco")){
                maquina1.troco();
            }

            if(ui[0].equals("Comprar")){
                maquina1.comprar(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
                System.out.println(maquina1);
            }

            if(ui[0].equals("SetEspiral")){
                maquina1.alterarEspiral(Integer.parseInt(ui[1]), ui[2], Float.parseFloat(ui[3]), Integer.parseInt(ui[4]));
                System.out.println(maquina1);
            }

            if(ui[0].equals("Status")){
                System.out.println(maquina1);
            }

            
        }
        scanner.close();
    
    }


}

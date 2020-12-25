import java.util.ArrayList;

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
        return qtd + " : " + nome + " : " + valor;
    }
}




public class JunkFoodMachine {
    public float saldo;
    public Float lucro;
    public ArrayList<Espiral> espiral;

    public void addDinheiro(Float dindin){
        this.saldo = dindin;
    }

    public void troco(){
        if(saldo > 0){
            System.out.println("System: Seu troco é de " + saldo);
            this.saldo = 0;
        }
        else System.out.println("System: não há troco");
    }

    public boolean vender(int indice){
        if(indice >= 0 && indice < espiral.size()){
            if(espiral.get(indice).valor <= this.saldo){
                this.saldo -= espiral.get(indice).valor;
                this.lucro += espiral.get(indice).valor;
                espiral.get(indice).qtd --;
                return true;
            }
            System.out.println("SYSTEM-FAIL:Saldo insuficiente");
            return false;
        }
        System.out.println("SYSTEM-FAIL:Indice não existe");
        return false;
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
        String saida = "System info-----------\n";
       for(int i=0; i<=espiral.size(); i++){
           saida += i + " [ " + espiral.get(i).nome + " : " + espiral.get(i).qtd + " : " + espiral.get(i).valor+ " ]\n";
       }
        return saida;
    }

    public static void main(String[] args) {
        


        
    }


}

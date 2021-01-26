import java.util.ArrayList;
import java.util.Comparator;


//classe pessoa
class Pessoa{
    String id;
    int idade;

    //construtor
    public Pessoa(String id, int idade){
        this.id = id;
        this.idade = idade;
    }
    
    //toString
    @Override
    public String toString() {
        return this.id + ":" + this.idade;
    }

    //comparador
    public boolean equals(Object obj){
        if(!(obj instanceof Pessoa)) return false;

        Pessoa other = (Pessoa) obj;
        return this.id.equals(other.id);
    }

}

//classe Comparador de Pessoas
class ComparadorPessoas implements Comparator<Pessoa>{


    // < 0 se arg0 eh menor
    // > 0 se arg0 eh maior
    // == 0 se são iguais

    //metodo comparador
    public int compare(Pessoa arg0, Pessoa arg1){
        if(arg0 == null) return -1;

        if(arg1 == null) return 1;

        int resultado = arg0.id.compareTo(arg1.id);

        if(resultado != 0) return resultado;

        return Integer.compare(arg0.idade, arg1.idade);
    }

}

//Comparador de Idades de Pessoas
class ComparadorPessoasIdade implements Comparator<Pessoa>{
    //metodo comparador
    public int compare(Pessoa arg0, Pessoa arg1){
        if(arg0 == null) return -1;
        if(arg1 == null) return 1;
        return Integer.compare(arg0.idade, arg1.idade);
    }
}

//Main
public class Agenda{
    public static void main(String[] args){

        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

        pessoas.add(new Pessoa("Miguel", 3));
        pessoas.add(new Pessoa("Ana", 9));
        pessoas.add(new Pessoa("Ana", 8));
        pessoas.add(new Pessoa("Ana", 5));
        pessoas.add(new Pessoa("Joao", 1));
        pessoas.add(null);

        System.out.println(pessoas.contains(new Pessoa("ana", 9)));
        
        System.out.println(pessoas);
    }



}

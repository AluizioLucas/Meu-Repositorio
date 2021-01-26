import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;

class Pessoa{
    String id;
    int idade;

    public Pessoa(String id, int idade){
        this.id = id;
        this.idade = idade;
    }
    public String toString(){
        return this.id + ":" + this.idade;
    }
}

class ComparadorPessoas implements Comparator<Pessoa>{

    @Override
    // < 0 se arg0 eh menor
    // > 0 se arg0 eh maior
    // == 0 se são iguais
    public int compare(Pessoa arg0, Pessoa arg1) {
        if(arg0 == null)
            return -1;
        if(arg1 == null)
            return 1;
        return arg0.id.compareTo(arg1.id);
    }
}

class ComparadorPessoaPorIdade implements Comparator<Pessoa>{
    @Override
    public int compare(Pessoa arg0, Pessoa arg1) {
        if(arg0 == null)
            return -1;
        if(arg1 == null)
            return 1;
        return Integer.compare(arg0.idade, arg1.idade);
    }
}

class Agenda{
    public static void main(String[] args) {
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        pessoas.add(new Pessoa("Miguel", 3));
        pessoas.add(new Pessoa("Ana", 9));
        pessoas.add(new Pessoa("Ana", 8));
        pessoas.add(new Pessoa("Ana", 5));
        pessoas.add(new Pessoa("Joao", 1));
        pessoas.add(null);

        Pessoa procurada = null;
        for(Pessoa pessoa : pessoas){
            if(pessoa.id.equals("Ana")){
                procurada = pessoa;
                break;
            }
        }
        System.out.println(procurada);

        Collections.sort(pessoas, new ComparadorPessoas());
        System.out.println(pessoas);
    
        ArrayList<String> lista = new ArrayList<>(Arrays.asList("b", "c", "x", "d", "a"));
        Collections.sort(lista);
        System.out.println(lista);
    
    }
}
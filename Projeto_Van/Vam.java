import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Vam {
    public static void main(String[] args) {
        Veiculo veiculo = new Veiculo(12);
        Scanner scanner = new Scanner(System.in);

        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");

            if(ui[0].equals("Iniciar")){
                veiculo = new Veiculo(Integer.parseInt(ui[1]));
                System.out.println(veiculo);
            }

            if(ui[0].equals("Encerrar")){
                System.out.println("Fim do programa");
                break;
            }

            if(ui[0].equals("reservar")){
                veiculo.reservar(ui[1], ui[2], Integer.parseInt(ui[3]), Integer.parseInt(ui[4]));
                System.out.println(veiculo);
            }

            if(ui[0].equals("cancelar")){
                veiculo.cancelar(ui[1]);
                System.out.println(veiculo);
            }

            
        }
        scanner.close();
    
    }

}

class Passageiro implements Comparable<Passageiro>{
    String id;
    String nome;
    int idade;

    public Passageiro(String id, String nome, int idade){
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    @Override
    public String toString() {
        
        return this.id + ":" + this.nome + ":" + this.idade + " Anos";
    }

    public int compareTo(Passageiro other){
        return id.compareTo(other.id);
    }

}

class Veiculo{
    ArrayList<Passageiro> assentos;
    public Veiculo(int tamanho){
        assentos = new ArrayList<>(Collections.nCopies(tamanho, null));
    }

    public void reservar(String id, String nome, int idade, int index){
        if(index < 0 || index > assentos.size()){
            System.out.println("Assento Invalido");
            return;
        }

        if(assentos.get(index) != null){
            System.out.println("Assento Ocupado");
            return;
        }

        for(Passageiro assento: assentos ){
            if(assento != null && assento.id.equals(id)){
                System.out.println("Ja existe essa pessoa na Van");
                return;
            }
        }

        assentos.set(index, new Passageiro(id, nome, idade));
    }

    public void cancelar(String id){
        boolean encontrei = false;
        for( int i = 0; i < assentos.size(); i++){
            if(assentos.get(i) != null && assentos.get(i).id.equals(id)){
                assentos.set(i, null);
                encontrei = true;
                break;
            }
        }
        if(encontrei) System.out.println("Reserva de assento cancelada");
        else System.out.println("Id não encontrado");
    }

    @Override
    public String toString() {
        String saida = "[ ";
        for(Passageiro passageiro: assentos){
            if(passageiro == null) saida += "-";
            else saida += passageiro + " ";
        }
        return saida + " ]";
    }
}
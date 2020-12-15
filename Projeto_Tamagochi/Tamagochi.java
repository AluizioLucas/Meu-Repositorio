import java.util.Scanner;

public class Tamagochi{

    public static void main(String[] args) {
        Pet pet = new Pet(0, 0, 0);
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");

            if(ui[0].equals("iniciar")){
                pet = new Pet(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]), Integer.parseInt(ui[3]));
            }

            if(ui[0].equals("encerrar")){
                break;
            }

            if(ui[0].equals("comer")){
                pet.comer();
            }

            if(ui[0].equals("banho")){
                pet.banho();
            }

            if(ui[0].equals("brincar")){
                pet.brincar();
            }

            if(ui[0].equals("dormir")){
                pet.dormir();
            }

            if(ui[0].equals("status")){
                System.out.println(pet);
            }
        }
        scanner.close();
    }
}


class Pet{

    private int energiaMax, saciedadeMax, limpezaMax;
    private int energia, saciedade, limpeza;
    private int diamantes;
    private int idade;
    boolean vida;

    private void setDiamante(){
        diamantes += 1;
    }
    private void setEnergia(int valor){
        if(valor > energiaMax) energia = energiaMax;
        else if(valor < 0) energia = 0;
        else this.energia = valor;
    }

    private void setFome(int valor){
        if(valor > saciedadeMax) saciedade = saciedadeMax;
        else if(valor < 0) saciedade = 0;
        else saciedade = valor;
    }

    private void setLimpeza(int valor){
        if(valor > limpezaMax) limpeza = limpezaMax;
        else if(valor < 0) limpeza = 0;
        else limpeza = valor;
    }
    
    public Pet(int energiaMax, int saciedadeMax, int limpezaMax){
        this.energiaMax = energiaMax;
        this.saciedadeMax = saciedadeMax;
        this.limpezaMax = limpezaMax;
        this.energia = energiaMax;
        this.saciedade = saciedadeMax;
        this.limpeza = limpezaMax;
        this.diamantes = 0;
        this.idade = 0;
        this.vida = true;
    }

    public int getLimpeza(){
        return limpeza;
    }

    public int getFome(){
        return saciedade;
    }

    public int getEnergia(){
        return energia;
    }

    public int getLimpezaMax(){
        return limpezaMax;
    }

    public int getFomeMax(){
        return saciedadeMax;
    }

    public int getEnergiaMax(){
        return energiaMax;
    }
    
    public int getIdade(){
        return idade;
    }

    @Override
    public String toString() {
        return "\nEnergia: " + energia + "/" + energiaMax + " Fome: " + saciedade + "/" + saciedadeMax + " Limpeza: " + limpeza + "/" + limpezaMax + "\nDiamantes -> " + diamantes + " Esta vivo? " + vida + " Idade -> " + idade;
    }

    private void idade(){
        this.idade +=1;
    }

    private boolean estaVivo(){
        if( getEnergia()==0 || getFome()==0 || getLimpeza()==0){
            this.vida = false;
            System.out.println("\nVoce falhou em mante-lo vivo");
            return this.vida;
        }
        return this.vida;
    }
    
    public void brincar(){
        if(estaVivo()){
            setEnergia(getEnergia() - 2);
            setFome(getFome() - 1);
            setLimpeza(getLimpeza() - 3);
            idade();
            setDiamante();
            
        }
        else System.out.println("\nAcho seu pet não pode mais brincar");
    }

    public void banho(){
        if(estaVivo()){
            setEnergia(getEnergia() - 2);
            setFome(getFome() - 1);
            setLimpeza(getLimpezaMax());
            idade();
        }
        else System.out.println("\nSeu pet não consegue mais tomar banho");
    }

    public void comer(){
        if(estaVivo()){
            setEnergia(getEnergia() - 2);
            setFome(getFomeMax());
            setLimpeza(getLimpeza() - 3);
            idade();
        }
        else System.out.println("\nSeu pet não precisa mais comer");
    }

    public void dormir(){
        if(estaVivo()){
            setEnergia(getEnergiaMax());
            setFome(getFome() - 1);
            setLimpeza(getLimpeza() - 3);
            idade();
        }
        else System.out.println("\nEle ja esta dormindo e não ira mais acordar");
    }








}
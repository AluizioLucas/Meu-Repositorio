import java.util.Scanner;
public class HelicopteroCombate {

    public float combustivel;
    public float combustivelMax;
    public int passageiro;
    public int passageiroMax;
    public float km;
    public String modelo;
    public int gunnerEsquerda;
    public int gunnerDireita;

    @Override
    public String toString() {
        return "\nIdentificação de Cauda -> " + modelo + "\nQuilometragem -> " + km +  "\nCombustivel -> " + combustivel + "/" + combustivelMax + "\nPassageiros -> " + passageiro + "/" + passageiroMax + "\nGunner Direita -> " + gunnerDireita + "/1\nGunner Esquerda -> " + gunnerEsquerda + "/1\n"; 
    }
    //construtor
    HelicopteroCombate(String modelo){
        this.combustivelMax = 30;
        this.combustivel = 0;
        this.passageiroMax = 8;
        this.passageiro = 0;
        this.km = 0;
        this.modelo = modelo;
        this.gunnerEsquerda = 0;
        this.gunnerDireita = 0;
    }

    //embarcar
    public void in(){
        if(passageiro < passageiroMax){
            passageiro +=1;
            System.out.println("Embarcou com sucesso!!!");
        }
        else System.out.println("Veiculo lotado");

        if(passageiro > 2){
            gunnerEsquerda = 1;
            if(passageiro > 3){
                gunnerDireita = 1;
            }
        }
    }

    //desembarcar
    public void out(){
        if(passageiro > 0){
            passageiro -=1;
            System.out.println("Desenbarcou com sucesso!!!");
        }
        else System.out.println("Não foi possivel ocorrer desenbarque!!!");

        if(passageiro < 4){
            gunnerDireita = 0;
            if(passageiro < 3){
                gunnerEsquerda = 0;
            }
        }
       
    }

    //abastecer
    public void fuel(float fuel){
        if(combustivel < combustivelMax){
            combustivel += fuel;
            System.out.println("Abastecido com Sucesso!!");
            if(combustivel > combustivelMax){
                System.out.println("Galões exedentes -> " + (combustivel - combustivelMax));
                combustivel = combustivelMax;
            }
        }
        else System.out.println("Tanque já esta cheio");
    }

    //andar
    public void transportar(float km){
        if(passageiro > 0 && combustivel > 0){
            if((km / 20) <= combustivel){
                System.out.println("Sim senhor! Partiremos agora mesmo");
                combustivel -= (km/20);
            }
            else System.out.println("Senhor, o combustivel não é suficiente!");
        }
        else System.out.println("Coronel, cheque o combustivel e a tripulação");
    }

    //atirar
    public void atirar(String atirar){
        if(atirar.equals("esquerda")){
            if(1 == gunnerEsquerda) System.out.println("\n▄︻┻┳═一 ҉ - - - - - - - - - - - - -");
            else System.out.println("Não ha ninguém na gunner esquerda");
        }
        else if(atirar.equals("direita")){
            if(gunnerDireita == 1) System.out.println("\n- - - - - - - - - - - - - ҉ ー═┻┳︻▄");
            else System.out.println("Não ha ninguém na gunner direita");
        }
        else{
            System.out.println("indique o lado Coronel, esquerda ou direita!!!");
        }
        
    }
    
    
        

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HelicopteroCombate heli = new HelicopteroCombate("Digite 'iniciar (nome do Helicoptero)' para nomear seu Helicoptero!!!");

        System.out.println("\n\n              ▀▀▀▀▀▀▀▀▀▀▀▄▄▀▀▀▀▀▀▀▀▀▀▀\n                        █▀▀█\n                       █▓▓▓▓█\n                   ══▄▀█▓▓▓▓█▀▄══\n              ▄▄▄▄▄▄▄█▒█▓▓▓▓█▒█▄▄▄▄▄▄▄\n              █▀▀▀▀█▀███▄▓▓▄███▀█▀▀▀▀█\n             ▄█▄──▄█▄───▀██▀───▄█▄──▄█▄\n             █▒█──█▒█──────────█▒█──█▒█\n             ▀▀▀──▀▀▀──────────▀▀▀──▀▀▀");
        System.out.println("\n----Estes são seus comandos possiveis para seu heli----\n");
        System.out.println("-> nomear 'nome do Helicoptero'.\n-> encerrar.\n-> detalhes.\n-> embarcar.\n-> desenbarcar.\n-> abastecer.\n-> transportar.\n-> atirar 'lado (esquerda ou direiota)'\n");
        System.out.println("Digite seu primeiro comando\n");
    
        
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            
            if(ui[0].equals("nomear")){
                
                heli = new HelicopteroCombate(ui[1]);
                System.out.println("Veiculo nomeado com sucesso, ao seu comando Coronel!!");
            }
            else if(ui[0].equals("encerrar")){
                System.out.println("Encerrando missão");
                System.out.println("\n░▄░█░░░▄▀▀▀▀▀▄░░░█░▄░\n▄▄▀▄░░░█─▀─▀─█░░░▄▀▄▄\n░░░░▀▄▒▒▒▒▒▒▒▒▒▄▀░░░░\n░░░░░█────▀────█░░░░░\n░░░░░█────▀────█░░░░░\n.....FELIZ NATAL.....");
                break;
            }
            else if(ui[0].equals("detalhes")){
                System.out.println(heli);
            }
            else if(ui[0].equals("embarcar")){
                heli.in();
            }
            else if(ui[0].equals("desenbarcar")){
                heli.out();
            }
            else if(ui[0].equals("abastecer")){
                heli.fuel(Float.parseFloat(ui[1]));
            }
            else if(ui[0].equals("transportar")){
                heli.transportar(Float.parseFloat(ui[1]));
            }
            else if(ui[0].equals("atirar")){
                heli.atirar(ui[1]);
            }
            else System.out.println("Fail: Comando invalido\n");

        }
        scanner.close();

       
       
    }



}

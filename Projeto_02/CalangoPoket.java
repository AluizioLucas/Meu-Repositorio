public class CalangoPoket {

    public int vida;
    public int vidaMax;
    public int resistencia;
    public int resistenciaMax;
    public int forca;
    public int forcaMax;
    public int level;
    public int levelPoint;
    public String nome;
    private int vari = 1;

    CalangoPoket(String nome, int vida, int resistencia, int forca){
        this.vidaMax = 100;
        this.resistenciaMax = 10;
        this.forcaMax =15;
        this.level = 0;
        this.levelPoint = 0;
        this.nome = nome;
        this.vida = vida;
        this.resistencia = resistencia;
        this.forca = forca;
    }

    @Override
    public String toString() {
        System.out.println("Nome: " + nome + " Vida: " + vida + "/" + vidaMax + " Resistencia: " + resistencia + "/" + resistenciaMax);
        System.out.println("Força: " + forca + " level: " + level + " levelPoints: " + levelPoint);
        return "Esses são os status do seu calango fighter";
    }

    public void batalhar(){
        if(resistencia > 0 && 5 <= vida){
            resistencia --;
            vida = vida - 17;
            forca = forca + 1;
            levelPoint = levelPoint + 10;
            this.toString();
            System.out.println("Que pea foi essa em \n");
        }
        else System.out.println("Melhor ir descansar ou comer alguma coisa antes de ir a luta");
    }

    public void dormir(){
        vida = vidaMax;
        resistencia = resistencia - 2;
    }

    public void treinar(){
        if(levelPoint >= (100 * vari)){
            vari ++;
            vidaMax = vidaMax + 10;
            resistenciaMax = resistenciaMax + 2;
            forcaMax = forcaMax + 3;
            level ++;
            levelPoint = 0;
        }
        else System.out.println("Seu calango do agreste não tem pontos suficientes");

    }



    public static void main(String[] args) {
        CalangoPoket cacau = new CalangoPoket("Lavrante", 100, 10, 15);
        
        cacau.batalhar();
        cacau.batalhar();
        cacau.batalhar();
        cacau.batalhar();
        cacau.batalhar();
        
    }
}

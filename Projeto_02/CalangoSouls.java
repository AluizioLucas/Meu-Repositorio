public class CalangoSouls {


    public static void main(String[] args) {
        CalangoSouls gacho = new CalangoSouls(100, 70, 5);

        System.out.println(gacho);
        gacho.power();
        System.out.println(gacho);
        gacho.explorar();
        System.out.println(gacho);
        gacho.descansar();
        System.out.println(gacho);
        
   }


    // obejeto calango

    public int vida;
    public int vidaMax;
    public int mana;
    public int manaMax;
    public int mosquitosflasks;
    public int mosquitosflasksMax;
    public int membros;
    //public store inventario;
	

    @Override
    public String toString() {
       return "Hp: " + vida + "/"  + vidaMax + " Mana: " + mana + "/" + manaMax + " MosquitosFlasks: " + mosquitosflasks + "/" + mosquitosflasksMax;
    }

    //construtor
    CalangoSouls(int vida, int mana, int mosquitosflasks){
        this.vidaMax = 100;
        this.manaMax = 70;
        this.mosquitosflasksMax = 5;
        this.membros = 4;

        if(this.vidaMax >= vida && vida > 0) this.vida = vida;
        else System.out.println("coloque um valor dentro de 1 a 100 para definir hp");

        if(this.manaMax >= mana && mana > 0) this.mana = mana;
        else System.out.println("coloque um valor dentro de 1 a 70 para definir Mana");
            
        if(this.mosquitosflasksMax >= mosquitosflasks && mosquitosflasks > 0) this.mosquitosflasks = mosquitosflasks;
        else System.out.println("coloque um valor dentro de 1 a 5! para definir mosquitosflasks");
    }

    public void explorar(){
        if((mana - 5) > 0){
            mana -=5;
            System.out.println("Vamo dar uma volta por aqui");
        }
        else System.out.println("Não tenho mais forças para explorar");
    }

    public void power(){
        if((mana - 10) > 0){
            mana -=10;
            System.out.println("Toma fireball seu fdp");
        }
    }

    public void descansar(){
        this.mana = manaMax;
        System.out.println("Vou sentar so um pouquinho aqui");
    }

}










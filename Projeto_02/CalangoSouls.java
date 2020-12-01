public class CalangoSouls {


    public static void main(String[] args) {
        CalangoSouls gacho = new CalangoSouls(100, 70, 5);

        System.out.println(gacho);
        
   }


    // obejeto calango

    public int vida;
    public int vidaMax;
    public int stamina;
    public int staminaMax;
    public int mosquitosflasks;
    public int mosquitosflasksMax;
    public int membros;
    //public store inventario;
	

    @Override
    public String toString() {
       return "Hp: " + vida + "/"  + vidaMax + " Stamina: " + stamina + "/" + staminaMax + " MosquitosFlasks: " + mosquitosflasks + "/" + mosquitosflasksMax;
    }

    //construtor
    CalangoSouls(int vida, int stamina, int mosquitosflasks){
        this.vidaMax = 100;
        this.staminaMax = 70;
        this.mosquitosflasksMax = 5;
        this.membros = 4;

        if(this.vidaMax >= vida && vida > 0) this.vida = vida;
        else System.out.println("coloque um valor dentro de 1 a 100 para definir hp");

        if(this.staminaMax >= stamina && stamina > 0) this.stamina = stamina;
        else System.out.println("coloque um valor dentro de 1 a 70 para definir stamina");
            
        if(this.mosquitosflasksMax >= mosquitosflasks && mosquitosflasks > 0) this.mosquitosflasks = mosquitosflasks;
        else System.out.println("coloque um valor dentro de 1 a 5! para definir mosquitosflasks");
    }

    public void explorar(){
        if((stamina - 10) > 0){
            stamina = -10;
        }
        else System.out.println("Não tenho mais forças para explorar");
    }
    public void atacar(){
        if((stamina - 10) > 0){
            stamina = -10;
        }
    }


}










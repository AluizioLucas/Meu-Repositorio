import java.util.ArrayList;
import java.util.TreeMap;

class Fone{
    String id;
    String numero;

    public Fone(String id, String numero){
        this.id = id;
        this.numero = numero;
    }

    public boolean validate(String number){
        String validos = "0123456789()-";
        for(int i = 0; i < number.length(); i++){
            if(validos.indexOf(number.charAt(i)) == -1) return false;
        }
        return true;
    }

   @Override
   public String toString() {
      
       return id + ":" + numero;
   }

}

class Contato{
    String nome;
    ArrayList<Fone> fones;
    boolean favorito;

    public Contato(String nome){
        this.nome = nome;
        this.fones = new ArrayList<>();
        this.favorito = false;
    }

    public boolean addFone(String id, String numero){
        Fone fones = new Fone(id, numero);
        if(fones.validate(numero)){
            addFone(id, numero);
            return true;
        }
        System.out.println("Numero Invalido");
        return false;
    }

    public boolean rmFone(int index){
        if(index < 0 || index > fones.size()){
            return false;
        }
        fones.remove(index);
        return true;
    }

    public void setBookMark(boolean mark){
        this.favorito = mark;
    }

    public boolean getBookMarck(){
        return this.favorito;
    }

    @Override
    public String toString() {
        String saida = "";
        if(getBookMarck()) saida += "@ " + this.nome;
        else saida += "- " + this.nome;
        if(fones.size() > 0) saida += " ";

        for(int i=0; i < fones.size(); i++){
            Fone fone = fones.get(i);
            saida += "[" + i + ":" + fone + "]";
        }

        return saida;
    }

    public String getNome(){
        return this.nome;
    }
}

class Agenda{
    
    TreeMap<String, Contato> Contato;
    TreeMap<String, Contato> Favorito;

    public Agenda(){
        Contato = new TreeMap<>();
        Favorito = new TreeMap<>();
    }

    public Contato getContato(String nome){
        for(Contato contatos: Contato.values()){
            if(contatos.getNome().equals(nome)){
                return contatos;
            }
        }
        return null;
    }

    public ArrayList<Contato> getContato(){
        return (ArrayList<Contato>) Contato.values();
    }

    public boolean iniciarContato(String nome){
        Contato contato1 = getContato(nome);
        if(contato1 != null) return false;
        return true;
    }

    public void addContato(String nome, String id, String numero){
        Contato contato1 = getContato(nome);
        if(contato1 == null){
           contato1 = new Contato(nome);
           contato1.addFone(id, numero);
        }
        else{
            contato1.addFone(id, numero);
        }
    }

    public boolean rmContato(String nome){
        Contato contato1 = getContato(nome);
        if(contato1 == null){
            return false;
        }

        if(contato1.getBookMarck()) Favorito.remove(nome);

        Contato.remove(nome);
        return true;
    }


    public void Procurar(String id){
        ArrayList<Contato> procura = new ArrayList<>();

        for(Contato contato1: Contato.values()){
            if(contato1.toString().indexOf(id) != -1){
                procura.add(contato1);
            }
        }

        for(int i = 0; i < procura.size(); i++){
            System.out.println(procura.get(i));
        }
    }
    
    public void favoritar(String id){
        Contato contato1 = getContato(id);
        if(contato1 == null) return;
        if(contato1.getBookMarck()) return;
        contato1.setBookMark(true);
        Favorito.put(id, contato1);
    }

    public void desFavoritar(String id){
        Contato contato1 = getContato(id);
        if(contato1 == null) return;
        if(!contato1.getBookMarck()) return;
        contato1.setBookMark(false);
        Favorito.remove(id);
    }

    public ArrayList<Contato> getContatos(){
        return (ArrayList<Contato>) Contato.values();
    }

    public void show(){
        for(Contato contato1 : Contato.values()){
            System.out.println(contato1);
        }
    }
}

class favoritos{

    public static void main(String[] args) {
        
    Agenda agenda = new Agenda();

    agenda.addContato("Lucas", "22", "9219647761");
    agenda.addContato("sas", "23", "1231");
    agenda.addContato("rib", "333", "3453543");
    agenda.addContato("hob", "2276", "67575635");
    agenda.show();





    }
}
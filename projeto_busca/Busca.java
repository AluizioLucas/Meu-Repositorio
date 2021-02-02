import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;



class Fone{
    String id;
    String numero;

    public String getId(){
        return id;
    }

    public String getNumero(){
        return numero;
    }

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
       
        return this.id + ":" + this.numero;
    }

}

class Contato{

    String nome;
    ArrayList<Fone> fones;

    public boolean addFone(String id, String number){
        Fone fone = new Fone(id,number);
        if(fone.validate(number)){
            fones.add(new Fone(id,number));
            return true;
        }else{
            System.out.println("fail: fone invalido");
        }
        return false;
    }

    boolean rmFone(int index){
        if(index < 0 || index >= fones.size())
            return false;
        fones.remove(index);
        return true;
    }

    public Contato(String nome){
        this.nome = nome;
        this.fones = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("- ");
        int indice = 0;
        
        str.append(this.nome);

        for(Fone fone : fones) {
            str.append(" [");
            str.append(indice);
            str.append(":");
            str.append(fone.getId());
            str.append(":");
            str.append(fone.getNumero());
            str.append("]");
            indice++;
        }
        return str.toString();
    }
    
    public List<Fone> getFones(){
        return this.fones;
    }

    public String getNome(){
        return this.nome;
    }
}

class Agenda{
    private TreeMap<String, Contato> contatos;

    public Agenda(){
        contatos = new TreeMap<>();
    }
   
    Contato getContato(String name) {
        for(Contato cont: contatos.values()){
            if(cont.getNome().equals(name)){
                return cont;
            }
        }
        return null;
    }

    public boolean initContato(String nome){
        Contato cont = getContato(nome);
        if(cont != null) return false;
        contatos.put(nome, new Contato(nome));
        return true;
    }

    public void addContato(String name, String id, String number){
        Contato cont = getContato(name);
        if(cont == null){
            cont = new Contato(name);
            cont.addFone(id, number);
        }
        else if(cont != null){
            cont.addFone(id, number);    
        }
        
    }
    
    public boolean rmContato(String name){
        Contato cont = getContato(name);
        if(cont == null){
            return false;
        }
        contatos.remove(name);
        return true;
    }

    public void search(String label){
        
        ArrayList<Contato> pesquisa = new ArrayList<>();
        
        for(Contato cont : contatos.values()){
            if(cont.toString().indexOf(label) != -1) pesquisa.add(cont);
        }
        
        for(int i = 0; i < pesquisa.size(); i++){
            System.out.println(pesquisa.get(i));
        }
    }

    public void rmFoneindice(String name, int indice){
        Contato contatos = getContato(name);
        if(contatos != null)
            contatos.rmFone(indice);
    }

    public ArrayList<Contato> getContatos(){
        return (ArrayList<Contato>) contatos.values();
    }
    
    public void show(){
        for(Contato cont : contatos.values()){
            System.out.println(cont);
        }
    }
}

class Busca{

    public static void main(String[] args){
        
        Scanner scan = new Scanner(System.in);
        Agenda agenda = new Agenda();
        
        while(true){
            
            String line = scan.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            
            if(ui[0].equals("Encerrar")){
                break;
                 
            }else if(ui[0].equals("Adicionar")){

                agenda.initContato(ui[1]);
                
                for(int i = 2; i < ui.length; i++){
                    String partes[] = ui[i].split(":");
                    agenda.addContato(ui[1], partes[0], partes[1]);
                }
            
            }else if(ui[0].equals("RemoverFone")){
                agenda.rmFoneindice(ui[1], Integer.parseInt(ui[2]));
            
            }else if(ui[0].equals("agenda")){
                agenda.show();

            }else if(ui[0].equals("RemoverContato")){
                agenda.rmContato(ui[1]);
                
            }else if(ui[0].equals("Procurar")){ 
                agenda.search(ui[1]);
                
            }else{
                System.out.println("fail: comando inválido");
            }
        }
        scan.close();
    }


}

import java.util.*;

class Fone {
    public String id;
    public String number;

    public Fone(String id, String number) {
        this.id = id;    
        this.number = number;     
    }
    
    public String toString() {
        return id + ":" + number;
    }

    public static boolean validar(String fone) {
        String validos = "0123456789().-";
        for (int i = 0; i < fone.length(); i++) {
            char c = fone.charAt(i);
            if (validos.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }

}

class Contato {
    public String name;
    public ArrayList<Fone> fones = new ArrayList<>();

    public Contato(String name) {
        this.name = name;
    }

    public String toString() {
        String out = "";
        out += name;
        for (int i = 0; i < fones.size(); i++) {
            out += "[" + i + ":" + fones.get(i).id + ":" + fones.get(i).number + "]";
        }
        return out;
    }

}

class ContatoPlus extends Contato {
    public boolean favorited;

    public ContatoPlus(String name) {
        super(name);
        favorited = false;
    }

    @Override
    public String toString() {
        String out = "";
        if(favorited == true)
            out += "@ " + name;
        else
            out += "- " + name;
        
        for (int i = 0; i < fones.size(); i++) {
            out += " [" + i + ":" + fones.get(i).id + ":" + fones.get(i).number + "]";
        }
        
        return out;
    }
}

class Agenda{

    static TreeMap<String, Contato> contatos;

    public Agenda(){
        contatos = new TreeMap<>();
    }

    private TreeMap<String, Contato> getContatos() {
        return contatos;
    }

    public void addContato(ContatoPlus contato, List<Fone> fones) {
        if(contatos.get(contato.name) == null || contatos.isEmpty()) {
            contatos.put(contato.name, new ContatoPlus(contato.name));
            for(int i = 0; i < fones.size(); i++) {
                if(Fone.validar(fones.get(i).number))
                    contatos.get(contato.name).fones.add(fones.get(i));
            }
            return;
        }
        if(contatos.get(contato.name).name.equals(contato.name)) {
            for(int i = 0; i < fones.size(); i++) {
                contatos.get(contato.name).fones.add(fones.get(i));
            }
            return;
        }
        contatos.put(contato.name, new ContatoPlus(contato.name));
        for(int i = 0; i < fones.size(); i++) {
            contatos.get(contato.name).fones.add(fones.get(i));
        }
    }
    
    public void rmContact(String name) {
        if(contatos.get(name) == null) {
            System.out.println("Fail: O contato nao existe \n");
            return;
        }
        contatos.remove(name);
        System.out.println("O contato foi removido \n");
    }

    public void rmFone(String name, int index) {

        int size = contatos.get(name).fones.size();

        if(index > size || index < 0) {
            System.out.println("Fail: O numero não foi encontrado \n");
            return;
        }
        contatos.get(name).fones.remove(index);
        System.out.println("O numero foi removido \n"); 
    }



    public TreeMap<String, Contato> search(String caracteres) {

        TreeMap<String, Contato> FiltraContatos = new TreeMap<>();

        for(Contato contato : contatos.values()) {
            if(contato.name.contains(caracteres)) {
                FiltraContatos.put(contato.name, contato);
            }
            else{
                for(int i = 0; i < contato.fones.size(); i++)
                    if(contato.fones.get(i).number.contains(caracteres))
                        FiltraContatos.put(contato.name, contato);
            }
        }

        if(!FiltraContatos.isEmpty())
            return FiltraContatos;
        System.out.println("Fail: Nenhum valor foi encontrado");
        return contatos;
    }

    public void Favoritos(String name) {
        if(getContatos().containsKey(name)) {
            AgendaPlus.Favoritar(name);
        }
    }

    public void NaoFavoritos(String name) {
        if(getContatos().containsKey(name)) {
            AgendaPlus.Desfavoritar(name);
        }
    }

    public Collection<ContatoPlus> getfavorited() {
        TreeMap<String, ContatoPlus> favoriteds = new TreeMap<>();
        for(Contato contact : contatos.values()) {
            if(contact instanceof ContatoPlus) {
                ContatoPlus contatoPlus = (ContatoPlus) contact;
                if(contatoPlus.favorited == true)
                    favoriteds.put(contatoPlus.name, contatoPlus);
            }
        }
        return favoriteds.values();
    }

    public String toString() {
        String out = "";
        for(Contato contato : contatos.values()) {
            out += contato + "\n";
        }
        return out;
    }
}

class AgendaPlus extends Agenda {
    public static TreeMap<String, ContatoPlus> favoritos;

    public AgendaPlus() {
        favoritos = new TreeMap<>();
    }

    public static void Favoritar(String id) {
        favoritos = new TreeMap<>();

        if(contatos.containsKey(id)) {
            ContatoPlus contatoPlus = (ContatoPlus) contatos.get(id);
            contatoPlus.favorited = true;
            favoritos.put(contatoPlus.name, contatoPlus);
        } 
        else{
            System.out.println("Fail: O contato nao foi encontrado");
            return;
        }
    }

    public static void Desfavoritar(String id) {

        if(contatos.containsKey(id)) {
            ContatoPlus contatoPlus = (ContatoPlus) contatos.get(id);
            contatoPlus.favorited = false;
            favoritos.remove(id);
        } 
        else{
            System.out.println("Fail: O contato nao foi encontrado");
            return;
        }

    }

}

public class PlayContatoPlus{
    public static void main(String[] args) {
        Agenda agenda = new AgendaPlus();

        agenda.addContato(new ContatoPlus("eva"), Arrays.asList(new Fone("oio", "8585"), new Fone("cla", "9999")));
        agenda.addContato(new ContatoPlus("ana"), Arrays.asList(new Fone("Tim", "3434")));
        agenda.addContato(new ContatoPlus("bia"), Arrays.asList(new Fone("viv", "5454")));
        agenda.addContato(new ContatoPlus("ana"), Arrays.asList(new Fone("cas", "4567"), new Fone("oio", "8754")));

        System.out.println(agenda);

        agenda.Favoritos("eva");
        agenda.Favoritos("ana");
        agenda.Favoritos("ana");
        agenda.Favoritos("zac");
        agenda.Favoritos("rex");
        
        System.out.println(agenda);

        for(ContatoPlus favoriteds : agenda.getfavorited()){
            System.out.println(favoriteds);
        }
        
        agenda.rmContact("zac");
        System.out.println(agenda);

        for(ContatoPlus favoriteds : agenda.getfavorited()){
            System.out.println(favoriteds);
        }

        agenda.NaoFavoritos("ana");
        for(ContatoPlus favoriteds : agenda.getfavorited()){
            System.out.println(favoriteds);
        }
        
        System.out.println(agenda);

    
    }
}
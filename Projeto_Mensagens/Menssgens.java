import java.util.*;

//COrpo da menssagen
class Msg{
    int id;
    String sender;
    String text;


    //contrutor
    public Msg(int id, String sender, String text){
        this.id = id;
        this.sender = sender;
        this.text = text;
    }

    //ToString
    @Override
    public String toString() {
        return "" + id + ":" + sender + ":" + text;
    }

}


//usuario do serviço
class Usuario{
    String username;
    ArrayList<Msg> inbox;
    int ContNaoLidos;

    //construtor
    public Usuario(String username){
        this.username = username;
        inbox = new ArrayList<Msg>();
        ContNaoLidos = 0;
    }

    //enviar Menssagen
    public void sendMsg(int idMsg, Usuario dest, String text){
        dest.inbox.add(new Msg(idMsg, username, text));
        dest.ContNaoLidos +=1;
    }

    //caixa de entrada
    public ArrayList<Msg> getInbox(){
        ArrayList<Msg> output = new ArrayList<>();
        for(int i = inbox.size() - this.ContNaoLidos; i < inbox.size(); i++){
            output.add(inbox.get(i));
        }
        ContNaoLidos = 0;
        return output;
    }

}


//sistema de menssagens

class Sistema{
    ArrayList<Usuario> usuarios;
    int idNextMsg;

    //construtor
    public Sistema(){
        this.usuarios = new ArrayList<>();
    }

    //adicionar usuario
    void addUser(String username){
        if(this.getUser(username) != null){
            System.out.println("Usernem ja existe");
            return;
        }
        usuarios.add(new Usuario(username));
    }

    //retorne usuario
    public Usuario getUser(String username){
        for(Usuario usuario: usuarios)
            if(usuario.username.equals(username)) return usuario;
        return null;
    }

    //enviar mensagens

}
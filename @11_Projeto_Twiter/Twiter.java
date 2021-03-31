import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;

class Usuario {
    String username;
    int contNlidos;
    TreeMap<String, Usuario> followers;
    TreeMap<String, Usuario> following;
    ArrayList<Tweet> timeline;
    
    public Usuario(String username){
        this.contNlidos = 0;
        this.username = username;
        this.followers = new TreeMap<>();
        this.following = new TreeMap<>();
        this.timeline = new ArrayList<>();
    }
    public void follow(Usuario follows, Usuario other){
        if(follows.following.containsKey(other.username))
            return;       
        follows.following.put(other.username, other);
        other.followers.put(follows.username, follows);                
    }
   
    public void unfollow(Usuario unfollow, Usuario other){

        if(unfollow.following.containsKey(other.username)){
            unfollow.following.remove(other.username);
            other.followers.remove(unfollow.username);
            return;
        }
        throw new NullPointerException("fail: usuario nao encontrado");    
    }
  
    public void sendTweet(Tweet tweet){
        timeline.add(tweet);
        for(Usuario seguidores : followers.values()){
            seguidores.timeline.add(tweet);
            seguidores.contNlidos++;
        }
    }
    
    public Tweet getTweet(int idTweet){
        for(Tweet tw : timeline){
            if(tw.idTweet==idTweet)
                return tw;
        }
        throw new NullPointerException("fail: tweet nao existe");
    }  
    
    public String getUnread(){
        String saida = "";
        int aux = timeline.size() - contNlidos;
        for(int i=aux; i<timeline.size(); i++){
            saida+=timeline.get(i)+"\n";
        }
        contNlidos=0;
        return saida;
    }
    
    public String getTimeline(){
        String saida="";
        for(Tweet tweet : timeline)
            saida+=tweet+"\n";
        return saida;
    }    

    public TreeMap<String, Usuario> getSeguidos(){
        return following;
    }
 
    public TreeMap<String, Usuario> getSeguidores(){
        return followers;
    }    
    
    public String toString(){
        String saida=username+"\n  Seguidos   [ ";
        for(Usuario user : following.values())
            saida+=user.username+" ";
        saida+="]\n  Seguidores [ ";
        for(Usuario user : followers.values())
            saida+=user.username+" ";
        saida+="]";
        return saida;
    }
}

class Controller {
    TreeMap<String, Usuario> usuarios;
    TreeMap<String, Usuario> tweets;
    int nextIdTw;
    
    public Controller(){
        this.usuarios =  new TreeMap<>();
        this.tweets =  new TreeMap<>();
    }
    
    public void addUser(String username){
        if(!(usuarios.containsKey(username))){
            Usuario user = new Usuario(username);
            usuarios.put(username, user);
        }else{
            System.out.println("fail: usuario existente");
        }
            
    }    
    
    public void sendTweet(String username, String msg){
        Usuario user = this.getUser(username);       
        Tweet tweet = new Tweet(nextIdTw++, username, msg);
        user.sendTweet(tweet);
    }

    
    public Usuario getUser(String username){
        if(usuarios.containsKey(username))
            return usuarios.get(username);
        else        
           throw new NullPointerException("fail: usuario nao encontrado");
    }
    
    public String toString(){
        String saida = "";
        for(Usuario user : usuarios.values())
            saida+=user.toString()+"\n";
        return saida;
    }
}

class Tweet {
    int idTweet;
    String username;
    String msg;
    TreeSet<String> likes;
    
    public Tweet(int id, String username, String msg){
        this.idTweet = id;
        this.username = username;
        this.msg = msg;
        this.likes = new TreeSet<>();
    }
    
    public void like(String username){
        for(String nome : likes)
            if(nome.equals(username))
                return;
        likes.add(username);
    }
    
    public String toString(){
        String saida = "";
        saida +=idTweet+":"+username+"{ "+msg+"}";
        if(likes.size()>0){
            saida+="[ ";
            for(String nome : likes)
                saida+=nome+" ";
            saida+="]";
        }
        return saida;
    }
}
class Main{
    public static void main(String[]args){  
        Controller sistema = new Controller();
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            String line = scanner.nextLine();
            String in[] = line.split(" ");
            try{
                if(line.equals("end")){
                    break;
                }else if(in[0].equals("addUser")){
                    sistema.addUser(in[1]);
                }else if(line.equals("show")){
                    System.out.println(sistema);
                }else if(in[0].equals("follow")){
                    Usuario one = sistema.getUser(in[1]);
                    Usuario two = sistema.getUser(in[2]);
                    one.follow(one, two);
                }else if(in[0].equals("unfollow")){
                    Usuario one = sistema.getUser(in[1]);
                    Usuario two = sistema.getUser(in[2]);
                    one.unfollow(one, two);                
                }else if(in[0].equals("timeline")){
                    Usuario user = sistema.getUser(in[1]);
                    System.out.println(user.getTimeline());
                }else if(in[0].equals("like")){
                    Usuario user = sistema.getUser(in[1]);
                    Tweet tw = user.getTweet(Integer.parseInt(in[2]));
                    tw.like(in[1]);
                }else if(in[0].equals("twittar")){
                    String msg = "";
                    for(int i = 2; i<in.length; i++)
                        msg+=in[i]+" ";
                    sistema.sendTweet(in[1], msg);
                }else{
                    System.out.println("fail: comando invalido");
                }
            }catch(NullPointerException ex){
                System.out.println(ex.getMessage());
            }
            scanner.close();
        } 
    }
}
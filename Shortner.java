
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

class UrlShortner{
    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = BASE62.length();
    private static final String DOMAIN = "https://UrlShorted.link/";
    
    private HashMap<String,String> urlMap;
    private HashMap<String,String> decoMap;
    Random rand=new Random();
    int value=rand.nextInt(2500);
    UrlShortner(){
        urlMap=new HashMap<>();
        decoMap=new HashMap<>();
        
    }
    
    public String urlShortner(String original)
    {
        if(urlMap.containsKey(original)){
            return DOMAIN+urlMap.get(original);
        }
        
        String encrypt=encode(value);
        urlMap.put(original,encrypt);
        decoMap.put(encrypt,original);
        value++;
        
        return DOMAIN+encrypt;
    }
    
    
    public String encode(int num){
        StringBuilder sb=new StringBuilder();
        while(num>0){
            sb.append(BASE62.charAt(num%BASE));//where BASE is length of BASE62
            num/=BASE;
        }
        return sb.reverse().toString();
    }
    
    
    public String decode(String shortUrl){
        String decoValue=shortUrl.replace(DOMAIN,"");
        return decoMap.get(decoValue);
        }
    
}
    


public class Shortner {
    public static void main(String[] args) {
        UrlShortner u=new UrlShortner();
        
        Scanner s=new Scanner(System.in);
        System.out.print("enter OriginalUrl: ");
        String original=s.next();
        String shortedUrl=u.urlShortner(original);
        System.out.println("shorted url is: "+shortedUrl);
        
        System.out.println("/n - - - - - - - - - - - - - - - - - - - - - -  - -");
        System.out.print("for original url click y or any word for exit: ");
        String word=s.next();
        if("y".equals(word)){
            String decode=u.decode(shortedUrl);
            System.out.println("original decoded url is:.decode(shortedUrl) "+decode);
        
        }
        
    }
}

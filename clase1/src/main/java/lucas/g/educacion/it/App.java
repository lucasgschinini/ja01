package lucas.g.educacion.it;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        
    	Usuario user = new Usuario("123","LUCAS");
    	Gson gson = new Gson();
    	
    	System.out.println(gson.toJson(user));
    	
    }
}

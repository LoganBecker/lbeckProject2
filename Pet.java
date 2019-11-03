//Author: Logan Becker
//Professor: Gregory Silver
//Date: 11/3

package petdatabase;




public class Pet {

    
    private String name;                //instatiates string name
    private int age;                    //instatiates int age
    
    public String getName(){            //gets name
        return name;
    }
    
    public String setName(String n){    //sets name
        this.name = n;
        return name;
    }
    
    public int getAge(){                //gets age
        return age;
    }
    
    public int setAge(int a){           //sets age
        this.age = a;
        return age;
    }
    
    public Pet(String name, int age){      //constructor with parameters
        this.name = name;
        this.age = age;
        
    }
    
    
    
    
    
    
    
    
    
    
    
}

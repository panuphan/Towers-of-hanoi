import java.io.*;
import java.util.*;
/*ที่ต้องทำ:
    1.เปลี่ยนตัวเลขเป็นดอกจัน*/
/*/*////
class Towers {
    
//-----    Creating Stack array  size = 4  
//         Not use index 0 because easy for understand.
    private int number ;
    private int round ;
    private Stack<Integer>[] tower = new Stack[4];
   //
    Towers(int N){
        number = N;
        round = 0;
        tower[1] = new Stack<Integer>();
        tower[2] = new Stack<Integer>();
        tower[3] = new Stack<Integer>();

//add all disks to tower L     
        for (int i = N; i > 0; i--) {
            tower[1].push(i);
        }
        display();
    }
    // methods return
    public  Stack<Integer> getTower(int index){
        return tower[index];
    }
    
    public int getRound(){
        return round;
    }
    
    public boolean isComplete(){
        if(tower[3].size() == number)return true;
        return false;
    }
    
    //methods
    public void Movedisk(String from,String to){
        
        //tower'from must have disks and from not equal to 
        if (!(tower[ n_tower(from) ].isEmpty()) && !(to.equalsIgnoreCase(from)) )    
        {
            
        //tower'to is Empty or top disk of tower'to more than tower'from  
            if (tower[ n_tower(to) ].isEmpty() || tower[ n_tower(from) ].peek() < tower[ n_tower(to) ].peek()){
                
        //pop tower'from and add tower'to
                int d = tower[n_tower(from)].pop();
                tower[ n_tower(to) ].push(d);          
                round++;
                display();
            }
        }        
    }
    
    public void Auto(){
        // auto is method Recursive
        auto(number,"L","C","R");
    }
    
       
    public  void auto(int number_disks, String a,String b,String c) {
        
        if (number_disks > 0) {
            
           //ย้ายDisks ทั้งหมดยกเว้น ฐานล่างสุด จาก L ไป C 
           auto(number_disks - 1, a, c, b);            
           //ย้ายDisk ที่เป็นฐานจาก L ไป R 
           Movedisk(a,c);
           //ย้ายDisk ทั้งหมดที่เคยอยู่ C ไป R ทับกับฐาน 
           auto(number_disks - 1, b, a, c); 
           
        }     
    }
    
//method for return number of towers from string L(1) C(2) R(3) 
    public int n_tower(String A){
        if(A.equalsIgnoreCase("L"))return 1;
        else if(A.equalsIgnoreCase("C"))return 2;
        else if(A.equalsIgnoreCase("R"))return 3;
        return 0;
    }
        
    public  void display() {
        if(round > 0)System.out.printf("[round %d]\n",round);
        System.out.println("  L  |  C  |  R");
        System.out.println("---------------");
        for (int i = number - 1; i >= 0; i--) {
            String d1 = " ", d2 = " ", d3 = " ";
            
            //ต้องมี try เพื่อให้ยังสามารถ run ได้
            try {
                d1 = String.valueOf(tower[1].get(i));
            } catch (Exception e) {
            }
            try {
                d2 = String.valueOf(tower[2].get(i));
            } catch (Exception e) {
            }
            try {
                d3 = String.valueOf(tower[3].get(i));
            } catch (Exception e) {
            }
            System.out.println("  " + d1 + "  |  " + d2 + "  |  " + d3);
        }
        System.out.println("\n");
    }
    

    public void clear(){
        tower[1].clear();
        tower[2].clear();
        tower[3].clear();
    }   
    dsdsd
}
//////////////////////////////////////////////////////////////////////////////// 

public class Towers_of_hanoi {

    public static void main(String[] args) {
     
        Scanner scan = new Scanner(System.in);
        Towers towers;
        while(true){
            
//----- 1. Ask the user to either “Auto” or “Manual” 

            // creating array string for check ต้องการตัวอักษรตัวไหนบ้าง
            String c;
            String[] Store = {"A","M","Q"};
            do {
                
                System.out.printf("Auto(A) or Manual(M) or Quit(Q) : ");
                c = scan.next();
                
            } while (!(check(Store,c)));
            // Q for exit loop
            if (c.equalsIgnoreCase("Q")) {
                break;
            }
            
            
//----- 2. Accepting number of disks
         //number if more than 12 program is very slow to 
         //running in Auto mode  show step by step
            boolean correct = true;
            int N = 0;
                do {
                    System.out.printf("Enter number of disks (2-12):");
                    try {
                        N = scan.nextInt();
                        correct = false;
                    } catch (Exception e){ 
                        //correct by scan again
                        scan = new Scanner(System.in);      
                    } 
                } while (correct || N <= 1 || N>=13);
                
//----- 3. Creating class Towers              
            towers = new Towers(N);    
             
//----- 4. if user choose Auto mode     
        if (c.equalsIgnoreCase("A"))
            towers.Auto();  

//----- 5. if user choose Manual mode            
        else {
            
        // creating array string for check ต้องการตัวอักษรตัวไหนบ้าง    
        String from = null, to = null;
        String[] store = {"C","R","L","Q"};
        
        correct = true;
        try{
        //if game not finish     
        while( !(towers.isComplete()) || correct){
               
            
//----- 5.1.Accepting towers want to move             
            do {
                    System.out.printf("     Move top from tower(L,C,R) [Back to main press Q] =");
                    from = scan.next();
            } while ( !(check(store, from)) );
            if (from.equalsIgnoreCase("Q")) {
                break;
            }
                
//----- 5.2.Accepting towers want to place                     
            do {
                    System.out.printf("              to tower(L,C,R) [Back to main press Q] =");
                    to = scan.next();
            } while ( !(check(store, to)) );
            if (to.equalsIgnoreCase("Q")) {
                break;
            }

//----- 5.3.Accepting parameter of method Movedisk             
        towers.Movedisk(from, to);
        correct = false;
        
        }//end loop for not finish 
        }catch(Exception e){ /*error case : from/to is Q or number */}
        
        }//end Manual mode
        
//----- 6.report Game finish 
        if (towers.isComplete()) {
            System.out.printf("You move all disks in %d steps !!! \n\n", towers.getRound());
        }
   
//----- 7. clear Towers for new play !!  
        towers.clear();
    
        }//end loop for play
    }//end main

    
    // method for check String to input 
    public static boolean check(String[] store,String input) {
        for(int i = 0 ;i < store.length ; i++){
            if(store[i].equalsIgnoreCase(input))return true;
        }
        return false;
    }
    
}//end class 

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Pseudo{
    static boolean test=false;
    static int bits;
    static boolean negative=false;
    public static void main(String []args){
    Random random=new Random();
    int limit=1000;
    int k=0;
    if(test==false) k=limit-1;
        for(;k<limit;k++){

int num1=random.nextInt(2,10000);
int num2=random.nextInt(1,num1);

num1=10;
num2=40;

int num11=num1;
int num22=num2;
bits=8 * 5;

boolean bTable[][] = new boolean[3][bits];

short operation=0;

if(test){
 operation=(short)random.nextInt(1,5);
}

else{
    System.out.println("choose an operation:\n Sum -> 1\nSubstraction -> 2\n Multipication -> 3\n Division -> 4");
    Scanner scanner = new Scanner(System.in);

    operation=  scanner.nextShort();
    scanner.close();
}

boolean reverse=false;
int powerOfTen=0;

if(num2>num1){
    reverse=true;

    if(operation==4)
    while(num2>num1){
        powerOfTen++;
        num1*=10;
    }

    else if(operation==2){
        int temp =num1;
        num1= num2;
        num2 =temp;
    }
}

int power=bits-1;
while(power>=0 && num1>0){
    int num=(int)Math.pow(2, power);

    if(num<= num1){ 
        bTable[0][power]=true;
        num1-=num;
    }
    
    power--;
}

power=bits-1;
while(power>=0 && num2>0){
    int num=(int)Math.pow(2, power);

    if(num<= num2){ 
        bTable[1][power]=true;
        num2-=num;
    }
    
    power--;
}
if(test)
printArray(bTable[0]);
if(num1!=0)System.out.println("insufficient memory for num1");
if(num2!=0)System.out.println("insufficient memory for num2");

double exactResult =0;
switch(operation){
    case 1: sum(bTable); exactResult=num11+num22;
    break;
    case 2: subs(bTable); exactResult=num11-num22;
    break;
    case 3: mult(bTable); exactResult=num11*num22;
    break;
    case 4: div(bTable); exactResult=num11/num22;
    break;
}

double num3= (double)binToNum(bTable[2]);

if(reverse){

    switch(operation){
        case 1: 
        break;
        case 2: num3*=-1; exactResult*=-1;
        break;
        case 3:
        break;
        case 4:
        String number=(int)num3+"";
        int i1=number.length() - powerOfTen;
        if(powerOfTen> 0 && i1>0){
            number=number.substring(0, i1)+"."+number.substring(i1+1);
        }

        else if(powerOfTen> 0 && i1<=0){
            number="0.";
        while(i1<0){
            number+="0";
            i1++;
        }
        number+=(int)num3+"";
    }
        num3=Double.valueOf(number);
        exactResult=1/exactResult;
        break;
    }

}

if(negative) num3*=-1;
negative =false;

if(test==false)
System.out.printf("result: %.2f",num3);

if(test)
if(num3 != exactResult) {
    System.out.println("ERROR!");
    System.out.println("operation: "+operation);
    System.out.println("num1: "+num11+" num2: "+num22+" num3: "+num3);
    System.out.println("exactResult: "+exactResult);
    System.exit(1);
}
}
}
private static void sum(boolean bTable[][]){
    boolean extra=false;
    
    for(int i=0;i<bits;i++){
       boolean b1= bTable[0][i];
       boolean b2= bTable[1][i];
       boolean b3= false;

       if(b1 && b2){

        if(extra){
            b3=true;
            extra=true;
        }
        else{
            b3=false;
            extra=true;
        }

       }
       else if(b1==false && b2){

        if(extra){
            b3=false;
            extra=true;
        }
        else{
            b3=true;
            extra=false;
        }
        
       }
       else if(b1 && b2==false){
        
        if(extra){
            b3=false;
            extra=true;
        }
        else{
            b3=true;
            extra=false;
        }
        
       }
       else if(b1==false && b2==false){
        
        if(extra){
            b3=true;
            extra=false;
        }
        else{
            b3=false;
        }
        
       } 
       
       bTable[2][i]=b3;
    }

}

private static void subs(boolean bTable[][]){
    boolean extra=false;
    
    for(int i=0;i<bits;i++){
       boolean b1= bTable[0][i];
       boolean b2= bTable[1][i];
       boolean b3= false;

       if(b1 && b2){

        if(extra){
            b3=true;
            extra=true;
        }
        else{
            b3=false;
        }

       }
       else if(b1==false && b2){

        if(extra){
            b3=false;
           
        }
        else{
            b3=true;
            extra=true;
        }
        
       }
       else if(b1 && b2==false){
        
        if(extra){
            b3=false;
            extra=false;
        }
        else{
            b3=true;
            extra=false;
        }
        
       }
       else if(b1==false && b2==false){
        
        if(extra){
            b3=true;
            
        }
        else{
            b3=false;
        }
        
       } 

       bTable[2][i]=b3;
    }

    if(extra){
        negative=true;
        int i=0;
        while(bTable[2][i]==false) i++;

        i++;

    for(;i<bits;i++){
        if(bTable[2][i] == true) bTable[2][i]=false;
        else bTable[2][i]=true;
    }
}

}

private static void mult(boolean bTable[][]){

int j=0;

for(int i=0;i<bits;i++){

    if(bTable[1][i] == true){
        j=i;
        boolean [] boolArr =new boolean[bits];

        for(;j<bits;j++){
            boolArr[j]=bTable[0][j-i];
        }
        boolean[][]newBTable={boolArr,bTable[2],bTable[2]};
        sum(newBTable);
    }

}
    
}

private static void div(boolean bTable[][]){
    Stack<Boolean> tempStack=new Stack<>();
    Stack<Boolean> resultStack=new Stack<>();

    boolean BArr[] = bTable[0];

    int index=getLength(BArr)-1;

    int length=getLength(bTable[1]);
    boolean once =true;
    if(test)
    System.out.println(index +" --> index");
    while(index>=0){

        do{
           if(BArr[index]==true)
            tempStack.push(BArr[index]);
            else{
                if(tempStack.contains(true))
                tempStack.push(BArr[index]);
            }
            index--;
            if(test)
            System.out.println(index +" --> index");
        }
        while(once && index >= 0 && tempStack.size()<length);
        once=false;

        while(index >=0 && tempStack.size()<length){
            if(BArr[index]==true)
            tempStack.push(BArr[index]);
            else{
                if(tempStack.contains(true))
                tempStack.push(BArr[index]);
            }
            index--;
            if(test)
            System.out.println(index +" --> index");
            resultStack.push(false);
        }

        Stack <Boolean> newStack=new Stack<>();
        newStack.addAll(tempStack);

        bTable[0]=stackToArray(newStack);

        int result=compare(bTable[0],bTable[1]);
    
        if(test){
        System.out.println("test");
        printArray(bTable[0]);
        }

        if(index<0 && ( tempStack.size()<length || result<0)) {
            resultStack.push(false);
            break;
        }
        

        if( result <0 ){
            if(BArr[index]==true)
            tempStack.push(BArr[index]);
            else{
                if(tempStack.contains(true))
                tempStack.push(BArr[index]);
            }
            index--;
            if(test)
            System.out.println(index +" --> index");
            resultStack.push(false);
        }

        bTable[0]=stackToArray(tempStack);

        if(test){
        System.out.println("numerator");
        printArray(bTable[0]);
        }
       
        subs(bTable);
        resultStack.push(true);
       
        int index1=getLength(bTable[2])-1;
          
        if(test){
        System.out.println("remainder");
        printArray(bTable[2]);
        } 
        
        boolean pass=false;
        while(index1>=0){
            if(bTable[2][index1])pass=true;

            if(pass)
            tempStack.push(bTable[2][index1]);
            index1--;
        }
        bTable[2]=new boolean[bits];
    }
    bTable[2]=stackToArray(resultStack);
    if(test)
    printArray(bTable[2]);

}
    
private static boolean[] stackToArray(Stack<Boolean> stack){
    
    boolean[] BoolArr=new boolean[bits];
    int i=0;
    while(stack.isEmpty()==false){
        BoolArr[i]=stack.pop();
        i++;
    }
    return BoolArr;

}

private static int compare(boolean [] boolArr1, boolean [] boolArr2){
   
    for(int i=bits-1;i>=0;i--){
        boolean bool1= boolArr1[i];
        boolean bool2= boolArr2[i];

        if(bool1== true && bool2==false) return 1;
        else if( bool1==false && bool2==true) return -1;

    }

    return 0;
}

private static int getLength(boolean [] boolArr){
    int i=bits-1;
    while(i>=0 &&boolArr[i]==false) i--;

    return i+1;
}

private static int binToNum(boolean [] boolArr){
    int num=0;

    for(int i=0;i<bits;i++){
       if(boolArr[i]==true) num+=(int)Math.pow(2, i);
    }
    
       return num;
}

private static void printArray(boolean [] array){
    System.out.println("----------->\t");
  for(int i=array.length-1;i>=0;i--){
    System.out.print(array[i]+" ");
  }
  System.out.println();
}
}

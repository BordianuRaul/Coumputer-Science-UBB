/*#[allow(unused_variables)]

fn main() {
    println!("Hello, world!");
    let v:i32;

    let y ={
        let x= 4;
        let k=5;

        x +k// or x+k===> y=the result of x+k
    };

    println!("{}",y);
}*/
/*
fn main() {

    let  a=10;
    let b= 30;
    let c= add_numbers(a,b);

    println!("The result is {}", c);
}

fn add_numbers(x:i32, y:i32) -> i32{
    let z = x+ y;
    z
}

*/
/*
fn main(){
    let x =5;
    let y =x; //copy

    let s1= String::from("hello");
    let s2 =s1.clone(); //s2=s1 violates R2 of ownership in Rust;just the pointer is copied, not valid in Rust

    println!("s1={}, s2={}", s1, s2);
}*/

/*
//ownership and functions 1
fn main(){
    let s= String::from("hello"); // the scope of s
    takes_ownership(s.clone()); // s's value moves into the function (it is consumed), and no longer valid on the next statements

    println!("{}",s.len());// we should send a copy to the other method, and give ownership to the called method

    let  x=13;// x comes into scope
    makes_copy(x); //x would move into the function, but the value of x is copied, so the x variable can be used onwards
    //x =x+1; //x is immutable
    println!("{}",x);
}


fn makes_copy(intV:i32){
    println!("makes_copy: {}", intV);
}

fn takes_ownership(stringV:String){
    println!("takes_ownership: {}", stringV);
}// the data gets deallocated from the heap memory

*/

fn main(){
    let s1= gives_ownership();

    println!("{}",s1);
    let s2= String::from("hello"); // the scope of s

    println!("{}",s2);//s2 is in this scope
    println!("{}",s2.len());// s2 is in this scope

    let s3= takes_and_gives_ownership(s2.clone()); // s2 value moves into the function (it is consumed), and no longer valid on the next statements
    //s2 is sent, to the method, consumed there, and dropped and the end of the execution
    //s3 ownerships is trnfered to this method
    println!("{}",s2.len());// we should send a copy to the other method, and give ownership to the called method

    println!("{}, {}",s3, s3.len());// s3 is in this scope

}//s1 is dropped here, s2 was moved to the method, s3 is dropped here (after being transfered to this method)


fn gives_ownership() -> String{
    let stringV= String::from("yours");
    stringV
}//ownership of the data from stringV is given to the caller method

fn takes_and_gives_ownership(stringV:String)-> String{
    stringV
}// the data gets deallocated from the heap memory


/*
yours - created in gives_ownership in stringV
transfered ownership to main
yours - owned by main in s1
stringV is dropped

hello - created in main in s2
transfered ownership in takes_and_gives_ownership
s2 is dropped
hello - owned in takes_and_gives_ownership
transfered ownership in main
hello - owned by main in s3
 */

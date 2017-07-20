//get fibonacci number by index

//Solution 1 - recursion
function fib1(i){
    if(i==0 || i==1)return i;
    return fib1(i-1) + fib1(i-2);
}

//Solution 2 - loop
function fib2(i){
    if(i==0 || i==1)return i;
    var count=2, n1=0, n2=1;
    while(count<=i){
        count+=1;
        const t = n2;
        n2 = n1+n2;
        n1 = t;
    }
    return n2;
}
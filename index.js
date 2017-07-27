//Question: Get fibonacci number by index:

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

//Question: Print fibonacci calling: printFib(n1, n2)

//Solution 1 - Descending order
function printFib1(n1, n2){
    console.log(n1);
    if(n1 > 0)printFib(n2-n1, n1);
}
//Solution 2 - Ascending order
function printFib2(n1, n2){
    if(n1 > 0)printFib(n2-n1, n1);
    console.log(n1);
}

//Question: Find missing number in array with values between 1...n

//Solution 1 - Assume only one number is missing and Max value is known for example: 100
function findMissing1(arr){
    var sum = 0;
    for(const n of arr){
        sum += n;
    }
    return (100*(100+1)/2) - sum;
}

//Solution 2 - Assume only one number is missing and Max value is unknown
function findMissing2(arr){
    var sum = 0, max = 0;
    for(const n of arr){
        sum += n;
        if(n > max) max = n;
    }
    return (max*(max+1)/2) - sum;
}

//Solution 3 - Assume 2 numbers are missing and Max value is unknown
//TODO

//Solution 4 - Assume unknown amount of numbers are missing and Max value is unknown
//TODO

//Question: find biggest contiguous sub array
function biggestSubArr(arr){
    var max = Number.MIN_SAFE_INTEGER, sum = 0;
    var test = [], found = [];
    for(const n of arr){
        sum += n;
        test.push(n);
        if(max < sum ){
            max = sum;
            found = test.slice(0);//clone existing test values to found
        }
        if(sum < 0){
            sum = 0;
            test = [];
        }
    }
    return found;
}

//Question: count all digits in a number

//Solution 1 - dynamic type conversion String -> Number -> String
function countDigits1(num){
    num = ""+num;//stringify the number
    const digits = [0,0,0,0,0,0,0,0,0,0];
    for(let i=0, count=num.length; i<count; i++){
       digits[num[i]*1]++;
    }
    return digits;
}

//Solution 2 - without type conversion
function countDigits2(num){
    const digits = [0,0,0,0,0,0,0,0,0,0];
    for(let d; num > 0 && 0 <= (d = num%10); ){
        digits[d]++;
        num = parseInt(num / 10);
    }
    return digits;
}
import Foundation

//Question: Get fibonacci number by index:

//Solution 1 - recursion
public func fib0(i: Int) -> Int {
    if i==0 || i == 1{ return i; }
    return fib0(i: i-1 ) + fib0(i: i-2);
}

//Solution 2 - loop
public func fib2(i:Int)->Int{
    if i==0 || i==1{return i;}
    var x=2, n1=0, n2=1;
    while(x<=i){
        x+=1;
        let t = n2;
        n2 = n1+n2;
        n1 = t;
    }
    return n2;
}

//Question: Print fibonacci calling: printFib(n1, n2)

//Solution 1 - Descending order
public func printFib1(n1:Int, n2:Int){
    print(n1);
    if n1 > 0{
        printFib1(n1: n2-n1, n2: n1);
    }
}

//Solution 2 - Ascending order
public func printFib2(n1:Int, n2:Int){
    if n1 > 0{
        printFib2(n1: n2-n1, n2: n1);
    }
    print(n1);
}

//Question: Find missing number in array with values between 1...n (unordered)

//Solution 1 - Assume only one number is missing and Max value is known for example: 100
//TODO

//Solution 2 - Assume only one number is missing and Max value is unknown
//TODO

//Solution 3 - Assume 2 numbers are missing and Max value is unknown
//TODO

//Solution 4 - Assume unknown amount of numbers are missing and Max value is unknown
//TODO

//Question: find biggest contiguous sub array
public func maxSubArr(_ arr: [Int]) ->[Int]{
    var max=0,sum=0;
    var test:[Int]=[], found:[Int]=[];
    for n in arr{
        sum+=n;
        test.append(n);
        if(max < sum ){
            max = sum;
            found = Array(test);//clone existing test values to found
        }
        if(sum < 0){
            sum = 0;
            test = [];
        }
    }
    return found;
}

//Question: Shuffle an array
public func mShuffle (_ nums:[Int]) -> [Int] {
    var rNums = nums;
    let last = rNums.count-1;
    var i = last;
    while(i>=0) {
        let randIndex = Int(arc4random_uniform(UInt32(i)));
        let temp=rNums[randIndex];
        rNums[randIndex]=rNums[i];
        rNums[i]=temp;
        i-=1;
    }
    return rNums;
}

//Question: count all digits in a number

//Solution 1 - dynamic type conversion String -> Number -> String
public func countDigits(num:Int){
    let numStr = "\(num)";
    var digits = [Int](repeatElement(0, count: 10));//[0,0,0,0,0,0,0,0,0,0]
    for c in numStr.characters{
        digits[Int("\(c)")!] += 1;
    }
    print(digits);
}

//Solution 2 - without type conversion
//TODO

//Question: Find duplicated int in array with values between 0...n (unordered). Cannot use additional memory.

//Solution 1 - Using sorting O(n^2).
//TODO

//Solution 2 - Without sorting O(n).
//TODO




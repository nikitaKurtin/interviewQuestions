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
//Solution 1 - Assume only one number is missing and Max value is known for example: 100
func findMissing1(arr : [Int])->Int{
    var sum = 0;
    for n in arr{
        sum += n;
    }
    return (100*(100+1)/2) - sum;
}

//Solution 2 - Assume only one number is missing and Max value is unknown
func findMissing2(arr : [Int]) -> Int{
    var sum = 0, max = 0;
    for  n in arr{
        sum += n;
        if(n > max){max = n;}
    }
    return (max*(max+1)/2) - sum;
}

//Solution 3 - Assume 2 numbers are missing and Max value is unknown
func findMissing3(arr : [Int]) -> [Int]{
    var sum = 0, max = 0, misSum=0;
    var misNums:[Int]=[];
    for n in arr{
        sum += n;
        if(n > max){max = n;}
    }
    misSum = (max*(max+1)/2) - sum;//Sum of two missing numbers
    var n:Int = (misSum < max-1) ? misSum : max-1;
    while( n > 1){
        if(!arr.contains(n)){misNums = [n, misSum-n];}
        n-=1;
    }
    return misNums;
}

//Solution 4 - Assume unknown amount of numbers are missing and Max value is unknown
public func findMissing4(arr : [Int])->[Int]{
    var sum = 0, count = 0, max = 0, misSum=0;
    var misNums:[Int]=[];
    for n in arr{
        count += 1;
        sum += n;
        if(n > max){ max = n;}
    }
    misSum = (max*(max+1)/2) - sum;//Sum of all missing numbers
    count = max - count;//How many numbers are missing
    var n:Int = (misSum < max-1) ? misSum : max-1;
    while(n > 1){
        if(!arr.contains(n)){
            misNums.append(n);
            misSum -= n;
            count -= 1;
            if(count == 1){//only one left
                misNums.append(misSum);
                break;
            }
        }
        n -= 1;
    }
    return misNums;
}

//Solution 5 - Assume unknown amount of numbers are missing and Max value is unknown (using second array)
public func findMissing5(arr : [Int])->[Int]{
    var count=0, max = 0;
    var neededNums:[Int] = [];
    var misNums:[Int] = [];
    for n in arr{
        count += 1;
        neededNums.append(0);//zero for every needed number
        if(n > max){ max = n;}
    }
    for _ in count..<max{
        neededNums.append(0);//complete zeros for missing numbers
    }
    for n in arr{//iterate again
        neededNums[n == max ? 0 : n] += 1;//add one - used as index in second array (convert max to zero)
    }
    for i in 1..<neededNums.count{
        if(neededNums[i]<1){misNums.append(i);}//if value is zero, than index is a missing number
    }
    return misNums;
}

//Question: find biggest contiguous sub array
public func biggestSubArr(_ arr: [Int]) ->[Int]{
    var max=Int.min, sum=0;
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
public func countDigits1(num:Int){
    let numStr = "\(num)";
    var digits = [Int](repeatElement(0, count: 10));//[0,0,0,0,0,0,0,0,0,0]
    for c in numStr.characters{
        digits[Int("\(c)")!] += 1;
    }
    print(digits);
}

//Solution 2 - without type conversion
public func countDigits2(num : Int)->[Int]{
    var digits = [0,0,0,0,0,0,0,0,0,0];
    var tNum = num;
    var d:Int;
    while(tNum > 0){
        d = tNum%10;
        if(0 <= d){
            digits[d] += 1;
            tNum = Int(tNum / 10);
        }
    }
    return digits;
}

//Question: Find duplicated int in array with values between 0...n (unordered). Cannot use additional memory.

//Solution 1 - Using sorting O(n^2).
public func findDup1(arr : [Int])->Int?{
  var sArr = arr.sorted();
  var i = sArr.count-1;
  while(i >= 0){
      let t = sArr[i];
      if(t == sArr[i-1]){return t;}
      i-=1;
  }
  return nil;
}

//Solution 2 - Without sorting O(n).
public func findDup2(arr : [Int])->Int?{
    var tArr = arr;
    for n in arr{
        let t = n<0 ? n * -1 : n;//if negative turn to positive
        tArr[t] *= -1;
    }
    for n in tArr{
        if(n > 0){return n;}
    }
    return nil;
}




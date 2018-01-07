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

//Question: Find missing number in array with values between 1...n (unordered)

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
function findMissing3(arr){
    var sum = 0, max = 0, misSum, misNums;
    for(const n of arr){
        sum += n;
        if(n > max) max = n;
    }
    misSum = (max*(max+1)/2) - sum;//Sum of two missing numbers
    for(let n = Math.min(misSum, max-1); n > 1; n--){
        if(arr.indexOf(n) < 0)misNums = [n, misSum-n];
    }
    return misNums;
}

//Solution 4 - Assume unknown amount of numbers are missing and Max value is unknown
function findMissing4(arr){
    var sum = 0, count = 0, max = 0, misSum, misNums=[];
    for(const n of arr){
        count++;
        sum += n;
        if(n > max) max = n;
    }
    misSum = (max*(max+1)/2) - sum;//Sum of all missing numbers
    count = max - count;//How many numbers are missing
    for(let n = Math.min(misSum, max-1); n > 1; n--){
        if(arr.indexOf(n) < 0){
            misNums.push(n);
            misSum -= n;
            if(--count == 1){//only one left
                misNums.push(misSum);
                break;
            }
        }
    }
    return misNums;
}

//Solution 5 - Assume unknown amount of numbers are missing and Max value is unknown (using second array)
function findMissing5(arr){
    var count=0, max = 0, neededNums=[], misNums=[];
    for(const n of arr){
        count++;
        neededNums.push(0);//zero for every needed number
        if(n > max) max = n;
    }
    for(let i = (max - count); i>0; i--){
        neededNums.push(0);//complete zeros for missing numbers
    }
    for(const n of arr){//iterate again
        neededNums[n == max ? 0 : n]++;//add one - used as index in second array (convert max to zero)
    }
    for(let i=neededNums.length; i>0; i--){
        if(neededNums[i]<1)misNums.push(i);//if value is zero, than index is a missing number
    }
    return misNums;
}

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

//Question: Shuffle an array
function shuffle(arr){
    var shuffle = arr.slice(0);
    for(let i=shuffle.length-1; i >= 0; i--){
        const r = Math.floor(Math.random() * i);
        const t = shuffle[r];
        shuffle[r] = shuffle[i];
        shuffle[i] = t;
    }
    return shuffle;
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

//Question: Find duplicated int in array with values between 0...n (unordered). Cannot use additional memory.

//Solution 1 - Using sorting O(n^2).
function findDup1(arr){
    arr.sort();//Bottle neck of this solution
    for(let i=arr.length-1; i>=0; i--){
        const t = arr[i];
        if(t == arr[i-1])return t;
    }
}

//Solution 2 - Without sorting O(n).
function findDup2(arr){
    for(const n of arr){
        const t = n<0 ? n*-1 : n;//if negative turn to positive
        arr[t] *= -1;
    }
    for(const n of arr){
        if(n > 0)return n;
    }
}

//Question: Compress a string using the counts of repeated characters. 

//Solution - Assume the string consist only from letters [A-Za-z] (no numbers)
function compress(str){
  var compressed = "", strLength = str.length;
  for(let i=0, compCount=0; i<strLength; i++){
     compCount++;
     if(i+1 >= strLength || str[i+1] != str[i]){
        compressed += str[i]+compCount;
        compCount = 0;
     }
  }
  return compressed.length < strLength ? compressed : str;//if compressed isn't smaller - return original
}

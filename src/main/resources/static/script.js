function sayHello() {
   alert("Hello World")
}

function conversionUKToUS(int weight, int height) {
    let kg = 2.20
    let m = 39.37
    let newHeight = height/12
    let newWeight = weight/kg
    let height2 = height/m
    console.log(newWeight/(height2**2))
}
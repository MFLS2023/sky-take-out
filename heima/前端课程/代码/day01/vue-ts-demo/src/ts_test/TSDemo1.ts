//字符串类型
let username: string = 'itcast'

//数字类型
let age: number = 20

//布尔类型
let isTrue: boolean = true

console.log(username)
console.log(age)
console.log(isTrue)

//字面量类型
function printText(s: string, alignment: 'left'|'right'|'center'){
    console.log(s,alignment)
}

printText('hello','left')
//printText('hello','aaa')

//定义接口
interface Cat {
    name: string,
    age?: number //当前属性为可选
}

//定义变量，并且指定为Cat类型
const c1: Cat = {name: '小白', age: 1}
const c2: Cat = {name: '小白'}
//const c3: Cat = {name: '小白', age: 1, sex: ''}

//定义一个类，使用class关键字
class User {
    name: string; //指定类中的属性
    constructor(name: string){ //构造方法
        this.name = name
    }

    //方法
    study(){
        console.log(this.name + '正在学习')
    }
}

//使用User类型
const user = new User('张三')

//输出类中的属性
console.log(user.name)

//调用类中的方法
user.study()

//定义接口
interface Animal {
    name: string
    eat(): void
}

//定义一个类，实现上面的接口
class Bird implements Animal {
    name: string
    constructor(name: string){
        this.name = name
    }

    eat(): void {
        console.log(this.name + 'eat')
    }
}

//创建类型为Bird的对象
const b1 = new Bird('燕子')
console.log(b1.name)
b1.eat()

//定义一个类，继承上面的类
class Parrot extends Bird {
    say() {
        console.log(this.name + ' say hello')
    }
}

const myParrot = new Parrot('Polly')

myParrot.eat()
myParrot.say()
console.log(myParrot.name)
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
//字符串类型
var username = 'itcast';
//数字类型
var age = 20;
//布尔类型
var isTrue = true;
console.log(username);
console.log(age);
console.log(isTrue);
//字面量类型
function printText(s, alignment) {
    console.log(s, alignment);
}
printText('hello', 'left');
//定义变量，并且指定为Cat类型
var c1 = { name: '小白', age: 1 };
var c2 = { name: '小白' };
//const c3: Cat = {name: '小白', age: 1, sex: ''}
//定义一个类，使用class关键字
var User = /** @class */ (function () {
    function User(name) {
        this.name = name;
    }
    //方法
    User.prototype.study = function () {
        console.log(this.name + '正在学习');
    };
    return User;
}());
//使用User类型
var user = new User('张三');
//输出类中的属性
console.log(user.name);
//调用类中的方法
user.study();
//定义一个类，实现上面的接口
var Bird = /** @class */ (function () {
    function Bird(name) {
        this.name = name;
    }
    Bird.prototype.eat = function () {
        console.log(this.name + 'eat');
    };
    return Bird;
}());
//创建类型为Bird的对象
var b1 = new Bird('燕子');
console.log(b1.name);
b1.eat();
//定义一个类，继承上面的类
var Parrot = /** @class */ (function (_super) {
    __extends(Parrot, _super);
    function Parrot() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    Parrot.prototype.say = function () {
        console.log(this.name + ' say hello');
    };
    return Parrot;
}(Bird));
var myParrot = new Parrot('Polly');
myParrot.eat();
myParrot.say();
console.log(myParrot.name);

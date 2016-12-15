# Dagger2Demo
> 经历过Dagger2，最初2.X的版本到现在2.8的版本迭代，Dagger2越来越受到各方面的关注
> 这一次迭代的版本，对于SubComponent进行了功能优化，赋予了这个标注应有的意义
> 这个Demo结合自己做项目的经验，尝试在各种情况下去最大化利用Dagger2进行代码的构建
> 下面就是个人在项目中实践出的一些代码架构的心得，比较适合一些大型Android的项目，对于代码质量有一定要求的项目，小型项目推荐就用用AndroidAnnotations吧，这个比较全能
> 整个框架的基本思路可以参考如下网址[The Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)


## 一些强势的第三方帮手
> 把这个放在这里先写，是希望一些好的框架能更多的被人知道和了解，当然不是说一定要下面的这些去构建代码
> 下面介绍的这些，都是个人用了蛮久，并且都是经过实战历练的第三方
> 有兴趣的可以去拜见一下

- UI相关：
    [butterknife](https://github.com/JakeWharton/butterknife) 以前项目用的是[AndroidAnnotations](http://androidannotations.org/), 用久了发现`AndroidAnnotations`覆盖面太过于广泛，基本是属于一个库能涉及到整个Android_App的构建,这就导致在整个项目配合其他库包一起附合使用的时候，AA的价值就会被缩小
        所以后期我决定不再依赖AA,而是选择更纯粹的用于UI界面注入的butterknife,优点就是更针对性，更轻量化。
    SupportV7 相关, recyclerView;appcompat;cardView;design;palette等，这部分的依赖主要是帮助项目去实现基于[Material Design](https://developer.android.google.cn/design/index.html)风格的设计代码
        `Material Design`这东西，单独拉出来说 一两天都讲不完，所以这个就请自行去研究吧，反正，很炫酷，我很喜欢

- Net网络请求相关：
    [retrofit2.0](https://github.com/square/retrofit) 网络请求相关的库包，个人觉得这是个处理网络请求最好的第三方库，同时也是个延展性非常好的框架，本身框架自带对于[OkHttp](https://github.com/square/okhttp)、[RxJava](https://github.com/ReactiveX/RxJava)等的支持，联合起来使用效果更佳
        备注：这是套同样可以运用在Java上的库包，有兴趣的可以多去研究研究

- 数据处理相关：
    [rxJava](https://github.com/ReactiveX/RxJava) 有人戏称，这是套万能的框架，只要你想得出，他就能实现的出来；事实证明，这套库包很厉害！至少他帮我解决了项目中和数据相关的所有处理问题，代码写起来配合Java8的特性，可以很炫酷，可以说是集颜值和智商为一体的存在
        提到RxJava 不得不提的是Java8的特性lambda表达式，有了这个，可以让RxJava的代码更美观 简洁
        下面有两个lambda相关的：
            [gradle-retrolambda](https://github.com/evant/gradle-retrolambda) 这个是用于Gradle编译的项目，可以做到在Android中编写Java8代码
            [retrolambda](https://github.com/orfjackal/retrolambda) 这个是通用的，可以让没有装Java8环境的机器编写Java8代码
    Ps：可能你会疑问为什么没有DB相关的，嘛……项目当中不让用和DB相关的第三方，所以这里我也就不坑大家了，自己找找吧，网上还是有很多的比方说orlimte

- 一些辅助工具：
    [gson](https://github.com/google/gson) 别告诉我你没见过这个……
    [auto](https://github.com/google/auto) 这个是google提供的一个工具库包里面有个Auto_Value的东西可以帮助你去实例化自定义的Annotation，这个在Dagger2当中会用到。


## Gradle环境编译方式
> 工欲善其事必先利其器，话是这么说，[gradle](https://docs.gradle.org/current/release-notes)官方文档全英文的，看起来是挺头疼的
> 但是有些东西还是需要去了解的，这样可以更好帮助你去管理项目代码，和apk打包等机械化的操作



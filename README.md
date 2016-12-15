# Dagger2Demo
> 经历过Dagger2，最初2.X的版本到现在2.8的版本迭代，Dagger2越来越受到各方面的关注
> 这一次迭代的版本，对于SubComponent进行了功能优化，赋予了这个标注应有的意义
> 这个Demo结合自己做项目的经验，尝试在各种情况下去最大化利用Dagger2进行代码的构建
> 下面就是个人在项目中实践出的一些代码架构的心得，比较适合一些大型Android的项目，对于代码质量有一定要求的项目。
> 
> 整个框架的基本思路可以参考如下网址[The Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)
> 如果是小型项目，推荐就用用AndroidAnnotations吧，这个比较全能


## 一些强势的第三方帮手
> 把这个放在这里先写，是希望一些好的框架能更多的被人知道和了解，当然不是说一定要下面的这些去构建代码
> 下面介绍的这些，都是个人用了蛮久，并且都是经过实战历练的第三方
> 有兴趣的可以去拜见一下


- UI相关
    - [butterknife](https://github.com/JakeWharton/butterknife) 以前项目用的是[AndroidAnnotations](http://androidannotations.org/), 用久了发现`AndroidAnnotations`覆盖面太过于广泛，基本是属于一个库能涉及到整个Android_App的构建,这就导致在整个项目配合其他库包一起附合使用的时候，AA的价值就会被缩小
        所以后期我决定不再依赖AA,而是选择更纯粹的用于UI界面注入的butterknife,优点就是更针对性，更轻量化。
    - SupportV7 相关, recyclerView;appcompat;cardView;design;palette等，这部分的依赖主要是帮助项目去实现基于[Material Design](https://developer.android.google.cn/design/index.html)风格的设计代码
        `Material Design`这东西，单独拉出来说 一两天都讲不完，所以这个就请自行去研究吧，反正，很炫酷，我很喜欢

- Net网络请求相关
    - [retrofit2.0](https://github.com/square/retrofit) 网络请求相关的库包，个人觉得这是个处理网络请求最好的第三方库，同时也是个延展性非常好的框架，本身框架自带对于[OkHttp](https://github.com/square/okhttp)、[RxJava](https://github.com/ReactiveX/RxJava)等的支持，联合起来使用效果更佳
        备注：这是套同样可以运用在Java上的库包，有兴趣的可以多去研究研究

- 数据处理相关
    - [rxJava](https://github.com/ReactiveX/RxJava) 有人戏称，这是套万能的框架，只要你想得出，他就能实现的出来；事实证明，这套库包很厉害！至少他帮我解决了项目中和数据相关的所有处理问题，代码写起来配合Java8的特性，可以很炫酷，可以说是集颜值和智商为一体的存在
        提到RxJava 不得不提的是Java8的特性lambda表达式，有了这个，可以让RxJava的代码更美观 简洁
        下面有两个lambda相关的：
            - [gradle-retrolambda](https://github.com/evant/gradle-retrolambda) 这个是用于Gradle编译的项目，可以做到在Android中编写Java8代码
            - [retrolambda](https://github.com/orfjackal/retrolambda) 这个是通用的，可以让没有装Java8环境的机器编写Java8代码
    Ps：可能你会疑问为什么没有DB相关的，嘛……项目当中不让用和DB相关的第三方，所以这里我也就不坑大家了，自己找找吧，网上还是有很多的比方说orlimte

- 一些辅助工具
    - [gson](https://github.com/google/gson) 别告诉我你没见过这个……
    - [auto](https://github.com/google/auto) 这个是google提供的一个工具库包里面有个Auto_Value的东西可以帮助你去实例化自定义的Annotation，这个在Dagger2当中会用到。


## Gradle环境编译方式
> 工欲善其事必先利其器，话是这么说，[gradle](https://docs.gradle.org/current/release-notes)官方文档全英文的，看起来是挺头疼的
> 但是有些东西还是需要去了解的，这样可以更好帮助你去管理项目代码，和apk打包等机械化的操作

前言：在项目中，很多时候避免不了要引入多个module作为jar使用，这时候，如果这些module的配置每次都要手动去配，会加大工作量和导致项目编译不统一等等各种问题，于是一种统一配置所有module的方式就变得有些必要

具体看代码：-root/build.gradle
`apply from: 'buildConfigs/dependencies.gradle'`
可以在顶部发现这样一段话，这段代码目的在于，在Project的整体配置当中通配到`buildConfigs/dependencies.gradle`目录下的这个文件，是的该文件下定义的属性可以被任何一个子`module`运用到，这样就可以把一些全局的变量写在这个`dependencies.gradle`里，然后需要调用的地方配置一下即可

然后在看`buildConfigs/dependencies.gradle`文件，里面配置了一些基本的版本信息VersionCode，和所有第三方引用的gradle配置行

以第三方库的引用举例：

dependencies.gradle 配置
```
    daggerVersion = '2.8'
    
    appDependencies = [
      // dagger2 库包
      daggerCompiler        : "com.google.dagger:dagger-compiler:${daggerVersion}",
      dagger                : "com.google.dagger:dagger:${daggerVersion}",            
    ]
```

引用的地方presentation/build.gradle
```
dependencies {
	def dependencies = rootProject.ext.appDependencies // 定义一个变量去获取到dependencies的属性
    apt dependencies.daggerCompiler
    compile dependencies.dagger
}
```

### 自动化打包apk相关
> 打包apk这件事情，做项目都知道，一个不小心，环境配错了，XX部分测试代码漏出去了，等等各种乱七八糟的问题很容易出现，项目越大，设计到打包所对应的配置越多，比方说渠道包、提测包、release发布包等等，各种情况各种有
> 为了应对这种种的情况，自动化打包，变得很有必要
> gradle为了应对这样的情况在配置当中添加了 buildTypes这个神奇的东西，让打包变得可变动

具体代码：
```
buildTypes {
        debug {
            // 版本信息
            buildConfigField "String", "VERSION_SUFFIX", "\"_debug\""
            // 频道
            buildConfigField "String", "CHANNEL", "\"CHANNEL\""
            // 区分debug和release包名
            applicationIdSuffix ".debug"
            minifyEnabled false
            zipAlignEnabled true
        }

        release.initWith(buildTypes.debug)
        release {

            // 版本信息
            buildConfigField "String", "VERSION_SUFFIX", "\"V%s\""
            // 频道
            buildConfigField "String", "CHANNEL", "\"CHANNEL\""
            applicationIdSuffix ""
            // 设置签名配置
            signingConfig signingConfigs.release

            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'

            // 修改打包出的apk的生成路径的方法
            applicationVariants.all {
                variant ->
                    variant.outputs.each { output ->
                        def apkFile = output.outputFile
                        if (apkFile != null && apkFile.name.endsWith('.apk')
                                && variant.buildType.name.contains("release")) {
                            def fileName;
                            if ("release".equalsIgnoreCase(variant.buildType.name)) {
                                fileName = "${releaseTime()}" + "_${defaultConfig.versionName}.apk"
                            } else
                                fileName = variant.buildType.name.replaceAll("release", "").toLowerCase() +
                                        "_${releaseTime()}" + "_${defaultConfig.versionName}.apk"
                            // 通过获取到truck项目的根路径去创建release目录
                            def dir = rootProject.relativeProjectPath("release")
                            File dirFile = new File(dir)
                            if (!dirFile.exists()) {
                                dirFile.mkdirs();
                            }
                            output.outputFile = new File(dir, fileName)
                            println()
                        }
                    }
            }
        }
    }
def releaseTime() {
    return new Date().format("yyyyMMdd_HHmmss")
}

```
其中，`buildConfigField `指的是可自定义的变量，这些变量在此处配置完成之后，build一下，会在`BuildConfig`类中看到对应配置的属性，可以利用这个进行多渠道，多环境的配置。
按照上面配置完成之后，可以在`AndroidStudio`的`BuildVariants`工具栏中找到你所配置的不同`buildType`，然后在你打包之前切换成对应的`buildType`即可

后记：其实不要把gradle想的太可怕，gradle本身也是支持java代码的，所以你大可以利用java的思路去编写，不懂的地方请狗哥或者stackoverflow一下，总有一款适合您




## 代码结构简介

首先附上项目整体结构图(楼下灵魂画师出没)

![image](https://github.com/CrazyClownSola/Dagger2Demo/blob/master/pictures/project_structure.png)

打开代码，你会发现项目的整体结构如上图，比较关键的几个文件我都在图里显示出来了，老司机一看可能就会明白，这是比较普遍的MVP的结构。
的确这样的构建代码的方式，最根源的思路就是在代码级别实现MVP分层，让各个层之间保持一定的松耦合。
但这也不仅仅是MVP这么简单，这里面还涉及到一个设计模式`Domain-Design-Driver`俗称<strong>"DDD"领域驱动模式</strong>


具体几个层级的关系图如下

![image](https://github.com/CrazyClownSola/Dagger2Demo/blob/master/pictures/structure_introduction.png)


### 层级定义：

- presentation 
	界面展示层，通常Activity，Fragment，View的代码都在这里，同时这是作为app启动的入口。
	理论上这里是不会出现任何和业务相关的逻辑代码，只负责界面的展示和一些工具的配置。
	但是由于引入了`Dagger2`，这一层级还会持有`Component`,`Module`等转换器的实例，打到实例配置的作用。
	这样这一层的代码就分成： UI相关 + 工具类 这样的形式
- domain
	业务逻辑层，如果你有观察过`domain/build.gradle`你会发现这样一行代码
	`apply plugin: 'java'`
	这代表了，整个domain层的代码是纯java的，和android搭不上什么关系
	这其实就很好定义了这一层代码，业务中转，这是一层纯粹和业务相关的代码块。
	这一层<strong>暴露出</strong>非常多的业务Case，每一个Case下面有这和自己这个Case相关的接口方法，当然这一层还定义了这些接口所需要的数据结构。
	对于其他层而言，这一层就是一个拥有茫茫多接口的server，调用层并不用关心接口具体的实现，调用层只需要对自己所需要的case进行接口访问，然后数据会通过回调以约定好的数据结构返回回来。
	然后回到实现部分，这时候一个新的概念需要提出`repository`仓储，仓储也是一个泛化的概念，他的本质是一个数据存储的地方，但是这个数据存储的方式仓储本身并没有明确定义。
	简单点说就是在`domain`层需要定义出业务所需的不同的数据仓储接口，然后在实现具体某个case的接口的时候，该层就会调用对应的仓储接口去获取数据，当然具体仓储如何实现，这并不是这一层所关心的，关心这个实现的是下面一层`data`。
	
- data
   数据层，顾名思义，处理数据的地方，然而很明显，如果没有个明确的目标，光说这种处理数据，估计会被人打死0 0
   这一层的实现的核心是`domain`所提供的一系列的`repository`接口，其实从代码角度看，你会发现无数个实现了`repository`接口的类。
   这一些实现类就是这一层的核心。
   就像上面所介绍的那样，`repository`仓储在定义的时候，他本身并不关心这个仓储的实现方式，意味着，这可以是通过DB、或者Net、或者Cache，具体采用怎样方式把数据返回，仓储本身并不在意。
> 题外话
   那么我不知道是否有人考虑过这样一个问题，在很多成型的项目初期，如何定义数据库结构，这个问题往往会造成很大的困惑，往往项目初期几张简单的表能实现基本功能，然后随着项目迭代，避免会出现需要改表结构，甚至是增删表等各种恶心的问题。
   此时，如果获取数据的方式是按照上文所陈述的仓储的方式的话，那么设想一下，是否会有这样的可能，在初期构建的时候仓储是通过Cache去返回数据，并不建库，这样初期数据全部都是存储在内存当中，这样当你想要修改一个数据结构的时候，你需要做的事情是否就少了很多。
   然后等到项目(需求)稳定的时候，你再将仓储的实现方式调整成数据库。
  当然这只是提供了一个思路。
  

	回归正题，既然`domain`层已经把仓储定义完，那么这一层需要做的就变得相对简单，单一，
	就是获取数据，存储，输出
	在Android的角度上看，这一层就是实现网络请求最合适的地方。

### 层级之间的联动和代码实现
> 在介绍完层级之间的定义之后，如何实现？
> 其实我很想大声说一句“这种高深的问题，自己研究去，23333”
> 但是我的码农灵魂告诉我，我不能这么做。
> （楼上病犯了）
> 咳咳……
>   
> 好了，正题
> 在认知到`Dagger2`的这套框架之前，上面的分层的代码，可能只能停留在概念的角度上，否则你可能需要付出非常多的精力在实例绑定的角度上，更或者是更多其他问题上……
> 具体`Dagger2`到底做了什么，请看下文


#### Dagger2
> Dagger is a fully static, compile-time dependency injection framework for both Java and Android. It is an adaptation of an earlier version created by Square and now maintained by Google.
> 楼上这段话，截取自[官方文档](https://google.github.io/dagger/)
> 其实啥也没说，就是说这个是个依赖注入式的框架
> 让我酝酿下……要讲出来好烦……写写代码会好很多……


##### 基本元素

- Component 组件
    核心概念，`Dagger2`当中最大的一个元素。
    从Android角度看，Component在大多数的情况下对应的是Application和Activity
    从`Dagger2`本身的角度看，Component并没有局限性，
- Module

- SubComponent

- Inject


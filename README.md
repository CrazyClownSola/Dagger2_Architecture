# Dagger2-Retrofit-RxJava-Retrolambda
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
    - [picasso](https://github.com/square/picasso) `square`公司是个很靠谱的公司，提供了很多很实用的第三方库，当中很出名的就是`picasso`了，`picasso`图片处理库，优势在于使用简单，内部实现讲究几个基本原则：
        - 在adapter中回收和取消当前的下载；
        - 使用最少的内存完成复杂的图形转换操作；
        - 自动的内存和硬盘缓存；
        - 图形转换操作，如变换大小，旋转等，提供了接口来让用户可以自定义转换操作；
        - 加载载网络或本地资源；
        Cache的算法采用的是Lrucacha，主要是get和set方法，存储的结构采用了LinkedHashMap，这种map内部实现了lru算法（Least Recently Used 近期最少使用算法）。
	嘛... 谁用谁知道

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

<strong>前言：</strong>在项目中，很多时候避免不了要引入多个module作为jar使用，这时候，如果这些module的配置每次都要手动去配，会加大工作量和导致项目编译不统一等等各种问题，于是一种统一配置所有module的方式就变得有些必要

具体看代码：

`-root/build.gradle` 当中 `apply from: 'buildConfigs/dependencies.gradle'`
可以在顶部发现这样一段话，这段代码目的在于，在Project的整体配置当中通配到`buildConfigs/dependencies.gradle`目录下的这个文件，是的该文件下定义的属性可以被任何一个子`module`运用到，这样就可以把一些全局的变量写在这个`dependencies.gradle`里，然后需要调用的地方配置一下即可

然后在看`buildConfigs/dependencies.gradle`文件，里面配置了一些基本的版本信息VersionCode，和所有第三方引用的gradle配置行

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
            // 自定义的变量，这些变量在此处配置完成之后，build一下，会在`BuildConfig`类中看到对应配置的属性
            // 版本信息
            buildConfigField "String", "VERSION_SUFFIX", "\"_debug\""
            // 频道
            buildConfigField "String", "CHANNEL", "\"CHANNEL\""
            // 区分debug和release包名
            applicationIdSuffix ".debug"
            minifyEnabled false
            zipAlignEnabled true
        }

        release.initWith(buildTypes.debug) // 复用Debug当中部分配置
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
                        def apkFile = output.outputFile // 获取到output里面生成的apk文件
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
                        }
                    }
            }
        }
}

// 定义一个返回时间戳的方法
def releaseTime() {
    return new Date().format("yyyyMMdd_HHmmss")
}

```

按照上面配置完成之后，可以在`AndroidStudio`的`BuildVariants`工具栏中找到你所配置的不同`buildType`，然后在你打包之前切换成对应的`buildType`即可

<strong>后记：</strong>其实不要把gradle想的太可怕，gradle本身也是支持java代码的，所以你大可以利用java的思路去编写，不懂的地方请狗哥或者stackoverflow一下，总有一款适合您


## 代码结构简介

首先附上项目整体结构图(楼下灵魂画师出没)

![image](https://github.com/CrazyClownSola/Dagger2Demo/blob/master/pictures/project_structure.png)

打开代码，你会发现项目的整体结构如上图，比较关键的几个文件我都在图里显示出来了，老司机一看可能就会明白，这是比较普遍的MVP的结构。
的确这样的构建代码的方式，最根源的思路就是在代码级别实现MVP分层，让各个层之间保持一定的松耦合。
但这也不仅仅是MVP这么简单，这里面还涉及到一个设计模式`Domain-Design-Driver`俗称[DDD领域驱动模式][1]


具体几个层级的关系图如下

![image](https://github.com/CrazyClownSola/Dagger2Demo/blob/master/pictures/structure_introduction.png)


### 层级定义：

- <strong>presentation</strong>

	界面展示层，通常Activity，Fragment，View的代码都在这里，同时这是作为app启动的入口。
	理论上这里是不会出现任何和业务相关的逻辑代码，只负责界面的展示和一些工具的配置。
	但是由于引入了`Dagger2`，这一层级还会持有`Component`,`Module`等转换器的实例，打到实例配置的作用。
	这样这一层的代码就分成： UI相关 + 工具类 这样的形式
- <strong>domain</strong>

	业务逻辑层，如果你有观察过`domain/build.gradle`你会发现这样一行代码
	`apply plugin: 'java'`
	这代表了，整个domain层的代码是纯java的，和android搭不上什么关系
	这其实就很好定义了这一层代码，业务中转，这是一层纯粹和业务相关的代码块。
	这一层<strong>暴露出</strong>非常多的业务Case，每一个Case下面有这和自己这个Case相关的接口方法，当然这一层还定义了这些接口所需要的数据结构。
	对于其他层而言，这一层就是一个拥有茫茫多接口的server，调用层并不用关心接口具体的实现，调用层只需要对自己所需要的case进行接口访问，然后数据会通过回调以约定好的数据结构返回回来。
	然后回到实现部分，这时候一个新的概念需要提出`repository`仓储，仓储也是一个泛化的概念，他的本质是一个数据存储的地方，但是这个数据存储的方式仓储本身并没有明确定义。
	简单点说就是在`domain`层需要定义出业务所需的不同的数据仓储接口，然后在实现具体某个case的接口的时候，该层就会调用对应的仓储接口去获取数据，当然具体仓储如何实现，这并不是这一层所关心的，关心这个实现的是下面一层`data`。
- <strong>data</strong>

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
    从`Dagger2`本身的角度看，Component并没有局限性，只要是一个可以持有实例，并且拥有自己的生命周期的，都可以认知为一个Component

- Module 模块组

    依存于Component存在的模块，一般和Component成双成对出现(FFF团举起了火把)，作用是分配实例，对Component当中注入的抽象类或接口进行实例绑定

- SubComponent 子组件

    Dagger2 2.X版本之后更新的新的标注，但是在2.7版本之前不是很好用，2.7版本之后更新了对于这个注解的适配。
    顾名思义子组件，依附于一个ParentComponent存在的组建，很像java的继承的概念。

- Inject

    注入，标记了@Inject的类，会被注入到对应的组件中去。


##### 用法整合

看代码吧

```
@Singleton // 定义组件中的实例的为单例，整个组件的生命周期中只有一个实例
@Component( // 定义这个接口为组件标志
        modules = { // 定义这个组建的module
                AppModule.class
        }
)
public interface AppComponent {
    // 这种方式叫做，显示注入，这个方法的配置等于告诉Dagger，我需要把MainApplication当中所有有@Inject的类注入到这个组件当中去
    void inject(MainApplication application);
}

@Module // 标记这个类为Module
public class AppModule { // 这是一种简单使用的方式

    public AppModule() {
    }

    @Provides // This annotation means that method below provides dependency 自己翻译去
    @Singleton
    Utils provideUtils() {
        return new Utils();
    }
}

public class Utils {

    public String getTestString() {
        return "Text";
    }

}

public class MainApplication extends Application {

    @Inject // 注入
    Utils mUtil;

    // 组件实例，这里缓存了Component实例，是在于组件实例的第一次初始化。
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        // 这里的`DaggerAppComponent`是编译之后Dagger自己生成的类，找不到这个类，编译一下
        // 很多时候会发现写好代码，编译不通过，然后不知道错在哪儿的时候，可以参考`DaggerAppComponent`
        appComponent = DaggerAppComponent.builder().build();
        appComponent.inject(this); // 初始化后第一次申请
        mUtil.getTestString();
    }
}

```

上面是简单用法，具体可以参考官方文档，有介绍
然后涉及到SubComponent，会恶心一些

```
@Singleton // 定义组件中的实例的为单例，整个组件的生命周期中只有一个实例
@Component( // 定义这个接口为组件标志
        modules = { // 定义这个组建的module
                AppModule.class,
                SubComponentBindingModule.class // 万恶之源
        }
)
public interface AppComponent {
    // 这种方式叫做，显示注入，这个方法的配置等于告诉Dagger，我需要把MainApplication当中所有有@Inject的类注入到这个组件当中去
    void inject(MainApplication application);
}

@Module(
        subcomponents = { // 这个subcomponents是新版多出来的属性，让Module可以对应多个子组件
                DataBaseComponent.class
        }
)
public abstract class SubComponentBindingModule {// 这个类的本意是，将所有的SubComponent统一化，然后以Map的方式存放在Application中

    @IntoMap // 将这个实例绑定到 MainApplication所持有的实例Map中去
    // @Provides // 注意这个标注，持有
    @Binds // 这个是绑定
    @SubMapKey(type = ESubType.TYPE_DB, index = 1)// 定义Map的Key，这等于是在告诉Dagger，对应这个key的value用`DataBaseComponent.Builder`这个实例去替换
    public SubComponentBuilder dataBaseComponentBuilder(DataBaseComponent.Builder impl) {
        return impl;
    }
}

@MapKey(unwrapValue = false)// 定义这个标注的属性为复数位
public @interface SubMapKey { // 自定义标注的登场，
    //ESubType type();
    int type(); // 鉴于狗哥强烈的建议，这里用一种新的方式去替换原有的枚举，谁让枚举消耗实在太大，不适合

    int index();
}

// 狗哥提倡的是代替枚举的比较好的实现
@IntDef({TYPE_DB, TYPE_CACHE, TYPE_ACTIVITY})
@Retention(RetentionPolicy.SOURCE)
public @interface ESubType {

    int TYPE_DB = 0;

    int TYPE_CACHE = 1;

    int TYPE_ACTIVITY = 2;
}

// 定义一个接口去量化SubComponent
public interface SubComponentBuilder<Module, Component> {
    SubComponentBuilder<Module, Component> moduleBuild(Module module); // 提供初始化

    Component build(); // 获取实例
}


@Subcomponent(
        modules = DataBaseComponent.DataBaseModule.class // 当前组件的Module
)
public interface DataBaseComponent { // 如果自己研究会发现这个组件在AppComponent是以单例存在的原因在于`SubComponentBindingModule# @Provides`具体请参考官方文档

    @Subcomponent.Builder // 注意有这个标注的只能是interface或者抽象类
    interface Builder extends SubComponentBuilder<DataBaseModule, DataBaseComponent> {
    }

    @Module
    class DataBaseModule {
        // 定义各种实例绑定

    }
}

// 调用的地方来了
public class MainApplication extends Application implements HasSubComponentBuilders {

    @Inject // 这个map不需要去实例化，Dagger会自己帮你完成实例的过程，并且将DataBaseComponent组件自行存入这个map中，你可以debug试一下，不要问我怎么知道的
    Map<SubMapKey, SubComponentBuilder> subComponentBuilderMap;

    ...
    ...
    ...
    ...

    @Override // 接口实现
    public SubComponentBuilder getSubComponentBuild(ESubType type, int index) {
        SubComponentBuilder builder = subComponentBuilderMap.get(
                SubMapKeyCreator.createSubMapKey(type, index));
        // ? 一个疑问点是，这个Module的实例是否依旧是单例
        if (type == ESubType.TYPE_DB && builder instanceof DataBaseComponent.Builder) {
            // 这是初始化的操作 ，这部分用法是我个人觉得这样会好一些，确保Module的唯一性，方便后期显示调用的时候不需要再新建Module
            // 当然可能每次调用都用新的Module也是一种需求？
            ((DataBaseComponent.Builder) builder)
                    .moduleBuild(new DataBaseComponent.DataBaseModule());
        }
        return builder;
    }
}

// 提供给外部去获取AppComponent当中存有的SubComponent实例的接口，这样做就可以避免在Activity当中再去存有Activity所需的Component了
public interface HasSubComponentBuilders {

    SubComponentBuilder getSubComponentBuild(ESubType type, int index);
}

```

初步用法是上面这个样子的，当然这只是初步使用
伴随代码的更新我会一点一点阐述出这整个框架的魅力

<strong>@Inject 用法需要注意的一个问题</strong>
> 可能刚开始用Dagger的时候，看demo会觉得Inject一下好像就能用了，好神奇神马的，可是当自己写起来的时候，往往这个部分的配置是最难也最不怎么容易理解的部分
> 对于这个部分，我仅说说个人的看法

`Component`获取实例的方式大致可分为两种：

注：这命名是我自己定义的，官方并没有这么解释过。

- 显式获取
> 多数用在Fragment和其他没有自己的生命周期的地方上

    ```
    // 定义组件
    @Singleton // 单例
    @Component(
        module = AComponent.AModule.class
    )
    interface AComponent {

       // build之后你会在`DaggerAComponent`当中找到`Provider<ClassA>`这样的代码，
       // 表示Dagger已经默认将ClassA个ClassA中标记为@Inject的类同时给引入Component中去了
        ClassA provideClassA();

        @Module
        class AModule {
        }
    }

    // 定义需要注入组件的类
    public class ClassA {

        @Inject
        public ClassA(){
        }

        public void doSomething(){
            ...
        }
    }

    ```

    ```
    // 引用的地方
    public class ClassB {

        private AComponent component;

        public void init(){
            component = DaggerAComponent.create();
            test();
        }

        public void test(){
            // 显示调用，由于ClassB 本身并没有注入到AComponent当中去，导致ClassB当中不能直接使用AComponent当中已有的实例
            // 直接通过AComponent所暴露出的接口进行访问
            ClassA classA = component.provideClassA();
            classA.doSomething();
        }
    }

    ```


- 隐式获取
> 多数用在Activity当中


    ```
    // 定义组件
    @Singleton // 单例
    @Component(
        module = AComponent.AModule.class
    )
    interface AComponent {

       // build之后你会在`DaggerAComponent`当中找到`Provider<ClassB>`和`Provider<ClassA>`这样的代码，
       // 表示Dagger已经默认将ClassB当中标记为@Inject的类同时给引入Component中去了
       void inject(ClassB classB);

        @Module
        class AModule {
        }
    }

    // 定义需要注入组件的类
    public class ClassA {

        @Inject
        public ClassA(){
        }

        public void doSomething(){
            ...
        }
    }

    ```

    ```
    // 引用的地方
    public class ClassB {

        private AComponent component;

        @Inject // Inject有连带性，有点一人升天，全家齐飞的敢叫
        ClassA classA;

        public void init(){
            component = DaggerAComponent.create();
            component.inject(this); // 这里是将ClassB 注入到组件AComponent当中去，经由这一步操作之后，ClassB就可以很放心的直接使用AComponent当中所持有的类
            test();
        }

        public void test(){****
            // 无需在意实例的创建，直接使用
            classA.doSomething();
        }
    }

    ```
### UI - Data (Net or DB or Cache) 界面-数据(网络、数据库、缓存) 
> 介绍过`Dagger2`基本用法之后，大致可以开始编写代码部分了，App界面呈现不外乎两个基本概念，界面-数据(网络、数据库、缓存) 
> 这里就大致介绍下，这一系列的部分在框架中是如何实现的
- 界面
> 就如前面框架所介绍的，界面层级的代码全都在presenter当中，其中包括各种Activity和Fragment代码
> 同时界面也是所有数据请求的入口处，沿着这个思路，我们看代码如下


[CJMainActivity.java](/presentation/src/main/java/com/sola/github/dagger2demo/ui/cj_demo/CJMainActivity.java)

```

public class CJMainActivity extends ACJBaseActivity {
    
    ...
    ...
    
    
    @Override
    protected void doAfterView() {
        ....
        getPresenter().requestUserInfo("",
                iRecyclerViewDelegate -> adapter.addItem(iRecyclerViewDelegate),
                errorDTO -> getToastUtils().makeToast(this, "测试数据", Toast.LENGTH_SHORT));
    }
    
    /** 其中getPresenter方法是根据获取父类当中持有的Presenter对象 **/
    
    @Inject
    CJPresenter presenter;
    
    public CJPresenter getPresenter() {
        return presenter;
    }

    ...
    ...
    
}

```

[CJPresenter.java](/presentation/src/main/java/com/sola/github/dagger2demo/presenter/CJPresenter.java)

```

public class CJPresenter{
    
    private final AUserCenterCase userCenterCase;

    @Inject
    CJPresenter(
            AUserCenterCase userCenterCase) {
            // 隐式注入，获取对应的UC的业务Case实例，通过前面对于Dagger2的介绍，这里对应的实现实例是UserCenterCaseImpl.class
        this.userCenterCase = userCenterCase;
    }
    
    public void requestUserInfo(
            String userId,
            Action1<IRecyclerViewDelegate> onNext,
            Action1<ErrorDTO> onError) {
        userCenterCase.requestUserInfo(userId,
                // 参数二是对回调回来的接口数据做处理，转换成对应界面所需求的数据结构
                userInfoDTO -> onNext.call(new UserInfoViewDTO(userInfoDTO)), // 这里后续View相关的部分会解释出这里ViewDTO的意义
                onError);
    }
    
}

```

具体的实例绑定请参照如下代码

[CompoundJumpActivityComponent.java](/presentation/src/main/java/com/sola/github/dagger2demo/di/subs/CompoundJumpActivityComponent.java)

```
@CJScope
@Subcomponent(
        modules = CompoundJumpActivityComponent.CompoundJumpModule.class
)
public interface CompoundJumpActivityComponent {

    String TAG = "Sola/CJComponent";

    @Subcomponent.Builder
    interface Builder extends SubComponentBuilder<CompoundJumpModule, CompoundJumpActivityComponent> {

    }

    @Module
    class CompoundJumpModule {

        public CompoundJumpModule() {
            LogUtils.i(TAG, "CompoundJumpModule() called");
        }

        @Provides
        //@Singleton // 这个标注其实是一个非常值得深思的一个点，这代表实例的生命周期跟随那一部分
        // 这里到底是跟随整个Component还是跟随组件中的一个Activity，这就是一个问题所在
        @CJScope
            // 有待测试
        AUserCenterCase provideUserCenterCase(UserCenterCaseImpl impl) {
            return impl; // 实例绑定关键代码
        }

    }
  
    ......
}

```

然后目光转回实现

[UserCenterCaseImpl.java](/domain/src/main/java/com/sola/github/domain/cases/impl/UserCenterCaseImpl.java)

```

public class UserCenterCaseImpl extends AUserCenterCase {

    private final UserCenterRepository userCenterRepository;

    @Inject
    UserCenterCaseImpl(
            NetExecutorThread threadExecutor,
            UIExecutorThread postExecutionThread,
            ErrorDelegate errorPresenter,
            UserCenterRepository userCenterRepository 
            // 这里参考前面获取AUserCenterCase实例的方式，这里对应所绑定的实例是`UserCenterDataRepository.java`
            ) {
        super(threadExecutor, postExecutionThread, errorPresenter);
        this.userCenterRepository = userCenterRepository;
    }

    @Override
    public void requestUserInfo(String userId, Action1<UserInfoDTO> onNext, Action1<ErrorDTO> onError) {
        execute(
            userCenterRepository.requestUserInfo(userId), // 这行代码是关键
            onNext, // 成功回调，数据的响应全都是通过回调的形式返回给界面层
            getErrorAction(onError)); // 失败回调处理
    }
}

```

[AppModule.java](/presentation/src/main/java/com/sola/github/dagger2demo/di/app/AppModule.java)

```

@Module
public class AppModule {

    ...
    ...
    
    @Provides
    @Singleton
    UserCenterRepository provideUserCenterRepository(
            UserCenterDataRepository repository) { 
            // 这里可以切换实例的绑定可以在一个适当的时间将demo实例废弃
        return repository;
    }
    
    ...
    ...
    
}

```

PS：可能有人会奇怪为什么这两个实例绑定的地方不一样，原因在于两者的生命周期依附对象不同，这段话要理解需要自己去琢磨，算是Dagger2精髓的一个地方。


最后实现点如下

[UserCenterDataRepository.java](/data/src/main/java/com/sola/github/data/repository/UserCenterDataRepository.java)

```

public class UserCenterDataRepository
 extends AConnectionRepository implements UserCenterRepository {
 
    @Inject
    UserCenterDataRepository(
            @HttpsRestAdapter AApiConnection apiConnection // 注意这里是https请求
            ) {
        super(apiConnection);
    }
    
    @Override
    public Observable<UserInfoDTO> requestUserInfo(String userId) {
        // 网络请求
        return apiConnection.createService(
                FireBaseAuthService.BASE_URL, FireBaseAuthService.class) // 网络请求
                .requestDaggerUserInfo("user_dagger.json")// 请求对应接口
                .flatMap(this::defaultErrorMapper)// 处理回调，通配处理，这步可以不要
                .flatMap(fireBaseUserInfoEntity -> 
                        Observable.just(transform(fireBaseUserInfoEntity))); 
                        // 处理回调数据，进行数据转换，如果觉得这步多余可以直接用DTO，个人建议利用Entity转换成DTO的形式
    }
    
     private UserInfoDTO transform(FireBaseUserInfoEntity entity) {
        return ....;
     }
 
}

```

- 数据(网络、数据库、缓存) 
> 数据的获取方式，粗略的概括一下就三种方式，网络请求、数据库、缓存，在这里我首先介绍一种Net获取数据的方式
> Net 网络通讯，在这里我利用`Retrofit2.0`和`RxJava`的配合帮助我去完成Api服务的请求


<strong>网络请求</strong>
        
请求工具类，提供请求方法入口
[ApiHttpsConnection.java](/data/src/main/java/com/sola/github/data/net/ApiHttpsConnection.java)

```

public class ApiHttpsConnection extends AApiConnection {
    
    private OkHttpClient httpClient;
    
    private final Retrofit.Builder builder;

    @Inject
    ApiHttpsConnection(
            Context context,
            ContextUtils contextUtils) {
        super(context, contextUtils);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 配置请求返回参数为RxJava的形式
                .addConverterFactory(GSONConverter.create(gson)); // 将Reponse和Request请求的内容进行Gson转换
    }
    
    @Override
    public <S> S createService(String baseUrl, Class<S> serviceCls) {
        buildSSlClient(); // 处理Https协议，这部分代码我还有待完善，这部分代码可以以自身经验为主
        Retrofit retrofit = builder.baseUrl(baseUrl).client(httpClient).build();  // Retrofit 初始化
        return retrofit.create(serviceCls);// Retrofit 启动接口请求代码块
    }
    
    private void checkHttpClient() {
        if (httpClient != null)
            return;
        // 配置Http请求的Client
        httpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(new LoggingInterceptor()) // 添加日志
                .build();
    }
    
    ...
    ...
    
}

```

对应服务接口实现
[FireBaseAuthService.java](/data/src/main/java/com/sola/github/data/net/FireBaseAuthService.java)

```

public interface FireBaseAuthService {
    // 这里用了Google提供的Firebase的Api去做数据请求
    // Firebase这东西是个神器，方便快捷，构建速度快，虽然国内要用的话……比较尴尬
    String BASE_URL = "https://solatest-d36e7.firebaseio.com/";

    @GET("/users/{userId}") // 定义接口请求方式，和路径
    Observable<FireBaseUserInfoEntity> requestDaggerUserInfo(@Path("userId") String userId); // 注意这个方法的返回直接是Observable，这是由于在ApiConnection当中添加了RxJavaCallAdapterFactory了缘故

}

```


<strong>数据库</strong>
这部分代码我还没写


## View相关
> 其实犹豫过，这部分是否要单独拉出来说，这部分的主题是`RecyclerView`，想必很多人都关注过这个，并且去用过这个控件
> `RecyclerView`控件的强大，并不需要我去多描述什么。
> 但是在使用的时候，还是有一些以前ListView的习惯，遗留在这里面，比方说茫茫多的Adapter，Data和View之间的绑定等等
> 嘛，google官方提出的概念是，希望开发人员更多的去使用ViewHolder去做View的构建，在代码上更好的实现的View和Data的分离
> 在使用了蛮长一段时间的这个控件，结合自身的一些习惯，个人整理了一些用法，在此分享下

### Adapter
> `RecyclerView`的核心离不开`Adapter`，适配器是呈现界面的决定因素，`Adapter`当中界面元素和Data数据的绑定是一切的根源

`RecyclerView`提供了一个基本的`Adapter`类
 代码如下:RecyclerView.java
```
/**
 * Base class for an Adapter
 *
 * <p>Adapters provide a binding from an app-specific data set to views that are displayed
 * within a {@link RecyclerView}.</p>
 */
public static abstract class Adapter<VH extends ViewHolder>{

    // 关键方法
    // 根据ViewType创建不同的ViewHolder
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    // 界面绑定，根据position位置，进行Data和Holder之间的绑定
    public abstract void onBindViewHolder(VH holder, int position);

    ……
    ……
    ……
}

```

很明显，狗哥的意思是让我们定义一个Adapter去继承他，然后绑定到对应的RecyclerView当中去
从这个角度看，这和ListView很像，然后套用以前ListView构建的思路，这里应该有很多个不同的Adapter，去适配不同界面
总觉得这样有点麻烦
于是网上出现的更多的会是这样的代码

```

public MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ...
    ....
    .....
    ......

}

public ListAdapter1 extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ...
    ....
    .....
    ......

}


```

诸如此类，一个界面对应一个Adapter这样的写法，个人还是比较反感去这样实现代码，个人推崇一些更加松耦合一些的代码实现方式。
缘由这样的想法，结合自己的研究，整合出现在个人常用的一套方式去构架RecyclerView的界面

下面的实现核心思路，对于我所有的界面而言，我的Adapter只有一个，利用自定义接口`IRecyclerViewDelegate`或者`IRecyclerViewClickDelegate`后者是前者的子类
利用这个接口去通配界面代码的数据结构

核心代码如下

[RecyclerBaseAdapter.java](/tools/src/main/java/com/sola/github/tools/adapter/RecyclerBaseAdapter.java)

```

public class RecyclerBaseAdapter<Param extends IRecyclerViewDelegate>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "Sola";

    private final WeakReference<Context> mContext;

    private OnRecyclerItemClickListener<Param> listener;

    private List<Param> cacheList;

    /**
     * viewType 和 ViewHolder之间的关系序列
     * 由于考量到轻量化的问题，这里并没有以 {viewType : ViewHolder}的方式去做实现
     * 而是采用 {viewType : position}的方式，这里position表示viewType第一次出现的位置
     */
    private SparseIntArray typeRelationship = new SparseIntArray();

    public RecyclerBaseAdapter(
            Context mContext,
            Collection<Param> list
    ) {
        this.mContext = new WeakReference<>(mContext);
        refreshList(list);
    }


    public void setListener(OnRecyclerItemClickListener<Param> listener) {
        this.listener = listener;
    }

    public List<Param> getCacheList() {
        return cacheList;
    }

    public Context getContext() {
        return mContext.get();
    }

   @Override
   public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // 可以看出这里主要是通过不同的类型在cache的数据当中找到对应的代理
       IRecyclerViewDelegate delegate = getItemByViewType(viewType);
       if (delegate == null)
           return null;
       // 返回代理中所实现的ViewHolder
       return delegate.getHolder(mContext.get(), parent, viewType);
   }

   @Override
   public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       // 这里思路和ListView，BindData的思路很像，找到数组中对应position的数据进行数据绑定
       if (position < 0 || position >= cacheList.size())
           return;
       Param item = cacheList.get(position);
       if (item == null)
           return;
       item.refreshView(mContext.get(), holder, position);
       if (listener != null)
           holder.itemView.setOnClickListener(v -> listener.onClick(v, item));
   }

   @Override
   public int getItemCount() {
       return cacheList == null ? 0 : cacheList.size();
   }

   @Override
   public int getItemViewType(int position) {
       if (cacheList == null || cacheList.size() == 0)
           return -1;
       else {
           int viewType = cacheList.get(position).getViewType(position);
           if (typeRelationship.indexOfKey(viewType) < 0) {
               // 入口处，注意这里的实现是确保唯一性，只有第一次发现新的ViewType的时候才记录当前位置
               typeRelationship.put(viewType, position);
           }
           return viewType;
       }
   }

   ....

}

```

具体的界面实现类

[BBSDataViewDTO.java](/presentation/src/main/java/com/sola/github/dagger2demo/ui/params/BBSDataViewDTO.java)

```

// 数据和界面的绑定是由继承这个抽象类的实例去实现的
public abstract class BaseViewDTO<T> implements IRecyclerViewDelegate {

    protected T data; // 界面所需要绑定的数据

    BaseViewDTO(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int getViewType(int position) {
        // 为了确保每次的Type唯一，这里用了这种统一生成id的方式
        return TypeBuilder.getInstance().generateId();
    }

}


public class BBSDataViewDTO extends BaseViewDTO<BBSDataDTO> implements IRecyclerViewDelegate {

    public BBSDataViewDTO(BBSDataDTO data) {
        super(data);
    }

    @Override
    public RecyclerView.ViewHolder getHolder(Context context, ViewGroup parent, int viewType) {
        // 界面的初始化
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.xxx, parent, false));
    }

    @Override
    public void refreshView(Context context, RecyclerView.ViewHolder holder, int position) {
        if (data == null)
            return; // 这样做会在界面复用的时候有一些问题
        ViewHolder viewHolder = (ViewHolder) holder;

        // 界面控件和数据进行绑定
    }

    private class ViewHolder extends RecyclerView.ViewHolder {


        TextView ....;

        ViewHolder(View itemView) {
            super(itemView);
            // 这里不用BindView的原因在于，BindView的使用需要public这个ViewHolder，这样会有一个警告，看上去不爽
            // 控件的初始化
        }
    }

}

```

调用的地方

[MainActivity.java](/presentation/src/main/java/com/sola/github/dagger2demo/ui/MainActivity.java)

```

public class MainActivity extends RxBaseActivity {

    @BindView(R.id.id_recycler_view)
    RecyclerView id_recycler_view;

    // RecyclerView的适配器
    RecyclerBaseAdapter<BaseViewDTO<BBSDataDTO>> adapter;

    @Override
    protected void doAfterView() {
        ...
        ...
        // RecyclerView 的基础配置代码
        id_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        id_recycler_view.setItemAnimator(new DefaultItemAnimator());
        id_recycler_view.addItemDecoration(new LinearDecoration(
                getContext(),
                LinearDecoration.VERTICAL_LIST).setMargins(
                DensityUtil.dip2px(getContext(), 50), 0,
                DensityUtil.dip2px(getContext(), 10), 0));

        adapter = new RecyclerBaseAdapter<>(getContext(), null);// 这里可以大胆的在初始化的时候数据给空，并不影响界面
        id_recycler_view.setAdapter(adapter);

        ...
        ...
    }


    private void requestData() {
        mainPresenter.requestMainListData(1, 20,
                viewDTOs -> // 这是请求数据返回的结果
                        adapter.refreshList(viewDTOs), // 这一行代码是具体的adapter数据绑定的入口
                errorDTO -> Toast.makeText(
                        getContext(), errorDTO.getErrorMessage(), Toast.LENGTH_SHORT).show());
    }

}

```

[MainPresenter.java](/presentation/src/main/java/com/sola/github/dagger2demo/ui/presenter/MainPresenter.java)

```

@ActivityScope
public class MainPresenter implements IPresenter {

    private final ABBSCase abbsCase;

    @Inject
    MainPresenter(ABBSCase abbsCase) {
    	// 通过隐式注入的方式，将实例传输到这里，并且供内部调用
        this.abbsCase = abbsCase;
    }

    public void requestMainListData(
            int pageCount, int pageSize,
            Action1<Collection<BaseViewDTO<BBSDataDTO>>> onNext,
            Action1<ErrorDTO> onError) {
	    // 
        abbsCase.searchBBSList(
                pageCount, pageSize,
                bbsDataDTOs -> onNext.call(transform(bbsDataDTOs)),
                onError);
    }

    private Collection<BaseViewDTO<BBSDataDTO>> transform(Collection<BBSDataDTO> bbsDataDTOs) {
        Collection<BaseViewDTO<BBSDataDTO>> retList = new LinkedList<>();
        if (bbsDataDTOs != null) {
            for (BBSDataDTO dto :
                    bbsDataDTOs) {
            // 核心代码是这段，这里是在list数组中插入了BBSDataViewDTO的数据集合,配合MainActivity中的adapter.refreshList(viewDTOs)，界面刷新完成
                retList.add(new BBSDataViewDTO(dto));
            }
        }
        return retList;
    }

}

```

以上是我个人惯用的RecyclerView的构建方式，仅供参考



## 附页

### Domain-driven design 相关

- 基础
    领域驱动设计告诉我们，在通过软件实现一个业务系统时，建立一个领域模型是非常重要和必要的，因为领域模型具有以下特点：
    - 领域模型是对具有某个边界的领域的一个抽象，反映了领域内用户业务需求的本质；领域模型是有边界的，只反应了我们在领域内所关注的部分；
    - 领域模型只反映业务，和任何技术实现无关；领域模型不仅能反映领域中的一些实体概念，如货物，书本，应聘记录，地址，等；还能反映领域中的一些过程概念，如资金转账，等；
    - 领域模型确保了我们的软件的业务逻辑都在一个模型中，都在一个地方；这样对提高软件的可维护性，业务可理解性以及可重用性方面都有很好的帮助；
    - 领域模型能够帮助开发人员相对平滑地将领域知识转化为软件构造；
    - 领域模型贯穿软件分析、设计，以及开发的整个过程；领域专家、设计人员、开发人员通过领域模型进行交流，彼此共享知识与信息；因为大家面向的都是同一个模型，所以可以防止需求走样，可以让软件设计开发人员做出来的软件真正满足需求；
    - 要建立正确的领域模型并不简单，需要领域专家、设计、开发人员积极沟通共同努力，然后才能使大家对领域的认识不断深入，从而不断细化和完善领域模型；
    - 为了让领域模型看的见，我们需要用一些方法来表示它；图是表达领域模型最常用的方式，但不是唯一的表达方式，代码或文字描述也能表达领域模型；
    - 领域模型是整个软件的核心，是软件中最有价值和最具竞争力的部分；设计足够精良且符合业务需求的领域模型能够更快速的响应需求变化；





[1]:https://en.wikipedia.org/wiki/Domain-driven_design

# IntentManager
## Android 界面之间跳转工具



<div align="center"><image src="https://github.com/Golabe/IntentManager/blob/master/gif/GIF.gif?raw=true" width="260"/></div>
### 使用

gradle


简单
```java
   IntentGo
                .with(this)
                .target(Main2Activity.class)
                .go();
```
参数
```java
  IntentGo
                .with(this)
                .target(Main3Activity.class)
                .params("username", "allen")
                .params("age", 12)
                .params("userInfo", new User("allen", 12))
                .params("id", 2000000L)
                .params("float", 0.5F)
                .params("bool", false)
                .go();
```
有返回值
```java
 IntentGo
                .with(this)
                .targetForResult(Main3Activity.class, 2000)
                .params("username", "allen")
                .params("age", 12)
                .params("userInfo", new User("allen", 12))
                .params("id", 2000000L)
                .params("float", 0.5F)
                .params("bool", false)
                .go();
```

共享动画
```java
 IntentGo
                .with(this)
                .targetForResult(Main3Activity.class, 2000)
                .params("username", "allen")
                .params("age", 12)
                .params("userInfo", new User("allen", 12))
                .params("id", 2000000L)
                .params("float", 0.5F)
                .params("bool", false)
                .makeSceneTransitionAnimation(new Pair<>(view, "BUTTON"))
                .go();
```


返回数据给上一个界面
```java
  IntentResult
                .with(this)
                .params("username","allen")
                .result(RESULT_OK);
```

#### 支持类型
String,Float,Integer,Serializable,Parcelable,Long


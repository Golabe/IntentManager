# IntentManager
## Android 界面之间跳转工具

### 新增 overridePendingTransition方法
- 当overridePendingTransition和makeSceneTransitionAnimation一起使用时，SDK版本大于等于21时执行makeSceneTransitionAnimation相关动画，反之执行overridePendingTransition相关动画。

<div align="center"><image src="https://github.com/Golabe/IntentManager/blob/master/gif/GIF.gif?raw=true" width="400"/></div>

### 使用

gradle添加
```xml
implementation 'top.golabe.intent.IntentManager:library:1.0.1'

```


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

### 支持类型
#### String
#### Float
#### Integer
#### Long;
#### 对象必须实现Serializable，或者Parcelable


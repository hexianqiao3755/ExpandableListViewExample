___

##### 在日常开发，有可能会遇到需要一些可以展开的列表，比如QQ的好友列表。
##### 但是，用Android的该怎么做呢？其实在我没有接触过`ExpandableListView`类之前，
##### 都是自定义控件继承于`ListView`或者`ListView`嵌套`ListView`来实现的, 
##### 当然也不推荐大家这样使用, 这样使用**会降低app页面的加载速度和性能**.  然后，
##### 后来发现了`Android`也给我们提供了`ExpandableListView`类，完美实现这样类似的需求, 极大的方便了我们开发.

Demo来自某个线上电商app的页面, 使用前先看看效果图
![demo演示](https://github.com/hexianqiao3755/ExpandableListViewExample/blob/master/example.gif)

#### 1. 如果子条目需要响应click事件,必需返回true
```
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
```

#### 2.  去除掉默认的关闭和打开状态的图片(系统默认的不好看)
```
android:groupIndicator="@null"
```

#### 3.  解决无法正常展开问题
```
//HorizontalScrollView
hsv_goods_list.setFocusable(false)
```

## 反馈
欢迎各位提issues和PRs

## 第三方库
- [Fresco](https://github.com/facebook/fresco)
- [AndroidAutoLayout](https://github.com/hongyangAndroid/AndroidAutoLayout)

## 联系我
_hexianqiao3755@gmail.com_

## 许可证

    Copyright 2017 He Qiao

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
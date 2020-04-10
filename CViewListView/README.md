## 通讯录案例知识点
1. ListView控件的使用  
	布局中添加ListView控件，并且必须标明**ID属性**，编写每个条目的布局。
2. 适配器类  
	编写适配器类继承自BaseAdapter(Abstract Class)，当然也可以去继承它的实现类ArrayAdapter和SimpleAdapter，重写父类基本方法。为了对列表数据优化，提高加载效率，使用静态内部类``ViewHolder``和ConvertView的setTage和getTag方法实现代码的优化。
3. ListView点击事件和滑动事件的监听  
	主要重写onScrollStateChangeListener和onItemClickListener实现监听。  

## 圆形ImageView控件的实现  
主要借助CircleImageView实现对ImageView控件的重新绘制，具体看代码。

ListView使用流程代码中有相应注释。这里不再展示  

![案例演示](./app/src/main/res/drawable/list.png) 





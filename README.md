
## android案例源码记录
- zgmooc案例演示默认放在项目根路径下后缀名为.md的文件
- RecyclerView 
 + **1.0版本**，用RecyclerView主要实现了ListView和GridView效果，代码核心思想布局管理器LayoutManager和Adapter，布局管理器主要实现类有`LinearLayoutManger`、`GridViewLayoutManger`、`StaggerLayoutManager`;适配器Adapter继承`RecyclerView.Adapter<VH extends ViewHolder>`，一般用内部类extends `RecyclerView.ViewHolder`来创建ViewHolder。  
 + **2.0版本**，多条目类型的实现（今日头条列表布局），借助SwipeRefreshLayout实现下拉刷新，另外对ListViewAdapter进行了整改，实现了上拉加载，但是存在bug，每次刷新都会多刷出一个条目，TODO待解决。上拉加载的实现原理和多条目类型一样，内部适配器存在多个ViewHolder，根据`getItemViewType int`的返回值来确定加载哪一个布局，然后通过onBindViewHolder内部对ViewHolder进行实例类型的判断，来决定去做什么，是加载正常数据到Item还是显示刷新布局。


## android案例源码
- 截图默认放在images文件夹下，android studio切换到project files视图下就可以看到images目录
- RecyclerView 
 1.0版本，用RecyclerView主要实现了ListView和GridView效果，代码核心思想布局管理器LayoutManager，主要实现类LinearLayoutManger、 
 GridViewLayoutManger、StaggerLayoutManager;适配器Adapter继承RecyclerView.Adapter<VH extends ViewHolder>，一般用内部类extends ViewHolder实现布局控件 
 的查找、回调事件的监听等。
 2.0版本，
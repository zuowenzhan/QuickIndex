# QuickIndex
自定义快速索引


 ### 快速索引的实现：
 #### 1.布局的设置。
    如何获取自定义view的宽度、高度，具体一个字符的宽度、高度，包裹字符的边框的宽度、高度
 #### 2.通过onTouchEvent()，得到当前操作的index,并且在onDraw()，对指定的index重绘
 #### 3.如何自定义监听，使用在QuickIndex上。
  实现：当操作的QuickIndex中的index改变时，相应的MainActivity中的TextView改变其内容
       当执行MotionEvent的ACTION_UP时，指定相应的TextView以延迟2s的方式消失
      （需要考虑到消息的移除）
 #### 4.显示联系人的列表（ListView + BaseAdapter + List + item Layout)
     使用现成的CommonBaseAdapter + ViewHolder 实现数据的加载显示
 #### 5.指定的字符对应的集合中的数据，显示在列表的开头：
    调用ListView的setSelection(int position);将position位置的item显示在开头

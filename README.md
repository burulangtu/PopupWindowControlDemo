# PopupWindowControlDemo

PopupWindow 控制一共有两种方式

    showAtLocation //自己控制 popupWindow 的位置
    showAsDropDown //根据锚 view 显示 popupwindow 的位置
    
#### 先说 showAsDropDown 。一共有三个 api 

    showAsDropDown(View anchor)
    showAsDropDown(View anchor, int xoff, int yoff)
    showAsDropDown(View anchor, int xoff, int yoff, int gravity)
    
***第三种方法最低 api 19 ,所以使用的不多。***

1. showAsDropDown(View anchor)

    是直接显示在『锚 view 』的左下方。当 popupWindow 位置超出屏幕的时候，会留在屏幕边缘。
    
1. showAsDropDown(View anchor, int xoff, int yoff)
   
   popupWindow 显示在『锚 view 』的左下方。同时受到 xoff,yoff 两个偏移量的控制。右方和下方为正，左方和上方为负。当 popupWindow 超出屏幕时，有可能会显示不出来，或者页面被挤压。
 
1. showAsDropDown(View anchor, int xoff, int yoff, int gravity)
    
   popupWindow 显示在 『锚 view 』的下方，受到 xoff,yoff 和 gravity 的控制。gravity 的值为 Gravity.Left,Gravity.Right （其他值也会有作用，但是最后都是等同这两个值中的一个）。
   当 gravity 为 Gravity.Left 的时候，和 showAsDropDown(View anchor, int xoff, int yoff) 效果一样
   当 gravity 为 Gravity.Right 的时候，测试显示有问题（测试机器，小米3s,android 6.0.1）
   ***(不建议使用方法)***

### showAtLocation 

showAtLocation 相对于 showAsDropDown 更复杂但是也更灵活控制。

先看一下源码
    
    public void showAtLocation(View parent, int gravity, int x, int y) {
        showAtLocation(parent.getWindowToken(), gravity, x, y);
    }
    
从源码可以看出，第一个参数只要是当前页面的 view 提供一个 windowToken 就可以了。表示 popupWidow 可以显示当前屏幕上。

x,y 分别表示 popupWindow 的位置。gravity 表示重力方向。*** x,y 值受到 gravity 的控制***

gravity 可以单独使用，也可以组合使用。Gravity 有很多的值，对 popupWidow 有效果的可以分为下面几组，***每种情况的原点位置都不同，一定要注意坐标系***

***如果位置超过屏幕范围，会自动留在屏幕边缘。***

##### 基础组
   
   基础组包括：Gravtiy.LEFT,Gravity.TOP,Gravity.RIGHT,Gravity.BOTTOM,Gravity.NO_GRAVITY,Gravity.CENTER
       
    Gravtiy.LEFT,   原点位置 （屏幕左侧，屏幕中央）
                    x 往右为正，往左为负
                    y 往下为正，往上为负
                    
    Gravity.TOP,    原点位置 （屏幕中央,屏幕上侧）
                    x 方向，右侧为正，左侧为负
                    y 方向，往下为正，往上为负
                    
                    
    Gravity.RIGHT,  原点位置 （屏幕右侧，屏幕中央）
                    x 方向，左侧为正，右侧为负
                    y 方向，往上为正，往下为负
                    
    Gravity.BOTTOM 原点位置 （屏幕中央,屏幕上侧）
                    x 方向，右侧为正，左侧为负
                    y 方向，往上为正，往下为负
    
    Gravity.NO_GRAVITY, 原点位置 屏幕左上角
                    x 方向，右侧为正，左侧为负
                    y 方向，往下为正，往上为负
    Gravity.CENTER ,原点位置 屏幕中央
                    x 方向，右侧为正，左侧为负
                    y 方向，往下为正，往上为负
   
##### 两两组合

    Gravtiy.LEFT | Gravtiy.TOP, 原点位置 屏幕左上角
                                x 方向，右侧为正，左侧为负
                                y 方向，往下为正，往上为负
   
    Gravtiy.LEFT | Gravtiy.BOTTOM, 原点位置 左下角
                                x 方向，右侧为正，左侧为负
                                y 方向，往上为正，往下为负

    Gravtiy.RIGHT | Gravtiy.TOP,原点位置 右上角
                                x 方向，左侧为正，右侧为负
                                y 方向，往下为正，往上为负
    
    Gravtiy.RIGHT | Gravtiy.BOTTOM,原点位置 右下角
                                x 方向，左侧为正，右侧为负
                                y 方向，往上为正，往下为负
    
    Gravtiy.LEFT | Gravtiy.RIGHT,原点位置 屏幕左侧中间
                                x 方向，x 值没有效果
                                y 方向，往下为正，往上为负
    
    Gravtiy.BOTTOM | Gravtiy.TOP,原点位置 屏幕上方中间
                                x 方向，右侧为正，左侧为负
                                y 方向，y值没有效果


#### showAtLocation  实现 showAsDropDown

配合 View 的 getLocationOnScreen(@Size(2) int[] outLocation) 方法，获得 『锚 view 』在屏幕的位置，View 的 getHeight() 、getWidth() 获得 『锚 view 』的宽高。配合不同的 Gravtiy 值进行定位。必要的时候可以配合 context.getResources().getDisplayMetrics().widthPixels;和context.getResources().getDisplayMetrics().heightPixels;  计算屏幕宽高。

使用的时候，可能受到『锚 view 』的 padding 值影响



    
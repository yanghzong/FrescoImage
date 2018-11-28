package com.zemu.fresco.note;

/**
 * Created by 择木 on 2018/11/28.
 */

public class note {

/*

    http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650820929&idx=1&sn=914622a161e1132827b364aa86b13aca&mpshare=1&scene=23&srcid=1117fb0LE6AzERFglD25fz5x#rd

    hss01248的博客地址：

    http://blog.csdn.net/hss01248

            1
    简介
[Fresco](https://github.com/facebook/fresco) 是Facebook开源的安卓上的图片加载框架,也可以说是至今为止安卓上最强大的图片加载框架。


    相对于其他几个图片加载框架,Fresco主要的优点在于更好的内存管理和更强大的功能,更便捷的使用,缺点则是体积比较大,引入后会导致应用apk增加1.5M到2M的大小,但是相对于其便捷性来讲,我觉得这都不是事儿.


    优点一:内存管理

    对于5.0以下系统,fresco使用”ashmem”（匿名共享内存）区域存储Bitmap缓存，这样Bitmap对象的创建、释放将不会触发GC,不会占用javaheap.这个特点是其他图片加载框架所没有做到的.

               5.0以上系统，由于安卓系统本身内存管理的优化，所以对于5.0以上的系统Fresco将Bitmap缓存直接放到了javaheap内存中.

            并且,fresco实现了真正的三级缓存:两级的内存缓存+一个磁盘缓存.两个内存缓存为:bitmap缓存 和未解码的图片缓存,这样既可以加快图片的加载速度,又能节省内存的占用.这个两级的内存缓存也是其他图片加载框架所没有做到的.

            另外提一点,在app切换到后台时,fresco会自动清理两级的内存缓存,无需手动.

            通过以上几点,使用fresco加载图片时内存占用要比其他图片加载框架小一大半,基本很少发生oom的事情.

            几个图片加载框架的内存占用测试结果对比请戳这里:

            •Android Image Loader 第三方库对比测试(http://m.blog.csdn.net/article/details?id=46959649)

            优点二:更便捷的使用:

            初期入门,轻度的图片加载,甚至可以直接用:

        Fresco.initiliaze(context);

<com.facebook.drawee.view.SimpleDraweeView
    android:id="@+id/my_image_view"
    android:layout_width="20dp"
    android:layout_height="20dp"
    fresco:placeholderImage="@drawable/news_default"
            .../>

            simpleDraweeView.setImageURI(uri);

    其他都不用管,fresco自动帮我们做缓存,图片缩略.

            显示圆角或圆圈图片也只是xml中配置一下而已:
    圆圈 - 设置roundAsCircle为true
    圆角 - 设置roundedCornerRadius

    另外再说一句,fresco还支持webp,所以,splash和引导界面的大图我一般都是用智图(http://zhitu.isux.us/)压成webp放在drawable中,用fresco加载就行了.

            2

            相关文档及开源库

            •[官方文档中文版](http://fresco-cn.org/)


            •[中文的Fresco源码解读,分析版本：0.7.0](https://github.com/desmond1121/Fresco-Source-Analysis)

            •[fresco里的photoview :用于查看大图并随手势缩放](https://github.com/ongakuer/PhotoDraweeView)

            •[bilibili开源的借助fresco加载图片的 spannable text view : Bilibili/drawee-text-view](https://github.com/Bilibili/drawee-text-view)

            •fresco的bitmap后处理器封装,可以直接使用](https://github.com/wasabeef/fresco-processors)


        3

        使用心得及一些方法的封装

        3.1 加载大图

        首先,终极的解决方法肯定是,客户端在图片请求中带上需要的宽和高,服务器将图片缩略到该规格后返回该小图.这个做得比较好的是七牛.

        注意:不管服务器能不能返回缩略图,所存储的原图都不应该太大,有时图片太大,甚至都无法下载下来(报504之类的错误).

       •那么,如果服务器只能拿到原图或大图,fresco怎么缩略显示?



    fresco中提供了三个功能来[生成缩略图](http://www.fresco-cn.org/docs/resizing-rotating.htm):


•Scaling :画布操作,通常是由硬件加速的。图片实际大小保持不变，它只不过在绘制时被放大或缩小.使用时需要配置缩放类型fresco:actualImageScaleType,具体类型名与Imageview的ScaleType几乎一样.


•Resizing 是一种软件执行的管道操作。它返回一张新的，尺寸不同的图片,也就是说直接改变bitmap的大小,可惜是单独使用时,只支持jpg,当然,结合Downsampling使用时,可以支持除gif以为的所有常见图片,包括webp.


•Downsampling 同样是软件实现的管道操作。它不是创建一张新的图片，而是在解码时改变图片的大小。 同样是软件实现的管道操作。它不是创建一张新的图片，而是在解码时改变图片的大小。类似于android中的BitmapFactory在decodefile时的inSampleSize,都是指定一个采样率,默认是关闭的,如果开启,那么需要结合Resizing来使用.


综上,要缩小内存占用,以及减少cpu计算量,减少卡顿,应该是Downsampling结合Resizing来使用.其中Downsampling是在Fresco初始化时开启,而Resizing则是通过构建ImageRequest时通过制定宽高来实现,所以可以定制每一张或每一类图片的宽高. 示例代码如下:


    初始化:


    利用SimpleDraweeView加载图片的一般姿势:


    注意,我这里没有去设置DraweeHierarchy,因为依照fresco的设计思维,DraweeHierarchy属于view层次的东西,应该在xml中配置.当然如果非要设置,请看这里(http://fresco-cn.org/docs/using-drawees-code.html).


     3.2 缩放图片的技巧

    如何避免显示图片时把人的脸部截掉。


    这个就要用到Scaling了.图片的缩放拉伸以及裁剪模式.具体看文档(http://fresco-cn.org/docs/scaling.html#_)

            可用的缩放类型:
36
            center

            居中，无缩放。

            centerCrop

            保持宽高比缩小或放大，使得两边都大于或等于显示边界，且宽或高契合显示边界。居中显示。

            focusCrop

            同centerCrop, 但居中点不是中点，而是指定的某个点。

         centerInside

         缩放图片使两边都在显示边界内，居中显示。和 fitCenter 不同，不会对图片进行放大。如果图尺寸大于显示边界，则保持长宽比缩小图片。

        fitCenter

        保持宽高比，缩小或者放大，使得图片完全显示在显示边界内，且宽或高契合显示边界。居中显示。

        fitStart

       同上。但不居中，和显示边界左上对齐。

       fitEnd

       同fitCenter， 但不居中，和显示边界右下对齐。

         fitXY

       不保存宽高比，填充满显示边界。

         none

          如要使用tile mode显示, 需要设置为none




         这些缩放类型和Android ImageView 支持的缩放类型几乎一样.




            图片默认是centerCrop,那么在用一个横向的SimpleDraweeView来显示一张竖着拍的人像时,就很可能把人的头部给截掉了,但对于在listview中展示的SimpleDraweeView来说,我们又无法用focusCrop直接指定其居中点在图片上半部分某个地方,因为其他图片可能是横着拍的或很正方形的自拍,这个时候怎么办?




        就需要根据人脸的检测来设置focusCrop的那个点了,而android从sdk 1.0开始就已经提供了一个人脸识别的类FaceDetector(http://www.cnblogs.com/mainroadlee/p/android_sdk_face_detection.html),原理是通过找眼睛来识别人脸,可以拿到眼睛的中心点坐标,那么根据该坐标,结合图片本身的宽高,计算出针对每张图片的focusCrop 需要设置的点,就能够解决这个问题了.




//TODO 这个还没有去写方法,但有一个开源项目facecropper(https://github.com/lafosca/AndroidFaceCropper/blob/master/FaceCropper-library/src/main/java/cat/lafosca/facecropper/FaceCropper.java)可以参考,他们的做法是将一个大的bitmap截图成小的bitmap。






       3.3 获取缓存的图片文件




        对于内存的缓存,fresco根据图片Uri,以及图片的resising参数和processor参数综合生成缓存key来缓存bitmap,而磁盘文件缓存则是只根据Uri生成key,那么,如果要获取文件缓存,只需要知道uri和通过key取file的api就行了,原先的调用链较长,




故封装成单个方法:



      需要注意的是文件名后缀不是普通的图片后缀(.jpg之类的),而是.cnt,但都是二进制文件,可以将文件直接拷贝到指定路径重命名成正常图片后缀即可.当然如果只是读取到内存做其他用途,可以直接读取,无需拷贝更改后缀,不影响使用.

      以下是读取缓存文件的方法.而拷贝到其他文件目录的方法也已封装好于FrescoUtils中.

3.4 直接操作bitmap

    由于某种原因无法使用SimpleDraweeView来显示图片(比如说弹幕上显示头像),而需要直接操作bitmap,那么要怎么拿到url返回的bitmap?

    自己构建图片请求,然后类似上面的文件下载,还是采用DataSubscriber来监听回调,只不过返回的不是void,而是CloseableImage的bitmap,

    注意,此bitmap对象的缓存由Fresco管理,所以不要去调用bitmap.recycle()之类的方法.

            对此bitmap的一些处理,如果处理后的bitmap对象还是指向该bitmap引用,会影响到其他同样url,width height,并且同样Postprocessor的图片组件的显示,比如,将该bitmap高斯模糊了,就会影响到其他的这四个参数相同的SimpleDraweeView的显示.

    那么,如果要不影响,怎么办?很简单,让四个参数任一一个不同就行.

3.5 配合ListView

•Fresco中在listview之类的快速滑动时停止加载,滑动停止后恢复加载时调用的API是什么?

    话不多说,直接看代码.

3.6 gif图片圆角化

    gif图片无法显示成圆形,怎么办?(当然有的情况下,jpg也无法显示圆形) –加一层和图片的parentview 背景色一样的圆形遮罩即可:

            3.7 清除缓存

    很多app都有清除磁盘缓存的功能,那么fresco怎么清除缓存呢?

            3.8 ?高斯模糊

    高斯模糊是app里设置一些背景效果 常用到的手段.

    在fresco中,可以通过postprocessor来实现,也可以自己拿到bitmap后将bitmap模糊化后设置到ImageView或SimpleDraweeView(这个不建议,会消除掉SimpleDraweeView的层级结构,变成单纯的ImageView)上.

    推荐前一种: 用到别人封装好的BlurPostprocessor(https://github.com/wasabeef/fresco-processors/blob/master/processors/src/main/java/jp/wasabeef/fresco/processors/BlurPostprocessor.java) :

            掘金是一个高质量的技术社区，从 RxJava 到 React Native，性能优化到优秀开源库，让你不错过 Android 开发的每一个技术干货。长按图片二维码识别或者各大应用市场搜索「掘金」，技术干货尽在掌握中。

            如果你有好的文章想和大家分享，欢迎投稿，直接向我投递文章链接即可。

            欢迎长按下图->识别图中二维码或者扫一扫关注我的公众号：
*/



}
